package com.tbea.ic.operation.controller.servlet.wgcpqk;

public enum WgcpqkType{

    YLFX_WGCPYLNL_BYQ_ZH(11),
    YLFX_WGCPYLNL_BYQ_MLL(12),
    YLFX_WGCPYLNL_XL_ZH(14),
    YLFX_WGCPYLNL_XL_CPFL(15);
    
    private int _value;

    private WgcpqkType(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }

}