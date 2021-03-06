package com.tbea.ic.operation.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.util.tools.Data;
import com.util.tools.DataNode;

import net.sf.json.JSONArray;

public class CompanySelection {
	public interface Filter {
		boolean keep(Company comp);
		boolean keepGroup(Company comp);
	}
	private boolean mTopOnly = false;
	private List<Company> mTopComps;
	private Filter mFilter;
	private int firstCompany = 0;
	private CompanyType firstCompanyType;

	
	private List<Company> removeNullObject(List<Company> topComps){
		for (int i = topComps.size() - 1; i >= 0; --i){
			if (topComps.get(i) == null){
				topComps.remove(i);
			}
		}
		return topComps;
	}
	
	public CompanySelection(boolean topOnly, List<Company> topComps, Filter filter) {
		super();
		this.mTopOnly = topOnly;
		this.mTopComps = removeNullObject(topComps);
		this.mFilter = filter;
	}
	
	public static List<CompanyType> getCompanys(HttpServletRequest request){
		return getCompanys(request, "companies");
	}
	
	public static List<CompanyType> getCompanys(HttpServletRequest request, String name){
		String companys = request.getParameter(name);
		JSONArray ja = JSONArray.fromObject(companys);
		return getCompanys(ja);
	}
	
	public static List<CompanyType> getCompanys(JSONArray ja){
		List<CompanyType> compTys = new ArrayList<CompanyType>();
			for (int i = 0; i < ja.size(); ++i){
				CompanyType tmp = CompanyType.valueOf(ja.getInt(i));
				if (null != tmp){
					compTys.add(tmp);
				}				
			}

		return compTys;
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
		this.mTopComps = removeNullObject(topComps);
		this.mFilter = new Filter(){
			public boolean keep(Company comp){
				return true;
			}

			@Override
			public boolean keepGroup(Company comp) {
				return true;
			}
		};
	}
	
	public void setFirstCompany(CompanyType compType){
		firstCompanyType = compType;
		for (int  i = 0; i < mTopComps.size(); ++i){
			if (mTopComps.get(i).getType() == compType){
				this.firstCompany = i;
				break;
			}
		}
		
	}
	
	private List<Company> filterCompanys(List<Company> comps){
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
	
	
	private DataNode bind(Company comp){
		DataNode node;
		Data data;
		node = new DataNode();
		data = new Data();
		data.setId(comp.getType().ordinal());
		data.setValue(comp.getType().getValue());
		node.setData(data);
		return node;
	}
	
	private List<DataNode> bind(List<Company> comps){
		List<DataNode> nodes = new ArrayList<DataNode>();
		for (int i = 0; i < comps.size(); ++i){
			nodes.add(bind(comps.get(i)));
		}
		return nodes;
	}
	
	public List<DataNode> select(int depth) {
		return select(this.mTopComps, depth);
	}
	
	private List<DataNode> select(List<Company> topComps, int depth) {
		List<DataNode> nodes = new ArrayList<DataNode>();
		if (0 != depth) {
			DataNode node;
			Company topComp;
			for (int i = 0; i < topComps.size(); ++i) {
				topComp = topComps.get(i);
				
				if (!mFilter.keepGroup(topComp)) {
					continue;
				}
				
				List<DataNode> subNodes = select(topComp.getSubCompanies(),
						depth - 1);
				if (!subNodes.isEmpty()) {
					node = bind(topComp);
					nodes.add(node);
					if (mFilter.keep(topComp)) {
						DataNode nodeTmp = bind(topComp);
						nodeTmp.getData().setValue(topComp.getName() + "总体");
						node.getSubNodes().add(0, nodeTmp);
					}
					node.getSubNodes().addAll(subNodes);
				} else if (mFilter.keep(topComp)) {
					node = bind(topComp);
					nodes.add(node);
				}
			}
		}
		return nodes;
	}
	
	public void select(Map<String, Object> map, int depth){
		List<DataNode> nodes = select(mTopComps, depth);
		map.put("nodeData", JSONArray.fromObject(nodes).toString());
	}
	
	public void select(Map<String, Object> map){
		List<Company> topComps = new ArrayList<Company>();
		List<String[][]> subComps = new ArrayList<String[][]>();
		List<Company> passedComps = new ArrayList<Company>();
		String[][] name_ids;
		Company tmpComp;
		Map<String, Boolean> notKeptTopComps = new HashMap<String, Boolean>();
		for (int i = 0; i < mTopComps.size(); ++i){
			tmpComp = mTopComps.get(i);
			
			passedComps = filterCompanys(tmpComp.getSubCompanies());
			
			if (!passedComps.isEmpty()){
				
				
				
				name_ids = Util.getCompanyNameAndIds(passedComps);
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
		map.put("topComps", JSONArray.fromObject(name_ids).toString());
		if (!subComps.isEmpty()){
			map.put("subComps", JSONArray.fromObject(subComps).toString());
		}
		map.put("subComp", subComps);
		map.put("onlytop", mTopOnly);
		map.put("both", !mTopOnly);
		if (name_ids[1].length > 0){
			map.put("firstCompany", name_ids[1][this.firstCompany]);
		}
		if (null != firstCompanyType){
			map.put("firstCompanyType", firstCompanyType.ordinal());			
		}
		

		List<DataNode> nodes = new ArrayList<DataNode>();
		DataNode node;
		Company topComp;
		
		if (this.mTopOnly) {
			for (int i = 0; i < mTopComps.size(); ++i) {
				topComp = mTopComps.get(i);
				if (mFilter.keep(topComp)) {
					node = bind(topComp);
					nodes.add(node);
				}
			}
		} else {
			for (int i = 0; i < mTopComps.size(); ++i) {
				topComp = mTopComps.get(i);
				passedComps = filterCompanys(topComp.getSubCompanies());
				if (!passedComps.isEmpty()) {
					List<DataNode> subNodes = bind(passedComps);
					node = bind(topComp);
					nodes.add(node);
					node.getSubNodes().addAll(subNodes);
					if (mFilter.keep(topComp)) {
						DataNode nodeTmp = bind(topComp);
						nodeTmp.getData().setValue(topComp.getName() + "总体");
						node.getSubNodes().add(0, nodeTmp);
					}

				} else if (mFilter.keep(topComp)) {
					node = bind(topComp);
					nodes.add(node);
					DataNode nodeTmp = bind(topComp);
					nodeTmp.getData().setValue(topComp.getName() + "总体");
					node.getSubNodes().add(0, nodeTmp);
				}
			}
		}
		map.put("nodeData", JSONArray.fromObject(nodes).toString());
	}
}
