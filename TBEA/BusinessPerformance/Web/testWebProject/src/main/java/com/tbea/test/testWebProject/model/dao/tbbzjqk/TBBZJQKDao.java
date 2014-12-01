package com.tbea.test.testWebProject.model.dao.tbbzjqk;

import java.sql.Date;
import java.util.List;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.model.entity.TBBZJXX;

public interface TBBZJQKDao {

	List<TBBZJXX> getTbbzj(Date d, Company comp);

}
