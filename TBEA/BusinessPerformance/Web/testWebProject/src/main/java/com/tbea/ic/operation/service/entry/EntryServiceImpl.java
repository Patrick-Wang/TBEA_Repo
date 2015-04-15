package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sbdzb.SbdNdjhZbDao;
import com.tbea.ic.operation.model.dao.jygk.shzt.SHZTDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.NDJHZB;
import com.tbea.ic.operation.model.entity.jygk.QXGL;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YDJHZB;
import com.tbea.ic.operation.model.entity.jygk.YDZBZT;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.service.entry.zbCalculator.GeneralZbCalculator;
import com.tbea.ic.operation.service.entry.zbCalculator.NdjhZbCalculator;
import com.tbea.ic.operation.service.entry.zbCalculator.ZbCalculator;
import com.tbea.ic.operation.service.entry.zbInjector.SimpleZbInjectorFactory;



@Service
@Transactional("transactionManager")
public class EntryServiceImpl implements EntryService{

	@Autowired
	SbdNdjhZbDao sbdNdjhzbDao;
	
	@Autowired
	QXGLDao qxglDao;
	
	@Autowired
	SJZBDao sjzbDao;
	
	@Autowired
	YJ20ZBDao yj20zbDao;
	
	@Autowired
	YJ28ZBDao yj28zbDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	@Autowired
	ZBXXDao zbxxDao;
	
	@Autowired
	SHZTDao shztDao;
	
	@Autowired
	YDJHZBDao ydjhzbDao;
	
	@Autowired
	NDJHZBDao ndjhzbDao;
	
	@Autowired
	YDZBZTDao ydzbztDao;
	
	final static Set<Integer> calculatedZbs = new HashSet<Integer>();

	static {
		calculatedZbs.add(GSZB.RJSR.getValue());
		calculatedZbs.add(GSZB.RJLR.getValue());
		calculatedZbs.add(GSZB.SXFYL.getValue());
		calculatedZbs.add(GSZB.XSLRL.getValue());
	};
	
	
	CompanyManager companyManager;
	
	ZbCalculator ndjhzbCalc;
	ZbCalculator ydjhzbCalc;
	ZbCalculator yd28Calc;
	ZbCalculator yj20Calc;
	ZbCalculator sjzbCalc;

	List<Company> mainCompanies = new ArrayList<Company>();
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager){
		Organization org = companyManager.getBMDBOrganization();
		this.companyManager = companyManager;
		mainCompanies.add(org.getCompany(CompanyType.SBGS));
		mainCompanies.add(org.getCompany(CompanyType.HBGS));
		mainCompanies.add(org.getCompany(CompanyType.XBC));
		mainCompanies.add(org.getCompany(CompanyType.LLGS));
		mainCompanies.add(org.getCompany(CompanyType.XLC));
		mainCompanies.add(org.getCompany(CompanyType.DLGS));
		mainCompanies.add(org.getCompany(CompanyType.XTNYGS));
		mainCompanies.add(org.getCompany(CompanyType.XNYGS));
		mainCompanies.add(org.getCompany(CompanyType.TCNY));
		mainCompanies.add(org.getCompany(CompanyType.NDGS));
		mainCompanies.add(org.getCompany(CompanyType.JCKGS_JYDW));
		mainCompanies.add(org.getCompany(CompanyType.GJGCGS_GFGS));
		mainCompanies.add(org.getCompany(CompanyType.ZHGS));
	}
	
	@Autowired
	public void init(){
		ndjhzbCalc = new NdjhZbCalculator(SimpleZbInjectorFactory.createInjector(zbxxDao, dwxxDao, shztDao, ndjhzbDao), sbdNdjhzbDao);
		ydjhzbCalc = new GeneralZbCalculator(SimpleZbInjectorFactory.createInjector(zbxxDao, dwxxDao, shztDao, ydjhzbDao));
		yd28Calc = new GeneralZbCalculator(SimpleZbInjectorFactory.createInjector(zbxxDao, dwxxDao, shztDao, yj28zbDao));
		yj20Calc = new GeneralZbCalculator(SimpleZbInjectorFactory.createInjector(zbxxDao, dwxxDao, shztDao, yj20zbDao));
		sjzbCalc = new GeneralZbCalculator(SimpleZbInjectorFactory.createInjector(zbxxDao, dwxxDao, shztDao, sjzbDao));
	}
	
	
	public void setYdzbzt(Company comp, int nf, int yf, ZBType entryType){
		YDZBZT ydzbzt = ydzbztDao.getYdzbzt(comp, nf, yf);
		if (null == ydzbzt){
			ydzbzt = new YDZBZT();
			ydzbzt.setDwxx(dwxxDao.getById(comp.getId()));
			ydzbzt.setNf(nf);
			ydzbzt.setYf(yf);
			ydzbzt.setZt(0);
			ydzbztDao.create(ydzbzt);
		}
		
		switch (entryType){
		case BY20YJ:
			if (ydzbzt.getZt() < 1){
				ydzbzt.setZt(1);
			}
			break;
		case BY28YJ:
			if (ydzbzt.getZt() <= 1){
				ydzbzt.setZt(2);
			}
			break;
		case BYSJ:
			if (ydzbzt.getZt() <= 2){
				ydzbzt.setZt(3);
			}
			break;
		default:
			break;
		}
		ydzbztDao.merge(ydzbzt);
	}
	
	@Override
	public boolean updateZb(Date date, Account account, CompanyType comp,
			ZBType entryType, JSONArray data) {
		
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		boolean bRet = false;
		switch (entryType){
		case BY20YJ:
			bRet = update20YJ(date, company, data);
			break;
		case BY28YJ:
			bRet = update28YJ(date, company, data);
			break;
		case BYSJ:
			bRet = updateBYSJ(date, company, data);
			break;
		case NDJH:
			bRet = updateNDJH(date, company, data);
			break;
		case YDJDMJH:
			bRet = updateYDJDMJH(date, company, data);
			break;
		default:
			break;
		}
		return bRet;
	}

	private boolean updateYDJDMJH(Date date, Company company, JSONArray data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Set<Integer> unenteredZb = getUnenteredJhzb(company, data);
		List<Boolean> approvedList = isApproved(date, company.getType(),
				ZBType.YDJDMJH);
		
		Integer zbId;
		JSONArray row;
		Double val;
		
		for (int c = 0; c < approvedList.size(); ++c) {
			if (!approvedList.get(c)) {
				ydjhzbCalc.reset();
				for (int r = 0; r < data.size(); ++r) {
					row = data.getJSONArray(r);
					zbId = Integer.valueOf(row.getString(0));
					val = Util.toDouble(row.getString(c + 1));
					ydjhzbCalc.compute(zbId, val, cal, company);
				}

				for (Integer id : unenteredZb) {
					ydjhzbCalc.compute(id, null, cal, company);
				}
			}

			cal.add(Calendar.MONTH, 1);
		}

		return true;

	}

	private Set<Integer> getUnenteredSjzb(Company company, JSONArray data){
		Integer zbId;
		JSONArray row;
		DWXX dwxx = dwxxDao.getById(company.getId());
		Set<Integer> zbIdSet = toZBIDSet(dwxx.getSjzbxxs());
		for (int r = 0; r < data.size(); ++r) {
			row = data.getJSONArray(r);
			zbId = Integer.valueOf(row.getString(0));
			zbIdSet.remove(zbId);
		}
		return zbIdSet;
	}
	
	private Set<Integer> getUnenteredJhzb(Company company, JSONArray data){
		Integer zbId;
		JSONArray row;
		DWXX dwxx = dwxxDao.getById(company.getId());
		Set<Integer> zbIdSet = toZBIDSet(dwxx.getJhzbxxs());
		for (int r = 0; r < data.size(); ++r) {
			row = data.getJSONArray(r);
			zbId = Integer.valueOf(row.getString(0));
			zbIdSet.remove(zbId);
		}
		return zbIdSet;
	}

	private boolean updateNDJH(Date date, Company company, JSONArray data) {
		List<Boolean> approvedList = isApproved(date, company.getType(), ZBType.NDJH);
		if (!approvedList.get(0)){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			JSONArray row;
			ndjhzbCalc.reset();
			for (int r = 0; r < data.size(); ++r){
				row = data.getJSONArray(r);
				ndjhzbCalc.compute(
						Integer.valueOf(row.getString(0)), 
						Util.toDouble(row.getString(1)), 
						cal, 
						company);
				
			}
			
			Set<Integer> unenteredZb = getUnenteredJhzb(company, data);
			for (Integer id : unenteredZb) {
				ndjhzbCalc.compute(id, null, cal, company);
			}

		}
		return true;
	}

	private boolean updateBYSJ(Date date, Company company, JSONArray data) {
		List<Boolean> approvedList = isApproved(date, company.getType(), ZBType.BYSJ);
		if (!approvedList.get(0)){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			JSONArray row;
			sjzbCalc.reset();
			for (int i = 0; i < data.size(); ++i){
				row = data.getJSONArray(i);
				sjzbCalc.compute(
						Integer.valueOf(row.getString(0)), 
						Util.toDouble(row.getString(1)), 
						cal, 
						company);

			}

			Set<Integer> unenteredZb = getUnenteredSjzb(company, data);
			for (Integer id : unenteredZb) {
				sjzbCalc.compute(id, null, cal, company);
			}

			setYdzbzt(company, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, ZBType.BYSJ);
		}
		return true;
	}

	private boolean update28YJ(Date date, Company company, JSONArray data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer zbId;
		JSONArray row;

		Set<Integer> unenteredZb = getUnenteredSjzb(company, data);
		List<Boolean> approvedList = isApproved(date, company.getType(), ZBType.BY28YJ);
		
		if (!approvedList.get(0)) {
			yd28Calc.reset();
			for (int r = 0; r < data.size(); ++r) {
				row = data.getJSONArray(r);
				zbId = Integer.valueOf(row.getString(0));
				yd28Calc.compute(
						zbId, 
						Util.toDouble(row.getString(1)), 
						cal, 
						company);			
			}
			
			for (Integer id : unenteredZb) {
				yd28Calc.compute(id, null, cal, company);
			}
			
			setYdzbzt(company, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, ZBType.BY28YJ);
		}
		
		
		for (int c = 1; c < approvedList.size(); ++c){
			cal.add(Calendar.MONTH, 1);
			if (!approvedList.get(0)) {
				yj20Calc.reset();
				for (int r = 0; r < data.size(); ++r) {
					row = data.getJSONArray(r);
					zbId = Integer.valueOf(row.getString(0));
					yj20Calc.compute(
							zbId, 
							Util.toDouble(row.getString(c + 1)), 
							cal, 
							company);			
				}
				
				for (Integer id : unenteredZb) {
					yj20Calc.compute(id, null, cal, company);
				}
				
				setYdzbzt(company, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, ZBType.BY20YJ);
			}
		}		
		return true;
	}

	private boolean update20YJ(Date date, Company company, JSONArray data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		JSONArray row;
		Integer zbId;
		
		Set<Integer> unenteredZb = getUnenteredSjzb(company, data);
		List<Boolean> approvedList = isApproved(date, company.getType(), ZBType.BY20YJ);
		for (int c = 0; c < approvedList.size(); ++c){
			if (!approvedList.get(0)) {
				yj20Calc.reset();
				for (int r = 0; r < data.size(); ++r) {
					row = data.getJSONArray(r);
					zbId = Integer.valueOf(row.getString(0));
					yj20Calc.compute(
							zbId, 
							Util.toDouble(row.getString(c + 1)), 
							cal, 
							company);			
				}
				
				for (Integer id : unenteredZb) {
					yj20Calc.compute(id, null, cal, company);
				}
				
				setYdzbzt(company, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, ZBType.BY20YJ);
			}
			cal.add(Calendar.MONTH, 1);
		}		

		return true;
	}

	@Override
	public boolean hasEntryPlanPermission(Account account) {
		return qxglDao.getJhzlrCount(account) > 0;
	}

	@Override
	public boolean hasEntryPredictPermission(Account account) {	
		return qxglDao.getSjzlrCount(account)> 0;
	}

	@Override
	public List<Company> getValidJHCompanys(Account account) {
		List<QXGL> qxgls = qxglDao.getJhzlr(account);
		List<Company> comps = new ArrayList<Company>();
		Organization org = companyManager.getBMDBOrganization();
		for (QXGL qxgl : qxgls){
			comps.add(org.getCompany(qxgl.getDwxx().getId()));
		}
		return comps;
	}

	@Override
	public List<Company> getValidSJCompanys(Account account) {
		List<QXGL> qxgls = qxglDao.getSjzlr(account);
		List<Company> comps = new ArrayList<Company>();
		Organization org = companyManager.getBMDBOrganization();
		for (QXGL qxgl : qxgls){
			comps.add(org.getCompany(qxgl.getDwxx().getId()));
		}
		return comps;
	}

	@Override
	public List<String[]> getZb(Date date, Account account, CompanyType comp,
			ZBType entryType) {
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		List<String[]> ret = null;
		switch (entryType){
		case BY20YJ:
			ret = get20YJ(date, company);
			break;
		case BY28YJ:
			ret = get28YJ(date, company);
			break;
		case BYSJ:
			ret = getBYSJ(date, company);
			break;
		case NDJH:
			ret = getNDJH(date, company);
			break;
		case YDJDMJH:
			ret = getYDJDMJH(date, company);
			break;
		default:
			break;
		}
		
		
		return ret;
	}

	private List<String[]> getYDJDMJH(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//cal.add(Calendar.MONTH, 1);
		DWXX dwxx = dwxxDao.getById(company.getId());
		Map<Integer, String[]> map = creatZBXXMap(dwxx.getJhzbxxs(), 5);
		for (int i = 0; i < 3; ++i){
			List<YDJHZB> zbs = ydjhzbDao.getZbs(Util.toDate(cal), company);
			cal.add(Calendar.MONTH, 1);
			updateYJJDMap(map, zbs, i);
		}
		
		return toArray(map);
	}


	
	private void updateYJJDMap(Map<Integer, String[]> map, List<YDJHZB> zbs, int col) {
		for(YDJHZB zb : zbs){
			String[] row = map.get(zb.getZbxx().getId());
			if (null != row){
				row[col + 2] = zb.getYdjhz() + "";
			}
		}
	}

	private List<String[]> getNDJH(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		DWXX dwxx = dwxxDao.getById(company.getId());
		Map<Integer, String[]> map = creatZBXXMap(dwxx.getJhzbxxs(), 3);
		List<NDJHZB> zbs = ndjhzbDao.getZbs(Util.toDate(cal), company);

		for(NDJHZB zb : zbs){
			String[] row = map.get(zb.getZbxx().getId());
			if (null != row){
				row[2] = zb.getNdjhz() + "";
			}
		}
		return toArray(map);
	}


	private List<String[]> getBYSJ(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		DWXX dwxx = dwxxDao.getById(company.getId());
		Map<Integer, String[]> map = creatZBXXMap(dwxx.getSjzbxxs(), 3);
		List<SJZB> zbs = sjzbDao.getZbs(Util.toDate(cal), company);
		for(SJZB zb : zbs){
			String[] row = map.get(zb.getZbxx().getId());
			if (null != row){
				row[2] = zb.getSjz() + "";
			}
		}
		return toArray(map);
	}

	private List<String[]> get28YJ(Date date, Company company) {
		DWXX dwxx = dwxxDao.getById(company.getId());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int leftMonth = 3 - (cal.get(Calendar.MONTH) + 1) % 3;
		
		Map<Integer, String[]> map = creatZBXXMap(dwxx.getSjzbxxs(), leftMonth + 3);
		updateYJ28Map(map, yj28zbDao.getZbs(Util.toDate(cal), company), 0);
		cal.add(Calendar.MONTH, 1);
		
		for (int i = 1; i <= leftMonth; ++i){
			updateYJ20Map(map, yj20zbDao.getZbs(Util.toDate(cal), company), i);
			cal.add(Calendar.MONTH, 1);
		}
		
		return toArray(map);
	}

	private void updateYJ28Map(Map<Integer, String[]> map, List<YJ28ZB> zbs, int col) {
		String[] tmp = null;
		for (int i = 0; i < zbs.size(); ++i){
			tmp =  map.get(zbs.get(i).getZbxx().getId());
			if (null != tmp){
				tmp[col + 2] = zbs.get(i).getYj28z() + "";
			}			
		}
	}

	
	private List<String[]> toArray(Map<Integer, String[]> map){
		Object[] key_arr = map.keySet().toArray();   
		Arrays.sort(key_arr);   
		List<String[]> ret = new ArrayList<String[]>();
		for(Object id : key_arr){
			ret.add(map.get(id));
		}
		return ret;
	}
	
	private Map<Integer, String[]> creatZBXXMap(Set<ZBXX> zbxxs, int size){
		Map<Integer, String[]> map = new HashMap<Integer, String[]>();
		for (ZBXX zbxx : zbxxs){
			if (!calculatedZbs.contains(zbxx.getId())){
				String[] row = new String[size];
				map.put(zbxx.getId(), row);
				row[0] = zbxx.getId() + "";
				row[1] = zbxx.getName();
			}
		}
		return map;
	}
	
	private Set<Integer> toZBIDSet(Set<ZBXX> zbxxs){
		Set<Integer> ret = new HashSet<Integer>();
		for (ZBXX zbxx : zbxxs){
			ret.add(zbxx.getId());
		}
		return ret;
	}
	
	private List<String[]> get20YJ(Date date, Company company) {	
		DWXX dwxx = dwxxDao.getById(company.getId());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int leftMonth = 3 - (cal.get(Calendar.MONTH) + 1) % 3;
		
		Map<Integer, String[]> map = creatZBXXMap(dwxx.getSjzbxxs(), leftMonth + 3);
		for (int i = 0; i <= leftMonth; ++i){
			updateYJ20Map(map, yj20zbDao.getZbs(Util.toDate(cal), company), i);
			cal.add(Calendar.MONTH, 1);
		}
		return toArray(map);
	}

	private void updateYJ20Map(Map<Integer, String[]> map, List<YJ20ZB> zbs, int col) {
		String[] tmp = null;
		for (int i = 0; i < zbs.size(); ++i){
			tmp =  map.get(zbs.get(i).getZbxx().getId());
			if (null != tmp){
				tmp[col + 2] = zbs.get(i).getYj20z() + "";
			}			
		}
	}
	
	@Override
	public List<Boolean> isApproved(Date date, CompanyType comp, ZBType entryType) {
		List<Boolean> bResult = new ArrayList<Boolean>();
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int leftMonth = 3 - (cal.get(Calendar.MONTH) + 1) % 3;
		switch (entryType){
		case BY20YJ:
			for (int i = 0; i <= leftMonth; ++i){
				bResult.add(yj20zbDao.getApprovedZbsCount(Util.toDate(cal), company) > 0);
				cal.add(Calendar.MONTH, 1);
			}
			break;
		case BY28YJ:
			bResult.add(yj28zbDao.getApprovedZbsCount(Util.toDate(cal), company) > 0);
			cal.add(Calendar.MONTH, 1);
			for (int i = 1; i <= leftMonth; ++i){
				bResult.add(yj20zbDao.getApprovedZbsCount(Util.toDate(cal), company) > 0);
				cal.add(Calendar.MONTH, 1);
			}
			break;
		case BYSJ:
			bResult.add(sjzbDao.getApprovedZbsCount(date, company) > 0);
			break;
		case NDJH:
			bResult.add(ndjhzbDao.getApprovedZbsCount(date, company) > 0);
			break;
		case YDJDMJH:
			for (int i = 0; i < 3; ++i){
				bResult.add(ydjhzbDao.getApprovedZbsCount(Util.toDate(cal), company) > 0);
				cal.add(Calendar.MONTH, 1);
			}
			break;
		default:
			break;
		}
		return bResult;
	}

	@Override
	public List<String[]> getEntryStatus(Date date, ZBType entryType) {
		List<String[]> result = new ArrayList<String[]>();
		List<Integer> entryCompletedCompanies = null;
		List<Date> entryTime = null;
		switch (entryType){
		case BY20YJ:
			entryCompletedCompanies = yj20zbDao.getEntryCompletedCompanies(date);
			break;
		case BY28YJ:
			entryCompletedCompanies = yj28zbDao.getEntryCompletedCompanies(date);
			break;
		case BYSJ:
			entryCompletedCompanies = sjzbDao.getEntryCompletedCompanies(date);
			break;
		case NDJH:
			entryCompletedCompanies = ndjhzbDao.getEntryCompletedCompanies(date);
			break;
		case YDJDMJH:
			entryCompletedCompanies = ydjhzbDao.getEntryCompletedCompanies(date);
			break;
		default:
			return result;
		}
		
		for (Company comp : mainCompanies){			
			if (entryCompletedCompanies.contains(comp.getId())){
				Date time = null;
				switch (entryType){
				case BY20YJ:
					time = yj20zbDao.getEntryTime(date, comp);
					break;
				case BY28YJ:
					entryCompletedCompanies = yj28zbDao.getEntryCompletedCompanies(date);
					time = yj28zbDao.getEntryTime(date, comp);
					break;
				case BYSJ:
					entryCompletedCompanies = sjzbDao.getEntryCompletedCompanies(date);
					time = sjzbDao.getEntryTime(date, comp);
					break;
				case NDJH:
					entryCompletedCompanies = ndjhzbDao.getEntryCompletedCompanies(date);
					time = ndjhzbDao.getEntryTime(date, comp);
					break;
				case YDJDMJH:
					entryCompletedCompanies = ydjhzbDao.getEntryCompletedCompanies(date);
					time = ydjhzbDao.getEntryTime(date, comp);
					break;
				}
				
				result.add(new String[]{comp.getName(), "true", null != time ? Util.formatToDay(time) : null});
			} else{
				result.add(new String[]{comp.getName(), "false", null});
			}
		}

		return result;
	}
}
