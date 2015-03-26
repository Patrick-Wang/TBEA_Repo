package com.tbea.datatransfer.model.dao.zj.jygk.ydzbzt;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zj.jygk.YDZBZT;

public interface YDZBZTDao extends AbstractReadOnlyDao<YDZBZT> {

	public List<YDZBZT> getAllYDZBZT();

	public List<YDZBZT> getNewYDZBZT(int nf, int yf);

}
