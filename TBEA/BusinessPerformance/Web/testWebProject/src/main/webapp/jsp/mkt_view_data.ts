declare var echarts;
module mkt_view_data {

    class JQGridAssistantFactory {

        public static createBidTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "t0",true,JQTable.TextAlign.Left),
                new JQTable.Node("投标编号", "t1",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目信息编号", "t2",true,JQTable.TextAlign.Left),
                new JQTable.Node("授权编号", "t3",true,JQTable.TextAlign.Left),
                new JQTable.Node("办事处或项目部", "t4",true,JQTable.TextAlign.Left),
                new JQTable.Node("投标月份", "t5",true,JQTable.TextAlign.Left),
                new JQTable.Node("投标日期", "t6",true,JQTable.TextAlign.Left),
                new JQTable.Node("所属行业", "t7",true,JQTable.TextAlign.Left),
                new JQTable.Node("所属系统", "t8",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目所在区域", "t9",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目名称", "t10",true,JQTable.TextAlign.Left),
                new JQTable.Node("业主单位", "t11",true,JQTable.TextAlign.Left),
                new JQTable.Node("产品型号", "t12",true,JQTable.TextAlign.Left),
                new JQTable.Node("电压等级", "t13",true,JQTable.TextAlign.Left),
                new JQTable.Node("投标数量", "t14",true,JQTable.TextAlign.Left),
                new JQTable.Node("投标容量(kVA)", "t15",true,JQTable.TextAlign.Left),
                new JQTable.Node("我厂投标价格", "t16",true,JQTable.TextAlign.Left),
                new JQTable.Node("中标厂家", "t17",true,JQTable.TextAlign.Left),
                new JQTable.Node("中标价格", "t18",true,JQTable.TextAlign.Left),
                new JQTable.Node("中标或未中标原因分析", "t19",true,JQTable.TextAlign.Left),
                new JQTable.Node("定标月份", "t20",true,JQTable.TextAlign.Left),
                new JQTable.Node("状态", "t21",true,JQTable.TextAlign.Left),
                new JQTable.Node("是否反馈投标总结", "t22",true,JQTable.TextAlign.Left),
                new JQTable.Node("具体投标单位", "t23",true,JQTable.TextAlign.Left)
            ], gridName);
        }
        
        public static createPrjTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "t0",true,JQTable.TextAlign.Left),
                new JQTable.Node("办事处名称", "t1",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目序号", "t2",true,JQTable.TextAlign.Left),
                new JQTable.Node("所属行业", "t3",true,JQTable.TextAlign.Left),
                new JQTable.Node("所属系统", "t4",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目名称", "t5",true,JQTable.TextAlign.Left),
                new JQTable.Node("业主单位", "t6",true,JQTable.TextAlign.Left),
                new JQTable.Node("产品型号", "t7",true,JQTable.TextAlign.Left),
                new JQTable.Node("数量", "t8",true,JQTable.TextAlign.Left),
                new JQTable.Node("预计投标金额", "t9",true,JQTable.TextAlign.Left),
                new JQTable.Node("预计招标时间", "t10",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目所在区域", "t11",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目简介", "t12",true,JQTable.TextAlign.Left),
                new JQTable.Node("目前推进跟踪情况及后期计划", "t13",true,JQTable.TextAlign.Left),
                new JQTable.Node("本单位项目负责人及联系方式", "t14",true,JQTable.TextAlign.Left),
                new JQTable.Node("本单位负责该项目的主管领导", "t15",true,JQTable.TextAlign.Left),
                new JQTable.Node("跟踪该项目的其它内部企业名称", "t16",true,JQTable.TextAlign.Left),
                new JQTable.Node("投标情况", "t17",true,JQTable.TextAlign.Left),
                new JQTable.Node("备注", "t18",true,JQTable.TextAlign.Left)
            ], gridName);
        }
        
        public static createContTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "t0",true,JQTable.TextAlign.Left),
                new JQTable.Node("合同编号", "t1",true,JQTable.TextAlign.Left),
                new JQTable.Node("办事处或项目部", "t2",true,JQTable.TextAlign.Left),
                new JQTable.Node("签约月份", "t3",true,JQTable.TextAlign.Left),
                new JQTable.Node("所属行业", "t4",true,JQTable.TextAlign.Left),
                new JQTable.Node("所属系统", "t5",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目所在区域", "t6",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目名称", "t7",true,JQTable.TextAlign.Left),
                new JQTable.Node("业主单位", "t8",true,JQTable.TextAlign.Left),
                new JQTable.Node("产品型号/类型", "t9",true,JQTable.TextAlign.Left),
                new JQTable.Node("电压等级", "t10",true,JQTable.TextAlign.Left),
                new JQTable.Node("数量（台）", "t11",true,JQTable.TextAlign.Left),
                new JQTable.Node("签约容量(kVA)", "t12",true,JQTable.TextAlign.Left),
                new JQTable.Node("签约金额", "t13",true,JQTable.TextAlign.Left),
                new JQTable.Node("付款方式", "t14",true,JQTable.TextAlign.Left),
                new JQTable.Node("签订人", "t15",true,JQTable.TextAlign.Left),
                new JQTable.Node("具体签约单位", "t16",true,JQTable.TextAlign.Left)
            ], gridName);
        }
        
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }


        private mMonth: number;
        private mYear: number;
        private mDataSet: Util.Ajax;
        private mCompanyName;
        private mDocType;
        echartId: string;
        TableId: string;
        childTableId: string;
        
        public init( TableId: string, 
	        year : number,
            month : number): void {
            
            this.mYear = year;
            this.mMonth = month;
            this.TableId = TableId;
            this.childTableId = TableId+"_jqgrid_1234";
             //请求数据
            this.mDataSet = new Util.Ajax("mkt_view_update.do");
            this.onDoc_TypeSelected();
            this.onCompanySelected();
            //this.updateUI();
        }

        public onDoc_TypeSelected()
        {
            this.mDocType= $("#rpttype").val();
        }
                
        public onCompanySelected() {
            this.mCompanyName = $("#comp_category").val();
        }
        
        public updateUI() {
            //删去历史内容
            var parent = $("#" + this.TableId);
            parent.empty();
            parent.append("<table id='"+ this.childTableId +"'></table>" + "<div id='pager'></div>");
            
           
            
            this.mDataSet.get({ companyName: this.mCompanyName, docType: this.mDocType})
                .then((data: any) => {
                    var fktjData = data;
                    
                         $('#dataStatus').css("display", "none");
                         if(this.mDocType=="bid_info"){
                        this.updateTable(
                            this.TableId,
                            this.childTableId,
                            JQGridAssistantFactory.createBidTable(this.childTableId),
                            fktjData[0]);
                    }else if(this.mDocType=="project_info"){
                        this.updateTable(
                            this.TableId,
                            this.childTableId,
                            JQGridAssistantFactory.createPrjTable(this.childTableId),
                            fktjData[0]);
                    }else if(this.mDocType=="sign_contract"){
                        this.updateTable(
                            this.TableId,
                            this.childTableId,
                            JQGridAssistantFactory.createContTable(this.childTableId),
                            fktjData[0]);
                    }

                    var title=$("#comp_category option:selected").text()+$("#rpttype option:selected").text();
                    $('h1').text(title);
                    document.title = title;
                 });
        }
 
        private updateTable(      
		        parentName: string, 
		        childName : string, 
		        tableAssist : JQTable.JQGridAssistant, 
		        rawData : Array<string[]>): void {
			
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
                    height: '100%',
                    width: $(document).width()-60,
                    shrinkToFit: true,
                    autoScroll: true,
                    pager:'#pager',
                    rowNum: 20,
                    viewrecords: true//是否显示行数 

                })
           );

        }
    }
}