///<reference path="../framework/basic/basic.ts"/>
///<reference path="../framework/basic/basicShow.ts"/>

module wgcpqk{
    export interface Option extends framework.basic.PluginOption {
        tb:string;
    }

    export enum WgcpqkType{
        BYQ,
        XL,
        
        YLFX_WGCPYLNL_BYQ_ZH = 11,
        YLFX_WGCPYLNL_BYQ_DYDJ = 12,
        YLFX_WGCPYLNL_BYQ_CPFL = 13,
        YLFX_WGCPYLNL_BYQ_CPFL_T1 = 14,
        YLFX_WGCPYLNL_XL_ZH = 15,
        YLFX_WGCPYLNL_XL_CPFL = 16
    }
}