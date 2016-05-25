///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>

module cpzlqk{
    export interface Option extends framework.basic.PluginOption {
        tb:string;
        ct?:string;
        ctarea?:string;
        tips?:string
    }

    export interface FrameOption extends framework.basic.Option {
        contentType: string;
        bhglx:string;
    }

    export enum YDJDType{
        YD,
        JD
    }

    export enum ByqBhgType{
        YBYSQFJYS,
        PBCP
    }

    export interface CpzlqkResp{
        tjjg : string[][];
        waveItems: WaveItem[];
        waveX?:string[];
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
        export let ZLFE_GET_BHGLX : number = FrameEvent.lastEvent();
    }
}