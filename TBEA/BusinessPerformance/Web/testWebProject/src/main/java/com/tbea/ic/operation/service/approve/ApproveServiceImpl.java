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

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Override
	public List<List<String[]>> getZb(List<Company> comps, Date date,
			ZBType approveType) {
		List<List<String[]>> ret = null;
		switch (approveType) {
		case BY20YJ:
			ret = get20Zb(comps, date);
			break;
		case BY28YJ:
			ret = get28Zb(comps, date);
			break;
		case BYSJ:
			ret = getBysjZb(comps, date);
			break;
		case NDJH:
			ret = getNdjhZb(comps, date);
			break;
		case YDJDMJH:
			ret = getYdjdZb(comps, date);
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
	private List<List<String[]>> getYdjdZb(List<Company> comps, Date date) {
		List<List<String[]>> retList = new ArrayList<List<String[]>>();
		List<String[]> approveList = new ArrayList<String[]>();
		List<String[]> unapproveList = new ArrayList<String[]>();
		List<DWXX> dwxxs = dwxxDao.getDwxxs(comps);
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateHelper.getJdStart(date));

		for (int m = 0; m < 3; ++m) {
			Date d = Util.toDate(cal);
			for (Company comp : comps) {
				boolean isApproved = ydjhzbDao.getApprovedZbsCount(d, comp) > 0;
				boolean isSaved = ydjhzbDao.getSavedZbsCount(d, comp) > 0;
				DWXX dwxx = findDwxx(dwxxs, comp);
				if (null != dwxx) {
					List<ZBXX> allZbs = toSortList(dwxx.getJhzbxxs());
					List<YDJHZB> zbs = ydjhzbDao.getZbs(d, comp);

					for (int i = 0, len = allZbs.size(); i < len; ++i) {
						String[] zbTmp = new String[6];
						zbTmp[0] = comp.getType().ordinal() + "";
						zbTmp[1] = allZbs.get(i).getId() + "";
						zbTmp[2] = allZbs.get(i).getName();
						YDJHZB zb = findYdjhzb(allZbs.get(i).getId(), zbs);
						if (null != zb) {
							zbTmp[3] = zb.getYdjhz() + "";
						}
						zbTmp[4] = cal.get(Calendar.YEAR) + "";
						zbTmp[5] = cal.get(Calendar.MONTH) + 1 + "";

						if (isApproved) {// approved
							unapproveList.add(zbTmp);
						} else if (!isSaved){
							approveList.add(zbTmp);
						}
					}
				}
			}
			cal.add(Calendar.MONTH, 1);
		}

		retList.add(approveList);
		retList.add(unapproveList);
		return retList;
	}

	// [[compId ,zbId, zbName, value] ...] approved
	// [[compId ,zbId, zbName, value] ...] unapproved
	private List<List<String[]>> getNdjhZb(List<Company> comps, Date date) {
		List<List<String[]>> retList = new ArrayList<List<String[]>>();
		List<String[]> approveList = new ArrayList<String[]>();
		List<String[]> unapproveList = new ArrayList<String[]>();
		List<DWXX> dwxxs = dwxxDao.getDwxxs(comps);

		for (Company comp : comps) {
			boolean isApproved = ndjhzbDao.getApprovedZbsCount(date, comp) > 0;
			boolean isSaved = ndjhzbDao.getSavedZbsCount(date, comp) > 0;
			DWXX dwxx = findDwxx(dwxxs, comp);
			if (null != dwxx) {
				List<ZBXX> allZbs = toSortList(dwxx.getJhzbxxs());
				List<NDJHZB> ndjhzbs = ndjhzbDao.getZbs(date, comp);

				for (int i = 0, len = allZbs.size(); i < len; ++i) {
					String[] zbTmp = new String[4];
					zbTmp[0] = comp.getType().ordinal() + "";
					zbTmp[1] = allZbs.get(i).getId() + "";
					zbTmp[2] = allZbs.get(i).getName();
					NDJHZB ndjhzb = findNdzb(allZbs.get(i).getId(), ndjhzbs);
					if (null != ndjhzb) {
						zbTmp[3] = ndjhzb.getNdjhz() + "";
					}

					if (isApproved) {// approved
						unapproveList.add(zbTmp);
					} else if (!isSaved){
						approveList.add(zbTmp);
					}
				}
			}
		}

		retList.add(approveList);
		retList.add(unapproveList);
		return retList;
	}

	// [[compId ,zbId, zbName, value] ...] approved
	// [[compId ,zbId, zbName, value] ...] unapproved
	private List<List<String[]>> getBysjZb(List<Company> comps, Date date) {
		List<List<String[]>> retList = new ArrayList<List<String[]>>();
		List<String[]> approveList = new ArrayList<String[]>();
		List<String[]> unapproveList = new ArrayList<String[]>();
		List<DWXX> dwxxs = dwxxDao.getDwxxs(comps);

		for (Company comp : comps) {
			boolean isApproved = sjzbDao.getApprovedZbsCount(date, comp) > 0;
			boolean isSaved = sjzbDao.getSavedZbsCount(date, comp) > 0;
			DWXX dwxx = findDwxx(dwxxs, comp);
			if (null != dwxx) {
				List<ZBXX> allZbs = toSortList(dwxx.getSjzbxxs());
				List<SJZB> zbs = sjzbDao.getZbs(date, comp);

				for (int i = 0, len = allZbs.size(); i < len; ++i) {
					String[] zbTmp = new String[4];
					zbTmp[0] = comp.getType().ordinal() + "";
					zbTmp[1] = allZbs.get(i).getId() + "";
					zbTmp[2] = allZbs.get(i).getName();
					SJZB zb = findSjzb(allZbs.get(i).getId(), zbs);
					if (null != zb) {
						zbTmp[3] = zb.getSjz() + "";
					}

					if (isApproved) {// approved
						unapproveList.add(zbTmp);
					} else if (!isSaved){
						approveList.add(zbTmp);
					}
				}
			}
		}

		retList.add(approveList);
		retList.add(unapproveList);
		return retList;
	}

	// [[compId ,zbId, zbName, value] ...] approved
	// [[compId ,zbId, zbName, value] ...] unapproved
	private List<List<String[]>> get28Zb(List<Company> comps, Date date) {
		List<List<String[]>> retList = new ArrayList<List<String[]>>();
		List<String[]> approveList = new ArrayList<String[]>();
		List<String[]> unapproveList = new ArrayList<String[]>();
		List<DWXX> dwxxs = dwxxDao.getDwxxs(comps);

		for (Company comp : comps) {
			boolean isApproved = yj28zbDao.getApprovedZbsCount(date, comp) > 0;
			boolean isSaved = yj28zbDao.getSavedZbsCount(date, comp) > 0;
			DWXX dwxx = findDwxx(dwxxs, comp);
			if (null != dwxx) {
				List<ZBXX> allZbs = toSortList(dwxx.getSjzbxxs());
				List<YJ28ZB> zbs = yj28zbDao.getZbs(date, comp);

				for (int i = 0, len = allZbs.size(); i < len; ++i) {
					String[] zbTmp = new String[4];
					zbTmp[0] = comp.getType().ordinal() + "";
					zbTmp[1] = allZbs.get(i).getId() + "";
					zbTmp[2] = allZbs.get(i).getName();
					YJ28ZB zb = findYj28zb(allZbs.get(i).getId(), zbs);
					if (null != zb) {
						zbTmp[3] = zb.getYj28z() + "";
					}

					if (isApproved) {// approved
						unapproveList.add(zbTmp);
					} else if(!isSaved){
						approveList.add(zbTmp);
					}
				}
			}
		}

		retList.add(approveList);
		retList.add(unapproveList);
		return retList;
	}

	// [[compId ,zbId, zbName, value] ...] approved
	// [[compId ,zbId, zbName, value] ...] unapproved
	private List<List<String[]>> get20Zb(List<Company> comps, Date date) {
		List<List<String[]>> retList = new ArrayList<List<String[]>>();
		List<String[]> approveList = new ArrayList<String[]>();
		List<String[]> unapproveList = new ArrayList<String[]>();
		List<DWXX> dwxxs = dwxxDao.getDwxxs(comps);

		for (Company comp : comps) {
			boolean isApproved = yj20zbDao.getApprovedZbsCount(date, comp) > 0;
			boolean isSaved = yj20zbDao.getSavedZbsCount(date, comp) > 0;
			DWXX dwxx = findDwxx(dwxxs, comp);
			if (null != dwxx) {
				List<ZBXX> allZbs = toSortList(dwxx.getSjzbxxs());
				List<YJ20ZB> zbs = yj20zbDao.getZbs(date, comp);

				for (int i = 0, len = allZbs.size(); i < len; ++i) {
					String[] zbTmp = new String[4];
					zbTmp[0] = comp.getType().ordinal() + "";
					zbTmp[1] = allZbs.get(i).getId() + "";
					zbTmp[2] = allZbs.get(i).getName();
					YJ20ZB zb = findYj20zb(allZbs.get(i).getId(), zbs);
					if (null != zb) {
						zbTmp[3] = zb.getYj20z() + "";
					}

					if (isApproved) {// approved
						unapproveList.add(zbTmp);
					} else if (!isSaved){
						approveList.add(zbTmp);
					}
				}
			}
		}

		retList.add(approveList);
		retList.add(unapproveList);
		return retList;
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
	public boolean approveNdjhZb(List<Company> comps, Date date) {
		List<NDJHZB> ndjhzbs = ndjhzbDao.getUnapprovedZbs(date, comps);
		for (NDJHZB ndjhzb : ndjhzbs) {
			ndjhzb.setNdjhshzt(shztDao.getById(ZBStatus.APPROVED.ordinal()));
			ndjhzbDao.merge(ndjhzb);
		}
		return true;
	}

	@Override
	public boolean unapproveNdjhZb(List<Company> comps, Date date) {
		List<NDJHZB> ndjhzbs = ndjhzbDao.getApprovedZbs(date, comps);
		for (NDJHZB ndjhzb : ndjhzbs) {
			ndjhzb.setNdjhshzt(shztDao.getById(ZBStatus.SUBMITTED.ordinal()));
			ndjhzbDao.merge(ndjhzb);
		}
		return true;
	}

	@Override
	public boolean approveYj20Zb(List<Company> comps, Date date) {
		List<YJ20ZB> zbs = yj20zbDao.getUnapprovedZbs(date, comps);
		for (YJ20ZB yj20zb : zbs) {
			yj20zb.setYj20shzt(shztDao.getById(ZBStatus.APPROVED.ordinal()));
			yj20zbDao.merge(yj20zb);
		}

		return true;
	}

	@Override
	public boolean approveYj28Zb(List<Company> comps, Date date) {
		List<YJ28ZB> zbs = yj28zbDao.getUnapprovedZbs(date, comps);
		for (YJ28ZB yj28zb : zbs) {
			yj28zb.setYj28shzt(shztDao.getById(ZBStatus.APPROVED.ordinal()));
			yj28zbDao.merge(yj28zb);
		}
		return true;
	}

	@Override
	public boolean approveSjZb(List<Company> comps, Date date) {
		List<SJZB> zbs = sjzbDao.getUnapprovedZbs(date, comps);
		for (SJZB zb : zbs) {
			zb.setSjshzt(shztDao.getById(ZBStatus.APPROVED.ordinal()));
			sjzbDao.merge(zb);
		}
		return true;
	}

	@Override
	public boolean unapproveSjZb(List<Company> comps, Date date) {
		List<SJZB> zbs = sjzbDao.getApprovedZbs(date, comps);
		for (SJZB zb : zbs) {
			zb.setSjshzt(shztDao.getById(ZBStatus.SUBMITTED.ordinal()));
			sjzbDao.merge(zb);
		}
		return true;
	}

	@Override
	public boolean unapproveYj28Zb(List<Company> comps, Date date) {
		List<YJ28ZB> zbs = yj28zbDao.getApprovedZbs(date, comps);
		for (YJ28ZB yj28zb : zbs) {
			yj28zb.setYj28shzt(shztDao.getById(ZBStatus.SUBMITTED.ordinal()));
			yj28zbDao.merge(yj28zb);
		}
		return true;
	}

	@Override
	public boolean unapproveYj20Zb(List<Company> comps, Date date) {
		List<YJ20ZB> zbs = yj20zbDao.getApprovedZbs(date, comps);
		for (YJ20ZB zb : zbs) {
			zb.setYj20shzt(shztDao.getById(ZBStatus.SUBMITTED.ordinal()));
			yj20zbDao.merge(zb);
		}
		return true;
	}

	@Override
	public boolean approveYdjdZb(List<Company> comps, List<Date> dateList) {
		for (int i = 0; i < comps.size() && i < dateList.size(); ++i) {
			List<YDJHZB> zbs = ydjhzbDao.getUnapprovedZbs(dateList.get(i),
					comps.get(i));
			for (YDJHZB zb : zbs) {
				zb.setYdjhshzt(shztDao.getById(ZBStatus.APPROVED.ordinal()));
				ydjhzbDao.merge(zb);
			}
		}
		return true;
	}

	@Override
	public boolean unapproveYdjdZb(List<Company> comps, List<Date> dateList) {
		for (int i = 0; i < comps.size() && i < dateList.size(); ++i) {
			List<YDJHZB> zbs = ydjhzbDao.getApprovedZbs(dateList.get(i),
					comps.get(i));
			for (YDJHZB zb : zbs) {
				zb.setYdjhshzt(shztDao.getById(ZBStatus.SUBMITTED.ordinal()));
				ydjhzbDao.merge(zb);
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
