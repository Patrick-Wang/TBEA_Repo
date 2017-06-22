///<reference path="../../basic/basic.ts"/>
///<reference path="../../basic/basicShow.ts"/>
///<reference path="../../../unitedSelector.ts"/>

module basicReport{
    export interface Option extends framework.basic.PluginOption {
        tb:string;
        items:Util.IDataNode[];
    }
}