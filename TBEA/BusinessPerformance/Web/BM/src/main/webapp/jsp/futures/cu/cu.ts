/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../futuresdef.ts" />

declare var echarts;
declare var view:futures.FrameView;

module futures {
    export module cu {
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    JQTable.Node.create({name:"企业名称", width: 100}),
                    JQTable.Node.create({name:"类型", width: 100}),
                    JQTable.Node.create({name:"持仓量（吨）", width: 100}),
                    JQTable.Node.create({name:"持仓均价（元）", width: 150}),
                    JQTable.Node.create({name:"现价（元）", width: 100}),
                    JQTable.Node.create({name:"盈亏比例", width: 100}),
                    JQTable.Node.create({name:"盈亏金额（元）", width: 150})
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            ct:string;
            tb:string;
        }

        interface chartObject {
            companyName:string;
            dateSet:string[];
            valueList:string[];
        }

        interface receivedData {
            chartData:Array<chartObject>;
            tableData:string[][];
        }

        class CuView extends BasePluginView {
            private mData:receivedData;
            private mChartData:Array<chartObject>;
            private mTableData:string[][];
            private mAjax:Util.Ajax = new Util.Ajax("update.do?type=1", false);

            public static newInstance():CuView {
                return new CuView();
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate():void {
                this.mAjax.get({})
                    .then((jsonData:any) => {
                        this.mData = jsonData;
                        this.mChartData = this.mData.chartData;
                        this.mTableData = this.mData.tableData;
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
                view.register("铜", this);
            }

            public updateChart() {
                let legends :string[] = [];
                let xData:string[] = [];
                let data:string[][] = [];
                //for (let k = 0; k < items.length; ++k){
                //    data.push([]);
                //}
                $(this.mChartData).each((i:number)=> {
                    legends.push(this.mChartData[i].companyName);
                    xData = this.mChartData[i].dateSet;
                    //for (let j = 0; j < legends.length; ++j) {
                    data[i] = this.mChartData[i].valueList;
                    //}
                });
                this.updateEchart("期货利润（元）", this.option().ct, legends, xData, data);
            }

            private updateEchart(title:string, echart:string, legend:Array<string>, xData:Array<string>, data:Array<string[]>):void {
                let series = [];
                for (let i in legend) {
                    series.push({
                        name: legend[i],
                        type: 'line',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: data[i]
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
                            data: xData
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
                        width: '100%',
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 20,
                        data: tableAssist.getData(this.mTableData),
                        datatype: "local",
                        viewrecords : true,
                        pager : name + "pager"
                    }));
            }
        }
        export var pluginView = CuView.newInstance();
    }
}
