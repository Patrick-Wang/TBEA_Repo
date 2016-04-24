package com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs;

public enum WGCPYLNL_XL_CPFL_Type{

	WGCPYLNL_XL_CPFL_DX(59),
	WGCPYLNL_XL_CPFL_BDX(60),
	WGCPYLNL_XL_CPFL_JKX(61),
	WGCPYLNL_XL_CPFL_KZDL(62),
	WGCPYLNL_XL_CPFL_JLDL(63),
	WGCPYLNL_XL_CPFL_DLDL(64),
	WGCPYLNL_XL_CPFL_DCX(65),
	WGCPYLNL_XL_CPFL_TZDL(66),
	WGCPYLNL_XL_CPFL_DLFJ(67),
	WGCPYLNL_XL_CPFL_TG(68),
	WGCPYLNL_XL_CPFL_LG(69),
	WGCPYLNL_XL_CPFL_PVC(70),
	WGCPYLNL_XL_CPFL_GZL(71),
	WGCPYLNL_XL_ZH_QT(1);
    
    private int _value;

    private WGCPYLNL_XL_CPFL_Type(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }
}