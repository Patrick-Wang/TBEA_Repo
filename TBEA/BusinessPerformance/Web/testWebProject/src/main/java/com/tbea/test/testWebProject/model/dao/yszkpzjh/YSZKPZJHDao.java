package com.tbea.test.testWebProject.model.dao.yszkpzjh;

import java.sql.Date;
import java.util.List;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.model.entity.YSZKPZGH;

public interface YSZKPZJHDao {

	List<YSZKPZGH> getPzjhData(Date d, Company comp);

}
