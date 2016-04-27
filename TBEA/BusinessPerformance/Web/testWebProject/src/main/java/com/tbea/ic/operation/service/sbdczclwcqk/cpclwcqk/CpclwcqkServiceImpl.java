package com.tbea.ic.operation.service.sbdczclwcqk.cpclwcqk;

import javax.annotation.Resource;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpclwcqk.CpclwcqkDaoImpl;
import com.tbea.ic.operation.model.dao.sbdczclwcqk.cpclwcqk.CpclwcqkDao;
import com.tbea.ic.operation.service.sbdczclwcqk.cpclwcqk.CpclwcqkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(CpclwcqkServiceImpl.NAME)
@Transactional("transactionManager")
public class CpclwcqkServiceImpl implements CpclwcqkService {
	@Resource(name=CpclwcqkDaoImpl.NAME)
	CpclwcqkDao cpclwcqkDao;

	public final static String NAME = "CpclwcqkServiceImpl";

}
