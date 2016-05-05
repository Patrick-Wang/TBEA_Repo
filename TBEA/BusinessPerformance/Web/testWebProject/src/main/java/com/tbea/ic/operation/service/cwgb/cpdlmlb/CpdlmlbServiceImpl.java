package com.tbea.ic.operation.service.cwgb.cpdlmlb;

import javax.annotation.Resource;
import com.tbea.ic.operation.model.dao.cwgb.cpdlmlb.CpdlmlbDaoImpl;
import com.tbea.ic.operation.model.dao.cwgb.cpdlmlb.CpdlmlbDao;
import com.tbea.ic.operation.service.cwgb.cpdlmlb.CpdlmlbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(CpdlmlbServiceImpl.NAME)
@Transactional("transactionManager")
public class CpdlmlbServiceImpl implements CpdlmlbService {
	@Resource(name=CpdlmlbDaoImpl.NAME)
	CpdlmlbDao cpdlmlbDao;

	public final static String NAME = "CpdlmlbServiceImpl";

}
