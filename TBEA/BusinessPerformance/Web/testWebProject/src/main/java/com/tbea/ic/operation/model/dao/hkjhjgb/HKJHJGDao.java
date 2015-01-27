package com.tbea.ic.operation.model.dao.hkjhjgb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.HKJHJG;

public interface HKJHJGDao {

	List<HKJHJG> getHkjhjg(Date d, Company comp);

	HKJHJG getLatestHkjg();

}
