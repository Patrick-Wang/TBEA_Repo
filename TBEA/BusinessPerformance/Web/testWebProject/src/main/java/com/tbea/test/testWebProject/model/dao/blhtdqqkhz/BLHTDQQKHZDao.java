package com.tbea.test.testWebProject.model.dao.blhtdqqkhz;

import java.util.Calendar;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.test.testWebProject.model.entity.BLHTDQQKHZ;

public interface BLHTDQQKHZDao extends AbstractReadWriteDao<BLHTDQQKHZ> {

	List<BLHTDQQKHZ> getBlAfterDate(Calendar cal);

	List<BLHTDQQKHZ> getBltbbh(Calendar cal);

}
