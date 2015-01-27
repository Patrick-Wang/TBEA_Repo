package com.tbea.datatransfer.service.local.ztyszkfxb;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.ztyszkfxb.ZTYSZKFXBLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.ztyszkfxb.ZTYSZKFXBBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.ztyszkfxb.ZTYSZKFXBXLDao;
import com.tbea.datatransfer.model.entity.local.ZTYSZKFXBLocal;
import com.tbea.datatransfer.model.entity.zjbyq.ZTYSZKFXBBYQ;
import com.tbea.datatransfer.model.entity.zjxl.ZTYSZKFXBXL;

@Transactional("transactionManager")
public class ZTYSZKFXBTransferServiceImpl implements ZTYSZKFXBTransferService {

	private ZTYSZKFXBLocalDao ztyszkfxbLocalDao;

	private ZTYSZKFXBXLDao ztyszkfxbDLDao;

	private ZTYSZKFXBXLDao ztyszkfxbLLDao;

	private ZTYSZKFXBBYQDao ztyszkfxbTBDao;

	private ZTYSZKFXBBYQDao ztyszkfxbSBDao;

	private ZTYSZKFXBXLDao ztyszkfxbXLDao;

	private ZTYSZKFXBBYQDao ztyszkfxbXBDao;

	@Override
	public boolean transferZTYSZKFXB() {
		boolean result = false;
		try {
			// dl
			ztyszkfxbLocalDao.deleteZTYSZKFXBLocalByQY(6);
			ZTYSZKFXBLocal ztyszkfxbLocal = null;
			List<ZTYSZKFXBXL> ztyszkfxbDLList = ztyszkfxbDLDao
					.getAllZTYSZKFXB();
			for (ZTYSZKFXBXL ztyszkfxbDL : ztyszkfxbDLList) {
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
			// ll
			ztyszkfxbLocalDao.deleteZTYSZKFXBLocalByQY(4);
			List<ZTYSZKFXBXL> ztyszkfxbLLList = ztyszkfxbLLDao
					.getAllZTYSZKFXB();
			for (ZTYSZKFXBXL ztyszkfxbLL : ztyszkfxbLLList) {
				ztyszkfxbLocal = new ZTYSZKFXBLocal();
				ztyszkfxbLocal.setGxrq(ztyszkfxbLL.getGxrq());
				ztyszkfxbLocal.setGsbm(ztyszkfxbLL.getGsbm());
				ztyszkfxbLocal.setByzmyszkye(ztyszkfxbLL.getByzmyszkye());
				ztyszkfxbLocal.setByblkzye(ztyszkfxbLL.getByblkzye());
				ztyszkfxbLocal.setByyszksjs(ztyszkfxbLL.getByyszksjs());
				ztyszkfxbLocal.setBysr(ztyszkfxbLL.getBysr());
				ztyszkfxbLocal.setQntqzmyszkye(ztyszkfxbLL.getQntqzmyszkye());
				ztyszkfxbLocal.setQntqblye(ztyszkfxbLL.getQntqblye());
				ztyszkfxbLocal.setQntqyszksjs(ztyszkfxbLL.getQntqyszksjs());
				ztyszkfxbLocal.setQntqsr(ztyszkfxbLL.getQntqsr());
				ztyszkfxbLocal.setSfdrwc(ztyszkfxbLL.getSfdrwc());
				ztyszkfxbLocal.setQybh(4);
				ztyszkfxbLocalDao.merge(ztyszkfxbLocal);
			}
			// xl
			ztyszkfxbLocalDao.deleteZTYSZKFXBLocalByQY(5);
			List<ZTYSZKFXBXL> ztyszkfxbXLList = ztyszkfxbXLDao
					.getAllZTYSZKFXB();
			for (ZTYSZKFXBXL ztyszkfxbXL : ztyszkfxbXLList) {
				ztyszkfxbLocal = new ZTYSZKFXBLocal();
				ztyszkfxbLocal.setGxrq(ztyszkfxbXL.getGxrq());
				ztyszkfxbLocal.setGsbm(ztyszkfxbXL.getGsbm());
				ztyszkfxbLocal.setByzmyszkye(ztyszkfxbXL.getByzmyszkye());
				ztyszkfxbLocal.setByblkzye(ztyszkfxbXL.getByblkzye());
				ztyszkfxbLocal.setByyszksjs(ztyszkfxbXL.getByyszksjs());
				ztyszkfxbLocal.setBysr(ztyszkfxbXL.getBysr());
				ztyszkfxbLocal.setQntqzmyszkye(ztyszkfxbXL.getQntqzmyszkye());
				ztyszkfxbLocal.setQntqblye(ztyszkfxbXL.getQntqblye());
				ztyszkfxbLocal.setQntqyszksjs(ztyszkfxbXL.getQntqyszksjs());
				ztyszkfxbLocal.setQntqsr(ztyszkfxbXL.getQntqsr());
				ztyszkfxbLocal.setSfdrwc(ztyszkfxbXL.getSfdrwc());
				ztyszkfxbLocal.setQybh(5);
				ztyszkfxbLocalDao.merge(ztyszkfxbLocal);
			}
			// tb
			ztyszkfxbLocalDao.deleteZTYSZKFXBLocalByQY(301);
			List<ZTYSZKFXBBYQ> ztyszkfxbTBList = ztyszkfxbTBDao
					.getAllZTYSZKFXB();
			for (ZTYSZKFXBBYQ ztyszkfxbTB : ztyszkfxbTBList) {
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
			List<ZTYSZKFXBBYQ> ztyszkfxbSBList = ztyszkfxbSBDao
					.getAllZTYSZKFXB();
			for (ZTYSZKFXBBYQ ztyszkfxbSB : ztyszkfxbSBList) {
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
				ztyszkfxbLocal.setQybh(1);
				ztyszkfxbLocalDao.merge(ztyszkfxbLocal);
			}
			// xb
			ztyszkfxbLocalDao.deleteZTYSZKFXBLocalByQY(3);
			List<ZTYSZKFXBBYQ> ztyszkfxbXBList = ztyszkfxbXBDao
					.getAllZTYSZKFXB();
			for (ZTYSZKFXBBYQ ztyszkfxbXB : ztyszkfxbXBList) {
				ztyszkfxbLocal = new ZTYSZKFXBLocal();
				ztyszkfxbLocal.setGxrq(ztyszkfxbXB.getGxrq());
				ztyszkfxbLocal.setGsbm(ztyszkfxbXB.getGsbm());
				ztyszkfxbLocal.setByzmyszkye(ztyszkfxbXB.getByzmyszkye());
				ztyszkfxbLocal.setByblkzye(ztyszkfxbXB.getByblkzye());
				ztyszkfxbLocal.setByyszksjs(ztyszkfxbXB.getByyszksjs());
				ztyszkfxbLocal.setBysr(ztyszkfxbXB.getBysr());
				ztyszkfxbLocal.setQntqzmyszkye(ztyszkfxbXB.getQntqzmyszkye());
				ztyszkfxbLocal.setQntqblye(ztyszkfxbXB.getQntqblye());
				ztyszkfxbLocal.setQntqyszksjs(ztyszkfxbXB.getQntqyszksjs());
				ztyszkfxbLocal.setQntqsr(ztyszkfxbXB.getQntqsr());
				ztyszkfxbLocal.setSfdrwc(ztyszkfxbXB.getSfdrwc());
				ztyszkfxbLocal.setQybh(3);
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

	public ZTYSZKFXBXLDao getZtyszkfxbDLDao() {
		return ztyszkfxbDLDao;
	}

	public void setZtyszkfxbDLDao(ZTYSZKFXBXLDao ztyszkfxbDLDao) {
		this.ztyszkfxbDLDao = ztyszkfxbDLDao;
	}

	public ZTYSZKFXBBYQDao getZtyszkfxbTBDao() {
		return ztyszkfxbTBDao;
	}

	public void setZtyszkfxbTBDao(ZTYSZKFXBBYQDao ztyszkfxbTBDao) {
		this.ztyszkfxbTBDao = ztyszkfxbTBDao;
	}

	public ZTYSZKFXBBYQDao getZtyszkfxbSBDao() {
		return ztyszkfxbSBDao;
	}

	public void setZtyszkfxbSBDao(ZTYSZKFXBBYQDao ztyszkfxbSBDao) {
		this.ztyszkfxbSBDao = ztyszkfxbSBDao;
	}

	public ZTYSZKFXBXLDao getZtyszkfxbLLDao() {
		return ztyszkfxbLLDao;
	}

	public void setZtyszkfxbLLDao(ZTYSZKFXBXLDao ztyszkfxbLLDao) {
		this.ztyszkfxbLLDao = ztyszkfxbLLDao;
	}

	public ZTYSZKFXBXLDao getZtyszkfxbXLDao() {
		return ztyszkfxbXLDao;
	}

	public void setZtyszkfxbXLDao(ZTYSZKFXBXLDao ztyszkfxbXLDao) {
		this.ztyszkfxbXLDao = ztyszkfxbXLDao;
	}

	public ZTYSZKFXBBYQDao getZtyszkfxbXBDao() {
		return ztyszkfxbXBDao;
	}

	public void setZtyszkfxbXBDao(ZTYSZKFXBBYQDao ztyszkfxbXBDao) {
		this.ztyszkfxbXBDao = ztyszkfxbXBDao;
	}

}
