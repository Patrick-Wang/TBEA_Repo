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
        var YqyszcsysView = (function (_super) {
            __extends(YqyszcsysView, _super);
            function YqyszcsysView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("yqyszcsys/update.do", false);
            }
            YqyszcsysView.newInstance = function () {
                return new YqyszcsysView();
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
