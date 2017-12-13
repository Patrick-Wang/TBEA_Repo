/// <reference path="util.ts" />
/// <reference path="jqgrid/jqassist.ts" />
///<reference path="dateSelector.ts"/>
var mkt_contract_analysis;
(function (mkt_contract_analysis) {
    var ContractZb;
    (function (ContractZb) {
        ContractZb[ContractZb["hy"] = 0] = "hy";
        ContractZb[ContractZb["htsl"] = 1] = "htsl";
        ContractZb[ContractZb["htje"] = 2] = "htje";
        ContractZb[ContractZb["dyzydbl"] = 3] = "dyzydbl";
        ContractZb[ContractZb["ndsl"] = 4] = "ndsl";
        ContractZb[ContractZb["ndhtje"] = 5] = "ndhtje";
        ContractZb[ContractZb["ndzbl"] = 6] = "ndzbl";
        ContractZb[ContractZb["qnhtsl"] = 7] = "qnhtsl";
        ContractZb[ContractZb["qnhtje"] = 8] = "qnhtje";
        ContractZb[ContractZb["qnzndbl"] = 9] = "qnzndbl";
        ContractZb[ContractZb["htzz"] = 10] = "htzz";
    })(ContractZb || (ContractZb = {}));
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createContractTable4Industry = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("行业", "t0", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("当月情况", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("合同数量", "t11"))
                    .append(new JQTable.Node("合同金额(万元)", "t12"))
                    .append(new JQTable.Node("占月度签约总额比例", "t13")),
                new JQTable.Node("年度累计", "t2", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("合同数量", "t21"))
                    .append(new JQTable.Node("合同金额(万元)", "t22"))
                    .append(new JQTable.Node("占年度签约总额比例", "t23")),
                new JQTable.Node("去年同期累计", "t3", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("合同数量", "t31"))
                    .append(new JQTable.Node("合同金额(万元)", "t32"))
                    .append(new JQTable.Node("占年度签约总额比例", "t33")),
                new JQTable.Node("同比", "t4", false, JQTable.TextAlign.Right, 0, undefined, undefined, false)
            ], gridName);
        };
        JQGridAssistantFactory.createContractTable4Companys = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("项目公司", "t0", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("当月情况", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("合同数量", "t11"))
                    .append(new JQTable.Node("合同金额(万元)", "t12"))
                    .append(new JQTable.Node("占月度签约总额比例", "t13")),
                new JQTable.Node("年度累计", "t2", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("合同数量", "t21"))
                    .append(new JQTable.Node("合同金额(万元)", "t22"))
                    .append(new JQTable.Node("占年度签约总额比例", "t23")),
                new JQTable.Node("去年同期累计", "t3", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("合同数量", "t31"))
                    .append(new JQTable.Node("合同金额(万元)", "t32"))
                    .append(new JQTable.Node("占年度签约总额比例", "t33")),
                new JQTable.Node("同比", "t4", false, JQTable.TextAlign.Right, 0, undefined, undefined, false)
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var View = (function () {
        function View() {
            this.mSelCompanys = [];
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (TableId, dateId, year, month, companyName) {
            this.mCompanyName = companyName;
            this.TableId = TableId;
            this.childTableId = TableId + "_jqgrid_1234";
            this.mDs = new Util.DateSelector({ year: year - 1, month: 1 }, { year: year, month: month }, dateId);
            //请求数据
            this.mDataSet = new Util.Ajax("mkt_contract_analysis_update.do", false);
            this.onType_TypeSelected();
            //if (this.mCompanyName == "股份公司") {
            //    this.onCompanySelected();
            //}
            //this.updateUI();
        };
        View.prototype.onType_TypeSelected = function () {
            this.mAnalysisType = $("#analysisType").val();
        };
        View.prototype.onCompanySelected = function () {
            //this.mCompanyName = $("#comp_category").val();
        };
        View.prototype.exportExcel = function () {
            var dt = this.mDs.getDate();
            $("#exportBidAnalysisData")[0].action = "mkt_contract_analysis_export.do?" + Util.Ajax.toUrlParam({ companyName: JSON.stringify(this.mSelCompanys), year: dt.year, month: dt.month, type: this.mAnalysisType });
            $("#exportBidAnalysisData")[0].submit();
        };
        View.prototype.formatData = function (rowData, integerList, percentList) {
            var outputData = [];
            var formaterChain = new Util.FormatPercentHandler([], percentList.toArray());
            formaterChain.next(new Util.FormatIntHandler([], integerList.toArray()))
                .next(new Util.FormatCurrencyHandler());
            var row = [];
            for (var j = 0; j < rowData.length; ++j) {
                row = [].concat(rowData[j]);
                for (var i = 1; i < row.length; ++i) {
                    row[i] = formaterChain.handle(row[0], i, row[i]);
                }
                outputData.push(row);
            }
            return outputData;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mSelCompanys = [];
            if (this.mCompanyName == "股份公司") {
                $('#comp_category').multiselect("getChecked").each(function (i, item) {
                    _this.mSelCompanys.push($(item).val());
                });
            }
            else {
                this.mSelCompanys.push(this.mCompanyName);
            }
            var parent = $("#" + this.TableId);
            parent.empty();
            parent.append("<table id='" + this.childTableId + "'></table>" + "<div id='pager'></div>");
            var dt = this.mDs.getDate();
            this.mDataSet.get({ companyName: JSON.stringify(this.mSelCompanys), year: dt.year, month: dt.month, type: this.mAnalysisType })
                .then(function (data) {
                var rowBidData = data;
                if (_this.mAnalysisType == "contract_industry") {
                    _this.updateTable(_this.TableId, _this.childTableId, JQGridAssistantFactory.createContractTable4Industry(_this.childTableId), rowBidData);
                }
                else if (_this.mAnalysisType == "contract_company") {
                    _this.updateTable(_this.TableId, _this.childTableId, JQGridAssistantFactory.createContractTable4Companys(_this.childTableId), rowBidData);
                }
            });
        };
        View.prototype.updateTable = function (parentName, childName, tableAssist, rawData) {
            var data = [];
            var integerList = new std.vector();
            var percentList = new std.vector();
            integerList.push(ContractZb.htsl);
            integerList.push(ContractZb.ndsl);
            integerList.push(ContractZb.qnhtsl);
            percentList.push(ContractZb.dyzydbl);
            percentList.push(ContractZb.ndzbl);
            percentList.push(ContractZb.qnzndbl);
            percentList.push(ContractZb.htzz);
            data = this.formatData(rawData, integerList, percentList);
            $("#" + childName).jqGrid(tableAssist.decorate({
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                // autowidth : false,
                //cellsubmit: 'clientArray',
                //cellEdit: true,
                editurl: 'clientArray',
                height: '100%',
                width: $(document).width() - 60,
                shrinkToFit: true,
                autoScroll: true,
                pager: '#pager',
                rowNum: 20,
                viewrecords: true //是否显示行数 
            }));
            if (rawData.length != 0) {
                $("#assist").css("display", "block");
            }
        };
        return View;
    }());
    mkt_contract_analysis.View = View;
})(mkt_contract_analysis || (mkt_contract_analysis = {}));
