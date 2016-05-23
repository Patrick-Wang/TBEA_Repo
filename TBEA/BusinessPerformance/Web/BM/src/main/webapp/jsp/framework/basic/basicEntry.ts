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

    export class EntryFrameView extends BasicFrameView {
        onEvent(e:framework.route.Event):any {
            super.onEvent(e);
            switch (e.id) {
                case FrameEvent.FE_SAVE:
                    this.save();
                    break;
                case FrameEvent.FE_SUBMIT:
                    this.submit();
                    break;
            }
        }

        protected submit() {
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SUBMIT,{
                date:this.mCurrentDate,
                compType:this.mCurrentComp
            });
        }

        protected save() {
            router.to(this.mCurrentPlugin).send(FrameEvent.FE_SAVE,{
                date:this.mCurrentDate,
                compType:this.mCurrentComp
            });
        }
    }
}