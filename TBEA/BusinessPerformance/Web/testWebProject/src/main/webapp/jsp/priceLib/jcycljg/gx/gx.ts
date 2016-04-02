/// <reference path="../../../jqgrid/jqassist.ts" />
/// <reference path="../../../util.ts" />
/// <reference path="../../../dateSelector.ts" />
/// <reference path="../jcycljgdef.ts" />

declare var echarts;
declare var view:jcycljg.FrameView;

module jcycljg {
    export module gx {
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("上海沙钢<br/>（元/吨）", "a1"),
                    new JQTable.Node("南京济源<br/>（元/吨）", "a2"),
                    new JQTable.Node("郑州济源<br/>（元/吨）", "a3"),
                    new JQTable.Node("天津唐钢<br/>（元/吨）", "a4"),
                    new JQTable.Node("成都酒钢<br/>（元/吨）", "a5"),
                    new JQTable.Node("平均价<br/>（元/吨）", "a6")
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            ct:string;
            tb:string;
        }

        class GxView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("jcycljg/update.do?type=" + jcycljg.JcycljgType.GX, false);
            private mDateSelector:Util.DateSelector;

            public static newInstance():GxView {
                return new GxView();
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
                        this.refresh();
                    });
            }
            public refresh() : void{
                if ( this.mData == undefined){
                    return;
                }

                if (this.mDispType == DisplayType.CHART) {
                    this.updateChart();
                }else{
                    this.updateTable();
                }
            }
            public init(opt:Option):void {
                super.init(opt);
                view.register("高线（45-70# Φ6.5）", this);
            }

            public updateChart() {
                let items = ["上海沙钢", "南京济源", "郑州济源", "天津唐钢", "成都酒钢", "平均价"];
                let data:string[][] = [];
                for (let k = 0; k < items.length; ++k){
                    data.push([]);
                }
                $(this.mData).each((i:number)=> {
                    for (let j = 0; j < items.length; ++j) {
                        data[j].push(this.mData[i][1 + j])
                    }
                });
                this.updateEchart("高线价格趋势（元/吨）", this.option().ct, items, data);
            }

            public  getDateType():DateType {
                return DateType.DAY;
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
                parent.append("<table id='" + name + "'></table><div id='" + name + "pager" + "'></div>");
                this.$(name).jqGrid(
                    tableAssist.decorate({
                        multiselect: false,
                        drag: false,
                        resize: false,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 20,
                        data: tableAssist.getData(this.mData),
                        datatype: "local",
                        viewrecords : true,
                        pager : name + "pager"
                    }));
            }
        }
        export var pluginView = GxView.newInstance();
    }
}
