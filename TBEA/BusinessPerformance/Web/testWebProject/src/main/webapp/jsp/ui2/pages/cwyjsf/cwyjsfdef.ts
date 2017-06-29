///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>

module cwyjsf{
    export interface Option extends framework.basic.PluginOption {
        tb:string;
        tb1:string;
        date:any;
    }
    
    import FrameEvent = framework.basic.FrameEvent;
    export module Event{
        export let CW_ISMONTH_SUPPORTED : number = FrameEvent.lastEvent();
    }
}