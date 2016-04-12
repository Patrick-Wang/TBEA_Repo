package com.tbea.ic.operation.service.chgb;

import com.tbea.ic.operation.model.dao.chgb.nych.NychDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.nych.NychDao;
import com.tbea.ic.operation.model.dao.chgb.chxzqk.ChxzqkDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chxzqk.ChxzqkDao;
import com.tbea.ic.operation.model.dao.chgb.chzlbhqk.ChzlbhqkDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chzlbhqk.ChzlbhqkDao;
import com.tbea.ic.operation.model.dao.chgb.chjykc.ChJykcDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chjykc.ChJykcDao;
import javax.annotation.Resource;
import com.tbea.ic.operation.model.dao.chgb.chzm.ChZmDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chzm.ChZmDao;
import com.tbea.ic.operation.service.chgb.ChgbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(ChgbServiceImpl.NAME)
@Transactional("transactionManager")
public class ChgbServiceImpl implements ChgbService {
	@Resource(name=NychDaoImpl.NAME)
	NychDao nychDao;

	@Resource(name=ChxzqkDaoImpl.NAME)
	ChxzqkDao chxzqkDao;

	@Resource(name=ChzlbhqkDaoImpl.NAME)
	ChzlbhqkDao chzlbhqkDao;

	@Resource(name=ChJykcDaoImpl.NAME)
	ChJykcDao chJykcDao;

	@Resource(name=ChZmDaoImpl.NAME)
	ChZmDao chZmDao;

	public final static String NAME = "ChgbServiceImpl";

}
