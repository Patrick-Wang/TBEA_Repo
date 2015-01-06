/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module ztyszkfx {

    class JQGridAssistantFactory {

        private static createCurrentYearNode(year: number): JQTable.Node {
            return new JQTable.Node(year + "年", "n" + year)
                .append(new JQTable.Node("本月账面应收账款余额", "byzmyszkye", true, JQTable.TextAlign.Right, 100))
                .append(new JQTable.Node("本月保理控制余额", "byblkzye", true, JQTable.TextAlign.Right, 100))
                .append(new JQTable.Node("本月应收账款实际数", "byyszksjs", true, JQTable.TextAlign.Right, 100))
                .append(new JQTable.Node("本月收入", "bysr", true, JQTable.TextAlign.Right, 100))
                .append(new JQTable.Node("账面应收占收入比例", "zmyszsrbl", true, JQTable.TextAlign.Right, 100));
        }

        private static createPreYearNode(year: number): JQTable.Node {
            return new JQTable.Node(year + "年", "n" + year)
                .append(new JQTable.Node("去年同期账面应收账款余额", "qntqzmyszkye", true, JQTable.TextAlign.Right, 100))
                .append(new JQTable.Node("去年同期保理余额", "qntqblye", true, JQTable.TextAlign.Right, 100))
                .append(new JQTable.Node("去年同期应收账款实际数", "qntqyszksjs", true, JQTable.TextAlign.Right, 100))
                .append(new JQTable.Node("去年同期收入", "qntqsr", true, JQTable.TextAlign.Right, 100))
                .append(new JQTable.Node("账面应收占收入比", "zmyszsrb", true, JQTable.TextAlign.Right, 100));
        }

        public static createTable(gridName: string, year: number): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dw", true, JQTable.TextAlign.Left, 90),
                JQGridAssistantFactory.createCurrentYearNode(year),
                JQGridAssistantFactory.createPreYearNode(year - 1),
                new JQTable.Node("同比增长", "tbzz")
                    .append(new JQTable.Node("账面余额较去年同期增长比", "zmyejqntqzzb", true, JQTable.TextAlign.Right, 100))
                    .append(new JQTable.Node("保理较去年同期增长比", "bljqntqzzb", true, JQTable.TextAlign.Right, 100))
                    .append(new JQTable.Node("实际应收较去年同期增长比", "sjysjqntqzzb", true, JQTable.TextAlign.Right, 100))
                    .append(new JQTable.Node("收入较去年同期增长比", "sujqntqzzb", true, JQTable.TextAlign.Right, 100))
            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }

        private mMonth : number;
        private mYear: number;
        private mData: Array<string[]>;
        private mDataSet: Util.DateDataSet;
        private mTableId: string;
        private mEchartId;
        public init(echartId: string, tableId: string, year: number, month:number): void {
            this.mYear = year;
            this.mMonth = month;
            this.mDataSet = new Util.DateDataSet("ztyszkfx_update.do");
            this.mTableId = tableId;
            this.mEchartId = echartId;
            this.updateTable();
            this.updateUI();

        }
        public onYearSelected(year: number) {
            this.mYear = year;
        }

        public onMonthSelected(month : number){
            this.mMonth = month;
        }
        
        public updateUI() {
            this.mDataSet.getData(this.mMonth, this.mYear, (arrayData: Array<string[]>) => {
                if (null != arrayData) {
                    this.mData = arrayData;
                    $('h1').text(this.mYear + "年 整体应收账款分析表");
                    document.title = this.mYear + "年 整体应收账款分析表";
                    this.updateTable();
                    this.updateEchart();
                }
            });
        }

        private updateEchart(): void {
            var ztyszkfxChart = echarts.init($("#" + this.mEchartId)[0]);
            var month: string[] = [];
            for (var i = 1; i <= 12; ++i) {
                month.push(i + "月");
            }
            var legend = ["账面应收账款余额", "保理控制余额", "应收账款实际数", "累计收入", "账面应收占收入比"];

            var zmysData = [41982, 31876, 51975, 43856, 61498, 32696, 38574, 62641, 28434, 51114, 41563, 68415];
            var blkzData = [29167, 21401, 47155, 32584, 52523, 19573, 24652, 50217, 17426, 43018, 37107, 60047];
            var yssjData = [49841, 57498, 34574, 87756, 85353, 57772, 23587, 54536, 48478, 67488, 99837, 10760];
            var ljsrData = [47291, 67214, 14715, 53258, 45252, 31957, 32465, 25021, 71742, 64301, 83710, 76004];
            var yszbData: any[] = [];
            for (var i = 0; i < zmysData.length; i++) {
                yszbData.push((zmysData[i] / (ljsrData[i] / (i + 1) * 12)).toFixed(2));
            }

            var ztyszkfxOption = {
                title: {
                    text: '整体应收账款分析'
                },
                tooltip: {
                    trigger: 'axis',
                    formatter: function(v) {
                        return v[0][1] + '<br/>'
                            + v[0][0] + ' : ' + v[0][2] + '<br/>'
                            + v[1][0] + ' : ' + v[1][2] + '<br/>'
                            + v[2][0] + ' : ' + v[2][2] + '<br/>'
                            + v[3][0] + ' : ' + v[3][2] + '<br/>'
                            + v[4][0] + ' : ' + v[4][2] + '%';
                    }
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
                        boundaryGap: true,
                        data: month
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
                    {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'line',
                        smooth: true,
                        data: zmysData
                    },
                    {
                        name: legend[1],
                        type: 'line',
                        smooth: true,
                        data: blkzData
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        data: yssjData
                    },
                    {
                        name: legend[3],
                        type: 'line',
                        smooth: true,
                        data: ljsrData
                    },
                    {
                        name: legend[4],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: yszbData
                    }
                ]
            }
            ztyszkfxChart.setOption(ztyszkfxOption);

        }

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

        private updateTable(): void {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mYear);

            var data = [["沈变"],
                ["衡变"],
                ["新变"],
                ["天变"],
                ["变压器合计"],
                ["鲁缆"],
                ["新缆"],
                ["德缆"],
                ["线缆合计"],
                ["产业集团合计"]
            ];


            if (undefined != this.mData) {
                for (var i = 0; i < this.mData.length && i < data.length; ++i) {
                    var row = [];
                    for (var j = 0; j < this.mData[i].length; ++j) {
                        if ((this.mData[i][j] + "").indexOf("%") < 0) {
                            row.push(Util.formatCurrency(this.mData[i][j]));
                        }
                        else {
                            row.push(this.mData[i][j]);
                        }
                    }
                    data[i] = data[i].concat(row);
                }
            }
            
            tableAssist.setRowBgColor(4, 183, 222, 232);
            tableAssist.setRowBgColor(8, 183, 222, 232);
            tableAssist.setRowBgColor(9, 183, 222, 232);
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

            $("#" + name).jqGrid(
                tableAssist.decorate({
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
                    width: 1300,
                    shrinkToFit: true,
                    autoScroll: true,
                    //userData: {
                    //    'dw': "产业集团合计"
                    //},
                    //footerrow: true,
                    //userDataOnFooter: true
                }));

        }
    }
}