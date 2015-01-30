package com.tbea.datatransfer.service.inner.ydhkjhzxqk;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

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
				yqyszksjhk = ydsjhkqkLocal.getYqyszksjhk();
				ydhkjhzxqk.setYqyszksjhk(yqyszksjhk);
				yqksjhk = ydsjhkqkLocal.getYqksjhk();
				ydhkjhzxqk.setYqksjhk(yqksjhk);
				wdqyszksjhk = ydsjhkqkLocal.getWdqyszksjhk();
				ydhkjhzxqk.setWdqyszksjhk(wdqyszksjhk);
				wdqksjhk = ydsjhkqkLocal.getWdqksjhk();
				ydhkjhzxqk.setWdqksjhk(wdqksjhk);
				qbkhsjhk = ydsjhkqkLocal.getQbkhhk();
				ydhkjhzxqk.setQbkhsjhk(qbkhsjhk);
				zqkhsjhk = ydsjhkqkLocal.getZqkhhk();
				ydhkjhzxqk.setZqkhsjhk(zqkhsjhk);
				xkxhhk = ydsjhkqkLocal.getXkxhhk();
				ydhkjhzxqk.setXkxhhk(xkxhhk);
				jhwhk = ydsjhkqkLocal.getJhwhk();
				ydhkjhzxqk.setJhwhk(jhwhk);

				// jhhk
				qybh = ydsjhkqkLocal.getQybh();
				gsbm = ydsjhkqkLocal.getGsbm();
				date = dateFormat.format(gxrq);
				ydjhhkqkLocal = ydhkjhjgbLocalDao.getHKJHByQY(date, qybh, gsbm);
				if (null != ydjhhkqkLocal) {
					yqyszkjhhk = ydjhhkqkLocal.getQbkhyqyszk()
							+ ydjhhkqkLocal.getZqkhyqyszk();
					ydhkjhzxqk.setYqyszkjhhk(yqyszkjhhk);
					yqkjhhk = ydjhhkqkLocal.getQbkhyqk()
							+ ydjhhkqkLocal.getZqkhyqk();
					ydhkjhzxqk.setYqkjhhk(yqkjhhk);
					wdqyszkjhhk = ydjhhkqkLocal.getQbkhwdqyszk()
							+ ydjhhkqkLocal.getZqkhwdqyszk();
					ydhkjhzxqk.setWdqyszkjhhk(wdqyszkjhhk);
					wdqkjhhk = ydjhhkqkLocal.getQbkhwdqk()
							+ ydjhhkqkLocal.getZqkhwdqk();
					ydhkjhzxqk.setWdqkjhhk(wdqkjhhk);
					qbkhjhhk = ydjhhkqkLocal.getQbkhyqyszk()
							+ ydjhhkqkLocal.getQbkhyqk()
							+ ydjhhkqkLocal.getQbkhwdqyszk()
							+ ydjhhkqkLocal.getQbkhwdqk();
					ydhkjhzxqk.setQbkhjhhk(qbkhjhhk);
					zqkhjhhk = ydjhhkqkLocal.getZqkhyqyszk()
							+ ydjhhkqkLocal.getZqkhyqk()
							+ ydjhhkqkLocal.getZqkhwdqyszk()
							+ ydjhhkqkLocal.getZqkhwdqk();
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
				yqyszkjhwcl = String.format("%.2f", yqyszksjhk / yqyszkjhhk)
						+ "%";
				ydhkjhzxqk.setYqyszkjhwcl(yqyszkjhwcl);
				yqkjhwcl = String.format("%.2f", yqksjhk / yqkjhhk) + "%";
				ydhkjhzxqk.setYqkjhwcl(yqkjhwcl);
				wdqyszkjhwcl = String.format("%.2f", wdqyszksjhk / wdqyszkjhhk)
						+ "%";
				ydhkjhzxqk.setWdqyszkjhwcl(wdqyszkjhwcl);
				wdqkjhwcl = String.format("%.2f", wdqksjhk / wdqkjhhk) + "%";
				ydhkjhzxqk.setWdqkjhwcl(wdqkjhwcl);
				qbkhjhwcl = String.format("%.2f", qbkhsjhk / qbkhjhhk) + "%";
				ydhkjhzxqk.setQbkhjhwcl(qbkhjhwcl);
				zqkhjhwcl = String.format("%.2f", zqkhsjhk / zqkhjhhk) + "%";
				ydhkjhzxqk.setZqkhjhwcl(zqkhjhwcl);
				xjwcl = String.format("%.2f", sjxj / jhxj) + "%";
				ydhkjhzxqk.setXjwcl(xjwcl);
				hjwcl = String.format("%.2f", sjhj / jhhj) + "%";
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
