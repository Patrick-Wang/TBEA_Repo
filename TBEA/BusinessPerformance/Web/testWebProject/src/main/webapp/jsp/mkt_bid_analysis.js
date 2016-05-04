var mkt_bid_analysis;
(function (mkt_bid_analysis) {
    var Bid4IndustryZb;
    (function (Bid4IndustryZb) {
        Bid4IndustryZb[Bid4IndustryZb["hy"] = 0] = "hy";
        Bid4IndustryZb[Bid4IndustryZb["dytbsl"] = 1] = "dytbsl";
        Bid4IndustryZb[Bid4IndustryZb["dytbje"] = 2] = "dytbje";
        Bid4IndustryZb[Bid4IndustryZb["dyzbje"] = 3] = "dyzbje";
        Bid4IndustryZb[Bid4IndustryZb["dyzydbl"] = 4] = "dyzydbl";
        Bid4IndustryZb[Bid4IndustryZb["ndsl"] = 5] = "ndsl";
        Bid4IndustryZb[Bid4IndustryZb["ndtbje"] = 6] = "ndtbje";
        Bid4IndustryZb[Bid4IndustryZb["ndzbje"] = 7] = "ndzbje";
        Bid4IndustryZb[Bid4IndustryZb["ndzbl"] = 8] = "ndzbl";
        Bid4IndustryZb[Bid4IndustryZb["ndtbbl"] = 9] = "ndtbbl";
        Bid4IndustryZb[Bid4IndustryZb["qntbsl"] = 10] = "qntbsl";
        Bid4IndustryZb[Bid4IndustryZb["qntbje"] = 11] = "qntbje";
        Bid4IndustryZb[Bid4IndustryZb["qnzbje"] = 12] = "qnzbje";
        Bid4IndustryZb[Bid4IndustryZb["qnzbl"] = 13] = "qnzbl";
        Bid4IndustryZb[Bid4IndustryZb["qnzndbl"] = 14] = "qnzndbl";
        Bid4IndustryZb[Bid4IndustryZb["tbzz"] = 15] = "tbzz";
    })(Bid4IndustryZb || (Bid4IndustryZb = {}));
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createBidTable4Industry = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("行业", "t0", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("当月情况", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标数量", "t11"))
                    .append(new JQTable.Node("投标金额(万元)", "t12"))
                    .append(new JQTable.Node("中标金额(万元)", "t13"))
                    .append(new JQTable.Node("占月度投标总额比例", "t14")),
                new JQTable.Node("年度累计", "t2", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标数量", "t21"))
                    .append(new JQTable.Node("投标金额(万元)", "t22"))
                    .append(new JQTable.Node("中标金额(万元)", "t23"))
                    .append(new JQTable.Node("中标率", "t24"))
                    .append(new JQTable.Node("占年度投标总额比例", "t25")),
                new JQTable.Node("去年同期累计", "t3", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标数量", "t31"))
                    .append(new JQTable.Node("投标金额(万元)", "t32"))
                    .append(new JQTable.Node("中标金额(万元)", "t33"))
                    .append(new JQTable.Node("中标率", "t34"))
                    .append(new JQTable.Node("占年度投标总额比例", "t35")),
                new JQTable.Node("投标额同比增长幅度", "t4", false, JQTable.TextAlign.Right, 0, undefined, undefined, false)
            ], gridName);
        };
        JQGridAssistantFactory.createBidTable4Companys = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("项目公司", "t0", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("当月情况", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标数量", "t11"))
                    .append(new JQTable.Node("投标金额(万元)", "t12"))
                    .append(new JQTable.Node("中标金额(万元)", "t13"))
                    .append(new JQTable.Node("占月度投标总额比例", "t14")),
                new JQTable.Node("年度累计", "t2", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标数量", "t21"))
                    .append(new JQTable.Node("投标金额(万元)", "t22"))
                    .append(new JQTable.Node("中标金额(万元)", "t23"))
                    .append(new JQTable.Node("中标率", "t24"))
                    .append(new JQTable.Node("占年度投标总额比例", "t25")),
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var company = {};
    company.sb = ["沈变"];
    company.hb = ["衡变"];
    company.xb = ["新变"];
    company.tb = ["天变"];
    company.ll = ["鲁缆"];
    company.xl = ["新缆"];
    company.dl = ["德缆"];
    company.byqcy = ["沈变", "衡变", "新变", "天变"];
    company.xlcy = ["鲁缆", "新缆", "德缆"];
    company.all = ["沈变", "衡变", "新变", "天变", "鲁缆", "新缆", "德缆"];
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
            this.mDataSet = new Util.Ajax("mkt_bid_analysis_update.do", false);
            this.onType_TypeSelected();
        };
        View.prototype.onType_TypeSelected = function () {
            this.mAnalysisType = $("#analysisType").val();
        };
        View.prototype.onCompanySelected = function () {
        };
        View.prototype.exportExcel = function () {
            var dt = this.mDs.getDate();
            $("#exportBidAnalysisData")[0].action = "mkt_bid_analysis_export.do?" + Util.Ajax.toUrlParam({ companyName: JSON.stringify(this.mSelCompanys), year: dt.year, month: dt.month, type: this.mAnalysisType });
            $("#exportBidAnalysisData")[0].submit();
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
                if (_this.mAnalysisType == "bid_industry") {
                    _this.updateTable(_this.TableId, _this.childTableId, JQGridAssistantFactory.createBidTable4Industry(_this.childTableId), rowBidData);
                }
                else if (_this.mAnalysisType == "bid_company") {
                    _this.updateTable(_this.TableId, _this.childTableId, JQGridAssistantFactory.createBidTable4Companys(_this.childTableId), rowBidData);
                }
            });
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
        View.prototype.updateTable = function (parentName, childName, tableAssist, rawData) {
            var data = [];
            if (this.mAnalysisType == "bid_industry") {
                var integerList = new std.vector();
                var percentList = new std.vector();
                integerList.push(Bid4IndustryZb.dytbsl);
                integerList.push(Bid4IndustryZb.ndsl);
                integerList.push(Bid4IndustryZb.qntbsl);
                percentList.push(Bid4IndustryZb.dyzydbl);
                percentList.push(Bid4IndustryZb.ndzbl);
                percentList.push(Bid4IndustryZb.ndtbbl);
                percentList.push(Bid4IndustryZb.qnzbl);
                percentList.push(Bid4IndustryZb.qnzndbl);
                percentList.push(Bid4IndustryZb.tbzz);
                data = this.formatData(rawData, integerList, percentList);
            }
            else if (this.mAnalysisType == "bid_company") {
                var integerList = new std.vector();
                var percentList = new std.vector();
                integerList.push(Bid4IndustryZb.dytbsl);
                integerList.push(Bid4IndustryZb.ndsl);
                integerList.push(Bid4IndustryZb.qntbsl);
                percentList.push(Bid4IndustryZb.dyzydbl);
                percentList.push(Bid4IndustryZb.ndzbl);
                percentList.push(Bid4IndustryZb.ndtbbl);
                data = this.formatData(rawData, integerList, percentList);
            }
            $("#" + childName).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                editurl: 'clientArray',
                height: '100%',
                width: $(document).width() - 60,
                shrinkToFit: true,
                autoScroll: true,
                pager: '#pager',
                rowNum: 20,
                viewrecords: true
            }));
            if (rawData.length != 0) {
                $("#assist").css("display", "block");
            }
        };
        return View;
    }());
    mkt_bid_analysis.View = View;
})(mkt_bid_analysis || (mkt_bid_analysis = {}));
