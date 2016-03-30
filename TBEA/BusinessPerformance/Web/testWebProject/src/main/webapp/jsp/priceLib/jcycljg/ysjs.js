/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
var ysjs;
(function (ysjs) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("日期", "rq", true),
                new JQTable.Node("长江现货（元/吨）", "cjxh")
                    .append(new JQTable.Node("铜", "xhcu"))
                    .append(new JQTable.Node("吕", "xhal"))
                    .append(new JQTable.Node("锌", "xhzn")),
                new JQTable.Node("LME结算价（美元/吨）", "cjxh")
                    .append(new JQTable.Node("铜", "LEMcu"))
                    .append(new JQTable.Node("吕", "LEMal"))
                    .append(new JQTable.Node("锌", "LEMzn"))
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
        View.prototype.init = function (opt) {
            this.mOpt = opt;
            this.mDataSet = new Util.Ajax("ysjs/update.do", false);
            this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3, month: 1 }, {
                year: this.mOpt.date.year,
                month: this.mOpt.date.month
            }, this.mOpt.dt);
            this.mDateSelector.select(this.mOpt.date);
            //var dt:Util.Date = Util.addMonth(this.mOpt.date, -1);
            //$("#" + this.mOpt.dts).val(this.mOpt.date.year + "-" + this.mOpt.date.month);
            //$("#" + this.mOpt.dte).val(dt.year + "-" + dt.month + "-" + dt.day);
            //var option = {
            //    //            numberOfMonths:1,//显示几个月
            //    //            showButtonPanel:true,//是否显示按钮面板
            //    dateFormat: 'yy-mm',
            //    //            clearText:"清除",//清除日期的按钮名称
            //    //            closeText:"关闭",//关闭选择框的按钮名称
            //    yearSuffix: '年',
            //    showMonthAfterYear: true,
            //    defaultDate: 2012 + "-" + 11 + "-" + 11,
            //    minDate:'2011-03-05',//最小日期
            //    maxDate: this.mOpt.date.year + "-" + this.mOpt.date.month,//最大日期
            //    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            //    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            //    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            //    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            //    onSelect: (selectedDate, e) => {//选择日期后执行的操作
            //        this.mCurrentDate = new Date(selectedDate);
            //    }
            //}
            //$("#" + this.mOpt.dts).datepicker(option);
            //$("#" + this.mOpt.dte).datepicker(option);
            this.updateUI();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDateSelector.toString();
            this.mDataSet.get({ start: this.mDateSelector.toString() + "-1", end: this.mDateSelector.toString() + "-" + this.mDateSelector.monthDays() })
                .then(function (jsonData) {
                _this.mData = jsonData;
                _this.updateTable();
                _this.updateCuChart();
                _this.updateAlChart();
                _this.updateZnChart();
            });
        };
        View.prototype.updateCuChart = function () {
            var _this = this;
            var data = [];
            var lemData = [];
            $(this.mData).each(function (i) {
                data.push(_this.mData[i][1]);
                lemData.push(_this.mData[i][4]);
            });
            this.updateEchart("铜 结算价格趋势", this.mOpt.cu, data, lemData);
        };
        View.prototype.updateAlChart = function () {
            var _this = this;
            var data = [];
            var lemData = [];
            $(this.mData).each(function (i) {
                data.push(_this.mData[i][2]);
                lemData.push(_this.mData[i][5]);
            });
            this.updateEchart("铝 结算价格趋势", this.mOpt.al, data, lemData);
        };
        View.prototype.updateZnChart = function () {
            var _this = this;
            var data = [];
            var lemData = [];
            $(this.mData).each(function (i) {
                data.push(_this.mData[i][3]);
                lemData.push(_this.mData[i][6]);
            });
            this.updateEchart("锌 结算价格趋势", this.mOpt.zn, data, lemData);
        };
        View.prototype.updateEchart = function (title, echart, data, lemData) {
            var _this = this;
            var xData = [];
            $(this.mData).each(function (i) {
                xData.push(_this.mData[i][0]);
            });
            var option = {
                title: {
                    text: title
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ["长江现货价格", "LME结算价格"]
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: xData.length < 1 ? [0] : xData
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: "长江现货价格",
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 0,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: data.length < 1 ? [0] : data
                    },
                    {
                        name: "LME结算价格",
                        type: 'line',
                        yAxisIndex: 1,
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: lemData.length < 1 ? [0] : lemData
                    }
                ]
            };
            echarts.init($('#' + echart)[0]).setOption(option);
        };
        View.prototype.updateTable = function () {
            var name = this.mOpt.tb + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name);
            var parent = $("#" + this.mOpt.tb);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: 1200,
                shrinkToFit: true,
                autoScroll: true,
                data: tableAssist.getData(this.mData),
                datatype: "local"
            }));
        };
        return View;
    })();
    ysjs.View = View;
})(ysjs || (ysjs = {}));
var view = ysjs.View.newInstance();
