/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../yszkgbdef.ts" />
///<reference path="../../messageBox.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var yszkgb;
(function (yszkgb) {
    var yszkkxxzEntry;
    (function (yszkkxxzEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "aa", true, TextAlign.Center),
                    new JQTable.Node("月度", "ab", true, TextAlign.Center)
                        .append(new JQTable.Node("逾期0-1个月", "ba"))
                        .append(new JQTable.Node("逾期1-3月", "bb"))
                        .append(new JQTable.Node("逾期3-6月", "bc"))
                        .append(new JQTable.Node("逾期6-12月", "bd"))
                        .append(new JQTable.Node("逾期1年以上", "be")),
                    new JQTable.Node("逾期款（含到期保证金）", "ag"),
                    new JQTable.Node("未到期(不含质保金)", "ah"),
                    new JQTable.Node("未到期质保金", "ai"),
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var YszkkxxzEntryView = (function (_super) {
            __extends(YszkkxxzEntryView, _super);
            function YszkkxxzEntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("yszkkxxz/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("yszkkxxz/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("yszkkxxz/entry/submit.do", false);
            }
            YszkkxxzEntryView.newInstance = function () {
                return new YszkkxxzEntryView();
            };
            YszkkxxzEntryView.prototype.option = function () {
                return this.mOpt;
            };
            YszkkxxzEntryView.prototype.pluginSave = function (dt, cpType) {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    companyId: cpType,
                    data: JSON.stringify(submitData)
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("保存 成功");
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            YszkkxxzEntryView.prototype.pluginSubmit = function (dt, cpType) {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j]) {
                            Util.MessageBox.tip("有空内容 无法提交");
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
                        Util.MessageBox.tip("提交 成功");
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            YszkkxxzEntryView.prototype.pluginUpdate = function (date, cpType) {
                var _this = this;
                this.mDt = date;
                this.mAjaxUpdate.get({
                    date: date,
                    companyId: cpType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            YszkkxxzEntryView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            YszkkxxzEntryView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                view.register("应收账款款项性质情况", this);
            };
            YszkkxxzEntryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                this.mTableAssist = JQGridAssistantFactory.createTable(name);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                this.$(name).jqGrid(this.mTableAssist.decorate({
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    //height: data.length > 25 ? 550 : '100%',
                    // width: titles.length * 200,
                    rowNum: 150,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    data: this.mTableAssist.getData(this.mData),
                    viewrecords: true
                }));
            };
            return YszkkxxzEntryView;
        })(yszkgb.EntryPluginView);
        yszkkxxzEntry.pluginView = YszkkxxzEntryView.newInstance();
    })(yszkkxxzEntry = yszkgb.yszkkxxzEntry || (yszkgb.yszkkxxzEntry = {}));
})(yszkgb || (yszkgb = {}));
