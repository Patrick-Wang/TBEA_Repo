/// <reference path="../../../jqgrid/jqassist.ts" />
/// <reference path="../../../util.ts" />
/// <reference path="../../../dateSelector.ts" />
/// <reference path="../jcycljgdef.ts" />

declare var echarts;
declare var view:jcycljg.FrameView;

module jcycljg {
    export module jkzj {
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq"),
                    new JQTable.Node("加拿大乔治王子<br/>美元/吨", "a1"),
                    new JQTable.Node("加拿大虹鱼<br/>美元/吨", "a2"),
                    new JQTable.Node("智利金星<br/>美元/吨", "a3")
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            ct:string;
            tb:string;
        }

        class MyzsView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("update.do?type=" + jcycljg.JcycljgType.JKZJ, false);
            private mDateSelector:Util.DateSelector;

            public static newInstance():MyzsView {
                return new MyzsView();
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
                        this.mData = this.formateData(jsonData);
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
                view.register("进口纸浆", this);
            }

            public updateChart() {
                let items = ["加拿大乔治王子", "加拿大虹鱼", "智利金星"];
                let data:string[][] = [];
                for (let k = 0; k < items.length; ++k){
                    data.push([]);
                }
                $(this.mData).each((i:number)=> {
                    for (let j = 0; j < items.length; ++j) {
                        data[j].push(this.mData[i][1 + j])
                    }
                });
                this.updateEchart("进口纸浆价格趋势（美元/吨）", this.option().ct, items, data);
            }

            public  getDateType():DateType {
                return DateType.MONTH;
            }
            private formateData(data:Array<string[]>){
                for (let i = 0; i < data.length; ++i) {
                    for (let j = 0; j < data[i].length; ++j) {
                        if (data[i][j] == null) {
                            data[i][j] = '--';
                        }
                    }
                }
                return data;
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
                        data: data[i].length < 1 ? [0] : Util.replaceNull(data[i])
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
        export var pluginView = MyzsView.newInstance();
    }
}
