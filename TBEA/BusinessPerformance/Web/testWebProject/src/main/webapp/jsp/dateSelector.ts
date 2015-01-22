/// <reference path="util.ts" />
declare var $;
module Util {

    export interface Date {
        year: number;
        month?: number;
        day?: number;
    }

    export class DateSelector {

        private mStartDate: Date;
        private mEndDate: Date;
        private mCurDate: Date;
        private mCtrlId: String;
        private mDateCache: any = {};
        public constructor(start: Date, end: Date, divId: string) {
            this.mStartDate = start;
            this.mEndDate = end;
            this.mCtrlId = divId + "_date";
            this.mCurDate = <Date>{ year: this.mEndDate.year };
            this.mCurDate.month = this.mEndDate.month;
            this.mCurDate.day = this.mEndDate.day;

            if (!isExist(this.mStartDate.month)) {
                this.mStartDate.month = 1;
            }

            if (!isExist(this.mStartDate.day)) {
                this.mStartDate.day = 1;
            }

            $("#" + divId).append('<table id="' + this.mCtrlId + '" cellspacing="0" cellpadding="0"><tr></tr></table>');
            this.updateYear(this.mCurDate.year);
            this.updateMonth(this.mCurDate.month);
            this.updateDay(this.mCurDate.day);
        }

        public select(date: Date): void {
            var bChanged: boolean = false;
            var year = this.mCurDate.year;
            if (date.year != undefined && this.mStartDate.year <= date.year && this.mEndDate.year >= date.year) {
                this.updateYear(date.year);
                year = date.year;
                bChanged = true;
            }

            if (isExist(date.month) && isExist(this.mCurDate.month) && this.getStartMonth() <= date.month && this.getLatestMonth() >= date.month) {
                this.mCurDate.month = date.month;
                bChanged = true;
            }

            if (isExist(date.day) && isExist(this.mCurDate.day) && this.getStartDay() <= date.day && this.getLatestDay() >= date.day) {
                this.mCurDate.day = date.day;
                bChanged = true;
            }

            if (bChanged) {
                this.onYearSelected(year);
            }
        }

        private getStartDay(): number {
            var startDay: number = 1;
            if (this.mCurDate.year == this.mStartDate.year && this.mStartDate.month == this.mStartDate.month) {
                startDay = this.mStartDate.day;
            }
            return startDay;
        }

        private getStartMonth(): number {
            var startMon: number = 1;
            if (this.mCurDate.year == this.mStartDate.year) {
                startMon = this.mStartDate.month;
            }
            return startMon;
        }

        private getLatestDay() : number {
            var latestDay: number = this.getDaysInMonth(this.mCurDate.year, this.mCurDate.month);
            if (this.mCurDate.year == this.mEndDate.year && this.mCurDate.month == this.mEndDate.month) {
                latestDay = this.mEndDate.day;
            }
            return latestDay;
        }

        private getLatestMonth(): number {
            var latestMon: number = 12;
            if (this.mCurDate.year == this.mEndDate.year) {
                latestMon = this.mEndDate.month;
            }
            return latestMon;
        }

        private updateYear(selYear: number) {
            var yearSel = $("#" + this.mCtrlId + "year");
            if (yearSel.length == 0) {
                $("#" + this.mCtrlId + " tr").append('<td>' +
                    '<select id="' + this.mCtrlId + 'year"' +
                    'style="width: 90px; margin-right:5px;"></select>' +
                    '</td>');
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

            yearSel.change(() => {
                var newYear = yearSel.children('option:selected').val();
                if (this.mCurDate.year != newYear) {
                    this.onYearSelected(parseInt(newYear));
                }
            });
        }

        private updateMonth(selMonth : number) {
            if (isExist(this.mEndDate.month)) {
                var monthSel = $("#" + this.mCtrlId + "month");
                if (0 == monthSel.length) {
                    $("#" + this.mCtrlId + " tr").append('<td>' +
                        '<select id="' + this.mCtrlId + 'month"' +
                        'style="width: 90px; margin-right:5px;"></select>' +
                        '</td>');
                    monthSel = $("#" + this.mCtrlId + "month");
                } else {
                    monthSel.empty();
                }

                var endMonth = this.getLatestMonth();
                var startMonth = this.getStartMonth();

                for (var i = startMonth; i <= endMonth; ++i) {
                    if (selMonth == i) {
                        monthSel.append('<option value="' + selMonth + '" selected="selected">' + selMonth + '月</option>');
                    } else {
                        monthSel.append('<option value="' + i + '">' + i + '月</option>');
                    }
                }
               
                monthSel.change(() => {
                    var newMonth = monthSel.children('option:selected').val();
                    if (this.mCurDate.month != newMonth) {
                        this.onMonthSelected(parseInt(newMonth));
                    }
                });
            }
        }

        private getDaysInMonth(year: number, month: number): number {
            if (undefined != year && undefined != month) {
                var temp = new Date(year, month, 0);
                return temp.getDate();
            }
            return 0;
        }

        private updateDay(selDay: number) {
            if (isExist(this.mEndDate.day)) {
                var daySel = $("#" + this.mCtrlId + "day");
                if (0 == daySel.length) {
                    $("#" + this.mCtrlId + " tr").append('<td>' +
                        '<select id="' + this.mCtrlId + 'day"' +
                        'style="width: 90px; margin-right:5px;"></select>' +
                        '</td>');
                    daySel = $("#" + this.mCtrlId + "day");
                } else {
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


                daySel.change(() => {
                    var newDay = daySel.children('option:selected').val();
                    if (this.mCurDate.day != newDay) {
                        this.onDaySelected(parseInt(newDay));
                    }
                });
            }
        }


        private onYearSelected(year: number) {
            this.mCurDate.year = year;
            if (isExist(this.mCurDate.month)) {
                var curMonth: number = this.mCurDate.month;
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
        }

        private onMonthSelected(month: number) {
            this.mCurDate.month = month;
            if (isExist(this.mCurDate.day)) {
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
        }

        private onDaySelected(day: number) {
            this.mCurDate.day = day;
        }

        public getDate(): Date {
            return this.mCurDate;
        }

    }
}