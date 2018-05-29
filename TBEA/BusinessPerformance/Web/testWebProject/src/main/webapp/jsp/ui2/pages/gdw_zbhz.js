/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
var gdw_zbhz;
(function (gdw_zbhz) {
    var router = framework.router;
    var DwZb;
    (function (DwZb) {
        DwZb[DwZb["qnjh"] = 0] = "qnjh";
        DwZb[DwZb["dyjh"] = 1] = "dyjh";
        DwZb[DwZb["dysj"] = 2] = "dysj";
        DwZb[DwZb["dyjhwcl"] = 3] = "dyjhwcl";
        DwZb[DwZb["dyqntq"] = 4] = "dyqntq";
        DwZb[DwZb["dytbzf"] = 5] = "dytbzf";
        DwZb[DwZb["jdjh"] = 6] = "jdjh";
        DwZb[DwZb["jdlj"] = 7] = "jdlj";
        DwZb[DwZb["jdjhwcl"] = 8] = "jdjhwcl";
        DwZb[DwZb["jdqntq"] = 9] = "jdqntq";
        DwZb[DwZb["jdtbzf"] = 10] = "jdtbzf";
        DwZb[DwZb["ndlj"] = 11] = "ndlj";
        DwZb[DwZb["ndljjhwcl"] = 12] = "ndljjhwcl";
        DwZb[DwZb["ndqntq"] = 13] = "ndqntq";
        DwZb[DwZb["ndtbzf"] = 14] = "ndtbzf";
    })(DwZb || (DwZb = {}));
    ;
    var JQGridAssistantFactory = /** @class */ (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("公司", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("全年计划", "qnjh"),
                new JQTable.Node("月度", "yd")
                    .append(new JQTable.Node("当月计划", "y1"))
                    .append(new JQTable.Node("当月实际", "y2"))
                    .append(new JQTable.Node("计划完成率", "y3"))
                    .append(new JQTable.Node("去年同期", "y4"))
                    .append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度", "jd")
                    .append(new JQTable.Node("季度计划", "j1"))
                    .append(new JQTable.Node("季度累计", "j2"))
                    .append(new JQTable.Node("季度计划完成率", "j3"))
                    .append(new JQTable.Node("去年同期", "j4"))
                    .append(new JQTable.Node("同比增幅", "j5")),
                new JQTable.Node("年度", "nd")
                    .append(new JQTable.Node("年度累计", "n1"))
                    .append(new JQTable.Node("累计计划完成率", "n2"))
                    .append(new JQTable.Node("去年同期", "n3"))
                    .append(new JQTable.Node("同比增幅", "n4"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var SimpleView = /** @class */ (function () {
        function SimpleView() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("/BusinessManagement/ydzb/gdw_zbhz_update.do");
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
            var minDate = Util.addYear(opt.date, -3);
            minDate.month = 1;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(minDate),
                maxDate: Util.date2Str(opt.date),
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");
            $("#zk").on("click", function () {
                window.parent ? window.parent.triggerClickUrl("/BusinessManagement/report/v2/zk.do") : undefined;
            });
            $(window).resize(function () {
                _this.adjustSize();
            });
            $("#grid-update").on("click", function () {
                _this.updateUI();
            });
            $("#grid-export").on("click", function () {
                _this.exportExcel();
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
            this.mDataSet.get($.extend(this.getDate(), { zbId: $("#grid-type").val() }))
                .then(function (dataArray) {
                _this.mData = dataArray;
                _this.updateTable();
            });
        };
        SimpleView.prototype.exportExcel = function () {
            $("#exportExcel")[0].action = "/BusinessManagement/ydzb/gdw_zbhz_export.do?" + Util.Ajax.toUrlParam($.extend(this.getDate(), { top5index: $("#grid-type").val(), zbName: $("#grid-type  option:selected").text() }));
            $("#exportExcel")[0].submit();
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
            this.tableAssist.mergeRow(0);
            //for (var i = 0; i < 5; ++i) {
            //    this.tableAssist.setRowBgColor(i * 8 + 5, 183, 222, 232);
            //    this.tableAssist.setRowBgColor(i * 8 + 7, 183, 222, 232);
            //}
            return this.tableAssist;
        };
        SimpleView.prototype.updateTable = function () {
            this.createJqassist();
            var data = [
                ["沈变公司"],
                ["衡变公司"],
                ["新变厂"],
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
                ["股份公司小计"],
                ["众和公司"],
                ["集团合计"]
            ];
            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (data[i][0].lastIndexOf("计") >= 0) {
                    this.tableAssist.setRowBgColor(i, 183, 222, 232);
                }
                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
                    for (var j = 0; j < row.length; ++j) {
                        if (j == DwZb.dyjhwcl || j == DwZb.dytbzf ||
                            j == DwZb.jdjhwcl || j == DwZb.jdtbzf ||
                            j == DwZb.ndljjhwcl || j == DwZb.ndtbzf) {
                            row[j] = Util.formatPercent(row[j]);
                        }
                        else {
                            row[j] = Util.formatCurrency(row[j]);
                        }
                    }
                    data[i] = data[i].concat(row);
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
    gdw_zbhz.SimpleView = SimpleView;
})(gdw_zbhz || (gdw_zbhz = {}));
