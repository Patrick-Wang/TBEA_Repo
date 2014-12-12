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
		GJMYCTGS	("国际贸易成套公司"),
		ZTFGS	("中特分公司"),
		KJHGQ	("康嘉互感器"),
		DQZJFGS	("电气组件分公司"),
		DLZDHGS	("电力自动化公司"),
		SKGS	("上开公司"),
		XSZX	("修试中心"),
		XDWLGS	("现代物流公司"),
		DLKCSJGS	("电力勘测设计公司"),
		XLGGS	("新利钢公司"),
		GNCTB	("国内成套部"),
		DLAZB	("电力安装部"),
		ZXGS	("中型公司"),
		HXGS	("和新公司"),
		TBDG_YD_NYYXGS	("特变电工（印度）能源有限公司"),
		SBWYGS	("沈变物业公司"),
		DQFGS	("电气分公司"),		
		HNGJWLGS	("湖南国际物流公司"),		
		HNGCGS	("湖南工程公司"),		
		ZYGS	("众业公司"),		
		HNZNDQGS	("湖南智能电气公司"),		
		NJZNDQGS	("南京智能电气公司"),		
		HNYLGS	("湖南园林公司"),
		TBGS	("天变公司"),		
		ZTGS	("中特公司"),		
		XBGS	("箱变公司"),		
		GJCTGCGS	("国际成套工程公司"),		
		GNGCJXGS	("国内工程检修公司"),		
		XJXTGJWLMYGS	("新疆新特国际物流贸易公司");
	

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
	static Organization BM = new BMDepartment();

	public static Organization getBMOrganization() {
		return BM;
	}

	public static Organization getPzghOrganization() {
		return pzgh;
	}
	
	public static Organization getOperationOrganization() {
		return opera;
	}


}
