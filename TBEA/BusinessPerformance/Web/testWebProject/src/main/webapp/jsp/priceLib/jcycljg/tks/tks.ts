/// <reference path="../../../jqgrid/jqassist.ts" />
/// <reference path="../../../util.ts" />
/// <reference path="../../../dateSelector.ts" />
/// <reference path="../jcycljgdef.ts" />

declare var echarts;
declare var view:jcycljg.FrameView;

module jcycljg {
    export module tks {
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("国产矿（元/吨）", "gck")
                        .append(new JQTable.Node("山西代县<br/>64品位", "sxdx"))
                        .append(new JQTable.Node("辽宁辽阳<br/>65品位", "lnly"))
                        .append(new JQTable.Node("山东淄博<br/>65品位", "sdzb"))
                        .append(new JQTable.Node("安徽霍邱<br/>64品位", "anhq")),
                    new JQTable.Node("进口矿（元/吨）", "jkk")
                        .append(new JQTable.Node("青岛港巴西粉矿<br/>63.5品位", "qdgbxfk"))
                        .append(new JQTable.Node("印度粉矿<br/>60品位", "ydfk"))
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            jk:string;
            gc:string;
            tb:string;
        }

        class TksView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("jcycljg/update.do?type=" + jcycljg.JcycljgType.TKS, false);
            private mDateSelector:Util.DateSelector;

            public static newInstance():TksView {
                return new TksView();
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
                        this.updateGcChart();
                        this.updateJkChart();
                    });
            }

            public init(opt:Option):void {
                super.init(opt);
                view.register("铁矿石", this);
            }

            public updateGcChart() {
                let items = ["山西代县(64品位)", "辽宁辽阳(65品位)", "山东淄博(65品位)", "安徽霍邱(64品位)"];
                let data:string[][] = [];
                for (let k = 0; k < items.length; ++k){
                    data.push([]);
                }
                $(this.mData).each((i:number)=> {
                    for (let j = 0; j < items.length; ++j) {
                        data[j].push(this.mData[i][1 + j])
                    }
                });
                this.updateEchart("国产矿价格趋势（元/吨）", this.option().gc,items, data);
            }

            public updateJkChart() {
                let items = ["青岛港巴西粉矿(63.5品位)", "印度粉矿(60品位)"];
                let data:string[][] = [];
                for (let k = 0; k < items.length; ++k){
                    data.push([]);
                }
                $(this.mData).each((i:number)=> {
                    for (let j = 0; j < items.length; ++j) {
                        data[j].push(this.mData[i][5 + j])
                    }
                })
                this.updateEchart("进口矿价格趋势（元/吨）", this.option().jk, items, data);
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
                        rowNum: 100,
                        data: tableAssist.getData(this.mData),
                        datatype: "local"
                    }));

            }
        }
        export var pluginView = TksView.newInstance();
    }
}
