var yqysysfx;
(function (yqysysfx) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("因素分类", "ysfl", true, 0 /* Left */),
                new JQTable.Node("内部因素", "nbys", true, 0 /* Left */),
                new JQTable.Node("客户资信", "khzx", true, 0 /* Left */),
                new JQTable.Node("滚动付款", "gdfk", true, 0 /* Left */),
                new JQTable.Node("项目变化", "xmbh", true, 0 /* Left */),
                new JQTable.Node("合同因素", "htys", true, 0 /* Left */),
                new JQTable.Node("手续办理", "sxbl", true, 0 /* Left */),
                new JQTable.Node("其它", "qt", true, 0 /* Left */)
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (echartId, tableId) {
            this.updateTable(tableId);
        };
        View.prototype.initEchart = function (echart) {
            var ysyq_payment_Chart = echarts.init(echart);
            var ysyq_payment_Option = {
                animation: true,
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'line'
                    }
                },
                legend: {
                    x: 'right',
                    data: ["合同金额", "预期阶段", "中标阶段", "完工阶段"]
                },
                xAxis: [{
                    type: 'category',
                    data: ['沈变', '衡变', '新变', '天变']
                }],
                yAxis: [{
                    type: 'value'
                }],
                calculable: true,
                series: [{
                    name: '合同金额',
                    type: 'bar',
                    barCategoryGap: "50%",
                    data: [63363.11, 55628.27, 58521.55, 69100.58]
                }, {
                    name: '预期阶段',
                    type: 'bar',
                    stack: '阶段',
                    data: [9098.58, 1240.13, 1140.61, 3154.82]
                }, {
                    name: '中标阶段',
                    type: 'bar',
                    stack: '阶段',
                    data: [3934.13, 3200.22, 1382.52, 3934.13]
                }, {
                    name: '完工阶段',
                    type: 'bar',
                    stack: '阶段',
                    data: [11980.74, 2240.18, 3487.11, 6980.74]
                }]
            };
            ysyq_payment_Chart.setOption(ysyq_payment_Option);
        };
        View.prototype.updateTable = function (name) {
            var tableAssist = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeRow(0);
            var data = [
                ["总数量", "户数"],
                ["总数量", "金额"],
                ["其中：法律手段清收", "户数"],
                ["其中：法律手段清收", "金额"]
            ];
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                width: '100%',
                shrinkToFit: false,
                autoScroll: true
            }));
        };
        return View;
    })();
    yqysysfx.View = View;
})(yqysysfx || (yqysysfx = {}));
