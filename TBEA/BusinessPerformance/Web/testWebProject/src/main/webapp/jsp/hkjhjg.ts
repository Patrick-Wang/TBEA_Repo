/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module hkjhjg {

    class JQGridAssistantFactory {

        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("逾期应收账款", "yqyszk"),
                new JQTable.Node("逾期应收账款", "yqyszk_1"),
                new JQTable.Node("逾期款", "yqk"),
                new JQTable.Node("逾期款", "yqk_1"),
                new JQTable.Node("未到期应收账款", "wdqyszk"),
                new JQTable.Node("未到期应收账款", "wdqyszk_1"),
                new JQTable.Node("未到期款", "wdqk"),
                new JQTable.Node("未到期款", "wdqk_1"),
                new JQTable.Node("小计", "xj"),
                new JQTable.Node("小计", "xj_1")
            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }
        private mComp : Util.CompanyType = Util.CompanyType.JT;
        private mMonth: number;
        private mYear: number;
        private mData: Array<string[]>;
        private mDataSet : Util.DateDataSet;
        private mTableId : string;
        private mEchartId : string;
        public init(echartId: string, tableId: string, month: number, year: number): void {
        this.mYear = year;
            this.mMonth = month;
            this.mDataSet = new Util.DateDataSet("hkjhjg_update.do");
            this.mTableId = tableId;
            this.mEchartId = echartId;
            this.updateTable();
            this.updateUI();
        }
        
        public onYearSelected(year : number){
        	this.mYear = year;
        }
        
        public onMonthSelected(month : number){
        	this.mMonth = month;
        }
        
        public onCompanySelected(comp : Util.CompanyType){
        	this.mComp = comp;
        }
        
		public updateUI(){
			this.mDataSet.getDataByCompany(this.mMonth, this.mYear, this.mComp, (data : string) =>{
				if (null != data){
					this.mData = JSON.parse(data);
					$('h1').text(this.mYear + "年" + this.mMonth + "月 回款计划结构");
					document.title = this.mYear + "年" + this.mMonth + "月 回款计划结构";
					this.updateTable();
					//this.updateEchart();
				}
			});
		}
//        private updateEchart(): void {
//            var ysyq_payment_Chart = echarts.init($("#" + this.mEchartId)[0]);
//            var ysyq_payment_Option = {
//                animation: true,
//                tooltip: {
//                    trigger: 'axis',
//                    /* formatter : "{b}<br/>{a} : {c} 万元<br/>{a1} : {c1} 万元", */
//
//                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
//                        type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
//                    }
//                },
//                legend: {
//                    x: 'right',
//                    data: ["合同金额", "预期阶段", "中标阶段", "完工阶段"],
//
//                },
//                xAxis: [{
//                    type: 'category',
//                    data: ['沈变', '衡变', '新变', '天变']
//                }],
//                yAxis: [{
//                    type: 'value'
//
//                }],
//
//                calculable: true,
//                series: [{
//                    name: '合同金额',
//                    type: 'bar',
//
//                    barCategoryGap: "50%",
//                    data: [63363.11, 55628.27, 58521.55, 69100.58]
//                }, {
//                        name: '预期阶段',
//                        type: 'bar',
//
//                        stack: '阶段',
//                        data: [9098.58, 1240.13, 1140.61, 3154.82]
//                    }, {
//                        name: '中标阶段',
//
//                        type: 'bar',
//                        stack: '阶段',
//                        data: [3934.13, 3200.22, 1382.52, 3934.13]
//                    }, {
//                        name: '完工阶段',
//                        type: 'bar',
//
//                        stack: '阶段',
//                        data: [11980.74, 2240.18, 3487.11, 6980.74]
//                    }]
//            };
//            ysyq_payment_Chart.setOption(ysyq_payment_Option);
//        }

        private updateTable(): void {
       		var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeTitle(undefined, 0, true);
            var data = [
                ["本月回笼", "", "本月回笼", "","本月回笼", "","本月回笼", "","本月回笼", ""],
                ["确保可回", "", "确保可回", "","确保可回", "","确保可回", "","确保可回", ""],
                ["争取可回", "", "争取可回", "","争取可回", "","争取可回", "","争取可回", ""]
            ];

            if (undefined != this.mData) {
                for (var i = 0; i < this.mData[0].length; ++i) {
                    data[0][i * 2 + 1] = this.mData[0][i];
                    data[1][i * 2 + 1] = this.mData[1][i];
                    data[2][i * 2 + 1] = this.mData[2][i];
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
                    // height: '100%',
                    width: 1000,
                    shrinkToFit: false,
                    autoScroll: true
                }));
        }
    }
}