/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../messageBox.ts" />
///<reference path="../../dateSelector.ts"/>
declare var echarts;

module yszkgb { export module zmb {

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;

    class JQGridAssistantFactory {
        public static createTable(gridName:string):JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("账面净额", "rq"),
                new JQTable.Node("坏账准备", "aa"),
                new JQTable.Node("原值", "ab")
            ], gridName);
        }
    }

    interface Option extends PluginOption {
        tb:string;
    }

    export class SimpleView extends BasePluginView {

        private static ins : SimpleView = new SimpleView("zmb");


        private mData:Array<string[]>;
        private mAjax:Util.Ajax = new Util.Ajax("/BusinessManagement/yszkgb/zmb/update.do", false);
        private tableAssist:JQTable.JQGridAssistant;

        constructor(id:string){
            super(id);
        }

        pluginGetExportUrl(date:string, cpType:Util.CompanyType):string {
            return "/BusinessManagement/yszkgb/zmb/export.do?" + Util.Ajax.toUrlParam({
                date: date,
                companyId: cpType
            });
        }

        private option():Option {
            return <Option>this.mOpt;
        }

        public pluginUpdate(date:string, cpType:Util.CompanyType):void {
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
            framework.router.to(Util.FAMOUS_VIEW).send(Util.MSG_REG, {name: "应收帐款账面表", plugin:this});
        }

        public adjustSize() {
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
            return this.tableAssist;
        }

        private updateTable():void {
            this.createJqassist();

            this.tableAssist.create({
                data: this.mData,
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