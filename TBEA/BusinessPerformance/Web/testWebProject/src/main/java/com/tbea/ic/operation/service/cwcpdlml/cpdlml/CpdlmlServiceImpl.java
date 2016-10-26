package com.tbea.ic.operation.service.cwcpdlml.cpdlml;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml.CpdlmlDao;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpfl.CpflDao;
import com.tbea.ic.operation.model.dao.cwcpdlml.cpfl.CpflDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cwgb.cy.CyDao;
import com.tbea.ic.operation.model.dao.identifier.cwgb.cy.CyDaoImpl;
import com.tbea.ic.operation.model.entity.cwcpdlml.CpdlmlEntity;
import com.tbea.ic.operation.model.entity.cwcpdlml.CpflEntity;

@Service(CpdlmlServiceImpl.NAME)
@Transactional("transactionManager")
public class CpdlmlServiceImpl implements CpdlmlService {
	@Resource(name=CyDaoImpl.NAME)
	CyDao cyDao;

	@Resource(name=CpflDaoImpl.NAME)
	CpflDao cpflDao;
	
	@Autowired
	CpdlmlDao cpdlmlDao;

	public final static String NAME = "CpdlmlServiceImpl";

	private void compute(List<String> hj, List<List<String>> result, int start, int end){
		for (int i = end; i >= 0; --i){
			List<String> line = result.get(i);
			line.set(3, "" + MathUtil.division(MathUtil.toDouble(line.get(2)), MathUtil.toDouble(hj.get(2))));
			line.set(5, "" + MathUtil.minus(MathUtil.toDouble(line.get(4)), MathUtil.toDouble(line.get(2))));
			line.set(6, "" + MathUtil.division(MathUtil.toDouble(line.get(5)), MathUtil.toDouble(hj.get(5))));
			line.set(7, "" + MathUtil.division(MathUtil.toDouble(line.get(5)), MathUtil.toDouble(line.get(2))));
			line.set(10, "" + MathUtil.division(
					MathUtil.minus(
							MathUtil.toDouble(line.get(8)), 
							MathUtil.toDouble(line.get(9))), 
					MathUtil.toDouble(line.get(8))));
			line.set(11, "" + MathUtil.minus(MathUtil.toDouble(line.get(10)), MathUtil.toDouble(line.get(7))));
		}
	}
	
	@Override
	public List<List<String>> getCpdlml(Date d, List<Company> comps) {
		List<CpflEntity> cpfls = cpflDao.getAll();
		List<List<String>> result = new ArrayList<List<String>>();
//		List<Integer> hjs = new ArrayList<Integer>();
		EasyCalendar ec = new EasyCalendar(d);
		ec.addYear(-1);
		ec.setMonth(12);
		for (int i = 0; i < cpfls.size(); ++i){
			CpflEntity cpflEntity = cpfls.get(i);
			List<String> line = new ArrayList<String>();
			result.add(line);
			Util.resize(line, 12);
			line.set(0, cpflEntity.getCy().getName());
			line.set(1, cpflEntity.getName());		
//			if (cpflEntity.getLx() > 1){
//				hjs.add(i);
//			}
			CpdlmlEntity dlmx = cpdlmlDao.getSumByDate(d, cpflEntity.getId(), comps);
			if (dlmx != null){
				line.set(2, "" + dlmx.getLjsr());
				line.set(4, "" + dlmx.getLjcb());
			}
			dlmx = cpdlmlDao.getSumByDate(ec.getDate(), cpflEntity.getId(), comps);
			if (dlmx != null){
				line.set(8, "" + dlmx.getLjsr());
				line.set(9, "" + dlmx.getLjcb());
			}
		}
//		hjs.add(cpfls.size() - 1);
//		int start = 0;
//		for (int i = 0; i < hjs.size(); ++i){
//			int end = hjs.get(i);
//			compute(result.get(end), result, start, end);
//			start = end + 1;
//		}
//		
		compute(result.get(cpfls.size() - 1), result, 0, cpfls.size() - 1);
		return result;
	}

}
