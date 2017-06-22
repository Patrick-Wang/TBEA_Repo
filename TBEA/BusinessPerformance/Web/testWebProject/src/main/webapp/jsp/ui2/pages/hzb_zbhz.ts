/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
declare var echarts;

module hzb_zbhz {

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;
    enum SrqyId{
        zb, qnjh, dyjh, dysj, jhwcl, ljwc, ljwcl, qntqz, tbzzl, qntqlj, ljtbzzl
    }
    ;

    enum ZtId{
        zb, qnjh, dyjh, dysj, dyjhwcl, dyqntq, dytbzf, jdjh, jdlj, jdjhwcl, jdqntq, jdtbzf, ndlj, ndljjhwcl, ndqntq, ndtbzf
    }
    ;

    class JQGridAssistantFactory {

        public static createSrqyTable(gridName:string):JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("当期", "dq")
                    .append(new JQTable.Node("全年计划", "1"))
                    .append(new JQTable.Node("当月计划", "2"))
                    .append(new JQTable.Node("当月实际", "3"))
                    .append(new JQTable.Node("计划完成率", "4"))
                    .append(new JQTable.Node("累计完成", "5"))
                    .append(new JQTable.Node("累计完成率", "6")),
                new JQTable.Node("去年", "qn")
                    .append(new JQTable.Node("去年同期值", "7"))
                    .append(new JQTable.Node("同比增长率", "8"))
                    .append(new JQTable.Node("去年同期累计", "9"))
                    .append(new JQTable.Node("同比增长率", "10"))
            ], gridName);
        }


        public static createTable(gridName:string):JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("全年计划", "qnjh"),
                new JQTable.Node("月度", "yd")
                    .append(new JQTable.Node("当月计划", "y1"))
                    .append(new JQTable.Node("当月实际", "y2"))
                    .append(new JQTable.Node("计划完成率", "y3"))
                    .append(new JQTable.Node("去年同期", "y4"))
                    .append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度", "jd")
                    .append(new JQTable.Node("季度计划", "j1"))
                    .append(new JQTable.Node("季度累计", "j2"))
                    .append(new JQTable.Node("季度计划完成率", "j3"))
                    .append(new JQTable.Node("去年同期", "j4"))
                    .append(new JQTable.Node("同比增幅", "j5")),
                new JQTable.Node("年度", "nd")
                    .append(new JQTable.Node("年度累计", "n1"))
                    .append(new JQTable.Node("累计计划完成率", "n2"))
                    .append(new JQTable.Node("去年同期", "n3"))
                    .append(new JQTable.Node("同比增幅", "n4"))
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
        private mDataSet:Util.Ajax = new Util.Ajax("/BusinessManagement/ydzb/hzb_zbhz_update.do");
        private mXmgsDataSet:Util.Ajax = new Util.Ajax("/BusinessManagement/ydzb/hzb_zbhz_xmgs_compute.do", false);
        private mJydwDataSet:Util.Ajax = new Util.Ajax("/BusinessManagement/ydzb/hzb_zbhz_jydw_compute.do", false);
        private mOpt: IViewOption;
        
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
            $("#export-all").on("click", ()=> {
                this.exportExcelJydw();
            });
            $("#export-xmgs").on("click", ()=> {
                this.exportExcelXmgs();
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

        public updateUI() {
            this.mDataSet.get($.extend(this.getDate(), {type: $("#grid-type").val()}))
                .then((dataArray:any) => {
                    this.mData = dataArray;
                    this.updateTable();
                });
        }

        public exportExcelJydw() {
            var fName = $("#grid-date").val() + "经营单位整体指标完成情况";
            this.mJydwDataSet.get($.extend(this.getDate(), {
                type: $("#grid-type").val(),
                fileName: fName
            })).then((tmStamp)=> {
                $("#exportJydw")[0].action = "/BusinessManagement/ydzb/general_export.do?" + Util.Ajax.toUrlParam({timeStamp: tmStamp.timeStamp});
                $("#exportJydw")[0].submit();
            })
        }

        public exportExcelXmgs() {
            var fName = $("#grid-date").val() + "项目公司整体指标完成情况";
            this.mXmgsDataSet.get($.extend(this.getDate(), {
                type: $("#grid-type").val(),
                fileName: fName
            })).then((tmStamp)=> {
                $("#exportxmgs")[0].action = "/BusinessManagement/ydzb/general_export.do?" + Util.Ajax.toUrlParam({timeStamp: tmStamp.timeStamp});
                $("#exportxmgs")[0].submit();
            });
        }

        private initZTPercentList():std.vector<number> {
            var precentList:std.vector<number> = new std.vector<number>();
            precentList.push(ZtId.dyjhwcl);
            precentList.push(ZtId.dytbzf);
            precentList.push(ZtId.jdjhwcl);
            precentList.push(ZtId.jdtbzf);
            precentList.push(ZtId.ndljjhwcl);
            precentList.push(ZtId.ndtbzf);
            return precentList;
        }

        private initSrqyPercentList():std.vector<number> {
            var precentList:std.vector<number> = new std.vector<number>();
            precentList.push(SrqyId.jhwcl);
            precentList.push(SrqyId.ljwcl);
            precentList.push(SrqyId.tbzzl);
            precentList.push(SrqyId.ljtbzzl);
            return precentList;
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

        private createJqassist():JQTable.JQGridAssistant{
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='"+ this.jqgridName() +"'></table>");
            if (0 == $("#grid-type").val()) {
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
            } else {
                this.tableAssist = JQGridAssistantFactory.createSrqyTable(this.jqgridName());
            }
            return this.tableAssist;
        }

        private updateTable():void {
            if (this.mData.length == 0) {
                return;
            }

            this.createJqassist();

            var outputData:string[][] = [];
            if (0 == $("#grid-type").val()) {
                Util.formatData(outputData, this.mData, this.initZTPercentList(), []);
            } else {
                Util.formatData(outputData, this.mData, this.initSrqyPercentList(), []);
            }

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