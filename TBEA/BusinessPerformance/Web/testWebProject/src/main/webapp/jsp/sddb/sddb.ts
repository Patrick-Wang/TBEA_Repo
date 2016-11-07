///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="../components/dateSelectorProxy.ts"/>
///<reference path="../unitedSelector.ts"/>

module sddb{

    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;
    class SddbShowView extends framework.basic.ShowFrameView{
        mDStartSel : Util.DateSelectorProxy;
        mDEndSel : Util.DateSelectorProxy;
        unitedSelector : Util.UnitedSelector;
        private renderItemSelector(itemId:string):void{
            let sels = $("#" + itemId + " select");
            for (let i = 0; i < sels.length; ++i){
                let opts = $("#" + itemId + " select:eq(" + i + ") option");
                let items = [];
                for (let j = 0; j < opts.length; ++j){
                    items.push(opts[j].text);
                }
                let width = Util.getUIWidth(items);
                $(sels[i]) .multiselect({
                        multiple: false,
                        header: false,
                        minWidth: width < 80 ? 80 : width,
                        height: '100%',
                        // noneSelectedText: "请选择月份",
                        selectedList: 1
                    })
                    .css("padding", "2px 0 2px 4px")
                    .css("text-align", "left")
                    .css("font-size", "12px");
            }
        }

        protected init(opt:any):void {
            this.mOpt = opt;
            if (opt.itemNodes != '') {
                this.unitedSelector = new Util.UnitedSelector(opt.itemNodes, opt.itemId);
                this.unitedSelector.change(()=> {
                    this.renderItemSelector(opt.itemId);
                });
                this.renderItemSelector(opt.itemId);
            }
            if (opt.date != undefined) {
                this.mDStartSel = new Util.DateSelectorProxy("dstart",
                    Util.addYear(opt.date, -3),
                    Util.addYear(opt.date, 20),
                    Util.addDay(opt.date, -5 * 7)
                );

                this.mDEndSel = new Util.DateSelectorProxy("dEnd",
                    Util.addYear(opt.date, -3),
                    Util.addYear(opt.date, 20),
                    opt.date
                );
            } else {
                $("#dstart").css("display", "none");
                $("#dEnd").css("display", "none");
                $("#dstartLabel").css("display", "none");
                $("#dEndLabel").css("display", "none");
            }
            $("#ui-datepicker-div").css('font-size', '0.8em'); //改变大小;
            if (this.mOpt.comp != '') {

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
            this.mCurrentDate = {year:2010, month:1, day:1};
            this.updateTypeSelector();
            this.updateUI();
        }

        protected updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());


            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);
            if (this.mCompanySelector == undefined){
                $("#headertitle")[0].innerHTML = node.getData().value;
            }else{
                this.mCurrentComp = this.mCompanySelector.getCompany();
                if (null != this.mCurrentComp){
                    $("#headertitle")[0].innerHTML = this.mCompanySelector.getCompanyName() + " " + node.getData().value;
                }
                else {
                    $("#headertitle")[0].innerHTML = node.getData().value;
                }
            }



            //let unit = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GETUNIT);
            //if (undefined != unit){
            //    $("#unit").text(unit);
            //}else{
            //    $("#unit").text("");
            //}

            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                dStart:this.mDStartSel == undefined ? undefined : Util.date2Str(this.mDStartSel.getDate()),
                dEnd:this.mDEndSel == undefined ? undefined : Util.date2Str(this.mDEndSel.getDate()),
                compType:this.mCurrentComp,
                item:this.unitedSelector != undefined ? this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value:undefined
            });
        }

        public exportExcel(elemId:string) {
            let url:string = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GET_EXPORTURL, {
                dStart:this.mDStartSel == undefined ? undefined : Util.date2Str(this.mDStartSel.getDate()),
                dEnd:this.mDEndSel == undefined ? undefined : Util.date2Str(this.mDEndSel.getDate()),
                compType:this.mCurrentComp,
                item:this.unitedSelector != undefined ? this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value:undefined
            });

            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
        }
    }
    let ins = new SddbShowView();
}