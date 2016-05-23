package com.tbea.ic.operation.common;


public enum ZBType {
    NDJH,
    YDJDMJH,
    BY20YJ,
    BY28YJ,
    BYSJ;
    public static ZBType valueOf(Integer val){
    	ZBType[] types = ZBType.values();
		if (types.length > val){
			return types[val];
		}
		return null;
    }
}
