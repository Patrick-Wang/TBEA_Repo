package com.tbea.test.testWebProject.model.dao.transfer.htxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.test.testWebProject.model.entity.yszk15.HTXX15;

public interface HTXX15Dao extends AbstractReadOnlyDao<HTXX15> {

	public List<HTXX15> getAllHTXX15();
	
}
