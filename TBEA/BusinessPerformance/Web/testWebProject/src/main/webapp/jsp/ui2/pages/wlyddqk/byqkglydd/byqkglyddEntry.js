/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
// <reference path="../sbdddcbjpcqkdef.ts" />
///<reference path="../../wlyddqk/wlyddqkEntry.ts"/>
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
var wlyddqk;
(function (wlyddqk) {
    var byqkglyddEntry;
    (function (byqkglyddEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = /** @class */ (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, type, readOnly, cplb) {
                var nodeFirst;
                if (type == wlyddqk.WlyddType.SCDY) {
                    nodeFirst = new JQTable.Node("生产单元（项目公司）", "scdy", readOnly, TextAlign.Center, 0, "text", undefined, false);
                }
                else {
                    var vals = "";
                    for (var i = 0; i < cplb.length; ++i) {
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
                    new JQTable.Node("月产出能力", "rqa", readOnly)
                        .append(new JQTable.Node("产值", "ba", readOnly))
                        .append(new JQTable.Node("产量", "bb", readOnly)),
                    new JQTable.Node("所有可供履约订单总量产值", "ab", readOnly)
                        .append(new JQTable.Node("产值", "cca", readOnly))
                        .append(new JQTable.Node("产量", "ccb", readOnly)),
                    new JQTable.Node("当年可供履约订单总量产值", "ac", readOnly)
                        .append(new JQTable.Node("产值", "da", readOnly))
                        .append(new JQTable.Node("产量", "db", readOnly)),
                    new JQTable.Node("n+1月订单量", "ada", readOnly)
                        .append(new JQTable.Node("产值", "ea", readOnly))
                        .append(new JQTable.Node("产量", "eb", readOnly)),
                    new JQTable.Node("n+2月订单量", "aeb", readOnly)
                        .append(new JQTable.Node("产值", "fc", readOnly))
                        .append(new JQTable.Node("产量", "fb", readOnly)),
                    new JQTable.Node("n+3月订单量", "aec", readOnly)
                        .append(new JQTable.Node("产值", "fc", readOnly))
                        .append(new JQTable.Node("产量", "fb", readOnly)),
                    new JQTable.Node("n+4月订单量", "aed", readOnly)
                        .append(new JQTable.Node("产值", "fc", readOnly))
                        .append(new JQTable.Node("产量", "fb", readOnly)),
                    new JQTable.Node("n+5月订单量", "aef", readOnly)
                        .append(new JQTable.Node("产值", "fc", readOnly))
                        .append(new JQTable.Node("产量", "fb", readOnly)),
                    new JQTable.Node("n+6月订单量", "aeg", readOnly)
                        .append(new JQTable.Node("产值", "fc", readOnly))
                        .append(new JQTable.Node("产量", "fb", readOnly)),
                    new JQTable.Node("n+6月以后可供履约订单", "aeh", readOnly)
                        .append(new JQTable.Node("产值", "fc", readOnly))
                        .append(new JQTable.Node("产量", "fb", readOnly)),
                    new JQTable.Node("次年及以后可供履约订单排产值", "af", readOnly)
                        .append(new JQTable.Node("产值", "ga", readOnly))
                        .append(new JQTable.Node("产量", "gb", readOnly)),
                    new JQTable.Node("交货期待定产值", "ag", readOnly)
                        .append(new JQTable.Node("产值", "ga", readOnly))
                        .append(new JQTable.Node("产量", "gb", readOnly))
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ByqkglyddEntryView = /** @class */ (function (_super) {
            __extends(ByqkglyddEntryView, _super);
            function ByqkglyddEntryView() {
                var _this = _super !== null && _super.apply(this, arguments) || this;
                _this.mAjaxUpdate = new Util.Ajax("/BusinessManagement/sbdddcbjpcqk/byqkglydd/entry/update.do", false);
                _this.mAjaxSave = new Util.Ajax("/BusinessManagement/sbdddcbjpcqk/byqkglydd/entry/save.do", false);
                _this.mAjaxSubmit = new Util.Ajax("/BusinessManagement/sbdddcbjpcqk/byqkglydd/entry/submit.do", false);
                return _this;
            }
            ByqkglyddEntryView.newInstance = function () {
                return new ByqkglyddEntryView();
            };
            ByqkglyddEntryView.prototype.option = function () {
                return this.mOpt;
            };
            ByqkglyddEntryView.prototype.pluginSave = function (dt, compType) {
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
                    }
                    else {
                        Util.Toast.failed(resp.message);
                    }
                });
            };
            ByqkglyddEntryView.prototype.pluginSubmit = function (dt, compType) {
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
                    }
                    else {
                        Util.Toast.failed(resp.message);
                    }
                });
            };
            ByqkglyddEntryView.prototype.pluginUpdate = function (date, compType) {
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
            ByqkglyddEntryView.prototype.isSupported = function (compType) {
                if (compType == Util.CompanyType.SBGS || compType == Util.CompanyType.HBGS || compType == Util.CompanyType.XBC || compType == Util.CompanyType.TBGS) {
                    return true;
                }
                return false;
            };
            ByqkglyddEntryView.prototype.refresh = function () {
                this.raiseReadOnlyChangeEvent(this.mData.statusData.readOnly);
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ByqkglyddEntryView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                entryView.register("可供履约订单情况(产品类别口径)", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.SCLB));
                entryView.register("可供履约订单情况(生产单元口径)", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.WlyddType.SCDY));
                $.extend($.jgrid.edit, {
                    bSubmit: "确定"
                });
            };
            ByqkglyddEntryView.prototype.adjustSize = function () {
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
            ByqkglyddEntryView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                var pagername = this.jqgridName() + "pager";
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table><div id='" + pagername + "'></div>");
                this.mTableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mType, false, this.mData.cplb);
                return this.mTableAssist;
            };
            ByqkglyddEntryView.prototype.updateTable = function () {
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
                //var name = this.option().host + this.option().tb + "_jqgrid_1234";
                //var pagername = name + "pager";
                //this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mType, false, this.mData.cplb);
                //var parent = this.$(this.option().tb);
                //parent.empty();
                //parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                //let jqTable = this.$(name);
                //jqTable.jqGrid(
                //    this.mTableAssist.decorate({
                //        datatype: "local",
                //        data: this.mTableAssist.getDataWithId(this.mData.statusData.data),
                //        multiselect: false,
                //        drag: false,
                //        resize: false,
                //        assistEditable:true,
                //        //autowidth : false,
                //        cellsubmit: 'clientArray',
                //        //editurl: 'clientArray',
                //        cellEdit: true,
                //        //height: data.length > 25 ? 550 : '100%',
                //        //width: titles.length * 200,
                //        rowNum: 20,
                //        height: this.mData.statusData.data.length > 25 ? 550 : '100%',
                //        width: 1400,
                //        shrinkToFit: true,
                //        autoScroll: true,
                //        viewrecords: true,
                //        pager: '#' + pagername,
                //    }));
            };
            return ByqkglyddEntryView;
        }(wlyddqk.BaseEntryPluginView));
        byqkglyddEntry.pluginView = ByqkglyddEntryView.newInstance();
    })(byqkglyddEntry = wlyddqk.byqkglyddEntry || (wlyddqk.byqkglyddEntry = {}));
})(wlyddqk || (wlyddqk = {}));
