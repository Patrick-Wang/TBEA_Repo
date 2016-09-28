package com.tbea.ic.operation.common.indi.relation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;

import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;

public class RelationUtil {
	public static Set<CompanyType> parseCompanySet(Element elem, CompanyManager compMgr, DWXXDao dwxxDao) throws Exception {
		Set<CompanyType> comps = new HashSet<CompanyType>();
		XmlUtil.each(elem.getChildNodes(), new OnLoop(){

			@Override
			public void on(Element elem) throws Exception {
				CompanyType ct = parseCompany(elem, compMgr, dwxxDao);
				if (null != ct){
					comps.add(ct);
				}
			}
			
		});
		return comps;
	}

	public static CompanyType parseCompany(Element elem, CompanyManager compMgr, DWXXDao dwxxDao) {
		if (elem.hasAttribute("id")){
			Organization org = compMgr.getBMDBOrganization();
			Company comp = org.getCompany(Integer.valueOf(elem.getAttribute("id")));
			if (null != comp){
				return comp.getType();
			}
		}
		
		if (elem.hasAttribute("type")){
			return CompanyType.valueOf(elem.getAttribute("type"));
		}
		
		if (elem.hasAttribute("name")){
			DWXX dwxx = dwxxDao.getByName(elem.getAttribute("name"));
			if (null != dwxx){
				Organization org = compMgr.getBMDBOrganization();
				Company comp = org.getCompany(dwxx.getId());
				return comp.getType();
			}
		}
		
		return null;
	}
	
	public static Integer parseIndi(Element elem, ZBXXDao zbxxDao){
		if (elem.hasAttribute("id")){
			return Integer.valueOf(elem.getAttribute("id"));
		}
		if (elem.hasAttribute("name")){
			ZBXX zbxx = zbxxDao.getZbByName(elem.getAttribute("name"));
			if (zbxx != null){
				return zbxx.getId();
			}
		}
		return null;
	}
	
	public static Integer parseIndi(Element elem, Map<String, Integer> indis){
		return indis.get(elem.getAttribute("indi"));
	}
	
	public static Set<ZBType> parseTypes(Element elem) {
		if (elem.hasAttribute("type")){
			String type = elem.getAttribute("type");
			String[] typeArr = type.split(",");
			Set<ZBType> types = new HashSet<ZBType>();
			for (int i = 0; i < typeArr.length; ++i){
				if ("年计划".equals(typeArr[i])){
					types.add(ZBType.NDJH);
				}else if ("月计划".equals(typeArr[i])){
					types.add(ZBType.YDJDMJH);
				}else if ("20预计".equals(typeArr[i])){
					types.add(ZBType.BY20YJ);
				}else if ("28预计".equals(typeArr[i])){
					types.add(ZBType.BY28YJ);
				}else if ("月实际".equals(typeArr[i])){
					types.add(ZBType.BYSJ);
				}
			}
			return types;
		}
		return null;
	}

}
