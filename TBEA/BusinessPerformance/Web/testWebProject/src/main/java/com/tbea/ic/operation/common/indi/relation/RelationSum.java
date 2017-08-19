package com.tbea.ic.operation.common.indi.relation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.xml.frame.report.util.XmlUtil;
import com.xml.frame.report.util.XmlUtil.OnLoop;

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
	
	public static List<RelationSum> parse(Element e, CompanyManager compMgr, DWXXDao dwxxDao, Map<String, Integer> indiDelc) throws Exception{
	
		List<RelationGroup> target = RelationGroup.parse(XmlUtil.element(e.getElementsByTagName("target"), 0), compMgr, dwxxDao, indiDelc);
		
		
		if (null != target){
			Map<Integer, RelationGroup> srcs = new HashMap<Integer, RelationGroup>();
			
			XmlUtil.each(e.getElementsByTagName("src"), new OnLoop(){

				@Override
				public void on(Element elem) throws Exception {
					List<RelationGroup> src = RelationGroup.parse(elem, compMgr, dwxxDao, indiDelc);
					if (src != null){
						for (RelationGroup rl : src){
							srcs.put(rl.indi(), rl);
						}
					}
				}
			});
			
			if (!srcs.isEmpty()){
				List<RelationSum> rss = new ArrayList<RelationSum>();
				for (RelationGroup rg : target){
					RelationSum rs = new RelationSum();
					rs.srcs = srcs;
					rs.target = rg;
					rss.add(rs);
				}
				return rss;
			}
		}
		return null;
		
	}
}
