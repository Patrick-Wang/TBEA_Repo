package com.tbea.ic.operation.service.chgb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.chgb.nych.NychDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.nych.NychDao;
import com.tbea.ic.operation.model.dao.chgb.chxzqk.ChxzqkDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chxzqk.ChxzqkDao;
import com.tbea.ic.operation.model.dao.chgb.chzlbhqk.ChzlbhqkDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chzlbhqk.ChzlbhqkDao;
import com.tbea.ic.operation.model.dao.chgb.chjykc.ChJykcDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chjykc.ChJykcDao;

import javax.annotation.Resource;

import com.tbea.ic.operation.model.dao.chgb.chzm.ChZmDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chzm.ChZmDao;
import com.tbea.ic.operation.model.entity.chgb.ChZmEntity;
import com.tbea.ic.operation.model.entity.chgb.ChJykcEntity;
import com.tbea.ic.operation.service.chgb.ChgbService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm.JykcxmDao;
import com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm.JykcxmDaoImpl;
import com.tbea.ic.operation.model.entity.identifier.chgb.JykcxmEntity;

@Service(ChgbServiceImpl.NAME)
@Transactional("transactionManager")
public class ChgbServiceImpl implements ChgbService {
	@Resource(name=NychDaoImpl.NAME)
	NychDao nychDao;

	@Resource(name=ChxzqkDaoImpl.NAME)
	ChxzqkDao chxzqkDao;

	@Resource(name=ChzlbhqkDaoImpl.NAME)
	ChzlbhqkDao chzlbhqkDao;

	@Resource(name=ChJykcDaoImpl.NAME)
	ChJykcDao chJykcDao;

	@Resource(name=ChZmDaoImpl.NAME)
	ChZmDao chzmDao;
	
	@Resource(name=JykcxmDaoImpl.NAME)
	JykcxmDao jykcxmDao;

	public final static String NAME = "ChgbServiceImpl";

	@Override
	public List<List<String>> getChzmb(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<ChZmEntity> entities= chzmDao.getByDate(d, company);
		for (ChZmEntity entity : entities){
			List<String> list = new ArrayList<String>();
			list.add("" + entity.getZmje());
			list.add("" + entity.getHzzb());
			list.add("" + entity.getYz());
			result.add(list);
		}
		return result;
	}
	
	@Override
	public List<List<String>> getChjykcb(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<ChJykcEntity> entities= chJykcDao.getByDate(d, company);
		List<JykcxmEntity> jykcxmEntities = jykcxmDao.getXMMapping();
		
		Double hjSyye = null;
		Double hjByxz = null;
		Double hjBycz = null;
		Double hjQmye = null;
		
		for (int i = 0; i < jykcxmEntities.size(); i++) {
			
			Boolean bIsFind = false;
			List<String> list = new ArrayList<String>();
			
			for (ChJykcEntity entity : entities){
				
				if (entity.getJykcxmEntity().getId() == jykcxmEntities.get(i).getId()) {
					
					bIsFind = true;
					
					list.add(entity.getJykcxmEntity().getName());
					list.add("" + entity.getSyye());
					list.add("" + entity.getByxz());
					list.add("" + entity.getBycz());
					list.add("" + entity.getQmye());
					
					if (entity.getSyye() != null){
						hjSyye += entity.getSyye();
					}
					
					if (entity.getByxz() != null){
						hjByxz += entity.getByxz();
					}
					
					if (entity.getBycz() != null){
						hjBycz += entity.getBycz();
					}
					
					if (entity.getQmye() != null){
						hjQmye += entity.getQmye();
					}

					entities.remove(entity);
					break;
				}
			}
			
			if (!bIsFind) {
				list.add(jykcxmEntities.get(i).getName());
				list.add("null");
				list.add("null");
				list.add("null");
				list.add("null");
			}

			result.add(list);
		}
			
		List<String> listHj = new ArrayList<String>();

		listHj.add("合计");
		listHj.add("" + hjSyye);
		listHj.add("" + hjByxz);
		listHj.add("" + hjBycz);
		listHj.add("" + hjQmye);
		
		result.add(listHj);
		
		return result;
	}
}
