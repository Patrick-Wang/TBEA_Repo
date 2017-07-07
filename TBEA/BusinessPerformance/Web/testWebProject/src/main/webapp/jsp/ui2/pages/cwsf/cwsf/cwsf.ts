/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cwsfdef.ts"/>
///<reference path="../cwsf.ts"/>

module plugin {
    export let cwsf:number = framework.basic.endpoint.lastId();
}

module cwsf {
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
            tableAssist:JQTable.JQGridAssistant;

            getId():number {
                return plugin.cwsf;
            }

            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case framework.basic.FrameEvent.FE_UPDATE:
                    {
                        this.pluginUpdate(e.data.dStart, e.data.dEnd, e.data.comps, e.data.item);
                    }
                        return;
                    case framework.basic.FrameEvent.FE_GET_EXPORTURL:
                    {

                        return this.pluginGetExportUrl(e.data.dStart, e.data.dEnd, e.data.comps, e.data.item);
                    }
                    default:
                        break;
                }
                return super.onEvent(e);
            }

            pluginGetExportUrl(dStart:string, dEnd:string, comps : string, item:any):string {
                return this.option().exportUrl + "?" + Util.Ajax.toUrlParam({
                        dStart: dStart,
                        dEnd: dEnd,
                        comps: comps,
                        item: item
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(dStart:string, dEnd:string, comps : string, item:any):void {

                if (undefined != dStart) {
                    let dS = new Date(Date.parse(dStart.replace(/-/g, '/'))).getTime();
                    let dE = new Date(Date.parse(dEnd.replace(/-/g, '/'))).getTime();
                    if (dS <= dE) {
                        this.mAjax.get({
                                dStart: dStart,
                                dEnd: dEnd,
                                comps: comps,
                                item: item
                            })
                            .then((jsonData:any) => {
                                this.mData = jsonData;
                                this.refresh();
                            });
                    } else {
                        Util.Toast.failed("起始时间不能晚于结束时间");
                    }
                } else {
                    this.mAjax.get({
                            dStart: dStart,
                            dEnd: dEnd,
                            comps: comps,
                            item: item
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

                this.updateTable();

                this.adjustSize();
            }

            public init(opt:Option):void {
                this.mAjax = new Util.Ajax(opt.updateUrl, false)
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, opt.title);
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }


                let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);

                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }


                //if (this.mData.tjjg.length > 0){
                //    this.$(this.option().ctarea).show();
                //
                //    if (this.mYdjdType == YDJDType.JD) {
                //        this.$(this.option().ct1).parent().hide();
                //        this.$(this.option().ct).parent().css("width", "100%");
                //        this.updateJDEchart();
                //    } else {
                //        this.$(this.option().ct).parent().show();
                //        this.$(this.option().ct1).parent().show();
                //        this.$(this.option().ct).parent().css("width", "50%");
                //        this.$(this.option().ct1).parent().css("width", "50%");
                //        this.updateYDEchart();
                //    }
                //}
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></div>");
                this.tableAssist = Util.createTable(this.jqgridName(), this.mData);
                return this.tableAssist;
            }

            private updateTable():any {
                this.createJqassist();

                this.tableAssist.create({
                    data: this.mData.data,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: false,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: this.mData.shrinkToFit == "false" ? false : true,
                    rowNum: 10000,
                    autoScroll: true
                });


            }

            //private updateTable():void {
            //    var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
            //    //var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mData.header);
            //    var parent = this.$(this.option().tb);
            //    parent.empty();
            //    parent.append("<table id='" + name + "'></table>");
            //    var tableAssist:JQTable.JQGridAssistant = Util.createTable(name, this.mData);
            //    this.$(name).jqGrid(
            //        tableAssist.decorate({
            //            datatype: "local",
            //            data: tableAssist.getData(this.mData.data),
            //            multiselect: false,
            //            drag: false,
            //            resize: false,
            //            autoScroll: true,
            //            rowNum: 1000,
            //            height: this.mData.data.length > 25 ? 550 : '100%',
            //            width: this.mData.width == undefined ? 1300 : this.mData.width,
            //            shrinkToFit: this.mData.shrinkToFit == "false" ? false : true
            //        }));
            //}
        }
    }
}
