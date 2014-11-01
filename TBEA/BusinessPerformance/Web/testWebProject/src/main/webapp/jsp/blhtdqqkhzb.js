/// <reference path="jqgrid/jqassist.ts" />

var blhtdqqkhzb;
(function (blhtdqqkhzb) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createPreNode = function (month) {
            var node;
            if (month >= 1) {
                node = new JQTable.Node(month + "月末保理余额", "ymblye" + month);
            } else {
                node = new JQTable.Node(12 + "月末保理余额", "ymblye" + 12);
            }

            return node.append(new JQTable.Node("非客户付息式保理余额", "fkhfxsblye")).append(new JQTable.Node("客户付息式保理余额", "khfxsblye"));
        };

        JQGridAssistantFactory.createNextNode = function (month, andlater) {
            if (typeof andlater === "undefined") { andlater = false; }
            var node;
            var title = andlater ? "月及以后" : "月";
            if (month > 12) {
                node = new JQTable.Node((month - 12) + title, "y" + (month - 12));
            } else {
                node = new JQTable.Node(month + title, "y" + month);
            }

            return node.append(new JQTable.Node("到期保理金额", "dqblje")).append(new JQTable.Node("到期保理中已回款金额", "dqblzyhkje"));
        };

        JQGridAssistantFactory.createTable = function (gridName, month) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("保理到期月份", "bldqyf"),
                new JQTable.Node("保理到期月份", "bldqyf_1"),
                JQGridAssistantFactory.createPreNode(month - 1),
                JQGridAssistantFactory.createNextNode(month),
                JQGridAssistantFactory.createNextNode(month + 1),
                JQGridAssistantFactory.createNextNode(month + 2),
                JQGridAssistantFactory.createNextNode(month + 3),
                JQGridAssistantFactory.createNextNode(month + 4, true)
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
            this.mYear = args[1];
            this.mChartData = args[2];
            this.mTableData = args[3];
            this.updateEchart(echartId);
            this.updateTable(tableId);
        };

        View.prototype.fillData = function (month) {
            if (this.mChartData == undefined) {
                this.mChartData.push([]);
                this.mChartData.push([]);
                for (var i = 1; i <= this.mMonth; ++i) {
                    month.push(i + "月");
                    this.mChartData[0].push(Math.floor(Math.random() * (1000 + 1)) + "");
                    this.mChartData[1].push(Math.floor(Math.random() * (1000 + 1)) + "");
                }
            } else {
                for (var i = 1; i <= this.mMonth; ++i) {
                    month.push(i + "月");
                }
            }
        };

        View.prototype.updateEchart = function (echart) {
            var month = [];

            this.fillData(month);
            var data = this.mChartData;
            var option = {
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: [this.mYear - 1 + "年", this.mYear + "年"]
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
                series: [
                    {
                        name: this.mYear - 1 + '年',
                        type: 'line',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: this.mYear + '年',
                        type: 'line',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    }
                ]
            };

            echarts.init($('#' + echart)[0]).setOption(option);
        };

        View.prototype.updateTable = function (name) {
            var tableAssist = JQGridAssistantFactory.createTable(name, this.mMonth);
            tableAssist.mergeTitle();
            tableAssist.mergeRow(0);
            var data = [
                ["保理合同到期情况", "金额"],
                ["保理合同到期情况", "份数"]
            ];
            for (var i = 0; i < data.length; ++i) {
                data[i] = data[i].concat(this.mTableData[i]);
            }

            $("#" + name).jqGrid(tableAssist.decorate({
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                width: 1000,
                shrinkToFit: false,
                autoScroll: true,
                data: tableAssist.getData(data),
                datatype: "local",
                loadComplete: function (resp) {
                    //var data: Array<string[]> = resp.data;
                }
            }));
        };
        return View;
    })();
    blhtdqqkhzb.View = View;
})(blhtdqqkhzb || (blhtdqqkhzb = {}));
//# sourceMappingURL=blhtdqqkhzb.js.map
