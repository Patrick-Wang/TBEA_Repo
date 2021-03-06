/// <reference path="../../../jqgrid/jqassist.ts" />
/// <reference path="../../../util.ts" />
/// <reference path="../../../dateSelector.ts" />
/// <reference path="../jcycljgdef.ts" />

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
                        .append(new JQTable.Node("铝", "xhal"))
                        .append(new JQTable.Node("锌", "xhzn")),
                    new JQTable.Node("LME结算价（美元/吨）", "cjxh")
                        .append(new JQTable.Node("铜", "LEMcu"))
                        .append(new JQTable.Node("铝", "LEMal"))
                        .append(new JQTable.Node("锌", "LEMzn"))
                ], gridName);
            }
        }

        interface Option extends PluginOption{
            cu:string;
            al:string;
            zn:string;
            tb:string;
        }

        class YsjsView extends BasePluginView{
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/jcycljg/update.do?type=" + jcycljg.JcycljgType.YSJS, false);
            tableAssist:JQTable.JQGridAssistant;

            public static newInstance():YsjsView {
                return new YsjsView();
            }

            private option() : Option{
                return <Option>this.mOpt;
            }

            public refresh() : void{
                if ( this.mData == undefined){
                    return;
                }

                //if (this.mDispType == DisplayType.CHART) {
                    this.updateCuChart();
                    this.updateAlChart();
                    this.updateZnChart();
                //}else{
                    this.updateTable();
                //}
                this.adjustSize();
            }
            pluginUpdate(start:string, end:string):void {
                this.mAjax.get({
                        start: start,
                        end: end
                    })
                    .then((jsonData:any) => {
                        this.mData = this.formateData(jsonData);
                        this.refresh();
                    });
            }

            public init(opt:Option):void {
                super.init(opt);
                view.register("有色金属类", this);
            }

            public updateCuChart() {
                var data:string[] = [];
                var lemData:string[] = [];
                $(this.mData).each((i:number)=> {
                    data.push(this.mData[i][1]);
                    lemData.push(this.mData[i][4]);
                });
                this.updateEchart("铜 结算价格趋势", this.option().cu, data, lemData);
            }

            public updateAlChart() {
                var data:string[] = [];
                let lemData:string[] = [];
                $(this.mData).each((i:number)=> {
                    data.push(this.mData[i][2]);
                    lemData.push(this.mData[i][5]);
                });
                this.updateEchart("铝 结算价格趋势", this.option().al, data, lemData);
            }

            public updateZnChart() {
                var data:string[] = [];
                var lemData:string[] = [];
                $(this.mData).each((i:number)=> {
                    data.push(this.mData[i][3]);
                    lemData.push(this.mData[i][6]);
                });
                this.updateEchart("锌 结算价格趋势", this.option().zn, data, lemData);
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
                echarts.init(this.$(echart)[0]).setOption(option);

            }

            adjustSize(){
                super.adjustSize();
                this.updateAlChart();
                this.updateZnChart();
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


       export  var pluginView = YsjsView.newInstance();
    }
}
