package com.tbea.ic.operation.model.dao.jygk.yjzbzt;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.YDZBZT;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;

public interface YDZBZTDao extends AbstractReadWriteDao<YDZBZT>{

	YDZBZT getYdzbzt(Company comp, int nf, int yf);

}
