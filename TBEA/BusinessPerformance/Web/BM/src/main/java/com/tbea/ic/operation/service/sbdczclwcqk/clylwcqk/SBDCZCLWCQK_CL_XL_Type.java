package com.tbea.ic.operation.service.sbdczclwcqk.clylwcqk;

public enum SBDCZCLWCQK_CL_XL_Type{

	SBDCZCLWCQK_CL_XL_CPFL_CU(1),
	SBDCZCLWCQK_CL_XL_CPFL_AL(2);
	
    private int _value;

    private SBDCZCLWCQK_CL_XL_Type(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }
}