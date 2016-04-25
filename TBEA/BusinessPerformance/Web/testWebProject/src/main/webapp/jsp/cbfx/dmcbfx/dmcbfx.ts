/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../cbfxdef.ts"/>

module plugin {
    export let dmcbfxHost : number = framework.basic.endpoint.lastId();
    export let dmcbfx : number = framework.basic.endpoint.lastId();
    export let dmcbqsfx : number = framework.basic.endpoint.lastId();
}

module cbfx {
    export module dmcbfx {
        import TextAlign = JQTable.TextAlign;
        import EndpointProxy = framework.basic.EndpointProxy;
        class JQGridAssistantFactory {
            //吨煤成本分析表
            public static createTable(gridName:string, year:number, month:number):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("成本构成", "rqa", true, TextAlign.Right),
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
            private mAjax:Util.Ajax = new Util.Ajax("../dmcbfx/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;
            private mCurCbfxType : CbfxType;
            onEvent(e:framework.route.Event):any {
                if (e.redirects[e.redirects.length - 1] == plugin.dmcbfx){
                    this.mCurCbfxType = CbfxType.dmcbfx;
                }else{
                    this.mCurCbfxType = CbfxType.dmcbqsfx;
                }
                return super.onEvent(e);
            }

            getId():number {
                return plugin.dmcbfxHost;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "dmcbfx/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType,
                        type:this.mCurCbfxType
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

                this.updateTable();
            }

            public init(opt:Option):void {
                framework.router
                    .fromEp(new EndpointProxy(plugin.dmcbfx, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "吨煤成本分析表");
                framework.router
                    .fromEp(new EndpointProxy(plugin.dmcbqsfx, this.getId()))
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
			
            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                let tableAssist:JQTable.JQGridAssistant;
                if (this.mCurCbfxType == CbfxType.dmcbfx){
                    tableAssist = JQGridAssistantFactory.createTable(name, this.getYear(), this.getMonth());
                }else{
                    tableAssist = JQGridAssistantFactory.createQsTable(name);
                }

                //let data : string[][] = [
                //    ["土方剥离爆破成本"],
                //    ["原煤爆破成本"],
                //    ["原煤采运成本"],
                //    ["回筛倒运成本"],
                //    ["装车成本"],
                //    ["直接成本合计"],
                //    ["非可控成本"],
                //    ["可控成本"],
                //    ["制造费用小计"],
                //    ["技改财务费用"],
                //    ["生产成本合计"]
                //];
                //
                //for (let i = 0; i < data.length; ++i){
                //    data[i] = data[i].concat(this.mData[i]);
                //}

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
                        rowNum: 20,
                        data: tableAssist.getData(this.mData),
                        datatype: "local",
                        viewrecords : true
                    }));
            }
        }
    }
}
