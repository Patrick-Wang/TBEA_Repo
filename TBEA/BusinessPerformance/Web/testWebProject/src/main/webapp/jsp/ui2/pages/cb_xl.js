/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var cb_xl;
(function (cb_xl) {
    var JQGridAssistantFactory = /** @class */ (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createSubNode = function (parent) {
            return parent
                .append(new JQTable.Node("单价", "dj"))
                .append(new JQTable.Node("用量", "yl"));
        };
        JQGridAssistantFactory.createMxTable = function (gridName) {
            var title = ["订单所在单位及项目公司", "投标报价时间", "用户单位名称",
                "产品大类", "数量", "产值", "预计开标时间",
                "销售部门预测的中标概率", "投标电解铜用量", "投标电解铜单价",
                "投标铝用量", "投标铝单价", "投标其他成本合计",
                "投标成本总计", "运费", "投标毛利率", "投标毛利额"];
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (0 == i) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Left, 90));
                }
                else if (2 == i) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Left, 300));
                }
                else if (6 == i) {
                    nodes.push(new JQTable.Node(title[i], "Mx" + i, true, JQTable.TextAlign.Right, 130));
                }
                else if (i < 5) {
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
                new JQTable.Node("单位", "dw", true, JQTable.TextAlign.Left),
                new JQTable.Node("产值", "cz"),
                new JQTable.Node("毛利额", "mle"),
                new JQTable.Node("毛利率", "mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("铝", "l"))
            ], gridName);
        };
        JQGridAssistantFactory.createGstbTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("时间", "sj", true, JQTable.TextAlign.Left),
                new JQTable.Node("产值", "cz"),
                new JQTable.Node("毛利额", "mle"),
                new JQTable.Node("毛利率", "mll"),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("电解铜", "djt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("铝", "l"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var View = /** @class */ (function () {
        function View() {
            //      private mfdwData : string[];
            //      private mgwData : string[];
            //      private mnwData : string[];
            this.mMxData = [[]];
            this.mJtData = [[]];
            this.mGsData = [[]];
            this.mDataSet = new Util.Ajax("tb_update.do");
            this.mComp = Util.CompanyType.SBGS;
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (mxTableId, jttbTableId, gstbTableId, month, year) {
            this.mMxTableId = mxTableId;
            this.mJttbTableId = jttbTableId;
            this.mGstbTableId = gstbTableId;
            this.mMonth = month;
            this.mYear = year;
            this.updateMxTable();
            this.updateJttbTable();
            this.updateGstbTable();
            this.updateUI();
        };
        View.prototype.onCompanySelected = function (comp) {
            this.mComp = comp;
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.onMonthSelected = function (month) {
            this.mMonth = month;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, companyId: this.mComp })
                .then(function (jsonData) {
                _this.mMxData = jsonData[0];
                _this.mJtData = jsonData[1];
                _this.mGsData = jsonData[2];
                _this.updateMxTable();
                _this.updateJttbTable();
                _this.updateGstbTable();
            });
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
                height: 250,
                width: 1250,
                shrinkToFit: false,
                autoScroll: true,
                rowNum: 1000
                //                    userData: {
                //                        'title': "合计"
                //                    },
                //                    footerrow: true,
                //                    userDataOnFooter: true
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
            if (this.mJtData.length > 1) {
                for (var i = 0; i < data.length; ++i) {
                    data[i] = this.format(data[i].concat(this.mJtData[i]));
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
            if (this.mGsData.length > 1) {
                for (var i = 0; i < this.mMonth; ++i) {
                    data.push(this.format([(i + 1) + "月"].concat(this.mGsData[i])));
                }
                data.push(this.format(["总计"].concat(this.mGsData[this.mMonth])));
            }
            var parent = $("#" + this.mGstbTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            var height = '100%';
            if (this.mMonth > 4) {
                height = 110;
            }
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
                height: height,
                width: 1250,
                shrinkToFit: true,
                autoScroll: true,
            }));
        };
        return View;
    }());
    cb_xl.View = View;
})(cb_xl || (cb_xl = {}));
