var xl_fkfstj;
(function (xl_fkfstj) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createSubNode = function (parent) {
            return parent.append(new JQTable.Node("笔数", "bs")).append(new JQTable.Node("金额", "je"));
        };
        JQGridAssistantFactory.createFdwTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "title", true, 0 /* Left */),
                new JQTable.Node("", "title1", true, 0 /* Left */),
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
                new JQTable.Node("", "title", true, 0 /* Left */),
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
                new JQTable.Node("", "title", true, 0 /* Left */),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("南网合同订单总量", "gwhtddzl")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:6:2:1", "1621")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("1:2:6:1", "1261")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("0:09:01", "00901")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("其他", "qt")),
                JQGridAssistantFactory.createSubNode(new JQTable.Node("质保期超过1年的合同", "zbqcq1n"))
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
        View.prototype.init = function (echartIdFDW, echartIdGW, echartIdNW, fdwTableId, gwTableId, nwTableId, fdwData, gwData, nwData) {
            this.updateFdwTable(fdwTableId, fdwTableId + "_jqgrid_1234", JQGridAssistantFactory.createFdwTable(fdwTableId + "_jqgrid_1234"), fdwData);
            var rawData = [
                ["集中招标"],
                ["非集中招标"],
                ["合计"]
            ];
            this.updateTable(gwTableId, gwTableId + "_jqgrid_1234", JQGridAssistantFactory.createGwTable(gwTableId + "_jqgrid_1234"), rawData, gwData);
            this.updateTable(nwTableId, nwTableId + "_jqgrid_1234", JQGridAssistantFactory.createNwTable(nwTableId + "_jqgrid_1234"), rawData, nwData);
            this.updatePieEchart(echartIdFDW, "非电网合同订单总量", [651654.32, 514613.95, 111984.61, 564895.41, 416516.54, 651654.32, 514613.95, 111984.61, 564895.41, 416516.54, 487519.32]);
            this.updateEchart(echartIdGW, "国网合同订单总量", [{ value: 466446.34, name: '集中招标' }, { value: 487519.32, name: '非集中招标' }]);
            this.updateEchart(echartIdNW, "南网合同订单总量", [{ value: 865146.13, name: '集中招标' }, { value: 955648.95, name: '非集中招标' }]);
        };
        View.prototype.updatePieEchart = function (chartId, tileTex, data) {
            var chart = echarts.init($("#" + chartId)[0]);
            var legend = ["火电", "水电", "核电", "风电、光伏", "轨道交通", "石油石化", "煤炭煤化工", "钢铁冶金", "航天军工", "连锁经营", "其他"];
            var dataOut = [];
            var dykhTotal = 0;
            var fdlscTotal = 0;
            for (var i = 0; i < legend.length; ++i) {
                dataOut.push({ name: legend[i], value: parseInt(data[i]) });
                if (i < 4) {
                    dykhTotal += parseInt(data[i]);
                }
                else {
                    fdlscTotal += parseInt(data[i]);
                }
            }
            var dataIn = [{ name: "电源客户", value: dykhTotal }, { name: "非电力市场", value: fdlscTotal }];
            var option = {
                tooltip: {
                    trigger: 'item'
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
                        radius: [100, 130],
                        data: dataOut
                    },
                    {
                        type: 'pie',
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
                    }
                ]
            };
            chart.setOption(option);
        };
        View.prototype.updateEchart = function (chartId, tileTex, data) {
            var chart = echarts.init($("#" + chartId)[0]);
            var legend = ["集中招标", "非集中招标"];
            var total = 0;
            var option = {
                title: {
                    text: tileTex,
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item'
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
                        if (col % 2 != 0) {
                            row[col] = Util.formatCurrency(row[col]);
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
                shrinkToFit: false,
                autoScroll: true
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
                shrinkToFit: false
            }));
        };
        return View;
    })();
    xl_fkfstj.View = View;
})(xl_fkfstj || (xl_fkfstj = {}));
