///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>

module nwbzlqk{

    let echartsInit = echarts.init;
    echarts.init = function(e){
        $(e).empty();
        $(e).removeAttr("_echarts_instance_");
        return echartsInit.call(echarts, e);
    }

    export interface Option extends framework.basic.PluginOption {
        tb:string;
        ct?:string;
        ct1?:string;
        ctarea?:string;
        tips?:string
    }

    export enum PageType {
        NONE,
        APPROVE,
        ENTRY,
        SHOW
    }

    export interface FrameOption extends framework.basic.Option {
        contentType: string;
    }

    export enum YDJDType{
        YD,
        JD
    }

    export interface Comment{
        comment?:string;
        readonly?:string;
        deny?:string;
    }

    export interface CpzlqkResp{
        tjjg : string[][];
        waveItems: WaveItem[];
        waveX?:string[];
        status?:Util.ZBStatus;
        bhglx?:string[];
        zrlb?:string[];
        zt:number;
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