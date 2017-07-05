/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../wgcpqkdef.ts"/>

module plugin {
    export let wgcpylnlspcs:number = framework.basic.endpoint.lastId();

    export let byq_zh:number = framework.basic.endpoint.lastId();

    export let byq_mll:number = framework.basic.endpoint.lastId();

    export let xl_zh:number = framework.basic.endpoint.lastId();

    export let xl_cpfl:number = framework.basic.endpoint.lastId();

}

module ylfxwgcpylnlspcs {
    export module wgcpylnlspcs {
        
        import TextAlign = JQTable.TextAlign;
        import Option = wgcpqk.Option;
        
        class JQGridAssistantFactory {
            public static createTable(gridName:string, date:string):JQTable.JQGridAssistant {

                let curDate:Date = new Date(Date.parse(date.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                let data = [];
                let node:JQTable.Node;
                let titleNodes:JQTable.Node[] = [];

                node = new JQTable.Node("产品", "wgcpylnlspcs_cp", true, TextAlign.Left);
                titleNodes.push(node);

                node = new JQTable.Node("上年度", "wgcpylnlspcs_snd", true, TextAlign.Center);
                for (let i = month; i <= 12; ++i) {
                    node.append(new JQTable.Node(i + "月", "wgcpylnlspcs_snd_" + i));
                }

                titleNodes.push(node);

                node = new JQTable.Node("本年度", "wgcpylnlspcs_bnd", true, TextAlign.Center);
                for (let i = 1; i <= month; ++i) {
                    node.append(new JQTable.Node(i + "月", "wgcpylnlspcs_bnd_" + i));
                }
                titleNodes.push(node);

                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/wgcpylnlspcs/update.do", false);
            private tableAssist:JQTable.JQGridAssistant;
            private mDt: string;
            private mWgcpqkType: wgcpqk.WgcpqkType;

            getId():number {
                return plugin.wgcpylnlspcs;
            }
            
            
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "/BusinessManagement/wgcpylnlspcs/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType,
                        wgcpqkType:this.mWgcpqkType
                    });
            }
            private option():wgcpqk.Option {
                return <wgcpqk.Option>this.mOpt;
            }

             public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjax.get({
                        date: date,
                        companyId: compType,
                        wgcpqkType: this.mWgcpqkType
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

                this.updateTable();
            }
                        
             isSupported(compType:Util.CompanyType):boolean {

                if (this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_MLL) {
                    if (compType == Util.CompanyType.BYQCY
                    ) {
                        return true;
                    }
                }

                if (this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_XL_ZH ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_XL_CPFL) {
                    if (compType == Util.CompanyType.XLCY) {
                        return true;
                    }
                }

                if (this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_XL_ZH) {
                    if (compType == Util.CompanyType.SBDCYJT) {
                        return true;
                    }
                }


                if (this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_MLL) {
                    if (compType == Util.CompanyType.SBGS ||
                        compType == Util.CompanyType.HBGS ||
                        compType == Util.CompanyType.XBC ||
                        compType == Util.CompanyType.TBGS
                    ) {
                        return true;
                    }
                } else {
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS
                    ) {
                        return true;
                    }
                }
                return false;
            }
            
            pluginGetUnit():string{
                return "单位：百分比";
            }


           public init(opt:Option):void {

                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.byq_zh, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "完工产品盈利能力变化趋势-综合");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.byq_mll, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "完工产品盈利能力变化趋势（毛利率）");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.xl_zh, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "完工产品盈利能力变化趋势-综合");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.xl_cpfl, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "完工产品盈利能力变化趋势（毛利率）");
            }
    
            onEvent(e:framework.route.Event):any {
                        if (e.road != undefined) {
                            switch (e.road[e.road.length - 1]) {
                                case plugin.byq_zh:
                                    this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH;
                                    break;
                                case plugin.byq_mll:
                                    this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_MLL;
                                    break;
                                case plugin.xl_zh:
                                    this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_XL_ZH;
                                    break;
                                case plugin.xl_cpfl:
                                    this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_XL_CPFL;
                                    break;
                                default:
                                    this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH;
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
                this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);

                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                //this.$(this.option().ct).css("height", "300px");
                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart(this.mFinalData);
            }

            private createJqassist():JQTable.JQGridAssistant{
                let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
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
