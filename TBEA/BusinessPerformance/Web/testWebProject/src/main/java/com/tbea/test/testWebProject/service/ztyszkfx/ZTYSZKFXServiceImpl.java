package com.tbea.test.testWebProject.service.ztyszkfx;

import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("transactionManager")
public class ZTYSZKFXServiceImpl implements ZTYSZKFXService{

	public String[][] getZtyszkfxData(Date d){
		return new String[10][14];
	}

}
