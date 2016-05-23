package com.tbea.ic.operation.model.dao.pricelib.jcycljg;

import java.sql.Date;
import java.util.List;

public interface GetEntitiesDao<T> {
	List<T> getEntities(Date start, Date end);
}
