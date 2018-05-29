/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var cb_zx_xl;
(function (cb_zx_xl) {
    var JQGridAssistantFactory = /** @class */ (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createSubNode = function (parent) {
            return parent
                .append(new JQTable.Node("单价", "dj"))
                .append(new JQTable.Node("用量", "yl"));
        };
        JQGridAssistantFactory.createMxTable = function (gridName) {
            var title = ["订单所在单位及项目公司", "投标报价时间", "合同中标时间", "合同号",
                "产品描述", "数量", "用户单位名称", "产品大类", "产值",
                "铜用量", "铜单价", "铜加工费", "铝用量", "铝单价",
                "主材成本", "中标其他材料成本", "材料合计", "人工制造费用",
                "生产总成本", "运费", "产值测算毛利额", "产值测算毛利率"];
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                nodes.push(new JQTable.Node(title[i], "Mx" + i));
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        JQGridAssistantFactory.createJttbTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dw", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同金额", "htje"),
                new JQTable.Node("合同金额", "htje_1", true, JQTable.TextAlign.Center),
                new JQTable.Node("毛利额", "mle"),
                new JQTable.Node("毛利率", "mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("铝", "l"))
            ], gridName);
        };
        JQGridAssistantFactory.createGstbTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("产品类型", "1cplx", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同金额", "1htje"),
                new JQTable.Node("合同金额", "1htje_1", true, JQTable.TextAlign.Center),
                new JQTable.Node("毛利额", "1mle"),
                new JQTable.Node("毛利率", "1mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "1djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("铝", "1l"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var View = /** @class */ (function () {
        function View() {
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (mxTableId, jttbTableId, gstbTableId) {
            this.mMxTableId = mxTableId;
            this.mJttbTableId = jttbTableId;
            this.mGstbTableId = gstbTableId;
            this.updateMxTable();
            this.updateJttbTable();
            this.updateGstbTable();
        };
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
        View.prototype.updateMxTable = function () {
            var name = this.mMxTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createMxTable(name);
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
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
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
            }));
        };
        View.prototype.updateJttbTable = function () {
            var name = this.mJttbTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createJttbTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            var data = [
                ["鲁缆", " ", "中标阶段"],
                ["鲁缆", " ", "预期阶段"],
                ["新缆", "", "中标阶段"],
                ["新缆", "", "预期阶段"],
                ["德缆", " ", "中标阶段"],
                ["德缆", " ", "预期阶段"],
                ["总计", "", "中标阶段"],
                ["总计", "", "预期阶段"]
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
            var parent = $("#" + this.mJttbTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
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
            }));
        };
        View.prototype.updateGstbTable = function () {
            var name = this.mGstbTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createGstbTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            var data = [["导线", "", "中标阶段"],
                ["导线", "", "预期阶段"],
                ["交联", " ", "中标阶段"],
                ["交联", " ", "预期阶段"],
                ["其中：高压交联", "", "中标阶段"],
                ["其中：高压交联", "", "预期阶段"],
                ["中压交联", " ", "中标阶段"],
                ["中压交联", " ", "预期阶段"],
                ["低压交联", "", "中标阶段"],
                ["低压交联", "", "预期阶段"],
                ["电力电缆", " ", "中标阶段"],
                ["电力电缆", " ", "预期阶段"],
                ["控制电缆", "", "中标阶段"],
                ["控制电缆", "", "预期阶段"],
                ["架空线", " ", "中标阶段"],
                ["架空线", " ", "预期阶段"],
                ["布电线", "", "中标阶段"],
                ["布电线", "", "预期阶段"],
                ["特种电缆", " ", "中标阶段"],
                ["特种电缆", " ", "预期阶段"],
                ["总计", "", "中标阶段"],
                ["总计", "", "预期阶段"]];
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
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
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
                rowNum: 100
                //                    userData: {
                //                        'title': "合计"
                //                    },
                //                    footerrow: true,
                //                    userDataOnFooter: true
            }));
        };
        return View;
    }());
    cb_zx_xl.View = View;
})(cb_zx_xl || (cb_zx_xl = {}));
