package com.tbea.test.testWebProject.model.dao.rhkxx;

import java.sql.Date;
import java.util.List;

import com.tbea.test.testWebProject.model.entity.RHKXX;

public interface RHKXXDao {

	List<RHKXX> getRhkxxData(Date d);

	RHKXX getLatestRhkxx();

}
