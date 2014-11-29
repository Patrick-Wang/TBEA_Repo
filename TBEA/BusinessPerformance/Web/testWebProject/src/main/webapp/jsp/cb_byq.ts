/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;
module cb_byq {

    class JQGridAssistantFactory {

        private static createSubNode(parent: JQTable.Node): JQTable.Node {
            return parent
                .append(new JQTable.Node("单价", "dj"))
                .append(new JQTable.Node("用量", "yl"));
        }

        public static createMxTable(gridName: string): JQTable.JQGridAssistant {
            var title = ["订单所在单位及项目公司", "投标报价时间", "用户单位名称", "项目名称",
                "预计交货时间", "型号", "电压", "产量（万KVA）", "产值",
                "预计开标时间", "销售部门预测的中标概率", "投标硅钢牌号", "投标硅钢用量（单台）",
                "投标硅钢单价", "投标电解铜用量（单台）", "投标电解铜单价", "投标变压器油用量（单台）",
                "投标变压器油单价", "投标钢材用量（单台）", "投标钢材单价", "投标纸板用量（单台）",
                "投标纸板单价", "投标五大主材成本", "投标其他材料成本", "投标材料成本总计",
                "人工及制造费用", "投标制造成本", "运费", "投标毛利（单台）", "投标毛利率"];
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                nodes.push(new JQTable.Node(title[i], "Mx" + i));
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }

        public static createJttbTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dw", true, JQTable.TextAlign.Left),
                new JQTable.Node("产值", "cz"),
                new JQTable.Node("毛利额", "mle"),
                new JQTable.Node("毛利率", "mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt"))
                    .append(new JQTable.Node("加工费", "jgf")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "gc"))
                    .append(new JQTable.Node("加工费", "jgf"))
            ], gridName);
        }
        
         public static createGstbTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("时间", "1sj", true, JQTable.TextAlign.Left),
                new JQTable.Node("产值", "1cz"),
                new JQTable.Node("毛利额", "1mle"),
                new JQTable.Node("毛利率", "1mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "1gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "1djt"))
                    .append(new JQTable.Node("加工费", "1jgf")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "1zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "1byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "1gc"))
                    .append(new JQTable.Node("加工费", "1jgf"))
            ], gridName);
        }
    }

    export class View {
        public static newInstance(): View {
            return new View();
        }


//		private mfdwData : string[];
//		private mgwData : string[];
//		private mnwData : string[];
		private mMxTableId : string;
		private mJttbTableId : string;
		private mGstbTableId : string;
        public init(
	        mxTableId: string, 
	        jttbTableId: string, 
	        gstbTableId: string): void {
			this.mMxTableId = mxTableId;
			this.mJttbTableId = jttbTableId;
			this.mGstbTableId = gstbTableId;
			
            this.updateMxTable();
             this.updateJttbTable();
          	this.updateGstbTable();
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

        private updateMxTable(): void {
         	var name = this.mMxTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createMxTable(name);
			var data = [[""]
			];
  			var row = [];
//            for (var i = 0; i < data.length; ++i) {
//                if (rawData[i] instanceof Array) {
//                    row = [].concat(rawData[i]);
//                    for (var col in row) {
//                    	if (col % 2 != 0){
//                        	row[col] = Util.formatCurrency(row[col]);
//                        }
//                    }
//                    data[i] = data[i].concat(row);
//                }
//            }
            
            
			var parent = $("#" + this.mMxTableId);
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
        
        
         private updateJttbTable(): void {
         	var name = this.mJttbTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createJttbTable(name);
			var data = [
				["沈变"],
				["衡变"],
				["新变"],
				["总计"]];
  			var row = [];
//            for (var i = 0; i < data.length; ++i) {
//                if (rawData[i] instanceof Array) {
//                    row = [].concat(rawData[i]);
//                    for (var col in row) {
//                    	if (col % 2 != 0){
//                        	row[col] = Util.formatCurrency(row[col]);
//                        }
//                    }
//                    data[i] = data[i].concat(row);
//                }
//            }
            
            
			var parent = $("#" + this.mJttbTableId);
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
                    width: 1250,
                    shrinkToFit: true,
                    autoScroll: true,
//                    userData: {
//                        'title': "合计"
//                    },
//                    footerrow: true,
//                    userDataOnFooter: true
                }));

        }
        
        private updateGstbTable(): void {
         	var name = this.mGstbTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createGstbTable(name);
			var data = [
				["1月"],
				["2月"],
				["3月"],
				["4月"],
				["总计"]];
  			var row = [];
//            for (var i = 0; i < data.length; ++i) {
//                if (rawData[i] instanceof Array) {
//                    row = [].concat(rawData[i]);
//                    for (var col in row) {
//                    	if (col % 2 != 0){
//                        	row[col] = Util.formatCurrency(row[col]);
//                        }
//                    }
//                    data[i] = data[i].concat(row);
//                }
//            }
            
            
			var parent = $("#" + this.mGstbTableId);
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
                    width: 1250,
                    shrinkToFit: true,
                    autoScroll: true,
//                    userData: {
//                        'title': "合计"
//                    },
//                    footerrow: true,
//                    userDataOnFooter: true
                }));

        }
    }
}