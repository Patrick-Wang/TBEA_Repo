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
    pluginEntry.pdcpycssbhgwtmx = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var cpzlqk;
(function (cpzlqk) {
    var pdcpycssbhgwtmxEntry;
    (function (pdcpycssbhgwtmxEntry) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly, bhglx, zrlb) {
                return new JQTable.JQGridAssistant([
                    Node.create({ name: "产品类型", align: TextAlign.Center, isReadOnly: readOnly, isNumber: false }),
                    Node.create({ name: "生产号", align: TextAlign.Center, isReadOnly: readOnly, isNumber: false }),
                    Node.create({ name: "产品型号", align: TextAlign.Center, isReadOnly: readOnly, isNumber: false }),
                    Node.create({ name: "试验不合格现象", align: TextAlign.Center, isReadOnly: readOnly, isNumber: false }),
                    Node.create({ name: "不合格类别", align: TextAlign.Center, isReadOnly: readOnly, editType: "select", options: { value: bhglx }, isNumber: false }),
                    Node.create({ name: "原因分析", align: TextAlign.Center, isReadOnly: readOnly, isNumber: false }),
                    Node.create({ name: "处理措施", align: TextAlign.Center, isReadOnly: readOnly, isNumber: false }),
                    Node.create({ name: "处理结果", align: TextAlign.Center, isReadOnly: readOnly, isNumber: false }),
                    Node.create({ name: "责任类别", align: TextAlign.Center, isReadOnly: readOnly, editType: "select", options: { value: zrlb }, isNumber: false })
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var EntryView = (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("/BusinessManagement/pdcpycssbhgwtmx/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("/BusinessManagement/pdcpycssbhgwtmx/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("/BusinessManagement/pdcpycssbhgwtmx/entry/submit.do", false);
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.pdcpycssbhgwtmx;
            };
            EntryView.prototype.isSupportBhglb = function () {
                return true;
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
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j]) {
                            if (j == 5) {
                                Util.Toast.failed("不合格类别不能为空");
                                return;
                            }
                            else if (j == 9) {
                                Util.Toast.failed("责任类别不能为空");
                                return;
                            }
                        }
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    companyId: compType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        var param = {
                            year: Util.toDate(dt).year,
                            month: Util.toDate(dt).month,
                            pageType: 2,
                            companyId: compType,
                            tableStatus: JSON.stringify([
                                {
                                    id: plugin.pdcpycssbhgwtmx
                                }, {
                                    id: plugin.pdcpycssbhgxxfb
                                }
                            ])
                        };
                        window.location.href = "/BusinessManagement/cpzlqk/v2/show.do?breads=" + breads + "&param=" + JSON.stringify(param);
                    }
                    else {
                        Util.Toast.failed(resp.message);
                    }
                });
            };
            EntryView.prototype.pluginSubmit = function (dt, compType) {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j]) {
                            Util.Toast.failed("有空内容 无法提交");
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
                    .send(framework.basic.FrameEvent.FE_REGISTER, "产品一次送试不合格问题明细");
            };
            EntryView.prototype.adjustSize = function () {
                if (document.body.clientHeight < 10 || document.body.clientWidth < 10) {
                    return;
                }
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() <= this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                //
                //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                //this.mTableAssist.resizeHeight(maxTableBodyHeight);
                //
                //if (this.jqgridHost().width() < this.jqgridHost().find(".ui-jqgrid").width()) {
                //    jqgrid.setGridWidth(this.jqgridHost().width());
                //}
            };
            EntryView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table><div id='" + this.jqgridName() + "pager'></div>");
                this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), Util.ZBStatus.APPROVED == this.mData.status, this.mData.bhglx, this.mData.zrlb);
                this.mTableAssist.mergeTitle(0);
                this.mTableAssist.mergeRow(0);
                return this.mTableAssist;
            };
            EntryView.prototype.updateTable = function () {
                this.createJqassist();
                this.mTableAssist.create({
                    dataWithId: this.mData.tjjg,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    height: '100%',
                    width: this.mTableAssist.getColNames().length * 400,
                    shrinkToFit: true,
                    autoScroll: true,
                    rowNum: 15,
                    assistEditable: Util.ZBStatus.APPROVED != this.mData.status,
                    pager: '#' + this.jqgridName() + "pager",
                    viewrecords: true
                });
                this.adjustSize();
            };
            EntryView.ins = new EntryView();
            return EntryView;
        })(cpzlqk.ZlEntryPluginView);
    })(pdcpycssbhgwtmxEntry = cpzlqk.pdcpycssbhgwtmxEntry || (cpzlqk.pdcpycssbhgwtmxEntry = {}));
})(cpzlqk || (cpzlqk = {}));
