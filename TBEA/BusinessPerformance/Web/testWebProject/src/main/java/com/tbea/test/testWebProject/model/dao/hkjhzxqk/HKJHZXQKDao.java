package com.tbea.test.testWebProject.model.dao.hkjhzxqk;

import java.sql.Date;
import java.util.List;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.entity.YDHKJHJG;
import com.tbea.test.testWebProject.model.entity.YDSJHKQK;

public interface HKJHZXQKDao {

	List<YDHKJHJG> getHkjhjg(Date d, Company comp);

	List<YDSJHKQK> getSjhkqk(Date d, Company comp);

	YDSJHKQK getLatestYdsjhk();

	List<YDSJHKQK> getHkqkXj(Date d, Company comp);

}
