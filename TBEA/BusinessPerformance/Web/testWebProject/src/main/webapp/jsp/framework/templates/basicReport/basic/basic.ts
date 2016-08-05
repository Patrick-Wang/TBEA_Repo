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

            private find(id : number){
                for (let i = this.option().items.length - 1; i >= 0; --i){
                    if (this.option().items[i].data.id == id){
                        return i;
                    }
                }
                return -1;
            }

            onEvent(e: framework.route.Event): any {
                if (e.road != undefined) {
                    let index = this.find(e.road[e.road.length - 1]);
                    if (index >= 0){
                        this.mCurEp = index;
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
                for (var i = 0; i < opt.items.length; ++i){
                    framework.router
                        .fromEp(new framework.basic.EndpointProxy(<number>(opt.items[i].data.id), this.getId()))
                        .to(framework.basic.endpoint.FRAME_ID)
                        .send(framework.basic.FrameEvent.FE_REGISTER, opt.items[i].data.value);
                }

            }

			private getMonth():number{
				let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
				return month;
			}
			
            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist:JQTable.JQGridAssistant = Util.createTable(name, this.m);
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
