package com.tbea.ic.operation.controller.servlet.sbdczclwcqk;

public enum SbdczclwcqkType{

	SBDCZCLWCQK_CZ_BYQ(11),
	SBDCZCLWCQK_CZ_XL(12),
	SBDCZCLWCQK_CL_BYQ(13),
	SBDCZCLWCQK_CL_XL(14);
    
    private int _value;

    private SbdczclwcqkType(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }

}