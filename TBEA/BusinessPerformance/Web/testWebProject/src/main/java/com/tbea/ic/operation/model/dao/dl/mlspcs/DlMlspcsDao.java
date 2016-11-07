package com.tbea.ic.operation.model.dao.dl.mlspcs;

import java.sql.Date;
import java.util.List;

public interface DlMlspcsDao {

	List<Object[]> getZxyw(Date d);
	List<Object[]> getZzy(Date d);

}
