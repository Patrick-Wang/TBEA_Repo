package com.tbea.ic.operation.common.companys;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class CompanyManager {
	public static enum CompanyType {
		BB		("本部"),
		BBXSGS		("本部销售公司"),
		BJFGS		("北京分公司"),
		BYQC		("沈变变压器厂"),
		DBGS		("电材公司"),
		DJBGS		("电极箔公司"),
		DJGEGS		("多晶硅二公司"),
		DJGGS		("多晶硅公司"),
		DJGYFGS		("多晶硅一分公司"),
		DKHEB		("大客户二部"),
		DKHYB		("大客户一部"),
		DLAZB		("电力安装部"),
		DLBYGS		("动力保障公司"),
		DLC		("动力厂"),
		DLCLFGS		("电缆材料分公司"),
		DLDLXMGS		("电力电缆项目公司"),
		DLGCGS		("电缆工程公司"),
		DLGCSYB		("电力工程事业部"),
		DLGS		("徳缆公司"),
		DLJSGCGS		("电力建设工程公司"),
		DLJXSYB		("电缆经销事业部"),
		DLKCSJGS		("电力勘测设计公司"),
		DLZDHGS		("电力自动化公司"),
		DQFGS		("电气分公司"),
		DQZJFGS		("电气组件分公司"),
		DYCJGJWLMYYXGS		("德阳川疆国际物流贸易有限公司"),
		FDGCSYB		("风电工程事业部"),
		FLSWB		("法律事务部"),
		FNSYB		("风能事业部"),
		FWGS		("服务公司"),
		GCFWGS		("工程服务公司"),
		GCGS_DL		("工程公司"),
		GCGS_GCL		("工程公司"),
		GCGS_ND		("工程公司"),
		GCL		("工程类"),
		GCLYPGS		("高纯铝制品公司"),
		GFGS		("股份公司"),
		GJB		("国际部"),
		GJCTGCGS		("国际成套工程公司"),
		GJCTZGS		("国际成套总公司"),
		GJGCGS_SYB		("国际工程公司"),
		GJGCGS_GFGS		("国际工程公司"),
		GJGCGS_XL 		("国际工程公司"),
		GJMYB		("国际贸易部"),
		GJMYCTGS		("国际贸易成套公司"),
		GJSYB		("国际事业部"),
		GJYWB		("国际业务部"),
		GMB		("国贸部"),
		GNCTB		("国内成套部"),
		GNFGS		("国内分公司"),
		GNGCGS		("国内工程公司"),
		GNGCJXGS		("国内工程检修公司"),
		GPSYB		("硅片事业部"),
		HBDQFGS		("衡变电气分公司"),
		HBGS		("衡变公司"),
		HBYYGS		("衡变众业公司"),
		HJBLGS		("合金材料公司"),
		HNGCGS		("湖南工程公司"),
		HNGJWLGS		("湖南国际物流公司"),
		HNYLGS		("湖南园林公司"),
		HNYNDQGS		("湖南智能电气公司"),
		HNZNDQGS		("湖南智能电气公司"),
		HXGS		("和新公司"),
		HXTG		("和新套管"),
		JCK		("进出口公司"),
		JCKGS_JYDW		("进出口公司"),
		JCKGS_SYB		("进出口公司"),
		JJWL		("津疆物流"),
		JNDXXMGS		("节能导线项目公司"),
		JNDXXMGS_LL		("节能导线项目公司"),
		JNDXXMGS_XL		("节能导线项目公司"),
		JSJGYTSBLGS		("金属结构与碳素材料公司"),
		JT		("集团"),
		JWYHGS		("经纬众和公司"),
		JYGS		("佳阳公司"),
		KGYJS		("开关研究所"),
		KJHGQ		("康嘉互感器"),
		KYDLXMGS		("矿用电缆项目公司"),
		KYGS		("矿业公司"),
		LBGS		("铝箔公司"),
		LHB		("绿化部"),
		LLGS		("鲁缆公司"),
		LYWYGS		("铝苑物业公司"),
		MYGS_TCNY		("贸易公司"),
		NDGS		("能动公司"),
		NJYNDQGS		("南京智能电气公司"),
		NJZNDQGS		("南京智能电气公司"),
		NLTK		("南露天矿"),
		NYCY		("能源产业"),
		NYSYB		("能源事业部"),
		RDGS		("热电公司"),
		SBDCY		("输变电产业"),
		SBDCYJT		("输变电产业集团"),
		SBDLKBSJGS		("沈变电力勘测设计公司"),
		SBDLYDHGS		("沈变电力自动化公司"),
		SBGJMYCTGS		("沈变国际贸易成套公司"),
		SBGS		("沈变公司"),
		SBKJHGQ		("沈变康嘉互感器"),
		SBSKGS		("沈变上开公司"),
		SBWYGS		("沈变物业公司"),
		SBXDWLGS		("沈变现代物流公司"),
		SBXNY		("沈变新能源"),
		SBXSYX		("沈变修试中心"),
		SBYTFGS		("沈变中特分公司"),
		SBYXGS		("沈变中型公司"),
		SDDLGCGS		("山东电力工程公司"),
		SDFGS		("山东分公司"),
		SKGS		("上开公司"),
		TBDG_YD_NYYXGS		("特变电工（印度）能源有限公司"),
		TBDGYDNYYXGS		("特变电工（印度）能源有限公司"),
		TBGS		("天变公司"),
		TCNY		("天池能源"),
		TLGS		("特缆公司"),
		TLXMGS		("特缆项目公司"),
		TYDXXMGS		("通用电线项目公司"),
		TYDXXMGS_LL		("通用电线项目公司"),
		TYDXXMGS_XL		("通用电线项目公司"),
		TYXMGS		("通用项目公司"),
		TZDGXJDGCLYXGS		("特变电工新疆电工材料有限公司"),
		TZDLXMGS		("特种电缆项目公司"),
		TZDLYFXMGS		("特种电缆研发项目公司"),
		WLGS		("物流公司"),
		WYDXDLC		("五元电线电缆厂"),
		WYGS		("物业公司"),
		XADLSJY		("西安电力设计院"),
		XBC		("新变厂"),
		XBCBB		("新变厂（本部）"),
		XBGJCTGCGS		("新变国际成套工程公司"),
		XBGNGCJXGS		("新变国内工程检修公司"),
		XBGS		("箱变公司"),
		XBXBGS		("新变箱变公司"),
		XBYTGS		("新变中特公司"),
		XDWLGS		("现代物流公司"),
		XJFGS		("新疆分公司"),
		XJNY		("新疆能源"),
		XJXTGJWLMYGS		("新疆新特国际物流贸易公司"),
		XJYXGS		("新疆知信公司"),
		XKGS		("西科公司"),
		XLC		("新缆厂"),
		XLGGS		("新利钢公司"),
		XNDQGCGS		("西南电气工程公司"),
		XNDQGS		("西南电气公司"),
		XNY		("新能源"),
		XNYCY		("新能源产业"),
		XNYGS		("新能源公司"),
		XNYSYB		("新能源事业部"),
		XNYYJY		("新能源研究院"),
		XSGS		("销售公司"),
		XSYGS		("销售总公司"),
		XSZGS		("销售总公司"),
		XSZX		("修试中心"),
		XTBLGS		("新特材料公司"),
		XTDLXMGS		("橡套电缆项目公司"),
		XTGS		("新特公司"),
		XTJCSYB		("系统集成事业部"),
		XTNYGS		("新特能源公司"),
		XTWLGS		("新特物流公司"),
		ZBDC		("自备电厂"),
		ZHGS		("众和公司"),
		ZHGS_SYB	("众和公司"),
		YJJSGCGS		("冶金建设工程公司"),
		ZPDCJ		("总配电车间"),
		ZTWLGS		("中特物流公司"),
		YXGS		("运销公司"),
		ZHGS_MYGS		("贸易公司"),
		ZJWL		("中疆物流"),
		ZJZTGJWLYXGS		("新疆中特国际物流有限公司"),
		ZTFGS		("中特分公司"),
		ZTGS		("中特公司"),
		ZXGS		("中型公司"),
		ZYGS		("众业公司"),

		
		//非正式公司
		DBSBDCYJT		("东北输变电产业集团"),
		NFSBDCYJT		("南方输变电产业集团"),
		XBCZT			("新变厂整体"),
		BYQCY			("变压器产业"),
		XLCY			("线缆产业"),
		TCNY_and_XJNY	("天池能源+新疆能源公司");
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
	
	Organization BMDB;
	Organization virtualYSZK;
	Organization virtualJYZB;
 
	@Resource(type=BMDepartmentDB.class)
	public void setBMDBOrganization(Organization org) {
		BMDB = org;
		virtualYSZK = new VirtualYSZKOrganization(BMDB);
		virtualJYZB = new VirtualJYZBOrganization((VirtualYSZKOrganization) virtualYSZK, BMDB);
	}
	
	public Organization getVirtualJYZBOrganization() {
		return virtualJYZB;
	}
	
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
