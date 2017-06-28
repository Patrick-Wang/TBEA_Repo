/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cbfxdef.ts"/>

module plugin {
    export let nymyywmlfx:number = framework.basic.endpoint.lastId();
}

module cbfx {
    export module nymyywmlfx {
        import TextAlign = JQTable.TextAlign;
        import EndpointProxy = framework.basic.EndpointProxy;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("合作客户", "rqa", true, TextAlign.Left, 0, "text"),
                    new JQTable.Node("贸易项目", "ab", true, TextAlign.Left, 0, "text"),
                    new JQTable.Node("数量", "ac", true),
                    new JQTable.Node("收入", "ada", true),
                    new JQTable.Node("成本", "adb", true),
                    new JQTable.Node("毛利", "adbml", true),
                    new JQTable.Node("毛利率", "adbmll", true)
                ], gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/nymyywmlfx/update.do", false);
            private tableAssist:JQTable.JQGridAssistant;
            private mDt: string;

            getId():number {
                return plugin.nymyywmlfx;
            }

            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "/BusinessManagement/nymyywmlfx/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType
                    });
            }

            protected isSupported(compType: Util.CompanyType): boolean {
                return compType == Util.CompanyType.XJNY || compType == Util.CompanyType.NLTK;
            }

            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
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

                this.$(this.option().ctarea).show();
                this.updateTable();

                this.adjustSize();
            }

            public init(opt:Option):void {
                framework.router.fromEp(this).to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_REGISTER, "能源贸易业务毛利分析");
            }

            private getMonth():number{
                let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                return month;
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.tableAssist.resizeHeight(maxTableBodyHeight);

                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                //this.$(this.option().ct).css("height", "300px");
                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart(this.mFinalData);
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                return this.tableAssist;
            }


            private updateTable():any {
                this.createJqassist();

                this.tableAssist.create({
                    data: this.mData,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true
                });
                return ;
            }
        }
    }
}
