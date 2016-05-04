var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.xnych = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var xnych;
(function (xnych_1) {
    var xnych;
    (function (xnych) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("账面净额合计", "rqa"),
                    new JQTable.Node("EPC项目存货", "ab"),
                    new JQTable.Node("已转让自有项目存货", "ac"),
                    new JQTable.Node("未转让自有项目存货", "ada"),
                    new JQTable.Node("已转固自有项目存货", "adb"),
                    new JQTable.Node("资本化前期费用", "adc")
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../xnych/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.xnych;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../xnych/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
                this.mAjax.get({
                    date: date,
                    companyId: compType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ShowView.prototype.init = function (opt) {
                framework.router.fromEp(this).to(framework.basic.endpoint.FRAME_ID).send(framework.basic.FrameEvent.FE_REGISTER, "新能源存货");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: this.mData.length > 20 ? 600 : '100%',
                    width: 1400,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 1000,
                    data: tableAssist.getData(this.mData),
                    datatype: "local",
                    viewrecords: true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        }(framework.basic.ShowPluginView));
    })(xnych = xnych_1.xnych || (xnych_1.xnych = {}));
})(xnych || (xnych = {}));
