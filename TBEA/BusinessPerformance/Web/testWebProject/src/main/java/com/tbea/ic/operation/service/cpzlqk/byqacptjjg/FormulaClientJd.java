package com.tbea.ic.operation.service.cpzlqk.byqacptjjg;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Formula;
import com.tbea.ic.operation.common.FormulaClient;
import com.tbea.ic.operation.common.FormulaServer;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Pair;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqJdAcptjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;

class FormulaClientJd implements FormulaClient<Pair<ZltjjgEntity, ZltjjgEntity>>{

	private final ByqacptjjgServiceImpl byqacptjjgServiceImpl;
	protected List<String> row = null;
	private ByqJdAcptjjgEntity entity;
	private ZltjjgEntity zltj1;
	private ZltjjgEntity zltj2;
	
	public List<String> getRow(){
		return row;
	}
	
	public FormulaClientJd(ByqacptjjgServiceImpl byqacptjjgServiceImpl, ByqJdAcptjjgEntity entity, ZltjjgEntity zltj1, ZltjjgEntity zltj2){
		this.byqacptjjgServiceImpl = byqacptjjgServiceImpl;
		this.entity = entity;
		this.zltj1 = zltj1;
		this.zltj2 = zltj2;

	}
	
	private Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> getDjTq(){
		return new Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>>(
				entity.getId(), 
				new Pair<ZltjjgEntity, ZltjjgEntity>(zltj1, zltj2));
	}
	
	@Override
	public Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> onThis() {
		Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> pair = this.getDjTq();
		row = new ArrayList<String>();
		Util.resize(row, 8);
		this.byqacptjjgServiceImpl.setRow(row, entity, pair.getSecond().getFirst(), pair.getSecond().getSecond());
		return pair;
	}

	@Override
	public Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> onNull() {
		return this.getDjTq();
	}

	@Override
	public Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> onFormula(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		row = new ArrayList<String>();
		Util.resize(row, 8);
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
		this.byqacptjjgServiceImpl.setRow(row, entity, dj, tq);
		
		return new Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>>(
				entity.getId(), 
				new Pair<ZltjjgEntity, ZltjjgEntity>(dj, tq));
	}

	@Override
	public Pair<Integer, Pair<ZltjjgEntity, ZltjjgEntity>> onFormulaNoCache(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server,
			Formula formula) {
		row = new ArrayList<String>();
		Util.resize(row, 8);
		return null;
	}

	@Override
	public void onStart(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete(
			FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> server) {
	}
}