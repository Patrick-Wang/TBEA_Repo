/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
///<reference path="yszkgb.ts"/>
module yszkgb {
    export interface PluginView {
        hide (): void;
        show () : void;
        update (date:Util.Date, cpType:Util.CompanyType) : void;
        refresh():void;
        getExportUrl(date:Util.Date, cpType:Util.CompanyType):string;
        adjustSize();
    }

    export interface PluginOption {
        host:string;
        tbarea:string;
        tb:string;
        ctarea:string;
        ct:string;
        ctarea1:string;
        ct1:string;
    }

    export abstract class BasePluginView implements PluginView, framework.route.Endpoint{

        mOpt:PluginOption;
        mId:any;

        constructor(id:string){
            this.mId = id;
            framework.router.register(this);
        }

        getId():any{
            return this.mId;
        }

        onEvent(e:framework.route.Event):any{
            switch (e.id){
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
            }
        }

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

        protected $(id:string):any {
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

        jqgrid(){
            return this.$(this.jqgridName());
        }


        jqgridHost():any{
            return this.$(this.mOpt.tb);
        }


        jqgridName():string{
            return this.mOpt.host + this.mOpt.tb + "_jqgrid_real";
        }
    }

    export interface EntryPluginView {
        hide (): void;
        show () : void;
        update (date:Util.Date, cpType:Util.CompanyType) : void;
        refresh():void;
        setOnReadOnlyChangeListener(callBack:(isReadOnly:boolean)=>void);
        save(date:Util.Date, cpType:Util.CompanyType):void;
        submit(date:Util.Date, cpType:Util.CompanyType):void;
        adjustSize();
    }

    export abstract class BaseEntryPluginView implements EntryPluginView, framework.route.Endpoint{
        private mReadOnlyChange:(isReadOnly:boolean)=>void;

        setOnReadOnlyChangeListener(callBack:(isReadOnly:boolean)=>void){
            this.mReadOnlyChange = callBack;
        }
        mOpt:PluginOption;
        mId:any;

        constructor(id:string){
            this.mId = id;
            framework.router.register(this);
        }

        getId():any{
            return this.mId;
        }

        onEvent(e:framework.route.Event):any{
            switch (e.id){
                case Util.MSG_INIT:
                    this.init(e.data);
                    break;
            }
        }

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

        protected $(id:string):any {
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

        jqgrid(){
            return this.$(this.jqgridName());
        }


        jqgridHost():any{
            return this.$(this.mOpt.tb);
        }


        jqgridName():string{
            return this.mOpt.host + this.mOpt.tb + "_jqgrid_real";
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
