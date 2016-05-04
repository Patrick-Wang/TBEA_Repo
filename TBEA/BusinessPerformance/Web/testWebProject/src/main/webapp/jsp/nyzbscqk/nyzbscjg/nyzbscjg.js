var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.nyzbscjg = framework.basic.endpoint.lastId();
    plugin.wcwzbkqjg = framework.basic.endpoint.lastId();
    plugin.qtzbkqjg = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var nyzbscqk;
(function (nyzbscqk) {
    var nyzbscjg;
    (function (nyzbscjg) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, year) {
                var titleNodes = [
                    new JQTable.Node("矿区", "rqa", true, TextAlign.Center),
                    new JQTable.Node("煤种", "ab", true, TextAlign.Center),
                    new JQTable.Node(year + "年", "nf", true, TextAlign.Center)];
                for (var i = 1; i <= 12; ++i) {
                    titleNodes[2].append(new JQTable.Node(i + "月", "y" + i + "f"));
                }
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../nyzbscjg/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.nyzbscjg;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../nyzbscjg/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.isSupported = function (compType) {
                if (this.mZbkqId == plugin.wcwzbkqjg) {
                    if (Util.CompanyType.NLTK == compType) {
                        return true;
                    }
                }
                else {
                    if (Util.CompanyType.XJNY == compType) {
                        return true;
                    }
                }
                return false;
            };
            ShowView.prototype.onEvent = function (e) {
                if (e.road != undefined) {
                    this.mZbkqId = e.road[e.road.length - 1];
                }
                return _super.prototype.onEvent.call(this, e);
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
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.wcwzbkqjg, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "五彩湾周边矿区市场销量价格情况");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(plugin.qtzbkqjg, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "奇台周边矿区市场销量价格情况");
            };
            ShowView.prototype.getMonth = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                return month;
            };
            ShowView.prototype.getYear = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                return curDate.getFullYear();
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var tableAssist = JQGridAssistantFactory.createTable(name, this.getYear());
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                tableAssist.mergeRow(0);
                this.$(name).jqGrid(tableAssist.decorate({
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: 1400,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 20,
                    data: tableAssist.getData(this.mData),
                    datatype: "local",
                    viewrecords: true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        }(framework.basic.ShowPluginView));
    })(nyzbscjg = nyzbscqk.nyzbscjg || (nyzbscqk.nyzbscjg = {}));
})(nyzbscqk || (nyzbscqk = {}));
