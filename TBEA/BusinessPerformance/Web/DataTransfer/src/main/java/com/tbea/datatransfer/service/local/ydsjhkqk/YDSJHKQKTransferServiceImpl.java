package com.tbea.datatransfer.service.local.ydsjhkqk;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.ydsjhkqk.YDSJHKQKLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.ydsjhkqk.YDSJHKQKDLDao;
import com.tbea.datatransfer.model.dao.zjsb.ydsjhkqk.YDSJHKQKSBDao;
import com.tbea.datatransfer.model.dao.zjtb.ydsjhkqk.YDSJHKQKTBDao;
import com.tbea.datatransfer.model.entity.local.YDSJHKQKLocal;
import com.tbea.datatransfer.model.entity.zjbyq.YDSJHKQKBYQ;
import com.tbea.datatransfer.model.entity.zjxl.YDSJHKQKXL;

@Transactional("transactionManager")
public class YDSJHKQKTransferServiceImpl implements YDSJHKQKTransferService {

	private YDSJHKQKLocalDao ydsjhkqkLocalDao;

	private YDSJHKQKDLDao ydsjhkqkDLDao;

	private YDSJHKQKTBDao ydsjhkqkTBDao;

	private YDSJHKQKSBDao ydsjhkqkSBDao;

	@Override
	public boolean transferYDSJHKQK() {
		boolean result = false;
		try {
			// dl
			ydsjhkqkLocalDao.deleteYDSJHKQKLocalByQY(6);
			YDSJHKQKLocal ydsjhkqkLocal = null;
			List<YDSJHKQKXL> ydsjhkqkDLList = ydsjhkqkDLDao.getAllYDSJHKQK();
			for (YDSJHKQKXL ydsjhkqkDL : ydsjhkqkDLList) {
				ydsjhkqkLocal = new YDSJHKQKLocal();
				ydsjhkqkLocal.setGxrq(ydsjhkqkDL.getGxrq());
				ydsjhkqkLocal.setGsbm(ydsjhkqkDL.getGsbm());
				ydsjhkqkLocal.setYqyszksjhk(ydsjhkqkDL.getYqyszksjhk());
				ydsjhkqkLocal.setYqksjhk(ydsjhkqkDL.getYqksjhk());
				ydsjhkqkLocal.setWdqyszksjhk(ydsjhkqkDL.getWdqyszksjhk());
				ydsjhkqkLocal.setWdqksjhk(ydsjhkqkDL.getWdqksjhk());
				ydsjhkqkLocal.setQbkhhk(ydsjhkqkDL.getQbkhhk());
				ydsjhkqkLocal.setZqkhhk(ydsjhkqkDL.getZqkhhk());
				ydsjhkqkLocal.setXkxhhk(ydsjhkqkDL.getXkxhhk());
				ydsjhkqkLocal.setJhwhk(ydsjhkqkDL.getJhwhk());
				ydsjhkqkLocal.setSfdrwc(ydsjhkqkDL.getSfdrwc());
				ydsjhkqkLocal.setQybh(6);
				ydsjhkqkLocalDao.merge(ydsjhkqkLocal);
			}
			// tb
			ydsjhkqkLocalDao.deleteYDSJHKQKLocalByQY(301);
			List<YDSJHKQKBYQ> ydsjhkqkTBList = ydsjhkqkTBDao.getAllYDSJHKQK();
			for (YDSJHKQKBYQ ydsjhkqkTB : ydsjhkqkTBList) {
				ydsjhkqkLocal = new YDSJHKQKLocal();
				ydsjhkqkLocal.setGxrq(ydsjhkqkTB.getGxrq());
				ydsjhkqkLocal.setGsbm(ydsjhkqkTB.getGsbm());
				ydsjhkqkLocal.setYqyszksjhk(ydsjhkqkTB.getYqyszksjhk());
				ydsjhkqkLocal.setYqksjhk(ydsjhkqkTB.getYqksjhk());
				ydsjhkqkLocal.setWdqyszksjhk(ydsjhkqkTB.getWdqyszksjhk());
				ydsjhkqkLocal.setWdqksjhk(ydsjhkqkTB.getWdqksjhk());
				ydsjhkqkLocal.setQbkhhk(ydsjhkqkTB.getQbkhhk());
				ydsjhkqkLocal.setZqkhhk(ydsjhkqkTB.getZqkhhk());
				ydsjhkqkLocal.setXkxhhk(ydsjhkqkTB.getXkxhhk());
				ydsjhkqkLocal.setJhwhk(ydsjhkqkTB.getJhwhk());
				ydsjhkqkLocal.setSfdrwc(ydsjhkqkTB.getSfdrwc());
				ydsjhkqkLocal.setQybh(301);
				ydsjhkqkLocalDao.merge(ydsjhkqkLocal);
			}

			// sb
			ydsjhkqkLocalDao.deleteYDSJHKQKLocalByQY(1);
			List<YDSJHKQKBYQ> ydsjhkqkSBList = ydsjhkqkSBDao
					.getAllYDSJHKQK();
			for (YDSJHKQKBYQ ydsjhkqkSB : ydsjhkqkSBList) {
				ydsjhkqkLocal = new YDSJHKQKLocal();
				ydsjhkqkLocal.setGxrq(ydsjhkqkSB.getGxrq());
				ydsjhkqkLocal.setGsbm(ydsjhkqkSB.getGsbm());
				ydsjhkqkLocal.setYqyszksjhk(ydsjhkqkSB.getYqyszksjhk());
				ydsjhkqkLocal.setYqksjhk(ydsjhkqkSB.getYqksjhk());
				ydsjhkqkLocal.setWdqyszksjhk(ydsjhkqkSB.getWdqyszksjhk());
				ydsjhkqkLocal.setWdqksjhk(ydsjhkqkSB.getWdqksjhk());
				ydsjhkqkLocal.setQbkhhk(ydsjhkqkSB.getQbkhhk());
				ydsjhkqkLocal.setZqkhhk(ydsjhkqkSB.getZqkhhk());
				ydsjhkqkLocal.setXkxhhk(ydsjhkqkSB.getXkxhhk());
				ydsjhkqkLocal.setJhwhk(ydsjhkqkSB.getJhwhk());
				ydsjhkqkLocal.setSfdrwc(ydsjhkqkSB.getSfdrwc());
				ydsjhkqkLocal.setQybh(1);
				ydsjhkqkLocalDao.merge(ydsjhkqkLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public YDSJHKQKLocalDao getYdsjhkqkLocalDao() {
		return ydsjhkqkLocalDao;
	}

	public void setYdsjhkqkLocalDao(YDSJHKQKLocalDao ydsjhkqkLocalDao) {
		this.ydsjhkqkLocalDao = ydsjhkqkLocalDao;
	}

	public YDSJHKQKDLDao getYdsjhkqkDLDao() {
		return ydsjhkqkDLDao;
	}

	public void setYdsjhkqkDLDao(YDSJHKQKDLDao ydsjhkqkDLDao) {
		this.ydsjhkqkDLDao = ydsjhkqkDLDao;
	}

	public YDSJHKQKTBDao getYdsjhkqkTBDao() {
		return ydsjhkqkTBDao;
	}

	public void setYdsjhkqkTBDao(YDSJHKQKTBDao ydsjhkqkTBDao) {
		this.ydsjhkqkTBDao = ydsjhkqkTBDao;
	}

	public YDSJHKQKSBDao getYdsjhkqkSBDao() {
		return ydsjhkqkSBDao;
	}

	public void setYdsjhkqkSBDao(YDSJHKQKSBDao ydsjhkqkSBDao) {
		this.ydsjhkqkSBDao = ydsjhkqkSBDao;
	}

}
