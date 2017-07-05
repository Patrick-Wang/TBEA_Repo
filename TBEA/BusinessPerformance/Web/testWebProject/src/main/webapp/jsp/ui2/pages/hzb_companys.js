/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
var hzb_companys;
(function (hzb_companys) {
    var router = framework.router;
    var AllZb;
    (function (AllZb) {
        AllZb[AllZb["zb"] = 0] = "zb";
        AllZb[AllZb["qnjh"] = 1] = "qnjh";
        AllZb[AllZb["dyjh"] = 2] = "dyjh";
        AllZb[AllZb["dysj"] = 3] = "dysj";
        AllZb[AllZb["dyjhwcl"] = 4] = "dyjhwcl";
        AllZb[AllZb["dyqntq"] = 5] = "dyqntq";
        AllZb[AllZb["dytbzf"] = 6] = "dytbzf";
        AllZb[AllZb["jdjh"] = 7] = "jdjh";
        AllZb[AllZb["jdlj"] = 8] = "jdlj";
        AllZb[AllZb["jdjhwcl"] = 9] = "jdjhwcl";
        AllZb[AllZb["jdqntq"] = 10] = "jdqntq";
        AllZb[AllZb["jdtbzf"] = 11] = "jdtbzf";
        AllZb[AllZb["ndlj"] = 12] = "ndlj";
        AllZb[AllZb["ndljjhwcl"] = 13] = "ndljjhwcl";
        AllZb[AllZb["ndqntq"] = 14] = "ndqntq";
        AllZb[AllZb["ndtbzf"] = 15] = "ndtbzf";
    })(AllZb || (AllZb = {}));
    ;
    var HbZb;
    (function (HbZb) {
        HbZb[HbZb["zb"] = 0] = "zb";
        HbZb[HbZb["qnjh"] = 1] = "qnjh";
        HbZb[HbZb["dyjh"] = 2] = "dyjh";
        HbZb[HbZb["dysj"] = 3] = "dysj";
        HbZb[HbZb["dyjhwcl"] = 4] = "dyjhwcl";
        HbZb[HbZb["dysytq"] = 5] = "dysytq";
        HbZb[HbZb["dyhbzf"] = 6] = "dyhbzf";
        HbZb[HbZb["dytbzf"] = 7] = "dytbzf";
        HbZb[HbZb["jdjh"] = 8] = "jdjh";
        HbZb[HbZb["jdlj"] = 9] = "jdlj";
        HbZb[HbZb["jdjhwcl"] = 10] = "jdjhwcl";
        HbZb[HbZb["jdqntq"] = 11] = "jdqntq";
        HbZb[HbZb["jdtbzf"] = 12] = "jdtbzf";
        HbZb[HbZb["ndlj"] = 13] = "ndlj";
        HbZb[HbZb["ndljjhwcl"] = 14] = "ndljjhwcl";
        HbZb[HbZb["ndqntq"] = 15] = "ndqntq";
        HbZb[HbZb["ndtbzf"] = 16] = "ndtbzf";
    })(HbZb || (HbZb = {}));
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
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
            this.mDataSet = new Util.Ajax("/BusinessManagement/ydzb/hzb_companys_update.do");
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
            this.mCompanySelector = new Util.CompanySelector(false, "comp-sel", opt.comps);
            this.mCompanySelector.change(function () {
                _this.adjustHeader();
            });
            $(window).resize(function () {
                _this.adjustHeader();
                _this.adjustSize();
            });
            $("#grid-update").on("click", function () {
                _this.updateUI();
            });
            $("#grid-export").on("click", function () {
                _this.exportExcel();
            });
            this.adjustHeader();
            this.updateUI();
        };
        SimpleView.prototype.adjustHeader = function () {
            $("#headerHost").removeCss("width");
            if ($("#headerHost").height() > 40) {
                $(".page-header").addClass("page-header-double");
                $("#headerHost").css("width", $("#comp-sel").width() + "px");
            }
            else {
                $(".page-header").removeClass("page-header-double");
            }
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
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get($.extend(this.getDate(), { companyId: compType }))
                .then(function (dataArray) {
                _this.mData = dataArray;
                _this.updateTable();
            });
        };
        SimpleView.prototype.exportExcel = function () {
            var compType = this.mCompanySelector.getCompany();
            $("#exportExcel")[0].action = "/BusinessManagement/ydzb/companys_zbhz_export.do?" + Util.Ajax.toUrlParam($.extend(this.getDate(), { companyId: compType }));
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
            return this.tableAssist;
        };
        SimpleView.prototype.updateTable = function () {
            this.createJqassist();
            var outputData = [];
            Util.formatData(outputData, this.mData, this.initPercentList(), [AllZb.dysj,
                AllZb.dyqntq,
                AllZb.jdlj,
                AllZb.jdqntq,
                AllZb.ndlj,
                AllZb.ndqntq,
            ]);
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
        SimpleView.prototype.initPercentList = function () {
            var precentList = new std.vector();
            precentList.push(AllZb.dyjhwcl);
            precentList.push(AllZb.dytbzf);
            precentList.push(AllZb.jdjhwcl);
            precentList.push(AllZb.jdtbzf);
            precentList.push(AllZb.ndljjhwcl);
            precentList.push(AllZb.ndtbzf);
            return precentList;
        };
        SimpleView.ins = new SimpleView();
        return SimpleView;
    })();
    hzb_companys.SimpleView = SimpleView;
})(hzb_companys || (hzb_companys = {}));
