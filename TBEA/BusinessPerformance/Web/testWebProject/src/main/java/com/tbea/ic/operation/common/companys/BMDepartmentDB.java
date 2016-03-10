package com.tbea.ic.operation.common.companys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.DWXX;

@Repository
public class BMDepartmentDB extends AbstractOrganization {

	static Map<Integer, CompanyType> id_typeMap = new HashMap<Integer, CompanyType>();
	static {
		id_typeMap.put(0, CompanyType.GFGS);
		id_typeMap.put(1, CompanyType.SBGS);
		id_typeMap.put(2, CompanyType.HBGS);
		id_typeMap.put(3, CompanyType.XBC);
		id_typeMap.put(4, CompanyType.LLGS);
		id_typeMap.put(5, CompanyType.XLC);
		id_typeMap.put(6, CompanyType.DLGS);
		id_typeMap.put(7, CompanyType.TCNY);
		id_typeMap.put(8, CompanyType.NDGS);
		id_typeMap.put(9, CompanyType.XNYGS);
		id_typeMap.put(10, CompanyType.XTNYGS);
		id_typeMap.put(11, CompanyType.JCKGS_JYDW);
		id_typeMap.put(12, CompanyType.GJGCGS_GFGS);
		id_typeMap.put(13, CompanyType.ZHGS);
		id_typeMap.put(101, CompanyType.SBGJMYCTGS);
		id_typeMap.put(102, CompanyType.SBZTFGS);
		id_typeMap.put(103, CompanyType.SBKJHGQ);
		id_typeMap.put(104, CompanyType.HXTG);
		id_typeMap.put(105, CompanyType.SBDLYDHGS);
		id_typeMap.put(106, CompanyType.SBSKGS);
		id_typeMap.put(107, CompanyType.SBXSYX);
		id_typeMap.put(108, CompanyType.SBXDWLGS);
		id_typeMap.put(109, CompanyType.SBDLKBSJGS);
		id_typeMap.put(110, CompanyType.XLGGS);
		id_typeMap.put(111, CompanyType.SBZXGS);
		id_typeMap.put(112, CompanyType.SBWYGS);
		id_typeMap.put(113, CompanyType.KGYJS);
		id_typeMap.put(114, CompanyType.SBXNY);
		id_typeMap.put(115, CompanyType.TBDG_YD_NYYXGS);
		id_typeMap.put(201, CompanyType.HBDQFGS);
		id_typeMap.put(202, CompanyType.HNGJWLGS);
		id_typeMap.put(203, CompanyType.HNGCGS);
		id_typeMap.put(204, CompanyType.HBYYGS);
		id_typeMap.put(205, CompanyType.HNYNDQGS);
		id_typeMap.put(206, CompanyType.NJZNDQGS);
		id_typeMap.put(207, CompanyType.HNYLGS);
		id_typeMap.put(301, CompanyType.TBGS);
		id_typeMap.put(302, CompanyType.XBYTGS);
		id_typeMap.put(303, CompanyType.XBXBGS);
		id_typeMap.put(304, CompanyType.XBGJCTGCGS);
		id_typeMap.put(305, CompanyType.XBGNGCJXGS);
		id_typeMap.put(306, CompanyType.XJXTGJWLMYGS);
		id_typeMap.put(308, CompanyType.GJSYB);
		id_typeMap.put(309, CompanyType.JJWL);
		id_typeMap.put(401, CompanyType.TYDXXMGS);
		id_typeMap.put(402, CompanyType.JNDXXMGS);
		id_typeMap.put(403, CompanyType.XTDLXMGS);
		id_typeMap.put(404, CompanyType.DLJXSYB);
		id_typeMap.put(405, CompanyType.DLGCGS);
		id_typeMap.put(406, CompanyType.SDDLGCGS);
		id_typeMap.put(501, CompanyType.TLXMGS);
		id_typeMap.put(502, CompanyType.TYXMGS);
		id_typeMap.put(503, CompanyType.DBGS);
		id_typeMap.put(504, CompanyType.GMB);
		id_typeMap.put(505, CompanyType.GJGCGS_XL);
		id_typeMap.put(506, CompanyType.ZTWLGS);
		id_typeMap.put(507, CompanyType.XSZGS);
		id_typeMap.put(508, CompanyType.XJFGS);
		id_typeMap.put(509, CompanyType.BJFGS);
		id_typeMap.put(510, CompanyType.GNFGS);
		id_typeMap.put(601, CompanyType.TLGS);
		id_typeMap.put(602, CompanyType.XTGS);
		id_typeMap.put(603, CompanyType.GCGS_DL);
		id_typeMap.put(604, CompanyType.XNDQGCGS);
		id_typeMap.put(701, CompanyType.NLTK);
		id_typeMap.put(702, CompanyType.XJNY);
		id_typeMap.put(703, CompanyType.YXGS);
		id_typeMap.put(704, CompanyType.MYGS_TCNY);
		id_typeMap.put(801, CompanyType.DLC);
		id_typeMap.put(802, CompanyType.ZPDCJ);
		id_typeMap.put(803, CompanyType.GCGS_ND);
		id_typeMap.put(804, CompanyType.LHB);
		id_typeMap.put(805, CompanyType.WYGS);
		id_typeMap.put(901, CompanyType.XTJCSYB);
		id_typeMap.put(902, CompanyType.FDGCSYB);
		id_typeMap.put(903, CompanyType.XKGS);
		id_typeMap.put(904, CompanyType.JYGS);
		id_typeMap.put(905, CompanyType.GPSYB);
		id_typeMap.put(906, CompanyType.FNSYB);
		id_typeMap.put(907, CompanyType.XADLSJY);
		id_typeMap.put(908, CompanyType.DLGCSYB);
		id_typeMap.put(909, CompanyType.XNYYJY);
		id_typeMap.put(910, CompanyType.GJB);
		id_typeMap.put(911, CompanyType.RSGS);
		id_typeMap.put(1001, CompanyType.DJGGS);
		id_typeMap.put(1002, CompanyType.DJGYFGS);
		id_typeMap.put(1003, CompanyType.DJGEGS);
		id_typeMap.put(1004, CompanyType.ZBDC);
		id_typeMap.put(1005, CompanyType.XTBLGS);
		id_typeMap.put(1006, CompanyType.GCFWGS);
		id_typeMap.put(1007, CompanyType.XTWLGS);
		id_typeMap.put(1008, CompanyType.XJZXGS);
		id_typeMap.put(1301, CompanyType.GCLYPGS);
		id_typeMap.put(1302, CompanyType.HJBLGS);
		id_typeMap.put(1303, CompanyType.LBGS);
		id_typeMap.put(1304, CompanyType.DJBGS);
		id_typeMap.put(1305, CompanyType.JSJGYTSBLGS);
		id_typeMap.put(1306, CompanyType.WYDXDLC);
		id_typeMap.put(1307, CompanyType.RDGS);
		id_typeMap.put(1308, CompanyType.DLBYGS);
		id_typeMap.put(1309, CompanyType.WLGS);
		id_typeMap.put(1310, CompanyType.ZHGS_MYGS);
		id_typeMap.put(1311, CompanyType.JWYHGS);
		id_typeMap.put(1312, CompanyType.YJJSGCGS);
		id_typeMap.put(1313, CompanyType.DLJSGCGS);
		id_typeMap.put(1314, CompanyType.FWGS);
		id_typeMap.put(1315, CompanyType.LYWYGS);
		id_typeMap.put(1316, CompanyType.KYGS);
		id_typeMap.put(10000, CompanyType.SBDCYJT);
		id_typeMap.put(20000, CompanyType.NYSYB);
		id_typeMap.put(30000, CompanyType.XNYSYB);
		id_typeMap.put(40000, CompanyType.JCKGS_SYB);
		id_typeMap.put(50000, CompanyType.GJGCGS_SYB);
		id_typeMap.put(60000, CompanyType.ZHGS_SYB);
		id_typeMap.put(100000, CompanyType.JT);
	}

	private int m_depth = 0;
	
	@Transactional("transactionManager")
	@PersistenceContext(unitName = "localDB")
	void setEntityManager(EntityManager entityManager) {
		Query q = entityManager.createQuery("from DWXX where parent_ID = 100000");
		List<DWXX> dwxxs = q.getResultList();
		if (!dwxxs.isEmpty()) {
			
			CompanyType type;
			Integer id;
			Company comp;
			for (int i = 0; i < dwxxs.size(); ++i) {
				id = dwxxs.get(i).getId();
				if (id_typeMap.containsKey(id)) {
					type = id_typeMap.get(id);
					comp = getCompany(type.setValue(dwxxs.get(i).getName()), id);
					append(comp);
					int tmpDepth = appendChildren(comp, dwxxs.get(i).getChildren());
					if (m_depth < tmpDepth){
						m_depth += tmpDepth;
					}
				}
			}
			++m_depth;
		}
	}

	private int appendChildren(Company comp, List<DWXX> dwxxs) {
		int depth = 0;
		if (!dwxxs.isEmpty()) {
			CompanyType type;
			Integer id;
			Company compTmp;
			for (int i = 0; i < dwxxs.size(); ++i) {
				id = dwxxs.get(i).getId();
				if (id_typeMap.containsKey(id)) {
					type = id_typeMap.get(id);
					compTmp = getCompany(type.setValue(dwxxs.get(i).getName()),
							id);
					comp.append(compTmp);
					int tmpDepth =  appendChildren(compTmp, dwxxs.get(i).getChildren());
					if (depth < tmpDepth){
						depth = tmpDepth;
					}
				}
			}
			++depth;
		}
		return depth;
	}

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static List<Company> getJydw(List<Company> sybs) {
		List<Company> jydws = new ArrayList<Company>();
		for (Company syb : sybs) {
			if (!syb.getSubCompanies().isEmpty()) {
				jydws.addAll(syb.getSubCompanies());
			}
		}
		return jydws;
	}
	
	public static List<Company> getJydw(Company syb) {
		List<Company> jydws = new ArrayList<Company>();
		if (!syb.getSubCompanies().isEmpty()) {
			jydws.addAll(syb.getSubCompanies());
		}
		return jydws;
	}
	
	
	public static List<Company> getJydw(CompanyManager mgr) {
		return getJydw(mgr.getBMDBOrganization().getCompany(CompanyType.GFGS).getSubCompanies());
	}
	
	public static List<Company> getMainlyJydw(CompanyManager mgr){
		Organization org = mgr.getBMDBOrganization();
		List<Company> sybs = org.getCompany(CompanyType.GFGS).getSubCompanies();
		List<Company> sybsTmp = new ArrayList<Company>();
		sybsTmp.addAll(sybs);
		sybsTmp.add(org.getCompany(CompanyType.ZHGS_SYB));
		return getJydw(sybsTmp);
	}
	
	public static List<Company> getXmgs(Company syb){
		List<Company> jydws = getJydw(syb);
		List<Company> xmgss = new ArrayList<Company>();
		for (Company jydw : jydws){
			if (!jydw.getSubCompanies().isEmpty()){
				xmgss.addAll(jydw.getSubCompanies());
			}
		}
		return xmgss;
	}
}
