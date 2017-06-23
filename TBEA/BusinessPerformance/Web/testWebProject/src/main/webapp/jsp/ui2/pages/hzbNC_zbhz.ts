/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
declare var echarts;

module hzbNC_zbhz {

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;

    enum ZtId{
        zb, dysj, dyqntq, dytbzf, ndlj, ndqntq, ndtbzf
    };

    class JQGridAssistantFactory {

        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("当月实际", "dysj", true, JQTable.TextAlign.Left),
                new JQTable.Node("去年同期", "qntq", true, JQTable.TextAlign.Left),
                new JQTable.Node("同比增幅", "tbzf", true, JQTable.TextAlign.Left),
                new JQTable.Node("年度累计", "ndlj", true, JQTable.TextAlign.Left),
                new JQTable.Node("去年同期累计", "qnndlj", true, JQTable.TextAlign.Left),
                new JQTable.Node("同比增幅", "ndtbzf", true, JQTable.TextAlign.Left)
            ], gridName);
        }
    }


    interface IViewOption {
        tableId: string;
        date: Util.Date;
    }
    
    export class SimpleView implements Endpoint {

        getId():number {
            return Util.FAMOUS_VIEW;
        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
                case Util.MSG_UPDATE:
                    this.updateUI();
                    break;
            }
        }

        public constructor() {
            router.register(this);
        }

        private static ins:SimpleView = new SimpleView();

        private mData:Array<string[]> = [];
        private tableAssist:JQTable.JQGridAssistant;
        private mDataSet:Util.Ajax = new Util.Ajax("/BusinessManagement/NCzb/AllCompanysNC_overview_update.do");
        private mOpt: IViewOption;
        
        public init(opt:any):void {
            this.mOpt = opt;

            let minDate = Util.addYear(opt.date, -1);
            minDate.month = 1;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");

            $(window).resize(()=> {
                this.adjustSize();
            });
            $("#grid-update").on("click", ()=> {
                this.updateUI();
            });
            $("#grid-export").on("click", ()=> {
                this.exportExcel();
            });
            this.updateUI();
        }

        private getDate():Util.Date {
            let rq = $("#grid-date").val().replace("年", "-").replace("月", "-").replace("日", "-").split("-");
            return {
                year: rq[0] ? parseInt(rq[0]) : undefined,
                month: rq[1] ? parseInt(rq[1]) : undefined,
                day: rq[2] ? parseInt(rq[2]) : undefined
            };
        }

        public exportExcel(){
            $("#export")[0].action = "/BusinessManagement/NCzb/AllCompanysNC_overview_export.do?" + Util.Ajax.toUrlParam(this.getDate());
            $("#export")[0].submit();
        }

        public updateUI() {
            this.mDataSet.get(this.getDate())
                .then((dataArray:any) => {
                    this.mData = dataArray;
                    if (dataArray.length == 0){
                        var pro = $("#prompt");
                        pro.empty();
                        pro.append("<b>暂时没有数据！</b>")
                    }else{
                        var pro = $("#prompt");
                        pro.empty();
                    }
                    this.updateTable();

                });
        }

        private adjustSize() {
            var jqgrid = this.jqgrid();
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }

            let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            this.tableAssist.resizeHeight(maxTableBodyHeight);

            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
        }

        private jqgrid(){
            return $("#" + this.jqgridName());
        }

        private jqgridName():string{
            return this.mOpt.tableId + "_jqgrid_real";
        }

        private initPercentList(): std.vector<number>
        {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(ZtId.dytbzf);
            precentList.push(ZtId.ndtbzf);
            return precentList;
        }

        private createJqassist():JQTable.JQGridAssistant{
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='"+ this.jqgridName() +"'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
            return this.tableAssist;
        }

        private updateTable():void {
            this.createJqassist();

            if (this.mData.length == 0) {
                return;
            }

            var outputData: string[][] = [];
            Util.formatData(outputData, this.mData, this.initPercentList(), []);

            this.tableAssist.create({
                data: outputData,
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: $("#" + this.mOpt.tableId).width(),
                shrinkToFit: true,
                rowNum: 2000,
                autoScroll: true
            });

            this.adjustSize();
        }
    }
}