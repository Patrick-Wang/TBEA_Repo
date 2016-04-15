/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../../js/jquery/jquery.d.ts" />
module yszkgb {
    export interface PluginView {
        hide (): void;
        show () : void;
        update (date:Util.Date, cpType:Util.CompanyType) : void;
        refresh():void;
        getExportUrl(date:Util.Date, cpType:Util.CompanyType):string;
    }

    export interface FrameView {
        register(name:string, plugin:PluginView):void;
        unregister(name:string) :PluginView;
        exportExcel(elemId:string):void;
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

        public  update(date:Util.Date, cpType:Util.CompanyType):void {
            let st = date.year + "-" + date.month + "-" + date.day;
            this.pluginUpdate(st, cpType);
        }

        abstract  pluginUpdate(date:string, cpType:Util.CompanyType):void;

        getExportUrl(date:Util.Date, cpType:Util.CompanyType):string{
            let st = date.year + "-" + date.month + "-" + date.day;
            return this.pluginGetExportUrl(st, cpType);
        }

        abstract  pluginGetExportUrl(date:string, cpType:Util.CompanyType):string;
    }

    export interface EntryPluginView {
        hide (): void;
        show () : void;
        update (date:Util.Date, cpType:Util.CompanyType) : void;
        refresh():void;
        setOnReadOnlyChangeListener(callBack:(isReadOnly:boolean)=>void);
        save(date:Util.Date, cpType:Util.CompanyType):void;
        submit(date:Util.Date, cpType:Util.CompanyType):void;
    }

    export interface EntryFrameView {
        register(name:string, plugin:EntryPluginView):void;
        unregister(name:string) :EntryPluginView;
    }

    export abstract class BaseEntryPluginView implements EntryPluginView{
        private mReadOnlyChange:(isReadOnly:boolean)=>void;

        setOnReadOnlyChangeListener(callBack:(isReadOnly:boolean)=>void){
            this.mReadOnlyChange = callBack;
        }
        mOpt:PluginOption;
        public init(opt:PluginOption):void {
            this.mOpt = opt;
        }

        public hide():void {
            $("#" + this.mOpt.host).hide();
        }

        public show():void {
            $("#" + this.mOpt.host).show();
        }

        public raiseReadOnlyChangeEvent(isReadOnly:boolean):void{
            if (undefined != this.mReadOnlyChange){
                this.mReadOnlyChange(isReadOnly);
            }
        }

        protected $(id:string):jQuery {
            return $("#" + this.mOpt.host + " #" + id);
        }

        public  update(start:Util.Date, cpType:Util.CompanyType):void {
            let st = start.year + "-" + start.month + "-" + start.day;
            this.pluginUpdate(st, cpType);
        }

        public save(date:Util.Date, cpType:Util.CompanyType):void{
            let dt:string = date.year + "-" + date.month + "-" + date.day;
            this.pluginSave(dt, cpType);
        }
        public submit(date:Util.Date, cpType:Util.CompanyType):void{
            let dt:string = date.year + "-" + date.month + "-" + date.day;
            this.pluginSubmit(dt, cpType);
        }

        abstract refresh():void;
        abstract  pluginUpdate(date:string, cpType:Util.CompanyType):void;
        abstract pluginSave(dt:string, cpType:Util.CompanyType):void;
        abstract pluginSubmit(dt:string, cpType:Util.CompanyType):void;

    }

    export interface StatusData{
        readOnly: boolean;
        data:Array<string[]>;
    }
}
