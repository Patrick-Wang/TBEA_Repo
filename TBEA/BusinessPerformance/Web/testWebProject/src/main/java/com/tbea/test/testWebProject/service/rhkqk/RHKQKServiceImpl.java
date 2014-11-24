package com.tbea.test.testWebProject.service.rhkqk;

import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional("transactionManager")
public class RHKQKServiceImpl implements   RHKQKService{

	public String[][] getRhkqkData(Date d){
		return new String[8][14];		
	}

}
