/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqk.ts"/>

module plugin {
    export let byqcpycssbhgxxfb : number = framework.basic.endpoint.lastId();
    export let byqybysqfysxxfb : number = framework.basic.endpoint.lastId();
    export let byqybyspbcpxxfb : number = framework.basic.endpoint.lastId();
}

module cpzlqk {
    export module byqcpycssbhgxxfb {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, title: string[]):JQTable.JQGridAssistant {
                let nodes : Node[] = [Node.create({name : "单位", align: TextAlign.Center})];
                for (let i in title){
                    nodes.push(Node.create({name : title[i]}));
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            }
        }

        interface BhgxxfbResp{
            bhglbs : string[];
            result : string[][];
        }
        class ShowView extends ZlPluginView  {
            static ins = new ShowView();
            private mData:BhgxxfbResp;
            private mAjax:Util.Ajax = new Util.Ajax("../byqcpycssbhgxxfb/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;
            private mBhgmxType : ByqBhgType;

            getId():number {
                return plugin.byqcpycssbhgxxfb;
            }
            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.SBGS || compType == Util.CompanyType.HBGS
                    ||compType == Util.CompanyType.XBC || compType == Util.CompanyType.TBGS;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../byqcpycssbhgxxfb/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType,
                        bhgType:this.mBhgmxType,
                        ydjd:this.mYdjdType,
                        all: this.mCompSize > 1
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
                        bhgType:this.mBhgmxType,
                        ydjd:this.mYdjdType,
                        all: this.mCompSize > 1
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

            onEvent(e: framework.route.Event): any {
                if (e.road != undefined) {
                    if (plugin.byqybysqfysxxfb == e.road[e.road.length - 1]){
                        this.mBhgmxType = ByqBhgType.YBYSQFJYS;
                    }else{
                        this.mBhgmxType = ByqBhgType.PBCP;
                    }
                }
                return super.onEvent(e);
            }

            public init(opt:Option):void {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.byqybysqfysxxfb, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "110kV及以上产品送试不合格现象分布");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.byqybyspbcpxxfb, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "配变产品送试不合格现象分布");
            }

			private getMonth():number{
				let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
				return month;
			}
			
            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mData.bhglbs);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                tableAssist.mergeRow(0);
                tableAssist.mergeColum(0);
                this.$(name).jqGrid(
                    tableAssist.decorate({
						datatype: "local",
						data: tableAssist.getData(this.mData.result),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 1000,
                        viewrecords : true
                    }));
            }
        }
    }
}
