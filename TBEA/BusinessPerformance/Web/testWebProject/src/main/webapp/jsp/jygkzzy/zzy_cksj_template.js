/// <reference path="../jqgrid/jqassist.ts" />
/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../companySelector.ts" />
/// <reference path="bglx_selector.ts" />
var zzy_cksj_template;
(function (zzy_cksj_template) {
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
            if ($("#bglx").find("option:selected").val() == "20002" || $("#bglx").find("option:selected").val() == "20003") {
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId, true);
            }
            else {
                this.mDateSelector = new Util.DateSelector({ year: this.mOpt.date.year - 3 }, this.mOpt.date, this.mOpt.dateId);
            }
            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.companyId, opt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }
            this.updateTitle();
            //this.updateUI();
        };
        View.prototype.initBglxSelect = function (opt) {
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
            this.mCondition.get({ entryType: $("#bglx").find("option:selected").val() })
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
            $("#export")[0].action = "zzy_cksj_export.do?" + Util.Ajax.toUrlParam({
                month: this.mDateSelector.getDate().month,
                year: this.mDateSelector.getDate().year,
                companyId: this.mCompanySelector.getCompany(),
                entryType: $("#bglx").find("option:selected").val()
            });
            $("#export")[0].submit();
        };
        View.prototype.save = function () {
            var date = this.mDateSelector.getDate();
            var allData = this.mTableAssist.getAllData();
            var submitData = [];
            var colNames = this.mTableAssist.getColNames();
            for (var i = 0; i < allData.length; ++i) {
                submitData.push([]);
                for (var j = 0; j < allData[i].length; ++j) {
                    if (j != 1) {
                        submitData[i].push(allData[i][j]);
                        allData[i][j] = allData[i][j].replace(new RegExp(' ', 'g'), '');
                    }
                }
            }
            this.mSave.post({
                year: date.year,
                month: date.month,
                entryType: $("#bglx").find("option:selected").val(),
                companyId: this.mCompanySelector.getCompany(),
                data: JSON.stringify(submitData)
            }).then(function (data) {
                if ("true" == data.result) {
                    Util.MessageBox.tip("保存 成功");
                }
                else if ("false" == data.result) {
                    Util.MessageBox.tip("保存 失败");
                }
                else {
                    Util.MessageBox.tip(data.result);
                }
            });
        };
        View.prototype.updateTitle = function () {
            var header = "";
            var date = this.mDateSelector.getDate();
            var compName = this.mCompanySelector.getCompanyName();
            header = date.year + "年" + date.month + "月 " + compName + " " + $("#bglx").find("option:selected").text() + "查看";
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
            var titles = null;
            switch ($("#bglx").find("option:selected").val()) {
                case "20001":
                    titles = ["指标名称", "收入", "毛利额"];
                    this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);
                    break;
                case "20002":
                case "20003":
                    titles = ["指标名称", "产值", "产量", "中标毛利率", "预计优化后毛利额", "预计优化后毛利率"];
                    this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);
                    break;
                case "20004":
                    titles = ["指标名称", "指标值"];
                    this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);
                    break;
                case "20005":
                case "20006":
                    titles = ["指标名称", "生产台数", "优化台数", "结构参数优化降本", "材料替代降本", "其他降本"];
                    this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);
                    break;
                case "20007":
                    titles = ["指标名称", "年度计划", "月度计划", "月度完成"];
                    this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);
                    break;
                case "20008":
                    titles = ["", "", "", "", "", "", ""];
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
                    break;
                case "20009":
                case "20010":
                    titles = ["", "", "", "", ""];
                    this.mTableAssist = new JQTable.JQGridAssistant([
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
                    break;
                case "20011":
                    titles = ["年度", "调差", "调配置/标准", "优化付款方式", "取消客户指定", "其他", "合计"];
                    this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);
                    break;
                case "20012":
                case "20013":
                    titles = ["", "", "", "", "", "", ""];
                    this.mTableAssist = new JQTable.JQGridAssistant([
                        new JQTable.Node("产品类别", "cplb", true, JQTable.TextAlign.Left),
                        new JQTable.Node("产量/铜铝当量（吨）", "cl")
                            .append(new JQTable.Node("当月产量", "dycl"))
                            .append(new JQTable.Node("去年同期", "clqn"))
                            .append(new JQTable.Node("增长比", "zzqn")),
                        new JQTable.Node("产值(不含税)", "cl")
                            .append(new JQTable.Node("当月产值", "dycz"))
                            .append(new JQTable.Node("去年同期", "czqn"))
                            .append(new JQTable.Node("增长比", "czqn"))
                    ], name);
                    break;
                case "20014":
                    titles = ["", "", "", "", ""];
                    this.mTableAssist = new JQTable.JQGridAssistant([
                        new JQTable.Node("产品类别", "cplb", true, JQTable.TextAlign.Left),
                        new JQTable.Node("总工时", "zgs")
                            .append(new JQTable.Node("当月", "dy"))
                            .append(new JQTable.Node("去年同期", "clqn"))
                            .append(new JQTable.Node("增长比", "zzb"))
                    ], name);
                    break;
                case "20015":
                case "20016":
                    titles = ["单位", "月产出能力产值", "月产出能力产量", "可供履约订单总产值", "可供履约订单总产量", "年度可供履约订单总产值", "年度可供履约订单总产量", "n+1月产值(已排产)", "n+1月产值(未排产)", "n+1月产量", "n+2月产值(已排产)", "n+2月产值(未排产)",
                        "n+2月产量", "n+3月产值(已排产)", "n+3月产值(未排产)", "n+3月产量", "n+4月产值", "n+4月产量", "n+5月产值", "n+5月产量", "n+6月产值", "n+6月产量", "n+6月后可供履约订单产值", "n+6月后可供履约订单产量", "n+3月后可供履约订单产值", "待定产值",
                        "待定产量", "外协产值", "外协产量"];
                    this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);
                    break;
                case "20017":
                    titles = ["时点", "原材料", "半成品", "实际库存商品", "已发货未开票", "期货浮动盈亏(盈+，亏-)", "期货平仓盈亏(盈-，亏+)", "未发货已开票", "其他", "合计"];
                    this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);
                    break;
                case "20018":
                    titles = ["原材料", "5年以上原材料", "5年以上半成品", "5年以上产成品", "5年以上其他", "4-5年原材料", "4-5年半成品", "4-5年产成品", "4-5年其他", "3-4年原材料", "3-4年半成品", "3-4年产成品", "3-4年其他", "2-3年原材料", "2-3年半成品",
                        "2-3年产成品", "2-3年其他", "1-2年原材料", "1-2年半成品", "1-2年产成品", "1-2年其他", "1年原材料", "1年半成品", "1年产成品", "1年其他", "合计"];
                    this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);
                    break;
                case "20019":
                    titles = ["指标名称", "截止月底库存金额", "年初库存金额"];
                    this.mTableAssist = JQGridAssistantFactory.createFlatTable(name, titles, this.mStatusList);
                    break;
            }
            for (var i = 0; i < this.mTableData.length; ++i) {
                for (var j = 2; j < this.mTableData[i].length; ++j) {
                    if ("" != this.mTableData[i][j]) {
                        this.mTableData[i][j] = parseFloat(this.mTableData[i][j]) + "";
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
                width: titles.length * 200,
                shrinkToFit: true,
                autoScroll: true,
                rowNum: 150
            }));
        };
        View.instance = new View();
        return View;
    })();
    zzy_cksj_template.View = View;
})(zzy_cksj_template || (zzy_cksj_template = {}));
