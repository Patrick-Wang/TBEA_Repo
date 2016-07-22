/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../sbdscqyqkdef.ts"/>

module plugin {
    export let xfcpqy : number = framework.basic.endpoint.lastId();
    
    export let xfcpqy_byq:number = framework.basic.endpoint.lastId();

    export let xfcpqy_xl:number = framework.basic.endpoint.lastId();
}

module sbdscqyqk {
    export module xfcpqy {
        import TextAlign = JQTable.TextAlign;
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
                //if (month != 12) {
                //    titleNodes.push(node);
                //}

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
            private mAjax:Util.Ajax = new Util.Ajax("../xfcpqy/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;
            private mType:sbdscqyqk.SbdscqyqkType;

            getId():number {
                return plugin.xfcpqy;
            }

            pluginGetUnit():string{
                return "单位：万元";
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../xfcpqy/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType,
                        type: this.mType
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

                this.updateTable();
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
			
            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mDt);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(
                    tableAssist.decorate({
                        multiselect: false,
                        drag: false,
                        resize: false,
                        height: this.mData.length > 25 ? 550 : '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 1000,
                        data: tableAssist.getData(this.mData),
                        datatype: "local",
                        viewrecords : true
                    }));
            }
        }
    }
}
