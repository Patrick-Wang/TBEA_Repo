package com.tbea.datatransfer.service.local.htxx;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.htxx.HTXXLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.htxx.HTXXDLDao;
import com.tbea.datatransfer.model.dao.zjsb.htxx.HTXXSBDao;
import com.tbea.datatransfer.model.dao.zjtb.htxx.HTXXTBDao;
import com.tbea.datatransfer.model.entity.local.HTXXLocal;
import com.tbea.datatransfer.model.entity.zjbyq.HTXXBYQ;
import com.tbea.datatransfer.model.entity.zjxl.HTXXXL;

@Transactional("transactionManager")
public class HTXXTransferServiceImpl implements HTXXTransferService {

	private HTXXLocalDao htxxLocalDao;

	private HTXXDLDao htxxDLDao;

	private HTXXTBDao htxxTBDao;

	private HTXXSBDao htxxSBDao;

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

	public HTXXDLDao getHtxxDLDao() {
		return htxxDLDao;
	}

	public void setHtxxDLDao(HTXXDLDao htxxDLDao) {
		this.htxxDLDao = htxxDLDao;
	}

	public HTXXTBDao getHtxxTBDao() {
		return htxxTBDao;
	}

	public void setHtxxTBDao(HTXXTBDao htxxTBDao) {
		this.htxxTBDao = htxxTBDao;
	}

	public HTXXSBDao getHtxxSBDao() {
		return htxxSBDao;
	}

	public void setHtxxSBDao(HTXXSBDao htxxSBDao) {
		this.htxxSBDao = htxxSBDao;
	}

}
