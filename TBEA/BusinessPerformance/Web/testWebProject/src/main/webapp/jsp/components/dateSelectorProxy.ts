///<reference path="../dateSelector.ts"/>
///<reference path="dateSeasonSelector.ts"/>

module Util{
    export class DateSelectorProxy{
        curDate : Util.Date;
        dateSelect : Util.DateSelector;
        seasonSelect : Util.DateSeasonSelector;
        public constructor(divId:string,
                           dtStart : Util.Date,
                           dtEnd : Util.Date,
                           dtNow : Util.Date,
                           asSeason:boolean = false){
            this.curDate = dtNow;
            if (asSeason){
                this.seasonSelect = new Util.DateSeasonSelector(dtStart, dtEnd, dtNow, divId);
            } else if (this.curDate.day != undefined){
                let strDate = this.curDate.year + "-" + this.curDate.month + "-" + this.curDate.day;
                let maxDate = dtEnd.year + "-" + dtEnd.month + "-" + dtEnd.day;
                let minDate = dtStart.year + "-" + dtStart.month + "-" + dtStart.day;
                $("#" + divId).append("<input id='dtInput' style='width: 100px'>");
                $("#" + divId + " #dtInput").val(strDate);
                $("#" + divId + " #dtInput").datepicker({
                    //            numberOfMonths:1,//显示几个月
                    showButtonPanel:true,//是否显示按钮面板
                    dateFormat: 'yy-mm-dd',//日期格式
                    //            clearText:"清除",//清除日期的按钮名称
                    //            closeText:"关闭",//关闭选择框的按钮名称
                    yearSuffix: '年', //年的后缀
                    showMonthAfterYear: true,//是否把月放在年的后面
                    defaultDate: strDate,//默认日期
                    minDate: minDate,//最小日期
                    maxDate: maxDate,//最大日期
                    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                    onSelect: (selectedDate) => {//选择日期后执行的操作
                        let d : any = new Date(selectedDate);
                        this.curDate = {
                            year: d.getFullYear(),
                            month: (d.getMonth() + 1),
                            day: d.getDate()
                        }
                    }
                });
            }else{
                this.dateSelect = new Util.DateSelector(dtStart, dtEnd, divId);
            }
        }

        public getDate(): Util.Date{
            if (this.dateSelect != undefined){
                this.curDate = this.dateSelect.getDate();
            }else if (this.seasonSelect != undefined){
                this.curDate = this.seasonSelect.getDate();
            }
            return this.curDate;
        }

    }
}