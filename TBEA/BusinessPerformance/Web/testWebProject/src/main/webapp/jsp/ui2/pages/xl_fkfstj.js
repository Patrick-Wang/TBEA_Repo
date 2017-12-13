/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var xl_fkfstj;
(function (xl_fkfstj) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createSubNode = function (parent, fixedWidth) {
            if (fixedWidth === void 0) { fixedWidth = true; }
            if (fixedWidth) {
                return parent
                    .append(new JQTable.Node("笔数", "bs", true, JQTable.TextAlign.Right, 70))
                    .append(new JQTable.Node("金额", "je", true, JQTable.TextAlign.Right, 80));
            }
            else {
                return parent
                    .append(new JQTable.Node("笔数", "bs"))
                    .append(new JQTable.Node("金额", "je"));
            }
        };
        JQGridAssistantFactory.createFdwTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left, 70),
                new JQTable.Node("", "title1", true, JQTable.TextAlign.Left, 70),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("订单总量", "ddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无预付款合同", "wyfkht")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("预付款<10%合同", "yfkxy10")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("预付款10%-30%之间的合同", "yfk1030zj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("货物交付后付款比例小于80%的合同", "hwjfhfkblxy80")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保金10%的合同", "zbj10")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保金5%的合同", "zbj5")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无质保金合同", "wzbj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期超过1年的合同", "zbqcg1n")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无兜底时间合同", "wddsj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("现款现货合同", "xkxh"))
            ], gridName);
        };
        JQGridAssistantFactory.createGwTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left, 70),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("国网合同订单总量", "gwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:06:0:01", "306001")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:09:0:01", "009001")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("3:4:2:1", "3421")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("2:5:2:1", "2521")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("2:5:2.5:0.5", "252505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0：10：0:0", "01000")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:9.5:0.5", "09505")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他", "qt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("无质保金合同", "qzbj")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期超过1年的合同", "zbqcq1n"))
            ], gridName);
        };
        JQGridAssistantFactory.createNwTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, JQTable.TextAlign.Left),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("南网合同订单总量", "gwhtddzl"), false),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:6:2:1", "1621"), false),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:2:6:1", "1261"), false),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:09:01", "00901"), false),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他", "qt"), false),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期超过1年的合同", "zbqcq1n"), false)
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var View = (function () {
        function View() {
            this.mDataSet = new Util.Ajax("xlfkfstj_update.do");
            this.mComp = Util.CompanyType.SBGS;
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (echartIdFDW, echartIdGW, echartIdNW, fdwTableId, gwTableId, nwTableId, year, month) {
            this.mYear = year;
            this.mMonth = month;
            this.echartIdFDW = echartIdFDW;
            this.echartIdGW = echartIdGW;
            this.echartIdNW = echartIdNW;
            this.fdwTableId = fdwTableId;
            this.gwTableId = gwTableId;
            this.nwTableId = nwTableId;
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
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, companyId: this.mComp })
                .then(function (data) {
                var fktjData = data;
                _this.updateFdwTable(_this.fdwTableId, _this.fdwTableId + "_jqgrid_1234", JQGridAssistantFactory.createFdwTable(_this.fdwTableId + "_jqgrid_1234"), fktjData[0]);
                var rawData = [
                    ["集中招标"],
                    ["非集中招标"],
                    ["合计"]
                ];
                _this.updateTable(_this.gwTableId, _this.gwTableId + "_jqgrid_1234", JQGridAssistantFactory.createGwTable(_this.gwTableId + "_jqgrid_1234"), rawData, fktjData[1]);
                rawData = [
                    ["集中招标"],
                    ["非集中招标"],
                    ["合计"]
                ];
                _this.updateTable(_this.nwTableId, _this.nwTableId + "_jqgrid_1234", JQGridAssistantFactory.createNwTable(_this.nwTableId + "_jqgrid_1234"), rawData, fktjData[2], true);
                $('h1').text("线缆 " + _this.mYear + "年" + _this.mMonth + "月 付款方式统计");
                document.title = "线缆 " + _this.mYear + "年" + _this.mMonth + "月 付款方式统计";
                var chartDataFdw = [];
                for (var i = 0; i < fktjData[0].length - 1; ++i) {
                    chartDataFdw.push(parseFloat(fktjData[0][i][1]).toFixed(2));
                }
                _this.updatePieEchart(_this.echartIdFDW, "非电网合同订单总量", chartDataFdw);
                _this.updateEchart(_this.echartIdGW, "国网合同订单总量", [{ value: parseFloat(fktjData[1][0][1]).toFixed(2), name: '集中招标' },
                    { value: parseFloat(fktjData[1][1][1]).toFixed(2), name: '非集中招标' }]);
                _this.updateEchart(_this.echartIdNW, "南网合同订单总量", [{ value: parseFloat(fktjData[2][0][1]).toFixed(2), name: '集中招标' },
                    { value: parseFloat(fktjData[2][1][1]).toFixed(2), name: '非集中招标' }]);
            });
        };
        View.prototype.updatePieEchart = function (chartId, tileTex, data) {
            var chart = echarts.init($("#" + chartId)[0]);
            var legend = ["火电", "水电", "核电", "风电、光伏",
                "轨道交通", "石油石化", "煤炭煤化工", "钢铁冶金", "航天军工", "连锁经营", "其他"];
            var dataOut = [];
            var dykhTotal = 0;
            var fdlscTotal = 0;
            var legendData = [];
            for (var i = 0; i < legend.length; ++i) {
                if (parseInt(data[i]) > 0) {
                    legendData.push(legend[i]);
                    dataOut.push({ name: legend[i], value: parseInt(data[i]) });
                    if (i < 4) {
                        dykhTotal += parseInt(data[i]);
                    }
                    else {
                        fdlscTotal += parseInt(data[i]);
                    }
                }
            }
            var dataIn = [];
            if (dykhTotal > 0) {
                legendData.push("电源客户");
                dataIn.push({ name: "电源客户", value: dykhTotal });
            }
            if (fdlscTotal > 0) {
                legendData.push("非电力市场");
                dataIn.push({ name: "非电力市场", value: fdlscTotal });
            }
            var option = {
                title: {
                    text: "非电网合同订单总量",
                    x: 'right'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    x: "left",
                    data: legendData,
                    orient: "vertical"
                },
                calculable: false,
                series: [{
                        name: "行业占比",
                        type: 'pie',
                        minAngle: 2,
                        radius: [0, 60],
                        itemStyle: {
                            normal: {
                                label: {
                                    position: 'inner'
                                },
                                labelLine: {
                                    show: false
                                }
                            }
                        },
                        data: dataIn
                    },
                    {
                        name: "行业占比",
                        type: 'pie',
                        minAngle: 0.01,
                        radius: [100, 130],
                        data: dataOut
                    }
                ]
            };
            chart.setOption(option);
        };
        View.prototype.updateEchart = function (chartId, tileTex, data) {
            var chart = echarts.init($("#" + chartId)[0]);
            var legend = ["集中招标", "非集中招标"];
            var total = 0;
            var legendData = [];
            var realData = [];
            if (data[0].value > 0) {
                realData.push(data[0]);
                legendData.push(data[0].name);
            }
            if (data[1].value > 0) {
                realData.push(data[1]);
                legendData.push(data[1].name);
            }
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
                    data: legendData,
                    orient: "vertical"
                },
                toolbox: {
                    show: true,
                },
                calculable: false,
                series: [
                    {
                        name: "行业占比",
                        type: 'pie',
                        radius: '50%',
                        data: realData
                    }
                ]
            };
            chart.setOption(option);
        };
        View.prototype.updateTable = function (parentName, childName, tableAssist, data, rawData, shrink) {
            if (shrink === void 0) { shrink = false; }
            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (rawData[i] instanceof Array) {
                    row = [].concat(rawData[i]);
                    for (var col in row) {
                        if (col % 2 != 0) {
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
                shrinkToFit: shrink,
                autoScroll: true,
            }));
        };
        View.prototype.updateFdwTable = function (parentName, childName, tableAssist, rawData) {
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            tableAssist.mergeColum(0, 11);
            var data = [
                ["电源客户", "火电"],
                ["电源客户", "水电"],
                ["电源客户", "核电"],
                ["电源客户", "风电、光伏"],
                ["非电力市场", "轨道交通"],
                ["非电力市场", "石油石化"],
                ["非电力市场", "煤炭煤化工"],
                ["非电力市场", "钢铁冶金"],
                ["非电力市场", "航天军工"],
                ["非电力市场", "连锁经营"],
                ["非电力市场", "其他"],
                ["合", "计"]
            ];
            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (rawData[i] instanceof Array) {
                    row = [].concat(rawData[i]);
                    for (var col in row) {
                        if (col % 2 != 0) {
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
                shrinkToFit: false
                //                    autoScroll: true,
                //                    footerrow: true,
                //                    userDataOnFooter: true
            }));
        };
        return View;
    }());
    xl_fkfstj.View = View;
})(xl_fkfstj || (xl_fkfstj = {}));
