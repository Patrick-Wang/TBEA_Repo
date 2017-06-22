/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../wgcpqkdef.ts"/>

module plugin {

    export let wgcpylnlspcs:number = framework.basic.endpoint.lastId();

    export let byq_zh:number = framework.basic.endpoint.lastId();

    export let byq_mll:number = framework.basic.endpoint.lastId();

    export let xl_zh:number = framework.basic.endpoint.lastId();

    export let xl_cpfl:number = framework.basic.endpoint.lastId();
}

module  ylfxwgcpylnlspcs {
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

               // if (month != 12) {
                    titleNodes.push(node);
              //  }

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
            private mAjax:Util.Ajax = new Util.Ajax("../wgcpylnlspcs/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt:string;
            private mCompType:Util.CompanyType;
            private mWgcpqkType:wgcpqk.WgcpqkType;

            getId():number {
                return plugin.wgcpylnlspcs;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../wgcpylnlspcs/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: compType,
                        wgcpqkType: this.mWgcpqkType
                    });
            }

            private option():wgcpqk.Option {
                return <wgcpqk.Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
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

            public refresh():void {
                if (this.mData == undefined) {
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

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mDt);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");


                this.$(name).jqGrid(
                    tableAssist.decorate({
                        multiselect: false,
                        drag: false,
                        resize: false,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 1000,
                        data: tableAssist.getData(this.mData),
                        datatype: "local",
                        viewrecords: true
                    }));
            }
        }
    }
}
