/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="dateSelector.ts" />
declare var echarts;
declare var $;
module entry_template {


    class JQGridAssistantFactory {

        public static createFlatTable(gridName: string, title: string[]): JQTable.JQGridAssistant {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i == 0) {
                    nodes.push(new JQTable.Node(title[i], "_" + i, true, JQTable.TextAlign.Left));
                } else {
                    nodes.push(new JQTable.Node(title[i], "_" + i, false));
                }

            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }

        //        public static createHierarchyTable(gridName: string): JQTable.JQGridAssistant {
        //            return new JQTable.JQGridAssistant([
        //                new JQTable.Node("A", "a"),
        //                new JQTable.Node("B", "b")
        //                    .append(new JQTable.Node("C", "c"))
        //            ], gridName);
        //        }
    }

    interface IViewOption {
        tableId: string;
        dateId: string;
        date?: Util.Date;
        //entryType?: Util.EntyType;
        entryType?: Util.ZBType;
    }

    interface ISubmitResult {
        result: boolean;
    }


    export class View {
        public static instance: View = new View();
        public static getInstance(): View {
            return View.instance;
        }

        private mTableData: Array<string[]>;
        private mDateSelector: Util.DateSelector;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("zb_update.do");
        private mSubmit: Util.Ajax = new Util.Ajax("zb_submit.do");
        private mTableAssist: JQTable.JQGridAssistant;
        initInstance(opt: IViewOption) {
            this.mOpt = opt;
            this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 1 }, this.mOpt.date, this.mOpt.dateId);
            this.updateTitle();
            this.updateUI();
        }

        public updateUI() {
            var date = this.mDateSelector.getDate();
            this.mDataSet.get({ year: date.year, month: date.month, entryType: this.mOpt.entryType })
                .then((data: any) => {
                    this.mTableData = data;
                    this.updateTitle();
                    this.updateTable(this.mOpt.tableId);
                });
        }

        public submit() {
            var date = this.mDateSelector.getDate();

            this.mSubmit.post({
                year: date.year,
                month: date.month,
                entryType: this.mOpt.entryType,
                data: JSON.stringify(this.mTableAssist.getAllData())
            })
                .then((data: ISubmitResult) => {
                    if (data.result) {
                        alert("submit 成功");
                    } else {
                        alert("submit 失敗");
                    }
                });
        }

        private updateTitle() {
            var header = "";
            var date = this.mDateSelector.getDate();
            switch (this.mOpt.entryType) {
                case Util.ZBType.QNJH:
                    header = date.year + "年 计划数据录入";
                    break;
                case Util.ZBType.BY20JH:
                    header = date.year + "年" + date.month + "月 20日计划值录入";
                    break;
                case Util.ZBType.BY28JH:
                    header = date.year + "年" + date.month + "月 28日计划值录入";
                    break;
                case Util.ZBType.BY20YJ:
                    header = date.year + "年" + date.month + "月 20日预计值录入";
                    break;
                case Util.ZBType.BY28YJ:
                    header = date.year + "年" + date.month + "月 28日预计值录入";
                    break;
                case Util.ZBType.BYSJ:
                    header = date.year + "年" + date.month + "月 实际数据录入";
                    break;
            }

            $('h1').text(header);
            document.title = header;

        }

        private createPredict(title: string[]): Array<string> {

            var ret: Array<string> = [title[0]];
            var date = this.mDateSelector.getDate();
            var left = date.month % 3;
            if ((this.mOpt.entryType == Util.ZBType.BY20JH ||
                this.mOpt.entryType == Util.ZBType.BY28JH) && 0 == left) {
                if (12 == date.month) {
                    ret.push((date.year + 1) + "年1月计划")
                    ret.push((date.year + 1) + "年2月计划")
                    ret.push((date.year + 1) + "年3月计划")
                } else {
                    ret.push((date.month + 1) + "月计划")
                    ret.push((date.month + 2) + "月计划")
                    ret.push((date.month + 3) + "月计划")
                }

            } else if (this.mOpt.entryType == Util.ZBType.BY20YJ ||
                this.mOpt.entryType == Util.ZBType.BY28YJ) {
                ret.push(title[1]);
                if (0 != left) {
                    var leftMonth = 3 - left;
                    for (var i = 1; i <= leftMonth; ++i) {
                        ret.push((date.month + i) + "月预计")
                    }
                }
            }
            return ret;
        }

        
        private disableEntry(tableId: string) {
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<div style='font-size:18px'>没有可修改的记录</div>");
            $("#submit").css("display", "none");

        }

        private enableEntry() {
            $("#submit").css("display", "");
        }
        
        private updateTable(tableId: string): void {
            var name = tableId + "_jqgrid";

            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            this.enableEntry();

            var titles = null;
            switch (this.mOpt.entryType) {
                case Util.ZBType.QNJH:
                    titles = ["指标名称", "全年计划"];
                    break;
                case Util.ZBType.BY20JH:
                    if (this.mDateSelector.getDate().month % 3 != 0) {
                        this.disableEntry(tableId);
                        return;
                    }
                case Util.ZBType.BY20YJ:
                    titles = this.createPredict(["指标名称", "本月20日预计值"]);
                    break;
                case Util.ZBType.BY28JH:
                     if (this.mDateSelector.getDate().month % 3 != 0) {
                        this.disableEntry(tableId);
                        return;
                    }
                case Util.ZBType.BY28YJ:
                    titles = this.createPredict(["指标名称", "本月28日预计值"]);
                    break;
                case Util.ZBType.BYSJ:
                    titles = ["指标名称", "本月实际"];
                    break;
            }
            this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
            var data = this.mTableData;

        

            $("#" + name).jqGrid(
                this.mTableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: this.mTableAssist.getData(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: titles.length * 200,
                    shrinkToFit: true,
                    autoScroll: true,
                }));

        }
    }
}