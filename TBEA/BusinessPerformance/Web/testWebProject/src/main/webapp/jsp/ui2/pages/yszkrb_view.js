/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
var yszkrb_view;
(function (yszkrb_view) {
    var router = framework.router;
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
    var JQGridAssistantFactory = /** @class */ (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("单位", "t0", true, JQTable.TextAlign.Left),
                new JQTable.Node("应收账款指标", "t1"),
                new JQTable.Node("回款计划", "t2"),
                new JQTable.Node("其中：确保款项", "t3"),
                new JQTable.Node("争取款项", "t4"),
                new JQTable.Node("上月应收余额", "t5"),
                new JQTable.Node("今日新增应收账款", "t6"),
                new JQTable.Node("本月累计新增应收", "t7"),
                new JQTable.Node("今日回款", "t8"),
                new JQTable.Node("累计回款", "t9"),
                new JQTable.Node("回款完成率", "t10"),
                new JQTable.Node("累计可降应收回款", "t11"),
                new JQTable.Node("今日应收账款余额", "t12"),
                new JQTable.Node("应收账款完成率", "t13"),
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var SimpleView = /** @class */ (function () {
        function SimpleView() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("/BusinessManagement/yszkrb/yszkrb_update.do");
            router.register(this);
        }
        SimpleView.prototype.getId = function () {
            return Util.FAMOUS_VIEW;
        };
        SimpleView.prototype.onEvent = function (e) {
            switch (e.id) {
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
                case Util.MSG_UPDATE:
                    this.updateUI();
                    break;
            }
        };
        SimpleView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            var minDate = Util.addYear(opt.date, -1);
            minDate.month = 1;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月DD日",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");
            $(window).resize(function () {
                _this.adjustSize();
            });
            $("#grid-update").on("click", function () {
                _this.updateUI();
            });
            $("#grid-export").on("click", function () {
                _this.exportExcelYSDialy();
            });
            this.updateUI();
        };
        SimpleView.prototype.getDate = function () {
            var rq = $("#grid-date").val().replace("年", "-").replace("月", "-").replace("日", "-").split("-");
            return {
                year: rq[0] ? parseInt(rq[0]) : undefined,
                month: rq[1] ? parseInt(rq[1]) : undefined,
                day: rq[2] ? parseInt(rq[2]) : undefined
            };
        };
        SimpleView.prototype.updateUI = function () {
            var _this = this;
            this.mDataSet.get(this.getDate())
                .then(function (dataArray) {
                _this.mData = dataArray;
                _this.updateTable();
            });
        };
        SimpleView.prototype.exportExcelYSDialy = function () {
            $("#exportExcel")[0].action = "/BusinessManagement/yszkrb/yszk_view_export.do?" + Util.Ajax.toUrlParam(this.getDate());
            $("#exportExcel")[0].submit();
        };
        SimpleView.prototype.initPercentList = function () {
            var precentList = new std.vector();
            if (this.mData.length == 1) {
                precentList.push(YSZKColumnId.HLZBWC + 1);
                precentList.push(YSZKColumnId.HLJHWCL + 1);
                precentList.push(YSZKColumnId.JHWCL + 1);
            }
            else {
                precentList.push(YSZKColumnId.HLZBWC);
                precentList.push(YSZKColumnId.HLJHWCL);
                precentList.push(YSZKColumnId.JHWCL);
            }
            return precentList;
        };
        SimpleView.prototype.adjustSize = function () {
            var jqgrid = this.jqgrid();
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
            var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
        };
        SimpleView.prototype.jqgrid = function () {
            return $("#" + this.jqgridName());
        };
        SimpleView.prototype.jqgridName = function () {
            return this.mOpt.tableId + "_jqgrid_real";
        };
        SimpleView.prototype.createJqassist = function () {
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='" + this.jqgridName() + "'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
            return this.tableAssist;
        };
        SimpleView.prototype.updateTable = function () {
            if (this.mData.length == 0) {
                return;
            }
            this.createJqassist();
            var outputData = [];
            var data = [[]];
            if (this.mData.length == 1) {
                data = this.mData;
                /*Util.formatData(outputData, this.mData, this.initPercentList(), [], 0);*/
            }
            else {
                /*Util.formatData(outputData, this.mData, this.initPercentList(), [], 0);*/
                data = [
                    ["沈变公司"],
                    ["衡变公司"],
                    ["新变厂"],
                    ["其中：天变公司"],
                    ["鲁缆公司"],
                    ["新缆厂"],
                    ["德缆公司"],
                    ["输变电小计"],
                    ["新能源"],
                    ["新特能源公司"],
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
                for (var i = 0; i < this.mData.length; i++) {
                    for (var j = 0; j < this.mData[i].length; j++) {
                        data[i][j + 1] = this.mData[i][j];
                    }
                }
            }
            for (var i = 0; i < data.length; ++i) {
                if (data[i][0].lastIndexOf("计") >= 0) {
                    this.tableAssist.setRowBgColor(i, 183, 222, 232);
                }
            }
            this.tableAssist.create({
                data: data,
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: $("#" + this.mOpt.tableId).width(),
                shrinkToFit: true,
                rowNum: 2000,
                autoScroll: true
            });
            this.adjustSize();
        };
        SimpleView.ins = new SimpleView();
        return SimpleView;
    }());
    yszkrb_view.SimpleView = SimpleView;
})(yszkrb_view || (yszkrb_view = {}));
