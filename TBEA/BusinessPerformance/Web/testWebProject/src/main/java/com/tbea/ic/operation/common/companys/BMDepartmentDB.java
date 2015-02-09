package com.tbea.ic.operation.common.companys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.entity.jygk.DWXX;

@Repository
public class BMDepartmentDB extends AbstractOrganization {

	static Map<Integer, CompanyType> id_typeMap = new HashMap<Integer, CompanyType>();
	static {
		id_typeMap.put(1, CompanyType.SB);
		id_typeMap.put(2, CompanyType.HB);
		id_typeMap.put(3, CompanyType.XB);
		id_typeMap.put(-1, CompanyType.TBGS);
		id_typeMap.put(4, CompanyType.LL);
		id_typeMap.put(5, CompanyType.XL);
		id_typeMap.put(6, CompanyType.DL);
		id_typeMap.put(10, CompanyType.XTNYGS);
		id_typeMap.put(9, CompanyType.XNY);
		id_typeMap.put(7, CompanyType.TCNY);
		id_typeMap.put(8, CompanyType.NDGS);
		id_typeMap.put(-1, CompanyType.ZJWL);
		id_typeMap.put(11, CompanyType.JCK);
		id_typeMap.put(13, CompanyType.ZH);
		id_typeMap.put(10000, CompanyType.SBDCY);
		id_typeMap.put(30000, CompanyType.XNYCY);
		id_typeMap.put(20000, CompanyType.NYCY);
		id_typeMap.put(-1, CompanyType.GCL);
		id_typeMap.put(0, CompanyType.JT);
		id_typeMap.put(-1, CompanyType.BYQC);
		id_typeMap.put(101, CompanyType.GJMYCTGS);
		id_typeMap.put(102, CompanyType.ZTFGS);
		id_typeMap.put(103, CompanyType.KJHGQ);
		id_typeMap.put(-1, CompanyType.DQZJFGS);
		id_typeMap.put(105, CompanyType.DLZDHGS);
		id_typeMap.put(106, CompanyType.SKGS);
		id_typeMap.put(107, CompanyType.XSZX);
		id_typeMap.put(108, CompanyType.XDWLGS);
		id_typeMap.put(109, CompanyType.DLKCSJGS);
		id_typeMap.put(110, CompanyType.XLGGS);
		id_typeMap.put(-1, CompanyType.GNCTB);
		id_typeMap.put(-1, CompanyType.DLAZB);
		id_typeMap.put(111, CompanyType.ZXGS);
		id_typeMap.put(-1, CompanyType.HXGS);
		id_typeMap.put(115, CompanyType.TBDG_YD_NYYXGS);
		id_typeMap.put(112, CompanyType.SBWYGS);
		id_typeMap.put(-1, CompanyType.DQFGS);
		id_typeMap.put(-1, CompanyType.HNGJWLGS);
		id_typeMap.put(-1, CompanyType.HNGCGS);
		id_typeMap.put(-1, CompanyType.ZYGS);
		id_typeMap.put(-1, CompanyType.HNZNDQGS);
		id_typeMap.put(-1, CompanyType.NJZNDQGS);
		id_typeMap.put(-1, CompanyType.HNYLGS);
		id_typeMap.put(-1, CompanyType.TBGS);
		id_typeMap.put(-1, CompanyType.ZTGS);
		id_typeMap.put(-1, CompanyType.XBGS);
		id_typeMap.put(-1, CompanyType.GJCTGCGS);
		id_typeMap.put(-1, CompanyType.GNGCJXGS);
		id_typeMap.put(-1, CompanyType.XJXTGJWLMYGS);
		id_typeMap.put(-1, CompanyType.XSZGS);
		id_typeMap.put(-1, CompanyType.SDFGS);
		id_typeMap.put(-1, CompanyType.GJCTZGS);
		id_typeMap.put(-1, CompanyType.LL_TYDXXMGS);
		id_typeMap.put(-1, CompanyType.LL_JNDXXMGS);
		id_typeMap.put(-1, CompanyType.DLCLFGS);
		id_typeMap.put(-1, CompanyType.TZDLYFXMGS);
		id_typeMap.put(-1, CompanyType.DLJXSYB);
		id_typeMap.put(-1, CompanyType.XNYSYB);
		id_typeMap.put(-1, CompanyType.DLGCGS);
		id_typeMap.put(-1, CompanyType.SDDLGCGS);
		id_typeMap.put(-1, CompanyType.KYDLXMGS);
		id_typeMap.put(-1, CompanyType.XL_TYDXXMGS);
		id_typeMap.put(-1, CompanyType.XL_JNDXXMGS);
		id_typeMap.put(-1, CompanyType.TZDLXMGS);
		id_typeMap.put(-1, CompanyType.DLDLXMGS);
		id_typeMap.put(-1, CompanyType.TZDGXJDGCLYXGS);
		id_typeMap.put(-1, CompanyType.DKHYB);
		id_typeMap.put(-1, CompanyType.DKHEB);
		id_typeMap.put(-1, CompanyType.GJMYB);
		id_typeMap.put(-1, CompanyType.GNGCGS);
		id_typeMap.put(12, CompanyType.GJGCGS);
		id_typeMap.put(-1, CompanyType.ZJZTGJWLYXGS);
		id_typeMap.put(-1, CompanyType.TLGS);
		id_typeMap.put(-1, CompanyType.XTGS);
		id_typeMap.put(-1, CompanyType.GCGS);
		id_typeMap.put(-1, CompanyType.BB);
		id_typeMap.put(-1, CompanyType.XSGS);
		id_typeMap.put(-1, CompanyType.GJYWB);
		id_typeMap.put(-1, CompanyType.FLSWB);
		id_typeMap.put(-1, CompanyType.DYCJGJWLMYYXGS);
		id_typeMap.put(-1, CompanyType.XNDQGS);	
		id_typeMap.put(104, CompanyType.HXTG);	
		id_typeMap.put(113, CompanyType.KGYJS);	
		id_typeMap.put(114, CompanyType.SBXNY);	
		
	}

	private int m_depth = 0;
	
	@Transactional("transactionManager")
	@PersistenceContext(unitName = "localDB")
	void setEntityManager(EntityManager entityManager) {
		Query q = entityManager.createQuery("from DWXX where parent_ID = null");
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

}
