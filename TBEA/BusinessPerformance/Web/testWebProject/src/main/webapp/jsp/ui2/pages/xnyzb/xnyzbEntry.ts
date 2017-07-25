///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicEntry.ts"/>

module xnyzb{
    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;
    class XnyzbEntryView extends framework.basic.EntryFrameView{
        //mDStart:string;
        //mDEnd:string;

        protected init(opt:any):void {
            this.mOpt = opt;

            //this.mDStart=opt.date.year + "-" + opt.date.month + "-" + opt.date.day;
            //$("#dstart").val(this.mDStart);
            //$("#dstart").datepicker({
            //    //            numberOfMonths:1,//显示几个月
            //    //            showButtonPanel:true,//是否显示按钮面板
            //    dateFormat: 'yy-mm-dd',//日期格式
            //    //            clearText:"清除",//清除日期的按钮名称
            //    //            closeText:"关闭",//关闭选择框的按钮名称
            //    yearSuffix: '年', //年的后缀
            //    showMonthAfterYear: true,//是否把月放在年的后面
            //    defaultDate: this.mDStart,//默认日期
            //    //            minDate:'2011-03-05',//最小日期
            //    maxDate: 2019 + "-" + 1 + "-" + 1,//最大日期
            //    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            //    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            //    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            //    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            //    onSelect: (selectedDate) => {//选择日期后执行的操作
            //        var d: Date = new Date(selectedDate.replace(/-/g, '/'));
            //        this.mDStart = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
            //    }
            //});
            //$("#dEnd").val(this.mDStart);
            //this.mDEnd=this.mDStart;
            //$("#dEnd").datepicker({
            //    //            numberOfMonths:1,//显示几个月
            //    //            showButtonPanel:true,//是否显示按钮面板
            //    dateFormat: 'yy-mm-dd',//日期格式
            //    //            clearText:"清除",//清除日期的按钮名称
            //    //            closeText:"关闭",//关闭选择框的按钮名称
            //    yearSuffix: '年', //年的后缀
            //    showMonthAfterYear: true,//是否把月放在年的后面
            //    defaultDate: this.mDStart,//默认日期
            //    //            minDate:'2011-03-05',//最小日期
            //    maxDate: 2019 + "-" + 1 + "-" + 1,//最大日期
            //    monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            //    dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            //    dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            //    dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            //    onSelect: (selectedDate) => {//选择日期后执行的操作
            //        var d: Date = new Date(selectedDate.replace(/-/g, '/'));
            //        this.mDEnd = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
            //    }
            //});
            //$("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;

            var start : any= {
                format: 'YYYY年MM月DD日',
                isinitVal:true,
                isTime: false,
                ishmsVal:false,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(Util.addYear(this.mOpt.date, -3)) + " 00:00:00",
                maxDate: Util.date2Str(this.mOpt.date) + " 00:00:00",
                choosefun: (elem, val, date) =>{
                    setTimeout(()=>{
                        end.minDate = Util.date2Str(this.getStartDate()) + " 00:00:00";
                        endDates();
                    },0);
                }
            };
            var end : any= {
                format: 'YYYY年MM月DD日',
                isinitVal:true,
                isTime: false,
                ishmsVal:false,
                isClear: false,
                isToday: false,
                minDate: Util.date2Str(this.mOpt.date) + " 00:00:00",
                maxDate: Util.date2Str(this.mOpt.date) + " 00:00:00",
                choosefun: (elem, val, date)=>{
                    start.maxDate = Util.date2Str(this.getEndDate()) + " 00:00:00"; //将结束日的初始值设定为开始日的最大日期
                }
            };

            //这里是日期联动的关键
            function endDates() {
                //将结束日期的事件改成 false 即可
                end.insTrigger = false;
                $("#inpend").jePopup();
            }

            $('#grid-date').jeDate(start).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top")
                .addClass("day");
            $('#grid-date-end').jeDate(end).removeCss("height")
                .removeCss("padding")
                .removeCss("margin-top")
                .addClass("day");

            $(window).resize(()=>{
                router.to(this.mCurrentPlugin).send(FrameEvent.FE_ADJUST_SZIE);
            });

            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }

            this.mCompanySelector.change((selector:any, depth:number) => {
                this.updateTypeSelector();
            });
            this.mCurrentDate = {year:2010, month:1, day:1};
            this.updateTypeSelector();
            this.updateUI();
        }

        protected getStartDate():Util.Date {
            let curDate = $('#grid-date').getDate();
            let ret = {
                year : curDate.getFullYear(),
                month : curDate.getMonth() + 1,
                day : curDate.getDate()
            };
            return ret;
        }

        protected getEndDate():Util.Date {
            let curDate = $('#grid-date-end').getDate();
            let ret = {
                year : curDate.getFullYear(),
                month : curDate.getMonth() + 1,
                day : curDate.getDate()
            };
            return ret;
        }

        protected updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());


            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);

            this.mCurrentComp = this.mCompanySelector.getCompany();

            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);
            //if (null != this.mCurrentComp){
            //    $("#headertitle")[0].innerHTML = this.mCompanySelector.getCompanyName() + " " + node.getData().value;
            //}
            //else{
            //    $("#headertitle")[0].innerHTML = node.getData().value;
            //}

            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                dStart:Util.date2Str(this.getStartDate()),
                dEnd:Util.date2Str(this.getEndDate()),
                compType:this.mCurrentComp
            });
        }
        submit() {
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SUBMIT,{
                dStart:Util.date2Str(this.getStartDate()),
                dEnd:Util.date2Str(this.getEndDate()),
                compType:this.mCurrentComp
            });
        }

        //save() {
        //    router.to(this.mCurrentPlugin).send(FrameEvent.FE_SAVE,{
        //        dStart:Util.date2Str(this.getStartDate()),
        //        dEnd:Util.date2Str(this.getEndDate()),
        //        compType:this.mCurrentComp
        //    });
        //}
    }
    let ins = new XnyzbEntryView();
}