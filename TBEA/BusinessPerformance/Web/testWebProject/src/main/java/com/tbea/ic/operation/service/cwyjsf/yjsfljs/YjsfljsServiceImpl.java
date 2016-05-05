package com.tbea.ic.operation.service.cwyjsf.yjsfljs;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsfndqcs.YjsfNdqcsDaoImpl;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsfndqcs.YjsfNdqcsDao;

import javax.annotation.Resource;

import com.tbea.ic.operation.model.dao.cwyjsf.yjsf.YjsfDaoImpl;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsf.YjsfDao;
import com.tbea.ic.operation.service.cwyjsf.yjsfljs.YjsfljsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(YjsfljsServiceImpl.NAME)
@Transactional("transactionManager")
public class YjsfljsServiceImpl implements YjsfljsService {
	@Resource(name=YjsfNdqcsDaoImpl.NAME)
	YjsfNdqcsDao yjsfNdqcsDao;

	@Resource(name=YjsfDaoImpl.NAME)
	YjsfDao yjsfDao;

	public final static String NAME = "YjsfljsServiceImpl";

	@Override
	public List<List<String>> getYjsfljs(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

}
