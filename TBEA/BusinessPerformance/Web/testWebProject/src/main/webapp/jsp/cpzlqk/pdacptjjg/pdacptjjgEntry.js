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
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqkEntry.ts"/>
var pluginEntry;
(function (pluginEntry) {
    pluginEntry.pdacptjjg = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var cpzlqk;
(function (cpzlqk) {
    var pdacptjjgEntry;
    (function (pdacptjjgEntry) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "产品类别", align: TextAlign.Center }),
                    Node.create({ name: "产品类别", align: TextAlign.Center }),
                    Node.create({ name: "不合格数(台)", isReadOnly: readOnly }),
                    Node.create({ name: "总数(台)", isReadOnly: readOnly })
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var EntryView = (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("../pdacptjjg/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("../pdacptjjg/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("../pdacptjjg/entry/submit.do", false);
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.pdacptjjg;
            };
            EntryView.prototype.isSupported = function (compType) {
                return compType == Util.CompanyType.SBZTFGS || compType == Util.CompanyType.HBDQFGS
                    || compType == Util.CompanyType.XBZTGS || compType == Util.CompanyType.TBGS
                    || compType == Util.CompanyType.XBXBGS;
            };
            EntryView.prototype.option = function () {
                return this.mOpt;
            };
            EntryView.prototype.pluginSave = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([allData[i][0]]);
                    for (var j = 3; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 3] = submitData[i][j - 3].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("保存 成功", function () {
                            _this.pluginUpdate(dt, compType);
                        });
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            EntryView.prototype.pluginSubmit = function (dt, compType) {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([allData[i][0]]);
                    for (var j = 3; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 3] = submitData[i][j - 3].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j - 3]) {
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
                        var param = {
                            companyId: compType,
                            year: Util.toDate(dt).year,
                            month: Util.toDate(dt).month,
                            pageType: 2,
                            tableStatus: JSON.stringify([
                                {
                                    id: plugin.pdacptjjg
                                }, {
                                    id: plugin.pdadwtjjg
                                }
                            ])
                        };
                        window.location.href = "show.do?param=" + JSON.stringify(param);
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
                framework.router
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(cpzlqk.Event.ZLFE_DATA_STATUS, this.mData.status);
                this.updateTable();
            };
            EntryView.prototype.init = function (opt) {
                framework.router
                    .fromEp(this)
                    .to(framework.basic.endpoint.FRAME_ID)
                    .send(framework.basic.FrameEvent.FE_REGISTER, "按产品统计结果");
            };
            EntryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, Util.ZBStatus.APPROVED == this.mData.status);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                var jqTable = this.$(name);
                this.mTableAssist.mergeColum(0);
                this.mTableAssist.mergeRow(0);
                this.mTableAssist.mergeTitle(0);
                jqTable.jqGrid(this.mTableAssist.decorate({
                    datatype: "local",
                    data: this.mTableAssist.getDataWithId(this.mData.tjjg),
                    multiselect: false,
                    drag: false,
                    resize: false,
                    assistEditable: Util.ZBStatus.APPROVED != this.mData.status,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    //editurl: 'clientArray',
                    cellEdit: true,
                    // height: data.length > 25 ? 550 : '100%',
                    // width: titles.length * 200,
                    rowNum: 1000,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    //pager: '#' + pagername,
                    viewrecords: true
                }));
            };
            EntryView.ins = new EntryView();
            return EntryView;
        })(cpzlqk.ZlEntryPluginView);
    })(pdacptjjgEntry = cpzlqk.pdacptjjgEntry || (cpzlqk.pdacptjjgEntry = {}));
})(cpzlqk || (cpzlqk = {}));
