///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicEntry.ts"/>
///<reference path="cpzlqk.ts"/>

module cpzlqk{

    import router = framework.router;
    import FrameEvent = framework.basic.FrameEvent;


    class CpzlqkEntryFrameView extends framework.basic.EntryFrameView {


        onEvent(e:framework.route.Event):any {
            switch (e.id){
                case Event.ZLFE_DATA_STATUS:
                    $("#submit").show();
                    break;
            }
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




    export abstract class ZlEntryPluginView extends framework.basic.EntryPluginView {
        onEvent(e:framework.route.Event):any {
            return super.onEvent(e);
        }

    }

    let ins = new CpzlqkEntryFrameView();
}