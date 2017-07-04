///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="cwyjsfdef.ts"/>
module cwyjsf{
    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;

    class CwyjsfFrameView extends framework.basic.ShowFrameView {
       
        date:Util.Date;

        private updateDate(){
            this.date = $.extend(this.date, this.getDate());
            if (router.to(this.plugin(this.mItemSelector.getDataNode(this.mItemSelector.getPath()))).send(Event.CW_ISMONTH_SUPPORTED)){
                this.createInternalDate(this.mOpt.dt,
                    {year:this.mOpt.date.year, month: this.mOpt.date.month},
                    {nowDate: Util.date2Str(this.date)});
                //this.createDate({nowDate: Util.date2Str(this.date)});
            }else{
                this.createInternalDate(this.mOpt.dt,
                    {year:this.mOpt.date.year},
                    {nowDate: Util.date2Str(this.date)});
            }
        }

        protected updateUI() {
            super.updateUI();
        }

        protected updateTypeSelector(width:number = 285) :boolean{
            let ret = super.updateTypeSelector(width);
            this.mItemSelector.change(()=>{
                this.updateDate();
            });
            return ret;
        }

        protected init(opt:any):void {
            this.date = $.extend({}, opt.date);
            super.init(opt);
            this.updateDate();
        }
    }

    let ins = new CwyjsfFrameView();
}