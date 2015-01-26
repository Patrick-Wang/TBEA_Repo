package com.tbea.datatransfer.model.dao.local.ydhkjhjgb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.YDHKJHJGBLocal;

public interface YDHKJHJGBLocalDao extends AbstractReadWriteDao<YDHKJHJGBLocal> {

	public List<YDHKJHJGBLocal> getAllYDHKJHJGBLocal();

	public void deleteYDHKJHJGBLocalByQY(int qybh);

	public Double getHKJHZEByQY(String date, int qybh, String gsbm);
	
	public YDHKJHJGBLocal getHKJHByQY(String date, int qybh, String gsbm);
}
