///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>

module plugin {
    let startId = 10293;
    export let byqacptjjg : number = ++startId;
    export let byqadwtjjg : number = ++startId;
    export let byqcpycssbhgwtmx : number = ++startId;
    export let byqybysqfyswtmx : number = ++startId;
    export let byqybyspbcpwtmx : number = ++startId;
    export let byqcpycssbhgxxfb : number = ++startId;
    export let byqybysqfysxxfb : number = ++startId;
    export let byqybyspbcpxxfb : number = ++startId;
    export let pdacptjjg : number = ++startId;
    export let pdadwtjjg : number = ++startId;
    export let pdcpycssbhgwtmx : number = ++startId;
    export let pdybysqfyswtmx : number = ++startId;
    export let pdybyspbcpwtmx : number = ++startId;
    export let pdcpycssbhgxxfb : number = ++startId;
    export let pdybysqfysxxfb : number = ++startId;
    export let pdybyspbcpxxfb : number = ++startId;
    export let xlacptjjg : number = ++startId;
    export let xladydjtjjg : number = ++startId;
    export let xlbhgcpmx : number = ++startId;
}

module cpzlqk{



    import ZBStatus = Util.ZBStatus;
    export interface Option extends framework.basic.PluginOption {
        tb:string;
        ct?:string;
        ct1?:string;
        ctarea?:string;
        tips?:string;
        tableStatus:TableStatus[];
    }

    export interface FrameOption extends framework.basic.Option {
        contentType: string;
    }

    export enum PageType {
        NONE,
        APPROVE,
        ENTRY,
        SHOW
    }

    export interface Comment{
        comment?:string;
        readonly?:string;
        deny?:string;
        zt?:ZBStatus;
    }

    export enum YDJDType{
        YD,
        JD
    }

    export interface TableStatus{
        id:number;
        status:ZBStatus;
    }

    export interface CpzlqkResp{
        tjjg : string[][];
        waveItems: WaveItem[];
        waveX?:string[];
        zt:number;
        status?:Util.ZBStatus;
        bhglx?:string[];
        zrlb?:string[];
    }

    export interface WaveItem{
        name : string;
        data : string[];
    }

    import FrameEvent = framework.basic.FrameEvent;

    export module Event{
        export let ZLFE_IS_COMPANY_SUPPORTED : number = FrameEvent.lastEvent();
        export let ZLFE_IS_YDJD_SUPPORTED : number = FrameEvent.lastEvent();
        export let ZLFE_YD_SELECTED : number = FrameEvent.lastEvent();
        export let ZLFE_JD_SELECTED : number = FrameEvent.lastEvent();
        export let ZLFE_IS_BHGLX_SUPPORTED : number = FrameEvent.lastEvent();
        export let ZLFE_SET_ZBSTATUS : number = FrameEvent.lastEvent();
        export let ZLFE_SAVE_COMMENT : number = FrameEvent.lastEvent();
        export let ZLFE_APPROVE_COMMENT : number = FrameEvent.lastEvent();
        export let ZLFE_APPROVE_COMMENT1 : number = FrameEvent.lastEvent();
        export let ZLFE_APPROVE_COMMENT2 : number = FrameEvent.lastEvent();
        export let ZLFE_APPROVE_COMMENT3 : number = FrameEvent.lastEvent();
        export let ZLFE_COMMENT_UPDATED : number = FrameEvent.lastEvent();
        export let ZLFE_DATA_STATUS : number = FrameEvent.lastEvent();
        export let ZLFE_COMMENT_DENY : number = FrameEvent.lastEvent();
        export let ZLFE_APPROVEAUTH_UPDATED : number = FrameEvent.lastEvent();
    }

    export enum NwbzlType {
        ZT,
        SJZL,
        SCZZ
    }
}