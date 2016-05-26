/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var tbbzjqk;
(function (tbbzjqk) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, month) {
            var cols = [
                new JQTable.Node("月份", "yf", true, JQTable.TextAlign.Left)
            ];
            for (var i = 0; i < month; ++i) {
                cols.push(new JQTable.Node((i + 1) + "月", i + "y"));
            }
            return new JQTable.JQGridAssistant(cols, gridName);
        };
        return JQGridAssistantFactory;
    }());
    var View = (function () {
        function View() {
            this.mComp = Util.CompanyType.HBGS;
            this.mData = [];
            this.mDataSet = new Util.Ajax("tbbzjqk_update.do");
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (echartId, tableId, year) {
            this.mYear = year;
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
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, companyId: this.mComp })
                .then(function (data) {
                _this.mData = data;
                $('h1').text(_this.mYear + "年 投标保证金情况");
                document.title = _this.mYear + "年 投标保证金情况";
                _this.updateTable();
                _this.updateEchart();
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
            var legend = [this.mYear + "年"];
            var ser = [];
            for (var i = 0; i < legend.length; ++i) {
                ser.push({
                    name: legend[i],
                    type: 'line',
                    smooth: true,
                    data: this.mData
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
                    show: true,
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
        //private initEchart(echart): void{
        //    var ysyq_payment_Chart = echarts.init(echart);
        //	var ysyq_payment_Option = {
        //			animation:true,
        //		tooltip:{
        //            trigger : 'axis',
        //            /* formatter : "{b}<br/>{a} : {c} 万元<br/>{a1} : {c1} 万元", */
        //            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        //	            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
        //	        }   
        //        },
        //        legend:{
        //            x : 'right',
        //            data : [ "计划回款","实际回款","计划完成率" ]
        //        },
        //		xAxis : [ {
        //			type : 'category',
        //			data : [ "未到期应收账款","逾期款应收账款","未到期款","逾期款"]
        //		} ],
        //		yAxis : [ {
        //			type : 'value'
        //		} ,
        //        {
        //            type : 'value',
        //            min: 0,
        //            max: 100
        //        }],
        //		calculable : true,
        //		series : [ {
        //			name : '计划回款',
        //			type : 'bar',
        //			barCategoryGap: "50%",
        //			data : [ 63363.11, 55628.27, 58521.55, 69100.58]
        //		}, {
        //			name : '实际回款',
        //			type : 'bar',
        //			data : [ 50690.48, 50065.44, 58521.55, 58044.48]
        //		} ,{
        //			name : '计划完成率',
        //			type : 'line',
        //			yAxisIndex: 1,
        //			data : [80, 90, 100, 84]
        //		} ]
        //	};
        //	ysyq_payment_Chart.setOption(ysyq_payment_Option);
        //}
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name, 12);
            var data = [["余额"]];
            if (undefined != this.mData) {
                for (var i = 0; i < this.mData.length; ++i) {
                    data[0].push(Util.formatCurrency(this.mData[i]));
                }
                for (var i = this.mData.length; i < 12; ++i) {
                    data[0].push("0.00");
                }
            }
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                //url: "datasource/syhkjhzxqk.do",
                //datatype: "json",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: '100%',
                shrinkToFit: true,
                width: 12 * 90,
            }));
        };
        return View;
    }());
    tbbzjqk.View = View;
})(tbbzjqk || (tbbzjqk = {}));
