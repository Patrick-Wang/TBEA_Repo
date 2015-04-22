package com.tbea.ic.operation.service.approve;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.DateHelper;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
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
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.service.approve.OnGetStatus;
import com.tbea.ic.operation.service.entry.EntryServiceImpl;

@Service
@Transactional("transactionManager")
public class ApproveServiceImpl implements ApproveService {

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
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Override
	public List<List<String[]>> getZb(Account account, List<Company> comps,
			Date date, ZBType approveType) {
		List<List<String[]>> ret = null;
		switch (approveType) {
		case BY20YJ:
			ret = get20Zb(account, comps, date);
			break;
		case BY28YJ:
			ret = get28Zb(account, comps, date);
			break;
		case BYSJ:
			ret = getBysjZb(account, comps, date);
			break;
		case NDJH:
			ret = getNdjhZb(account, comps, date);
			break;
		case YDJDMJH:
			ret = getYdjdZb(account, comps, date);
			break;
		default:
			break;
		}
		return ret;
	}

	private DWXX findDwxx(List<DWXX> dwxxs, Company comp) {
		for (DWXX dwxx : dwxxs) {
			if (dwxx.getId() == comp.getId()) {
				return dwxx;
			}
		}
		return null;
	}

	private List<ZBXX> toSortList(Set<ZBXX> zbSet) {
		List<ZBXX> zbList = new ArrayList<ZBXX>();
		boolean inserted = false;
		for (ZBXX zbxx : zbSet) {
			inserted = false;
			for (int i = 0; i < zbList.size(); ++i) {
				if (zbList.get(i).getId() > zbxx.getId()) {
					inserted = true;
					zbList.add(i, zbxx);
					break;
				}
			}
			if (!inserted) {
				zbList.add(zbxx);
			}
		}
		return zbList;
	}

	private NDJHZB findNdzb(Integer id, List<NDJHZB> ndjhzbs) {
		for (NDJHZB ndjhzb : ndjhzbs) {
			if (ndjhzb.getZbxx().getId() == id) {
				return ndjhzb;
			}
		}
		return null;
	}

	private YDJHZB findYdjhzb(Integer id, List<YDJHZB> ydjhzbs) {
		for (YDJHZB ydjhzb : ydjhzbs) {
			if (ydjhzb.getZbxx().getId() == id) {
				return ydjhzb;
			}
		}
		return null;
	}

	private SJZB findSjzb(Integer id, List<SJZB> zbs) {
		for (SJZB zb : zbs) {
			if (zb.getZbxx().getId() == id) {
				return zb;
			}
		}
		return null;
	}

	private YJ28ZB findYj28zb(Integer id, List<YJ28ZB> zbs) {
		for (YJ28ZB zb : zbs) {
			if (zb.getZbxx().getId() == id) {
				return zb;
			}
		}
		return null;
	}

	private YJ20ZB findYj20zb(Integer id, List<YJ20ZB> zbs) {
		for (YJ20ZB zb : zbs) {
			if (zb.getZbxx().getId() == id) {
				return zb;
			}
		}
		return null;
	}

	// [[compId ,zbId, zbName, value, year, month] ...] approved
	// [[compId ,zbId, zbName, value, year, month] ...] unapproved
	private List<List<String[]>> getYdjdZb(Account account,
			List<Company> comps, Date date) {
		List<DWXX> dwxxs = dwxxDao.getDwxxs(comps);
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateHelper.getJdStart(date));
		ZBDispatcher dispatcher = new ZBDispatcher(6);
		for (int m = 0; m < 3; ++m) {
			Date d = Util.toDate(cal);
			for (Company comp : comps) {
				ZBStatus status = ydjhzbDao.getZbStatus(date, comp);

				if (!validateConsistency(account, status)) {
					continue;
				}

				DWXX dwxx = findDwxx(dwxxs, comp);
				if (null != dwxx) {

					List<ZBXX> allZbs = toSortList(dwxx.getJhzbxxs());
					List<YDJHZB> zbs = ydjhzbDao.getZbs(d, comp);

					for (int i = 0, len = allZbs.size(); i < len; ++i) {
						String[] zbTmp = dispatcher.createZbArray();
						zbTmp[0] = comp.getType().ordinal() + "";
						zbTmp[1] = allZbs.get(i).getId() + "";
						zbTmp[2] = allZbs.get(i).getName();
						YDJHZB zb = findYdjhzb(allZbs.get(i).getId(), zbs);
						if (null != zb) {
							zbTmp[3] = zb.getYdjhz() + "";
						}
						zbTmp[4] = cal.get(Calendar.YEAR) + "";
						zbTmp[5] = cal.get(Calendar.MONTH) + 1 + "";
						dispatcher.dispatch(status, zbTmp);
					}
				}
			}
			cal.add(Calendar.MONTH, 1);
		}

		return dispatcher.getResult();
	}

	//校验当前用户是否可以看到对应指标状态的指标
	private boolean validateConsistency(Account account, ZBStatus status) {
		if (1 == account.getRole()) {
			if (status == ZBStatus.SUBMITTED_2) {
				return true;
			}
		} else {
			if (status == ZBStatus.APPROVED || status == ZBStatus.SUBMITTED) {
				return true;
			}
		}
		return false;
	}

	// [[compId ,zbId, zbName, value] ...] approved
	// [[compId ,zbId, zbName, value] ...] unapproved
	private List<List<String[]>> getNdjhZb(Account account,
			List<Company> comps, Date date) {
		ZBDispatcher dispatcher = new ZBDispatcher(4);
		List<DWXX> dwxxs = dwxxDao.getDwxxs(comps);

		for (Company comp : comps) {
			ZBStatus status = ndjhzbDao.getZbStatus(date, comp);

			if (!validateConsistency(account, status)) {
				continue;
			}

			DWXX dwxx = findDwxx(dwxxs, comp);
			if (null != dwxx) {
				List<ZBXX> allZbs = toSortList(dwxx.getJhzbxxs());
				List<NDJHZB> ndjhzbs = ndjhzbDao.getZbs(date, comp);

				for (int i = 0, len = allZbs.size(); i < len; ++i) {
					String[] zbTmp = dispatcher.createZbArray();
					zbTmp[0] = comp.getType().ordinal() + "";
					zbTmp[1] = allZbs.get(i).getId() + "";
					zbTmp[2] = allZbs.get(i).getName();
					NDJHZB ndjhzb = findNdzb(allZbs.get(i).getId(), ndjhzbs);
					if (null != ndjhzb) {
						zbTmp[3] = ndjhzb.getNdjhz() + "";
					}

					dispatcher.dispatch(status, zbTmp);
				}
			}
		}

		return dispatcher.getResult();
	}

	// [[compId ,zbId, zbName, value] ...] approved
	// [[compId ,zbId, zbName, value] ...] unapproved
	private List<List<String[]>> getBysjZb(Account account,
			List<Company> comps, Date date) {
		ZBDispatcher dispatcher = new ZBDispatcher(4);
		List<DWXX> dwxxs = dwxxDao.getDwxxs(comps);

		for (Company comp : comps) {
			ZBStatus status = sjzbDao.getZbStatus(date, comp);

			if (!validateConsistency(account, status)) {
				continue;
			}

			DWXX dwxx = findDwxx(dwxxs, comp);
			if (null != dwxx) {
				List<ZBXX> allZbs = toSortList(dwxx.getSjzbxxs());
				List<SJZB> zbs = sjzbDao.getZbs(date, comp);

				for (int i = 0, len = allZbs.size(); i < len; ++i) {
					String[] zbTmp = dispatcher.createZbArray();
					zbTmp[0] = comp.getType().ordinal() + "";
					zbTmp[1] = allZbs.get(i).getId() + "";
					zbTmp[2] = allZbs.get(i).getName();
					SJZB zb = findSjzb(allZbs.get(i).getId(), zbs);
					if (null != zb) {
						zbTmp[3] = zb.getSjz() + "";
					}

					dispatcher.dispatch(status, zbTmp);
				}
			}
		}

		return dispatcher.getResult();
	}

	// [[compId ,zbId, zbName, value] ...] approved
	// [[compId ,zbId, zbName, value] ...] unapproved
	private List<List<String[]>> get28Zb(Account account, List<Company> comps,
			Date date) {
		ZBDispatcher dispatcher = new ZBDispatcher(4);
		List<DWXX> dwxxs = dwxxDao.getDwxxs(comps);

		for (Company comp : comps) {
			ZBStatus status = yj28zbDao.getZbStatus(date, comp);

			if (!validateConsistency(account, status)) {
				continue;
			}

			DWXX dwxx = findDwxx(dwxxs, comp);
			if (null != dwxx) {
				List<ZBXX> allZbs = toSortList(dwxx.getSjzbxxs());
				List<YJ28ZB> zbs = yj28zbDao.getZbs(date, comp);

				for (int i = 0, len = allZbs.size(); i < len; ++i) {
					String[] zbTmp = dispatcher.createZbArray();
					zbTmp[0] = comp.getType().ordinal() + "";
					zbTmp[1] = allZbs.get(i).getId() + "";
					zbTmp[2] = allZbs.get(i).getName();
					YJ28ZB zb = findYj28zb(allZbs.get(i).getId(), zbs);
					if (null != zb) {
						zbTmp[3] = zb.getYj28z() + "";
					}

					dispatcher.dispatch(status, zbTmp);
				}
			}
		}

		return dispatcher.getResult();
	}

	// [[compId ,zbId, zbName, value] ...] approved
	// [[compId ,zbId, zbName, value] ...] unapproved
	private List<List<String[]>> get20Zb(Account account, List<Company> comps,
			Date date) {
		ZBDispatcher dispatcher = new ZBDispatcher(4);
		List<DWXX> dwxxs = dwxxDao.getDwxxs(comps);

		for (Company comp : comps) {
			ZBStatus status = yj20zbDao.getZbStatus(date, comp);

			if (!validateConsistency(account, status)) {
				continue;
			}

			DWXX dwxx = findDwxx(dwxxs, comp);
			if (null != dwxx) {
				List<ZBXX> allZbs = toSortList(dwxx.getSjzbxxs());
				List<YJ20ZB> zbs = yj20zbDao.getZbs(date, comp);

				for (int i = 0, len = allZbs.size(); i < len; ++i) {
					String[] zbTmp = dispatcher.createZbArray();
					zbTmp[0] = comp.getType().ordinal() + "";
					zbTmp[1] = allZbs.get(i).getId() + "";
					zbTmp[2] = allZbs.get(i).getName();
					YJ20ZB zb = findYj20zb(allZbs.get(i).getId(), zbs);
					if (null != zb) {
						zbTmp[3] = zb.getYj20z() + "";
					}

					dispatcher.dispatch(status, zbTmp);
				}
			}
		}

		return dispatcher.getResult();
	}

	@Override
	public boolean hasApprovePlanPermission(Account account) {
		return qxglDao.getJhzshCount(account) > 0;
	}

	@Override
	public boolean hasApprovePredictPermission(Account account) {
		return qxglDao.getSjzshCount(account) > 0;
	}

	@Override
	public boolean approveNdjhZb(Account account, List<Company> comps, Date date) {
		PreApprovedCompanyFilter preApprovedCompanyFilter = new PreApprovedCompanyFilter(
				account, date, new OnGetStatus() {
					@Override
					public ZBStatus onGetStatus(Date d, Company comp) {
						return ndjhzbDao.getZbStatus(d, comp);
					}
				});
		comps = preApprovedCompanyFilter.filter(comps);
		if (!comps.isEmpty()) {
			Integer approveStatus = account.getRole() == 1 ? ZBStatus.APPROVED_2
					.ordinal() : ZBStatus.APPROVED.ordinal();

			List<NDJHZB> ndjhzbs = ndjhzbDao.getZbs(date, comps);
			for (NDJHZB ndjhzb : ndjhzbs) {
				ndjhzb.setNdjhshzt(shztDao.getById(approveStatus));
				ndjhzbDao.merge(ndjhzb);
			}
		}
		return true;
	}

	@Override
	public boolean unapproveNdjhZb(Account account, List<Company> comps,
			Date date) {
		PreUnapprovedCompanyFilter preUnapprovedCompanyFilter = new PreUnapprovedCompanyFilter(
				account, date, new OnGetStatus() {
					@Override
					public ZBStatus onGetStatus(Date d, Company comp) {
						return ndjhzbDao.getZbStatus(d, comp);
					}
				});
		comps = preUnapprovedCompanyFilter.filter(comps);
		if (!comps.isEmpty()) {

			List<NDJHZB> ndjhzbs = ndjhzbDao.getZbs(date, comps);
			for (NDJHZB ndjhzb : ndjhzbs) {
				ndjhzb.setNdjhshzt(shztDao.getById(ZBStatus.SUBMITTED.ordinal()));
				ndjhzbDao.merge(ndjhzb);
			}
		}
		return true;
	}

	@Override
	public boolean approveYj20Zb(Account account, List<Company> comps, Date date) {
		PreApprovedCompanyFilter preApprovedCompanyFilter = new PreApprovedCompanyFilter(
				account, date, new OnGetStatus() {
					@Override
					public ZBStatus onGetStatus(Date d, Company comp) {
						return yj20zbDao.getZbStatus(d, comp);
					}
				});
		comps = preApprovedCompanyFilter.filter(comps);
		if (!comps.isEmpty()) {
			Integer approveStatus = account.getRole() == 1 ? ZBStatus.APPROVED_2
					.ordinal() : ZBStatus.APPROVED.ordinal();

			List<YJ20ZB> zbs = yj20zbDao.getZbs(date, comps);
			for (YJ20ZB yj20zb : zbs) {
				yj20zb.setYj20shzt(shztDao.getById(approveStatus));
				yj20zbDao.merge(yj20zb);
			}
			
			if (approveStatus == ZBStatus.APPROVED_2
					.ordinal()){
				for(Company company : comps){
					setYdzbzt(date, company, ZBType.BY20YJ);
				}
			}
		}
		return true;
	}

	@Override
	public boolean approveYj28Zb(Account account, List<Company> comps, Date date) {
		PreApprovedCompanyFilter preApprovedCompanyFilter = new PreApprovedCompanyFilter(
				account, date, new OnGetStatus() {
					@Override
					public ZBStatus onGetStatus(Date d, Company comp) {
						return yj28zbDao.getZbStatus(d, comp);
					}
				});
		comps = preApprovedCompanyFilter.filter(comps);
		if (!comps.isEmpty()) {
			Integer approveStatus = account.getRole() == 1 ? ZBStatus.APPROVED_2
					.ordinal() : ZBStatus.APPROVED.ordinal();

			List<YJ28ZB> zbs = yj28zbDao.getZbs(date, comps);
			for (YJ28ZB yj28zb : zbs) {
				yj28zb.setYj28shzt(shztDao.getById(approveStatus));
				yj28zbDao.merge(yj28zb);
			}
			
			if (approveStatus == ZBStatus.APPROVED_2
					.ordinal()){
				for(Company company : comps){
					setYdzbzt(date, company, ZBType.BY28YJ);
				}
			}
		}
		return true;
	}

	@Override
	public boolean approveSjZb(Account account, List<Company> comps, Date date) {
		PreApprovedCompanyFilter preApprovedCompanyFilter = new PreApprovedCompanyFilter(
				account, date, new OnGetStatus() {
					@Override
					public ZBStatus onGetStatus(Date d, Company comp) {
						return sjzbDao.getZbStatus(d, comp);
					}
				});
		comps = preApprovedCompanyFilter.filter(comps);
		if (!comps.isEmpty()) {
			Integer approveStatus = account.getRole() == 1 ? ZBStatus.APPROVED_2
					.ordinal() : ZBStatus.APPROVED.ordinal();

			List<SJZB> zbs = sjzbDao.getZbs(date, comps);
			for (SJZB zb : zbs) {
				zb.setSjshzt(shztDao.getById(approveStatus));
				sjzbDao.merge(zb);			
			}
			
			if (approveStatus == ZBStatus.APPROVED_2
					.ordinal()){
				for(Company company : comps){
					setYdzbzt(date, company, ZBType.BYSJ);
				}
			}
		}
		return true;
	}

	@Override
	public boolean unapproveSjZb(Account account, List<Company> comps, Date date) {
		PreUnapprovedCompanyFilter preUnapprovedCompanyFilter = new PreUnapprovedCompanyFilter(
				account, date, new OnGetStatus() {
					@Override
					public ZBStatus onGetStatus(Date d, Company comp) {
						return sjzbDao.getZbStatus(d, comp);
					}
				});
		comps = preUnapprovedCompanyFilter.filter(comps);
		if (!comps.isEmpty()) {

			List<SJZB> zbs = sjzbDao.getZbs(date, comps);
			for (SJZB zb : zbs) {
				zb.setSjshzt(shztDao.getById(ZBStatus.SUBMITTED.ordinal()));
				sjzbDao.merge(zb);
			}
		}
		return true;
	}

	@Override
	public boolean unapproveYj28Zb(Account account, List<Company> comps,
			Date date) {
		PreUnapprovedCompanyFilter preUnapprovedCompanyFilter = new PreUnapprovedCompanyFilter(
				account, date, new OnGetStatus() {
					@Override
					public ZBStatus onGetStatus(Date d, Company comp) {
						return yj28zbDao.getZbStatus(d, comp);
					}
				});
		comps = preUnapprovedCompanyFilter.filter(comps);
		if (!comps.isEmpty()) {

			List<YJ28ZB> zbs = yj28zbDao.getZbs(date, comps);
			for (YJ28ZB yj28zb : zbs) {
				yj28zb.setYj28shzt(shztDao.getById(ZBStatus.SUBMITTED.ordinal()));
				yj28zbDao.merge(yj28zb);
			}
		}
		return true;
	}

	@Override
	public boolean unapproveYj20Zb(Account account, List<Company> comps,
			Date date) {
		PreUnapprovedCompanyFilter preUnapprovedCompanyFilter = new PreUnapprovedCompanyFilter(
				account, date, new OnGetStatus() {
					@Override
					public ZBStatus onGetStatus(Date d, Company comp) {
						return yj20zbDao.getZbStatus(d, comp);
					}
				});
		comps = preUnapprovedCompanyFilter.filter(comps);
		if (!comps.isEmpty()) {
			List<YJ20ZB> zbs = yj20zbDao.getZbs(date, comps);
			for (YJ20ZB zb : zbs) {
				zb.setYj20shzt(shztDao.getById(ZBStatus.SUBMITTED.ordinal()));
				yj20zbDao.merge(zb);
			}
		}
		return true;
	}

	private void setYdzbzt(Date date, Company comp, ZBType type){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		EntryServiceImpl.setYdzbzt(ydzbztDao, dwxxDao, comp, cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH) + 1, type);
	}
	
	@Override
	public boolean approveYdjdZb(Account account, List<Company> comps,
			List<Date> dateList) {

		Integer approveStatus = account.getRole() == 1 ? ZBStatus.APPROVED_2
				.ordinal() : ZBStatus.APPROVED.ordinal();

		for (int i = 0; i < comps.size() && i < dateList.size(); ++i) {

			PreApprovedCompanyFilter preApprovedCompanyFilter = new PreApprovedCompanyFilter(
					account, dateList.get(i), new OnGetStatus() {
						@Override
						public ZBStatus onGetStatus(Date d, Company comp) {
							return ydjhzbDao.getZbStatus(d, comp);
						}
					});
			if (preApprovedCompanyFilter.filter(comps.get(i))) {
				List<YDJHZB> zbs = ydjhzbDao.getZbs(dateList.get(i),
						comps.get(i));
				for (YDJHZB zb : zbs) {
					zb.setYdjhshzt(shztDao.getById(approveStatus));
					ydjhzbDao.merge(zb);
				}
			}
		}
		return true;
	}

	@Override
	public boolean unapproveYdjdZb(Account account, List<Company> comps,
			List<Date> dateList) {
		for (int i = 0; i < comps.size() && i < dateList.size(); ++i) {
			PreUnapprovedCompanyFilter preUnapprovedCompanyFilter = new PreUnapprovedCompanyFilter(
					account, dateList.get(i), new OnGetStatus() {
						@Override
						public ZBStatus onGetStatus(Date d, Company comp) {
							return ydjhzbDao.getZbStatus(d, comp);
						}
					});
			if (preUnapprovedCompanyFilter.filter(comps.get(i))) {
				List<YDJHZB> zbs = ydjhzbDao.getZbs(dateList.get(i),
						comps.get(i));
				for (YDJHZB zb : zbs) {
					zb.setYdjhshzt(shztDao.getById(ZBStatus.SUBMITTED.ordinal()));
					ydjhzbDao.merge(zb);
				}
			}
		}
		return true;
	}

	@Override
	public List<Integer> getCompanies(ZBType approveType) {
		List<Integer> ret = null;
		switch (approveType) {
		case BY20YJ:
			ret = yj20zbDao.getCompanies();
			break;
		case BY28YJ:
			ret = yj28zbDao.getCompanies();
			break;
		case BYSJ:
			ret = sjzbDao.getCompanies();
			break;
		case NDJH:
			ret = ndjhzbDao.getCompanies();
			break;
		case YDJDMJH:
			ret = ydjhzbDao.getCompanies();
			break;
		default:
			break;
		}
		return ret;
	}

	@Override
	public List<Company> getValidSjCompanies(Account account) {
		List<QXGL> compIds = qxglDao.getSjzsh(account);
		List<Company> comps = new ArrayList<Company>();
		Organization org = companyManager.getBMDBOrganization();
		for (int i = 0; i < compIds.size(); ++i) {
			comps.add(org.getCompany(compIds.get(i).getDwxx().getId()));
		}
		return comps;
	}

	@Override
	public List<Company> getValidJhCompanies(Account account) {
		List<QXGL> compIds = qxglDao.getJhzsh(account);
		List<Company> comps = new ArrayList<Company>();
		Organization org = companyManager.getBMDBOrganization();
		for (int i = 0; i < compIds.size(); ++i) {
			comps.add(org.getCompany(compIds.get(i).getDwxx().getId()));
		}
		return comps;
	}

}
