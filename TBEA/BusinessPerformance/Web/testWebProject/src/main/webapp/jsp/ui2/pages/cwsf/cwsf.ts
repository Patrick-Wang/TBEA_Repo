///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="../components/dateSelectorProxy.ts"/>
///<reference path="../unitedSelector.ts"/>

module cwsf{

    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;


    class CwsfShowView extends framework.basic.ShowFrameView{

        mDStartSel : Util.DateSelectorProxy;
        mDEndSel : Util.DateSelectorProxy;
        unitedSelector : Util.UnitedSelector;


        private getMaxWidth(opts : any) : number{
            var max = 0;
            var tmp = 0;
            var fontSize = Util.isMSIE() ? 14 : 13;
            for (var i = 0; i < opts.length; ++i){
                tmp = $(opts[i]).text().getWidth(fontSize) + 25;
                if (max < tmp){
                    max = tmp;
                }
            }
            return max;
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
            opt.date.day = undefined;
            opt.dateStart.day = undefined;
            opt.dateEnd.day = undefined;
            let startOpt = this.createInternalDate("dstart", opt.date, {
                nowDate: Util.date2Str(Util.addMonth(opt.date, -1)),
                minDate: Util.date2Str(opt.dateStart),
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
                maxDate: Util.date2Str(opt.dateEnd),
                choosefun: (elem, val, date)=>{
                    startOpt.maxDate = Util.date2Str(this.getEndDate()); //将结束日的初始值设定为开始日的最大日期
                }
            });

            //这里是日期联动的关键
            function endDates() {
                //将结束日期的事件改成 false 即可
                endOpt.insTrigger = false;
                $("#dEnd").jeDate(endOpt).jePopup();
            }


            let nodes = [];
            for (var i = 0; i < this.mNodesAll.length; ++i) {
                if (router.to(this.plugin(this.mNodesAll[i])).send(FrameEvent.FE_IS_COMPANY_SUPPORTED)) {
                    nodes.push(this.mNodesAll[i]);
                }
            }
            this.mItemSelector = new Util.UnitedSelector(nodes, this.mOpt.type);
            if (nodes.length == 1) {
                this.mItemSelector.hide();
            }


            this.mCompanySelector = new Util.CompanySelector(true, this.mOpt.comp, this.mOpt.comps);

            if (opt.comps.length <= 10) {
                this.mCompanySelector.hide();
            }

            this.mCompanySelector.checkAll();

            this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);


            this.updateUI();

            $(window).resize(()=> {
                this.adjustHeader();
                router.to(this.mCurrentPlugin).send(FrameEvent.FE_ADJUST_SZIE);
            });

            this.adjustHeader();
        }


        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FrameEvent.FE_EXPORTEXCEL:
                    this.exportExcel(e.data);
                    break;
            }
            return super.onEvent(e);
        }



        protected updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());


            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);

            this.mCompanySelector.getCompanyNames();



            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                dStart:Util.date2Str(this.getStartDate()),
                dEnd:Util.date2Str(this.getEndDate()),
                comps:JSON.stringify(this.mCompanySelector.getCompanyNames()),
                item:this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value
            });
        }

        public exportExcel(elemId:string) {
            let url:string = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GET_EXPORTURL, {
                dStart:Util.date2Str(this.getStartDate()),
                dEnd:Util.date2Str(this.getEndDate()),
                comps:JSON.stringify(this.mCompanySelector.getCompanyNames()),
                item:this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value
            });

            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
        }
    }
    let ins = new CwsfShowView();
}