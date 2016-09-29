package com.tbea.ic.operation.service.cpzlqk.pdadwtjjg;

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
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoCacheProxy;
import com.tbea.ic.operation.model.entity.cpzlqk.PdAdwtjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;

class FormulaClientJd implements FormulaClient<Pair<ZltjjgEntity, ZltjjgEntity>>{

	private final PdadwtjjgServiceImpl pdadwtjjgServiceImpl;
	protected List<List<String>> result = new ArrayList<List<String>>();
	private Map<Formula, Pair<PdAdwtjjgEntity, Company>> forMap = new HashMap<Formula, Pair<PdAdwtjjgEntity, Company>>();
	ZltjjgDao tjjgDao;
	Date d;
	YDJDType yjType;
	List<Integer> zts;
	List<String> getRow(Formula formula){
		return this.result.get(forIndexMap.get(formula));
	}
	
	private Map<Formula, Integer> forIndexMap = new HashMap<Formula, Integer>();

	public List<List<String>> getResult(){
		return result;
	}
	
	public void add(Formula formula, PdAdwtjjgEntity entity, Company comp) {
		forMap.put(formula, new Pair<PdAdwtjjgEntity, Company>(entity, comp));
	}
	
	public FormulaClientJd(PdadwtjjgServiceImpl pdadwtjjgServiceImpl,
			ZltjjgDao tjjgDao, List<Integer> comps, Date d, YDJDType yjType, List<Integer> zts) {
		super();
		this.yjType = yjType;
		this.zts = zts;
		this.pdadwtjjgServiceImpl = pdadwtjjgServiceImpl;
		this.tjjgDao = new ZltjjgDaoCacheProxy(tjjgDao).setComps(comps);
		this.d = d;
	}
	
	
	private Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> getDjTq(Formula formula){
		Pair<PdAdwtjjgEntity, Company> pair = forMap.get(formula);
		ZltjjgEntity tj1 = null;
		ZltjjgEntity tj2 = null;
		if (pair.getSecond() != null){
			if (this.yjType == YDJDType.YD){
				tj1 = tjjgDao.getByDate(d, pair.getFirst().getCpxl().getId(), pair.getSecond(), this.zts);
				tj2 = tjjgDao.getYearAcc(d, pair.getFirst().getCpxl().getId(), pair.getSecond(), this.zts);
			}else{
				tj1 = tjjgDao.getJdAcc(d, pair.getFirst().getCpxl().getId(), pair.getSecond(), this.zts);
				tj2 = tjjgDao.getJdAccQntq(d, pair.getFirst().getCpxl().getId(), pair.getSecond(), this.zts);
			}
		}

		return new Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>>(
				pair.getFirst().getId(), 
				new Pair<ZltjjgEntity, ZltjjgEntity>(tj1, tj2));
	}
	
	@Override
	public Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> onThis(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> pair = this.getDjTq(formula);
		List<String> r = getRow(formula);
		this.pdadwtjjgServiceImpl.setRow(r, forMap.get(formula).getFirst(), pair.getSecond().getFirst(), pair.getSecond().getSecond());
		return pair;
	}

	@Override
	public Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> onNull(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		this.result.remove(this.forIndexMap.get(formula).intValue());
		return this.getDjTq(formula);
	}

	@Override
	public Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> onFormula(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		List<String> r = this.getRow(formula);
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
		this.pdadwtjjgServiceImpl.setRow(r, forMap.get(formula).getFirst(), dj, tq);
		
		return new Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>>(
				forMap.get(formula).getFirst().getId(), 
				new Pair<ZltjjgEntity, ZltjjgEntity>(dj, tq));
	}

	@Override
	public Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> onFormulaNoCache(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		return null;
	}
	@Override
	public void onStart(FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		List<String> r = Util.resize(new ArrayList<String>(), 8);
		result.add(r);
		forIndexMap.put(formula, result.size() - 1);
	}
	@Override
	public void onComplete(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		forIndexMap.remove(formula);
	}



}