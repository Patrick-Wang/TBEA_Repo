package com.tbea.ic.operation.service.cpzlqk.xlbhgcpmx;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.controller.servlet.cpzlqk.ByqBhgType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.dwmc.DwmcDao;
import com.tbea.ic.operation.model.dao.cpzlqk.xlbhgwtmx.XlBhgwtmxDao;
import com.tbea.ic.operation.model.dao.cpzlqk.xlbhgwtmx.XlBhgwtmxDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlbhglb.XlBhglbDao;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlbhglb.XlBhglbDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlzrlb.XlZrlbDao;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlzrlb.XlZrlbDaoImpl;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqBhgwtmxEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.XlBhgwtmxEntity;

@Service(XlbhgcpmxServiceImpl.NAME)
@Transactional("transactionManager")
public class XlbhgcpmxServiceImpl implements XlbhgcpmxService {
	@Resource(name=XlBhgwtmxDaoImpl.NAME)
	XlBhgwtmxDao xlBhgwtmxDao;

	@Resource(name=XlZrlbDaoImpl.NAME)
	XlZrlbDao xlZrlbDao;

	@Resource(name=XlBhglbDaoImpl.NAME)
	XlBhglbDao xlBhglbDao;

	@Autowired
	DwmcDao dwmcDao;
	
	public final static String NAME = "XlbhgcpmxServiceImpl";

	@Override
	public List<List<String>> getXlbhgcpmx(Date d) {
		List<XlBhgwtmxEntity> entities = xlBhgwtmxDao.getByDate(d);
		List<List<String>> result = new ArrayList<List<String>>();
		for (XlBhgwtmxEntity entity : entities){
			result.add(toList(entity));
		}
		return result;
	}
	
	private List<String> toList(XlBhgwtmxEntity entity) {
		List<String> row = new ArrayList<String>();
		row.add(dwmcDao.getByDwid(entity.getDwid()).getDwmc().getName());
		row.add(entity.getCplx());
		row.add(entity.getSch());
		row.add(entity.getCpxh());
		row.add("" + entity.getBhgsl());
		row.add(entity.getSybhgxx());
		row.add(entity.getBhglx().getName());
		row.add(entity.getYyfx());
		row.add(entity.getClcs());
		row.add(entity.getCljg());
		row.add(entity.getZrlb().getName());
		return row;
	}

}
