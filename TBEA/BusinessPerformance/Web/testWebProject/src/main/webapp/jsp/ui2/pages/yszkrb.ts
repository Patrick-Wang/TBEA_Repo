/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var $:any;

module yszkrb {
        import Endpoint = framework.route.Endpoint;
        import router = framework.router;

        class JQGridAssistantFactory {
            public static createTable(gridName: string): JQTable.JQGridAssistant {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("应收账款指标", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                    new JQTable.Node("回款计划", "t2", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                    new JQTable.Node("其中：确保款项", "t3", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                    new JQTable.Node("争取款项", "t4", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                    new JQTable.Node("上月应收余额", "t5", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                    new JQTable.Node("今日新增应收账款", "t6", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                    new JQTable.Node("今日回款", "t7", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                    new JQTable.Node("累计可降应收回款", "t8", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
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
                }
            }

            public constructor() {
                router.register(this);
            }

            private static ins:SimpleView = new SimpleView();

            private mData:Array<string[]>;
            private mAjaxSubmit: Util.Ajax = new Util.Ajax("/BusinessManagement/dailyReport/yszklr_submit.do");
            private mAjaxUpdate:Util.Ajax = new Util.Ajax("/BusinessManagement/dailyReport/yszklr_update.do", false);
            private mDt:string;
            private tableAssist:JQTable.JQGridAssistant;
            private mOpt: IViewOption;
            private mStopBtn = false;
            public init(opt:any):void {
                this.mOpt = opt;

                let minDate = Util.addYear(opt.date, -1);
                minDate.month = 1;
                $("#grid-date").jeDate({
                    skinCell: "jedatedeepgreen",
                    format: "YYYY年MM月DD日",
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
                    if (!this.mStopBtn){
                        this.updateUI();
                    }
                });
                $("#submit").on("click", ()=> {
                    if (!this.mStopBtn) {
                        this.submit();
                    }
                });
                this.updateUI();
            }

            public updateUI() {
                this.mAjaxUpdate.get(this.getDate())
                    .then((dataArray: any) => {
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

            private getDate():Util.Date {
                let rq = $("#grid-date").val().replace("年", "-").replace("月", "-").replace("日", "-").split("-");
                return {
                    year: rq[0] ? parseInt(rq[0]) : undefined,
                    month: rq[1] ? parseInt(rq[1]) : undefined,
                    day: rq[2] ? parseInt(rq[2]) : undefined
                };
            }

            private jqgrid(){
                return $("#" + this.jqgridName());
            }

            private jqgridName():string{
                return this.mOpt.tableId + "_jqgrid_real";
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

            public submit() {
                var allData = this.tableAssist.getAllData();
                var submitData = [];
                var colNames = this.tableAssist.getColNames();
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        if (j != 0) {
                            submitData[i].push(allData[i][j])
                            allData[i][j] = allData[i][j].replace(new RegExp(' ', 'g'), '')
                        }
                    }
                }

                this.mAjaxSubmit.post($.extend(this.getDate(), {
                    data: JSON.stringify(submitData[0])
                })).then((resp:Util.IResponse) => {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.Toast.success("提交 成功");
                    } else if(Util.ErrorCode.NULL_STRING == resp.errorCode){
                        Util.Toast.success("有未录入项！");
                    } else {
                        Util.Toast.failed(resp.message);
                    }
                });
            }


            public refresh():void {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
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
                var row = [];
                for (var i = 0; i < this.mData.length; ++i) {
                    data.push([]);
                    if (this.mData[i] instanceof Array) {
                        row = [].concat(this.mData[i]);
                        for (var col in row) {
                            row[col] = Util.formatCurrency(row[col]);
                        }
                        data[i] = data[i].concat(row);
                    }
                }

                this.tableAssist.create({
                    data:this.mData,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.tableAssist.getColNames().length * 400,
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true,
                    assistEditable: true,
                    assistOnEdit:()=>{
                        this.mStopBtn = true;
                        $('.btn').attr("disabled", true);
                    },
                    assistPostEdit:()=>{
                        this.mStopBtn = false;
                        $('.btn').attr("disabled", false);
                    }
                });

                this.adjustSize();
            }
        }
}