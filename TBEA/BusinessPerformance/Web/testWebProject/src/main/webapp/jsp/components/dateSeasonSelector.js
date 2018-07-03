var Util;
(function (Util) {
    var months = [{
            data: {
                id: 0,
                value: "首月"
            }
        }, {
            data: {
                id: 1,
                value: "次月"
            }
        }, {
            data: {
                id: 2,
                value: "末月"
            }
        }];
    var seasons = [{
            data: {
                id: 0,
                value: "第一季度"
            },
            subNodes: months
        }, {
            data: {
                id: 1,
                value: "第二季度"
            },
            subNodes: months
        }, {
            data: {
                id: 2,
                value: "第三季度"
            },
            subNodes: months
        }, {
            data: {
                id: 3,
                value: "第四季度"
            },
            subNodes: months
        }];
    var DateSeasonSelector = /** @class */ (function () {
        function DateSeasonSelector(start, end, now, id) {
            var _this = this;
            var seasonStart = parseInt("" + (start.month - 1) / 3);
            var monthStart = (start.month - 1) % 3;
            var seasonEnd = parseInt("" + (end.month - 1) / 3);
            var monthEnd = (end.month - 1) % 3;
            var dates = this.getYears(start, end);
            if (dates.length == 1) {
                dates[0].subNodes = [];
                var sStartNode = $.extend({}, seasons[seasonStart]);
                sStartNode.subNodes = [];
                if (seasonStart == seasonEnd) {
                    dates[0].subNodes.push(sStartNode);
                    for (var i = monthStart; i <= monthEnd; ++i) {
                        dates[0].subNodes[0].subNodes.push($.extend({}, months[i]));
                    }
                }
                else {
                    dates[0].subNodes.push(this.getStartSeasons(seasonStart, monthStart));
                    for (var i = seasonStart + 1; i < seasonEnd; ++i) {
                        var season = seasons[i];
                        dates[0].subNodes.push(season);
                    }
                    dates[0].subNodes.push(this.getEndSeasons(seasonEnd, monthEnd));
                }
            }
            else {
                dates[0].subNodes = [];
                dates[0].subNodes.push(this.getStartSeasons(seasonStart, monthStart));
                for (var i = seasonStart; i < 4; ++i) {
                    dates[0].subNodes.push(seasons[i]);
                }
                for (var i = 1; i < dates.length - 1; ++i) {
                    dates[i].subNodes = seasons;
                }
                dates[dates.length - 1].subNodes = [];
                for (var i = 0; i < seasonEnd; ++i) {
                    dates[dates.length - 1].subNodes.push(seasons[i]);
                }
                dates[dates.length - 1].subNodes.push(this.getEndSeasons(seasonEnd, monthEnd));
            }
            var seasonNow = parseInt("" + (now.month - 1) / 3);
            var monthNow = (now.month - 1) % 3;
            var nowYearIndex = now.year - start.year;
            var nowMonthIndex;
            var nowSeasonIndex;
            for (var sub in dates[nowYearIndex].subNodes) {
                if (dates[nowYearIndex].subNodes[sub].data.id == seasonNow) {
                    nowSeasonIndex = sub;
                    break;
                }
            }
            for (var sub in dates[nowYearIndex].subNodes[nowSeasonIndex].subNodes) {
                if (dates[nowYearIndex].subNodes[nowSeasonIndex].subNodes[sub].data.id == monthNow) {
                    nowMonthIndex = sub;
                    break;
                }
            }
            this.unitedSelector = new Util.UnitedSelector(dates, id, [nowYearIndex, nowSeasonIndex, nowMonthIndex]);
            this.unitedSelector.change(function (sel, depth) {
                sel = _this.unitedSelector.getSelect();
                $(sel).multiselect({
                    multiple: false,
                    header: false,
                    minWidth: 80,
                    height: '100%',
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                }).css("text-align:center");
                $(sel[1]).multiselect({
                    multiple: false,
                    header: false,
                    minWidth: 100,
                    height: '100%',
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                }).css("text-align:center");
            });
            var sel = this.unitedSelector.getSelect();
            $(sel).multiselect({
                multiple: false,
                header: false,
                minWidth: 80,
                height: '100%',
                // noneSelectedText: "请选择月份",
                selectedList: 1
            }).css("text-align:center");
            $(sel[1]).multiselect({
                multiple: false,
                header: false,
                minWidth: 100,
                height: '100%',
                // noneSelectedText: "请选择月份",
                selectedList: 1
            }).css("text-align:center");
        }
        DateSeasonSelector.prototype.getDate = function () {
            var selNodes = this.unitedSelector.getNodes();
            return {
                year: selNodes[0].data.id,
                month: selNodes[1].data.id * 3 + selNodes[2].data.id + 1
            };
        };
        DateSeasonSelector.prototype.getYears = function (start, end) {
            var dates = [];
            for (var i = start.year; i <= end.year; ++i) {
                var year = {
                    data: {
                        id: i,
                        value: i + "年"
                    }
                };
                dates.push(year);
            }
            return dates;
        };
        DateSeasonSelector.prototype.getStartSeasons = function (seasonStart, monthStart) {
            var sStartNode = $.extend({}, seasons[seasonStart]);
            sStartNode.subNodes = [];
            for (var i = monthStart; i < 3; ++i) {
                sStartNode.subNodes.push($.extend({}, months[i]));
            }
            return sStartNode;
        };
        DateSeasonSelector.prototype.getEndSeasons = function (seasonEnd, monthEnd) {
            var sEndNode = $.extend({}, seasons[seasonEnd]);
            sEndNode.subNodes = [];
            for (var i = 0; i <= monthEnd; ++i) {
                sEndNode.subNodes.push($.extend({}, months[i]));
            }
            return sEndNode;
        };
        return DateSeasonSelector;
    }());
    Util.DateSeasonSelector = DateSeasonSelector;
})(Util || (Util = {}));
