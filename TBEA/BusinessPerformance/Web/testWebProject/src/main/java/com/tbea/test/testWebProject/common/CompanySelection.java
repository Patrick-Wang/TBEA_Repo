package com.tbea.test.testWebProject.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;

public class CompanySelection {
	public interface Filter {
		boolean keep(Company comp);
	}
	private boolean mTopOnly = false;
	private List<Company> mTopComps;
	private Filter mFilter;
	private int firstCompany = 0;
	public CompanySelection(boolean topOnly, List<Company> topComps, Filter filter) {
		super();
		this.mTopOnly = topOnly;
		this.mTopComps = topComps;
		this.mFilter = filter;
	}
	
	public static CompanyType getCompany(HttpServletRequest request){
		String companyId = request.getParameter("companyId");
		if (null != companyId){
			int cid = Integer.parseInt(companyId);
			return CompanyType.valueOf(cid);
		}
		return null;
	}
	
	public CompanySelection(boolean topOnly, List<Company> topComps) {
		super();
		this.mTopOnly = topOnly;
		this.mTopComps = topComps;
		this.mFilter = new Filter(){
			public boolean keep(Company comp){
				return true;
			}
		};
	}
	
	public void setFirstCompany(CompanyType compType){
		for (int  i = 0; i < mTopComps.size(); ++i){
			if (mTopComps.get(i).getType() == compType){
				this.firstCompany = i;
				break;
			}
		}
	}
	
	private List<Company> FilterCompanys(List<Company> comps){
		List<Company> passedComps = new ArrayList<Company>();
		passedComps = new ArrayList<Company>();
		Company tmpComp;
		for (int j = 0; j < comps.size(); ++j){
			tmpComp = comps.get(j);
			if (mFilter.keep(tmpComp)){
				passedComps.add(tmpComp);
			}
		}
		return passedComps;
	}
	
	public void select(Map<String, Object> map){
		List<Company> topComps = new ArrayList<Company>();
		List<String[][]> subComps = new ArrayList<String[][]>();
		List<Company> existComps = new ArrayList<Company>();
		String[][] name_ids;
		Company tmpComp;
		Map<String, Boolean> notKeptTopComps = new HashMap<String, Boolean>();
		for (int i = 0; i < mTopComps.size(); ++i){
			tmpComp = mTopComps.get(i);
			
			existComps = FilterCompanys(tmpComp.getSubCompanys());
			
			if (!existComps.isEmpty()){
				name_ids = Util.getCompanyNameAndIds(existComps);
				subComps.add(name_ids);
				topComps.add(tmpComp);
				
				if (!mFilter.keep(tmpComp)){
					notKeptTopComps.put("" + tmpComp.getType().ordinal(), true);
				}
				
			} else if (mFilter.keep(tmpComp)){
				topComps.add(tmpComp);
			}
		}
		
		name_ids = Util.getCompanyNameAndIds(topComps);
		map.put("emptyComp", notKeptTopComps);
		map.put("topComp", name_ids);
		map.put("subComp", subComps);
		map.put("onlytop", mTopOnly);
		map.put("both", !mTopOnly);
		map.put("firstCompany", name_ids[1][this.firstCompany]);
	}
}
