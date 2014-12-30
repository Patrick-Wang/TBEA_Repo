var ztyszkfx;
(function (ztyszkfx) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createCurrentYearNode = function (year) {
            return new JQTable.Node(year + "年", "n" + year).append(new JQTable.Node("本月账面应收账款余额", "byzmyszkye", true, 1 /* Right */, 100)).append(new JQTable.Node("本月保理控制余额", "byblkzye", true, 1 /* Right */, 100)).append(new JQTable.Node("本月应收账款实际数", "byyszksjs", true, 1 /* Right */, 100)).append(new JQTable.Node("本月收入", "bysr", true, 1 /* Right */, 100)).append(new JQTable.Node("账面应收占收入比例", "zmyszsrbl", true, 1 /* Right */, 100));
        };
        JQGridAssistantFactory.createPreYearNode = function (year) {
            return new JQTable.Node(year + "年", "n" + year).append(new JQTable.Node("去年同期账面应收账款余额", "qntqzmyszkye", true, 1 /* Right */, 100)).append(new JQTable.Node("去年同期保理余额", "qntqblye", true, 1 /* Right */, 100)).append(new JQTable.Node("去年同期应收账款实际数", "qntqyszksjs", true, 1 /* Right */, 100)).append(new JQTable.Node("去年同期收入", "qntqsr", true, 1 /* Right */, 100)).append(new JQTable.Node("账面应收占收入比", "zmyszsrb", true, 1 /* Right */, 100));
        };
        JQGridAssistantFactory.createTable = function (gridName, year) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "dw", true, 0 /* Left */, 90),
                JQGridAssistantFactory.createCurrentYearNode(year),
                JQGridAssistantFactory.createPreYearNode(year - 1),
                new JQTable.Node("同比增长", "tbzz").append(new JQTable.Node("账面余额较去年同期增长比", "zmyejqntqzzb", true, 1 /* Right */, 100)).append(new JQTable.Node("保理较去年同期增长比", "bljqntqzzb", true, 1 /* Right */, 100)).append(new JQTable.Node("实际应收较去年同期增长比", "sjysjqntqzzb", true, 1 /* Right */, 100)).append(new JQTable.Node("收入较去年同期增长比", "sujqntqzzb", true, 1 /* Right */, 100))
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
        View.prototype.init = function (echartId, tableId, year) {
            this.mYear = year;
            this.mDataSet = new Util.DateDataSet("ztyszkfx_update.do");
            this.mTableId = tableId;
            this.mEchartId = echartId;
            this.updateTable();
            this.updateUI();
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.getDataByYearOnly(this.mYear, function (arrayData) {
                if (null != arrayData) {
                    _this.mData = arrayData;
                    $('h1').text(_this.mYear + "年 整体应收账款分析表");
                    document.title = _this.mYear + "年 整体应收账款分析表";
                    _this.updateTable();
                    _this.updateEchart();
                }
            });
        };
        View.prototype.updateEchart = function () {
            var ztyszkfxChart = echarts.init($("#" + this.mEchartId)[0]);
            var month = [];
            for (var i = 1; i <= 12; ++i) {
                month.push(i + "月");
            }
            var legend = ["账面应收账款余额", "保理控制余额", "应收账款实际数", "累计收入", "账面应收占收入比"];
            var zmysData = [41982, 31876, 51975, 43856, 61498, 32696, 38574, 62641, 28434, 51114, 41563, 68415];
            var blkzData = [29167, 21401, 47155, 32584, 52523, 19573, 24652, 50217, 17426, 43018, 37107, 60047];
            var yssjData = [49841, 57498, 34574, 87756, 85353, 57772, 23587, 54536, 48478, 67488, 99837, 10760];
            var ljsrData = [47291, 67214, 14715, 53258, 45252, 31957, 32465, 25021, 71742, 64301, 83710, 76004];
            var yszbData = [];
            for (var i = 0; i < zmysData.length; i++) {
                yszbData.push((zmysData[i] / (ljsrData[i] / (i + 1) * 12)).toFixed(2));
            }
            var ztyszkfxOption = {
                title: {
                    text: '整体应收账款分析'
                },
                tooltip: {
                    trigger: 'axis',
                    formatter: function (v) {
                        return v[0][1] + '<br/>' + v[0][0] + ' : ' + v[0][2] + '<br/>' + v[1][0] + ' : ' + v[1][2] + '<br/>' + v[2][0] + ' : ' + v[2][2] + '<br/>' + v[3][0] + ' : ' + v[3][2] + '<br/>' + v[4][0] + ' : ' + v[4][2] + '%';
                    }
                },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,
                        data: month
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
                    {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'line',
                        smooth: true,
                        data: zmysData
                    },
                    {
                        name: legend[1],
                        type: 'line',
                        smooth: true,
                        data: blkzData
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        data: yssjData
                    },
                    {
                        name: legend[3],
                        type: 'line',
                        smooth: true,
                        data: ljsrData
                    },
                    {
                        name: legend[4],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: yszbData
                    }
                ]
            };
            ztyszkfxChart.setOption(ztyszkfxOption);
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name, this.mYear);
            var data = [["沈变"], ["衡变"], ["新变"], ["天变"], ["变压器合计"], ["鲁缆"], ["新缆"], ["德缆"], ["线缆合计"], ["产业集团合计"]];
            if (undefined != this.mData) {
                for (var i = 0; i < this.mData.length && i < data.length; ++i) {
                    var row = [];
                    for (var j = 0; j < this.mData[i].length; ++j) {
                        if ((this.mData[i][j] + "").indexOf("%") < 0) {
                            row.push(Util.formatCurrency(this.mData[i][j]));
                        }
                        else {
                            row.push(this.mData[i][j]);
                        }
                    }
                    data[i] = data[i].concat(row);
                }
            }
            tableAssist.setRowBgColor(4, 183, 222, 232);
            tableAssist.setRowBgColor(8, 183, 222, 232);
            tableAssist.setRowBgColor(9, 183, 222, 232);
            var parent = $("#" + this.mTableId);
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
                width: 1300,
                shrinkToFit: true,
                autoScroll: true
            }));
        };
        return View;
    })();
    ztyszkfx.View = View;
})(ztyszkfx || (ztyszkfx = {}));
