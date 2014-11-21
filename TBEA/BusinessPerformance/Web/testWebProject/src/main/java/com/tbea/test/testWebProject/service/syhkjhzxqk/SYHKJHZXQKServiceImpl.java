package com.tbea.test.testWebProject.service.syhkjhzxqk;

import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Company;
@Service
@Transactional("transactionManager")
public class SYHKJHZXQKServiceImpl implements SYHKJHZXQKService{

	public String[][] getSyhkjhzxqkData(Date d, Company comp){
		return new String[][]{
				{"1", "1", "1"},
				{"1", "1", "1"},
				{"1", "1", "1"},
				{"1", "1", "1"},
				{"1", "1", "1"},
				{"1", "1", "1"},
				{"1", "1", "1"},
				{"1", "1", "1"},
				{"1", "1", "1"},
				{"1", "1", "1"},
		};
	}

}
