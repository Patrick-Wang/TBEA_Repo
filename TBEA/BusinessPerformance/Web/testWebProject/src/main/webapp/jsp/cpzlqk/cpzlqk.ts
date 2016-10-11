///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="cpzlqkdef.ts"/>
///<reference path="../components/dateSelectorProxy.ts"/>

module cpzlqk {
    declare var tableStatus : TableStatus[];

    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;
    import DateSelector = Util.DateSelector;

    class CpzlqkFrameView extends framework.basic.ShowFrameView {

        isCompanySupported:boolean = false;
        mYdjdType : YDJDType;
        mDtYd:Util.Date;
        mDtJd:Util.Date;

        checkCompanyCount(comps : Util.IDataNode[]){
            if (comps == undefined){
                return true;
            }

            if (comps.length == 1){
                return this.checkCompanyCount(comps[0].subNodes);
            }
            return false;
        }

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
            if (this.checkCompanyCount(opt.comps)) {
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

        protected register(name:string, plugin:number):void {
            let contains = true;
            if (tableStatus != undefined){
                contains = false;
                for (let i = 0; i < tableStatus.length; ++i){
                    if (tableStatus[i].id == plugin){
                        contains = true;
                        break;
                    }
                }
            }

            if (contains){
                super.register(name,plugin)
            }
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
                       // if ( this.mYdjdType ==  YDJDType.JD || this.mYdjdType == undefined){
                            let dtNow = this.mDtSec.getDate();
                            this.mDtJd = this.mDtSec.getDate();
                            if (this.mDtYd != undefined){
                                dtNow = this.mDtYd;
                            }
                            this.mYdjdType = YDJDType.YD;
                            $("#" + this.mOpt.dt).empty();
                            let dsp : any = new Util.DateSelectorProxy(this.mOpt.dt,
                                {year: this.mOpt.date.year - 3, month: 1},
                                {
                                    year: this.mOpt.date.year,
                                    month: 12
                                },
                                dtNow, false, false);
                            this.mDtSec = dsp;
                            router.to(this.plugin(node)).send(Event.ZLFE_YD_SELECTED);
                      //  }


                    } else {
                        //if ( this.mYdjdType ==  YDJDType.YD || this.mYdjdType == undefined){
                            let dtNow = this.mDtSec.getDate();
                            this.mDtYd = this.mDtSec.getDate();
                            //if (this.mDtJd != undefined){
                            //    dtNow = this.mDtJd;
                            //}
                            this.mYdjdType = YDJDType.JD;

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
                       // }
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
                case Event.ZLFE_APPROVE_COMMENT1:
                case Event.ZLFE_APPROVE_COMMENT2:
                case Event.ZLFE_APPROVE_COMMENT:
                    router.to(this.mCurrentPlugin).send(e.id, $("#commentText").val());
                    break;
                case Event.ZLFE_COMMENT_DENY:
                    $("#comment").hide();
                    break;
                case Event.ZLFE_APPROVEAUTH_UPDATED:
                    $("#approveComment").hide();
                    $("#approveComment1").hide();
                    $("#approveComment2").hide();
                    if (Util.indexOf(e.data, 22) >= 0){
                        $("#approveComment").show();
                    }
                    if (Util.indexOf(e.data, 53) >= 0){
                        $("#approveComment1").show();
                    }
                    if (Util.indexOf(e.data, 54) >= 0){
                        $("#approveComment2").show();
                    }
                    break;
                case Event.ZLFE_COMMENT_UPDATED:
                    let comment : Comment = e.data.comment;

                    if (comment.deny == "deny"){
                        $("#comment").hide();
                        break;
                    }

                    $("#comment").show();
                    $("#commentText").val(comment.comment);
                    $("#commentText").attr("readonly","readonly");
                    if (window.pageType == PageType.APPROVE){//approve
                        if (comment.zt == Util.IndiStatus.APPROVED){
                            $("#approveComment").hide();
                            $("#approveComment1").hide();
                            $("#approveComment2").hide();
                        } else if (comment.zt == Util.IndiStatus.INTER_APPROVED_1){
                            $("#approveComment").hide();
                            $("#approveComment1").hide();
                            if ($("#approveComment2").is(":hidden")){
                                $("#commentText").val("");
                            }

                        } else if (comment.zt == Util.IndiStatus.INTER_APPROVED_2){
                            $("#approveComment1").hide();
                            $("#approveComment2").hide();
                            if ($("#approveComment").is(":hidden")){
                                $("#commentText").val("");
                            }
                        } else if (comment.zt == Util.IndiStatus.SUBMITTED){
                            $("#approveComment").hide();
                            $("#approveComment2").hide();
                            if ($("#approveComment1").is(":hidden")){
                                $("#commentText").val("");
                            }
                        } else{
                            $("#approveComment").hide();
                            $("#approveComment1").hide();
                            $("#approveComment2").hide();
                            $("#commentText").val("");
                        }
                    }else if (window.pageType == PageType.ENTRY){//submit
                        $("#commentText").removeAttr("readonly");
                    }else if (window.pageType == PageType.SHOW){//show
                        if (comment.zt != 1){
                            $("#commentText").val("");
                        }
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