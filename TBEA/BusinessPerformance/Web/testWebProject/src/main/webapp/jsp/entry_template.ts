/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="dateSelector.ts" />
/// <reference path="companySelector.ts" />
declare var echarts;
declare var $;
module entry_template {

    class JQGridAssistantFactory {

        public static createFlatTable(gridName: string, title: string[], statusList : string[]): JQTable.JQGridAssistant {
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


    export class View {
        public static instance: View = new View();
        public static getInstance(): View {
            return View.instance;
        }

        private mStatusList: Array<string>;
        private mTableData: Array<string[]>;
        private mDateSelector: Util.DateSelector;
        private mCompanySelector: Util.CompanySelector;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("zb_update.do", false);
        private mSubmit: Util.Ajax = new Util.Ajax("zb_submit.do");
        private mSave: Util.Ajax = new Util.Ajax("zb_save.do");
        private mSubmitToDeputy: Util.Ajax = new Util.Ajax("zb_submitToDeputy.do");
        private mTableAssist: JQTable.JQGridAssistant;
        private mTitleCompanyName : string;
        initInstance(opt: IViewOption) {
           
            this.mOpt = opt;
            switch (this.mOpt.entryType) {

                case Util.ZBType.YDJDMJH:
                    this.mDateSelector = new Util.DateSelector(
                            { year: this.mOpt.date.year - 1 }, 
                            this.mOpt.date,
                            this.mOpt.dateId, true);
                    break;
                case Util.ZBType.QNJH:
                case Util.ZBType.BY20YJ:
                case Util.ZBType.BY28YJ:
                case Util.ZBType.BYSJ:
                    this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 1 }, this.mOpt.date, this.mOpt.dateId);
                    break;
            }

            this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
            if (opt.comps.length == 1){
                this.mCompanySelector.hide();
            }
            
            this.updateTitle();
            
            //this.updateUI();
        }
        


        public updateUI() {
            $("#nodatatips").css("display", "none");
            $("#entryarea").css("display", "");
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH){
                date = Util.addMonth(date, -2);
            }
            this.mDataSet.get({ year: date.year, month: date.month, entryType: this.mOpt.entryType, companyId: this.mCompanySelector.getCompany() })
                .then((data: any) => {
                this.mStatusList = data.status;
                this.mTableData = data.values;
                this.updateTitle();
                this.updateTable(this.mOpt.tableId);
                this.updateApproveStatusFromDeputy(date.year, date.month, this.mOpt.entryType);
                $('#save').css("display", "block");
                $('#submit').css("display", "block");
                if(data.isJydw)
                {
                    $('#submitToDeputy').css("display", "block");
                }   
            });
            
        }
        
        public updateApproveStatusFromDeputy(year: number, month: number, entryType: Util.ZBType) {
            //年度计划数经营副总审核状态
            var isShowSubmit_2: boolean = false;
            var isShowapprove_2: boolean = false;
            var approveContent: string = "";
            var unapproveContent: string = "";
            //年度计划数据和实际计划数据
            if ((Util.ZBType.QNJH == entryType || Util.ZBType.BYSJ) && this.mStatusList.length == 1) {
                if (this.mStatusList[0] == Util.ZBStatus.SUBMITTED_2) {
                    isShowSubmit_2 = true;
                    if (Util.ZBType.QNJH == entryType) {
                        unapproveContent = year + "年计划数据";
                    } else {
                        unapproveContent = month + "月实际数据";
                    }
                } else if (this.mStatusList[0] == Util.ZBStatus.APPROVED_2) {
                    isShowapprove_2 = true;
                    if (Util.ZBType.QNJH == entryType) {
                        approveContent = year + "年计划数据";
                    } else {
                        approveContent = month + "月实际数据";
                    }
                }
                this.addContent(isShowapprove_2, isShowSubmit_2, approveContent, unapproveContent);
            }
            
            //月度-季度计划数经营副总审核状态
            if (Util.ZBType.YDJDMJH == entryType && this.mStatusList.length == 3) {
                //Init MatchArray for Month
                var MatchArray: Array<number> = this.initMatchArray(entryType, month, year);
                
                for (var i = 0; i < this.mStatusList.length; i++) {
                    if (this.mStatusList[i] == Util.ZBStatus.SUBMITTED_2) {
                        isShowSubmit_2 = true;
                        unapproveContent += MatchArray[i] + "月,";
                    } else if (this.mStatusList[i] == Util.ZBStatus.APPROVED_2) {
                        isShowapprove_2 = true;
                        approveContent += MatchArray[i] + "月,";
                    } 
                }
                if ("" != approveContent && isShowapprove_2)
                {
                    approveContent = approveContent.substring(0, approveContent.length - 1);
                    approveContent += "计划数据";
                }    
                if ("" != unapproveContent && isShowSubmit_2)
                {
                    unapproveContent = unapproveContent.substring(0, unapproveContent.length - 1);               
                    unapproveContent += "计划数据";
                }
                
                this.addContent(isShowapprove_2, isShowSubmit_2, approveContent, unapproveContent);
            }
            //20号实际数据和28号实际数据
            if ((Util.ZBType.BY20YJ == entryType || Util.ZBType.BY28YJ == entryType ) && this.mStatusList.length > 1) {
                
                var MatchArray: Array<number> = this.initMatchArray(entryType, month, year);
                
                if (month == 12) {
                    for (var i = 0; i < this.mStatusList.length; i++) {
                        if (i == 0) {
                            if (this.mStatusList[0] == Util.ZBStatus.SUBMITTED_2) {
                                isShowSubmit_2 = true;
                                unapproveContent += year + "年" + MatchArray[i] + "月,";
                            } else if (this.mStatusList[0] == Util.ZBStatus.APPROVED_2) {
                                isShowapprove_2 = true;
                                approveContent += year + "年" + MatchArray[i] + "月,";
                            }
                        } else {
                            if (this.mStatusList[i] == Util.ZBStatus.SUBMITTED_2) {
                                isShowSubmit_2 = true;
                                unapproveContent += (year + 1) + "年" + MatchArray[i] + "月,";
                            } else if (this.mStatusList[i] == Util.ZBStatus.APPROVED_2) {
                                isShowapprove_2 = true;
                                approveContent += (year + 1) + "年" + MatchArray[i] + "月,";
                            }
                        }
                    }
                } else {
                    for (var i = 0; i < this.mStatusList.length; i++) {
                        if (this.mStatusList[i] == Util.ZBStatus.SUBMITTED_2) {
                            isShowSubmit_2 = true;
                            approveContent += MatchArray[i] + "月,";

                        } else if (this.mStatusList[i] == Util.ZBStatus.APPROVED_2) {
                            isShowapprove_2 = true;
                            unapproveContent += MatchArray[i] + "月,";
                        } 
                    }
                }
                
                if ((Util.ZBType.BY20YJ == entryType) && (isShowSubmit_2 || isShowapprove_2)) {
                    approveContent += "20号实际数据";
                    unapproveContent += "20号实际数据";
                } else if ((Util.ZBType.BY28YJ == entryType) && (isShowSubmit_2 || isShowapprove_2)){
                    approveContent += "28号实际数据";
                    unapproveContent += "28号实际数据";
                }                
                this.addContent(isShowapprove_2, isShowSubmit_2, approveContent, unapproveContent);
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
                            retArray.push(month + 　i);
                        }
                    }
                    break;
                default:
                    break;

            }
            return retArray;
        }
        
        private addContent(approveMark: boolean, unapproveMark: boolean, approveContent: string, unapproveContent: string)
        {
            var mergecontent: string = "";
            if (approveMark) {
                $('#DeputyApprovementStatus').css("display", "block");
                mergecontent += approveContent + "被经营副总审核!" + "<br/>";
            }

            if (unapproveMark) {
                $('#DeputyApprovementStatus').css("display", "block");
                mergecontent += unapproveContent + "尚未被经营副总审核!";
            }
            if ("" != mergecontent)
            {
              
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
            for (var i = 0; i < allData.length; ++i){
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j){
                    if (j != 1){
                        submitData[i].push(allData[i][j])
                        allData[i][j] = allData[i][j].replace(new RegExp(' ', 'g'), '')
                    }
                }
            }
            
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
            for (var i = 0; i < allData.length; ++i){
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j){
                    if (j != 1){
                        submitData[i].push(allData[i][j])
                        if (allData[i][j].replace(new RegExp(' ', 'g'), '') == ""){
                            Util.MessageBox.tip("有空内容 无法提交")
                            return;
                        }
                    }
                }
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
            for (var i = 0; i < allData.length; ++i){
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j){
                    if (j != 1){
                        submitData[i].push(allData[i][j])
                        allData[i][j] = allData[i][j].replace(new RegExp(' ', 'g'), '')
                    }
                }
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
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH){
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
            
          for (var i = 0; i < this.mTableData.length; ++i){
              for (var j = 2; j < this.mTableData[i].length; ++j){
                  if ("" != this.mTableData[i][j]){
                    this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) + "";
                  }
              }
          }
            
            var data = this.mTableData;
            var lastsel = "";
            var lastcell = "";
            $("#" + name).jqGrid(
                this.mTableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
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
                    rowNum: 150,
                    
                    onSelectCell : (id,nm,tmp,iRow,iCol) =>{
//                       console.log(iRow +', ' + iCol);
                    },
                    
//                    onCellSelect: (ri,ci,tdHtml,e) =>{
//                       console.log(ri +', ' + ci);
//                    },
                    beforeSaveCell :(rowid,cellname,v,iRow,iCol) =>{
                        var ret = parseFloat( v.replace(new RegExp(',', 'g'), ''));
                        if (isNaN (ret)){
                           $.jgrid.jqModal = {
                              width: 290,
                              left : $("#table").offset().left + $("#table").width() / 2 - 290 / 2, 
                              top : $("#table").offset().top + $("#table").height() / 2 - 90};
                           return v;
                        }else{
                           return ret;
                        }
                    },
                    beforeEditCell:(rowid,cellname,v,iRow,iCol)=>{
                        lastsel = iRow; 
                        lastcell = iCol; 
//                        console.log(iRow +', ' + iCol);
                        $("input").attr("disabled",true); 
                    },
                    
                    afterEditCell:(rowid,cellname,v,iRow,iCol)=>{
                        $("input[type=text]").bind("keydown", function(e){
                            if (e.keyCode === 13){
                                setTimeout(function(){
                                    $("#" + name).jqGrid("editCell", iRow + 1, iCol, true);    
                                }, 10);
                            }
                        });
                    },
                    
                    afterSaveCell : ()=>{
                        $("input").attr("disabled",false); 
                        lastsel=""; 
                    },
                    
                    afterRestoreCell : ()=>{
                        $("input").attr("disabled",false); 
                        
                        lastsel=""; 
                    }
//                    ,
//                    afterEditCell:(rowid,cellname,v,iRow,iCol)=>{
//                        lastsel = ""; 
//                        lastcell = ""; 
//                    } 
                }));
            
          
          $('html').bind('click', function(e) { //用于点击其他地方保存正在编辑状态下的行
              if ( lastsel != "" ) { //if a row is selected for edit 
                  if($(e.target).closest("#" + name).length == 0) { //and the click is outside of the grid //save the row being edited and unselect the row  
                      //  $("#" + name).jqGrid('saveRow', lastsel); 
                      $("#" + name).jqGrid("saveCell",lastsel,lastcell);
                      //$("#" + name).resetSelection(); 
                      lastsel=""; 
                  } 
              } 
          });
        }
    }
}