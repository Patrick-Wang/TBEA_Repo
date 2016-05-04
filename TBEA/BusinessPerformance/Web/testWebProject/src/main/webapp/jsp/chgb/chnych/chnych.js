var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var chgb;
(function (chgb) {
    var chnych;
    (function (chnych) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "chnych_yd", true, TextAlign.Center),
                    new JQTable.Node("月度", "chnych_ydtwo", true, TextAlign.Center),
                    new JQTable.Node("原材料", "chnych_ycl"),
                    new JQTable.Node("库存商品", "chnych_kcsp"),
                    new JQTable.Node("生产成本-待配比土方", "chnych_tf"),
                    new JQTable.Node("发出商品", "chnych_fcsp"),
                    new JQTable.Node("低耗", "chnych_dh"),
                    new JQTable.Node("合计", "chnych_hj")
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var CHNYCHView = (function (_super) {
            __extends(CHNYCHView, _super);
            function CHNYCHView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("chnych/update.do", false);
            }
            CHNYCHView.newInstance = function () {
                return new CHNYCHView();
            };
            CHNYCHView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "chnych/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: cpType
                });
            };
            CHNYCHView.prototype.option = function () {
                return this.mOpt;
            };
            CHNYCHView.prototype.pluginUpdate = function (date, cpType) {
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
            CHNYCHView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            CHNYCHView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("能源存货", this);
            };
            CHNYCHView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                var year = curDate.getFullYear();
                var data = [];
                data.push([year + "年期初结余", year + "年期初结余"].concat(this.mData[12]));
                for (var i = month + 1; i <= 12; ++i) {
                    data.push(["上年度", i + "月"].concat(this.mData[i - month - 1]));
                }
                for (var i = 1; i <= month; ++i) {
                    data.push(["本年度", i + "月"].concat(this.mData[12 - month + i - 1]));
                }
                tableAssist.mergeRow(0);
                tableAssist.mergeTitle();
                tableAssist.mergeColum(0);
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
            return CHNYCHView;
        }(chgb.BasePluginView));
        chnych.pluginView = CHNYCHView.newInstance();
    })(chnych = chgb.chnych || (chgb.chnych = {}));
})(chgb || (chgb = {}));
