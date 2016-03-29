/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="dateSelector.ts" />
declare var echarts;

module ysjs {

    class JQGridAssistantFactory {
        public static createTable(gridName:string):JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("日期", "rq", true),
                new JQTable.Node("长江现货（元/吨）", "cjxh")
                    .append(new JQTable.Node("铜", "xhcu"))
                    .append(new JQTable.Node("吕", "xhal"))
                    .append(new JQTable.Node("锌", "xhzn")),
                new JQTable.Node("LME结算价（美元/吨）", "cjxh")
                    .append(new JQTable.Node("铜", "LEMcu"))
                    .append(new JQTable.Node("吕", "LEMal"))
                    .append(new JQTable.Node("锌", "LEMzn"))
            ], gridName);
        }
    }

    interface Option {
        cu:string;
        al:string;
        zn:string;
        tb:string;
        dt:string;
        date : Util.Date;
    }

    export class View {
        public static newInstance():View {
            return new View();
        }

        private mData:Array<string[]>;
        private mDataSet:Util.Ajax;
        private mOpt:Option;
        private mDateSelector:Util.DateSelector;

        public init(opt:Option):void {
            this.mOpt = opt;
            this.mDataSet = new Util.Ajax("update.do");
            this.mDateSelector = new Util.DateSelector({year: this.mOpt.date.year - 3, month: 1, day: 1}, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month,
                day: this.mOpt.date.day
            }, this.mOpt.dt);

            this.updateUI();
        }


        public updateUI() {
            this.mDataSet.get({start: "2013-12-12", end: "2019-9-8"})
                .then((jsonData:any) => {
                    this.mData = jsonData;
                    this.updateTable();
                });

        }

        private fillData(month:string[]) {

            if (this.mChartData == undefined) {
                this.mChartData.push([]);
                this.mChartData.push([]);
                for (var i = 1; i <= this.mMonth; ++i) {
                    month.push(i + "月");
                    this.mChartData[0].push(Math.floor(Math.random() * (1000 + 1)) + "");
                    this.mChartData[1].push(Math.floor(Math.random() * (1000 + 1)) + "");
                }
            }
            else {
                for (var i = 1; i <= 12; ++i) {
                    month.push(i + "月");
                }
            }
        }

        private updateEchart(echart:string):void {
            var month:string[] = [];

            this.fillData(month);
            var data = this.mChartData;
            var option = {

                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: [this.mYear - 1 + "年", this.mYear + "年"]
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
                series: [
                    {
                        name: this.mYear - 1 + '年',
                        type: 'line',
                        smooth: true,
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: data[0]
                    },
                    {
                        name: this.mYear + '年',
                        type: 'line',
                        smooth: true,
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: data[1]
                    }
                ]
            };

            echarts.init($('#' + echart)[0]).setOption(option);

        }

        private updateTable():void {
            var name = this.mOpt.tb + "_jqgrid_1234";
            var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            var parent = $("#" + this.mOpt.tb);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(
                tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    data: tableAssist.getData(this.mData),
                    datatype: "local"
                }));

        }
    }
}
var view = ysjs.View.newInstance();