package com.tbea.datatransfer.service.local.bl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.bl.BLLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.bl.BLDLDao;
import com.tbea.datatransfer.model.dao.zjsb.bl.BLSBDao;
import com.tbea.datatransfer.model.dao.zjtb.bl.BLTBDao;
import com.tbea.datatransfer.model.entity.local.BLLocal;
import com.tbea.datatransfer.model.entity.zjdl.BLDL;
import com.tbea.datatransfer.model.entity.zjsb.BLSB;
import com.tbea.datatransfer.model.entity.zjtb.BLTB;

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
			List<BLDL> blDLList = blDLDao.getAllBLDL();
			for (BLDL blDL : blDLList) {
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
			List<BLTB> blTBList = blTBDao.getAllBLTB();
			for (BLTB blTB : blTBList) {
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
			List<BLSB> blSBList = blSBDao.getAllBLSB();
			for (BLSB blTB : blSBList) {
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

}
