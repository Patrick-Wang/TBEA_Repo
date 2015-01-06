package com.tbea.test.testWebProject.model.dao.hkjhjgb;

import java.sql.Date;
import java.util.List;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.entity.HKJHJG;

public interface HKJHJGDao {

	List<HKJHJG> getHkjhjg(Date d, Company comp);

	HKJHJG getLatestHkjg();

}
