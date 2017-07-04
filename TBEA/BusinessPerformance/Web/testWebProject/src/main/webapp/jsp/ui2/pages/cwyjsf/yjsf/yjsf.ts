/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cwyjsfdef.ts"/>

module plugin {
    export let yjsf : number = framework.basic.endpoint.lastId();
}

module cwyjsf {
    export module yjsf {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        import EndpointProxy = framework.basic.EndpointProxy;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, year:number):JQTable.JQGridAssistant {
                let dyyjs =  Node.create({name : year + "年当月应交数"});
                for (let i = 1; i <= 12; ++i){
                    dyyjs.append( Node.create({name : i + ""}));
                }
                let dyyijs =  Node.create({name : year + "年当已交数"});
                for (let i = 1; i <= 12; ++i){
                    dyyijs.append( Node.create({name : i + ""}));
                }
                return new JQTable.JQGridAssistant([
                    Node.create({name : "税种", align : TextAlign.Left}),
                    Node.create({name : "期初数"}),
                    dyyjs,
                    dyyijs
                ], gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mData1:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/yjsf/update.do", false);
            private tableAssist:JQTable.JQGridAssistant;
            private tableAssist1:JQTable.JQGridAssistant;
            private mDt: string;
            private mCompType:Util.CompanyType;

            getId():number {
                return plugin.yjsf;
            }
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "/BusinessManagement/yjsf/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType
                    });
            }

            onEvent(e:framework.route.Event):any {
                switch (e.id) {
                    case Event.CW_ISMONTH_SUPPORTED:
                        return false;
                }
                return super.onEvent(e);
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
                        this.updateTable();
                        this.adjustSize();
                    });
                this.mAjax.get({
                        date: (this.getYear() - 1) + "-" + this.getMonth() + "-1",
                        companyId:compType
                    })
                    .then((jsonData:any) => {
                        this.mData1 = jsonData;
                        this.updateTable1();
                        this.adjustSize();
                    });
            }


            public refresh() : void{
                if ( this.mData == undefined){
                    return;
                }
                this.adjustSize();
            }

            public init(opt:Option):void {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "应交税费");
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

            jqgridHost1():any{
                return this.$(this.mOpt.tb1);
            }

            jqgridName1():string{
                return this.mOpt.host + this.mOpt.tb1 + "_jqgrid_real";
            }

            jqgrid1(): any{
                return this.$(this.jqgridName1());
            }

            adjustSize() {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }

                var jqgrid1 = this.jqgrid1();
                if (this.jqgridHost1().width() != this.jqgridHost1().children().eq(0).width()) {
                    jqgrid1.setGridWidth(this.jqgridHost1().width());
                }

                //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                //this.tableAssist.resizeHeight(maxTableBodyHeight);
                //
                //if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                //    jqgrid.setGridWidth(this.jqgridHost().width());
                //}

                //this.$(this.option().ct).css("height", "300px");
                //this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                //this.updateEchart(this.mFinalData);
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.getYear());
                return this.tableAssist;
            }

            private createJqassist1():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb1);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName1() +"'></table>");
                this.tableAssist1 = JQGridAssistantFactory.createTable(this.jqgridName1(), this.getYear() - 1);
                return this.tableAssist1;
            }

            private updateTable(): any {
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
                    rowNum: 100,
                    autoScroll: true
                });
                return ;
            }

            private updateTable1(): any {
                this.createJqassist1();

                this.tableAssist1.create({
                    data: this.mData1,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.jqgridHost1().width(),
                    shrinkToFit: true,
                    rowNum: 100,
                    autoScroll: true
                });
                return ;
            }
        }
    }
}
