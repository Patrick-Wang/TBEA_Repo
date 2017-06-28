/// <reference path="../../../jqgrid/jqassist.ts" />
/// <reference path="../../../util.ts" />
/// <reference path="../../../dateSelector.ts" />
/// <reference path="../jcycljgdef.ts" />

declare var echarts;
declare var view:jcycljg.FrameView;

module jcycljg {
    export module eva {
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq"),
                    new JQTable.Node("北京有机（14-2）<br/>元/吨", "a1"),
                    new JQTable.Node("北京有机（18-3）<br/>元/吨", "a2"),
                    new JQTable.Node("扬巴V5110J<br/>元/吨", "a3")
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            ct:string;
            tb:string;
        }

        class EVAView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/jcycljg/update.do?type=" + jcycljg.JcycljgType.EVA, false);
            tableAssist:JQTable.JQGridAssistant;

            public static newInstance():EVAView {
                return new EVAView();
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

                //if (this.mDispType == DisplayType.CHART) {
                    this.updateChart();
                //}else{
                    this.updateTable();
                //}
                this.adjustSize();
            }
            public init(opt:Option):void {
                super.init(opt);
                view.register("EVA", this);
            }

            public updateChart() {
                let items = ["北京有机（14-2）", "北京有机（18-3）", "扬巴V5110J"];
                let data:string[][] = [];
                for (let k = 0; k < items.length; ++k){
                    data.push([]);
                }
                $(this.mData).each((i:number)=> {
                    for (let j = 0; j < items.length; ++j) {
                        data[j].push(this.mData[i][1 + j])
                    }
                });
                this.updateEchart("EVA价格趋势（元/吨）", this.option().ct, items, data);
            }

            public  getDateType():DateType {
                return DateType.DAY;
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
                this.$(echart).empty();
                this.$(echart).removeAttr("_echarts_instance_");
                echarts.init(this.$(echart)[0]).setOption(option);

            }

            adjustSize(){
                super.adjustSize();
                this.updateChart();
            }

            createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'><div id='" + this.jqgridName() + "pager" + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                return this.tableAssist;
            }

            private updateTable():void {
                this.createJqassist();

                this.tableAssist.create({
                    data: this.mData,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 15,
                    autoScroll: true,
                    pager : this.jqgridName() + "pager"
                });

            }

            //private updateTable():void {
            //    var name = this.option().host + this.option().tb + "_jqgrid_1234";
            //    var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            //    var parent = this.$(this.option().tb);
            //    parent.empty();
            //    parent.append("<table id='" + name + "'></table><div id='" + name + "pager" + "'></div>");
            //    this.$(name).jqGrid(
            //        tableAssist.decorate({
            //            multiselect: false,
            //            drag: false,
            //            resize: false,
            //            height: '100%',
            //            width: 1200,
            //            shrinkToFit: true,
            //            autoScroll: true,
            //            rowNum: 20,
            //            data: tableAssist.getData(this.mData),
            //            datatype: "local",
            //            viewrecords : true,
            //            pager : name + "pager"
            //        }));
            //}
        }
        export var pluginView = EVAView.newInstance();
    }
}
