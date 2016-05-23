package com.tbea.ic.operation.model.dao.tbbzjqk;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.TBBZJXX;

public interface TBBZJQKDao {

	List<TBBZJXX> getTbbzj(Date d, Company comp);

	TBBZJXX getLatestTBJ();

}
