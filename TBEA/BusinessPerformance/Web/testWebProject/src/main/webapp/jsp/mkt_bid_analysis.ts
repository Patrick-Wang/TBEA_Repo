/// <reference path="util.ts" />
/// <reference path="jqgrid/jqassist.ts" />
declare var echarts;
module mkt_bid_analysis {

    enum Bid4IndustryZb {
        hy, dytbsl, dytbje, dyzbje, dyzydbl, ndsl, ndtbje, ndzbje,
        ndzbl, ndtbbl, qntbsl, qntbje, qnzbje, qnzbl, qnzndbl,
        tbzz
    }

    class JQGridAssistantFactory {

        public static createBidTable4Industry(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("行业", "t0", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("当月情况", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标数量", "t11"))
                    .append(new JQTable.Node("投标金额(万元)", "t12"))
                    .append(new JQTable.Node("中标金额(万元)", "t13"))
                    .append(new JQTable.Node("占月度投标总额比例", "t14")),
                new JQTable.Node("年度累计", "t2", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标数量", "t21"))
                    .append(new JQTable.Node("投标金额(万元)", "t22"))
                    .append(new JQTable.Node("中标金额(万元)", "t23"))
                    .append(new JQTable.Node("中标率", "t24"))
                    .append(new JQTable.Node("占年度投标总额比例", "t25")),
                new JQTable.Node("去年同期累计", "t3", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标数量", "t31"))
                    .append(new JQTable.Node("投标金额(万元)", "t32"))
                    .append(new JQTable.Node("中标金额(万元)", "t33"))
                    .append(new JQTable.Node("中标率", "t34"))
                    .append(new JQTable.Node("占年度投标总额比例", "t35")),
                new JQTable.Node("投标额同比增长幅度", "t4", false, JQTable.TextAlign.Right, 0, undefined, undefined, false)
            ], gridName);
        }


        public static createBidTable4Companys(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("项目公司", "t0", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node("当月情况", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标数量", "t11"))
                    .append(new JQTable.Node("投标金额(万元)", "t12"))
                    .append(new JQTable.Node("中标金额(万元)", "t13"))
                    .append(new JQTable.Node("占月度投标总额比例", "t14")),
                new JQTable.Node("年度累计", "t2", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标数量", "t21"))
                    .append(new JQTable.Node("投标金额(万元)", "t22"))
                    .append(new JQTable.Node("中标金额(万元)", "t23"))
                    .append(new JQTable.Node("中标率", "t24"))
                    .append(new JQTable.Node("占年度投标总额比例", "t25")),
            ], gridName);
        }
    }

    var company: any = {};
    company.sb = ["沈变"];
    company.hb = ["衡变"];
    company.xb = ["新变"];
    company.tb = ["天变"];
    company.ll = ["鲁缆"];
    company.xl = ["新缆"];
    company.dl = ["德缆"];
    company.byqcy = ["沈变", "衡变", "新变", "天变"];
    company.xlcy = ["鲁缆", "新缆", "德缆"];
    company.all = ["沈变", "衡变", "新变", "天变", "鲁缆", "新缆", "德缆"];

    export class View {
        public static newInstance(): View {
            return new View();
        }
        private mDataSet: Util.Ajax;
        //private mExportDataSet: Util.Ajax;
        private mCompanyName;
        private mDs: Util.DateSelector;
        private mAnalysisType;
        private mSelCompanys: string[] = [];

        TableId: string;
        childTableId: string;

        public init(TableId: string, dateId: string, year: number, month: number, companyName: string): void {
            this.mCompanyName = companyName;

            this.TableId = TableId;
            this.childTableId = TableId + "_jqgrid_1234";
            this.mDs = new Util.DateSelector(
                { year: year - 1, month: 1 },
                { year: year, month: month },
                dateId);
            //请求数据
            this.mDataSet = new Util.Ajax("mkt_bid_analysis_update.do", false);
            this.onType_TypeSelected();
            //if (this.mCompanyName == "股份公司") {
            //    this.onCompanySelected();
            //} 
            //                else {
            //                for (var i in company) {
            //                    if (company[i].length == 1) {
            //                        if (this.mCompanyName == company[i][0]) {
            //                            this.mSelCompanys = company[i];
            //                        }
            //                    }
            //
            //                }
            //            }
           
            //this.updateUI();
        }

        public onType_TypeSelected() {
            //this.mAnalysisType = company[$("#analysisType").val()];
            this.mAnalysisType = $("#analysisType").val();
        }

        public onCompanySelected() {
            //this.mCompanyName = $("#comp_category").val();
            //this.mSelCompanys = company[$("#comp_category").val()];
        }

        public exportExcel() {
            var dt: Util.Date = this.mDs.getDate();
            $("#exportBidAnalysisData")[0].action = "mkt_bid_analysis_export.do?" + Util.Ajax.toUrlParam({ companyName: JSON.stringify(this.mSelCompanys), year: dt.year, month: dt.month, type: this.mAnalysisType });
            $("#exportBidAnalysisData")[0].submit();
        }



        public updateUI() {

            this.mSelCompanys = [];
            if (this.mCompanyName == "股份公司") {

                $('#comp_category').multiselect("getChecked").each((i, item) => {
                    this.mSelCompanys.push($(item).val());
                });

            } else {
                this.mSelCompanys.push(this.mCompanyName);

            }

            var parent = $("#" + this.TableId);
            parent.empty();
            parent.append("<table id='" + this.childTableId + "'></table>" + "<div id='pager'></div>");
            var dt: Util.Date = this.mDs.getDate();
            this.mDataSet.get({ companyName: JSON.stringify(this.mSelCompanys), year: dt.year, month: dt.month, type: this.mAnalysisType })
                .then((data: any) => {
                    var rowBidData = data;
                    if (this.mAnalysisType == "bid_industry") {
                        this.updateTable(
                            this.TableId,
                            this.childTableId,
                            JQGridAssistantFactory.createBidTable4Industry(this.childTableId),
                            rowBidData);
                    } else if (this.mAnalysisType == "bid_company") {
                        this.updateTable(
                            this.TableId,
                            this.childTableId,
                            JQGridAssistantFactory.createBidTable4Companys(this.childTableId),
                            rowBidData);
                    }

                });
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




        private updateTable(
            parentName: string,
            childName: string,
            tableAssist: JQTable.JQGridAssistant,
            rawData: Array<string[]>): void {
            var data: string[][] = [];
            if (this.mAnalysisType == "bid_industry") {
                var integerList: std.vector<number> = new std.vector<number>();
                var percentList: std.vector<number> = new std.vector<number>();
                integerList.push(Bid4IndustryZb.dytbsl);
                integerList.push(Bid4IndustryZb.ndsl);
                integerList.push(Bid4IndustryZb.qntbsl);
                percentList.push(Bid4IndustryZb.dyzydbl);
                percentList.push(Bid4IndustryZb.ndzbl);
                percentList.push(Bid4IndustryZb.ndtbbl);
                percentList.push(Bid4IndustryZb.qnzbl);
                percentList.push(Bid4IndustryZb.qnzndbl);
                percentList.push(Bid4IndustryZb.tbzz);
                data = this.formatData(rawData, integerList, percentList);

            } else if (this.mAnalysisType == "bid_company") {
                var integerList: std.vector<number> = new std.vector<number>();
                var percentList: std.vector<number> = new std.vector<number>();
                integerList.push(Bid4IndustryZb.dytbsl);
                integerList.push(Bid4IndustryZb.ndsl);
                integerList.push(Bid4IndustryZb.qntbsl);
                percentList.push(Bid4IndustryZb.dyzydbl);
                percentList.push(Bid4IndustryZb.ndzbl);
                percentList.push(Bid4IndustryZb.ndtbbl);
                data = this.formatData(rawData, integerList, percentList);
            }

            $("#" + childName).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(data),
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