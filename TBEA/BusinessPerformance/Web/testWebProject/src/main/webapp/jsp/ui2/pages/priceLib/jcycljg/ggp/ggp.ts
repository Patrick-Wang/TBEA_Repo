/// <reference path="../../../jqgrid/jqassist.ts" />
/// <reference path="../../../util.ts" />
/// <reference path="../../../dateSelector.ts" />
/// <reference path="../jcycljgdef.ts" />

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
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/jcycljg/update.do?type=" + jcycljg.JcycljgType.GGP, false);
            tableAssist:JQTable.JQGridAssistant;

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
                        this.mData = this.formateData(jsonData);
                        this.refresh();
                    });
            }
            public refresh() : void{
                if ( this.mData == undefined){
                    return;
                }

                //if (this.mDispType == DisplayType.CHART) {
                    this.updateWgChart();
                    this.updateBgChart();
                //}else{
                    this.updateTable();
                //}
            }
            public init(opt:Option):void {
                super.init(opt);
                view.register("硅钢片", this);
            }

            public updateWgChart() {
                let items =  ["30Q120", "30RK100", "27RK095", "23RK085"];
                let data:string[][] = [];
                for (let k = 0; k < items.length; ++k){
                    data.push([]);
                }
                $(this.mData).each((i:number)=> {
                    for (let j = 0; j < items.length; ++j) {
                        data[j].push(this.mData[i][1 + j])
                    }
                });
                this.updateEchart("武钢结算价格趋势（元/吨）", this.option().wg, items, data);
            }

            public updateBgChart() {
                let items =  ["B30P120", "B30P100", "B27R095", "B27R085"];
                let data:string[][] = [];
                for (let k = 0; k < items.length; ++k){
                    data.push([]);
                }
                $(this.mData).each((i:number)=> {
                    for (let j = 0; j < items.length; ++j) {
                        data[j].push(this.mData[i][5 + j])
                    }
                });
                this.updateEchart("宝钢结算价格趋势（元/吨）", this.option().bg, items, data);
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

            adjustSize(){
                super.adjustSize();
                this.updateBgChart();
                this.updateWgChart();
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
        export var pluginView = GgpView.newInstance();
    }
}
