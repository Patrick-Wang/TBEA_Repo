var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var chgb;
(function (chgb) {
    var chzlbhqkEntry;
    (function (chzlbhqkEntry) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName, readOnly) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("日期", "chzlbhqk_arq_entry", true, TextAlign.Center),
                    new JQTable.Node("5年以上", "chzlbhqk_aa_entry", false),
                    new JQTable.Node("4-5年", "chzlbhqk_ab_entry", false),
                    new JQTable.Node("3-4年", "chzlbhqk_ac_entry", false),
                    new JQTable.Node("2-3年", "chzlbhqk_ad_entry", false),
                    new JQTable.Node("1-2年", "chzlbhqk_ae_entry", false),
                    new JQTable.Node("1年以内", "chzlbhqk_af_entry", false)
                ], gridName);
            };
            return JQGridAssistantFactory;
        }());
        var ChzlbhqkEntryView = (function (_super) {
            __extends(ChzlbhqkEntryView, _super);
            function ChzlbhqkEntryView() {
                _super.apply(this, arguments);
                this.mAjaxUpdate = new Util.Ajax("chzlbhqk/entry/update.do", false);
                this.mAjaxSave = new Util.Ajax("chzlbhqk/entry/save.do", false);
                this.mAjaxSubmit = new Util.Ajax("chzlbhqk/entry/submit.do", false);
            }
            ChzlbhqkEntryView.newInstance = function () {
                return new ChzlbhqkEntryView();
            };
            ChzlbhqkEntryView.prototype.option = function () {
                return this.mOpt;
            };
            ChzlbhqkEntryView.prototype.pluginSave = function (dt, cpType) {
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
            ChzlbhqkEntryView.prototype.pluginSubmit = function (dt, cpType) {
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
            ChzlbhqkEntryView.prototype.pluginUpdate = function (date, cpType) {
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
            ChzlbhqkEntryView.prototype.refresh = function () {
                this.raiseReadOnlyChangeEvent(this.mIsReadOnly);
                if (this.mData == undefined) {
                    return;
                }
                this.updateTable();
            };
            ChzlbhqkEntryView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                entryView.register("存货账龄变化情况", this);
            };
            ChzlbhqkEntryView.prototype.updateTable = function () {
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
                    cellsubmit: 'clientArray',
                    cellEdit: true,
                    rowNum: 150,
                    height: '100%',
                    width: 1200,
                    shrinkToFit: true,
                    autoScroll: true,
                    data: this.mTableAssist.getData([[ny].concat(this.mData[0])]),
                    viewrecords: true,
                    onSelectCell: function (id, nm, tmp, iRow, iCol) {
                    },
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
                            $("#" + name).jqGrid("saveCell", lastsel, lastcell);
                            lastsel = "";
                        }
                    }
                });
            };
            return ChzlbhqkEntryView;
        }(chgb.BaseEntryPluginView));
        chzlbhqkEntry.pluginView = ChzlbhqkEntryView.newInstance();
    })(chzlbhqkEntry = chgb.chzlbhqkEntry || (chgb.chzlbhqkEntry = {}));
})(chgb || (chgb = {}));
