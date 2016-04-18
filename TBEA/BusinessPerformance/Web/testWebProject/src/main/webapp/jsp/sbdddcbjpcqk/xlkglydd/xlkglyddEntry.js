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
                if (type == wlyddqk.KglyddType.SCDY) {
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
        })();
        var XlkglyddEntryView = (function (_super) {
            __extends(XlkglyddEntryView, _super);
            function XlkglyddEntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("../sbdddcbjpcqk/xlkglydd/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("../sbdddcbjpcqk/xlkglydd/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("../sbdddcbjpcqk/xlkglydd/entry/submit.do", false);
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
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
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
                    for (var j = 0; j < allData[i].length; ++j) {
                        submitData[i].push(allData[i][j]);
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
                entryView.register("线缆可供履约订单变化情况按生产类别", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.KglyddType.SCLB));
                entryView.register("线缆可供履约订单变化情况按生产单元", new wlyddqk.TypeEntryViewProxy(this, wlyddqk.KglyddType.SCDY));
                $.extend($.jgrid.edit, {
                    bSubmit: "确定"
                });
            };
            XlkglyddEntryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                var pagername = name + "pager";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mType, false, this.mData.cplb);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                var addId = 0;
                var delId = 0;
                var jqTable = this.$(name);
                jqTable.jqGrid(this.mTableAssist.decorate({
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
                    data: this.mTableAssist.getDataWithId(this.mData.statusData.data),
                    viewrecords: true,
                    pager: '#' + pagername
                }));
                jqTable.bind("jqGridAddEditAfterShowForm", function (event, element, oper) {
                    if (oper == "edit") {
                        var page = jqTable.jqGrid('getGridParam', 'page');
                        var selectid = parseInt(jqTable.jqGrid('getGridParam', 'selrow'));
                        var rownum = jqTable.jqGrid('getGridParam', 'rowNum');
                        var acRowid = ((page - 1) * rownum) + selectid;
                    }
                });
                var editModeWidth = 350;
                jqTable.bind("jqGridAddEditAfterSubmit", function (event, element, data, oper) {
                    if (oper == "add") {
                        jqTable.addRowData("add" + (++addId), data, 'first');
                    }
                    else if (oper == "edit") {
                        var selectid = parseInt(jqTable.jqGrid('getGridParam', 'selrow'));
                        jqTable.setRowData(selectid, data);
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
                //            var dataEdit = jqTable.data("formProp");
                //            if (undefined != dataEdit) {
                //                dataEdit.width = editModeWidth;
                //                dataEdit.datawidth = "auto";
                //                jqTable.data("formProp", dataEdit);
                //            }
                //            jqTable.jqGrid("editGridRow", sr, { width: editModeWidth });
                //        }
                //    }, {}, {}, {}, { multipleSearch: true });
                //} else {
                jqTable.jqGrid('navGrid', '#' + pagername, {
                    del: false, add: true, edit: true, refresh: false,
                    addfunc: function () {
                        var dataEdit = jqTable.data("formProp");
                        if (undefined != dataEdit) {
                            dataEdit.width = editModeWidth;
                            dataEdit.datawidth = "auto";
                            jqTable.data("formProp", dataEdit);
                        }
                        jqTable.jqGrid("editGridRow", "new", { width: editModeWidth, closeAfterEdit: true, closeAfterAdd: true });
                    },
                    editfunc: function (sr) {
                        var dataEdit = jqTable.data("formProp");
                        if (undefined != dataEdit) {
                            dataEdit.width = editModeWidth;
                            dataEdit.datawidth = "auto";
                            jqTable.data("formProp", dataEdit);
                        }
                        jqTable.jqGrid("editGridRow", sr, { width: editModeWidth, closeAfterEdit: true, closeAfterAdd: true });
                    }
                }, { width: editModeWidth }, {}, { multipleSearch: true });
                //}
            };
            return XlkglyddEntryView;
        })(wlyddqk.BaseEntryPluginView);
        xlkglyddEntry.pluginView = XlkglyddEntryView.newInstance();
    })(xlkglyddEntry = sbdddcbjpcqk.xlkglyddEntry || (sbdddcbjpcqk.xlkglyddEntry = {}));
})(sbdddcbjpcqk || (sbdddcbjpcqk = {}));
