var hkjhjg;
(function (hkjhjg) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createJGTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("逾期应收账款", "yqyszk", true, 2 /* Center */),
                new JQTable.Node("逾期应收账款", "yqyszk_1"),
                new JQTable.Node("逾期款", "yqk", true, 2 /* Center */),
                new JQTable.Node("逾期款", "yqk_1"),
                new JQTable.Node("未到期应收账款", "wdqyszk", true, 2 /* Center */),
                new JQTable.Node("未到期应收账款", "wdqyszk_1"),
                new JQTable.Node("未到期款", "wdqk", true, 2 /* Center */),
                new JQTable.Node("未到期款", "wdqk_1"),
                new JQTable.Node("小计", "xj", true, 2 /* Center */),
                new JQTable.Node("小计", "xj_1")
            ], gridName);
        };
        JQGridAssistantFactory.createZTTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("到期款", "dqk"),
                new JQTable.Node("未到期款", "wdqk"),
                new JQTable.Node("未到期应收账款", "wdqyszk"),
                new JQTable.Node("逾期应收账款", "yqyszk"),
                new JQTable.Node("合计", "hj")
            ], gridName);
        };
        JQGridAssistantFactory.createXZTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("确保可回款", "qbkhk"),
                new JQTable.Node("争取可回款", "zqkhk"),
                new JQTable.Node("下月清收款", "xyqsk"),
                new JQTable.Node("隔月清收款", "gyqsk"),
                new JQTable.Node("合计", "hj")
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var HKJHType;
    (function (HKJHType) {
        HKJHType[HKJHType["JG"] = 0] = "JG";
        HKJHType[HKJHType["ZT"] = 1] = "ZT";
        HKJHType[HKJHType["XZ"] = 2] = "XZ";
    })(HKJHType || (HKJHType = {}));
    var View = (function () {
        function View() {
            this.mComp = 1 /* HB */;
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (echartId, tableId, month, year) {
            this.mYear = year;
            this.mMonth = month;
            this.mDataSet = new Util.DateDataSet("hkjhjg_update.do");
            this.mTableIds = tableId;
            this.mEchartId = echartId;
            this.updateJGTable();
            this.updateZTTable();
            this.updateXZTable();
            this.updateUI();
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.onMonthSelected = function (month) {
            this.mMonth = month;
        };
        View.prototype.onCompanySelected = function (comp) {
            this.mComp = comp;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.getDataByCompany(this.mMonth, this.mYear, this.mComp, function (data) {
                if (null != data) {
                    var tmpData = data.split("##");
                    _this.mJGData = JSON.parse(tmpData[0]);
                    _this.mXZData = JSON.parse(tmpData[1]);
                    _this.mZTData = JSON.parse(tmpData[2]);
                    $('h1').text(_this.mYear + "年" + _this.mMonth + "月 回款计划结构明细");
                    document.title = _this.mYear + "年" + _this.mMonth + "月 回款计划结构明细";
                    _this.updateJGTable();
                    _this.updateZTTable();
                    _this.updateXZTable();
                    _this.updateEchart();
                }
            });
        };
        View.prototype.updateEchart = function () {
            var hkjhjgChart = echarts.init($("#" + this.mEchartId)[0]);
            var legend = ["确保可回逾期应收账款", "确保可回逾期款", "确保可回未到期应收账款", "确保可回未到期款", "争取可回逾期应收账款", "争取可回逾期款", "争取可回未到期应收账款", "争取可回未到期款"];
            var dataOut = [];
            var qbTotal = 0;
            var zqTotal = 0;
            for (var i = 0; i < legend.length; ++i) {
                dataOut.push({ name: legend[i], value: i + 1 });
                if (i < 4) {
                    qbTotal += (i + 1);
                }
                else {
                    zqTotal += (i + 1);
                }
            }
            var dataIn = [{ name: "确保", value: qbTotal }, { name: "争取", value: zqTotal }];
            var hkjhjgOption = {
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    x: "left",
                    data: legend,
                    orient: "vertical"
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                series: [
                    {
                        name: "1",
                        type: 'pie',
                        radius: [100, 130],
                        data: dataOut
                    },
                    {
                        name: "2",
                        type: 'pie',
                        radius: [0, 60],
                        itemStyle: {
                            normal: {
                                label: {
                                    position: 'inner'
                                },
                                labelLine: {
                                    show: false
                                }
                            }
                        },
                        data: dataIn
                    }
                ]
            };
            hkjhjgChart.setOption(hkjhjgOption);
        };
        View.prototype.updateJGTable = function () {
            var name = this.mTableIds[0 /* JG */] + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createJGTable(name);
            tableAssist.mergeTitle(undefined, 0, true);
            var data = [
                ["本月回笼", "", "本月回笼", "", "本月回笼", "", "本月回笼", "", "本月回笼", ""],
                ["确保可回", "", "确保可回", "", "确保可回", "", "确保可回", "", "确保可回", ""],
                ["争取可回", "", "争取可回", "", "争取可回", "", "争取可回", "", "争取可回", ""]
            ];
            if (undefined != this.mJGData) {
                for (var i = 0; i < this.mJGData[0].length; ++i) {
                    data[0][i * 2 + 1] = Util.formatCurrency(this.mJGData[0][i]);
                    data[1][i * 2 + 1] = Util.formatCurrency(this.mJGData[1][i]);
                    data[2][i * 2 + 1] = Util.formatCurrency(this.mJGData[2][i]);
                }
            }
            var parent = $("#" + this.mTableIds[0 /* JG */]);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                caption: "回款计划结构",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                width: 1000,
                shrinkToFit: true,
                autoScroll: true
            }));
        };
        View.prototype.updateZTTable = function () {
            var name = this.mTableIds[1 /* ZT */] + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createZTTable(name);
            tableAssist.mergeTitle(undefined, 0, true);
            var data = [[]];
            if (undefined != this.mZTData) {
                for (var i = 0; i < this.mZTData.length; ++i) {
                    data[0].push(Util.formatCurrency(this.mZTData[i]));
                }
            }
            var parent = $("#" + this.mTableIds[1 /* ZT */]);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                caption: "回款计划款项状态结构",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                width: '100%',
                shrinkToFit: true,
                autoScroll: true
            }));
        };
        View.prototype.updateXZTable = function () {
            var name = this.mTableIds[2 /* XZ */] + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createXZTable(name);
            tableAssist.mergeTitle(undefined, 0, true);
            var data = [[]];
            if (undefined != this.mXZData) {
                for (var i = 0; i < this.mXZData.length; ++i) {
                    data[0].push(Util.formatCurrency(this.mXZData[i]));
                }
            }
            var parent = $("#" + this.mTableIds[2 /* XZ */]);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                caption: "回款计划款项性质结构",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                width: '100%',
                shrinkToFit: true,
                autoScroll: true
            }));
            $(".ui-jqgrid-titlebar-close").hide();
        };
        return View;
    })();
    hkjhjg.View = View;
})(hkjhjg || (hkjhjg = {}));
