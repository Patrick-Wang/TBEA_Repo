package com.tbea.ic.operation.service.wlydd.wlyddmlspcs;

public enum MLSPCS_XL_CPFL_Type{

	MLSPCS_XL_CPFL_DX(2),
	MLSPCS_XL_CPFL_BDX(2),
	MLSPCS_XL_CPFL_JKX(2),
	MLSPCS_XL_CPFL_KZDL(2),
	MLSPCS_XL_CPFL_JLDL(2),
	MLSPCS_XL_CPFL_DLDL(2),
	MLSPCS_XL_CPFL_DCX(2),
	MLSPCS_XL_CPFL_TZDL(2),
	MLSPCS_XL_CPFL_DLFJ(2),
	MLSPCS_XL_CPFL_TG(2),
	MLSPCS_XL_CPFL_LG(2),
	MLSPCS_XL_CPFL_PVC(2),
	MLSPCS_XL_CPFL_GZL(2),
	MLSPCS_XL_ZH_QT(1);
    
    private int _value;

    private MLSPCS_XL_CPFL_Type(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }
}