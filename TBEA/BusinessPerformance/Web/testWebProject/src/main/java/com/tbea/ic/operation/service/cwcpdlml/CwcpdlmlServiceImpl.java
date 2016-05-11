package com.tbea.ic.operation.service.cwcpdlml;

import java.sql.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml.CpdlmlDao;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpfl.CpflDao;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpfl.CpflDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cwgb.cy.CyDao;
import com.tbea.ic.operation.model.dao.identifier.cwgb.cy.CyDaoImpl;

@Service(CwcpdlmlServiceImpl.NAME)
@Transactional("transactionManager")
public class CwcpdlmlServiceImpl implements CwcpdlmlService {
	@Resource(name=CyDaoImpl.NAME)
	CyDao cyDao;

	@Resource(name=CpflDaoImpl.NAME)
	CpflDao cpflDao;
	
	@Autowired
	CpdlmlDao cpdlmlDao;

	public final static String NAME = "CwcpdlmlServiceImpl";

	

	@Override
	public void importFromNC(Date d) {
		// TODO Auto-generated method stub
		
	}




}
