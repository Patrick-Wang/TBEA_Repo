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
                    $(sels[i]) .multiselect({
                            multiple: false,
                            header: false,
                            minWidth: width < 80 ? 80 + extendWidth : width + 20 + extendWidth,
                            height: hasScroll ? 270 : '100%',
                            // noneSelectedText: "请选择月份",
                            selectedList: 1
                        })
                        .css("padding", "2px 0 2px 4px")
                        .css("text-align", "left")
                        .css("font-size", "12px");
                }
            }
        }

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

        protected init(opt:any):void {

            this.mOpt = opt;
            this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);

            var sel = $("#" + opt.itemId);
            var width = this.getMaxWidth(sel.children());
            sel.css("width", width);
            sel .multiselect({
                multiple: false,
                header: false,
                minWidth: 120,
                height: '100%',
                // noneSelectedText: "请选择月份",
                selectedList: 1
            });

            this.mDStartSel = new Util.DateSelectorProxy("dstart",
                opt.dateStart == undefined ? Util.addYear(opt.date, -3) : opt.dateStart,
                opt.dateEnd == undefined ? Util.addYear(opt.date, 20) : opt.dateEnd,
                Util.addDay(opt.date, -5 * 7)
            );

            this.mDEndSel = new Util.DateSelectorProxy("dEnd",
                opt.dateStart == undefined ? Util.addYear(opt.date, -3) : opt.dateStart,
                opt.dateEnd == undefined ? Util.addYear(opt.date, 20) : opt.dateEnd,
                opt.date
            );

            this.mDEndSel.change((date:any)=>{
                let d = this.mDStartSel.getDate();
                let dS = Util.uDate2sDate(d).getTime();
                let dE = Util.uDate2sDate(date).getTime();
                if (dS > dE){
                    this.mDStartSel.setDate(date);
                }
            });

            $("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;

            this.mCompanySelector = new Util.CompanySelector(true, this.mOpt.comp, this.mOpt.comps);


            if (opt.comps.length <= 10) {
                this.mCompanySelector.hide();
            }

            this.mCompanySelector.checkAll();


            this.updateUI();
        }


        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FrameEvent.FE_EXPORTEXCEL:
                    this.exportExcel(e.data);
                    break;
            }
            return super.onEvent(e);
        }

        //不可以起名叫做export 在IE中有冲突
        public exportExcel(elemId:string) {
            let url:string = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GET_EXPORTURL, {
                date: this.mCurrentDate,
                compType: this.mCurrentComp
            });

            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
        }

        protected updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());


            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);

            $("#headertitle")[0].innerHTML = node.getData().value;

            this.mCompanySelector.getCompanyNames();

            //let unit = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GETUNIT);
            //if (undefined != unit){
            //    $("#unit").text(unit);
            //}else{
            //    $("#unit").text("");
            //}

            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                dStart:this.mDStartSel == undefined ? undefined : Util.date2Str(this.mDStartSel.getDate()),
                dEnd:this.mDEndSel == undefined ? undefined : Util.date2Str(this.mDEndSel.getDate()),
                comps:JSON.stringify(this.mCompanySelector.getCompanyNames()),
                item:this.unitedSelector != undefined ? this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value:undefined
            });
        }

        public exportExcel(elemId:string) {
            let url:string = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GET_EXPORTURL, {
                dStart:this.mDStartSel == undefined ? undefined : Util.date2Str(this.mDStartSel.getDate()),
                dEnd:this.mDEndSel == undefined ? undefined : Util.date2Str(this.mDEndSel.getDate()),
                comps:JSON.stringify(this.mCompanySelector.getCompanyNames()),
                item:this.unitedSelector != undefined ? this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value:undefined
            });

            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
        }
    }
    let ins = new CwsfShowView();
}