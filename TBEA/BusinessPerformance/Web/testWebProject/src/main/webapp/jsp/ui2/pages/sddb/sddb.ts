///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="../components/dateSelectorProxy.ts"/>
///<reference path="../unitedSelector.ts"/>

module sddb{

    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;

    export module SDDBEvent {
        export let FE_SHOWTIME : number = FrameEvent.lastEvent();
        export let FE_HIDETIME : number = FrameEvent.lastEvent();
    }


    class SddbShowView extends framework.basic.ShowFrameView{
        //mDStartSel : Util.DateSelectorProxy;
        //mDEndSel : Util.DateSelectorProxy;
        unitedSelector : Util.UnitedSelector;
        unitedSelector0 : Util.UnitedSelector;


        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case SDDBEvent.FE_SHOWTIME:
                    if (this.mOpt.date != undefined) {
                        $("#dstart").parent().css("display", "");
                        $("#dEnd").parent().css("display", "");
                        //$("#dstartLabel").css("display", "");
                        //$("#dEndLabel").css("display", "");
                    }
                    break;
                case SDDBEvent.FE_HIDETIME: if (this.mOpt.date != undefined) {
                        $("#dstart").css("display", "none");
                        $("#dEnd").css("display", "none");
                        //$("#dstartLabel").css("display", "none");
                        //$("#dEndLabel").css("display", "none");
                    }
                    break;
            }
            return super.onEvent(e);
        }

        private renderItemSelector(itemId:string):void{
            let sels = $("#" + itemId + " select");
            for (let i = 0; i < sels.length; ++i){
                if (sels.length == i + 1 && sels.length > 1){
                    this.renderSelector2(sels[i])
                }else{
                    let opts = $("#" + itemId + " select:eq(" + i + ") option");
                    let items = [];
                    for (let j = 0; j < opts.length; ++j){
                        items.push(opts[j].text);
                    }
                    var hasScroll = $(sels).find("option").length > 10;
                    var extendWidth = hasScroll ? 27 : 0;
                    let width = Util.getUIWidth(items);
                    //$(sels[i]) .multiselect({
                    //        multiple: false,
                    //        header: false,
                    //        minWidth: width < 80 ? 80 + extendWidth : width + 20 + extendWidth,
                    //        height: hasScroll ? 270 : '100%',
                    //        // noneSelectedText: "请选择月份",
                    //        selectedList: 1
                    //    })
                    //    .css("padding", "2px 0 2px 4px")
                    //    .css("text-align", "left")
                    //    .css("font-size", "12px");
                }
            }
        }

        private renderSelector2(sels:any):void{
            let opts = $(sels).find("option");
            let items = [];
            for (let j = 0; j < opts.length; ++j){
                items.push(opts[j].text);
            }
            $(sels)
                //.css("width", Math.max(Util.getUIWidth(items), 80))
                //.css("height", "20px")
                .select2({
                    language: "zh-CN"
                });
        }

        protected getStartDate():Util.Date {
            let ret : any = {};

                let curDate = $("#dstart").getDate();
                ret = {
                    year : curDate.getFullYear(),
                    month : curDate.getMonth() + 1,
                    day : curDate.getDate()
                };

            return ret;
        }

        protected getEndDate():Util.Date {
            let ret : any = {};

            let curDate = $("#dEnd").getDate();
            ret = {
                year : curDate.getFullYear(),
                month : curDate.getMonth() + 1,
                day : curDate.getDate()
            };

            return ret;
        }

        protected init(opt:any):void {
            this.mOpt = opt;
            if (opt.itemNodes != '' && opt.itemNodes.length != 0) {
                this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
                this.unitedSelector.change(()=> {
                    this.renderItemSelector(opt.itemId);
                });
                this.renderItemSelector(opt.itemId);
            }else{
                $("#item-label").css("display", "none");
            }
            if (opt.itemNodes0 != '') {
                this.unitedSelector0 = new Util.UnitedSelector(opt.itemNodes0, opt.itemId0);
                this.unitedSelector0.change(()=> {
                    this.renderItemSelector(opt.itemId0);
                });
                this.renderItemSelector(opt.itemId0);
            }



            if (opt.date != undefined) {
                //this.mDStartSel = new Util.DateSelectorProxy("dstart",
                //    opt.dateStart == undefined ? Util.addYear(opt.date, -3) : opt.dateStart,
                //    opt.dateEnd == undefined ? Util.addYear(opt.date, 20) : opt.dateEnd,
                //    Util.addDay(opt.date, -5 * 7)
                //);


                let startOpt = this.createInternalDate("dstart", opt.date, {
                    nowDate: Util.date2Str(Util.addDay(opt.date, -5 * 7)),
                    minDate: Util.date2Str(opt.dateStart == undefined ? Util.addYear(opt.date, -3) : opt.dateStart),
                    maxDate: Util.date2Str(opt.date),
                    choosefun: (elem, val, date) =>{
                        setTimeout(()=>{
                            endOpt.minDate = Util.date2Str(this.getStartDate());
                            endDates();
                        },0);
                    }
                });

                let endOpt = this.createInternalDate("dEnd", opt.date, {
                    nowDate: Util.date2Str(opt.date),
                    minDate: startOpt.nowDate,
                    maxDate: Util.date2Str(opt.dateEnd == undefined ? Util.addYear(opt.date, 20) : opt.dateEnd),
                    choosefun: (elem, val, date)=>{
                        startOpt.maxDate = Util.date2Str(this.getEndDate()); //将结束日的初始值设定为开始日的最大日期
                    }
                });

                //这里是日期联动的关键
                function endDates() {
                    //将结束日期的事件改成 false 即可
                    endOpt.insTrigger = false;
                    $("#dEnd").jePopup();
                }


                //this.mDEndSel = new Util.DateSelectorProxy("dEnd",
                //    opt.dateStart == undefined ? Util.addYear(opt.date, -3) : opt.dateStart,
                //    opt.dateEnd == undefined ? Util.addYear(opt.date, 20) : opt.dateEnd,
                //    opt.date
                //);

                //this.mDEndSel.change((date:any)=>{
                //    let d = this.mDStartSel.getDate();
                //    let dS = Util.uDate2sDate(d).getTime();
                //    let dE = Util.uDate2sDate(date).getTime();
                //    if (dS > dE){
                //        this.mDStartSel.setDate(date);
                //    }
                //});
            } else {
                $("#dstart").parent().css("display", "none");
                $("#dEnd").parent().css("display", "none");
                //$("#dstartLabel").css("display", "none");
                //$("#dEndLabel").css("display", "none");
            }
            //$("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;
            if (this.mOpt.comps != '') {

                this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
                if (opt.comps.length == 1) {
                    this.mCompanySelector.hide();
                }

                this.mCompanySelector.change((selector:any, depth:number) => {
                    this.updateTypeSelector();
                });
            }else{
                $("#" + this.mOpt.comp).hide();
            }

            if ((opt.itemNodes == '' || opt.itemNodes.length == 0)&& opt.itemNodes0 == '' && opt.date == undefined && this.mOpt.comps == ''){
                $("#doUpdate").css("display", "none");
            }

            this.mCurrentDate = {year:2010, month:1, day:1};
            this.updateTypeSelector();
            this.updateUI();

            $(window).resize(()=> {
                this.adjustHeader();
                router.to(this.mCurrentPlugin).send(FrameEvent.FE_ADJUST_SZIE);
            });

            this.adjustHeader();
        }

        protected updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());


            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);
            if (this.mCompanySelector == undefined){
                //$("#headertitle")[0].innerHTML = node.getData().value;
            }else{
                this.mCurrentComp = this.mCompanySelector.getCompany();
                //if (null != this.mCurrentComp){
                //    $("#headertitle")[0].innerHTML = this.mCompanySelector.getCompanyName() + " " + node.getData().value;
                //}
                //else {
                //    $("#headertitle")[0].innerHTML = node.getData().value;
                //}
            }



            //let unit = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GETUNIT);
            //if (undefined != unit){
            //    $("#unit").text(unit);
            //}else{
            //    $("#unit").text("");
            //}

            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                dStart: this.mOpt.date == undefined ? undefined : Util.date2Str(this.getStartDate()),
                dEnd: this.mOpt.date == undefined ? undefined : Util.date2Str(this.getEndDate()),
                compType:this.mCurrentComp,
                item0:this.unitedSelector0 != undefined ? this.unitedSelector0.getDataNode(this.unitedSelector0.getPath()).data.value:undefined,
                item:this.unitedSelector != undefined ? this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value:undefined
            });
        }

        public exportExcel(elemId:string) {
            let url:string = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GET_EXPORTURL, {
                dStart: this.mOpt.date == undefined ? undefined : Util.date2Str(this.getStartDate()),
                dEnd: this.mOpt.date == undefined ? undefined : Util.date2Str(this.getEndDate()),
                compType:this.mCurrentComp,
                item:this.unitedSelector != undefined ? this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value:undefined
            });

            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
        }
    }
    let ins = new SddbShowView();
}