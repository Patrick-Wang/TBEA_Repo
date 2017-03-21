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
    var yqyszcsys;
    (function (yqyszcsys) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "rq", true, TextAlign.Center),
                    new JQTable.Node("月度", "rqa", true, TextAlign.Center),
                    new JQTable.Node("内部因素", "ab"),
                    new JQTable.Node("客户资信", "ac"),
                    new JQTable.Node("滚动付款", "ad"),
                    new JQTable.Node("项目变化", "ae"),
                    new JQTable.Node("合同因素", "af"),
                    new JQTable.Node("手续办理", "ag"),
                    new JQTable.Node("诉讼", "ah"),
                    new JQTable.Node("合计", "ai")
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var YqyszcsysView = (function (_super) {
            __extends(YqyszcsysView, _super);
            function YqyszcsysView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("yqyszcsys/update.do", false);
            }
            YqyszcsysView.newInstance = function () {
                return new YqyszcsysView();
            };
            YqyszcsysView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "yqyszcsys/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: cpType
                });
            };
            YqyszcsysView.prototype.option = function () {
                return this.mOpt;
            };
            YqyszcsysView.prototype.pluginUpdate = function (date, cpType) {
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
            YqyszcsysView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            YqyszcsysView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("逾期应收账产生因素", this);
            };
            YqyszcsysView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                var data = [];
                for (var i = month + 1; i <= 12; ++i) {
                    data.push(["上年度", i + "月"].concat(this.mData[i - month - 1]));
                }
                for (var i = 1; i <= month; ++i) {
                    data.push(["本年度", i + "月"].concat(this.mData[12 - month + i - 1]));
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
            return YqyszcsysView;
        })(yszkgb.BasePluginView);
        yqyszcsys.pluginView = YqyszcsysView.newInstance();
    })(yqyszcsys = yszkgb.yqyszcsys || (yszkgb.yqyszcsys = {}));
})(yszkgb || (yszkgb = {}));
