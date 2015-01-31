package com.tbea.ic.operation.service.yqysysfx;

import java.util.ArrayList;
import java.util.List;

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
	

	private void setYqysysfx(String[][] result, List<YQYSYSFX> list,
			List<Company> comp) {
		int col = 0;
		for (YQYSYSFX ysys : list) {
			col = ysys.getYsfl();
			result[0][col - 1] = Util.toInt(result[0][col - 1])
					+ Util.valueOf(ysys.getHs()) + "";
			result[1][col - 1] = Util.toDouble(result[1][col - 1])
					+ Util.valueOf(ysys.getJe()) + "";
			result[2][col - 1] = Util.toInt(result[2][col - 1])
					+ Util.valueOf(ysys.getFlsdhs()) + "";
			result[3][col - 1] = Util.toDouble(result[3][col - 1])
					+ Util.valueOf(ysys.getFlsdje()) + "";
		}
	}
	
	public String[][] getYqysysfxData(Company comp){
		String[][] result = new String[4][7];
		List<YQYSYSFX> list = ysysfxDao.getYqysysfxList(comp);
		List<Company> comps = new ArrayList<Company>();
		comps.add(comp);
		setYqysysfx(result, list, comps);
		return result;
	}

	@Override
	public String[][] getYqysysfxData(List<Company> comps) {
		String[][] result = new String[4][7];
		List<YQYSYSFX> list = ysysfxDao.getYqysysfxList(comps);
		setYqysysfx(result, list, comps);
		return result;
	}

}
