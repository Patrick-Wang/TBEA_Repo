package com.tbea.ic.operation.service.cwcpdlml.cpdlml;

import javax.annotation.Resource;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml.CpdlmlDaoImpl;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml.CpdlmlDao;
import com.tbea.ic.operation.service.cwcpdlml.cpdlml.CpdlmlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(CpdlmlServiceImpl.NAME)
@Transactional("transactionManager")
public class CpdlmlServiceImpl implements CpdlmlService {
	@Resource(name=CpdlmlDaoImpl.NAME)
	CpdlmlDao cpdlmlDao;

	public final static String NAME = "CpdlmlServiceImpl";

}
