package com.tbea.datatransfer.service.local.bl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.bl.BLLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.bl.BLDLDao;
import com.tbea.datatransfer.model.dao.zjsb.bl.BLSBDao;
import com.tbea.datatransfer.model.dao.zjtb.bl.BLTBDao;
import com.tbea.datatransfer.model.entity.local.BLLocal;
import com.tbea.datatransfer.model.entity.zjbyq.BLBYQ;
import com.tbea.datatransfer.model.entity.zjxl.BLXL;

@Transactional("transactionManager")
public class BLTransferServiceImpl implements BLTransferService {

	private BLLocalDao blLocalDao;

	private BLDLDao blDLDao;

	private BLTBDao blTBDao;

	private BLSBDao blSBDao;

	@Override
	public boolean transferBL() {
		boolean result = false;
		try {
			// dl
			blLocalDao.deleteBLLocalByQY(6);
			BLLocal blLocal = null;
			List<BLXL> blDLList = blDLDao.getAllBL();
			for (BLXL blDL : blDLList) {
				blLocal = new BLLocal();
				blLocal.setGxrq(blDL.getGxrq());
				blLocal.setBlbh(blDL.getBlbh());
				blLocal.setHtbh(blDL.getHtbh());
				blLocal.setBlrq(blDL.getBlrq());
				blLocal.setKxxz(blDL.getKxxz());
				blLocal.setBlje(blDL.getBlje());
				blLocal.setBldqr(blDL.getBldqr());
				blLocal.setBlhkje(blDL.getBlhkje());
				blLocal.setBlye(blDL.getBlye());
				blLocal.setSfdrwc(blDL.getSfdrwc());
				blLocal.setQybh(6);
				blLocalDao.merge(blLocal);
			}
			// tb
			blLocalDao.deleteBLLocalByQY(301);
			List<BLBYQ> blTBList = blTBDao.getAllBL();
			for (BLBYQ blTB : blTBList) {
				blLocal = new BLLocal();
				blLocal.setGxrq(blTB.getGxrq());
				blLocal.setBlbh(blTB.getBlbh());
				blLocal.setHtbh(blTB.getHtbh());
				blLocal.setBlrq(blTB.getBlrq());
				blLocal.setKxxz(blTB.getKxxz());
				blLocal.setBlje(blTB.getBlje());
				blLocal.setBldqr(blTB.getBldqr());
				blLocal.setBlhkje(blTB.getBlhkje());
				blLocal.setBlye(blTB.getBlye());
				blLocal.setSfdrwc(blTB.getSfdrwc());
				blLocal.setQybh(301);
				blLocalDao.merge(blLocal);
			}

			// sb
			blLocalDao.deleteBLLocalByQY(1);
			List<BLBYQ> blBYQList = blSBDao.getAllBL();
			for (BLBYQ blSB : blBYQList) {
				blLocal = new BLLocal();
				blLocal.setGxrq(blSB.getGxrq());
				blLocal.setBlbh(blSB.getBlbh());
				blLocal.setHtbh(blSB.getHtbh());
				blLocal.setBlrq(blSB.getBlrq());
				blLocal.setKxxz(blSB.getKxxz());
				blLocal.setBlje(blSB.getBlje());
				blLocal.setBldqr(blSB.getBldqr());
				blLocal.setBlhkje(blSB.getBlhkje());
				blLocal.setBlye(blSB.getBlye());
				blLocal.setSfdrwc(blSB.getSfdrwc());
				blLocal.setQybh(1);
				blLocalDao.merge(blLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public BLLocalDao getBlLocalDao() {
		return blLocalDao;
	}

	public void setBlLocalDao(BLLocalDao blLocalDao) {
		this.blLocalDao = blLocalDao;
	}

	public BLDLDao getBlDLDao() {
		return blDLDao;
	}

	public void setBlDLDao(BLDLDao blDLDao) {
		this.blDLDao = blDLDao;
	}

	public BLTBDao getBlTBDao() {
		return blTBDao;
	}

	public void setBlTBDao(BLTBDao blTBDao) {
		this.blTBDao = blTBDao;
	}

	public BLSBDao getBlSBDao() {
		return blSBDao;
	}

	public void setBlSBDao(BLSBDao blSBDao) {
		this.blSBDao = blSBDao;
	}

}
