package com.tbea.ic.operation.common.excel;

public enum YlfxwlyddmlspcsSheetType{
    YLFX_WLYMLSP_BYQ_ZH(11),
    YLFX_WLYMLSP_BYQ_DYDJ(12),
    YLFX_WLYMLSP_BYQ_CPFL(13),
    YLFX_WLYMLSP_XL_ZH(14),
    YLFX_WLYMLSP_XL_CPFL(15),
    END(16);
    
    private int _value;

    private YlfxwlyddmlspcsSheetType(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }
}