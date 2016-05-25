/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqk.ts"/>

module plugin {
    export let xlbhgcpmx : number = framework.basic.endpoint.lastId();
}

module cpzlqk {
    export module xlbhgcpmx {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    Node.create({name : "单位", align : TextAlign.Center}),
                    Node.create({name : "产品类型", align : TextAlign.Center,}),
                    Node.create({name : "生产号", align : TextAlign.Center,}),
                    Node.create({name : "产品型号", align : TextAlign.Center,}),
                    Node.create({name : "不合格数量", align : TextAlign.Center,}),
                    Node.create({name : "试验不合格现象", align : TextAlign.Center,}),
                    Node.create({name : "不合格类别", align : TextAlign.Center,}),
                    Node.create({name : "原因分析", align : TextAlign.Center,}),
                    Node.create({name : "处理措施", align : TextAlign.Center,}),
                    Node.create({name : "处理结果", align : TextAlign.Center,}),
                    Node.create({name : "责任类别", align : TextAlign.Center,})
                ], gridName);
            }
        }

        class ShowView extends ZlPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("../xlbhgcpmx/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;

            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.LLGS || compType == Util.CompanyType.DLGS
                    ||compType == Util.CompanyType.XLC;
            }

            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case Event.ZLFE_IS_YDJD_SUPPORTED:
                        return false;
                }
                return super.onEvent(e);
            }

            getId():number {
                return plugin.xlbhgcpmx;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../xlbhgcpmx/export.do?" + Util.Ajax.toUrlParam({
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
					.send(framework.basic.FrameEvent.FE_REGISTER, "不合格产品明细");
            }

			private getMonth():number{
				let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
				return month;
			}
			
            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var pagername = name + "pager";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                tableAssist.mergeColum(0);
                tableAssist.mergeTitle();
                tableAssist.mergeRow(0);
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
                        rowNum: 20,
                        viewrecords : true,
                        pager: '#' + pagername,
                    }));
            }
        }
    }
}
