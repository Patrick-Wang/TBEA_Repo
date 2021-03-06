/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="dateSelector.ts" />
/// <reference path="companySelector.ts" />
///<reference path="messageBox.ts"/>
declare var echarts;
declare var $;
module entry_template {

    import Cell = JQTable.Cell;
    import Formula = JQTable.Formula;
    import Formula = JQTable.Formula;
    class JQGridAssistantFactory {

        public static createFlatTable(gridName: string, title: string[], statusList: string[]): JQTable.JQGridAssistant {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i == 0) {
                    nodes.push(new JQTable.Node(title[i], "_" + i, true, JQTable.TextAlign.Left));
                } else {
                    nodes.push(new JQTable.Node(title[i], "_" + i, statusList[i - 1] == Util.ZBStatus.APPROVED));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }
    }

    interface IViewOption {
        tableId: string;
        dateId: string;
        date?: Util.Date;
        companyId: string;
        comps: Util.IDataNode[];
        entryType?: Util.ZBType;
    }

    interface ISubmitResult {
        result: string;
    }

    interface Zbxx{
        id:number;
        name:string;
        parent:number;
        children:Zbxx[];
    }

    interface ExchangeRate{
        id:number;
        nf:number;
        rate:number;
    }

    function find(data:Array<string[]>, id:any){
        for (let i = 0; i < data.length; ++i){
            if (data[i][0] == id){
                return i;
            }
        }
        return -1;
    }

    export class View {
        public static instance: View = new View();
        public static getInstance(): View {
            return View.instance;
        }
        private mRate:number;
        private mZbxxs:Array<Zbxx>;
        private mStatusList: Array<string>;
        private mTableData: Array<any[]>;
        private mDateSelector: Util.DateSelector;
        private mCompanySelector: Util.CompanySelector;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("zb_update.do", false);
        private mSubmit: Util.Ajax = new Util.Ajax("zb_submit.do");
        private mSave: Util.Ajax = new Util.Ajax("zb_save.do");
        private mSubmitToDeputy: Util.Ajax = new Util.Ajax("zb_submitToDeputy.do");
        private mTableAssist: JQTable.JQGridAssistant;
        private mExRateZbs : number[];
        private mTitleCompanyName: string;

        initInstance(opt: IViewOption) {

            this.mOpt = opt;
            switch (this.mOpt.entryType) {

                case Util.ZBType.YDJDMJH:
                    this.mDateSelector = new Util.DateSelector(
                        { year: this.mOpt.date.year - 3 },
                        Util.addMonth({ year: this.mOpt.date.year, month: this.mOpt.date.month }, 3),
                        this.mOpt.dateId, true);
                    break;
                case Util.ZBType.QNJH:
                    this.mDateSelector = new Util.DateSelector(
                        { year: this.mOpt.date.year - 3 },
                        { year: this.mOpt.date.year },
                        this.mOpt.dateId, true);
                    break;
                case Util.ZBType.BY20YJ:
                case Util.ZBType.BY28YJ:
                case Util.ZBType.BYSJ:
                    this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);
                    break;
            }

            // this.mDateSelector.select(this.mOpt.date);

            this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }

            this.updateTitle();

            //this.updateUI();
        }

        private getCurrentExchangeRate(){

        }

        public updateUI() {
            $("#nodatatips").css("display", "none");
            $("#entryarea").css("display", "");
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH) {
                date = Util.addMonth(date, -2);
            }
            this.mDataSet.get({ year: date.year, month: date.month, entryType: this.mOpt.entryType, companyId: this.mCompanySelector.getCompany() })
                .then((data: any) => {
                    this.mStatusList = data.status;
                    this.mTableData = data.values;
                    this.mZbxxs = data.zbxx;
                    this.mRate = data.exchangeRate;
                    this.mExRateZbs = [295,302,306];
                    this.updateTitle();
                    this.updateTable(this.mOpt.tableId);
                    this.updateApproveStatusFromDeputy(date.year, date.month, this.mOpt.entryType);
                    $('#save').css("display", "block");
                    $('#submit').css("display", "block");
                    if (data.isJydw) {
                        $('#submitToDeputy').css("display", "block");
                    }
                });

        }

        public updateApproveStatusFromDeputy(year: number, month: number, entryType: Util.ZBType) {
            //年度计划数经营副总审核状态
            var isShowSubmit_2: boolean = false;
            var isShowapprove_2: boolean = false;
            var approve_2Content: string = "";
            var submit_2Content: string = "";
            //年度计划数据和实际计划数据
            if ((Util.ZBType.QNJH == entryType || Util.ZBType.BYSJ) && this.mStatusList.length == 1) {
                if (this.mStatusList[0] == Util.ZBStatus.SUBMITTED_2) {
                    isShowSubmit_2 = true;
                    if (Util.ZBType.QNJH == entryType) {
                        submit_2Content = year + "年计划数据";
                    } else {
                        submit_2Content = month + "月实际数据";
                    }
                } else if (this.mStatusList[0] == Util.ZBStatus.APPROVED_2) {
                    isShowapprove_2 = true;
                    if (Util.ZBType.QNJH == entryType) {
                        approve_2Content = year + "年计划数据";
                    } else {
                        approve_2Content = month + "月实际数据";
                    }
                }
                this.addContent(isShowapprove_2, isShowSubmit_2, approve_2Content, submit_2Content);
            }

            //月度-季度计划数经营副总审核状态
            if (Util.ZBType.YDJDMJH == entryType && this.mStatusList.length == 3) {
                //Init MatchArray for Month
                var MatchArray: Array<number> = this.initMatchArray(entryType, month, year);

                for (var i = 0; i < this.mStatusList.length; i++) {
                    if (this.mStatusList[i] == Util.ZBStatus.SUBMITTED_2) {
                        isShowSubmit_2 = true;
                        submit_2Content += MatchArray[i] + "月,";
                    } else if (this.mStatusList[i] == Util.ZBStatus.APPROVED_2) {
                        isShowapprove_2 = true;
                        approve_2Content += MatchArray[i] + "月,";
                    }
                }
                if ("" != approve_2Content && isShowapprove_2) {
                    approve_2Content = approve_2Content.substring(0, approve_2Content.length - 1);
                    approve_2Content += "计划数据";
                }
                if ("" != submit_2Content && isShowSubmit_2) {
                    submit_2Content = submit_2Content.substring(0, submit_2Content.length - 1);
                    submit_2Content += "计划数据";
                }

                this.addContent(isShowapprove_2, isShowSubmit_2, approve_2Content, submit_2Content);
            }
            //20号实际数据和28号实际数据
            if ((Util.ZBType.BY20YJ == entryType || Util.ZBType.BY28YJ == entryType) && this.mStatusList.length > 1) {

                var MatchArray: Array<number> = this.initMatchArray(entryType, month, year);

                if (month == 12) {
                    for (var i = 0; i < this.mStatusList.length; i++) {
                        if (i == 0) {
                            if (this.mStatusList[0] == Util.ZBStatus.SUBMITTED_2) {
                                isShowSubmit_2 = true;
                                submit_2Content += year + "年" + MatchArray[i] + "月,";
                            } else if (this.mStatusList[0] == Util.ZBStatus.APPROVED_2) {
                                isShowapprove_2 = true;
                                approve_2Content += year + "年" + MatchArray[i] + "月,";
                            }
                        } else {
                            if (this.mStatusList[i] == Util.ZBStatus.SUBMITTED_2) {
                                isShowSubmit_2 = true;
                                submit_2Content += (year + 1) + "年" + MatchArray[i] + "月,";
                            } else if (this.mStatusList[i] == Util.ZBStatus.APPROVED_2) {
                                isShowapprove_2 = true;
                                approve_2Content += (year + 1) + "年" + MatchArray[i] + "月,";
                            }
                        }
                    }
                } else {
                    for (var i = 0; i < this.mStatusList.length; i++) {
                        if (this.mStatusList[i] == Util.ZBStatus.SUBMITTED_2) {
                            isShowSubmit_2 = true;
                            submit_2Content += MatchArray[i] + "月,";

                        } else if (this.mStatusList[i] == Util.ZBStatus.APPROVED_2) {
                            isShowapprove_2 = true;
                            approve_2Content += MatchArray[i] + "月,";
                        }
                    }
                }
                if (Util.ZBType.BY20YJ == entryType) {
                    if ("" != approve_2Content && isShowapprove_2) {
                        approve_2Content = approve_2Content.substring(0, approve_2Content.length - 1);
                        approve_2Content += "20号预计数据";
                    }
                    if ("" != submit_2Content && isShowSubmit_2) {
                        submit_2Content = submit_2Content.substring(0, submit_2Content.length - 1);
                        submit_2Content += "20号预计数据";
                    }
                } else if (Util.ZBType.BY28YJ == entryType) {
                    if ("" != approve_2Content && isShowapprove_2) {
                        approve_2Content = approve_2Content.substring(0, approve_2Content.length - 1);
                        approve_2Content += "预计数据";
                    }
                    if ("" != submit_2Content && isShowSubmit_2) {
                        submit_2Content = submit_2Content.substring(0, submit_2Content.length - 1);
                        submit_2Content += "预计数据";
                    }
                }

                this.addContent(isShowapprove_2, isShowSubmit_2, approve_2Content, submit_2Content);
            }
        }

        private initMatchArray(entryType: Util.ZBType, month: number, year: number): Array<number> {
            var retArray: Array<number> = [];
            switch (entryType) {
                case Util.ZBType.YDJDMJH:
                    retArray.push(month);
                    retArray.push(month + 1);
                    retArray.push(month + 2);
                    break;
                case Util.ZBType.BY20YJ:
                case Util.ZBType.BY28YJ:
                    if (12 == month) {
                        retArray.push(12)
                        retArray.push(1)
                        retArray.push(2)
                        retArray.push(3)
                    } else {
                        for (var i = 0; i < this.mStatusList.length; i++) {
                            retArray.push(month + i);
                        }
                    }
                    break;
                default:
                    break;

            }
            return retArray;
        }

        private addContent(approve_2Mark: boolean, submit_2Mark: boolean, approve_2Content: string, submit_2Content: string) {
            var mergecontent: string = "";
            if (approve_2Mark) {
                $('#DeputyApprovementStatus').css("display", "block");
                mergecontent += approve_2Content + "被经营副总审核!" + "<br/>";
            }

            if (submit_2Mark) {
                $('#DeputyApprovementStatus').css("display", "block");
                mergecontent += submit_2Content + "尚未被经营副总审核!";
            }
            if ("" != mergecontent) {

                $('#DeputyApprovementStatus')[0].innerHTML = mergecontent;
            }

        }

        public save() {
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH) {
                date = Util.addMonth(date, -2);
            }
            var allData = this.mTableAssist.getAllData();
            var submitData = [];
            var colNames = this.mTableAssist.getColNames();
            for (var i = 0; i < allData.length; ++i) {
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j) {
                    if (j != 1) {
                        submitData[i].push(allData[i][j])
                        allData[i][j] = allData[i][j].replace(new RegExp(' ', 'g'), '')
                    }
                }
            }
            //if (Util.ZBType.BYSJ == this.mOpt.entryType) {
            //    let zbxxs:Zbxx[] = this.checkSum(submitData);
            //    if (zbxxs.length != 0) {
            //        let zbxxs:Zbxx[] = this.checkSum(submitData);
            //        if (zbxxs.length != 0) {
            //            let msg = "";
            //            for (let i = 0; i < zbxxs.length; ++i){
            //                msg += "、" + zbxxs[i].name;
            //            }
            //
            //            Util.MessageBox.tip(msg.substr(1) + " 指标值与子项和不匹配");
            //            return;
            //        }
            //    }
            //}

            this.mSave.post({
                year: date.year,
                month: date.month,
                entryType: this.mOpt.entryType,
                companyId: this.mCompanySelector.getCompany(),
                data: JSON.stringify(submitData)
            }).then((data: ISubmitResult) => {
                if ("true" == data.result) {
                    Util.MessageBox.tip("保存 成功");
                } else if ("false" == data.result) {
                    Util.MessageBox.tip("保存 失败");
                } else {
                    Util.MessageBox.tip(data.result);
                }
            });
        }



        public submit() {
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH) {
                date = Util.addMonth(date, -2);
            }
            var allData = this.mTableAssist.getAllData();
            var submitData = [];
            var colNames = this.mTableAssist.getColNames();
            for (var i = 0; i < allData.length; ++i) {
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j) {
                    if (j != 1) {
                        submitData[i].push(allData[i][j])
                        if (allData[i][j].replace(new RegExp(' ', 'g'), '') == "") {
                            Util.MessageBox.tip("有空内容 无法提交")
                            return;
                        }
                    }
                }
            }

            if (Util.ZBType.BYSJ == this.mOpt.entryType) {
                //let zbxxs:Zbxx[] = this.checkSum(submitData);
                //if (zbxxs.length != 0) {
                for (let i = 1; i < submitData[0].length; ++i){
                    let zbxxs:Zbxx[] = this.checkSum(submitData, i);
                    if (zbxxs.length != 0) {
                        let msg = "";
                        for (let i = 0; i < zbxxs.length; ++i){
                            msg += "、" + zbxxs[i].name;
                        }

                        Util.MessageBox.tip("第" + i + "列 " + msg.substr(1) + " 指标值与子项和不匹配");
                        return;
                    }
                }
                //}
            }

            this.mSubmit.post({
                year: date.year,
                month: date.month,
                entryType: this.mOpt.entryType,
                companyId: this.mCompanySelector.getCompany(),
                data: JSON.stringify(submitData)
            }).then((data: ISubmitResult) => {

                if ("true" == data.result) {
                    Util.MessageBox.tip("提交 成功");
                } else if ("false" == data.result) {
                    Util.MessageBox.tip("提交 失败");
                } else {
                    Util.MessageBox.tip(data.result);
                }
            });
        }

        public submitToDeputy() {
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH) {
                date = Util.addMonth(date, -2);
            }
            var allData = this.mTableAssist.getAllData();
            var submitData = [];
            var colNames = this.mTableAssist.getColNames();
            for (var i = 0; i < allData.length; ++i) {
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j) {
                    if (j != 1) {
                        submitData[i].push(allData[i][j])
                        if (allData[i][j].replace(new RegExp(' ', 'g'), '') == "") {
                            Util.MessageBox.tip("有空内容 无法提交")
                            return;
                        }
                    }
                }
            }

            if (Util.ZBType.BYSJ == this.mOpt.entryType) {
                //let zbxxs:Zbxx[] = this.checkSum(submitData);
                //if (zbxxs.length != 0) {
                for (let i = 1; i < submitData[0].length; ++i) {
                    let zbxxs:Zbxx[] = this.checkSum(submitData, i);
                    if (zbxxs.length != 0) {
                        let msg = "";
                        for (let i = 0; i < zbxxs.length; ++i) {
                            msg += "、" + zbxxs[i].name;
                        }

                        Util.MessageBox.tip("第" + i + "列" + msg.substr(1) + " 指标值与子项和不匹配");
                        return;
                    }
                }
                //}
            }

            this.mSubmitToDeputy.post({
                year: date.year,
                month: date.month,
                entryType: this.mOpt.entryType,
                companyId: this.mCompanySelector.getCompany(),
                data: JSON.stringify(submitData)
            }).then((data: ISubmitResult) => {
                if ("true" == data.result) {
                    Util.MessageBox.tip("提交内部审核 成功");
                } else if ("false" == data.result) {
                    Util.MessageBox.tip("提交内部审核  失败");
                } else {
                    Util.MessageBox.tip(data.result);
                }
            });
        }

        private updateTitle() {
            var header = "";
            var date = this.mDateSelector.getDate();
            var compName = this.mCompanySelector.getCompanyName();
            switch (this.mOpt.entryType) {
                case Util.ZBType.QNJH:
                    header = date.year + "年 " + compName + " 计划数据录入";
                    break;
                case Util.ZBType.YDJDMJH:
                    header = date.year + "年 " + compName + " 季度-月度计划值录入";
                    break;
                case Util.ZBType.BY20YJ:
                    header = date.year + "年" + date.month + "月 " + compName + " 20日预计值录入";
                    break;
                case Util.ZBType.BY28YJ:
                    header = date.year + "年" + date.month + "月 " + compName + " 28日预计值录入";
                    break;
                case Util.ZBType.BYSJ:
                    header = date.year + "年" + date.month + "月 " + compName + " 实际数据录入";
                    break;
            }

            $('h1').text(header);
            document.title = header;
        }

        private createPredict(title: string[]): Array<string> {

            var ret: Array<string> = [title[0]];
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH) {
                date = Util.addMonth(date, -3);
            }
            var left = date.month % 3;
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH && 0 == left) {
                if (12 == date.month) {
                    ret.push("1月计划")
                    ret.push("2月计划")
                    ret.push("3月计划")
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
                case Util.ZBType.YDJDMJH:
                    if (this.mDateSelector.getDate().month % 3 != 0) {
                        this.disableEntry(tableId);
                        return;
                    } else {
                        titles = this.createPredict(["指标名称"]);
                    }
                    break;
                case Util.ZBType.BY20YJ:
                    titles = this.createPredict(["指标名称", "本月20日预计值"]);
                    break;
                case Util.ZBType.BY28YJ:
                    titles = this.createPredict(["指标名称", "本月28日预计值"]);
                    break;
                case Util.ZBType.BYSJ:
                    titles = ["指标名称", "本月实际"];
                    break;
            }
            this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);

            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 2; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j]) {
                        this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) + "";
                    }else{
                        if (347 == this.mTableData[i][0]){
                            if (this.mOpt.entryType == Util.ZBType.BYSJ ||
                                this.mOpt.entryType == Util.ZBType.BY28YJ ||
                                this.mOpt.entryType == Util.ZBType.BY20YJ){
                                this.mTableData[i][j] = 0;
                            }
                        }
                    }
                }
            }

            if (Util.ZBType.BYSJ == this.mOpt.entryType){
                let disabledCell = [];
                for (let i = 0; i < this.mZbxxs.length; ++i){
                    let zbxx : Zbxx = this.mZbxxs[i];
                    if (find(this.mTableData, zbxx.id) >= 0){
                        for (let j = 0; j < zbxx.children.length; ++j){
                            let cells = this.parseZbxx(zbxx.children[j]);
                            if (cells.length > 0){
                                disabledCell = disabledCell.concat(cells);
                            }
                        }
                    }
                }

                let cells = this.parseZbxx48();
                if (cells.length > 0){
                    disabledCell = disabledCell.concat(cells);
                }

                if (disabledCell.length != 0){
                    this.mTableAssist.disableCellEdit(disabledCell);
                }
            }

            var data = this.mTableData;
            $("#" + name).jqGrid(
                this.mTableAssist.decorate({
                    data: this.mTableAssist.getDataWithId(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: data.length > 25 ? 550 : '100%',
                    width: titles.length * 200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 1000,
                    assistEditable: true
                }));
        }

        private parseZbxx(zbxx:Zbxx):Cell[] {
            let row = find(this.mTableData, zbxx.id);
            if (row < 0) {
                return [];
            }

            let dsts:Cell[] = [];
            for (let i = 1; i < this.mTableData[0].length - 1; ++i){
                let cells = [];
                for (let j = 0; j < zbxx.children.length; ++j){
                    let row2 = find(this.mTableData, zbxx.children[j].id);
                    if (row2 >= 0){
                        let cel = new Cell(row2, i);
                        if (Util.indexOf(this.mExRateZbs, zbxx.children[j].id) >= 0){
                            cel.rate = this.mRate;
                        }else{
                            cel.rate = 1;
                        }
                        cells.push(cel);
                    }
                }
                if (cells.length == 0) {
                    return [];
                }

                let dst = new Cell(row, i);
                dsts.push(dst);
                let form : Formula  = new Formula(dst, cells, (dest:Cell, srcs:Cell[])=>{
                    let sum : any;
                    for (let i = 0; i < srcs.length; ++i){
                        let val = srcs[i].getVal();
                        if ("" != val){
                            if (sum == undefined){
                                sum = parseFloat(val) * srcs[i].rate;
                            }else{
                                sum += parseFloat(val) * srcs[i].rate;
                            }
                        }
                    }
                    if (sum != undefined){
                        sum = sum.toFixed(4);
                    }
                    return sum;
                });
                this.mTableAssist.addFormula(form);
            }
            return dsts;
        }

        private parseZbxx48():Cell[] {
            let dsts:Cell[] = [];
            let row = find(this.mTableData, 48);
            if (row >= 0) {
                return [];
            }


            for (let i = 1; i < this.mTableData[0].length - 1; ++i) {
                let cells:any = [];
                let cellTmp = new Cell(find(this.mTableData, 290), 1);
                if (cellTmp.row() >= 0) {
                    cells.push(cellTmp);
                }
                cellTmp = new Cell(find(this.mTableData, 299), 1);
                if (cellTmp.row() >= 0) {
                    cells.push(cellTmp);
                }
                cellTmp = new Cell(find(this.mTableData, 304), 1);
                if (cellTmp.row() >= 0) {
                    cells.push(cellTmp);
                }

                if (cells.length == 0) {
                    return [];
                }

                let dst = new Cell(row, 1);
                dsts.push(dst);
                let form:Formula = new Formula(dst, cells, (dest:Cell, srcs:Cell[])=> {
                    let sum:any;
                    for (let i = 0; i < srcs.length; ++i) {
                        let val = srcs[i].getVal();
                        if ("" != val) {
                            if (sum == undefined) {
                                sum = parseFloat(val);
                            } else {
                                sum += parseFloat(val);
                            }
                        }
                    }
                    if (sum != undefined) {
                        sum = sum.toFixed(4);
                    }
                    return sum;

                });
                this.mTableAssist.addFormula(form);
            }
            return dsts;
        }


        private checkSum(submitData:any, col:number): Zbxx[] {
            let zbxxs : Zbxx[] = [];
            let zbxx:Zbxx;
            for (let i = 0; i < this.mZbxxs.length; ++i){
                zbxx = this.mZbxxs[i];
                let row = find(submitData, zbxx.id);
                if (row < 0) {
                    continue;
                }


                let sum : number;
                for(let j = 0; j < zbxx.children.length; ++j){
                    let row2 = find(submitData, zbxx.children[j].id);
                    if (row2 < 0) {
                        continue;
                    }
                    if (submitData[row2][1] != ""){
                        if (sum == undefined){
                            sum = parseFloat(submitData[row2][col]);
                        }else{
                            sum += parseFloat(submitData[row2][col]);
                        }
                    }else{
                        if (sum == undefined){
                            sum = 0;
                        }
                    }
                }
                if (sum != undefined){
                    if (submitData[row][col] == "" && sum != 0){
                        zbxxs.push(zbxx);
                    }else if (Math.abs(sum - parseFloat(submitData[row][col])) > 2){
                        zbxxs.push(zbxx);
                    }
                    sum = undefined;
                }
            }
            return zbxxs;
        }
    }
}