/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../companySelector.ts" />
/// <reference path="bglx_selector.ts" />
var fx_jkcb_scjb_template;
(function (fx_jkcb_scjb_template) {
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
                    nodes.push(new JQTable.Node(title[i], "_" + i, true));
                }
            }
            return new JQTable.JQGridAssistant(nodes, gridName);
        };
        return JQGridAssistantFactory;
    })();
    var View = (function () {
        function View() {
            this.mDataSet = new Util.Ajax("zb_update.do", false);
            this.mCondition = new Util.Ajax("zb_entry.do");
        }
        View.getInstance = function () {
            return View.instance;
        };
        View.prototype.initInstance = function (opt) {
            $("#date").html("");
            $("#company").html("");
            this.mOpt = opt;
            this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);
            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.companyId, opt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.updateTitle();
            //this.updateUI();
        };
        View.prototype.initBglxViewSelect = function (opt) {
            this.mBglxViewSelector = new Util.BglxViewSelector(opt.bglxId, opt.curbglx);
        };
        View.prototype.updateUI = function () {
            var _this = this;
            $("#nodatatips").css("display", "none");
            $("#entryarea").css("display", "");
            var date = this.mDateSelector.getDate();
            this.mDataSet.get({ year: date.year, month: date.month, entryType: $("#bglx").find("option:selected").val(), companyId: this.mCompanySelector.getCompany() })
                .then(function (data) {
                _this.mStatusList = data.status;
                _this.mTableData = data.values;
                _this.updateTitle();
                _this.updateTable(_this.mOpt.tableId);
                $('#export').css("display", "block");
            });
        };
        View.prototype.updateEntry = function () {
            var _this = this;
            $("#nodatatips").css("display", "block");
            $("#entryarea").css("display", "none");
            $('#export').css("display", "none");
            this.mCondition.get({ entryType: "20008" })
                .then(function (data) {
                _this.initInstance({
                    tableId: "table",
                    dateId: "date",
                    companyId: "company",
                    comps: data.comps,
                    date: {
                        month: data.month == null ? undefined : data.month,
                        year: data.year
                    }
                });
            });
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
        View.prototype.exportExcel = function (fName) {
            //var date : Util.Date = this.mDateSelector.getDate();
            var compType = this.mCompanySelector.getCompany();
            $("#export")[0].action = "fx_jkcb_scjb_export.do?" + Util.Ajax.toUrlParam({
                month: this.mDateSelector.getDate().month,
                year: this.mDateSelector.getDate().year,
                companyId: this.mCompanySelector.getCompany(),
                entryType: $("#bglx").find("option:selected").val()
            });
            $("#export")[0].submit();
        };
        View.prototype.updateTitle = function () {
            var header = "";
            var date = this.mDateSelector.getDate();
            var compName = this.mCompanySelector.getCompanyName();
            header = date.year + "年 " + compName + " " + $("#bglx").find("option:selected").text() + "查看";
            $('h1').text(header);
            document.title = header;
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
            var titles = ["", "", "", "", "", "", ""];
            this.mTableAssist = new JQTable.JQGridAssistant([
                new JQTable.Node("材料名称", "clmc", true, JQTable.TextAlign.Left),
                new JQTable.Node("当月完成", "dywc")
                    .append(new JQTable.Node("实际领用量", "dnsjlyl"))
                    .append(new JQTable.Node("废料", "dnfl"))
                    .append(new JQTable.Node("利用率", "dnlyl")),
                new JQTable.Node("去年同期", "qntq")
                    .append(new JQTable.Node("实际领用量", "qnsjlyl"))
                    .append(new JQTable.Node("废料", "qnfl"))
                    .append(new JQTable.Node("利用率", "qnlyl"))
            ], name);
            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 2; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j] && "--" != this.mTableData[i][j]) {
                        if (j == 4 || j == 7) {
                            this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) * 100 + "%";
                        }
                        else {
                            this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) + "";
                        }
                    }
                    else {
                        this.mTableData[i][j] = "--";
                    }
                }
            }
            var data = this.mTableData;
            $("#" + name).jqGrid(this.mTableAssist.decorate({
                data: this.mTableAssist.getDataWithId(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: data.length > 25 ? 550 : '100%',
                width: titles.length * 100,
                shrinkToFit: true,
                autoScroll: true,
                rowNum: 150
            }));
        };
        View.instance = new View();
        return View;
    })();
    fx_jkcb_scjb_template.View = View;
})(fx_jkcb_scjb_template || (fx_jkcb_scjb_template = {}));
