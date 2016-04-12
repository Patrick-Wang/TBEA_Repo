/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../../js/jquery/jquery.d.ts" />
module yszkgb {
    export interface PluginView {
        hide (): void;
        show () : void;
        update (date:Util.Date) : void;
        refresh():void;
    }

    export interface FrameView {
        register(name:string, plugin:PluginView):void;
        unregister(name:string) :PluginView;
    }

    export interface PluginOption {
        host:string;
        tbarea:string;
    }

    export abstract class BasePluginView implements PluginView {

        mOpt:PluginOption;
        public init(opt:PluginOption):void {
            this.mOpt = opt;
        }

        abstract refresh():void;

        public hide():void {
            $("#" + this.mOpt.host).hide();
        }

        public show():void {
            $("#" + this.mOpt.host).show();
        }

        protected $(id:string):jQuery {
            return $("#" + this.mOpt.host + " #" + id);
        }

        public  update(start:Util.Date):void {
            let st = start.year + "-" + start.month + "-" + start.day;
            this.pluginUpdate(st);
        }

        abstract  pluginUpdate(date:string):void;
    }
}
