package com.tbea.ic.operation.model.dao.transfer.yszktz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.ic.operation.model.entity.yszk15.YSZKTZ15;

public interface YSZKTZ15Dao extends AbstractReadOnlyDao<YSZKTZ15> {

	public List<YSZKTZ15> getAllYSZKTZ15();
	
}
