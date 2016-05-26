/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var cb_wg_byq;
(function (cb_wg_byq) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createSubNode = function (parent) {
            return parent
                .append(new JQTable.Node("单价", "dj"))
                .append(new JQTable.Node("用量", "yl"));
        };
        JQGridAssistantFactory.createMxTable = function (gridName) {
            var title = ["订单所在单位", "订单所在项目公司", "工作号", "完工时间", "订货单位", "产品型号", "电压等级", "产量（万KVA）", "产值", " 实际硅钢片用量 ", " 实际硅钢片单价 ", " 实际电解铜用量 ", " 实际电解铜单价（无税含加工费） ", " 加工费(含税) ", " 实际变压器油用量 ", " 实际变压器油单价 ", " 实际钢材用量 ", " 实际钢材单价 ", " 实际绝缘纸板用量 ", " 实际绝缘纸板单价 ", " 实际五大主材成本 ", " 实际其他材料成本合计 ", " 实际材料成本总计 ", " 实际人工制造费用 ", " 实际总成本 ", " 运费 ", " 实际毛利额 ", "实际毛利率"];
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i == 0) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Left, 90));
                }
                else if (i < 7) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Left, 80));
                }
                else {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Right, 80));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        JQGridAssistantFactory.createJttbTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("完工时间", "wgsj", true, JQTable.TextAlign.Left),
                new JQTable.Node("单位", "dw", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同金额", "htje"),
                new JQTable.Node("合同金额", "htje_1", true, JQTable.TextAlign.Center),
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
                new JQTable.Node("单位", "1dw", true, JQTable.TextAlign.Left),
                new JQTable.Node("完工时间", "1wgsj", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同金额", "1htje"),
                new JQTable.Node("合同金额", "1htje_1", true, JQTable.TextAlign.Center),
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
                new JQTable.Node("完工时间", "2wgsj", true, JQTable.TextAlign.Left),
                new JQTable.Node("电压等级", "2dydj", true, JQTable.TextAlign.Left),
                new JQTable.Node("合同金额", "2htje"),
                new JQTable.Node("合同金额", "2htje_1", true, JQTable.TextAlign.Center),
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
    }());
    var View = (function () {
        function View() {
            //		private mfdwData : string[];
            //		private mgwData : string[];
            //		private mnwData : string[];
            this.mMxData = [[]];
            this.mJtData = [[]];
            this.mGsData = [[]];
            this.mBtdyData = [[]];
            this.mDateDataSet = new Util.Ajax("wg_date_update.do");
            this.mCompanyDataSet = new Util.Ajax("wg_update.do");
            this.mComp = Util.CompanyType.SBGS;
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (mxTableId, jttbTableId, gstbTableId, fdyTableId, month, year) {
            this.mMxTableId = mxTableId;
            this.mJttbTableId = jttbTableId;
            this.mGstbTableId = gstbTableId;
            this.mFdyTableId = fdyTableId;
            this.mMonth = month; //month;
            this.mYear = year; //year;
            this.updateMxTable();
            this.updateJttbTable();
            //this.updateGstbTable();
            this.updateFdyTable();
            this.updateCompany();
            this.updateDate();
        };
        View.prototype.onCompanySelected = function (comp) {
            this.mComp = comp;
        };
        View.prototype.updateCompany = function () {
            var _this = this;
            this.mCompanyDataSet.get({ month: this.mMonth, year: this.mYear, companyId: this.mComp })
                .then(function (jsonData) {
                _this.mMxData = jsonData;
                _this.updateMxTable();
            });
        };
        View.prototype.updateDate = function () {
            var _this = this;
            this.mDateDataSet.get({ month: this.mMonth, year: this.mYear })
                .then(function (jsonData) {
                _this.mJtData = jsonData[0];
                _this.mBtdyData = jsonData[1];
                _this.updateJttbTable();
                _this.updateFdyTable();
            });
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.onMonthSelected = function (month) {
            this.mMonth = month;
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
                            if (26 == col) {
                                row[col] = (parseFloat(row[col]) * 100).toFixed(2) + "%";
                            }
                            else if (col >= 7 && col != 10 && col != 8 && col != 13 && col != 15 && col != 17) {
                                row[col] = Util.formatCurrency(row[col]);
                            }
                            else if (col >= 7 && (col == 10 || col == 8 || col == 13 || col == 15 || col == 17)) {
                                row[col] = parseFloat(row[col]).toFixed(2);
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
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                cellsubmit: 'clientArray',
                rowNum: 10000,
                cellEdit: true,
                height: 250,
                width: 1250,
                shrinkToFit: false,
                autoScroll: true,
            }));
        };
        View.prototype.updateJttbTable = function () {
            var name = this.mJttbTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createJttbTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeColum(0, 9);
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            tableAssist.mergeRow(2, 0, 3);
            tableAssist.mergeRow(2, 3, 3);
            tableAssist.mergeRow(2, 6, 3);
            tableAssist.mergeRow(2, 9, 3);
            var data = [
                [this.mMonth + "月", "沈变", "", "中标阶段"],
                [this.mMonth + "月", "沈变", "", "预期阶段"],
                [this.mMonth + "月", "沈变", "", "完工阶段"],
                [this.mMonth + "月", "衡变", " ", "中标阶段"],
                [this.mMonth + "月", "衡变", " ", "预期阶段"],
                [this.mMonth + "月", "衡变", " ", "完工阶段"],
                [this.mMonth + "月", "新变", "", "中标阶段"],
                [this.mMonth + "月", "新变", "", "预期阶段"],
                [this.mMonth + "月", "新变", "", "完工阶段"],
                [this.mYear + "年" + this.mMonth, "月小计", " ", "中标阶段"],
                [this.mYear + "年" + this.mMonth, "月小计", " ", "预期阶段"],
                [this.mYear + "年" + this.mMonth, "月小计", " ", "完工阶段"]];
            for (var i = 0; i < this.mJtData.length; ++i) {
                if (this.mJtData[i] instanceof Array) {
                    for (var col in this.mJtData[i]) {
                        if (col == 0) {
                            data[i][2] = Util.formatCurrency(this.mJtData[i][col]);
                        }
                        else if (col % 2 == 1) {
                            data[i].push(Util.formatCurrency(this.mJtData[i][col]));
                        }
                        else if (col == 2) {
                            data[i].push((parseFloat(this.mJtData[i][col]) * 100.0).toFixed(2) + "%");
                        }
                        else {
                            data[i].push(parseFloat(this.mJtData[i][col]).toFixed(2));
                        }
                    }
                }
            }
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
            tableAssist.mergeColum(0, 15);
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            tableAssist.mergeRow(2);
            var data = [["XX公司", "1月", "", "中标阶段"],
                ["XX公司", "1月", "", "预期阶段"],
                ["XX公司", "1月", "", "完工阶段"],
                ["XX公司", "2月", " ", "中标阶段"],
                ["XX公司", "2月", " ", "预期阶段"],
                ["XX公司", "2月", " ", "完工阶段"],
                ["XX公司", "3月", "", "中标阶段"],
                ["XX公司", "3月", "", "预期阶段"],
                ["XX公司", "3月", "", "完工阶段"],
                ["XX公司", "4月", " ", "中标阶段"],
                ["XX公司", "4月", " ", "预期阶段"],
                ["XX公司", "4月", " ", "完工阶段"],
                ["XX公司", "5月", "", "中标阶段"],
                ["XX公司", "5月", "", "预期阶段"],
                ["XX公司", "5月", "", "完工阶段"],
                ["X月", "小计", " ", "中标阶段"],
                ["X月", "小计", " ", "预期阶段"],
                ["X月", "小计", " ", "完工阶段"]];
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
        View.prototype.updateFdyTable = function () {
            var name = this.mFdyTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createFdyTable(name);
            tableAssist.mergeTitle();
            tableAssist.mergeColum(0, 18);
            tableAssist.mergeRow(0);
            tableAssist.mergeRow(1);
            for (var i = 0; i < 7; ++i) {
                tableAssist.mergeRow(2, i * 3, 3);
            }
            var data = [[this.mMonth + "月", "110KV", "", "中标阶段"],
                [this.mMonth + "月", "110KV", "", "预期阶段"],
                [this.mMonth + "月", "110KV", "", "完工阶段"],
                [this.mMonth + "月", "220KV", " ", "中标阶段"],
                [this.mMonth + "月", "220KV", " ", "预期阶段"],
                [this.mMonth + "月", "220KV", " ", "完工阶段"],
                [this.mMonth + "月", "330KV", "", "中标阶段"],
                [this.mMonth + "月", "330KV", "", "预期阶段"],
                [this.mMonth + "月", "330KV", "", "完工阶段"],
                [this.mMonth + "月", "500KV", " ", "中标阶段"],
                [this.mMonth + "月", "500KV", " ", "预期阶段"],
                [this.mMonth + "月", "500KV", " ", "完工阶段"],
                [this.mMonth + "月", "直流", "", "中标阶段"],
                [this.mMonth + "月", "直流", "", "预期阶段"],
                [this.mMonth + "月", "直流", "", "完工阶段"],
                [this.mMonth + "月", "电抗器", " ", "中标阶段"],
                [this.mMonth + "月", "电抗器", " ", "预期阶段"],
                [this.mMonth + "月", "电抗器", " ", "完工阶段"],
                [this.mYear + "年" + this.mMonth, "月小计", "", "中标阶段"],
                [this.mYear + "年" + this.mMonth, "月小计", "", "预期阶段"],
                [this.mYear + "年" + this.mMonth, "月小计", "", "完工阶段"]];
            for (var i = 0; i < this.mBtdyData.length; ++i) {
                if (this.mBtdyData[i] instanceof Array) {
                    for (var col in this.mBtdyData[i]) {
                        if (col == 0) {
                            data[i][2] = Util.formatCurrency(this.mBtdyData[i][col]);
                        }
                        else if (col % 2 == 1) {
                            data[i].push(Util.formatCurrency(this.mBtdyData[i][col]));
                        }
                        else if (col == 2) {
                            data[i].push((parseFloat(this.mBtdyData[i][col]) * 100.0).toFixed(2) + "%");
                        }
                        else {
                            data[i].push(parseFloat(this.mBtdyData[i][col]).toFixed(2));
                        }
                    }
                }
            }
            var parent = $("#" + this.mFdyTableId);
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
                rowNum: 200,
                shrinkToFit: true,
                autoScroll: true,
            }));
        };
        return View;
    }());
    cb_wg_byq.View = View;
})(cb_wg_byq || (cb_wg_byq = {}));
