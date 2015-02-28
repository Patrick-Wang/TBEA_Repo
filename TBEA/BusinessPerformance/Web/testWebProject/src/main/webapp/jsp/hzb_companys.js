var hzb_companys;
(function (hzb_companys) {
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName) {
            return new JQTable.JQGridAssistant([
                new JQTable.Node("指标", "zb", true, JQTable.TextAlign.Left),
                new JQTable.Node("全年计划", "qnjh"),
                new JQTable.Node("月度", "yd").append(new JQTable.Node("当月计划", "y1")).append(new JQTable.Node("当月实际", "y2")).append(new JQTable.Node("计划完成率", "y3")).append(new JQTable.Node("去年同期", "y4")).append(new JQTable.Node("同比增幅", "y5")),
                new JQTable.Node("季度", "jd").append(new JQTable.Node("季度计划", "j1")).append(new JQTable.Node("季度累计", "j2")).append(new JQTable.Node("季度计划完成率", "j3")).append(new JQTable.Node("去年同期", "j4")).append(new JQTable.Node("同比增幅", "j5")),
                new JQTable.Node("年度", "nd").append(new JQTable.Node("年度累计", "n1")).append(new JQTable.Node("累计计划完成率", "n2")).append(new JQTable.Node("去年同期", "n3")).append(new JQTable.Node("同比增幅", "n4")),
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
                $('h1').text(date.year + "年" + date.month + "月 指标汇总");
                document.title = date.year + "年" + date.month + "月 指标汇总";
                _this.updateTable();
            });
        };
        View.prototype.updateTable = function () {
            var name = this.mOpt.tableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name);
            var data = [];
            var row = [];
            for (var i = 0; i < this.mData.length; ++i) {
                row = [].concat(this.mData[i]);
                data.push(row);
            }
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: 1200,
                shrinkToFit: true,
                rowNum: 100,
                autoScroll: true
            }));
        };
        return View;
    })();
    hzb_companys.View = View;
})(hzb_companys || (hzb_companys = {}));
