package com.tbea.ic.operation.model.dao.pricelib.jcycljg.ggp;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GgpEntity;


public interface GgpDao extends AbstractReadWriteDao<GgpEntity>{

	List<GgpEntity> getGgp(Date start, Date end);

}
