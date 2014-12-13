package com.tbea.test.testWebProject.service.syhkjhzxqk;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.dao.hkjhjgb.HKJHJGDao;
import com.tbea.test.testWebProject.model.dao.hkjhzxqk.HKJHZXQKDao;
import com.tbea.test.testWebProject.model.entity.HKJHJG;
import com.tbea.test.testWebProject.model.entity.YDHKJHJG;
import com.tbea.test.testWebProject.model.entity.YDSJHKQK;

@Service
@Transactional("transactionManager")
public class SYHKJHZXQKServiceImpl implements SYHKJHZXQKService {

	@Autowired
	private HKJHZXQKDao hkjhzxqkDao;

	@Autowired
	private HKJHJGDao hkjhjgDao;

	private String div(Double sub, Double base) {
		if (base != 0) {
			return sub / base + "";
		}
		return "--";
	}

	public String[][] getSyhkjhzxqkData(Date d, Company comp) {
		String[][] ret = new String[10][3];

		//List<HKJHJG> hkjhjgs = hkjhjgDao.getHkjhjg(d, comp);
		List<YDSJHKQK> ydsjhkqks = hkjhzxqkDao.getSjhkqk(d, comp);

		HKJHJG hkjhjg = null;
		YDSJHKQK ydsjhkqk = null;
		if (!ydsjhkqks.isEmpty()) {
			ydsjhkqk = ydsjhkqks.get(0);
			ret[0][0] = ydsjhkqk.getWdqyszkjhhk() + "";
			ret[1][0] = ydsjhkqk.getYqyszkjhhk() + "";
			ret[2][0] = ydsjhkqk.getWdqkjhhk() + "";
			ret[3][0] = ydsjhkqk.getYqkjhhk() + "";
			ret[4][0] = ydsjhkqk.getQbkhjhhk() + "";
			ret[5][0] = ydsjhkqk.getQbkhjhhk() + "";
			ret[6][0] = ydsjhkqk.getJhxj() + "";

			ret[9][0] = ydsjhkqk.getJhhj() + "";
			
			
			ret[0][1] = ydsjhkqk.getWdqyszksjhk() + "";
			ret[1][1] = ydsjhkqk.getYqyszksjhk() + "";
			ret[2][1] = ydsjhkqk.getWdqksjhk() + "";
			ret[3][1] = ydsjhkqk.getYqksjhk() + "";
			ret[4][1] = ydsjhkqk.getQbkhsjhk() + "";
			ret[5][1] = ydsjhkqk.getQbkhsjhk() + "";
			ret[6][1] = ydsjhkqk.getSjxj() + "";

			ret[9][1] = ydsjhkqk.getSjhj() + "";
			
			
			
			ret[0][2] = ydsjhkqk.getWdqyszkjhwcl() + "";
			ret[1][2] = ydsjhkqk.getYqyszkjhwcl() + "";
			ret[2][2] = ydsjhkqk.getWdqkjhwcl() + "";
			ret[3][2] = ydsjhkqk.getYqkjhwcl() + "";
			ret[4][2] = ydsjhkqk.getQbkhjhwcl() + "";
			ret[5][2] = ydsjhkqk.getQbkhjhwcl() + "";
			ret[6][2] = ydsjhkqk.getXjwcl() + "";

			ret[9][2] = ydsjhkqk.getHjwcl() + "";
			
		}
	
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

}
