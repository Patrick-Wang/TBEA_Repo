package com.tbea.ic.operation.service.sbdczclwcqk.clylwcqk;

import javax.annotation.Resource;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.clylwcqk.ClylwcqkDaoImpl;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.clylwcqk.ClylwcqkDao;
import com.tbea.ic.operation.service.sbdczclwcqk.clylwcqk.ClylwcqkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(ClylwcqkServiceImpl.NAME)
@Transactional("transactionManager")
public class ClylwcqkServiceImpl implements ClylwcqkService {
	@Resource(name=ClylwcqkDaoImpl.NAME)
	ClylwcqkDao clylwcqkDao;

	public final static String NAME = "ClylwcqkServiceImpl";

}
