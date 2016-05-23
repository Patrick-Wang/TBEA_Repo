///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>

module cpzlqk{
    export interface Option extends framework.basic.PluginOption {
        tb:string;
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
}