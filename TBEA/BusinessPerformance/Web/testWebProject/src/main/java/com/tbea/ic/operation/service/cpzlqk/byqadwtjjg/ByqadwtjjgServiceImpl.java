package com.tbea.ic.operation.service.cpzlqk.byqadwtjjg;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Formula;
import com.tbea.ic.operation.common.FormulaServer;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Pair;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.byqadwtjjg.ByqAdwtjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.byqadwtjjg.ByqAdwtjjgDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoImpl;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqAdwtjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;


@Service(ByqadwtjjgServiceImpl.NAME)
@Transactional("transactionManager")
public class ByqadwtjjgServiceImpl implements ByqadwtjjgService {
	@Resource(name=ByqAdwtjjgDaoImpl.NAME)
	ByqAdwtjjgDao byqAdwtjjgDao;

	@Resource(name=ZltjjgDaoImpl.NAME)
	ZltjjgDao zltjjgDao;

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	public final static String NAME = "ByqadwtjjgServiceImpl";

	@Override
	public List<List<String>> getByqadwtjjg(Date d,
			YDJDType yjType) {
		return getByqadwtjjg(d, yjType, byqAdwtjjgDao.getAll());
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
	

	void setRow(List<String> row, ByqAdwtjjgEntity entity, ZltjjgEntity tj1, ZltjjgEntity tj2){
		int start = 0;
		row.set(start++, entity.getDwmc().getName());
		row.set(start++, entity.getCpxl().getName());
		start = setZltjjg(row, start, tj1);
		setZltjjg(row, start, tj2);
	}

	
	private List<List<String>> getByqadwtjjg(Date d, YDJDType yjType, List<ByqAdwtjjgEntity> entities) {
		List<Integer> comps = new ArrayList<Integer>();
		FormulaClientJd client = new FormulaClientJd(this, zltjjgDao, comps, d, yjType);
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>(client);
		for (ByqAdwtjjgEntity entity : entities){
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
	
	@Override
	public List<List<String>> getByqadwtjjg(Date d, YDJDType yjType,
			Company company) {
		return this.getByqadwtjjg(d, yjType, byqAdwtjjgDao.getByDw(company));
	}
}
