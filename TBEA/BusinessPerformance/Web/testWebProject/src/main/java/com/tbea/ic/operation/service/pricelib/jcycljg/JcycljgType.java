package com.tbea.ic.operation.service.pricelib.jcycljg;


public enum JcycljgType {
	YSJS, 
	GGP,
	GJYY,
	TKS,
	JT,
	FGC,
	LZBB,
	ZHB,
	GX,
	PVCSZ,
	DMDJYX,
	EVA,
	JKZJ,
	MYZS,
	LWG;
	
	public static JcycljgType valueOf(int val){
		JcycljgType[] types = JcycljgType.values();
		if (types.length > val){
			return types[val];
		}
		return null;
    }
}
