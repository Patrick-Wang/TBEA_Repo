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
    var chjykcb;
    (function (chjykcb) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("项目", "xm", true, TextAlign.Center),
                    new JQTable.Node("项目", "xm1", true, TextAlign.Center),
                    new JQTable.Node("上月余额", "syye"),
                    new JQTable.Node("本月新增", "byxz"),
                    new JQTable.Node("本月处置", "bycz"),
                    new JQTable.Node("期末余额", "qmye")
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var CHJYKCBView = (function (_super) {
            __extends(CHJYKCBView, _super);
            function CHJYKCBView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("chjykcb/update.do", false);
            }
            CHJYKCBView.newInstance = function () {
                return new CHJYKCBView();
            };
            CHJYKCBView.prototype.option = function () {
                return this.mOpt;
            };
            CHJYKCBView.prototype.pluginUpdate = function (date, cpType) {
                var _this = this;
                this.mAjax.get({
                    date: date,
                    companyId: cpType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            CHJYKCBView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            CHJYKCBView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("积压库存表", this);
            };
            CHJYKCBView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var data = [];
                data.push(["积压库存（原值）"].concat(this.mData[0]));
                data.push(["积压库存（原值）"].concat(this.mData[1]));
                data.push(["积压库存（原值）"].concat(this.mData[2]));
                data.push(["积压库存（原值）"].concat(this.mData[3]));
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
            return CHJYKCBView;
        })(chgb.BasePluginView);
        chjykcb.pluginView = CHJYKCBView.newInstance();
    })(chjykcb = chgb.chjykcb || (chgb.chjykcb = {}));
})(chgb || (chgb = {}));
