package com.tbea.ic.operation.service.cpzlqkyd.tjjg;

import javax.annotation.Resource;
import com.tbea.ic.operation.model.dao.cpzlqkyd.tjjg.TjjgDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqkyd.tjjg.TjjgDao;
import com.tbea.ic.operation.service.cpzlqkyd.tjjg.TjjgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(TjjgServiceImpl.NAME)
@Transactional("transactionManager")
public class TjjgServiceImpl implements TjjgService {
	@Resource(name=TjjgDaoImpl.NAME)
	TjjgDao tjjgDao;

	public final static String NAME = "TjjgServiceImpl";

}
