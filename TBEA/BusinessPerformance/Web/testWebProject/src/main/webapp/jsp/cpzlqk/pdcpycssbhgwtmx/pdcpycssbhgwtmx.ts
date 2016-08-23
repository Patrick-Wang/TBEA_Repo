/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqk.ts"/>

module plugin {
    export let pdcpycssbhgwtmx : number = framework.basic.endpoint.lastId();
    export let pdybysqfyswtmx : number = framework.basic.endpoint.lastId();
    export let pdybyspbcpwtmx : number = framework.basic.endpoint.lastId();
}

module cpzlqk {
    export module pdcpycssbhgwtmx {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
					Node.create({name : "单位", align : TextAlign.Center}),
                    Node.create({name : "发生日期", align : TextAlign.Center}),
                    Node.create({name : "产品类型", align : TextAlign.Center}),
                    Node.create({name : "生产号", align : TextAlign.Center}),
                    Node.create({name : "产品型号", align : TextAlign.Center}),
                    Node.create({name : "试验不合格现象", align : TextAlign.Center}),
                    Node.create({name : "不合格类别", align : TextAlign.Center}),
                    Node.create({name : "原因分析", align : TextAlign.Center}),
                    Node.create({name : "处理措施", align : TextAlign.Center}),
                    Node.create({name : "处理结果", align : TextAlign.Center}),
                    Node.create({name : "责任类别", align : TextAlign.Center})
                ], gridName);
            }
        }

        class ShowView extends ZlPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("../pdcpycssbhgwtmx/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;
            private mCommentGet:Util.Ajax = new Util.Ajax("../report/zlfxUpdate.do", false);
            private mCommentSubmit:Util.Ajax = new Util.Ajax("../report/zlfxSubmit.do", false);
            getId():number {
                return plugin.pdcpycssbhgwtmx;
            }
            protected isSupported(compType:Util.CompanyType):boolean {
                return compType == Util.CompanyType.SBZTFGS || compType == Util.CompanyType.HBDQFGS
                    || compType == Util.CompanyType.XBZTGS || compType == Util.CompanyType.TBGS
                    || compType == Util.CompanyType.PDCY;
            }
            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../pdcpycssbhgwtmx/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType,
                        ydjd:this.mYdjdType,
                        all: this.mCompType == Util.CompanyType.PDCY
                    });
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mCompType = compType;
                this.mCommentGet.get({condition:Util.Ajax.toUrlParam({
                    url : this.mAjax.baseUrl(),
                    date: date,
                    companyId:compType,
                    ydjd:this.mYdjdType
                }),compId:compType}).then((jsonData:any)=>{
                    if (jsonData.deny == "deny"){
                        framework.router
                            .fromEp(this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(Event.ZLFE_COMMENT_DENY);
                    }else {
                        framework.router
                            .fromEp(this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(Event.ZLFE_COMMENT_UPDATED, jsonData.comment);
                    }
                });
                this.mAjax.get({
                        date: date,
                        companyId:compType,
                        ydjd:this.mYdjdType,
                        all: this.mCompType == Util.CompanyType.PDCY
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
                switch (e.id){
                    case Event.ZLFE_SAVE_COMMENT:
                        let param = {
                            condition:Util.Ajax.toUrlParam({
                                url : this.mAjax.baseUrl(),
                                date: this.mDt,
                                companyId:this.mCompType,
                                ydjd:this.mYdjdType
                            }),
                            comment:e.data
                        }
                        this.mCommentSubmit.get({
                            data : JSON.stringify([[param.condition, param.comment]])
                        }).then((jsonData:any)=>{
                            Util.MessageBox.tip("保存成功", undefined, 1000);
                        });
                        break;
                }
                return super.onEvent(e);
            }

            public init(opt:Option):void {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "产品一次送试不合格问题明细");
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
