/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="jcycljgdef.ts" />

declare var echarts;
declare var view:jcycljg.FrameView;

module jcycljg {
    export module ggp {
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("武钢（元/吨）", "wg")
                        .append(new JQTable.Node("30Q120", "w"))
                        .append(new JQTable.Node("30RK100", "ww"))
                        .append(new JQTable.Node("27RK095", "www"))
                        .append(new JQTable.Node("23RK085", "wwww")),
                    new JQTable.Node("宝钢（元/吨）", "bg")
                        .append(new JQTable.Node("B30P120", "b"))
                        .append(new JQTable.Node("B30P100", "bb"))
                        .append(new JQTable.Node("B27R095", "bbb"))
                        .append(new JQTable.Node("B27R085", "bbbb"))
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            wg:string;
            bg:string;
            tb:string;
        }

        class GgpView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("jcycljg/ggp/update.do", false);
            private mDateSelector:Util.DateSelector;

            public static newInstance():GgpView {
                return new GgpView();
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(start:string, end:string):void {
                this.mAjax.get({
                        start: start,
                        end: end
                    })
                    .then((jsonData:any) => {
                        this.mData = jsonData;
                        this.updateTable();
                        this.updateWgChart();
                        this.updateBgChart();
                    });
            }

            public init(opt:Option):void {
                super.init(opt);
                view.register("硅钢片", this);
            }

            public updateWgChart() {
                var data:string[][] = [[], [], [], []];
                $(this.mData).each((i:number)=> {
                    data[0].push(this.mData[i][1]);
                    data[1].push(this.mData[i][2]);
                    data[2].push(this.mData[i][3]);
                    data[3].push(this.mData[i][4]);
                })
                this.updateEchart("武钢结算价格趋势", this.option().wg, ["30Q120", "30RK100", "27RK095", "23RK085"], data);
            }

            public updateBgChart() {
                var data:string[][] = [[], [], [], []];
                $(this.mData).each((i:number)=> {
                    data[0].push(this.mData[i][5]);
                    data[1].push(this.mData[i][6]);
                    data[2].push(this.mData[i][7]);
                    data[3].push(this.mData[i][8]);
                })
                this.updateEchart("宝钢结算价格趋势", this.option().bg, ["B30P120", "B30P100", "B27R095", "B27R085"], data);
            }

            public  getDateType():DateType {
                return DateType.MONTH;
            }

            private updateEchart(title:string, echart:string, legend:Array<string>, data:Array<string[]>):void {
                var xData:string[] = [];

                $(this.mData).each((i:number)=> {
                    xData.push(this.mData[i][0]);
                })

                let series = [];
                for (let i in legend) {
                    series.push({
                        name: legend[i],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 0,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: data[i].length < 1 ? [0] : data[i]
                    })
                }

                var option = {
                    title: {
                        text: title
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
                            data: xData.length < 1 ? [0] : xData
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: series
                };

                echarts.init(this.$(echart)[0]).setOption(option);

            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(
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
        export var pluginView = GgpView.newInstance();
    }
}
