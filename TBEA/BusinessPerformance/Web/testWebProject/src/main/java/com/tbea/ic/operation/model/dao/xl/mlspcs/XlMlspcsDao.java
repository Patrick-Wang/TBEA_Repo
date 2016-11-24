package com.tbea.ic.operation.model.dao.xl.mlspcs;

import java.sql.Date;
import java.util.List;

public interface XlMlspcsDao {

	List<Object[]> getZxyw(Date d);
	List<Object[]> getZzy(Date d);

}
