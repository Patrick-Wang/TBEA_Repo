var fx_jkcb_scjb_template;
(function (fx_jkcb_scjb_template) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("材料名称", "clmc", true, JQTable.TextAlign.Left),
                new JQTable.Node("当月完成", "dywc")
                    .append(new JQTable.Node("实际领用量", "dnsjlyl"))
                    .append(new JQTable.Node("废料", "dnfl"))
                    .append(new JQTable.Node("利用率", "dnlyl")),
                new JQTable.Node("去年同期", "qntq")
                    .append(new JQTable.Node("实际领用量", "qnsjlyl"))
                    .append(new JQTable.Node("废料", "qnfl"))
                    .append(new JQTable.Node("利用率", "qnlyl"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    }());
    var View = (function () {
        function View() {
            this.mDataSet = new Util.Ajax("readview.do", false);
        }
        View.getInstance = function () {
            if (View.instance == undefined) {
                View.instance = new View();
            }
            return View.instance;
        };
        View.prototype.initInstance = function (opt) {
            this.mOpt = opt;
            if (opt.comps.length == 0) {
                $('h1').text("没有任何可以查看的公司");
                $('input').css("display", "none");
            }
            else {
                this.mOpt = opt;
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);
                this.mCompanySelector = new Util.CompanySelectorZzy(opt.companyId, opt.comps, opt.isSbdcy, '01');
                this.mBglxSelector = new Util.BglxViewSelector(opt.bglxId, opt.curbglx, opt.isByq, opt.isXl, opt.isSbdcy);
                if (opt.comps.length == 1) {
                    this.mCompanySelector.hide();
                }
                this.updateUI();
            }
        };
        View.prototype.exportExcel = function () {
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            $("#export")[0].action = "export.do?" + Util.Ajax.toUrlParam({ month: date.month, year: date.year, companyId: compType });
            $("#export")[0].submit();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get({ year: date.year, month: date.month, companyId: compType })
                .then(function (dataArray) {
                _this.mTableData = dataArray.values;
                _this.updateTextandTitle(date);
                _this.updateTable();
            });
        };
        View.prototype.updateTextandTitle = function (date) {
            var compName = this.mCompanySelector.getCompanyName();
            $('h1').text(date.year + "年" + date.month + "月" + compName + "生产降本");
            document.title = date.year + "年" + date.month + "月" + compName + "生产降本";
        };
        View.prototype.updateTable = function () {
            var name = this.mOpt.tableId + "_jqgrid_1234";
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            if (this.mTableData.length == 0) {
                $("#tips").css("display", "");
                return;
            }
            $("#tips").css("display", "none");
            this.mTableAssist = JQGridAssistantFactory.createTable(name);
            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 2; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j] && "--" != this.mTableData[i][j]) {
                        if (j == 4 || j == 7) {
                            this.mTableData[i][j] = (parseFloat(this.mTableData[i][j]) * 100).toFixed(2) + "%";
                        }
                        else {
                            this.mTableData[i][j] = (parseFloat(this.mTableData[i][j])).toFixed(2) + "";
                        }
                    }
                    else {
                        this.mTableData[i][j] = "--";
                    }
                }
            }
            $("#" + name).jqGrid(this.mTableAssist.decorate({
                data: this.mTableAssist.getDataWithId(this.mTableData),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: this.mTableData.length > 23 ? 500 : '100%',
                width: this.mTableData[0].length * 100,
                shrinkToFit: true,
                rowNum: 1000,
                autoScroll: true
            }));
        };
        return View;
    }());
    fx_jkcb_scjb_template.View = View;
})(fx_jkcb_scjb_template || (fx_jkcb_scjb_template = {}));
