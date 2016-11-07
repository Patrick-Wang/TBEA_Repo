///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="cpzlqkdef.ts"/>
///<reference path="../components/dateSelectorProxy.ts"/>


declare var pageType;

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
        mAjaxApprove:Util.Ajax = new Util.Ajax("doApprove.do", false);
        mAjaxYclApprove:Util.Ajax = new Util.Ajax("../report/yclhglqktjZlDoApprove.do", false);
        mAjaxAuth:Util.Ajax = new Util.Ajax("auth.do", false);
        mCurZt:number;
        mAuths:any;
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
                let compType = this.mCompanySelector.getCompany();
                let isPd = (compType == Util.CompanyType.SBZTFGS || compType == Util.CompanyType.HBDQFGS
                || compType == Util.CompanyType.XBZTGS || compType == Util.CompanyType.TBGS
                || compType == Util.CompanyType.XBXBGS|| compType == Util.CompanyType.PDCY);
                if (isPd){
                    $("#zlAndyclhgl").hide();
                }else{
                    $("#zlAndyclhgl").show();
                }
                this.updateTypeSelector();
            });
            let inputs = $("#" + (<FrameOption>this.mOpt).contentType).show();
            inputs.click((e)=>{
                let node:Util.DataNode = this.triggerYdjdChecked();
            });
            var pageSlector = new Util.UnitedSelector([{
                data:{
                    id:0,
                    value:"产品一次送试"
                }
            },{
                data:{
                    id:1,
                    value:"原材料合格率"
                }
            }],"zlAndyclhgl");

            pageSlector.change(() => {
                if (pageSlector.getPath()[0] == 1){
                    if (pageType == 1){
                        window.location.href="../report/yclhglqktj.do?approve=true";
                    }else{
                        window.location.href="../report/yclhglqktj.do";
                    }
                }
            });

            $("#zlAndyclhgl select")
                .multiselect({
                    multiple: false,
                    header: false,
                    minWidth: 115,
                    height:'100%',
                    // noneSelectedText: "请选择月份",
                    selectedList: 1
                })
                .css("padding", "2px 0 2px 4px")
                .css("text-align", "left")
                .css("font-size", "12px");

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

        onApproved(id:number){
            let zt;
            switch(id){
                case Event.ZLFE_APPROVE_COMMENT1:
                    zt = Util.IndiStatus.INTER_APPROVED_1;
                    break;
                case Event.ZLFE_APPROVE_COMMENT2:
                    zt = Util.IndiStatus.INTER_APPROVED_2;
                    break;
                case Event.ZLFE_APPROVE_COMMENT3:
                    zt = Util.IndiStatus.INTER_APPROVED_3;
                    break;
                case Event.ZLFE_APPROVE_COMMENT:
                    zt = Util.IndiStatus.APPROVED;
                    break;
            }

            if (undefined != zt){
                this.mAjaxYclApprove.get({
                    date : this.mCurrentDate.year + "-" + this.mCurrentDate.month + "-1",
                    item: this.mCurrentComp,
                    zt : zt
                }).then((jsonData:any)=>{
                    this.mAjaxApprove.get({
                        date : this.mCurrentDate.year + "-" + this.mCurrentDate.month + "-1",
                        companyId: this.mCurrentComp,
                        zt : zt
                    }).then((jsonData:any)=>{

                        framework.router
                            .fromEp(this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(Event.ZLFE_APPROVEAUTH_UPDATED);

                        framework.router
                            .fromEp(this)
                            .to(framework.basic.endpoint.FRAME_ID)
                            .send(Event.ZLFE_COMMENT_UPDATED, {comment:{
                                comment : $("#commentText").val()
                            }, zt:zt});

                        if (zt == Util.IndiStatus.INTER_APPROVED_1){
                            Util.MessageBox.tip("审核成功", undefined);
                        }else{
                            Util.MessageBox.tip("上报成功", undefined);
                        }

                    });
                });
            }
        }

        onEvent(e:framework.route.Event):any {

            switch (e.id) {
                case Event.ZLFE_APPROVE_COMMENT1:
                case Event.ZLFE_APPROVE_COMMENT2:
                case Event.ZLFE_APPROVE_COMMENT3:
                case Event.ZLFE_APPROVE_COMMENT:
                    this.onApproved(e.id);
                    break;
                case Event.ZLFE_SAVE_COMMENT:
                    router.to(this.mCurrentPlugin).send(e.id, $("#commentText").val());
                    break;
                case Event.ZLFE_COMMENT_DENY:
                    $("#comment").hide();
                    break;
                case Event.ZLFE_APPROVEAUTH_UPDATED:
                    this.onUpdateAuth();
                    break;
                case Event.ZLFE_COMMENT_UPDATED:
                    this.onUpdateComment(e.data.comment, e.data.zt);
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


            if (pageType == PageType.APPROVE){
                this.mAjaxAuth.get({companyId: this.mCurrentComp})
                    .then((jsonData:any)=>{
                        this.mAuths = jsonData;
                        router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                            date:dt,
                            compType:this.mCurrentComp
                        });
                    });
            }else{
                router.to(this.mCurrentPlugin).send(FrameEvent.FE_UPDATE, {
                    date:dt,
                    compType:this.mCurrentComp
                });
            }


        }

        private onUpdateComment(comment:Comment, zt:number):void {

            if (comment.deny == "deny"){
                $("#comment").hide();
                return;
            }

            this.mCurZt = zt;

            $("#comment").show();
            $("#commentText").val(comment.comment);
            $("#commentText").attr("readonly","readonly");
            if (pageType == PageType.APPROVE){//approve
                if (zt == Util.IndiStatus.SUBMITTED){
                    if ($("#approveComment1").is(":visible")){

                    }else{
                        $("#commentText").val("");
                    }
                    $("#approveComment").hide();
                    $("#approveComment2").hide();
                    $("#approveComment3").hide();
                } else if (zt == Util.IndiStatus.INTER_APPROVED_1){
                    if ($("#approveComment2").is(":visible") ||
                        $("#approveComment1").is(":visible")){

                    }else{
                        $("#commentText").val("");
                    }
                    $("#approveComment").hide();
                    $("#approveComment1").hide();
                    $("#approveComment3").hide();

                } else if (zt == Util.IndiStatus.INTER_APPROVED_2){
                    if ($("#approveComment2").is(":visible") ||
                        $("#approveComment1").is(":visible") ||
                        $("#approveComment3").is(":visible")){

                    }else{
                        $("#commentText").val("");
                    }
                    $("#approveComment1").hide();
                    $("#approveComment2").hide();
                    $("#approveComment").hide();
                } else if (zt == Util.IndiStatus.INTER_APPROVED_3){
                    if ($("#approveComment2").is(":visible") ||
                        $("#approveComment1").is(":visible") ||
                        $("#approveComment3").is(":visible") ||
                        $("#approveComment").is(":visible")){

                    }else{
                        $("#commentText").val("");
                    }
                    $("#approveComment1").hide();
                    $("#approveComment2").hide();
                    $("#approveComment3").hide();

                } else if (zt == Util.IndiStatus.APPROVED){
                    $("#approveComment").hide();
                    $("#approveComment1").hide();
                    $("#approveComment2").hide();
                    $("#approveComment3").hide();
                } else {
                    $("#approveComment").hide();
                    $("#approveComment1").hide();
                    $("#approveComment2").hide();
                    $("#approveComment3").hide();
                    $("#commentText").val("");
                    $("#comment").hide();
                }
            }else if (pageType == PageType.ENTRY){//submit
                $("#approveComment").hide();
                $("#approveComment1").hide();
                $("#approveComment2").hide();
                $("#approveComment3").hide();
                $("#commentText").removeAttr("readonly");
                if (zt == 0){
                    $("#comment").hide();
                }
            }else if (pageType == PageType.SHOW){//show
                $("#approveComment").hide();
                $("#approveComment1").hide();
                $("#approveComment2").hide();
                $("#approveComment3").hide();
                if (zt != 1){
                    $("#commentText").val("");
                }
            }
        }

        private onUpdateAuth():void {
            $("#approveComment").hide();
            $("#approveComment1").hide();
            $("#approveComment2").hide();
            $("#approveComment3").hide();
            if (Util.indexOf(this.mAuths, 22) >= 0){
                $("#approveComment").show();
            }
            if (Util.indexOf(this.mAuths, 53) >= 0){
                $("#approveComment1").show();
            }
            if (Util.indexOf(this.mAuths, 54) >= 0){
                $("#approveComment2").show();
            }
            if (Util.indexOf(this.mAuths, 55) >= 0){
                $("#approveComment3").show();
            }
        }
    }

    export abstract class ZlPluginView extends framework.basic.ShowPluginView {
        protected mYdjdType : YDJDType;
        protected mCompSize:number;
        mCommentSubmit:Util.Ajax = new Util.Ajax("../report/zlfxSubmit.do", false);
        mCommentGet:Util.Ajax = new Util.Ajax("../report/zlfxUpdate.do", false);

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
                case Event.ZLFE_SAVE_COMMENT:
                    this.onSaveComment(e.data);
                    break;
            }
            return super.onEvent(e);
        }

        abstract onSaveComment(data:any):void;
    }


    let ins = new CpzlqkFrameView();
}