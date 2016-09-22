/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../wgcpqkdef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.yclbfqk = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var wgcpqk;
(function (wgcpqk) {
    var yclbfqk;
    (function (yclbfqk) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, month) {
                var sndfll = new JQTable.Node("上年度废料率", "ac");
                var dndfll = new JQTable.Node("当年度废料率", "ad");
                for (var i = month; i <= 12; ++i) {
                    sndfll.append(new JQTable.Node(i + "月", "ac" + i + "a"));
                }
                for (var i = 1; i <= month; ++i) {
                    dndfll.append(new JQTable.Node(i + "月", "ad" + i + "a"));
                }
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("材料名称", "rqa", true, TextAlign.Center),
                    new JQTable.Node("当月（吨）", "ab", true, TextAlign.Center)
                        .append(new JQTable.Node("领用量", "abh"))
                        .append(new JQTable.Node("废料", "abi"))
                        .append(new JQTable.Node("利用率", "abkm")),
                    sndfll,
                    dndfll
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
                this.mAjax = new Util.Ajax("../yclbfqk/update.do", false);
            }
            ShowView.prototype.getId = function () {
                return plugin.yclbfqk;
            };
            ShowView.prototype.isSupported = function (compType) {
                if (compType == Util.CompanyType.SBDCYJT) {
                    return false;
                }
                return true;
            };
            ShowView.prototype.pluginGetExportUrl = function (date, compType) {
                return "../yclbfqk/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: compType,
                    wgcpqkType: this.mWgcpqkType
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
                    companyId: compType,
                    wgcpqkType: this.mWgcpqkType
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
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "原材料废料情况");
            };
            ShowView.prototype.updateTable = function () {
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var tableAssist = JQGridAssistantFactory.createTable(name, month);
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
    })(yclbfqk = wgcpqk.yclbfqk || (wgcpqk.yclbfqk = {}));
})(wgcpqk || (wgcpqk = {}));
