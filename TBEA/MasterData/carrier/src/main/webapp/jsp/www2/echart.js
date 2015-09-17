var __extends = this.__extends || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    __.prototype = b.prototype;
    d.prototype = new __();
};
var ECharts;
(function (ECharts) {
    (function (LegendOrient) {
        LegendOrient[LegendOrient["vertical"] = 0] = "vertical";
        LegendOrient[LegendOrient["horizontal"] = 1] = "horizontal";
    })(ECharts.LegendOrient || (ECharts.LegendOrient = {}));
    var LegendOrient = ECharts.LegendOrient;

    (function (LegendX) {
        LegendX[LegendX["left"] = 0] = "left";
        LegendX[LegendX["center"] = 1] = "center";
        LegendX[LegendX["right"] = 2] = "right";
    })(ECharts.LegendX || (ECharts.LegendX = {}));
    var LegendX = ECharts.LegendX;

    (function (LabelPosition) {
        LabelPosition[LabelPosition["outer"] = 0] = "outer";
        LabelPosition[LabelPosition["inner"] = 1] = "inner";
    })(ECharts.LabelPosition || (ECharts.LabelPosition = {}));
    var LabelPosition = ECharts.LabelPosition;

    var pair = (function () {
        function pair(first, second) {
            this.mFirst = first;
            this.mSecond = second;
        }
        Object.defineProperty(pair.prototype, "first", {
            get: function () {
                return this.mFirst;
            },
            enumerable: true,
            configurable: true
        });

        Object.defineProperty(pair.prototype, "second", {
            get: function () {
                return this.mSecond;
            },
            enumerable: true,
            configurable: true
        });
        return pair;
    })();
    ECharts.pair = pair;

    function mkobj(obj, key) {
        if (obj[key] == undefined) {
            obj[key] = {};
        }
        return obj[key];
    }

    var XAxis = (function () {
        function XAxis(data, boundaryGap) {
            if (typeof boundaryGap === "undefined") { boundaryGap = false; }
            this.mData = data;
            this.mBoundaryGap = boundaryGap;
        }
        Object.defineProperty(XAxis.prototype, "data", {
            get: function () {
                return this.mData;
            },
            enumerable: true,
            configurable: true
        });

        XAxis.prototype.toJson = function () {
            return new pair("xAxis", [
                {
                    type: 'category',
                    boundaryGap: this.mBoundaryGap,
                    data: this.mData
                }
            ]);
        };
        return XAxis;
    })();
    ECharts.XAxis = XAxis;

    var YAxis = (function () {
        function YAxis() {
        }
        YAxis.prototype.toJson = function () {
            return new pair("yAxis", [
                {
                    type: 'value'
                }
            ]);
        };
        return YAxis;
    })();
    ECharts.YAxis = YAxis;

    var Legend = (function () {
        function Legend(data, x, orient) {
            if (typeof orient === "undefined") { orient = 1 /* horizontal */; }
            this.mX = 1 /* center */;
            this.mData = data;
            this.mX = x;
            this.mOrient = orient;
        }
        Object.defineProperty(Legend.prototype, "x", {
            get: function () {
                return this.mX;
            },
            enumerable: true,
            configurable: true
        });

        Legend.prototype.toJson = function () {
            return new pair("legend", {
                x: Legend.LegendPosMap[this.mX],
                data: this.mData,
                orient: Legend.LegendOrientMap[this.mOrient]
            });
        };
        Legend.LegendPosMap = ["left", "center", "right"];
        Legend.LegendOrientMap = ["vertical", "horizontal"];
        return Legend;
    })();
    ECharts.Legend = Legend;

    var Label = (function () {
        function Label(pos, show) {
            this.mPosition = pos;
            this.mShow = show;
        }
        Object.defineProperty(Label.prototype, "position", {
            get: function () {
                return Label.LabelPositionMap[this.mPosition];
            },
            enumerable: true,
            configurable: true
        });

        Object.defineProperty(Label.prototype, "show", {
            get: function () {
                return this.mShow;
            },
            enumerable: true,
            configurable: true
        });
        Label.LabelPositionMap = ["outer", "inner"];
        return Label;
    })();
    ECharts.Label = Label;

    var Series = (function () {
        function Series(name, data, type) {
            this.mSeries = { smooth: true };
            this.mSeries.name = name;
            this.mSeries.type = type;
            this.mSeries.data = data;
        }
        Object.defineProperty(Series.prototype, "xAxisIndex", {
            set: function (xAxisIndex) {
                this.mSeries.xAxisIndex = xAxisIndex;
            },
            enumerable: true,
            configurable: true
        });

        Object.defineProperty(Series.prototype, "label", {
            set: function (lab) {
                var label = mkobj(mkobj(mkobj(this.mSeries, "itemStyle"), "normal"), "label");
                if (lab.position != undefined) {
                    label.position = lab.position;
                }

                if (lab.show != undefined) {
                    label.show = lab.show;
                }
            },
            enumerable: true,
            configurable: true
        });

        Series.prototype.rgba = function (r, g, b, a) {
            if (typeof a === "undefined") { a = 1; }
            mkobj(mkobj(this.mSeries, "itemStyle"), "normal").color = "rgba(" + r + "," + g + "," + b + "," + a + ")";
        };

        Series.prototype.toJson = function () {
            var p = new pair("series", [this.mSeries]);
            return p;
        };
        return Series;
    })();
    ECharts.Series = Series;

    (function (Bar) {
        var SeriesImpl = (function (_super) {
            __extends(SeriesImpl, _super);
            function SeriesImpl(name, data) {
                _super.call(this, name, data, "bar");
            }
            Object.defineProperty(SeriesImpl.prototype, "stack", {
                set: function (name) {
                    this.mStackName = name;
                },
                enumerable: true,
                configurable: true
            });

            SeriesImpl.prototype.toJson = function () {
                var p = _super.prototype.toJson.call(this);
                if (this.mStackName != undefined) {
                    p.second[0].stack = this.mStackName;
                }
                return p;
            };
            return SeriesImpl;
        })(Series);
        Bar.SeriesImpl = SeriesImpl;
    })(ECharts.Bar || (ECharts.Bar = {}));
    var Bar = ECharts.Bar;

    (function (Line) {
        var SeriesImpl = (function (_super) {
            __extends(SeriesImpl, _super);
            function SeriesImpl(name, data) {
                _super.call(this, name, data, "line");
            }
            Object.defineProperty(SeriesImpl.prototype, "stack", {
                set: function (name) {
                    this.mStackName = name;
                },
                enumerable: true,
                configurable: true
            });

            SeriesImpl.prototype.toJson = function () {
                var p = _super.prototype.toJson.call(this);
                if (this.mStackName != undefined) {
                    p.second[0]["stack"] = this.mStackName;
                }
                return p;
            };
            return SeriesImpl;
        })(Series);
        Line.SeriesImpl = SeriesImpl;

        var SquareSeries = (function (_super) {
            __extends(SquareSeries, _super);
            function SquareSeries(name, data) {
                _super.call(this, name, data);
            }
            SquareSeries.prototype.toJson = function () {
                var p = _super.prototype.toJson.call(this);
                mkobj(mkobj(p.second[0], "itemStyle"), "normal").areaStyle = { type: 'default' };
                return p;
            };
            return SquareSeries;
        })(SeriesImpl);
        Line.SquareSeries = SquareSeries;
    })(ECharts.Line || (ECharts.Line = {}));
    var Line = ECharts.Line;
    (function (Pie) {
        var Data = (function () {
            function Data(name, value) {
                this.mName = name;
                this.mValue = value;
            }
            Object.defineProperty(Data.prototype, "name", {
                get: function () {
                    return this.mName;
                },
                enumerable: true,
                configurable: true
            });

            Object.defineProperty(Data.prototype, "value", {
                get: function () {
                    return this.mValue;
                },
                enumerable: true,
                configurable: true
            });
            return Data;
        })();
        Pie.Data = Data;

        var LabelLine = (function () {
            function LabelLine(show, length) {
                this.mShow = true;
                this.mShow = show;
                this.mLength = length;
            }
            Object.defineProperty(LabelLine.prototype, "show", {
                get: function () {
                    return this.mShow;
                },
                enumerable: true,
                configurable: true
            });

            Object.defineProperty(LabelLine.prototype, "length", {
                get: function () {
                    return this.mLength;
                },
                enumerable: true,
                configurable: true
            });
            return LabelLine;
        })();
        Pie.LabelLine = LabelLine;

        var SeriesImpl = (function (_super) {
            __extends(SeriesImpl, _super);
            function SeriesImpl(name, data) {
                _super.call(this, name, (function () {
                    var newData = [];
                    for (var i in data) {
                        newData.push({
                            name: data[i].name,
                            value: data[i].value
                        });
                    }
                    return newData;
                })(), "pie");
                this.mInRadius = 0;
            }
            Object.defineProperty(SeriesImpl.prototype, "inRadius", {
                set: function (inRadius) {
                    this.mInRadius = inRadius;
                },
                enumerable: true,
                configurable: true
            });

            Object.defineProperty(SeriesImpl.prototype, "labelLine", {
                set: function (line) {
                    this.mLabelLine = line;
                },
                enumerable: true,
                configurable: true
            });

            Object.defineProperty(SeriesImpl.prototype, "centerX", {
                set: function (x) {
                    this.mCenterLeft = x;
                },
                enumerable: true,
                configurable: true
            });

            Object.defineProperty(SeriesImpl.prototype, "centerY", {
                set: function (y) {
                    this.mCenterTop = y;
                },
                enumerable: true,
                configurable: true
            });

            Object.defineProperty(SeriesImpl.prototype, "outRadius", {
                set: function (outRadius) {
                    this.mOutRadius = outRadius;
                },
                enumerable: true,
                configurable: true
            });

            SeriesImpl.prototype.toJson = function () {
                var p = _super.prototype.toJson.call(this);
                if (this.mOutRadius != undefined) {
                    p.second[0]["radius"] = [this.mInRadius, this.mOutRadius];
                    if (this.mCenterLeft != undefined) {
                        p.second[0]["center"] = [this.mCenterLeft];
                        if (this.mCenterTop != undefined) {
                            p.second[0]["center"].push(this.mCenterTop);
                        }
                    }
                }

                if (this.mLabelLine != undefined) {
                    var labelLine = mkobj(mkobj(mkobj(p.second[0], "itemStyle"), "normal"), "labelLine");
                    labelLine.show = this.mLabelLine.show;
                    labelLine.length = this.mLabelLine.length;
                }
                return p;
            };
            return SeriesImpl;
        })(Series);
        Pie.SeriesImpl = SeriesImpl;
    })(ECharts.Pie || (ECharts.Pie = {}));
    var Pie = ECharts.Pie;

    var JsonElement = (function () {
        function JsonElement(key, jsonObj) {
            this.mJson = {};
            this.mJson = jsonObj;
            this.mKey = key;
        }
        JsonElement.prototype.toJson = function () {
            return new pair(this.mKey, this.mJson);
        };
        return JsonElement;
    })();

    var Chart = (function () {
        function Chart(x, y) {
            this.elements = [];
            this.addElement(x);
            this.addElement(y);
        }
        Chart.prototype.addElement = function (e) {
            if (e != null) {
                this.elements.push(e);
            }
            return this;
        };

        Chart.prototype.addOption = function (jsonKey, jsonVal) {
            this.addElement(new JsonElement(jsonKey, jsonVal));
            return this;
        };

        Chart.prototype.setLegend = function (legend) {
            return this.addElement(legend);
        };

        Chart.prototype.addSeries = function (series) {
            return this.addElement(series);
        };

        Chart.prototype.addArrayOption = function (opt, ejson) {
            if (opt[ejson.first] == undefined) {
                opt[ejson.first] = ejson.second;
            } else if (opt[ejson.first] instanceof Array) {
                opt[ejson.first] = opt[ejson.first].concat(ejson.second);
            } else {
                throw "echart type error";
            }
        };

        Chart.prototype.update = function (chartId) {
            try  {
                echarts.init($('#' + chartId)[0]).setOption(this.toJson().second[0]);
            } catch (e) {
                var k = 9;
                ++k;
            }
        };

        Chart.prototype.toJson = function () {
            var option = {
                tooltip: {
                    trigger: 'axis'
                },
                toolbox: {
                    trigger: 'axis',
                    /* formatter : "{b}<br/>{a} : {c} ��Ԫ<br/>{a1} : {c1} ��Ԫ", */
                    axisPointer: {
                        type: 'line'
                    }
                },
                calculable: false
            };
            var item;
            for (var i in this.elements) {
                item = this.elements[i].toJson();
                if (item.second instanceof Array) {
                    this.addArrayOption(option, item);
                } else {
                    option[item.first] = item.second;
                }
            }
            return new pair("series", [option]);
        };
        return Chart;
    })();
    ECharts.Chart = Chart;
})(ECharts || (ECharts = {}));
//# sourceMappingURL=echart.js.map
