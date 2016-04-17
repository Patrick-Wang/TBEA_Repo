package com.tbea.ic.operation.service.sbdddcbjpcqk;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.controller.servlet.sbdddcbjpcqk.KglyddType;
import com.tbea.ic.operation.model.dao.identifier.sbdddcbjpcqk.byqcplx.ByqcplxDao;
import com.tbea.ic.operation.model.dao.identifier.sbdddcbjpcqk.byqcplx.ByqcplxDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.sbdddcbjpcqk.xlcplx.XlcplxDao;
import com.tbea.ic.operation.model.dao.identifier.sbdddcbjpcqk.xlcplx.XlcplxDaoImpl;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.byqkglydd.ByqkglyddDao;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.byqkglydd.ByqkglyddDaoImpl;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.xlkglydd.XlkglyddDao;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.xlkglydd.XlkglyddDaoImpl;
import com.tbea.ic.operation.model.entity.identifier.sbdddcbjpcqk.ByqcplxEntity;
import com.tbea.ic.operation.model.entity.identifier.sbdddcbjpcqk.XlcplxEntity;
import com.tbea.ic.operation.model.entity.sbdddcbjpcqk.ByqkglyddEntity;
import com.tbea.ic.operation.model.entity.sbdddcbjpcqk.XlkglyddEntity;

@Service(SbdddcbjpcqkServiceImpl.NAME)
@Transactional("transactionManager")
public class SbdddcbjpcqkServiceImpl implements SbdddcbjpcqkService {
	
	@Resource(name=XlcplxDaoImpl.NAME)
	XlcplxDao xlcplxDao;

	@Resource(name=ByqcplxDaoImpl.NAME)
	ByqcplxDao byqcplxDao;

	@Resource(name = XlkglyddDaoImpl.NAME)
	XlkglyddDao xlkglyddDao;

	@Resource(name = ByqkglyddDaoImpl.NAME)
	ByqkglyddDao byqkglyddDao;

	public final static String NAME = "SbdddcbjpcqkServiceImpl";

	private List<String> toList(ByqkglyddEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getSclx());
		list.add("" + entity.getYccnlcz());
		list.add("" + entity.getYccnlcl());
		list.add("" + entity.getSykglyddzlcz());
		list.add("" + entity.getSykglyddzlcl());
		list.add("" + entity.getDnkglyddzlcz());
		list.add("" + entity.getDnkglyddzlcl());
		list.add("" + entity.getNj1yddlcz());
		list.add("" + entity.getNj1yddlcl());
		list.add(""	+ Util.division(entity.getNj1yddlcl(), entity.getYccnlcl()));
		list.add("" + entity.getNj2yddlcz());
		list.add("" + entity.getNj2yddlcl());
		list.add(""	+ Util.division(entity.getNj2yddlcl(), entity.getYccnlcl()));
		list.add("" + entity.getNj3yddlcz());
		list.add("" + entity.getNj3yddlcl());
		list.add(""	+ Util.division(entity.getNj3yddlcl(), entity.getYccnlcl()));
		list.add("" + entity.getNj4yddlcz());
		list.add("" + entity.getNj4yddlcl());
		list.add(""	+ Util.division(entity.getNj4yddlcl(), entity.getYccnlcl()));
		list.add("" + entity.getNj5yddlcz());
		list.add("" + entity.getNj5yddlcl());
		list.add(""	+ Util.division(entity.getNj5yddlcl(), entity.getYccnlcl()));
		list.add("" + entity.getNj6yddlcz());
		list.add("" + entity.getNj6yddlcl());
		list.add(""	+ Util.division(entity.getNj6yddlcl(), entity.getYccnlcl()));
		list.add("" + entity.getNj6yyhkglyddlcz());
		list.add("" + entity.getNj6yyhkglyddlcl());
		list.add(""	+ Util.division(entity.getNj6yyhkglyddlcl(), entity.getYccnlcl()));
		list.add("" + entity.getCnjyhkglyddcz());
		list.add("" + entity.getCnjyhkglyddcl());
		list.add("" + entity.getJhqddcz());
		list.add("" + entity.getJhqddcl());
		return list;
	}

	@Override
	public List<List<String>> getByqkglydd(Date d, KglyddType type) {
		List<ByqkglyddEntity> entities = byqkglyddDao.getByDate(d, type);
		List<List<String>> result = new ArrayList<List<String>>();
		for (ByqkglyddEntity entity : entities) {
			result.add(toList(entity));
		}
		return result;
	}

	private List<String> toList(XlkglyddEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getSclx());
		list.add("" + entity.getYccnl());
		list.add("" + entity.getWlyddzl());
		list.add("" + entity.getDnwlyddzl());
		list.add("" + entity.getNj1yddlypc());
		list.add("" + entity.getNj1yddlwpc());
		list.add(""
				+ Util.division(
						Util.sum(new Double[] { entity.getNj1yddlypc(),
								entity.getNj1yddlwpc() }), entity.getYccnl()));
		list.add("" + entity.getNj2yddlypc());
		list.add("" + entity.getNj2yddlwpc());
		list.add(""
				+ Util.division(
						Util.sum(new Double[] { entity.getNj2yddlypc(),
								entity.getNj2yddlwpc() }), entity.getYccnl()));
		list.add("" + entity.getNj3yddlypc());
	    list.add("" + entity.getNj3yddlwpc());
		list.add(""
				+ Util.division(
						Util.sum(new Double[] { entity.getNj3yddlypc(),
								entity.getNj3yddlwpc() }), entity.getYccnl()));
		list.add("" + entity.getNj3yyhlydd());
		list.add("" + entity.getCnjyhkglyddpcz());
		list.add("" + entity.getJhqdd());
		list.add("" + entity.getWx());
		return list;
	}

	@Override
	public List<List<String>> getXlkglydd(Date d, KglyddType type) {
		List<XlkglyddEntity> entities = xlkglyddDao.getByDate(d, type);
		List<List<String>> result = new ArrayList<List<String>>();
		for (XlkglyddEntity entity : entities) {
			result.add(toList(entity));
		}
		return result;
	}

	private List<String> toEntryList(ByqkglyddEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getSclx());
		list.add("" + entity.getYccnlcz());
		list.add("" + entity.getYccnlcl());
		list.add("" + entity.getSykglyddzlcz());
		list.add("" + entity.getSykglyddzlcl());
		list.add("" + entity.getDnkglyddzlcz());
		list.add("" + entity.getDnkglyddzlcl());
		list.add("" + entity.getNj1yddlcz());
		list.add("" + entity.getNj1yddlcl());
		list.add("" + entity.getNj2yddlcz());
		list.add("" + entity.getNj2yddlcl());
		list.add("" + entity.getNj3yddlcz());
		list.add("" + entity.getNj3yddlcl());
		list.add("" + entity.getNj4yddlcz());
		list.add("" + entity.getNj4yddlcl());
		list.add("" + entity.getNj5yddlcz());
		list.add("" + entity.getNj5yddlcl());
		list.add("" + entity.getNj6yddlcz());
		list.add("" + entity.getNj6yddlcl());
		list.add("" + entity.getNj6yyhkglyddlcz());
		list.add("" + entity.getNj6yyhkglyddlcl());
		list.add("" + entity.getCnjyhkglyddcz());
		list.add("" + entity.getCnjyhkglyddcl());
		list.add("" + entity.getJhqddcz());
		list.add("" + entity.getJhqddcl());
		return list;
	}

	@Override
	public List<List<String>> getByqkglyddEntry(Date d, KglyddType type) {
		List<ByqkglyddEntity> entities = byqkglyddDao.getByDate(d, type);
		List<List<String>> result = new ArrayList<List<String>>();
		for (ByqkglyddEntity entity : entities) {
			result.add(toEntryList(entity));
		}
		return result;
	}

	private List<String> toEntryList(XlkglyddEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getSclx());
		list.add("" + entity.getYccnl());
		list.add("" + entity.getWlyddzl());
		list.add("" + entity.getNj1yddlypc());
		list.add("" + entity.getNj1yddlwpc());
		list.add("" + entity.getNj2yddlypc());
		list.add("" + entity.getNj2yddlwpc());
		list.add("" + entity.getNj3yddlypc());
	    list.add("" + entity.getNj3yddlwpc());
		list.add("" + entity.getNj3yyhlydd());
		list.add("" + entity.getCnjyhkglyddpcz());
		list.add("" + entity.getJhqdd());
		list.add("" + entity.getWx());
		return list;
	}

	@Override
	public List<List<String>> getXlkglyddEntry(Date d, KglyddType type) {
		List<XlkglyddEntity> entities = xlkglyddDao.getByDate(d, type);
		List<List<String>> result = new ArrayList<List<String>>();
		for (XlkglyddEntity entity : entities) {
			result.add(toEntryList(entity));
		}
		return result;
	}

	@Override
	public ErrorCode saveXlkglydd(Date d, KglyddType type) {
		// TODO Auto-generated method stub
		return ErrorCode.OK;
	}

	@Override
	public ErrorCode saveByqkglydd(Date d, KglyddType type) {
		// TODO Auto-generated method stub
		return ErrorCode.OK;
	}

	@Override
	public ErrorCode submitByqkglydd(Date d, KglyddType type) {
		// TODO Auto-generated method stub
		return ErrorCode.OK;
	}

	@Override
	public ErrorCode submitXlkglydd(Date d, KglyddType type) {
		// TODO Auto-generated method stub
		return ErrorCode.OK;
	}

	@Override
	public List<String> getByqCplb() {
		List<ByqcplxEntity> entities = byqcplxDao.getAll();
		List<String> list = new ArrayList<String>();
		for (ByqcplxEntity entity : entities){
			list.add("" + entity.getName());
		}
		return list;
	}

	@Override
	public List<String> getXlCplb() {
		List<XlcplxEntity> entities = xlcplxDao.getAll();
		List<String> list = new ArrayList<String>();
		for (XlcplxEntity entity : entities){
			list.add("" + entity.getName());
		}
		return list;
	}

}
