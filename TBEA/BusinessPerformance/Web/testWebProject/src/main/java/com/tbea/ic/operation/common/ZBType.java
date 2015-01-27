package com.tbea.ic.operation.common;


public enum ZBType {
	 QNJH,
     BY20JH,
     BY28JH,
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
