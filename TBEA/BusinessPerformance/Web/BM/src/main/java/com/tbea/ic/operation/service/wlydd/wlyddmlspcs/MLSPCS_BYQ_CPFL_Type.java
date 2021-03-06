package com.tbea.ic.operation.service.wlydd.wlyddmlspcs;

public enum MLSPCS_BYQ_CPFL_Type{

	MLSPCS_BYQ_CPFL_GSBYQ(42),
	MLSPCS_BYQ_CPFL_GSBYQ_F(43),
	MLSPCS_BYQ_CPFL_GSBYQ_H(44),
	MLSPCS_BYQ_CPFL_GSBYQ_XSBDZ(45),
	MLSPCS_BYQ_CPFL_GSBYQ_GSDKQ(46),
	MLSPCS_BYQ_CPFL_TZBYQ(47),
	MLSPCS_BYQ_CPFL_TZBYQ_GBB(48),
	MLSPCS_BYQ_CPFL_TZBYQ_ZLB(49),
	MLSPCS_BYQ_CPFL_TZBYQ_QYB(50),
	MLSPCS_BYQ_CPFL_TZBYQ_YTB(51),
	MLSPCS_BYQ_CPFL_TZBYQ_QT(52),
	MLSPCS_BYQ_CPFL_YSL(53),
	MLSPCS_BYQ_CPFL_YSL_PDZDH(54),
	MLSPCS_BYQ_CPFL_YSL_KG(55),
	MLSPCS_BYQ_CPFL_YSL_TG(56),
	MLSPCS_BYQ_CPFL_YSL_HGQ(57),
	MLSPCS_BYQ_CPFL_YSLWXPJ(58);
	
	
    private int _value;

    private MLSPCS_BYQ_CPFL_Type(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }
}