/**
 * Created by Floyd on 2016/3/28.
 */
/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
module yearTotal {

    class JQGridAssistantFactory {

         public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("月度", "yd", true, JQTable.TextAlign.Center),
                new JQTable.Node("月度", "yd1", true, JQTable.TextAlign.Center),
                new JQTable.Node("5年以上", "wnys", true, JQTable.TextAlign.Left),
                new JQTable.Node("4-5年", "sdwn", true, JQTable.TextAlign.Left),
                new JQTable.Node("3-4年", "sdsn", true, JQTable.TextAlign.Left),
                new JQTable.Node("2-3年", "edsn", true, JQTable.TextAlign.Left),
                new JQTable.Node("1-2年", "yden", true, JQTable.TextAlign.Left),
                new JQTable.Node("1年以内", "ynyn", true, JQTable.TextAlign.Left),
                new JQTable.Node("合计", "hj", true, JQTable.TextAlign.Left),
            ], gridName);
        }
    }
    export class View {
        public static newInstance(): View {
            return new View();
        }

        private mYear: number;
        private mMonth: number;
        private mTableData: Array<string[]>;
        private mComp: Util.CompanyType = Util.CompanyType.HB;
        private mDataSet : Util.Ajax;
        private mTableId : string;
        public init(tableId: string, year: number, month : number): void {
            this.mYear = year;
            this.mMonth = month;
            this.mTableId = tableId;
            this.mDataSet = new Util.Ajax("blhtdqqkhz_update.do");
            this.updateTable(this.mTableId);
            this.updateUI();
        }



        public onYearSelected(year : number){
            this.mYear = year;
        }

        public onCompanySelected(comp : Util.CompanyType){
            this.mComp = comp;
        }

        public updateUI() {
            this.mDataSet.get({
                    year: this.mYear,
                    companyId: this.mComp
                })
                .then((jsonData: any) => {
                    this.mTableData = jsonData;
                    document.title = this.mYear + "年";
                    this.updateTable(this.mTableId);
                });

        }

        private updateTable(name: string): void {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);

            var data = [];

            for (var i = this.mMonth + 1; i <= 12; ++i){
                data.push(["上年度", i]);
            }

            for (var i = 1; i <= this.mMonth; ++i){
                data.push(["本年度", i]);
            }

            if (undefined != this.mTableData) {
                var row = [];
                for (var i = 0; i < data.length; ++i) {
                    row = [].concat(this.mTableData[i]);
                    for (var col in row) {
                        row[col] = Util.formatCurrency(row[col]);
                    }
                    data[i] = data[i].concat(row);
                }
            }
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(
                tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    data: tableAssist.getData(data),
                    datatype: "local"
                }));
        }
    }
}

var view = yearTotal.View.newInstance();