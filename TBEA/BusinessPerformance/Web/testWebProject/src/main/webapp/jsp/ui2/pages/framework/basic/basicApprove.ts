/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="basic.ts" />
/// <reference path="../../unitedSelector.ts"/>
///<reference path="../../messageBox.ts"/>
///<reference path="../../companySelector.ts"/>
///<reference path="../route/route.ts"/>
module framework.basic {
    import router = framework.router;

    export interface ApproveOption extends Option{
        approveBtn: string;
        unapproveBtn:string;
    }

    export class ApproveFrameView extends BasicFrameView {
        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FrameEvent.FE_APPROVE:
                    this.approve();
                    break;
                case FrameEvent.FE_UNAPPROVE:
                    this.unapprove();
                    break;
                case FrameEvent.FE_NOT_SUBMITTED:
                    $("#" + (<ApproveOption>(this.mOpt)).approveBtn).hide();
                    $("#" + (<ApproveOption>(this.mOpt)).unapproveBtn).hide();
                    break;
                case FrameEvent.FE_SUBMITTED:
                    $("#" + (<ApproveOption>(this.mOpt)).approveBtn).show();
                    $("#" + (<ApproveOption>(this.mOpt)).unapproveBtn).hide();
                    break;
                case FrameEvent.FE_APPROVED:
                    $("#" + (<ApproveOption>(this.mOpt)).approveBtn).hide();
                    $("#" + (<ApproveOption>(this.mOpt)).unapproveBtn).show();
                    break;
            }
            return super.onEvent(e);
        }

        protected approve() {
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_APPROVE,{
                date:this.mCurrentDate,
                compType:this.mCurrentComp
            });
        }

        protected unapprove() {
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_UNAPPROVE,{
                date:this.mCurrentDate,
                compType:this.mCurrentComp
            });
        }
    }
}