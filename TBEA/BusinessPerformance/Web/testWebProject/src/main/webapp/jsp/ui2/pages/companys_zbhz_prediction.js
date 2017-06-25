/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
var companys_zbhz_prediction;
(function (companys_zbhz_prediction) {
    var router = framework.router;
    var FirstMonthZb;
    (function (FirstMonthZb) {
        FirstMonthZb[FirstMonthZb["zb"] = 0] = "zb";
        FirstMonthZb[FirstMonthZb["ndjh"] = 1] = "ndjh";
        FirstMonthZb[FirstMonthZb["bjdjh"] = 2] = "bjdjh";
        FirstMonthZb[FirstMonthZb["byjhz"] = 3] = "byjhz";
        FirstMonthZb[FirstMonthZb["dyyjz"] = 4] = "dyyjz";
        FirstMonthZb[FirstMonthZb["dyjhwcl"] = 5] = "dyjhwcl";
        FirstMonthZb[FirstMonthZb["dyqntq"] = 6] = "dyqntq";
        FirstMonthZb[FirstMonthZb["dytbzf"] = 7] = "dytbzf";
        FirstMonthZb[FirstMonthZb["cyyj"] = 8] = "cyyj";
        FirstMonthZb[FirstMonthZb["myyj"] = 9] = "myyj";
        FirstMonthZb[FirstMonthZb["jdyjhj"] = 10] = "jdyjhj";
        FirstMonthZb[FirstMonthZb["jdyjwcl"] = 11] = "jdyjwcl";
        FirstMonthZb[FirstMonthZb["jdqntq"] = 12] = "jdqntq";
        FirstMonthZb[FirstMonthZb["jdtbzf"] = 13] = "jdtbzf";
        FirstMonthZb[FirstMonthZb["ndljwcz"] = 14] = "ndljwcz";
        FirstMonthZb[FirstMonthZb["ndzbwcl"] = 15] = "ndzbwcl";
        FirstMonthZb[FirstMonthZb["ndqntqz"] = 16] = "ndqntqz";
        FirstMonthZb[FirstMonthZb["ndtbzf"] = 17] = "ndtbzf";
    })(FirstMonthZb || (FirstMonthZb = {}));
    ;
    var SecondMonthZb;
    (function (SecondMonthZb) {
        SecondMonthZb[SecondMonthZb["zb"] = 0] = "zb";
        SecondMonthZb[SecondMonthZb["ndjh"] = 1] = "ndjh";
        SecondMonthZb[SecondMonthZb["jdjh"] = 2] = "jdjh";
        SecondMonthZb[SecondMonthZb["byjhz"] = 3] = "byjhz";
        SecondMonthZb[SecondMonthZb["dyyjz"] = 4] = "dyyjz";
        SecondMonthZb[SecondMonthZb["dyjhwcl"] = 5] = "dyjhwcl";
        SecondMonthZb[SecondMonthZb["dyqntq"] = 6] = "dyqntq";
        SecondMonthZb[SecondMonthZb["dytbzf"] = 7] = "dytbzf";
        SecondMonthZb[SecondMonthZb["jdlj"] = 8] = "jdlj";
        SecondMonthZb[SecondMonthZb["jdjhwcl"] = 9] = "jdjhwcl";
        SecondMonthZb[SecondMonthZb["jdqntqz"] = 10] = "jdqntqz";
        SecondMonthZb[SecondMonthZb["jdtbzf"] = 11] = "jdtbzf";
        SecondMonthZb[SecondMonthZb["jdmyyj"] = 12] = "jdmyyj";
        SecondMonthZb[SecondMonthZb["jdyjhj"] = 13] = "jdyjhj";
        SecondMonthZb[SecondMonthZb["jdyjwcl"] = 14] = "jdyjwcl";
        SecondMonthZb[SecondMonthZb["jdyjqntq"] = 15] = "jdyjqntq";
        SecondMonthZb[SecondMonthZb["jdyjtbzf"] = 16] = "jdyjtbzf";
        SecondMonthZb[SecondMonthZb["ndljwcz"] = 17] = "ndljwcz";
        SecondMonthZb[SecondMonthZb["ndzbwcl"] = 18] = "ndzbwcl";
        SecondMonthZb[SecondMonthZb["ndqntqz"] = 19] = "ndqntqz";
        SecondMonthZb[SecondMonthZb["ndtbzf"] = 20] = "ndtbzf";
    })(SecondMonthZb || (SecondMonthZb = {}));
    ;
    var ThirdMonthZb;
    (function (ThirdMonthZb) {
        ThirdMonthZb[ThirdMonthZb["zb"] = 0] = "zb";
        ThirdMonthZb[ThirdMonthZb["ndjh"] = 1] = "ndjh";
        ThirdMonthZb[ThirdMonthZb["bjdjh"] = 2] = "bjdjh";
        ThirdMonthZb[ThirdMonthZb["xjdjh"] = 3] = "xjdjh";
        ThirdMonthZb[ThirdMonthZb["dyjhz"] = 4] = "dyjhz";
        ThirdMonthZb[ThirdMonthZb["dyyjz"] = 5] = "dyyjz";
        ThirdMonthZb[ThirdMonthZb["dyjhwcl"] = 6] = "dyjhwcl";
        ThirdMonthZb[ThirdMonthZb["dyqntq"] = 7] = "dyqntq";
        ThirdMonthZb[ThirdMonthZb["dytbzf"] = 8] = "dytbzf";
        ThirdMonthZb[ThirdMonthZb["jdlj"] = 9] = "jdlj";
        ThirdMonthZb[ThirdMonthZb["jdjhwcl"] = 10] = "jdjhwcl";
        ThirdMonthZb[ThirdMonthZb["jdqntqz"] = 11] = "jdqntqz";
        ThirdMonthZb[ThirdMonthZb["jdtbzf"] = 12] = "jdtbzf";
        ThirdMonthZb[ThirdMonthZb["ndljwcz"] = 13] = "ndljwcz";
        ThirdMonthZb[ThirdMonthZb["ndzbwcl"] = 14] = "ndzbwcl";
        ThirdMonthZb[ThirdMonthZb["ndqntqz"] = 15] = "ndqntqz";
        ThirdMonthZb[ThirdMonthZb["ndtbzf"] = 16] = "ndtbzf";
        ThirdMonthZb[ThirdMonthZb["xjdsyyj"] = 17] = "xjdsyyj";
        ThirdMonthZb[ThirdMonthZb["xjdcyyj"] = 18] = "xjdcyyj";
        ThirdMonthZb[ThirdMonthZb["xjdmyyj"] = 19] = "xjdmyyj";
        ThirdMonthZb[ThirdMonthZb["xjdyjhj"] = 20] = "xjdyjhj";
        ThirdMonthZb[ThirdMonthZb["xjdyjwcl"] = 21] = "xjdyjwcl";
        ThirdMonthZb[ThirdMonthZb["xjdndlj"] = 22] = "xjdndlj";
        ThirdMonthZb[ThirdMonthZb["xjdndljwcl"] = 23] = "xjdndljwcl";
        ThirdMonthZb[ThirdMonthZb["xjdqntq"] = 24] = "xjdqntq";
        ThirdMonthZb[ThirdMonthZb["xjdtbzf"] = 25] = "xjdtbzf";
    })(ThirdMonthZb || (ThirdMonthZb = {}));
    ;
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, gridStyle) {
            if (1 == gridStyle) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度计划", "ndjh"),
                    new JQTable.Node("本季度计划", "jdjh"),
                    new JQTable.Node("当月完成", "dywc")
                        .append(new JQTable.Node("本月计划值", "y1"))
                        .append(new JQTable.Node("当月预计值", "y2"))
                        .append(new JQTable.Node("计划完成率", "y3"))
                        .append(new JQTable.Node("去年同期", "y4"))
                        .append(new JQTable.Node("同比增幅", "y5")),
                    new JQTable.Node("季度预计完成", "jdyjwc")
                        .append(new JQTable.Node("次月预计", "j1"))
                        .append(new JQTable.Node("末月预计", "j2"))
                        .append(new JQTable.Node("季度预计合计", "j3"))
                        .append(new JQTable.Node("季度预计完成率", "j4"))
                        .append(new JQTable.Node("去年同期", "j5"))
                        .append(new JQTable.Node("同比增幅", "j6")),
                    new JQTable.Node("年度累计完成", "nd")
                        .append(new JQTable.Node("累计完成值", "n1"))
                        .append(new JQTable.Node("年度指标完成率", "n2"))
                        .append(new JQTable.Node("去年同期值", "n3"))
                        .append(new JQTable.Node("同比增幅", "n4"))
                ], gridName);
            }
            if (2 == gridStyle) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度计划", "ndjh"),
                    new JQTable.Node("本季度计划", "jdjh"),
                    new JQTable.Node("当月完成", "dywc")
                        .append(new JQTable.Node("本月计划值", "y1"))
                        .append(new JQTable.Node("当月预计值", "y2"))
                        .append(new JQTable.Node("计划完成率", "y3"))
                        .append(new JQTable.Node("去年同期", "y4"))
                        .append(new JQTable.Node("同比增幅", "y5")),
                    new JQTable.Node("季度累计完成", "jdljwc")
                        .append(new JQTable.Node("季度累计", "jl1"))
                        .append(new JQTable.Node("季度计划完成率", "jl2"))
                        .append(new JQTable.Node("去年同期值", "jl3"))
                        .append(new JQTable.Node("同比增幅", "jl4")),
                    new JQTable.Node("季度预计完成", "jdyjwc")
                        .append(new JQTable.Node("末月预计", "jy1"))
                        .append(new JQTable.Node("季度预计合计", "jy2"))
                        .append(new JQTable.Node("季度预计完成率", "jy3"))
                        .append(new JQTable.Node("去年同期", "jy4"))
                        .append(new JQTable.Node("同比增幅", "jy5")),
                    new JQTable.Node("年度累计完成", "nd")
                        .append(new JQTable.Node("累计完成值", "n1"))
                        .append(new JQTable.Node("年度指标完成率", "n2"))
                        .append(new JQTable.Node("去年同期值", "n3"))
                        .append(new JQTable.Node("同比增幅", "n4"))
                ], gridName);
            }
            if (3 == gridStyle) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度计划", "ndjh"),
                    new JQTable.Node("本季度计划", "jdjh"),
                    new JQTable.Node("下季度计划", "xjdjh"),
                    new JQTable.Node("当月完成", "dywc")
                        .append(new JQTable.Node("本月计划值", "y1"))
                        .append(new JQTable.Node("当月预计值", "y2"))
                        .append(new JQTable.Node("计划完成率", "y3"))
                        .append(new JQTable.Node("去年同期", "y4"))
                        .append(new JQTable.Node("同比增幅", "y5")),
                    new JQTable.Node("季度累计完成", "jdljwc")
                        .append(new JQTable.Node("季度累计", "jl1"))
                        .append(new JQTable.Node("季度计划完成率", "jl2"))
                        .append(new JQTable.Node("去年同期值", "jl3"))
                        .append(new JQTable.Node("同比增幅", "jl4")),
                    new JQTable.Node("年度累计完成", "nd")
                        .append(new JQTable.Node("累计完成值", "n1"))
                        .append(new JQTable.Node("年度指标完成率", "n2"))
                        .append(new JQTable.Node("去年同期值", "n3"))
                        .append(new JQTable.Node("同比增幅", "n4")),
                    new JQTable.Node("下季度预计完成", "xjdyjwc")
                        .append(new JQTable.Node("下季度首月预计", "xjy1"))
                        .append(new JQTable.Node("下季度次月预计", "xjy2"))
                        .append(new JQTable.Node("下季度末月预计", "xjy3"))
                        .append(new JQTable.Node("季度预计合计", "xjy4"))
                        .append(new JQTable.Node("季度预计完成率", "xjy5"))
                        .append(new JQTable.Node("年度累计", "xjy6"))
                        .append(new JQTable.Node("年度累计完成率", "xjy7"))
                        .append(new JQTable.Node("去年同期", "xjy8"))
                        .append(new JQTable.Node("同比增幅", "xjy9"))
                ], gridName);
            }
        };
        return JQGridAssistantFactory;
    })();
    var SimpleView = (function () {
        function SimpleView() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("/BusinessManagement/ydzb/hzb_companys_prediction_update.do");
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
                format: "YYYY年 && $$MM月",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(minDate),
                maxDate: Util.date2Str(opt.date),
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top")
                .addClass("season-month");
            this.mCompanySelector = new Util.CompanySelector(false, "comp-sel", opt.comps);
            this.mCompanySelector.change(function () {
                $("#headerHost").removeCss("width");
                if ($("#headerHost").height() > 40) {
                    $(".page-header").addClass("page-header-double");
                    $("#headerHost").css("width", $("#comp-sel").width() + "px");
                }
                else {
                    $(".page-header").removeClass("page-header-double");
                }
            });
            $(window).resize(function () {
                $("#headerHost").removeCss("width");
                if ($("#headerHost").height() > 40) {
                    $(".page-header").addClass("page-header-double");
                    $("#headerHost").css("width", $("#comp-sel").width() + "px");
                }
                else {
                    $(".page-header").removeClass("page-header-double");
                }
                _this.adjustSize();
            });
            $("#grid-update").on("click", function () {
                _this.updateUI();
            });
            $("#grid-export").on("click", function () {
                _this.exportExcel();
            });
            $("#headerHost").removeCss("width");
            if ($("#headerHost").height() > 40) {
                $(".page-header").addClass("page-header-double");
                $("#headerHost").css("width", $("#comp-sel").width() + "px");
            }
            else {
                $(".page-header").removeClass("page-header-double");
            }
            this.updateUI();
        };
        SimpleView.prototype.getDate = function () {
            var curDate = $("#grid-date").getDate();
            return {
                year: curDate.getFullYear(),
                month: curDate.getMonth() + 1,
                day: curDate.getDate()
            };
        };
        SimpleView.prototype.updateUI = function () {
            var _this = this;
            /*  this.mActualMonth = (parseInt($("#grid-season").val()) - 1) * 3 + parseInt($("#grid-season-month").val());*/
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get($.extend(this.getDate(), { companyId: compType }))
                .then(function (dataArray) {
                _this.mData = dataArray;
                _this.updateTable();
            });
        };
        SimpleView.prototype.exportExcel = function () {
            /*  this.mActualMonth = (parseInt($("#grid-season").val()) - 1) * 3 + parseInt($("#grid-season-month").val());*/
            var compType = this.mCompanySelector.getCompany();
            $("#grid-export")[0].action = "hzb_companys_prediction_export.do?" + Util.Ajax.toUrlParam($.extend(this.getDate(), { companyId: compType }));
            $("#grid-export")[0].submit();
        };
        SimpleView.prototype.adjustSize = function () {
            var jqgrid = this.jqgrid();
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
            var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 38 - 42 - $(".page-breadcrumbs").height() - $(".page-header").height();
            this.tableAssist.resizeHeight(maxTableBodyHeight);
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
            $(".page-container").css("height", ($(".page-breadcrumbs").height() + $(".page-header").height() + $(".page-body").height()) + "px");
        };
        SimpleView.prototype.jqgrid = function () {
            return $("#" + this.jqgridName());
        };
        SimpleView.prototype.jqgridName = function () {
            return this.mOpt.tableId + "_jqgrid_real";
        };
        SimpleView.prototype.formatFirstMonthData = function (outputData) {
            var precentList = new std.vector();
            precentList.push(FirstMonthZb.dyjhwcl);
            precentList.push(FirstMonthZb.dytbzf);
            precentList.push(FirstMonthZb.jdyjwcl);
            precentList.push(FirstMonthZb.jdtbzf);
            precentList.push(FirstMonthZb.ndzbwcl);
            precentList.push(FirstMonthZb.ndtbzf);
            return Util.formatData(outputData, this.mData, precentList, []);
        };
        SimpleView.prototype.formatSecondMonthData = function (outputData) {
            var precentList = new std.vector();
            precentList.push(SecondMonthZb.dyjhwcl);
            precentList.push(SecondMonthZb.dytbzf);
            precentList.push(SecondMonthZb.jdjhwcl);
            precentList.push(SecondMonthZb.jdyjtbzf);
            precentList.push(SecondMonthZb.jdyjwcl);
            precentList.push(SecondMonthZb.jdtbzf);
            precentList.push(SecondMonthZb.ndzbwcl);
            precentList.push(SecondMonthZb.ndtbzf);
            return Util.formatData(outputData, this.mData, precentList, []);
        };
        SimpleView.prototype.formatThirdMonthData = function (outputData) {
            var precentList = new std.vector();
            precentList.push(ThirdMonthZb.dyjhwcl);
            precentList.push(ThirdMonthZb.dytbzf);
            precentList.push(ThirdMonthZb.jdjhwcl);
            precentList.push(ThirdMonthZb.jdtbzf);
            precentList.push(ThirdMonthZb.ndzbwcl);
            precentList.push(ThirdMonthZb.ndtbzf);
            precentList.push(ThirdMonthZb.xjdyjwcl);
            precentList.push(ThirdMonthZb.xjdndljwcl);
            precentList.push(ThirdMonthZb.xjdtbzf);
            return Util.formatData(outputData, this.mData, precentList, []);
        };
        SimpleView.prototype.createJqassist = function () {
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='" + this.jqgridName() + "'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), (1 + (this.getDate().month - 1) % 3));
            return this.tableAssist;
        };
        SimpleView.prototype.updateTable = function () {
            this.createJqassist();
            var date = this.getDate();
            var outputData = [];
            if (1 == (1 + (date.month - 1) % 3)) {
                this.formatFirstMonthData(outputData);
            }
            else if (2 == (1 + (date.month - 1) % 3)) {
                this.formatSecondMonthData(outputData);
            }
            else if (3 == (1 + (date.month - 1) % 3)) {
                this.formatThirdMonthData(outputData);
            }
            for (var i = 0; i < outputData.length; ++i) {
                if (outputData[i][0].lastIndexOf("计") >= 0 && outputData[i][0].lastIndexOf("审计") < 0) {
                    this.tableAssist.setRowBgColor(i, 183, 222, 232);
                }
            }
            this.tableAssist.create({
                data: outputData,
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
    })();
    companys_zbhz_prediction.SimpleView = SimpleView;
})(companys_zbhz_prediction || (companys_zbhz_prediction = {}));
