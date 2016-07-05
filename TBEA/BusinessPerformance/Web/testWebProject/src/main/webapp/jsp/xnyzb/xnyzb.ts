///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>

module xnyzb{

    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;
    class XnyzbShowView extends framework.basic.ShowFrameView{
        protected init(opt:any):void {
            this.mOpt = opt;

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

            let unit = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GETUNIT);
            if (undefined != unit){
                $("#unit").text(unit);
            }else{
                $("#unit").text("");
            }

            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                date:this.mCurrentDate,
                compType:this.mCurrentComp
            });
        }
    }
    let ins = new XnyzbShowView();
}