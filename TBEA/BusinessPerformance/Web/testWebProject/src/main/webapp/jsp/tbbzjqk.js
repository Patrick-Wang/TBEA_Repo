/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />

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

        View.prototype.init = function (tableId, year) {
            this.mYear = year;
            this.mDataSet = new Util.DateDataSet("tbbzjqk_update.do");
            this.mTableId = tableId;
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
                }
            });
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
                width: '100%'
            }));
        };
        return View;
    })();
    tbbzjqk.View = View;
})(tbbzjqk || (tbbzjqk = {}));
//# sourceMappingURL=tbbzjqk.js.map
