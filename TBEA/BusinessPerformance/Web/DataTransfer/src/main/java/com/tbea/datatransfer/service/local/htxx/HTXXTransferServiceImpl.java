package com.tbea.datatransfer.service.local.htxx;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.htxx.HTXXLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.htxx.HTXXDLDao;
import com.tbea.datatransfer.model.dao.zjtb.htxx.HTXXTBDao;
import com.tbea.datatransfer.model.entity.local.HTXXLocal;
import com.tbea.datatransfer.model.entity.zjdl.HTXXDL;
import com.tbea.datatransfer.model.entity.zjtb.HTXXTB;

@Transactional("transactionManager")
public class HTXXTransferServiceImpl implements HTXXTransferService {

	private HTXXLocalDao htxxLocalDao;

	private HTXXDLDao htxxDLDao;
	
	private HTXXTBDao htxxTBDao;

	@Override
	public boolean transferHTXX() {
		boolean result = false;
		try {
			// dl
			htxxLocalDao.deleteHTXXLocalByQY(6);
			HTXXLocal htxxLocal = null;
			List<HTXXDL> htxxDLList = htxxDLDao.getAllHTXXDL();
			for (HTXXDL htxxDL : htxxDLList) {
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
			List<HTXXTB> htxxTBList = htxxTBDao.getAllHTXXTB();
			for (HTXXTB htxxTB : htxxTBList) {
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

}
