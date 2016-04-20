/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../../js/jquery/jquery.d.ts" />
/// <reference path="../route/route.ts" />
///<reference path="../../unitedSelector.ts"/>
module framework.basic {

    export module FrameEvent {
        let eventBase = 9988392;

        export function lastEvent():number {
            return ++eventBase;
        }

        export let FE_INIT_EVENT = lastEvent();
        export let FE_REGISTER = lastEvent();
        export let FE_EXPORT_EXCEL = lastEvent();
        export let FE_SHOW = lastEvent();
        export let FE_HIDE = lastEvent();
        export let FE_REFRESH = lastEvent();
        export let FE_IS_COMPANY_SUPPORTED = lastEvent();
        export let FE_UPDATE = lastEvent();
        export let FE_GET_EXPORTURL = lastEvent();
        export let FE_SAVE = lastEvent();
        export let FE_SUBMIT = lastEvent();
    }
    export interface PluginOption {
        host:string;
        tbarea:string;
    }

    export abstract class BasicEndpoint implements framework.route.Endpoint {
        constructor() {
            framework.route.router.register(this);
        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
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

        getId():number {
            return FrameView.FRAME_ID;
        }

        onInitialize(opt:any):void {
            this.init(<FrameOption>opt);
        }

        onEvent(e:framework.route.Event):any {
            super.onEvent(e);
            switch (e.id) {
                case FrameEvent.FE_REGISTER:
                    this.register(e.data.name, e.data.plugin);
                    break;
            }
        }

        protected abstract init(opt:FrameOption):void;

        protected abstract register(name:string, plugin:number):void;

        protected abstract unregister(name:string):number;
    }

    export abstract class BasePluginView extends BasicEndpoint {
        mOpt:PluginOption;

        onInitialize(opt:any):void {
            this.init(<PluginOption>opt);
        }

        onEvent(e:framework.route.Event):any {
            let val = super.onEvent(e);
            switch (e.id) {
                case FrameEvent.FE_SHOW:
                    this.show();
                    break;
                case FrameEvent.FE_HIDE:
                    this.hide();
                    break;
                case FrameEvent.FE_REFRESH:
                    this.refresh();
                    break;
                case FrameEvent.FE_UPDATE:
                {
                    let date:Util.Date = e.data.date;
                    let st:string = date.year + "-" + date.month + "-" + date.day;
                    this.pluginUpdate(st, e.data.compType);
                }
                    break;
                case FrameEvent.FE_IS_COMPANY_SUPPORTED:
                    val = this.isSupported(e.data);
                    break;
                default:
                    break;
            }
            return val;
        }

        private hide():void {
            $("#" + this.mOpt.host).hide();
        }

        private show():void {
            $("#" + this.mOpt.host).show();
        }

        protected $(id:string):jQuery {
            return $("#" + this.mOpt.host + " #" + id);
        }

        isSupported(compType:Util.CompanyType):boolean {
            return true;
        }

        abstract  pluginUpdate(date:string, compType:Util.CompanyType):void;

        abstract refresh():void;

        abstract init(opt:PluginOption):void;

        abstract getId():number;
    }

    export abstract class ShowPluginView extends BasicEndpoint {
        onEvent(e:framework.route.Event):any {
            let val = super.onEvent(e);
            switch (e.id) {
                case FrameEvent.FE_GET_EXPORTURL:
                {
                    let date:Util.Date = e.data.date;
                    let st:string = date.year + "-" + date.month + "-" + date.day;
                    val = this.pluginGetExportUrl(st, e.data.compType);
                }
                    break;
                default:
                    break;
            }
            return val;
        }
        abstract  pluginGetExportUrl(date:string, compType:Util.CompanyType):string;
    }

    export abstract class EntryPluginView extends BasicEndpoint {
        onEvent(e:framework.route.Event):any {
            let val = super.onEvent(e);
            switch (e.id) {
                case FrameEvent.FE_SAVE:
                {
                    let date:Util.Date = e.data.date;
                    let st:string = date.year + "-" + date.month + "-" + date.day;
                    this.pluginSave(st, e.data.compType);
                }
                    break;
                case FrameEvent.FE_SUBMIT:
                {
                    let date:Util.Date = e.data.date;
                    let st:string = date.year + "-" + date.month + "-" + date.day;
                    this.pluginSubmit(st, e.data.compType);
                }
                    break;
                default:
                    break;
            }
            return val;
        }
        abstract pluginSave(dt:string, compType:Util.CompanyType):void;
        abstract pluginSubmit(dt:string, compType:Util.CompanyType):void;
    }

    export interface StatusData {
        readOnly: boolean;
        data:Array<string[]>;
    }
}
