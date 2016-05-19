var entry_template;
(function (entry_template) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createFlatTable = function (gridName, title, statusList) {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i == 0) {
                    nodes.push(new JQTable.Node(title[i], "_" + i, true, 0 /* Left */));
                }
                else {
                    nodes.push(new JQTable.Node(title[i], "_" + i, statusList[i - 1] == Util.ZBStatus.APPROVED));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mDataSet = new Util.Ajax("zb_update.do", false);
            this.mSubmit = new Util.Ajax("zb_submit.do");
            this.mSave = new Util.Ajax("zb_save.do");
            this.mSubmitToDeputy = new Util.Ajax("zb_submitToDeputy.do");
        }
        View.getInstance = function () {
            return View.instance;
        };
        View.prototype.initInstance = function (opt) {
            this.mOpt = opt;
            switch (this.mOpt.entryType) {
                case 1 /* YDJDMJH */:
                    this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 2 }, Util.addMonth({ year: this.mOpt.date.year, month: this.mOpt.date.month }, 3), this.mOpt.dateId, true);
                    break;
                case 0 /* QNJH */:
                    this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 2 }, { year: this.mOpt.date.year }, this.mOpt.dateId, true);
                    break;
                case 2 /* BY20YJ */:
                case 3 /* BY28YJ */:
                case 4 /* BYSJ */:
                    this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 2 }, this.mOpt.date, this.mOpt.dateId);
                    break;
            }
            this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.updateTitle();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            $("#nodatatips").css("display", "none");
            $("#entryarea").css("display", "");
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == 1 /* YDJDMJH */) {
                date = Util.addMonth(date, -2);
            }
            this.mDataSet.get({ year: date.year, month: date.month, entryType: this.mOpt.entryType, companyId: this.mCompanySelector.getCompany() }).then(function (data) {
                _this.mStatusList = data.status;
                _this.mTableData = data.values;
                _this.updateTitle();
                _this.updateTable(_this.mOpt.tableId);
                _this.updateApproveStatusFromDeputy(date.year, date.month, _this.mOpt.entryType);
                $('#save').css("display", "block");
                $('#submit').css("display", "block");
                if (data.isJydw) {
                    $('#submitToDeputy').css("display", "block");
                }
            });
        };
        View.prototype.updateApproveStatusFromDeputy = function (year, month, entryType) {
            var isShowSubmit_2 = false;
            var isShowapprove_2 = false;
            var approve_2Content = "";
            var submit_2Content = "";
            if ((0 /* QNJH */ == entryType || 4 /* BYSJ */) && this.mStatusList.length == 1) {
                if (this.mStatusList[0] == Util.ZBStatus.SUBMITTED_2) {
                    isShowSubmit_2 = true;
                    if (0 /* QNJH */ == entryType) {
                        submit_2Content = year + "年计划数据";
                    }
                    else {
                        submit_2Content = month + "月实际数据";
                    }
                }
                else if (this.mStatusList[0] == Util.ZBStatus.APPROVED_2) {
                    isShowapprove_2 = true;
                    if (0 /* QNJH */ == entryType) {
                        approve_2Content = year + "年计划数据";
                    }
                    else {
                        approve_2Content = month + "月实际数据";
                    }
                }
                this.addContent(isShowapprove_2, isShowSubmit_2, approve_2Content, submit_2Content);
            }
            if (1 /* YDJDMJH */ == entryType && this.mStatusList.length == 3) {
                var MatchArray = this.initMatchArray(entryType, month, year);
                for (var i = 0; i < this.mStatusList.length; i++) {
                    if (this.mStatusList[i] == Util.ZBStatus.SUBMITTED_2) {
                        isShowSubmit_2 = true;
                        submit_2Content += MatchArray[i] + "月,";
                    }
                    else if (this.mStatusList[i] == Util.ZBStatus.APPROVED_2) {
                        isShowapprove_2 = true;
                        approve_2Content += MatchArray[i] + "月,";
                    }
                }
                if ("" != approve_2Content && isShowapprove_2) {
                    approve_2Content = approve_2Content.substring(0, approve_2Content.length - 1);
                    approve_2Content += "计划数据";
                }
                if ("" != submit_2Content && isShowSubmit_2) {
                    submit_2Content = submit_2Content.substring(0, submit_2Content.length - 1);
                    submit_2Content += "计划数据";
                }
                this.addContent(isShowapprove_2, isShowSubmit_2, approve_2Content, submit_2Content);
            }
            if ((2 /* BY20YJ */ == entryType || 3 /* BY28YJ */ == entryType) && this.mStatusList.length > 1) {
                var MatchArray = this.initMatchArray(entryType, month, year);
                if (month == 12) {
                    for (var i = 0; i < this.mStatusList.length; i++) {
                        if (i == 0) {
                            if (this.mStatusList[0] == Util.ZBStatus.SUBMITTED_2) {
                                isShowSubmit_2 = true;
                                submit_2Content += year + "年" + MatchArray[i] + "月,";
                            }
                            else if (this.mStatusList[0] == Util.ZBStatus.APPROVED_2) {
                                isShowapprove_2 = true;
                                approve_2Content += year + "年" + MatchArray[i] + "月,";
                            }
                        }
                        else {
                            if (this.mStatusList[i] == Util.ZBStatus.SUBMITTED_2) {
                                isShowSubmit_2 = true;
                                submit_2Content += (year + 1) + "年" + MatchArray[i] + "月,";
                            }
                            else if (this.mStatusList[i] == Util.ZBStatus.APPROVED_2) {
                                isShowapprove_2 = true;
                                approve_2Content += (year + 1) + "年" + MatchArray[i] + "月,";
                            }
                        }
                    }
                }
                else {
                    for (var i = 0; i < this.mStatusList.length; i++) {
                        if (this.mStatusList[i] == Util.ZBStatus.SUBMITTED_2) {
                            isShowSubmit_2 = true;
                            submit_2Content += MatchArray[i] + "月,";
                        }
                        else if (this.mStatusList[i] == Util.ZBStatus.APPROVED_2) {
                            isShowapprove_2 = true;
                            approve_2Content += MatchArray[i] + "月,";
                        }
                    }
                }
                if (2 /* BY20YJ */ == entryType) {
                    if ("" != approve_2Content && isShowapprove_2) {
                        approve_2Content = approve_2Content.substring(0, approve_2Content.length - 1);
                        approve_2Content += "20号预计数据";
                    }
                    if ("" != submit_2Content && isShowSubmit_2) {
                        submit_2Content = submit_2Content.substring(0, submit_2Content.length - 1);
                        submit_2Content += "20号预计数据";
                    }
                }
                else if (3 /* BY28YJ */ == entryType) {
                    if ("" != approve_2Content && isShowapprove_2) {
                        approve_2Content = approve_2Content.substring(0, approve_2Content.length - 1);
                        approve_2Content += "预计数据";
                    }
                    if ("" != submit_2Content && isShowSubmit_2) {
                        submit_2Content = submit_2Content.substring(0, submit_2Content.length - 1);
                        submit_2Content += "预计数据";
                    }
                }
                this.addContent(isShowapprove_2, isShowSubmit_2, approve_2Content, submit_2Content);
            }
        };
        View.prototype.initMatchArray = function (entryType, month, year) {
            var retArray = [];
            switch (entryType) {
                case 1 /* YDJDMJH */:
                    retArray.push(month);
                    retArray.push(month + 1);
                    retArray.push(month + 2);
                    break;
                case 2 /* BY20YJ */:
                case 3 /* BY28YJ */:
                    if (12 == month) {
                        retArray.push(12);
                        retArray.push(1);
                        retArray.push(2);
                        retArray.push(3);
                    }
                    else {
                        for (var i = 0; i < this.mStatusList.length; i++) {
                            retArray.push(month + i);
                        }
                    }
                    break;
                default:
                    break;
            }
            return retArray;
        };
        View.prototype.addContent = function (approve_2Mark, submit_2Mark, approve_2Content, submit_2Content) {
            var mergecontent = "";
            if (approve_2Mark) {
                $('#DeputyApprovementStatus').css("display", "block");
                mergecontent += approve_2Content + "被经营副总审核!" + "<br/>";
            }
            if (submit_2Mark) {
                $('#DeputyApprovementStatus').css("display", "block");
                mergecontent += submit_2Content + "尚未被经营副总审核!";
            }
            if ("" != mergecontent) {
                $('#DeputyApprovementStatus')[0].innerHTML = mergecontent;
            }
        };
        View.prototype.save = function () {
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == 1 /* YDJDMJH */) {
                date = Util.addMonth(date, -2);
            }
            var allData = this.mTableAssist.getAllData();
            var submitData = [];
            var colNames = this.mTableAssist.getColNames();
            for (var i = 0; i < allData.length; ++i) {
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j) {
                    if (j != 1) {
                        submitData[i].push(allData[i][j]);
                        allData[i][j] = allData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
            }
            this.mSave.post({
                year: date.year,
                month: date.month,
                entryType: this.mOpt.entryType,
                companyId: this.mCompanySelector.getCompany(),
                data: JSON.stringify(submitData)
            }).then(function (data) {
                if ("true" == data.result) {
                    Util.MessageBox.tip("保存 成功");
                }
                else if ("false" == data.result) {
                    Util.MessageBox.tip("保存 失败");
                }
                else {
                    Util.MessageBox.tip(data.result);
                }
            });
        };
        View.prototype.submit = function () {
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == 1 /* YDJDMJH */) {
                date = Util.addMonth(date, -2);
            }
            var allData = this.mTableAssist.getAllData();
            var submitData = [];
            var colNames = this.mTableAssist.getColNames();
            for (var i = 0; i < allData.length; ++i) {
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j) {
                    if (j != 1) {
                        submitData[i].push(allData[i][j]);
                        if (allData[i][j].replace(new RegExp(' ', 'g'), '') == "") {
                            Util.MessageBox.tip("有空内容 无法提交");
                            return;
                        }
                    }
                }
            }
            this.mSubmit.post({
                year: date.year,
                month: date.month,
                entryType: this.mOpt.entryType,
                companyId: this.mCompanySelector.getCompany(),
                data: JSON.stringify(submitData)
            }).then(function (data) {
                if ("true" == data.result) {
                    Util.MessageBox.tip("提交 成功");
                }
                else if ("false" == data.result) {
                    Util.MessageBox.tip("提交 失败");
                }
                else {
                    Util.MessageBox.tip(data.result);
                }
            });
        };
        View.prototype.submitToDeputy = function () {
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == 1 /* YDJDMJH */) {
                date = Util.addMonth(date, -2);
            }
            var allData = this.mTableAssist.getAllData();
            var submitData = [];
            var colNames = this.mTableAssist.getColNames();
            for (var i = 0; i < allData.length; ++i) {
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j) {
                    if (j != 1) {
                        submitData[i].push(allData[i][j]);
                        if (allData[i][j].replace(new RegExp(' ', 'g'), '') == "") {
                            Util.MessageBox.tip("有空内容 无法提交");
                            return;
                        }
                    }
                }
            }
            this.mSubmitToDeputy.post({
                year: date.year,
                month: date.month,
                entryType: this.mOpt.entryType,
                companyId: this.mCompanySelector.getCompany(),
                data: JSON.stringify(submitData)
            }).then(function (data) {
                if ("true" == data.result) {
                    Util.MessageBox.tip("提交内部审核 成功");
                }
                else if ("false" == data.result) {
                    Util.MessageBox.tip("提交内部审核  失败");
                }
                else {
                    Util.MessageBox.tip(data.result);
                }
            });
        };
        View.prototype.updateTitle = function () {
            var header = "";
            var date = this.mDateSelector.getDate();
            var compName = this.mCompanySelector.getCompanyName();
            switch (this.mOpt.entryType) {
                case 0 /* QNJH */:
                    header = date.year + "年 " + compName + " 计划数据录入";
                    break;
                case 1 /* YDJDMJH */:
                    header = date.year + "年 " + compName + " 季度-月度计划值录入";
                    break;
                case 2 /* BY20YJ */:
                    header = date.year + "年" + date.month + "月 " + compName + " 20日预计值录入";
                    break;
                case 3 /* BY28YJ */:
                    header = date.year + "年" + date.month + "月 " + compName + " 28日预计值录入";
                    break;
                case 4 /* BYSJ */:
                    header = date.year + "年" + date.month + "月 " + compName + " 实际数据录入";
                    break;
            }
            $('h1').text(header);
            document.title = header;
        };
        View.prototype.createPredict = function (title) {
            var ret = [title[0]];
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == 1 /* YDJDMJH */) {
                date = Util.addMonth(date, -3);
            }
            var left = date.month % 3;
            if (this.mOpt.entryType == 1 /* YDJDMJH */ && 0 == left) {
                if (12 == date.month) {
                    ret.push("1月计划");
                    ret.push("2月计划");
                    ret.push("3月计划");
                }
                else {
                    ret.push((date.month + 1) + "月计划");
                    ret.push((date.month + 2) + "月计划");
                    ret.push((date.month + 3) + "月计划");
                }
            }
            else if (this.mOpt.entryType == 2 /* BY20YJ */ || this.mOpt.entryType == 3 /* BY28YJ */) {
                ret.push(title[1]);
                if (0 != left) {
                    var leftMonth = 3 - left;
                    for (var i = 1; i <= leftMonth; ++i) {
                        ret.push((date.month + i) + "月预计");
                    }
                }
                else {
                    if (12 == date.month) {
                        ret.push((date.year + 1) + "年1月预计");
                        ret.push((date.year + 1) + "年2月预计");
                        ret.push((date.year + 1) + "年3月预计");
                    }
                    else {
                        ret.push((date.month + 1) + "月预计");
                        ret.push((date.month + 2) + "月预计");
                        ret.push((date.month + 3) + "月预计");
                    }
                }
            }
            return ret;
        };
        View.prototype.disableEntry = function (tableId) {
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<div style='font-size:18px'>没有可修改的记录</div>");
            $("#submit").css("display", "none");
        };
        View.prototype.enableEntry = function () {
            $("#submit").css("display", "");
        };
        View.prototype.updateTable = function (tableId) {
            var name = tableId + "_jqgrid";
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            this.enableEntry();
            var titles = null;
            switch (this.mOpt.entryType) {
                case 0 /* QNJH */:
                    titles = ["指标名称", "全年计划"];
                    break;
                case 1 /* YDJDMJH */:
                    if (this.mDateSelector.getDate().month % 3 != 0) {
                        this.disableEntry(tableId);
                        return;
                    }
                    else {
                        titles = this.createPredict(["指标名称"]);
                    }
                    break;
                case 2 /* BY20YJ */:
                    titles = this.createPredict(["指标名称", "本月20日预计值"]);
                    break;
                case 3 /* BY28YJ */:
                    titles = this.createPredict(["指标名称", "本月28日预计值"]);
                    break;
                case 4 /* BYSJ */:
                    titles = ["指标名称", "本月实际"];
                    break;
            }
            this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);
            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 2; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j]) {
                        this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) + "";
                    }
                }
            }
            var data = this.mTableData;
            var lastsel = "";
            var lastcell = "";
            $("#" + name).jqGrid(this.mTableAssist.decorate({
                data: this.mTableAssist.getDataWithId(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: data.length > 25 ? 550 : '100%',
                width: titles.length * 200,
                shrinkToFit: true,
                autoScroll: true,
                rowNum: 150,
                onSelectCell: function (id, nm, tmp, iRow, iCol) {
                },
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
        View.instance = new View();
        return View;
    })();
    entry_template.View = View;
})(entry_template || (entry_template = {}));
