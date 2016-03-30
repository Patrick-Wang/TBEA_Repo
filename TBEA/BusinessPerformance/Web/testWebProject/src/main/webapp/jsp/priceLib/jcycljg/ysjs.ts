/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="jcycljgdef.ts" />

declare var echarts;
declare var view:jcycljg.FrameView;

module jcycljg {
    export module ysjs {
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
        }

        class YsjsView implements PluginView{
            private mCurrentDate:Date;
            private mData:Array<string[]>;
            private mDataSet:Util.Ajax;
            private mOpt:Option;
            private mDateSelector:Util.DateSelector;

            public static newInstance():YsjsView {
                return new YsjsView();
            }

            public hide(): void{
                $("#" + this.mOpt.al).hide();
                $("#" + this.mOpt.cu).hide();
                $("#" + this.mOpt.zn).hide();
                $("#" + this.mOpt.tb).hide();
            }
            public show(): void{
                $("#" + this.mOpt.al).show();
                $("#" + this.mOpt.cu).show();
                $("#" + this.mOpt.zn).show();
                $("#" + this.mOpt.tb).show();
            }

            public update(st:Util.Date, ed:Util.Date) {
                this.mDataSet.get({
                        start: st.year + "-" + st.month + "-" + st.day,
                        end: ed.year + "-" + ed.month + "-" + ed.day
                    })
                    .then((jsonData:any) => {
                        this.mData = jsonData;
                        this.updateTable();
                        this.updateCuChart();
                        this.updateAlChart();
                        this.updateZnChart();
                    });
            }

            public init(opt:Option):void {
                this.mOpt = opt;
                this.mDataSet = new Util.Ajax("jcycljg/ysjs/update.do", false);
                view.register("有色金属类", this);
            }


            public updateUI() {
                this.mDateSelector.toString()
                this.mDataSet.get({
                        start: this.mDateSelector.toString() + "-1",
                        end: this.mDateSelector.toString() + "-" + this.mDateSelector.monthDays()
                    })
                    .then((jsonData:any) => {
                        this.mData = jsonData;
                        this.updateTable();
                        this.updateCuChart();
                        this.updateAlChart();
                        this.updateZnChart();
                    });
            }

            public updateCuChart() {
                var data:string[] = [];
                var lemData:string[] = [];
                $(this.mData).each((i:number)=> {
                    data.push(this.mData[i][1]);
                    lemData.push(this.mData[i][4]);
                })
                this.updateEchart("铜 结算价格趋势", this.mOpt.cu, data, lemData);
            }

            public updateAlChart() {
                var data:string[] = [];
                var lemData:string[] = [];
                $(this.mData).each((i:number)=> {
                    data.push(this.mData[i][2]);
                    lemData.push(this.mData[i][5]);
                })
                this.updateEchart("铝 结算价格趋势", this.mOpt.al, data, lemData);
            }

            public updateZnChart() {
                var data:string[] = [];
                var lemData:string[] = [];
                $(this.mData).each((i:number)=> {
                    data.push(this.mData[i][3]);
                    lemData.push(this.mData[i][6]);
                })
                this.updateEchart("锌 结算价格趋势", this.mOpt.zn, data, lemData);
            }

            private updateEchart(title:string, echart:string, data:Array<string>, lemData:Array<string>):void {
                var xData:string[] = [];

                $(this.mData).each((i:number)=> {
                    xData.push(this.mData[i][0]);
                })
                var option = {
                    title: {
                        text: title
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ["长江现货价格", "LME结算价格"]
                    },
                    toolbox: {
                        show: true,
                    },
                    calculable: false,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: xData.length < 1 ? [0] : xData
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        },
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: "长江现货价格",
                            type: 'line',
                            smooth: true,
                            yAxisIndex: 0,
                            // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                            data: data.length < 1 ? [0] : data
                        },
                        {
                            name: "LME结算价格",
                            type: 'line',
                            yAxisIndex: 1,
                            smooth: true,
                            // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                            data: lemData.length < 1 ? [0] : lemData

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
       export  var pluginView = YsjsView.newInstance();
    }
}
