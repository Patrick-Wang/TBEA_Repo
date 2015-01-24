package com.tbea.datatransfer.service.local.tbbzjxx;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.tbbzjxx.TBBZJXXLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.tbbzjxx.TBBZJXXBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.tbbzjxx.TBBZJXXXLDao;
import com.tbea.datatransfer.model.entity.local.TBBZJXXLocal;
import com.tbea.datatransfer.model.entity.zjbyq.TBBZJXXBYQ;
import com.tbea.datatransfer.model.entity.zjxl.TBBZJXXXL;

@Transactional("transactionManager")
public class TBBZJXXTransferServiceImpl implements TBBZJXXTransferService {

	private TBBZJXXLocalDao tbbzjxxLocalDao;

	private TBBZJXXXLDao tbbzjxxDLDao;

	private TBBZJXXXLDao tbbzjxxLLDao;

	private TBBZJXXBYQDao tbbzjxxTBDao;

	private TBBZJXXBYQDao tbbzjxxSBDao;

	private TBBZJXXXLDao tbbzjxxXLDao;

	private TBBZJXXBYQDao tbbzjxxXBDao;

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
			// ll
			tbbzjxxLocalDao.deleteTBBZJXXLocalByQY(4);
			List<TBBZJXXXL> tbbzjxxLLList = tbbzjxxLLDao.getAllTBBZJXX();
			for (TBBZJXXXL tbbzjxxLL : tbbzjxxLLList) {
				tbbzjxxLocal = new TBBZJXXLocal();
				tbbzjxxLocal.setGxrq(tbbzjxxLL.getGxrq());
				tbbzjxxLocal.setGsbm(tbbzjxxLL.getGsbm());
				tbbzjxxLocal.setNf(tbbzjxxLL.getNf());
				tbbzjxxLocal.setYf(tbbzjxxLL.getYf());
				tbbzjxxLocal.setJe(tbbzjxxLL.getJe());
				tbbzjxxLocal.setSfdrwc(tbbzjxxLL.getSfdrwc());
				tbbzjxxLocal.setQybh(4);
				tbbzjxxLocalDao.merge(tbbzjxxLocal);
			}
			// xl
			tbbzjxxLocalDao.deleteTBBZJXXLocalByQY(5);
			List<TBBZJXXXL> tbbzjxxXLList = tbbzjxxXLDao.getAllTBBZJXX();
			for (TBBZJXXXL tbbzjxxXL : tbbzjxxXLList) {
				tbbzjxxLocal = new TBBZJXXLocal();
				tbbzjxxLocal.setGxrq(tbbzjxxXL.getGxrq());
				tbbzjxxLocal.setGsbm(tbbzjxxXL.getGsbm());
				tbbzjxxLocal.setNf(tbbzjxxXL.getNf());
				tbbzjxxLocal.setYf(tbbzjxxXL.getYf());
				tbbzjxxLocal.setJe(tbbzjxxXL.getJe());
				tbbzjxxLocal.setSfdrwc(tbbzjxxXL.getSfdrwc());
				tbbzjxxLocal.setQybh(5);
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
			// xb
			tbbzjxxLocalDao.deleteTBBZJXXLocalByQY(3);
			List<TBBZJXXBYQ> tbbzjxxXBList = tbbzjxxXBDao.getAllTBBZJXX();
			for (TBBZJXXBYQ tbbzjxxXB : tbbzjxxXBList) {
				tbbzjxxLocal = new TBBZJXXLocal();
				tbbzjxxLocal.setGxrq(tbbzjxxXB.getGxrq());
				tbbzjxxLocal.setGsbm(tbbzjxxXB.getGsbm());
				tbbzjxxLocal.setNf(tbbzjxxXB.getNf());
				tbbzjxxLocal.setYf(tbbzjxxXB.getYf());
				tbbzjxxLocal.setJe(tbbzjxxXB.getJe());
				tbbzjxxLocal.setSfdrwc(tbbzjxxXB.getSfdrwc());
				tbbzjxxLocal.setQybh(3);
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

	public TBBZJXXXLDao getTbbzjxxDLDao() {
		return tbbzjxxDLDao;
	}

	public void setTbbzjxxDLDao(TBBZJXXXLDao tbbzjxxDLDao) {
		this.tbbzjxxDLDao = tbbzjxxDLDao;
	}

	public TBBZJXXXLDao getTbbzjxxLLDao() {
		return tbbzjxxLLDao;
	}

	public void setTbbzjxxLLDao(TBBZJXXXLDao tbbzjxxLLDao) {
		this.tbbzjxxLLDao = tbbzjxxLLDao;
	}

	public TBBZJXXBYQDao getTbbzjxxTBDao() {
		return tbbzjxxTBDao;
	}

	public void setTbbzjxxTBDao(TBBZJXXBYQDao tbbzjxxTBDao) {
		this.tbbzjxxTBDao = tbbzjxxTBDao;
	}

	public TBBZJXXBYQDao getTbbzjxxSBDao() {
		return tbbzjxxSBDao;
	}

	public void setTbbzjxxSBDao(TBBZJXXBYQDao tbbzjxxSBDao) {
		this.tbbzjxxSBDao = tbbzjxxSBDao;
	}

	public TBBZJXXXLDao getTbbzjxxXLDao() {
		return tbbzjxxXLDao;
	}

	public void setTbbzjxxXLDao(TBBZJXXXLDao tbbzjxxXLDao) {
		this.tbbzjxxXLDao = tbbzjxxXLDao;
	}

	public TBBZJXXBYQDao getTbbzjxxXBDao() {
		return tbbzjxxXBDao;
	}

	public void setTbbzjxxXBDao(TBBZJXXBYQDao tbbzjxxXBDao) {
		this.tbbzjxxXBDao = tbbzjxxXBDao;
	}

}
