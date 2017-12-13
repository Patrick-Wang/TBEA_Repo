///<reference path="../dateSelector.ts"/>
///<reference path="dateSeasonSelector.ts"/>
///<reference path="SeasonAccSelector.ts"/>
var Util;
(function (Util) {
    var DateSelectorProxy = (function () {
        function DateSelectorProxy(divId, dtStart, dtEnd, dtNow, asSeason, asSeasonAcc, jdName) {
            if (asSeason === void 0) { asSeason = false; }
            if (asSeasonAcc === void 0) { asSeasonAcc = false; }
            var _this = this;
            this.curDate = dtNow;
            if (asSeasonAcc) {
                this.seasonAccSelect = new Util.SeasonAccSelector(dtStart, dtEnd, dtNow, divId, jdName);
            }
            else if (asSeason) {
                this.seasonSelect = new Util.DateSeasonSelector(dtStart, dtEnd, dtNow, divId);
            }
            else if (this.curDate.day != undefined) {
                var strDate = this.curDate.year + "-" + this.curDate.month + "-" + this.curDate.day;
                var maxDate = dtEnd.year + "-" + dtEnd.month + "-" + dtEnd.day;
                var minDate = dtStart.year + "-" + dtStart.month + "-" + dtStart.day;
                $("#" + divId).append("<input id='" + divId + "dtInput' style='width: 100px'>");
                this.datePicker = $("#" + divId + " #" + divId + "dtInput");
                this.datePicker.val(strDate);
                this.datePicker.datepicker({
                    //            numberOfMonths:1,//显示几个月
                    showButtonPanel: true,
                    dateFormat: 'yy-mm-dd',
                    //            clearText:"清除",//清除日期的按钮名称
                    //            closeText:"关闭",//关闭选择框的按钮名称
                    yearSuffix: '年',
                    showMonthAfterYear: true,
                    defaultDate: strDate,
                    minDate: minDate,
                    maxDate: maxDate,
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                    onSelect: function (selectedDate) {
                        var d = new Date(selectedDate.replace(/-/g, '/'));
                        _this.curDate = {
                            year: d.getFullYear(),
                            month: (d.getMonth() + 1),
                            day: d.getDate()
                        };
                        _this.triggerOnChange();
                    }
                });
            }
            else {
                this.dateSelect = new Util.DateSelector(dtStart, dtEnd, divId);
                this.dateSelect.select(dtNow);
            }
        }
        DateSelectorProxy.prototype.triggerOnChange = function () {
            var _this = this;
            if (this.changeFn != undefined) {
                setTimeout(function () {
                    _this.changeFn(_this.curDate);
                }, 1);
            }
        };
        DateSelectorProxy.prototype.change = function (changeFn) {
            this.changeFn = changeFn;
        };
        DateSelectorProxy.prototype.setDate = function (date) {
            if (this.dateSelect != undefined) {
            }
            else if (this.seasonSelect != undefined) {
            }
            else if (this.seasonAccSelect != undefined) {
            }
            else {
                this.datePicker.datepicker('setDate', Util.uDate2sDate(date));
                this.curDate = date;
                this.triggerOnChange();
            }
        };
        DateSelectorProxy.prototype.getDate = function () {
            if (this.dateSelect != undefined) {
                this.curDate = this.dateSelect.getDate();
            }
            else if (this.seasonSelect != undefined) {
                this.curDate = this.seasonSelect.getDate();
            }
            else if (this.seasonAccSelect != undefined) {
                this.curDate = this.seasonAccSelect.getDate();
            }
            return this.curDate;
        };
        return DateSelectorProxy;
    }());
    Util.DateSelectorProxy = DateSelectorProxy;
})(Util || (Util = {}));
