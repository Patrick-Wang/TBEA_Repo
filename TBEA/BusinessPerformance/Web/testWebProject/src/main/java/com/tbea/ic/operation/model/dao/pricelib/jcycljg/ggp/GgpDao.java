package com.tbea.ic.operation.model.dao.pricelib.jcycljg.ggp;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GgpEntity;


public interface GgpDao {

	List<GgpEntity> getGgp(Date start, Date end);

}
