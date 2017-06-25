/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
var hzb_zbhz;
(function (hzb_zbhz) {
    var router = framework.router;
    var SrqyId;
    (function (SrqyId) {
        SrqyId[SrqyId["zb"] = 0] = "zb";
        SrqyId[SrqyId["qnjh"] = 1] = "qnjh";
        SrqyId[SrqyId["dyjh"] = 2] = "dyjh";
        SrqyId[SrqyId["dysj"] = 3] = "dysj";
        SrqyId[SrqyId["jhwcl"] = 4] = "jhwcl";
        SrqyId[SrqyId["ljwc"] = 5] = "ljwc";
        SrqyId[SrqyId["ljwcl"] = 6] = "ljwcl";
        SrqyId[SrqyId["qntqz"] = 7] = "qntqz";
        SrqyId[SrqyId["tbzzl"] = 8] = "tbzzl";
        SrqyId[SrqyId["qntqlj"] = 9] = "qntqlj";
        SrqyId[SrqyId["ljtbzzl"] = 10] = "ljtbzzl";
    })(SrqyId || (SrqyId = {}));
    ;
    var ZtId;
    (function (ZtId) {
        ZtId[ZtId["zb"] = 0] = "zb";
        ZtId[ZtId["qnjh"] = 1] = "qnjh";
        ZtId[ZtId["dyjh"] = 2] = "dyjh";
        ZtId[ZtId["dysj"] = 3] = "dysj";
        ZtId[ZtId["dyjhwcl"] = 4] = "dyjhwcl";
        ZtId[ZtId["dyqntq"] = 5] = "dyqntq";
        ZtId[ZtId["dytbzf"] = 6] = "dytbzf";
        ZtId[ZtId["jdjh"] = 7] = "jdjh";
        ZtId[ZtId["jdlj"] = 8] = "jdlj";
        ZtId[ZtId["jdjhwcl"] = 9] = "jdjhwcl";
        ZtId[ZtId["jdqntq"] = 10] = "jdqntq";
        ZtId[ZtId["jdtbzf"] = 11] = "jdtbzf";
        ZtId[ZtId["ndlj"] = 12] = "ndlj";
        ZtId[ZtId["ndljjhwcl"] = 13] = "ndljjhwcl";
        ZtId[ZtId["ndqntq"] = 14] = "ndqntq";
        ZtId[ZtId["ndtbzf"] = 15] = "ndtbzf";
    })(ZtId || (ZtId = {}));
    ;
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createSrqyTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("当期", "dq")
                    .append(new JQTable.Node("全年计划", "1"))
                    .append(new JQTable.Node("当月计划", "2"))
                    .append(new JQTable.Node("当月实际", "3"))
                    .append(new JQTable.Node("计划完成率", "4"))
                    .append(new JQTable.Node("累计完成", "5"))
                    .append(new JQTable.Node("累计完成率", "6")),
                new JQTable.Node("去年", "qn")
                    .append(new JQTable.Node("去年同期值", "7"))
                    .append(new JQTable.Node("同比增长率", "8"))
                    .append(new JQTable.Node("去年同期累计", "9"))
                    .append(new JQTable.Node("同比增长率", "10"))
            ], gridName);
        };
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
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
    })();
    var SimpleView = (function () {
        function SimpleView() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("/BusinessManagement/ydzb/hzb_zbhz_update.do");
            this.mXmgsDataSet = new Util.Ajax("/BusinessManagement/ydzb/hzb_zbhz_xmgs_compute.do", false);
            this.mJydwDataSet = new Util.Ajax("/BusinessManagement/ydzb/hzb_zbhz_jydw_compute.do", false);
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
            $(window).resize(function () {
                _this.adjustSize();
            });
            $("#grid-update").on("click", function () {
                _this.updateUI();
            });
            $("#export-all").on("click", function () {
                _this.exportExcelJydw();
            });
            $("#export-xmgs").on("click", function () {
                _this.exportExcelXmgs();
            });
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
            this.mDataSet.get($.extend(this.getDate(), { type: $("#grid-type").val() }))
                .then(function (dataArray) {
                _this.mData = dataArray;
                _this.updateTable();
            });
        };
        SimpleView.prototype.exportExcelJydw = function () {
            var fName = $("#grid-date").val() + "经营单位整体指标完成情况";
            this.mJydwDataSet.get($.extend(this.getDate(), {
                type: $("#grid-type").val(),
                fileName: fName
            })).then(function (tmStamp) {
                $("#exportJydw")[0].action = "/BusinessManagement/ydzb/general_export.do?" + Util.Ajax.toUrlParam({ timeStamp: tmStamp.timeStamp });
                $("#exportJydw")[0].submit();
            });
        };
        SimpleView.prototype.exportExcelXmgs = function () {
            var fName = $("#grid-date").val() + "项目公司整体指标完成情况";
            this.mXmgsDataSet.get($.extend(this.getDate(), {
                type: $("#grid-type").val(),
                fileName: fName
            })).then(function (tmStamp) {
                $("#exportxmgs")[0].action = "/BusinessManagement/ydzb/general_export.do?" + Util.Ajax.toUrlParam({ timeStamp: tmStamp.timeStamp });
                $("#exportxmgs")[0].submit();
            });
        };
        SimpleView.prototype.initZTPercentList = function () {
            var precentList = new std.vector();
            precentList.push(ZtId.dyjhwcl);
            precentList.push(ZtId.dytbzf);
            precentList.push(ZtId.jdjhwcl);
            precentList.push(ZtId.jdtbzf);
            precentList.push(ZtId.ndljjhwcl);
            precentList.push(ZtId.ndtbzf);
            return precentList;
        };
        SimpleView.prototype.initSrqyPercentList = function () {
            var precentList = new std.vector();
            precentList.push(SrqyId.jhwcl);
            precentList.push(SrqyId.ljwcl);
            precentList.push(SrqyId.tbzzl);
            precentList.push(SrqyId.ljtbzzl);
            return precentList;
        };
        SimpleView.prototype.adjustSize = function () {
            var jqgrid = this.jqgrid();
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
            var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            this.tableAssist.resizeHeight(maxTableBodyHeight);
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
            if (0 == $("#grid-type").val()) {
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
            }
            else {
                this.tableAssist = JQGridAssistantFactory.createSrqyTable(this.jqgridName());
            }
            return this.tableAssist;
        };
        SimpleView.prototype.updateTable = function () {
            if (this.mData.length == 0) {
                return;
            }
            this.createJqassist();
            var outputData = [];
            if (0 == $("#grid-type").val()) {
                Util.formatData(outputData, this.mData, this.initZTPercentList(), []);
            }
            else {
                Util.formatData(outputData, this.mData, this.initSrqyPercentList(), []);
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
    hzb_zbhz.SimpleView = SimpleView;
})(hzb_zbhz || (hzb_zbhz = {}));
