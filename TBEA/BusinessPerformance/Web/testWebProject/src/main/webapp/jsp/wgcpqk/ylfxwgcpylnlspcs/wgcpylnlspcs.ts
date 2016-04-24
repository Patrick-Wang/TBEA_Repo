/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../wgcpqkdef.ts"/>

module plugin {
        
    export let wgcpylnlspcs : number = framework.basic.endpoint.lastId();
    
    export let byq_zh : number = framework.basic.endpoint.lastId();
    
    export let byq_dydj : number = framework.basic.endpoint.lastId();
    
    export let byq_cpfl : number = framework.basic.endpoint.lastId();
    
    export let byq_cpfl_t1 : number = framework.basic.endpoint.lastId();
    
    export let xl_zh : number = framework.basic.endpoint.lastId();
    
    export let xl_cpfl : number = framework.basic.endpoint.lastId();
}

module  ylfxwgcpylnlspcs {
    export module wgcpylnlspcs {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, date : string):JQTable.JQGridAssistant {
                
                let curDate : Date = new Date(date);
                let month = curDate.getMonth() + 1;
                let data = [];
                let node : JQTable.Node;
                let titleNodes : JQTable.Node[] = [];
                
                node = new JQTable.Node("产品", "wlyddmlspcs_cp", true, TextAlign.Left);
                titleNodes.push(node);
                
                node = new JQTable.Node("上年度", "wlyddmlspcs_snd", true, TextAlign.Center);
                for (let i = month + 1; i <= 12; ++i){
                    node.append(new JQTable.Node(i + "月", "wlyddmlspcs_snd_" + i));
                }
                
                if (month != 12) {
                    titleNodes.push(node);
                }
                
                node = new JQTable.Node("本年度", "wlyddmlspcs_bnd", true, TextAlign.Center);
                for (let i = 1; i <= month; ++i){
                    node.append(new JQTable.Node(i + "月", "wlyddmlspcs_bnd_" + i));
                }               
                titleNodes.push(node);
                
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }
      
       class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
           
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("wgcpylnlspcs/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;
            private mWgcpqkType: wgcpqk.WgcpqkType;

            getId():number {
                return plugin.wgcpylnlspcs;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "wgcpylnlspcs/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType,
                        wgcpqkType:this.mWgcpqkType
                    });
            }

            private option():wgcpqk.Option{
                return <wgcpqk.Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
                this.mAjax.get({
                        date: date,
                        companyId:compType,
                        wgcpqkType:this.mWgcpqkType
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

            isSupported(compType: Util.CompanyType): boolean {
                if (this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_DYDJ ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL ||
                    this.mWgcpqkType == wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL_T1) {
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

                    
            public init(opt:Option):void {
                
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.wgcpylnlspcs, plugin.byq_zh))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "完工产品盈利能力变化趋势-综合");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.wgcpylnlspcs, plugin.byq_dydj))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "完工产品盈利能力变化趋势-电压等级");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.wgcpylnlspcs, plugin.byq_cpfl))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "完工产品盈利能力变化趋势-产品分类");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.wgcpylnlspcs, plugin.byq_cpfl_t1))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "完工产品盈利能力变化趋势-产品分类特殊1");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.wgcpylnlspcs, plugin.xl_zh))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "线缆未履约订单毛利水平测算-综合");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.wgcpylnlspcs, plugin.xl_cpfl))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "线缆未履约订单毛利水平测算-产品分类");
            }

            onEvent(e: framework.route.Event): any {
                
                switch (e.id) {
                    case plugin.byq_zh:
                        this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_ZH;
                        break;
                    case plugin.byq_dydj:
                        this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_DYDJ;
                        break;
                    case plugin.byq_cpfl:
                        this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL;
                        break;
                    case plugin.byq_cpfl_t1:
                        this.mWgcpqkType = wgcpqk.WgcpqkType.YLFX_WGCPYLNL_BYQ_CPFL_T1;
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
                        rowNum: 30,
                        data: tableAssist.getData(this.mData),
                        datatype: "local",
                        viewrecords : true
                    }));
            }
        }
    }
}
