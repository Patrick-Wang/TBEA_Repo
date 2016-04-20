package com.tbea.ic.operation.service.dzwzgb;

import javax.annotation.Resource;
import com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb.DzclkcbDaoImpl;
import com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb.DzclkcbDao;
import com.tbea.ic.operation.service.dzwzgb.DzwzgbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(DzwzgbServiceImpl.NAME)
@Transactional("transactionManager")
public class DzwzgbServiceImpl implements DzwzgbService {
	@Resource(name=DzclkcbDaoImpl.NAME)
	DzclkcbDao dzclkcbDao;

	public final static String NAME = "DzwzgbServiceImpl";

}
