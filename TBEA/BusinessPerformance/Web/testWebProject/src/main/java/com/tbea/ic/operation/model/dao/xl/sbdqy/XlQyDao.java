package com.tbea.ic.operation.model.dao.xl.sbdqy;

import java.sql.Date;
import java.util.List;

public interface XlQyDao {

	List<Object[]> getCpqy(Date d);

	List<Object[]> getScqy(Date d);

	

}
