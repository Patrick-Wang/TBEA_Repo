var cb_zx_byq;
(function (cb_zx_byq) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createSubNode = function (parent) {
            return parent.append(new JQTable.Node("单价", "dj")).append(new JQTable.Node("用量", "yl"));
        };
        JQGridAssistantFactory.createMxTable = function (gridName) {
            var title = ["订单所在单位及项目公司", "订单执行阶段", "工作号", "国别", "客户行业类型", "合同中标时间 ", "产品型号", "合同号", "订货单位", "交货时间", "产值", "硅钢牌号", "硅钢数量", "硅钢单价", "铜用量", "铜单价", "铜加工费", "变压器油规格", "变压器油用量", "变压器油单价", "钢材用量", "钢材单价", "纸板用量", "纸板单价", "五大主材成本", "其他材料成本", "材料合计", "人工制造费用", "生产总成本", "运费", "产值测算毛利额", "产值测算毛利率"];
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                nodes.push(new JQTable.Node(title[i], "Mx" + i));
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        JQGridAssistantFactory.createJttbTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dw", true, 0 /* Left */),
                new JQTable.Node("产值", "cz"),
                new JQTable.Node("产值", "cz_1", true, 2 /* Center */),
                new JQTable.Node("毛利额", "mle"),
                new JQTable.Node("毛利率", "mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt")).append(new JQTable.Node("加工费", "jgf")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "gc")).append(new JQTable.Node("加工费", "jgf"))
            ], gridName);
        };
        JQGridAssistantFactory.createGstbTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("时间", "1sj", true, 0 /* Left */),
                new JQTable.Node("产值", "1cz"),
                new JQTable.Node("产值", "1cz_1", true, 2 /* Center */),
                new JQTable.Node("毛利额", "1mle"),
                new JQTable.Node("毛利率", "1mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "1gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "1djt")).append(new JQTable.Node("加工费", "1jgf")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "1zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "1byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "1gc")).append(new JQTable.Node("加工费", "1jgf"))
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
        View.prototype.init = function (mxTableId, jttbTableId, gstbTableId) {
            this.mMxTableId = mxTableId;
            this.mJttbTableId = jttbTableId;
            this.mGstbTableId = gstbTableId;
            this.updateMxTable();
            this.updateJttbTable();
            this.updateGstbTable();
        };
        View.prototype.updateMxTable = function () {
            var name = this.mMxTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createMxTable(name);
            var data = [[""]];
            var row = [];
            var parent = $("#" + this.mMxTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                width: 1250,
                shrinkToFit: false,
                autoScroll: true
            }));
        };
        View.prototype.updateJttbTable = function () {
            var name = this.mJttbTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createJttbTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            var data = [
                ["沈变", " ", "中标阶段"],
                ["沈变", " ", "预期阶段"],
                ["衡变", "  ", "中标阶段"],
                ["衡变", "  ", "预期阶段"],
                ["新变", "   ", "中标阶段"],
                ["新变", "   ", "预期阶段"],
                ["总计", "    ", "中标阶段"],
                ["总计", "    ", "预期阶段"]
            ];
            var row = [];
            var parent = $("#" + this.mJttbTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                width: 1250,
                shrinkToFit: true,
                autoScroll: true
            }));
        };
        View.prototype.updateGstbTable = function () {
            var name = this.mGstbTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createGstbTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            var data = [
                ["1月", " ", "中标阶段"],
                ["1月", " ", "预期阶段"],
                ["2月", "  ", "中标阶段"],
                ["2月", "  ", "预期阶段"],
                ["3月", "   ", "中标阶段"],
                ["3月", "   ", "预期阶段"],
                ["总计", "    ", "中标阶段"],
                ["总计", "    ", "预期阶段"]
            ];
            var row = [];
            var parent = $("#" + this.mGstbTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                width: 1250,
                shrinkToFit: true,
                autoScroll: true
            }));
        };
        return View;
    })();
    cb_zx_byq.View = View;
})(cb_zx_byq || (cb_zx_byq = {}));
