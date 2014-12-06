package com.tbea.test.testWebProject.service.hkjhjg;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.model.dao.hkjhjgb.HKJHJGDao;
import com.tbea.test.testWebProject.model.entity.HKJHJG;
import com.tbea.test.testWebProject.service.hkjhjg.HKJHJGService;

@Service
@Transactional("transactionManager")
public class HKJHJGServiceImpl implements HKJHJGService {

	@Autowired
	private HKJHJGDao hkjhjgDao;

	@Override
	public String[][] getHkjhjgData(Date d, Company comp) {

		String[][] ret = new String[3][5];
		List<HKJHJG> hkjhjgs = hkjhjgDao.getHkjhjg(d, comp);
		if (!hkjhjgs.isEmpty()) {
			HKJHJG hkjhjg = hkjhjgs.get(0);
			ret[0][0] = hkjhjg.getByhlyqyszk() + "";
			ret[0][1] = hkjhjg.getByhlyqk() + "";
			ret[0][2] = hkjhjg.getByhlwdqyszk() + "";
			ret[0][3] = hkjhjg.getByhlwdqk() + "";
			ret[0][4] = hkjhjg.getByhlxj() + "";

			ret[1][0] = hkjhjg.getQbkhyqyszk() + "";
			ret[1][1] = hkjhjg.getQbkhyqk() + "";
			ret[1][2] = hkjhjg.getQbkhwdqyszk() + "";
			ret[1][3] = hkjhjg.getQbkhwdqk() + "";
			ret[1][4] = hkjhjg.getQbkhxj() + "";

			ret[2][0] = hkjhjg.getZqkhyqyszk() + "";
			ret[2][1] = hkjhjg.getZqkhyqk() + "";
			ret[2][2] = hkjhjg.getZqkhwdqyszk() + "";
			ret[2][3] = hkjhjg.getZqkhwdqk() + "";
			ret[2][4] = hkjhjg.getZqkhxj() + "";
		}

		return ret;
		// new String[][]{
		// {"1.00", "2.00", "3.00", "4.00", "4.00"},
		// {"1.00", "2.00", "3.00", "4.00", "4.00"},
		// {"1.00", "2.00", "3.00", "4.00", "4.00"}
		// };
	}

	@Override
	public String[] getHkjhztData(Date d, Company comp) {
		String[] ret = new String[5];
		List<HKJHJG> hkjhjgs = hkjhjgDao.getHkjhjg(d, comp);
		if (!hkjhjgs.isEmpty()) {
			HKJHJG hkjhjg = hkjhjgs.get(0);
			ret[0] = hkjhjg.getByhlyqk() + "";
			ret[1] = hkjhjg.getByhlwdqk() + "";
			ret[2] = hkjhjg.getByhlyqyszk() + "";
			ret[3] = hkjhjg.getByhlwdqyszk() + "";
			ret[4] = hkjhjg.getByhlxj() + "";
		}

		return ret;
		// return new String[]
		// {"1.00", "2.00", "3", "4", "4"};
	}

	@Override
	public String[] getHkjhxzData(Date d, Company comp) {
		String[] ret = new String[5];
		List<HKJHJG> hkjhjgs = hkjhjgDao.getHkjhjg(d, comp);
		if (!hkjhjgs.isEmpty()) {
			HKJHJG hkjhjg = hkjhjgs.get(0);
			ret[0] = hkjhjg.getQbkhxj() + "";
			ret[1] = hkjhjg.getZqkhxj() + "";
			ret[2] = hkjhjg.getXyqsk() + "";
			ret[3] = hkjhjg.getGyqsk() + "";
			ret[4] = (hkjhjg.getQbkhxj() + hkjhjg.getZqkhxj()
					+ hkjhjg.getXyqsk() + hkjhjg.getGyqsk())
					+ "";
		}

		return ret;
		// return new String[]
		// {"1.00", "2.00", "3.00", "4.00", "4"};
	}
}
