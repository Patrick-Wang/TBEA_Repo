/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />

module jcycljg {
    export interface PluginView{
        hide : () => void;
        show : () => void;
        update : (start: Util.Date, end: Util.Date) => void;
    }

    export interface FrameView{
        register: (name:string, plugin:PluginView)=>void;
        unregister: (name:string) => PluginView;
    }
 }
