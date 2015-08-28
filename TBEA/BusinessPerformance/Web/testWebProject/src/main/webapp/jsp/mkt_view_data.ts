declare var echarts;
module mkt_view_data {

    class JQGridAssistantFactory {

        public static createBidTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "title0",true,JQTable.TextAlign.Left),
                new JQTable.Node("投标编号", "title1",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目信息编号", "title2",true,JQTable.TextAlign.Left),
                new JQTable.Node("授权编号", "title3",true,JQTable.TextAlign.Left),
                new JQTable.Node("办事处或项目部", "title4",true,JQTable.TextAlign.Left),
                new JQTable.Node("投标月份", "title5",true,JQTable.TextAlign.Left),
                new JQTable.Node("投标日期", "title6",true,JQTable.TextAlign.Left),
                new JQTable.Node("所属行业", "title7",true,JQTable.TextAlign.Left),
                new JQTable.Node("所属系统", "title8",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目所在区域", "title9",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目名称", "title10",true,JQTable.TextAlign.Left),
                new JQTable.Node("业主单位", "title11",true,JQTable.TextAlign.Left),
                new JQTable.Node("产品型号", "title12",true,JQTable.TextAlign.Left),
                new JQTable.Node("电压等级", "title13",true,JQTable.TextAlign.Left),
                new JQTable.Node("投标数量", "title14",true,JQTable.TextAlign.Left),
                new JQTable.Node("投标容量(kVA)", "title15",true,JQTable.TextAlign.Left),
                new JQTable.Node("我厂投标价格", "title16",true,JQTable.TextAlign.Left),
                new JQTable.Node("中标厂家", "title17",true,JQTable.TextAlign.Left),
                new JQTable.Node("中标价格", "title18",true,JQTable.TextAlign.Left),
                new JQTable.Node("中标或未中标原因分析", "title19",true,JQTable.TextAlign.Left),
                new JQTable.Node("定标月份", "title20",true,JQTable.TextAlign.Left),
                new JQTable.Node("状态", "title21",true,JQTable.TextAlign.Left),
                new JQTable.Node("是否反馈投标总结", "title22",true,JQTable.TextAlign.Left),
                new JQTable.Node("具体投标单位", "title23",true,JQTable.TextAlign.Left)
            ], gridName);
        }
        
        public static createPrjTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "title0",true,JQTable.TextAlign.Left),
                new JQTable.Node("办事处名称", "title1",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目序号", "title2",true,JQTable.TextAlign.Left),
                new JQTable.Node("所属行业", "title3",true,JQTable.TextAlign.Left),
                new JQTable.Node("所属系统", "title4",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目名称", "title5",true,JQTable.TextAlign.Left),
                new JQTable.Node("业主单位", "title6",true,JQTable.TextAlign.Left),
                new JQTable.Node("产品型号", "title7",true,JQTable.TextAlign.Left),
                new JQTable.Node("数量", "title8",true,JQTable.TextAlign.Left),
                new JQTable.Node("预计投标金额", "title9",true,JQTable.TextAlign.Left),
                new JQTable.Node("预计招标时间", "title10",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目所在区域", "title11",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目简介", "title12",true,JQTable.TextAlign.Left),
                new JQTable.Node("目前推进跟踪情况及后期计划", "title13",true,JQTable.TextAlign.Left),
                new JQTable.Node("本单位项目负责人及联系方式", "title14",true,JQTable.TextAlign.Left),
                new JQTable.Node("本单位负责该项目的主管领导", "title15",true,JQTable.TextAlign.Left),
                new JQTable.Node("跟踪该项目的其它内部企业名称", "title16",true,JQTable.TextAlign.Left),
                new JQTable.Node("投标情况", "title17",true,JQTable.TextAlign.Left),
                new JQTable.Node("备注", "title18",true,JQTable.TextAlign.Left)
            ], gridName);
        }
        
        public static createContTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "title0",true,JQTable.TextAlign.Left),
                new JQTable.Node("合同编号", "title1",true,JQTable.TextAlign.Left),
                new JQTable.Node("办事处或项目部", "title2",true,JQTable.TextAlign.Left),
                new JQTable.Node("签约月份", "title3",true,JQTable.TextAlign.Left),
                new JQTable.Node("所属行业", "title4",true,JQTable.TextAlign.Left),
                new JQTable.Node("所属系统", "title5",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目所在区域", "title6",true,JQTable.TextAlign.Left),
                new JQTable.Node("项目名称", "title7",true,JQTable.TextAlign.Left),
                new JQTable.Node("业主单位", "title8",true,JQTable.TextAlign.Left),
                new JQTable.Node("产品型号/类型", "title9",true,JQTable.TextAlign.Left),
                new JQTable.Node("电压等级", "title10",true,JQTable.TextAlign.Left),
                new JQTable.Node("数量（台）", "title11",true,JQTable.TextAlign.Left),
                new JQTable.Node("签约容量(kVA)", "title12",true,JQTable.TextAlign.Left),
                new JQTable.Node("签约金额", "title13",true,JQTable.TextAlign.Left),
                new JQTable.Node("付款方式", "title14",true,JQTable.TextAlign.Left),
                new JQTable.Node("签订人", "title15",true,JQTable.TextAlign.Left),
                new JQTable.Node("具体签约单位", "title16",true,JQTable.TextAlign.Left)
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
        
        public init(echartId: string, 
	        TableId: string, 
	        year : number,
            month : number): void {
            
            this.mYear = year;
            this.mMonth = month;
            this.echartId = echartId;
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
                    width: $(document).width()-40,
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