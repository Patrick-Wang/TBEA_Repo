/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="dateSelector.ts" />
/// <reference path="companySelector.ts" />
///<reference path="messageBox.ts"/>
var entry_template;
(function (entry_template) {
    var Cell = JQTable.Cell;
    var Formula = JQTable.Formula;
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createFlatTable = function (gridName, title, statusList) {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i == 0) {
                    nodes.push(new JQTable.Node(title[i], "_" + i, true, JQTable.TextAlign.Left));
                }
                else {
                    nodes.push(new JQTable.Node(title[i], "_" + i, statusList[i - 1] == Util.ZBStatus.APPROVED));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        return JQGridAssistantFactory;
    }());
    function find(data, id) {
        for (var i = 0; i < data.length; ++i) {
            if (data[i][0] == id) {
                return i;
            }
        }
        return -1;
    }
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
                case Util.ZBType.YDJDMJH:
                    this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, Util.addMonth({ year: this.mOpt.date.year, month: this.mOpt.date.month }, 3), this.mOpt.dateId, true);
                    break;
                case Util.ZBType.QNJH:
                    this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, { year: this.mOpt.date.year }, this.mOpt.dateId, true);
                    break;
                case Util.ZBType.BY20YJ:
                case Util.ZBType.BY28YJ:
                case Util.ZBType.BYSJ:
                    this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);
                    break;
            }
            // this.mDateSelector.select(this.mOpt.date);
            this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.updateTitle();
            //this.updateUI();
        };
        View.prototype.getCurrentExchangeRate = function () {
        };
        View.prototype.updateUI = function () {
            var _this = this;
            $("#nodatatips").css("display", "none");
            $("#entryarea").css("display", "");
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH) {
                date = Util.addMonth(date, -2);
            }
            this.mDataSet.get({ year: date.year, month: date.month, entryType: this.mOpt.entryType, companyId: this.mCompanySelector.getCompany() })
                .then(function (data) {
                _this.mStatusList = data.status;
                _this.mTableData = data.values;
                _this.mZbxxs = data.zbxx;
                _this.mRate = data.exchangeRate;
                _this.mExRateZbs = [295, 302, 306];
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
            //年度计划数经营副总审核状态
            var isShowSubmit_2 = false;
            var isShowapprove_2 = false;
            var approve_2Content = "";
            var submit_2Content = "";
            //年度计划数据和实际计划数据
            if ((Util.ZBType.QNJH == entryType || Util.ZBType.BYSJ) && this.mStatusList.length == 1) {
                if (this.mStatusList[0] == Util.ZBStatus.SUBMITTED_2) {
                    isShowSubmit_2 = true;
                    if (Util.ZBType.QNJH == entryType) {
                        submit_2Content = year + "年计划数据";
                    }
                    else {
                        submit_2Content = month + "月实际数据";
                    }
                }
                else if (this.mStatusList[0] == Util.ZBStatus.APPROVED_2) {
                    isShowapprove_2 = true;
                    if (Util.ZBType.QNJH == entryType) {
                        approve_2Content = year + "年计划数据";
                    }
                    else {
                        approve_2Content = month + "月实际数据";
                    }
                }
                this.addContent(isShowapprove_2, isShowSubmit_2, approve_2Content, submit_2Content);
            }
            //月度-季度计划数经营副总审核状态
            if (Util.ZBType.YDJDMJH == entryType && this.mStatusList.length == 3) {
                //Init MatchArray for Month
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
            //20号实际数据和28号实际数据
            if ((Util.ZBType.BY20YJ == entryType || Util.ZBType.BY28YJ == entryType) && this.mStatusList.length > 1) {
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
                if (Util.ZBType.BY20YJ == entryType) {
                    if ("" != approve_2Content && isShowapprove_2) {
                        approve_2Content = approve_2Content.substring(0, approve_2Content.length - 1);
                        approve_2Content += "20号预计数据";
                    }
                    if ("" != submit_2Content && isShowSubmit_2) {
                        submit_2Content = submit_2Content.substring(0, submit_2Content.length - 1);
                        submit_2Content += "20号预计数据";
                    }
                }
                else if (Util.ZBType.BY28YJ == entryType) {
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
                case Util.ZBType.YDJDMJH:
                    retArray.push(month);
                    retArray.push(month + 1);
                    retArray.push(month + 2);
                    break;
                case Util.ZBType.BY20YJ:
                case Util.ZBType.BY28YJ:
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
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH) {
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
            //if (Util.ZBType.BYSJ == this.mOpt.entryType) {
            //    let zbxxs:Zbxx[] = this.checkSum(submitData);
            //    if (zbxxs.length != 0) {
            //        let zbxxs:Zbxx[] = this.checkSum(submitData);
            //        if (zbxxs.length != 0) {
            //            let msg = "";
            //            for (let i = 0; i < zbxxs.length; ++i){
            //                msg += "、" + zbxxs[i].name;
            //            }
            //
            //            Util.MessageBox.tip(msg.substr(1) + " 指标值与子项和不匹配");
            //            return;
            //        }
            //    }
            //}
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
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH) {
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
            if (Util.ZBType.BYSJ == this.mOpt.entryType) {
                //let zbxxs:Zbxx[] = this.checkSum(submitData);
                //if (zbxxs.length != 0) {
                for (var i_1 = 1; i_1 < submitData[0].length; ++i_1) {
                    var zbxxs = this.checkSum(submitData, i_1);
                    if (zbxxs.length != 0) {
                        var msg = "";
                        for (var i_2 = 0; i_2 < zbxxs.length; ++i_2) {
                            msg += "、" + zbxxs[i_2].name;
                        }
                        Util.MessageBox.tip("第" + i_1 + "列 " + msg.substr(1) + " 指标值与子项和不匹配");
                        return;
                    }
                }
                //}
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
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH) {
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
            if (Util.ZBType.BYSJ == this.mOpt.entryType) {
                //let zbxxs:Zbxx[] = this.checkSum(submitData);
                //if (zbxxs.length != 0) {
                for (var i_3 = 1; i_3 < submitData[0].length; ++i_3) {
                    var zbxxs = this.checkSum(submitData, i_3);
                    if (zbxxs.length != 0) {
                        var msg = "";
                        for (var i_4 = 0; i_4 < zbxxs.length; ++i_4) {
                            msg += "、" + zbxxs[i_4].name;
                        }
                        Util.MessageBox.tip("第" + i_3 + "列" + msg.substr(1) + " 指标值与子项和不匹配");
                        return;
                    }
                }
                //}
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
                case Util.ZBType.QNJH:
                    header = date.year + "年 " + compName + " 计划数据录入";
                    break;
                case Util.ZBType.YDJDMJH:
                    header = date.year + "年 " + compName + " 季度-月度计划值录入";
                    break;
                case Util.ZBType.BY20YJ:
                    header = date.year + "年" + date.month + "月 " + compName + " 20日预计值录入";
                    break;
                case Util.ZBType.BY28YJ:
                    header = date.year + "年" + date.month + "月 " + compName + " 28日预计值录入";
                    break;
                case Util.ZBType.BYSJ:
                    header = date.year + "年" + date.month + "月 " + compName + " 实际数据录入";
                    break;
            }
            $('h1').text(header);
            document.title = header;
        };
        View.prototype.createPredict = function (title) {
            var ret = [title[0]];
            var date = this.mDateSelector.getDate();
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH) {
                date = Util.addMonth(date, -3);
            }
            var left = date.month % 3;
            if (this.mOpt.entryType == Util.ZBType.YDJDMJH && 0 == left) {
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
            else if (this.mOpt.entryType == Util.ZBType.BY20YJ ||
                this.mOpt.entryType == Util.ZBType.BY28YJ) {
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
                case Util.ZBType.QNJH:
                    titles = ["指标名称", "全年计划"];
                    break;
                case Util.ZBType.YDJDMJH:
                    if (this.mDateSelector.getDate().month % 3 != 0) {
                        this.disableEntry(tableId);
                        return;
                    }
                    else {
                        titles = this.createPredict(["指标名称"]);
                    }
                    break;
                case Util.ZBType.BY20YJ:
                    titles = this.createPredict(["指标名称", "本月20日预计值"]);
                    break;
                case Util.ZBType.BY28YJ:
                    titles = this.createPredict(["指标名称", "本月28日预计值"]);
                    break;
                case Util.ZBType.BYSJ:
                    titles = ["指标名称", "本月实际"];
                    break;
            }
            this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);
            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 2; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j]) {
                        this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) + "";
                    }
                    else {
                        if (347 == this.mTableData[i][0]) {
                            if (this.mOpt.entryType == Util.ZBType.BYSJ ||
                                this.mOpt.entryType == Util.ZBType.BY28YJ ||
                                this.mOpt.entryType == Util.ZBType.BY20YJ) {
                                this.mTableData[i][j] = 0;
                            }
                        }
                    }
                }
            }
            if (Util.ZBType.BYSJ == this.mOpt.entryType) {
                var disabledCell = [];
                for (var i_5 = 0; i_5 < this.mZbxxs.length; ++i_5) {
                    var zbxx = this.mZbxxs[i_5];
                    if (find(this.mTableData, zbxx.id) >= 0) {
                        for (var j_1 = 0; j_1 < zbxx.children.length; ++j_1) {
                            var cells_1 = this.parseZbxx(zbxx.children[j_1]);
                            if (cells_1.length > 0) {
                                disabledCell = disabledCell.concat(cells_1);
                            }
                        }
                    }
                }
                var cells = this.parseZbxx48();
                if (cells.length > 0) {
                    disabledCell = disabledCell.concat(cells);
                }
                if (disabledCell.length != 0) {
                    this.mTableAssist.disableCellEdit(disabledCell);
                }
            }
            var data = this.mTableData;
            $("#" + name).jqGrid(this.mTableAssist.decorate({
                data: this.mTableAssist.getDataWithId(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                cellsubmit: 'clientArray',
                cellEdit: true,
                height: data.length > 25 ? 550 : '100%',
                width: titles.length * 200,
                shrinkToFit: true,
                autoScroll: true,
                rowNum: 1000,
                assistEditable: true
            }));
        };
        View.prototype.parseZbxx = function (zbxx) {
            var row = find(this.mTableData, zbxx.id);
            if (row < 0) {
                return [];
            }
            var dsts = [];
            for (var i = 1; i < this.mTableData[0].length - 1; ++i) {
                var cells = [];
                for (var j = 0; j < zbxx.children.length; ++j) {
                    var row2 = find(this.mTableData, zbxx.children[j].id);
                    if (row2 >= 0) {
                        var cel = new Cell(row2, i);
                        if (Util.indexOf(this.mExRateZbs, zbxx.children[j].id) >= 0) {
                            cel.rate = this.mRate;
                        }
                        else {
                            cel.rate = 1;
                        }
                        cells.push(cel);
                    }
                }
                if (cells.length == 0) {
                    return [];
                }
                var dst = new Cell(row, i);
                dsts.push(dst);
                var form = new Formula(dst, cells, function (dest, srcs) {
                    var sum;
                    for (var i_6 = 0; i_6 < srcs.length; ++i_6) {
                        var val = srcs[i_6].getVal();
                        if ("" != val) {
                            if (sum == undefined) {
                                sum = parseFloat(val) * srcs[i_6].rate;
                            }
                            else {
                                sum += parseFloat(val) * srcs[i_6].rate;
                            }
                        }
                    }
                    if (sum != undefined) {
                        sum = sum.toFixed(4);
                    }
                    return sum;
                });
                this.mTableAssist.addFormula(form);
            }
            return dsts;
        };
        View.prototype.parseZbxx48 = function () {
            var dsts = [];
            var row = find(this.mTableData, 48);
            if (row >= 0) {
                return [];
            }
            for (var i = 1; i < this.mTableData[0].length - 1; ++i) {
                var cells = [];
                var cellTmp = new Cell(find(this.mTableData, 290), 1);
                if (cellTmp.row() >= 0) {
                    cells.push(cellTmp);
                }
                cellTmp = new Cell(find(this.mTableData, 299), 1);
                if (cellTmp.row() >= 0) {
                    cells.push(cellTmp);
                }
                cellTmp = new Cell(find(this.mTableData, 304), 1);
                if (cellTmp.row() >= 0) {
                    cells.push(cellTmp);
                }
                if (cells.length == 0) {
                    return [];
                }
                var dst = new Cell(row, 1);
                dsts.push(dst);
                var form = new Formula(dst, cells, function (dest, srcs) {
                    var sum;
                    for (var i_7 = 0; i_7 < srcs.length; ++i_7) {
                        var val = srcs[i_7].getVal();
                        if ("" != val) {
                            if (sum == undefined) {
                                sum = parseFloat(val);
                            }
                            else {
                                sum += parseFloat(val);
                            }
                        }
                    }
                    if (sum != undefined) {
                        sum = sum.toFixed(4);
                    }
                    return sum;
                });
                this.mTableAssist.addFormula(form);
            }
            return dsts;
        };
        View.prototype.checkSum = function (submitData, col) {
            var zbxxs = [];
            var zbxx;
            for (var i = 0; i < this.mZbxxs.length; ++i) {
                zbxx = this.mZbxxs[i];
                var row = find(submitData, zbxx.id);
                if (row < 0) {
                    continue;
                }
                var sum = void 0;
                for (var j = 0; j < zbxx.children.length; ++j) {
                    var row2 = find(submitData, zbxx.children[j].id);
                    if (row2 < 0) {
                        continue;
                    }
                    if (submitData[row2][1] != "") {
                        if (sum == undefined) {
                            sum = parseFloat(submitData[row2][col]);
                        }
                        else {
                            sum += parseFloat(submitData[row2][col]);
                        }
                    }
                    else {
                        if (sum == undefined) {
                            sum = 0;
                        }
                    }
                }
                if (sum != undefined) {
                    if (submitData[row][col] == "" && sum != 0) {
                        zbxxs.push(zbxx);
                    }
                    else if (Math.abs(sum - parseFloat(submitData[row][col])) > 2) {
                        zbxxs.push(zbxx);
                    }
                    sum = undefined;
                }
            }
            return zbxxs;
        };
        View.instance = new View();
        return View;
    }());
    entry_template.View = View;
})(entry_template || (entry_template = {}));
