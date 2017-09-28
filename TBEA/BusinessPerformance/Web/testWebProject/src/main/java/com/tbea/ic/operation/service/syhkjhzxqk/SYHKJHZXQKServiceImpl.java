package com.tbea.ic.operation.service.syhkjhzxqk;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.hkjhjgb.HKJHJGDao;
import com.tbea.ic.operation.model.dao.hkjhzxqk.HKJHZXQKDao;
import com.tbea.ic.operation.model.entity.YDSJHKQK;
import com.util.tools.DateUtil;

@Service
@Transactional("transactionManager")
public class SYHKJHZXQKServiceImpl implements SYHKJHZXQKService {

	@Autowired
	private HKJHZXQKDao hkjhzxqkDao;

	@Autowired
	private HKJHJGDao hkjhjgDao;

	private void setSyhkjhzxqkData(String[][] ret, List<YDSJHKQK> ydsjhkqks) {

		for (YDSJHKQK ydsjhkqk : ydsjhkqks){

			ret[0][0] = Util.plus(ret[0][0], ydsjhkqk.getWdqyszkjhhk() + "");
			ret[1][0] = Util.plus(ret[1][0], ydsjhkqk.getYqyszkjhhk() + "");
			ret[2][0] = Util.plus(ret[2][0], ydsjhkqk.getWdqkjhhk() + "");
			ret[3][0] = Util.plus(ret[3][0], ydsjhkqk.getYqkjhhk() + "");
			ret[4][0] = Util.plus(ret[4][0], ydsjhkqk.getQbkhjhhk() + "");
			ret[5][0] = Util.plus(ret[5][0], ydsjhkqk.getQbkhjhhk() + "");
			ret[6][0] = Util.plus(ret[6][0], ydsjhkqk.getJhxj() + "");

			ret[9][0] = Util.plus(ret[9][0], ydsjhkqk.getJhhj() + "");
			
			
			ret[0][1] = Util.plus(ret[0][1], ydsjhkqk.getWdqyszksjhk() + "");
			ret[1][1] = Util.plus(ret[1][1], ydsjhkqk.getYqyszksjhk() + "");
			ret[2][1] = Util.plus(ret[2][1], ydsjhkqk.getWdqksjhk() + "");
			ret[3][1] = Util.plus(ret[3][1], ydsjhkqk.getYqksjhk() + "");
			ret[4][1] = Util.plus(ret[4][1], ydsjhkqk.getQbkhsjhk() + "");
			ret[5][1] = Util.plus(ret[5][1], ydsjhkqk.getQbkhsjhk() + "");
			ret[6][1] = Util.plus(ret[6][1], ydsjhkqk.getSjxj() + "");

			ret[9][1] = Util.plus(ret[9][1], ydsjhkqk.getSjhj() + "");
			
			
			
			ret[0][2] = Util.division(ret[0][0], ret[0][1]);
			ret[1][2] = Util.division(ret[1][0], ret[1][1]);
			ret[2][2] = Util.division(ret[2][0], ret[2][1]);
			ret[3][2] = Util.division(ret[3][0], ret[3][1]);
			ret[4][2] = Util.division(ret[4][0], ret[4][1]);
			ret[5][2] = Util.division(ret[5][0], ret[5][1]);
			ret[6][2] = Util.division(ret[6][0], ret[6][1]);

			ret[9][2] = Util.division(ret[9][0], ret[9][1]);
			
//			ret[0][2] = ydsjhkqk.getWdqyszkjhwcl() + "";
//			ret[1][2] = ydsjhkqk.getYqyszkjhwcl() + "";
//			ret[2][2] = ydsjhkqk.getWdqkjhwcl() + "";
//			ret[3][2] = ydsjhkqk.getYqkjhwcl() + "";
//			ret[4][2] = ydsjhkqk.getQbkhjhwcl() + "";
//			ret[5][2] = ydsjhkqk.getQbkhjhwcl() + "";
//			ret[6][2] = ydsjhkqk.getXjwcl() + "";
//
//			ret[9][2] = ydsjhkqk.getHjwcl() + "";
			
		}

		// return new String[][]{
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// };
	}

	public String[][] getSyhkjhzxqkData(Date d, Company comp) {
		String[][] ret = new String[10][3];

		List<YDSJHKQK> ydsjhkqks = hkjhzxqkDao.getSjhkqk(d, comp);

		setSyhkjhzxqkData(ret, ydsjhkqks);
		
		
//		YDSJHKQK ydsjhkqk = null;
//		if (!ydsjhkqks.isEmpty()) {
//			ydsjhkqk = ydsjhkqks.get(0);
//			ret[0][0] = ydsjhkqk.getWdqyszkjhhk() + "";
//			ret[1][0] = ydsjhkqk.getYqyszkjhhk() + "";
//			ret[2][0] = ydsjhkqk.getWdqkjhhk() + "";
//			ret[3][0] = ydsjhkqk.getYqkjhhk() + "";
//			ret[4][0] = ydsjhkqk.getQbkhjhhk() + "";
//			ret[5][0] = ydsjhkqk.getQbkhjhhk() + "";
//			ret[6][0] = ydsjhkqk.getJhxj() + "";
//
//			ret[9][0] = ydsjhkqk.getJhhj() + "";
//			
//			
//			ret[0][1] = ydsjhkqk.getWdqyszksjhk() + "";
//			ret[1][1] = ydsjhkqk.getYqyszksjhk() + "";
//			ret[2][1] = ydsjhkqk.getWdqksjhk() + "";
//			ret[3][1] = ydsjhkqk.getYqksjhk() + "";
//			ret[4][1] = ydsjhkqk.getQbkhsjhk() + "";
//			ret[5][1] = ydsjhkqk.getQbkhsjhk() + "";
//			ret[6][1] = ydsjhkqk.getSjxj() + "";
//
//			ret[9][1] = ydsjhkqk.getSjhj() + "";
//			
//			
//			
//			ret[0][2] = ydsjhkqk.getWdqyszkjhwcl() + "";
//			ret[1][2] = ydsjhkqk.getYqyszkjhwcl() + "";
//			ret[2][2] = ydsjhkqk.getWdqkjhwcl() + "";
//			ret[3][2] = ydsjhkqk.getYqkjhwcl() + "";
//			ret[4][2] = ydsjhkqk.getQbkhjhwcl() + "";
//			ret[5][2] = ydsjhkqk.getQbkhjhwcl() + "";
//			ret[6][2] = ydsjhkqk.getXjwcl() + "";
//
//			ret[9][2] = ydsjhkqk.getHjwcl() + "";
//			
//		}
	
		return ret;
		// return new String[][]{
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// {"1", "1", "1"},
		// };
	}

	@Override
	public Date getLatestDate() {
		YDSJHKQK ydsjhk = hkjhzxqkDao.getLatestYdsjhk();
		if (null != ydsjhk){
			return (Date) DateUtil.fromMonth1(ydsjhk.getNy());
		}
		return null;
	}


	public void setHkjhzxqkXj(String[][] ret, Calendar cal, List<YDSJHKQK> ydsjhkqks) {

		int month = 0;
		for (YDSJHKQK ydsjhkqk : ydsjhkqks){
			cal.setTime(DateUtil.fromMonth1(ydsjhkqk.getNy()));
			month = cal.get(Calendar.MONTH);
			ret[month][0] = Util.plus(ret[month][0], ydsjhkqk.getJhhj() + "");
			ret[month][1] = Util.plus(ret[month][1], ydsjhkqk.getSjhj() + "");
			ret[month][2] = Util.division(ret[month][0], ret[month][1]);
		}
	}
	
	
	@Override
	public String[][] getHkjhzxqkXjData(Date d, Company comp) {

		List<YDSJHKQK> ydsjhkqks = hkjhzxqkDao.getHkqkXj(d, comp);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String[][] ret = new String[cal.get(Calendar.MONTH) + 1][3];
		setHkjhzxqkXj(ret, cal, ydsjhkqks);
//		for (YDSJHKQK ydsjhkqk : ydsjhkqks){
//			cal.setTime(Util.valueOf(ydsjhkqk.getNy()));
//			ret[cal.get(Calendar.MONTH)][0] = ydsjhkqk.getJhxj() + "";
//			ret[cal.get(Calendar.MONTH)][1] = ydsjhkqk.getSjxj() + "";
//			ret[cal.get(Calendar.MONTH)][2] = Util.division("100", ydsjhkqk.getXjwcl().replace("%", ""));
//		}

		return ret;
	}

	@Override
	public String[][] getSyhkjhzxqkData(Date d, List<Company> comps) {
		String[][] ret = new String[10][3];
		List<YDSJHKQK> ydsjhkqks = hkjhzxqkDao.getSjhkqk(d, comps);
		setSyhkjhzxqkData(ret, ydsjhkqks);
		return ret;
	}

	@Override
	public String[][] getHkjhzxqkXjData(Date d, List<Company> comps) {
		List<YDSJHKQK> ydsjhkqks = hkjhzxqkDao.getHkqkXj(d, comps);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String[][] ret = new String[cal.get(Calendar.MONTH) + 1][3];
		setHkjhzxqkXj(ret, cal, ydsjhkqks);
		return ret;
	}

}
