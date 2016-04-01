/// <reference path="../../../jqgrid/jqassist.ts" />
/// <reference path="../../../util.ts" />
/// <reference path="../../../dateSelector.ts" />
/// <reference path="../jcycljgdef.ts" />

declare var echarts;
declare var view:jcycljg.FrameView;

module jcycljg {
    export module jt {
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("山西吕梁<br/>（元/吨）", "sxll"),
                    new JQTable.Node("河北邢台<br/>（元/吨）", "hbxt"),
                    new JQTable.Node("山东青岛<br/>（元/吨）", "sdqd"),
                    new JQTable.Node("河南济源<br/>（元/吨）", "hnjy"),
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            ct:string;
            tb:string;
        }

        class JtView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("jcycljg/update.do?type=" + jcycljg.JcycljgType.JT, false);
            private mDateSelector:Util.DateSelector;

            public static newInstance():JtView {
                return new JtView();
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
                        this.updateChart();
                    });
            }

            public init(opt:Option):void {
                super.init(opt);
                view.register("焦炭", this);
            }

            public updateChart() {
                var data:string[][] = [[], [], [], []];
                $(this.mData).each((i:number)=> {
                    data[0].push(this.mData[i][1]);
                    data[1].push(this.mData[i][2]);
                    data[2].push(this.mData[i][3]);
                    data[3].push(this.mData[i][4]);
                })
                this.updateEchart("焦炭价格趋势（元/吨）", this.option().ct, ["山西吕梁", "河北邢台", "山东青岛", "河南济源"], data);
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
                        rowNum: 100,
                        data: tableAssist.getData(this.mData),
                        datatype: "local"
                    }));

            }
        }
        export var pluginView = JtView.newInstance();
    }
}
