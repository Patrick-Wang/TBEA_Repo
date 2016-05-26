var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="../../framework/basic/basicdef.ts"/>
///<reference path="../../framework/route/route.ts"/>
///<reference path="../cbfxdef.ts"/>
var pluginEntry;
(function (pluginEntry) {
    pluginEntry.nymyywmlfx = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var cbfx;
(function (cbfx) {
    var nymyywmlfxEntry;
    (function (nymyywmlfxEntry) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "合作客户", align: TextAlign.Left, isReadOnly: readOnly }),
                    Node.create({ name: "贸易项目", align: TextAlign.Left, isReadOnly: readOnly }),
                    Node.create({ name: "数量", isReadOnly: readOnly }),
                    Node.create({ name: "收入", isReadOnly: readOnly }),
                    Node.create({ name: "成本", isReadOnly: readOnly })
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var EntryView = (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("../nymyywmlfx/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("../nymyywmlfx/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("../nymyywmlfx/entry/submit.do", false);
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.nymyywmlfx;
            };
            EntryView.prototype.option = function () {
                return this.mOpt;
            };
            EntryView.prototype.isSupported = function (compType) {
                return compType == Util.CompanyType.TCNY;
            };
            EntryView.prototype.pluginSave = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        _this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("保存 成功");
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            EntryView.prototype.pluginSubmit = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([allData[i][0], allData[i][2]]);
                    submitData[i][1] = submitData[i][1].replace(new RegExp(' ', 'g'), '');
                    if ("" == submitData[i][1]) {
                        Util.MessageBox.tip("有空内容 无法提交");
                        return;
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        _this.pluginUpdate(dt, compType);
                        Util.MessageBox.tip("提交 成功");
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            EntryView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mCompType = compType;
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
                    .send(framework.basic.FrameEvent.FE_REGISTER, "能源贸易业务毛利分析");
            };
            EntryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                var jqTable = this.$(name);
                jqTable.jqGrid(this.mTableAssist.decorate({
                    datatype: "local",
                    data: this.mTableAssist.getDataWithId(this.mData),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    assistEditable: true,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    rowNum: 22,
                    height: '100%',
                    width: 1000,
                    shrinkToFit: true,
                    autoScroll: true,
                    viewrecords: true,
                    pager: '#' + pagername,
                }));
            };
            EntryView.ins = new EntryView();
            return EntryView;
        }(framework.basic.EntryPluginView));
    })(nymyywmlfxEntry = cbfx.nymyywmlfxEntry || (cbfx.nymyywmlfxEntry = {}));
})(cbfx || (cbfx = {}));
