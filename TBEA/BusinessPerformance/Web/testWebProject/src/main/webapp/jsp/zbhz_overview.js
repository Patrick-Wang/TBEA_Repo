/// <reference path="jqgrid/jqassist.ts" />
/// <reference path="util.ts" />

var zbhz_overview;
(function (zbhz_overview) {
    var CompanyType;
    (function (CompanyType) {
        CompanyType[CompanyType["SB"] = 0] = "SB";
        CompanyType[CompanyType["HB"] = 1] = "HB";
        CompanyType[CompanyType["XB"] = 2] = "XB";
        CompanyType[CompanyType["TB"] = 3] = "TB";
        CompanyType[CompanyType["LL"] = 4] = "LL";
        CompanyType[CompanyType["XL"] = 5] = "XL";
        CompanyType[CompanyType["DL"] = 6] = "DL";
        CompanyType[CompanyType["GY"] = 7] = "GY";
        CompanyType[CompanyType["XNY"] = 8] = "XNY";
        CompanyType[CompanyType["TCNY"] = 9] = "TCNY";
        CompanyType[CompanyType["NDGS"] = 10] = "NDGS";
        CompanyType[CompanyType["ZJWL"] = 11] = "ZJWL";
        CompanyType[CompanyType["JCK"] = 12] = "JCK";
        CompanyType[CompanyType["GCGS"] = 13] = "GCGS";
        CompanyType[CompanyType["ZH"] = 14] = "ZH";
        CompanyType[CompanyType["SBDCY"] = 15] = "SBDCY";
        CompanyType[CompanyType["XNYCY"] = 16] = "XNYCY";
        CompanyType[CompanyType["NYCY"] = 17] = "NYCY";
        CompanyType[CompanyType["GCL"] = 18] = "GCL";
        CompanyType[CompanyType["JT"] = 19] = "JT";
        CompanyType[CompanyType["ALL"] = 100] = "ALL";
    })(CompanyType || (CompanyType = {}));

    var YDZBDataSet = (function () {
        function YDZBDataSet(comId, yd, jd, nd, ydtb, jdtb) {
            this.companyId = 19 /* JT */;
            this.companyId = comId;
            this.mYd = yd;
            this.mJd = jd;
            this.mNd = nd;
            this.mYdtb = ydtb;
            this.mJdtb = jdtb;
        }
        YDZBDataSet.prototype.getYd = function () {
            return this.mYd;
        };
        YDZBDataSet.prototype.getJd = function () {
            return this.mJd;
        };
        YDZBDataSet.prototype.getNd = function () {
            return this.mNd;
        };
        YDZBDataSet.prototype.getYdtb = function () {
            return this.mYdtb;
        };
        YDZBDataSet.prototype.getJdtb = function () {
            return this.mJdtb;
        };

        YDZBDataSet.prototype.getType = function () {
            return this.companyId;
        };
        return YDZBDataSet;
    })();

    var DataSetManager = (function () {
        function DataSetManager(zbid) {
            this.dataSetMap = {};
            this.mZbid = zbid;
        }
        DataSetManager.prototype.addDataSet = function (dataSet) {
            this.dataSetMap[dataSet.getType() + ""] = dataSet;
        };

        DataSetManager.prototype.getData = function (ty, callBack) {
            var _this = this;
            if (this.dataSetMap[ty + ""] == undefined) {
                $.ajax({
                    type: "GET",
                    url: "zbhz_overview_update.do?" + "companyId=" + ty + "&zb=" + this.mZbid,
                    success: function (data) {
                        var dataObj = JSON.parse(data);
                        _this.addDataSet(new YDZBDataSet(ty, dataObj.yd, dataObj.jd, dataObj.nd, dataObj.ydtb, dataObj.jdtb));
                        callBack(_this.dataSetMap[ty + ""]);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        callBack(null);
                    }
                });
            } else {
                callBack(this.dataSetMap[ty + ""]);
            }
        };
        return DataSetManager;
    })();

    var ChartType;
    (function (ChartType) {
        ChartType[ChartType["YDZB"] = 0] = "YDZB";
        ChartType[ChartType["JDZB"] = 1] = "JDZB";
        ChartType[ChartType["NDZB"] = 2] = "NDZB";
        ChartType[ChartType["YDTQ"] = 3] = "YDTQ";
        ChartType[ChartType["JDTQ"] = 4] = "JDTQ";
    })(ChartType || (ChartType = {}));

    var View = (function () {
        function View() {
            this.mSelectCy = true;
            this.mCy = 19 /* JT */;
            this.mDw = 100 /* ALL */;
        }
        View.newInstance = function () {
            if (View.ins == undefined) {
                View.ins = new View();
            }
            return View.ins;
        };

        View.prototype.init = function (echartIds, month, year, zbid) {
            this.mDataSetMgr = new DataSetManager(zbid);
            this.mMonth = month;
            this.mYear = year;
            this.mChartIds = echartIds;
            this.updateUI();
        };

        View.prototype.getCurrentCompany = function () {
            if (this.mDw != 100 /* ALL */) {
                return this.mDw;
            } else {
                return this.mCy;
            }
        };

        View.prototype.updateUI = function () {
            var _this = this;
            this.mDataSetMgr.getData(this.getCurrentCompany(), function (dataSet) {
                if (dataSet != null) {
                    _this.updateYdUI(dataSet.getYd());
                    _this.updateJdUI(dataSet.getJd());
                    _this.updateNdUI(dataSet.getNd());
                    _this.updateYdtbUI(dataSet.getYdtb());
                    _this.updateJdtbUI(dataSet.getJdtb());
                }
            });
        };

        View.prototype.getMonth = function () {
            var month = [];
            for (var i = 0; i < this.mMonth; ++i) {
                month.push((i + 1) + "月");
            }
            return month;
        };

        View.prototype.updateYdUI = function (data) {
            var month = this.getMonth();
            var legend = [(this.mMonth) + "月计划", (this.mMonth) + "月完成", (this.mMonth) + "月计划完成率"];
            var option = {
                title: {
                    text: '月度指标完成情况'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,
                        data: month
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
                    {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: data[2]
                    }
                ]
            };
            echarts.init($('#' + this.mChartIds[0 /* YDZB */])[0]).setOption(option);
        };

        View.prototype.updateJdUI = function (data) {
            var jdCount = data[0].length;
            var jd = [];
            var legend = ["季度计划", "季度累计", "季度完成率"];
            for (var i = 1; i <= jdCount; ++i) {
                jd.push("第" + i + "季度");
            }

            var option = {
                title: {
                    text: '季度指标完成情况'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,
                        data: jd
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
                    {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: data[2]
                    }
                ]
            };
            echarts.init($('#' + this.mChartIds[1 /* JDZB */])[0]).setOption(option);
        };

        View.prototype.updateNdUI = function (data) {
            var jdCount = data[0].length;
            var jd = [];
            var legend = ["年度计划", "年度累计", "累计完成率"];
            var xYear = [this.mYear - 2 + "年", this.mYear - 1 + "年", this.mYear + "年"];

            for (var i = 1; i <= jdCount; ++i) {
                jd.push("第" + i + "季度");
            }

            var option = {
                title: {
                    text: '年度指标完成情况'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,
                        data: xYear
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
                    {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: data[2]
                    }
                ]
            };
            echarts.init($('#' + this.mChartIds[2 /* NDZB */])[0]).setOption(option);
        };

        View.prototype.updateYdtbUI = function (data) {
            var month = this.getMonth();
            var legend = [(this.mYear - 1) + "年*月完成", (this.mYear) + "年*月完成", "同比增长率"];
            var option = {
                title: {
                    text: '月度同期对比'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,
                        data: month
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
                    {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: data[2]
                    }
                ]
            };
            echarts.init($('#' + this.mChartIds[3 /* YDTQ */])[0]).setOption(option);
        };

        View.prototype.updateJdtbUI = function (data) {
            var jdCount = data[0].length;
            var jd = [];
            var legend = [this.mYear - 1 + "年季度累计", this.mYear + "年季度累计", "同比增长率"];
            for (var i = 1; i <= jdCount; ++i) {
                jd.push("第" + i + "季度");
            }

            var option = {
                title: {
                    text: '季度同期对比'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: legend
                },
                toolbox: {
                    show: true
                },
                calculable: false,
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: true,
                        data: jd
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
                    {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    }
                ],
                series: [
                    {
                        name: legend[0],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[0]
                    },
                    {
                        name: legend[1],
                        type: 'bar',
                        smooth: true,
                        itemStyle: { normal: { areaStyle: { type: 'default' } } },
                        data: data[1]
                    },
                    {
                        name: legend[2],
                        type: 'line',
                        smooth: true,
                        yAxisIndex: 1,
                        data: data[2]
                    }
                ]
            };
            echarts.init($('#' + this.mChartIds[4 /* JDTQ */])[0]).setOption(option);
        };

        View.prototype.onCySelected = function (val) {
            this.mCy = val;
            var select = $("#dw select")[0];
            this.mDw = parseInt(select.options[select.selectedIndex].value);
        };

        View.prototype.onDwSelected = function (val) {
            this.mDw = val;
        };
        return View;
    })();
    zbhz_overview.View = View;
})(zbhz_overview || (zbhz_overview = {}));
//# sourceMappingURL=zbhz_overview.js.map
