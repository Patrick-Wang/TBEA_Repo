package com.tbea.ic.operation.service.dzwzgb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb.DzclkcbDao;
import com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb.DzclkcbDaoImpl;
import com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb.TqbzYjjDao;
import com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb.TqbzYjjDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.common.clmc.Clmc;
import com.tbea.ic.operation.model.entity.dzwzgb.DzclkcbEntity;
import com.tbea.ic.operation.model.entity.dzwzgb.TqbzYjjEntity;
import com.xml.frame.report.util.EasyCalendar;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service(DzwzgbServiceImpl.NAME)
@Transactional("transactionManager")
public class DzwzgbServiceImpl implements DzwzgbService {
	@Resource(name=DzclkcbDaoImpl.NAME)
	DzclkcbDao dzclkcbDao;
	
	@Resource(name=TqbzYjjDaoImpl.NAME)
	TqbzYjjDao tqbzyjjDao;

	static Map<Integer, Clmc> tqbzCllxMcMap = new HashMap<Integer, Clmc>();
	static{
		tqbzCllxMcMap.put(1, Clmc.CU);
		tqbzCllxMcMap.put(2, Clmc.AL);
	}
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
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
					List<String> row = toList(entityCu);
					row.remove(6);
					row.remove(3);
					result.add(row);
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
	
	@Override
	public ErrorCode importDzclcb(Date d){
		Logger logger = Logger.getLogger("LOG-NC");
		logger.debug("大宗物资管报" + d);
		List<TqbzYjjEntity> entities = tqbzyjjDao.getByDate(d);
		for (TqbzYjjEntity entity : entities){
			logger.debug(JSONObject.fromObject(entity).toString());
			importToDzclcb(entity, d);
		}
		return ErrorCode.OK;
	}

	private void importToDzclcb(TqbzYjjEntity entity, Date d) {
		if (tqbzCllxMcMap.containsKey(entity.getCLLB().intValue())){
			Organization org15 = companyManager.get15Org();
			Company comp = org15.getCompany(entity.getQYBH().intValue());
			if (null != comp){
				Organization bmdb = companyManager.getBMDBOrganization();
				comp = bmdb.getCompany(comp.getType());
				DzclkcbEntity dzclkcb = dzclkcbDao.getByNy(d, comp, tqbzCllxMcMap.get(entity.getCLLB().intValue()).ordinal());
				if (null == dzclkcb){
					EasyCalendar ec = new EasyCalendar(d);
					dzclkcb = new DzclkcbEntity();
					dzclkcb.setNf(ec.getYear());
					dzclkcb.setYf(ec.getMonth());
					dzclkcb.setClid(tqbzCllxMcMap.get(entity.getCLLB().intValue()).ordinal());
					dzclkcb.setDwid(comp.getId());
					dzclkcb.setZt(ZBStatus.APPROVED.ordinal());
				}
				
				dzclkcb.setQhyk(entity.getDYQHYK());//期货盈亏
				dzclkcb.setScxhyjj(entity.getSCJ());//市场现货月均价
				dzclkcb.setCgyjj(entity.getCGJ2());//采购月均价
				dzclkcb.setSxfybbj(entity.getSXFYJ());//三项费用保本价
				dzclkcb.setMblrdsj(entity.getMBLRDSJ());//目标利润倒算假
				dzclkcb.setCgl(entity.getCGL());//采购量
				
				if (comp.getType() == CompanyType.SBGS ||
						comp.getType() == CompanyType.HBGS ||
						comp.getType() == CompanyType.TBGS ||
						comp.getType() == CompanyType.XBC){
					dzclkcb.setZdjazmulr(MathUtil.division(MathUtil.mul(entity.getCGL(),
																		MathUtil.minus(entity.getCGJ2(), entity.getMBLRDSJ())),
															10000d));//指导价格按照目标利润价
				}else{
					dzclkcb.setZdjazmulr(MathUtil.division(MathUtil.mul(entity.getCGL(),
																		MathUtil.minus(entity.getCGJ2(), entity.getMBLRDSJ())),
															10000d));//指导价格按照目标利润价
					dzclkcb.setZdjazbbj(MathUtil.division(MathUtil.mul(entity.getCGL(),
																		MathUtil.minus(entity.getCGJ2(), entity.getSXFYJ())),
															10000d));//指导价格按照保本价
				}
				dzclkcbDao.merge(dzclkcb);
				
			}
		}
		
	}

}
