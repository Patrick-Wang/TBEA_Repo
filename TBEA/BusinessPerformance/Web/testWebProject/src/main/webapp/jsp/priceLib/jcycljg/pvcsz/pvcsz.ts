/// <reference path="../../../jqgrid/jqassist.ts" />
/// <reference path="../../../util.ts" />
/// <reference path="../../../dateSelector.ts" />
/// <reference path="../jcycljgdef.ts" />

declare var echarts;
declare var view:jcycljg.FrameView;

module jcycljg {
    export module pvcsz {
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq"),
                    new JQTable.Node("电石法PVC树脂  （元/吨）", "dsf")
                        .append(new JQTable.Node("泰州盐化", "a1"))
                        .append(new JQTable.Node("湖南株化", "a2"))
                        .append(new JQTable.Node("山西榆社", "a3"))
                        .append(new JQTable.Node("黑龙江昊华化工", "a4"))
                        .append(new JQTable.Node("河南宇航", "a5"))
                        .append(new JQTable.Node("陕西北元化工", "a6"))
                        .append(new JQTable.Node("宜宾天原", "a7")),
                    new JQTable.Node("电乙烯法PVC树脂（元/吨）", "yxf")
                        .append(new JQTable.Node("天津大沽化", "a8"))
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            dsf:string;
            yxf:string;
            tb:string;
        }

        class PVCSzView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("jcycljg/update.do?type=" + jcycljg.JcycljgType.PVCSZ, false);
            private mDateSelector:Util.DateSelector;

            public static newInstance():PVCSzView {
                return new PVCSzView();
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
                        this.updateDsfChart();
                        this.updateYxfChart();
                    });
            }

            public init(opt:Option):void {
                super.init(opt);
                view.register("PVC树脂", this);
            }

            public updateDsfChart() {
                let items = ["泰州盐化", "湖南株化", "山西榆社", "黑龙江昊华化工", "河南宇航", "陕西北元化工", "宜宾天原"];
                let data:string[][] = [];
                for (let k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each((i:number)=> {
                    for (let j = 0; j < items.length; ++j) {
                        data[j].push(this.mData[i][1 + j])
                    }
                });
                this.updateEchart("电石法PVC树脂（元/吨）", this.option().dsf, items, data);
            }

            public updateYxfChart() {
                let items = ["天津大沽化"];
                let data:string[][] = [];
                for (let k = 0; k < items.length; ++k) {
                    data.push([]);
                }
                $(this.mData).each((i:number)=> {
                    for (let j = 0; j < items.length; ++j) {
                        data[j].push(this.mData[i][8 + j])
                    }
                });
                this.updateEchart("乙烯法PVC树脂（元/吨）", this.option().yxf, items, data);
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
        export var pluginView = PVCSzView.newInstance();
    }
}
