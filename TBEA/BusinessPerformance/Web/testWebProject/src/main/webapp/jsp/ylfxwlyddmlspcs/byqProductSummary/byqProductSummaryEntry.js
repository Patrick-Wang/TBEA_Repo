/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../chgbdef.ts" />
///<reference path="../../messageBox.ts"/>
///<reference path="../chgbEntry.ts"/>
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var chgb;
(function (chgb) {
    var chxzqkEntry;
    (function (chxzqkEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "chxzqk_rq_entry", true, TextAlign.Center),
                    new JQTable.Node("原材料", "chxzqk_ycl_entry", false),
                    new JQTable.Node("半成品", "chxzqk_bcp_entry", false),
                    new JQTable.Node("实际库存商品", "chxzqk_sjkcsp_entry", false),
                    new JQTable.Node("已发货未开票", "chxzqk_yfhwkp_entry", false),
                    new JQTable.Node("期货浮动盈亏(盈+，亏-)", "chxzqk_qhfdyk_entry", false),
                    new JQTable.Node("期货平仓盈亏(盈-，亏+)", "chxzqk_qhpc_entry", false),
                    new JQTable.Node("未发货已开票", "chxzqk_wfhykp_entry", false),
                    new JQTable.Node("其他", "chxzqk_qt_entry", false)
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ChxzqkEntryView = (function (_super) {
            __extends(ChxzqkEntryView, _super);
            function ChxzqkEntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("chxzqk/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("chxzqk/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("chxzqk/entry/submit.do", false);
            }
            ChxzqkEntryView.newInstance = function () {
                return new ChxzqkEntryView();
            };
            ChxzqkEntryView.prototype.option = function () {
                return this.mOpt;
            };
            ChxzqkEntryView.prototype.pluginSave = function (dt, cpType) {
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
            ChxzqkEntryView.prototype.pluginSubmit = function (dt, cpType) {
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
            ChxzqkEntryView.prototype.pluginUpdate = function (date, cpType) {
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
            ChxzqkEntryView.prototype.refresh = function () {
                this.raiseReadOnlyChangeEvent(this.mIsReadOnly);
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ChxzqkEntryView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                entryView.register("存货账龄变化情况", this);
            };
            ChxzqkEntryView.prototype.updateTable = function () {
                var _this = this;
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mIsReadOnly);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var ny = this.mDt.substr(0, this.mDt.length - 2).replace("-", "年") + "月";
                for (var i = 0; i < this.mData.length; ++i) {
                    for (var j = 0; j < this.mData[i].length; ++j) {
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
                                left: _this.$(name).offset().left + _this.$(name).width() / 2 - 290 / 2,
                                top: _this.$(name).offset().top + _this.$(name).height() / 2 - 90
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
            return ChxzqkEntryView;
        }(chgb.BaseEntryPluginView));
        chxzqkEntry.pluginView = ChxzqkEntryView.newInstance();
    })(chxzqkEntry = chgb.chxzqkEntry || (chgb.chxzqkEntry = {}));
})(chgb || (chgb = {}));
