package com.tbea.datatransfer.service.local.mrhk;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.mrhk.MRHKLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.mrhk.MRHKBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.mrhk.MRHKXLDao;
import com.tbea.datatransfer.model.entity.local.MRHKLocal;
import com.tbea.datatransfer.model.entity.zjbyq.MRHKBYQ;
import com.tbea.datatransfer.model.entity.zjxl.MRHKXL;

@Transactional("transactionManager")
public class MRHKTransferServiceImpl implements MRHKTransferService {

	private MRHKLocalDao mrhkLocalDao;

	private MRHKXLDao mrhkDLDao;

	private MRHKXLDao mrhkLLDao;

	private MRHKBYQDao mrhkTBDao;

	private MRHKBYQDao mrhkSBDao;

	@Override
	public boolean transferMRHK() {
		boolean result = false;
		try {
			// dl
			mrhkLocalDao.deleteMRHKLocalByQY(6);
			MRHKLocal mrhkLocal = null;
			List<MRHKXL> mrhkDLList = mrhkDLDao.getAllMRHK();
			for (MRHKXL mrhkDL : mrhkDLList) {
				mrhkLocal = new MRHKLocal();
				mrhkLocal.setGxrq(mrhkDL.getGxrq());
				mrhkLocal.setXmgs(mrhkDL.getXmgs());
				mrhkLocal.setHkxz(mrhkDL.getHkxz());
				mrhkLocal.setHkrq(mrhkDL.getHkrq());
				mrhkLocal.setHkje(mrhkDL.getHkje());
				mrhkLocal.setSfdrwc(mrhkDL.getSfdrwc());
				mrhkLocal.setQybh(6);
				mrhkLocalDao.merge(mrhkLocal);
			}
			// ll
			mrhkLocalDao.deleteMRHKLocalByQY(4);
			List<MRHKXL> mrhkLLList = mrhkLLDao.getAllMRHK();
			for (MRHKXL mrhkLL : mrhkLLList) {
				mrhkLocal = new MRHKLocal();
				mrhkLocal.setGxrq(mrhkLL.getGxrq());
				mrhkLocal.setXmgs(mrhkLL.getXmgs());
				mrhkLocal.setHkxz(mrhkLL.getHkxz());
				mrhkLocal.setHkrq(mrhkLL.getHkrq());
				mrhkLocal.setHkje(mrhkLL.getHkje());
				mrhkLocal.setSfdrwc(mrhkLL.getSfdrwc());
				mrhkLocal.setQybh(4);
				mrhkLocalDao.merge(mrhkLocal);
			}
			// tb
			mrhkLocalDao.deleteMRHKLocalByQY(301);
			List<MRHKBYQ> mrhkTBList = mrhkTBDao.getAllMRHK();
			for (MRHKBYQ mrhkTB : mrhkTBList) {
				mrhkLocal = new MRHKLocal();
				mrhkLocal.setGxrq(mrhkTB.getGxrq());
				mrhkLocal.setXmgs(mrhkTB.getXmgs());
				mrhkLocal.setHkxz(mrhkTB.getHkxz());
				mrhkLocal.setHkrq(mrhkTB.getHkrq());
				mrhkLocal.setHkje(mrhkTB.getHkje());
				mrhkLocal.setSfdrwc(mrhkTB.getSfdrwc());
				mrhkLocal.setQybh(301);
				mrhkLocalDao.merge(mrhkLocal);
			}

			// sb
			mrhkLocalDao.deleteMRHKLocalByQY(1);
			List<MRHKBYQ> mrhkSBList = mrhkSBDao.getAllMRHK();
			for (MRHKBYQ mrhkSB : mrhkSBList) {
				mrhkLocal = new MRHKLocal();
				mrhkLocal.setGxrq(mrhkSB.getGxrq());
				mrhkLocal.setXmgs(mrhkSB.getXmgs());
				mrhkLocal.setHkxz(mrhkSB.getHkxz());
				mrhkLocal.setHkrq(mrhkSB.getHkrq());
				mrhkLocal.setHkje(mrhkSB.getHkje());
				mrhkLocal.setSfdrwc(mrhkSB.getSfdrwc());
				mrhkLocal.setQybh(1);
				mrhkLocalDao.merge(mrhkLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public MRHKLocalDao getMrhkLocalDao() {
		return mrhkLocalDao;
	}

	public void setMrhkLocalDao(MRHKLocalDao mrhkLocalDao) {
		this.mrhkLocalDao = mrhkLocalDao;
	}

	public MRHKXLDao getMrhkDLDao() {
		return mrhkDLDao;
	}

	public void setMrhkDLDao(MRHKXLDao mrhkDLDao) {
		this.mrhkDLDao = mrhkDLDao;
	}

	public MRHKBYQDao getMrhkTBDao() {
		return mrhkTBDao;
	}

	public void setMrhkTBDao(MRHKBYQDao mrhkTBDao) {
		this.mrhkTBDao = mrhkTBDao;
	}

	public MRHKBYQDao getMrhkSBDao() {
		return mrhkSBDao;
	}

	public void setMrhkSBDao(MRHKBYQDao mrhkSBDao) {
		this.mrhkSBDao = mrhkSBDao;
	}

	public MRHKXLDao getMrhkLLDao() {
		return mrhkLLDao;
	}

	public void setMrhkLLDao(MRHKXLDao mrhkLLDao) {
		this.mrhkLLDao = mrhkLLDao;
	}

}
