package com.tbea.ic.operation.service.yszkgb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.model.dao.yszkgb.yszkyjtztjqs.YszkYjtzTjqsDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkyjtztjqs.YszkYjtzTjqsDao;
import com.tbea.ic.operation.model.dao.yszkgb.yqyszcsys.YqyszcsysDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yqyszcsys.YqyszcsysDao;
import com.tbea.ic.operation.model.dao.yszkgb.yszkkxxz.YszkKxxzDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkkxxz.YszkKxxzDao;
import com.tbea.ic.operation.model.dao.yszkgb.yszkzl.YszkZlDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkzl.YszkZlDao;

import javax.annotation.Resource;

import com.tbea.ic.operation.model.dao.yszkgb.yszkzm.YszkzmDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkzm.YszkzmDao;
import com.tbea.ic.operation.service.yszkgb.YszkgbService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(YszkgbServiceImpl.NAME)
@Transactional("transactionManager")
public class YszkgbServiceImpl implements YszkgbService {
	@Resource(name=YszkYjtzTjqsDaoImpl.NAME)
	YszkYjtzTjqsDao yszkYjtzTjqsDao;

	@Resource(name=YqyszcsysDaoImpl.NAME)
	YqyszcsysDao yqyszcsysDao;

	@Resource(name=YszkKxxzDaoImpl.NAME)
	YszkKxxzDao yszkKxxzDao;

	@Resource(name=YszkZlDaoImpl.NAME)
	YszkZlDao yszkZlDao;

	@Resource(name=YszkzmDaoImpl.NAME)
	YszkzmDao yszkzmDao;

	public final static String NAME = "YszkgbServiceImpl";

	@Override
	public List<List<String>> getZmb(Date d) {
		List<List<String>> result = new ArrayList<List<String>>();
		return result;
	}

}
