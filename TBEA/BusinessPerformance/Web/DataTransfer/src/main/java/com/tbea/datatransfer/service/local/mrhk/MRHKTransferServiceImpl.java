package com.tbea.datatransfer.service.local.mrhk;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.mrhk.MRHKLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.mrhk.MRHKDLDao;
import com.tbea.datatransfer.model.dao.zjsb.mrhk.MRHKSBDao;
import com.tbea.datatransfer.model.dao.zjtb.mrhk.MRHKTBDao;
import com.tbea.datatransfer.model.entity.local.MRHKLocal;
import com.tbea.datatransfer.model.entity.zjdl.MRHKDL;
import com.tbea.datatransfer.model.entity.zjsb.MRHKSB;
import com.tbea.datatransfer.model.entity.zjtb.MRHKTB;

@Transactional("transactionManager")
public class MRHKTransferServiceImpl implements MRHKTransferService {

	private MRHKLocalDao mrhkLocalDao;

	private MRHKDLDao mrhkDLDao;

	private MRHKTBDao mrhkTBDao;

	private MRHKSBDao mrhkSBDao;
	
	@Override
	public boolean transferMRHK() {
		boolean result = false;
		try {
			// dl
			mrhkLocalDao.deleteMRHKLocalByQY(6);
			MRHKLocal mrhkLocal = null;
			List<MRHKDL> mrhkDLList = mrhkDLDao.getAllMRHKDL();
			for (MRHKDL mrhkDL : mrhkDLList) {
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
			// tb
			mrhkLocalDao.deleteMRHKLocalByQY(301);
			List<MRHKTB> mrhkTBList = mrhkTBDao.getAllMRHKTB();
			for (MRHKTB mrhkTB : mrhkTBList) {
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
			List<MRHKSB> mrhkSBList = mrhkSBDao.getAllMRHKSB();
			for (MRHKSB mrhkSB : mrhkSBList) {
				mrhkLocal = new MRHKLocal();
				mrhkLocal.setGxrq(mrhkSB.getGxrq());
				mrhkLocal.setXmgs(mrhkSB.getXmgs());
				mrhkLocal.setHkxz(mrhkSB.getHkxz());
				mrhkLocal.setHkrq(mrhkSB.getHkrq());
				mrhkLocal.setHkje(mrhkSB.getHkje());
				mrhkLocal.setSfdrwc(mrhkSB.getSfdrwc());
				mrhkLocal.setQybh(301);
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

	public MRHKDLDao getMrhkDLDao() {
		return mrhkDLDao;
	}

	public void setMrhkDLDao(MRHKDLDao mrhkDLDao) {
		this.mrhkDLDao = mrhkDLDao;
	}

	public MRHKTBDao getMrhkTBDao() {
		return mrhkTBDao;
	}

	public void setMrhkTBDao(MRHKTBDao mrhkTBDao) {
		this.mrhkTBDao = mrhkTBDao;
	}

}
