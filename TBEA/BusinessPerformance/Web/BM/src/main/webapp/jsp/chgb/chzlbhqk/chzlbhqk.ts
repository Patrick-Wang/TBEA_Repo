/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../chgbdef.ts" />

declare var echarts;
declare var view:chgb.FrameView;

module chgb {
    export module chzlbhqk {
        import TextAlign = JQTable.TextAlign;
        class JQGridAssistantFactory {
            public static createTable(gridName:string):JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "chzlbhqk_rq", true, TextAlign.Center),
                    new JQTable.Node("月度", "chzlbhqk_rqtwo", true, TextAlign.Center),
                    new JQTable.Node("5年以上", "chzlbhqk_aa"),
                    new JQTable.Node("4-5年", "chzlbhqk_ab"),
                    new JQTable.Node("3-4年", "chzlbhqk_ac"),
                    new JQTable.Node("2-3年", "chzlbhqk_ad"),
                    new JQTable.Node("1-2年", "chzlbhqk_ae"),
                    new JQTable.Node("1年以内", "chzlbhqk_af"),
                    new JQTable.Node("合计", "chzlbhqk_ag")
                ], gridName);
            }
        }

        interface Option extends PluginOption {
            tb:string;
        }

        class CHZLBHQKView extends BasePluginView {
            private mData:Array<string[]>;
            private mAjax:Util.Ajax = new Util.Ajax("chzlbhqk/update.do", false);
            private mDateSelector:Util.DateSelector;
            private mDt: string;
            
            public static newInstance():CHZLBHQKView {
                return new CHZLBHQKView();
            }
            pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
                return "chzlbhqk/export.do?" + Util.Ajax.toUrlParam({
                        date: date,
                        companyId: cpType
                    });
            }
            private option():Option {
                return <Option>this.mOpt;
            }

            public pluginUpdate(date:string, cpType:Util.CompanyType):void {
                this.mDt = date;
                this.mAjax.get({
                        date: date,
                        companyId: cpType
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
                super.init(opt);
                view.register("存货账龄变化情况", this);
            }

            private updateTable():void {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                
                let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                let month = curDate.getMonth() + 1;
                let data = [];
                for (let i = month + 1; i <= 12; ++i){
                    data.push(["上年度", i + "月"].concat(this.mData[i - month - 1]));
                }
                for (let i = 1; i <= month; ++i){
                    data.push(["本年度", i + "月"].concat(this.mData[12 - month + i - 1]));
                }

                tableAssist.mergeRow(0);
                tableAssist.mergeTitle();
                
                this.$(name).jqGrid(
                    tableAssist.decorate({
                        multiselect: false,
                        drag: false,
                        resize: false,
                        height: '100%',
                        width: 1200,
                        shrinkToFit: true,
                        autoScroll: true,
                        rowNum: 20,
                        data: tableAssist.getData(data),
                        datatype: "local",
                        viewrecords : true
                    }));
            }
        }

        export var pluginView = CHZLBHQKView.newInstance();
    }
}
