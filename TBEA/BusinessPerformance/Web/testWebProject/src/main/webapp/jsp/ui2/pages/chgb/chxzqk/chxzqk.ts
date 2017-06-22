/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../chgbdef.ts"/>

module plugin {
    export let chxzqk : number = framework.basic.endpoint.lastId();
}

module chgb {
    export module chxzqk {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "chxzqk_yd", true, TextAlign.Center),
                    new JQTable.Node("月度", "chxzqk_ydtwo", true, TextAlign.Center),
                    new JQTable.Node("原材料", "chxzqk_ycl"),
                    new JQTable.Node("半成品", "chxzqk_bcp"),
                    new JQTable.Node("实际库存商品", "chxzqk_sjkcsp"),
                    new JQTable.Node("已发货未开票", "chxzqk_yfhwkp"),
                    new JQTable.Node("期货浮动盈亏(盈+，亏-)", "chxzqk_qhfdyk"),
                    new JQTable.Node("期货平仓盈亏(盈-，亏+)", "chxzqk_qhpc"),
                    new JQTable.Node("未发货已开票", "chxzqk_wfhykp"),
                    new JQTable.Node("其他", "chxzqk_qt"),
                    new JQTable.Node("合计", "chxzqk_hj")
                ], gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("chxzqk/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;

            getId():number {
                return plugin.chxzqk;
            }
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "chxzqk/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType
                    });
            }
            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, cpType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = cpType;
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
                let title = "存货性质情况";

                let legend:Array<string> = [
                    "原材料",
                    "半成品",
                    "实际库存商品"];

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
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "存货性质情况");
            }

			private getMonth():number{
				let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
				return month;
			}
			
            private updateTable():any[] {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
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
    }
}
