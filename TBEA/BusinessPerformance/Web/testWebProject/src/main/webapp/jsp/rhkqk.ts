/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;
module rhkqk {

    class JQGridAssistantFactory {
        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dw", true, JQTable.TextAlign.Left),
                new JQTable.Node("集团下达月度资金回笼指标", "dwxdydzjhlzb"),
                new JQTable.Node("各单位自行制定的回款计划", "gdwzxdzdhkjh"),
                new JQTable.Node("今日回款", "jrhk"),
                new JQTable.Node("月累计(截止今日)", "ylj"),
                new JQTable.Node("资金回笼指标完成", "zjhlzbwc"),
                new JQTable.Node("回款计划完成率", "hkjhwcl"),
                new JQTable.Node("已回款中可降应收的回款金额", "yhkzkjysdhkje"),
                new JQTable.Node("目前-月底回款计划", "mqydhkjh")
                    .append(new JQTable.Node("确保搬出", "qbbc"))
                    .append(new JQTable.Node("争取办出", "zqbc"))
                    .append(new JQTable.Node("两者合计", "lzhj")),
                new JQTable.Node("全月确保", "qyqb"),
                new JQTable.Node("预计全月计划完成率", "yjqyjhwcl"),
                new JQTable.Node("截止月底应收账款账面余额", "jzydyszkzmye")

            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }

        private mMonth: number;
        private mYear: number;
        private mData: Array<string[]> = [];
        private mDataSet : Util.DateDataSet;
        private mTableId : string;
        private mDay: number;
        public init(tableId: string, month: number, year: number, day: number): void {
            this.mYear = year;
            this.mMonth = month;
			this.mDataSet = new Util.DateDataSet("rhkqk_update.do");
            this.mTableId = tableId;
            this.mDay = day;
            this.updateTable();
            this.updateUI();

        }
        
        public onDaySelected(day : number){
        	this.mDay = day;
        }
        
 		public onYearSelected(year : number){
        	this.mYear = year;
        }
        
        public onMonthSelected(month : number){
        	this.mMonth = month;
        }
        
		public updateUI(){
			this.mDataSet.getDataByDay(this.mMonth, this.mYear, this.mDay, (dataArray : Array<string[]>) =>{
				if (null != dataArray){
					this.mData = dataArray;
					$('h1').text(this.mYear + "年" + this.mMonth + "月" + this.mDay + "日  回款情况");
					document.title = this.mYear + "年" + this.mMonth + "月" + this.mDay + "日  回款情况";
					this.updateTable();
				}
			});
		}

//        private initEchart(echart): void {
//            var ysyq_payment_Chart = echarts.init(echart)
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

            var data = [["沈变公司"],
						["衡变公司"],
						["新变厂"],
						["其中：天变公司"],
						["鲁缆公司"],
						["新缆厂"],
						["德缆公司"],
						["合计"]];
            
            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
                    for (var col in row) {
                        if (col != 4 && col != 11)
                        row[col] = Util.formatCurrency(row[col]);
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
                    shrinkToFit: false,
                    autoScroll: true,
//                    userData: {
//                        'yf': "合计"
//                    },
//                    footerrow: true,
//                    userDataOnFooter: true
                }));

        }
    }
}