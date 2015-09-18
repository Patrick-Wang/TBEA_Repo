/// <reference path="util.ts" />
/// <reference path="jqgrid/jqassist.ts" />
declare var echarts;
module mkt_view_data {

    class JQGridAssistantFactory {

        public static createBidTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "t0", true, JQTable.TextAlign.Left),
                new JQTable.Node("投标编号", "t1", false, JQTable.TextAlign.Left),
                new JQTable.Node("项目信息编号", "t2", false, JQTable.TextAlign.Left),
                new JQTable.Node("授权编号", "t3", false, JQTable.TextAlign.Left),
                new JQTable.Node("办事处或项目部", "t4", false, JQTable.TextAlign.Left),
                new JQTable.Node("投标月份", "t5", false, JQTable.TextAlign.Left, 0
                ,"select", {value:"1:1;2:2;3:3;4:4;5:5;6:6;7:7;8:8;9:9;10:10;11:11;12:12"}),
                new JQTable.Node("投标日期", "t6", false, JQTable.TextAlign.Left),
                new JQTable.Node("所属行业", "t7", false, JQTable.TextAlign.Left, 0
                    ,"select", 
                    {value:"国网:国网;南网:南网;火电:火电;水电:水电;风电:风电;核电:核电;光伏:光伏;轨道交通:轨道交通;石油石化:石油石化;钢铁冶金:钢铁冶金;煤炭煤化工:煤炭煤化工;航天军工:航天军工;其他:其他"}),
                new JQTable.Node("所属系统", "t8", false, JQTable.TextAlign.Left, 0
                    ,"select",
                    {value:"国网公司:国网公司;南网公司:南网公司;内蒙电力:内蒙电力;兵团电力:兵团电力;华能集团:华能集团;华电集团:华电集团;大唐集团:大唐集团;中电投集团:中电投集团;国电集团:国电集团;华润集团:华润集团;神华集团:神华集团;国投集团:国投集团;京能集团:京能集团;中煤能源集团:中煤能源集团;中国风电集团:中国风电集团;中国电建集团:中国电建集团;中国能建集团:中国能建集团;三峡集团:三峡集团;粤电集团:粤电集团;浙能集团:浙能集团;深圳能源集团:深圳能源集团;申能集团:申能集团;福建能源集团:福建能源集团;山东鲁能集团:山东鲁能集团;中核集团:中核集团;中广核集团:中广核集团;中铁电气化局:中铁电气化局;中铁建电气化局:中铁建电气化局;中石油:中石油;中石化:中石化;中海油:中海油;其他:其他"}),
                new JQTable.Node("项目所在区域", "t9", false, JQTable.TextAlign.Left,0
                    ,"select",
                    {value:"北京:北京;天津:天津;河北:河北;山西:山西;内蒙:内蒙;辽宁:辽宁;吉林:吉林;黑龙江:黑龙江;上海:上海;江苏:江苏;浙江:浙江;安徽:安徽;福建:福建;江西:江西;山东:山东;河南:河南;湖北:湖北;湖南:湖南;广东:广东;广西:广西;海南:海南;重庆:重庆;四川:四川;贵州:贵州;云南:云南;西藏:西藏;陕西:陕西;甘肃:甘肃;青海:青海;宁夏:宁夏;新疆:新疆;台湾:台湾;香港:香港;澳门:澳门;国际外省:国际外省"}),
                new JQTable.Node("项目名称", "t10", false, JQTable.TextAlign.Left),
                new JQTable.Node("业主单位", "t11", false, JQTable.TextAlign.Left),
                new JQTable.Node("产品型号", "t12", false, JQTable.TextAlign.Left),
                new JQTable.Node("电压等级", "t13", false, JQTable.TextAlign.Left),
                new JQTable.Node("投标数量", "t14", false, JQTable.TextAlign.Left),
                new JQTable.Node("投标容量(kVA)", "t15", false, JQTable.TextAlign.Left),
                new JQTable.Node("我厂投标价格", "t16", false, JQTable.TextAlign.Left),
                new JQTable.Node("中标厂家", "t17", false, JQTable.TextAlign.Left),
                new JQTable.Node("中标价格", "t18", false, JQTable.TextAlign.Left),
                new JQTable.Node("中标或未中标原因分析", "t19", false, JQTable.TextAlign.Left),
                new JQTable.Node("定标月份", "t20", false, JQTable.TextAlign.Left, 0, "select",
                {value:"1:1;2:2;3:3;4:4;5:5;6:6;7:7;8:8;9:9;10:10;11:11;12:12"}),
                new JQTable.Node("状态", "t21", false, JQTable.TextAlign.Left,0
                ,"select", {value:"中标:中标;失标:失标;未定标:未定标"}),
                new JQTable.Node("是否反馈投标总结", "t22", false, JQTable.TextAlign.Left,0,"select",{value:"是:是;否:否"}),
                new JQTable.Node("具体投标单位", "t23", false, JQTable.TextAlign.Left)
            ], gridName);
        }

        public static createPrjTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "t0", true, JQTable.TextAlign.Left),
                new JQTable.Node("办事处名称", "t1", false, JQTable.TextAlign.Left),
                new JQTable.Node("项目序号", "t2", false, JQTable.TextAlign.Left),
                new JQTable.Node("所属行业", "t3", false, JQTable.TextAlign.Left, 0
                    ,"select", 
                    {value:"国网:国网;南网:南网;火电:火电;水电:水电;风电:风电;核电:核电;光伏:光伏;轨道交通:轨道交通;石油石化:石油石化;钢铁冶金:钢铁冶金;煤炭煤化工:煤炭煤化工;航天军工:航天军工;其他:其他"}),
                new JQTable.Node("所属系统", "t4", false, JQTable.TextAlign.Left, 0
                    ,"select",
                    {value:"国网公司:国网公司;南网公司:南网公司;内蒙电力:内蒙电力;兵团电力:兵团电力;华能集团:华能集团;华电集团:华电集团;大唐集团:大唐集团;中电投集团:中电投集团;国电集团:国电集团;华润集团:华润集团;神华集团:神华集团;国投集团:国投集团;京能集团:京能集团;中煤能源集团:中煤能源集团;中国风电集团:中国风电集团;中国电建集团:中国电建集团;中国能建集团:中国能建集团;三峡集团:三峡集团;粤电集团:粤电集团;浙能集团:浙能集团;深圳能源集团:深圳能源集团;申能集团:申能集团;福建能源集团:福建能源集团;山东鲁能集团:山东鲁能集团;中核集团:中核集团;中广核集团:中广核集团;中铁电气化局:中铁电气化局;中铁建电气化局:中铁建电气化局;中石油:中石油;中石化:中石化;中海油:中海油;其他:其他"}),
                new JQTable.Node("项目名称", "t5", false, JQTable.TextAlign.Left),
                new JQTable.Node("业主单位", "t6", false, JQTable.TextAlign.Left),
                new JQTable.Node("产品型号", "t7", false, JQTable.TextAlign.Left),
                new JQTable.Node("数量", "t8", false, JQTable.TextAlign.Left),
                new JQTable.Node("预计投标金额", "t9", false, JQTable.TextAlign.Left),
                new JQTable.Node("预计招标时间", "t10", false, JQTable.TextAlign.Left),
                new JQTable.Node("项目所在区域", "t11", false, JQTable.TextAlign.Left,0
                    ,"select",
                    {value:"北京:北京;天津:天津;河北:河北;山西:山西;内蒙:内蒙;辽宁:辽宁;吉林:吉林;黑龙江:黑龙江;上海:上海;江苏:江苏;浙江:浙江;安徽:安徽;福建:福建;江西:江西;山东:山东;河南:河南;湖北:湖北;湖南:湖南;广东:广东;广西:广西;海南:海南;重庆:重庆;四川:四川;贵州:贵州;云南:云南;西藏:西藏;陕西:陕西;甘肃:甘肃;青海:青海;宁夏:宁夏;新疆:新疆;台湾:台湾;香港:香港;澳门:澳门;国际外省:国际外省"}),
                new JQTable.Node("项目简介", "t12", false, JQTable.TextAlign.Left),
                new JQTable.Node("目前推进跟踪情况及后期计划", "t13", false, JQTable.TextAlign.Left),
                new JQTable.Node("本单位项目负责人及联系方式", "t14", false, JQTable.TextAlign.Left),
                new JQTable.Node("本单位负责该项目的主管领导", "t15", false, JQTable.TextAlign.Left),
                new JQTable.Node("跟踪该项目的其它内部企业名称", "t16", false, JQTable.TextAlign.Left),
                new JQTable.Node("投标限制", "t17", false, JQTable.TextAlign.Left,0,"select",{value:"无限制:无限制;允许兄弟企业参与投标:允许兄弟企业参与投标;仅允许一家参与投标:仅允许一家参与投标"}),
                new JQTable.Node("投标情况", "t18", false, JQTable.TextAlign.Left,0, 
                "select",{value:"已投标:已投标;已报价:已报价;放弃跟踪:放弃跟踪;弃标:弃标;项目重复:项目重复;正在跟踪:正在跟踪;未招标结束:未招标结束"}),
                new JQTable.Node("备注", "t19", false, JQTable.TextAlign.Left)
            ], gridName);
        }

        public static createContTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "t0", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同编号", "t1", false, JQTable.TextAlign.Left),
                new JQTable.Node("办事处或项目部", "t2", false, JQTable.TextAlign.Left),
                new JQTable.Node("签约月份", "t3", false, JQTable.TextAlign.Left),
                new JQTable.Node("所属行业", "t4", false, JQTable.TextAlign.Left, 0
                    ,"select", 
                    {value:"国网:国网;南网:南网;火电:火电;水电:水电;风电:风电;核电:核电;光伏:光伏;轨道交通:轨道交通;石油石化:石油石化;钢铁冶金:钢铁冶金;煤炭煤化工:煤炭煤化工;航天军工:航天军工;其他:其他"}),
                new JQTable.Node("所属系统", "t5", false, JQTable.TextAlign.Left, 0
                    ,"select",
                    {value:"国网公司:国网公司;南网公司:南网公司;内蒙电力:内蒙电力;兵团电力:兵团电力;华能集团:华能集团;华电集团:华电集团;大唐集团:大唐集团;中电投集团:中电投集团;国电集团:国电集团;华润集团:华润集团;神华集团:神华集团;国投集团:国投集团;京能集团:京能集团;中煤能源集团:中煤能源集团;中国风电集团:中国风电集团;中国电建集团:中国电建集团;中国能建集团:中国能建集团;三峡集团:三峡集团;粤电集团:粤电集团;浙能集团:浙能集团;深圳能源集团:深圳能源集团;申能集团:申能集团;福建能源集团:福建能源集团;山东鲁能集团:山东鲁能集团;中核集团:中核集团;中广核集团:中广核集团;中铁电气化局:中铁电气化局;中铁建电气化局:中铁建电气化局;中石油:中石油;中石化:中石化;中海油:中海油;其他:其他"}),
                new JQTable.Node("项目所在区域", "t6", false, JQTable.TextAlign.Left,0
                    ,"select",
                    {value:"北京:北京;天津:天津;河北:河北;山西:山西;内蒙:内蒙;辽宁:辽宁;吉林:吉林;黑龙江:黑龙江;上海:上海;江苏:江苏;浙江:浙江;安徽:安徽;福建:福建;江西:江西;山东:山东;河南:河南;湖北:湖北;湖南:湖南;广东:广东;广西:广西;海南:海南;重庆:重庆;四川:四川;贵州:贵州;云南:云南;西藏:西藏;陕西:陕西;甘肃:甘肃;青海:青海;宁夏:宁夏;新疆:新疆;台湾:台湾;香港:香港;澳门:澳门;国际外省:国际外省"}),
                new JQTable.Node("项目名称", "t7", false, JQTable.TextAlign.Left),
                new JQTable.Node("业主单位", "t8", false, JQTable.TextAlign.Left),
                new JQTable.Node("产品型号/类型", "t9", false, JQTable.TextAlign.Left),
                new JQTable.Node("电压等级", "t10", false, JQTable.TextAlign.Left),
                new JQTable.Node("数量（台）", "t11", false, JQTable.TextAlign.Left),
                new JQTable.Node("签约容量(kVA)", "t12", false, JQTable.TextAlign.Left),
                new JQTable.Node("签约金额", "t13", false, JQTable.TextAlign.Left),
                new JQTable.Node("付款方式", "t14", false, JQTable.TextAlign.Left),
                new JQTable.Node("签订人", "t15", false, JQTable.TextAlign.Left),
                new JQTable.Node("具体签约单位", "t16", false, JQTable.TextAlign.Left),
                new JQTable.Node("是否现款现货", "t17", false, JQTable.TextAlign.Left,0,"select",{value:"是:是;否:否"}),
                new JQTable.Node("是否制造服务业", "t18", false, JQTable.TextAlign.Left,0,"select",{value:"是:是;否:否"})
            ], gridName);
        }

    }
    
    
  
            
    interface ISubmitResult {
        result: string;
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }
        private mDataSet: Util.Ajax;
        private mSaveDataSet:Util.Ajax;
        private mExportDataSet:Util.Ajax;
        private mCompanyName;
        private mDocType;
        TableId: string;
        childTableId: string;

        public init(TableId: string, companyName:string ): void {
            this.mCompanyName = companyName;
            this.TableId = TableId;
            this.childTableId = TableId + "_jqgrid_1234";
            //请求数据
            this.mDataSet = new Util.Ajax("mkt_view_update.do");
            this.mSaveDataSet = new Util.Ajax("mkt_entry_data.do");
            this.onDoc_TypeSelected();
            //this.onCompanySelected();
            //this.updateUI();
        }

        public onDoc_TypeSelected() {
            this.mDocType = $("#rpttype").val();
            $("#assist").css("display","none");
        }

        public onCompanySelected() {
            this.mCompanyName = $("#comp_category").val();
        }
        
          public exportExcel() {
            $("#exportMarketData")[0].action = "mkt_view_export.do?" + Util.Ajax.toUrlParam({ mktType: this.mDocType });
            $("#exportMarketData")[0].submit();
          }
        
        public saveData(){
              var submitData: string[][] = [];
              for(var row = 0; row <　$("#" + this.childTableId)[0].p.data.length; row++){
                  //var index = 0;
                  submitData.push([]);
                  for(var col in $("#" + this.childTableId)[0].p.data[row]){
                      submitData[row].push($("#" + this.childTableId)[0].p.data[row][col]);
                  }
              }
              this.mSaveDataSet.post({
                  mktType: this.mDocType,
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

        public updateUI() {
            //删去历史内容
            var parent = $("#" + this.TableId);
            parent.empty();
            parent.append("<table id='" + this.childTableId + "'></table>" + "<div id='pager'></div>");

            this.mDataSet.get({docType: this.mDocType })
                .then((data: any) => {
                    var fktjData = data;

                    $('#dataStatus').css("display", "none");
                    if (this.mDocType == 3) {
                        this.updateTable(
                            this.TableId,
                            this.childTableId,
                            JQGridAssistantFactory.createBidTable(this.childTableId),
                            fktjData[0]);
                    } else if (this.mDocType == 2) {
                        this.updateTable(
                            this.TableId,
                            this.childTableId,
                            JQGridAssistantFactory.createPrjTable(this.childTableId),
                            fktjData[0]);
                    } else if (this.mDocType == 4) {
                        this.updateTable(
                            this.TableId,
                            this.childTableId,
                            JQGridAssistantFactory.createContTable(this.childTableId),
                            fktjData[0]);
                    }

                    var title = this.mCompanyName +"市场部"+ $("#rpttype option:selected").text();
                    $('h1').text(title);
                    document.title = title;
                });
        }

        private updateTable(
            parentName: string,
            childName: string,
            tableAssist: JQTable.JQGridAssistant,
            rawData: Array<string[]>): void {

            $("#" + childName).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(rawData),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    // autowidth : false,
                    //cellsubmit: 'clientArray',
                    //cellEdit: true,
                    editurl: 'clientArray',
                    height: '100%',
                    width: $(document).width() - 60,
                    shrinkToFit: true,
                    autoScroll: true,
                    pager: '#pager',
                    rowNum: 20,
                    viewrecords: true//是否显示行数 

                })
            );
            if (rawData.length != 0){
                $("#assist").css("display","block");
            }
            
            $("#" + childName).bind("jqGridAddEditAfterSubmit", (o, resp, data, d4) => {
                if (d4 == "add") {
                    data.t0 = this.mCompanyName;
                    $("#" + childName).addRowData($("#" + childName)[0].p.data.length + 1, data, 'last');
                    $("#pager input.ui-pg-input").val($('input.ui-pg-input').next().text());
                    var e = $.Event("keypress");
                    e.keyCode = 13;
                    $("#pager input.ui-pg-input").trigger(e);
                } else if (d4 == "edit") {
                    var page = $("#" + childName).jqGrid('getGridParam', 'page');
                    var selectid = parseInt($("#" + childName).jqGrid('getGridParam', 'selrow'));
                    var rownum = $("#" + childName).jqGrid('getGridParam', 'rowNum');
                    var acRowid = ((page - 1) * rownum) + selectid;
                    //$("#" + name).jqGrid('saveRow', $("#" + name)[0].p.data.length);
                    //$("#" + name)[0].p.data.id = acRowid;
                    //data.id = acRowid + "";
                    //data.id = undefined;
                    function swap(arr, a, b) {
                        var tmp = arr[a];
                        arr[a] = arr[b];
                        arr[b] = tmp;
                    }
                    swap($("#" + childName)[0].p.data, selectid - 1, acRowid - 1);
                    $("#" + childName).setRowData(selectid, data);
                    swap($("#" + childName)[0].p.data, selectid - 1, acRowid - 1);
                    //$("#" + name).jqGrid("setRowData", acRowid-1, data);
                    //$("#" + name).setRowData
                }
            });
            
             if (this.mCompanyName == "股份公司市场部") {
                $("#" + childName).jqGrid('navGrid', '#pager', { del: false, add: false, edit: false }, {}, {}, {}, { multipleSearch: true });
            } else {
                $("#" + childName).jqGrid('navGrid', '#pager', { del: false, add: true, edit: true }, {}, {}, {}, { multipleSearch: true });
            }

        }
    }
}