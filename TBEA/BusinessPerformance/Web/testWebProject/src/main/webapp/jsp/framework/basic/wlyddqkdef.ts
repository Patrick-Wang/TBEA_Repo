/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../../js/jquery/jquery.d.ts" />
/// <reference path="../route/route.ts" />
///<reference path="../../unitedSelector.ts"/>
module framework.basic {

    export module FrameEvent{
        let eventBase = 9988392;
        export let FE_INIT_EVENT = eventBase++;
        export let FE_REGISTER = eventBase++;
        export let FE_EXPORT_EXCEL = eventBase++;
    }

    export interface PluginView {
        hide (): void;
        show () : void;
        update (date:Util.Date, compType:Util.CompanyType) : void;
        refresh():void;
        getExportUrl(date:Util.Date, compType:Util.CompanyType):string;
        isSupported( compType:Util.CompanyType):boolean;
    }

    export interface PluginOption {
        host:string;
        tbarea:string;
    }

    export abstract class BasicEndpoint implements framework.route.Endpoint{
        constructor(){
            framework.route.router.register(this);
        }

        onEvent(e:framework.route.Event):any{
            switch (e.id){
                case FrameEvent.FE_INIT_EVENT:
                    this.onInitialize(e.data);
                    break;
            }
        }

        abstract getId():number;
        abstract onInitialize(opt:any):void;
    }


    interface FrameOption {
        dt:string;
        type:string;
        comp:string;
        comps: Util.IDataNode[];
        date: Util.Date;
    }


    export abstract class FrameView extends BasicEndpoint {
        static FRAME_ID:number = 0;
        getId():number{
            return FrameView.FRAME_ID;
        }
        onInitialize(opt:any):void{
            this.init(<FrameOption>opt);
        }
        onEvent(e:framework.route.Event):any{
            super.onEvent(e);
            switch (e.id){
                case FrameEvent.FE_REGISTER:
                    this.register(e.data.name, e.data.pluginView);
                    break;
                case FrameEvent.FE_EXPORT_EXCEL:
                    this.exportExcel(e.data);
                    break;
            }
        }

        abstract init(opt:FrameOption):void;
        abstract register(name:string, plugin:PluginView):void;
        abstract unregister(name:string) :PluginView;
        abstract exportExcel(elemId:string):void;
    }



    export abstract class BasePluginView implements PluginView, framework.route.Endpoint {

        static INIT_EVENT = 9988392;
        constructor(){
            framework.route.router.register(this);
        }

        onEvent(e:framework.route.Event):any{
            switch (e.id){
                case BasePluginView.INIT_EVENT:
                    this.init(e.data);
                    break;
            }
        }

        mOpt:PluginOption;

        private init(opt:PluginOption):void {
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

        public  update(date:Util.Date, compType:Util.CompanyType):void {
            let st = date.year + "-" + date.month + "-" + date.day;
            this.pluginUpdate(st, compType);
        }

        abstract  pluginUpdate(date:string, compType:Util.CompanyType):void;

        getExportUrl(date:Util.Date, compType:Util.CompanyType):string{
            let st = date.year + "-" + date.month + "-" + date.day;
            return this.pluginGetExportUrl(st, compType);
        }

        isSupported( compType:Util.CompanyType):boolean{
            return true;
        }

        abstract getId():number;
        abstract  pluginGetExportUrl(date:string, compType:Util.CompanyType):string;
    }

    export interface EntryPluginView {
        hide (): void;
        show () : void;
        update (date:Util.Date, compType:Util.CompanyType) : void;
        refresh():void;
        setOnReadOnlyChangeListener(callBack:(isReadOnly:boolean)=>void);
        save(date:Util.Date, compType:Util.CompanyType):void;
        submit(date:Util.Date, compType:Util.CompanyType):void;
        isSupported( compType:Util.CompanyType):boolean;
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

        mType : WlyddType;
        public init(opt:PluginOption):void {
            this.mOpt = opt;
        }


        public setType(type : WlyddType):void{
            this.mType = type;
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

        public  update(start:Util.Date, compType:Util.CompanyType):void {
            let st = start.year + "-" + start.month + "-" + start.day;
            this.pluginUpdate(st, compType);
        }

        public save(date:Util.Date, compType:Util.CompanyType):void{
            let dt:string = date.year + "-" + date.month + "-" + date.day;
            this.pluginSave(dt, compType);
        }
        public submit(date:Util.Date, compType:Util.CompanyType):void{
            let dt:string = date.year + "-" + date.month + "-" + date.day;
            this.pluginSubmit(dt, compType);
        }

        isSupported( compType:Util.CompanyType):boolean{
            return true;
        }

        abstract refresh():void;
        abstract  pluginUpdate(date:string, compType:Util.CompanyType):void;
        abstract pluginSave(dt:string, compType:Util.CompanyType):void;
        abstract pluginSubmit(dt:string, compType:Util.CompanyType):void;

    }

    export class TypeEntryViewProxy implements EntryPluginView{
        mStub : BaseEntryPluginView;

        mType : WlyddType;

        constructor(stub : BaseEntryPluginView,  type : WlyddType){

            this.mStub = stub;
            this.mType = type;
        }

        isSupported( compType:Util.CompanyType):boolean{
            this.mStub.setType(this.mType);
            return this.mStub.isSupported(compType);
        }

        hide():void {
            this.mStub.hide();
        }

        show():void {
            this.mStub.show();
        }

        update(date:Util.Date, compType:Util.CompanyType):void {
            this.mStub.setType(this.mType);
            this.mStub.update(date, compType);
        }

        refresh():void {
            this.mStub.refresh();
        }

        setOnReadOnlyChangeListener(callBack:(isReadOnly:boolean)=>void) {
            this.mStub.setOnReadOnlyChangeListener(callBack);
        }

        save(date:Util.Date, compType:Util.CompanyType):void {
            this.mStub.save(date, compType);
        }

        submit(date:Util.Date, compType:Util.CompanyType):void {
            this.mStub.submit(date, compType);
        }
    }

    export interface StatusData{
        readOnly: boolean;
        data:Array<string[]>;
    }
}
