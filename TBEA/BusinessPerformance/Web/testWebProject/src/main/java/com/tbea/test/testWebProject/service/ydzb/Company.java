package com.tbea.test.testWebProject.service.ydzb;

import java.util.HashMap;
import java.util.Map;

public class Company {

	public enum Type {
		SB, HB, XB, TB, LL, XL, DL, 
		XNY, GY,
		TCNY, NDGS, ZJWL,
		JCK,   GCGS,  
		ZH, 
		SBDCY, XNYCY, NYCY, GCL, JT
	}

	private static Map<String, Company> cyMap = null;
	
	private static Company companys[] = new Company[] {
			new Company("5", "沈变公司"),
			new Company("6", "衡变公司"),
			new Company("7", "新变厂"),
			new Company("8", "天变公司"),
			new Company("9", "鲁缆公司"),
			new Company("10", "新缆厂"),
			new Company("11", "德缆公司"),
			new Company("30", "新特能源公司"),
			new Company("29", "新能源"),
			new Company("66", "天池能源"),
			new Company("25", "能动公司"),
			new Company("74", "中疆物流"),
			new Company("23", "进出口公司"),
			new Company("70", "国际工程公司"),
			new Company("27", "众和公司"),		
			new CompanyGroup("999", "输变电产业", new Type[] { Type.SB, Type.HB,
					Type.XB, Type.TB, Type.LL, Type.XL, Type.DL }),
			new CompanyGroup("888", "新能源产业", new Type[] { Type.XNY, Type.GY}),
			new CompanyGroup("777", "能源产业", new Type[] { Type.TCNY, Type.NDGS,
					Type.ZJWL }),
			new CompanyGroup("666", "工程类", new Type[] { Type.JCK, Type.GCGS }),
			new CompanyGroup("9999", "集团", new Type[] { Type.SB, Type.HB,
					Type.XB, Type.TB, Type.LL, Type.XL, Type.DL, Type.JCK,
					Type.NDGS, Type.ZH, Type.XNY, Type.GY, Type.TCNY,
					Type.GCGS, Type.ZJWL }) };

	private String name;
	private String Id;

	protected Company(String id, String name) {
		super();
		this.name = name;
		Id = id;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return Id;
	}

	public static Company[] getAll(){
		return companys;
	}
	
	public static Company get(Type ct) {
		return companys[ct.ordinal()];
	}
	
	public static Company get(int ct) {
		return companys[ct];
	}
	
	public static Company get(String id) {
		if (cyMap == null){
			cyMap = new HashMap<String, Company>();
		}
		
		if (!cyMap.containsKey(id)){
			for (int i = companys.length - 1; i >= 0; --i){
				if (companys[i].getId().equals(id)){
					cyMap.put(id, companys[i]);
					break;
				}
			}
		}
		
		return cyMap.get(id);
	}
}
