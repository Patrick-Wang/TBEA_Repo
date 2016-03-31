/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
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
                new JQTable.Node("月度", "yd")
                    .append(new JQTable.Node("当月计划", "y1"))
                    .append(new JQTable.Node("当月实际", "y2"))
                    .append(new JQTable.Node("计划完成率", "y3"))
                    .append(new JQTable.Node("去年同期", "y4"))
                    .append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度", "jd")
                    .append(new JQTable.Node("季度计划", "j1"))
                    .append(new JQTable.Node("季度累计", "j2"))
                    .append(new JQTable.Node("季度计划完成率", "j3"))
                    .append(new JQTable.Node("去年同期", "j4"))
                    .append(new JQTable.Node("同比增幅", "j5")),
                new JQTable.Node("年度", "nd")
                    .append(new JQTable.Node("年度累计", "n1"))
                    .append(new JQTable.Node("累计计划完成率", "n2"))
                    .append(new JQTable.Node("去年同期", "n3"))
                    .append(new JQTable.Node("同比增幅", "n4"))
            ], gridName);
        };
        JQGridAssistantFactory.createHbTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("全年计划", "qnjh"),
                new JQTable.Node("月度", "yd")
                    .append(new JQTable.Node("当月计划", "y1"))
                    .append(new JQTable.Node("当月实际", "y2"))
                    .append(new JQTable.Node("计划完成率", "y3"))
                    .append(new JQTable.Node("上月同期", "y4"))
                    .append(new JQTable.Node("环比增幅", "y4"))
                    .append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度", "jd")
                    .append(new JQTable.Node("季度计划", "j1"))
                    .append(new JQTable.Node("季度累计", "j2"))
                    .append(new JQTable.Node("季度计划完成率", "j3"))
                    .append(new JQTable.Node("去年同期", "j4"))
                    .append(new JQTable.Node("同比增幅", "j5")),
                new JQTable.Node("年度", "nd")
                    .append(new JQTable.Node("年度累计", "n1"))
                    .append(new JQTable.Node("累计计划完成率", "n2"))
                    .append(new JQTable.Node("去年同期", "n3"))
                    .append(new JQTable.Node("同比增幅", "n4"))
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
            if (opt.comps.length == 0) {
                $('h1').text("没有任何可以查看的公司");
                $('input').css("display", "none");
            }
            else {
                var month = this.mOpt.date.month + (2 - (this.mOpt.date.month - 1) % 3);
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, { year: this.mOpt.date.year, month: month }, this.mOpt.dateId);
                this.mDateSelector.select(this.mOpt.date);
                this.mCompanySelector = new Util.CompanySelector(false, opt.companyId, opt.comps);
                this.updateUI();
            }
        };
        View.prototype.exportExcel = function (fName) {
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            $("#export")[0].action = "companys_zbhz_export.do?" + Util.Ajax.toUrlParam({ month: date.month, year: date.year, companyId: compType });
            $("#export")[0].submit();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get({ year: date.year, month: date.month, companyId: compType })
                .then(function (dataArray) {
                _this.mData = dataArray;
                _this.updateTextandTitle(date);
                _this.updateTable();
            });
        };
        View.prototype.updateTextandTitle = function (date) {
            $('h1').text(date.year + "年" + date.month + "月经营单位与项目公司指标完成情况");
            document.title = date.year + "年" + date.month + "月经营单位与项目公司指标完成情况";
        };
        View.prototype.updateTable = function () {
            var name = this.mOpt.tableId + "_jqgrid_1234";
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            if (this.mData.length == 0) {
                $("#tips").css("display", "");
                return;
            }
            $("#tips").css("display", "none");
            var tableAssist = null;
            tableAssist = JQGridAssistantFactory.createTable(name);
            var outputData = [];
            Util.formatData(outputData, this.mData, this.initPercentList(), [AllZb.dysj,
                AllZb.dyqntq,
                AllZb.jdlj,
                AllZb.jdqntq,
                AllZb.ndlj,
                AllZb.ndqntq,
            ]);
            $("#" + name).jqGrid(tableAssist.decorate({
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(outputData),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                //                    cellsubmit: 'clientArray',
                //                    cellEdit: true,
                height: outputData.length > 23 ? 500 : '100%',
                width: 1300,
                shrinkToFit: true,
                rowNum: 1000,
                autoScroll: true
            }));
        };
        View.prototype.initPercentList = function () {
            var precentList = new std.vector();
            precentList.push(AllZb.dyjhwcl);
            precentList.push(AllZb.dytbzf);
            precentList.push(AllZb.jdjhwcl);
            precentList.push(AllZb.jdtbzf);
            precentList.push(AllZb.ndljjhwcl);
            precentList.push(AllZb.ndtbzf);
            return precentList;
        };
        return View;
    })();
    hzb_companys.View = View;
})(hzb_companys || (hzb_companys = {}));
