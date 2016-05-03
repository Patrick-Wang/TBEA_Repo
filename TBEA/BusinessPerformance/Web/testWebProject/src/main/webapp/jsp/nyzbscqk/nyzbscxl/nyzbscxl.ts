/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../nyzbscqkdef.ts"/>

module plugin {
    export let nyzbscxl : number = framework.basic.endpoint.lastId();
    export let wcwzbkq : number = framework.basic.endpoint.lastId();
    export let qtzbkq : number = framework.basic.endpoint.lastId();
}

module nyzbscqk {
    export module nyzbscxl {
        import TextAlign = JQTable.TextAlign;
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
            private mAjax:Util.Ajax = new Util.Ajax("../nyzbscxl/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;
            private mZbkqId : number;
            getId():number {
                return plugin.nyzbscxl;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "../nyzbscxl/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId:compType
                    });
            }

            protected isSupported(compType:Util.CompanyType):boolean {
                if (this.mZbkqId == plugin.wcwzbkq){
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
                    .fromEp(new framework.basic.EndpointProxy(plugin.wcwzbkq, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "五彩湾周边矿区块煤市场月销量");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.qtzbkq, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "奇台周边矿区块煤市场月销量");
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
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.getYear());
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                tableAssist.mergeRow(0);
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
