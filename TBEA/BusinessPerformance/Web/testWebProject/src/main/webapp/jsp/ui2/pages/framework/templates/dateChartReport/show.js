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
            dateChartReport.FE_ZL_APPROVED = framework.route.nextId();
            function create() {
                return new SimpleShowView();
            }
            dateChartReport.create = create;
            var SimpleShowView = (function (_super) {
                __extends(SimpleShowView, _super);
                function SimpleShowView() {
                    _super.apply(this, arguments);
                }
                SimpleShowView.prototype.findChartId = function (itemId) {
                    for (var i = 0; i < this.option().itemChart.length; ++i) {
                        if (this.option().itemChart[i].item == itemId) {
                            return this.option().itemChart[i].chart;
                        }
                    }
                    return undefined;
                };
                SimpleShowView.prototype.findChartNode = function (chartId) {
                    for (var i = 0; i < this.option().chartNodes.length; ++i) {
                        if (this.option().chartNodes[i].chart == chartId) {
                            return this.option().chartNodes[i].nodes;
                        }
                    }
                    return undefined;
                };
                SimpleShowView.prototype.updateChartSelect = function () {
                    var changed = false;
                    var chartSelId = this.option().chartSelId;
                    var ctNodeId = this.findChartId(this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id);
                    if (this.chartSelector == undefined) {
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
                    }
                };
                SimpleShowView.prototype.onInitialize = function (opt) {
                    var _this = this;
                    this.opt = opt;
                    this.mChartUpdate = new Util.Ajax(this.option().chartUrl, false);
                    this.unitedSelector = new UnitedSelector(opt.itemNodes, opt.itemId);
                    this.unitedSelector.change(function () {
                        _this.updateChartSelect();
                        _this.adjustHeader();
                    });
                    this.updateChartSelect();
                    $(window).resize(function () {
                        _this.adjustHeader();
                    });
                    _super.prototype.onInitialize.call(this, opt);
                };
                SimpleShowView.prototype.option = function () {
                    return (this.opt);
                };
                //onEvent(e:framework.route.Event):any {
                //    switch (e.id) {
                //        case FE_TB_CLICKED:
                //            $("#" + this.option().host).show();
                //            $("#" + this.option().chartId).hide();
                //            $("#" + this.option().chartSelId).hide();
                //            this.updateTable();
                //            break;
                //        case FE_CT_CLICKED:
                //            $("#" + this.option().host).hide();
                //            $("#" + this.option().chartId).show();
                //            $("#" + this.option().chartSelId).show();
                //            this.updateChart();
                //            break;
                //    }
                //
                //    return super.onEvent(e);
                //}
                SimpleShowView.prototype.getParams = function (date) {
                    return {
                        date: this.getDate(date),
                        item: this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.id
                    };
                };
                SimpleShowView.prototype.update = function (date) {
                    var _this = this;
                    var nodes = this.chartSelector.getNodes();
                    this.mChartUpdate.get($.extend({
                        chart: nodes[nodes.length - 1].data.id
                    }, this.getParams(date))).then(function (jsonData) {
                        _this.mChartResp = jsonData;
                        _this.updateChart();
                    });
                    this.mAjaxUpdate.get(this.getParams(date))
                        .then(function (jsonData) {
                        _this.resp = jsonData;
                        _this.updateTable();
                    });
                };
                SimpleShowView.prototype.adjustHeader = function () {
                    $("#headerHost").removeCss("width");
                    if ($("#headerHost").height() > 40) {
                        $(".page-header").addClass("page-header-double");
                        $("#headerHost").css("width", $("#sels").width() + "px");
                    }
                    else {
                        $(".page-header").removeClass("page-header-double");
                    }
                    return false;
                };
                SimpleShowView.prototype.adjustSize = function () {
                    var jqgrid = this.jqgrid();
                    if ($("#" + this.opt.host).width() != $("#" + this.opt.host + " .ui-jqgrid").width()) {
                        jqgrid.setGridWidth($("#" + this.opt.host).width());
                    }
                    //let maxTableBodyHeight = document.documentElement.clientHeight - 4 - 150;
                    //this.tableAssist.resizeHeight(maxTableBodyHeight);
                    //if (this.jqgridHost().width() != this.jqgridHost().children().eq(0).width()) {
                    //    jqgrid.setGridWidth(this.jqgridHost().width());
                    //}
                    $("#" + this.option().chartId).css("height", "300px");
                    $("#" + this.option().chartId).css("width", $("#" + this.opt.host).width() + "px");
                    this.updateChart();
                };
                SimpleShowView.prototype.updateChart = function () {
                    $("#" + this.option().chartId).empty();
                    $("#" + this.option().chartId).removeAttr("_echarts_instance_");
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
                            show: true,
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
                    echarts.init($("#" + this.option().chartId)[0]).setOption(option);
                };
                return SimpleShowView;
            })(framework.templates.singleDateReport.ShowView);
        })(dateChartReport = templates.dateChartReport || (templates.dateChartReport = {}));
    })(templates = framework.templates || (framework.templates = {}));
})(framework || (framework = {}));
