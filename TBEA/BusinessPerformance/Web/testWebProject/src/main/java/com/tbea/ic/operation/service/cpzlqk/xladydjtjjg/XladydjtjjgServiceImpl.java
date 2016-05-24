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
		List<XlAdwtjjgEntity> entities = xladwtjjgDao.getAll();
		List<Integer> comps = new ArrayList<Integer>();
		FormulaClientJd client = new FormulaClientJd(this, zltjjgDao, comps, d, yjType);
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>(client);
		for (XlAdwtjjgEntity entity : entities){
			Formula formula = new Formula(entity.getFormul());
			Company comp = null;
			if (entity.getDw() != null){
				comp = companyManager.getBMDBOrganization().getCompany(entity.getDw().getId());
				comps.add(comp.getId());
			}
			client.add(formula, entity, comp);
			fs.addFormul(formula);
		}
		
		fs.run();
		return client.getResult();
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
				 
		row.set(start++, entity.getCpdl().getName());
		start = setZltjjg(row, start, tj1);
		setZltjjg(row, start, tj2);
	}

}
