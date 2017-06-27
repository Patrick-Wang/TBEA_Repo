/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
// <reference path="../sbdddcbjpcqkdef.ts" />
///<reference path="../../wlyddqk/wlyddqkEntry.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var wlyddqk;
(function (wlyddqk) {
    var xlkglyddEntry;
    (function (xlkglyddEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, type, readOnly, cplb) {
                var nodeFirst;
                if (type == wlyddqk.WlyddType.SCDY) {
                    nodeFirst = new JQTable.Node("生产单元（项目公司）", "scdy", readOnly, TextAlign.Center, 0, "text", undefined, false);
                }
                else {
                    var vals = "";
                    for (var i in cplb) {
                        if (i < cplb.length - 1) {
                            vals += cplb[i] + ':' + cplb[i] + ';';
                        }
                        else {
                            vals += cplb[i] + ':' + cplb[i];
                        }
                    }
                    nodeFirst = new JQTable.Node("产品类别", "sclb", readOnly, TextAlign.Center, 0, "select", { value: vals }, false);
                }
                return new JQTable.JQGridAssistant([
                    nodeFirst,
                    new JQTable.Node("月产出能力（产值）", "rqa", readOnly),
                    new JQTable.Node("可供履约订单总额", "ab", readOnly),
                    new JQTable.Node("当年可供履约订单总量", "ac", readOnly),
                    new JQTable.Node("n+1月订单量", "ada", readOnly)
                        .append(new JQTable.Node("已排产", "ba", readOnly))
                        .append(new JQTable.Node("未排产", "bc", readOnly)),
                    new JQTable.Node("n+2月订单量", "adb", readOnly)
                        .append(new JQTable.Node("已排产", "ba", readOnly))
                        .append(new JQTable.Node("未排产", "bc", readOnly)),
                    new JQTable.Node("n+3月订单量", "adc", readOnly)
                        .append(new JQTable.Node("已排产", "ba", readOnly))
                        .append(new JQTable.Node("未排产", "bc", readOnly)),
                    new JQTable.Node("n+3月以后履约订单", "ae", readOnly),
                    new JQTable.Node("次年及以后可供履约订单排产值", "af", readOnly),
                    new JQTable.Node("交货期待定产值", "ag", readOnly),
                    new JQTable.Node("外协", "ah", readOnly)
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var XlkglyddEntryView = (function (_super) {
            __extends(XlkglyddEntryView, _super);
            function XlkglyddEntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("/BusinessManagement/sbdddcbjpcqk/xlkglydd/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("/BusinessManagement/sbdddcbjpcqk/xlkglydd/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("/BusinessManagement/sbdddcbjpcqk/xlkglydd/entry/submit.do", false);
            }
            XlkglyddEntryView.newInstance = function () {
                return new XlkglyddEntryView();
            };
            XlkglyddEntryView.prototype.option = function () {
                return this.mOpt;
            };
            XlkglyddEntryView.prototype.isSupported = function (compType) {
                if (compType == Util.CompanyType.LLGS || compType == Util.CompanyType.XLC || compType == Util.CompanyType.DLGS) {
                    return true;
                }
                return false;
            };
            XlkglyddEntryView.prototype.pluginSave = function (dt, compType) {
                var _this = this;
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
                this.mAjaxSave.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    type: this.mType,
                    companyId: compType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.Toast.success("保存 成功");
                        _this.pluginUpdate(dt, compType);
                    }
                    else {
                        Util.Toast.failed(resp.message);
                    }
                });
            };
            XlkglyddEntryView.prototype.pluginSubmit = function (dt, compType) {
                var _this = this;
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
                    type: this.mType,
                    companyId: compType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.Toast.success("提交 成功");
                        _this.pluginUpdate(dt, compType);
                    }
                    else {
                        Util.Toast.failed(resp.message);
                    }
                });
            };
            XlkglyddEntryView.prototype.pluginUpdate = function (date, compType) {
                var _this = this;
                this.mDt = date;
                this.mAjaxUpdate.get({
                    type: this.mType,
                    date: date,
                    companyId: compType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            XlkglyddEntryView.prototype.refresh = function () {
                this.raiseReadOnlyChangeEvent(this.mData.statusData.readOnly);
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            XlkglyddEntryView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                entryView.register("可供履约订单情况(产品类别口径)", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.SCLB));
                entryView.register("可供履约订单情况(生产单元口径)", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.SCDY));
                $.extend($.jgrid.edit, {
                    bSubmit: "确定"
                });
            };
            XlkglyddEntryView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() <= this.jqgridHost().find(".ui-jqgrid").width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                //this.mTableAssist.resizeHeight(maxTableBodyHeight);
                //
                //if (this.jqgridHost().width() < this.jqgridHost().find(".ui-jqgrid").width()) {
                //    jqgrid.setGridWidth(this.jqgridHost().width());
                //}
            };
            XlkglyddEntryView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                var pagername = this.jqgridName() + "pager";
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table><div id='" + pagername + "'></div>");
                this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mType, false, this.mData.cplb);
                return this.mTableAssist;
            };
            XlkglyddEntryView.prototype.updateTable = function () {
                this.createJqassist();
                this.mTableAssist.create({
                    dataWithId: this.mData.statusData.data,
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
                    assistEditable: true,
                    pager: '#' + this.jqgridName() + "pager"
                });
                this.adjustSize();
            };
            return XlkglyddEntryView;
        })(wlyddqk.BaseEntryPluginView);
        xlkglyddEntry.pluginView = XlkglyddEntryView.newInstance();
    })(xlkglyddEntry = wlyddqk.xlkglyddEntry || (wlyddqk.xlkglyddEntry = {}));
})(wlyddqk || (wlyddqk = {}));
