package com.tbea.test.testWebProject.common;

public class CompanyManager {
	public enum CompanyType {
		SB		("沈变公司"),
		HB		("衡变公司"),
		XB		("新变厂"),
		TB		("天变公司"),	
		LL		("鲁缆公司"),
		XL		("新缆厂"),
		DL		("德缆公司"),
		XNY		("新特能源公司"),
		GY		("新能源"),
		TCNY	("天池能源"),
		NDGS	("能动公司"),
		ZJWL	("中疆物流"),
		JCK		("进出口公司"),
		GCGS	("国际工程公司"),
		ZH		("众和公司"),
		SBDCY	("输变电产业"),
		XNYCY	("新能源产业"),
		NYCY	("能源产业"),
		GCL		("工程类"),
		JT		("集团"),
		BYQC	("变压器厂"),
		ZTFGS	("中特分公司"),
		KJHGQ	("康嘉互感器"),
		SKGS	("上开公司"),
		DQFGS	("电气分公司"),
		ZTGS	("中特公司"),
		XBGS	("箱变公司");

		private final String value;

		CompanyType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		protected Company getCompany(int id) {
			return new Company(id, this);
		}
		
		public static CompanyType valueOf(int tyOrd){
			CompanyType[] types = CompanyType.values();
			if (types.length > tyOrd){
				return types[tyOrd];
			}
			return null;
		}
	}

	static Organization pzgh = new BMDepartmentPzgh();
	static Organization opera = new OperationDepartment();

	public static Organization getPzghOrganization() {
		return pzgh;
	}

	public static Organization getOperationOrganization() {
		return opera;
	}


}
