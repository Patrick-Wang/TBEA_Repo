/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;
module byq_fkfstj {

    class JQGridAssistantFactory {

        private static createSubNode(parent: JQTable.Node): JQTable.Node{
            return parent
                .append(new JQTable.Node("笔数", "bs", true, JQTable.TextAlign.Right, 70))
                .append(new JQTable.Node("金额", "je", true, JQTable.TextAlign.Right, 80));
        }

        public static createFdwTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left, 70),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("非电网合同订单总量", "fdwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无预付款合同", "wyfkht")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("预付款<10%合同", "yfkxy10")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("预付款10%-30%之间的合同", "yfk1030zj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("货物交付后付款比例小于80%的合同", "hwjfhfkblxy80")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无兜底时间合同", "wddsj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期大于>12个月合同", "zbqdy12")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("现款现货合同", "xkxh"))
            ], gridName);
        }
        
         public static createGwTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left, 70),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("国网合同订单总量", "gwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:4:2:1", "3421")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:4:2.5:0.5", "342505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:9:0:1", "0901")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:4:4:1", "1414")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1：4:4.5:0.5", "144505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0：10：0:0", "01000")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("9.5:0.5", "9505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他", "qt"))
            ], gridName);
        }
        
        public static createNwTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left, 70),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("南网合同订单总量", "gwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:3:3:1", "3331")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:4:4:0.5:0.5", "1440505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:2:6.5:0.5", "126505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:4:4.5:0.5", "144505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他1", "qt1")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他2", "qt2"))
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
        private mComp: Util.CompanyType = Util.CompanyType.SB;
        echartIdGW: string;
        echartIdNW: string;
        fdwTableId: string;
        gwTableId: string;
        nwTableId: string;
        echartIdFDW: string;
        
        public init(echartIdFDW: string, 
        	echartIdGW: string, 
        	echartIdNW: string, 
	        fdwTableId: string, 
	        gwTableId: string, 
	        nwTableId: string, 
	        year : number,
            month : number): void {
            
            this.mYear = year;
            this.mMonth = month;
            this.echartIdGW = echartIdGW;
            this.echartIdNW = echartIdNW;
            this.echartIdFDW = echartIdFDW;
            this.fdwTableId = fdwTableId;
            this.gwTableId = gwTableId;
            this.nwTableId = nwTableId;
            
			this.mDataSet = new Util.Ajax("byqfkfstj_update.do");
		
            this.updateUI();
        }

        
        public onYearSelected(year : number){
            this.mYear = year;
        }
        
        public onMonthSelected(month : number){
            this.mMonth = month;
        }
        
        public onCompanySelected(comp: Util.CompanyType) {
            this.mComp = comp;
        }
        
        public updateUI() {
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, companyId: this.mComp })
                .then((data: any) => {

                    var fktjData = data;
                    var rowData = [
                        ["沈变"],
                        ["衡变"],
                        ["新变"],
                        ["合计"]];

                    this.updateTable(
                        this.fdwTableId,
                        this.fdwTableId + "_jqgrid_1234",
                        JQGridAssistantFactory.createFdwTable(this.fdwTableId + "_jqgrid_1234"),
                        rowData,
                        fktjData[0]);
                    rowData = [
                        ["沈变"],
                        ["衡变"],
                        ["新变"],
                        ["合计"]];
                    this.updateTable(
                        this.gwTableId,
                        this.gwTableId + "_jqgrid_1234",
                        JQGridAssistantFactory.createGwTable(this.gwTableId + "_jqgrid_1234"),
                        rowData,
                        fktjData[1]);
                    rowData = [
                        ["沈变"],
                        ["衡变"],
                        ["新变"],
                        ["合计"]];
                    this.updateTable(
                        this.nwTableId,
                        this.nwTableId + "_jqgrid_1234",
                        JQGridAssistantFactory.createNwTable(this.nwTableId + "_jqgrid_1234"),
                        rowData,
                        fktjData[2]);

                    $('h1').text("变压器 " + this.mYear + "年" + this.mMonth + "月 付款方式统计");
                    document.title = "变压器 " + this.mYear + "年" + this.mMonth + "月 付款方式统计";

                    this.updateEchart(this.echartIdFDW, "非电网合同订单总量",
                        [{ value: parseFloat(fktjData[0][0][1]).toFixed(2), name: '沈变' },
                            { value: parseFloat(fktjData[0][1][1]).toFixed(2), name: '衡变' },
                            { value: parseFloat(fktjData[0][2][1]).toFixed(2), name: '新变' }]);
                    this.updateEchart(this.echartIdGW, "国网合同订单总量",
                        [{ value: parseFloat(fktjData[1][0][1]).toFixed(2), name: '沈变' },
                            { value: parseFloat(fktjData[1][1][1]).toFixed(2), name: '衡变' },
                            { value: parseFloat(fktjData[1][2][1]).toFixed(2), name: '新变' }]);
                    this.updateEchart(this.echartIdNW, "南网合同订单总量",
                        [{ value: parseFloat(fktjData[2][0][1]).toFixed(2), name: '沈变' },
                            { value: parseFloat(fktjData[2][1][1]).toFixed(2), name: '衡变' },
                            { value: parseFloat(fktjData[2][2][1]).toFixed(2), name: '新变' }]);
                });



        }
 
			private updateEchart(chartId: string, tileTex: string, data: any[]): void{
	        	var chart = echarts.init($("#" + chartId)[0]);
	            var legend = ["沈变", "衡变", "新变"];
                var legendData = [];
                var realData = [];
                for (var i = 0; i < legend.length; ++i){
                    if (data[i].value >　0){
                        legendData.push(legend[i]);
                        realData.push(data[i]);
                    }    
                }
	            var option = {
	            	title : {
					    text: tileTex,
					    x: 'center'
					},
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
	                },
	                legend: {
	                    x: "left",
	                    data: legendData,
	                    orient: "vertical"
	                },
	                toolbox: {
	                    show: true,
	                },
	                calculable: false,
	                series: [
	                    {
                            name: "公司占比",
	                        type: 'pie',
	                        radius: '50%',
	                        data: realData
	                    }
	                ]
	            }

	            chart.setOption(option);
	        }
        private updateTable(      
		        parentName: string, 
		        childName : string, 
		        tableAssist : JQTable.JQGridAssistant, 
		        data : Array<string[]>, 
		        rawData : Array<string[]>): void {

  			var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (rawData[i] instanceof Array) {
                    row = [].concat(rawData[i]);
                    for (var col in row) {
                        if (col % 2 == 1){
                            row[col] = Util.formatCurrency(row[col]);
                        }
                        else{
                             row[col] = parseInt(row[col]);
                        }
                    }
                    data[i] = data[i].concat(row);
                }
            }
            
            
			var parent = $("#" + parentName);
			parent.empty();
			parent.append("<table id='"+ childName +"'></table>");
			
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
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: 1250,
                    shrinkToFit: true,
                    autoScroll: true,
// userData: {
// 'title': "合计"
// },
// footerrow: true,
// userDataOnFooter: true
                }));

        }
    }
}