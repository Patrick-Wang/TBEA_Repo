package com.tbea.ic.operation.service.cpzlqk.pdacptjjg;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.Formula;
import com.tbea.ic.operation.common.FormulaClient;
import com.tbea.ic.operation.common.FormulaServer;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Pair;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoCacheProxy;
import com.tbea.ic.operation.model.entity.cpzlqk.PdYdAcptjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;

class FormulaClientYd implements FormulaClient<Pair<ZltjjgEntity, ZltjjgEntity>>{

	private final PdacptjjgServiceImpl pdacptjjgServiceImpl;
	protected List<Formula> formulaOrder = new ArrayList<Formula>();
	private Map<Formula, PdYdAcptjjgEntity> forMap = new HashMap<Formula, PdYdAcptjjgEntity>();
	private Map<Formula, List<String>> forRowMap = new HashMap<Formula, List<String>>();
	ZltjjgDao tjjgDao;
	Company company;
	Date d;
	List<Integer> ids;
	List<Integer> zts;
	public List<List<String>> getResult(){
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < formulaOrder.size(); ++i){
			result.add(forRowMap.get(formulaOrder.get(i)));
		}
		return result;
	}
	
	public FormulaClientYd(PdacptjjgServiceImpl pdacptjjgServiceImpl,
			ZltjjgDao tjjgDao, Company company, Date d, List<Integer> zts) {
		super();
		this.zts = zts;
		this.pdacptjjgServiceImpl = pdacptjjgServiceImpl;
		this.tjjgDao = new ZltjjgDaoCacheProxy(tjjgDao,company.getId());
		if (company.getType() == CompanyType.BYQCY ||
				company.getType() == CompanyType.XLCY ||
				company.getType() == CompanyType.PDCY){
			ids = new ArrayList<Integer>();
			for (Company comp : company.getSubCompanies()){
				ids.add(comp.getId());
			}
		}
		this.company = company;
		this.d = d;
	}


	private Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> getDjTq(Formula formula){
		ZltjjgEntity tj1 = null;
		ZltjjgEntity tj2 = null;

		if (ids == null){
			tj1 = tjjgDao.getByDate(d, forMap.get(formula).getCpxl().getId(), company, zts);
			tj2 = tjjgDao.getYearAcc(d, forMap.get(formula).getCpxl().getId(), company, zts);
		}else{
			tj1 = tjjgDao.getByDate(d, forMap.get(formula).getCpxl().getId(), ids, zts);
			tj2 = tjjgDao.getYearAcc(d, forMap.get(formula).getCpxl().getId(), ids, zts);
		}
		return new Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>>(
				forMap.get(formula).getId(), 
				new Pair<ZltjjgEntity, ZltjjgEntity>(tj1, tj2));
	}

	@Override
	public Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> onThis(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> pair = this.getDjTq(formula);
		List<String> r = Util.resize(new ArrayList<String>(), 8);
		this.pdacptjjgServiceImpl.setRow(r, forMap.get(formula), pair.getSecond().getFirst(), pair.getSecond().getSecond());
		forRowMap.put(formula, r);
		return pair;
	}


	@Override
	public Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> onNull(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		return this.getDjTq(formula);
	}

	@Override
	public Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> onFormula(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		List<String> r = Util.resize(new ArrayList<String>(), 8);
		forRowMap.put(formula, r);
		Pair<ZltjjgEntity, ZltjjgEntity> pair;
		for (Integer id : formula.getParameters()){
			pair = server.getCache(id);
			if (null != pair){
				if (null != pair.getFirst()){
					formula.setParameter(id, 0,pair.getFirst().getBhgs());
					formula.setParameter(id, 1,pair.getFirst().getZs());
				}
				if (null != pair.getSecond()){
					formula.setParameter(id, 2,pair.getSecond().getBhgs());
					formula.setParameter(id, 3,pair.getSecond().getZs());
				}
			}
		}
		
		ZltjjgEntity dj = new ZltjjgEntity();
		dj.setBhgs(formula.compute(0));
		dj.setZs(formula.compute(1));
		ZltjjgEntity tq = new ZltjjgEntity();
		tq.setBhgs(formula.compute(2));
		tq.setZs(formula.compute(3));
		this.pdacptjjgServiceImpl.setRow(r, forMap.get(formula), dj, tq);
		
		return new Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>>(
				forMap.get(formula).getId(), 
				new Pair<ZltjjgEntity, ZltjjgEntity>(dj, tq));
	}

	@Override
	public Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> onFormulaNoCache(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		List<String> r = Util.resize(new ArrayList<String>(), 8);
		forRowMap.put(formula, r);
		return null;
	}

	@Override
	public void onStart(FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		formulaOrder.add(formula);
	}

	@Override
	public void onComplete(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		if (!forRowMap.containsKey(formula)){
			formulaOrder.remove(formula);
		}
	}

	public void add(Formula formula, PdYdAcptjjgEntity entity) {
		forMap.put(formula, entity);
	}

}