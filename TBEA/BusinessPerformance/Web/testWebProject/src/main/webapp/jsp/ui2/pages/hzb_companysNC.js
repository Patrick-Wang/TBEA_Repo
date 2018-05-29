/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
var hzb_companysNC;
(function (hzb_companysNC) {
    var router = framework.router;
    var ZtId;
    (function (ZtId) {
        ZtId[ZtId["zb"] = 0] = "zb";
        ZtId[ZtId["dysj"] = 1] = "dysj";
        ZtId[ZtId["dyqntq"] = 2] = "dyqntq";
        ZtId[ZtId["dytbzf"] = 3] = "dytbzf";
        ZtId[ZtId["ndlj"] = 4] = "ndlj";
        ZtId[ZtId["ndqntq"] = 5] = "ndqntq";
        ZtId[ZtId["ndtbzf"] = 6] = "ndtbzf";
    })(ZtId || (ZtId = {}));
    ;
    var JQGridAssistantFactory = /** @class */ (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("当月实际", "dysj", true, JQTable.TextAlign.Left),
                new JQTable.Node("去年同期", "qntq", true, JQTable.TextAlign.Left),
                new JQTable.Node("同比增幅", "tbzf", true, JQTable.TextAlign.Left),
                new JQTable.Node("年度累计", "ndlj", true, JQTable.TextAlign.Left),
                new JQTable.Node("去年同期累计", "qnndlj", true, JQTable.TextAlign.Left),
                new JQTable.Node("同比增幅", "ndtbzf", true, JQTable.TextAlign.Left)
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var SimpleView = /** @class */ (function () {
        function SimpleView() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("/BusinessManagement/NCzb/CompanysNC_update.do");
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
            this.mCompanySelector = new Util.CompanySelector(false, "comp-sel", opt.comps);
            var minDate = Util.addYear(opt.date, -1);
            minDate.month = 1;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月",
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
        SimpleView.prototype.exportExcel = function () {
            $("#export")[0].action = "/BusinessManagement/NCzb/CompanysNC_export.do?" + Util.Ajax.toUrlParam(this.getDate());
            $("#export")[0].submit();
        };
        SimpleView.prototype.updateUI = function () {
            var _this = this;
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get($.extend(this.getDate(), { companyId: compType }))
                .then(function (dataArray) {
                _this.mData = dataArray;
                if (dataArray.length == 0) {
                    var pro = $("#prompt");
                    pro.empty();
                    pro.append("<b>暂时没有数据！</b>");
                }
                else {
                    var pro = $("#prompt");
                    pro.empty();
                }
                _this.updateTable();
            });
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
        SimpleView.prototype.initPercentList = function () {
            var precentList = new std.vector();
            precentList.push(ZtId.dytbzf);
            precentList.push(ZtId.ndtbzf);
            return precentList;
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
            if (this.mData.length == 0) {
                return;
            }
            var outputData = [];
            Util.formatData(outputData, this.mData, this.initPercentList(), []);
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
    }());
    hzb_companysNC.SimpleView = SimpleView;
})(hzb_companysNC || (hzb_companysNC = {}));
