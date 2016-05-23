package com.tbea.ic.operation.service.cpzlqk.xladydjtjjg;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Formula;
import com.tbea.ic.operation.common.FormulaServer;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Pair;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.xladwtjjg.XlAdwtjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoImpl;
import com.tbea.ic.operation.model.entity.cpzlqk.XlAdwtjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;

@Service(XladydjtjjgServiceImpl.NAME)
@Transactional("transactionManager")
public class XladydjtjjgServiceImpl implements XladydjtjjgService {
	@Resource(name=ZltjjgDaoImpl.NAME)
	ZltjjgDao zltjjgDao;

	@Autowired
	XlAdwtjjgDao xladwtjjgDao;
	
	public final static String NAME = "XladydjtjjgServiceImpl";

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Override
	public List<List<String>> getXladydjtjjg(Date d, YDJDType yjType) {
		if (yjType == YDJDType.YD){
			return getYdAdwtjjg(d);
		}
		return getJdAdwtjjg(d);
	}

	private List<List<String>> getJdAdwtjjg(Date d) {
		List<XlAdwtjjgEntity> entities = xladwtjjgDao.getAll();
		List<List<String>> result = new ArrayList<List<String>>();
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>();
		List<FormulaClientJd> clients = new ArrayList<FormulaClientJd>();
		for (XlAdwtjjgEntity entity : entities){
			ZltjjgEntity dj = null;
			ZltjjgEntity tq = null;
			if (entity.getDw() != null){
				Company comp = companyManager.getBMDBOrganization().getCompany(entity.getDw().getId());
				dj = zltjjgDao.getJdAcc(d, entity.getCpxl().getId(), comp);
				tq = zltjjgDao.getJdAccQntq(d, entity.getCpxl().getId(), comp);
			}
			clients.add(new FormulaClientJd(this, entity, dj, tq));
			fs.addRule(new Formula(entity.getFormul()), clients.get(clients.size() - 1));
		}
		
		fs.run();
		
		for (FormulaClientJd client : clients){
			List<String> list = client.getRow();
			if (null != list){
				result.add(list);
			}
		}

		return result;
	}

	

	
	
	private List<List<String>> getYdAdwtjjg(Date d) {
		List<XlAdwtjjgEntity> entities = xladwtjjgDao.getAll();
		List<List<String>> result = new ArrayList<List<String>>();
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>();
		List<FormulaClientJd> clients = new ArrayList<FormulaClientJd>();
		for (XlAdwtjjgEntity entity : entities){
			ZltjjgEntity dy = null;
			ZltjjgEntity lj = null;
			if (entity.getDw() != null){
				Company comp = companyManager.getBMDBOrganization().getCompany(entity.getDw().getId());
				dy = zltjjgDao.getByDate(d, entity.getCpxl().getId(), comp);
				lj = zltjjgDao.getYearAcc(d, entity.getCpxl().getId(), comp);
			}
			clients.add(new FormulaClientJd(this, entity, dy, lj));
			fs.addRule(new Formula(entity.getFormul()), clients.get(clients.size() - 1));
		}
		
		fs.run();
		
		for (FormulaClientJd client : clients){
			List<String> list = client.getRow();
			if (null != list){
				result.add(list);
			}
		}
		
		return result;
	}
	
	private int setZltjjg(List<String> row, int start, ZltjjgEntity zltjjg){
		if (null != zltjjg){
			row.set(start++, "" + zltjjg.getBhgs());
			row.set(start++, "" + zltjjg.getZs());
			row.set(start++, "" + MathUtil.division(MathUtil.minus(zltjjg.getZs(), zltjjg.getBhgs()), zltjjg.getZs()));
		}else{
			start += 3;
		}
		return start;
	}
	

	void setRow(List<String> row, XlAdwtjjgEntity entity, ZltjjgEntity tj1, ZltjjgEntity tj2){
		int start = 0;
		if (null == entity.getDwmc()){
			row.set(start++, entity.getCpxl().getName());
		}else{
			row.set(start++, entity.getDwmc().getName());
		}
				
		row.set(start++, entity.getCpxl().getName());
		start = setZltjjg(row, start, tj1);
		setZltjjg(row, start, tj2);
	}

}
