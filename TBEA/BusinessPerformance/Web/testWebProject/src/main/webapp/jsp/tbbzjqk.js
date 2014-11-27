var tbbzjqk;
(function (tbbzjqk) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, month) {
            var cols = [
                new JQTable.Node("月份", "yf", true, 0 /* Left */)
            ];
            for (var i = 0; i < month; ++i) {
                cols.push(new JQTable.Node((i + 1) + "月", i + "y"));
            }
            return new JQTable.JQGridAssistant(cols, gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mComp = 19 /* JT */;
            this.mData = [];
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (echartId, tableId, year) {
            this.mYear = year;
            this.mDataSet = new Util.DateDataSet("tbbzjqk_update.do");
            this.mTableId = tableId;
            this.mEchartId = echartId;
            this.updateTable();
            this.updateUI();
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.onCompanySelected = function (comp) {
            this.mComp = comp;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.getDataByYear(this.mYear, this.mComp, function (data) {
                if (null != data) {
                    _this.mData = JSON.parse(data);
                    $('h1').text(_this.mYear + "年 投标保证金情况");
                    document.title = _this.mYear + "年 投标保证金情况";
                    _this.updateTable();
                    _this.updateEchart();
                }
            });
        };
        View.prototype.updateEchart = function () {
            var tbbzjChart = echarts.init($("#" + this.mEchartId)[0]);
            var month = [];
            var currentYearData = [];
            var lastYearData = [];
            for (var i = 1; i <= 12; ++i) {
                month.push(i + "月");
                lastYearData.push(i);
                currentYearData.push(i + 12);
            }
            var data = [];
            data.push(lastYearData);
            data.push(currentYearData);
            var legend = [this.mYear - 1 + "年", this.mYear + "年"];
            var ser = [];
            for (var i = 0; i < legend.length; ++i) {
                ser.push({
                    name: legend[i],
                    type: 'line',
                    smooth: true,
                    data: data[i]
                });
            }
            var tbbzjOption = {
                tooltip: {
                    trigger: 'axis'
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
                        boundaryGap: false,
                        data: month
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: ser
            };
            tbbzjChart.setOption(tbbzjOption);
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name, this.mData.length);
            var data = [["余额"]];
            if (undefined != this.mData) {
                for (var i = 0; i < this.mData.length; ++i) {
                    data[0].push(Util.formatCurrency(this.mData[i]));
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
    tbbzjqk.View = View;
})(tbbzjqk || (tbbzjqk = {}));
