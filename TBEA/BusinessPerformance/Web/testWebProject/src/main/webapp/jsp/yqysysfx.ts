/// <reference path="jqgrid/jqassist.ts" />
declare var echarts;

module yqysysfx {

    class JQGridAssistantFactory {
        public static createTable(gridName): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("因素分类", "ysfl", true, JQTable.TextAlign.Left),
                new JQTable.Node("因素分类", "ysfl_1", true, JQTable.TextAlign.Left),
                new JQTable.Node("内部因素", "nbys", true, JQTable.TextAlign.Left),
                new JQTable.Node("客户资信", "khzx", true, JQTable.TextAlign.Left),
                new JQTable.Node("滚动付款", "gdfk", true, JQTable.TextAlign.Left),
                new JQTable.Node("项目变化", "xmbh", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同因素", "htys", true, JQTable.TextAlign.Left),
                new JQTable.Node("手续办理", "sxbl", true, JQTable.TextAlign.Left),
                new JQTable.Node("其它", "qt", true, JQTable.TextAlign.Left)
            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }

        
        private mData: Array<string[]>;
        private mDataSet : Util.DateDataSet;
        private mTableId : string;
        private mEchartId;
        private mComp: Util.CompanyType = Util.CompanyType.SB;
        
        public init(echartId: string, tableId: string): void {
           // this.initEchart($('#' + echartId)[0]);
            this.mTableId = tableId;
            this.mEchartId = echartId;
            this.mDataSet = new Util.DateDataSet("yqysysfx_update.do");
            this.updateTable();
            this.updateUI();
        }
        
        public updateUI(){
            this.mDataSet.getDataByCompany(1, 2014, this.mComp, (dataArray : string) =>{
                if (null != dataArray){
                    this.mData = JSON.parse(dataArray);
//                    $('h1').text(this.mYear + "年" + this.mMonth + "月" + this.mDay + "日 现金流日报");
//                    document.title = this.mYear + "年" + this.mMonth + "月" + this.mDay + "日 现金流日报";
                    this.updateTable();
                    this.updateEchart();
                }
            });
        }

        public onCompanySelected(comp : Util.CompanyType){
            this.mComp = comp;
        }
        

        private updateEchart(): void{
        	var yqysysChart = echarts.init($("#" + this.mEchartId)[0]);
            var legend = ["总金额", "其中：法律手段清收"];
            var ysfl = ["内部因素", "客户资信", "滚动付款", "项目变化", "合同因素", "手续办理", "其它"]

            var zjeData = [41982, 31876, 51975, 43856, 61498, 32696, 38574];
            var flsdData = [29167, 21401, 47155, 32584, 52523, 19573, 24652];
            
            var yqysysOption = {
				title : {
				        text: '逾期应收因素分析'
				},	   
				tooltip : {
			        trigger: 'axis'
			    },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true,
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,
                        data: ysfl
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
			        {
			            type : 'value',
			            axisLabel : {
			                formatter: '{value} %'
			            }
			        }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: zjeData
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: flsdData
                    }
                ]
            }
            yqysysChart.setOption(yqysysOption);
		
        }

        private updateTable(): void {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeRow(0);
            tableAssist.mergeTitle();
            var data = [
                ["总数量","户数"],
                ["总数量","金额"],
                ["其中：法律手段清收","户数"],
                ["其中：法律手段清收","金额"]];

            if (this.mData != undefined) {
                var row = [];
                for (var i = 0; i < data.length; ++i) {
                    row = [].concat(this.mData[i]);
                    if (0 != i % 2) {
                        for (var col in row) {
                            row[col] = Util.formatCurrency(row[col]);
                        }
                    }

                    data[i] = data[i].concat(row);
                }
            }

            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='"+ name +"'></table>");
            
                
            $("#" + name).jqGrid(
                tableAssist.decorate({
                    // url: "TestTable/WGDD_load.do",
                    // datatype: "json",
                    data: tableAssist.getData(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true
                }));

        }
    }
}