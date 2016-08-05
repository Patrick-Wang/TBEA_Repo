///<reference path="../unitedSelector.ts"/>
///<reference path="../dateSelector.ts"/>
///<reference path="../../js/jquery/jquery.d.ts"/>
/**
 * Created by Floyd on 2016/7/19.
 */
var Util;
(function (Util) {
    var SeasonAccSelector = (function () {
        function SeasonAccSelector(start, end, now, id) {
            var _this = this;
            var dates = this.getYears(start, end);
            var seasons = [{
                    data: {
                        id: 0, value: "一季度"
                    }
                }, {
                    data: {
                        id: 1, value: "半年度"
                    }
                }, {
                    data: {
                        id: 2, value: "三季度"
                    }
                }, {
                    data: {
                        id: 3, value: "年度"
                    }
                }];
            var seasonNow = parseInt("" + (now.month - 1) / 3);
            $("#" + id).append("<div id='" + id + "year'></div>");
            $("#" + id).append("<div style='margin-left:5px' id='" + id + "season'></div>");
            this.yearSelector = new Util.UnitedSelector(dates, id + "year", [now.year - start.year]);
            this.seasonSelector = new Util.UnitedSelector(seasons, id + "season", [seasonNow]);
            this.yearSelector.change(function (sel, depth) {
                sel = _this.yearSelector.getSelect();
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
            var sel = this.yearSelector.getSelect();
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
            this.seasonSelector.change(function (sel, depth) {
                sel = _this.seasonSelector.getSelect();
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
            sel = this.seasonSelector.getSelect();
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
        SeasonAccSelector.prototype.getDate = function () {
            var selNodes = this.yearSelector.getNodes();
            var seasonNodes = this.seasonSelector.getNodes();
            return {
                year: selNodes[0].data.id,
                month: seasonNodes[0].data.id * 3 + 3
            };
        };
        SeasonAccSelector.prototype.getYears = function (start, end) {
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
        return SeasonAccSelector;
    })();
    Util.SeasonAccSelector = SeasonAccSelector;
})(Util || (Util = {}));
