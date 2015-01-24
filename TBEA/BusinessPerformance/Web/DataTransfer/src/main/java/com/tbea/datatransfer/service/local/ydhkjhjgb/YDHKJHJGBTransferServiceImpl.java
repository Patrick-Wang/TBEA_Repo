package com.tbea.datatransfer.service.local.ydhkjhjgb;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.ydhkjhjgb.YDHKJHJGBLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.ydhkjhjgb.YDHKJHJGBDLDao;
import com.tbea.datatransfer.model.dao.zjsb.ydhkjhjgb.YDHKJHJGBSBDao;
import com.tbea.datatransfer.model.dao.zjtb.ydhkjhjgb.YDHKJHJGBTBDao;
import com.tbea.datatransfer.model.entity.local.YDHKJHJGBLocal;
import com.tbea.datatransfer.model.entity.zjbyq.YDHKJHJGBBYQ;
import com.tbea.datatransfer.model.entity.zjxl.YDHKJHJGBXL;

@Transactional("transactionManager")
public class YDHKJHJGBTransferServiceImpl implements YDHKJHJGBTransferService {

	private YDHKJHJGBLocalDao ydhkjhjgbLocalDao;

	private YDHKJHJGBDLDao ydhkjhjgbDLDao;

	private YDHKJHJGBTBDao ydhkjhjgbTBDao;

	private YDHKJHJGBSBDao ydhkjhjgbSBDao;

	@Override
	public boolean transferYDHKJHJGB() {
		boolean result = false;
		try {
			// dl
			ydhkjhjgbLocalDao.deleteYDHKJHJGBLocalByQY(6);
			YDHKJHJGBLocal ydhkjhjgbLocal = null;
			List<YDHKJHJGBXL> ydhkjhjgbDLList = ydhkjhjgbDLDao
					.getAllYDHKJHJGB();
			for (YDHKJHJGBXL ydhkjhjgbDL : ydhkjhjgbDLList) {
				ydhkjhjgbLocal = new YDHKJHJGBLocal();
				ydhkjhjgbLocal.setGxrq(ydhkjhjgbDL.getGxrq());
				ydhkjhjgbLocal.setGsbm(ydhkjhjgbDL.getGsbm());
				ydhkjhjgbLocal.setQbkhyqyszk(ydhkjhjgbDL.getQbkhyqyszk());
				ydhkjhjgbLocal.setQbkhyqk(ydhkjhjgbDL.getQbkhyqk());
				ydhkjhjgbLocal.setQbkhwdqyszk(ydhkjhjgbDL.getQbkhwdqyszk());
				ydhkjhjgbLocal.setQbkhwdqk(ydhkjhjgbDL.getQbkhwdqk());
				ydhkjhjgbLocal.setZqkhyqyszk(ydhkjhjgbDL.getZqkhyqyszk());
				ydhkjhjgbLocal.setZqkhyqk(ydhkjhjgbDL.getZqkhyqk());
				ydhkjhjgbLocal.setZqkhwdqyszk(ydhkjhjgbDL.getZqkhwdqyszk());
				ydhkjhjgbLocal.setZqkhwdqk(ydhkjhjgbDL.getZqkhwdqk());
				ydhkjhjgbLocal.setXyqsk(ydhkjhjgbDL.getXyqsk());
				ydhkjhjgbLocal.setGyqsk(ydhkjhjgbDL.getGyqsk());
				ydhkjhjgbLocal.setSfdrwc(ydhkjhjgbDL.getSfdrwc());
				ydhkjhjgbLocal.setQybh(6);
				ydhkjhjgbLocalDao.merge(ydhkjhjgbLocal);
			}
			// tb
			ydhkjhjgbLocalDao.deleteYDHKJHJGBLocalByQY(301);
			List<YDHKJHJGBBYQ> ydhkjhjgbTBList = ydhkjhjgbTBDao
					.getAllYDHKJHJGB();
			for (YDHKJHJGBBYQ ydhkjhjgbTB : ydhkjhjgbTBList) {
				ydhkjhjgbLocal = new YDHKJHJGBLocal();
				ydhkjhjgbLocal.setGxrq(ydhkjhjgbTB.getGxrq());
				ydhkjhjgbLocal.setGsbm(ydhkjhjgbTB.getGsbm());
				ydhkjhjgbLocal.setQbkhyqyszk(ydhkjhjgbTB.getQbkhyqyszk());
				ydhkjhjgbLocal.setQbkhyqk(ydhkjhjgbTB.getQbkhyqk());
				ydhkjhjgbLocal.setQbkhwdqyszk(ydhkjhjgbTB.getQbkhwdqyszk());
				ydhkjhjgbLocal.setQbkhwdqk(ydhkjhjgbTB.getQbkhwdqk());
				ydhkjhjgbLocal.setZqkhyqyszk(ydhkjhjgbTB.getZqkhyqyszk());
				ydhkjhjgbLocal.setZqkhyqk(ydhkjhjgbTB.getZqkhyqk());
				ydhkjhjgbLocal.setZqkhwdqyszk(ydhkjhjgbTB.getZqkhwdqyszk());
				ydhkjhjgbLocal.setZqkhwdqk(ydhkjhjgbTB.getZqkhwdqk());
				ydhkjhjgbLocal.setXyqsk(ydhkjhjgbTB.getXyqsk());
				ydhkjhjgbLocal.setGyqsk(ydhkjhjgbTB.getGyqsk());
				ydhkjhjgbLocal.setSfdrwc(ydhkjhjgbTB.getSfdrwc());
				ydhkjhjgbLocal.setQybh(301);
				ydhkjhjgbLocalDao.merge(ydhkjhjgbLocal);
			}
			// sb
			ydhkjhjgbLocalDao.deleteYDHKJHJGBLocalByQY(1);
			List<YDHKJHJGBBYQ> ydhkjhjgbSBList = ydhkjhjgbSBDao
					.getAllYDHKJHJGB();
			for (YDHKJHJGBBYQ ydhkjhjgbSB : ydhkjhjgbSBList) {
				ydhkjhjgbLocal = new YDHKJHJGBLocal();
				ydhkjhjgbLocal.setGxrq(ydhkjhjgbSB.getGxrq());
				ydhkjhjgbLocal.setGsbm(ydhkjhjgbSB.getGsbm());
				ydhkjhjgbLocal.setQbkhyqyszk(ydhkjhjgbSB.getQbkhyqyszk());
				ydhkjhjgbLocal.setQbkhyqk(ydhkjhjgbSB.getQbkhyqk());
				ydhkjhjgbLocal.setQbkhwdqyszk(ydhkjhjgbSB.getQbkhwdqyszk());
				ydhkjhjgbLocal.setQbkhwdqk(ydhkjhjgbSB.getQbkhwdqk());
				ydhkjhjgbLocal.setZqkhyqyszk(ydhkjhjgbSB.getZqkhyqyszk());
				ydhkjhjgbLocal.setZqkhyqk(ydhkjhjgbSB.getZqkhyqk());
				ydhkjhjgbLocal.setZqkhwdqyszk(ydhkjhjgbSB.getZqkhwdqyszk());
				ydhkjhjgbLocal.setZqkhwdqk(ydhkjhjgbSB.getZqkhwdqk());
				ydhkjhjgbLocal.setXyqsk(ydhkjhjgbSB.getXyqsk());
				ydhkjhjgbLocal.setGyqsk(ydhkjhjgbSB.getGyqsk());
				ydhkjhjgbLocal.setSfdrwc(ydhkjhjgbSB.getSfdrwc());
				ydhkjhjgbLocal.setQybh(1);
				ydhkjhjgbLocalDao.merge(ydhkjhjgbLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public YDHKJHJGBLocalDao getYdhkjhjgbLocalDao() {
		return ydhkjhjgbLocalDao;
	}

	public void setYdhkjhjgbLocalDao(YDHKJHJGBLocalDao ydhkjhjgbLocalDao) {
		this.ydhkjhjgbLocalDao = ydhkjhjgbLocalDao;
	}

	public YDHKJHJGBDLDao getYdhkjhjgbDLDao() {
		return ydhkjhjgbDLDao;
	}

	public void setYdhkjhjgbDLDao(YDHKJHJGBDLDao ydhkjhjgbDLDao) {
		this.ydhkjhjgbDLDao = ydhkjhjgbDLDao;
	}

	public YDHKJHJGBTBDao getYdhkjhjgbTBDao() {
		return ydhkjhjgbTBDao;
	}

	public void setYdhkjhjgbTBDao(YDHKJHJGBTBDao ydhkjhjgbTBDao) {
		this.ydhkjhjgbTBDao = ydhkjhjgbTBDao;
	}

	public YDHKJHJGBSBDao getYdhkjhjgbSBDao() {
		return ydhkjhjgbSBDao;
	}

	public void setYdhkjhjgbSBDao(YDHKJHJGBSBDao ydhkjhjgbSBDao) {
		this.ydhkjhjgbSBDao = ydhkjhjgbSBDao;
	}

}
