package com.tbea.test.testWebProject.service.hkjhjg;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.dao.cqk.CQKDao;
import com.tbea.test.testWebProject.model.dao.transfer.yszktz.YSZKTZLocalDao;
import com.tbea.test.testWebProject.model.entity.local.CQK;
import com.tbea.test.testWebProject.service.hkjhjg.HKJHJGService;

@Service
@Transactional("transactionManager")
public class HKJHJGServiceImpl implements HKJHJGService {

	@Override
	public String[][] getHkjhjgData(Date d, Company comp) {
		// TODO Auto-generated method stub
		return new String[][]{
				{"1.00", "2.00", "3.00", "4.00", "4.00"},
				{"1.00", "2.00", "3.00", "4.00", "4.00"},
				{"1.00", "2.00", "3.00", "4.00", "4.00"}
				};
	}

	@Override
	public String[] getHkjhztData(Date d, Company comp) {
		// TODO Auto-generated method stub
		return new String[]
				{"1.00", "2.00", "3", "4", "4"};
	}

	@Override
	public String[] getHkjhxzData(Date d, Company comp) {
		return new String[]
				{"1.00", "2.00", "3.00", "4.00", "4"};
	}
}
