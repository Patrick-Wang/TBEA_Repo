/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;

module syhkjhzxqk {

    class JQGridAssistantFactory {
        public static createTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("款项性质", "kxxz", true, JQTable.TextAlign.Left),
                new JQTable.Node("款项性质", "kxxz_1", true, JQTable.TextAlign.Left),
                new JQTable.Node("计划回款", "jhhk"),
                new JQTable.Node("实际回款", "sjhk"),
                new JQTable.Node("计划完成率", "jhwcl")
            ], gridName);

        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }
        private mComp: Util.CompanyType = Util.CompanyType.HB;
        private mMonth: number;
        private mYear: number;
        private mData: Array<string[]>;
        private mDataSet: Util.Ajax = new Util.Ajax("syhkjhzxqk_update.do");
        private mTableId: string;
        private mEchartId;
        public init(echartId: string, tableId: string, month: number, year: number): void {
            this.mYear = year;
            this.mMonth = month;
            this.mTableId = tableId;
            this.mEchartId = echartId;
            this.updateTable();
            this.updateUI();

        }
        public onYearSelected(year: number) {
            this.mYear = year;
        }

        public onMonthSelected(month: number) {
            this.mMonth = month;
        }

        public onCompanySelected(comp : Util.CompanyType){
        	this.mComp = comp;
        }

        public updateUI() {
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, companyId: this.mComp })
                .then((data: any) => {

                    this.mData = data;
                    $('h1').text(this.mYear + "年" + this.mMonth + "月 回款计划执行情况");
                    document.title = this.mYear + "年" + this.mMonth + "月 回款计划执行情况";
                    this.updateTable();
                    this.updateEchart();

                });
        }
        
        private getMonth(): string[] {
            var month: string[] = [];
            for (var i = 0; i < 12; ++i) {
                month.push((i + 1) + "月");
            }
            return month;
        } 

        private updateEchart(): void{
        	var zxqkChart = echarts.init($("#" + this.mEchartId)[0]);
            var month: string[] = this.getMonth();
            var legend = ["计划回款", "实际回款", "计划完成率"];

            var jhData = [];
            var sjData = [];
            var wclData = [];
            for (var i  = 0; i < this.mData[1].length; ++i){
                jhData.push(parseFloat(this.mData[1][i][0]).toFixed(2));
                sjData.push(parseFloat(this.mData[1][i][1]).toFixed(2));
                wclData.push((parseFloat(this.mData[1][i][2]) * 100).toFixed(2));
            }
            
            var zxqkOption = {
				title : {
				        text: '回款计划执行情况'
				},	   
				tooltip : {
			        trigger: 'axis',
			        formatter: (v) => {
			            return v[0][1] + '<br/>'
		                + v[0][0] + ' : ' + Util.formatCurrency(v[0][2]) + '<br/>'
		                + v[1][0] + ' : ' + Util.formatCurrency(v[1][2]) + '<br/>'
			            + v[2][0] + ' : ' + v[2][2] + '%';
		        }
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
                        data: month
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
                        data: jhData
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: sjData
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: wclData
                    }
                ]
            }
            zxqkChart.setOption(zxqkOption);
		
        }
 
        private updateTable(): void {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeRow(0);
            tableAssist.mergeColum(0, 6);
            tableAssist.mergeColum(0, 7);
            tableAssist.mergeColum(0, 8);
            tableAssist.mergeColum(0, 9);
            tableAssist.mergeTitle();
            var data = [["按款项状态分", "未到期应收账款"],
                ["按款项状态分", "逾期款应收账款"],
                ["按款项状态分", "未到期款"],
                ["按款项状态分", "逾期款"],
                ["按清收性质分", "确保可回款"],
                ["按清收性质分", "争取可回款"],
                ["小", "计"],
                ["现款现", "货回款"],
                ["计划外", "回款"],
                ["合", "计"]];
            if (this.mData != undefined) {
                for (var i = 0; i < data.length; ++i) {
                    for(var j = 0; j < this.mData[0][i].length; ++j){
                        if (j < 2){
                             data[i].push(Util.formatCurrency(this.mData[0][i][j]));
                            }
                        else{
                            //data[i].push((this.mData[0][i][j]));
                           	data[i].push((parseFloat(this.mData[0][i][j]) * 100).toFixed(2) + "%");
                        }
                    }
                }
            }
        
            
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

            $("#" + name).jqGrid(
                tableAssist.decorate({
                    // url: "datasource/syhkjhzxqk.do",
                    // datatype: "json",
                    data: tableAssist.getData(data),
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    // autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: '100%',
                    // userData : {
                    // 'kxxz' : "合计"
                    // },
                    // footerrow : true,
                    // userDataOnFooter : true,
                    // grouping:true,
                    // groupingView : {
                    // groupField : ['g'],
                    // groupColumnShow : [false]
                    // },
                    // serverSuccess : (data : JQTable.Response) =>{

                    // }
                }));
        }
    }
}