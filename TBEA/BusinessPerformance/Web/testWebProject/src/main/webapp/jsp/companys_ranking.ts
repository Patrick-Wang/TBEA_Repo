/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

enum RANKINKType1{GSMC,NDJH, NDLJWC,JHWCLORTB,YEARRANKING, YDJH, YDWC,YDWCLORTB,MONTHRANKING};

module companys_ranking {

    class JQGridAssistantFactory {
        public static createTable(gridName: string, RankingType: number): JQTable.JQGridAssistant {
            if (RankingType == 1 ||  RankingType == 9) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度完成率排名", "yearRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("年度计划", "n1"))
                        .append(new JQTable.Node("年度累计完成", "n2"))
                        .append(new JQTable.Node("计划完成率", "n3"))
                        .append(new JQTable.Node("年度排名", "n4")),
                    new JQTable.Node("月度完成率排名", "monthRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("月度计划", "y1"))
                        .append(new JQTable.Node("月度完成", "y2"))
                        .append(new JQTable.Node("月度完成率", "y3"))
                        .append(new JQTable.Node("月度排名", "y4")),
                ], gridName);
            } else if (RankingType == 2) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度同比增长情况排名", "yearRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("年度累计", "n1"))
                        .append(new JQTable.Node("去年同期累计", "n2"))
                        .append(new JQTable.Node("同比增长", "n3"))
                        .append(new JQTable.Node("年度排名", "n4")),
                    new JQTable.Node("月度同比增长情况排名", "monthRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("当月完成", "y1"))
                        .append(new JQTable.Node("去年同期", "y2"))
                        .append(new JQTable.Node("同比增长", "y3"))
                        .append(new JQTable.Node("月度排名", "y4")),
                ], gridName);
            } else if (RankingType == 3 || RankingType == 4) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度累计完成排名", "yearRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("年度累计完成", "n1"))
                        .append(new JQTable.Node("年度排名", "n2")),
                    new JQTable.Node("月度完成", "monthRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("月度完成", "y1"))
                        .append(new JQTable.Node("月度排名", "y2")),
                ], gridName);
            }
        }
    }

    export class View {
        private static ins: View;

        public static newInstance(): View {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        }
        private mYear: number;
        private mMonth: number;
        private mIndex: number;
        private mDs: Util.DateSelector;
        private mData: Array<string[]> = [];
        private mDataSet: Util.Ajax = new Util.Ajax("companys_ranking_update.do");
        private mTableId: string;
        public init(tableId: string, dateId: string, year: number, month: number): void {
            this.mYear = year;
            this.mTableId = tableId;
            this.mMonth = month;
            this.mDs = new Util.DateSelector(
                { year: year - 1, month: 1 },
                { year: year, month: month },
                dateId);
            this.onIndexSelected();

        }

        public onIndexSelected() {
            this.mIndex = $("#ranktype").val();
            //this.mIndex = $("#indextype  option:selected").text();
        }

        public updateUI() {
            var date: Util.Date = this.mDs.getDate();
            //this.onIndexSelected();
            this.mDataSet.get({ month: date.month, year: date.year, rankingType: this.mIndex })
                .then((dataArray: any) => {
                this.mData = dataArray;
                $('h1').text(date.year + "年" + date.month + "月" + "经营单位指标排名情况");
                document.title = date.year + "年" + date.month + "月" + "经营单位指标排名情况";
                this.updateTable(this.mIndex);

            });

        }

        private formatData(data: string[][]) {
            var row = [];
            var mdata = [];
            for (var i = 0; i < this.mData.length; ++i) {
                row = [].concat(this.mData[i]);
                mdata[i] = data[i].concat(row);
                for(var j = 1; j < mdata[i].length; j++)
                {
                    if (RANKINKType1.YEARRANKING == j ||  RANKINKType1.MONTHRANKING == j){
                       mdata[i][j] = Util.formatInt(mdata[i][j]); 
                    }
                    else if (RANKINKType1.JHWCLORTB == j || RANKINKType1.YDWCLORTB == j){
                        mdata[i][j] = Util.formatPercent(mdata[i][j]); 
                    }else{
                        mdata[i][j] = Util.formatCurrency(mdata[i][j]);
                    }   
                }                 
            }            
            return mdata;
        }

        private updateTable(rankingType: number): void {
            var name = this.mTableId + "_jqgrid_1234";
            
             var data = [
                ["沈变公司"],
                ["衡变公司"],
                ["新变厂",],
                ["鲁缆公司"],
                ["新缆厂"],
                ["德缆公司"],
                ["天池能源"],
                ["能动公司"],
                ["新能源公司"],
                ["新特能源公司"],
                ["进出口公司"],
                ["国际工程公司"]];
            var tableAssist: JQTable.JQGridAssistant = null;

            tableAssist = JQGridAssistantFactory.createTable(name, rankingType);
            data = this.formatData(data);
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    //                    cellsubmit: 'clientArray',
                    //                    cellEdit: true,
                    height: '100%',
                    width: 1000,
                    shrinkToFit: true,
                    rowNum: 100,
                    autoScroll: true
                }));

        }
    }
}