var Util;
(function (Util) {
    var DateSelector = (function () {
        function DateSelector(start, end, divId) {
            this.mDateCache = {};
            this.mStartDate = start;
            this.mEndDate = end;
            this.mCtrlId = divId + "_date";
            this.mCurDate = { year: this.mEndDate.year };
            this.mCurDate.month = this.mEndDate.month;
            this.mCurDate.day = this.mEndDate.day;
            if (!Util.isExist(this.mStartDate.month)) {
                this.mStartDate.month = 1;
            }
            if (!Util.isExist(this.mStartDate.day)) {
                this.mStartDate.day = 1;
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
            if (Util.isExist(date.month) && Util.isExist(this.mCurDate.month) && this.getStartMonth() <= date.month && this.getLatestMonth() >= date.month) {
                this.mCurDate.month = date.month;
                bChanged = true;
            }
            if (Util.isExist(date.day) && Util.isExist(this.mCurDate.day) && this.getStartDay() <= date.day && this.getLatestDay() >= date.day) {
                this.mCurDate.day = date.day;
                bChanged = true;
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
                $("#" + this.mCtrlId + " tr").append('<td>' + '<select id="' + this.mCtrlId + 'year"' + 'style="width: 90px; margin-right:5px;"></select>' + '</td>');
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
        };
        DateSelector.prototype.updateMonth = function (selMonth) {
            var _this = this;
            if (Util.isExist(this.mEndDate.month)) {
                var monthSel = $("#" + this.mCtrlId + "month");
                if (0 == monthSel.length) {
                    $("#" + this.mCtrlId + " tr").append('<td>' + '<select id="' + this.mCtrlId + 'month"' + 'style="width: 90px; margin-right:5px;"></select>' + '</td>');
                    monthSel = $("#" + this.mCtrlId + "month");
                }
                else {
                    monthSel.empty();
                }
                var endMonth = this.getLatestMonth();
                var startMonth = this.getStartMonth();
                for (var i = startMonth; i <= endMonth; ++i) {
                    if (selMonth == i) {
                        monthSel.append('<option value="' + selMonth + '" selected="selected">' + selMonth + '月</option>');
                    }
                    else {
                        monthSel.append('<option value="' + i + '">' + i + '月</option>');
                    }
                }
                monthSel.change(function () {
                    var newMonth = monthSel.children('option:selected').val();
                    if (_this.mCurDate.month != newMonth) {
                        _this.onMonthSelected(parseInt(newMonth));
                    }
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
            if (Util.isExist(this.mEndDate.day)) {
                var daySel = $("#" + this.mCtrlId + "day");
                if (0 == daySel.length) {
                    $("#" + this.mCtrlId + " tr").append('<td>' + '<select id="' + this.mCtrlId + 'day"' + 'style="width: 90px; margin-right:5px;"></select>' + '</td>');
                    daySel = $("#" + this.mCtrlId + "day");
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
        };
        DateSelector.prototype.onDaySelected = function (day) {
            this.mCurDate.day = day;
        };
        DateSelector.prototype.getDate = function () {
            return this.mCurDate;
        };
        return DateSelector;
    })();
    Util.DateSelector = DateSelector;
})(Util || (Util = {}));
