package com.tbea.datatransfer.service.local.ztyszkfxb;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.ztyszkfxb.ZTYSZKFXBLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.ztyszkfxb.ZTYSZKFXBDLDao;
import com.tbea.datatransfer.model.dao.zjsb.ztyszkfxb.ZTYSZKFXBSBDao;
import com.tbea.datatransfer.model.dao.zjtb.ztyszkfxb.ZTYSZKFXBTBDao;
import com.tbea.datatransfer.model.entity.local.ZTYSZKFXBLocal;
import com.tbea.datatransfer.model.entity.zjdl.ZTYSZKFXBDL;
import com.tbea.datatransfer.model.entity.zjsb.ZTYSZKFXBSB;
import com.tbea.datatransfer.model.entity.zjtb.ZTYSZKFXBTB;

@Transactional("transactionManager")
public class ZTYSZKFXBTransferServiceImpl implements ZTYSZKFXBTransferService {

	private ZTYSZKFXBLocalDao ztyszkfxbLocalDao;

	private ZTYSZKFXBDLDao ztyszkfxbDLDao;

	private ZTYSZKFXBTBDao ztyszkfxbTBDao;
	
	private ZTYSZKFXBSBDao ztyszkfxbSBDao;

	@Override
	public boolean transferZTYSZKFXB() {
		boolean result = false;
		try {
			// dl
			ztyszkfxbLocalDao.deleteZTYSZKFXBLocalByQY(6);
			ZTYSZKFXBLocal ztyszkfxbLocal = null;
			List<ZTYSZKFXBDL> ztyszkfxbDLList = ztyszkfxbDLDao
					.getAllZTYSZKFXBDL();
			for (ZTYSZKFXBDL ztyszkfxbDL : ztyszkfxbDLList) {
				ztyszkfxbLocal = new ZTYSZKFXBLocal();
				ztyszkfxbLocal.setGxrq(ztyszkfxbDL.getGxrq());
				ztyszkfxbLocal.setGsbm(ztyszkfxbDL.getGsbm());
				ztyszkfxbLocal.setByzmyszkye(ztyszkfxbDL.getByzmyszkye());
				ztyszkfxbLocal.setByblkzye(ztyszkfxbDL.getByblkzye());
				ztyszkfxbLocal.setByyszksjs(ztyszkfxbDL.getByyszksjs());
				ztyszkfxbLocal.setBysr(ztyszkfxbDL.getBysr());
				ztyszkfxbLocal.setQntqzmyszkye(ztyszkfxbDL.getQntqzmyszkye());
				ztyszkfxbLocal.setQntqblye(ztyszkfxbDL.getQntqblye());
				ztyszkfxbLocal.setQntqyszksjs(ztyszkfxbDL.getQntqyszksjs());
				ztyszkfxbLocal.setQntqsr(ztyszkfxbDL.getQntqsr());
				ztyszkfxbLocal.setSfdrwc(ztyszkfxbDL.getSfdrwc());
				ztyszkfxbLocal.setQybh(6);
				ztyszkfxbLocalDao.merge(ztyszkfxbLocal);
			}
			// tb
			ztyszkfxbLocalDao.deleteZTYSZKFXBLocalByQY(301);
			List<ZTYSZKFXBTB> ztyszkfxbTBList = ztyszkfxbTBDao
					.getAllZTYSZKFXBTB();
			for (ZTYSZKFXBTB ztyszkfxbTB : ztyszkfxbTBList) {
				ztyszkfxbLocal = new ZTYSZKFXBLocal();
				ztyszkfxbLocal.setGxrq(ztyszkfxbTB.getGxrq());
				ztyszkfxbLocal.setGsbm(ztyszkfxbTB.getGsbm());
				ztyszkfxbLocal.setByzmyszkye(ztyszkfxbTB.getByzmyszkye());
				ztyszkfxbLocal.setByblkzye(ztyszkfxbTB.getByblkzye());
				ztyszkfxbLocal.setByyszksjs(ztyszkfxbTB.getByyszksjs());
				ztyszkfxbLocal.setBysr(ztyszkfxbTB.getBysr());
				ztyszkfxbLocal.setQntqzmyszkye(ztyszkfxbTB.getQntqzmyszkye());
				ztyszkfxbLocal.setQntqblye(ztyszkfxbTB.getQntqblye());
				ztyszkfxbLocal.setQntqyszksjs(ztyszkfxbTB.getQntqyszksjs());
				ztyszkfxbLocal.setQntqsr(ztyszkfxbTB.getQntqsr());
				ztyszkfxbLocal.setSfdrwc(ztyszkfxbTB.getSfdrwc());
				ztyszkfxbLocal.setQybh(301);
				ztyszkfxbLocalDao.merge(ztyszkfxbLocal);
			}
			// sb
			ztyszkfxbLocalDao.deleteZTYSZKFXBLocalByQY(1);
			List<ZTYSZKFXBSB> ztyszkfxbSBList = ztyszkfxbSBDao
					.getAllZTYSZKFXBSB();
			for (ZTYSZKFXBSB ztyszkfxbSB : ztyszkfxbSBList) {
				ztyszkfxbLocal = new ZTYSZKFXBLocal();
				ztyszkfxbLocal.setGxrq(ztyszkfxbSB.getGxrq());
				ztyszkfxbLocal.setGsbm(ztyszkfxbSB.getGsbm());
				ztyszkfxbLocal.setByzmyszkye(ztyszkfxbSB.getByzmyszkye());
				ztyszkfxbLocal.setByblkzye(ztyszkfxbSB.getByblkzye());
				ztyszkfxbLocal.setByyszksjs(ztyszkfxbSB.getByyszksjs());
				ztyszkfxbLocal.setBysr(ztyszkfxbSB.getBysr());
				ztyszkfxbLocal.setQntqzmyszkye(ztyszkfxbSB.getQntqzmyszkye());
				ztyszkfxbLocal.setQntqblye(ztyszkfxbSB.getQntqblye());
				ztyszkfxbLocal.setQntqyszksjs(ztyszkfxbSB.getQntqyszksjs());
				ztyszkfxbLocal.setQntqsr(ztyszkfxbSB.getQntqsr());
				ztyszkfxbLocal.setSfdrwc(ztyszkfxbSB.getSfdrwc());
				ztyszkfxbLocal.setQybh(301);
				ztyszkfxbLocalDao.merge(ztyszkfxbLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public ZTYSZKFXBLocalDao getZtyszkfxbLocalDao() {
		return ztyszkfxbLocalDao;
	}

	public void setZtyszkfxbLocalDao(ZTYSZKFXBLocalDao ztyszkfxbLocalDao) {
		this.ztyszkfxbLocalDao = ztyszkfxbLocalDao;
	}

	public ZTYSZKFXBDLDao getZtyszkfxbDLDao() {
		return ztyszkfxbDLDao;
	}

	public void setZtyszkfxbDLDao(ZTYSZKFXBDLDao ztyszkfxbDLDao) {
		this.ztyszkfxbDLDao = ztyszkfxbDLDao;
	}

	public ZTYSZKFXBTBDao getZtyszkfxbTBDao() {
		return ztyszkfxbTBDao;
	}

	public void setZtyszkfxbTBDao(ZTYSZKFXBTBDao ztyszkfxbTBDao) {
		this.ztyszkfxbTBDao = ztyszkfxbTBDao;
	}

}
