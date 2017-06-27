/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../sbdscqyqkdef.ts"/>

module plugin {
    export let xfcpqy : number = framework.basic.endpoint.lastId();
    
    export let xfcpqy_byq:number = framework.basic.endpoint.lastId();

    export let xfcpqy_xl:number = framework.basic.endpoint.lastId();
}

module sbdscqyqk {
    export module xfcpqy {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
             public static createTable(gridName:string, date:string):JQTable.JQGridAssistant {

                let curDate:Date = new Date(Date.parse(date.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                let data = [];
                let node:JQTable.Node;
                let titleNodes:JQTable.Node[] = [];
                node = new JQTable.Node("产品", "cp", true, TextAlign.Left);
                titleNodes.push(node);
                node = new JQTable.Node("上年度", "snd", true, TextAlign.Center);
                for (let i = month; i <= 12; ++i) {
                    node.append(new JQTable.Node(i + "月", "snd_" + i));
                }
                titleNodes.push(node);
                node = new JQTable.Node("本年度", "sbdscqyqk_bnd", true, TextAlign.Center);
                for (let i = 1; i <= month; ++i) {
                    node.append(new JQTable.Node(i + "月", "bnd_" + i));
                }
                titleNodes.push(node);

                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/xfcpqy/update.do", false);
            private tableAssist:JQTable.JQGridAssistant;
            private mDt: string;
            private mType:sbdscqyqk.SbdscqyqkType;

            getId():number {
                return plugin.xfcpqy;
            }
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "/BusinessManagement/xfcpqy/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType,
                        type: this.mType
                    });
            }
            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjax.get({
                        date: date,
                        companyId:compType,
                        type: this.mType
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
                this.updateTable();

                this.adjustSize();
            }
            
             isSupported(compType:Util.CompanyType):boolean {
                if (this.mType == sbdscqyqk.SbdscqyqkType.BYQ) {
                    if (compType == Util.CompanyType.SBGS ||
                        compType == Util.CompanyType.HBGS ||
                        compType == Util.CompanyType.XBC ||
                        compType == Util.CompanyType.TBGS||
                        compType == Util.CompanyType.BYQCY
                    ) {
                        return true;
                    }
                } else {
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS ||
                        compType == Util.CompanyType.XLCY
                    ) {
                        return true;
                    }
                }
                return false;
            }


            public init(opt:Option):void {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.xfcpqy_byq, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "细分产品签约情况及趋势（国内市场制造业签约）");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.xfcpqy_xl, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "细分产品签约情况及趋势（国内市场制造业签约）");
            }
            
             onEvent(e:framework.route.Event):any {
                if (e.road != undefined) {
                    switch (e.road[e.road.length - 1]) {
                        case plugin.xfcpqy_byq:
                            this.mType = sbdscqyqk.SbdscqyqkType.BYQ;
                            break;
                        case plugin.xfcpqy_xl:
                            this.mType = sbdscqyqk.SbdscqyqkType.XL;
                            break;
                        default:
                            this.mType = sbdscqyqk.SbdscqyqkType.BYQ;
                    }
                }
                return super.onEvent(e);
            }

            private getMonth():number{
                let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                return month;
            }


            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.tableAssist.resizeHeight(maxTableBodyHeight);

                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                //this.$(this.option().ct).css("height", "300px");
                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart(this.mFinalData);
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mDt);
                return this.tableAssist;
            }

            private updateTable():any {
                this.createJqassist();
                this.tableAssist.create({
                    data: this.mData,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true
                });
                return ;
            }
        }
    }
}
