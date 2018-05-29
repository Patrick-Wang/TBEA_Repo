var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../messageBox.ts" />
///<reference path="../../dateSelector.ts"/>
var yszkgb;
(function (yszkgb) {
    var yszkkxxz;
    (function (yszkkxxz) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "aa", true, TextAlign.Center),
                    new JQTable.Node("月度", "a1a", true, TextAlign.Center),
                    new JQTable.Node("月度", "ab", true, TextAlign.Center)
                        .append(new JQTable.Node("逾期0-1个月", "ba"))
                        .append(new JQTable.Node("逾期1-3月", "bb"))
                        .append(new JQTable.Node("逾期3-6月", "bc"))
                        .append(new JQTable.Node("逾期6-12月", "bd"))
                        .append(new JQTable.Node("逾期1年以上", "be"))
                        .append(new JQTable.Node("小计", "bf")),
                    new JQTable.Node("未到期(不含质保金)", "ah"),
                    new JQTable.Node("未到期质保金", "ai"),
                    new JQTable.Node("合计", "aj")
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var SimpleView = /** @class */ (function (_super) {
            __extends(SimpleView, _super);
            function SimpleView(id) {
                var _this = _super.call(this, id) || this;
                _this.mAjax = new Util.Ajax("/BusinessManagement/yszkgb/yszkkxxz/update.do", false);
                return _this;
            }
            SimpleView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "/BusinessManagement/yszkgb/yszkkxxz/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: cpType
                });
            };
            SimpleView.prototype.option = function () {
                return this.mOpt;
            };
            SimpleView.prototype.pluginUpdate = function (date, cpType) {
                var _this = this;
                this.mDt = date;
                this.mAjax.get({
                    date: date,
                    companyId: cpType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            SimpleView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            SimpleView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                framework.router.to(Util.FAMOUS_VIEW).send(Util.MSG_REG, { name: "应收账款款项性质情况", plugin: this });
            };
            SimpleView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            };
            SimpleView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                this.tableAssist.mergeRow(0);
                this.tableAssist.mergeTitle();
                return this.tableAssist;
            };
            SimpleView.prototype.updateTable = function () {
                this.createJqassist();
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                var data = [];
                for (var i = month + 1; i <= 12; ++i) {
                    data.push(["上年度", i + "月"].concat(this.mData[i - month - 1]));
                }
                for (var i = 1; i <= month; ++i) {
                    data.push(["本年度", i + "月"].concat(this.mData[12 - month + i - 1]));
                }
                this.tableAssist.create({
                    data: data,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true
                });
                this.adjustSize();
            };
            SimpleView.ins = new SimpleView("yszkkxxz");
            return SimpleView;
        }(yszkgb.BasePluginView));
        yszkkxxz.SimpleView = SimpleView;
    })(yszkkxxz = yszkgb.yszkkxxz || (yszkgb.yszkkxxz = {}));
})(yszkgb || (yszkgb = {}));
