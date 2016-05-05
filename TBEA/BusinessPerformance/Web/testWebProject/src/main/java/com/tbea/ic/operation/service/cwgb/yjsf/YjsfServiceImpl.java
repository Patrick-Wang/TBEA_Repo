package com.tbea.ic.operation.service.cwgb.yjsf;

import javax.annotation.Resource;
import com.tbea.ic.operation.model.dao.cwgb.yjsf.YjsfDaoImpl;
import com.tbea.ic.operation.model.dao.cwgb.yjsf.YjsfDao;
import com.tbea.ic.operation.service.cwgb.yjsf.YjsfService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(YjsfServiceImpl.NAME)
@Transactional("transactionManager")
public class YjsfServiceImpl implements YjsfService {
	@Resource(name=YjsfDaoImpl.NAME)
	YjsfDao yjsfDao;

	public final static String NAME = "YjsfServiceImpl";

}
