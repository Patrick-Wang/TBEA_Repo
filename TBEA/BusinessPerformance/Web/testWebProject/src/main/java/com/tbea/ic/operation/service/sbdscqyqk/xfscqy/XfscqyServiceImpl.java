package com.tbea.ic.operation.service.sbdscqyqk.xfscqy;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.sbdscqyqk.xfscqy.XfscqyDaoImpl;
import com.tbea.ic.operation.model.dao.sbdscqyqk.xfscqy.XfscqyDao;
import com.tbea.ic.operation.service.sbdscqyqk.xfscqy.XfscqyService;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(XfscqyServiceImpl.NAME)
@Transactional("transactionManager")
public class XfscqyServiceImpl implements XfscqyService {
	@Resource(name=XfscqyDaoImpl.NAME)
	XfscqyDao xfscqyDao;

	public final static String NAME = "XfscqyServiceImpl";

	@Override
	public List<List<String>> getXfscqy(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getXfscqyEntry(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode saveXfscqy(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode submitXfscqy(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

}
