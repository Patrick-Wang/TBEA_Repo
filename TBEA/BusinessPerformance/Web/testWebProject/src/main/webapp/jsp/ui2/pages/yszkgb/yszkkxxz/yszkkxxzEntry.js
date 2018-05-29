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
    var yszkkxxz;
    (function (yszkkxxz) {
        var TextAlign = JQTable.TextAlign;
        var BaseEntryPluginView = yszkgb.BaseEntryPluginView;
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "aa", true, TextAlign.Center),
                    new JQTable.Node("月度", "ab", false, TextAlign.Center)
                        .append(new JQTable.Node("逾期0-1个月", "ba", readOnly))
                        .append(new JQTable.Node("逾期1-3月", "bb", readOnly))
                        .append(new JQTable.Node("逾期3-6月", "bc", readOnly))
                        .append(new JQTable.Node("逾期6-12月", "bd", readOnly))
                        .append(new JQTable.Node("逾期1年以上", "be", readOnly)),
                    new JQTable.Node("未到期(不含质保金)", "ah", readOnly),
                    new JQTable.Node("未到期质保金", "ai", readOnly)
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var SimplePluginEntryView = /** @class */ (function (_super) {
            __extends(SimplePluginEntryView, _super);
            function SimplePluginEntryView(id) {
                var _this = _super.call(this, id) || this;
                _this.mAjaxUpdate = new Util.Ajax("/BusinessManagement/yszkgb//yszkkxxz/entry/update.do", false);
                _this.mAjaxSave = new Util.Ajax("/BusinessManagement/yszkgb/yszkkxxz/entry/save.do", false);
                _this.mAjaxSubmit = new Util.Ajax("/BusinessManagement/yszkgb/yszkkxxz/entry/submit.do", false);
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
                framework.router.to(Util.FAMOUS_VIEW).send(Util.MSG_REG, { name: "应收账款款项性质情况", plugin: this });
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
            SimplePluginEntryView.ins = new SimplePluginEntryView("yszkkxxz");
            return SimplePluginEntryView;
        }(BaseEntryPluginView));
    })(yszkkxxz = yszkgb.yszkkxxz || (yszkgb.yszkkxxz = {}));
})(yszkgb || (yszkgb = {}));
