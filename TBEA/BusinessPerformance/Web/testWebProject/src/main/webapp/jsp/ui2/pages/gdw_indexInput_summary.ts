/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
declare var echarts;

module gdw_indexinput_summary{

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;

    class JQGridAssistantFactory {
        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("公司名称", "gsmc", true, JQTable.TextAlign.Left),
                new JQTable.Node("预计指标填写情况", "inputCondition", true, JQTable.TextAlign.Left),
                new JQTable.Node("填写时间", "inputTime", true, JQTable.TextAlign.Left),
                new JQTable.Node("审核时间", "approveTime", true, JQTable.TextAlign.Left)
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

        private mOpt: IViewOption;
        private mData:Array<string[]> = [];
        private tableAssist:JQTable.JQGridAssistant;
        private mDataSet:Util.Ajax = new Util.Ajax("/BusinessManagement/dashboard/status_update.do");

        public init(opt:any):void {
            this.mOpt = opt;

            let minDate = Util.addYear(opt.date, -3);
            minDate.month = 1;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(minDate),
                maxDate: Util.date2Str(opt.date),
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");

            $(window).resize(()=> {
                this.adjustSize();
            });
            $("#grid-update").on("click", ()=> {
                this.updateUI();
            });

            this.updateUI();
        }

        private formatData() {
            var data = [];
            var row = [];
            for (var j = 0; j < this.mData.length; ++j) {
                row = [].concat(this.mData[j]);
                if (null != row[1]) {
                    if (row[1] == "") {
                        row[1] = "尚未提交";
                        row[2] = "--";
                    }
                    if (row[3] == ""){
                        row[3] = "--";
                    }
                }
                //mdata[j] = data[j].concat(row);
                data.push(row);
            }
            return data;
        }

        private getDate():Util.Date {
            let rq = $("#grid-date").val().replace("年", "-").replace("月", "-").replace("日", "-").split("-");
            return {
                year: rq[0] ? parseInt(rq[0]) : undefined,
                month: rq[1] ? parseInt(rq[1]) : undefined,
                day: rq[2] ? parseInt(rq[2]) : undefined
            };
        }

        public updateUI() {
            this.mDataSet.get($.extend(this.getDate(), {entryType: $("#grid-type").val()}, {companyType:$("#company-type").val()}))
                .then((dataArray:any) => {
                    this.mData = dataArray;
                    this.updateTable();
                });
        }

        private adjustSize() {
            var jqgrid = this.jqgrid();
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }

            let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);

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

        private createJqassist():JQTable.JQGridAssistant{
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='"+ this.jqgridName() +"'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
            return this.tableAssist;
        }

        private updateTable():void {
            this.createJqassist();
            var data = [];
            data = this.formatData();

            this.tableAssist.create({
                data: data,
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