package com.tbea.ic.operation.service.cwgbjyxxjl.jyxxjl;

import javax.annotation.Resource;
import com.tbea.ic.operation.model.dao.cwgbjyxxjl.jyxxjl.jyxxjlDaoImpl;
import com.tbea.ic.operation.model.dao.cwgbjyxxjl.jyxxjl.jyxxjlDao;
import com.tbea.ic.operation.service.cwgbjyxxjl.jyxxjl.JyxxjlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(JyxxjlServiceImpl.NAME)
@Transactional("transactionManager")
public class JyxxjlServiceImpl implements JyxxjlService {
	@Resource(name=jyxxjlDaoImpl.NAME)
	jyxxjlDao jyxxjlDao;

	public final static String NAME = "JyxxjlServiceImpl";

}
