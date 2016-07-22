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
    var chjykcbEntry;
    (function (chjykcbEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("项目", "chjykcbentry_xm", true, TextAlign.Center),
                    new JQTable.Node("项目", "chjykcbentry_xm1", true, TextAlign.Center),
                    new JQTable.Node("上月余额", "chjykcbentry_syye", false),
                    new JQTable.Node("本月新增", "chjykcbentry_byxz", false),
                    new JQTable.Node("本月处置", "chjykcbentry_bycz", false),
                    new JQTable.Node("期末余额", "chjykcbentry_qmye", false)
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var ChjykcbEntryView = (function (_super) {
            __extends(ChjykcbEntryView, _super);
            function ChjykcbEntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("chjykcb/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("chjykcb/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("chjykcb/entry/submit.do", false);
            }
            ChjykcbEntryView.newInstance = function () {
                return new ChjykcbEntryView();
            };
            ChjykcbEntryView.prototype.option = function () {
                return this.mOpt;
            };
            ChjykcbEntryView.prototype.pluginSave = function (dt, cpType) {
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
            ChjykcbEntryView.prototype.pluginSubmit = function (dt, cpType) {
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
            ChjykcbEntryView.prototype.pluginUpdate = function (date, cpType) {
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
            ChjykcbEntryView.prototype.refresh = function () {
                this.raiseReadOnlyChangeEvent(this.mIsReadOnly);
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ChjykcbEntryView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                entryView.register("积压库存表", this);
            };
            ChjykcbEntryView.prototype.updateTable = function () {
                var name = this.option().host + this.option().tb + "_jqgrid_1234";
                this.mTableAssist = JQGridAssistantFactory.createTable(name, this.mIsReadOnly);
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + name + "'></table>");
                var data = [];
                data.push(["积压库存（原值）"].concat(this.mData[0]));
                data.push(["积压库存（原值）"].concat(this.mData[1]));
                data.push(["积压库存（原值）"].concat(this.mData[2]));
                this.mTableAssist.mergeRow(0);
                this.mTableAssist.mergeTitle();
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
                    data: this.mTableAssist.getData(data),
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
            return ChjykcbEntryView;
        })(chgb.BaseEntryPluginView);
        chjykcbEntry.pluginView = ChjykcbEntryView.newInstance();
    })(chjykcbEntry = chgb.chjykcbEntry || (chgb.chjykcbEntry = {}));
})(chgb || (chgb = {}));
