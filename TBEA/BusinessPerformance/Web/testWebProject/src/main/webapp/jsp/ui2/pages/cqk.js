/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var cqk;
(function (cqk) {
    var JQGridAssistantFactory = /** @class */ (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, year) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("客户所属行业", "khsshy", true, JQTable.TextAlign.Left),
                new JQTable.Node("客户所属行业", "khsshy_1", true, JQTable.TextAlign.Left),
                new JQTable.Node(year - 4 + "年及以前", "n4n"),
                new JQTable.Node(year - 3 + "年", "n3n"),
                new JQTable.Node(year - 2 + "年", "n2n"),
                new JQTable.Node("合计", "hj")
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var View = /** @class */ (function () {
        function View() {
            this.currentSelected = 0;
            this.mComp = Util.CompanyType.SBGS;
            this.mDataSet = new Util.Ajax("cqk_update.do");
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };
        View.prototype.init = function (echartIdPie, echartIdSquire, echartIdLine, tableId, args) {
            this.mMonth = args[0];
            this.mYear = args[1];
            this.mTableId = tableId;
            this.mEchartIdLine = echartIdLine;
            this.mEchartIdPie = echartIdPie;
            this.mEchartIdSquire = echartIdSquire;
            this.updateTable(this.mTableId);
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
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, companyId: this.mComp })
                .then(function (jsonData) {
                _this.mTableData = jsonData[0];
                _this.mLineData = jsonData[1];
                $('h1').text(_this.mYear + "年" + _this.mMonth + "月  陈欠款");
                document.title = _this.mYear + "年" + _this.mMonth + "月  陈欠款";
                _this.updateTable(_this.mTableId);
                _this.updatePieEchart(_this.mEchartIdPie);
                _this.updateLineEchart(_this.mEchartIdLine);
                _this.updateSquareEchart(_this.mEchartIdSquire);
            });
        };
        View.prototype.onSelected = function (i) {
            this.currentSelected = i;
            this.updateLineEchart(this.mEchartIdLine);
            this.updateSquareEchart(this.mEchartIdSquire);
        };
        View.prototype.updateSquareEchart = function (echart) {
            var data = [];
            var month = [];
            if (data == null) {
                data = [];
                data.push([]);
                data.push([]);
                data.push([]);
                for (var i = 1; i <= this.mMonth; ++i) {
                    month.push(i + "月");
                    data[0].push(Math.floor(Math.random() * (1000 + 1)) + "");
                    data[1].push(Math.floor(Math.random() * (1000 + 1)) + "");
                    data[2].push(Math.floor(Math.random() * (1000 + 1)) + "");
                }
            }
            else {
                data.push(this.mLineData[this.currentSelected * 5 + 2]);
                data.push(this.mLineData[this.currentSelected * 5 + 3]);
                data.push(this.mLineData[this.currentSelected * 5 + 4]);
                for (var i = 1; i <= 12; ++i) {
                    month.push(i + "月");
                }
            }
            for (var i = 0; i < data.length; ++i) {
                for (var j = 0; j < data[i].length; ++j) {
                    data[i][j] = parseFloat(data[i][j]).toFixed(2);
                }
            }
            var legend = ["陈欠4年及以上", "陈欠3年", "陈欠2年"];
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
                title: {
                    text: '行业陈欠款趋势'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true,
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
            var data = [];
            var month = [];
            if (data == null) {
                data = [];
                data.push([]);
                data.push([]);
                for (var i = 1; i <= this.mMonth; ++i) {
                    month.push(i + "月");
                    data[0].push(Math.floor(Math.random() * (1000 + 1)) + "");
                    data[1].push(Math.floor(Math.random() * (1000 + 1)) + "");
                }
            }
            else {
                data.push(this.mLineData[this.currentSelected * 5]);
                data.push(this.mLineData[this.currentSelected * 5 + 1]);
                for (var i = 1; i <= 12; ++i) {
                    month.push(i + "月");
                }
            }
            var legend = [this.mYear - 1 + "年", this.mYear + "年"];
            for (var i = 0; i < data.length; ++i) {
                for (var j = 0; j < data[i].length; ++j) {
                    data[i][j] = parseFloat(data[i][j]).toFixed(2);
                }
            }
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
                title: {
                    text: '行业陈欠款同期对比'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true,
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
            var data = this.mTableData;
            var legend = ["国网、南网", "省、市电力公司", "五大发电", "其他电源", "石油石化", "轨道交通", "出口合同"];
            var dljpt = 0;
            for (var i = 0; i < 4; ++i) {
                dljpt += parseInt(this.mTableData[i][3]);
            }
            var dataIn = [
                { name: "电力及配套", value: dljpt },
                { name: "石油石化", value: parseInt(this.mTableData[4][3]) },
                { name: "轨道交通", value: parseInt(this.mTableData[5][3]) },
                { name: "出口合同", value: parseInt(this.mTableData[6][3]) },
                { name: "其他", value: parseInt(this.mTableData[7][3]) }
            ];
            var dataOut = [];
            for (var i = 0; i < legend.length; ++i) {
                dataOut.push({ name: legend[i], value: parseFloat(this.mTableData[i][3]).toFixed(2) });
            }
            //var dataIn = [{ name: "  电力\r\n及配套", value: Math.random() * (1000 + 1) },
            //    { name: "出口\r\n合同", value: Math.random() * (1000 + 1) },
            //    { name: "其它", value: Math.random() * (1000 + 1) }];
            var option = {
                title: {
                    text: '行业占比'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    x: "left",
                    y: '40',
                    data: legend,
                    orient: "vertical"
                },
                toolbox: {
                    show: true,
                },
                calculable: false,
                series: [
                    {
                        name: "行业占比",
                        type: 'pie',
                        radius: [0, 130],
                        data: dataOut
                    }
                ]
            };
            echarts.init($('#' + echart)[0]).setOption(option);
        };
        //private initEchart(echart): void {
        //    var ysyq_payment_Chart = echarts.init(echart)
        //    var ysyq_payment_Option = {
        //        animation: true,
        //        tooltip: {
        //            trigger: 'axis',
        //            /* formatter : "{b}<br/>{a} : {c} 万元<br/>{a1} : {c1} 万元", */
        //            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
        //                type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
        //            }
        //        },
        //        legend: {
        //            x: 'right',
        //            data: ["合同金额", "预期阶段", "中标阶段", "完工阶段"],
        //        },
        //        xAxis: [{
        //            type: 'category',
        //            data: ['沈变', '衡变', '新变', '天变']
        //        }],
        //        yAxis: [{
        //            type: 'value'
        //        }],
        //        calculable: true,
        //        series: [{
        //            name: '合同金额',
        //            type: 'bar',
        //            barCategoryGap: "50%",
        //            data: [63363.11, 55628.27, 58521.55, 69100.58]
        //        }, {
        //                name: '预期阶段',
        //                type: 'bar',
        //                stack: '阶段',
        //                data: [9098.58, 1240.13, 1140.61, 3154.82]
        //            }, {
        //                name: '中标阶段',
        //                type: 'bar',
        //                stack: '阶段',
        //                data: [3934.13, 3200.22, 1382.52, 3934.13]
        //            }, {
        //                name: '完工阶段',
        //                type: 'bar',
        //                stack: '阶段',
        //                data: [11980.74, 2240.18, 3487.11, 6980.74]
        //            }]
        //    };
        //    ysyq_payment_Chart.setOption(ysyq_payment_Option);
        //}
        View.prototype.updateTable = function (name) {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name, this.mYear);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            tableAssist.mergeColum(0, 4);
            tableAssist.mergeColum(0, 5);
            tableAssist.mergeColum(0, 6);
            tableAssist.mergeColum(0, 7);
            tableAssist.mergeColum(0, 8);
            var data = [
                ["电力及配套", "国网、南网"],
                ["电力及配套", "省、市电力系统"],
                ["电力及配套", "五大发电"],
                ["电力及配套", "其他电源"],
                ["石油", "石化"],
                ["轨道", "交通"],
                ["出口", "合同"],
                ["其", "他"],
                ["合", "计"]
            ];
            //			for (var i = 0; i < data.length; ++i){
            //				data[i] = data[i].concat(this.mTableData[i]);
            //			}
            if (undefined != this.mTableData) {
                var row = [];
                for (var i = 0; i < data.length; ++i) {
                    row = [].concat(this.mTableData[i]);
                    for (var col in row) {
                        row[col] = Util.formatCurrency(row[col]);
                    }
                    data[i] = data[i].concat(row);
                }
            }
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                //                    cellsubmit: 'clientArray',
                //                    cellEdit: true,
                height: '100%',
                width: 1000,
                autoScroll: true
            }));
        };
        return View;
    }());
    cqk.View = View;
})(cqk || (cqk = {}));
