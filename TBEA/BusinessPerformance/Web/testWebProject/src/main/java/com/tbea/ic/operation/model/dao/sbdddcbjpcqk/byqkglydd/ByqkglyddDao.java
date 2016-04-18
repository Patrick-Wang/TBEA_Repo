package com.tbea.ic.operation.model.dao.sbdddcbjpcqk.byqkglydd;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.controller.servlet.wlydd.sbdddcbjpcqk.KglyddType;
import com.tbea.ic.operation.model.entity.sbdddcbjpcqk.ByqkglyddEntity;



public interface ByqkglyddDao extends AbstractReadWriteDao<ByqkglyddEntity> {

	List<ByqkglyddEntity> getByDate(Date d, KglyddType type);

}
