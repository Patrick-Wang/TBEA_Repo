/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
declare var echarts;

module hzb_zbhz_prediciton{

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;

    enum FirstMonthZb{
        zb, ndjh, bjdjh, byjhz, dyyjz, dyjhwcl, dyqntq, dytbzf,
        cyyj, myyj, jdyjhj, jdyjwcl, jdqntq, jdtbzf,
        ndljwcz, ndzbwcl, ndqntqz, ndtbzf
    };

    enum SecondMonthZb{
        zb, ndjh, jdjh, byjhz, dyyjz, dyjhwcl, dyqntq, dytbzf,
        jdlj, jdjhwcl, jdqntqz, jdtbzf,
        jdmyyj, jdyjhj, jdyjwcl, jdyjqntq, jdyjtbzf,
        ndljwcz, ndzbwcl, ndqntqz, ndtbzf
    };

    enum ThirdMonthZb{
        zb, ndjh, bjdjh, xjdjh, dyjhz, dyyjz, dyjhwcl, dyqntq, dytbzf,
        jdlj, jdjhwcl, jdqntqz, jdtbzf,
        ndljwcz, ndzbwcl, ndqntqz, ndtbzf,
        xjdsyyj, xjdcyyj, xjdmyyj, xjdyjhj, xjdyjwcl, xjdndlj, xjdndljwcl, xjdqntq,xjdtbzf
    };

    class JQGridAssistantFactory {

        public static createTable(gridName: string, gridStyle: number): JQTable.JQGridAssistant {
            if (1 == gridStyle)
            {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度计划", "ndjh"),
                    new JQTable.Node("本季度计划", "jdjh"),
                    new JQTable.Node("当月完成", "dywc")
                        .append(new JQTable.Node("本月计划值", "y1"))
                        .append(new JQTable.Node("当月预计值", "y2"))
                        .append(new JQTable.Node("计划完成率", "y3"))
                        .append(new JQTable.Node("去年同期", "y4"))
                        .append(new JQTable.Node("同比增幅", "y5")),
                    new JQTable.Node("季度预计完成", "jdyjwc")
                        .append(new JQTable.Node("次月预计", "j1"))
                        .append(new JQTable.Node("末月预计", "j2"))
                        .append(new JQTable.Node("季度预计合计", "j3"))
                        .append(new JQTable.Node("季度预计完成率", "j4"))
                        .append(new JQTable.Node("去年同期", "j5"))
                        .append(new JQTable.Node("同比增幅", "j6")),
                    new JQTable.Node("年度累计完成", "nd")
                        .append(new JQTable.Node("累计完成值", "n1"))
                        .append(new JQTable.Node("年度指标完成率", "n2"))
                        .append(new JQTable.Node("去年同期值", "n3"))
                        .append(new JQTable.Node("同比增幅", "n4"))
                ], gridName);
            }
            if (2 == gridStyle)
            {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度计划", "ndjh"),
                    new JQTable.Node("本季度计划", "jdjh"),
                    new JQTable.Node("当月完成", "dywc")
                        .append(new JQTable.Node("本月计划值", "y1"))
                        .append(new JQTable.Node("当月预计值", "y2"))
                        .append(new JQTable.Node("计划完成率", "y3"))
                        .append(new JQTable.Node("去年同期", "y4"))
                        .append(new JQTable.Node("同比增幅", "y5")),
                    new JQTable.Node("季度累计完成", "jdljwc")
                        .append(new JQTable.Node("季度累计", "jl1"))
                        .append(new JQTable.Node("季度计划完成率", "jl2"))
                        .append(new JQTable.Node("去年同期值", "jl3"))
                        .append(new JQTable.Node("同比增幅", "jl4")),
                    new JQTable.Node("季度预计完成", "jdyjwc")
                        .append(new JQTable.Node("末月预计", "jy1"))
                        .append(new JQTable.Node("季度预计合计", "jy2"))
                        .append(new JQTable.Node("季度预计完成率", "jy3"))
                        .append(new JQTable.Node("去年同期", "jy4"))
                        .append(new JQTable.Node("同比增幅", "jy5")),
                    new JQTable.Node("年度累计完成", "nd")
                        .append(new JQTable.Node("累计完成值", "n1"))
                        .append(new JQTable.Node("年度指标完成率", "n2"))
                        .append(new JQTable.Node("去年同期值", "n3"))
                        .append(new JQTable.Node("同比增幅", "n4"))
                ], gridName);
            }
            if (3 == gridStyle)
            {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度计划", "ndjh"),
                    new JQTable.Node("本季度计划", "jdjh"),
                    new JQTable.Node("下季度计划", "xjdjh"),
                    new JQTable.Node("当月完成", "dywc")
                        .append(new JQTable.Node("本月计划值", "y1"))
                        .append(new JQTable.Node("当月预计值", "y2"))
                        .append(new JQTable.Node("计划完成率", "y3"))
                        .append(new JQTable.Node("去年同期", "y4"))
                        .append(new JQTable.Node("同比增幅", "y5")),
                    new JQTable.Node("季度累计完成", "jdljwc")
                        .append(new JQTable.Node("季度累计", "jl1"))
                        .append(new JQTable.Node("季度计划完成率", "jl2"))
                        .append(new JQTable.Node("去年同期值", "jl3"))
                        .append(new JQTable.Node("同比增幅", "jl4")),
                    new JQTable.Node("年度累计完成", "nd")
                        .append(new JQTable.Node("累计完成值", "n1"))
                        .append(new JQTable.Node("年度指标完成率", "n2"))
                        .append(new JQTable.Node("去年同期值", "n3"))
                        .append(new JQTable.Node("同比增幅", "n4")),
                    new JQTable.Node("下季度预计完成", "xjdyjwc")
                        .append(new JQTable.Node("下季度首月预计", "xjy1"))
                        .append(new JQTable.Node("下季度次月预计", "xjy2"))
                        .append(new JQTable.Node("下季度末月预计", "xjy3"))
                        .append(new JQTable.Node("季度预计合计", "xjy4"))
                        .append(new JQTable.Node("季度预计完成率", "xjy5"))
                        .append(new JQTable.Node("年度累计", "xjy6"))
                        .append(new JQTable.Node("年度累计完成率", "xjy7"))
                        .append(new JQTable.Node("去年同期", "xjy8"))
                        .append(new JQTable.Node("同比增幅", "xjy9"))
                ], gridName);
            }

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

        private mActualMonth: number;
        private mOpt: IViewOption;
        private mData:Array<string[]> = [];
        private tableAssist:JQTable.JQGridAssistant;
        private mDataSet:Util.Ajax = new Util.Ajax("/BusinessManagement/ydzb/hzb_zbhz_prediction_update.do");

        private mJydwDataSet: Util.Ajax = new Util.Ajax("/BusinessManagement/ydzb/hzb_zbhz_prediction_jydw_compute.do", false);
        private mXmgsDataSet: Util.Ajax = new Util.Ajax("/BusinessManagement/ydzb/hzb_zbhz_prediction_xmgs_compute.do", false);

        public init(opt:any):void {
            this.mOpt = opt;

            let minDate = Util.addYear(opt.date, -3);
            minDate.month = 1;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年",
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
            this.mActualMonth = (parseInt($("#grid-season").val()) - 1) * 3 + parseInt($("#grid-season-month").val());
            this.mDataSet.get({month: this.mActualMonth, year: parseInt($("#grid-date").val())})
                .then((dataArray:any) => {
                    this.mData = dataArray;
                    this.updateTable();
                });
        }

        public exportExcelJydw() {
            this.mActualMonth = (parseInt($("#grid-season").val()) - 1) * 3 + parseInt($("#grid-season-month").val());
            this.mJydwDataSet.get({month: this.mActualMonth, year: parseInt($("#grid-date").val())}).then((tmStamp)=> {
                $("#exportJydw")[0].action = "/BusinessManagement/ydzb/general_export.do?" + Util.Ajax.toUrlParam({timeStamp: tmStamp.timeStamp});
                $("#exportJydw")[0].submit();
            })
        }

        public exportExcelXmgs() {
            this.mActualMonth = (parseInt($("#grid-season").val()) - 1) * 3 + parseInt($("#grid-season-month").val());
            this.mXmgsDataSet.get({month: this.mActualMonth, year: parseInt($("#grid-date").val())}).then((tmStamp)=> {
                $("#exportJydw")[0].action = "/BusinessManagement/ydzb/general_export.do?" + Util.Ajax.toUrlParam({timeStamp: tmStamp.timeStamp});
                $("#exportJydw")[0].submit();
            })
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

        private formatFirstMonthData(outputData: string[][]) {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(FirstMonthZb.dyjhwcl);
            precentList.push(FirstMonthZb.dytbzf);
            precentList.push(FirstMonthZb.jdyjwcl);
            precentList.push(FirstMonthZb.jdtbzf);
            precentList.push(FirstMonthZb.ndzbwcl);
            precentList.push(FirstMonthZb.ndtbzf);
            return Util.formatData(outputData,this.mData, precentList,[]);
        }

        private formatSecondMonthData(outputData: string[][]) {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(SecondMonthZb.dyjhwcl);
            precentList.push(SecondMonthZb.dytbzf);
            precentList.push(SecondMonthZb.jdjhwcl);
            precentList.push(SecondMonthZb.jdyjtbzf);
            precentList.push(SecondMonthZb.jdyjwcl);
            precentList.push(SecondMonthZb.jdtbzf);
            precentList.push(SecondMonthZb.ndzbwcl);
            precentList.push(SecondMonthZb.ndtbzf);
            return Util.formatData(outputData,this.mData, precentList,[]);
        }

        private formatThirdMonthData(outputData : string[][]) {
            var precentList: std.vector<number> = new std.vector<number>();
            precentList.push(ThirdMonthZb.dyjhwcl);
            precentList.push(ThirdMonthZb.dytbzf);
            precentList.push(ThirdMonthZb.jdjhwcl);
            precentList.push(ThirdMonthZb.jdtbzf);
            precentList.push(ThirdMonthZb.ndzbwcl);
            precentList.push(ThirdMonthZb.ndtbzf);
            precentList.push(ThirdMonthZb.xjdyjwcl);
            precentList.push(ThirdMonthZb.xjdndljwcl);
            precentList.push(ThirdMonthZb.xjdtbzf);
            return Util.formatData(outputData,this.mData, precentList,[]);
        }



        private createJqassist():JQTable.JQGridAssistant{
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='"+ this.jqgridName() +"'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(),parseInt($("#grid-season-month").val()));
            return this.tableAssist;
        }

        private updateTable():void {
            this.createJqassist();

            var outputData = [];
            if (1 == parseInt($("#grid-season-month").val())){
                this.formatFirstMonthData(outputData);
            } else if (2 == parseInt($("#grid-season-month").val())){
                this.formatSecondMonthData(outputData);
            } else if (3 == parseInt($("#grid-season-month").val())){
                this.formatThirdMonthData(outputData);
            }
            for (var i = 0; i < outputData.length; ++i) {
                if (outputData[i][0].lastIndexOf("计") >= 0 && outputData[i][0].lastIndexOf("审计") < 0) {
                    this.tableAssist.setRowBgColor(i, 183, 222, 232);
                }
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