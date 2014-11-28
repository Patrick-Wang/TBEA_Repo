/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
declare var echarts;
module cb_wg_xl {

    class JQGridAssistantFactory {

        private static createSubNode(parent: JQTable.Node): JQTable.Node {
            return parent
                .append(new JQTable.Node("单价", "dj"))
                .append(new JQTable.Node("用量", "yl"));
        }

        public static createMxTable(gridName: string): JQTable.JQGridAssistant {
            var title = ["订单所在单位及项目公司","完工时间","投标报价时间","合同中标时间",
            "合同号","产品描述","数量","用户单位名称","产品大类","产值",
            "实际铜用量","实际铜单价","实际铜加工费","实际铝用量","实际铝单价",
            "主材成本","实际其他材料成本合计","材料成本合计","人工制造费用",
            "实际总成本","运费","实际毛利额","实际毛利率"];

            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                nodes.push(new JQTable.Node(title[i], "Mx" + i));
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        }

        public static createJttbTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("完工时间", "wgsj", true, JQTable.TextAlign.Left),
                new JQTable.Node("单位", "dw", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同金额", "htje"),
                new JQTable.Node("合同金额", "htje_1", true, JQTable.TextAlign.Center),
                new JQTable.Node("毛利额", "mle"),
                new JQTable.Node("毛利率", "mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("铝", "l"))
            ], gridName);
        }

        public static createGstbTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "1dw", true, JQTable.TextAlign.Left),
                new JQTable.Node("完工时间", "1wgsj", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同金额", "1htje"),
                new JQTable.Node("合同金额", "1htje_1", true, JQTable.TextAlign.Center),
                new JQTable.Node("毛利额", "1mle"),
                new JQTable.Node("毛利率", "1mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "1djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("铝", "1l"))
            ], gridName);
        }
        
        public static createFdyTable(gridName: string): JQTable.JQGridAssistant {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("完工时间", "2wgsj", true, JQTable.TextAlign.Left),
                new JQTable.Node("电压等级", "2dydj", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同金额", "2htje"),
                new JQTable.Node("合同金额", "2htje_1", true, JQTable.TextAlign.Center),
                new JQTable.Node("毛利额", "2mle"),
                new JQTable.Node("毛利率", "2mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("铝", "l"))
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
        private mMxTableId: string;
        private mJttbTableId: string;
        private mGstbTableId: string;
        private mFdyTableId: string;
        public init(
            mxTableId: string,
            jttbTableId: string,
            gstbTableId: string,
            fdyTableId : string): void {
            this.mMxTableId = mxTableId;
            this.mJttbTableId = jttbTableId;
            this.mGstbTableId = gstbTableId;
            this.mFdyTableId = fdyTableId;

            this.updateMxTable();
            this.updateJttbTable();
            this.updateGstbTable();
            this.updateFdyTable();
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
            //                                 data[i] = data[i].concat(row);
            //                }
            //            }
            
            
            var parent = $("#" + this.mMxTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

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
            tableAssist.mergeTitle();
            tableAssist.mergeColum(0, 9);
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            tableAssist.mergeRow(2);
            var data = [
                ["1月","鲁缆","","中标阶段"],
                ["1月","鲁缆","","预期阶段"],
                ["1月","鲁缆","","完工阶段"],
                ["1月","新缆"," ","中标阶段"],
                ["1月","新缆"," ","预期阶段"],
                ["1月","新缆"," ","完工阶段"],
                ["1月","德缆","","中标阶段"],
                ["1月","德缆","","预期阶段"],
                ["1月","德缆","","完工阶段"],
                ["X月","小计"," ","中标阶段"],
                ["X月","小计"," ","预期阶段"],
                ["X月","小计"," ","完工阶段"]];
            var row = [];
            //            for (var i = 0; i < data.length; ++i) {
            //                if (rawData[i] instanceof Array) {
            //                    row = [].concat(rawData[i]);
            //                    for (var col in row) {
            //                    	if (col % 2 != 0){
            //                        	row[col] = Util.formatCurrency(row[col]);
            //                        }
            //                                 data[i] = data[i].concat(row);
            //                }
            //            }
            
            
            var parent = $("#" + this.mJttbTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

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
           tableAssist.mergeTitle();
            tableAssist.mergeColum(0, 15);
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            tableAssist.mergeRow(2);
            var data = [["XX公司","1月","","中标阶段"],
                        ["XX公司","1月","","预期阶段"],
                        ["XX公司","1月","","完工阶段"],
                        ["XX公司","2月"," ","中标阶段"],
                        ["XX公司","2月"," ","预期阶段"],
                        ["XX公司","2月"," ","完工阶段"],
                        ["XX公司","3月","","中标阶段"],
                        ["XX公司","3月","","预期阶段"],
                        ["XX公司","3月","","完工阶段"],
                        ["XX公司","4月"," ","中标阶段"],
                        ["XX公司","4月"," ","预期阶段"],
                        ["XX公司","4月"," ","完工阶段"],
                        ["XX公司","5月","","中标阶段"],
                        ["XX公司","5月","","预期阶段"],
                        ["XX公司","5月","","完工阶段"],
                        ["X月","小计"," ","中标阶段"],
                        ["X月","小计"," ","预期阶段"],
                        ["X月","小计"," ","完工阶段"]];
            var row = [];
            //            for (var i = 0; i < data.length; ++i) {
            //                if (rawData[i] instanceof Array) {
            //                    row = [].concat(rawData[i]);
            //                    for (var col in row) {
            //                    	if (col % 2 != 0){
            //                        	row[col] = Util.formatCurrency(row[col]);
            //                        }
            //                                 data[i] = data[i].concat(row);
            //                }
            //            }
            
            
            var parent = $("#" + this.mGstbTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

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
        
        private updateFdyTable(): void {
            var name = this.mFdyTableId + "_jqgrid_1234";
            var tableAssist: JQTable.JQGridAssistant = JQGridAssistantFactory.createFdyTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            tableAssist.mergeRow(2);
             tableAssist.mergeColum(0, 30);
            var data = [["X年","导线","","中标阶段"],
                        ["X年","导线","","预期阶段"],
                        ["X年","导线","","完工阶段"],
                        ["X年","交联"," ","中标阶段"],
                        ["X年","交联"," ","预期阶段"],
                        ["X年","交联"," ","完工阶段"],
                        ["X年","其中：高压（66kV以上）","","中标阶段"],
                        ["X年","其中：高压（66kV以上）","","预期阶段"],
                        ["X年","其中：高压（66kV以上）","","中标阶段"],
                        ["X年", "中压（3.6-6.6kV）", " ", "中标阶段"],
                        ["X年", "中压（3.6-6.6kV）", " ", "预期阶段"],
                        ["X年", "中压（3.6-6.6kV）", " ", "完工阶段"],
                        ["X年", "低压（1kV以下）", "", "中标阶段"],
                        ["X年", "低压（1kV以下）", "", "预期阶段"],
                        ["X年", "低压（1kV以下）", "", "完工阶段"],
                        ["X年", "电力电缆", " ", "中标阶段"],
                        ["X年", "电力电缆", " ", "预期阶段"],
                        ["X年", "电力电缆", " ", "完工阶段"],
                        ["X年", "控制电缆", "", "中标阶段"],
                        ["X年", "控制电缆", "", "预期阶段"],
                        ["X年", "控制电缆", "", "完工阶段"],
                        ["X年", "架空线", " ", "中标阶段"],
                        ["X年","架空线"," ","预期阶段"],
                        ["X年","架空线"," ","完工阶段"],
                        ["X年","布电线","","中标阶段"],
                        ["X年","布电线","","预期阶段"],
                        ["X年","布电线","","完工阶段"],
                        ["X年","特种电缆"," ","中标阶段"],
                        ["X年","特种电缆"," ","预期阶段"],
                        ["X年","特种电缆"," ","完工阶段"],
                        ["X月","小计","","中标阶段"],
                        ["X月","小计","","预期阶段"],
                        ["X月","小计","","完工阶段"]];
            var row = [];
            //            for (var i = 0; i < data.length; ++i) {
            //                if (rawData[i] instanceof Array) {
            //                    row = [].concat(rawData[i]);
            //                    for (var col in row) {
            //                      if (col % 2 != 0){
            //                          row[col] = Util.formatCurrency(row[col]);
            //                        }
            //                                 data[i] = data[i].concat(row);
            //                }
            //            }
            
            
            var parent = $("#" + this.mFdyTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");

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
                    rowNum: 200,
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