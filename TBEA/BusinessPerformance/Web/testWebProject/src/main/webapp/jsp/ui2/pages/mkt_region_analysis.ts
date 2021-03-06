/// <reference path="util.ts" />
/// <reference path="jqgrid/jqassist.ts" />
declare var echarts;
module mkt_region_analysis {

    enum RegionZb {
        hy, tbje, zbje, zbl, qyje,
        qntbje, qnzbje, qnzbl, qnqyje,
        tbjetbzf, zbjetbzf, zbltbzf, qyjetbzf
    }


    class JQGridAssistantFactory {

        public static createRegionIndexTable(gridName: string, year: number, startMonth: number, endMonth: number): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("区域", "t0", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node(year + "年" + startMonth + "-" + endMonth + "月市场关键累计指标", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标金额(万元)", "t11"))
                    .append(new JQTable.Node("中标金额(万元)", "t12"))
                    .append(new JQTable.Node("中标率", "t13"))
                    .append(new JQTable.Node("签约金额", "t14"))
            ], gridName);
        }


        public static createIndustryIndexTable(gridName: string, year: number, startMonth: number, endMonth: number): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("行业", "t0", false, JQTable.TextAlign.Left, 0, undefined, undefined, false),
                new JQTable.Node(year + "年" + startMonth + "-" + endMonth + "月市场关键累计指标", "t1", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标金额(万元)", "t11"))
                    .append(new JQTable.Node("中标金额(万元)", "t12"))
                    .append(new JQTable.Node("中标率", "t13"))
                    .append(new JQTable.Node("签约金额", "t14")),
                new JQTable.Node((year - 1) + "年" + startMonth + "-" + endMonth + "月市场关键累计指标", "t2", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标金额(万元)", "t21"))
                    .append(new JQTable.Node("中标金额(万元)", "t22"))
                    .append(new JQTable.Node("中标率", "t23"))
                    .append(new JQTable.Node("签约金额", "t24")),
                new JQTable.Node("累计同期对比情况", "t3", false, JQTable.TextAlign.Left, 0, undefined, undefined, false)
                    .append(new JQTable.Node("投标金额同比增长", "t31"))
                    .append(new JQTable.Node("中标金额同比增长", "t32"))
                    .append(new JQTable.Node("中标率同比提高", "t33"))
                    .append(new JQTable.Node("签约金额同比增长", "t34"))
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
        private mAnalysisType: string;
        private mYear: number;
        private mStartMonth: number;
        private mEndMonth: number;
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
            this.mDataSet = new Util.Ajax("mkt_region_analysis_update.do", false);
            this.onType_TypeSelected();
            //this.onCompanySelected();
            this.onYearSelected();
            this.onStartMonthSelected();
            this.onEndMonthSelected();
            //this.updateUI();
        }

        public onType_TypeSelected() {
            this.mAnalysisType = $("#analysisType").val();
        }

        public onCompanySelected() {
            //this.mCompanyName = $("#comp_category").val();
        }

        public exportExcel() {
            $("#exportBidAnalysisData")[0].action = "mkt_region_analysis_export.do?" + Util.Ajax.toUrlParam({ companyName: JSON.stringify(this.mSelCompanys), year: this.mYear, month: this.mEndMonth, startYear: this.mYear, startMonth: this.mStartMonth, type: this.mAnalysisType });
            $("#exportBidAnalysisData")[0].submit();
        }

        public onYearSelected() {
            this.mYear = parseInt($("#year").val());
        }

        public onStartMonthSelected() {
            this.mStartMonth = parseInt($("#start_month").val());
        }

        public onEndMonthSelected() {
            this.mEndMonth = parseInt($("#end_month").val());
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
            if (this.mStartMonth > this.mEndMonth) {
                Util.MessageBox.tip("起始月份大于终止月份，请重新选择月份！");
            } else if (this.mStartMonth == 0 || this.mEndMonth == 0) {
                Util.MessageBox.tip("请选择起始月份和终止月份！");
            }
            else {

                this.mSelCompanys = [];
                if (this.mCompanyName == "股份公司") {

                    $('#comp_category').multiselect("getChecked").each((i, item) => {
                        this.mSelCompanys.push($(item).val());
                    });

                } else {
                    this.mSelCompanys.push(this.mCompanyName);

                }

                this.mDataSet.get({ companyName: JSON.stringify(this.mSelCompanys), year: this.mYear, month: this.mEndMonth, startYear: this.mYear, startMonth: this.mStartMonth, type: this.mAnalysisType })
                    .then((data: any) => {
                        var parent = $("#" + this.TableId);
                        parent.empty();
                        parent.append("<table id='" + this.childTableId + "'></table>" + "<div id='pager'></div>");

                        if (this.mAnalysisType == "region_index") {
                            var integerList: std.vector<number> = new std.vector<number>();
                            var percentList: std.vector<number> = new std.vector<number>();
                            percentList.push(RegionZb.zbl);
                            data = this.formatData(data, integerList, percentList);
                            this.updateTable(
                                this.TableId,
                                this.childTableId,
                                JQGridAssistantFactory.createRegionIndexTable(this.childTableId, this.mYear, this.mStartMonth, this.mEndMonth),
                                data);

                        } else if (this.mAnalysisType == "industry_index") {

                            var integerList: std.vector<number> = new std.vector<number>();
                            var percentList: std.vector<number> = new std.vector<number>();

                            percentList.push(RegionZb.zbl);
                            percentList.push(RegionZb.qnzbl);
                            percentList.push(RegionZb.tbjetbzf);
                            percentList.push(RegionZb.zbjetbzf);
                            percentList.push(RegionZb.zbltbzf);
                            percentList.push(RegionZb.qyjetbzf);
                            data = this.formatData(data, integerList, percentList);
                            this.updateTable(
                                this.TableId,
                                this.childTableId,
                                JQGridAssistantFactory.createIndustryIndexTable(this.childTableId, this.mYear, this.mStartMonth, this.mEndMonth),
                                data);
                        }
                    });
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



            $(rawData).each((i, item: string[]) => {
                if (item[0].indexOf("计") >= 0) {
                    tableAssist.setRowBgColor(i, 183, 222, 232);
                }
            })



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