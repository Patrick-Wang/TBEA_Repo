/// <reference path="../../util.ts" />
/// <reference path="../../dateSelector.ts" />
/// <reference path="../../../js/jquery/jquery.d.ts" />
/// <reference path="../route/route.ts" />
///<reference path="../../unitedSelector.ts"/>
module framework.basic {

    export module endpoint {
        export let lastId : ()=>number= (function(idBase:number){
            return function(){
                return ++idBase;
            };
        })(100002);

        export let FRAME_ID : number = lastId();
    }


    export module FrameEvent {
        export let lastEvent : ()=>number = (function(idBase:number){
            return function(){
                return ++idBase;
            };
        })(9988392);

        export let FE_INIT_EVENT : number = lastEvent();
        export let FE_REGISTER : number = lastEvent();
        export let FE_SHOW : number = lastEvent();
        export let FE_HIDE : number = lastEvent();
        export let FE_REFRESH : number = lastEvent();
        export let FE_IS_COMPANY_SUPPORTED : number = lastEvent();
        export let FE_UPDATE : number = lastEvent();
        export let FE_GET_EXPORTURL : number = lastEvent();
        export let FE_SAVE : number = lastEvent();
        export let FE_SUBMIT : number = lastEvent();
        export let FE_PROXY : number = lastEvent();
        export let FE_APPROVE : number = lastEvent();
        export let FE_UNAPPROVE : number = lastEvent();
        export let FE_NOT_SUBMITTED : number = lastEvent();
        export let FE_SUBMITTED : number = lastEvent();
        export let FE_APPROVED : number = lastEvent();
        export let FE_GETUNIT : number = lastEvent();
    }
    export interface PluginOption {
        host:string;
        tbarea:string;
    }

    export abstract class BasicEndpoint implements framework.route.Endpoint {
        constructor() {
            router.register(this);
        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FrameEvent.FE_INIT_EVENT:
                    this.onInitialize(e.data);
                    break;
            }
            return true;
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
        getId():number {
            return endpoint.FRAME_ID;
        }

        onInitialize(opt:any):void {
            this.init(<FrameOption>opt);
        }

        onEvent(e:framework.route.Event):any {
            switch (e.id) {
                case FrameEvent.FE_REGISTER:
                    this.register(e.data, e.from);
                    break;
            }
            return super.onEvent(e);
        }

        protected abstract init(opt:FrameOption):void;

        protected abstract register(name:string, plugin:number):void;

        protected abstract unregister(name:string):number;
    }

    export abstract class BasePluginView extends BasicEndpoint {
        mOpt:PluginOption;

        onInitialize(opt:any):void {
            this.mOpt = opt;
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

        protected hide():void {
            $("#" + this.mOpt.host).hide();
        }

        protected show():void {
            $("#" + this.mOpt.host).show();
        }

        protected $(id:string):JQuery {
            return $("#" + this.mOpt.host + " #" + id);
        }

        protected isSupported(compType:Util.CompanyType):boolean {
            return true;
        }

        protected abstract  pluginUpdate(date:string, compType:Util.CompanyType):void;

        protected abstract refresh():void;

        protected abstract init(opt:PluginOption):void;

        abstract getId():number;
    }

    export abstract class ShowPluginView extends BasePluginView {
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
                case FrameEvent.FE_GETUNIT:
                {
                    val = this.pluginGetUnit();
                }
                    break;
                default:
                    break;
            }
            return val;
        }
        abstract  pluginGetExportUrl(date:string, compType:Util.CompanyType):string;
        pluginGetUnit():string{
            return undefined
        }
    }

    export abstract class EntryPluginView extends BasePluginView {
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

    export abstract class ApprovePluginView extends BasePluginView {
        onEvent(e:framework.route.Event):any {
            let val = super.onEvent(e);
            switch (e.id) {
                case FrameEvent.FE_APPROVE:
                {
                    let date:Util.Date = e.data.date;
                    let st:string = date.year + "-" + date.month + "-" + date.day;
                    this.pluginApprove(st, e.data.compType);
                }
                    break;
                case FrameEvent.FE_UNAPPROVE:
                {
                    let date:Util.Date = e.data.date;
                    let st:string = date.year + "-" + date.month + "-" + date.day;
                    this.pluginUnapprove(st, e.data.compType);
                }
                    break;
                default:
                    break;
            }
            return val;
        }
        abstract pluginApprove(dt:string, compType:Util.CompanyType):void;
        abstract pluginUnapprove(dt:string, compType:Util.CompanyType):void;
    }

    export class EndpointProxy implements framework.route.Endpoint{
        mId:number;
        mStub : number;
        constructor(id:number, stub : number){
            this.mId = id;
            this.mStub = stub;
            router.register(this);
        }

        getId():number {
            return this.mId;
        }
        onEvent(e:framework.route.Event):any {
            return router.redirect(this.mStub, e);
        }
    }
}
