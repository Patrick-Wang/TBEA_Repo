/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
declare var echarts;

//利润计划完成率排名,经营性净现金流实际完成排名
enum RANKINGTYPE1{GSMC,NDJH, NDLJWC,JHWCL,YEARRANKING, YDJH, YDWC,YDWCL,MONTHRANKING};
//利润指标年度累计完成同比增长情况排名
enum RANKINGTYPE2{GSMC,NDLJ,QNTQLJ,NDTBZZ,YEARRANKING,DYWC,QNTQ,YDTBZZ,MONTHRANKING};
//人均利润，人均收入
enum RANKINGTYPE3{GSMC,NDLJ,YEARRANKING,DYWC,MONTHRANKING};
//应收账款占收入排名
enum RANKINGTYPE4{GSMC,BYSR,BYYSZK,YSZKZSRBZ,MONTHRANKING};
//应收账款加保理
enum RANKINGTYPE5{GSMC,BYSR,BYYSZK,BYBLYE,YSZKZSRBZ,MONTHRANKING};
//存货占比
enum RANKINGTYPE6{GSMC,BYSR,BYCH,CHZSRBZ,MONTHRANKING};
//应收账款加存货占比
enum RANKINGTYPE7{GSMC,BYSR,BYYSZK,BYCH,YSZKCHZSRBZ,MONTHRANKING};

module companys_ranking{

    import Endpoint = framework.route.Endpoint;
    import router = framework.router;

    class JQGridAssistantFactory {
        public static createTable(gridName: string, RankingType: number): JQTable.JQGridAssistant {
            if (RankingType == 1 ||  RankingType == 9 || RankingType == 11) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度完成率排名", "yearRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("年度计划", "n1"))
                        .append(new JQTable.Node("年度累计完成", "n2"))
                        .append(new JQTable.Node("计划完成率", "n3"))
                        .append(new JQTable.Node("年度排名", "n4", true, JQTable.TextAlign.Right, 0, undefined, undefined, true, true, "int")),
                    new JQTable.Node("月度完成率排名", "monthRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("月度计划", "y1"))
                        .append(new JQTable.Node("月度完成", "y2"))
                        .append(new JQTable.Node("月度完成率", "y3"))
                        .append(new JQTable.Node("月度排名", "y4",true, JQTable.TextAlign.Right, 0, undefined, undefined, true, true, "int"))
                ], gridName);
            } else if (RankingType == 2) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度同比增长情况排名", "yearRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("年度累计", "n1"))
                        .append(new JQTable.Node("去年同期累计", "n2"))
                        .append(new JQTable.Node("同比增长", "n3"))
                        .append(new JQTable.Node("年度排名", "n4", true, JQTable.TextAlign.Right, 0, undefined, undefined, true, true, "int")),
                    new JQTable.Node("月度同比增长情况排名", "monthRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("当月完成", "y1"))
                        .append(new JQTable.Node("去年同期", "y2"))
                        .append(new JQTable.Node("同比增长", "y3"))
                        .append(new JQTable.Node("月度排名", "y4", true, JQTable.TextAlign.Right, 0, undefined, undefined, true, true, "int"))
                ], gridName);
            } else if (RankingType == 3 || RankingType == 4 || RankingType == 13 || RankingType == 14) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度累计完成排名", "yearRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("年度累计完成", "n1"))
                        .append(new JQTable.Node("年度排名", "n2", true, JQTable.TextAlign.Right, 0, undefined, undefined, true, true, "int")),
                    new JQTable.Node("月度完成", "monthRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("月度完成", "y1"))
                        .append(new JQTable.Node("月度排名", "y2", true, JQTable.TextAlign.Right, 0, undefined, undefined, true, true, "int"))
                ], gridName);
            }else if(RankingType == 5){
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月收入（还原至全年）", "income", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月应收账款", "accountReceive", true, JQTable.TextAlign.Left),
                    new JQTable.Node("应收账款占收入比重", "accountReceiveRate", true, JQTable.TextAlign.Left),
                    new JQTable.Node("月度排名", "monthRanking", true, JQTable.TextAlign.Left, 0, undefined, undefined, true, true, "int")
                ], gridName);
            }else if(RankingType == 6){
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月收入（还原至全年）", "income", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月应收账款", "accountReceive", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月保理余额", "factoring", true, JQTable.TextAlign.Left),
                    new JQTable.Node("应收账款占收入比重", "accountReceiveRate", true, JQTable.TextAlign.Left),
                    new JQTable.Node("月度排名", "monthRanking", true, JQTable.TextAlign.Left, 0, undefined, undefined, true, true, "int")
                ], gridName);
            }else if(RankingType == 7){
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月收入（还原至全年）", "income", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月存货", "stock", true, JQTable.TextAlign.Left),
                    new JQTable.Node("存货占收入比重", "stockRate", true, JQTable.TextAlign.Left),
                    new JQTable.Node("月度排名", "monthRanking", true, JQTable.TextAlign.Left, 0, undefined, undefined, true, true, "int")
                ], gridName);
            }else if(RankingType == 8){
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月收入（还原至全年）", "income", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月应收账款", "accountReceive", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月存货", "stock", true, JQTable.TextAlign.Left),
                    new JQTable.Node("（应收账款+存货）占收入比重", "accountReceiveandStockRate", true, JQTable.TextAlign.Left),
                    new JQTable.Node("月度排名", "monthRanking", true, JQTable.TextAlign.Left, 0, undefined, undefined, true, true, "int")
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

        private mOpt: IViewOption;
        private mData:Array<string[]> = [];
        private tableAssist:JQTable.JQGridAssistant;
        private mDataSet:Util.Ajax = new Util.Ajax("/BusinessManagement/ydzbRanking/companys_ranking_update.do");
        private mIndex: number;

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
            $("#grid-export").on("click", ()=> {
                this.exportExcel();
            });

            $("#company-type").change(()=>{
                this.showCont();
            })

            this.showCont();
            this.updateUI();
        }

        private formatData(data: string[][]) {
            var row = [];
            var mdata = [];
            for (var i = 0; i < this.mData.length; ++i) {
                if(data[i] == null){
                    mdata[i] = [].concat(this.mData[i]);
                }else{
                    row = [].concat(this.mData[i]);
                    mdata[i] = data[i].concat(row);
                }
                for(var j = 1; j < mdata[i].length; j++)
                {
                    if(this.mIndex == 1 || this.mIndex == 9 || this.mIndex == 11)
                    {
                        if (RANKINGTYPE1.YEARRANKING == j ||  RANKINGTYPE1.MONTHRANKING == j){
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }
                        else if (RANKINGTYPE1.JHWCL == j || RANKINGTYPE1.YDWCL == j){
                            mdata[i][j] = Util.formatPercent(mdata[i][j]);
                        }else{
                            mdata[i][j] = Util.formatCurrency(mdata[i][j]);
                        }
                    }else if(this.mIndex == 3 || this.mIndex == 4 ||this.mIndex == 13 ||this.mIndex == 14)
                    {
                        if (RANKINGTYPE3.YEARRANKING == j ||  RANKINGTYPE3.MONTHRANKING == j){
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }else{
                            mdata[i][j] = Util.formatFordot(mdata[i][j],1);
                        }
                    } else if (this.mIndex == 2) {
                        if (RANKINGTYPE2.YEARRANKING == j || RANKINGTYPE2.MONTHRANKING == j){
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }else if (RANKINGTYPE2.NDTBZZ == j || RANKINGTYPE2.YDTBZZ == j){
                            mdata[i][j] = Util.formatPercent(mdata[i][j]);
                        }else {
                            mdata[i][j] =  Util.formatCurrency(mdata[i][j]);
                        }
                    }else if(this.mIndex ==5){
                        if (RANKINGTYPE4.MONTHRANKING == j){
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }else if (RANKINGTYPE4.YSZKZSRBZ == j){
                            mdata[i][j] = Util.formatPercent(mdata[i][j]);
                        }else {
                            mdata[i][j] =  Util.formatCurrency(mdata[i][j]);
                        }
                    }else if(this.mIndex == 6){
                        if (RANKINGTYPE5.MONTHRANKING == j){
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }else if (RANKINGTYPE5.YSZKZSRBZ == j){
                            mdata[i][j] = Util.formatPercent(mdata[i][j]);
                        }else {
                            mdata[i][j] =  Util.formatCurrency(mdata[i][j]);
                        }
                    }else if(this.mIndex == 7){
                        if (RANKINGTYPE6.MONTHRANKING == j){
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }else if (RANKINGTYPE6.CHZSRBZ == j){
                            mdata[i][j] = Util.formatPercent(mdata[i][j]);
                        }else {
                            mdata[i][j] =  Util.formatCurrency(mdata[i][j]);
                        }
                    }else if(this.mIndex == 8){
                        if (RANKINGTYPE7.MONTHRANKING == j){
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }else if (RANKINGTYPE7.YSZKCHZSRBZ == j){
                            mdata[i][j] = Util.formatPercent(mdata[i][j]);
                        }else {
                            mdata[i][j] =  Util.formatCurrency(mdata[i][j]);
                        }
                    }
                }
            }
            return mdata;
        }
        private showCont(){
        switch($("#company-type").val()){
            case "1":
                $("#report-type").empty();
                var option = $("<option>").text("利润计划完成率排名").val(1);
                var option2 = $("<option>").text("利润指标年度累计完成同比增长排名").val(2);
                var option3 = $("<option>").text("人均利润实际完成排名").val(3);
                var option4 = $("<option>").text("人均收入实际完成排名").val(4);
                var option5 = $("<option>").text("应收账款占收入比排名").val(5);
                var option6 = $("<option>").text("应收账款加保理占收入排名").val(6);
                var option7 = $("<option>").text("存货占收入比排名").val(7);
                var option8 = $("<option>").text("应收加存货占收入比排名").val(8);
                var option9 = $("<option>").text("经营性净现金流实际完成排名").val(9);
                //var option10 = $("<option>").text("各单位净资产收益率完成排名").val(10);
                $("#report-type").append(option).append(option2).append(option3).append(option4)
                    .append(option5).append(option6).append(option7).append(option8).append(option9);
                break;
            case "2":
                $("#report-type").empty();
                var option = $("<option>").text("利润指标年度累计完成同比增长排名").val(11);
                //var option2 = $("<option>").text("项目公司净资产收益率排名").val(12);
                var option3 = $("<option>").text("人均收入完成排名").val(13);
                var option4 = $("<option>").text("人均利润完成排名").val(14);
                $("#report-type").append(option).append(option3).append(option4);

                break;
            default:
                break;
        }
    }
        public exportExcel() {
            $("#exportExcel")[0].action = "/BusinessManagement/ydzbRanking/companys_ranking_export.do?" + Util.Ajax.toUrlParam($.extend(this.getDate(),{rankingType: this.mIndex}));
            $("#exportExcel")[0].submit();
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
            this.mIndex = $("#report-type").val();
            this.mDataSet.get($.extend(this.getDate(), {rankingType: this.mIndex}))
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
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mIndex);
            return this.tableAssist;
        }

        private updateTable():void {
            this.createJqassist();
            var data = null;
            if ($("#company-type").val() == "1")
            {
                if(this.mIndex == 1 || this.mIndex == 2 ||this.mIndex == 3 || this.mIndex == 4 || this.mIndex == 9){
                    var predata = [
                        ["沈变公司"],
                        ["衡变公司"],
                        ["新变厂"],
                        ["鲁缆公司"],
                        ["新缆厂"],
                        ["德缆公司"],
                        ["天池能源"],
                        ["能动公司"],
                        ["新能源公司"],
                        ["新特能源公司"],
                        ["进出口公司"],
                        ["国际工程公司"],
                        ["众和公司"]];
                    data = this.formatData(predata);
                }else{
                    data = this.formatData([]);
                }
            }else {
                data = this.formatData([]);
            }

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