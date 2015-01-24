package com.tbea.datatransfer.service.local.ydhkjhjgb;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.ydhkjhjgb.YDHKJHJGBLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.ydhkjhjgb.YDHKJHJGBBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.ydhkjhjgb.YDHKJHJGBXLDao;
import com.tbea.datatransfer.model.entity.local.YDHKJHJGBLocal;
import com.tbea.datatransfer.model.entity.zjbyq.YDHKJHJGBBYQ;
import com.tbea.datatransfer.model.entity.zjxl.YDHKJHJGBXL;

@Transactional("transactionManager")
public class YDHKJHJGBTransferServiceImpl implements YDHKJHJGBTransferService {

	private YDHKJHJGBLocalDao ydhkjhjgbLocalDao;

	private YDHKJHJGBXLDao ydhkjhjgbDLDao;

	private YDHKJHJGBXLDao ydhkjhjgbLLDao;

	private YDHKJHJGBBYQDao ydhkjhjgbTBDao;

	private YDHKJHJGBBYQDao ydhkjhjgbSBDao;

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
			// ll
			ydhkjhjgbLocalDao.deleteYDHKJHJGBLocalByQY(4);
			List<YDHKJHJGBXL> ydhkjhjgbLLList = ydhkjhjgbLLDao
					.getAllYDHKJHJGB();
			for (YDHKJHJGBXL ydhkjhjgbLL : ydhkjhjgbLLList) {
				ydhkjhjgbLocal = new YDHKJHJGBLocal();
				ydhkjhjgbLocal.setGxrq(ydhkjhjgbLL.getGxrq());
				ydhkjhjgbLocal.setGsbm(ydhkjhjgbLL.getGsbm());
				ydhkjhjgbLocal.setQbkhyqyszk(ydhkjhjgbLL.getQbkhyqyszk());
				ydhkjhjgbLocal.setQbkhyqk(ydhkjhjgbLL.getQbkhyqk());
				ydhkjhjgbLocal.setQbkhwdqyszk(ydhkjhjgbLL.getQbkhwdqyszk());
				ydhkjhjgbLocal.setQbkhwdqk(ydhkjhjgbLL.getQbkhwdqk());
				ydhkjhjgbLocal.setZqkhyqyszk(ydhkjhjgbLL.getZqkhyqyszk());
				ydhkjhjgbLocal.setZqkhyqk(ydhkjhjgbLL.getZqkhyqk());
				ydhkjhjgbLocal.setZqkhwdqyszk(ydhkjhjgbLL.getZqkhwdqyszk());
				ydhkjhjgbLocal.setZqkhwdqk(ydhkjhjgbLL.getZqkhwdqk());
				ydhkjhjgbLocal.setXyqsk(ydhkjhjgbLL.getXyqsk());
				ydhkjhjgbLocal.setGyqsk(ydhkjhjgbLL.getGyqsk());
				ydhkjhjgbLocal.setSfdrwc(ydhkjhjgbLL.getSfdrwc());
				ydhkjhjgbLocal.setQybh(4);
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

	public YDHKJHJGBXLDao getYdhkjhjgbDLDao() {
		return ydhkjhjgbDLDao;
	}

	public void setYdhkjhjgbDLDao(YDHKJHJGBXLDao ydhkjhjgbDLDao) {
		this.ydhkjhjgbDLDao = ydhkjhjgbDLDao;
	}

	public YDHKJHJGBXLDao getYdhkjhjgbLLDao() {
		return ydhkjhjgbLLDao;
	}

	public void setYdhkjhjgbLLDao(YDHKJHJGBXLDao ydhkjhjgbLLDao) {
		this.ydhkjhjgbLLDao = ydhkjhjgbLLDao;
	}

	public YDHKJHJGBBYQDao getYdhkjhjgbTBDao() {
		return ydhkjhjgbTBDao;
	}

	public void setYdhkjhjgbTBDao(YDHKJHJGBBYQDao ydhkjhjgbTBDao) {
		this.ydhkjhjgbTBDao = ydhkjhjgbTBDao;
	}

	public YDHKJHJGBBYQDao getYdhkjhjgbSBDao() {
		return ydhkjhjgbSBDao;
	}

	public void setYdhkjhjgbSBDao(YDHKJHJGBBYQDao ydhkjhjgbSBDao) {
		this.ydhkjhjgbSBDao = ydhkjhjgbSBDao;
	}

}
