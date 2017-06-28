/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cbfxdef.ts"/>

module plugin {
    export let dmcbfx : number = framework.basic.endpoint.lastId();
    export let dmcbfxProxy : number = framework.basic.endpoint.lastId();
    export let dmcbqsfxProxy : number = framework.basic.endpoint.lastId();
}

module cbfx {
    export module dmcbfx {
        import TextAlign = JQTable.TextAlign;
		import Node = JQTable.Node;
        import EndpointProxy = framework.basic.EndpointProxy;
        class JQGridAssistantFactory {
            //吨煤成本分析表
            public static createTable(gridName:string, year:number, month:number):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("成本构成", "rqa", true, TextAlign.Center),
                    new JQTable.Node(year + "年"+ month +"月度完成情况", "ab")
                        .append(new JQTable.Node("计划", "jh"))
                        .append(new JQTable.Node("实际", "sj"))
                        .append(new JQTable.Node("完成比", "wcb")),
                    new JQTable.Node("同期对比", "ac", true, TextAlign.Center)
                        .append(new JQTable.Node("上年同期", "ad"))
                        .append(new JQTable.Node("增减额", "ae"))
                        .append(new JQTable.Node("完成比", "af"))
                ], gridName);
            }

            //吨煤成本趋势分析表
            public static createQsTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("成本构成", "aa", true, TextAlign.Center),
                    new JQTable.Node("1月", "ab"),
                    new JQTable.Node("2月", "ac"),
                    new JQTable.Node("3月", "ad"),
                    new JQTable.Node("一季度加权（账面累计）", "ae"),
                    new JQTable.Node("4月", "af"),
                    new JQTable.Node("5月", "ag"),
                    new JQTable.Node("6月", "ah"),
                    new JQTable.Node("上半年加权", "ai"),
                    new JQTable.Node("7月", "aj"),
                    new JQTable.Node("8月", "ak"),
                    new JQTable.Node("9月", "al"),
                    new JQTable.Node("前三季度加权", "am"),
                    new JQTable.Node("10月", "an"),
                    new JQTable.Node("11月", "ao"),
                    new JQTable.Node("12月", "ap"),
                    new JQTable.Node("全年加权", "aq")
                ], gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/dmcbfx/update.do", false);
            private tableAssist:JQTable.JQGridAssistant;
            private mDt: string;
            private mCurCbfxType : CbfxType;
            onEvent(e:framework.route.Event):any {
                if (e.road != undefined){
                    if (e.road[e.road.length - 1] == plugin.dmcbfxProxy){
                        this.mCurCbfxType = CbfxType.dmcbfx;
                    }else{
                        this.mCurCbfxType = CbfxType.dmcbqsfx;
                    }
                }
                return super.onEvent(e);
            }

            getId():number {
                return plugin.dmcbfx;
            }
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "/BusinessManagement/dmcbfx/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType,
                        type:this.mCurCbfxType
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
                        companyId:compType,
                        type:this.mCurCbfxType
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
                framework.router
                    .fromEp(new EndpointProxy(plugin.dmcbfxProxy, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "吨煤成本分析表");
                framework.router
                    .fromEp(new EndpointProxy(plugin.dmcbqsfxProxy, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "吨煤成本趋势分析表");
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
                if (this.mCurCbfxType == CbfxType.dmcbfx){
                    this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.getYear(), this.getMonth());
                }else{
                    this.tableAssist = JQGridAssistantFactory.createQsTable(this.jqgridName());
                }
                return this.tableAssist;
            }

            private updateTable():any {
                this.createJqassist();

                //var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                //let tableAssist:JQTable.JQGridAssistant;
                //if (this.mCurCbfxType == CbfxType.dmcbfx){
                //    this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.getYear(), this.getMonth());
                //}else{
                //    this.tableAssist = JQGridAssistantFactory.createQsTable(this.jqgridName());
                //}
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
