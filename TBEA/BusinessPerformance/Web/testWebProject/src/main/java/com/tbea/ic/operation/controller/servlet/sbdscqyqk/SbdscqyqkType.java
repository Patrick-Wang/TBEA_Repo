package com.tbea.ic.operation.controller.servlet.sbdscqyqk;

public enum SbdscqyqkType{

    YLFX_WGCPYLNL_BYQ(0),
    YLFX_WGCPYLNL_XL(1);
    
    private int _value;

    private SbdscqyqkType(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }

}