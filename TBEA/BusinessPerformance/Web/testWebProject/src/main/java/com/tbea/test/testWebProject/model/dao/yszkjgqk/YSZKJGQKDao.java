package com.tbea.test.testWebProject.model.dao.yszkjgqk;

import java.util.Calendar;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.test.testWebProject.model.entity.YSZKJGQK;

public interface YSZKJGQKDao extends AbstractReadWriteDao<YSZKJGQK> {

	List<YSZKJGQK> getYszkjg(Calendar cal);

	List<YSZKJGQK> getJetbbh(Calendar cal);

	List<YSZKJGQK> getWdqtbbh(Calendar cal);

	
}
