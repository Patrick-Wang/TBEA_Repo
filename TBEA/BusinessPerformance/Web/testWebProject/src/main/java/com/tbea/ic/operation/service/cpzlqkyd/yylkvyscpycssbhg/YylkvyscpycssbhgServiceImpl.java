package com.tbea.ic.operation.service.cpzlqkyd.yylkvyscpycssbhg;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.cpzlqkyd.bhgwtmxbyq.BhgwtmxByqDao;
import com.tbea.ic.operation.model.dao.cpzlqkyd.bhgwtmxbyq.BhgwtmxByqDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqkyd.bhgwtmxxl.BhgwtmxXlDao;
import com.tbea.ic.operation.model.dao.cpzlqkyd.bhgwtmxxl.BhgwtmxXlDaoImpl;

@Service(YylkvyscpycssbhgServiceImpl.NAME)
@Transactional("transactionManager")
public class YylkvyscpycssbhgServiceImpl implements YylkvyscpycssbhgService {
	@Resource(name=BhgwtmxByqDaoImpl.NAME)
	BhgwtmxByqDao bhgcpmxByqDao;
	
	@Resource(name=BhgwtmxXlDaoImpl.NAME)
	BhgwtmxXlDao bhgcpmxXlDao;

	public final static String NAME = "YylkvyscpycssbhgServiceImpl";

}
