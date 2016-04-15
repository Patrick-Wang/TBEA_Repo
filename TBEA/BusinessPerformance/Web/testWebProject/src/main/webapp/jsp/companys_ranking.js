/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />
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
    var View = (function () {
        function View() {
            this.mData = [];
            this.mDataSet = new Util.Ajax("companys_ranking_update.do");
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };
        View.prototype.init = function (tableId, dateId, year, month) {
            this.mYear = year;
            this.mTableId = tableId;
            this.mMonth = month;
            this.mDs = new Util.DateSelector({ year: year - 1, month: 1 }, { year: year, month: month }, dateId);
            this.onIndexSelected();
        };
        View.prototype.onIndexSelected = function () {
            this.mIndex = $("#ranktype").val();
            //this.mIndex = $("#indextype  option:selected").text();
        };
        //导出excel
        View.prototype.exportExcel = function (fName) {
            var date = this.mDs.getDate();
            $("#export")[0].action = "companys_ranking_export.do?" + Util.Ajax.toUrlParam({ year: date.year, month: date.month, rankingType: this.mIndex });
            $("#export")[0].submit();
        };
        View.prototype.updateUI = function () {
            var _this = this;
            var date = this.mDs.getDate();
            this.onIndexSelected();
            this.mDataSet.get({ month: date.month, year: date.year, rankingType: this.mIndex })
                .then(function (dataArray) {
                _this.mData = dataArray;
                $('h1').text(date.year + "年" + date.month + "月" + "经营单位指标排名情况");
                document.title = date.year + "年" + date.month + "月" + "经营单位指标排名情况";
                _this.updateTable(_this.mIndex);
            });
        };
        View.prototype.formatData = function (data) {
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
        View.prototype.updateTable = function (rankingType) {
            var name = this.mTableId + "_jqgrid_1234";
            var tableAssist = null;
            var data = null;
            var parent = $("#" + this.mTableId);
            parent.empty();
            parent.append("<table id='" + name + "'></table>");
            if ($("input[name=rank]:checked").attr("id") == "JYcompanys") {
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
                        ["众和公司"]];
                    data = this.formatData(predata);
                }
                else {
                    data = this.formatData([]);
                }
            }
            else {
                data = this.formatData([]);
            }
            tableAssist = JQGridAssistantFactory.createTable(name, rankingType);
            $("#" + name).jqGrid(tableAssist.decorate({
                // url: "TestTable/WGDD_load.do",
                // datatype: "json",
                data: tableAssist.getData(data),
                datatype: "local",
                multiselect: false,
                drag: false,
                resize: false,
                //autowidth : false,
                //                    cellsubmit: 'clientArray',
                //                    cellEdit: true,
                height: '100%',
                width: 1000,
                shrinkToFit: true,
                rowNum: 100,
                autoScroll: true
            }));
            $("#export").css('display', 'block');
        };
        return View;
    }());
    companys_ranking.View = View;
})(companys_ranking || (companys_ranking = {}));
