///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicApprove.ts"/>
///<reference path="cpzlqkdef.ts"/>

module cpzlqk{

    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;


    class CpzlqkApproveFrameView extends framework.basic.ApproveFrameView {


        onEvent(e:framework.route.Event):any {

            return super.onEvent(e);
        }

        protected updateTypeSelector(width:number = 285) :boolean{
            let ret = super.updateTypeSelector(width);

            return ret;
        }


        protected init(opt:FrameOption):void {

            super.init(opt);
        }
    }




    export abstract class ZlApprovePluginView extends framework.basic.ApprovePluginView {
        onEvent(e:framework.route.Event):any {

            return super.onEvent(e);
        }


    }

    let ins = new CpzlqkApproveFrameView();
}