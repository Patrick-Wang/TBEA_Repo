package com.tbea.ic.operation.service.wlydd.wlyddmlspcs;

public enum MLSPCS_XL_CPFL_Type{

	MLSPCS_XL_CPFL_DX(59),
	MLSPCS_XL_CPFL_BDX(60),
	MLSPCS_XL_CPFL_JKX(61),
	MLSPCS_XL_CPFL_KZDL(62),
	MLSPCS_XL_CPFL_JLDL(63),
	MLSPCS_XL_CPFL_DLDL(64),
	MLSPCS_XL_CPFL_DCX(65),
	MLSPCS_XL_CPFL_TZDL(66),
	MLSPCS_XL_CPFL_DLFJ(67),
	MLSPCS_XL_CPFL_TG(68),
	MLSPCS_XL_CPFL_LG(69),
	MLSPCS_XL_CPFL_PVC(70),
	MLSPCS_XL_CPFL_GZL(71),
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