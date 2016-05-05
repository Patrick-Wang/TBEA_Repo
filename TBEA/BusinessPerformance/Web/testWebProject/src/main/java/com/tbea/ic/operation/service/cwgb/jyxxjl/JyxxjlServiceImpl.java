package com.tbea.ic.operation.service.cwgb.jyxxjl;

import javax.annotation.Resource;
import com.tbea.ic.operation.model.dao.cwgb.jyxxjl.JyxxjlDaoImpl;
import com.tbea.ic.operation.model.dao.cwgb.jyxxjl.JyxxjlDao;
import com.tbea.ic.operation.service.cwgb.jyxxjl.JyxxjlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(JyxxjlServiceImpl.NAME)
@Transactional("transactionManager")
public class JyxxjlServiceImpl implements JyxxjlService {
	@Resource(name=JyxxjlDaoImpl.NAME)
	JyxxjlDao jyxxjlDao;

	public final static String NAME = "JyxxjlServiceImpl";

}
