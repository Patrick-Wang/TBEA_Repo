package com.tbea.ic.operation.service.cwyjsf.yjsfljs;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.util.tools.ListUtil;
import com.util.tools.MathUtil;

@Service(YjsfljsServiceImpl.NAME)
@Transactional("transactionManager")
public class YjsfljsServiceImpl implements YjsfljsService {
	@Resource(name=YjsfNdqcsDaoImpl.NAME)
	YjsfNdqcsDao yjsfNdqcsDao;

	@Resource(name=YjsfDaoImpl.NAME)
	YjsfDao yjsfDao;
	
	@Resource(name=SzDaoImpl.NAME)
	SzDao szDao;
	
	public final static String NAME = "YjsfljsServiceImpl";

	@Override
	public List<List<String>> getYjsfljs(Date d, Company company) {
		List<SzEntity> szs = szDao.getAll();
		List<List<String>> result = new ArrayList<List<String>>();
		List<Double> hj = new ArrayList<Double>();
		ListUtil.resize(hj, 9);
		List<String> list = null;
		
		
		
		for (SzEntity sz : szs){
			
			list = new ArrayList<String>();
			result.add(list);
			YjsfNdqcsEntity ndqcs = yjsfNdqcsDao.getByDate(d, company, sz.getId());
			list.add(sz.getName());
			
			if (null != ndqcs){
				list.add("" + ndqcs.getQcs());
				hj.set(list.size() - 1, MathUtil.sum(hj.get(list.size() - 1), ndqcs.getQcs()));
			}else{
				list.add(null);
			}
			YjsfEntity yjsf = yjsfDao.getByDate(d, company, sz.getId());
			
			if (null != yjsf){
				list.add("" + yjsf.getYjs());
				hj.set(list.size() - 1, MathUtil.sum(hj.get(list.size() - 1), yjsf.getYjs()));
				list.add("" + yjsf.getLjyj());
				hj.set(list.size() - 1, MathUtil.sum(hj.get(list.size() - 1), yjsf.getLjyj()));
				list.add("" + yjsf.getYijs());
				hj.set(list.size() - 1, MathUtil.sum(hj.get(list.size() - 1), yjsf.getYijs()));
				list.add("" + yjsf.getLjyij());
				hj.set(list.size() - 1, MathUtil.sum(hj.get(list.size() - 1), yjsf.getLjyij()));
				list.add("" + yjsf.getWjs());
				hj.set(list.size() - 1, MathUtil.sum(hj.get(list.size() - 1), yjsf.getWjs()));
				list.add("" + yjsf.getLjwj());
				hj.set(list.size() - 1, MathUtil.sum(hj.get(list.size() - 1), yjsf.getLjwj()));
			}else{
				ListUtil.resize(list, 8);
			}
			Double smVal = MathUtil.minus(MathUtil.sum(ndqcs == null ? null
					: ndqcs.getQcs(), yjsf == null ? null : yjsf.getLjyj()),
					yjsf == null ? null : yjsf.getLjyij());
			list.add("" + smVal);
			hj.set(list.size() - 1, smVal);
		}
		
		list = Util.toList(hj);
		list.set(0, "合计");
		result.add(list);
		return result;
	}

}
