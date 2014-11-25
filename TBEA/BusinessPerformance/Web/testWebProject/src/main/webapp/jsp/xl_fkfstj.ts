/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
module xl_fkfstj {

    class JQGridAssistantFactory {

        private static createSubNode(parent: JQTable.Node): JQTable.Node{
            return parent
                .append(new JQTable.Node("笔数", "bs"))
                .append(new JQTable.Node("金额", "je"));
        }

        public static createFdwTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left),
                new JQTable.Node("", "title1", true, JQTable.TextAlign.Left),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("订单总量", "ddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无预付款合同", "wyfkht")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("预付款<10%合同", "yfkxy10")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("预付款10%-30%之间的合同", "yfk1030zj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("货物交付后付款比例小于80%的合同", "hwjfhfkblxy80")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保金10%的合同", "zbj10")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保金5%的合同", "zbj5")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无质保金合同", "wzbj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期超过1年的合同", "zbqcg1n")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无兜底时间合同", "wddsj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("现款现货合同", "xkxh"))
            ], gridName);
        }
        
        public static createGwTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("国网网合同订单总量", "gwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:06:0:01", "306001")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:09:0:01", "009001")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:4:2:1", "3421")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("2:5:2:1", "2521")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("2:5:2.5:0.5", "252505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0：10：0:0", "01000")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:9.5:0.5", "09505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他", "qt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无质保金合同", "qzbj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期超过1年的合同", "zbqcq1n"))
            ], gridName);
        }
        
          public static createNwTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("国网网合同订单总量", "gwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:6:2:1", "1621")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:2:6:1", "1261")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:09:01", "00901")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他", "qt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期超过1年的合同", "zbqcq1n"))
            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }

       public init(
	        fdwTableId: string, 
	        gwTableId: string, 
	        nwTableId: string, 
	        fdwData :  Array<string[]>,
			gwData : Array<string[]>,
			nwData :  Array<string[]>): void {
			
			

            this.updateFdwTable(
            	fdwTableId, 
            	fdwTableId + "_jqgrid_1234",
            	JQGridAssistantFactory.createFdwTable(fdwTableId + "_jqgrid_1234"),
            	fdwData);
            	
            	
            	
            var rawData = [
               	["集中招标"],
				["非集中招标"],
				["合计"]];
				
            this.updateTable(
            	gwTableId, 
            	gwTableId + "_jqgrid_1234",
            	JQGridAssistantFactory.createGwTable(gwTableId + "_jqgrid_1234"),
            	rawData, 
            	gwData);
            	
            this.updateTable(
            	nwTableId, 
            	nwTableId + "_jqgrid_1234",
            	JQGridAssistantFactory.createNwTable(nwTableId + "_jqgrid_1234"),
            	rawData, 
            	nwData);
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
                    	if (col % 2 != 0){
                        	row[col] = Util.formatCurrency(row[col]);
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
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: 1250,
                    shrinkToFit: false,
                    autoScroll: true,
//                    userData: {
//                        'title': "合计"
//                    },
//                    footerrow: true,
//                    userDataOnFooter: true
                }));

        }

        private updateFdwTable(
		        parentName: string, 
		        childName : string, 
		        tableAssist : JQTable.JQGridAssistant, 
		        rawData : Array<string[]>): void  {
		        
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            tableAssist.mergeColum(0, 11);

            var data = [
               	["电源客户", "火电"],
				["电源客户", "水电"],
				["电源客户", "核电"],
				["电源客户", "风电、光伏"],
				["非电力市场", "轨道交通"],
				["非电力市场", "石油石化"],
				["非电力市场", "煤炭煤化工"],
				["非电力市场", "钢铁冶金"],
				["非电力市场", "航天军工"],
				["非电力市场", "连锁经营"],
				["非电力市场", "其他"],
				["合", "计"]];
				
			var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (rawData[i] instanceof Array) {
                    row = [].concat(rawData[i]);
                    for (var col in row) {
                    	if (col % 2 != 0){
                        	row[col] = Util.formatCurrency(row[col]);
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
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: 1250,
                    shrinkToFit: false 
//                    autoScroll: true,
//                    footerrow: true,
//                    userDataOnFooter: true
                }));

        }
    }
}