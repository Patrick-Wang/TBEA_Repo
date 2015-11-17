/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../companySelector.ts" />
/// <reference path="bglx_selector.ts" />
declare var echarts;
declare var $;
module fx_cpylsp_hqlyddzl {

    class JQGridAssistantFactory {
        public static createTable(gridName: string): JQTable.JQGridAssistant {
            /**
            var nodes = [];
            var titles =["产品类型", "产值", "产量", "中标毛利率", "预计优化后毛利率", "预计优化后毛利额"]; 
            for (var i = 0; i < titles.length; ++i) { 
                if (i == 0) {
                    nodes.push(new JQTable.Node(titles[i], "_" + i, true, JQTable.TextAlign.Left));
                } else {
                    nodes.push(new JQTable.Node(titles[i], "_" + i,false));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
            **/            
            return new JQTable.JQGridAssistant([
                new JQTable.Node("产品类型", "_0", true, JQTable.TextAlign.Left),
                new JQTable.Node("产值", "_1"),
                new JQTable.Node("1层", "1c").append(new JQTable.Node("2层1", "2c1").append(new JQTable.Node("产量", "_2")).append(new JQTable.Node("中标毛利率", "_3")))
                .append(new JQTable.Node("2层2", "2c2").append(new JQTable.Node("预计优化后毛利率", "_4")).append(new JQTable.Node("预计优化后毛利额", "_5")))               
            ], gridName);         
        }
    }

    interface IViewOption {
        tableId: string;
        dateId: string;
        date?: Util.Date;
        companyId: string;
        comps: Util.IDataNode[];
        bglxId:string;
        bglxs: Util.Bglx[];
        curbglx:string;
        writeorview:string;
    }

    interface ISubmitResult {
        result: string;
    }


    export class View {
        private static instance: View;

        public static getInstance(): View {
            if (View.instance == undefined) {
                View.instance = new View();
            }
            return View.instance;
        }

        private mTableData: Array<string[]>;
        private mDateSelector: Util.DateSelector;
        private mCompanySelector: Util.CompanySelector;
        private mBglxSelector:Util.BglxSelector;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("read.do", false);
        private mSave: Util.Ajax = new Util.Ajax("save.do");
        private mTableAssist: JQTable.JQGridAssistant;
        private mTitleCompanyName : string;
        initInstance(opt: IViewOption) {           
            this.mOpt = opt;            
            this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);
            this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
            this.mBglxSelector=new Util.BglxSelector(opt.bglxId,opt.bglxs,opt.curbglx,opt.writeorview);
            if (opt.comps.length == 1){
                this.mCompanySelector.hide();
            }            
            this.updateTitle(); 
        }
        


        public read() {
            $("#nodatatips").css("display", "none");
            $("#entryarea").css("display", "");
            var date = this.mDateSelector.getDate();            
            this.mDataSet.get({ year: date.year, month: date.month, companyId: this.mCompanySelector.getCompany() })
                .then((data: any) => {
                this.mTableData = data.values;
                this.updateTitle();
                this.updateTable(this.mOpt.tableId);
                $('#save').css("display", "block");                
            });
            
        }
        public save() {
            var date = this.mDateSelector.getDate();            
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
       
        private updateTitle() {
            var header = "";
            var date = this.mDateSelector.getDate();
            var compName = this.mCompanySelector.getCompanyName();            
            header = date.year + "年" + date.month + "月 " + compName + " 制造业指标情况数据录入";   
            $('h1').text(header);
            document.title = header;
        }

        private updateTable(tableId: string): void {            
            var name = tableId + "_jqgrid";
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");            
                    
            this.mTableAssist = JQGridAssistantFactory.createTable(name);            
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
                    width: 1000,
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