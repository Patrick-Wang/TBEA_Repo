package com.tbea.test.testWebProject.service.xlfkfstj;

import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("transactionManager")
public class XLFKFSTJServiceImpl implements XLFKFSTJService {

	public String[][] getFdwData(Date d){
		return new String[12][22];
	}

	public String[][] getGwData(Date d){
		return new String[3][22];
	}

	public String[][] getNwData(Date d){
		return new String[3][12];
	}

}
