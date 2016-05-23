var __extends = this.__extends || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    __.prototype = b.prototype;
    d.prototype = new __();
};
var jcycljg;
(function (jcycljg) {
    var yhjzll;
    (function (yhjzll) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("贷款利率（%）", "a1").append(new JQTable.Node("六个月内", "b1")).append(new JQTable.Node("六个月至一年", "b2")).append(new JQTable.Node("一年至三年", "b3")),
                    new JQTable.Node("存款利率（%）", "a2").append(new JQTable.Node("活期", "b4")).append(new JQTable.Node("定期半年", "b5")).append(new JQTable.Node("定期一年", "b6"))
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var YhjzllView = (function (_super) {
            __extends(YhjzllView, _super);
            function YhjzllView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("update.do?type=" + 16 /* YHJZLL */, false);
            }
            YhjzllView.newInstance = function () {
                return new YhjzllView();
            };
            YhjzllView.prototype.getContentType = function () {
                return 1 /* TABLE */;
            };
            YhjzllView.prototype.switchDisplayType = function (type) {
            };
            YhjzllView.prototype.refresh = function () {
                this.updateTable();
            };
            YhjzllView.prototype.option = function () {
                return this.mOpt;
            };
            YhjzllView.prototype.pluginUpdate = function (start, end) {
                var _this = this;
                this.mAjax.get({
                    start: start,
                    end: end
                }).then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.updateTable();
                });
            };
            YhjzllView.prototype.init = function (opt) {
                super.init.call(this, opt);
                view.register("银行基准利率", this);
            };
            YhjzllView.prototype.getDateType = function () {
                return 0 /* DAY */;
            };
            YhjzllView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + name + "pager" + "'></div>");
                this.$(name).jqGrid(tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 20,
                    data: tableAssist.getData(this.mData),
                    datatype: "local",
                    viewrecords: true,
                    pager: name + "pager"
                }));
            };
            return YhjzllView;
        })(BasePluginView);
        yhjzll.pluginView = YhjzllView.newInstance();
    })(yhjzll = jcycljg.yhjzll || (jcycljg.yhjzll = {}));
})(jcycljg || (jcycljg = {}));
