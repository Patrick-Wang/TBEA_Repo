package com.tbea.ic.operation.service.pricelib.jcycljg.ysjs;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs.YsjsDaoImpl;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs.YsjsDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.ysjs.YsjsService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(YsjsServiceImpl.NAME)
@Transactional("transactionManager")
public class YsjsServiceImpl implements YsjsService {
	@Resource(name=YsjsDaoImpl.NAME)
	YsjsDao ysjsDao;

	public final static String NAME = "YsjsServiceImpl";

	@Override
	public List<List<String>> getYsjs(Date start, Date end) {
		List<YsjsEntity> ysjsEntitys = ysjsDao.getYsjs(start, end);
		List<List<String>> result = new ArrayList<List<String>>();
		for (YsjsEntity ysjs : ysjsEntitys){
			List<String> entity = new ArrayList<String>();
			entity.add(Util.formatToDay(ysjs.getDate()));
			entity.add(ysjs.getCjxhCu() + "");
			entity.add(ysjs.getCjxhAl() + "");
			entity.add(ysjs.getCjxhZn() + "");
			entity.add(ysjs.getLEMCu() + "");
			entity.add(ysjs.getLEMAl() + "");
			entity.add( ysjs.getLEMZn() + "");
		    result.add(entity);
		}
		return result;
	}

}
