package com.tbea.ic.operation.service.sbdddcbjpcqk;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.controller.servlet.sbdddcbjpcqk.KglyddType;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.byqkglydd.ByqkglyddDao;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.byqkglydd.ByqkglyddDaoImpl;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.xlkglydd.XlkglyddDao;
import com.tbea.ic.operation.model.dao.sbdddcbjpcqk.xlkglydd.XlkglyddDaoImpl;
import com.tbea.ic.operation.model.entity.sbdddcbjpcqk.ByqkglyddEntity;
import com.tbea.ic.operation.model.entity.sbdddcbjpcqk.XlkglyddEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YqyszcsysEntity;

@Service(SbdddcbjpcqkServiceImpl.NAME)
@Transactional("transactionManager")
public class SbdddcbjpcqkServiceImpl implements SbdddcbjpcqkService {
	@Resource(name = XlkglyddDaoImpl.NAME)
	XlkglyddDao xlkglyddDao;

	@Resource(name = ByqkglyddDaoImpl.NAME)
	ByqkglyddDao byqkglyddDao;

	public final static String NAME = "SbdddcbjpcqkServiceImpl";

	private List<String> toList(ByqkglyddEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add(entity.getSclx());
		list.add("" + entity.getYccnlcz());
		list.add("" + entity.getYccnlcl());
		list.add("" + entity.getSkglyddzlcz());
		list.add("" + entity.getSykglyddzlcl());
		list.add("" + entity.getDnkglyddzlcz());
		list.add("" + entity.getDnkglyddzlcl());
		list.add("" + entity.getDjdpcddcz());
		list.add("" + entity.getDjdpcddcl());
		list.add(""
				+ Util.division(Util.division(entity.getDjdpcddcl(), 3d),
						entity.getYccnlcl()));
		list.add("" + entity.getXjdpcddcz());
		list.add(""
				+ Util.division(Util.division(entity.getXjdpcddcz(), 3d),
						entity.getYccnlcl()));

		list.add("" + entity.getXjdpcddcl());
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
		list.add(entity.getSclx());
		list.add("" + entity.getYccnl());
		list.add("" + entity.getSykglyddzlcz());
		list.add("" + entity.getDnkglyddzlcz());
		list.add("" + entity.getDjdpcddcz());
		list.add(""
				+ Util.division(entity.getDjdpcddcz(), entity.getYccnl()));
		list.add("" + entity.getXjdpcddcz());
		list.add("" + Util.division(entity.getXjdpcddcz(), entity.getYccnl()));
		list.add("" + entity.getCnjyhkglyddpcz());
		list.add("" + entity.getJhqddcz());
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
		list.add(entity.getSclx());
		list.add("" + entity.getYccnlcz());
		list.add("" + entity.getYccnlcl());
		list.add("" + entity.getSkglyddzlcz());
		list.add("" + entity.getSykglyddzlcl());
		list.add("" + entity.getDnkglyddzlcz());
		list.add("" + entity.getDnkglyddzlcl());
		list.add("" + entity.getDjdpcddcz());
		list.add("" + entity.getDjdpcddcl());
		list.add("" + entity.getXjdpcddcz());
		list.add("" + entity.getXjdpcddcl());
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
		list.add(entity.getSclx());
		list.add("" + entity.getYccnl());
		list.add("" + entity.getSykglyddzlcz());
		list.add("" + entity.getDnkglyddzlcz());
		list.add("" + entity.getDjdpcddcz());
		list.add("" + entity.getXjdpcddcz());
		list.add("" + entity.getCnjyhkglyddpcz());
		list.add("" + entity.getJhqddcz());
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

}
