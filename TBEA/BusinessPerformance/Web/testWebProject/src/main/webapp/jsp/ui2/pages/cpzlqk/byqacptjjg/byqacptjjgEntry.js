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
/// <reference path="../cpzlqkdef.ts"/>
///<reference path="../cpzlqkEntry.ts"/>
var pluginEntry;
(function (pluginEntry) {
    pluginEntry.byqacptjjg = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var cpzlqk;
(function (cpzlqk) {
    var byqacptjjgEntry;
    (function (byqacptjjgEntry) {
        var TextAlign = JQTable.TextAlign;
        var Node = JQTable.Node;
        var JQGridAssistantFactory = /** @class */ (function () {
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
        }());
        var EntryView = /** @class */ (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjaxUpdate = new Util.Ajax("/BusinessManagement/byqacptjjg/entry/update.do", false);
                _this.mAjaxSave = new Util.Ajax("/BusinessManagement/byqacptjjg/entry/save.do", false);
                _this.mAjaxSubmit = new Util.Ajax("/BusinessManagement/byqacptjjg/entry/submit.do", false);
                return _this;
                //private updateTable():void {
                //    var name = this.option().host + this.option().tb + "_jqgrid_uiframe";
                //    var pagername = name + "pager";
                //    this.mTableAssist = JQGridAssistantFactory.createTable(name, Util.ZBStatus.APPROVED == this.mData.status);
                //
                //    var parent = this.$(this.option().tb);
                //    parent.empty();
                //    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                //    let jqTable = this.$(name);
                //    this.mTableAssist.mergeColum(0);
                //    this.mTableAssist.mergeRow(0);
                //    this.mTableAssist.mergeTitle(0);
                //    jqTable.jqGrid(
                //        this.mTableAssist.decorate({
                //            datatype: "local",
                //            data: this.mTableAssist.getDataWithId(this.mData.tjjg),
                //            multiselect: false,
                //            drag: false,
                //            resize: false,
                //            assistEditable: Util.ZBStatus.APPROVED != this.mData.status,
                //            //autowidth : false,
                //            cellsubmit: 'clientArray',
                //            //editurl: 'clientArray',
                //            cellEdit: true,
                //            // height: data.length > 25 ? 550 : '100%',
                //            // width: titles.length * 200,
                //            rowNum: 1000,
                //            height: '100%',
                //            width: 1200,
                //            shrinkToFit: true,
                //            autoScroll: true,
                //            //pager: '#' + pagername,
                //            viewrecords: true
                //        }));
                //}
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.byqacptjjg;
            };
            EntryView.prototype.getMonth = function () {
                return Util.toDate(this.mDt).month;
            };
            EntryView.prototype.getYear = function () {
                return Util.toDate(this.mDt).year;
            };
            EntryView.prototype.isSupported = function (compType) {
                if (compType == Util.CompanyType.SBGS ||
                    compType == Util.CompanyType.HBGS ||
                    compType == Util.CompanyType.XBC) {
                    return true;
                }
                return false;
            };
            EntryView.prototype.option = function () {
                return this.mOpt;
            };
            EntryView.prototype.pluginSave = function (dt, compType) {
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
                    submitData.push([allData[i][0]]);
                    for (var j = 3; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j - 3] = submitData[i][j - 3].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j - 3]) {
                            //Util.MessageBox.tip("有空内容 无法提交")
                            //return;
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
                            year: _this.getYear(),
                            month: _this.getMonth(),
                            pageType: 2,
                            tableStatus: JSON.stringify([
                                {
                                    id: plugin.byqacptjjg
                                }, {
                                    id: plugin.byqadwtjjg
                                }
                                //,{
                                //    id:plugin.byqcpycssbhgwtmx,
                                //    status:Util.ZBStatus.SUBMITTED
                                //},{
                                //    id:plugin.byqcpycssbhgxxfb,
                                //    status:Util.ZBStatus.SUBMITTED
                                //}
                            ])
                        };
                        window.location.href = encodeURI("/BusinessManagement/cpzlqk/v2/show.do?breads=" + breads + "&param=" + JSON.stringify(param));
                        //this.pluginUpdate(dt, compType);
                        // });
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
                    .send(framework.basic.FrameEvent.FE_REGISTER, "按产品统计结果");
            };
            EntryView.prototype.adjustSize = function () {
                if (document.body.clientHeight < 10 || document.body.clientWidth < 10) {
                    return;
                }
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() <= this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.mTableAssist && this.mTableAssist.resizeHeight(maxTableBodyHeight);
                if (this.jqgridHost().width() < this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            };
            EntryView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table><div id='" + this.jqgridName() + "pager'></div>");
                this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), Util.ZBStatus.APPROVED == this.mData.status);
                this.mTableAssist.mergeTitle();
                this.mTableAssist.mergeColum(0);
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
                    rowNum: 1000,
                    assistEditable: Util.ZBStatus.APPROVED != this.mData.status,
                    //pager: '#' + this.jqgridName()  + "pager",
                    viewrecords: true
                });
                this.adjustSize();
            };
            EntryView.ins = new EntryView();
            return EntryView;
        }(cpzlqk.ZlEntryPluginView));
    })(byqacptjjgEntry = cpzlqk.byqacptjjgEntry || (cpzlqk.byqacptjjgEntry = {}));
})(cpzlqk || (cpzlqk = {}));
