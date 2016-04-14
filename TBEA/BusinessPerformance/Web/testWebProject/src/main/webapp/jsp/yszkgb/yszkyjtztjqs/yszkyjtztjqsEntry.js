/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../yszkgbdef.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="../yszkgbEntry.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var yszkgb;
(function (yszkgb) {
    var yszkyjtztjqsEntry;
    (function (yszkyjtztjqsEntry) {
        var TextAlign = JQTable.TextAlign;
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
        })();
        var YszkyjtztjqsEntryView = (function (_super) {
            __extends(YszkyjtztjqsEntryView, _super);
            function YszkyjtztjqsEntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("yszkyjtztjqs/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("yszkyjtztjqs/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("yszkyjtztjqs/entry/submit.do", false);
            }
            YszkyjtztjqsEntryView.prototype.setReadOnlyCallBack = function (callBack) {
                this.mReadOnlyChange = callBack;
            };
            YszkyjtztjqsEntryView.newInstance = function () {
                return new YszkyjtztjqsEntryView();
            };
            YszkyjtztjqsEntryView.prototype.option = function () {
                return this.mOpt;
            };
            YszkyjtztjqsEntryView.prototype.pluginSave = function (dt, cpType) {
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
                        Util.MessageBox.tip("保存 成功");
                    }
                    else {
                        Util.MessageBox.tip(resp.message);
                    }
                });
            };
            YszkyjtztjqsEntryView.prototype.pluginSubmit = function (dt, cpType) {
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
            YszkyjtztjqsEntryView.prototype.pluginUpdate = function (date, cpType) {
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
            YszkyjtztjqsEntryView.prototype.refresh = function () {
                this.mReadOnlyChange(this.mIsReadOnly);
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            YszkyjtztjqsEntryView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                entryView.register("应收账款账面与预警台账调节趋势表", this);
            };
            YszkyjtztjqsEntryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mIsReadOnly);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var ny = this.mDt.substr(0, this.mDt.length - 2).replace("-", "年") + "月";
                for (var i = 0; i < this.mData.length; ++i) {
                    for (var j = 2; j < this.mData[i].length; ++j) {
                        if ("" != this.mData[i][j]) {
                            this.mData[i][j] = parseFloat(this.mData[i][j]) + "";
                        }
                    }
                }
                var lastsel = "";
                var lastcell = "";
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
                    data: this.mTableAssist.getData([[ny].concat(this.mData[0])]),
                    viewrecords: true,
                    onSelectCell: function (id, nm, tmp, iRow, iCol) {
                        //                       console.log(iRow +', ' + iCol);
                    },
                    //                    onCellSelect: (ri,ci,tdHtml,e) =>{
                    //                       console.log(ri +', ' + ci);
                    //                    },
                    beforeSaveCell: function (rowid, cellname, v, iRow, iCol) {
                        var ret = parseFloat(v.replace(new RegExp(',', 'g'), ''));
                        if (isNaN(ret)) {
                            $.jgrid.jqModal = {
                                width: 290,
                                left: $("#table").offset().left + $("#table").width() / 2 - 290 / 2,
                                top: $("#table").offset().top + $("#table").height() / 2 - 90
                            };
                            return v;
                        }
                        else {
                            return ret;
                        }
                    },
                    beforeEditCell: function (rowid, cellname, v, iRow, iCol) {
                        lastsel = iRow;
                        lastcell = iCol;
                        //                        console.log(iRow +', ' + iCol);
                        $("input").attr("disabled", true);
                    },
                    afterEditCell: function (rowid, cellname, v, iRow, iCol) {
                        $("input[type=text]").bind("keydown", function (e) {
                            if (e.keyCode === 13) {
                                setTimeout(function () {
                                    $("#" + name).jqGrid("editCell", iRow + 1, iCol, true);
                                }, 10);
                            }
                        });
                    },
                    afterSaveCell: function () {
                        $("input").attr("disabled", false);
                        lastsel = "";
                    },
                    afterRestoreCell: function () {
                        $("input").attr("disabled", false);
                        lastsel = "";
                    }
                }));
                $('html').bind('click', function (e) {
                    if (lastsel != "") {
                        if ($(e.target).closest("#" + name).length == 0) {
                            //  $("#" + name).jqGrid('saveRow', lastsel);
                            $("#" + name).jqGrid("saveCell", lastsel, lastcell);
                            //$("#" + name).resetSelection();
                            lastsel = "";
                        }
                    }
                });
            };
            return YszkyjtztjqsEntryView;
        })(yszkgb.EntryPluginView);
        yszkyjtztjqsEntry.pluginView = YszkyjtztjqsEntryView.newInstance();
    })(yszkyjtztjqsEntry = yszkgb.yszkyjtztjqsEntry || (yszkgb.yszkyjtztjqsEntry = {}));
})(yszkgb || (yszkgb = {}));
