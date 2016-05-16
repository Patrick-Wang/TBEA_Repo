/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkyddef.ts"/>

module plugin {
    export let tjjg : number = framework.basic.endpoint.lastId();

    export let byq_cp : number = framework.basic.endpoint.lastId();
    export let byq_dw : number = framework.basic.endpoint.lastId();
    export let xl_cp : number = framework.basic.endpoint.lastId();
    export let xl_dydj : number = framework.basic.endpoint.lastId();
}

module cpzlqkyd {
    export module tjjg {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, date:string):JQTable.JQGridAssistant {
                let curDate:Date = new Date(date);
                let data = [];
                let node:JQTable.Node;
                let titleNodes:JQTable.Node[] = [];

                node = new JQTable.Node("产品类别", "cpzlqkyd_cplb", true, TextAlign.Left);
                titleNodes.push(node);

                node = new JQTable.Node("当月", "cpzlqkyd_dy", true, TextAlign.Center);
                node.append(new JQTable.Node("不合格数(台)", "cpzlqkyd_dy_bhg"));
                node.append(new JQTable.Node("总数(台)", "cpzlqkyd_dy_zs"));
                node.append(new JQTable.Node("合格率", "cpzlqkyd_dy_hgl"));
                titleNodes.push(node);

                node = new JQTable.Node("年度累计", "cpzlqkyd_ndlj", true, TextAlign.Center);
                node.append(new JQTable.Node("不合格数(台)", "cpzlqkyd_ndlj_bhg"));
                node.append(new JQTable.Node("总数(台)", "cpzlqkyd_ndlj_zs"));
                node.append(new JQTable.Node("合格率", "cpzlqkyd_ndlj_hgl"));
                titleNodes.push(node);

                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("../tjjg/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;
            private mType:cpzlqkyd.CpzlqkType;

            getId():number {
                return plugin.tjjg;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../tjjg/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType,
                        type:this.mType
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
                this.mAjax.get({
                        date: date,
                        companyId:compType,
                        type:this.mType
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
                if (this.mType == cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_XL_CP ||
                    this.mType == cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_XL_DYDJ) {
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS
                    ) {
                        return true;
                    }
                } else {
                    if (this.mType == cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_BYQ_CP ||
                    this.mType == cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_BYQ_DW){
                            if (compType == Util.CompanyType.SBGS ||
                            compType == Util.CompanyType.HBGS ||
                            compType == Util.CompanyType.XBC ||
                            compType == Util.CompanyType.TBGS
                        ) {
                            return true;
                        }
                     }
                }
                return false;
            }

            public init(opt:Option):void {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.byq_cp, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "月度按产品统计结果");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.byq_dw, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "月度按单位统计结果");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.xl_cp, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "月度按产品类型统计结果");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.xl_dydj, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "月度按电压等级统计结果");
            }

            onEvent(e: framework.route.Event): any {
                if (e.road != undefined) {
                    switch (e.road[e.road.length - 1]) {
                        case plugin.byq_cp:
                            this.mType = cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_BYQ_CP;
                            break;
                        case plugin.byq_dw:
                            this.mType = cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_BYQ_DW;
                            break;
                        case plugin.xl_cp:
                            this.mType = cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_XL_CP;
                            break;
                        case plugin.xl_dydj:
                            this.mType = cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_XL_DYDJ;
                            break;
                        default:
                            this.mType = cpzlqkyd.CpzlqkType.CpzlqkType_TJJG_BYQ_CP;
                    }
                }
                return super.onEvent(e);
            }


			private getMonth():number{
				let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
				return month;
			}
			
            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mDt);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(
                    tableAssist.decorate({
						datatype: "local",
						data: tableAssist.getData(this.mData),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        height: '100%',
                        width: 1400,
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 20,
                        viewrecords : true
                    }));
            }
        }
    }
}
