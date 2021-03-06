/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../yszkgbdef.ts" />

declare var echarts;
declare var view:yszkgb.FrameView;

module yszkgb {
    export module yqyszcsys {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "rq", true, TextAlign.Center),
                    new JQTable.Node("月度", "rqa", true, TextAlign.Center),
                    new JQTable.Node("内部因素", "ab"),
                    new JQTable.Node("客户资信", "ac"),
                    new JQTable.Node("滚动付款", "ad"),
                    new JQTable.Node("项目变化", "ae"),
                    new JQTable.Node("合同因素", "af"),
                    new JQTable.Node("手续办理", "ag"),
                    new JQTable.Node("诉讼", "ah"),
                    new JQTable.Node("合计", "ai")
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            tb:string;
        }

        class YqyszcsysView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("yqyszcsys/update.do", false);

            private mDateSelector:Util.DateSelector;
            private mDt: string;

            public static newInstance():YqyszcsysView {
                return new YqyszcsysView();
            }

            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "yqyszcsys/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, cpType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjax.get({
                        date: date,
                        companyId: cpType
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
                this.$(this.option().ctarea).show();
                this.updateEchart(this.updateTable());
            }

            private updateEchart(data:any[]):void {
                let title = "逾期应收账产生因素";

                let legend:Array<string> = [
                    "内部因素",
                    "客户资信",
                    "滚动付款",
                    "项目变化",
                    "合同因素",
                    "手续办理",
                    "诉讼"];

                var xData:string[] = [];
                for (let i = 0; i < data.length; ++i){
                    xData.push(data[i][1]);
                }



                let tooltip : any = {
                    trigger: 'axis',

                };

                let yAxis : any = [
                    {
                        type: 'value',
                    }
                ];

                let series = [];
                for (let i = 0; i < legend.length; ++i){
                    let rData = [];
                    for (let j = 0; j < data.length; ++j){
                        rData.push(data[j][i + 2] == "--" ? 0 : data[j][i + 2]);
                    }
                    series.push({
                        name: legend[i],
                        type: 'line',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: rData
                    });
                }


                var option = {
                    title: {
                        text: title
                    },
                    tooltip: tooltip,
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
                    yAxis: yAxis,
                    series: series
                };

                echarts.init(this.$(this.option().ct)[0]).setOption(option);

            }

            public init(opt:Option):void {
                super.init(opt);
                view.register("逾期应收账产生因素", this);
            }

            private updateTable():any[] {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");

                let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                let data = [];
                for (let i = month + 1; i <= 12; ++i){
                    data.push(["上年度", i + "月"].concat(this.mData[i - month - 1]));
                }
                for (let i = 1; i <= month; ++i){
                    data.push(["本年度", i + "月"].concat(this.mData[12 - month + i - 1]));
                }

                tableAssist.mergeRow(0);
                tableAssist.mergeTitle();
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
                        data: tableAssist.getData(data),
                        datatype: "local",
                        viewrecords : true
                    }));
                return data;
            }
        }

        export var pluginView = YqyszcsysView.newInstance();
    }
}
