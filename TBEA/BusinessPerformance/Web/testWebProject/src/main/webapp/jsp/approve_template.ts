/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="dateSelector.ts" />
/// <reference path="companySelector.ts" />
declare var echarts;
declare var $;
module approve_template {

    class JQGridAssistantFactory {

        public static createFlatTable(gridName: string, title: string[]): JQTable.JQGridAssistant {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i == 0) {
                    nodes.push(new JQTable.Node(title[i], "_" + i, true, JQTable.TextAlign.Left, 125));
                } else {
                    nodes.push(new JQTable.Node(title[i], "_" + i, false, JQTable.TextAlign.Right, 125));
                }

            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }
    }

    export interface IViewOption {
        tableApproveId: string;
        tableUnapproveId: string;
        dateId: string;
        companyId: string;
        topComps:string[][];
        subComps?: Array<string[][]>;
        firstCompany?: string;
        date: Util.Date;
        approveType: Util.ZBType;
    }

    interface ISubmitResult {
        result: boolean;
    }


    export class View {
        public static instance: View = new View();
        public static getInstance(): View {
            return View.instance;
        }

        private mTableApproveAssist: JQTable.JQGridAssistant;
        private mApprove: Util.Ajax = new Util.Ajax("zb_approve.do");
        private mTableApproveData: Array<string[]>;
        private mTableUnapproveData: Array<string[]>;
        private mUnapprove: Util.Ajax = new Util.Ajax("zb_unapprove.do");
        private mTableUnapproveAssist: JQTable.JQGridAssistant;

        private mDateSelector: Util.DateSelector;
        private mCompanySelector: Util.CompanySelector;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("zb_update.do");


        initInstance(opt: IViewOption) {
            this.mOpt = opt;
            this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 1 }, this.mOpt.date, this.mOpt.dateId);
            this.mCompanySelector = new Util.CompanySelector(
            true, 
                opt.companyId, 
                opt.topComps, opt.
                firstCompany, 
                opt.subComps
//                ,{
//                noneSelectedText : '经营单位',
//                selectedText: '# 个经营单位被选中'     
//                }
                );
            this.updateTitle();
            this.updateUI();
        }

        public updateUI() {
            var date = this.mDateSelector.getDate();
            this.mDataSet.get({ year: date.year, month: date.month, approveType: this.mOpt.approveType })
                .then((data: any) => {
                    this.mTableApproveData = this.transposition(data[0]);
                    this.mTableUnapproveData = this.transposition(data[1]);
                    this.updateTitle();
                    this.mTableApproveAssist = this.updateTable(this.mOpt.tableApproveId, this.mTableApproveData);
                    this.mTableUnapproveAssist = this.updateTable(this.mOpt.tableUnapproveId, this.mTableUnapproveData);
                });
        }

        private transposition(data : string[][]) :　Array<string[]>{
            var dataRet : Array<string[]> = [];
           
            for (var i = 0; i < data[0].length; ++i){
                dataRet.push([]);         
               for (var j = 0; j < data.length; ++j){
                  dataRet[i].push(data[j][i]);   
               }
               dataRet[i] = dataRet[i].reverse();
            }
            return dataRet;
        }
        
        
        private getTitles() : string[][]{
            var titles = null;
            switch (this.mOpt.approveType) {
                case Util.ZBType.QNJH:
                    titles = this.transposition([["全年计划"]]);
                    break;
                case Util.ZBType.YDJDMJH:
                 	titles = this.transposition([this.createPredict(["月度-季度末计划值"])]);
                    break;
                case Util.ZBType.BY20YJ:
                    titles = this.transposition([this.createPredict(["本月20日预计值"])]);
                    break;
                case Util.ZBType.BY28YJ:
                    titles = this.transposition([this.createPredict(["本月28日预计值"])]);
                    break;
                case Util.ZBType.BYSJ:
                    titles = this.transposition([["本月实际"]]);
                    break;
            }
            return titles;
        }

        public updateTable(tableId: string, tableData : string[][]) :  JQTable.JQGridAssistant{
            var name = tableId + "_jqgrid";
            var titles = this.getTitles();
            var tableAssist : JQTable.JQGridAssistant = JQGridAssistantFactory.createFlatTable(name, ["审核类型"].concat(tableData[0]));
            var data =  titles;

            for (var i = 0; i < data.length; ++i){
                data[i] = data[i].concat(tableData[i + 1]);
            }

            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

            var width = (tableData[0].length + 1) * 125;
            if (width > 1000){
                width = 1000;
            }
            
            $("#" + name).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(data),
                    datatype: "local",
                    multiselect: true,
                    drag: false,
                    resize: false,
                    //autowidth : false,
//                    cellsubmit: 'clientArray',
//                    cellEdit: false,
                    height: '100%',
                    width: width,
                    shrinkToFit: width == 1000 ? false : true,
                    autoScroll: true,
                }));
            return tableAssist;
        }


        public unapprove() {
            var date = this.mDateSelector.getDate();

            this.mUnapprove.post({
                year: date.year,
                month: date.month,
                approveType: this.mOpt.approveType,
                data: JSON.stringify(this.mTableUnapproveAssist.getAllData())
            })
                .then((data: ISubmitResult) => {
                    if (data.result) {
                        alert("submit 成功");
                    } else {
                        alert("submit 失敗");
                    }
                });
        }


        public approve() {
            var date = this.mDateSelector.getDate();

            this.mApprove.post({
                year: date.year,
                month: date.month,
                approveType: this.mOpt.approveType,
                data: JSON.stringify(this.mTableApproveAssist.getAllData())
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
            switch (this.mOpt.approveType) {
                case Util.ZBType.QNJH:
                    header = date.year + "年 计划数据审核";
                    break;
                case Util.ZBType.YDJDMJH:
                    header = date.year + "年" + date.month + "月 季度-月度末计划值审核";
                    break;
                case Util.ZBType.BY20YJ:
                    header = date.year + "年" + date.month + "月 20日预计值审核";
                    break;
                case Util.ZBType.BY28YJ:
                    header = date.year + "年" + date.month + "月 28日预计值审核";
                    break;
                case Util.ZBType.BYSJ:
                    header = date.year + "年" + date.month + "月 实际数据审核";
                    break;
            }

            $('h1').text(header);
            document.title = header;
        }

        private createPredict(title: string[]): Array<string> {
            var ret: Array<string> = [title[0]];
            var date = this.mDateSelector.getDate();
            var left = date.month % 3;
            if (this.mOpt.approveType == Util.ZBType.YDJDMJH && left == 0) {
                if (12 == date.month) {
                    ret.push((date.year + 1) + "年1月计划")
                    ret.push((date.year + 1) + "年2月计划")
                    ret.push((date.year + 1) + "年3月计划")
                } else {
                    ret.push((date.month + 1) + "月计划")
                    ret.push((date.month + 2) + "月计划")
                    ret.push((date.month + 3) + "月计划")
                }

            } else if (this.mOpt.approveType == Util.ZBType.BY20YJ ||
                this.mOpt.approveType == Util.ZBType.BY28YJ) {
                ret.push(title[1]);
                if (0 != left) {
                    var leftMonth = 3 - left;
                    for (var i = 1; i <= leftMonth; ++i) {
                        ret.push((date.month + i) + "月预计")
                    }
                } else{
	                if (12 == date.month) {
	                    ret.push((date.year + 1) + "年1月预计")
	                    ret.push((date.year + 1) + "年2月预计")
	                    ret.push((date.year + 1) + "年3月预计")
	                } else {
	                    ret.push((date.month + 1) + "月预计")
	                    ret.push((date.month + 2) + "月预计")
	                    ret.push((date.month + 3) + "月预计")
	                }
                }
            }

            return ret;
        }
    }
}