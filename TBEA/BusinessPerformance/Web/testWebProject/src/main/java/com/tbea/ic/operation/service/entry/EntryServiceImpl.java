package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.Permission;
import com.tbea.ic.operation.model.entity.User;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.QXGL;

@Service
@Transactional("transactionManager")
public class EntryServiceImpl implements EntryService{

	@Autowired
	QXGLDao qxglDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Override
	public boolean updateZb(Date date, User usr, JSONArray fromObject,
			ZBType entryType) {
		// TODO Auto-generated method stub
		return false;
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
	public String[][] getZb(Date date, Account account, CompanyType comp,
			ZBType entryType) {
		return new String[][]{
			 {"1", "销售收入", "asdf", "ffd", "eew", "334f"},
			 {"2", "营业额", "ffd", "sdf", "4d0", "4.00"},
			 {"3", "利润", "2.00", "fa", "4f", "46650"}
		 };
	}

}
