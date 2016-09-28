package com.tbea.ic.operation.common.indi.relation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;

public class RelationGroup {
	
	Integer indi;
	Set<CompanyType> comps;
	
	//Map<Integer, Set<CompanyType>> shareIndis = new HashMap<Integer, Set<CompanyType>>();
	public boolean inGroup(CompanyType comp){
		return comps.contains(comp);
	}
	
	public Integer indi(){
		return indi;
	}
	
	public Set<CompanyType> companies(){
		return comps;
	}
	
	public void merge(RelationGroup rg){
		comps.addAll(rg.companies());
	}
	
	
	public static RelationGroup parse(Element e, CompanyManager compMgr, DWXXDao dwxxDao, Map<String, Integer> indis) throws Exception{
		Integer indi = RelationUtil.parseIndi(e, indis);
		if (indi == null){
			return null;
		}
		
		Set<CompanyType> comps = RelationUtil.parseCompanySet(e, compMgr, dwxxDao);
		if (comps.isEmpty()){
			return null;
		}
		RelationGroup rg = new RelationGroup();
		rg.indi = indi;
		rg.comps = comps;
		return rg;
	}
}
