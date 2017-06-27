var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../messageBox.ts"/>
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../sbdscqyqkdef.ts"/>
var pluginEntry;
(function (pluginEntry) {
    pluginEntry.xfcpqy = framework.basic.endpoint.lastId();
    pluginEntry.xfcpqy_byq = framework.basic.endpoint.lastId();
    pluginEntry.xfcpqy_xl = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var sbdscqyqk;
(function (sbdscqyqk) {
    var xfcpqyEntry;
    (function (xfcpqyEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly, date) {
                var curDate = new Date(Date.parse(date.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                var year = curDate.getFullYear();
                var data = [];
                var node;
                var titleNodes = [];
                node = new JQTable.Node("产品", "xfcpqyEntry_cp", true, TextAlign.Left);
                titleNodes.push(node);
                node = new JQTable.Node(year + "年" + month + "月", "xfcpqyEntry_riqi", false, TextAlign.Center);
                node.append(new JQTable.Node("签约额(万元)", "xfcpqyEntry_cb_", false));
                titleNodes.push(node);
                return new JQTable.JQGridAssistant(titleNodes, gridName);
            };
            return JQGridAssistantFactory;
        })();
        var EntryView = (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("/BusinessManagement/xfcpqy/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("/BusinessManagement/xfcpqy/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("/BusinessManagement/xfcpqy/entry/submit.do", false);
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.xfcpqy;
            };
            EntryView.prototype.option = function () {
                return this.mOpt;
            };
            EntryView.prototype.pluginSave = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 2; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 2] = submitData[i][j - 2].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType,
                    type: this.mType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        _this.pluginUpdate(dt, compType);
                        Util.Toast.success("保存 成功");
                    }
                    else {
                        Util.Toast.failed(resp.message);
                    }
                });
            };
            EntryView.prototype.pluginSubmit = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 2; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 2] = submitData[i][j - 2].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType,
                    type: this.mType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        _this.pluginUpdate(dt, compType);
                        Util.Toast.success("提交 成功");
                    }
                    else {
                        Util.Toast.failed(resp.message);
                    }
                });
            };
            EntryView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mAjaxUpdate.get({
                    date: date,
                    companyId: compType,
                    type: this.mType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            EntryView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            EntryView.prototype.isSupported = function (compType) {
                if (this.mType == sbdscqyqk.SbdscqyqkType.BYQ) {
                    if (compType == Util.CompanyType.SBGS ||
                        compType == Util.CompanyType.HBGS ||
                        compType == Util.CompanyType.XBC ||
                        compType == Util.CompanyType.TBGS) {
                        return true;
                    }
                }
                else {
                    if (compType == Util.CompanyType.LLGS ||
                        compType == Util.CompanyType.XLC ||
                        compType == Util.CompanyType.DLGS) {
                        return true;
                    }
                }
                return false;
            };
            EntryView.prototype.init = function (opt) {
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(pluginEntry.xfcpqy_byq, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "细分产品签约情况及趋势（国内市场制造业签约）");
                framework.router
                    .fromEp(new framework.basic.EndpointProxy(pluginEntry.xfcpqy_xl, this.getId()))
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "细分产品签约情况及趋势（国内市场制造业签约）");
            };
            EntryView.prototype.onEvent = function (e) {
                if (e.road != undefined) {
                    switch (e.road[e.road.length - 1]) {
                        case pluginEntry.xfcpqy_byq:
                            this.mType = sbdscqyqk.SbdscqyqkType.BYQ;
                            break;
                        case pluginEntry.xfcpqy_xl:
                            this.mType = sbdscqyqk.SbdscqyqkType.XL;
                            break;
                        default:
                            this.mType = sbdscqyqk.SbdscqyqkType.BYQ;
                    }
                }
                return _super.prototype.onEvent.call(this, e);
            };
            EntryView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.mTableAssist.resizeHeight(maxTableBodyHeight);
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            };
            EntryView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), false, this.mDt);
                return this.mTableAssist;
            };
            EntryView.prototype.updateTable = function () {
                this.createJqassist();
                this.mTableAssist.create({
                    data: this.mData,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.mTableAssist.getColNames().length * 400,
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true,
                    assistEditable: true
                });
                this.adjustSize();
            };
            EntryView.ins = new EntryView();
            return EntryView;
        })(framework.basic.EntryPluginView);
    })(xfcpqyEntry = sbdscqyqk.xfcpqyEntry || (sbdscqyqk.xfcpqyEntry = {}));
})(sbdscqyqk || (sbdscqyqk = {}));
