/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />

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
            this.mComp = 19 /* JT */;
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
                    //this.updateEchart();
                }
            });
        };

        //        private updateEchart(): void {
        //            var ysyq_payment_Chart = echarts.init($("#" + this.mEchartId)[0]);
        //            var ysyq_payment_Option = {
        //                animation: true,
        //                tooltip: {
        //                    trigger: 'axis',
        //                    /* formatter : "{b}<br/>{a} : {c} 万元<br/>{a1} : {c1} 万元", */
        //
        //                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
        //                        type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
        //                    }
        //                },
        //                legend: {
        //                    x: 'right',
        //                    data: ["合同金额", "预期阶段", "中标阶段", "完工阶段"],
        //
        //                },
        //                xAxis: [{
        //                    type: 'category',
        //                    data: ['沈变', '衡变', '新变', '天变']
        //                }],
        //                yAxis: [{
        //                    type: 'value'
        //
        //                }],
        //
        //                calculable: true,
        //                series: [{
        //                    name: '合同金额',
        //                    type: 'bar',
        //
        //                    barCategoryGap: "50%",
        //                    data: [63363.11, 55628.27, 58521.55, 69100.58]
        //                }, {
        //                        name: '预期阶段',
        //                        type: 'bar',
        //
        //                        stack: '阶段',
        //                        data: [9098.58, 1240.13, 1140.61, 3154.82]
        //                    }, {
        //                        name: '中标阶段',
        //
        //                        type: 'bar',
        //                        stack: '阶段',
        //                        data: [3934.13, 3200.22, 1382.52, 3934.13]
        //                    }, {
        //                        name: '完工阶段',
        //                        type: 'bar',
        //
        //                        stack: '阶段',
        //                        data: [11980.74, 2240.18, 3487.11, 6980.74]
        //                    }]
        //            };
        //            ysyq_payment_Chart.setOption(ysyq_payment_Option);
        //        }
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
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
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
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
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
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
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
//# sourceMappingURL=hkjhjg.js.map
