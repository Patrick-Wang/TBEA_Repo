package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
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
import com.tbea.ic.operation.service.ydzb.gszb.pipe.GszbPipe;



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
	
	CompanyManager companyManager;

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
		YDJHZB ydjhzb;
		JSONArray row;
		boolean newEntity = false;
		cal.setTime(date);
		List<Boolean> approvedList = isApproved(date, company.getType(), ZBType.YDJDMJH);
		for (int i = 0; i < data.size(); ++i){
			cal.setTime(date);
			row = data.getJSONArray(i);
			for (int j = 0; j < approvedList.size() && j < (row.size() - 1); ++j){
				if (!approvedList.get(j)){
					newEntity = false;
					ydjhzb = ydjhzbDao.getZb(Integer.valueOf(row.getString(0)), Util.toDate(cal), company);
					if (null == ydjhzb){
						newEntity = true;
						ydjhzb = new YDJHZB();
						ydjhzb.setZbxx(zbxxDao.getById(Integer.valueOf(row.getString(0))));
						ydjhzb.setDwxx(dwxxDao.getById(company.getId()));
					}
					ydjhzb.setYdjhshzt(shztDao.getById(2));
					ydjhzb.setYdjhxgsj(new java.sql.Date(new java.util.Date().getTime()));
					ydjhzb.setNf(cal.get(Calendar.YEAR));
					ydjhzb.setYf(cal.get(Calendar.MONTH) + 1);
					ydjhzb.setYdjhz(Util.toDouble(row.getString(j + 1)));
					if (newEntity){
						ydjhzbDao.persist(ydjhzb);
					} else{
						ydjhzbDao.merge(ydjhzb);
					}
				}
				cal.add(Calendar.MONTH, 1);
			}
		}

		return true;

	}

	
	private boolean isYszkzb(int zbId){
		return zbId == GSZB.YSZK.getValue();
	}
	
	private boolean isChzb(int zbId){
		return zbId == GSZB.CH.getValue();
	}
	
	private boolean isXssrzb(int zbId){
		return GSZB.XSSR.getValue() == zbId;
	}
	

	private void updateComputedZb(Double xssr, Calendar cal, Company company){
		if (xssr != null){
			boolean newEntity = false;
			NDJHZB zb = ndjhzbDao.getZb(GSZB.YSZK.getValue(), Util.toDate(cal), company);
			if (null == zb){
				newEntity = true;
				zb = new NDJHZB();
				zb.setZbxx(zbxxDao.getById(GSZB.YSZK.getValue()));
				zb.setDwxx(dwxxDao.getById(company.getId()));
			}
			zb.setNdjhshzt(shztDao.getById(2));
			zb.setNdjhxgsj(new java.sql.Date(new java.util.Date().getTime()));
			zb.setNf(cal.get(Calendar.YEAR));
			zb.setNdjhz(xssr * sbdNdjhzbDao.getYszb(cal.get(Calendar.YEAR), company));
			if (newEntity){
				ndjhzbDao.persist(zb);
			} else{
				ndjhzbDao.merge(zb);
			}
			
			newEntity = false;
			zb = ndjhzbDao.getZb(GSZB.CH.getValue(), Util.toDate(cal), company);
			if (null == zb){
				newEntity = true;
				zb = new NDJHZB();
				zb.setZbxx(zbxxDao.getById(GSZB.CH.getValue()));
				zb.setDwxx(dwxxDao.getById(company.getId()));
			}
			zb.setNdjhshzt(shztDao.getById(2));
			zb.setNdjhxgsj(new java.sql.Date(new java.util.Date().getTime()));
			zb.setNf(cal.get(Calendar.YEAR));
			zb.setNdjhz(xssr * sbdNdjhzbDao.getChzb(cal.get(Calendar.YEAR), company));
			if (newEntity){
				ndjhzbDao.persist(zb);
			} else{
				ndjhzbDao.merge(zb);
			}
		}
	}
	
	private boolean updateNDJH(Date date, Company company, JSONArray data) {
		Calendar cal = Calendar.getInstance();
		NDJHZB zb;
		NDJHZBDao zbDao = ndjhzbDao;
		JSONArray row;
		boolean newEntity = false;
		cal.setTime(date);

		List<Boolean> approvedList = isApproved(date, company.getType(), ZBType.NDJH);
		boolean isSbd = (sbdNdjhzbDao.getChzb(cal.get(Calendar.YEAR), company) != null);
		Double xssr = null;
		Integer zbId = null;
		if (!approvedList.get(0)){
			for (int i = 0; i < data.size(); ++i){
				row = data.getJSONArray(i);
				zbId = Integer.valueOf(row.getString(0));
				
				if (isSbd) {
					if (isYszkzb(zbId) && isChzb(zbId)){
						continue;
					}else if (isXssrzb(zbId)){
						 xssr = Util.toDouble(row.getString(1));
					}
				}
	
				zb = zbDao.getZb(zbId, Util.toDate(cal), company);
				if (null == zb){
					newEntity = true;
					zb = new NDJHZB();
					zb.setZbxx(zbxxDao.getById(Integer.valueOf(row.getString(0))));
					zb.setDwxx(dwxxDao.getById(company.getId()));
				}
				zb.setNdjhshzt(shztDao.getById(2));
				zb.setNdjhxgsj(new java.sql.Date(new java.util.Date().getTime()));
				zb.setNf(cal.get(Calendar.YEAR));
				zb.setNdjhz(Util.toDouble(row.getString(1)));
				if (newEntity){
					zbDao.persist(zb);
				} else{
					zbDao.merge(zb);
				}
			}
			
			updateComputedZb(xssr, cal, company);
		}
		return true;
	}

	private boolean updateBYSJ(Date date, Company company, JSONArray data) {
		Calendar cal = Calendar.getInstance();
		SJZB zb;
		JSONArray row;
		boolean newEntity = false;
		cal.setTime(date);
		
		List<Boolean> approvedList = isApproved(date, company.getType(), ZBType.BYSJ);
		if (!approvedList.get(0)){
			for (int i = 0; i < data.size(); ++i){
				row = data.getJSONArray(i);
				zb = sjzbDao.getZb(Integer.valueOf(row.getString(0)), Util.toDate(cal), company);
				if (null == zb){
					newEntity = true;
					zb = new SJZB();
					zb.setZbxx(zbxxDao.getById(Integer.valueOf(row.getString(0))));
					zb.setDwxx(dwxxDao.getById(company.getId()));
				}
				zb.setSjshzt(shztDao.getById(2));
				zb.setSjxgsj(new java.sql.Date(new java.util.Date().getTime()));
				zb.setNf(cal.get(Calendar.YEAR));
				zb.setYf(cal.get(Calendar.MONTH) + 1);
				zb.setSjz(Util.toDouble(row.getString(1)));
				if (newEntity){
					sjzbDao.persist(zb);
				} else{
					sjzbDao.merge(zb);
				}
			}
			cal.setTime(date);
			setYdzbzt(company, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, ZBType.BYSJ);
		}
		return true;
	}

	private boolean update28YJ(Date date, Company company, JSONArray data) {
		Calendar cal = Calendar.getInstance();
		int leftMonth;
		YJ20ZB zb20;
		YJ28ZB zb28;
		YJ28ZBDao zbDao = yj28zbDao;
		JSONArray row;
		boolean newEntity = false;
		cal.setTime(date);
		leftMonth = 3 - (cal.get(Calendar.MONTH) + 1) % 3;

		List<Boolean> approvedList = isApproved(date, company.getType(),
				ZBType.BY28YJ);
		
		
		for (int i = 0; i < data.size(); ++i) {
			cal.setTime(date);
			row = data.getJSONArray(i);

			if (!approvedList.get(0)) {
				newEntity = false;
				zb28 = zbDao.getZb(Integer.valueOf(row.getString(0)),
						Util.toDate(cal), company);
				if (null == zb28) {
					newEntity = true;
					zb28 = new YJ28ZB();
					zb28.setZbxx(zbxxDao.getById(Integer.valueOf(row
							.getString(0))));
					zb28.setDwxx(dwxxDao.getById(company.getId()));
				}
				zb28.setYj28shzt(shztDao.getById(2));
				zb28.setYj28xgsj(new java.sql.Date(new java.util.Date()
						.getTime()));
				zb28.setNf(cal.get(Calendar.YEAR));
				zb28.setYf(cal.get(Calendar.MONTH) + 1);
				zb28.setYj28z(Util.toDouble(row.getString(1)));
				if (newEntity) {
					yj28zbDao.persist(zb28);
				} else {
					yj28zbDao.merge(zb28);
				}
			}
			cal.add(Calendar.MONTH, 1);

			for (int j = 1; j <= leftMonth && j < (row.size() - 1); ++j) {
				if (!approvedList.get(j)) {
					newEntity = false;
					zb20 = yj20zbDao.getZb(Integer.valueOf(row.getString(0)),
							Util.toDate(cal), company);
					if (null == zb20) {
						newEntity = true;
						zb20 = new YJ20ZB();
						zb20.setZbxx(zbxxDao.getById(Integer.valueOf(row
								.getString(0))));
						zb20.setDwxx(dwxxDao.getById(company.getId()));
					}
					zb20.setYj20shzt(shztDao.getById(2));
					zb20.setYj20xgsj(new java.sql.Date(new java.util.Date()
							.getTime()));
					zb20.setNf(cal.get(Calendar.YEAR));
					zb20.setYf(cal.get(Calendar.MONTH) + 1);
					zb20.setYj20z(Util.toDouble(row.getString(j + 1)));
					if (newEntity) {
						yj20zbDao.persist(zb20);
					} else {
						yj20zbDao.merge(zb20);
					}
				}
				cal.add(Calendar.MONTH, 1);
			}
		}
		cal.setTime(date);
		if (!approvedList.get(0)){
			
			setYdzbzt(company, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, ZBType.BY28YJ);
		}
		
		cal.add(Calendar.MONTH, 1);
		for (int i = 1; i < approvedList.size(); ++i){
			if (!approvedList.get(i)){
				setYdzbzt(company, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, ZBType.BY20YJ);
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		return true;
	}

	private boolean update20YJ(Date date, Company company, JSONArray data) {
		Calendar cal = Calendar.getInstance();
		int leftMonth;
		YJ20ZB zb;
		YJ20ZBDao zbDao = yj20zbDao;
		JSONArray row;
		boolean newEntity = false;
		
		List<Boolean> approvedList = isApproved(date, company.getType(), ZBType.BY20YJ);
		
		for (int i = 0; i < data.size(); ++i) {
			cal.setTime(date);
			leftMonth = 3 - (cal.get(Calendar.MONTH) + 1) % 3;

			row = data.getJSONArray(i);
			for (int j = 0; j <= leftMonth && j < (row.size() - 1); ++j) {

				if (!approvedList.get(j)) {

					newEntity = false;
					zb = zbDao.getZb(Integer.valueOf(row.getString(0)),
							Util.toDate(cal), company);
					if (null == zb) {
						newEntity = true;
						zb = new YJ20ZB();
						zb.setZbxx(zbxxDao.getById(Integer.valueOf(row
								.getString(0))));
						zb.setDwxx(dwxxDao.getById(company.getId()));
					}
					zb.setYj20shzt(shztDao.getById(2));
					zb.setYj20xgsj(new java.sql.Date(new java.util.Date()
							.getTime()));
					zb.setNf(cal.get(Calendar.YEAR));
					zb.setYf(cal.get(Calendar.MONTH) + 1);
					zb.setYj20z(Util.toDouble(row.getString(j + 1)));
					if (newEntity) {
						zbDao.persist(zb);
					} else {
						zbDao.merge(zb);
					}
				}
				cal.add(Calendar.MONTH, 1);
			}
		}
		
		cal.setTime(date);
		for (int i =0; i < approvedList.size(); ++i){
			if (!approvedList.get(i)){
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
			String[] row = new String[size];
			map.put(zbxx.getId(), row);
			row[0] = zbxx.getId() + "";
			row[1] = zbxx.getName();
		}
		return map;
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
			result.add(new String[]{comp.getName(), entryCompletedCompanies.contains(comp.getId()) + ""});
		}

		return result;
	}
}
