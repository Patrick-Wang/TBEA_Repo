/// <reference path="../util.ts" />
/// <reference path="../dateSelector.ts" />
/// <reference path="../../js/jquery/jquery.d.ts" />
module wlyddqk {


    export enum WlyddType{
        SCDY,
        SCLB,
        YLFX_WLYMLSP_BYQ_ZH = 11,
        YLFX_WLYMLSP_BYQ_DYDJ = 12,
        YLFX_WLYMLSP_BYQ_CPFL = 13,
        YLFX_WLYMLSP_XL_ZH = 14,
        YLFX_WLYMLSP_XL_CPFL = 15
    }

    export interface EntryLyddData{
        cplb:string[];
        statusData : StatusData;
    }

    export interface PluginView {
        hide (): void;
        show () : void;
        update (date:Util.Date, compType:Util.CompanyType) : void;
        refresh():void;
        getExportUrl(date:Util.Date, compType:Util.CompanyType):string;
        isSupported( compType:Util.CompanyType):boolean;
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

        mType : WlyddType;
        public init(opt:PluginOption):void {
            this.mOpt = opt;
        }

        public setType(type : WlyddType):void{
            this.mType = type;
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

        abstract  pluginGetExportUrl(date:string, compType:Util.CompanyType):string;
    }

    export class TypeViewProxy implements PluginView{
        mStub : BasePluginView;

        mType : WlyddType;

        constructor(stub : BasePluginView,  type : WlyddType){
            this.mStub = stub;
            this.mType = type;
        }

        hide():void {
            this.mStub.hide();
        }

        show():void {
            this.mStub.show();
        }

        isSupported( compType:Util.CompanyType):boolean{
            this.mStub.setType(this.mType);
            return this.mStub.isSupported(compType);
        }

        update(date:Util.Date, compType:Util.CompanyType):void {
            this.mStub.setType(this.mType);
            this.mStub.update(date, compType);
        }

        refresh():void {
            this.mStub.refresh();
        }

        getExportUrl(date:Util.Date, compType:Util.CompanyType):string {
            return this.mStub.getExportUrl(date, compType);
        }
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
