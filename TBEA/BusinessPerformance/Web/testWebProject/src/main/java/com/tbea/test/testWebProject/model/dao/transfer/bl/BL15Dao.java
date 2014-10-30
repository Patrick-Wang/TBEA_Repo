package com.tbea.test.testWebProject.model.dao.transfer.bl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.test.testWebProject.model.entity.yszk15.BL15;

public interface BL15Dao extends AbstractReadOnlyDao<BL15> {

	public List<BL15> getAllBL15();
	
}
