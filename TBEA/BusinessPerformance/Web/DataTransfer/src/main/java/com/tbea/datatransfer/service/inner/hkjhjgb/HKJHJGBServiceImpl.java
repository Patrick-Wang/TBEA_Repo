package com.tbea.datatransfer.service.inner.hkjhjgb;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.inner.hkjhjgb.HKJHJGBDao;
import com.tbea.datatransfer.model.dao.local.ydhkjhjgb.YDHKJHJGBLocalDao;
import com.tbea.datatransfer.model.entity.inner.HKJHJGB;
import com.tbea.datatransfer.model.entity.local.YDHKJHJGBLocal;

@Transactional("transactionManager")
public class HKJHJGBServiceImpl implements HKJHJGBService {

	private HKJHJGBDao hkjhjgbDao;

	private YDHKJHJGBLocalDao ydhkjhjgbLocalDao;

	@Override
	public boolean importHKJHJGB() {
		boolean result = false;
		try {
			hkjhjgbDao.truncateHKJHJGB();
			HKJHJGB hkjhjgb = null;
			Date tempDate = null;
			Double qbkhyqyszk = null;
			Double qbkhyqk = null;
			Double qbkhwdqyszk = null;
			Double qbkhwdqk = null;
			Double qbkhxj = null;
			Double zqkhyqyszk = null;
			Double zqkhyqk = null;
			Double zqkhwdqyszk = null;
			Double zqkhwdqk = null;
			Double zqkhxj = null;
			SimpleDateFormat month_sdf = new SimpleDateFormat("yyyyMM");
			List<YDHKJHJGBLocal> ydhkjhjgbLocalList = ydhkjhjgbLocalDao
					.getAllYDHKJHJGBLocal();
			for (YDHKJHJGBLocal ydhkjhjgbLocal : ydhkjhjgbLocalList) {
				hkjhjgb = new HKJHJGB();
				hkjhjgb.setGsbm(ydhkjhjgbLocal.getGsbm());
				tempDate = ydhkjhjgbLocal.getGxrq();
				hkjhjgb.setNy(month_sdf.format(tempDate));
				// qbkh
				qbkhyqyszk = null != ydhkjhjgbLocal.getQbkhyqyszk() ? ydhkjhjgbLocal
						.getQbkhyqyszk() : 0.0D;
				hkjhjgb.setQbkhyqyszk(qbkhyqyszk);
				qbkhyqk = null != ydhkjhjgbLocal.getQbkhyqk() ? ydhkjhjgbLocal
						.getQbkhyqk() : 0.0D;
				hkjhjgb.setQbkhyqk(qbkhyqk);
				qbkhwdqyszk = null != ydhkjhjgbLocal.getQbkhwdqyszk() ? ydhkjhjgbLocal
						.getQbkhwdqyszk() : 0.0D;
				hkjhjgb.setQbkhwdqyszk(qbkhwdqyszk);
				qbkhwdqk = null != ydhkjhjgbLocal.getQbkhwdqk() ? ydhkjhjgbLocal
						.getQbkhwdqk() : 0.0D;
				hkjhjgb.setQbkhwdqk(qbkhwdqk);
				// zqkh
				zqkhyqyszk = null != ydhkjhjgbLocal.getZqkhyqyszk() ? ydhkjhjgbLocal
						.getZqkhyqyszk() : 0.0D;
				hkjhjgb.setZqkhyqyszk(zqkhyqyszk);
				zqkhyqk = null != ydhkjhjgbLocal.getZqkhyqk() ? ydhkjhjgbLocal
						.getZqkhyqk() : 0.0D;
				hkjhjgb.setZqkhyqk(zqkhyqk);
				zqkhwdqyszk = null != ydhkjhjgbLocal.getZqkhwdqyszk() ? ydhkjhjgbLocal
						.getZqkhwdqyszk() : 0.0D;
				hkjhjgb.setZqkhwdqyszk(zqkhwdqyszk);
				zqkhwdqk = null != ydhkjhjgbLocal.getZqkhwdqk() ? ydhkjhjgbLocal
						.getZqkhwdqk() : 0.0D;
				hkjhjgb.setZqkhwdqk(zqkhwdqk);
				// byhl
				hkjhjgb.setByhlyqyszk(qbkhyqyszk + zqkhyqyszk);
				hkjhjgb.setByhlyqk(qbkhyqk + zqkhyqk);
				hkjhjgb.setByhlwdqyszk(qbkhwdqyszk + zqkhwdqyszk);
				hkjhjgb.setByhlwdqk(qbkhwdqk + zqkhwdqk);
				// xj
				qbkhxj = qbkhyqyszk + qbkhyqk + qbkhwdqyszk + qbkhwdqk;
				hkjhjgb.setQbkhxj(qbkhxj);
				zqkhxj = zqkhyqyszk + zqkhyqk + zqkhwdqyszk + zqkhwdqk;
				hkjhjgb.setZqkhxj(zqkhxj);
				hkjhjgb.setByhlxj(qbkhxj + zqkhxj);
				// qsk
				hkjhjgb.setXyqsk(null != ydhkjhjgbLocal.getXyqsk() ? ydhkjhjgbLocal
						.getXyqsk() : 0.0D);
				hkjhjgb.setGyqsk(null != ydhkjhjgbLocal.getGyqsk() ? ydhkjhjgbLocal
						.getGyqsk() : 0.0D);
				hkjhjgb.setQybh(ydhkjhjgbLocal.getQybh());
				hkjhjgbDao.merge(hkjhjgb);
			}
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			System.out.println("importHKJHJGB:" + result);
		}
		return result;
	}

	public HKJHJGBDao getHkjhjgbDao() {
		return hkjhjgbDao;
	}

	public void setHkjhjgbDao(HKJHJGBDao hkjhjgbDao) {
		this.hkjhjgbDao = hkjhjgbDao;
	}

	public YDHKJHJGBLocalDao getYdhkjhjgbLocalDao() {
		return ydhkjhjgbLocalDao;
	}

	public void setYdhkjhjgbLocalDao(YDHKJHJGBLocalDao ydhkjhjgbLocalDao) {
		this.ydhkjhjgbLocalDao = ydhkjhjgbLocalDao;
	}

}
