package com.tbea.ic.operation.service.cwyjsf.yjsf;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsf.YjsfDao;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsf.YjsfDaoImpl;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsfndqcs.YjsfNdqcsDao;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsfndqcs.YjsfNdqcsDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.cwyjsf.sz.SzDao;
import com.tbea.ic.operation.model.dao.identifier.cwyjsf.sz.SzDaoImpl;
import com.tbea.ic.operation.model.entity.cwyjsf.YjsfEntity;
import com.tbea.ic.operation.model.entity.cwyjsf.YjsfNdqcsEntity;
import com.tbea.ic.operation.model.entity.identifier.cwyjsf.SzEntity;

@Service(YjsfServiceImpl.NAME)
@Transactional("transactionManager")
public class YjsfServiceImpl implements YjsfService {
	@Resource(name=YjsfNdqcsDaoImpl.NAME)
	YjsfNdqcsDao yjsfNdqcsDao;

	@Resource(name=YjsfDaoImpl.NAME)
	YjsfDao yjsfDao;

	@Resource(name=SzDaoImpl.NAME)
	SzDao szDao;
	
	public final static String NAME = "YjsfServiceImpl";

	@Override
	public List<List<String>> getYjsf(Date d, Company company) {
		List<SzEntity> szs = szDao.getAll();
		List<List<String>> result = new ArrayList<List<String>>();
		List<Double> hj = new ArrayList<Double>();
		Util.resize(hj, 2 + 12 * 2);
		List<String> list = null;
		for (SzEntity sz : szs){
			list = new ArrayList<String>();
			result.add(list);
			Util.resize(list, 2 + 12 * 2);
			list.set(0, sz.getName());
			
			YjsfNdqcsEntity ndqcs = yjsfNdqcsDao.getByDate(d, company, sz.getId());
			if (null != ndqcs){
				list.set(1, "" + ndqcs.getQcs());
				hj.set(1, MathUtil.sum(hj.get(1), ndqcs.getQcs()));
			}
			
			List<YjsfEntity> yjsfs = yjsfDao.getByYear(d, company, sz.getId());
			for (YjsfEntity yjsf : yjsfs){
				list.set(1 + yjsf.getYf(), "" + yjsf.getYjs());
				hj.set(1 + yjsf.getYf(), MathUtil.sum(hj.get(1 + yjsf.getYf()), yjsf.getYjs()));
				list.set(1 + 12 + yjsf.getYf(), "" + yjsf.getYijs());
				hj.set(1 + 12 + yjsf.getYf(), MathUtil.sum(hj.get(1 + 12 + yjsf.getYf()), yjsf.getYijs()));
			}
		}
		
		list = Util.toList(hj);
		list.set(0, "合计");
		result.add(list);
		return result;
	}

}
