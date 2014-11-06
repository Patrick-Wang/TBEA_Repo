/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />

var yszkjgqkb;
(function (yszkjgqkb) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("客户所属行业", "khsshy"),
                new JQTable.Node("客户所属行业", "khsshy_1"),
                new JQTable.Node("应收账款情况", "zqkh").append(new JQTable.Node("金额", "je")).append(new JQTable.Node("占全部比例", "zqbbl")),
                new JQTable.Node("欠款构成", "qkgc").append(new JQTable.Node("应收未收(包括到期质保金）", "ysws").append(new JQTable.Node("逾期1个月以内", "yq1yn")).append(new JQTable.Node("逾期1-3月", "yq13y")).append(new JQTable.Node("逾期3-6月", "yq36y")).append(new JQTable.Node("逾期6-12月", "yq612y")).append(new JQTable.Node("逾期1年以上", "yqynys"))).append(new JQTable.Node("未到期款", "wdqk")).append(new JQTable.Node("未到期质保金", "wdqzbj")).append(new JQTable.Node("应收账款合计", "yszkhj"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();

    var View = (function () {
        function View() {
            this.mCurrentSelected = 0;
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };

        View.prototype.init = function (echartIdPie, echartIdSquire, echartIdBar, echartIdLine, tableId, args) {
            this.mMonth = args[0];
            this.mYear = args[1];
            this.mTableData = args[2];
            this.mBarData = args[3];
            this.mLineData = args[4];

            this.mEchartIdBar = echartIdBar;
            this.mEchartIdLine = echartIdLine;
            this.mEchartIdPie = echartIdPie;
            this.mEchartIdSquire = echartIdSquire;

            this.updatePieEchart(this.mEchartIdPie);
            this.updateLineEchart(this.mEchartIdLine);
            this.updateBarEchart(this.mEchartIdBar);
            this.updateSquareEchart(this.mEchartIdSquire);
            this.updateTable(tableId);
        };

        View.prototype.onSelected = function (i) {
            this.mCurrentSelected = i;
            this.updateLineEchart(this.mEchartIdLine);
            this.updateBarEchart(this.mEchartIdBar);
            this.updateSquareEchart(this.mEchartIdSquire);
        };
        View.prototype.updateBarEchart = function (echart) {
            var legend = [this.mYear - 1 + "应收未收", this.mYear - 1 + "未到期款", this.mYear - 1 + "未到期质保金", '', this.mYear + "应收未收", this.mYear + "未到期款", this.mYear + "未到期质保金"];
            var month = [];
            var data = [];
            for (var i = 1; i <= this.mMonth; ++i) {
                month.push(i + "月");
            }

            for (var i = 0; i < 6; ++i) {
                data.push(this.mBarData[i + this.mCurrentSelected * 6]);
            }

            var ser = [];
            var rgba = [[193, 35, 43, 0.5], [181, 195, 52, 0.5], [252, 206, 16, 0.5], [193, 35, 43, 1], [181, 195, 52, 1], [252, 206, 16, 1]];

            var temp;
            for (var j = 0; j < legend.length; ++j) {
                if (j == 3) {
                    continue;
                }
                var k = j;
                if (j > 3) {
                    k = j - 1;
                }
                temp = {
                    name: legend[j],
                    type: 'bar',
                    smooth: true,
                    itemStyle: {
                        normal: {
                            color: "rgba(" + rgba[k][0] + "," + rgba[k][1] + "," + rgba[k][2] + "," + rgba[k][3] + ")",
                            label: {
                                show: true
                            }
                        }
                    },
                    data: data[k]
                };

                if (j < 3) {
                    temp.xAxisIndex = 1;
                }
                ser.push(temp);
            }

            var option = {
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,
                        data: month
                    }, {
                        type: 'category',
                        axisLine: { show: false },
                        axisTick: { show: false },
                        axisLabel: { show: false },
                        splitArea: { show: false },
                        splitLine: { show: false },
                        data: month
                    }],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: ser
            };
            try  {
                echarts.init($('#' + echart)[0]).setOption(option);
            } catch (e) {
            }
        };

        View.prototype.updateSquareEchart = function (echart) {
            var month = [];
            var data = [];
            for (var i = 1; i <= this.mMonth; ++i) {
                month.push(i + "月");
            }

            for (var i = 0; i < 3; ++i) {
                data.push(this.mBarData[i + this.mCurrentSelected * 6 + 3]);
            }

            var legend = ["应收未收", "未到期款", "未到期质保金"];

            //var chart: ECharts.Chart = new ECharts.Chart(new ECharts.XAxis(month), new ECharts.YAxis());
            //chart.setLegend(new ECharts.Legend(, ECharts.LegendX.center));
            //var ser: ECharts.Line.SeriesImpl = new ECharts.Line.SquareSeries('应收未收', data[0]);
            //ser.stack = "金额";
            //chart.addSeries(ser);
            //ser = new ECharts.Line.SquareSeries('未到期款', data[1]);
            //ser.stack = "金额";
            //chart.addSeries(ser);
            //ser = new ECharts.Line.SquareSeries('未到期质保金', data[2]);
            //ser.stack = "金额";
            //chart.addSeries(ser);
            //chart.update(echart);
            var ser = [];
            for (var i = 0; i < legend.length; ++i) {
                ser.push({
                    name: legend[i],
                    type: 'line',
                    smooth: true,
                    stack: "金额",
                    itemStyle: { normal: { areaStyle: { type: 'default' } } },
                    data: data[i]
                });
            }

            var option = {
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: month
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: ser
            };

            echarts.init($('#' + echart)[0]).setOption(option);
        };

        View.prototype.updateLineEchart = function (echart) {
            var month = [];
            var data = [];
            for (var i = 1; i <= this.mMonth; ++i) {
                month.push(i + "月");
            }

            for (var i = 0; i < 2; ++i) {
                data.push(this.mLineData[i + this.mCurrentSelected * 2]);
            }

            //var chart: ECharts.Chart = new ECharts.Chart(new ECharts.XAxis(month), new ECharts.YAxis());
            //chart.setLegend(new ECharts.Legend(["2013年", "2014年"], ECharts.LegendX.center));
            //var ser: ECharts.Line.SeriesImpl = new ECharts.Line.SeriesImpl('2013年', data[0]);
            //chart.addSeries(ser);
            //ser = new ECharts.Line.SeriesImpl('2014年', data[1]);
            //chart.addSeries(ser);
            //chart.update(echart);
            var legend = [this.mYear - 1 + "年", this.mYear + "年"];

            var ser = [];
            for (var i = 0; i < legend.length; ++i) {
                ser.push({
                    name: legend[i],
                    type: 'line',
                    smooth: true,
                    data: data[i]
                });
            }

            var option = {
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: month
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: ser
            };

            echarts.init($('#' + echart)[0]).setOption(option);
        };
        View.prototype.updatePieEchart = function (echart) {
            var legend = ["国网", "南网", "省、市电力公司", "五大发电", "其他电源", "出口合同", "其它"];
            var dataOut = [];
            var total = 0;
            for (var i = 0; i < legend.length; ++i) {
                dataOut.push({ name: legend[i], value: parseInt(this.mTableData[i][0]) });
                if (i < 5) {
                    total += parseInt(this.mTableData[i][0]);
                }
            }

            var dataIn = [
                { name: "  电力\r\n及配套", value: total },
                { name: " ", value: parseInt(this.mTableData[5][0]) },
                { name: "  ", value: parseInt(this.mTableData[6][0]) }];

            var option = {
                tooltip: {
                    trigger: 'axis'
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
                    }, {
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

            echarts.init($('#' + echart)[0]).setOption(option);
        };

        View.prototype.updateTable = function (name) {
            var tableAssist = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            tableAssist.mergeColum(0, 5);
            tableAssist.mergeColum(0, 6);
            tableAssist.mergeColum(0, 7);
            var data = [
                ["电力及配套", "国网"],
                ["电力及配套", "南网"],
                ["电力及配套", "省、市电力公司"],
                ["电力及配套", "五大发电"],
                ["电力及配套", "其他电源"],
                ["出口", "合同"],
                ["其", "它"],
                ["合", "计"]];

            for (var i = 0; i < data.length; ++i) {
                data[i] = data[i].concat(this.mTableData[i]);
            }

            var row = [];
            for (var i = 0; i < data.length; ++i) {
                row = [].concat(this.mTableData[i]);
                for (var col in row) {
                    if (col != '1') {
                        row[col] = Util.formatCurrency(row[col]);
                    }
                }
                data[i] = data[i].concat(row);
            }

            $("#" + name).jqGrid(tableAssist.decorate({
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
                width: 1200,
                shrinkToFit: true,
                autoScroll: true
            }));
        };
        return View;
    })();
    yszkjgqkb.View = View;
})(yszkjgqkb || (yszkjgqkb = {}));
//# sourceMappingURL=yszkjgqkb.js.map
