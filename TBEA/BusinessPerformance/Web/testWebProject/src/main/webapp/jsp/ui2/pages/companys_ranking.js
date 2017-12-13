/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
/// <reference path="messageBox.ts" />
///<reference path="dateSelector.ts"/>
//利润计划完成率排名,经营性净现金流实际完成排名
var RANKINGTYPE1;
(function (RANKINGTYPE1) {
    RANKINGTYPE1[RANKINGTYPE1["GSMC"] = 0] = "GSMC";
    RANKINGTYPE1[RANKINGTYPE1["NDJH"] = 1] = "NDJH";
    RANKINGTYPE1[RANKINGTYPE1["NDLJWC"] = 2] = "NDLJWC";
    RANKINGTYPE1[RANKINGTYPE1["JHWCL"] = 3] = "JHWCL";
    RANKINGTYPE1[RANKINGTYPE1["YEARRANKING"] = 4] = "YEARRANKING";
    RANKINGTYPE1[RANKINGTYPE1["YDJH"] = 5] = "YDJH";
    RANKINGTYPE1[RANKINGTYPE1["YDWC"] = 6] = "YDWC";
    RANKINGTYPE1[RANKINGTYPE1["YDWCL"] = 7] = "YDWCL";
    RANKINGTYPE1[RANKINGTYPE1["MONTHRANKING"] = 8] = "MONTHRANKING";
})(RANKINGTYPE1 || (RANKINGTYPE1 = {}));
;
//利润指标年度累计完成同比增长情况排名
var RANKINGTYPE2;
(function (RANKINGTYPE2) {
    RANKINGTYPE2[RANKINGTYPE2["GSMC"] = 0] = "GSMC";
    RANKINGTYPE2[RANKINGTYPE2["NDLJ"] = 1] = "NDLJ";
    RANKINGTYPE2[RANKINGTYPE2["QNTQLJ"] = 2] = "QNTQLJ";
    RANKINGTYPE2[RANKINGTYPE2["NDTBZZ"] = 3] = "NDTBZZ";
    RANKINGTYPE2[RANKINGTYPE2["YEARRANKING"] = 4] = "YEARRANKING";
    RANKINGTYPE2[RANKINGTYPE2["DYWC"] = 5] = "DYWC";
    RANKINGTYPE2[RANKINGTYPE2["QNTQ"] = 6] = "QNTQ";
    RANKINGTYPE2[RANKINGTYPE2["YDTBZZ"] = 7] = "YDTBZZ";
    RANKINGTYPE2[RANKINGTYPE2["MONTHRANKING"] = 8] = "MONTHRANKING";
})(RANKINGTYPE2 || (RANKINGTYPE2 = {}));
;
//人均利润，人均收入
var RANKINGTYPE3;
(function (RANKINGTYPE3) {
    RANKINGTYPE3[RANKINGTYPE3["GSMC"] = 0] = "GSMC";
    RANKINGTYPE3[RANKINGTYPE3["NDLJ"] = 1] = "NDLJ";
    RANKINGTYPE3[RANKINGTYPE3["YEARRANKING"] = 2] = "YEARRANKING";
    RANKINGTYPE3[RANKINGTYPE3["DYWC"] = 3] = "DYWC";
    RANKINGTYPE3[RANKINGTYPE3["MONTHRANKING"] = 4] = "MONTHRANKING";
})(RANKINGTYPE3 || (RANKINGTYPE3 = {}));
;
//应收账款占收入排名
var RANKINGTYPE4;
(function (RANKINGTYPE4) {
    RANKINGTYPE4[RANKINGTYPE4["GSMC"] = 0] = "GSMC";
    RANKINGTYPE4[RANKINGTYPE4["BYSR"] = 1] = "BYSR";
    RANKINGTYPE4[RANKINGTYPE4["BYYSZK"] = 2] = "BYYSZK";
    RANKINGTYPE4[RANKINGTYPE4["YSZKZSRBZ"] = 3] = "YSZKZSRBZ";
    RANKINGTYPE4[RANKINGTYPE4["MONTHRANKING"] = 4] = "MONTHRANKING";
})(RANKINGTYPE4 || (RANKINGTYPE4 = {}));
;
//应收账款加保理
var RANKINGTYPE5;
(function (RANKINGTYPE5) {
    RANKINGTYPE5[RANKINGTYPE5["GSMC"] = 0] = "GSMC";
    RANKINGTYPE5[RANKINGTYPE5["BYSR"] = 1] = "BYSR";
    RANKINGTYPE5[RANKINGTYPE5["BYYSZK"] = 2] = "BYYSZK";
    RANKINGTYPE5[RANKINGTYPE5["BYBLYE"] = 3] = "BYBLYE";
    RANKINGTYPE5[RANKINGTYPE5["YSZKZSRBZ"] = 4] = "YSZKZSRBZ";
    RANKINGTYPE5[RANKINGTYPE5["MONTHRANKING"] = 5] = "MONTHRANKING";
})(RANKINGTYPE5 || (RANKINGTYPE5 = {}));
;
//存货占比
var RANKINGTYPE6;
(function (RANKINGTYPE6) {
    RANKINGTYPE6[RANKINGTYPE6["GSMC"] = 0] = "GSMC";
    RANKINGTYPE6[RANKINGTYPE6["BYSR"] = 1] = "BYSR";
    RANKINGTYPE6[RANKINGTYPE6["BYCH"] = 2] = "BYCH";
    RANKINGTYPE6[RANKINGTYPE6["CHZSRBZ"] = 3] = "CHZSRBZ";
    RANKINGTYPE6[RANKINGTYPE6["MONTHRANKING"] = 4] = "MONTHRANKING";
})(RANKINGTYPE6 || (RANKINGTYPE6 = {}));
;
//应收账款加存货占比
var RANKINGTYPE7;
(function (RANKINGTYPE7) {
    RANKINGTYPE7[RANKINGTYPE7["GSMC"] = 0] = "GSMC";
    RANKINGTYPE7[RANKINGTYPE7["BYSR"] = 1] = "BYSR";
    RANKINGTYPE7[RANKINGTYPE7["BYYSZK"] = 2] = "BYYSZK";
    RANKINGTYPE7[RANKINGTYPE7["BYCH"] = 3] = "BYCH";
    RANKINGTYPE7[RANKINGTYPE7["YSZKCHZSRBZ"] = 4] = "YSZKCHZSRBZ";
    RANKINGTYPE7[RANKINGTYPE7["MONTHRANKING"] = 5] = "MONTHRANKING";
})(RANKINGTYPE7 || (RANKINGTYPE7 = {}));
;
var companys_ranking;
(function (companys_ranking) {
    var router = framework.router;
    var JQGridAssistantFactory = (function () {
        function JQGridAssistantFactory() {
        }
        JQGridAssistantFactory.createTable = function (gridName, RankingType) {
            if (RankingType == 1 || RankingType == 9 || RankingType == 11) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度完成率排名", "yearRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("年度计划", "n1"))
                        .append(new JQTable.Node("年度累计完成", "n2"))
                        .append(new JQTable.Node("计划完成率", "n3"))
                        .append(new JQTable.Node("年度排名", "n4", true, JQTable.TextAlign.Right, 0, undefined, undefined, true, true, "int")),
                    new JQTable.Node("月度完成率排名", "monthRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("月度计划", "y1"))
                        .append(new JQTable.Node("月度完成", "y2"))
                        .append(new JQTable.Node("月度完成率", "y3"))
                        .append(new JQTable.Node("月度排名", "y4", true, JQTable.TextAlign.Right, 0, undefined, undefined, true, true, "int"))
                ], gridName);
            }
            else if (RankingType == 2) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度同比增长情况排名", "yearRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("年度累计", "n1"))
                        .append(new JQTable.Node("去年同期累计", "n2"))
                        .append(new JQTable.Node("同比增长", "n3"))
                        .append(new JQTable.Node("年度排名", "n4", true, JQTable.TextAlign.Right, 0, undefined, undefined, true, true, "int")),
                    new JQTable.Node("月度同比增长情况排名", "monthRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("当月完成", "y1"))
                        .append(new JQTable.Node("去年同期", "y2"))
                        .append(new JQTable.Node("同比增长", "y3"))
                        .append(new JQTable.Node("月度排名", "y4", true, JQTable.TextAlign.Right, 0, undefined, undefined, true, true, "int"))
                ], gridName);
            }
            else if (RankingType == 3 || RankingType == 4 || RankingType == 13 || RankingType == 14) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("年度累计完成排名", "yearRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("年度累计完成", "n1"))
                        .append(new JQTable.Node("年度排名", "n2", true, JQTable.TextAlign.Right, 0, undefined, undefined, true, true, "int")),
                    new JQTable.Node("月度完成", "monthRanking", true, JQTable.TextAlign.Left)
                        .append(new JQTable.Node("月度完成", "y1"))
                        .append(new JQTable.Node("月度排名", "y2", true, JQTable.TextAlign.Right, 0, undefined, undefined, true, true, "int"))
                ], gridName);
            }
            else if (RankingType == 5) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月收入（还原至全年）", "income", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月应收账款", "accountReceive", true, JQTable.TextAlign.Left),
                    new JQTable.Node("应收账款占收入比重", "accountReceiveRate", true, JQTable.TextAlign.Left),
                    new JQTable.Node("月度排名", "monthRanking", true, JQTable.TextAlign.Left, 0, undefined, undefined, true, true, "int")
                ], gridName);
            }
            else if (RankingType == 6) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月收入（还原至全年）", "income", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月应收账款", "accountReceive", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月保理余额", "factoring", true, JQTable.TextAlign.Left),
                    new JQTable.Node("应收账款占收入比重", "accountReceiveRate", true, JQTable.TextAlign.Left),
                    new JQTable.Node("月度排名", "monthRanking", true, JQTable.TextAlign.Left, 0, undefined, undefined, true, true, "int")
                ], gridName);
            }
            else if (RankingType == 7) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月收入（还原至全年）", "income", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月存货", "stock", true, JQTable.TextAlign.Left),
                    new JQTable.Node("存货占收入比重", "stockRate", true, JQTable.TextAlign.Left),
                    new JQTable.Node("月度排名", "monthRanking", true, JQTable.TextAlign.Left, 0, undefined, undefined, true, true, "int")
                ], gridName);
            }
            else if (RankingType == 8) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("单位名称", "dwmc", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月收入（还原至全年）", "income", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月应收账款", "accountReceive", true, JQTable.TextAlign.Left),
                    new JQTable.Node("本月存货", "stock", true, JQTable.TextAlign.Left),
                    new JQTable.Node("（应收账款+存货）占收入比重", "accountReceiveandStockRate", true, JQTable.TextAlign.Left),
                    new JQTable.Node("月度排名", "monthRanking", true, JQTable.TextAlign.Left, 0, undefined, undefined, true, true, "int")
                ], gridName);
            }
        };
        return JQGridAssistantFactory;
    }());
    var SimpleView = (function () {
        function SimpleView() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("/BusinessManagement/ydzbRanking/companys_ranking_update.do");
            router.register(this);
        }
        SimpleView.prototype.getId = function () {
            return Util.FAMOUS_VIEW;
        };
        SimpleView.prototype.onEvent = function (e) {
            switch (e.id) {
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
                case Util.MSG_UPDATE:
                    this.updateUI();
                    break;
            }
        };
        SimpleView.prototype.init = function (opt) {
            var _this = this;
            this.mOpt = opt;
            var minDate = Util.addYear(opt.date, -3);
            minDate.month = 1;
            $("#grid-date").jeDate({
                skinCell: "jedatedeepgreen",
                format: "YYYY年MM月",
                isTime: false,
                isinitVal: true,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(minDate),
                maxDate: Util.date2Str(opt.date),
            }).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top");
            $(window).resize(function () {
                _this.adjustSize();
            });
            $("#grid-update").on("click", function () {
                _this.updateUI();
            });
            $("#grid-export").on("click", function () {
                _this.exportExcel();
            });
            $("#company-type").change(function () {
                _this.showCont();
            });
            this.showCont();
            this.updateUI();
        };
        SimpleView.prototype.formatData = function (data) {
            var row = [];
            var mdata = [];
            for (var i = 0; i < this.mData.length; ++i) {
                if (data[i] == null) {
                    mdata[i] = [].concat(this.mData[i]);
                }
                else {
                    row = [].concat(this.mData[i]);
                    mdata[i] = data[i].concat(row);
                }
                for (var j = 1; j < mdata[i].length; j++) {
                    if (this.mIndex == 1 || this.mIndex == 9 || this.mIndex == 11) {
                        if (RANKINGTYPE1.YEARRANKING == j || RANKINGTYPE1.MONTHRANKING == j) {
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }
                        else if (RANKINGTYPE1.JHWCL == j || RANKINGTYPE1.YDWCL == j) {
                            mdata[i][j] = Util.formatPercent(mdata[i][j]);
                        }
                        else {
                            mdata[i][j] = Util.formatCurrency(mdata[i][j]);
                        }
                    }
                    else if (this.mIndex == 3 || this.mIndex == 4 || this.mIndex == 13 || this.mIndex == 14) {
                        if (RANKINGTYPE3.YEARRANKING == j || RANKINGTYPE3.MONTHRANKING == j) {
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }
                        else {
                            mdata[i][j] = Util.formatFordot(mdata[i][j], 1);
                        }
                    }
                    else if (this.mIndex == 2) {
                        if (RANKINGTYPE2.YEARRANKING == j || RANKINGTYPE2.MONTHRANKING == j) {
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }
                        else if (RANKINGTYPE2.NDTBZZ == j || RANKINGTYPE2.YDTBZZ == j) {
                            mdata[i][j] = Util.formatPercent(mdata[i][j]);
                        }
                        else {
                            mdata[i][j] = Util.formatCurrency(mdata[i][j]);
                        }
                    }
                    else if (this.mIndex == 5) {
                        if (RANKINGTYPE4.MONTHRANKING == j) {
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }
                        else if (RANKINGTYPE4.YSZKZSRBZ == j) {
                            mdata[i][j] = Util.formatPercent(mdata[i][j]);
                        }
                        else {
                            mdata[i][j] = Util.formatCurrency(mdata[i][j]);
                        }
                    }
                    else if (this.mIndex == 6) {
                        if (RANKINGTYPE5.MONTHRANKING == j) {
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }
                        else if (RANKINGTYPE5.YSZKZSRBZ == j) {
                            mdata[i][j] = Util.formatPercent(mdata[i][j]);
                        }
                        else {
                            mdata[i][j] = Util.formatCurrency(mdata[i][j]);
                        }
                    }
                    else if (this.mIndex == 7) {
                        if (RANKINGTYPE6.MONTHRANKING == j) {
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }
                        else if (RANKINGTYPE6.CHZSRBZ == j) {
                            mdata[i][j] = Util.formatPercent(mdata[i][j]);
                        }
                        else {
                            mdata[i][j] = Util.formatCurrency(mdata[i][j]);
                        }
                    }
                    else if (this.mIndex == 8) {
                        if (RANKINGTYPE7.MONTHRANKING == j) {
                            mdata[i][j] = Util.formatInt(mdata[i][j]);
                        }
                        else if (RANKINGTYPE7.YSZKCHZSRBZ == j) {
                            mdata[i][j] = Util.formatPercent(mdata[i][j]);
                        }
                        else {
                            mdata[i][j] = Util.formatCurrency(mdata[i][j]);
                        }
                    }
                }
            }
            return mdata;
        };
        SimpleView.prototype.showCont = function () {
            switch ($("#company-type").val()) {
                case "1":
                    $("#report-type").empty();
                    var option = $("<option>").text("利润计划完成率排名").val(1);
                    var option2 = $("<option>").text("利润指标年度累计完成同比增长排名").val(2);
                    var option3 = $("<option>").text("人均利润实际完成排名").val(3);
                    var option4 = $("<option>").text("人均收入实际完成排名").val(4);
                    var option5 = $("<option>").text("应收账款占收入比排名").val(5);
                    var option6 = $("<option>").text("应收账款加保理占收入排名").val(6);
                    var option7 = $("<option>").text("存货占收入比排名").val(7);
                    var option8 = $("<option>").text("应收加存货占收入比排名").val(8);
                    var option9 = $("<option>").text("经营性净现金流实际完成排名").val(9);
                    //var option10 = $("<option>").text("各单位净资产收益率完成排名").val(10);
                    $("#report-type").append(option).append(option2).append(option3).append(option4)
                        .append(option5).append(option6).append(option7).append(option8).append(option9);
                    break;
                case "2":
                    $("#report-type").empty();
                    var option = $("<option>").text("利润指标年度累计完成同比增长排名").val(11);
                    //var option2 = $("<option>").text("项目公司净资产收益率排名").val(12);
                    var option3 = $("<option>").text("人均收入完成排名").val(13);
                    var option4 = $("<option>").text("人均利润完成排名").val(14);
                    $("#report-type").append(option).append(option3).append(option4);
                    break;
                default:
                    break;
            }
        };
        SimpleView.prototype.exportExcel = function () {
            $("#exportExcel")[0].action = "/BusinessManagement/ydzbRanking/companys_ranking_export.do?" + Util.Ajax.toUrlParam($.extend(this.getDate(), { rankingType: this.mIndex }));
            $("#exportExcel")[0].submit();
        };
        SimpleView.prototype.getDate = function () {
            var rq = $("#grid-date").val().replace("年", "-").replace("月", "-").replace("日", "-").split("-");
            return {
                year: rq[0] ? parseInt(rq[0]) : undefined,
                month: rq[1] ? parseInt(rq[1]) : undefined,
                day: rq[2] ? parseInt(rq[2]) : undefined
            };
        };
        SimpleView.prototype.updateUI = function () {
            var _this = this;
            this.mIndex = $("#report-type").val();
            this.mDataSet.get($.extend(this.getDate(), { rankingType: this.mIndex }))
                .then(function (dataArray) {
                _this.mData = dataArray;
                _this.updateTable();
            });
        };
        SimpleView.prototype.adjustSize = function () {
            var jqgrid = this.jqgrid();
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
            var maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
            this.tableAssist && this.tableAssist.resizeHeight(maxTableBodyHeight);
            if ($("#" + this.mOpt.tableId).width() != $("#" + this.mOpt.tableId).children().eq(0).width()) {
                jqgrid.setGridWidth($("#" + this.mOpt.tableId).width());
            }
        };
        SimpleView.prototype.jqgrid = function () {
            return $("#" + this.jqgridName());
        };
        SimpleView.prototype.jqgridName = function () {
            return this.mOpt.tableId + "_jqgrid_real";
        };
        SimpleView.prototype.createJqassist = function () {
            var parent = $("#" + this.mOpt.tableId);
            parent.empty();
            parent.append("<table id='" + this.jqgridName() + "'></table>");
            this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName(), this.mIndex);
            return this.tableAssist;
        };
        SimpleView.prototype.updateTable = function () {
            this.createJqassist();
            var data = null;
            if ($("#company-type").val() == "1") {
                if (this.mIndex == 1 || this.mIndex == 2 || this.mIndex == 3 || this.mIndex == 4 || this.mIndex == 9) {
                    var predata = [
                        ["沈变公司"],
                        ["衡变公司"],
                        ["新变厂"],
                        ["鲁缆公司"],
                        ["新缆厂"],
                        ["德缆公司"],
                        ["天池能源"],
                        ["能动公司"],
                        ["新能源公司"],
                        ["新特能源公司"],
                        ["进出口公司"],
                        ["国际工程公司"],
                        ["众和公司"]
                    ];
                    data = this.formatData(predata);
                }
                else {
                    data = this.formatData([]);
                }
            }
            else {
                data = this.formatData([]);
            }
            this.tableAssist.create({
                data: data,
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                height: '100%',
                width: $("#" + this.mOpt.tableId).width(),
                shrinkToFit: true,
                rowNum: 2000,
                autoScroll: true
            });
            this.adjustSize();
        };
        SimpleView.ins = new SimpleView();
        return SimpleView;
    }());
    companys_ranking.SimpleView = SimpleView;
})(companys_ranking || (companys_ranking = {}));
