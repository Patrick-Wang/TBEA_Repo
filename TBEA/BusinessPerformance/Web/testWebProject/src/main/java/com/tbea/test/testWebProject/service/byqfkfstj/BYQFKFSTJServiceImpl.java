package com.tbea.test.testWebProject.service.byqfkfstj;

import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional("transactionManager")
public class BYQFKFSTJServiceImpl implements BYQFKFSTJService{

	public String[][] getFdwData(Date d){
		return new String[4][16];
	}

	public String[][] getGwData(Date d){
		return new String[4][18];
	}

	public String[][] getNwData(Date d){
		return new String[4][14];
	}

}
