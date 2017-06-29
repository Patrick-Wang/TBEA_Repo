///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="cwyjsfdef.ts"/>
module cwyjsf{
    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;

    class CwyjsfFrameView extends framework.basic.ShowFrameView {
       
        date:Util.Date;
        protected updateUI() {
            super.updateUI();
            this.date = $.extend(this.date, this.getDate());
            if (router.to(this.mCurrentPlugin).send(Event.CW_ISMONTH_SUPPORTED)){
                this.createDate({nowDate: Util.date2Str(this.date)});
            }else{
                this.createDate({
                    format:"YYYY年",
                    nowDate: Util.date2Str(this.date)});
            }             
        }
        
        protected init(opt:any):void {
            this.date = $.extend({}, opt.date);
            super.init(opt);    
        }
    }

    let ins = new CwyjsfFrameView();
}