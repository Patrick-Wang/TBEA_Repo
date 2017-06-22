/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cwgbjyxxjldef.ts"/>

module plugin {
    export let ndjyxxjl : number = framework.basic.endpoint.lastId();
}

module cwgbjyxxjl {
    export module ndjyxxjl {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, date:string):JQTable.JQGridAssistant {

                let curDate:Date = new Date(Date.parse(date.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                let data = [];
                let node:JQTable.Node;
                let titleNodes:JQTable.Node[] = [];

                node = new JQTable.Node("科目", "cwgbjyxxjl_cp", true, TextAlign.Left, 300);
                titleNodes.push(node);

                node = new JQTable.Node("上年度", "cwgbjyxxjl_snd", true, TextAlign.Center);
                for (let i = month + 1; i <= 12; ++i) {
                    node.append(new JQTable.Node(i + "月", "cwgbjyxxjl_snd_" + i));
                }

                if (month != 12) {
                    titleNodes.push(node);
                }

                node = new JQTable.Node("本年度", "cwgbjyxxjl_bnd", true, TextAlign.Center);
                for (let i = 1; i <= month; ++i) {
                    node.append(new JQTable.Node(i + "月", "cwgbjyxxjl_bnd_" + i));
                }
                titleNodes.push(node);

                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("../ndjyxxjl/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;

            getId():number {
                return plugin.ndjyxxjl;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../ndjyxxjl/export.do?" + Util.Ajax.toUrlParam({
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
                framework.router
					.fromEp(this)
					.to(framework.basic.endpoint.FRAME_ID)
					.send(framework.basic.FrameEvent.FE_REGISTER, "经营性现金流年度情况");
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
                        height: '100%',
                        width: 1400,
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 40,
                        data: tableAssist.getData(this.mData),
                        datatype: "local",
                        viewrecords : true
                    }));
            }
        }
    }
}
