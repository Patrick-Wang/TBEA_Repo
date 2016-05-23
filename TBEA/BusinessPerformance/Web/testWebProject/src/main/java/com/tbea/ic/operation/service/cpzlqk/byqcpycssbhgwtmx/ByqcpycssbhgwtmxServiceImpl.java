package com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgwtmx;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.controller.servlet.cpzlqk.ByqBhgType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.byqbhglb.ByqBhglbDao;
import com.tbea.ic.operation.model.dao.cpzlqk.byqbhglb.ByqBhglbDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.byqbhgwtmx.ByqBhgwtmxDao;
import com.tbea.ic.operation.model.dao.cpzlqk.byqbhgwtmx.ByqBhgwtmxDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.dwmc.DwmcDao;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqBhgwtmxEntity;

@Service(ByqcpycssbhgwtmxServiceImpl.NAME)
@Transactional("transactionManager")
public class ByqcpycssbhgwtmxServiceImpl implements ByqcpycssbhgwtmxService {
	@Resource(name=ByqBhglbDaoImpl.NAME)
	ByqBhglbDao byqBhglbDao;

	@Resource(name=ByqBhgwtmxDaoImpl.NAME)
	ByqBhgwtmxDao byqBhgwtmxDao;
	
	@Autowired
	DwmcDao dwmcDao;

	public final static String NAME = "ByqcpycssbhgwtmxServiceImpl";

	@Override
	public List<List<String>> getByqcpycssbhgwtmx(Date d,
			YDJDType yjType, ByqBhgType bhgType) {
		List<ByqBhgwtmxEntity> entities = null;
		if (yjType == YDJDType.YD){
			entities = byqBhgwtmxDao.getByYd(d, bhgType.ordinal());
		}else{
			entities = byqBhgwtmxDao.getByJd(d, bhgType.ordinal());
		}
		List<List<String>> result = new ArrayList<List<String>>();
		for (ByqBhgwtmxEntity entity : entities){
			result.add(toList(entity));
		}
		return result;
	}
	
	private List<String> toList(ByqBhgwtmxEntity entity) {
		List<String> row = new ArrayList<String>();
		row.add(dwmcDao.getByDwid(entity.getDwid()).getDwmc().getName());
		row.add(entity.getCplx());
		row.add(entity.getSch());
		row.add(entity.getCpxh());
		row.add(entity.getSybhgxx());
		row.add(entity.getBhglb().getName());
		row.add(entity.getYyfx());
		row.add(entity.getClcs());
		row.add(entity.getCljg());
		row.add(entity.getZrlb().getName());
		return row;
	}

}
