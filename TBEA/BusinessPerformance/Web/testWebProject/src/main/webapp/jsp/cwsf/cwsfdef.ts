///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>

module cwsf{
    export interface Option extends framework.basic.PluginOption {
        tb:string;
        updateUrl:string;
        exportUrl:string;
        title:string;
    }
}