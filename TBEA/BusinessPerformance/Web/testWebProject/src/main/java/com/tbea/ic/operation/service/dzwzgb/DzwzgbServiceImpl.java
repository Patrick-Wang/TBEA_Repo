package com.tbea.ic.operation.service.dzwzgb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb.DzclkcbDaoImpl;
import com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb.DzclkcbDao;
import com.tbea.ic.operation.model.dao.identifier.ylfx.clmc.Clmc;
import com.tbea.ic.operation.model.entity.dzwzgb.DzclkcbEntity;
import com.tbea.ic.operation.service.dzwzgb.DzwzgbService;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(DzwzgbServiceImpl.NAME)
@Transactional("transactionManager")
public class DzwzgbServiceImpl implements DzwzgbService {
	@Resource(name=DzclkcbDaoImpl.NAME)
	DzclkcbDao dzclkcbDao;

	public final static String NAME = "DzwzgbServiceImpl";

	@Override
	public List<List<String>> getDzclcb(Date d, Company company) {
		List<DzclkcbEntity> entities = dzclkcbDao.getByNf(d, company);
		List<List<String>> result = new ArrayList<List<String>>();
		if (company.getType() == CompanyType.SBGS ||
				company.getType() == CompanyType.HBGS ||
				company.getType() == CompanyType.TBGS ||
				company.getType() == CompanyType.XBC ){
			DzclkcbEntity entityCu = null;
			for (int i = 0; i < 12; ++i){
				entityCu = null;
				for (DzclkcbEntity entity : entities){
					if (entity.getClid() == Clmc.CU.ordinal() && entity.getYf() == (i + 1)){
						entityCu = entity;
						break;
					}
				}
				if (null != entityCu){
					entities.remove(entityCu);
					result.add(toList(entityCu));
				}else{
					result.add(new ArrayList<String>());
				}
			}
		}else{
			DzclkcbEntity entityAl = null;
			DzclkcbEntity entityCu = null;
			for (int i = 0; i < 12; ++i){
				entityCu = null;
				entityAl = null;
				for (DzclkcbEntity entity : entities){
					if (entity.getYf() == (i + 1)){
						if (entity.getClid() == Clmc.CU.ordinal()){
							entityCu = entity;
						}else if(entity.getClid() == Clmc.AL.ordinal()){
							entityAl = entity;
						}
					}
					if (entityCu != null && entityAl != null){
						break;
					}
				}
				
				if (null != entityCu){
					entities.remove(entityCu);
					result.add(toList(entityCu));
				}else{
					result.add(new ArrayList<String>());
				}
				
				if (null != entityAl){
					entities.remove(entityAl);
					result.add(toList(entityAl));
				}else{
					result.add(new ArrayList<String>());
				}
			}
		}
		return result;
	}

	private List<String> toList(DzclkcbEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getQhyk());
		list.add("" + entity.getScxhyjj());
		list.add("" + entity.getCgyjj());
		list.add("" + entity.getSxfybbj());
		list.add("" + entity.getMblrdsj());
		list.add("" + entity.getCgl());
		list.add("" + entity.getZdjazbbj());
		list.add("" + entity.getZdjazmulr());
		return list;
	}
	
	@Override
	public List<List<String>> getDzclcbEntry(Date d, Company company) {
		List<DzclkcbEntity> entities = dzclkcbDao.getByNy(d, company);
		List<List<String>> result = new ArrayList<List<String>>();
		if (company.getType() == CompanyType.SBGS ||
				company.getType() == CompanyType.HBGS ||
				company.getType() == CompanyType.TBGS ||
				company.getType() == CompanyType.XBC ){
			if (!entities.isEmpty()){
				DzclkcbEntity entityCu = entities.get(0);
				result.add(toList(entityCu));
			}else{
				result.add(new ArrayList<String>());
			}
		}else{
			result.add(new ArrayList<String>());
			result.add(new ArrayList<String>());
			for (DzclkcbEntity entity : entities){
					if (entity.getClid() == Clmc.CU.ordinal()){
						result.set(0, toList(entity));
					}else if(entity.getClid() == Clmc.AL.ordinal()){
						result.set(1, toList(entity));
					}
			}
		}
		return result;
	}
	
	private DzclkcbEntity create(Date d, Company company, Clmc clmc, ZBStatus status){
		DzclkcbEntity entity = new DzclkcbEntity();
		entity.setDwid(company.getId());
		entity.setClid(clmc.ordinal());
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		entity.setNf(cal.get(Calendar.YEAR));
		entity.setYf(cal.get(Calendar.MONTH) + 1);
		entity.setZt(status.ordinal());
		return entity;
	}
	
	private DzclkcbEntity json2Entity(DzclkcbEntity entity, JSONArray data){
		int i = 0;
		entity.setQhyk(Util.toDoubleNull(data.getString(i++)));
		entity.setScxhyjj(Util.toDoubleNull(data.getString(i++)));
		entity.setCgyjj(Util.toDoubleNull(data.getString(i++)));
		entity.setSxfybbj(Util.toDoubleNull(data.getString(i++)));
		entity.setMblrdsj(Util.toDoubleNull(data.getString(i++)));
		entity.setCgl(Util.toDoubleNull(data.getString(i++)));
		entity.setZdjazbbj(Util.toDoubleNull(data.getString(i++)));
		entity.setZdjazmulr(Util.toDoubleNull(data.getString(i++)));
		return entity;
	}
	
	private ErrorCode entryDzclcb(Date d, Company company, JSONArray data, ZBStatus status){
		List<DzclkcbEntity> entities = dzclkcbDao.getByNy(d, company);
		if (company.getType() == CompanyType.SBGS ||
				company.getType() == CompanyType.HBGS ||
				company.getType() == CompanyType.TBGS ||
				company.getType() == CompanyType.XBC ){
			DzclkcbEntity entityCu = null;
			if (!entities.isEmpty()){
				entityCu = entities.get(0);
			}
			if (entityCu == null){
				entityCu = this.create(d, company, Clmc.CU, status);
			}
			dzclkcbDao.merge(json2Entity(entityCu, data.getJSONArray(0)));
		}else{
			DzclkcbEntity entityCu = null;
			DzclkcbEntity entityAl = null;
			for (DzclkcbEntity entity : entities){
					if (entity.getClid() == Clmc.CU.ordinal()){
						entityCu = entity;
					}else if(entity.getClid() == Clmc.AL.ordinal()){
						entityAl = entity;
					}
			}
			if (entityCu == null){
				entityCu = this.create(d, company, Clmc.CU, status);
			}
			if (entityAl == null){
				entityAl = this.create(d, company, Clmc.AL, status);
			}
			dzclkcbDao.merge(json2Entity(entityCu, data.getJSONArray(0)));
			dzclkcbDao.merge(json2Entity(entityAl, data.getJSONArray(1)));
		}
		return ErrorCode.OK;
	}

	@Override
	public ErrorCode submitDzclcb(Date d, Company company, JSONArray data) {
		return entryDzclcb(d, company, data, ZBStatus.SUBMITTED);
	}

	@Override
	public ErrorCode saveDzclcb(Date d, Company company, JSONArray data) {
		return entryDzclcb(d, company, data, ZBStatus.SAVED);
	}

}
