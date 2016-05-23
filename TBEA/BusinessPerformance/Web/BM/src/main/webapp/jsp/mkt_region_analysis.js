var mkt_region_analysis;
(function (mkt_region_analysis) {
    var RegionZb;
    (function (RegionZb) {
        RegionZb[RegionZb["hy"] = 0] = "hy";
        RegionZb[RegionZb["tbje"] = 1] = "tbje";
        RegionZb[RegionZb["zbje"] = 2] = "zbje";
        RegionZb[RegionZb["zbl"] = 3] = "zbl";
        RegionZb[RegionZb["qyje"] = 4] = "qyje";
        RegionZb[RegionZb["qntbje"] = 5] = "qntbje";
        RegionZb[RegionZb["qnzbje"] = 6] = "qnzbje";
        RegionZb[RegionZb["qnzbl"] = 7] = "qnzbl";
        RegionZb[RegionZb["qnqyje"] = 8] = "qnqyje";
        RegionZb[RegionZb["tbjetbzf"] = 9] = "tbjetbzf";
        RegionZb[RegionZb["zbjetbzf"] = 10] = "zbjetbzf";
        RegionZb[RegionZb["zbltbzf"] = 11] = "zbltbzf";
        RegionZb[RegionZb["qyjetbzf"] = 12] = "qyjetbzf";
    })(RegionZb || (RegionZb = {}));
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createRegionIndexTable = function (gridName, year, startMonth, endMonth) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("区域", "t0", false, 0 /* Left */, 0, undefined, undefined, false),
                new JQTable.Node(year + "年" + startMonth + "-" + endMonth + "月市场关键累计指标", "t1", false, 0 /* Left */, 0, undefined, undefined, false).append(new JQTable.Node("投标金额(万元)", "t11")).append(new JQTable.Node("中标金额(万元)", "t12")).append(new JQTable.Node("中标率", "t13")).append(new JQTable.Node("签约金额", "t14"))
            ], gridName);
        };
        JQGridAssistantFactory.createIndustryIndexTable = function (gridName, year, startMonth, endMonth) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("行业", "t0", false, 0 /* Left */, 0, undefined, undefined, false),
                new JQTable.Node(year + "年" + startMonth + "-" + endMonth + "月市场关键累计指标", "t1", false, 0 /* Left */, 0, undefined, undefined, false).append(new JQTable.Node("投标金额(万元)", "t11")).append(new JQTable.Node("中标金额(万元)", "t12")).append(new JQTable.Node("中标率", "t13")).append(new JQTable.Node("签约金额", "t14")),
                new JQTable.Node((year - 1) + "年" + startMonth + "-" + endMonth + "月市场关键累计指标", "t2", false, 0 /* Left */, 0, undefined, undefined, false).append(new JQTable.Node("投标金额(万元)", "t21")).append(new JQTable.Node("中标金额(万元)", "t22")).append(new JQTable.Node("中标率", "t23")).append(new JQTable.Node("签约金额", "t24")),
                new JQTable.Node("累计同期对比情况", "t3", false, 0 /* Left */, 0, undefined, undefined, false).append(new JQTable.Node("投标金额同比增长", "t31")).append(new JQTable.Node("中标金额同比增长", "t32")).append(new JQTable.Node("中标率同比提高", "t33")).append(new JQTable.Node("签约金额同比增长", "t34"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
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
            this.mDataSet = new Util.Ajax("mkt_region_analysis_update.do", false);
            this.onType_TypeSelected();
            this.onYearSelected();
            this.onStartMonthSelected();
            this.onEndMonthSelected();
        };
        View.prototype.onType_TypeSelected = function () {
            this.mAnalysisType = $("#analysisType").val();
        };
        View.prototype.onCompanySelected = function () {
        };
        View.prototype.exportExcel = function () {
            $("#exportBidAnalysisData")[0].action = "mkt_region_analysis_export.do?" + Util.Ajax.toUrlParam({ companyName: JSON.stringify(this.mSelCompanys), year: this.mYear, month: this.mEndMonth, startYear: this.mYear, startMonth: this.mStartMonth, type: this.mAnalysisType });
            $("#exportBidAnalysisData")[0].submit();
        };
        View.prototype.onYearSelected = function () {
            this.mYear = parseInt($("#year").val());
        };
        View.prototype.onStartMonthSelected = function () {
            this.mStartMonth = parseInt($("#start_month").val());
        };
        View.prototype.onEndMonthSelected = function () {
            this.mEndMonth = parseInt($("#end_month").val());
        };
        View.prototype.formatData = function (rowData, integerList, percentList) {
            var outputData = [];
            var formaterChain = new Util.FormatPercentHandler([], percentList.toArray());
            formaterChain.next(new Util.FormatIntHandler([], integerList.toArray())).next(new Util.FormatCurrencyHandler());
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
            if (this.mStartMonth > this.mEndMonth) {
                Util.MessageBox.tip("起始月份大于终止月份，请重新选择月份！");
            }
            else if (this.mStartMonth == 0 || this.mEndMonth == 0) {
                Util.MessageBox.tip("请选择起始月份和终止月份！");
            }
            else {
                this.mSelCompanys = [];
                if (this.mCompanyName == "股份公司") {
                    $('#comp_category').multiselect("getChecked").each(function (i, item) {
                        _this.mSelCompanys.push($(item).val());
                    });
                }
                else {
                    this.mSelCompanys.push(this.mCompanyName);
                }
                this.mDataSet.get({ companyName: JSON.stringify(this.mSelCompanys), year: this.mYear, month: this.mEndMonth, startYear: this.mYear, startMonth: this.mStartMonth, type: this.mAnalysisType }).then(function (data) {
                    var parent = $("#" + _this.TableId);
                    parent.empty();
                    parent.append("<table id='" + _this.childTableId + "'></table>" + "<div id='pager'></div>");
                    if (_this.mAnalysisType == "region_index") {
                        var integerList = new std.vector();
                        var percentList = new std.vector();
                        percentList.push(3 /* zbl */);
                        data = _this.formatData(data, integerList, percentList);
                        _this.updateTable(_this.TableId, _this.childTableId, JQGridAssistantFactory.createRegionIndexTable(_this.childTableId, _this.mYear, _this.mStartMonth, _this.mEndMonth), data);
                    }
                    else if (_this.mAnalysisType == "industry_index") {
                        var integerList = new std.vector();
                        var percentList = new std.vector();
                        percentList.push(3 /* zbl */);
                        percentList.push(7 /* qnzbl */);
                        percentList.push(9 /* tbjetbzf */);
                        percentList.push(10 /* zbjetbzf */);
                        percentList.push(11 /* zbltbzf */);
                        percentList.push(12 /* qyjetbzf */);
                        data = _this.formatData(data, integerList, percentList);
                        _this.updateTable(_this.TableId, _this.childTableId, JQGridAssistantFactory.createIndustryIndexTable(_this.childTableId, _this.mYear, _this.mStartMonth, _this.mEndMonth), data);
                    }
                });
            }
        };
        View.prototype.updateTable = function (parentName, childName, tableAssist, rawData) {
            $(rawData).each(function (i, item) {
                if (item[0].indexOf("计") >= 0) {
                    tableAssist.setRowBgColor(i, 183, 222, 232);
                }
            });
            $("#" + childName).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(rawData),
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
    })();
    mkt_region_analysis.View = View;
})(mkt_region_analysis || (mkt_region_analysis = {}));
