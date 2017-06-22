/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="company_selector.ts" />
/// <reference path="bglx_selector.ts" />
declare var echarts;
declare var $;
module zzy_lrsj_template {

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
    }

    interface IViewOption {
        tableId: string;
        dateId: string;
        date?: Util.Date;
        companyId: string;
        comps?: Array<Util.Dwxx>;
        entryType: String;
    }

    interface ISubmitResult {
        result: string;
    }


    export class View {
        public static instance: View = new View();
        public static getInstance(): View {
            return View.instance;
        }

        private mTableData: Array<string[]>;
        private mDateSelector: Util.DateSelector;
        private mCompanySelector: Util.CompanySelectorZzy;
        private mOpt: IViewOption;
        private mDataSet: Util.Ajax = new Util.Ajax("zb_update.do", false);
        private mSave: Util.Ajax = new Util.Ajax("zb_save.do");
        private mCondition: Util.Ajax = new Util.Ajax("zb_entry.do");
        private mBglxSelector:Util.BglxSelector;
        private mTableAssist: JQTable.JQGridAssistant;
        initInstance(opt: IViewOption) {
            $("#" + opt.dateId).html("");
            $("#" + opt.companyId).html("");
            this.mOpt = opt;
                if(this.mBglxSelector.getBglx() + "" == "10002" || this.mBglxSelector.getBglx() + "" == "10003"){
                    this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId, true);
                } else {
                    this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);
                }
                this.mCompanySelector = new Util.CompanySelectorZzy(this.mOpt.companyId, opt.comps, false,'01');
                if (opt.comps.length == 1){
                    this.mCompanySelector.hide();
                }
                this.updateTitle();
        }
        
        initBglxSelect(divId : string, curbglx : string, view : any, isByq : boolean, isXl : boolean) {
            this.mBglxSelector=new Util.BglxSelector(divId, curbglx, this, isByq, isXl);
       }

        public updateUI() {
            $("#nodatatips").css("display", "none");
            $("#entryarea").css("display", "");
            var date = this.mDateSelector.getDate();
            this.mDataSet.get({ year: date.year, month: date.month, entryType: this.mOpt.entryType, companyId: this.mCompanySelector.getCompany() })
                .then((data: any) => {
                this.mTableData = data.values;
                this.updateTitle();
                this.updateTable(this.mOpt.tableId);
                $('#save').css("display", "block");
            });
            
        }

        public updateEntry(entryType : String) {
            $("#nodatatips").css("display", "block");
            $("#entryarea").css("display", "none");
            $('#save').css("display", "none");
            this.mCondition.get({ entryType: entryType })
                .then((data: any) => {
                if (JSON.parse(data.comps).length == 0) {
                    $('h1').text("没有任何可以录入数据的公司");
                    $('input').css("display", "none");
                    $('#nodatatips').css("display", "none");
                    $('#div_bglx').css("display", "none");
                    return;
                }
                this.initInstance({
                        tableId : "table",
                        dateId: "date",
                        companyId: "company",
                        comps : JSON.parse(data.comps),
                        entryType : entryType,
                        date : {
                            month :data.month == null ? undefined : data.month, 
                            year : data.year
                        }
                    });                    
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
                    if(this.mBglxSelector.getBglx() + "" != "10018"){
                        if (j != 1){
                            submitData[i].push(allData[i][j])
                            allData[i][j] = allData[i][j].replace(new RegExp(' ', 'g'), '')
                        }
                    } else {
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
            if(this.mBglxSelector.getBglx() + "" == "10002" || this.mBglxSelector.getBglx() + "" == "10003"){
                header = date.year + "年" + (date.month / 3) + "季度 " + compName + " " + $("#bglx").find("option:selected").text() + "录入";
            } else {
                header = date.year + "年" + date.month + "月 " + compName + " " + $("#bglx").find("option:selected").text() + "录入";
            }
            
            $('h1').text(header);
            document.title = header;
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
            var date = this.mDateSelector.getDate();
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            this.enableEntry();

            var titles = null;
            switch(this.mBglxSelector.getBglx() + ""){
            case "10001":
                titles = ["产品类型", "收入", "毛利额"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            case "10002":
                titles = ["产品类型", "产值", "产量", "中标毛利率", "预计优化后毛利额", "预计优化后毛利率"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            case "10003":
                titles = ["产品类型", "产值", "中标毛利率", "预计优化后毛利额", "预计优化后毛利率"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            case "10004":
                titles = ["类别", "年度计划", "当月计划", "当月完成"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            case "10005":
                titles = ["产品类型", "生产台数", "优化台数", "产值", "结构参数优化降本", "材料替代降本", "其他降本"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            case "10006":
                titles = ["产品类型", "产值", "结构参数优化降本", "材料替代降本", "其他降本"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            case "10007":
                titles = ["材料", "年度计划", "月度计划", "月度完成"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            case "10008":
                titles = ["材料名称", "实际领用量", "废料"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            case "10009":
            case "10010":
                titles = ["", "水用量", "水金额", "电用量", "电金额", "蒸汽用量", "蒸汽金额", "燃气用量", "燃气金额"];
                this.mTableAssist = new JQTable.JQGridAssistant([
                new JQTable.Node("", "empty", true, JQTable.TextAlign.Left),
                new JQTable.Node("水（吨）", "sd", false)
                    .append(new JQTable.Node("用量", "syl", false))
                    .append(new JQTable.Node("金额（元）", "sje", false)),
                new JQTable.Node("电（度）", "dd")
                    .append(new JQTable.Node("用量", "dyl", false))
                    .append(new JQTable.Node("金额（元）", "dje", false)),
                new JQTable.Node("蒸汽（立方米）", "zqlfm")
                    .append(new JQTable.Node("用量", "zqyl", false))
                    .append(new JQTable.Node("金额（元）", "zqje", false)),
                new JQTable.Node("燃气（线缆）（立方米）", "rqxllfm")
                    .append(new JQTable.Node("用量", "rqxlyl", false))
                    .append(new JQTable.Node("金额（元）", "rqxlje", false)),
                new JQTable.Node("产值", "cz", false),
                new JQTable.Node("产量", "cl", false)
                ], name);
                break;
            case "10011":
                titles = ["年度", "调差", "调配置/标准", "优化付款方式", "取消客户指定", "其他"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            case "10012":
                this.mTableAssist = new JQTable.JQGridAssistant([
                new JQTable.Node("产品类别", "cplb", true, JQTable.TextAlign.Left),
                new JQTable.Node("产量(万KVA)", "cl", false)
                    .append(new JQTable.Node("当月产量", "dycl", false)),
                new JQTable.Node("产值(不含税)", "cz")
                    .append(new JQTable.Node("当月总产值", "dyzcz", false))
                ], name);
                titles = ["产品类别", "铜铝当量（吨）", "产值"];
                break;
            case "10013":
                this.mTableAssist = new JQTable.JQGridAssistant([
                new JQTable.Node("产品类别", "cplb", true, JQTable.TextAlign.Left),
                new JQTable.Node("铜铝当量（吨）", "cl", false)
                    .append(new JQTable.Node("当月产量", "dycl", false)),
                new JQTable.Node("产值(不含税)", "cz")
                    .append(new JQTable.Node("当月产值", "dyzcz", false))
                ], name);
                titles = ["产品类别", "铜铝当量（吨）", "产值"];
                break;
            case "10014":
                titles = ["产品类别", "工时"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            case "10015":
                titles = ["单位", "月产出能力产值", "月产出能力产量", "可供履约订单总产值", "可供履约订单总产量", 
                        "年度可供履约订单总产值", "年度可供履约订单总产量", "n+1月产值(已排产)", "n+1月产值(未排产)", "n+1月产量", 
                        "n+2月产值(已排产)", "n+2月产值(未排产)", "n+2月产量", "n+3月产值(已排产)", "n+3月产值(未排产)", 
                        "n+3月产量", "n+4月产值", "n+4月产量", "n+5月产值", "n+5月产量", 
                        "n+6月产值", "n+6月产量", "n+6月后可供履约订单产值", "n+6月后可供履约订单产量", "n+3月后可供履约订单产值", 
                        "待定产值", "待定产量", "外协产值", "外协产量"];
                this.mTableAssist = new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dwid", true, JQTable.TextAlign.Left),
                new JQTable.Node("月产出能力", "yscnl")
                //  .append(new JQTable.Node("", "yscnl1")
                    .append(new JQTable.Node("产值", "yccnlcz", false))
                    .append(new JQTable.Node("产量", "yccnlcl", false)),
                new JQTable.Node("所有可供履约订单总量", "yy")
                //                    .append(new JQTable.Node("", "yy1")
                    .append(new JQTable.Node("产值", "kglyddzcz", false))
                    .append(new JQTable.Node("产量", "kglyddzcl", false)),
                new JQTable.Node("可供履约订单（不含税）", "yd")
                    .append(new JQTable.Node(date.year + "年可供履约订单总量", "yd0")
                    .append(new JQTable.Node("产值", "ndkglyddzcz", false))
                    .append(new JQTable.Node("产量", "ndkglyddzcl", false)))
                    .append(new JQTable.Node("n+1月", "yd1")
                    .append(new JQTable.Node("产值", "n1cz", false))
                    .append(new JQTable.Node("产量", "n1cl", false)))
                    .append(new JQTable.Node("n+2月", "yd2")
                    .append(new JQTable.Node("产值", "n2cz", false))
                    .append(new JQTable.Node("产量", "n2cl", false)))
                    .append(new JQTable.Node("n+3月", "yd3")
                    .append(new JQTable.Node("产值", "n3cz", false))
                    .append(new JQTable.Node("产量", "n3cl", false)))
                    .append(new JQTable.Node("n+4月", "yd4")
                    .append(new JQTable.Node("产值", "n4cz", false))
                    .append(new JQTable.Node("产量", "n4cl", false)))
                    .append(new JQTable.Node("n+5月", "yd5")
                    .append(new JQTable.Node("产值", "n5cz", false))
                    .append(new JQTable.Node("产量", "n5cl", false)))
                    .append(new JQTable.Node("n+6月", "yd6")
                    .append(new JQTable.Node("产值", "n6cz", false))
                    .append(new JQTable.Node("产量", "n6cl", false))),
                new JQTable.Node("n+6月以后可供履约订单", "hyd")
                //                    .append(new JQTable.Node("", "hyd1")
                    .append(new JQTable.Node("产值", "n6hcz", false))
                    .append(new JQTable.Node("产量", "n6hcl", false)),
                new JQTable.Node("交货期待定", "dd")
                //                    .append(new JQTable.Node("", "dd1")
                    .append(new JQTable.Node("产值", "ddcl", false))
                    .append(new JQTable.Node("产量", "ddcz", false))
            ], name);
                break;
            case "10016":
                titles = ["单位", "月产出能力产值", "月产出能力产量", "可供履约订单总产值", "可供履约订单总产量", 
                        "年度可供履约订单总产值", "年度可供履约订单总产量", "n+1月产值(已排产)", "n+1月产值(未排产)", "n+1月产量", 
                        "n+2月产值(已排产)", "n+2月产值(未排产)", "n+2月产量", "n+3月产值(已排产)", "n+3月产值(未排产)", 
                        "n+3月产量", "n+4月产值", "n+4月产量", "n+5月产值", "n+5月产量", 
                        "n+6月产值", "n+6月产量", "n+6月后可供履约订单产值", "n+6月后可供履约订单产量", 
                        "n+3月后可供履约订单产值", "待定产值", "待定产量", "外协产值", "外协产量"];
                this.mTableAssist = new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dwid", true, JQTable.TextAlign.Left),
                new JQTable.Node("月产出能力（产值）", "yccnlcz", false),
                new JQTable.Node("未履约订单总额", "yy", false),
                new JQTable.Node("可供履约订单（不含税）", "yd")
                    .append(new JQTable.Node("本年度未履约订单总量", "yd0", false))
                    .append(new JQTable.Node("n+1月订单量", "yd1", false)
                    .append(new JQTable.Node("已排产", "n1cz", false))
                    .append(new JQTable.Node("未排产", "n1czn", false)))
                    .append(new JQTable.Node("n+2月订单量", "yd2", false)
                    .append(new JQTable.Node("已排产", "n2cz", false))
                        .append(new JQTable.Node("未排产", "n2czn", false)))
                    .append(new JQTable.Node("n+3月订单量", "yd3", false)
                    .append(new JQTable.Node("已排产", "n3cz", false))
                        .append(new JQTable.Node("未排产", "n3czn", false)))
                    .append(new JQTable.Node("n+3月及以后履约订单", "yd4", false)),
                new JQTable.Node("交货期待定", "ddcz", false),
                new JQTable.Node("外协", "wxcz", false)
            ], name);
                break;
            case "10017":
                titles = ["时点", "原材料", "半成品", "实际库存商品", "已发货未开票", "期货浮动盈亏(盈+，亏-)", "期货平仓盈亏(盈-，亏+)", "未发货已开票", "其他", "合计"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            case "10018":
                titles = ["原材料", "", "5年以上", "5年以上", "5年以上", "原材料", "4-5年半成品", "4-5年产成品", "4-5年其他", "3-4年原材料", "3-4年半成品", "3-4年产成品", "3-4年其他", "2-3年原材料", "2-3年半成品", 
                        "2-3年产成品", "2-3年其他", "1-2年原材料", "1-2年半成品", "1-2年产成品", "1-2年其他", "1年原材料", "1年半成品", "1年产成品", "1年其他", "合计"];
                this.mTableAssist = new JQTable.JQGridAssistant([
                    new JQTable.Node("5年以上", "n5s", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("原材料", "n5sycl", false))
                        .append(new JQTable.Node("半成品", "n5sbcp", false))
                        .append(new JQTable.Node("产成品", "n5sccp", false))
                        .append(new JQTable.Node("其他", "n5sqt", false)),
                    new JQTable.Node("4-5年", "n4z5", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("原材料", "n4z5ycl", false))
                        .append(new JQTable.Node("半成品", "n4z5bcp", false))
                        .append(new JQTable.Node("产成品", "n4z5ccp", false))
                        .append(new JQTable.Node("其他", "n4z5qt", false)),
                    new JQTable.Node("3-4年", "n3z4", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("原材料", "n3z4ycl", false))
                        .append(new JQTable.Node("半成品", "n3z4bcp", false))
                        .append(new JQTable.Node("产成品", "n3z4ccp", false))
                        .append(new JQTable.Node("其他", "n3z4qt", false)),
                    new JQTable.Node("2-3年", "n2z3", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("原材料", "n2z3ycl", false))
                        .append(new JQTable.Node("半成品", "n2z3bcp", false))
                        .append(new JQTable.Node("产成品", "n2z3ccp", false))
                        .append(new JQTable.Node("其他", "n2z3qt", false)),
                    new JQTable.Node("1-2年", "n1z2", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("原材料", "n1z2ycl", false))
                        .append(new JQTable.Node("半成品", "n1z2bcp", false))
                        .append(new JQTable.Node("产成品", "n1z2ccp", false))
                        .append(new JQTable.Node("其他", "n1z2qt", false)),
                    new JQTable.Node("1年以内", "n1", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("原材料", "n1ycl", false))
                        .append(new JQTable.Node("半成品", "n1bcp", false))
                        .append(new JQTable.Node("产成品", "n1ccp", false))
                        .append(new JQTable.Node("其他", "n1qt", false)),
                    new JQTable.Node("合计", "wxcz", false)
                ], name);
                break;
            case "10019":
                titles = ["单位名称", "截止月底库存金额", "年初库存金额"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            case "10020":
                titles = ["细项", "年度计划费用率"];
                this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles);
                break;
            }
            
          for (var i = 0; i < this.mTableData.length; ++i){
              var j = 2;
              if(this.mBglxSelector.getBglx() + "" == "10018"){
                j = 1;
              }
              for (; j < this.mTableData[i].length; ++j){
                  if ("" != this.mTableData[i][j]){
                    this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) + "";
                  }
              }
          }
            
            var data = this.mTableData;
            var lastsel = "";
            var lastcell = "";
            var len = 500;
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
                    width: titles.length < 5 ? titles.length * 200 : (titles.length < 7 ? titles.length * 150 : (titles.length < 12 ? titles.length * 100 : titles.length * 80)),
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