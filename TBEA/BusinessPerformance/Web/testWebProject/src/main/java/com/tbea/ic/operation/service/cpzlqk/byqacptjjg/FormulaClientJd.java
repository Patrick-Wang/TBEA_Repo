package com.tbea.ic.operation.service.cpzlqk.byqacptjjg;

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
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoCacheProxy;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqJdAcptjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;

class FormulaClientJd implements FormulaClient<Pair<ZltjjgEntity, ZltjjgEntity>>{

	private final ByqacptjjgServiceImpl byqacptjjgServiceImpl;
	protected List<Formula> formulaOrder = new ArrayList<Formula>();
	private Map<Formula, ByqJdAcptjjgEntity> forMap = new HashMap<Formula, ByqJdAcptjjgEntity>();
	private Map<Formula, List<String>> forRowMap = new HashMap<Formula, List<String>>();
	ZltjjgDao tjjgDao;
	Company company;
	Date d;
	
	public List<List<String>> getResult(){
		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i < formulaOrder.size(); ++i){
			result.add(forRowMap.get(formulaOrder.get(i)));
		}
		return result;
	}
	
	public FormulaClientJd(ByqacptjjgServiceImpl byqacptjjgServiceImpl,
			ZltjjgDao tjjgDao, Company company, Date d) {
		super();
		this.byqacptjjgServiceImpl = byqacptjjgServiceImpl;
		this.tjjgDao = new ZltjjgDaoCacheProxy(tjjgDao,company.getId());
		this.company = company;
		this.d = d;
	}


	private Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> getDjTq(Formula formula){
		ZltjjgEntity tj1 = tjjgDao.getJdAcc(d, forMap.get(formula).getCpxl().getId(), company);
		ZltjjgEntity tj2 = tjjgDao.getJdAccQntq(d, forMap.get(formula).getCpxl().getId(), company);

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
		this.byqacptjjgServiceImpl.setRow(r, forMap.get(formula), pair.getSecond().getFirst(), pair.getSecond().getSecond());
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
					formula.setParameter(id, 0, MathUtil.toDouble(pair.getFirst().getBhgs()));
					formula.setParameter(id, 1, MathUtil.toDouble(pair.getFirst().getZs()));
				}
				if (null != pair.getSecond()){
					formula.setParameter(id, 2, MathUtil.toDouble(pair.getSecond().getBhgs()));
					formula.setParameter(id, 3, MathUtil.toDouble(pair.getSecond().getZs()));
				}
			}
		}
		
		ZltjjgEntity dj = new ZltjjgEntity();
		dj.setBhgs(MathUtil.toInteger(formula.compute(0)));
		dj.setZs(MathUtil.toInteger(formula.compute(1)));
		ZltjjgEntity tq = new ZltjjgEntity();
		tq.setBhgs(MathUtil.toInteger(formula.compute(2)));
		tq.setZs(MathUtil.toInteger(formula.compute(3)));
		this.byqacptjjgServiceImpl.setRow(r, forMap.get(formula), dj, tq);
		
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

	public void add(Formula formula, ByqJdAcptjjgEntity entity) {
		forMap.put(formula, entity);
	}

}