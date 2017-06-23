/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../messageBox.ts" />
///<reference path="../../dateSelector.ts"/>
declare var echarts;

module yszkgb { export module yszkkxxz {

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;
    import TextAlign = JQTable.TextAlign;
    class JQGridAssistantFactory {
        public static createTable(gridName:string):JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("月度", "aa", true, TextAlign.Center),
                new JQTable.Node("月度", "a1a", true, TextAlign.Center),
                new JQTable.Node("月度", "ab", true, TextAlign.Center)
                    .append(new JQTable.Node("逾期0-1个月", "ba"))
                    .append(new JQTable.Node("逾期1-3月", "bb"))
                    .append(new JQTable.Node("逾期3-6月", "bc"))
                    .append(new JQTable.Node("逾期6-12月", "bd"))
                    .append(new JQTable.Node("逾期1年以上", "be"))
                    .append(new JQTable.Node("小计", "bf")),
                new JQTable.Node("未到期(不含质保金)", "ah"),
                new JQTable.Node("未到期质保金", "ai"),
                new JQTable.Node("合计", "aj")
            ], gridName);
        }
    }

    interface Option extends PluginOption {
        tb:string;
    }

    export class SimpleView extends BasePluginView {

        private static ins : SimpleView = new SimpleView("yszkkxxz");


        private mData:Array<string[]>;
        private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/yszkgb/yszkkxxz/update.do", false);
        private tableAssist:JQTable.JQGridAssistant;
        private mDt: string;

        constructor(id:string){
            super(id);
        }

        pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
            return "/BusinessManagement/yszkgb/yszkkxxz/export.do?" + Util.Ajax.toUrlParam({
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
            framework.router.to(Util.FAMOUS_VIEW).send(Util.MSG_REG, {name: "应收账款款项性质情况", plugin:this});
        }

        private adjustSize() {
            var jqgrid = this.jqgrid();
            if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                jqgrid.setGridWidth(this.jqgridHost().width());
            }

            let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            this.tableAssist.resizeHeight(maxTableBodyHeight);

            if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                jqgrid.setGridWidth(this.jqgridHost().width());
            }
        }

        private createJqassist():JQTable.JQGridAssistant{
            var parent = this.$(this.option().tb);
            parent.empty();
            parent.append("<table id='"+ this.jqgridName() +"'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
            this.tableAssist.mergeRow(0);
            this.tableAssist.mergeTitle();
            return this.tableAssist;
        }

        private updateTable():void {
            this.createJqassist();

            let curDate : Date = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
            let month = curDate.getMonth() + 1;
            let data = [];
            for (let i = month + 1; i <= 12; ++i){
                data.push(["上年度", i + "月"].concat(this.mData[i - month - 1]));
            }
            for (let i = 1; i <= month; ++i){
                data.push(["本年度", i + "月"].concat(this.mData[12 - month + i - 1]));
            }

            this.tableAssist.create({
                data: data,
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: this.jqgridHost().width(),
                shrinkToFit: true,
                rowNum: 2000,
                autoScroll: true
            });

            this.adjustSize();
        }

    }
}}