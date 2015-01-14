var syhkjhzxqk;
(function (syhkjhzxqk) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("款项性质", "kxxz", true, 0 /* Left */),
                new JQTable.Node("款项性质", "kxxz_1", true, 0 /* Left */),
                new JQTable.Node("计划回款", "jhhk"),
                new JQTable.Node("实际回款", "sjhk"),
                new JQTable.Node("计划完成率", "jhwcl")
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mComp = 1 /* HB */;
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (echartId, tableId, month, year) {
            this.mYear = year;
            this.mMonth = month;
            this.mDataSet = new Util.DateDataSet("syhkjhzxqk_update.do");
            this.mTableId = tableId;
            this.mEchartId = echartId;
            this.updateTable();
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
                    _this.mData = JSON.parse(data);
                    $('h1').text(_this.mYear + "年" + _this.mMonth + "月 回款计划执行情况");
                    document.title = _this.mYear + "年" + _this.mMonth + "月 回款计划执行情况";
                    _this.updateTable();
                    _this.updateEchart();
                }
            });
        };
        View.prototype.getMonth = function () {
            var month = [];
            for (var i = 0; i < 12; ++i) {
                month.push((i + 1) + "月");
            }
            return month;
        };
        View.prototype.updateEchart = function () {
            var zxqkChart = echarts.init($("#" + this.mEchartId)[0]);
            var month = this.getMonth();
            var legend = ["计划回款", "实际回款", "计划完成率"];
            var jhData = [];
            var sjData = [];
            var wclData = [];
            for (var i = 0; i < this.mData[1].length; ++i) {
                jhData.push(this.mData[1][i][0]);
                sjData.push(this.mData[1][i][1]);
                wclData.push((parseFloat(this.mData[1][i][2]) * 100).toFixed(2));
            }
            var zxqkOption = {
                title: {
                    text: '回款计划执行情况'
                },
                tooltip: {
                    trigger: 'axis',
                    formatter: function (v) {
                        return v[0][1] + '<br/>' + v[0][0] + ' : ' + v[0][2] + '<br/>' + v[1][0] + ' : ' + v[1][2] + '<br/>' + v[2][0] + ' : ' + v[2][2] + '%';
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
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: jhData
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: sjData
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: wclData
                    }
                ]
            };
            zxqkChart.setOption(zxqkOption);
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeRow(0);
            tableAssist.mergeColum(0, 6);
            tableAssist.mergeColum(0, 7);
            tableAssist.mergeColum(0, 8);
            tableAssist.mergeColum(0, 9);
            tableAssist.mergeTitle();
            var data = [["按款项状态分", "未到期应收账款"], ["按款项状态分", "逾期款应收账款"], ["按款项状态分", "未到期款"], ["按款项状态分", "逾期款"], ["按清收性质分", "确保可回款"], ["按清收性质分", "争取可回款"], ["小", "计"], ["现款现", "货回款"], ["计划外", "回款"], ["合", "计"]];
            if (this.mData != undefined) {
                for (var i = 0; i < data.length; ++i) {
                    for (var j = 0; j < this.mData[0][i].length; ++j) {
                        if (j < 2) {
                            data[i].push(Util.formatCurrency(this.mData[0][i][j]));
                        }
                        else {
                            data[i].push((this.mData[0][i][j]));
                        }
                    }
                }
            }
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
                width: '100%'
            }));
        };
        return View;
    })();
    syhkjhzxqk.View = View;
})(syhkjhzxqk || (syhkjhzxqk = {}));
