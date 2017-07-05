/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../wgcpqkdef.ts"/>

module plugin {
    export let yclbfqk : number = framework.basic.endpoint.lastId();
}

module wgcpqk {
    export module yclbfqk {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, month:number):JQTable.JQGridAssistant {
                let sndfll : JQTable.Node = new JQTable.Node("上年度废料率", "ac");
                let dndfll : JQTable.Node = new JQTable.Node("当年度废料率", "ad");
                for (let i = month; i <= 12; ++i){
                    sndfll.append(new JQTable.Node(i + "月", "ac" + i + "a"));
                }
                for (let i = 1; i <= month; ++i){
                    dndfll.append(new JQTable.Node(i + "月", "ad" + i + "a"));
                }
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("材料名称", "rqa", true, TextAlign.Center),
                    new JQTable.Node("当月（吨）", "ab", true, TextAlign.Center)
                        .append(new JQTable.Node("领用量", "abh"))
                        .append(new JQTable.Node("废料", "abi"))
                        .append(new JQTable.Node("利用率", "abkm")),
                    sndfll,
                    dndfll
                ], gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/yclbfqk/update.do", false);
            private tableAssist:JQTable.JQGridAssistant;
            private mDt: string;
            private mWgcpqkType: WgcpqkType;

            getId():number {
                return plugin.yclbfqk;
            }
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "/BusinessManagement/yclbfqk/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType,
                        wgcpqkType:this.mWgcpqkType
                    });
            }
            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, compType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjax.get({
                        date: date,
                        companyId:compType,
                        wgcpqkType:this.mWgcpqkType
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
                    .send(framework.basic.FrameEvent.FE_REGISTER, "原材料废料情况");
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
                this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);

                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                //this.$(this.option().ct).css("height", "300px");
                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart(this.mFinalData);
            }

            private createJqassist():JQTable.JQGridAssistant{
                let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), month);
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
