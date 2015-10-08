package com.tbea.ic.operation.service.market.pipe;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;

public class MarketUnit extends Company {

	public static enum Type{
		INDUSTRY,
		AREA,
		COMPANY
	}
	
	private String name;
	
	private MarketUnit(Integer id, CompanyType type) {
		super(id, type);
	}

	public MarketUnit(String name, Type type){
		this(type.ordinal(), CompanyType.UNKNOWN);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
