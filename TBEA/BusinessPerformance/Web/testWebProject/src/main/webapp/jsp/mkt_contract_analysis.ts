/// <reference path="util.ts" />
/// <reference path="jqgrid/jqassist.ts" />
declare var echarts;
module mkt_contract_analysis {

    class JQGridAssistantFactory {

        public static createContractTable4Industry(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("行业", "t0", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("当月情况", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("合同数量", "t11"))
                    .append(new JQTable.Node("合同金额(万元)", "t12"))
                    .append(new JQTable.Node("占月度签约总额比例", "t13")),
                new JQTable.Node("年度累计", "t2", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("合同数量", "t21"))
                    .append(new JQTable.Node("合同金额(万元)", "t22"))
                    .append(new JQTable.Node("占年度签约总额比例", "t23")),
                 new JQTable.Node("去年同期累计", "t3", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("合同数量", "t31"))
                    .append(new JQTable.Node("合同金额(万元)", "t32"))
                    .append(new JQTable.Node("占年度签约总额比例", "t33")),
                 new JQTable.Node("同比", "t4", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
            ], gridName);
        }
        
        
         public static createContractTable4Companys(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("项目公司", "t0", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("当月情况", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("合同数量", "t11"))
                    .append(new JQTable.Node("合同金额(万元)", "t12"))
                    .append(new JQTable.Node("占月度签约总额比例", "t13")),
                new JQTable.Node("年度累计", "t2", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("合同数量", "t21"))
                    .append(new JQTable.Node("合同金额(万元)", "t22"))
                    .append(new JQTable.Node("占年度签约总额比例", "t23")),
                 new JQTable.Node("同比", "t4", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }
        private mDataSet: Util.Ajax;
        //private mExportDataSet: Util.Ajax;
        private mCompanyName;
        private mDs: Util.DateSelector;
        private mAnalysisType:string;

        TableId: string;
        childTableId: string;

        public init(TableId: string, dateId: string, year:number, month:number, companyName: string): void {
            this.mCompanyName = companyName;

            this.TableId = TableId;
            this.childTableId = TableId + "_jqgrid_1234";
            this.mDs = new Util.DateSelector(
                { year: year - 1, month: 1 },
                { year: year, month: month },
                dateId);
            //请求数据
            this.mDataSet = new Util.Ajax("mkt_contract_analysis_update.do", false);
            this.onType_TypeSelected();
            this.onCompanySelected();
            //this.updateUI();
        }

        public onType_TypeSelected() {
            this.mAnalysisType = $("#analysisType").val();
        }

        public onCompanySelected() {
            this.mCompanyName = $("#comp_category").val();
        }

        public exportExcel() {

        }

       

        public updateUI() {
            var parent = $("#" + this.TableId);
            parent.empty();
            parent.append("<table id='" + this.childTableId + "'></table>" + "<div id='pager'></div>");

            if (this.mAnalysisType == "contract_industry") {
               this.updateTable(
                  this.TableId,
                   this.childTableId,
                   JQGridAssistantFactory.createContractTable4Industry(this.childTableId),
                   []); 

            }else if (this.mAnalysisType == "contract_company"){
                this.updateTable(
                  this.TableId,
                   this.childTableId,
                   JQGridAssistantFactory.createContractTable4Companys(this.childTableId),
                   []); 
            } 
            
//            this.mDataSet.get({ AnalysisType: this.mAnalysisType })
//                .then((data: any) => {
//                    var fktjData = data;
//
//                    $('#dataStatus').css("display", "none");
//                    if (this.mAnalysisType == "bid_industry") {
//                        this.updateTable(
//                            this.TableId,
//                            this.childTableId,
//                            JQGridAssistantFactory.createBidTable4Industry(this.childTableId),
//                            fktjData[0]);
//                    }else if (this.mAnalysisType == "bid_company"){
//                        
//                    } 
//
//                });
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
            if (rawData.length != 0) {
                $("#assist").css("display", "block");
            }  
        }
    }
}