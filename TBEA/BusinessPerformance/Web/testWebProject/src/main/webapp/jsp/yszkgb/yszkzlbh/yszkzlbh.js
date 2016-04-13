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
    var yszkzlbh;
    (function (yszkzlbh) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "rq", true, TextAlign.Center),
                    new JQTable.Node("月度", "rq1", true, TextAlign.Center),
                    new JQTable.Node("5年以上", "a1"),
                    new JQTable.Node("4-5年", "a2"),
                    new JQTable.Node("3-4年", "a3"),
                    new JQTable.Node("2-3年", "a4"),
                    new JQTable.Node("1-2年", "a5"),
                    new JQTable.Node("1年以内", "a6"),
                    new JQTable.Node("合计", "a7")
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var YSZKZLBHView = (function (_super) {
            __extends(YSZKZLBHView, _super);
            function YSZKZLBHView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("yszkzlbh/update.do", false);
            }
            YSZKZLBHView.newInstance = function () {
                return new YSZKZLBHView();
            };
            YSZKZLBHView.prototype.option = function () {
                return this.mOpt;
            };
            YSZKZLBHView.prototype.pluginUpdate = function (date, cpType) {
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
            YSZKZLBHView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            YSZKZLBHView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("应收账款账龄变化", this);
            };
            YSZKZLBHView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var curDate = new Date(Date.parse(this.mDt));
                var month = curDate.getMonth() + 1;
                var data = [];
                for (var i = month + 1; i <= 12; ++i) {
                    data.push(["上年度", i + "月"].concat(this.mData[i - month]));
                }
                for (var i = 1; i <= month; ++i) {
                    data.push(["本年度", i + "月"].concat(this.mData[12 - month + i]));
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
            return YSZKZLBHView;
        })(yszkgb.BasePluginView);
        yszkzlbh.pluginView = YSZKZLBHView.newInstance();
    })(yszkzlbh = yszkgb.yszkzlbh || (yszkgb.yszkzlbh = {}));
})(yszkgb || (yszkgb = {}));
