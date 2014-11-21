/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />

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
            this.mComp = 19 /* JT */;
            this.mData = [];
        }
        View.newInstance = function () {
            return new View();
        };

        View.prototype.init = function (tableId, month, year) {
            this.mYear = year;
            this.mMonth = month;
            this.mDataSet = new Util.DateDataSet("syhkjhzxqk_update.do");
            this.mTableId = tableId;
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
            var tableAssist = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeRow(0);
            tableAssist.mergeColum(0, 6);
            tableAssist.mergeColum(0, 7);
            tableAssist.mergeColum(0, 8);
            tableAssist.mergeColum(0, 9);
            tableAssist.mergeTitle();
            var data = [
                ["按款项状态分", "未到期应收账款"],
                ["按款项状态分", "逾期款应收账款"],
                ["按款项状态分", "未到期款"],
                ["按款项状态分", "逾期款"],
                ["按清收性质分", "确保可回款"],
                ["按清收性质分", "争取可回款"],
                ["小", "计"],
                ["现款现", "货回款"],
                ["计划外", "回款"],
                ["合", "计"]];

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
    syhkjhzxqk.View = View;
})(syhkjhzxqk || (syhkjhzxqk = {}));
//# sourceMappingURL=syhkjhzxqk.js.map
