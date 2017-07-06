/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cwsfdef.ts"/>
///<reference path="../cwsf.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.cwsf = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var cwsf;
(function (cwsf) {
    var sddb;
    (function (sddb) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, headers) {
                var nodes = [];
                for (var i = 0; i < headers.length; ++i) {
                    var node = Util.parseHeader(headers[i]);
                    if (null != node) {
                        nodes.push(node);
                    }
                }
                return new JQTable.JQGridAssistant(nodes, gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ShowView = (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                _super.apply(this, arguments);
            }
            ShowView.prototype.getId = function () {
                return plugin.cwsf;
            };
            ShowView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case framework.basic.FrameEvent.FE_UPDATE:
                        {
                            this.pluginUpdate(e.data.dStart, e.data.dEnd, e.data.comps, e.data.item);
                        }
                        return;
                    case framework.basic.FrameEvent.FE_GET_EXPORTURL:
                        {
                            return this.pluginGetExportUrl(e.data.dStart, e.data.dEnd, e.data.comps, e.data.item);
                        }
                    default:
                        break;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.pluginGetExportUrl = function (dStart, dEnd, comps, item) {
                return this.option().exportUrl + "?" + Util.Ajax.toUrlParam({
                    dStart: dStart,
                    dEnd: dEnd,
                    comps: comps,
                    item: item
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.pluginUpdate = function (dStart, dEnd, comps, item) {
                var _this = this;
                if (undefined != dStart) {
                    var dS = new Date(Date.parse(dStart.replace(/-/g, '/'))).getTime();
                    var dE = new Date(Date.parse(dEnd.replace(/-/g, '/'))).getTime();
                    if (dS <= dE) {
                        this.mAjax.get({
                            dStart: dStart,
                            dEnd: dEnd,
                            comps: comps,
                            item: item
                        })
                            .then(function (jsonData) {
                            _this.mData = jsonData;
                            _this.refresh();
                        });
                    }
                    else {
                        Util.MessageBox.tip("起始时间不能晚于结束时间");
                    }
                }
                else {
                    this.mAjax.get({
                        dStart: dStart,
                        dEnd: dEnd,
                        comps: comps,
                        item: item
                    })
                        .then(function (jsonData) {
                        _this.mData = jsonData;
                        _this.refresh();
                    });
                }
            };
            ShowView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ShowView.prototype.init = function (opt) {
                this.mAjax = new Util.Ajax(opt.updateUrl, false);
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, opt.title);
            };
            ShowView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                //var tableAssist:JQTable.JQGridAssistant = JQGridAssistantFactory.createTable(name, this.mData.header);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var tableAssist = Util.createTable(name, this.mData);
                this.$(name).jqGrid(tableAssist.decorate({
                    datatype: "local",
                    data: tableAssist.getData(this.mData.data),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    autoScroll: true,
                    rowNum: 1000,
                    height: this.mData.data.length > 25 ? 550 : '100%',
                    width: this.mData.width == undefined ? 1300 : this.mData.width,
                    shrinkToFit: this.mData.shrinkToFit == "false" ? false : true
                }));
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(sddb = cwsf.sddb || (cwsf.sddb = {}));
})(cwsf || (cwsf = {}));
