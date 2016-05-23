package com.tbea.ic.operation.model.dao.yszkpzjh;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.YSZKPZGH;

public interface YSZKPZJHDao {

	List<YSZKPZGH> getPzjhData(Date d, Company comp);

	YSZKPZGH getLatestDate();

}
