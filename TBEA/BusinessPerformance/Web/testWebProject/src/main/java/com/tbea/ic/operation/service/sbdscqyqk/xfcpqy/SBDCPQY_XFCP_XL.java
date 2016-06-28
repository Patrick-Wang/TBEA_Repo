package com.tbea.ic.operation.service.sbdscqyqk.xfcpqy;

public enum SBDCPQY_XFCP_XL{

	SBDCZCLWCQK_CZ_XL_CPFL_DX(59),
	SBDCZCLWCQK_CZ_XL_CPFL_BDX(60),
	SBDCZCLWCQK_CZ_XL_CPFL_JKX(61),
	SBDCZCLWCQK_CZ_XL_CPFL_KZDL(62),
	SBDCZCLWCQK_CZ_XL_CPFL_JLDL(63),
	SBDCZCLWCQK_CZ_XL_CPFL_DLDL(64),
	SBDCZCLWCQK_CZ_XL_CPFL_DCX(65),
	SBDCZCLWCQK_CZ_XL_CPFL_TZDL(66),
	SBDCZCLWCQK_CZ_XL_CPFL_DLFJ(67),
	SBDCZCLWCQK_CZ_XL_ZH_QT(1);
//	SBDCZCLWCQK_CZ_XL_GNGC(75),
//	SBDCZCLWCQK_CZ_XL_GJGC(76),
//	SBDCZCLWCQK_CZ_XL_FWL(11);
    
    private int _value;

    private SBDCPQY_XFCP_XL(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }
}