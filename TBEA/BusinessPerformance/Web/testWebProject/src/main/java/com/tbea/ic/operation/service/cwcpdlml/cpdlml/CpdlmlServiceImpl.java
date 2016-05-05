package com.tbea.ic.operation.service.cwcpdlml.cpdlml;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml.CpdlmlDaoImpl;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml.CpdlmlDao;
import com.tbea.ic.operation.service.cwcpdlml.cpdlml.CpdlmlService;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(CpdlmlServiceImpl.NAME)
@Transactional("transactionManager")
public class CpdlmlServiceImpl implements CpdlmlService {
	@Resource(name=CpdlmlDaoImpl.NAME)
	CpdlmlDao cpdlmlDao;

	public final static String NAME = "CpdlmlServiceImpl";


	@Override
	public List<List<String>> getCpdlml(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}


}
