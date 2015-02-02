package com.tbea.ic.operation.common.companys;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class CompanyManager {
	public static enum CompanyType {
		SB		("沈变公司"),
		HB		("衡变公司"),
		XB		("新变厂"),
		//TB		("天变公司"),	
		LL		("鲁缆公司"),
		XL		("新缆厂"),
		DL		("德缆公司"),
		XNY		("新特能源公司"),
		GY		("新能源"),
		TCNY	("天池能源"),
		NDGS	("能动公司"),
		ZJWL	("中疆物流"),
		JCK		("进出口公司"),
		ZH		("众和公司"),
		SBDCY	("输变电产业"),
		XNYCY	("新能源产业"),
		NYCY	("能源产业"),
		GCL		("工程类"),
		JT		("集团"),
		BYQC	("沈变变压器厂"),
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
		XJXTGJWLMYGS	("新疆新特国际物流贸易公司"),
		XSZGS	("销售总公司"),
		SDFGS	("山东分公司"),
		GJCTZGS	("国际成套总公司"),
		LL_TYDXXMGS	("通用电线项目公司"),
		LL_JNDXXMGS	("节能导线项目公司"),
		DLCLFGS	("电缆材料分公司"),
		TZDLYFXMGS	("特种电缆研发项目公司"),
		DLJXSYB	("电缆经销事业部"),
		XNYSYB	("新能源事业部"),
		DLGCGS	("电缆工程公司"),
		SDDLGCGS	("山东电力工程公司"),
		KYDLXMGS	("矿用电缆项目公司"),
		XL_TYDXXMGS	("通用电线项目公司"),
		XL_JNDXXMGS	("节能导线项目公司"),
		TZDLXMGS	("特种电缆项目公司"),
		DLDLXMGS	("电力电缆项目公司"),
		TZDGXJDGCLYXGS	("特变电工新疆电工材料有限公司"),
		DKHYB	("大客户一部"),
		DKHEB	("大客户二部"),
		GJMYB	("国际贸易部"),
		GNGCGS	("国内工程公司"),
		GJGCGS	("国际工程公司"),
		ZJZTGJWLYXGS	("新疆中特国际物流有限公司"),
		TLGS	("特缆公司"),
		XTGS	("新特公司"),
		GCGS	("工程公司"),
		BB	("本部"),
		XSGS	("销售公司"),
		GJYWB	("国际业务部"),
		FLSWB	("法律事务部"),
		DYCJGJWLMYYXGS	("德阳川疆国际物流贸易有限公司"),
		XNDQGS	("西南电气公司"),
		BBXSGS	("本部销售公司"),
		
		//非正式公司
		DBSBDCYJT("东北输变电产业集团"),
		NFSBDCYJT("南方输变电产业集团"),
		XBCZT("新变厂整体"),
		BYQCY("变压器产业"),
		XLCY("线缆产业"),
		SBDCYJT("输变电产业集团");

		private String value;

		CompanyType(String value) {
			this.value = value;
		}

		public CompanyType setValue(String value){
			this.value = value;
			return this;
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

	Organization pzgh = new BMDepartmentPzgh();
	Organization opera = new OperationDepartment();
	Organization BM = new BMDepartment();
	Organization zbhz = new OperationZBHZ(new OperationDepartment());
	
	@Resource(type=BMDepartmentDB.class)
	Organization BMDB;
	
	Organization virtualYSZK = new VirtualYSZKOrganization(new BMDepartment());
 
	
	public Organization getVirtualYSZKOrganization() {
		return virtualYSZK;
	}
	
	public Organization getBMDBOrganization() {
		return BMDB;
	}
	
	public Organization getBMOrganization() {
		return BM;
	}

	public Organization getPzghOrganization() {
		return pzgh;
	}
	
	public Organization getOperationOrganization() {
		return opera;
	}

	public Organization getOperationZBHZOrganization(){
		return zbhz;
	}

}
