package com.tbea.ic.operation.model.dao.dl.sbdqy;

import java.sql.Date;
import java.util.List;

public interface DlQyDao {

	List<Object[]> getCpqy(Date d);

	List<Object[]> getScqy(Date d);

	

}
