/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
///<reference path="../../messageBox.ts"/>
// <reference path="../sbdddcbjpcqkdef.ts" />
///<reference path="../sbdddcbjpcqkEntry.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var sbdddcbjpcqk;
(function (sbdddcbjpcqk) {
    var xlkglyddEntry;
    (function (xlkglyddEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, type, readOnly, cplb) {
                var nodeFirst;
                if (type == sbdddcbjpcqk.KglyddType.SCDY) {
                    nodeFirst = new JQTable.Node("生产单元（项目公司）", "scdy", readOnly, TextAlign.Center);
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
                    new JQTable.Node("未履约订单总额", "ab", readOnly),
                    new JQTable.Node("当年未履约订单总量", "ac", readOnly),
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
        }());
        var XlkglyddEntryView = (function (_super) {
            __extends(XlkglyddEntryView, _super);
            function XlkglyddEntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("xlkglydd/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("xlkglydd/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("xlkglydd/entry/submit.do", false);
            }
            XlkglyddEntryView.newInstance = function () {
                return new XlkglyddEntryView();
            };
            XlkglyddEntryView.prototype.option = function () {
                return this.mOpt;
            };
            XlkglyddEntryView.prototype.pluginSave = function (dt) {
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
                    data: JSON.stringify(submitData),
                    type: this.mType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("保存 成功");
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            XlkglyddEntryView.prototype.pluginSubmit = function (dt) {
                var allData = this.mTableAssist.getAllData();
                var submitData = [];
                for (var i = 0; i < allData.length; ++i) {
                    submitData.push([]);
                    for (var j = 0; j < allData[i].length - 2; ++j) {
                        submitData[i].push(allData[i][j + 2]);
                        submitData[i][j] = submitData[i][j].replace(new RegExp(' ', 'g'), '');
                        if ("" == submitData[i][j]) {
                            Util.MessageBox.tip("有空内容 无法提交");
                            return;
                        }
                    }
                }
                this.mAjaxSubmit.post({
                    date: dt,
                    data: JSON.stringify(submitData),
                    type: this.mType
                }).then(function (resp) {
                    if (Util.ErrorCode.OK == resp.errorCode) {
                        Util.MessageBox.tip("提交 成功");
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            XlkglyddEntryView.prototype.pluginUpdate = function (date) {
                var _this = this;
                this.mDt = date;
                this.mAjaxUpdate.get({
                    type: this.mType,
                    date: date
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
                entryView.register("线缆可供履约订单变化情况按生产类别", new sbdddcbjpcqk.TypeEntryViewProxy(this, sbdddcbjpcqk.KglyddType.SCLB));
                entryView.register("线缆可供履约订单变化情况按生产单元", new sbdddcbjpcqk.TypeEntryViewProxy(this, sbdddcbjpcqk.KglyddType.SCDY));
            };
            XlkglyddEntryView.prototype.updateTable = function () {
                var _this = this;
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mType, false, this.mData.cplb);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                this.$(name).jqGrid(this.mTableAssist.decorate({
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    //autowidth : false,
                    //cellsubmit: 'clientArray',
                    editurl: 'clientArray',
                    //cellEdit: true,
                    //height: data.length > 25 ? 550 : '100%',
                    // width: titles.length * 200,
                    rowNum: 150,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    data: this.mTableAssist.getData(this.mData.statusData.data),
                    viewrecords: true,
                    pager: '#' + pagername,
                }));
                this.$(name).bind("jqGridAddEditAfterShowForm", function (event, element, oper) {
                    if (oper == "edit") {
                        var page = _this.$(name).jqGrid('getGridParam', 'page');
                        var selectid = parseInt(_this.$(name).jqGrid('getGridParam', 'selrow'));
                        var rownum = _this.$(name).jqGrid('getGridParam', 'rowNum');
                        var acRowid = ((page - 1) * rownum) + selectid;
                    }
                });
                var editModeWidth = 350;
                this.$(name).bind("jqGridAddEditAfterSubmit", function (event, element, data, oper) {
                    if (oper == "add") {
                        //data.t0 = this.mCompanyName;
                        //this.mEditOper = "add";
                        var submitData = [];
                        for (var row = 0; row < 1; row++) {
                            submitData.push([]);
                            submitData[row].push(data["t0"]);
                            for (var col in data) {
                                if (col != "id" && col != "oper" && col != "t0") {
                                    submitData[row].push(data[col]);
                                }
                            }
                        }
                    }
                    else if (oper == "edit") {
                    }
                });
                //if (this.mCompanyName == "股份公司") {
                //    $("#" + childName).jqGrid('navGrid', '#pager', {
                //        del: false, add: false, edit: false,
                //        addfunc: function() {
                //            var dataEdit = $("#" + childName).data("formProp");
                //            if (undefined != dataEdit) {
                //                dataEdit.width = editModeWidth;
                //                dataEdit.datawidth = "auto";
                //                $("#" + childName).data("formProp", dataEdit);
                //            }
                //            $("#" + childName).jqGrid("editGridRow", "new", { width: editModeWidth });
                //        },
                //        editfunc: function(sr) {
                //            var dataEdit = this.$(name).data("formProp");
                //            if (undefined != dataEdit) {
                //                dataEdit.width = editModeWidth;
                //                dataEdit.datawidth = "auto";
                //                this.$(name).data("formProp", dataEdit);
                //            }
                //            this.$(name).jqGrid("editGridRow", sr, { width: editModeWidth });
                //        }
                //    }, {}, {}, {}, { multipleSearch: true });
                //} else {
                this.$(name).jqGrid('navGrid', '#' + pagername, {
                    del: false, add: true, edit: true, refresh: false,
                    addfunc: function () {
                        var dataEdit = _this.$(name).data("formProp");
                        if (undefined != dataEdit) {
                            dataEdit.width = editModeWidth;
                            dataEdit.datawidth = "auto";
                            _this.$(name).data("formProp", dataEdit);
                        }
                        _this.$(name).jqGrid("editGridRow", "new", { width: editModeWidth });
                    },
                    editfunc: function (sr) {
                        var dataEdit = _this.$(name).data("formProp");
                        if (undefined != dataEdit) {
                            dataEdit.width = editModeWidth;
                            dataEdit.datawidth = "auto";
                            _this.$(name).data("formProp", dataEdit);
                        }
                        _this.$(name).jqGrid("editGridRow", sr, { width: editModeWidth });
                    }
                }, { width: editModeWidth }, {}, { multipleSearch: true });
                //}
            };
            return XlkglyddEntryView;
        }(sbdddcbjpcqk.BaseEntryPluginView));
        xlkglyddEntry.pluginView = XlkglyddEntryView.newInstance();
    })(xlkglyddEntry = sbdddcbjpcqk.xlkglyddEntry || (sbdddcbjpcqk.xlkglyddEntry = {}));
})(sbdddcbjpcqk || (sbdddcbjpcqk = {}));
