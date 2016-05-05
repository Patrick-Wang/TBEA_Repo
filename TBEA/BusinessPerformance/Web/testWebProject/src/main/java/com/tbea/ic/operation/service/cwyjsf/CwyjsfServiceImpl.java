package com.tbea.ic.operation.service.cwyjsf;

import com.tbea.ic.operation.model.dao.cwyjsf.yjsfndqcs.YjsfNdqcsDaoImpl;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsfndqcs.YjsfNdqcsDao;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsf.YjsfDaoImpl;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsf.YjsfDao;
import javax.annotation.Resource;
import com.tbea.ic.operation.model.dao.identifier.cwyjsf.sz.SzDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cwyjsf.sz.SzDao;
import com.tbea.ic.operation.service.cwyjsf.CwyjsfService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(CwyjsfServiceImpl.NAME)
@Transactional("transactionManager")
public class CwyjsfServiceImpl implements CwyjsfService {
	@Resource(name=YjsfNdqcsDaoImpl.NAME)
	YjsfNdqcsDao yjsfNdqcsDao;

	@Resource(name=YjsfDaoImpl.NAME)
	YjsfDao yjsfDao;

	@Resource(name=SzDaoImpl.NAME)
	SzDao szDao;

	public final static String NAME = "CwyjsfServiceImpl";

}
