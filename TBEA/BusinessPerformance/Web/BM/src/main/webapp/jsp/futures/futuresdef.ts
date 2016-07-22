/// <reference path="../util.ts" />
/// <reference path="../../js/jquery/jquery.d.ts" />
module futures {

    export enum ContentType{
        TABLE_CHART,
        TABLE
    }

    export enum DisplayType{
        TABLE,
        CHART
    }

    export interface PluginView {
        hide (): void;
        show () : void;
        switchDisplayType(type:DisplayType):void;
        update () : void;
        getContentType():ContentType;
        refresh():void;
    }

    export interface FrameView {
        register(name:string, plugin:PluginView):void;
        unregister(name:string) :PluginView;
    }

    export interface PluginOption {
        host:string;
        ctarea:string;
        tbarea:string;
    }

        export abstract class BasePluginView implements PluginView {

        mOpt:PluginOption;
        mDispType : DisplayType;
        public init(opt:PluginOption):void {
            this.mOpt = opt;
        }

        abstract refresh():void;

        public switchDisplayType(type:futures.DisplayType):void {
            this.mDispType = type;
            switch (type){
                case DisplayType.TABLE:
                    this.$(this.mOpt.ctarea).css("display", "none");
                    this.$(this.mOpt.tbarea).css("display", "");
                    this.refresh();
                    break;
                case DisplayType.CHART:
                    this.$(this.mOpt.tbarea).css("display", "none");
                    this.$(this.mOpt.ctarea).css("display", "");
                    this.refresh();
                    break;
                default:
                    break;
            }
        }

        public getContentType():ContentType{
            return ContentType.TABLE_CHART;
        }

        public hide():void {
            $("#" + this.mOpt.host).hide();
        }

        public show():void {
            $("#" + this.mOpt.host).show();
        }

        protected $(id:string):jQuery {
            return $("#" + this.mOpt.host + " #" + id);
        }

            public  update():void {
                this.pluginUpdate();
            }

            abstract  pluginUpdate():void;
    }

    //export enum FuturesType {
    //    CU,
    //    AL
    //}
}
