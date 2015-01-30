package com.tbea.ic.operation.service.approve;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.User;
import com.tbea.ic.operation.model.entity.jygk.Account;

@Service
@Transactional("transactionManager")
public class ApproveServiceImpl implements ApproveService {

	@Autowired
	QXGLDao qxglDao;
	
	@Override
	public boolean unapproveZb(Date date, User usr, JSONArray fromObject,
			ZBType entryType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approveZb(Date date, User usr, JSONArray fromObject,
			ZBType entryType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String[][]> getZb(Date date, Account account, ZBType entryType) {
		List<String[][]> ret = new ArrayList<String[][]>();
		ret.add(new String[][]{
		 {"1.00", "2.00", "3.00", "4.00", "4.00"},
		 {"1.00", "2.00", "3.00", "4.00", "4.00"},
		 {"1.00", "2.00", "3.00", "4.00", "4.00"}
		 });
		ret.add(new String[][]{
				 {"计划", "121", "3.00", "4.00", "4.00"},
				 {"回款", "2330", "312300", "43320", "4120"},
				 {"应收", "2.0g0", "3.sdf00", "4.f0", "4a.00"},
				 {"保理", "2.00", "3.00", "4.00", "4.00"},
				 {"123", "2330", "312300", "43320", "4120"},
				 {"1.10", "2.0g0", "3.sdf00", "4.f0", "4a.00"},
				 {"1.00", "2.00", "3.00", "4.00", "4.00"},
				 {"123", "2330", "312300", "43320", "4120"},
				 {"1.10", "2.0g0", "3.sdf00", "4.f0", "4a.00"},
				 {"1.00", "2.00", "3.00", "4.00", "4.00"},
				 {"123", "2330", "312300", "43320", "4120"},
				 {"1.10", "2.0g0", "3.sdf00", "4.f0", "4a.00"},
				 {"1.00", "2.00", "3.00", "4.00", "4.00"},
				 {"123", "2330", "312300", "43320", "4120"},
				 {"1.10", "2.0g0", "3.sdf00", "4.f0", "4a.00"}
				 });
		return ret;
	}

	@Override
	public boolean hasApprovePlanPermission(Account account) {
		return qxglDao.getJhzshCount(account) > 0;
	}

	@Override
	public boolean hasApprovePredictPermission(Account account) {
		return qxglDao.getSjzshCount(account) > 0;
	}

}
