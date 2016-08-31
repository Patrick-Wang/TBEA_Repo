///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="cpzlqkdef.ts"/>
///<reference path="../components/dateSelectorProxy.ts"/>

module cpzlqk {
    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;
    import DateSelector = Util.DateSelector;

    class CpzlqkFrameView extends framework.basic.ShowFrameView {

        isCompanySupported:boolean = false;
        mYdjdType : YDJDType;

        protected init(opt:any):void {
            this.mOpt = opt;
            let dsp : any = new Util.DateSelectorProxy(this.mOpt.dt,
                {year: this.mOpt.date.year - 3, month: 1},
                {
                    year: this.mOpt.date.year,
                    month: 12
                },
                {
                    year: this.mOpt.date.year,
                    month: this.mOpt.date.month
                }, false, false);
            this.mDtSec = dsp;

            this.mCompanySelector = new Util.CompanySelector(false, this.mOpt.comp, this.mOpt.comps);
            if (opt.comps.length == 1) {
                this.mCompanySelector.hide();
            }

            this.mCompanySelector.change((selector:any, depth:number) => {
                this.updateTypeSelector();
            });
            let inputs = $("#" + (<FrameOption>this.mOpt).contentType).show();
            inputs.click((e)=>{
                let node:Util.DataNode = this.triggerYdjdChecked();
            });
            this.updateTypeSelector();
            this.updateUI();
        }

        protected checkCompanySupported() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            let isSupported = router.to(this.plugin(node)).send(Event.ZLFE_IS_COMPANY_SUPPORTED, this.mOpt.comps.length);
            if (undefined == isSupported || isSupported) {
                if (this.mOpt.comps.length > 1){
                    this.mCompanySelector.show();
                }
                this.isCompanySupported = true;
            } else {
                this.mCompanySelector.hide();
                this.isCompanySupported = false;
            }
        }

        protected checkYdjdSupported(){
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            let isSupported  = router.to(this.plugin(node)).send(Event.ZLFE_IS_YDJD_SUPPORTED);
            if (isSupported){
                $("#" + (<FrameOption>this.mOpt).contentType).show();
                this.triggerYdjdChecked();
            }else{
                $("#" + (<FrameOption>this.mOpt).contentType).hide();
                this.mYdjdType = undefined;
            }
        }

        private triggerYdjdChecked() : Util.DataNode {
            let inputs = $("#" + (<FrameOption>this.mOpt).contentType + " input");
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            for (let i = 0; i < inputs.length; i++) {
                if (true == inputs[i].checked) {
                    if (inputs[i].id == 'rdyd') {
                        this.mYdjdType = YDJDType.YD;
                        router.to(this.plugin(node)).send(Event.ZLFE_YD_SELECTED);
                        let dtNow = this.mDtSec.getDate();
                        $("#" + this.mOpt.dt).empty();
                        let dsp : any = new Util.DateSelectorProxy(this.mOpt.dt,
                            {year: this.mOpt.date.year - 3, month: 1},
                            {
                                year: this.mOpt.date.year,
                                month: 12
                            },
                            dtNow, false, false);
                        this.mDtSec = dsp;
                    } else {
                        this.mYdjdType = YDJDType.JD;
                        let dtNow = this.mDtSec.getDate();
                        $("#" + this.mOpt.dt).empty();
                        let dsp : any = new Util.DateSelectorProxy(this.mOpt.dt,
                            {year: this.mOpt.date.year - 3, month: 1},
                            {
                                year: this.mOpt.date.year,
                                month: 12
                            },
                            dtNow, false, true);
                        this.mDtSec = dsp;
                        router.to(this.plugin(node)).send(Event.ZLFE_JD_SELECTED);
                    }
                }
            }
            return node;
        }

        protected updateTypeSelector(width:number = 285):boolean {
            if (super.updateTypeSelector(width)){
                this.mItemSelector.change(()=>{
                    this.checkCompanySupported();
                    this.checkYdjdSupported();
                });
                this.checkCompanySupported();
                this.checkYdjdSupported();
                return true;
            }
            return false;
        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case Event.ZLFE_SAVE_COMMENT:
                    router.to(this.mCurrentPlugin).send(Event.ZLFE_SAVE_COMMENT, $("#commentText").val());
                    break;
                case Event.ZLFE_COMMENT_DENY:
                    $("#comment").hide();
                    break;
                case Event.ZLFE_COMMENT_UPDATED:
                    let comment : Comment = e.data;
                    if (comment.deny == "deny"){
                        $("#comment").hide();
                    }else if(comment.readonly == "true"){
                        $("#saveComment").hide();
                        $("#comment").show();
                        $("#commentText").val(comment.comment);
                        $("#commentText").attr("readonly","readonly");
                    }else{
                        $("#comment").show();
                        $("#saveComment").show();
                        $("#commentText").val(comment.comment);
                        $("#commentText").removeAttr("readonly");
                    }

                    break;
            }
            return super.onEvent(e);
        }

        protected updateUI() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());

            let dt:Util.Date = this.mDtSec.getDate();
            if (dt.month == undefined){
                dt.month = 1;
            }

            if (dt.day == undefined) {
                dt.day = 1;
            }

            this.mCurrentPlugin = this.plugin(node);
            router.broadcast(FrameEvent.FE_HIDE);

            this.mCurrentComp = this.mCompanySelector.getCompany();
            this.mCurrentDate = dt;
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SHOW);
            let title = node.getData().value;

            if (this.mYdjdType == YDJDType.YD){
                title = "月度" + title;
            }else if (this.mYdjdType == YDJDType.JD){
                title = "季度" + title;
            }

            if (this.isCompanySupported){
                title = this.mCompanySelector.getCompanyName() + " " + title;
            }
            $("#headertitle")[0].innerHTML = title;
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                date:dt,
                compType:this.mCurrentComp
            });
        }
    }

    export abstract class ZlPluginView extends framework.basic.ShowPluginView {
        protected mYdjdType : YDJDType;
        protected mCompSize:number;
        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case Event.ZLFE_IS_YDJD_SUPPORTED:
                    return true;
                case Event.ZLFE_JD_SELECTED:
                    this.mYdjdType = YDJDType.JD;
                    break;
                case Event.ZLFE_YD_SELECTED:
                    this.mYdjdType = YDJDType.YD;
                    break;
                case Event.ZLFE_IS_COMPANY_SUPPORTED:
                    this.mCompSize = e.data;
                    return false;
            }
            return super.onEvent(e);
        }
    }


    let ins = new CpzlqkFrameView();
}