package com.tbea.ic.operation.common.indi.relation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;

public class RelationGroup {
	
	Integer indi;
	Set<CompanyType> comps;
	
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
	
	
	public static List<RelationGroup> parse(Element e, CompanyManager compMgr, DWXXDao dwxxDao, Map<String, Integer> indiDelc) throws Exception{
		Set<Integer> indi = RelationUtil.parseIndi(e, indiDelc);
		if (indi.isEmpty()){
			return null;
		}
		
		Set<CompanyType> comps = RelationUtil.parseCompanySet(e, compMgr, dwxxDao);
		if (comps.isEmpty()){
			return null;
		}
		List<RelationGroup> ret = new ArrayList<RelationGroup>();
		for (Integer ind : indi){
			RelationGroup rg = new RelationGroup();
			rg.indi = ind;
			rg.comps = comps;
			ret.add(rg);
		}
		return ret;
	}
}
