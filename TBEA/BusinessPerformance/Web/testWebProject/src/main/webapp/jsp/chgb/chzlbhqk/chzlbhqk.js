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
    var chzlbhqk;
    (function (chzlbhqk) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "chzlbhqk_rq", true, TextAlign.Center),
                    new JQTable.Node("月度", "chzlbhqk_rqtwo", true, TextAlign.Center),
                    new JQTable.Node("5年以上", "chzlbhqk_aa"),
                    new JQTable.Node("4-5年", "chzlbhqk_ab"),
                    new JQTable.Node("3-4年", "chzlbhqk_ac"),
                    new JQTable.Node("2-3年", "chzlbhqk_ad"),
                    new JQTable.Node("1-2年", "chzlbhqk_ae"),
                    new JQTable.Node("1年以内", "chzlbhqk_af"),
                    new JQTable.Node("合计", "chzlbhqk_ag")
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var CHZLBHQKView = (function (_super) {
            __extends(CHZLBHQKView, _super);
            function CHZLBHQKView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("chzlbhqk/update.do", false);
            }
            CHZLBHQKView.newInstance = function () {
                return new CHZLBHQKView();
            };
            CHZLBHQKView.prototype.option = function () {
                return this.mOpt;
            };
            CHZLBHQKView.prototype.pluginUpdate = function (date, cpType) {
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
            CHZLBHQKView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            CHZLBHQKView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("存货账龄变化情况", this);
            };
            CHZLBHQKView.prototype.updateTable = function () {
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
            return CHZLBHQKView;
        }(chgb.BasePluginView));
        chzlbhqk.pluginView = CHZLBHQKView.newInstance();
    })(chzlbhqk = chgb.chzlbhqk || (chgb.chzlbhqk = {}));
})(chgb || (chgb = {}));
