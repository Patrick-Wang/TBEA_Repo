package com.tbea.datatransfer.model.dao.yszk15.bl;

import java.util.Calendar;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.yszk15.BL15;

public interface BL15Dao extends AbstractReadOnlyDao<BL15> {

	public List<BL15> getAllBL15();

	// xjlrb
	public List<Object[]> getXJLRBByDate(Calendar date) throws Exception;

	// ydzbfdw
	public List<Object[]> getAllYDZBFDW(Integer year, Integer month)
			throws Exception;

	// zbhz
	public List<Object[]> getAllZBHZ(Integer year, Integer month)
			throws Exception;

}
