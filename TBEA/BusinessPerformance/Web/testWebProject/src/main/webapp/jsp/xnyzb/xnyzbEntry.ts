///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicEntry.ts"/>

module xnyzb{
    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;
    class XnyzbEntryView extends framework.basic.EntryFrameView{
        mDStart:string;
        mDEnd:string;

        protected init(opt:any):void {
            this.mOpt = opt;

            this.mDStart=opt.date.year + "-" + opt.date.month + "-" + opt.date.day;
            $("#dstart").val(this.mDStart);
            $("#dstart").datepicker({
                //            numberOfMonths:1,//显示几个月
                //            showButtonPanel:true,//是否显示按钮面板
                dateFormat: 'yy-mm-dd',//日期格式
                //            clearText:"清除",//清除日期的按钮名称
                //            closeText:"关闭",//关闭选择框的按钮名称
                yearSuffix: '年', //年的后缀
                showMonthAfterYear: true,//是否把月放在年的后面
                defaultDate: this.mDStart,//默认日期
                //            minDate:'2011-03-05',//最小日期
                maxDate: 2019 + "-" + 1 + "-" + 1,//最大日期
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                onSelect: (selectedDate) => {//选择日期后执行的操作
                    var d: Date = new Date(selectedDate);
                    this.mDStart = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
                }
            });
            $("#dEnd").val(this.mDStart);
            this.mDEnd=this.mDStart;
            $("#dEnd").datepicker({
                //            numberOfMonths:1,//显示几个月
                //            showButtonPanel:true,//是否显示按钮面板
                dateFormat: 'yy-mm-dd',//日期格式
                //            clearText:"清除",//清除日期的按钮名称
                //            closeText:"关闭",//关闭选择框的按钮名称
                yearSuffix: '年', //年的后缀
                showMonthAfterYear: true,//是否把月放在年的后面
                defaultDate: this.mDStart,//默认日期
                //            minDate:'2011-03-05',//最小日期
                maxDate: 2019 + "-" + 1 + "-" + 1,//最大日期
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
                dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
                onSelect: (selectedDate) => {//选择日期后执行的操作
                    var d: Date = new Date(selectedDate);
                    this.mDEnd = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
                }
            });
            $("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;

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

        protected updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());


            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);

            this.mCurrentComp = this.mCompanySelector.getCompany();

            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);
            if (null != this.mCurrentComp){
                $("#headertitle")[0].innerHTML = this.mCompanySelector.getCompanyName() + " " + node.getData().value;
            }
            else{
                $("#headertitle")[0].innerHTML = node.getData().value;
            }

            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                dStart:this.mDStart,
                dEnd:this.mDEnd,
                compType:this.mCurrentComp
            });
        }
        submit() {
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SUBMIT,{
                dStart:this.mDStart,
                dEnd:this.mDEnd,
                compType:this.mCurrentComp
            });
        }

        save() {
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SAVE,{
                dStart:this.mDStart,
                dEnd:this.mDEnd,
                compType:this.mCurrentComp
            });
        }
    }
    let ins = new XnyzbEntryView();
}