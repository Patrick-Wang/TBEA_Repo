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

        public static createQnjhTable(gridName: string, title: string[], ids: string[]): JQTable.JQGridAssistant {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i < 1) {
                    nodes.push(new JQTable.Node(title[i], ids[i], true, JQTable.TextAlign.Left, 125));
                } else {
                    nodes.push(new JQTable.Node(title[i], ids[i], false, JQTable.TextAlign.Right, 125));
                }

            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }

        public static createSjTable(gridName: string, title: string[], ids: string[]): JQTable.JQGridAssistant {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i <= 1) {
                    nodes.push(new JQTable.Node(title[i], ids[i], true, JQTable.TextAlign.Left, 125));
                } else {
                    nodes.push(new JQTable.Node(title[i], ids[i], false, JQTable.TextAlign.Right, 125));
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
        comps: Util.IDataNode[];
        firstCompany?: number;
        date: Util.Date;
        approveType: Util.ZBType;
    }

    interface ISubmitResult {
        result: boolean;
    }


    function transposition(data: string[][]): Array<string[]> {
        var dataRet: Array<string[]> = [];

        for (var i = 0; i < data[0].length; ++i) {
            dataRet.push([]);
            for (var j = 0; j < data.length; ++j) {
                dataRet[i].push(data[j][i]);
            }
            dataRet[i] = dataRet[i].reverse();
        }
        return dataRet;
    }

    function resize(data: string[], size) {
        if (data.length < size) {
            for (var i = data.length; i < size; ++i) {
                data.push("");
            }
            return data;
        } else {
            var dataNew = [];
            for (var i = 0; i < size; ++i) {
                dataNew.push(data[i]);
            }
            return dataNew;
        }
    }

    interface ISubView {
        getApprovedData(): number[][];
        getUnapprovedData(): number[][];
        process(data: any, date: Util.Date, companies: Util.IData[]): void;
        getDate(): Util.Date;
    }

    class QNJHSubView implements ISubView {

        private mTableUnapproveAssist: JQTable.JQGridAssistant;
        private mTableApproveAssist: JQTable.JQGridAssistant;
        private mOpt: IViewOption;
        private mData: any;
        private mApproveColZbIds: string[];
        private mDate: Util.Date;
        constructor(opt: IViewOption) {
            this.mOpt = opt;
        }

        getApprovedData(): number[][] {
            if (this.mTableApproveAssist != null) {
                var ids: any = this.mTableApproveAssist.getCheckedRowIds();
                $(ids).each((i) => {
                    ids[i] = parseInt(ids[i]);
                });
                return [ids];
            } else {
                return [[]];
            }
        }
               
        //[[compId ...]]
        getUnapprovedData(): number[][] {
            if (this.mTableUnapproveAssist != null) {
                var ids: any = this.mTableUnapproveAssist.getCheckedRowIds();
                $(ids).each((i) => {
                    ids[i] = parseInt(ids[i]);
                });
                return [ids];
            } else {
                return [[]];
            }
        }

        getDate(): Util.Date {
            return this.mData;
        }
        
        //[[compId ,zbId, zbName, value] ...] approved 
        //[[compId ,zbId, zbName, value] ...] unapproved
        process(data: any, date: Util.Date, companies: Util.IData[]): void {
            this.mData = date;
            if (data[0].length > 0) {
                this.mTableApproveAssist = this.updateTable(data[0], companies, this.mOpt.tableApproveId);
            }

            if (data[1].length > 0) {
                this.mTableUnapproveAssist = this.updateTable(data[1], companies, this.mOpt.tableUnapproveId);
            }
        }


        private updateTable(rawData: string[][], comps: Util.IData[], tableId: string): JQTable.JQGridAssistant {
            var tmpData = [];
            var title = ["单位名称"];
            var colZbIds = ["dw"];
            var zbColMap = {};
            var compMap = {};
            var companies = [];
            // remove unused company
            $(comps).each((i) => {
                $(rawData).each((j) => {
                    if (rawData[j][0] == "" + comps[i].id) {
                        compMap["c_" + comps[i].id] = comps[i];
                    }
                });
            });
            
            for (var i in compMap){
                 companies.push(compMap[i]);
            }
            
            //make title
            $(rawData).each((i) => {
                if (!Util.isExist(zbColMap["_" + rawData[i][1]])) {
                    colZbIds.push(rawData[i][1]);
                    title.push(rawData[i][2])
                    zbColMap["_" + rawData[i][1]] = title.length;
                }
            });
            
            //make data
            $(companies).each((i) => {
                tmpData.push([companies[i].id, companies[i].value]);
                $(rawData).each((j) => {
                    if (rawData[j][0] == "" + companies[i].id) {
                        if (tmpData[i].length <= zbColMap["_" + rawData[j][1]]) {
                            resize(tmpData[i], zbColMap["_" + rawData[j][1]]);
                        }
                        tmpData[i][zbColMap["_" + rawData[j][1]]] = rawData[j][3];
                    }
                });
            });



            var name = tableId + "_jqgrid";
            var jqAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createQnjhTable(name, title, colZbIds);

            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

            var width = (title.length) * 125;
            if (width > 1000) {
                width = 1000;
            }

            $("#" + name).jqGrid(
                jqAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: jqAssist.getDataWithId(tmpData),
                    datatype: "local",
                    multiselect: true,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    //cellsubmit: 'clientArray',
                    //cellEdit: false,
                    height: '100%',
                    width: width,
                    shrinkToFit: width == 1000 ? false : true,
                    autoScroll: true,
                }));
            return jqAssist;
        }
    }

    class YJSJSubView implements ISubView {

        private mTableUnapproveAssist: JQTable.JQGridAssistant;
        private mTableApproveAssist: JQTable.JQGridAssistant;
        private mOpt: IViewOption;
        private mData: any;
        private mApproveColZbIds: string[];
        private mDate: Util.Date;

        constructor(opt: IViewOption) {
            this.mOpt = opt;
        }

        getApprovedData(): number[][] {
            var ret: any = [[]];
            if (this.mTableApproveAssist != null) {
                var checkedRows: string[] = this.mTableApproveAssist.getCheckedRowIds();
                if (checkedRows.length != 0) {
                    ret = this.format(checkedRows);
                }
            }
            return ret;
        }

        private format(checkedRows: string[]): number[][] {
            var ret: any = [];
            var comps = [];
            var years = [];
            var months =[];
            $(checkedRows).each((i: number) => {
                var row: any = checkedRows[i].split("&");
                $(row).each((j) => {
                    row[j] = parseInt(row[j]);
                });
                
                comps.push(row[0]);
                if (row.length > 1){
                    years.push(row[1]);
                    months.push(row[2]);
                }

            });
            ret.push(comps);
            if (years.length > 0){
                ret.push(years);
            }
             if (months.length > 0){
                ret.push(months);
            }
            
            return ret;
        }
        
        //[[compId...], [year...], [month...]]
        getUnapprovedData(): number[][] {
            var ret: any = [[]];
            if (this.mTableUnapproveAssist != null) {
                var checkedRows: string[] = this.mTableUnapproveAssist.getCheckedRowIds();
                if (checkedRows.length != 0) {
                    ret = this.format(checkedRows);
                }
            }
            return ret;
        }

        getDate(): Util.Date {
            return this.mData;
        }
        
        //[[compId ,zbId, zbName, value, year?, month?] ...] approved 
        //[[compId ,zbId, zbName, value, year?, month?] ...] unapproved
        process(data: any, date: Util.Date, companies: Util.IData[]): void {
            this.mData = date;
            if (data[0].length > 0) {
                this.mTableApproveAssist = this.updateTable(data[0], companies, this.mOpt.tableApproveId);
            }

            if (data[1].length > 0) {
                this.mTableUnapproveAssist = this.updateTable(data[1], companies, this.mOpt.tableUnapproveId);
            }
        }


        private updateTable(rawData: string[][], comps: Util.IData[], tableId: string): JQTable.JQGridAssistant {
            var compMap = {};
            var companies = [];
            // remove unused company
            $(comps).each((i) => {
                $(rawData).each((j) => {
                    if (rawData[j][0] == "" + comps[i].id) {
                        compMap["c_" + comps[i].id] = comps[i];
                    }
                });
            });
            
            for (var i in compMap){
                 companies.push(compMap[i]);
            }

            var title = ["单位名称"];
            var colZbIds = ["dw", "rq"];
            var zbColMap = {};
            
            //make title
            $(rawData).each((i) => {
                if (!Util.isExist(zbColMap["_" + rawData[i][1]])) {
                    colZbIds.push(rawData[i][1]);
                    title.push(rawData[i][2])
                    zbColMap["_" + rawData[i][1]] = title.length;
                }
            });

            var hasDate: boolean = rawData[0].length > 4;
            if (hasDate) {
                title.push("日期");
                colZbIds.push("rq");
            }
            var tmpData = [];
            //make data
            $(companies).each((i) => {
                tmpData.push([companies[i].id, companies[i].value]);
                $(rawData).each((j) => {
                    if (rawData[j][0] == "" + companies[i].id) {
                        if (tmpData.length == i) {
                            if (hasDate) {
                                tmpData.push([companies[i].id + "&" + rawData[j][4] + "&" + rawData[j][5], companies[i].value, rawData[j][4] + "年" + rawData[j][5]] + "月");
                            } else {
                                tmpData.push([companies[i].id, companies[i].value]);
                            }
                        }

                        if (tmpData[i].length <= zbColMap["_" + rawData[j][1]]) {
                            resize(tmpData[i], zbColMap["_" + rawData[j][1]]);
                        }
                        tmpData[i][zbColMap["_" + rawData[j][1]]] = rawData[j][3];
                    }
                });
            });


//            $(companies).each((i) => {
//
//                $(rawData).each((j) => {
//                    if (!Util.isExist(zbColMap["_" + rawData[j][1]])) {
//                        colZbIds.push(rawData[j][1]);
//                        title.push(rawData[j][2])
//                        zbColMap["_" + rawData[j][1]] = title.length;
//                    }
//                });
//
//                $(rawData).each((j) => {
//                    if (rawData[j][0] == "" + companies[i].id) {
//                        if (tmpData.length == i) {
//                            if (hasDate) {
//                                tmpData.push([companies[i].id + "&" + rawData[j][4] + "&" + rawData[j][5], companies[i].value, rawData[j][4] + "年" + rawData[j][5]] + "月");
//                            } else {
//                                tmpData.push([companies[i].id, companies[i].value]);
//                            }
//                        }
//
//                        if (tmpData[i].length <= zbColMap["_" + rawData[j][1]]) {
//                            resize(tmpData[i], zbColMap["_" + rawData[j][1]]);
//                        }
//                        tmpData[i][zbColMap["_" + rawData[j][1]]] = rawData[j][3];
//                    }
//                });
//            });

            var name = tableId + "_jqgrid";
            var jqAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createQnjhTable(name, title, colZbIds);

            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

            var width = (title.length) * 125;
            if (width > 1000) {
                width = 1000;
            }

            $("#" + name).jqGrid(
                jqAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: jqAssist.getDataWithId(tmpData),
                    datatype: "local",
                    multiselect: true,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    //cellsubmit: 'clientArray',
                    //cellEdit: false,
                    height: '100%',
                    width: width,
                    shrinkToFit: width == 1000 ? false : true,
                    autoScroll: true,
                }));
            return jqAssist;
        }
    }
    //     class SJSubView implements ISubView{
    //        
    //        private mOpt: IViewOption;
    //        constructor(opt: IViewOption){
    //            this.mOpt = opt;    
    //        }
    //        
    //        getApprovedData() : string[][]{
    //            return null;
    //        }
    //        
    //        getUnapprovedData(): string[][]{
    //            return null;
    //        }
    //        process(data : any, date : Util.Date, companies :Util.IData[]) : void{
    //            
    //        }
    //    }
    
    export class View {
        public static instance: View = new View();
        public static getInstance(): View {
            return View.instance;
        }


        private mCurView: ISubView;
        private mDateSelector: Util.DateSelector;
        private mCompanySelector: Util.CompanySelector;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("zb_update.do", false);
        private mUnapprove: Util.Ajax = new Util.Ajax("zb_unapprove.do");
        private mApprove: Util.Ajax = new Util.Ajax("zb_approve.do");

        initInstance(opt: IViewOption) {
            this.mOpt = opt;
            if (this.mOpt.approveType == Util.ZBType.YDJDMJH) {
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 1 }, this.mOpt.date, this.mOpt.dateId, true);
            } else {
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 1 }, this.mOpt.date, this.mOpt.dateId);
            }

            this.mCompanySelector = new Util.CompanySelector(
                true,
                opt.companyId,
                opt.comps,
                opt.firstCompany
            //                ,{
            //                noneSelectedText : '经营单位',
            //                selectedText: '# 个经营单位被选中'     
            //                }
                );
            this.mCompanySelector.checkAll();

            switch (this.mOpt.approveType) {
                case Util.ZBType.QNJH:
                    this.mCurView = new QNJHSubView(opt);
                    break;
                case Util.ZBType.YDJDMJH:
                    //                    this.mCurView = new JDYDJHSubView(opt);
                    break;
                case Util.ZBType.BY20YJ:
                case Util.ZBType.BY28YJ:
                case Util.ZBType.BYSJ:
                    this.mCurView = new YJSJSubView(opt);
                    break;
            }

            this.updateTitle();
            this.updateUI();
        }

        public updateUI() {
            var comps = this.mCompanySelector.getCompanys();
            if (comps.length != 0) {
                var date = this.mDateSelector.getDate();
                this.mDataSet.post({ year: date.year, month: date.month, approveType: this.mOpt.approveType, companies: JSON.stringify(comps) })
                    .then((data: any) => {
                    this.updateTitle();

                    if (data[0].length == 0) {
                        $("#approve").css("display", "none");
                    } else {
                        $("#approve").css("display", "");
                    }

                    if (data[1].length == 0) {
                        $("#unapprove").css("display", "none");
                    } else {
                        $("#unapprove").css("display", "");
                    }
                    this.mCurView.process(data, this.mDateSelector.getDate(), this.mCompanySelector.getRawCompanyData());

                });
            } else {
                alert("请选择公司");
            }
        }


        //        private getTitles(): string[][] {
        //            var titles = null;
        //            switch (this.mOpt.approveType) {
        //                case Util.ZBType.QNJH:
        //                    titles = this.transposition([this.mCompanySelector.getCompanyNames()]);
        //                    break;
        //                case Util.ZBType.YDJDMJH:
        //                    titles = this.transposition([this.createPredict(["月度-季度末计划值"])]);
        //                    break;
        //                case Util.ZBType.BY20YJ:
        //                    titles = this.transposition([this.createPredict(["本月20日预计值"])]);
        //                    break;
        //                case Util.ZBType.BY28YJ:
        //                    titles = this.transposition([this.createPredict(["本月28日预计值"])]);
        //                    break;
        //                case Util.ZBType.BYSJ:
        //                    titles = this.transposition([["本月实际"]]);
        //                    break;
        //            }
        //            return titles;
        //        }

        //        public updateTable(tableId: string, tableData: string[][]): JQTable.JQGridAssistant {
        //            var name = tableId + "_jqgrid";
        //            var titles = this.getTitles();
        //            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createFlatTable(name, ["审核类型"].concat(tableData[0]));
        //            var data = titles;
        //
        //            for (var i = 0; i < data.length; ++i) {
        //                data[i] = data[i].concat(tableData[i + 1]);
        //            }
        //
        //            var parent = $("#" + tableId);
        //            parent.empty();
        //            parent.append("<table id='" + name + "'></table>");
        //
        //            var width = (tableData[0].length + 1) * 125;
        //            if (width > 1000) {
        //                width = 1000;
        //            }
        //
        //            $("#" + name).jqGrid(
        //                tableAssist.decorate({
        //                    // url: "TestTable/WGDD_load.do",
        //                    // datatype: "json",
        //                    data: tableAssist.getData(data),
        //                    datatype: "local",
        //                    multiselect: true,
        //                    drag: false,
        //                    resize: false,
        //                    //autowidth : false,
        //                    //                    cellsubmit: 'clientArray',
        //                    //                    cellEdit: false,
        //                    height: '100%',
        //                    width: width,
        //                    shrinkToFit: width == 1000 ? false : true,
        //                    autoScroll: true,
        //                }));
        //            return tableAssist;
        //        }


        public unapprove() {
            var date = this.mCurView.getDate();
            var compIds = this.mCurView.getUnapprovedData();
            if (compIds[0].length > 0) {
                this.mUnapprove.post({
                    approveType: this.mOpt.approveType,
                    year: date.year,
                    month: date.month,
                    data: JSON.stringify(compIds)
                }).then((data: ISubmitResult) => {
                    if (data.result) {
                        alert("submit 成功");
                        this.mDateSelector.select(date);
                        this.updateUI();
                    } else {
                        alert("submit 失敗");
                    }
                });
            } else {
                alert("请选择公司");
            }
        }


        public approve() {
            var date = this.mCurView.getDate();
            var compIds = this.mCurView.getApprovedData();
            if (compIds[0].length > 0) {
                this.mApprove.post({
                    approveType: this.mOpt.approveType,
                    year: date.year,
                    month: date.month,
                    data: JSON.stringify(compIds)
                }).then((data: ISubmitResult) => {
                    if (data.result) {
                        alert("submit 成功");
                        this.mDateSelector.select(date);
                        this.updateUI();
                    } else {
                        alert("submit 失敗");
                    }
                });
            } else {
                alert("请选择公司");
            }
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
                } else {
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