var yszkrb_view;
(function (yszkrb_view) {
    var YSZKColumnId;
    (function (YSZKColumnId) {
        YSZKColumnId[YSZKColumnId["JTZJHLZB"] = 0] = "JTZJHLZB";
        YSZKColumnId[YSZKColumnId["DWHKJH"] = 1] = "DWHKJH";
        YSZKColumnId[YSZKColumnId["JRHK"] = 2] = "JRHK";
        YSZKColumnId[YSZKColumnId["YLJ"] = 3] = "YLJ";
        YSZKColumnId[YSZKColumnId["HLZBWC"] = 4] = "HLZBWC";
        YSZKColumnId[YSZKColumnId["HLJHWCL"] = 5] = "HLJHWCL";
        YSZKColumnId[YSZKColumnId["KJYSZKHK"] = 6] = "KJYSZKHK";
        YSZKColumnId[YSZKColumnId["QBBC"] = 7] = "QBBC";
        YSZKColumnId[YSZKColumnId["ZQBC"] = 8] = "ZQBC";
        YSZKColumnId[YSZKColumnId["LZHJ"] = 9] = "LZHJ";
        YSZKColumnId[YSZKColumnId["QYQB"] = 10] = "QYQB";
        YSZKColumnId[YSZKColumnId["JHWCL"] = 11] = "JHWCL";
        YSZKColumnId[YSZKColumnId["JZZMYE"] = 12] = "JZZMYE";
    })(YSZKColumnId || (YSZKColumnId = {}));
    ;
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "t0", true, JQTable.TextAlign.Left),
                new JQTable.Node("集团下达月度资金回笼指标", "t1"),
                new JQTable.Node("各单位自行制定的回款计划", "t2"),
                new JQTable.Node("今日回款", "t3"),
                new JQTable.Node("月累计（截止今日）", "t4"),
                new JQTable.Node("资金回笼指标完成", "t5"),
                new JQTable.Node("回款计划完成率", "t6"),
                new JQTable.Node("已回款中可降应收的回款金额", "t7"),
                new JQTable.Node("目前-月底回款计划", "t8")
                    .append(new JQTable.Node("确保办出", "t81"))
                    .append(new JQTable.Node("争取办出", "t82"))
                    .append(new JQTable.Node("两者合计", "t83")),
                new JQTable.Node("全月确保", "t9"),
                new JQTable.Node("预计全月计划完成率", "t10"),
                new JQTable.Node("截止月底应收账款账面余额", "t11")
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var View = (function () {
        function View() {
            this.mDataSet = new Util.Ajax("yszk_update.do");
            this.mData = [];
        }
        View.newInstance = function () {
            return new View();
        };
        View.prototype.init = function (tableId, month, year, day) {
            var _this = this;
            this.mYear = year;
            this.mMonth = month;
            this.mTableId = tableId;
            this.mDay = day;
            $("#date").val(year + "/" + month + "/" + day);
            $("#date").datepicker({
                dateFormat: 'yy/mm/dd',
                yearSuffix: '年',
                showMonthAfterYear: true,
                defaultDate: year + "/" + month + "/" + day,
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
            $("#ui-datepicker-div").css('font-size', '0.8em');
            this.updateUI();
        };
        View.prototype.exportExcelYSDialy = function () {
            $("#exportYSDialy")[0].action = "yszk_view_export.do?" + Util.Ajax.toUrlParam({ year: this.mYear, month: this.mMonth, day: this.mDay });
            $("#exportYSDialy")[0].submit();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.get({ month: this.mMonth, year: this.mYear, day: this.mDay })
                .then(function (dataArray) {
                _this.mData = dataArray;
                $('h1').text(_this.mYear + "年" + _this.mMonth + "月" + _this.mDay + "日应收账款日报");
                document.title = _this.mYear + "年" + _this.mMonth + "月" + _this.mDay + "日应收账款日报";
                _this.updateTable();
            });
        };
        View.prototype.initPercentList = function () {
            var precentList = new std.vector();
            precentList.push(YSZKColumnId.HLZBWC);
            precentList.push(YSZKColumnId.HLJHWCL);
            precentList.push(YSZKColumnId.JHWCL);
            return precentList;
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name);
            var outputData = [];
            Util.formatData(outputData, this.mData, this.initPercentList(), [], 0);
            var data = [
                ["沈变公司"],
                ["衡变公司"],
                ["新变厂"],
                ["其中：天变公司"],
                ["鲁缆公司"],
                ["新缆厂"],
                ["德缆公司"],
                ["输变电小计"],
                ["新特能源公司"],
                ["新能源"],
                ["新能源小计"],
                ["天池能源"],
                ["能动公司"],
                ["能源小计"],
                ["进出口公司"],
                ["国际工程公司"],
                ["工程小计"],
                ["众和公司"],
                ["集团合计"]
            ];
            var row = [];
            for (var i = 0; i < outputData.length; ++i) {
                row = [].concat(outputData[i]);
                data[i] = data[i].concat(row);
                if (data[i][0].lastIndexOf("计") >= 0) {
                    tableAssist.setRowBgColor(i, 183, 222, 232);
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
                height: "100%",
                width: 1330,
                shrinkToFit: true,
                rowNum: 200,
                autoScroll: true
            }));
            $("#export").css('display', 'block');
        };
        return View;
    }());
    yszkrb_view.View = View;
})(yszkrb_view || (yszkrb_view = {}));
