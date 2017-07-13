var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../messageBox.ts" />
///<reference path="../../dateSelector.ts"/>
var yszkgb;
(function (yszkgb) {
    var yqyszcsys;
    (function (yqyszcsys) {
        var TextAlign = JQTable.TextAlign;
        var JQGridAssistantFactory = (function () {
            function JQGridAssistantFactory() {
            }
            JQGridAssistantFactory.createTable = function (gridName) {
                return new JQTable.JQGridAssistant([
                    new JQTable.Node("月度", "rq", true, TextAlign.Center),
                    new JQTable.Node("月度", "rqa", true, TextAlign.Center),
                    new JQTable.Node("内部因素", "ab"),
                    new JQTable.Node("客户资信", "ac"),
                    new JQTable.Node("滚动付款", "ad"),
                    new JQTable.Node("项目变化", "ae"),
                    new JQTable.Node("合同因素", "af"),
                    new JQTable.Node("手续办理", "ag"),
                    new JQTable.Node("诉讼", "ah"),
                    new JQTable.Node("合计", "ai")
                ], gridName);
            };
            return JQGridAssistantFactory;
        })();
        var SimpleView = (function (_super) {
            __extends(SimpleView, _super);
            function SimpleView(id) {
                _super.call(this, id);
                this.mAjax = new Util.Ajax("/BusinessManagement/yszkgb/yqyszcsys/update.do", false);
            }
            SimpleView.prototype.pluginGetExportUrl = function (date, cpType) {
                return "/BusinessManagement/yszkgb/yqyszcsys/export.do?" + Util.Ajax.toUrlParam({
                    date: date,
                    companyId: cpType
                });
            };
            SimpleView.prototype.option = function () {
                return this.mOpt;
            };
            SimpleView.prototype.pluginUpdate = function (date, cpType) {
                var _this = this;
                this.mDt = date;
                this.mAjax.get({
                    date: date,
                    companyId: cpType
                })
                    .then(function (jsonData) {
                    _this.mData = jsonData;
                    _this.refresh();
                });
            };
            SimpleView.prototype.refresh = function () {
                if (this.mData == undefined) {
                    return;
                }
                this.$(this.option().ctarea).show();
                this.mFinalData = this.updateTable();
                this.updateEchart(this.mFinalData);
                this.adjustSize();
            };
            SimpleView.prototype.updateEchart = function (data) {
                var title = "逾期应收账产生因素";
                var legend = [
                    "内部因素",
                    "客户资信",
                    "滚动付款",
                    "项目变化",
                    "合同因素",
                    "手续办理",
                    "诉讼"];
                var xData = [];
                for (var i = 0; i < data.length; ++i) {
                    xData.push(data[i][1]);
                }
                var tooltip = {
                    trigger: 'axis',
                };
                var yAxis = [
                    {
                        type: 'value',
                    }
                ];
                var series = [];
                for (var i = 0; i < legend.length; ++i) {
                    var rData = [];
                    for (var j = 0; j < data.length; ++j) {
                        rData.push(data[j][i + 2] == "--" ? 0 : data[j][i + 2]);
                    }
                    series.push({
                        name: legend[i],
                        type: 'line',
                        smooth: true,
                        // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data: rData
                    });
                }
                var option = {
                    title: {
                        text: title
                    },
                    tooltip: tooltip,
                    legend: {
                        data: legend
                    },
                    toolbox: {
                        show: true,
                    },
                    calculable: false,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: xData.length < 1 ? [0] : xData
                        }
                    ],
                    yAxis: yAxis,
                    series: series
                };
                echarts.init(this.$(this.option().ct)[0]).setOption(option);
            };
            SimpleView.prototype.init = function (opt) {
                _super.prototype.init.call(this, opt);
                framework.router.to(Util.FAMOUS_VIEW).send(Util.MSG_REG, { name: "逾期应收账产生因素", plugin: this });
            };
            SimpleView.prototype.adjustSize = function () {
                var jqgrid = this.jqgrid();
                if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    jqgrid.setGridWidth(this.jqgridHost().width());
                }
                //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                //this.tableAssist.resizeHeight(maxTableBodyHeight);
                //if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                //    jqgrid.setGridWidth(this.jqgridHost().width());
                //}
                this.$(this.option().ct).css("height", "300px");
                this.$(this.option().ct).css("width", this.jqgridHost().width() + "px");
                this.updateEchart(this.mFinalData);
            };
            SimpleView.prototype.createJqassist = function () {
                var parent = this.$(this.option().tb);
                parent.empty();
                parent.append("<table id='" + this.jqgridName() + "'></table>");
                this.tableAssist = JQGridAssistantFactory.createTable(this.jqgridName());
                this.tableAssist.mergeRow(0);
                this.tableAssist.mergeTitle();
                return this.tableAssist;
            };
            SimpleView.prototype.updateTable = function () {
                this.createJqassist();
                var curDate = new Date(Date.parse(this.mDt.replace(/-/g, '/')));
                var month = curDate.getMonth() + 1;
                var data = [];
                for (var i = month + 1; i <= 12; ++i) {
                    data.push(["上年度", i + "月"].concat(this.mData[i - month - 1]));
                }
                for (var i = 1; i <= month; ++i) {
                    data.push(["本年度", i + "月"].concat(this.mData[12 - month + i - 1]));
                }
                this.tableAssist.create({
                    data: data,
                    datatype: "local",
                    multiselect: false,
                    drag: false,
                    resize: false,
                    height: '100%',
                    width: this.jqgridHost().width(),
                    shrinkToFit: true,
                    rowNum: 2000,
                    autoScroll: true
                });
                return data;
            };
            SimpleView.ins = new SimpleView("yqyszcsys");
            return SimpleView;
        })(yszkgb.BasePluginView);
        yqyszcsys.SimpleView = SimpleView;
    })(yqyszcsys = yszkgb.yqyszcsys || (yszkgb.yqyszcsys = {}));
})(yszkgb || (yszkgb = {}));
