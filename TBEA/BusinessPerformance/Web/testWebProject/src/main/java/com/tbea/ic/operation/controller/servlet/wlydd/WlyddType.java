package com.tbea.ic.operation.controller.servlet.wlydd;

public enum WlyddType{

    SCDY(0),
    SCLB(1),
    YLFX_WLYMLSP_BYQ_ZXYW(11),
    YLFX_WLYMLSP_BYQ_DYDJ(12),
    YLFX_WLYMLSP_BYQ_CPFL(13),
    YLFX_WLYMLSP_XL_ZXYW(14),
    YLFX_WLYMLSP_XL_ZZY(15),
    YLFX_WLYMLSP_BYQ_ZZY(16);
    
    private int _value;

    private WlyddType(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }

}