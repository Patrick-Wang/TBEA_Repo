package com.tbea.test.testWebProject.service.tbbzjqk;

import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Company;
@Service
@Transactional("transactionManager")
public class TBBZJQKServiceImpl implements TBBZJQKService{

	public String[] getTbbzjqkData(Date d, Company comp){
		return new String[]{"0", "0","0","0","0","0","0"};
		
	}
	
}
