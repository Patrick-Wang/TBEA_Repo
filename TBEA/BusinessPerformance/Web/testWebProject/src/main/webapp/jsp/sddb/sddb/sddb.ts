/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../sddbdef.ts"/>
///<reference path="../sddb.ts"/>

module plugin {
    export let sddb:number = framework.basic.endpoint.lastId();
}

module sddb {
    export module sddb {
        import TextAlign = JQTable.TextAlign;
        import Node = JQTable.Node;
        import router = framework.router;
        import FRAME_ID = framework.basic.endpoint.FRAME_ID;

        class JQGridAssistantFactory {
            public static createTable(gridName:string, headers:Util.Header[]):JQTable.JQGridAssistant {

                let nodes:Node[] = [];
                for (let i = 0; i < headers.length; ++i) {
                    let node = Util.parseHeader(headers[i]);
                    if (null != node) {
                        nodes.push(node);
                    }
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            }
        }

        interface ChartCtrl {
            xNames:string[];
            yNames:string[];
            title:string;
            type:string;
            data:any[];
            boundaryGap:boolean;
            isValid:string;
            chartName:string;
            percentage:boolean;
        }

        interface SddbResp extends Util.ServResp {
            charts:ChartCtrl[];
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:SddbResp;
            private mAjax:Util.Ajax;
            private mDt:string;
            private mCompType:Util.CompanyType;


            getId():number {
                return plugin.sddb;
            }

            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case framework.basic.FrameEvent.FE_UPDATE:
                    {
                        this.pluginUpdate(e.data.dStart, e.data.dEnd, e.data.compType, e.data.item, e.data.item0);
                    }
                        return;
                    case framework.basic.FrameEvent.FE_GET_EXPORTURL:
                    {

                        return this.pluginGetExportUrl(e.data.dStart, e.data.dEnd, e.data.compType, e.data.item, e.data.item0);
                    }
                    default:
                        break;
                }
                return super.onEvent(e);
            }

            pluginGetExportUrl(dStart:string, dEnd:string, compType:Util.CompanyType, item:any, item0:any):string {
                return this.option().exportUrl + "?" + Util.Ajax.toUrlParam({
                        dStart: dStart,
                        dEnd: dEnd,
                        item: compType,
                        model: item,
                        item0: item0
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(dStart:string, dEnd:string, compType:Util.CompanyType, item:any, item0:any):void {
                this.mCompType = compType;
                if (undefined != dStart) {
                    let dS = new Date(Date.parse(dStart.replace(/-/g, '/'))).getTime();
                    let dE = new Date(Date.parse(dEnd.replace(/-/g, '/'))).getTime();
                    if (dS <= dE) {
                        this.mAjax.get({
                                dStart: dStart,
                                dEnd: dEnd,
                                item: compType,
                                model: item,
                                item0: item0
                            })
                            .then((jsonData:any) => {
                                this.mData = jsonData;
                                this.refresh();
                            });
                    } else {
                        Util.MessageBox.tip("起始时间不能晚于结束时间");
                    }
                } else {
                    this.mAjax.get({
                            dStart: dStart,
                            dEnd: dEnd,
                            item: compType,
                            model: item,
                            item0: item0
                        })
                        .then((jsonData:any) => {
                            this.mData = jsonData;
                            this.refresh();
                        });
                }
            }

            public refresh():void {
                if (this.mData == undefined) {
                    return;
                }

                if (this.mData.showTime == false){
                    router.to(FRAME_ID).send(SDDBEvent.FE_HIDETIME);
                }else{
                    router.to(FRAME_ID).send(SDDBEvent.FE_SHOWTIME);
                }


                this.updateTable();
                this.updateCharts();
            }

            public init(opt:Option):void {
                this.mAjax = new Util.Ajax(opt.updateUrl, false)
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, opt.title);
            }

            private updateCharts():void {

                let display = "none";
                if (undefined != this.mData.charts) {

                    let validCount = 0;
                    for (let i = 0; i < this.mData.charts.length; ++i) {
                        if (this.mData.charts[i].isValid == true) {
                            ++validCount;
                        }
                    }
                    if (validCount != 0) {
                        display = "";
                        $("#" + this.mOpt.chartId).css("display", "")
                            .css("width", this.mData.width == undefined ? 1300 : this.mData.width)
                            .css("height", validCount / 2 * 300 + validCount % 2 * 300);
                        $("#chartName").css("display", "")[0].innerHTML=this.mData.chartName;
                    }


                    for (let i = 0; i < this.mData.charts.length; ++i) {
                        if (this.mData.charts[i].isValid == true) {
                            let ctSel = $("#" + this.mOpt.chartId + i);
                            if (ctSel.length == 0) {
                                $("#" + this.mOpt.chartId)
                                    .append("<div id='" + this.mOpt.chartId + i + "' style='float:left;width:49%;height:300px'/>")
                            }
                            if (validCount == 1){
                                $("#" + this.mOpt.chartId + "0").css("width", "98%");
                            }
                            this.updateChart(this.mOpt.chartId + i, this.mData.charts[i]);
                        }
                    }


                }

                $("#" + this.mOpt.chartId).css("display", display);
                $("#chartName").css("display", display);
            }


            private getMonth():number {
                let curDate:Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                return month;
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                //var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mData.header);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var tableAssist:JQTable.JQGridAssistant = Util.createTable(name, this.mData);
                this.$(name).jqGrid(
                    tableAssist.decorate({
                        datatype: "local",
                        data: tableAssist.getData(this.mData.data),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        autoScroll: true,
                        rowNum: 1000,
                        height: this.mData.data.length > 25 ? 550 : '100%',
                        width: this.mData.width == undefined ? 1300 : this.mData.width,
                        shrinkToFit: this.mData.shrinkToFit == "false" ? false : true
                    }));
            }

            private updateChart(ctId:any, chart:ChartCtrl):void {
                let series = [];
                for (let i in chart.yNames) {
                    series.push({
                        name: chart.yNames[i],
                        type: chart.type,
                        smooth: true,
                        //barCategoryGap: "50%",
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: chart.data[i].length < 1 ? [0] : Util.replaceNull(chart.data[i])
                    })
                }

                let tooltip : any = {
                    trigger: 'axis'
                };
                let yAxis : any = [
                    {
                        type: 'value'
                    }
                ];
                if (chart.percentage){
                    tooltip.formatter =  function (params) {
                        var ret = params[0][1];
                        for (var i = 0; i < params.length; ++i) {
                            ret += "<br/>" + params[i][0] + ' : ' + (params[i][2] * 1.0).toFixed(2) + "%";
                        }
                        return ret;
                    };
                    yAxis[0].axisLabel = {
                        formatter: '{value} %'
                    };
                }

                var option = {
                    title: {
                        text: chart.title
                    },
                    tooltip: tooltip,
                    legend: {
                        data: chart.yNames
                    },
                    toolbox: {
                        show: true,
                    },
                    calculable: false,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: chart.boundaryGap,
                            data: chart.xNames.length < 1 ? [0] : chart.xNames
                        }
                    ],
                    yAxis: yAxis,
                    series: series
                };

                echarts.init($("#" + ctId)[0]).setOption(option);
            }
        }
    }
}
