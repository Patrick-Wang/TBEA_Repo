/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
var xjlrb;
(function (xjlrb) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位名称", "dwnc", true, JQTable.TextAlign.Left),
                new JQTable.Node("本日流入", "brlr"),
                new JQTable.Node("本月累计流入", "byljlr"),
                new JQTable.Node("本年累计流入", "bnljlr"),
                new JQTable.Node("本日流出", "brlc"),
                new JQTable.Node("本月累计流出", "byljlc"),
                new JQTable.Node("本年累计流出", "bnljlc"),
                new JQTable.Node("本日净流量", "brjll"),
                new JQTable.Node("本月累计净流量", "byljjll"),
                //new JQTable.Node("截止上月末报表累计调整数", "bbbytzs"),
                new JQTable.Node("本年累计净流量", "bnljjll")
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("xjlrb_update.do");
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };
        View.prototype.init = function (tableId, month, year, day) {
            var _this = this;
            this.mYear = year;
            this.mMonth = month;
            this.mTableId = tableId;
            this.mDay = day;
            $("#date").val(year + "/" + month + "/" + day);
            $("#date").datepicker({
                //            numberOfMonths:1,//显示几个月  
                //            showButtonPanel:true,//是否显示按钮面板  
                dateFormat: 'yy/mm/dd',
                //            clearText:"清除",//清除日期的按钮名称  
                //            closeText:"关闭",//关闭选择框的按钮名称  
                yearSuffix: '年',
                showMonthAfterYear: true,
                defaultDate: year + "/" + month + "/" + day,
                //            minDate:'2011-03-05',//最小日期  
                maxDate: year + "/" + month + "/" + day,
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                onSelect: function (selectedDate) {
                    var d = new Date(selectedDate);
                    _this.mYear = d.getFullYear();
                    _this.mMonth = d.getMonth() + 1;
                    _this.mDay = d.getDate();
                }
            });
            $("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;
            this.updateTable();
            this.updateUI();
        };
        View.prototype.onDaySelected = function (day) {
            this.mDay = day;
        };
        View.prototype.onYearSelected = function (year) {
            this.mYear = year;
        };
        View.prototype.onMonthSelected = function (month) {
            this.mMonth = month;
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, day: this.mDay })
                .then(function (dataArray) {
                _this.mData = dataArray;
                $('h1').text(_this.mYear + "年" + _this.mMonth + "月" + _this.mDay + "日 现金流日报");
                document.title = _this.mYear + "年" + _this.mMonth + "月" + _this.mDay + "日 现金流日报";
                _this.updateTable();
            });
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name);
            var data = [["沈变公司"],
                ["衡变公司"],
                ["新变厂"],
                ["天变公司"],
                ["鲁缆公司"],
                ["新缆厂"],
                ["德缆公司"],
                ["输变电小计"],
                ["新能源公司"],
                ["新特能源公司"],
                ["新能源小计"],
                ["天池能源公司"],
                ["能动公司"],
                ["能源小计"],
                ["进出口"],
                ["国际工程公司"],
                ["工程小计"],
                ["机关本部"],
                ["采购中心"],
                ["资金中心"],
                ["公司机关小计"],
                ["香港公司"],
                ["工业旅游"],
                ["股份公司合计"],
                ["众和公司"],
                ["合计"]];
            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (data[i][0].lastIndexOf("计") >= 0) {
                    tableAssist.setRowBgColor(i, 183, 222, 232);
                }
                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
                    for (var col in row) {
                        row[col] = Util.formatCurrency(row[col]);
                    }
                    data[i] = data[i].concat(row);
                }
            }
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                //                    cellsubmit: 'clientArray',
                //                    cellEdit: true,
                height: '100%',
                width: 1200,
                shrinkToFit: true,
                rowNum: 100,
                autoScroll: true
            }));
        };
        return View;
    })();
    xjlrb.View = View;
})(xjlrb || (xjlrb = {}));
