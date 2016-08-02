/// <reference path="../../../../jqgrid/jqassist.ts" />
/// <reference path="../../../../util.ts" />
/// <reference path="../../../../dateSelector.ts" />
/// <reference path="../../../basic/basicdef.ts"/>
/// <reference path="../../../route/route.ts"/>
/// <reference path="../basicReportdef.ts"/>

module plugin {
    export let basic : number = framework.basic.endpoint.lastId();
}

module basicReport {
    export module basic {

        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return Util.JQGridAssistantFactory.createTable(gridName)
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("../basic/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;
            resp:Util.ServResp;
            mCurEp;
            getId():number {
                return plugin.basic;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../basic/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }


            onEvent(e: framework.route.Event): any {
                if (e.road != undefined) {
                    if (this.option().ids.indexOf(<string>(e.road[e.road.length - 1])) >= 0){
                        this.mCurEp = this.option().ids.indexOf(<string>(e.road[e.road.length - 1]));
                    }
                }
                return super.onEvent(e);
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
                this.mAjax.get({
                        date: date,
                        companyId:compType,
                        item:this.mCurEp
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

            public init(opt:Option):void {
                for (var i = 0; i < opt.ids.length; ++i){
                    framework.router
                        .fromEp(new framework.basic.EndpointProxy(<number>(opt.ids[i]), this.getId()))
                        .to(framework.basic.endpoint.FRAME_ID)
                        .send(framework.basic.FrameEvent.FE_REGISTER, opt.names[i]);
                }

            }

			private getMonth():number{
				let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
				return month;
			}
			
            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
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
