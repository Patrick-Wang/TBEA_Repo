package com.tbea.ic.operation.service.sbdscqyqk.xfcpqy;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy.XfcpqyDaoImpl;
import com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy.XfcpqyDao;
import com.tbea.ic.operation.service.sbdscqyqk.xfcpqy.XfcpqyService;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(XfcpqyServiceImpl.NAME)
@Transactional("transactionManager")
public class XfcpqyServiceImpl implements XfcpqyService {
	@Resource(name=XfcpqyDaoImpl.NAME)
	XfcpqyDao xfcpqyDao;

	public final static String NAME = "XfcpqyServiceImpl";

	@Override
	public List<List<String>> getXfcpqy(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getXfcpqyEntry(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode submitXfcpqy(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode saveXfcpqy(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

}
