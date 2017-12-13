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
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../companySelector.ts" />
/// <reference path="../../util.ts" />
///<reference path="../../jqgrid/jqassist.ts"/>
///<reference path="../../messageBox.ts"/>
///<reference path="../yszkgbdef.ts"/>
var yszkgb;
(function (yszkgb) {
    var yszkyjtztjqs;
    (function (yszkyjtztjqs) {
        var TextAlign = JQTable.TextAlign;
        var BaseEntryPluginView = yszkgb.BaseEntryPluginView;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "aa", true, TextAlign.Center),
                    new JQTable.Node("财务账面应收净收余额", "ab", readOnly),
                    new JQTable.Node("保理余额（加项）", "ac", readOnly),
                    new JQTable.Node("货发票未开金额（加项）", "ad", readOnly),
                    new JQTable.Node("票开货未发金额（减项）", "ae", readOnly),
                    new JQTable.Node("预收款冲减应收（加项）", "af", readOnly),
                    new JQTable.Node("信用证冲减应收（加项）", "ag", readOnly),
                    new JQTable.Node("其他应收科目影响（加项）", "ah", readOnly),
                    new JQTable.Node("预警台账应收账款余额 ", "ai", readOnly)
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var SimplePluginEntryView = (function (_super) {
            __extends(SimplePluginEntryView, _super);
            function SimplePluginEntryView(id) {
                var _this = _super.call(this, id) || this;
                _this.mAjaxUpdate = new Util.Ajax("/BusinessManagement/yszkgb//yszkyjtztjqs/entry/update.do", false);
                _this.mAjaxSave = new Util.Ajax("/BusinessManagement/yszkgb/yszkyjtztjqs/entry/save.do", false);
                _this.mAjaxSubmit = new Util.Ajax("/BusinessManagement/yszkgb/yszkyjtztjqs/entry/submit.do", false);
                return _this;
            }
            SimplePluginEntryView.prototype.pluginSave = function (dt, cpType) {
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
            SimplePluginEntryView.prototype.pluginSubmit = function (dt, cpType) {
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
            SimplePluginEntryView.prototype.pluginUpdate = function (date, cpType) {
                var _this = this;
                this.mDt = date;
                this.mAjaxUpdate.get({
                    date: date,
                    companyId: cpType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData.data;
                    _this.mIsReadOnly = jsonData.readOnly;
                    _this.refresh();
                });
            };
            SimplePluginEntryView.prototype.refresh = function () {
                this.raiseReadOnlyChangeEvent(this.mIsReadOnly);
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            SimplePluginEntryView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                framework.router.to(Util.FAMOUS_VIEW).send(Util.MSG_REG, { name: "应收账款账面与预警台账调节趋势表", plugin: this });
            };
            SimplePluginEntryView.prototype.adjustSize = function () {
                if (document.body.clientHeight < 10 || document.body.clientWidth < 10) {
                    return;
                }
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() <= this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                this.mTableAssist && this.mTableAssist.resizeHeight(maxTableBodyHeight);
                if (this.jqgridHost().width() < this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
            };
            SimplePluginEntryView.prototype.createJqassist = function () {
                var parent = this.$(this.mOpt.tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mIsReadOnly);
                return this.mTableAssist;
            };
            SimplePluginEntryView.prototype.updateTable = function () {
                this.createJqassist();
                var ny = this.mDt.substr(0, this.mDt.length - 2).replace("-", "年") + "月";
                for (var i = 0; i < this.mData.length; ++i) {
                    for (var j = 0; j < this.mData[i].length; ++j) {
                        if ("" != this.mData[i][j]) {
                            this.mData[i][j] = parseFloat(this.mData[i][j]) + "";
                        }
                    }
                }
                this.mTableAssist.create({
                    data: [[ny].concat(this.mData[0])],
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
                    assistEditable: true
                });
                this.adjustSize();
            };
            SimplePluginEntryView.ins = new SimplePluginEntryView("yszkyjtztjqs");
            return SimplePluginEntryView;
        }(BaseEntryPluginView));
    })(yszkyjtztjqs = yszkgb.yszkyjtztjqs || (yszkgb.yszkyjtztjqs = {}));
})(yszkgb || (yszkgb = {}));
