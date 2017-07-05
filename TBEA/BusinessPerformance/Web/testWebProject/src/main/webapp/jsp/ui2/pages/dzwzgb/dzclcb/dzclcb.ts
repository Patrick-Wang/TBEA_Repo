/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../dzwzgbdef.ts"/>

module plugin {
    export let dzclcb : number = framework.basic.endpoint.lastId();
}

module dzwzgb {
    export module dzclcb {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string, isByq:boolean):JQTable.JQGridAssistant {
                if (isByq){
                    return new JQTable.JQGridAssistant([
                        new JQTable.Node("月份", "rqa", true, TextAlign.Center),
                        new JQTable.Node("材料", "ab", true, TextAlign.Center),
                        new JQTable.Node("期货盈亏（万元）", "ac"),
                        new JQTable.Node("市场现货月均价（元/吨）", "ada"),
                        new JQTable.Node("采购月均价（元/吨）（摊入当月期货盈亏）", "adb"),
                        new JQTable.Node("目标利润倒算价（元/吨）", "ae"),
                        new JQTable.Node("采购量（吨）", "af"),
                        new JQTable.Node("期现货合计盈亏（万元）", "ag")
                    ], gridName);
                }else{
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
        }

        class ShowView extends framework.basic.ShowPluginView {
            static ins = new ShowView();
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/dzwzgb/dzclcb/update.do", false);
            private tableAssist:JQTable.JQGridAssistant;
            private mDt: string;
            private mCompType:Util.CompanyType;
            private mIsByq: boolean;

            getId():number {
                return plugin.dzclcb;
            }
            
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "/BusinessManagement/dzwzgb/dzclcb/export.do?" + Util.Ajax.toUrlParam({
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

            public init(opt:Option):void {
                framework.router.fromEp(this).to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_REGISTER, "大宗材料控成本");
            }

            private createJqassist():JQTable.JQGridAssistant{
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='"+ this.jqgridName() +"'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mIsByq);
                this.tableAssist.mergeRow(0);
                return this.tableAssist;
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

            private updateTable():any {
               this.mIsByq = false;
                if (this.mCompType == Util.CompanyType.SBGS ||
                    this.mCompType == Util.CompanyType.HBGS ||
                    this.mCompType == Util.CompanyType.TBGS ||
                    this.mCompType == Util.CompanyType.XBC){
                    this.mIsByq = true;
                }

                this.createJqassist();

                let data = [];
                if (this.mIsByq){
                    for (let i = 0; i < 12; ++i){
                        let arr = this.mData[i];
                        data.push([i + 1, "铜"].concat(arr));
                    }
                }else{
                    for (let i = 0, j = 0; i < 12; ++i, j += 2){
                        let arr = this.mData[j];
                        data.push([i + 1, "铜"].concat(arr));
                        arr = this.mData[j + 1];
                        data.push([i + 1, "铝"].concat(arr));
                    }
                }
                this.tableAssist.create({
                    data: data,
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
