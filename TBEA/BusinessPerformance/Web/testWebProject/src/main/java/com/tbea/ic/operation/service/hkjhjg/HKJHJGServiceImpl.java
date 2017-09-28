package com.tbea.ic.operation.service.hkjhjg;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.hkjhjgb.HKJHJGDao;
import com.tbea.ic.operation.model.entity.HKJHJG;
import com.util.tools.DateUtil;

@Service
@Transactional("transactionManager")
public class HKJHJGServiceImpl implements HKJHJGService {

	@Autowired
	private HKJHJGDao hkjhjgDao;

	
	private void setHkjhjg(String[][] ret, List<HKJHJG> hkjhjgs) {
		for (HKJHJG hkjhjg : hkjhjgs) {
			ret[0][0] = Util.plus(ret[0][0], hkjhjg.getByhlyqyszk() + "");
			ret[0][1] = Util.plus(ret[0][1], hkjhjg.getByhlyqk() + "");
			ret[0][2] = Util.plus(ret[0][2], hkjhjg.getByhlwdqyszk() + "");
			ret[0][3] = Util.plus(ret[0][3], hkjhjg.getByhlwdqk() + "");
			ret[0][4] = Util.plus(ret[0][4], hkjhjg.getByhlxj() + "");

			ret[1][0] = Util.plus(ret[1][0], hkjhjg.getQbkhyqyszk() + "");
			ret[1][1] = Util.plus(ret[1][1], hkjhjg.getQbkhyqk() + "");
			ret[1][2] = Util.plus(ret[1][2], hkjhjg.getQbkhwdqyszk() + "");
			ret[1][3] = Util.plus(ret[1][3], hkjhjg.getQbkhwdqk() + "");
			ret[1][4] = Util.plus(ret[1][4], hkjhjg.getQbkhxj() + "");

			ret[2][0] = Util.plus(ret[2][0], hkjhjg.getZqkhyqyszk() + "");
			ret[2][1] = Util.plus(ret[2][1], hkjhjg.getZqkhyqk() + "");
			ret[2][2] = Util.plus(ret[2][2], hkjhjg.getZqkhwdqyszk() + "");
			ret[2][3] = Util.plus(ret[2][3], hkjhjg.getZqkhwdqk() + "");
			ret[2][4] = Util.plus(ret[2][4], hkjhjg.getZqkhxj() + "");
		}
	}
	
	@Override
	public String[][] getHkjhjgData(Date d, Company comp) {

		String[][] ret = new String[3][5];
		List<HKJHJG> hkjhjgs = hkjhjgDao.getHkjhjg(d, comp);
		setHkjhjg(ret, hkjhjgs);
//		if (!hkjhjgs.isEmpty()) {
//			HKJHJG hkjhjg = hkjhjgs.get(0);
//			ret[0][0] = hkjhjg.getByhlyqyszk() + "";
//			ret[0][1] = hkjhjg.getByhlyqk() + "";
//			ret[0][2] = hkjhjg.getByhlwdqyszk() + "";
//			ret[0][3] = hkjhjg.getByhlwdqk() + "";
//			ret[0][4] = hkjhjg.getByhlxj() + "";
//
//			ret[1][0] = hkjhjg.getQbkhyqyszk() + "";
//			ret[1][1] = hkjhjg.getQbkhyqk() + "";
//			ret[1][2] = hkjhjg.getQbkhwdqyszk() + "";
//			ret[1][3] = hkjhjg.getQbkhwdqk() + "";
//			ret[1][4] = hkjhjg.getQbkhxj() + "";
//
//			ret[2][0] = hkjhjg.getZqkhyqyszk() + "";
//			ret[2][1] = hkjhjg.getZqkhyqk() + "";
//			ret[2][2] = hkjhjg.getZqkhwdqyszk() + "";
//			ret[2][3] = hkjhjg.getZqkhwdqk() + "";
//			ret[2][4] = hkjhjg.getZqkhxj() + "";
//		}

		return ret;
		// new String[][]{
		// {"1.00", "2.00", "3.00", "4.00", "4.00"},
		// {"1.00", "2.00", "3.00", "4.00", "4.00"},
		// {"1.00", "2.00", "3.00", "4.00", "4.00"}
		// };
	}

	
	private void setHkjhzt(String[] ret, List<HKJHJG> hkjhjgs) {
		for (HKJHJG hkjhjg : hkjhjgs) {
			ret[0] = Util.plus(ret[0], hkjhjg.getByhlyqk() + "");
			ret[1] = Util.plus(ret[1], hkjhjg.getByhlwdqk() + "");
			ret[2] = Util.plus(ret[2], hkjhjg.getByhlyqyszk() + "");
			ret[3] = Util.plus(ret[3], hkjhjg.getByhlwdqyszk() + "");
			ret[4] = Util.plus(ret[4], hkjhjg.getByhlxj() + "");
		}
	}
	
	@Override
	public String[] getHkjhztData(Date d, Company comp) {
		String[] ret = new String[5];
		List<HKJHJG> hkjhjgs = hkjhjgDao.getHkjhjg(d, comp);
		setHkjhzt(ret, hkjhjgs);
//		if (!hkjhjgs.isEmpty()) {
//			HKJHJG hkjhjg = hkjhjgs.get(0);
//			ret[0] = hkjhjg.getByhlyqk() + "";
//			ret[1] = hkjhjg.getByhlwdqk() + "";
//			ret[2] = hkjhjg.getByhlyqyszk() + "";
//			ret[3] = hkjhjg.getByhlwdqyszk() + "";
//			ret[4] = hkjhjg.getByhlxj() + "";
//		}
		
		return ret;
		// return new String[]
		// {"1.00", "2.00", "3", "4", "4"};
	}

	
	private void setHkjhxz(String[] ret, List<HKJHJG> hkjhjgs) {
		for (HKJHJG hkjhjg : hkjhjgs) {
			ret[0] = Util.plus(ret[0], hkjhjg.getQbkhxj() + "");
			ret[1] = Util.plus(ret[1], hkjhjg.getZqkhxj() + "");
			ret[2] = Util.plus(ret[2], hkjhjg.getXyqsk() + "");
			ret[3] = Util.plus(ret[3], hkjhjg.getGyqsk() + "");
			ret[4] = Util.plus(ret[4], (hkjhjg.getQbkhxj() + hkjhjg.getZqkhxj()
					+ hkjhjg.getXyqsk() + hkjhjg.getGyqsk())
					+ "");
		}
	}
	
	@Override
	public String[] getHkjhxzData(Date d, Company comp) {
		String[] ret = new String[5];
		List<HKJHJG> hkjhjgs = hkjhjgDao.getHkjhjg(d, comp);
		setHkjhxz(ret, hkjhjgs);
//		if (!hkjhjgs.isEmpty()) {
//			HKJHJG hkjhjg = hkjhjgs.get(0);
//			ret[0] = hkjhjg.getQbkhxj() + "";
//			ret[1] = hkjhjg.getZqkhxj() + "";
//			ret[2] = hkjhjg.getXyqsk() + "";
//			ret[3] = hkjhjg.getGyqsk() + "";
//			ret[4] = (hkjhjg.getQbkhxj() + hkjhjg.getZqkhxj()
//					+ hkjhjg.getXyqsk() + hkjhjg.getGyqsk())
//					+ "";
//		}
//
		return ret;
		// return new String[]
		// {"1.00", "2.00", "3.00", "4.00", "4"};
	}

	@Override
	public Date getLatestDate() {
		HKJHJG hkjg = hkjhjgDao.getLatestHkjg();
		if (null != hkjg){
			return (Date) DateUtil.fromMonth1(hkjg.getNy());
		}
		return null;
	}

	@Override
	public String[][] getHkjhjgData(Date d, List<Company> comps) {
		String[][] ret = new String[3][5];
		List<HKJHJG> hkjhjgs = hkjhjgDao.getHkjhjg(d, comps);
		setHkjhjg(ret, hkjhjgs);
		return ret;
	}

	@Override
	public String[] getHkjhztData(Date d, List<Company> comps) {
		String[] ret = new String[5];
		List<HKJHJG> hkjhjgs = hkjhjgDao.getHkjhjg(d, comps);
		setHkjhzt(ret, hkjhjgs);
		return ret;
	}

	@Override
	public String[] getHkjhxzData(Date d, List<Company> comps) {
		String[] ret = new String[5];
		List<HKJHJG> hkjhjgs = hkjhjgDao.getHkjhjg(d, comps);
		setHkjhxz(ret, hkjhjgs);
		return ret;
	}
}
