var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var yszkgb;
(function (yszkgb) {
    var yszkyjtztjqs;
    (function (yszkyjtztjqs) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "rq", true, TextAlign.Center),
                    new JQTable.Node("月度", "rqa", true, TextAlign.Center),
                    new JQTable.Node("财务账面应收净收余额", "ab"),
                    new JQTable.Node("保理余额（加项）", "ac"),
                    new JQTable.Node("货发票未开金额（加项）", "ad"),
                    new JQTable.Node("票开货未发金额（减项）", "ae"),
                    new JQTable.Node("预收款冲减应收（加项）", "af"),
                    new JQTable.Node("信用证冲减应收（加项）", "ag"),
                    new JQTable.Node("其他应收科目影响（加项）", "ah"),
                    new JQTable.Node("预警台账应收账款余额 ", "ai")
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var YszkyjtztjqsView = (function (_super) {
            __extends(YszkyjtztjqsView, _super);
            function YszkyjtztjqsView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("yszkyjtztjqs/update.do", false);
            }
            YszkyjtztjqsView.newInstance = function () {
                return new YszkyjtztjqsView();
            };
            YszkyjtztjqsView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "yszkyjtztjqs/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: cpType
                });
            };
            YszkyjtztjqsView.prototype.option = function () {
                return this.mOpt;
            };
            YszkyjtztjqsView.prototype.pluginUpdate = function (date, cpType) {
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
            YszkyjtztjqsView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            YszkyjtztjqsView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("应收账款账面与预警台账调节趋势表", this);
            };
            YszkyjtztjqsView.prototype.updateTable = function () {
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
            return YszkyjtztjqsView;
        }(yszkgb.BasePluginView));
        yszkyjtztjqs.pluginView = YszkyjtztjqsView.newInstance();
    })(yszkyjtztjqs = yszkgb.yszkyjtztjqs || (yszkgb.yszkyjtztjqs = {}));
})(yszkgb || (yszkgb = {}));
