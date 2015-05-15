/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module companys_ranking {

    class JQGridAssistantFactory {

        public static createTable(gridName: string): JQTable.JQGridAssistant {

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
            this.mIndex = $("#indextype").val();
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
                this.updateTable();

            });

        }

        private formatData() {
            var data = [];
            var row = [];
            for (var j = 0; j < this.mData.length; ++j) {
                row = [].concat(this.mData[j]);
                if (null != row[1]) {
                    if (row[1] == "true") {
                        row[1] = "已提交";
                    }
                    if (row[1] == "false") {
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

        private updateTable(): void {
            var name = this.mTableId + "_jqgrid_1234";
            var data = [];
            var tableAssist: JQTable.JQGridAssistant = null;

            tableAssist = JQGridAssistantFactory.createTable(name)
            //data = this.formatData();


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