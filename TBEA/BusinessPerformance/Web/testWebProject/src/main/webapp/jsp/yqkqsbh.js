/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />

var yqkqsbh;
(function (yqkqsbh) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("月份", "yf", true, 150),
                new JQTable.Node("逾期一个月以内", "yqygyyn", true, 160),
                new JQTable.Node("逾期1-3月", "yqysy", true, 160),
                new JQTable.Node("逾期3-6月", "yqsly", true, 160),
                new JQTable.Node("逾期6-12月", "yqlsey", true, 160),
                new JQTable.Node("逾期一年以上", "yqynys", true, 160)
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

        View.prototype.init = function (echartId, tableId, args) {
            this.mMonth = args[0];
            this.mData = args[1];
            this.updateEchart(echartId);
            this.updateTable(tableId);
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
                //                total.push(0);
            }

            for (var j = 0; j < legend.length; ++j) {
                for (var i = 1; i <= this.mMonth; ++i) {
                    data[j].push(this.mData[i - 1][j]);
                }
            }

            //            for (var i = 1; i <= this.mMonth; ++i) {
            //                for (var j = 0; j < legend.length - 1; ++j) {
            //                    total[i - 1] += parseInt(data[j][i - 1]);
            //                }
            //            }
            //            for (var i = 1; i <= this.mMonth; ++i) {
            //                data[legend.length - 1].push(total[i - 1] + "");
            //            }
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
            var tableAssist = JQGridAssistantFactory.createTable(name);

            var data = [];
            var tmp = [];
            var row = [];
            for (var i = 0; i < this.mMonth; ++i) {
                tmp = [(i + 1) + "月份"];
                row = [].concat(this.mData[i]);
                for (var col in row) {
                    row[col] = Util.formatCurrency(row[col]);
                }
                data.push(tmp.concat(row));
            }
            tmp = ["合计"];
            row = [].concat(this.mData[this.mMonth]);
            for (var col in row) {
                row[col] = Util.formatCurrency(row[col]);
            }
            data.push(tmp.concat(row));

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
                width: 1000,
                shrinkToFit: true,
                autoScroll: true
            }));
        };
        return View;
    })();
    yqkqsbh.View = View;
})(yqkqsbh || (yqkqsbh = {}));
//# sourceMappingURL=yqkqsbh.js.map
