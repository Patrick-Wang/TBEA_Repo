var cb_xl;
(function (cb_xl) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createSubNode = function (parent) {
            return parent.append(new JQTable.Node("单价", "dj")).append(new JQTable.Node("用量", "yl"));
        };
        JQGridAssistantFactory.createMxTable = function (gridName) {
            var title = ["订单所在单位及项目公司", "投标报价时间", "用户单位名称", "产品大类", "数量", "产值", "预计开标时间", "销售部门预测的中标概率", "投标电解铜用量", "投标电解铜单价", "投标铝用量", "投标铝单价", "投标其他成本合计", "投标成本总计", "运费", "投标毛利率", "投标毛利额"];
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (2 == i) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, 0 /* Left */, 300));
                }
                else if (6 == i) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, 1 /* Right */, 130));
                }
                else if (i < 5) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, 0 /* Left */, 100));
                }
                else {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, 1 /* Right */, 100));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        JQGridAssistantFactory.createJttbTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dw", true, 0 /* Left */),
                new JQTable.Node("产值", "cz"),
                new JQTable.Node("毛利额", "mle"),
                new JQTable.Node("毛利率", "mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("铝", "l"))
            ], gridName);
        };
        JQGridAssistantFactory.createGstbTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("时间", "sj", true, 0 /* Left */),
                new JQTable.Node("产值", "cz"),
                new JQTable.Node("毛利额", "mle"),
                new JQTable.Node("毛利率", "mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("铝", "l"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mComp = 0 /* SB */;
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (mxTableId, jttbTableId, gstbTableId, mx, jt, gs, month) {
            this.mMxTableId = mxTableId;
            this.mJttbTableId = jttbTableId;
            this.mGstbTableId = gstbTableId;
            this.mMxData = mx;
            this.mJtData = jt;
            this.mGsData = gs;
            this.mMonth = month;
            this.mDataSet = new Util.DateDataSet("tb_update.do");
            this.updateJttbTable();
            this.updateGstbTable();
            this.updateUI();
        };
        View.prototype.onCompanySelected = function (comp) {
            this.mComp = comp;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.getDataByCompany(0, 0, this.mComp, function (data) {
                if (null != data) {
                    _this.mMxData = JSON.parse(data);
                    _this.updateMxTable();
                }
            });
        };
        View.prototype.updateMxTable = function () {
            var name = this.mMxTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createMxTable(name);
            var data = [[""]];
            var row = [];
            if (this.mMxData != undefined) {
                data = [];
                for (var i = 0; i < this.mMxData.length; ++i) {
                    if (this.mMxData[i] instanceof Array) {
                        row = [].concat(this.mMxData[i]);
                        for (var col in row) {
                            if (col == 5 || col == 9 || (col >= 11 && col <= 15)) {
                                row[col] = Util.formatCurrency(row[col]);
                            }
                            else if (col == 7 || col == 16) {
                                row[col] = (parseFloat(row[col]) * 100).toFixed(2) + "%";
                            }
                        }
                        data.push(row);
                    }
                }
            }
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
                height: 250,
                width: 1250,
                shrinkToFit: false,
                autoScroll: true,
                rowNum: 1000
            }));
        };
        View.prototype.updateJttbTable = function () {
            var name = this.mJttbTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createJttbTable(name);
            var data = [
                ["鲁缆"],
                ["新缆"],
                ["德缆"],
                ["总计"]
            ];
            for (var i = 0; i < data.length; ++i) {
                data[i] = this.format(data[i].concat(this.mJtData[i]));
            }
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
        View.prototype.format = function (row) {
            for (var col = 1; col < row.length; ++col) {
                if (col == 3) {
                    row[col] = (parseFloat(row[col]) * 100).toFixed(2) + "%";
                }
                else if (col != 5 && col != 7) {
                    row[col] = Util.formatCurrency(row[col]);
                }
                else {
                    row[col] = parseFloat(row[col]).toFixed(2);
                }
            }
            return row;
        };
        View.prototype.updateGstbTable = function () {
            var name = this.mGstbTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createGstbTable(name);
            var data = [];
            for (var i = 0; i < this.mMonth; ++i) {
                data.push(this.format([(i + 1) + "月"].concat(this.mGsData[i])));
            }
            data.push(this.format(["总计"].concat(this.mGsData[this.mMonth])));
            var parent = $("#" + this.mGstbTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            var height = '100%';
            if (this.mMonth > 4) {
                height = 110;
            }
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: height,
                width: 1250,
                shrinkToFit: true,
                autoScroll: true
            }));
        };
        return View;
    })();
    cb_xl.View = View;
})(cb_xl || (cb_xl = {}));
