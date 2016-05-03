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
///<reference path="../nyzbscqkdef.ts"/>
var pluginEntry;
(function (pluginEntry) {
    pluginEntry.nyzbscxl = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var nyzbscqk;
(function (nyzbscqk) {
    var nyzbscxlEntry;
    (function (nyzbscxlEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("矿区", "kq", true, TextAlign.Center),
                    new JQTable.Node("煤种", "mz", readOnly),
                    new JQTable.Node("月销量", "yxl", readOnly),
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var EntryView = (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("../nyzbscxl/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("../nyzbscxl/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("../nyzbscxl/entry/submit.do", false);
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.nyzbscxl;
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
                    for (var j = 2; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 2] = submitData[i][j - 2].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j - 2]) {
                            Util.MessageBox.tip("有空内容 无法提交");
                            return;
                        }
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
            };
            EntryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, false);
                var data = [];
                if (this.mCompType == Util.CompanyType.SBGS ||
                    this.mCompType == Util.CompanyType.HBGS ||
                    this.mCompType == Util.CompanyType.TBGS ||
                    this.mCompType == Util.CompanyType.XBC) {
                    data.push(["铜"].concat(this.mData[0]));
                }
                else {
                    data.push(["铜"].concat(this.mData[0]));
                    data.push(["铝"].concat(this.mData[1]));
                }
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                var jqTable = this.$(name);
                jqTable.jqGrid(this.mTableAssist.decorate({
                    datatype: "local",
                    data: this.mTableAssist.getData(data),
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
        })(framework.basic.EntryPluginView);
    })(nyzbscxlEntry = nyzbscqk.nyzbscxlEntry || (nyzbscqk.nyzbscxlEntry = {}));
})(nyzbscqk || (nyzbscqk = {}));
