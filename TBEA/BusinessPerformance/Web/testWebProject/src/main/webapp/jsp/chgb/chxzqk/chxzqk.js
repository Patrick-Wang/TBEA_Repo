/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../chgbdef.ts" />
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var chgb;
(function (chgb) {
    var chxzqk;
    (function (chxzqk) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "chxzqk_yd", true, TextAlign.Center),
                    new JQTable.Node("月度", "chxzqk_ydtwo", true, TextAlign.Center),
                    new JQTable.Node("原材料", "chxzqk_ycl"),
                    new JQTable.Node("半成品", "chxzqk_bcp"),
                    new JQTable.Node("实际库存商品", "chxzqk_sjkcsp"),
                    new JQTable.Node("已发货未开票", "chxzqk_yfhwkp"),
                    new JQTable.Node("期货浮动盈亏(盈+，亏-)", "chxzqk_qhfdyk"),
                    new JQTable.Node("期货平仓盈亏(盈-，亏+)", "chxzqk_qhpc"),
                    new JQTable.Node("未发货已开票", "chxzqk_wfhykp"),
                    new JQTable.Node("其他", "chxzqk_qt"),
                    new JQTable.Node("合计", "chxzqk_hj")
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var CHXZQKView = (function (_super) {
            __extends(CHXZQKView, _super);
            function CHXZQKView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("chxzqk/update.do", false);
            }
            CHXZQKView.newInstance = function () {
                return new CHXZQKView();
            };
            CHXZQKView.prototype.option = function () {
                return this.mOpt;
            };
            CHXZQKView.prototype.pluginUpdate = function (date, cpType) {
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
            CHXZQKView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            CHXZQKView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("存货性质情况", this);
            };
            CHXZQKView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var curDate = new Date(Date.parse(this.mDt));
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
            return CHXZQKView;
        }(chgb.BasePluginView));
        chxzqk.pluginView = CHXZQKView.newInstance();
    })(chxzqk = chgb.chxzqk || (chgb.chxzqk = {}));
})(chgb || (chgb = {}));
