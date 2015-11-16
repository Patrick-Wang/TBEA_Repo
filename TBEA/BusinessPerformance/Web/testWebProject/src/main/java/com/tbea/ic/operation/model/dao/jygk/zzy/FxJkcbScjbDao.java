package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbJsjb;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbScjb;

public interface FxJkcbScjbDao  extends AbstractReadWriteDao<JygkZzyFxJkcbScjb>{
	JygkZzyFxJkcbScjb getZb(int zb, Date date, int company);
}
