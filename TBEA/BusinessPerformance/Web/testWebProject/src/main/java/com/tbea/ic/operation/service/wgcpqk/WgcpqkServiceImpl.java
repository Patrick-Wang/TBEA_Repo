package com.tbea.ic.operation.service.wgcpqk;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDao;
import com.tbea.ic.operation.model.dao.identifier.common.CpmcDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.wlydd.wlyddmlspcs.WlyddmlspcsDao;
import com.tbea.ic.operation.model.dao.wlydd.wlyddmlspcs.WlyddmlspcsDaoImpl;

@Service(WgcpqkServiceImpl.NAME)
@Transactional("transactionManager")
public class WgcpqkServiceImpl implements WgcpqkService {
	@Resource(name = WlyddmlspcsDaoImpl.NAME)
	WlyddmlspcsDao wlyddmlspcsDao;
	
	@Resource(name=CpmcDaoImpl.NAME)
	CpmcDao cpmcDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	public final static String NAME = "WgcpqkServiceImpl";

	@Override
	public void importFromNC(Date d, List<Company> cOMPS) {
		// TODO Auto-generated method stub
		
	}


}
