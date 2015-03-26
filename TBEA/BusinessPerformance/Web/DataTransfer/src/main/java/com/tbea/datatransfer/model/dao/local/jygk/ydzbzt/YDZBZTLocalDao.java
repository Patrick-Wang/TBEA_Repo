package com.tbea.datatransfer.model.dao.local.jygk.ydzbzt;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.jygk.YDZBZTLocal;

public interface YDZBZTLocalDao extends AbstractReadWriteDao<YDZBZTLocal> {

	public List<YDZBZTLocal> getAllYDZBZTLocal();

	public void truncateYDZBZTLocal();

	public void deleteYDZBZTLocalByDW(List<Integer> dwidList);

	public void deleteYDZBZTLocalByDWAndDate(List<Integer> dwidList, int nf,
			int yf);

}
