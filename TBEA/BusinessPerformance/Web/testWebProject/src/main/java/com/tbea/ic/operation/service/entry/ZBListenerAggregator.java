package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.indi.relation.RelationGroup;
import com.tbea.ic.operation.common.indi.relation.RelationSum;
import com.tbea.ic.operation.common.indi.relation.Relationships;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.service.entry.zbInjector.ZBInjectListener;


class Param{
	public Integer zbId; 
	public double val; 
	public EasyCalendar cal;
	public Company comp;
	public Param(Integer zbId, double val, EasyCalendar cal, Company comp) {
		this.zbId = zbId;
		this.val = val;
		this.cal = cal;
		this.comp = comp;
	}
}


public class ZBListenerAggregator implements ZBInjectListener {

	public static class IndiValues{
		public Date date;
		public CompanyType compType;
		public JSONArray data;
	}
	
	
	SJZBDao sjzbDao;

	YJ20ZBDao yj20zbDao;

	YJ28ZBDao yj28zbDao;
	
	YDJHZBDao ydjhzbDao;
	
	NDJHZBDao ndjhzbDao;
	
	CompanyManager companyManager;
	
	Relationships indiRelations;
	
	ZBType zbType;
	
	Map<String, IndiValues> workingIndis = new HashMap<String, IndiValues>();
	Map<String, IndiValues> shareIndis = null;
	Map<String, IndiValues> sumIndis = null;
	
	List<Param> params = new ArrayList<Param>();
	
	public Map<String, IndiValues> getShareIndis(){
		if (shareIndis == null){
			workingIndis = new HashMap<String, IndiValues>();
			for (int i = 0; i < params.size(); ++i){
				Param p = params.get(i);
				parseSharedRelation(indiRelations.getShared(zbType, p.zbId), p.val, p.cal, p.comp);
			}
			shareIndis = workingIndis;
		}
		return shareIndis;
	}
	
	public Map<String, IndiValues> getSumIndis(){
		if (sumIndis == null){
			workingIndis = new HashMap<String, IndiValues>();
			for (int i = 0; i < params.size(); ++i){
				Param p = params.get(i);
				parseSumRelation(p.zbId, p.val, p.cal, p.comp);
			}
			sumIndis = workingIndis;
		}
		return sumIndis;
	}
	
	
	public ZBListenerAggregator(SJZBDao sjzbDao, YJ20ZBDao yj20zbDao,
		YJ28ZBDao yj28zbDao, YDJHZBDao ydjhzbDao,
		NDJHZBDao ndjhzbDao, CompanyManager companyManager,
		Relationships indiRelations, ZBType zbType) {
		super();
		this.sjzbDao = sjzbDao;
		this.yj20zbDao = yj20zbDao;
		this.yj28zbDao = yj28zbDao;
		this.ydjhzbDao = ydjhzbDao;
		this.ndjhzbDao = ndjhzbDao;
		this.companyManager = companyManager;
		this.indiRelations = indiRelations;
		this.zbType = zbType;
	}


	private void parseSharedRelation(RelationGroup rgShared, double val, EasyCalendar ec, Company excludeComp){
		if (rgShared != null){
			String df = ec.getFormat();
			JSONArray r = new JSONArray();
			r.add("" + rgShared.indi());
			r.add("" + val);
			for (CompanyType cp : rgShared.companies()){
				if (excludeComp != null && cp == excludeComp.getType()){
					continue;
				}
				
				if (workingIndis.containsKey(df + cp)){
					IndiValues indiVal = workingIndis.get(df + cp);
					indiVal.data.add(r);
				}else{
					IndiValues indiVal = new IndiValues();
					indiVal.data = new JSONArray();
					indiVal.data.add(r);
					indiVal.date = ec.getDate();
					indiVal.compType = cp;
					workingIndis.put(df + cp, indiVal);
				}
			}
		}		
	}
	
	
	@Override
	public void onInjected(Integer zbId, double val, Calendar cal,
			Company comp, ZBStatus status, Calendar time) {
		this.params.add(new Param(zbId, val, new EasyCalendar(cal.getTime()), comp));
	}

	private void parseSumRelation(Integer zbId, double val, EasyCalendar ec,
			Company comp) {
		List<RelationSum> rss = indiRelations.getSums(zbType);
		for (RelationSum rs : rss){
			parseSum(rs, zbId, val, ec, comp);
		}
	}

	private List<Integer> toIds(Set<CompanyType> comps){
		List<Integer> compIds = new ArrayList<Integer>();
		Organization org = companyManager.getBMDBOrganization();
		for (CompanyType ct : comps){
			compIds.add(org.getCompany(ct).getId());
		}
		return compIds;
	}
	
	private Double parseSum(RelationSum rs, Integer zbId, double val,
			EasyCalendar ec, Company comp) {
		
		if (!rs.containsSrcs(zbId, comp.getType())){
			return null;
		}

		
		Double ret = val;
		Date d = ec.getDate();
		for (Entry<Integer, RelationGroup> entry : rs.srcs().entrySet()){
			RelationGroup rg = entry.getValue();
			List<Integer> ids = toIds(rg.companies());
			ids.remove(comp.getId());
			switch(zbType){
			case BY20YJ:
				ret = MathUtil.sum(ret, yj20zbDao.getZb(rg.indi(), d, ids));
				break;
			case BY28YJ:
				ret = MathUtil.sum(ret, yj28zbDao.getZb(rg.indi(), d, ids));
				break;
			case BYSJ:
				ret = MathUtil.sum(ret, sjzbDao.getZb(rg.indi(), d, ids));
				break;
			case NDJH:
				ret = MathUtil.sum(ret, ndjhzbDao.getZb(rg.indi(), d, ids));
				break;
			case YDJDMJH:
				ret = MathUtil.sum(ret, ydjhzbDao.getZb(rg.indi(), d, ids));
				break;
			default:
				break;
			}
		}
		
		parseSharedRelation(rs.target(), ret, ec, null);
		return ret;
	}

}
