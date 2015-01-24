package com.tbea.datatransfer.service.local.ydsjhkqk;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.ydsjhkqk.YDSJHKQKLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.ydsjhkqk.YDSJHKQKBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.ydsjhkqk.YDSJHKQKXLDao;
import com.tbea.datatransfer.model.entity.local.YDSJHKQKLocal;
import com.tbea.datatransfer.model.entity.zjbyq.YDSJHKQKBYQ;
import com.tbea.datatransfer.model.entity.zjxl.YDSJHKQKXL;

@Transactional("transactionManager")
public class YDSJHKQKTransferServiceImpl implements YDSJHKQKTransferService {

	private YDSJHKQKLocalDao ydsjhkqkLocalDao;

	private YDSJHKQKXLDao ydsjhkqkDLDao;

	private YDSJHKQKXLDao ydsjhkqkLLDao;

	private YDSJHKQKBYQDao ydsjhkqkTBDao;

	private YDSJHKQKBYQDao ydsjhkqkSBDao;

	private YDSJHKQKXLDao ydsjhkqkXLDao;

	private YDSJHKQKBYQDao ydsjhkqkXBDao;

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
			// ll
			ydsjhkqkLocalDao.deleteYDSJHKQKLocalByQY(4);
			List<YDSJHKQKXL> ydsjhkqkLLList = ydsjhkqkLLDao.getAllYDSJHKQK();
			for (YDSJHKQKXL ydsjhkqkLL : ydsjhkqkLLList) {
				ydsjhkqkLocal = new YDSJHKQKLocal();
				ydsjhkqkLocal.setGxrq(ydsjhkqkLL.getGxrq());
				ydsjhkqkLocal.setGsbm(ydsjhkqkLL.getGsbm());
				ydsjhkqkLocal.setYqyszksjhk(ydsjhkqkLL.getYqyszksjhk());
				ydsjhkqkLocal.setYqksjhk(ydsjhkqkLL.getYqksjhk());
				ydsjhkqkLocal.setWdqyszksjhk(ydsjhkqkLL.getWdqyszksjhk());
				ydsjhkqkLocal.setWdqksjhk(ydsjhkqkLL.getWdqksjhk());
				ydsjhkqkLocal.setQbkhhk(ydsjhkqkLL.getQbkhhk());
				ydsjhkqkLocal.setZqkhhk(ydsjhkqkLL.getZqkhhk());
				ydsjhkqkLocal.setXkxhhk(ydsjhkqkLL.getXkxhhk());
				ydsjhkqkLocal.setJhwhk(ydsjhkqkLL.getJhwhk());
				ydsjhkqkLocal.setSfdrwc(ydsjhkqkLL.getSfdrwc());
				ydsjhkqkLocal.setQybh(4);
				ydsjhkqkLocalDao.merge(ydsjhkqkLocal);
			}
			// xl
			ydsjhkqkLocalDao.deleteYDSJHKQKLocalByQY(5);
			List<YDSJHKQKXL> ydsjhkqkXLList = ydsjhkqkXLDao.getAllYDSJHKQK();
			for (YDSJHKQKXL ydsjhkqkXL : ydsjhkqkXLList) {
				ydsjhkqkLocal = new YDSJHKQKLocal();
				ydsjhkqkLocal.setGxrq(ydsjhkqkXL.getGxrq());
				ydsjhkqkLocal.setGsbm(ydsjhkqkXL.getGsbm());
				ydsjhkqkLocal.setYqyszksjhk(ydsjhkqkXL.getYqyszksjhk());
				ydsjhkqkLocal.setYqksjhk(ydsjhkqkXL.getYqksjhk());
				ydsjhkqkLocal.setWdqyszksjhk(ydsjhkqkXL.getWdqyszksjhk());
				ydsjhkqkLocal.setWdqksjhk(ydsjhkqkXL.getWdqksjhk());
				ydsjhkqkLocal.setQbkhhk(ydsjhkqkXL.getQbkhhk());
				ydsjhkqkLocal.setZqkhhk(ydsjhkqkXL.getZqkhhk());
				ydsjhkqkLocal.setXkxhhk(ydsjhkqkXL.getXkxhhk());
				ydsjhkqkLocal.setJhwhk(ydsjhkqkXL.getJhwhk());
				ydsjhkqkLocal.setSfdrwc(ydsjhkqkXL.getSfdrwc());
				ydsjhkqkLocal.setQybh(5);
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
			List<YDSJHKQKBYQ> ydsjhkqkSBList = ydsjhkqkSBDao.getAllYDSJHKQK();
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
			// xb
			ydsjhkqkLocalDao.deleteYDSJHKQKLocalByQY(3);
			List<YDSJHKQKBYQ> ydsjhkqkXBList = ydsjhkqkXBDao.getAllYDSJHKQK();
			for (YDSJHKQKBYQ ydsjhkqkXB : ydsjhkqkXBList) {
				ydsjhkqkLocal = new YDSJHKQKLocal();
				ydsjhkqkLocal.setGxrq(ydsjhkqkXB.getGxrq());
				ydsjhkqkLocal.setGsbm(ydsjhkqkXB.getGsbm());
				ydsjhkqkLocal.setYqyszksjhk(ydsjhkqkXB.getYqyszksjhk());
				ydsjhkqkLocal.setYqksjhk(ydsjhkqkXB.getYqksjhk());
				ydsjhkqkLocal.setWdqyszksjhk(ydsjhkqkXB.getWdqyszksjhk());
				ydsjhkqkLocal.setWdqksjhk(ydsjhkqkXB.getWdqksjhk());
				ydsjhkqkLocal.setQbkhhk(ydsjhkqkXB.getQbkhhk());
				ydsjhkqkLocal.setZqkhhk(ydsjhkqkXB.getZqkhhk());
				ydsjhkqkLocal.setXkxhhk(ydsjhkqkXB.getXkxhhk());
				ydsjhkqkLocal.setJhwhk(ydsjhkqkXB.getJhwhk());
				ydsjhkqkLocal.setSfdrwc(ydsjhkqkXB.getSfdrwc());
				ydsjhkqkLocal.setQybh(3);
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

	public YDSJHKQKXLDao getYdsjhkqkDLDao() {
		return ydsjhkqkDLDao;
	}

	public void setYdsjhkqkDLDao(YDSJHKQKXLDao ydsjhkqkDLDao) {
		this.ydsjhkqkDLDao = ydsjhkqkDLDao;
	}

	public YDSJHKQKXLDao getYdsjhkqkLLDao() {
		return ydsjhkqkLLDao;
	}

	public void setYdsjhkqkLLDao(YDSJHKQKXLDao ydsjhkqkLLDao) {
		this.ydsjhkqkLLDao = ydsjhkqkLLDao;
	}

	public YDSJHKQKBYQDao getYdsjhkqkTBDao() {
		return ydsjhkqkTBDao;
	}

	public void setYdsjhkqkTBDao(YDSJHKQKBYQDao ydsjhkqkTBDao) {
		this.ydsjhkqkTBDao = ydsjhkqkTBDao;
	}

	public YDSJHKQKBYQDao getYdsjhkqkSBDao() {
		return ydsjhkqkSBDao;
	}

	public void setYdsjhkqkSBDao(YDSJHKQKBYQDao ydsjhkqkSBDao) {
		this.ydsjhkqkSBDao = ydsjhkqkSBDao;
	}

	public YDSJHKQKXLDao getYdsjhkqkXLDao() {
		return ydsjhkqkXLDao;
	}

	public void setYdsjhkqkXLDao(YDSJHKQKXLDao ydsjhkqkXLDao) {
		this.ydsjhkqkXLDao = ydsjhkqkXLDao;
	}

	public YDSJHKQKBYQDao getYdsjhkqkXBDao() {
		return ydsjhkqkXBDao;
	}

	public void setYdsjhkqkXBDao(YDSJHKQKBYQDao ydsjhkqkXBDao) {
		this.ydsjhkqkXBDao = ydsjhkqkXBDao;
	}

}
