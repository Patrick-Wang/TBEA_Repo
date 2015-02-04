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
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mApprove = new Util.Ajax("zb_approve.do");
            this.mUnapprove = new Util.Ajax("zb_unapprove.do");
            this.mDataSet = new Util.Ajax("zb_update.do");
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
            this.mCompanySelector = new Util.CompanySelector(true, opt.companyId, opt.topComps, opt.firstCompany, opt.subComps);
            this.updateTitle();
            this.updateUI();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDateSelector.getDate();
            this.mDataSet.get({ year: date.year, month: date.month, approveType: this.mOpt.approveType }).then(function (data) {
                _this.mTableApproveData = _this.transposition(data[0]);
                _this.mTableUnapproveData = _this.transposition(data[1]);
                _this.updateTitle();
                _this.mTableApproveAssist = _this.updateTable(_this.mOpt.tableApproveId, _this.mTableApproveData);
                _this.mTableUnapproveAssist = _this.updateTable(_this.mOpt.tableUnapproveId, _this.mTableUnapproveData);
            });
        };
        View.prototype.transposition = function (data) {
            var dataRet = [];
            for (var i = 0; i < data[0].length; ++i) {
                dataRet.push([]);
                for (var j = 0; j < data.length; ++j) {
                    dataRet[i].push(data[j][i]);
                }
                dataRet[i] = dataRet[i].reverse();
            }
            return dataRet;
        };
        View.prototype.getTitles = function () {
            var titles = null;
            switch (this.mOpt.approveType) {
                case Util.ZBType.QNJH:
                    titles = this.transposition([["全年计划"]]);
                    break;
                case Util.ZBType.YDJDMJH:
                    titles = this.transposition([this.createPredict(["月度-季度末计划值"])]);
                    break;
                case Util.ZBType.BY20YJ:
                    titles = this.transposition([this.createPredict(["本月20日预计值"])]);
                    break;
                case Util.ZBType.BY28YJ:
                    titles = this.transposition([this.createPredict(["本月28日预计值"])]);
                    break;
                case Util.ZBType.BYSJ:
                    titles = this.transposition([["本月实际"]]);
                    break;
            }
            return titles;
        };
        View.prototype.updateTable = function (tableId, tableData) {
            var name = tableId + "_jqgrid";
            var titles = this.getTitles();
            var tableAssist = JQGridAssistantFactory.createFlatTable(name, ["审核类型"].concat(tableData[0]));
            var data = titles;
            for (var i = 0; i < data.length; ++i) {
                data[i] = data[i].concat(tableData[i + 1]);
            }
            var parent = $("#" + tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            var width = (tableData[0].length + 1) * 125;
            if (width > 1000) {
                width = 1000;
            }
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: true,
                drag: false,
                resize: false,
                height: '100%',
                width: width,
                shrinkToFit: width == 1000 ? false : true,
                autoScroll: true,
            }));
            return tableAssist;
        };
        View.prototype.unapprove = function () {
            var date = this.mDateSelector.getDate();
            this.mUnapprove.post({
                year: date.year,
                month: date.month,
                approveType: this.mOpt.approveType,
                data: JSON.stringify(this.mTableUnapproveAssist.getAllData())
            }).then(function (data) {
                if (data.result) {
                    alert("submit 成功");
                }
                else {
                    alert("submit 失敗");
                }
            });
        };
        View.prototype.approve = function () {
            var date = this.mDateSelector.getDate();
            this.mApprove.post({
                year: date.year,
                month: date.month,
                approveType: this.mOpt.approveType,
                data: JSON.stringify(this.mTableApproveAssist.getAllData())
            }).then(function (data) {
                if (data.result) {
                    alert("submit 成功");
                }
                else {
                    alert("submit 失敗");
                }
            });
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
