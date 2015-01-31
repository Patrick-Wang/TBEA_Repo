package com.tbea.datatransfer.service.inner.ydhkjhzxqk;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.inner.ydhkjhzxqk.YDHKJHZXQKDao;
import com.tbea.datatransfer.model.dao.local.ydhkjhjgb.YDHKJHJGBLocalDao;
import com.tbea.datatransfer.model.dao.local.ydsjhkqk.YDSJHKQKLocalDao;
import com.tbea.datatransfer.model.entity.inner.YDHKJHZXQK;
import com.tbea.datatransfer.model.entity.local.YDHKJHJGBLocal;
import com.tbea.datatransfer.model.entity.local.YDSJHKQKLocal;

@Transactional("transactionManager")
public class YDHKJHZXQKServiceImpl implements YDHKJHZXQKService {

	private YDHKJHZXQKDao ydhkjhzxqkDao;

	private YDHKJHJGBLocalDao ydhkjhjgbLocalDao;

	private YDSJHKQKLocalDao ydsjhkqkLocalDao;

	@Override
	public boolean importYDHKJHZXQK() {
		boolean result = false;
		try {
			YDHKJHZXQK ydhkjhzxqk = null;
			List<YDSJHKQKLocal> ydsjhkqkLocalList = ydsjhkqkLocalDao
					.getAllYDSJHKQKLocal();
			YDHKJHJGBLocal ydjhhkqkLocal = null;

			SimpleDateFormat nyFormat = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String ny = null;
			String date = null;
			Date gxrq = null;
			Double yqyszksjhk = null;
			Double yqksjhk = null;
			Double wdqyszksjhk = null;
			Double wdqksjhk = null;
			Double qbkhsjhk = null;
			Double zqkhsjhk = null;
			Double xkxhhk = null;
			Double jhwhk = null;
			Double yqyszkjhhk = null;
			Double yqkjhhk = null;
			Double wdqyszkjhhk = null;
			Double wdqkjhhk = null;
			Double qbkhjhhk = null;
			Double zqkhjhhk = null;
			Double jhxj = null;
			Double sjxj = null;
			Double jhhj = null;
			Double sjhj = null;
			String yqyszkjhwcl = null;
			String yqkjhwcl = null;
			String wdqyszkjhwcl = null;
			String wdqkjhwcl = null;
			String qbkhjhwcl = null;
			String zqkhjhwcl = null;
			String xjwcl = null;
			String hjwcl = null;
			Integer qybh = null;
			String gsbm = null;

			for (YDSJHKQKLocal ydsjhkqkLocal : ydsjhkqkLocalList) {
				ydhkjhzxqk = new YDHKJHZXQK();
				gxrq = ydsjhkqkLocal.getGxrq();
				ny = nyFormat.format(gxrq);
				ydhkjhzxqk.setNy(ny);

				// sjhk
				yqyszksjhk = CommonMethod.objectToDouble(ydsjhkqkLocal
						.getYqyszksjhk());
				ydhkjhzxqk.setYqyszksjhk(yqyszksjhk);
				yqksjhk = CommonMethod.objectToDouble(ydsjhkqkLocal
						.getYqksjhk());
				ydhkjhzxqk.setYqksjhk(yqksjhk);
				wdqyszksjhk = CommonMethod.objectToDouble(ydsjhkqkLocal
						.getWdqyszksjhk());
				ydhkjhzxqk.setWdqyszksjhk(wdqyszksjhk);
				wdqksjhk = CommonMethod.objectToDouble(ydsjhkqkLocal
						.getWdqksjhk());
				ydhkjhzxqk.setWdqksjhk(wdqksjhk);
				qbkhsjhk = CommonMethod.objectToDouble(ydsjhkqkLocal
						.getQbkhhk());
				ydhkjhzxqk.setQbkhsjhk(qbkhsjhk);
				zqkhsjhk = CommonMethod.objectToDouble(ydsjhkqkLocal
						.getZqkhhk());
				ydhkjhzxqk.setZqkhsjhk(zqkhsjhk);
				xkxhhk = CommonMethod.objectToDouble(ydsjhkqkLocal.getXkxhhk());
				ydhkjhzxqk.setXkxhhk(xkxhhk);
				jhwhk = CommonMethod.objectToDouble(ydsjhkqkLocal.getJhwhk());
				ydhkjhzxqk.setJhwhk(jhwhk);

				// jhhk
				qybh = ydsjhkqkLocal.getQybh();
				gsbm = ydsjhkqkLocal.getGsbm();
				date = dateFormat.format(gxrq);
				ydjhhkqkLocal = ydhkjhjgbLocalDao.getHKJHByQY(date, qybh, gsbm);
				if (null != ydjhhkqkLocal) {
					yqyszkjhhk = CommonMethod.objectToDouble(ydjhhkqkLocal
							.getQbkhyqyszk())
							+ CommonMethod.objectToDouble(ydjhhkqkLocal
									.getZqkhyqyszk());
					ydhkjhzxqk.setYqyszkjhhk(yqyszkjhhk);
					yqkjhhk = CommonMethod.objectToDouble(ydjhhkqkLocal
							.getQbkhyqk())
							+ CommonMethod.objectToDouble(ydjhhkqkLocal
									.getZqkhyqk());
					ydhkjhzxqk.setYqkjhhk(yqkjhhk);
					wdqyszkjhhk = CommonMethod.objectToDouble(ydjhhkqkLocal
							.getQbkhwdqyszk())
							+ CommonMethod.objectToDouble(ydjhhkqkLocal
									.getZqkhwdqyszk());
					ydhkjhzxqk.setWdqyszkjhhk(wdqyszkjhhk);
					wdqkjhhk = CommonMethod.objectToDouble(ydjhhkqkLocal
							.getQbkhwdqk())
							+ CommonMethod.objectToDouble(ydjhhkqkLocal
									.getZqkhwdqk());
					ydhkjhzxqk.setWdqkjhhk(wdqkjhhk);
					qbkhjhhk = CommonMethod.objectToDouble(ydjhhkqkLocal
							.getQbkhyqyszk())
							+ CommonMethod.objectToDouble(ydjhhkqkLocal
									.getQbkhyqk())
							+ CommonMethod.objectToDouble(ydjhhkqkLocal
									.getQbkhwdqyszk())
							+ CommonMethod.objectToDouble(ydjhhkqkLocal
									.getQbkhwdqk());
					ydhkjhzxqk.setQbkhjhhk(qbkhjhhk);
					zqkhjhhk = CommonMethod.objectToDouble(ydjhhkqkLocal
							.getZqkhyqyszk())
							+ CommonMethod.objectToDouble(ydjhhkqkLocal
									.getZqkhyqk())
							+ CommonMethod.objectToDouble(ydjhhkqkLocal
									.getZqkhwdqyszk())
							+ CommonMethod.objectToDouble(ydjhhkqkLocal
									.getZqkhwdqk());
					ydhkjhzxqk.setZqkhjhhk(zqkhjhhk);
				}

				// xj
				jhxj = qbkhjhhk + zqkhjhhk;
				ydhkjhzxqk.setJhxj(jhxj);
				sjxj = qbkhsjhk + zqkhsjhk;
				ydhkjhzxqk.setSjxj(sjxj);

				// hj
				jhhj = jhxj;
				ydhkjhzxqk.setJhhj(jhhj);
				sjhj = sjxj + xkxhhk + jhwhk;
				ydhkjhzxqk.setSjhj(sjhj);

				// wcl
				Double zero = 0.0D;
				yqyszkjhwcl = zero.equals(yqyszkjhhk) ? "-" : (String.format(
						"%.2f", yqyszksjhk / yqyszkjhhk) + "%");
				ydhkjhzxqk.setYqyszkjhwcl(yqyszkjhwcl);
				yqkjhwcl = zero.equals(yqkjhhk) ? "-" : (String.format("%.2f",
						yqksjhk / yqkjhhk) + "%");
				ydhkjhzxqk.setYqkjhwcl(yqkjhwcl);
				wdqyszkjhwcl = zero.equals(wdqyszkjhhk) ? "-" : (String.format(
						"%.2f", wdqyszksjhk / wdqyszkjhhk) + "%");
				ydhkjhzxqk.setWdqyszkjhwcl(wdqyszkjhwcl);
				wdqkjhwcl = zero.equals(wdqkjhhk) ? "-" : (String.format(
						"%.2f", wdqksjhk / wdqkjhhk) + "%");
				ydhkjhzxqk.setWdqkjhwcl(wdqkjhwcl);
				qbkhjhwcl = zero.equals(qbkhjhhk) ? "-" : (String.format(
						"%.2f", qbkhsjhk / qbkhjhhk) + "%");
				ydhkjhzxqk.setQbkhjhwcl(qbkhjhwcl);
				zqkhjhwcl = zero.equals(zqkhjhhk) ? "-" : (String.format(
						"%.2f", zqkhsjhk / zqkhjhhk) + "%");
				ydhkjhzxqk.setZqkhjhwcl(zqkhjhwcl);
				xjwcl = zero.equals(jhxj) ? "-" : (String.format("%.2f", sjxj
						/ jhxj) + "%");
				ydhkjhzxqk.setXjwcl(xjwcl);
				hjwcl = zero.equals(jhhj) ? "-" : (String.format("%.2f", sjhj
						/ jhhj) + "%");
				ydhkjhzxqk.setHjwcl(hjwcl);

				ydhkjhzxqk.setQybh(qybh);
				ydhkjhzxqkDao.merge(ydhkjhzxqk);
			}
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public YDHKJHZXQKDao getYdhkjhzxqkDao() {
		return ydhkjhzxqkDao;
	}

	public void setYdhkjhzxqkDao(YDHKJHZXQKDao ydhkjhzxqkDao) {
		this.ydhkjhzxqkDao = ydhkjhzxqkDao;
	}

	public YDHKJHJGBLocalDao getYdhkjhjgbLocalDao() {
		return ydhkjhjgbLocalDao;
	}

	public void setYdhkjhjgbLocalDao(YDHKJHJGBLocalDao ydhkjhjgbLocalDao) {
		this.ydhkjhjgbLocalDao = ydhkjhjgbLocalDao;
	}

	public YDSJHKQKLocalDao getYdsjhkqkLocalDao() {
		return ydsjhkqkLocalDao;
	}

	public void setYdsjhkqkLocalDao(YDSJHKQKLocalDao ydsjhkqkLocalDao) {
		this.ydsjhkqkLocalDao = ydsjhkqkLocalDao;
	}

}
