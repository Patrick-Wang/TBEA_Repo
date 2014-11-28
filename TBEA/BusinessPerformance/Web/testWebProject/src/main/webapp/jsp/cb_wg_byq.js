var cb_wg_byq;
(function (cb_wg_byq) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createSubNode = function (parent) {
            return parent.append(new JQTable.Node("单价", "dj")).append(new JQTable.Node("用量", "yl"));
        };
        JQGridAssistantFactory.createMxTable = function (gridName) {
            var title = ["工作号", "完工时间", "产值", "实际硅钢片用量 ", "实际硅钢片单价 ", "实际电解铜用量 ", "实际电解铜单价（无税含加工费） ", "加工费(含税) ", " 实际变压器油用量 ", " 实际变压器油单价 ", "实际钢材用量 ", "实际钢材单价 ", " 实际绝缘纸板用量 ", " 实际绝缘纸板单价 ", "实际五大主材成本 ", "实际其他材料成本合计 ", "实际材料成本总计 ", "实际人工制造费用 ", "实际总成本 ", "运费 ", "实际毛利额 ", "实际毛利率"];
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                nodes.push(new JQTable.Node(title[i], "Mx" + i));
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        JQGridAssistantFactory.createJttbTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("完工时间", "wgsj", true, 0 /* Left */),
                new JQTable.Node("单位", "dw", true, 0 /* Left */),
                new JQTable.Node("合同金额", "htje"),
                new JQTable.Node("合同金额", "htje_1", true, 2 /* Center */),
                new JQTable.Node("毛利额", "mle"),
                new JQTable.Node("毛利率", "mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "gc"))
            ], gridName);
        };
        JQGridAssistantFactory.createGstbTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "1dw", true, 0 /* Left */),
                new JQTable.Node("完工时间", "1wgsj", true, 0 /* Left */),
                new JQTable.Node("合同金额", "1htje"),
                new JQTable.Node("合同金额", "1htje_1", true, 2 /* Center */),
                new JQTable.Node("毛利额", "1mle"),
                new JQTable.Node("毛利率", "1mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "1gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "1djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "1zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "1byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "1gc"))
            ], gridName);
        };
        JQGridAssistantFactory.createFdyTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("完工时间", "2wgsj", true, 0 /* Left */),
                new JQTable.Node("电压等级", "2dydj", true, 0 /* Left */),
                new JQTable.Node("合同金额", "2htje"),
                new JQTable.Node("合同金额", "2htje_1", true, 2 /* Center */),
                new JQTable.Node("毛利额", "2mle"),
                new JQTable.Node("毛利率", "2mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("硅钢", "2gg")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "2djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("纸板", "2zb")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("变压器油", "2byqy")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("钢材", "2gc"))
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
        View.prototype.init = function (mxTableId, jttbTableId, gstbTableId, fdyTableId) {
            this.mMxTableId = mxTableId;
            this.mJttbTableId = jttbTableId;
            this.mGstbTableId = gstbTableId;
            this.mFdyTableId = fdyTableId;
            this.updateMxTable();
            this.updateJttbTable();
            this.updateGstbTable();
            this.updateFdyTable();
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
            tableAssist.mergeColum(0, 9);
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            tableAssist.mergeRow(2);
            var data = [
                ["1月", "沈变", "", "中标阶段"],
                ["1月", "沈变", "", "预期阶段"],
                ["1月", "沈变", "", "完工阶段"],
                ["1月", "衡变", " ", "中标阶段"],
                ["1月", "衡变", " ", "预期阶段"],
                ["1月", "衡变", " ", "完工阶段"],
                ["1月", "新变", "", "中标阶段"],
                ["1月", "新变", "", "预期阶段"],
                ["1月", "新变", "", "完工阶段"],
                ["X月", "小计", " ", "中标阶段"],
                ["X月", "小计", " ", "预期阶段"],
                ["X月", "小计", " ", "完工阶段"]
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
            tableAssist.mergeColum(0, 15);
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            tableAssist.mergeRow(2);
            var data = [["XX公司", "1月", "", "中标阶段"], ["XX公司", "1月", "", "预期阶段"], ["XX公司", "1月", "", "完工阶段"], ["XX公司", "2月", " ", "中标阶段"], ["XX公司", "2月", " ", "预期阶段"], ["XX公司", "2月", " ", "完工阶段"], ["XX公司", "3月", "", "中标阶段"], ["XX公司", "3月", "", "预期阶段"], ["XX公司", "3月", "", "完工阶段"], ["XX公司", "4月", " ", "中标阶段"], ["XX公司", "4月", " ", "预期阶段"], ["XX公司", "4月", " ", "完工阶段"], ["XX公司", "5月", "", "中标阶段"], ["XX公司", "5月", "", "预期阶段"], ["XX公司", "5月", "", "完工阶段"], ["X月", "小计", " ", "中标阶段"], ["X月", "小计", " ", "预期阶段"], ["X月", "小计", " ", "完工阶段"]];
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
        View.prototype.updateFdyTable = function () {
            var name = this.mFdyTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createFdyTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeColum(0, 18);
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            tableAssist.mergeRow(2);
            var data = [["1月", "110KV以下", "", "中标阶段"], ["1月", "110KV以下", "", "预期阶段"], ["1月", "110KV以下", "", "完工阶段"], ["1月", "220KV", " ", "中标阶段"], ["1月", "220KV", " ", "预期阶段"], ["1月", "220KV", " ", "完工阶段"], ["1月", "330KV", "", "中标阶段"], ["1月", "330KV", "", "预期阶段"], ["1月", "330KV", "", "完工阶段"], ["1月", "500KV以上", " ", "中标阶段"], ["1月", "500KV以上", " ", "预期阶段"], ["1月", "500KV以上", " ", "完工阶段"], ["1月", "直流", "", "中标阶段"], ["1月", "直流", "", "预期阶段"], ["1月", "直流", "", "完工阶段"], ["1月", "电抗器", " ", "中标阶段"], ["1月", "电抗器", " ", "预期阶段"], ["1月", "电抗器", " ", "完工阶段"], [" 1月", "小计", "", "中标阶段"], [" 1月", "小计", "", "预期阶段"], [" 1月", "小计", "", "完工阶段"]];
            var row = [];
            var parent = $("#" + this.mFdyTableId);
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
                rowNum: 200,
                shrinkToFit: true,
                autoScroll: true
            }));
        };
        return View;
    })();
    cb_wg_byq.View = View;
})(cb_wg_byq || (cb_wg_byq = {}));
