///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>

module cpzlqkyd{
    export interface Option extends framework.basic.PluginOption {
        tb:string;
    }

    export enum CpzlqkType{
        CpzlqkType_TJJG_BYQ_CP = 1,
        CpzlqkType_TJJG_BYQ_DW = 2,
        CpzlqkType_TJJG_XL_CP = 3,
        CpzlqkType_TJJG_XL_DYDJ = 4
    }
}