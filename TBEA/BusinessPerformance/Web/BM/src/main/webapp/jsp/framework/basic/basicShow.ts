/// <reference path="../../jqgrid/jqassist.ts" />
/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../unitedSelector.ts"/>
///<reference path="../../messageBox.ts"/>
///<reference path="../../companySelector.ts"/>
///<reference path="basicdef.ts"/>
///<reference path="../route/route.ts"/>
///<reference path="basic.ts"/>
module framework.basic {

    import router = framework.router;

    export module FrameEvent {
        export let FE_EXPORTEXCEL = lastEvent();
    }

    export class ShowFrameView extends BasicFrameView {
        onEvent(e:framework.route.Event):any {
            super.onEvent(e);
            switch (e.id) {
                case FrameEvent.FE_EXPORTEXCEL:
                    this.exportExcel(e.data);
                    break;
            }
        }

        //不可以起名叫做export 在IE中有冲突
        public exportExcel(elemId:string) {
            let url:string = router.to(this.mCurrentPlugin).send(FrameEvent.FE_GET_EXPORTURL, {
                date: this.mCurrentDate,
                compType: this.mCurrentComp
            });

            $("#" + elemId)[0].action = url;
            $("#" + elemId)[0].submit();
        }
    }
}

