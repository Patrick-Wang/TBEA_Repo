/// <reference path="util.ts" />
var Util;
(function (Util) {
    function uDate2sDate(date) {
        var sd = new Date(Date.parse(date.year + "/" + date.month + "/" + date.day));
        return sd;
    }
    Util.uDate2sDate = uDate2sDate;
    function toDate(dt) {
        var curDate = new Date(Date.parse(dt.replace(/-/g, '/')));
        return {
            year: curDate.getFullYear(),
            month: curDate.getMonth() + 1,
            day: curDate.getDate()
        };
    }
    Util.toDate = toDate;
    function parseDate(year, month, day) {
        var date = {
            year: year == undefined || year.length == 0 ? undefined : parseInt(year),
            month: month == undefined || month.length == 0 ? undefined : parseInt(month),
            day: day == undefined || day.length == 0 ? undefined : parseInt(day)
        };
        if (date.year != undefined || date.month != undefined || date.day != undefined) {
            return date;
        }
        return undefined;
    }
    Util.parseDate = parseDate;
    function addYear(d, count) {
        return { year: d.year + count, month: d.month, day: d.day };
    }
    Util.addYear = addYear;
    function addMonth(d, count) {
        var monthCount = parseInt(d.month + '') + parseInt(d.year + '') * 12 + count;
        var year = parseInt('' + monthCount / 12) + (monthCount % 12 == 0 ? -1 : 0);
        var month = monthCount % 12 == 0 ? 12 : monthCount % 12;
        return { year: year, month: month, day: d.day };
    }
    Util.addMonth = addMonth;
    function addDay(dCur, count) {
        var d = new Date(Date.parse(dCur.year + "/" + dCur.month + "/" + dCur.day));
        d.setTime(d.getTime() + count * 24 * 60 * 60 * 1000);
        return {
            year: d.getFullYear(),
            month: d.getMonth() + 1,
            day: d.getDate()
        };
    }
    Util.addDay = addDay;
    function date2Str(dCur) {
        return dCur.year + "-" + dCur.month + "-" + (dCur.day == undefined ? 1 : dCur.day);
    }
    Util.date2Str = date2Str;
    var DateSelector = (function () {
        function DateSelector(start, end, divId, asSeason) {
            if (asSeason === void 0) { asSeason = false; }
            this.mDateCache = {};
            this.mStartDate = start;
            this.mEndDate = end;
            this.mCtrlId = divId + "_date";
            if (asSeason && Util.isExist(this.mEndDate.month)) {
                this.mAsSeasion = true;
                this.mEndDate.day = undefined;
                this.mStartDate.day = undefined;
                this.mEndDate.month = 3 * (parseInt((this.mEndDate.month - 1) / 3 + '') + 1);
                // this.mEndDate = addMonth(this.mEndDate, -((this.mEndDate.month % 3));
            }
            else {
                this.mAsSeasion = false;
            }
            this.mCurDate = {
                year: this.mEndDate.year,
                month: this.mEndDate.month,
                day: this.mEndDate.day
            };
            if (!Util.isExist(this.mStartDate.month)) {
                this.mStartDate.month = 1;
            }
            if (!Util.isExist(this.mStartDate.day)) {
                this.mStartDate.day = 1;
            }
            if (this.mAsSeasion) {
                this.mStartDate.month = 3 * (parseInt((this.mStartDate.month - 1) / 3 + '') + 1);
                //addMonth(this.mStartDate, -(this.mStartDate.month % 3));
            }
            $("#" + divId).append('<table id="' + this.mCtrlId + '" cellspacing="0" cellpadding="0"><tr></tr></table>');
            this.updateYear(this.mCurDate.year);
            this.updateMonth(this.mCurDate.month);
            this.updateDay(this.mCurDate.day);
        }
        DateSelector.prototype.select = function (date) {
            var bChanged = false;
            var year = this.mCurDate.year;
            if (date.year != undefined && this.mStartDate.year <= date.year && this.mEndDate.year >= date.year) {
                this.updateYear(date.year);
                year = date.year;
                bChanged = true;
            }
            if (Util.isExist(date.month) && this.getStartMonth() <= date.month && this.getLatestMonth() >= date.month) {
                this.mCurDate.month = date.month;
                bChanged = true;
            }
            if (!Util.isExist(date.month)) {
                this.mCurDate.month = undefined;
            }
            if (Util.isExist(date.day) && this.getStartDay() <= date.day && this.getLatestDay() >= date.day) {
                this.mCurDate.day = date.day;
                bChanged = true;
            }
            if (!Util.isExist(date.day)) {
                this.mCurDate.day = undefined;
            }
            if (bChanged) {
                this.onYearSelected(year);
            }
        };
        DateSelector.prototype.getStartDay = function () {
            var startDay = 1;
            if (this.mCurDate.year == this.mStartDate.year && this.mStartDate.month == this.mStartDate.month) {
                startDay = this.mStartDate.day;
            }
            return startDay;
        };
        DateSelector.prototype.getStartMonth = function () {
            var startMon = 1;
            if (this.mCurDate.year == this.mStartDate.year) {
                startMon = this.mStartDate.month;
            }
            return startMon;
        };
        DateSelector.prototype.monthDays = function () {
            return this.getDaysInMonth(this.mCurDate.year, this.mCurDate.month);
        };
        DateSelector.prototype.toString = function () {
            var date = this.getDate();
            var ds = date.year;
            if (date.month != undefined) {
                ds += "-" + date.month;
            }
            if (date.day != undefined) {
                ds += "-" + date.day;
            }
            return ds;
        };
        DateSelector.prototype.getLatestDay = function () {
            var latestDay = this.getDaysInMonth(this.mCurDate.year, this.mCurDate.month);
            if (this.mCurDate.year == this.mEndDate.year && this.mCurDate.month == this.mEndDate.month) {
                latestDay = this.mEndDate.day;
            }
            return latestDay;
        };
        DateSelector.prototype.getLatestMonth = function () {
            var latestMon = 12;
            if (this.mCurDate.year == this.mEndDate.year) {
                latestMon = this.mEndDate.month;
            }
            return latestMon;
        };
        DateSelector.prototype.updateYear = function (selYear) {
            var _this = this;
            var yearSel = $("#" + this.mCtrlId + "year");
            if (yearSel.length == 0) {
                $("#" + this.mCtrlId + " tr").append('<td>' +
                    '<select id="' + this.mCtrlId + 'year"' +
                    'style="width: 100px;"></select>' +
                    '</td><td><div style="width:5px;"></div></td>');
                yearSel = $("#" + this.mCtrlId + "year");
            }
            else {
                yearSel.empty();
            }
            for (var i = this.mStartDate.year; i <= this.mEndDate.year; ++i) {
                if (selYear == i) {
                    yearSel.append('<option value="' + selYear + '" selected="selected">' + selYear + '年</option>');
                }
                else {
                    yearSel.append('<option value="' + i + '">' + i + '年</option>');
                }
            }
            yearSel.change(function () {
                var newYear = yearSel.children('option:selected').val();
                if (_this.mCurDate.year != newYear) {
                    _this.onYearSelected(parseInt(newYear));
                }
            });
            yearSel.multiselect({
                multiple: false,
                header: false,
                minWidth: 80,
                height: '100%',
                // noneSelectedText: "请选择月份",
                selectedList: 1
            });
        };
        DateSelector.prototype.updateMonth = function (selMonth) {
            var _this = this;
            var monthSel = $("#" + this.mCtrlId + "month");
            if (Util.isExist(this.mEndDate.month)) {
                if (0 == monthSel.length) {
                    $("#" + this.mCtrlId + " tr").append('<td>' +
                        '<select id="' + this.mCtrlId + 'month"' +
                        'style="width: 100px;"></select>' +
                        '</td><td><div style="width:5px;"></div></td>');
                    monthSel = $("#" + this.mCtrlId + "month");
                }
                else {
                    monthSel.empty();
                }
                var endMonth = this.getLatestMonth();
                var startMonth = this.getStartMonth();
                var seasonCount = 0;
                if (!this.mAsSeasion) {
                    for (var i = startMonth; i <= endMonth; ++i) {
                        if (selMonth == i) {
                            monthSel.append('<option value="' + selMonth + '" selected="selected">' + selMonth + '月</option>');
                        }
                        else {
                            monthSel.append('<option value="' + i + '">' + i + '月</option>');
                        }
                    }
                }
                else {
                    for (var i = startMonth; i <= endMonth; ++i) {
                        if (i % 3 == 0) {
                            ++seasonCount;
                            if (selMonth == i) {
                                monthSel.append('<option value="' + selMonth + '" selected="selected">' + i / 3 + '季度</option>');
                            }
                            else {
                                monthSel.append('<option value="' + i + '">' + i / 3 + '季度</option>');
                            }
                        }
                    }
                }
                monthSel.change(function () {
                    var newMonth = monthSel.children('option:selected').val();
                    if (_this.mCurDate.month != newMonth) {
                        _this.onMonthSelected(parseInt(newMonth));
                    }
                });
                monthSel.multiselect({
                    multiple: false,
                    header: false,
                    minWidth: 80,
                    height: '100%',
                    selectedList: 1
                });
            }
        };
        DateSelector.prototype.getDaysInMonth = function (year, month) {
            if (undefined != year && undefined != month) {
                var temp = new Date(year, month, 0);
                return temp.getDate();
            }
            return 0;
        };
        DateSelector.prototype.updateDay = function (selDay) {
            var _this = this;
            var daySel = $("#" + this.mCtrlId + "day");
            if (Util.isExist(this.mEndDate.day)) {
                if (0 == daySel.length) {
                    $("#" + this.mCtrlId + " tr").append('<td>' +
                        '<select id="' + this.mCtrlId + 'day"' +
                        'style="width: 100px;"></select>' +
                        '</td><td><div style="width:5px;"></div></td>');
                    daySel = $("#" + this.mCtrlId + "day");
                    daySel.multiselect({
                        multiple: false,
                        header: "Select an option",
                        height: (endDay - startDay + 1) > 15 ? (15 * 28) + "px" : '100%',
                        noneSelectedText: "Select an Option",
                        selectedList: 1
                    });
                }
                else {
                    daySel.empty();
                }
                var endDay = this.getLatestDay();
                var startDay = this.getStartDay();
                for (var i = startDay; i <= endDay; ++i) {
                    if (selDay == i) {
                        daySel.append('<option value="' + selDay + '" selected="selected">' + selDay + '日</option>');
                    }
                    else {
                        daySel.append('<option value="' + i + '">' + i + '日</option>');
                    }
                }
                daySel.change(function () {
                    var newDay = daySel.children('option:selected').val();
                    if (_this.mCurDate.day != newDay) {
                        _this.onDaySelected(parseInt(newDay));
                    }
                });
                daySel.multiselect({
                    multiple: false,
                    header: false,
                    minWidth: 80,
                    height: (endDay - startDay + 1) > 15 ? (15 * 28) + "px" : '100%',
                    selectedList: 1
                });
            }
        };
        DateSelector.prototype.onYearSelected = function (year) {
            this.mCurDate.year = year;
            if (Util.isExist(this.mCurDate.month)) {
                var curMonth = this.mCurDate.month;
                var latestMonth = this.getLatestMonth();
                var startMonth = this.getStartMonth();
                if (latestMonth < this.mCurDate.month) {
                    curMonth = latestMonth;
                }
                if (startMonth > this.mCurDate.month) {
                    curMonth = startMonth;
                }
                this.updateMonth(curMonth);
                this.onMonthSelected(curMonth);
            }
            else {
                var monthSel = $("#" + this.mCtrlId + "month");
                monthSel.parent().empty();
                var daySel = $("#" + this.mCtrlId + "day");
                daySel.parent().empty();
            }
        };
        DateSelector.prototype.onMonthSelected = function (month) {
            this.mCurDate.month = month;
            if (Util.isExist(this.mCurDate.day)) {
                var curDay = this.mCurDate.day;
                var latestDay = this.getLatestDay();
                var startDay = this.getStartDay();
                if (latestDay < this.mCurDate.day) {
                    curDay = latestDay;
                }
                if (startDay > this.mCurDate.day) {
                    curDay = startDay;
                }
                this.updateDay(curDay);
                this.onDaySelected(curDay);
            }
            else {
            }
        };
        DateSelector.prototype.onDaySelected = function (day) {
            this.mCurDate.day = day;
        };
        DateSelector.prototype.toInt = function (val) {
            if (typeof (val) == 'string') {
                return parseInt(val);
            }
            return val;
        };
        DateSelector.prototype.getDate = function () {
            this.mCurDate.year = this.toInt(this.mCurDate.year);
            this.mCurDate.month = this.toInt(this.mCurDate.month);
            this.mCurDate.day = this.toInt(this.mCurDate.day);
            return { year: this.mCurDate.year, month: this.mCurDate.month, day: this.mCurDate.day };
        };
        return DateSelector;
    }());
    Util.DateSelector = DateSelector;
})(Util || (Util = {}));
