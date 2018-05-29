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
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../messageBox.ts"/>
/// <reference path="../../framework/basic/basicdef.ts"/>
/// <reference path="../../framework/route/route.ts"/>
/// <reference path="../sbdscqyqkdef.ts"/>
var pluginEntry;
(function (pluginEntry) {
    pluginEntry.xfscqy = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var sbdscqyqk;
(function (sbdscqyqk) {
    var xfscqyEntry;
    (function (xfscqyEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("行业", "hy", true, TextAlign.Center),
                    new JQTable.Node("行业", "hy2", true, TextAlign.Center),
                    new JQTable.Node("签约额(万元)", "qye", readOnly, TextAlign.Right)
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var EntryView = /** @class */ (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjaxUpdate = new Util.Ajax("/BusinessManagement/xfscqy/entry/update.do", false);
                _this.mAjaxSave = new Util.Ajax("/BusinessManagement/xfscqy/entry/save.do", false);
                _this.mAjaxSubmit = new Util.Ajax("/BusinessManagement/xfscqy/entry/submit.do", false);
                return _this;
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.xfscqy;
            };
            EntryView.prototype.option = function () {
                return this.mOpt;
            };
            EntryView.prototype.pluginSave = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([allData[i][3].replace(new RegExp(' ', 'g'), '')]);
                }
                var tmp = submitData[submitData.length - 1];
                submitData[submitData.length - 1] = submitData[submitData.length - 2];
                submitData[submitData.length - 2] = tmp;
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
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
                    submitData.push([allData[i][3].replace(new RegExp(' ', 'g'), '')]);
                    //if ("" == submitData[i][3]) {
                    //    Util.MessageBox.tip("有空内容 无法提交");
                    //    return;
                    //}
                }
                var tmp = submitData[submitData.length - 1];
                submitData[submitData.length - 1] = submitData[submitData.length - 2];
                submitData[submitData.length - 2] = tmp;
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
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
                    companyId: compType
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
            EntryView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "细分市场签约");
            };
            EntryView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.mTableAssist && this.mTableAssist.resizeHeight(maxTableBodyHeight);
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            };
            EntryView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), false);
                this.mTableAssist.mergeColum(0);
                this.mTableAssist.mergeRow(0);
                this.mTableAssist.mergeTitle();
                return this.mTableAssist;
            };
            EntryView.prototype.updateTable = function () {
                this.createJqassist();
                var data = [["传统电力市场"],
                    ["传统电力市场"],
                    ["传统电力市场"],
                    ["传统电力市场"],
                    ["新能源市场"],
                    ["新能源市场"],
                    ["新能源市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["重点领域市场"],
                    ["连锁经营"],
                    ["其它"]];
                for (var i = 0; i < data.length; ++i) {
                    if (i == data.length - 2) {
                        data[i] = data[i].concat(this.mData[i + 1]);
                    }
                    else if (i == data.length - 1) {
                        data[i] = data[i].concat(this.mData[i - 1]);
                    }
                    else {
                        data[i] = data[i].concat(this.mData[i]);
                    }
                }
                this.mTableAssist.create({
                    data: data,
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
        }(framework.basic.EntryPluginView));
    })(xfscqyEntry = sbdscqyqk.xfscqyEntry || (sbdscqyqk.xfscqyEntry = {}));
})(sbdscqyqk || (sbdscqyqk = {}));
