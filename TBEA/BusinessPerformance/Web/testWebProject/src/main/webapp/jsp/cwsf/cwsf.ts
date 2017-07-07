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

        protected init(opt:any):void {

            this.mOpt = opt;

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

            var sel = $("#" + opt.itemId + " select");
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
                item:this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value
            });
        }

        public exportExcel(elemId:string) {
            let url:string = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GET_EXPORTURL, {
                dStart:this.mDStartSel == undefined ? undefined : Util.date2Str(this.mDStartSel.getDate()),
                dEnd:this.mDEndSel == undefined ? undefined : Util.date2Str(this.mDEndSel.getDate()),
                comps:JSON.stringify(this.mCompanySelector.getCompanyNames()),
                item:this.unitedSelector.getDataNode(this.unitedSelector.getPath()).data.value
            });

            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
        }
    }
    let ins = new CwsfShowView();
}