var byq_fkfstj;
(function (byq_fkfstj) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createSubNode = function (parent) {
            return parent.append(new JQTable.Node("笔数", "bs")).append(new JQTable.Node("金额", "je"));
        };
        JQGridAssistantFactory.createFdwTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, 0 /* Left */),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("非电网合同订单总量", "fdwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无预付款合同", "wyfkht")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("预付款<10%合同", "yfkxy10")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("预付款10%-30%之间的合同", "yfk1030zj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("货物交付后付款比例小于80%的合同", "hwjfhfkblxy80")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无兜底时间合同", "wddsj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期大于>12个月合同", "zbqdy12")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("现款现货合同", "xkxh"))
            ], gridName);
        };
        JQGridAssistantFactory.createGwTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, 0 /* Left */),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("国网合同订单总量", "gwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:4:2:1", "3421")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:4:2.5:0.5", "342505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:9:0:1", "0901")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:4:4:1", "1414")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1：4:4.5:0.5", "144505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0：10：0:0", "01000")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("9.5:0.5", "9505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他", "qt"))
            ], gridName);
        };
        JQGridAssistantFactory.createNwTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, 0 /* Left */),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("南网合同订单总量", "gwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:3:3:1", "3331")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:4:4:0.5:0.5", "1440505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:2:6.5:0.5", "126505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:4:4.5:0.5", "144505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他1", "qt1")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他2", "qt2"))
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
        View.prototype.init = function (echartIdFDW, echartIdGW, echartIdNW, fdwTableId, gwTableId, nwTableId, year, month) {
            this.mYear = year;
            this.mMonth = month;
            this.echartIdGW = echartIdGW;
            this.echartIdNW = echartIdNW;
            this.echartIdFDW = echartIdFDW;
            this.fdwTableId = fdwTableId;
            this.gwTableId = gwTableId;
            this.nwTableId = nwTableId;
            this.mDataSet = new Util.DateDataSet("byqfkfstj_update.do");
            this.updateUI();
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.onMonthSelected = function (month) {
            this.mMonth = month;
        };
        View.prototype.onCompanySelected = function (comp) {
            this.mComp = comp;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.getDataByCompany(this.mMonth, this.mYear, this.mComp, function (data) {
                if (null != data) {
                    var fktjData = JSON.parse(data);
                    var rowData = [
                        ["沈变"],
                        ["衡变"],
                        ["新变"],
                        ["合计"]
                    ];
                    _this.updateTable(_this.fdwTableId, _this.fdwTableId + "_jqgrid_1234", JQGridAssistantFactory.createFdwTable(_this.fdwTableId + "_jqgrid_1234"), rowData, fktjData[0]);
                    rowData = [
                        ["沈变"],
                        ["衡变"],
                        ["新变"],
                        ["合计"]
                    ];
                    _this.updateTable(_this.gwTableId, _this.gwTableId + "_jqgrid_1234", JQGridAssistantFactory.createGwTable(_this.gwTableId + "_jqgrid_1234"), rowData, fktjData[1]);
                    rowData = [
                        ["沈变"],
                        ["衡变"],
                        ["新变"],
                        ["合计"]
                    ];
                    _this.updateTable(_this.nwTableId, _this.nwTableId + "_jqgrid_1234", JQGridAssistantFactory.createNwTable(_this.nwTableId + "_jqgrid_1234"), rowData, fktjData[2]);
                    $('h1').text("变压器" + _this.mYear + "年" + _this.mMonth + "月 付款方式统计");
                    document.title = "变压器 " + _this.mYear + "年" + _this.mMonth + "月 付款方式统计";
                }
                _this.updateEchart(_this.echartIdFDW, "非电网合同订单总量", [{ value: parseFloat(fktjData[0][0][1]).toFixed(2), name: '沈变' }, { value: parseFloat(fktjData[0][1][1]).toFixed(2), name: '衡变' }, { value: parseFloat(fktjData[0][2][1]).toFixed(2), name: '新变' }]);
                _this.updateEchart(_this.echartIdGW, "国网合同订单总量", [{ value: parseFloat(fktjData[1][0][1]).toFixed(2), name: '沈变' }, { value: parseFloat(fktjData[1][1][1]).toFixed(2), name: '衡变' }, { value: parseFloat(fktjData[1][2][1]).toFixed(2), name: '新变' }]);
                _this.updateEchart(_this.echartIdNW, "南网合同订单总量", [{ value: parseFloat(fktjData[2][0][1]).toFixed(2), name: '沈变' }, { value: parseFloat(fktjData[2][1][1]).toFixed(2), name: '衡变' }, { value: parseFloat(fktjData[2][2][1]).toFixed(2), name: '新变' }]);
            });
        };
        View.prototype.updateEchart = function (chartId, tileTex, data) {
            var chart = echarts.init($("#" + chartId)[0]);
            var legend = ["沈变", "衡变", "新变"];
            var option = {
                title: {
                    text: tileTex,
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    x: "left",
                    data: legend,
                    orient: "vertical"
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                series: [
                    {
                        type: 'pie',
                        radius: '50%',
                        data: data
                    }
                ]
            };
            chart.setOption(option);
        };
        View.prototype.updateTable = function (parentName, childName, tableAssist, data, rawData) {
            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (rawData[i] instanceof Array) {
                    row = [].concat(rawData[i]);
                    for (var col in row) {
                        if (col % 2 == 1) {
                            row[col] = Util.formatCurrency(row[col]);
                        }
                        else {
                            row[col] = parseInt(row[col]);
                        }
                    }
                    data[i] = data[i].concat(row);
                }
            }
            var parent = $("#" + parentName);
            parent.empty();
            parent.append("<table id='" + childName + "'></table>");
            $("#" + childName).jqGrid(tableAssist.decorate({
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
    byq_fkfstj.View = View;
})(byq_fkfstj || (byq_fkfstj = {}));
