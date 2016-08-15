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
            var seasonEnd = parseInt("" + (end.month - 1) / 3);
            var jdNames = ["一季度", "半年度", "三季度", "年度"];
            var seasons = [];
            for (var i = 0; i <= seasonEnd; ++i) {
                seasons.push({
                    data: {
                        id: i,
                        value: jdNames[i]
                    }
                });
            }
            var seasonNow = parseInt("" + (now.month - 1) / 3);
            $("#" + id).append("<div style='float:left' id='" + id + "year'></div>");
            $("#" + id).append("<div style='float:left' id='" + id + "season'></div>");
            this.yearSelector = new Util.UnitedSelector(dates, id + "year", [now.year - start.year]);
            this.seasonSelector = new Util.UnitedSelector(seasons, id + "season", [seasonNow]);
            $("#" + id + " select").css("width", "100px");
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
                    minWidth: 80,
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
                minWidth: 80,
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
