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
/// <reference path="../xnyzbdef.ts"/>
var pluginEntry;
(function (pluginEntry) {
    pluginEntry.xnyzb = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var xnyzb;
(function (xnyzb) {
    var xnyzbEntry;
    (function (xnyzbEntry) {
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
        var EntryView = (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                _super.apply(this, arguments);
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.xnyzb;
            };
            EntryView.prototype.onEvent = function (e) {
                switch (e.id) {
                    case framework.basic.FrameEvent.FE_SAVE:
                        {
                        }
                        return;
                    case framework.basic.FrameEvent.FE_SUBMIT:
                        {
                            this.pluginSubmit(e.data.dStart, e.data.dEnd, e.data.compType);
                        }
                        return;
                    case framework.basic.FrameEvent.FE_UPDATE:
                        {
                            this.pluginUpdate(e.data.dStart, e.data.dEnd, e.data.compType);
                        }
                        return;
                    default:
                        break;
                }
                return _super.prototype.onEvent.call(this, e);
            };
            EntryView.prototype.option = function () {
                return this.mOpt;
            };
            //public pluginSave(dStart:string, dEnd:string, compType:Util.CompanyType):void {
            //    var allData = this.mTableAssist.getAllData();
            //    var submitData = [];
            //    for (var i = 0; i < allData.length; ++i) {
            //        submitData.push([]);
            //        for (var j = 0; j < allData[i].length; ++j) {
            //            submitData[i].push(allData[i][j]);
            //            submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
            //        }
            //    }
            //    this.mAjaxSave.post({
            //        dStart: dStart,
            //        data: JSON.stringify(submitData),
            //        dEnd:dEnd,
            //        compId: compType
            //    }).then((resp:Util.IResponse) => {
            //        if (Util.ErrorCode.OK == resp.errorCode) {
            //            Util.MessageBox.tip("保存 成功", ()=>{
            //                this.pluginUpdate(dStart, dEnd, compType);
            //            });
            //        } else {
            //            Util.MessageBox.tip(resp.message);
            //        }
            //    });
            //}
            EntryView.prototype.pluginSubmit = function (dStart, dEnd, compType) {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSubmit.post({
                    dStart: dStart,
                    data: JSON.stringify(submitData),
                    dEnd: dEnd,
                    compId: compType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.Toast.success("提交 成功");
                    }
                    else {
                        Util.Toast.failed(resp.message);
                    }
                });
            };
            EntryView.prototype.pluginUpdate = function (dStart, dEnd, compType) {
                var _this = this;
                this.mCompType = compType;
                this.mAjaxUpdate.get({
                    dStart: dStart,
                    dEnd: dEnd,
                    compId: compType
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
                    .send(framework.basic.FrameEvent.FE_REGISTER, opt.title);
                this.mAjaxUpdate = new Util.Ajax(this.option().updateUrl, false);
                this.mAjaxSubmit = new Util.Ajax(this.option().submitUrl, false);
            };
            EntryView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                //this.tableAssist.resizeHeight(maxTableBodyHeight);
                //
                //if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                //    jqgrid.setGridWidth(this.jqgridHost().width());
                //}
            };
            EntryView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table><div id='" + this.jqgridName() + "pager'></div>");
                this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mData.header);
                return this.mTableAssist;
            };
            EntryView.prototype.updateTable = function () {
                this.createJqassist();
                this.mTableAssist.create({
                    dataWithId: this.mData.data,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    resize: false,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 15,
                    assistEditable: true,
                    autoScroll: true,
                    pager: '#' + this.jqgridName() + "pager",
                });
            };
            EntryView.ins = new EntryView();
            return EntryView;
        })(framework.basic.EntryPluginView);
    })(xnyzbEntry = xnyzb.xnyzbEntry || (xnyzb.xnyzbEntry = {}));
})(xnyzb || (xnyzb = {}));
