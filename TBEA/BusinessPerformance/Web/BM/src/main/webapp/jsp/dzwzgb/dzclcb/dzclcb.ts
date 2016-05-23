/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../dzwzgbdef.ts"/>

module plugin {
    export let dzclcb : number = framework.basic.endpoint.lastId();
}

module dzwzgb {
    export module dzclcb {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月份", "rqa", true, TextAlign.Center),
                    new JQTable.Node("材料", "ab", true, TextAlign.Center),
                    new JQTable.Node("期货盈亏（万元）", "ac"),
                    new JQTable.Node("市场现货月均价（元/吨）", "ada"),
                    new JQTable.Node("采购月均价（元/吨）（摊入当月期货盈亏）", "adb"),
                    new JQTable.Node("三项费用保本价（元/吨）", "adc"),
                    new JQTable.Node("目标利润倒算价（元/吨）", "ae"),
                    new JQTable.Node("采购量（吨）", "af"),
                    new JQTable.Node("期现货合计盈亏", "ag")
                        .append(new JQTable.Node("指导价格按照保本价（万元）", "ah"))
                        .append(new JQTable.Node("指导价格按照目标利润价（万元）", "ai"))
                ], gridName);
            }
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("dzclcb/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            private mCompType:Util.CompanyType;

            getId():number {
                return plugin.dzclcb;
            }

            pluginGetExportUrl(date:string, compType:Util.CompanyType):string {
                return "dzclcb/export.do?" + Util.Ajax.toUrlParam({
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
                framework.router.fromEp(this).to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_REGISTER, "大宗材料控成本");
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");

                let data = [];
                if (this.mCompType == Util.CompanyType.SBGS ||
                    this.mCompType == Util.CompanyType.HBGS ||
                    this.mCompType == Util.CompanyType.TBGS ||
                    this.mCompType == Util.CompanyType.XBC){
                    for (let i = 0; i < 12; ++i){
                        data.push([i + 1, "铜"].concat(this.mData[i]));
                    }
                }else{
                    for (let i = 0, j = 0; i < 12; ++i, j += 2){
                        data.push([i + 1, "铜"].concat(this.mData[j]));
                        data.push([i + 1, "铝"].concat(this.mData[j + 1]));
                    }
                }

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
                        data: tableAssist.getData(data),
                        datatype: "local",
                        viewrecords : true
                    }));
            }
        }
    }
}
