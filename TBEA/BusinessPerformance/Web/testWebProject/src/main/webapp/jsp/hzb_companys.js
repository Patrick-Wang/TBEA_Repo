var hzb_companys;
(function (hzb_companys) {
    var AllZb;
    (function (AllZb) {
        AllZb[AllZb["zb"] = 0] = "zb";
        AllZb[AllZb["qnjh"] = 1] = "qnjh";
        AllZb[AllZb["dyjh"] = 2] = "dyjh";
        AllZb[AllZb["dysj"] = 3] = "dysj";
        AllZb[AllZb["dyjhwcl"] = 4] = "dyjhwcl";
        AllZb[AllZb["dyqntq"] = 5] = "dyqntq";
        AllZb[AllZb["dytbzf"] = 6] = "dytbzf";
        AllZb[AllZb["jdjh"] = 7] = "jdjh";
        AllZb[AllZb["jdlj"] = 8] = "jdlj";
        AllZb[AllZb["jdjhwcl"] = 9] = "jdjhwcl";
        AllZb[AllZb["jdqntq"] = 10] = "jdqntq";
        AllZb[AllZb["jdtbzf"] = 11] = "jdtbzf";
        AllZb[AllZb["ndlj"] = 12] = "ndlj";
        AllZb[AllZb["ndljjhwcl"] = 13] = "ndljjhwcl";
        AllZb[AllZb["ndqntq"] = 14] = "ndqntq";
        AllZb[AllZb["ndtbzf"] = 15] = "ndtbzf";
    })(AllZb || (AllZb = {}));
    ;
    var HbZb;
    (function (HbZb) {
        HbZb[HbZb["zb"] = 0] = "zb";
        HbZb[HbZb["qnjh"] = 1] = "qnjh";
        HbZb[HbZb["dyjh"] = 2] = "dyjh";
        HbZb[HbZb["dysj"] = 3] = "dysj";
        HbZb[HbZb["dyjhwcl"] = 4] = "dyjhwcl";
        HbZb[HbZb["dysytq"] = 5] = "dysytq";
        HbZb[HbZb["dyhbzf"] = 6] = "dyhbzf";
        HbZb[HbZb["dytbzf"] = 7] = "dytbzf";
        HbZb[HbZb["jdjh"] = 8] = "jdjh";
        HbZb[HbZb["jdlj"] = 9] = "jdlj";
        HbZb[HbZb["jdjhwcl"] = 10] = "jdjhwcl";
        HbZb[HbZb["jdqntq"] = 11] = "jdqntq";
        HbZb[HbZb["jdtbzf"] = 12] = "jdtbzf";
        HbZb[HbZb["ndlj"] = 13] = "ndlj";
        HbZb[HbZb["ndljjhwcl"] = 14] = "ndljjhwcl";
        HbZb[HbZb["ndqntq"] = 15] = "ndqntq";
        HbZb[HbZb["ndtbzf"] = 16] = "ndtbzf";
    })(HbZb || (HbZb = {}));
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("全年计划", "qnjh"),
                new JQTable.Node("月度", "yd").append(new JQTable.Node("当月计划", "y1")).append(new JQTable.Node("当月实际", "y2")).append(new JQTable.Node("计划完成率", "y3")).append(new JQTable.Node("去年同期", "y4")).append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度", "jd").append(new JQTable.Node("季度计划", "j1")).append(new JQTable.Node("季度累计", "j2")).append(new JQTable.Node("季度计划完成率", "j3")).append(new JQTable.Node("去年同期", "j4")).append(new JQTable.Node("同比增幅", "j5")),
                new JQTable.Node("年度", "nd").append(new JQTable.Node("年度累计", "n1")).append(new JQTable.Node("累计计划完成率", "n2")).append(new JQTable.Node("去年同期", "n3")).append(new JQTable.Node("同比增幅", "n4"))
            ], gridName);
        };
        JQGridAssistantFactory.createHbTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("全年计划", "qnjh"),
                new JQTable.Node("月度", "yd").append(new JQTable.Node("当月计划", "y1")).append(new JQTable.Node("当月实际", "y2")).append(new JQTable.Node("计划完成率", "y3")).append(new JQTable.Node("上月同期", "y4")).append(new JQTable.Node("环比增幅", "y4")).append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度", "jd").append(new JQTable.Node("季度计划", "j1")).append(new JQTable.Node("季度累计", "j2")).append(new JQTable.Node("季度计划完成率", "j3")).append(new JQTable.Node("去年同期", "j4")).append(new JQTable.Node("同比增幅", "j5")),
                new JQTable.Node("年度", "nd").append(new JQTable.Node("年度累计", "n1")).append(new JQTable.Node("累计计划完成率", "n2")).append(new JQTable.Node("去年同期", "n3")).append(new JQTable.Node("同比增幅", "n4"))
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("hzb_companys_update.do");
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };
        View.prototype.init = function (opt) {
            this.mOpt = opt;
            this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 1 }, this.mOpt.date, this.mOpt.dateId);
            this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
            this.updateTable();
            this.updateUI();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get({ year: date.year, month: date.month, companyId: compType }).then(function (dataArray) {
                _this.mData = dataArray;
                $('h1').text(date.year + "年" + date.month + "月经营单位与项目公司指标汇总");
                document.title = date.year + "年" + date.month + "月经营单位与项目公司指标汇总";
                _this.updateTable();
            });
        };
        View.prototype.formatAllData = function () {
            var data = [];
            var row = [];
            var isRs = false;
            for (var j = 0; j < this.mData.length; ++j) {
                row = [].concat(this.mData[j]);
                isRs = row[0 /* zb */] == '人数';
                for (var i = 0; i < row.length; ++i) {
                    if (i == 4 /* dyjhwcl */ || i == 6 /* dytbzf */ || i == 9 /* jdjhwcl */ || i == 11 /* jdtbzf */ || i == 13 /* ndljjhwcl */ || i == 15 /* ndtbzf */) {
                        row[i] = Util.formatPercent(row[i]);
                    }
                    else if (i != 0 /* zb */) {
                        if (isRs) {
                            row[i] = Util.formatInt(row[i]);
                        }
                        else {
                            row[i] = Util.formatCurrency(row[i]);
                        }
                    }
                }
                data.push(row);
            }
            return data;
        };
        View.prototype.formatHbData = function () {
            var data = [];
            var row = [];
            var isRs = false;
            for (var j = 0; j < this.mData.length; ++j) {
                row = [].concat(this.mData[j]);
                isRs = row[0 /* zb */] == '人数';
                for (var i = 0; i < row.length; ++i) {
                    if (i == 4 /* dyjhwcl */ || i == 6 /* dyhbzf */ || i == 7 /* dytbzf */ || i == 10 /* jdjhwcl */ || i == 12 /* jdtbzf */ || i == 14 /* ndljjhwcl */ || i == 16 /* ndtbzf */) {
                        row[i] = Util.formatPercent(row[i]);
                    }
                    else if (i != 0 /* zb */) {
                        if (isRs) {
                            row[i] = Util.formatInt(row[i]);
                        }
                        else {
                            row[i] = Util.formatCurrency(row[i]);
                        }
                    }
                }
                data.push(row);
            }
            return data;
        };
        View.prototype.updateTable = function () {
            var name = this.mOpt.tableId + "_jqgrid_1234";
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            if (this.mData.length == 0) {
                return;
            }
            var data = [];
            var tableAssist = null;
            if (this.mData[0].length > 16) {
                tableAssist = JQGridAssistantFactory.createHbTable(name);
                data = this.formatHbData();
            }
            else {
                tableAssist = JQGridAssistantFactory.createTable(name);
                data = this.formatAllData();
            }
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: data.length > 23 ? 500 : '100%',
                width: 1300,
                shrinkToFit: true,
                rowNum: 1000,
                autoScroll: true
            }));
        };
        return View;
    })();
    hzb_companys.View = View;
})(hzb_companys || (hzb_companys = {}));
