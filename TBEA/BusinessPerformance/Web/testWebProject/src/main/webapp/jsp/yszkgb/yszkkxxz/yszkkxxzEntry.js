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
    var yszkkxxzEntry;
    (function (yszkkxxzEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
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
                    new JQTable.Node("逾期款（含到期保证金）", "ag", readOnly),
                    new JQTable.Node("未到期(不含质保金)", "ah", readOnly),
                    new JQTable.Node("未到期质保金", "ai", readOnly),
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
            YszkkxxzEntryView.prototype.setReadOnlyCallBack = function (callBack) {
                this.mReadOnlyChange = callBack;
            };
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
            YszkkxxzEntryView.prototype.pluginSubmit = function (dt, cpType) {
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
            YszkkxxzEntryView.prototype.pluginUpdate = function (date, cpType) {
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
            YszkkxxzEntryView.prototype.refresh = function () {
                this.mReadOnlyChange(this.mIsReadOnly);
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            YszkkxxzEntryView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                entryView.register("应收账款款项性质情况", this);
            };
            YszkkxxzEntryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mIsReadOnly);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var ny = this.mDt.substr(0, this.mDt.length - 2).replace("-", "年") + "月";
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
            return YszkkxxzEntryView;
        })(yszkgb.EntryPluginView);
        yszkkxxzEntry.pluginView = YszkkxxzEntryView.newInstance();
    })(yszkkxxzEntry = yszkgb.yszkkxxzEntry || (yszkgb.yszkkxxzEntry = {}));
})(yszkgb || (yszkgb = {}));
