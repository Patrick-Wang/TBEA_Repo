///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>
///<reference path="cpzlqkdef.ts"/>

module cpzlqk {
    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;

    export module Event{
        export let ZLFE_IS_COMPANY_SUPPORTED : number = FrameEvent.lastEvent();
        export let ZLFE_IS_YDJD_SUPPORTED : number = FrameEvent.lastEvent();
        export let ZLFE_YD_SELECTED : number = FrameEvent.lastEvent();
        export let ZLFE_JD_SELECTED : number = FrameEvent.lastEvent();
    }

    class CpzlqkFrameView extends framework.basic.ShowFrameView {

        isCompanySupported:boolean = false;
        mYdjdType : YDJDType;
        protected checkCompanySupported() {
            let node:Util.DataNode = this.mItemSelector.getDataNode(this.mItemSelector.getPath());
            let isSupported = router.to(this.plugin(node)).send(Event.ZLFE_IS_COMPANY_SUPPORTED);
            if (undefined == isSupported || isSupported) {
                this.mCompanySelector.show();
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
                    } else {
                        this.mYdjdType = YDJDType.JD;
                        router.to(this.plugin(node)).send(Event.ZLFE_JD_SELECTED);
                    }
                }
            }
            return node;
        }

        protected init(opt:FrameOption):void {
            super.init(opt);
            let inputs = $("#" + (<FrameOption>this.mOpt).contentType).show();
            inputs.click((e)=>{
                let node:Util.DataNode = this.triggerYdjdChecked();
            });
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
                    return false;
            }
            return super.onEvent(e);
        }
    }


    let ins = new CpzlqkFrameView();
}