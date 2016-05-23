var hzb_companysNC;
(function (hzb_companysNC) {
    var ZtId;
    (function (ZtId) {
        ZtId[ZtId["zb"] = 0] = "zb";
        ZtId[ZtId["dysj"] = 1] = "dysj";
        ZtId[ZtId["dyqntq"] = 2] = "dyqntq";
        ZtId[ZtId["dytbzf"] = 3] = "dytbzf";
        ZtId[ZtId["ndlj"] = 4] = "ndlj";
        ZtId[ZtId["ndqntq"] = 5] = "ndqntq";
        ZtId[ZtId["ndtbzf"] = 6] = "ndtbzf";
    })(ZtId || (ZtId = {}));
    ;
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, 0 /* Left */),
                new JQTable.Node("当月实际", "dysj", true, 0 /* Left */),
                new JQTable.Node("去年同期", "qntq", true, 0 /* Left */),
                new JQTable.Node("同比增幅", "tbzf", true, 0 /* Left */),
                new JQTable.Node("年度累计", "ndlj", true, 0 /* Left */),
                new JQTable.Node("去年同期累计", "qnndlj", true, 0 /* Left */),
                new JQTable.Node("同比增幅", "ndtbzf", true, 0 /* Left */)
            ], gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("CompanysNC_update.do");
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
            }
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            this.mDataSet.get({ year: date.year, month: date.month, companyId: compType }).then(function (dataArray) {
                _this.mData = dataArray;
                _this.updateTextandTitle(date);
                _this.updateTable();
            });
        };
        View.prototype.updateTextandTitle = function (date) {
            $('h1').text(date.year + "年" + date.month + "月经营单位财务指标完成情况(万元)");
            document.title = date.year + "年" + date.month + "月经营单位财务指标完成情况(万元)";
        };
        View.prototype.initPercentList = function () {
            var precentList = new std.vector();
            precentList.push(3 /* dytbzf */);
            precentList.push(6 /* ndtbzf */);
            return precentList;
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
            Util.formatData(outputData, this.mData, this.initPercentList(), []);
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(outputData),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: outputData.length > 23 ? 500 : '100%',
                width: 1300,
                shrinkToFit: true,
                rowNum: 1000,
                autoScroll: true
            }));
        };
        return View;
    })();
    hzb_companysNC.View = View;
})(hzb_companysNC || (hzb_companysNC = {}));
