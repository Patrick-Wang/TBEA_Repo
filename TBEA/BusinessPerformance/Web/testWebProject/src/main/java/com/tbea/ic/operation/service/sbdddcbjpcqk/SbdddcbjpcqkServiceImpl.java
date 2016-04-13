package com.tbea.ic.operation.service.sbdddcbjpcqk;

import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.kglydd.KglyddDaoImpl;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.kglydd.KglyddDao;
import javax.annotation.Resource;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.kglyddbh.KglyddbhDaoImpl;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.kglyddbh.KglyddbhDao;
import com.tbea.ic.operation.service.sbdddcbjpcqk.SbdddcbjpcqkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(SbdddcbjpcqkServiceImpl.NAME)
@Transactional("transactionManager")
public class SbdddcbjpcqkServiceImpl implements SbdddcbjpcqkService {
	@Resource(name=KglyddDaoImpl.NAME)
	KglyddDao kglyddDao;

	@Resource(name=KglyddbhDaoImpl.NAME)
	KglyddbhDao kglyddbhDao;

	public final static String NAME = "SbdddcbjpcqkServiceImpl";

}
