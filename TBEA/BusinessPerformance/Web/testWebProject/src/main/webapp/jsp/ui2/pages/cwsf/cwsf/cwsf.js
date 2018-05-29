/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../cwsfdef.ts"/>
///<reference path="../cwsf.ts"/>
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
var plugin;
(function (plugin) {
    plugin.cwsf = framework.basic.endpoint.lastId();
})(plugin || (plugin = {}));
var cwsf;
(function (cwsf) {
    var sddb;
    (function (sddb) {
        var JQGridAssistantFactory = /** @class */ (function () {
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
        }());
        var ShowView = /** @class */ (function (_super) {
            __extends(ShowView, _super);
            function ShowView() {
                return _super !== null && _super.apply(this, arguments) || this;
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
                        Util.Toast.failed("起始时间不能晚于结束时间");
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
                this.adjustSize();
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
                this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                //if (this.mData.tjjg.length > 0){
                //    this.$(this.option().ctarea).show();
                //
                //    if (this.mYdjdType == YDJDType.JD) {
                //        this.$(this.option().ct1).parent().hide();
                //        this.$(this.option().ct).parent().css("width", "100%");
                //        this.updateJDEchart();
                //    } else {
                //        this.$(this.option().ct).parent().show();
                //        this.$(this.option().ct1).parent().show();
                //        this.$(this.option().ct).parent().css("width", "50%");
                //        this.$(this.option().ct1).parent().css("width", "50%");
                //        this.updateYDEchart();
                //    }
                //}
            };
            ShowView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></div>");
                this.tableAssist = Util.createTable(this.jqgridName(), this.mData);
                return this.tableAssist;
            };
            ShowView.prototype.updateTable = function () {
                this.createJqassist();
                this.tableAssist.create({
                    data: this.mData.data,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: false,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: this.mData.shrinkToFit == "false" ? false : true,
                    rowNum: 10000,
                    autoScroll: true
                });
            };
            ShowView.ins = new ShowView();
            return ShowView;
        }(framework.basic.ShowPluginView));
    })(sddb = cwsf.sddb || (cwsf.sddb = {}));
})(cwsf || (cwsf = {}));
