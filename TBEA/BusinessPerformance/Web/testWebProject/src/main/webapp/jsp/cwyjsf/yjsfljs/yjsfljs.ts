/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cwyjsfdef.ts"/>
module plugin {
    export let yjsfljs : number = framework.basic.endpoint.lastId();
}

module cwyjsf {
    export module yjsfljs {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, year:number):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    Node.create({name : "税种", align : TextAlign.Left}),
                    Node.create({name : "期初余额"}),
                    Node.create({name : year + "年应交税额"})
                        .append( Node.create({name : "本月数"}))
                        .append( Node.create({name : "累计数"})),
                    Node.create({name : year + "年已交税额"})
                        .append( Node.create({name : "本月数"}))
                        .append( Node.create({name : "累计数"})),
                    Node.create({name : year + "年未交税额"})
                        .append( Node.create({name : "本月数"}))
                        .append( Node.create({name : "累计数"})),
                    Node.create({name : "期末余额"})
                ], gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("../yjsfljs/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;

            getId():number {
                return plugin.yjsfljs;
            }
            
            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case Event.CW_ISMONTH_SUPPORTED:
                        return true;
                }
                return super.onEvent(e);
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../yjsfljs/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType
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
                        companyId:compType
                    })
                    .then((jsonData:any) => {
                        this.updateTable(this.option().tb, this.getYear(), jsonData);
                    });
                this.mAjax.get({
                        date: (this.getYear() - 1) + "-" + this.getMonth() + "-1",
                        companyId:compType
                    })
                    .then((jsonData:any) => {
                        this.updateTable(this.option().tb1, this.getYear() - 1, jsonData);
                    });
            }

            public refresh() : void{
                if ( this.mData == undefined){
                    return;
                }
            }

            public init(opt:Option):void {
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "应交税费累计对比");
            }

			private getMonth():number{
				let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
				return month;
			}
            private getYear():number{
                let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                return curDate.getFullYear();
            }
            private updateTable(tbid : string, year:number, data:string[][]):void {
                let name = this.option().host + tbid + "_jqgrid_uiframe";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, year);
                var parent = this.$(tbid);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(
                    tableAssist.decorate({
                        datatype: "local",
                        data: tableAssist.getData(data),
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
