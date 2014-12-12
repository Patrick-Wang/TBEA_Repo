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

		List<HKJHJG> hkjhjgs = hkjhjgDao.getHkjhjg(d, comp);
		List<YDSJHKQK> ydsjhkqks = hkjhzxqkDao.getSjhkqk(d, comp);

		HKJHJG hkjhjg = null;
		YDSJHKQK ydsjhkqk = null;
		if (!hkjhjgs.isEmpty()) {
			hkjhjg = hkjhjgs.get(0);
			ret[0][0] = hkjhjg.getByhlwdqyszk() + "";
			ret[1][0] = hkjhjg.getByhlyqyszk() + "";
			ret[2][0] = hkjhjg.getByhlwdqk() + "";
			ret[3][0] = hkjhjg.getByhlyqk() + "";
			ret[4][0] = hkjhjg.getQbkhxj() + "";
			ret[5][0] = hkjhjg.getZqkhxj() + "";
			ret[6][0] = hkjhjg.getByhlyqyszk() + hkjhjg.getByhlwdqk()
					+ hkjhjg.getByhlyqk() + hkjhjg.getQbkhxj()
					+ hkjhjg.getZqkhxj() + "";

			ret[9][0] = ret[6][0];

		}
		if (!ydsjhkqks.isEmpty()) {
			ydsjhkqk = ydsjhkqks.get(0);
			ret[0][1] = ydsjhkqk.getWdqyszksjhk() + "";
			ret[1][1] = ydsjhkqk.getYqyszksjhk() + "";
			ret[2][1] = ydsjhkqk.getWdqksjhk() + "";
			ret[3][1] = ydsjhkqk.getYqksjhk() + "";
			ret[4][1] = ydsjhkqk.getQbkhhk() + "";
			ret[5][1] = ydsjhkqk.getZqkhhk() + "";
			ret[6][1] = ydsjhkqk.getWdqyszksjhk() + ydsjhkqk.getYqyszksjhk()
					+ ydsjhkqk.getWdqksjhk() + ydsjhkqk.getYqksjhk()
					+ ydsjhkqk.getQbkhhk() + ydsjhkqk.getZqkhhk() + "";

			ret[7][1] = ydsjhkqk.getXkxhhk() + "";
			ret[8][1] = ydsjhkqk.getJhwhk() + "";
			ret[9][1] = ydsjhkqk.getXkxhhk() + ydsjhkqk.getJhwhk()
					+ ydsjhkqk.getWdqyszksjhk() + ydsjhkqk.getYqyszksjhk()
					+ ydsjhkqk.getWdqksjhk() + ydsjhkqk.getYqksjhk()
					+ ydsjhkqk.getQbkhhk() + ydsjhkqk.getZqkhhk() + "";
		}

		if (hkjhjg != null && null != ydsjhkqk) {
			ret[0][2] = div(ydsjhkqk.getWdqyszksjhk(), hkjhjg.getByhlwdqyszk());
			ret[1][2] = div(ydsjhkqk.getYqyszksjhk(), hkjhjg.getByhlyqyszk());
			ret[2][2] = div(ydsjhkqk.getWdqksjhk(), hkjhjg.getByhlwdqk());
			ret[3][2] = div(ydsjhkqk.getYqksjhk(), hkjhjg.getByhlyqk());
			ret[4][2] = div(ydsjhkqk.getQbkhhk(), hkjhjg.getQbkhxj());
			ret[5][2] = div(ydsjhkqk.getZqkhhk(), hkjhjg.getZqkhxj());
			ret[6][2] = div(
					ydsjhkqk.getWdqyszksjhk() + ydsjhkqk.getYqyszksjhk()
							+ ydsjhkqk.getWdqksjhk() + ydsjhkqk.getYqksjhk()
							+ ydsjhkqk.getQbkhhk() + ydsjhkqk.getZqkhhk(),
					hkjhjg.getByhlyqyszk() + hkjhjg.getByhlwdqk()
							+ hkjhjg.getByhlyqk() + hkjhjg.getQbkhxj()
							+ hkjhjg.getZqkhxj());

			ret[7][2] = "0";
			ret[8][2] = "0";
			ret[9][2] = div(
					ydsjhkqk.getXkxhhk() + ydsjhkqk.getJhwhk()
							+ ydsjhkqk.getWdqyszksjhk()
							+ ydsjhkqk.getYqyszksjhk() + ydsjhkqk.getWdqksjhk()
							+ ydsjhkqk.getYqksjhk() + ydsjhkqk.getQbkhhk()
							+ ydsjhkqk.getZqkhhk(), hkjhjg.getByhlyqyszk()
							+ hkjhjg.getByhlwdqk() + hkjhjg.getByhlyqk()
							+ hkjhjg.getQbkhxj() + hkjhjg.getZqkhxj());
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
