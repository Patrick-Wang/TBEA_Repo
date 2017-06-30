/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../xnyzbdef.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var plugin;
(function (plugin) {
    plugin.xnyzb = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var xnyzb;
(function (xnyzb_1) {
    var xnyzb;
    (function (xnyzb) {
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
                return plugin.xnyzb;
            };
            ShowView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case framework.basic.FrameEvent.FE_UPDATE:
                        {
                            this.pluginUpdate(e.data.dStart, e.data.dEnd, e.data.compType);
                        }
                        return;
                    case framework.basic.FrameEvent.FE_GET_EXPORTURL:
                        {
                            return this.pluginGetExportUrl(e.data.dStart, e.data.dEnd, e.data.compType);
                        }
                    default:
                        break;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            ShowView.prototype.pluginGetExportUrl = function (dStart, dEnd, compType) {
                return this.option().exportUrl + "?" + Util.Ajax.toUrlParam({
                    dStart: dStart,
                    dEnd: dEnd,
                    compId: compType
                });
            };
            ShowView.prototype.option = function () {
                return this.mOpt;
            };
            ShowView.prototype.pluginUpdate = function (dStart, dEnd, compType) {
                var _this = this;
                this.mCompType = compType;
                this.mAjax.get({
                    dStart: dStart,
                    dEnd: dEnd,
                    compId: compType
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
                this.mAjax = new Util.Ajax(opt.updateUrl, false);
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, opt.title);
            };
            ShowView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.tableAssist.resizeHeight(maxTableBodyHeight);
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            };
            ShowView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mData.header);
                if (this.mData.mergeTitle != undefined) {
                    this.tableAssist.mergeTitle();
                }
                if (this.mData.mergeRows != undefined) {
                    for (var i = 0; i < this.mData.mergeRows.length; ++i) {
                        if (this.mData.mergeRows[i].col != undefined) {
                            if (this.mData.mergeRows[i].rowStart != undefined && this.mData.mergeRows[i].rowLen != undefined) {
                                this.tableAssist.mergeRow(parseInt(this.mData.mergeRows[i].col), parseInt(this.mData.mergeRows[i].rowStart), parseInt(this.mData.mergeRows[i].rowLen));
                            }
                            else if (this.mData.mergeRows[i].rowStart != undefined) {
                                this.tableAssist.mergeRow(parseInt(this.mData.mergeRows[i].col), parseInt(this.mData.mergeRows[i].rowStart));
                            }
                            else {
                                this.tableAssist.mergeRow(parseInt(this.mData.mergeRows[i].col));
                            }
                        }
                    }
                }
                if (this.mData.mergeCols != undefined) {
                    for (var i = 0; i < this.mData.mergeCols.length; ++i) {
                        if (this.mData.mergeCols[i].col != undefined) {
                            this.tableAssist.mergeColum(parseInt(this.mData.mergeCols[i].col));
                        }
                    }
                }
                return this.tableAssist;
            };
            ShowView.prototype.updateTable = function () {
                this.createJqassist();
                this.tableAssist.create({
                    dataWithId: this.mData.data,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true
                });
                this.adjustSize();
            };
            ShowView.ins = new ShowView();
            return ShowView;
        })(framework.basic.ShowPluginView);
    })(xnyzb = xnyzb_1.xnyzb || (xnyzb_1.xnyzb = {}));
})(xnyzb || (xnyzb = {}));
