/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../nyzbscqkdef.ts"/>

module plugin {
    export let nyzbscjg : number = framework.basic.endpoint.lastId();
    export let wcwzbkqjg : number = framework.basic.endpoint.lastId();
    export let qtzbkqjg : number = framework.basic.endpoint.lastId();
}

module nyzbscqk {
    export module nyzbscjg {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, year:number):JQTable.JQGridAssistant {
                let titleNodes = [
                    new JQTable.Node("矿区", "rqa", true, TextAlign.Center),
                    new JQTable.Node("煤种", "ab", true, TextAlign.Center),
                    new JQTable.Node(year + "年", "nf", true, TextAlign.Center)];
                for (let i = 1; i <= 12; ++i){
                    titleNodes[2].append(new JQTable.Node(i + "月", "y" + i + "f"));
                }
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/nyzbscjg/update.do", false);
            private tableAssist:JQTable.JQGridAssistant;
            private mDt: string;
            private mZbkqId : number;

            getId():number {
                return plugin.nyzbscjg;
            }
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "/BusinessManagement/nyzbscjg/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType
                    });
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
                this.updateTable();
                this.adjustSize();
            }

            protected isSupported(compType:Util.CompanyType):boolean {
                if (this.mZbkqId == plugin.wcwzbkqjg){
                    if (Util.CompanyType.NLTK == compType){
                        return true;
                    }
                }else {
                    if (Util.CompanyType.XJNY == compType){
                        return true;
                    }
                }
                return false;
            }

            onEvent(e:framework.route.Event):any {
                if (e.road != undefined) {
                    this.mZbkqId = e.road[e.road.length - 1];
                }
                return super.onEvent(e);
            }


            public init(opt:Option):void {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.wcwzbkqjg, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "五彩湾周边矿区市场销量价格情况");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.qtzbkqjg, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "奇台周边矿区市场销量价格情况");
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
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.getYear());
                this.tableAssist.mergeRow(0);
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
