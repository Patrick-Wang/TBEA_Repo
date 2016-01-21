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

import com.tbea.ic.operation.common.RequestHandler;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
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
import com.tbea.ic.operation.service.entry.zbCalculator.Request;
import com.tbea.ic.operation.service.entry.zbCalculator.ZbCalculator;
import com.tbea.ic.operation.service.entry.zbInjector.SimpleZbInjectorFactory;
import com.tbea.ic.operation.service.entry.zbInjector.ZbInjector;



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
	

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	ZbInjector ndjhzbInjector;
	ZbInjector ydjhzbInjector;
	ZbInjector yd28Injector;
	ZbInjector yj20Injector;
	ZbInjector sjzbInjector;
		
	@Autowired
	public void init(){
		ndjhzbInjector = SimpleZbInjectorFactory.createInjector(zbxxDao, dwxxDao, shztDao, ndjhzbDao);
		ydjhzbInjector = SimpleZbInjectorFactory.createInjector(zbxxDao, dwxxDao, shztDao, ydjhzbDao);
		yd28Injector = SimpleZbInjectorFactory.createInjector(zbxxDao, dwxxDao, shztDao, yj28zbDao);
		yj20Injector = SimpleZbInjectorFactory.createInjector(zbxxDao, dwxxDao, shztDao, yj20zbDao);
		sjzbInjector = SimpleZbInjectorFactory.createInjector(zbxxDao, dwxxDao, shztDao, sjzbDao);
	}
	

	
	private ZbCalculator createYdjhzbCalc(RequestHandler<Request> handler) {
		return new GeneralZbCalculator(ydjhzbInjector, handler);
		
	}

	private ZbCalculator createNdjhzbCalc(CompanyManager mgr, RequestHandler<Request> handler) {
		return new NdjhZbCalculator(mgr, ndjhzbInjector, this.sbdNdjhzbDao, handler);
	}

	private ZbCalculator createYj28Calc() {
		return new GeneralZbCalculator(yd28Injector, CalculatedZbManager.getSJHandler());
	}

	private ZbCalculator createYj20Calc() {
		return new GeneralZbCalculator(yj20Injector, CalculatedZbManager.getSJHandler());
	}

	private ZbCalculator createSjzbCalc() {
		return new GeneralZbCalculator(sjzbInjector, CalculatedZbManager.getSJHandler());
	}

	public static void setYdzbzt(YDZBZTDao ydzbztDao, DWXXDao dwxxDao, Company comp, int nf, int yf, ZBType entryType){
		boolean newEntity = false;
		YDZBZT ydzbzt = ydzbztDao.getYdzbzt(comp, nf, yf);
		if (null == ydzbzt){
			newEntity = true;
			ydzbzt = new YDZBZT();
			ydzbzt.setDwxx(dwxxDao.getById(comp.getId()));
			ydzbzt.setNf(nf);
			ydzbzt.setYf(yf);
			ydzbzt.setZt(0);
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
		
		if (newEntity){
			ydzbztDao.create(ydzbzt);
		}else{
			ydzbztDao.merge(ydzbzt);
		}
	}
	
	@Override
	public boolean submitZb(Date date, Account account, CompanyType comp,
			ZBType entryType, JSONArray data) {
		
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		boolean bRet = false;
		switch (entryType){
		case BY20YJ:
			bRet = update20YJ(date, company, data, ZBStatus.SUBMITTED);
			break;
		case BY28YJ:
			bRet = update28YJ(date, company, data, ZBStatus.SUBMITTED);
			break;
		case BYSJ:
			bRet = updateBYSJ(date, company, data, ZBStatus.SUBMITTED);
			break;
		case NDJH:
			bRet = updateNDJH(date, company, data, ZBStatus.SUBMITTED);
			break;
		case YDJDMJH:
			bRet = updateYDJDMJH(date, company, data, ZBStatus.SUBMITTED);
			break;
		default:
			break;
		}
		return bRet;
	}

	private boolean updateYDJDMJH(Date date, Company company, JSONArray data, ZBStatus status) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Set<Integer> unenteredZb = getUnenteredJhzb(company, data);
		List<ZBStatus> approvedList = getZbStatus(date, company.getType(), ZBType.YDJDMJH);

		Integer zbId;
		JSONArray row;
		Double val;
		
		for (int c = 0; c < approvedList.size(); ++c) {
			if (ZBStatus.APPROVED != approvedList.get(c)) {
				ZbCalculator calc = this.createYdjhzbCalc(CalculatedZbManager.getJHHandler(cal));
				for (int r = 0; r < data.size(); ++r) {
					row = data.getJSONArray(r);
					zbId = Integer.valueOf(row.getString(0));
					if (!row.getString(c + 1).isEmpty()) {
						val = Util.toDouble(row.getString(c + 1));
						calc.compute(zbId, val, cal, company, status);
					}
				}

				for (Integer id : unenteredZb) {
					calc.compute(id, null, cal, company, status);
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

	private boolean updateNDJH(Date date, Company company, JSONArray data, ZBStatus status) {
		List<ZBStatus> approvedList = getZbStatus(date, company.getType(), ZBType.NDJH);
		if (ZBStatus.APPROVED != approvedList.get(0)){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			JSONArray row;
			
			ZbCalculator calc = this.createNdjhzbCalc(companyManager, CalculatedZbManager.getJHHandler(cal));
			for (int r = 0; r < data.size(); ++r) {
				row = data.getJSONArray(r);
				if (!row.getString(1).isEmpty()) {
					calc.compute(Integer.valueOf(row.getString(0)),
							Util.toDouble(row.getString(1)), cal, company,
							status);
				}

			}

			Set<Integer> unenteredZb = getUnenteredJhzb(company, data);
			for (Integer id : unenteredZb) {
				calc.compute(id, null, cal, company,
						status);
			}

		}
		return true;
	}

	//[zbid, value]
	//[zbid, value]
	//[zbid, value]
	//[zbid, value]
	//[zbid, value]
	private boolean updateBYSJ(Date date, Company company, JSONArray data, ZBStatus status) {
		List<ZBStatus> approvedList = getZbStatus(date, company.getType(), ZBType.BYSJ);
		if (ZBStatus.APPROVED != approvedList.get(0)){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			JSONArray row;
			ZbCalculator calc = this.createSjzbCalc();
			for (int i = 0; i < data.size(); ++i) {
				row = data.getJSONArray(i);
				if (!row.getString(1).isEmpty()) {
					calc.compute(Integer.valueOf(row.getString(0)),
							Util.toDouble(row.getString(1)), cal, company,
							status);
				}

			}

			Set<Integer> unenteredZb = getUnenteredSjzb(company, data);
			for (Integer id : unenteredZb) {
				calc.compute(id, null, cal, company, status);
			}

			if (status == ZBStatus.SUBMITTED) {
				setYdzbzt(ydzbztDao, dwxxDao, company, cal.get(Calendar.YEAR),
						cal.get(Calendar.MONTH) + 1, ZBType.BYSJ);
			}
		}
		return true;
	}

	private boolean update28YJ(Date date, Company company, JSONArray data, ZBStatus status) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer zbId;
		JSONArray row;

		Set<Integer> unenteredZb = getUnenteredSjzb(company, data);
		List<ZBStatus> approvedList = getZbStatus(date, company.getType(), ZBType.BY28YJ);
		
		if (ZBStatus.APPROVED != approvedList.get(0)) {
			ZbCalculator calc = this.createYj28Calc();
			for (int r = 0; r < data.size(); ++r) {
				row = data.getJSONArray(r);
				zbId = Integer.valueOf(row.getString(0));
				if (!row.getString(1).isEmpty()) {
					calc.compute(
							zbId, 
							Util.toDouble(row.getString(1)), 
							cal, 
							company,
							status);	
				}
			}
			
			for (Integer id : unenteredZb) {
				calc.compute(id, null, cal, company,
						status);
			}
			
			if (status == ZBStatus.SUBMITTED) {
				setYdzbzt(ydzbztDao, dwxxDao, company, cal.get(Calendar.YEAR),
						cal.get(Calendar.MONTH) + 1, ZBType.BY28YJ);
			}
		}
		
		for (int c = 1; c < approvedList.size(); ++c) {
			cal.add(Calendar.MONTH, 1);
			if (ZBStatus.APPROVED != approvedList.get(c)) {
				ZbCalculator calc = this.createYj20Calc();
				for (int r = 0; r < data.size(); ++r) {
					row = data.getJSONArray(r);
					zbId = Integer.valueOf(row.getString(0));
					if (!row.getString(c + 1).isEmpty()) {
						calc.compute(zbId,
								Util.toDouble(row.getString(c + 1)), cal,
								company, status);
					}
				}

				for (Integer id : unenteredZb) {
					calc.compute(id, null, cal, company, status);
				}

				if (status == ZBStatus.SUBMITTED) {
					setYdzbzt(ydzbztDao, dwxxDao, company, cal.get(Calendar.YEAR),
							cal.get(Calendar.MONTH) + 1, ZBType.BY20YJ);
				}
			}
		}
		return true;
	}

	private boolean update20YJ(Date date, Company company, JSONArray data, ZBStatus status) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		JSONArray row;
		Integer zbId;
		
		Set<Integer> unenteredZb = getUnenteredSjzb(company, data);
		List<ZBStatus> approvedList = getZbStatus(date, company.getType(), ZBType.BY20YJ);
		for (int c = 0; c < approvedList.size(); ++c) {
			if (ZBStatus.APPROVED != approvedList.get(c)) {
				ZbCalculator calc = this.createYj20Calc();
				for (int r = 0; r < data.size(); ++r) {
					row = data.getJSONArray(r);
					zbId = Integer.valueOf(row.getString(0));
					if (!row.getString(c + 1).isEmpty()) {
						calc.compute(zbId,
								Util.toDouble(row.getString(c + 1)), cal,
								company, status);
					}
				}

				for (Integer id : unenteredZb) {
					calc.compute(id, null, cal, company, status);
				}

				if (status == ZBStatus.SUBMITTED) {
					setYdzbzt(ydzbztDao, dwxxDao, company, cal.get(Calendar.YEAR),
							cal.get(Calendar.MONTH) + 1, ZBType.BY20YJ);
				}
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
		
		Set<ZBXX> zbxxs = dwxx.getJhzbxxs();
		
		
		Map<Integer, String[]> map = creatZBXXMap(zbxxs, 5, CalculatedZbManager.getJHCalculatedZbs(cal));
		for (int i = 0; i < 3; ++i){
			List<YDJHZB> zbs = ydjhzbDao.getZbs(Util.toDate(cal), company);
			unifyYdjhZbs(zbxxs, zbs);
			
			cal.add(Calendar.MONTH, 1);
			updateYJJDMap(map, zbs, i);
		}
		
		return toArray(map);
	}


	
	private void unifyYdjhZbs(Set<ZBXX> zbxxs, List<YDJHZB> zbs) {
		boolean found = false;
		for (YDJHZB zb : zbs){
			found = false;
			for (ZBXX zbxx: zbxxs){
				if (zb.getZbxx().getId() == zbxx.getId()){
					found = true;
					break;
				}
			}
			if (!found){
				ydjhzbDao.delete(zb);
			}
		}
	}

	private void updateYJJDMap(Map<Integer, String[]> map, List<YDJHZB> zbs, int col) {
		for(YDJHZB zb : zbs){
			String[] row = map.get(zb.getZbxx().getSequence());
			if (null != row){
				row[col + 2] = zb.getYdjhz() + "";
			}
		}
	}

	private List<String[]> getNDJH(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		DWXX dwxx = dwxxDao.getById(company.getId());
		
		Set<ZBXX> zbxxs = dwxx.getJhzbxxs();
		List<NDJHZB> zbs =  ndjhzbDao.getZbs(Util.toDate(cal), company);
		unifyNdjhZbs(zbxxs, zbs);

		Map<Integer, String[]> map = creatZBXXMap(zbxxs, 3, CalculatedZbManager.getJHCalculatedZbs(cal));
		

		for(NDJHZB zb : zbs){
			String[] row = map.get(zb.getZbxx().getSequence());
			if (null != row){
				row[2] = zb.getNdjhz() + "";
			}
		}
		return toArray(map);
	}


	private void unifyNdjhZbs(Set<ZBXX> zbxxs, List<NDJHZB> zbs) {
		boolean found = false;
		for (NDJHZB zb : zbs){
			found = false;
			for (ZBXX zbxx: zbxxs){
				if (zb.getZbxx().getId() == zbxx.getId()){
					found = true;
					break;
				}
			}
			if (!found){
				ndjhzbDao.delete(zb);
			}
		}
	}

	private List<String[]> getBYSJ(Date date, Company company) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		DWXX dwxx = dwxxDao.getById(company.getId());
		
		Set<ZBXX> zbxxs = dwxx.getSjzbxxs();
		List<SJZB> sjzbs =  sjzbDao.getZbs(Util.toDate(cal), company);
		unifySjZbs(zbxxs, sjzbs);
		
		Map<Integer, String[]> map = creatZBXXMap(zbxxs, 3, CalculatedZbManager.getSJCalculatedZbs());

		for(SJZB zb : sjzbs){
			String[] row = map.get(zb.getZbxx().getSequence());
			if (null != row){
				row[2] = zb.getSjz() + "";
			}
		}
		
		return toArray(map);
	}

	private void unifySjZbs(Set<ZBXX> zbxxs, List<SJZB> zbs) {
		boolean found = false;
		for (SJZB zb : zbs){
			found = false;
			for (ZBXX zbxx: zbxxs){
				if (zb.getZbxx().getId() == zbxx.getId()){
					found = true;
					break;
				}
			}
			if (!found){
				sjzbDao.delete(zb);
			}
		}
	}

	private List<String[]> get28YJ(Date date, Company company) {
		DWXX dwxx = dwxxDao.getById(company.getId());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int leftMonth = 3 - (cal.get(Calendar.MONTH) + 1) % 3;
		Set<ZBXX> zbxxs = dwxx.getSjzbxxs();
		List<YJ28ZB> yj28zbs =  yj28zbDao.getZbs(Util.toDate(cal), company);
		unify28Zbs(zbxxs, yj28zbs);
		
		Map<Integer, String[]> map = creatZBXXMap(zbxxs, leftMonth + 3, CalculatedZbManager.getSJCalculatedZbs());
		updateYJ28Map(map, yj28zbs, 0);
		cal.add(Calendar.MONTH, 1);
		
		for (int i = 1; i <= leftMonth; ++i){
			List<YJ20ZB> yj20zbs =  yj20zbDao.getZbs(Util.toDate(cal), company);
			unify20Zbs(zbxxs, yj20zbs);
			updateYJ20Map(map, yj20zbs, i);
			cal.add(Calendar.MONTH, 1);
		}
		
		return toArray(map);
	}

	private void unify20Zbs(Set<ZBXX> zbxxs, List<YJ20ZB> yj20zbs) {
		boolean found = false;
		for (YJ20ZB zb : yj20zbs){
			found = false;
			for (ZBXX zbxx: zbxxs){
				if (zb.getZbxx().getId() == zbxx.getId()){
					found = true;
					break;
				}
			}
			if (!found){
				yj20zbDao.delete(zb);
			}
		}
	}

	private void unify28Zbs(Set<ZBXX> zbxxs, List<YJ28ZB> yj28zbs) {
		boolean found = false;
		for (YJ28ZB zb : yj28zbs){
			found = false;
			for (ZBXX zbxx: zbxxs){
				if (zb.getZbxx().getId() == zbxx.getId()){
					found = true;
					break;
				}
			}
			if (!found){
				yj28zbDao.delete(zb);
			}
		}
	}

	private void updateYJ28Map(Map<Integer, String[]> map, List<YJ28ZB> zbs, int col) {
		String[] tmp = null;
		for (int i = 0; i < zbs.size(); ++i){
			tmp =  map.get(zbs.get(i).getZbxx().getSequence());
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
	
	private Map<Integer, String[]> creatZBXXMap(Set<ZBXX> zbxxs, int size, Set<Integer> calculateZbs){
		Map<Integer, String[]> map = new HashMap<Integer, String[]>();
		for (ZBXX zbxx : zbxxs){
			if (!calculateZbs.contains(zbxx.getId())){
				String[] row = new String[size];
				map.put(zbxx.getSequence(), row);
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
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		DWXX dwxx = dwxxDao.getById(company.getId());
		Set<ZBXX> zbxxs = dwxx.getSjzbxxs();
		
		int leftMonth = 3 - (cal.get(Calendar.MONTH) + 1) % 3;
		
		Map<Integer, String[]> map = creatZBXXMap(zbxxs, leftMonth + 3, CalculatedZbManager.getSJCalculatedZbs());
		for (int i = 0; i <= leftMonth; ++i){
			List<YJ20ZB> zbs = yj20zbDao.getZbs(Util.toDate(cal), company);
			unify20Zbs(zbxxs, zbs);
			
			updateYJ20Map(map, zbs, i);
			cal.add(Calendar.MONTH, 1);
		}
		return toArray(map);
	}

	private void updateYJ20Map(Map<Integer, String[]> map, List<YJ20ZB> zbs, int col) {
		String[] tmp = null;
		for (int i = 0; i < zbs.size(); ++i){
			tmp =  map.get(zbs.get(i).getZbxx().getSequence());
			if (null != tmp){
				tmp[col + 2] = zbs.get(i).getYj20z() + "";
			}		
		}
	}
	
	
	@Override
	public List<ZBStatus> getZbStatus(Date date, CompanyType comp, ZBType entryType) {
		List<ZBStatus> result = new ArrayList<ZBStatus>();
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int leftMonth = 3 - (cal.get(Calendar.MONTH) + 1) % 3;
		switch (entryType){
		case BY20YJ:
			for (int i = 0; i <= leftMonth; ++i){
				result.add(yj20zbDao.getZbStatus(Util.toDate(cal), company));
				cal.add(Calendar.MONTH, 1);
			}
			break;
		case BY28YJ:
			result.add(yj28zbDao.getZbStatus(Util.toDate(cal), company));
			cal.add(Calendar.MONTH, 1);
			for (int i = 1; i <= leftMonth; ++i){
				result.add(yj20zbDao.getZbStatus(Util.toDate(cal), company));
				cal.add(Calendar.MONTH, 1);
			}
			break;
		case BYSJ:
			result.add(sjzbDao.getZbStatus(date, company));
			break;
		case NDJH:
			result.add(ndjhzbDao.getZbStatus(date, company));
			break;
		case YDJDMJH:
			for (int i = 0; i < 3; ++i){
				result.add(ydjhzbDao.getZbStatus(Util.toDate(cal), company));
				cal.add(Calendar.MONTH, 1);
			}
			break;
		default:
			break;
		}
		return result;
	}
	
	private Map<Integer, String> toCompanyMap(List<Object[]> entryCompletedCompanies){
		 Map<Integer, String>  compMap = new HashMap<Integer, String>();
		for (Object[] obj : entryCompletedCompanies){
			compMap.put((Integer)obj[0], (String)obj[1]);
		}
		return compMap;
	}
	
	@Override
	public List<String[]> getEntryStatus(Date date, ZBType entryType, List<Company> mainCompanies) {
		List<String[]> result = new ArrayList<String[]>();
		 Map<Integer, String> entryCompletedCompanies = null;
		switch (entryType){
		case BY20YJ:
			entryCompletedCompanies = toCompanyMap(yj20zbDao.getEntryCompletedCompanies(date));
			break;
		case BY28YJ:
			entryCompletedCompanies = toCompanyMap(yj28zbDao.getEntryCompletedCompanies(date));
			break;
		case BYSJ:
			entryCompletedCompanies = toCompanyMap(sjzbDao.getEntryCompletedCompanies(date));
			break;
		case NDJH:
			entryCompletedCompanies = toCompanyMap(ndjhzbDao.getEntryCompletedCompanies(date));
			break;
		case YDJDMJH:
			entryCompletedCompanies = toCompanyMap(ydjhzbDao.getEntryCompletedCompanies(date));
			break;
		default:
			return result;
		}
		
		for (Company comp : mainCompanies){	
				if (entryCompletedCompanies.containsKey(comp.getId())){
				Date time = null;
				switch (entryType){
				case BY20YJ:
					time = yj20zbDao.getEntryTime(date, comp);
					break;
				case BY28YJ:
					time = yj28zbDao.getEntryTime(date, comp);
					break;
				case BYSJ:
					time = sjzbDao.getEntryTime(date, comp);
					break;
				case NDJH:
					time = ndjhzbDao.getEntryTime(date, comp);
					break;
				case YDJDMJH:
					time = ydjhzbDao.getEntryTime(date, comp);
					break;
				}
				
				result.add(new String[]{comp.getName(), entryCompletedCompanies.get(comp.getId()), null != time ? Util.formatToDay(time) : null});
			} else{
				result.add(new String[]{comp.getName(), null, null});
			}
		}

		return result;
	}

	@Override
	public boolean saveZb(Date date, Account account, CompanyType comp,
			ZBType entryType, JSONArray data) {
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		boolean bRet = false;
		switch (entryType){
		case BY20YJ:
			bRet = update20YJ(date, company, data, ZBStatus.SAVED);
			break;
		case BY28YJ:
			bRet = update28YJ(date, company, data, ZBStatus.SAVED);
			break;
		case BYSJ:
			bRet = updateBYSJ(date, company, data, ZBStatus.SAVED);
			break;
		case NDJH:
			bRet = updateNDJH(date, company, data, ZBStatus.SAVED);
			break;
		case YDJDMJH:
			bRet = updateYDJDMJH(date, company, data, ZBStatus.SAVED);
			break;
		default:
			break;
		}
		return bRet;
	}
	
	@Override
	public boolean submitToDeputy(Date date, Account account, CompanyType comp,
			ZBType entryType, JSONArray data) {
		Company company = companyManager.getBMDBOrganization().getCompany(comp);
		boolean bRet = false;
		switch (entryType){
		case BY20YJ:
			bRet = update20YJ(date, company, data, ZBStatus.SUBMITTED_2);
			break;
		case BY28YJ:
			bRet = update28YJ(date, company, data, ZBStatus.SUBMITTED_2);
			break;
		case BYSJ:
			bRet = updateBYSJ(date, company, data, ZBStatus.SUBMITTED_2);
			break;
		case NDJH:
			bRet = updateNDJH(date, company, data, ZBStatus.SUBMITTED_2);
			break;
		case YDJDMJH:
			bRet = updateYDJDMJH(date, company, data, ZBStatus.SUBMITTED_2);
			break;
		default:
			break;
		}
		return bRet;
	}

	@Override
	public boolean hasMarketPermission(Account account) {
		// TODO Auto-generated method stub
		return account.getRole() == 2;
	}
}
