package com.tbea.ic.operation.model.dao.ll.sbdqy;

import java.sql.Date;
import java.util.List;

public interface LlQyDao {

	List<Object[]> getCpqy(Date d);

	List<Object[]> getScqy(Date d);

	

}
