package com.tbea.ic.operation.service.cwyjsf.yjsf;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsfndqcs.YjsfNdqcsDaoImpl;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsfndqcs.YjsfNdqcsDao;

import javax.annotation.Resource;

import com.tbea.ic.operation.model.dao.cwyjsf.yjsf.YjsfDaoImpl;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsf.YjsfDao;
import com.tbea.ic.operation.service.cwyjsf.yjsf.YjsfService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(YjsfServiceImpl.NAME)
@Transactional("transactionManager")
public class YjsfServiceImpl implements YjsfService {
	@Resource(name=YjsfNdqcsDaoImpl.NAME)
	YjsfNdqcsDao yjsfNdqcsDao;

	@Resource(name=YjsfDaoImpl.NAME)
	YjsfDao yjsfDao;

	public final static String NAME = "YjsfServiceImpl";

	@Override
	public List<List<String>> getYjsf(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

}
