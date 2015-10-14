/// <reference path="util.ts" />
/// <reference path="jqgrid/jqassist.ts" />
declare var echarts;
module mkt_contract_analysis {
    
    enum ContractZb{
        hy, htsl, htje, dyzydbl, ndsl, ndhtje, 
        ndzbl, qnhtsl, qnhtje, qnzndbl, htzz    
    }

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
            if (this.mCompanyName == "股份公司"){
                this.onCompanySelected();
            }
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
        
        private formatData(rowData: string[][], integerList: std.vector<number>, percentList: std.vector<number>) {
            var outputData: string[][] = [];

            var formaterChain: Util.FormatHandler = new Util.FormatPercentHandler([], percentList.toArray());
            formaterChain.next(new Util.FormatIntHandler([], integerList.toArray()))
                .next(new Util.FormatCurrencyHandler());
            var row = [];
            for (var j = 0; j < rowData.length; ++j) {
                row = [].concat(rowData[j]);
                for (var i = 1; i < row.length; ++i) {
                    row[i] = formaterChain.handle(row[0], i, row[i]);
                }
                outputData.push(row);
            }
            return outputData;
        }
       

        public updateUI() {
            var parent = $("#" + this.TableId);
            parent.empty();
            parent.append("<table id='" + this.childTableId + "'></table>" + "<div id='pager'></div>");
            var dt: Util.Date = this.mDs.getDate();
            this.mDataSet.get({ companyName: this.mCompanyName, year: dt.year, month: dt.month,type: this.mAnalysisType  })
                .then((data: any) => {
                var rowBidData = data;
                if (this.mAnalysisType == "contract_industry") {
                    this.updateTable(
                        this.TableId,
                        this.childTableId,
                        JQGridAssistantFactory.createContractTable4Industry(this.childTableId),
                        rowBidData);
                } else if (this.mAnalysisType == "contract_company") {
                    this.updateTable(
                        this.TableId,
                        this.childTableId,
                        JQGridAssistantFactory.createContractTable4Companys(this.childTableId),
                        rowBidData);
                }

            });
        }

        private updateTable(
            parentName: string,
            childName: string,
            tableAssist: JQTable.JQGridAssistant,
            rawData: Array<string[]>): void {

            var data: string[][] = [];

            var integerList: std.vector<number> = new std.vector<number>();
            var percentList: std.vector<number> = new std.vector<number>();
            integerList.push(ContractZb.htsl);
            integerList.push(ContractZb.ndsl);
            integerList.push(ContractZb.qnhtsl);
            percentList.push(ContractZb.dyzydbl);
            percentList.push(ContractZb.ndhtje);
            percentList.push(ContractZb.ndzbl);
            percentList.push(ContractZb.qnhtje);
            percentList.push(ContractZb.qnzndbl);
            percentList.push(ContractZb.htzz);
            data = this.formatData(rawData, integerList, percentList);

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
//            if (rawData.length != 0) {
//                $("#assist").css("display", "block");
//            }  
        }
    }
}