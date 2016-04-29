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
var pluginEntry;
(function (pluginEntry) {
    pluginEntry.yclbfqk = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var wgcpqk;
(function (wgcpqk) {
    var yclbfqkEntry;
    (function (yclbfqkEntry) {
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("材料名称", "clmc", true),
                    new JQTable.Node("领用量", "ac", readOnly),
                    new JQTable.Node("废料", "ada", readOnly)
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var EntryView = (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("yclbfqk/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("yclbfqk/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("yclbfqk/entry/submit.do", false);
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.yclbfqk;
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
                    var index = 0;
                    for (var j = 0; j < allData[i].length; ++j) {
                        if (j == 1) {
                            continue;
                        }
                        submitData[i].push(allData[i][j]);
                        submitData[i][index] = submitData[i][index].replace(new RegExp(' ', 'g'), '');
                        ++index;
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
                    submitData.push([]);
                    var index = 0;
                    for (var j = 0; j < allData[i].length; ++j) {
                        if (j == 1) {
                            continue;
                        }
                        submitData[i].push(allData[i][j]);
                        submitData[i][index] = submitData[i][index].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][index]) {
                            Util.MessageBox.tip("有空内容 无法提交");
                            return;
                        }
                        ++index;
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
            EntryView.prototype.isSupported = function (compType) {
                return true;
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
                    .send(framework.basic.FrameEvent.FE_REGISTER, "原材料报废情况");
            };
            EntryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false);
                var data = [];
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'>");
                var jqTable = this.$(name);
                jqTable.jqGrid(this.mTableAssist.decorate({
                    datatype: "local",
                    data: this.mTableAssist.getDataWithId(this.mData),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    assistEditable: true,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    //editurl: 'clientArray',
                    cellEdit: true,
                    //height: data.length > 25 ? 550 : '100%',
                    // width: titles.length * 200,
                    rowNum: 20,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    viewrecords: true
                }));
            };
            EntryView.ins = new EntryView();
            return EntryView;
        }(framework.basic.EntryPluginView));
    })(yclbfqkEntry = wgcpqk.yclbfqkEntry || (wgcpqk.yclbfqkEntry = {}));
})(wgcpqk || (wgcpqk = {}));
