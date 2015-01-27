package com.tbea.datatransfer.service.local.htxx;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.htxx.HTXXLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.htxx.HTXXBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.htxx.HTXXXLDao;
import com.tbea.datatransfer.model.entity.local.HTXXLocal;
import com.tbea.datatransfer.model.entity.zjbyq.HTXXBYQ;
import com.tbea.datatransfer.model.entity.zjxl.HTXXXL;

@Transactional("transactionManager")
public class HTXXTransferServiceImpl implements HTXXTransferService {

	private HTXXLocalDao htxxLocalDao;

	private HTXXXLDao htxxDLDao;

	private HTXXXLDao htxxLLDao;

	private HTXXBYQDao htxxTBDao;

	private HTXXBYQDao htxxSBDao;

	private HTXXXLDao htxxXLDao;

	private HTXXBYQDao htxxXBDao;

	@Override
	public boolean transferHTXX() {
		boolean result = false;
		try {
			// dl
			htxxLocalDao.deleteHTXXLocalByQY(6);
			HTXXLocal htxxLocal = null;
			List<HTXXXL> htxxDLList = htxxDLDao.getAllHTXX();
			for (HTXXXL htxxDL : htxxDLList) {
				htxxLocal = new HTXXLocal();
				htxxLocal.setGxrq(htxxDL.getGxrq());
				htxxLocal.setHtbh(htxxDL.getHtbh());
				htxxLocal.setXmxx(htxxDL.getXmxx());
				htxxLocal.setSspq(htxxDL.getSspq());
				htxxLocal.setKhbh(htxxDL.getKhbh());
				htxxLocal.setKhmc(htxxDL.getKhmc());
				htxxLocal.setKhsshy(htxxDL.getKhsshy());
				htxxLocal.setQdrq(htxxDL.getQdrq());
				htxxLocal.setCpje(htxxDL.getCpje());
				htxxLocal.setFy(htxxDL.getFy());
				htxxLocal.setZje(htxxDL.getZje());
				htxxLocal.setHtzt(htxxDL.getHtzt());
				htxxLocal.setSfdrwc(htxxDL.getSfdrwc());
				htxxLocal.setQybh(6);
				htxxLocalDao.merge(htxxLocal);
			}
			// ll
			htxxLocalDao.deleteHTXXLocalByQY(4);
			List<HTXXXL> htxxLLList = htxxLLDao.getAllHTXX();
			for (HTXXXL htxxLL : htxxLLList) {
				htxxLocal = new HTXXLocal();
				htxxLocal.setGxrq(htxxLL.getGxrq());
				htxxLocal.setHtbh(htxxLL.getHtbh());
				htxxLocal.setXmxx(htxxLL.getXmxx());
				htxxLocal.setSspq(htxxLL.getSspq());
				htxxLocal.setKhbh(htxxLL.getKhbh());
				htxxLocal.setKhmc(htxxLL.getKhmc());
				htxxLocal.setKhsshy(htxxLL.getKhsshy());
				htxxLocal.setQdrq(htxxLL.getQdrq());
				htxxLocal.setCpje(htxxLL.getCpje());
				htxxLocal.setFy(htxxLL.getFy());
				htxxLocal.setZje(htxxLL.getZje());
				htxxLocal.setHtzt(htxxLL.getHtzt());
				htxxLocal.setSfdrwc(htxxLL.getSfdrwc());
				htxxLocal.setQybh(4);
				htxxLocalDao.merge(htxxLocal);
			}
			// xl
			htxxLocalDao.deleteHTXXLocalByQY(5);
			List<HTXXXL> htxxXLList = htxxXLDao.getAllHTXX();
			for (HTXXXL htxxXL : htxxXLList) {
				htxxLocal = new HTXXLocal();
				htxxLocal.setGxrq(htxxXL.getGxrq());
				htxxLocal.setHtbh(htxxXL.getHtbh());
				htxxLocal.setXmxx(htxxXL.getXmxx());
				htxxLocal.setSspq(htxxXL.getSspq());
				htxxLocal.setKhbh(htxxXL.getKhbh());
				htxxLocal.setKhmc(htxxXL.getKhmc());
				htxxLocal.setKhsshy(htxxXL.getKhsshy());
				htxxLocal.setQdrq(htxxXL.getQdrq());
				htxxLocal.setCpje(htxxXL.getCpje());
				htxxLocal.setFy(htxxXL.getFy());
				htxxLocal.setZje(htxxXL.getZje());
				htxxLocal.setHtzt(htxxXL.getHtzt());
				htxxLocal.setSfdrwc(htxxXL.getSfdrwc());
				htxxLocal.setQybh(5);
				htxxLocalDao.merge(htxxLocal);
			}
			// tb
			htxxLocalDao.deleteHTXXLocalByQY(301);
			List<HTXXBYQ> htxxTBList = htxxTBDao.getAllHTXX();
			for (HTXXBYQ htxxTB : htxxTBList) {
				htxxLocal = new HTXXLocal();
				htxxLocal.setGxrq(htxxTB.getGxrq());
				htxxLocal.setHtbh(htxxTB.getHtbh());
				htxxLocal.setXmxx(htxxTB.getXmxx());
				htxxLocal.setSspq(htxxTB.getSspq());
				htxxLocal.setKhbh(htxxTB.getKhbh());
				htxxLocal.setKhmc(htxxTB.getKhmc());
				htxxLocal.setKhsshy(htxxTB.getKhsshy());
				htxxLocal.setQdrq(htxxTB.getQdrq());
				htxxLocal.setCpje(htxxTB.getCpje());
				htxxLocal.setFy(htxxTB.getFy());
				htxxLocal.setZje(htxxTB.getZje());
				htxxLocal.setHtzt(htxxTB.getHtzt());
				htxxLocal.setSfdrwc(htxxTB.getSfdrwc());
				htxxLocal.setQybh(301);
				htxxLocalDao.merge(htxxLocal);
			}

			// sb
			htxxLocalDao.deleteHTXXLocalByQY(1);
			List<HTXXBYQ> htxxSBList = htxxSBDao.getAllHTXX();
			for (HTXXBYQ htxxSB : htxxSBList) {
				htxxLocal = new HTXXLocal();
				htxxLocal.setGxrq(htxxSB.getGxrq());
				htxxLocal.setHtbh(htxxSB.getHtbh());
				htxxLocal.setXmxx(htxxSB.getXmxx());
				htxxLocal.setSspq(htxxSB.getSspq());
				htxxLocal.setKhbh(htxxSB.getKhbh());
				htxxLocal.setKhmc(htxxSB.getKhmc());
				htxxLocal.setKhsshy(htxxSB.getKhsshy());
				htxxLocal.setQdrq(htxxSB.getQdrq());
				htxxLocal.setCpje(htxxSB.getCpje());
				htxxLocal.setFy(htxxSB.getFy());
				htxxLocal.setZje(htxxSB.getZje());
				htxxLocal.setHtzt(htxxSB.getHtzt());
				htxxLocal.setSfdrwc(htxxSB.getSfdrwc());
				htxxLocal.setQybh(1);
				htxxLocalDao.merge(htxxLocal);
			}
			// xb
			htxxLocalDao.deleteHTXXLocalByQY(3);
			List<HTXXBYQ> htxxXBList = htxxXBDao.getAllHTXX();
			for (HTXXBYQ htxxXB : htxxXBList) {
				htxxLocal = new HTXXLocal();
				htxxLocal.setGxrq(htxxXB.getGxrq());
				htxxLocal.setHtbh(htxxXB.getHtbh());
				htxxLocal.setXmxx(htxxXB.getXmxx());
				htxxLocal.setSspq(htxxXB.getSspq());
				htxxLocal.setKhbh(htxxXB.getKhbh());
				htxxLocal.setKhmc(htxxXB.getKhmc());
				htxxLocal.setKhsshy(htxxXB.getKhsshy());
				htxxLocal.setQdrq(htxxXB.getQdrq());
				htxxLocal.setCpje(htxxXB.getCpje());
				htxxLocal.setFy(htxxXB.getFy());
				htxxLocal.setZje(htxxXB.getZje());
				htxxLocal.setHtzt(htxxXB.getHtzt());
				htxxLocal.setSfdrwc(htxxXB.getSfdrwc());
				htxxLocal.setQybh(3);
				htxxLocalDao.merge(htxxLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public HTXXLocalDao getHtxxLocalDao() {
		return htxxLocalDao;
	}

	public void setHtxxLocalDao(HTXXLocalDao htxxLocalDao) {
		this.htxxLocalDao = htxxLocalDao;
	}

	public HTXXXLDao getHtxxDLDao() {
		return htxxDLDao;
	}

	public void setHtxxDLDao(HTXXXLDao htxxDLDao) {
		this.htxxDLDao = htxxDLDao;
	}

	public HTXXBYQDao getHtxxTBDao() {
		return htxxTBDao;
	}

	public void setHtxxTBDao(HTXXBYQDao htxxTBDao) {
		this.htxxTBDao = htxxTBDao;
	}

	public HTXXBYQDao getHtxxSBDao() {
		return htxxSBDao;
	}

	public void setHtxxSBDao(HTXXBYQDao htxxSBDao) {
		this.htxxSBDao = htxxSBDao;
	}

	public HTXXXLDao getHtxxLLDao() {
		return htxxLLDao;
	}

	public void setHtxxLLDao(HTXXXLDao htxxLLDao) {
		this.htxxLLDao = htxxLLDao;
	}

	public HTXXXLDao getHtxxXLDao() {
		return htxxXLDao;
	}

	public void setHtxxXLDao(HTXXXLDao htxxXLDao) {
		this.htxxXLDao = htxxXLDao;
	}

	public HTXXBYQDao getHtxxXBDao() {
		return htxxXBDao;
	}

	public void setHtxxXBDao(HTXXBYQDao htxxXBDao) {
		this.htxxXBDao = htxxXBDao;
	}

}
