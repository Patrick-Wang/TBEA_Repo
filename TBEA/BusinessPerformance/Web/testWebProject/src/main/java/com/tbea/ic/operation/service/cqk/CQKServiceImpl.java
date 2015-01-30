package com.tbea.ic.operation.service.cqk;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cqk.CQKDao;
import com.tbea.ic.operation.model.entity.local.CQK;

@Service
@Transactional("transactionManager")
public class CQKServiceImpl implements CQKService {

	@Autowired
	private CQKDao cqkDao;

	private static Map<String, Integer> hyMap = new HashMap<String, Integer>();
	static {
		hyMap.put("国网、南网", 0);
		hyMap.put("省、市电力系统", 1);
		hyMap.put("五大发电", 2);
		hyMap.put("其他电源", 3);
		hyMap.put("石油石化", 4);
		hyMap.put("轨道交通", 5);
		hyMap.put("出口合同", 6);
		hyMap.put("其他", 7);
		hyMap.put("合计", 8);
	}

	// return value format according to cqk
	// hy one
	// [... current year data...]
	// hy two
	// [... current year data...]
	public void setCqkData(String[][] result, List<CQK> list) {
		for (CQK cqk : list) {
			if (cqk != null && hyMap.get(cqk.getHy()) != null) {
				result[hyMap.get(cqk.getHy()).intValue()][0] = Util
						.toDouble(result[hyMap.get(cqk.getHy()).intValue()][0])
						+ Util.valueOf(cqk.getCqk4njzq()) + "";
				
				result[hyMap.get(cqk.getHy()).intValue()][1] = Util
						.toDouble(result[hyMap.get(cqk.getHy()).intValue()][1])
						+ Util.valueOf(cqk.getCqk3n()) + "";

				result[hyMap.get(cqk.getHy()).intValue()][2] = Util
						.toDouble(result[hyMap.get(cqk.getHy()).intValue()][2])
						+ Util.valueOf(cqk.getCqk2n()) + "";

				result[hyMap.get(cqk.getHy()).intValue()][3] = Util
						.toDouble(result[hyMap.get(cqk.getHy()).intValue()][3])
						+ Util.valueOf(cqk.getZj()) + "";
			}
		}

	}

	private void setCompareData(String[][] result, Date d,
			List<CQK> listPreYear, List<CQK> listCurYear) {
		Calendar time = Calendar.getInstance();
		int month = 0;
		for (CQK cqk : listPreYear) {
			if (cqk != null && hyMap.get(cqk.getHy()) != null) {
				time.setTime(Util.valueOf(cqk.getNy()));
				month = time.get(Calendar.MONTH);
				result[0 + hyMap.get(cqk.getHy()).intValue() * 5][month] = Util
						.toDouble(result[0 + hyMap.get(cqk.getHy()).intValue() * 5][month])
						+ Util.valueOf(cqk.getZj()) + "";
			}
		}

		for (CQK cqk : listCurYear) {
			if (cqk != null && hyMap.get(cqk.getHy()) != null) {
				time.setTime(Util.valueOf(cqk.getNy()));
				month = time.get(Calendar.MONTH);
				result[1 + hyMap.get(cqk.getHy()).intValue() * 5][month] = Util
						.toDouble(result[1 + hyMap.get(cqk.getHy()).intValue() * 5][month])
						+ Util.valueOf(cqk.getZj()) + "";
				result[2 + hyMap.get(cqk.getHy()).intValue() * 5][month] = Util
						.toDouble(result[2 + hyMap.get(cqk.getHy()).intValue() * 5][month])
						+ Util.valueOf(cqk.getCqk2n()) + "";
				result[3 + hyMap.get(cqk.getHy()).intValue() * 5][month] = Util
						.toDouble(result[3 + hyMap.get(cqk.getHy()).intValue() * 5][month])
						+ Util.valueOf(cqk.getCqk3n()) + "";
				result[4 + hyMap.get(cqk.getHy()).intValue() * 5][month] = Util
						.toDouble(result[4 + hyMap.get(cqk.getHy()).intValue() * 5][month])
						+ Util.valueOf(cqk.getCqk4njzq()) + "";
			}
		}
	}

	// return value format
	// hy one
	// [... previous year's cqk zj(refer to CQK entity) from January to current
	// month...]
	// [... current year's cqk zj(refer to CQK entity) from January to current
	// month...]
	// [... current year's cqk ln(refer to CQK entity) from January to current
	// month...]
	// [... current year's cqk Sn(refer to CQK entity) from January to current
	// month...]
	// [... current year's cqk Snjzq(refer to CQK entity) from January to
	// current month...]
	// hy two
	// [... previous year's cqk zj(refer to CQK entity) from January to current
	// month...]
	// [... current year's cqk zj(refer to CQK entity) from January to current
	// month...]
	// [... current year's cqk ln(refer to CQK entity) from January to current
	// month...]
	// [... current year's cqk Sn(refer to CQK entity) from January to current
	// month...]
	// [... current year's cqk Snjzq(refer to CQK entity) from January to
	// current month...]
	// ......
	@Override
	public String[][] getCompareData(Date d, Company comp) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String[][] result = new String[5 * hyMap.size()][cal
				.get(Calendar.MONTH) + 1];
		setCompareData(result, d, cqkDao.getPreYearCQK(d, comp),
				cqkDao.getCurYearCQK(d, comp));
		return result;
	}

	@Override
	public String[][] getCompareData(Date d, List<Company> comps) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String[][] result = new String[5 * hyMap.size()][cal
				.get(Calendar.MONTH) + 1];
		setCompareData(result, d, cqkDao.getPreYearCQK(d, comps),
				cqkDao.getCurYearCQK(d, comps));
		return result;
	}

	@Override
	public Date getLatestDate() {
		CQK cqk = cqkDao.getLatestCQK();
		if (null != cqk) {
			return (Date) Util.valueOf(cqk.getNy());
		}
		return null;
	}

	// return value format according to cqk
	// hy one
	// [... current year data...]
	// hy two
	// [... current year data...]
	@Override
	public String[][] getCqkData(Date d, List<Company> comps) {
		List<CQK> list = cqkDao.getCqkData(d, comps);
		String[][] result = new String[hyMap.size()][4];
		setCqkData(result, list);
		return result;
	}

	// return value format according to cqk
	// hy one
	// [... current year data...]
	// hy two
	// [... current year data...]
	@Override
	public String[][] getCqkData(Date d, Company comp) {
		List<CQK> list = cqkDao.getCqkData(d, comp);
		String[][] result = new String[hyMap.size()][4];
		setCqkData(result, list);
		return result;
	}

}
