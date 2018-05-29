/// <reference path="../../../jqgrid/jqassist.ts" />
/// <reference path="../../../util.ts" />
/// <reference path="../../../dateSelector.ts" />
/// <reference path="../jcycljgdef.ts" />
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
var jcycljg;
(function (jcycljg) {
    var yhjzll;
    (function (yhjzll) {
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "rq", true),
                    new JQTable.Node("贷款利率（%）", "a1")
                        .append(new JQTable.Node("六个月内", "b1"))
                        .append(new JQTable.Node("六个月至一年", "b2"))
                        .append(new JQTable.Node("一年至三年", "b3")),
                    new JQTable.Node("存款利率（%）", "a2")
                        .append(new JQTable.Node("活期", "b4"))
                        .append(new JQTable.Node("定期半年", "b5"))
                        .append(new JQTable.Node("定期一年", "b6"))
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var YhjzllView = /** @class */ (function (_super) {
            __extends(YhjzllView, _super);
            function YhjzllView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjax = new Util.Ajax("/BusinessManagement/jcycljg/update.do?type=" + jcycljg.JcycljgType.YHJZLL, false);
                return _this;
                /*
                private updateTable():void {
                    var name = this.option().host + this.option().tb + "_jqgrid_1234";
                    var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name);
                    var parent = this.$(this.option().tb);
                    parent.empty();
                    parent.append("<table id='" + name + "'></table><div id='" + name + "pager" + "'></div>");
                    this.$(name).jqGrid(
                        tableAssist.decorate({
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
                            viewrecords : true,
                            pager : name + "pager"
                        }));
                }*/
            }
            YhjzllView.newInstance = function () {
                return new YhjzllView();
            };
            //public getContentType():ContentType{
            //    return ContentType.TABLE;
            //}
            //switchDisplayType(type:jcycljg.DisplayType):void {
            //
            //}
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
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.updateTable();
                    _this.adjustSize();
                });
            };
            YhjzllView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("银行基准利率", this);
            };
            YhjzllView.prototype.getDateType = function () {
                return jcycljg.DateType.YEAR;
            };
            YhjzllView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'><div id='" + this.jqgridName() + "pager" + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                return this.tableAssist;
            };
            YhjzllView.prototype.updateTable = function () {
                this.createJqassist();
                this.tableAssist.create({
                    data: this.mData,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 15,
                    autoScroll: true,
                    pager: this.jqgridName() + "pager"
                });
            };
            return YhjzllView;
        }(jcycljg.BasePluginView));
        yhjzll.pluginView = YhjzllView.newInstance();
    })(yhjzll = jcycljg.yhjzll || (jcycljg.yhjzll = {}));
})(jcycljg || (jcycljg = {}));
