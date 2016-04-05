/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../../js/jquery/jquery.d.ts" />
module jcycljg {

    export enum DateType{
        DAY,
        MONTH
    }

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
        switch(type:DisplayType):void;
        update (start:Util.Date, end:Util.Date) : void;
        getDateType():DateType;
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

        public switch(type:jcycljg.DisplayType):void {
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

        public  getDateType():DateType {
            return DateType.DAY;
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

        public  update(start:Util.Date, end:Util.Date):void {
            let st = start.year + "-" + start.month + "-" + start.day;
            let ed = end.year + "-" + end.month + "-" + end.day;
            this.pluginUpdate(st, ed);
        }

        abstract  pluginUpdate(start:string, end:string):void;
    }

    export enum JcycljgType {
        YSJS,
        GGP,
        GJYY,
        TKS,
        JT,
        FGC,
        LZBB,
        ZHB,
        GX,
        PVCSZ,
        DMDJYX,
        EVA,
        JKZJ,
        MYZS,
        LWG,
        PMICPIPPI,
        YHJZLL
    }
}
