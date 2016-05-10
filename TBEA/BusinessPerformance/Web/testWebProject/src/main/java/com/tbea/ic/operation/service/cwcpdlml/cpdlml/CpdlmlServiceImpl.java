package com.tbea.ic.operation.service.cwcpdlml.cpdlml;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Formula;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Pair;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml.CpdlmlDao;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpfl.CpflDao;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpfl.CpflDaoImpl;
import com.tbea.ic.operation.model.dao.cwcpdlml.cydw.CyDwDao;
import com.tbea.ic.operation.model.dao.cwcpdlml.cydw.CyDwDaoImpl;
import com.tbea.ic.operation.model.dao.cwcpdlml.formula.FormulaDao;
import com.tbea.ic.operation.model.dao.identifier.cwgb.cy.CyDao;
import com.tbea.ic.operation.model.dao.identifier.cwgb.cy.CyDaoImpl;
import com.tbea.ic.operation.model.entity.cwcpdlml.CpdlmlEntity;
import com.tbea.ic.operation.model.entity.cwcpdlml.CpflEntity;
import com.tbea.ic.operation.model.entity.cwcpdlml.CyDwEntity;
import com.tbea.ic.operation.model.entity.cwcpdlml.FormulaEntity;

@Service(CpdlmlServiceImpl.NAME)
@Transactional("transactionManager")
public class CpdlmlServiceImpl implements CpdlmlService {
	@Resource(name=CyDaoImpl.NAME)
	CyDao cyDao;

	@Resource(name=CpflDaoImpl.NAME)
	CpflDao cpflDao;

	@Resource(name=CyDwDaoImpl.NAME)
	CyDwDao cyDwDao;
	
	@Autowired
	FormulaDao formulaDao;
	
	@Autowired
	CpdlmlDao cpdlmlDao;

	public final static String NAME = "CpdlmlServiceImpl";

	
	
	@Override
	public List<List<String>> getCpdlml(Date d, Company company) {
		CyDwEntity cydw = cyDwDao.getByDw(company);
		List<CpflEntity> cpfls = cpflDao.getCpflByCy(cydw.getCy().getId());
		List<List<String>> result = new ArrayList<List<String>>();
		List<Pair<Integer, Pair<List<String>, FormulaEntity>>> formulaMap = new ArrayList<Pair<Integer, Pair<List<String>, FormulaEntity>>>();
		Map<Integer, CpdlmlEntity> cpmlCache = new HashMap<Integer, CpdlmlEntity>();
		for (CpflEntity cpflEntity : cpfls){
			List<String> line = new ArrayList<String>();
			result.add(line);
			Util.resize(line, 12);
			line.set(0, cpflEntity.getCy().getName());
			line.set(1, cpflEntity.getName());
			FormulaEntity formula = formulaDao.getByCpfl(cpflEntity.getId());
			if (null != formula){
				formulaMap.add(new Pair(result.size() - 1, new Pair(line, formula)));
			}else{
				CpdlmlEntity dlmx = cpdlmlDao.getByDw(d, company, cpflEntity.getId());
				if (dlmx != null){
					cpmlCache.put(cpflEntity.getId(), dlmx);
					line.set(2, "" + dlmx.getLjsr());
					line.set(4, "" + dlmx.getLjcb());
					line.set(8, "" + dlmx.getQnqnsr());
					line.set(9, "" + dlmx.getQnqncb());
				}
			}
		}
		
		for (int j = 0; j < formulaMap.size(); ++j){
			Formula fromula = new Formula(formulaMap.get(j).getSecond().getSecond().getFormula());
			List<String> line = formulaMap.get(j).getSecond().getFirst();
			List<Integer> cpIds = fromula.getParameters();
			for (int i = 0; i < cpIds.size(); ++i){
				if (cpmlCache.containsKey(cpIds.get(i))){
					fromula.setParameter(cpIds.get(i), cpmlCache.get(cpIds.get(i)).getLjsr());
				}
			}
			line.set(2, "" + fromula.compute());
			for (int i = 0; i < cpIds.size(); ++i){
				if (cpmlCache.containsKey(cpIds.get(i))){
					fromula.setParameter(cpIds.get(i), cpmlCache.get(cpIds.get(i)).getLjcb());
				}
			}
			line.set(4, "" + fromula.compute());
			for (int i = 0; i < cpIds.size(); ++i){
				if (cpmlCache.containsKey(cpIds.get(i))){
					fromula.setParameter(cpIds.get(i), cpmlCache.get(cpIds.get(i)).getQnqnsr());
				}
			}
			line.set(8, "" + fromula.compute());
			for (int i = 0; i < cpIds.size(); ++i){
				if (cpmlCache.containsKey(cpIds.get(i))){
					fromula.setParameter(cpIds.get(i), cpmlCache.get(cpIds.get(i)).getQnqncb());
				}
			}
			line.set(9, "" + fromula.compute());
		}
		
		Pair<Integer, Pair<List<String>, FormulaEntity>> last = formulaMap.get(formulaMap.size() - 1);
		List<String> hj = result.get(last.getFirst());
		for (int i = 0; i < result.size(); ++i){
			List<String> line = result.get(i);
			if (i <= last.getFirst()){
				line.set(3, "" + MathUtil.division(MathUtil.toDouble(line.get(2)), MathUtil.toDouble(hj.get(2))));
				line.set(6, "" + MathUtil.division(MathUtil.toDouble(line.get(5)), MathUtil.toDouble(hj.get(5))));
			}else{
				line.set(3, "" + MathUtil.division(MathUtil.toDouble(line.get(2)), MathUtil.toDouble(line.get(2))));
				line.set(6, "" + MathUtil.division(MathUtil.toDouble(line.get(5)), MathUtil.toDouble(line.get(5))));
			}
			line.set(5, "" + MathUtil.minus(MathUtil.toDouble(line.get(4)), MathUtil.toDouble(line.get(2))));
			line.set(7, "" + MathUtil.division(MathUtil.toDouble(line.get(5)), MathUtil.toDouble(line.get(2))));
			line.set(10, "" + MathUtil.division(
					MathUtil.minus(
							MathUtil.toDouble(line.get(8)), 
							MathUtil.toDouble(line.get(9))), 
					MathUtil.toDouble(line.get(8))));
			line.set(11, "" + MathUtil.minus(MathUtil.toDouble(line.get(10)), MathUtil.toDouble(line.get(7))));
		}
		return result;
	}

}
