package com.tbea.ic.operation.model.dao.exchangeRate;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.ExchangeRate;

public interface ExchangeRateDao extends AbstractReadWriteDao<ExchangeRate> {

	public List<ExchangeRate> getAll();

	public ExchangeRate getByDate(Date d);

}
