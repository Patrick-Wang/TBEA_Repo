/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../yszkgbdef.ts" />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var yszkgb;
(function (yszkgb) {
    var yszkkxxz;
    (function (yszkkxxz) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "a0", true, TextAlign.Center),
                    new JQTable.Node("月度", "a1", true, TextAlign.Center)
                        .append(new JQTable.Node("逾期0-1个月", "b1"))
                        .append(new JQTable.Node("逾期1-3月", "b2"))
                        .append(new JQTable.Node("逾期3-6月", "b3"))
                        .append(new JQTable.Node("逾期6-12月", "b4"))
                        .append(new JQTable.Node("逾期1年以上", "b5"))
                        .append(new JQTable.Node("小计", "b6")),
                    new JQTable.Node("逾期款（含到期保证金）", "a2"),
                    new JQTable.Node("未到期(不含质保金)", "a3"),
                    new JQTable.Node("未到期质保金", "a4"),
                    new JQTable.Node("合计", "a5")
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var YSZKKXXZView = (function (_super) {
            __extends(YSZKKXXZView, _super);
            function YSZKKXXZView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("yszkkxxz/update.do", false);
            }
            YSZKKXXZView.newInstance = function () {
                return new YSZKKXXZView();
            };
            YSZKKXXZView.prototype.option = function () {
                return this.mOpt;
            };
            YSZKKXXZView.prototype.pluginUpdate = function (date, cpType) {
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
            YSZKKXXZView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            YSZKKXXZView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("应收账款款项性质情况", this);
            };
            YSZKKXXZView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var curDate = new Date(Date.parse(this.mDt));
                var month = curDate.getMonth() + 1;
                var data = [];
                for (var i = month + 1; i <= 12; ++i) {
                }
                for (var i = 1; i <= month; ++i) {
                }
                tableAssist.mergeRow(0);
                tableAssist.mergeTitle();
                this.$(name).jqGrid(tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 20,
                    data: tableAssist.getData(data),
                    datatype: "local",
                    viewrecords: true
                }));
            };
            return YSZKKXXZView;
        })(yszkgb.BasePluginView);
        yszkkxxz.pluginView = YSZKKXXZView.newInstance();
    })(yszkkxxz = yszkgb.yszkkxxz || (yszkgb.yszkkxxz = {}));
})(yszkgb || (yszkgb = {}));
