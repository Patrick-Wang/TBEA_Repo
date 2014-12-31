package com.tbea.datatransfer.service.local.ydsjhkqk;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.ydsjhkqk.YDSJHKQKLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.ydsjhkqk.YDSJHKQKDLDao;
import com.tbea.datatransfer.model.dao.zjtb.ydsjhkqk.YDSJHKQKTBDao;
import com.tbea.datatransfer.model.entity.local.YDSJHKQKLocal;
import com.tbea.datatransfer.model.entity.zjdl.YDSJHKQKDL;
import com.tbea.datatransfer.model.entity.zjtb.YDSJHKQKTB;

@Transactional("transactionManager")
public class YDSJHKQKTransferServiceImpl implements YDSJHKQKTransferService {

	private YDSJHKQKLocalDao ydsjhkqkLocalDao;

	private YDSJHKQKDLDao ydsjhkqkDLDao;

	private YDSJHKQKTBDao ydsjhkqkTBDao;

	@Override
	public boolean transferYDSJHKQK() {
		boolean result = false;
		try {
			// dl
			ydsjhkqkLocalDao.deleteYDSJHKQKLocalByQY(6);
			YDSJHKQKLocal ydsjhkqkLocal = null;
			List<YDSJHKQKDL> ydsjhkqkDLList = ydsjhkqkDLDao.getAllYDSJHKQKDL();
			for (YDSJHKQKDL ydsjhkqkDL : ydsjhkqkDLList) {
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
			List<YDSJHKQKTB> ydsjhkqkTBList = ydsjhkqkTBDao.getAllYDSJHKQKTB();
			for (YDSJHKQKTB ydsjhkqkTB : ydsjhkqkTBList) {
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

}
