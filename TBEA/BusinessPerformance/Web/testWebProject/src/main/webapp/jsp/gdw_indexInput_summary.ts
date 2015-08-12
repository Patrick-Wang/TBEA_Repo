/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module gdw_indexinput_summary {

    class JQGridAssistantFactory {

        public static createTable(gridName: string): JQTable.JQGridAssistant {

            return new JQTable.JQGridAssistant([
                new JQTable.Node("公司名称", "gsmc", true, JQTable.TextAlign.Left),
                new JQTable.Node("预计指标填写情况", "inputCondition", true, JQTable.TextAlign.Left),
                new JQTable.Node("填写时间", "inputTime", true, JQTable.TextAlign.Left),
                new JQTable.Node("审核时间", "approveTime", true, JQTable.TextAlign.Left),
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
        private mCompanyType:number;
        private mCompanyName: string;
        private mDs: Util.DateSelector;
        private mData: Array<string[]> = [];
        private mDataSet: Util.Ajax = new Util.Ajax("status_update.do");
        private mTableId: string;
        private isZHCompany:boolean;
        public init(tableId: string, dateId: string, year: number, month: number,isZHCompany: boolean): void {
            this.mYear = year;
            this.mTableId = tableId;
            this.mMonth = month;
            this.isZHCompany = isZHCompany;
            this.mDs = new Util.DateSelector(
                { year: year - 3, month: 1 },
                { year: year, month: month },
                dateId);
            this.onIndexSelected();
            this.onCompanysSelected();

        }

        public onIndexSelected() {
            this.mIndex = $("#indextype").val();
            //this.mIndex = $("#indextype  option:selected").text();
        }
        
        public onCompanysSelected() {
            this.mCompanyType = $("#companytype").val();
            this.mCompanyName = $("#companytype  option:selected").text();
            //this.mIndex = $("#indextype  option:selected").text();
        }

        public updateUI() {
            var date: Util.Date = this.mDs.getDate();
            //this.onIndexSelected();
            this.mDataSet.get({ month: date.month, year: date.year, entryType: this.mIndex, companyType: this.mCompanyType })
                .then((dataArray: any) => {
                this.mData = dataArray;
                if (this.isZHCompany) {
                    $('h1').text(date.year + "年" + date.month + "月" + "众和公司各项目单位预测指标填报情况");
                    document.title = date.year + "年" + date.month + "月" + "众和公司各项目单位预测指标填报情况";
                    
                } else {
                    if(this.mCompanyType == 1)
                    {
                        $('h1').text(date.year + "年" + date.month + "月" + "各经营单位预测指标填报情况");
                        document.title = date.year + "年" + date.month + "月" + "各经营单位预测指标填报情况";
                    }else{
                         $('h1').text(date.year + "年" + date.month + "月" + this.mCompanyName + "预测指标填报情况");
                        document.title = date.year + "年" + date.month + "月" + this.mCompanyName + "预测指标填报情况";
                    }
                    
                }
                
                this.updateTable();

            });

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

        private updateTable(): void {
            var name = this.mTableId + "_jqgrid_1234";
            var data = [];
            var tableAssist: JQTable.JQGridAssistant = null;

            tableAssist = JQGridAssistantFactory.createTable(name)
            data = this.formatData();


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
                    width: 500,
                    shrinkToFit: true,
                    rowNum: 100,
                    autoScroll: true
                }));

        }
    }
}