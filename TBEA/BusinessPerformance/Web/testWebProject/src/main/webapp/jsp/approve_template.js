var approve_template;
(function (approve_template) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createFlatTable = function (gridName, title) {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i == 0) {
                    nodes.push(new JQTable.Node(title[i], "_" + i, true, JQTable.TextAlign.Left, 125));
                }
                else {
                    nodes.push(new JQTable.Node(title[i], "_" + i, false, JQTable.TextAlign.Right, 125));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        JQGridAssistantFactory.createQnjhTable = function (gridName, title, ids) {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i < 1) {
                    nodes.push(new JQTable.Node(title[i], ids[i], true, JQTable.TextAlign.Left, 125));
                }
                else {
                    nodes.push(new JQTable.Node(title[i], ids[i], false, JQTable.TextAlign.Right, 125));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        JQGridAssistantFactory.createSjTable = function (gridName, title, ids) {
            var nodes = [];
            for (var i = 0; i < title.length; ++i) {
                if (i <= 1) {
                    nodes.push(new JQTable.Node(title[i], ids[i], true, JQTable.TextAlign.Left, 125));
                }
                else {
                    nodes.push(new JQTable.Node(title[i], ids[i], false, JQTable.TextAlign.Right, 125));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        return JQGridAssistantFactory;
    })();
    function transposition(data) {
        var dataRet = [];
        for (var i = 0; i < data[0].length; ++i) {
            dataRet.push([]);
            for (var j = 0; j < data.length; ++j) {
                dataRet[i].push(data[j][i]);
            }
            dataRet[i] = dataRet[i].reverse();
        }
        return dataRet;
    }
    function resize(data, size) {
        if (data.length < size) {
            for (var i = data.length; i < size; ++i) {
                data.push("");
            }
            return data;
        }
        else {
            var dataNew = [];
            for (var i = 0; i < size; ++i) {
                dataNew.push(data[i]);
            }
            return dataNew;
        }
    }
    var QNJHSubView = (function () {
        function QNJHSubView(opt) {
            this.mOpt = opt;
        }
        QNJHSubView.prototype.getApprovedData = function () {
            if (this.mTableApproveAssist != null) {
                var ids = this.mTableApproveAssist.getCheckedRowIds();
                $(ids).each(function (i) {
                    ids[i] = parseInt(ids[i]);
                });
                return [ids];
            }
            else {
                return [[]];
            }
        };
        QNJHSubView.prototype.getUnapprovedData = function () {
            if (this.mTableUnapproveAssist != null) {
                var ids = this.mTableUnapproveAssist.getCheckedRowIds();
                $(ids).each(function (i) {
                    ids[i] = parseInt(ids[i]);
                });
                return [ids];
            }
            else {
                return [[]];
            }
        };
        QNJHSubView.prototype.getDate = function () {
            return this.mData;
        };
        QNJHSubView.prototype.process = function (data, date, companies) {
            this.mData = date;
            if (data[0].length > 0) {
                this.mTableApproveAssist = this.updateTable(data[0], companies, this.mOpt.tableApproveId);
            }
            if (data[1].length > 0) {
                this.mTableUnapproveAssist = this.updateTable(data[1], companies, this.mOpt.tableUnapproveId);
            }
        };
        QNJHSubView.prototype.updateTable = function (rawData, comps, tableId) {
            var tmpData = [];
            var title = ["单位名称"];
            var colZbIds = ["dw"];
            var zbColMap = {};
            var compMap = {};
            var companies = [];
            $(comps).each(function (i) {
                $(rawData).each(function (j) {
                    if (rawData[j][0] == "" + comps[i].id) {
                        compMap["c_" + comps[i].id] = comps[i];
                    }
                });
            });
            for (var i in compMap) {
                companies.push(compMap[i]);
            }
            $(rawData).each(function (i) {
                if (!Util.isExist(zbColMap["_" + rawData[i][1]])) {
                    colZbIds.push(rawData[i][1]);
                    title.push(rawData[i][2]);
                    zbColMap["_" + rawData[i][1]] = title.length;
                }
            });
            $(companies).each(function (i) {
                tmpData.push([companies[i].id, companies[i].value]);
                $(rawData).each(function (j) {
                    if (rawData[j][0] == "" + companies[i].id) {
                        if (tmpData[i].length <= zbColMap["_" + rawData[j][1]]) {
                            resize(tmpData[i], zbColMap["_" + rawData[j][1]]);
                        }
                        tmpData[i][zbColMap["_" + rawData[j][1]]] = rawData[j][3];
                    }
                });
            });
            var name = tableId + "_jqgrid";
            var jqAssist = JQGridAssistantFactory.createQnjhTable(name, title, colZbIds);
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            var width = (title.length) * 125;
            if (width > 1000) {
                width = 1000;
            }
            $("#" + name).jqGrid(jqAssist.decorate({
                data: jqAssist.getDataWithId(tmpData),
                datatype: "local",
                multiselect: true,
                drag: false,
                resize: false,
                height: '100%',
                width: width,
                shrinkToFit: width == 1000 ? false : true,
                autoScroll: true,
            }));
            return jqAssist;
        };
        return QNJHSubView;
    })();
    var YJSJSubView = (function () {
        function YJSJSubView(opt) {
            this.mOpt = opt;
        }
        YJSJSubView.prototype.getApprovedData = function () {
            var ret = [[]];
            if (this.mTableApproveAssist != null) {
                var checkedRows = this.mTableApproveAssist.getCheckedRowIds();
                if (checkedRows.length != 0) {
                    ret = this.format(checkedRows);
                }
            }
            return ret;
        };
        YJSJSubView.prototype.format = function (checkedRows) {
            var ret = [];
            var comps = [];
            var years = [];
            var months = [];
            $(checkedRows).each(function (i) {
                var row = checkedRows[i].split("&");
                $(row).each(function (j) {
                    row[j] = parseInt(row[j]);
                });
                comps.push(row[0]);
                if (row.length > 1) {
                    years.push(row[1]);
                    months.push(row[2]);
                }
            });
            ret.push(comps);
            if (years.length > 0) {
                ret.push(years);
            }
            if (months.length > 0) {
                ret.push(months);
            }
            return ret;
        };
        YJSJSubView.prototype.getUnapprovedData = function () {
            var ret = [[]];
            if (this.mTableUnapproveAssist != null) {
                var checkedRows = this.mTableUnapproveAssist.getCheckedRowIds();
                if (checkedRows.length != 0) {
                    ret = this.format(checkedRows);
                }
            }
            return ret;
        };
        YJSJSubView.prototype.getDate = function () {
            return this.mData;
        };
        YJSJSubView.prototype.process = function (data, date, companies) {
            this.mData = date;
            if (data[0].length > 0) {
                this.mTableApproveAssist = this.updateTable(data[0], companies, this.mOpt.tableApproveId);
            }
            if (data[1].length > 0) {
                this.mTableUnapproveAssist = this.updateTable(data[1], companies, this.mOpt.tableUnapproveId);
            }
        };
        YJSJSubView.prototype.updateTable = function (rawData, comps, tableId) {
            var compMap = {};
            var companies = [];
            $(comps).each(function (i) {
                $(rawData).each(function (j) {
                    if (rawData[j][0] == "" + comps[i].id) {
                        compMap["c_" + comps[i].id] = comps[i];
                    }
                });
            });
            for (var i in compMap) {
                companies.push(compMap[i]);
            }
            var title = ["单位名称"];
            var colZbIds = ["dw", "rq"];
            var zbColMap = {};
            $(rawData).each(function (i) {
                if (!Util.isExist(zbColMap["_" + rawData[i][1]])) {
                    colZbIds.push(rawData[i][1]);
                    title.push(rawData[i][2]);
                    zbColMap["_" + rawData[i][1]] = title.length;
                }
            });
            var hasDate = rawData[0].length > 4;
            if (hasDate) {
                title.push("日期");
                colZbIds.push("rq");
            }
            var tmpData = [];
            $(companies).each(function (i) {
                tmpData.push([companies[i].id, companies[i].value]);
                $(rawData).each(function (j) {
                    if (rawData[j][0] == "" + companies[i].id) {
                        if (tmpData.length == i) {
                            if (hasDate) {
                                tmpData.push([companies[i].id + "&" + rawData[j][4] + "&" + rawData[j][5], companies[i].value, rawData[j][4] + "年" + rawData[j][5]] + "月");
                            }
                            else {
                                tmpData.push([companies[i].id, companies[i].value]);
                            }
                        }
                        if (tmpData[i].length <= zbColMap["_" + rawData[j][1]]) {
                            resize(tmpData[i], zbColMap["_" + rawData[j][1]]);
                        }
                        tmpData[i][zbColMap["_" + rawData[j][1]]] = rawData[j][3];
                    }
                });
            });
            var name = tableId + "_jqgrid";
            var jqAssist = JQGridAssistantFactory.createQnjhTable(name, title, colZbIds);
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            var width = (title.length) * 125;
            if (width > 1000) {
                width = 1000;
            }
            $("#" + name).jqGrid(jqAssist.decorate({
                data: jqAssist.getDataWithId(tmpData),
                datatype: "local",
                multiselect: true,
                drag: false,
                resize: false,
                height: '100%',
                width: width,
                shrinkToFit: width == 1000 ? false : true,
                autoScroll: true,
            }));
            return jqAssist;
        };
        return YJSJSubView;
    })();
    var View = (function () {
        function View() {
            this.mDataSet = new Util.Ajax("zb_update.do", false);
            this.mUnapprove = new Util.Ajax("zb_unapprove.do");
            this.mApprove = new Util.Ajax("zb_approve.do");
        }
        View.getInstance = function () {
            return View.instance;
        };
        View.prototype.initInstance = function (opt) {
            this.mOpt = opt;
            if (this.mOpt.approveType == Util.ZBType.YDJDMJH) {
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 1 }, this.mOpt.date, this.mOpt.dateId, true);
            }
            else {
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 1 }, this.mOpt.date, this.mOpt.dateId);
            }
            this.mCompanySelector = new Util.CompanySelector(true, opt.companyId, opt.comps, opt.firstCompany);
            this.mCompanySelector.checkAll();
            switch (this.mOpt.approveType) {
                case Util.ZBType.QNJH:
                    this.mCurView = new QNJHSubView(opt);
                    break;
                case Util.ZBType.YDJDMJH:
                    break;
                case Util.ZBType.BY20YJ:
                case Util.ZBType.BY28YJ:
                case Util.ZBType.BYSJ:
                    this.mCurView = new YJSJSubView(opt);
                    break;
            }
            this.updateTitle();
            this.updateUI();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var comps = this.mCompanySelector.getCompanys();
            if (comps.length != 0) {
                var date = this.mDateSelector.getDate();
                this.mDataSet.post({ year: date.year, month: date.month, approveType: this.mOpt.approveType, companies: JSON.stringify(comps) }).then(function (data) {
                    _this.updateTitle();
                    if (data[0].length == 0) {
                        $("#approve").css("display", "none");
                    }
                    else {
                        $("#approve").css("display", "");
                    }
                    if (data[1].length == 0) {
                        $("#unapprove").css("display", "none");
                    }
                    else {
                        $("#unapprove").css("display", "");
                    }
                    _this.mCurView.process(data, _this.mDateSelector.getDate(), _this.mCompanySelector.getRawCompanyData());
                });
            }
            else {
                alert("请选择公司");
            }
        };
        View.prototype.unapprove = function () {
            var _this = this;
            var date = this.mCurView.getDate();
            var compIds = this.mCurView.getUnapprovedData();
            if (compIds[0].length > 0) {
                this.mUnapprove.post({
                    approveType: this.mOpt.approveType,
                    year: date.year,
                    month: date.month,
                    data: JSON.stringify(compIds)
                }).then(function (data) {
                    if (data.result) {
                        alert("submit 成功");
                        _this.mDateSelector.select(date);
                        _this.updateUI();
                    }
                    else {
                        alert("submit 失敗");
                    }
                });
            }
            else {
                alert("请选择公司");
            }
        };
        View.prototype.approve = function () {
            var _this = this;
            var date = this.mCurView.getDate();
            var compIds = this.mCurView.getApprovedData();
            if (compIds[0].length > 0) {
                this.mApprove.post({
                    approveType: this.mOpt.approveType,
                    year: date.year,
                    month: date.month,
                    data: JSON.stringify(compIds)
                }).then(function (data) {
                    if (data.result) {
                        alert("submit 成功");
                        _this.mDateSelector.select(date);
                        _this.updateUI();
                    }
                    else {
                        alert("submit 失敗");
                    }
                });
            }
            else {
                alert("请选择公司");
            }
        };
        View.prototype.updateTitle = function () {
            var header = "";
            var date = this.mDateSelector.getDate();
            switch (this.mOpt.approveType) {
                case Util.ZBType.QNJH:
                    header = date.year + "年 计划数据审核";
                    break;
                case Util.ZBType.YDJDMJH:
                    header = date.year + "年" + date.month + "月 季度-月度末计划值审核";
                    break;
                case Util.ZBType.BY20YJ:
                    header = date.year + "年" + date.month + "月 20日预计值审核";
                    break;
                case Util.ZBType.BY28YJ:
                    header = date.year + "年" + date.month + "月 28日预计值审核";
                    break;
                case Util.ZBType.BYSJ:
                    header = date.year + "年" + date.month + "月 实际数据审核";
                    break;
            }
            $('h1').text(header);
            document.title = header;
        };
        View.prototype.createPredict = function (title) {
            var ret = [title[0]];
            var date = this.mDateSelector.getDate();
            var left = date.month % 3;
            if (this.mOpt.approveType == Util.ZBType.YDJDMJH && left == 0) {
                if (12 == date.month) {
                    ret.push((date.year + 1) + "年1月计划");
                    ret.push((date.year + 1) + "年2月计划");
                    ret.push((date.year + 1) + "年3月计划");
                }
                else {
                    ret.push((date.month + 1) + "月计划");
                    ret.push((date.month + 2) + "月计划");
                    ret.push((date.month + 3) + "月计划");
                }
            }
            else if (this.mOpt.approveType == Util.ZBType.BY20YJ || this.mOpt.approveType == Util.ZBType.BY28YJ) {
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
        View.instance = new View();
        return View;
    })();
    approve_template.View = View;
})(approve_template || (approve_template = {}));
