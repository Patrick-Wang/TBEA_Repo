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
        private mCompIndex : number = 0;
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
                    $('h1').text(this.mYear + "年" + this.mMonth  + "月 整体应收账款分析表");
                    document.title = this.mYear + "年" + this.mMonth  + "月 整体应收账款分析表";
                    this.updateTable();
                    this.updateEchart();
                }
            });
        }

        private updateEchart(): void {
            var ztyszkfxChart = echarts.init($("#" + this.mEchartId)[0]);
            var month: string[] = [];

            var compMap = [0, 1, 2, 3, 5, 6, 7];
            
            
            var legend = ["本月", "去年同期"];

            var jn = [];
            var qitq = [];
            for (var i = 0; i < 4; i++) {
                 jn.push(parseFloat(this.mData[compMap[this.mCompIndex]][i]).toFixed(2));
                 qitq.push(parseFloat(this.mData[compMap[this.mCompIndex]][i + 5]).toFixed(2));
            }

            var ztyszkfxOption = {
                title: {
                    text: '整体应收账款分析'
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
                        boundaryGap: true,
                        data: ["账面应收账款余额", "保理控制余额", "应收账款实际数", "收入"]
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
                        type: 'bar',
                        smooth: true,
                        data: jn
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        data: qitq
                    },
                ]
            }
            ztyszkfxChart.setOption(ztyszkfxOption);

        }

        public onChartCompSelected(v) : void{
            this.mCompIndex = v;
            this.updateEchart();
        }
        
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