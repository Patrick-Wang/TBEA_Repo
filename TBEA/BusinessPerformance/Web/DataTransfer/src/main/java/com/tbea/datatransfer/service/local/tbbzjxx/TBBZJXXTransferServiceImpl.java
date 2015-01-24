package com.tbea.datatransfer.service.local.tbbzjxx;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.tbbzjxx.TBBZJXXLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.tbbzjxx.TBBZJXXDLDao;
import com.tbea.datatransfer.model.dao.zjsb.tbbzjxx.TBBZJXXSBDao;
import com.tbea.datatransfer.model.dao.zjtb.tbbzjxx.TBBZJXXTBDao;
import com.tbea.datatransfer.model.entity.local.TBBZJXXLocal;
import com.tbea.datatransfer.model.entity.zjbyq.TBBZJXXBYQ;
import com.tbea.datatransfer.model.entity.zjxl.TBBZJXXXL;

@Transactional("transactionManager")
public class TBBZJXXTransferServiceImpl implements TBBZJXXTransferService {

	private TBBZJXXLocalDao tbbzjxxLocalDao;

	private TBBZJXXDLDao tbbzjxxDLDao;

	private TBBZJXXTBDao tbbzjxxTBDao;

	private TBBZJXXSBDao tbbzjxxSBDao;

	@Override
	public boolean transferTBBZJXX() {
		boolean result = false;
		try {
			// dl
			tbbzjxxLocalDao.deleteTBBZJXXLocalByQY(6);
			TBBZJXXLocal tbbzjxxLocal = null;
			List<TBBZJXXXL> tbbzjxxDLList = tbbzjxxDLDao.getAllTBBZJXX();
			for (TBBZJXXXL tbbzjxxDL : tbbzjxxDLList) {
				tbbzjxxLocal = new TBBZJXXLocal();
				tbbzjxxLocal.setGxrq(tbbzjxxDL.getGxrq());
				tbbzjxxLocal.setGsbm(tbbzjxxDL.getGsbm());
				tbbzjxxLocal.setNf(tbbzjxxDL.getNf());
				tbbzjxxLocal.setYf(tbbzjxxDL.getYf());
				tbbzjxxLocal.setJe(tbbzjxxDL.getJe());
				tbbzjxxLocal.setSfdrwc(tbbzjxxDL.getSfdrwc());
				tbbzjxxLocal.setQybh(6);
				tbbzjxxLocalDao.merge(tbbzjxxLocal);
			}
			// tb
			tbbzjxxLocalDao.deleteTBBZJXXLocalByQY(301);
			List<TBBZJXXBYQ> tbbzjxxTBList = tbbzjxxTBDao.getAllTBBZJXX();
			for (TBBZJXXBYQ tbbzjxxTB : tbbzjxxTBList) {
				tbbzjxxLocal = new TBBZJXXLocal();
				tbbzjxxLocal.setGxrq(tbbzjxxTB.getGxrq());
				tbbzjxxLocal.setGsbm(tbbzjxxTB.getGsbm());
				tbbzjxxLocal.setNf(tbbzjxxTB.getNf());
				tbbzjxxLocal.setYf(tbbzjxxTB.getYf());
				tbbzjxxLocal.setJe(tbbzjxxTB.getJe());
				tbbzjxxLocal.setSfdrwc(tbbzjxxTB.getSfdrwc());
				tbbzjxxLocal.setQybh(301);
				tbbzjxxLocalDao.merge(tbbzjxxLocal);
			}
			// sb
			tbbzjxxLocalDao.deleteTBBZJXXLocalByQY(1);
			List<TBBZJXXBYQ> tbbzjxxSBList = tbbzjxxSBDao.getAllTBBZJXX();
			for (TBBZJXXBYQ tbbzjxxSB : tbbzjxxSBList) {
				tbbzjxxLocal = new TBBZJXXLocal();
				tbbzjxxLocal.setGxrq(tbbzjxxSB.getGxrq());
				tbbzjxxLocal.setGsbm(tbbzjxxSB.getGsbm());
				tbbzjxxLocal.setNf(tbbzjxxSB.getNf());
				tbbzjxxLocal.setYf(tbbzjxxSB.getYf());
				tbbzjxxLocal.setJe(tbbzjxxSB.getJe());
				tbbzjxxLocal.setSfdrwc(tbbzjxxSB.getSfdrwc());
				tbbzjxxLocal.setQybh(1);
				tbbzjxxLocalDao.merge(tbbzjxxLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public TBBZJXXLocalDao getTbbzjxxLocalDao() {
		return tbbzjxxLocalDao;
	}

	public void setTbbzjxxLocalDao(TBBZJXXLocalDao tbbzjxxLocalDao) {
		this.tbbzjxxLocalDao = tbbzjxxLocalDao;
	}

	public TBBZJXXDLDao getTbbzjxxDLDao() {
		return tbbzjxxDLDao;
	}

	public void setTbbzjxxDLDao(TBBZJXXDLDao tbbzjxxDLDao) {
		this.tbbzjxxDLDao = tbbzjxxDLDao;
	}

	public TBBZJXXTBDao getTbbzjxxTBDao() {
		return tbbzjxxTBDao;
	}

	public void setTbbzjxxTBDao(TBBZJXXTBDao tbbzjxxTBDao) {
		this.tbbzjxxTBDao = tbbzjxxTBDao;
	}

	public TBBZJXXSBDao getTbbzjxxSBDao() {
		return tbbzjxxSBDao;
	}

	public void setTbbzjxxSBDao(TBBZJXXSBDao tbbzjxxSBDao) {
		this.tbbzjxxSBDao = tbbzjxxSBDao;
	}

}
