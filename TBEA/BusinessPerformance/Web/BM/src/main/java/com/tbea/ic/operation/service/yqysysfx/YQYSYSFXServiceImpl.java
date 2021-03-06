package com.tbea.ic.operation.service.yqysysfx;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.yqysysfx.YQYSYSFXDao;
import com.tbea.ic.operation.model.entity.YQYSYSFX;


@Service
@Transactional("transactionManager")
public class YQYSYSFXServiceImpl implements YQYSYSFXService{

	@Autowired
	private YQYSYSFXDao ysysfxDao;
	
	private static Map<String, Integer> ysMap = new HashMap<String, Integer>();
	static {
		ysMap.put("1", 0);
		ysMap.put("2", 1);
		ysMap.put("3", 2);
		ysMap.put("4", 3);
		ysMap.put("5", 4);
		ysMap.put("6", 5);
		ysMap.put("7", 6);

	}

	private void setYqysysfx(String[][] result, List<YQYSYSFX> list,
			List<Company> comp) {
		for (YQYSYSFX ysys : list) {
			if (ysys != null) {
				try{
					result[0][ysMap.get(ysys.getYsflmc())] = Util
							.toInt(result[0][ysMap.get(ysys.getYsflmc())])
							+ Util.valueOf(ysys.getZhs()) + "";
					result[1][ysMap.get(ysys.getYsflmc())] = Util
							.toDouble(result[1][ysMap.get(ysys.getYsflmc())])
							+ Util.valueOf(ysys.getZje()) + "";
					result[2][ysMap.get(ysys.getYsflmc())] = Util
							.toInt(result[2][ysMap.get(ysys.getYsflmc())])
							+ Util.valueOf(ysys.getFlqshs()) + "";
					result[3][ysMap.get(ysys.getYsflmc())] = Util
							.toDouble(result[3][ysMap.get(ysys.getYsflmc())])
							+ Util.valueOf(ysys.getFlqsje()) + "";
				} 
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	public String[][] getYqysysfxData(Date d, Company comp){
		String[][] result = new String[4][7];
		List<YQYSYSFX> list = ysysfxDao.getYqysysfxList(d, comp);
		List<Company> comps = new ArrayList<Company>();
		comps.add(comp);
		setYqysysfx(result, list, comps);
		return result;
	}

	@Override
	public String[][] getYqysysfxData(Date d, List<Company> comps) {
		String[][] result = new String[4][7];
		List<YQYSYSFX> list = ysysfxDao.getYqysysfxList(d, comps);
		setYqysysfx(result, list, comps);
		return result;
	}

}
