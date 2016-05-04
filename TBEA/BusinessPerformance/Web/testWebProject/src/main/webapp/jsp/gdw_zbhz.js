var gdw_zbhz;
(function (gdw_zbhz) {
    var DwZb;
    (function (DwZb) {
        DwZb[DwZb["qnjh"] = 0] = "qnjh";
        DwZb[DwZb["dyjh"] = 1] = "dyjh";
        DwZb[DwZb["dysj"] = 2] = "dysj";
        DwZb[DwZb["dyjhwcl"] = 3] = "dyjhwcl";
        DwZb[DwZb["dyqntq"] = 4] = "dyqntq";
        DwZb[DwZb["dytbzf"] = 5] = "dytbzf";
        DwZb[DwZb["jdjh"] = 6] = "jdjh";
        DwZb[DwZb["jdlj"] = 7] = "jdlj";
        DwZb[DwZb["jdjhwcl"] = 8] = "jdjhwcl";
        DwZb[DwZb["jdqntq"] = 9] = "jdqntq";
        DwZb[DwZb["jdtbzf"] = 10] = "jdtbzf";
        DwZb[DwZb["ndlj"] = 11] = "ndlj";
        DwZb[DwZb["ndljjhwcl"] = 12] = "ndljjhwcl";
        DwZb[DwZb["ndqntq"] = 13] = "ndqntq";
        DwZb[DwZb["ndtbzf"] = 14] = "ndtbzf";
    })(DwZb || (DwZb = {}));
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
        return JQGridAssistantFactory;
    }());
    var View = (function () {
        function View() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("gdw_zbhz_update.do");
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };
        View.prototype.init = function (tableId, dateId, month, year) {
            this.mTableId = tableId;
            this.mDs = new Util.DateSelector({ year: year - 3, month: 1 }, { year: year, month: month }, dateId);
        };
        View.prototype.onIndexSelected = function () {
            this.mZBId = $("#indextype").val();
            this.mZBName = $("#indextype  option:selected").text();
        };
        View.prototype.exportExcel = function (fName) {
            var date = this.mDs.getDate();
            $("#export")[0].action = "gdw_zbhz_export.do?" + Util.Ajax.toUrlParam({ month: date.month, year: date.year, top5index: this.mZBId, zbName: this.mZBName });
            $("#export")[0].submit();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDs.getDate();
            this.onIndexSelected();
            this.mDataSet.get({ month: date.month, year: date.year, zbId: this.mZBId })
                .then(function (dataArray) {
                _this.mData = dataArray;
                $('h1').text(date.year + "年" + date.month + "月各单位" + _this.mZBName + "完成情况");
                document.title = date.year + "年" + date.month + "月各单位" + _this.mZBName + "完成情况";
                _this.updateTable();
            });
        };
        View.prototype.updateTable = function () {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = JQGridAssistantFactory.createTable(name);
            tableAssist.mergeRow(0);
            var data = [
                ["沈变公司"],
                ["衡变公司"],
                ["新变厂"],
                ["鲁缆公司"],
                ["新缆厂"],
                ["德缆公司"],
                ["输变电小计"],
                ["新特能源公司"],
                ["新能源"],
                ["新能源小计"],
                ["天池能源"],
                ["能动公司"],
                ["能源小计"],
                ["进出口公司"],
                ["国际工程公司"],
                ["工程小计"],
                ["股份公司小计"],
                ["众和公司"],
                ["集团合计"]
            ];
            var row = [];
            for (var i = 0; i < data.length; ++i) {
                if (data[i][0].lastIndexOf("计") >= 0) {
                    tableAssist.setRowBgColor(i, 183, 222, 232);
                }
                if (this.mData[i] instanceof Array) {
                    row = [].concat(this.mData[i]);
                    for (var j = 0; j < row.length; ++j) {
                        if (j == DwZb.dyjhwcl || j == DwZb.dytbzf ||
                            j == DwZb.jdjhwcl || j == DwZb.jdtbzf ||
                            j == DwZb.ndljjhwcl || j == DwZb.ndtbzf) {
                            row[j] = Util.formatPercent(row[j]);
                        }
                        else {
                            row[j] = Util.formatCurrency(row[j]);
                        }
                    }
                    data[i] = data[i].concat(row);
                }
            }
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            $("#" + name).jqGrid(tableAssist.decorate({
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: "100%",
                width: 1330,
                shrinkToFit: true,
                rowNum: 200,
                autoScroll: true
            }));
            $("#export").css('display', 'block');
        };
        return View;
    }());
    gdw_zbhz.View = View;
})(gdw_zbhz || (gdw_zbhz = {}));
