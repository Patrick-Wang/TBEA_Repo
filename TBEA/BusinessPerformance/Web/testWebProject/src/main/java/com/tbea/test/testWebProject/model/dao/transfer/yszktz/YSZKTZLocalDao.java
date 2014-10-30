package com.tbea.test.testWebProject.model.dao.transfer.yszktz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.test.testWebProject.model.entity.local.YSZKTZLocal;

public interface YSZKTZLocalDao extends AbstractReadWriteDao<YSZKTZLocal> {

	public List<YSZKTZLocal> getAllYSZKTZLocal();

	public Double getCQK(int startTime, int endTime, List<String> sshyList,
			boolean isIncluded, boolean isTotal) throws Exception;

}
