var fx_jkcb_ztnhqk_byq_template;
(function (fx_jkcb_ztnhqk_byq_template) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("", "empty", true, JQTable.TextAlign.Left),
                new JQTable.Node("水（吨）", "sd")
                    .append(new JQTable.Node("用量", "syl"))
                    .append(new JQTable.Node("金额（元）", "sje")),
                new JQTable.Node("电（度）", "dd")
                    .append(new JQTable.Node("用量", "dyl"))
                    .append(new JQTable.Node("金额（元）", "dje")),
                new JQTable.Node("蒸汽（立方米）", "zqlfm")
                    .append(new JQTable.Node("用量", "zqyl"))
                    .append(new JQTable.Node("金额（元）", "zqje")),
                new JQTable.Node("燃气（线缆）（立方米）", "rqxllfm")
                    .append(new JQTable.Node("用量", "rqxlyl"))
                    .append(new JQTable.Node("金额（元）", "rqxlje")),
                new JQTable.Node("产值", "cz"),
                new JQTable.Node("产量", "cl")
            ], name);
        };
        return JQGridAssistantFactory;
    }());
    var View = (function () {
        function View() {
            this.mDataSet = new Util.Ajax("readviewbyq.do", false);
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
                this.mCompanySelector = new Util.CompanySelectorZzy(opt.companyId, opt.comps, opt.isSbdcy, '02');
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
            $("#export")[0].action = "exportbyq.do?" + Util.Ajax.toUrlParam({ year: date.year, month: date.month, companyId: compType });
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
            $('h1').text(date.year + "年" + date.month + "月" + compName + "整体能耗情况");
            document.title = date.year + "年" + date.month + "月" + compName + "整体能耗情况";
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
                        this.mTableData[i][j] = (parseFloat(this.mTableData[i][j])).toFixed(2) + "";
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
    fx_jkcb_ztnhqk_byq_template.View = View;
})(fx_jkcb_ztnhqk_byq_template || (fx_jkcb_ztnhqk_byq_template = {}));
