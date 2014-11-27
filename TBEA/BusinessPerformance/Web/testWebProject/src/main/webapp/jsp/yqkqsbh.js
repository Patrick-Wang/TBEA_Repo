var yqkqsbh;
(function (yqkqsbh) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("月份", "yf", true, 0 /* Left */),
                new JQTable.Node("逾期一个月以内", "yqygyyn"),
                new JQTable.Node("逾期1-3月", "yqysy"),
                new JQTable.Node("逾期3-6月", "yqsly"),
                new JQTable.Node("逾期6-12月", "yqlsey"),
                new JQTable.Node("逾期一年以上", "yqynys")
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mComp = 19 /* JT */;
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (echartId, tableId, args) {
            this.mMonth = args[0];
            this.mYear = args[1];
            this.mTableId = tableId;
            this.mCharId = echartId;
            this.mDataSet = new Util.DateDataSet("yqkbhqs_update.do");
            this.updateTable(tableId);
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
            this.mDataSet.getDataByCompany(this.mMonth, this.mYear, this.mComp, function (data) {
                if (null != data) {
                    _this.mData = JSON.parse(data);
                    $('h1').text(_this.mYear + "年  逾期款趋势变化表");
                    document.title = _this.mYear + "年  逾期款趋势变化表";
                    _this.updateEchart(_this.mCharId);
                    _this.updateTable(_this.mTableId);
                }
            });
        };
        View.prototype.updateEchart = function (echart) {
            var legend = ["一个月以内", "1-3月", "3-6月", "6-12月", "一年以上"];
            var month = [];
            var data = [];
            for (var i = 0; i < legend.length; ++i) {
                data.push([]);
            }
            for (var i = 1; i <= this.mMonth; ++i) {
                month.push(i + "月");
            }
            for (var j = 0; j < legend.length; ++j) {
                for (var i = 1; i <= this.mMonth; ++i) {
                    data[j].push(this.mData[i - 1][j]);
                }
            }
            var ser = [];
            for (var i = 0; i < legend.length; ++i) {
                ser.push({
                    name: legend[i],
                    type: 'line',
                    smooth: true,
                    stack: "金额",
                    itemStyle: { normal: { areaStyle: { type: 'default' } } },
                    data: data[i]
                });
            }
            var option = {
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
            echarts.init($('#' + echart)[0]).setOption(option);
        };
        View.prototype.updateTable = function (name) {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name);
            var data = [];
            var tmp = [];
            var row = [];
            for (var i = 0; i < this.mMonth; ++i) {
                tmp = [(i + 1) + "月份"];
                if (undefined != this.mData) {
                    row = [].concat(this.mData[i]);
                    for (var col in row) {
                        row[col] = Util.formatCurrency(row[col]);
                    }
                }
                data.push(tmp.concat(row));
            }
            tmp = ["合计"];
            if (undefined != this.mData) {
                row = [].concat(this.mData[this.mMonth]);
                for (var col in row) {
                    row[col] = Util.formatCurrency(row[col]);
                }
            }
            data.push(tmp.concat(row));
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: 1000,
                shrinkToFit: true,
                autoScroll: true
            }));
        };
        return View;
    })();
    yqkqsbh.View = View;
})(yqkqsbh || (yqkqsbh = {}));
