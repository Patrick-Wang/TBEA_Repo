var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
///<reference path="../../route/route.ts"/>
///<reference path="../../basic/basicdef.ts"/>
///<reference path="../../basic/basicShow.ts"/>
///<reference path="../../../messageBox.ts"/>
///<reference path="../../../components/dateSelectorProxy.ts"/>
///<reference path="../singleDateReport/show.ts"/>
var framework;
(function (framework) {
    var templates;
    (function (templates) {
        var dateChartReport;
        (function (dateChartReport) {
            var UnitedSelector = Util.UnitedSelector;
            dateChartReport.FE_TB_CLICKED = framework.route.nextId();
            dateChartReport.FE_CT_CLICKED = framework.route.nextId();
            function createInstance() {
                return new ShowView();
            }
            dateChartReport.createInstance = createInstance;
            var ShowView = (function (_super) {
                __extends(ShowView, _super);
                function ShowView() {
                    _super.apply(this, arguments);
                }
                ShowView.prototype.findChartId = function (itemId) {
                    for (var i = 0; i < this.option().itemChart.length; ++i) {
                        if (this.option().itemChart[i].item == itemId) {
                            return this.option().itemChart[i].chart;
                        }
                    }
                    return undefined;
                };
                ShowView.prototype.findChartNode = function (chartId) {
                    for (var i = 0; i < this.option().chartNodes.length; ++i) {
                        if (this.option().chartNodes[i].chart == chartId) {
                            return this.option().chartNodes[i].nodes;
                        }
                    }
                    return undefined;
                };
                ShowView.prototype.updateChartSelect = function () {
                    var changed = false;
                    var chartSelId = this.option().chartSelId;
                    var ctNodeId = this.findChartId(this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id);
                    if (this.chartSelector == undefined) {
                        $("#" + this.option().chartId).append("<div id='" + this.option().chartId + "ct' style='width:1200px;height:500px'/>");
                        this.mChartType = ctNodeId;
                        changed = true;
                    }
                    else {
                        if (this.mChartType != ctNodeId) {
                            this.mChartType = ctNodeId;
                            changed = true;
                            $("#" + chartSelId).empty();
                        }
                    }
                    if (changed) {
                        this.chartSelector = new UnitedSelector(this.findChartNode(ctNodeId), chartSelId);
                        $("#" + chartSelId + " select")
                            .multiselect({
                            multiple: false,
                            header: false,
                            minWidth: 100,
                            height: '100%',
                            // noneSelectedText: "请选择月份",
                            selectedList: 1
                        })
                            .css("padding", "2px 0 2px 4px")
                            .css("text-align", "left")
                            .css("font-size", "12px");
                    }
                };
                ShowView.prototype.onInitialize = function (opt) {
                    var _this = this;
                    this.opt = opt;
                    this.mChartUpdate = new Util.Ajax(this.option().chartUrl, false);
                    this.unitedSelector = new UnitedSelector(opt.itemNodes, opt.itemId);
                    this.unitedSelector.change(function () {
                        $("#" + opt.itemId + " select")
                            .multiselect({
                            multiple: false,
                            header: false,
                            minWidth: 100,
                            height: '100%',
                            // noneSelectedText: "请选择月份",
                            selectedList: 1
                        })
                            .css("padding", "2px 0 2px 4px")
                            .css("text-align", "left")
                            .css("font-size", "12px");
                        _this.updateChartSelect();
                    });
                    $("#" + opt.itemId + " select")
                        .multiselect({
                        multiple: false,
                        header: false,
                        minWidth: 100,
                        height: '100%',
                        // noneSelectedText: "请选择月份",
                        selectedList: 1
                    })
                        .css("padding", "2px 0 2px 4px")
                        .css("text-align", "left")
                        .css("font-size", "12px");
                    this.updateChartSelect();
                    _super.prototype.onInitialize.call(this, opt);
                };
                ShowView.prototype.option = function () {
                    return (this.opt);
                };
                ShowView.prototype.onEvent = function (e) {
                    switch (e.id) {
                        case dateChartReport.FE_TB_CLICKED:
                            $("#" + this.option().host).show();
                            $("#" + this.option().chartId).hide();
                            $("#" + this.option().chartSelId).hide();
                            this.updateTable();
                            break;
                        case dateChartReport.FE_CT_CLICKED:
                            $("#" + this.option().host).hide();
                            $("#" + this.option().chartId).show();
                            $("#" + this.option().chartSelId).show();
                            this.updateChart();
                            break;
                    }
                    return _super.prototype.onEvent.call(this, e);
                };
                ShowView.prototype.getParams = function (date) {
                    return {
                        date: this.getDate(date),
                        item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
                    };
                };
                ShowView.prototype.getDate = function (date) {
                    return "" + (date.year + "-" + (date.month == undefined ? 1 : date.month) + "-" + (date.day == undefined ? 1 : date.day));
                };
                ShowView.prototype.update = function (date) {
                    var _this = this;
                    var nodes = this.chartSelector.getNodes();
                    this.mChartUpdate.get($.extend({
                        chart: nodes[nodes.length - 1].data.id
                    }, this.getParams(date))).then(function (jsonData) {
                        _this.mChartResp = jsonData;
                        if (!$("#" + _this.option().chartId).is(":hidden")) {
                            _this.updateChart();
                        }
                    });
                    this.mAjaxUpdate.get(this.getParams(date))
                        .then(function (jsonData) {
                        _this.resp = jsonData;
                        if (!$("#" + _this.option().host).is(":hidden")) {
                            _this.updateTable();
                        }
                    });
                };
                ShowView.prototype.updateTable = function () {
                    var name = this.opt.host + "_jqgrid_uiframe";
                    var pagername = name + "pager";
                    this.mTableAssist = Util.createTable(name, this.resp);
                    var parent = $("#" + this.opt.host);
                    parent.empty();
                    parent.append("<table id='" + name + "'></table><div id='" + pagername + "'></div>");
                    var jqTable = $("#" + name);
                    jqTable.jqGrid(this.mTableAssist.decorate({
                        datatype: "local",
                        data: this.mTableAssist.getData(this.resp.data),
                        multiselect: false,
                        drag: false,
                        resize: false,
                        assistEditable: false,
                        //autowidth : false,
                        cellsubmit: 'clientArray',
                        //editurl: 'clientArray',
                        cellEdit: false,
                        // height: data.length > 25 ? 550 : '100%',
                        // width: titles.length * 200,
                        rowNum: 1000,
                        height: this.resp.data.length > 25 ? 550 : '100%',
                        width: this.resp.width == undefined ? 1200 : this.resp.width,
                        shrinkToFit: true,
                        autoScroll: true
                    }));
                };
                ShowView.prototype.exportExcel = function (date, id) {
                    $("#" + id)[0].action = this.opt.exportUrl + "?" + Util.Ajax.toUrlParam(this.getParams(date));
                    $("#" + id)[0].submit();
                };
                ShowView.prototype.updateChart = function () {
                    var series = [];
                    for (var i in this.mChartResp.yNames) {
                        series.push({
                            name: this.mChartResp.yNames[i],
                            type: 'line',
                            smooth: true,
                            // itemStyle: {normal: {areaStyle: {type: 'default'}}},
                            data: this.mChartResp.data[i].length < 1 ? [0] : Util.replaceNull(this.mChartResp.data[i])
                        });
                    }
                    var option = {
                        title: {
                            text: this.chartSelector.getDataNode(this.chartSelector.getPath()).data.value
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: this.mChartResp.yNames
                        },
                        toolbox: {
                            show: true
                        },
                        calculable: false,
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: false,
                                data: this.mChartResp.xNames.length < 1 ? [0] : this.mChartResp.xNames
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: series
                    };
                    echarts.init($("#" + this.option().chartId + "ct")[0]).setOption(option);
                };
                return ShowView;
            })(framework.templates.singleDateReport.ShowView);
            dateChartReport.ShowView = ShowView;
        })(dateChartReport = templates.dateChartReport || (templates.dateChartReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
