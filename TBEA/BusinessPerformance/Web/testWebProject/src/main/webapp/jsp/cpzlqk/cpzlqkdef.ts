///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>

module cpzlqk{
    export interface Option extends framework.basic.PluginOption {
        tb:string;
        ct?:string;
        ctarea?:string;
    }

    export interface FrameOption extends framework.basic.Option {
        contentType: string;
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
    }

    export interface WaveItem{
        name : string;
        data : string[];
    }
}