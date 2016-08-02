///<reference path="../../basic/basic.ts"/>
///<reference path="../../basic/basicShow.ts"/>

module basicReport{
    export interface Option extends framework.basic.PluginOption {
        tb:string;
        ids:string[];
        names:string[];
    }
}