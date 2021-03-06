/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module yqysysfx {
    class JQGridAssistantFactory {
        public static createTable(gridName): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("因素分类", "ysfl", true, JQTable.TextAlign.Left),
                new JQTable.Node("因素分类", "ysfl_1", true, JQTable.TextAlign.Left),
                new JQTable.Node("内部因素", "nbys", true, JQTable.TextAlign.Left),
                new JQTable.Node("客户资信", "khzx", true, JQTable.TextAlign.Left),
                new JQTable.Node("滚动付款", "gdfk", true, JQTable.TextAlign.Left),
                new JQTable.Node("项目变化", "xmbh", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同因素", "htys", true, JQTable.TextAlign.Left),
                new JQTable.Node("手续办理", "sxbl", true, JQTable.TextAlign.Left),
                new JQTable.Node("其它", "qt", true, JQTable.TextAlign.Left)
            ], gridName);
        }
    }

    export class View {
        
        public static newInstance(): View {
            return new View();
        }


        private mData: Array<string[]>;
        private mDataSet: Util.Ajax = new Util.Ajax("yqysysfx_update.do");
        private mTableId: string;
        private mEchartId;
        private mComp: Util.CompanyType = Util.CompanyType.SBGS;
        private mMonth: number;
        private mYear: number;
        public init(echartId: string, tableId: string): void {
            // this.initEchart($('#' + echartId)[0]);
            this.mTableId = tableId;
            this.mEchartId = echartId;
            this.updateTable();
            this.updateUI();
        }

        public updateUI() {
            this.mDataSet.get({year : this.mYear, month : this.mMonth, companyId: this.mComp })
                .then((dataArray: any) => {

                    this.mData = dataArray;
                    //                    $('h1').text(this.mYear + "年" + this.mMonth + "月" + this.mDay + "日 现金流日报");
                    //                    document.title = this.mYear + "年" + this.mMonth + "月" + this.mDay + "日 现金流日报";
                    this.updateTable();
                    this.updateEchart();

                });
        }

        public onCompanySelected(comp: Util.CompanyType) {
            this.mComp = comp;
        }
        
        public onYearSelected(year : number){
            this.mYear = year;
        }
        
        public onMonthSelected(month : number){
            this.mMonth = month;
        }

        private updateEchart(): void {
            var yqysysChart = echarts.init($("#" + this.mEchartId)[0]);
            var legend = ["总金额", "其中：法律手段清收"];
            var ysfl = ["内部因素", "客户资信", "滚动付款", "项目变化", "合同因素", "手续办理", "其它"]
            var data = [];
            if (this.mData != undefined) {
                var row = [];
                for (var i = 0; i < data.length; ++i) {
                    row = [].concat(this.mData[i]);
                    if (0 != i % 2) {
                        for (var col in row) {
                            row[col] = Util.formatCurrency(row[col]);
                           
                        }
                    }

                    data[i] = data[i].concat(row);
                }
            }

            var zjeData = [];
            for (var col in this.mData[1]) {
                zjeData.push(parseFloat(this.mData[1][col]).toFixed(2));
            }
            var flsdData = [];
            for (var col in this.mData[3]) {
                flsdData.push(parseFloat(this.mData[3][col]).toFixed(2));
            }

            var option = {
                title: {
                    text: '逾期应收因素分析'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['总金额', '其中法律清收']
                },
                toolbox: {
                    show: true,
                },
                calculable: true,

                xAxis: [
                    {
                        type: 'category',
                        data: ['内部因素', '客户资信', '滚动付款', '项目变化', '合同因素', '手续办理', '其他']
                    },
                    {
                        type: 'category',
                        axisLine: { show: false },
                        axisTick: { show: false },
                        axisLabel: { show: false },
                        splitArea: { show: false },
                        splitLine: { show: false },
                        data: ['内部因素', '客户资信', '滚动付款', '项目变化', '合同因素', '手续办理', '其他']

                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                    }
                ],
                series: [
                    {
                        name: '其中法律清收',
                        type: 'bar',
                        barWidth: 18,
                        itemStyle: { normal: { color: 'rgba(193,35,43,1)', label: { show: true } } },
                        data: flsdData
                    },

                    {
                        name: '总金额',
                        type: 'bar',
                        barWidth: 25,
                        xAxisIndex: 1,
                        itemStyle: { normal: { color: 'rgba(193,35,43,0.5)', label: { show: true, formatter: function(a, b, c) { return c > 0 ? (c + '\n') : ''; } } } },
                        data: zjeData
                    }

                ]
            };
            yqysysChart.setOption(option);

        }

        private updateTable(): void {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeRow(0);
            tableAssist.mergeTitle();
            var data = [
                ["总数量", "户数"],
                ["总数量", "金额"],
                ["其中：法律手段清收", "户数"],
                ["其中：法律手段清收", "金额"]];

            if (this.mData != undefined) {
                var row = [];
                for (var i = 0; i < data.length; ++i) {
                    row = [].concat(this.mData[i]);
                    if (0 != i % 2) {
                        for (var col in row) {
                            row[col] = Util.formatCurrency(row[col]);
                        }
                    }

                    data[i] = data[i].concat(row);
                }
            }

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
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true
                }));

        }
    }
}