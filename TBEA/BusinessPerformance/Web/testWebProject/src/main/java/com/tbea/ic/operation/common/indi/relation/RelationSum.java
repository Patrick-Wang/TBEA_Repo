package com.tbea.ic.operation.common.indi.relation;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;

public class RelationSum {
	RelationGroup target = new RelationGroup();
	Map<Integer, RelationGroup> srcs;
	
	
	public RelationGroup target(){
		return target;
	}
	
	public Map<Integer, RelationGroup> srcs(){
		return srcs;
	}
	
	
	public boolean containsSrcs(Integer indi, CompanyType comp){
		RelationGroup rg = srcs.get(indi);
		if (rg != null){
			return rg.inGroup(comp);
		}
		return false;
	}
	
	public static RelationSum parse(Element e, CompanyManager compMgr, DWXXDao dwxxDao, Map<String, Integer> indis) throws Exception{
	
		RelationGroup target = RelationGroup.parse(XmlUtil.element(e.getElementsByTagName("target"), 0), compMgr, dwxxDao, indis);
		
		
		if (null != target){
			Map<Integer, RelationGroup> srcs = new HashMap<Integer, RelationGroup>();
			
			XmlUtil.each(e.getElementsByTagName("src"), new OnLoop(){

				@Override
				public void on(Element elem) throws Exception {
					RelationGroup src = RelationGroup.parse(elem, compMgr, dwxxDao, indis);
					if (src != null){
						srcs.put(src.indi(), src);
					}
				}
			});
			
			if (!srcs.isEmpty()){
				RelationSum rs = new RelationSum();
				rs.srcs = srcs;
				rs.target = target;
				return rs;
			}
		}
		return null;
		
	}
}