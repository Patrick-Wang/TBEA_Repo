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
/// <reference path="../chgbdef.ts"/>
var pluginEntry;
(function (pluginEntry) {
    pluginEntry.chjykcb = framework.basic.endpoint.lastId();
})(pluginEntry || (pluginEntry = {}));
var chgb;
(function (chgb) {
    var chjykcbEntry;
    (function (chjykcbEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("项目", "chjykcbentry_xm", true, TextAlign.Center),
                    new JQTable.Node("项目", "chjykcbentry_xm1", true, TextAlign.Center),
                    new JQTable.Node("上月余额", "chjykcbentry_syye", false),
                    new JQTable.Node("本月新增", "chjykcbentry_byxz", false),
                    new JQTable.Node("本月处置", "chjykcbentry_bycz", false),
                    new JQTable.Node("期末余额", "chjykcbentry_qmye", false)
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var EntryView = /** @class */ (function (_super) {
            __extends(EntryView, _super);
            function EntryView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjaxUpdate = new Util.Ajax("/BusinessManagement/chgb/chjykcb/entry/update.do", false);
                _this.mAjaxSave = new Util.Ajax("/BusinessManagement/chgb/chjykcb/entry/save.do", false);
                _this.mAjaxSubmit = new Util.Ajax("/BusinessManagement/chgb/chjykcb/entry/submit.do", false);
                return _this;
            }
            EntryView.prototype.getId = function () {
                return pluginEntry.chjykcb;
            };
            EntryView.prototype.option = function () {
                return this.mOpt;
            };
            EntryView.prototype.pluginSave = function (dt, cpType) {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length - 2; ++j) {
                        submitData[i].push(allData[i][j + 2]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    companyId: cpType,
                    data: JSON.stringify(submitData)
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.Toast.success("保存 成功");
                    }
                    else {
                        Util.Toast.failed(resp.message);
                    }
                });
            };
            EntryView.prototype.pluginSubmit = function (dt, cpType) {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length - 2; ++j) {
                        submitData[i].push(allData[i][j + 2]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j]) {
                            Util.Toast.failed("有空内容 无法提交");
                            return;
                        }
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    companyId: cpType,
                    data: JSON.stringify(submitData)
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.Toast.success("提交 成功");
                    }
                    else {
                        Util.Toast.failed(resp.message);
                    }
                });
            };
            EntryView.prototype.pluginUpdate = function (date, cpType) {
                var _this = this;
                this.mDt = date;
                this.mAjaxUpdate.get({
                    date: date,
                    companyId: cpType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData.data;
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
                    .send(framework.basic.FrameEvent.FE_REGISTER, "积压库存表");
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
                this.mTableAssist.mergeRow(0);
                this.mTableAssist.mergeTitle();
                return this.mTableAssist;
            };
            EntryView.prototype.updateTable = function () {
                this.createJqassist();
                var data = [];
                data.push(["积压库存（原值）"].concat(this.mData[0]));
                data.push(["积压库存（原值）"].concat(this.mData[1]));
                data.push(["积压库存（原值）"].concat(this.mData[2]));
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
    })(chjykcbEntry = chgb.chjykcbEntry || (chgb.chjykcbEntry = {}));
})(chgb || (chgb = {}));
