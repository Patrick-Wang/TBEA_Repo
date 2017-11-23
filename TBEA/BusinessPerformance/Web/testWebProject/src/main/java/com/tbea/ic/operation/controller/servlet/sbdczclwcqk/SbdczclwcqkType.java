package com.tbea.ic.operation.controller.servlet.sbdczclwcqk;

public enum SbdczclwcqkType{

	SBDCZCLWCQK_CZ_BYQ(11),
	SBDCZCLWCQK_CZ_XL(12),
	SBDCZCLWCQK_CL_BYQ(13),
	SBDCZCLWCQK_CLYLWCQK_XL(14),
	SBDCZCLWCQK_CL_XL(15),
    SBDCZCLWCQK_BYQ(16),
    SBDCZCLWCQK_XL(17);
    
    private int _value;

    private SbdczclwcqkType(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }
    
    public static SbdczclwcqkType valueOf(Integer val){
    	SbdczclwcqkType[] types = SbdczclwcqkType.values();
		for (int i = types.length - 1; i >= 0; --i) {
			if (types[i].value() == val) {
				return types[i];
			}
		}
		return null;
    }

}