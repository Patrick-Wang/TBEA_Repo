package com.tbea.datatransfer.service.local.mrhkhz;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.mrhkhz.MRHKHZLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.mrhkhz.MRHKHZDLDao;
import com.tbea.datatransfer.model.dao.zjtb.mrhkhz.MRHKHZTBDao;
import com.tbea.datatransfer.model.entity.local.MRHKHZLocal;
import com.tbea.datatransfer.model.entity.zjdl.MRHKHZDL;
import com.tbea.datatransfer.model.entity.zjtb.MRHKHZTB;

@Transactional("transactionManager")
public class MRHKHZTransferServiceImpl implements MRHKHZTransferService {

	private MRHKHZLocalDao mrhkhzLocalDao;

	private MRHKHZDLDao mrhkhzDLDao;

	private MRHKHZTBDao mrhkhzTBDao;

	@Override
	public boolean transferMRHKHZ() {
		boolean result = false;
		try {
			// dl
			mrhkhzLocalDao.deleteMRHKHZLocalByQY(6);
			MRHKHZLocal mrhkhzLocal = null;
			List<MRHKHZDL> mrhkhzDLList = mrhkhzDLDao.getAllMRHKHZDL();
			for (MRHKHZDL mrhkhzDL : mrhkhzDLList) {
				mrhkhzLocal = new MRHKHZLocal();
				mrhkhzLocal.setGxrq(mrhkhzDL.getGxrq());
				mrhkhzLocal.setHkrq(mrhkhzDL.getHkrq());
				mrhkhzLocal.setHkje(mrhkhzDL.getHkje());
				mrhkhzLocal.setQzqbbc(mrhkhzDL.getQzqbbc());
				mrhkhzLocal.setQzzqbc(mrhkhzDL.getQzzqbc());
				mrhkhzLocal.setYhkzkjysdhkje(mrhkhzDL.getYhkzkjysdhkje());
				mrhkhzLocal.setJzydyszkzmye(mrhkhzDL.getJzydyszkzmye());
				mrhkhzLocal.setJtxdydzjhlzb(mrhkhzDL.getJtxdydzjhlzb());
				mrhkhzLocal.setSfdrwc(mrhkhzDL.getSfdrwc());
				mrhkhzLocal.setQybh(6);
				mrhkhzLocalDao.merge(mrhkhzLocal);
			}
			// tb
			mrhkhzLocalDao.deleteMRHKHZLocalByQY(301);
			List<MRHKHZTB> mrhkhzTBList = mrhkhzTBDao.getAllMRHKHZTB();
			for (MRHKHZTB mrhkhzTB : mrhkhzTBList) {
				mrhkhzLocal = new MRHKHZLocal();
				mrhkhzLocal.setGxrq(mrhkhzTB.getGxrq());
				mrhkhzLocal.setHkrq(mrhkhzTB.getHkrq());
				mrhkhzLocal.setHkje(mrhkhzTB.getHkje());
				mrhkhzLocal.setQzqbbc(mrhkhzTB.getQzqbbc());
				mrhkhzLocal.setQzzqbc(mrhkhzTB.getQzzqbc());
				mrhkhzLocal.setYhkzkjysdhkje(mrhkhzTB.getYhkzkjysdhkje());
				mrhkhzLocal.setJzydyszkzmye(mrhkhzTB.getJzydyszkzmye());
				mrhkhzLocal.setJtxdydzjhlzb(mrhkhzTB.getJtxdydzjhlzb());
				mrhkhzLocal.setSfdrwc(mrhkhzTB.getSfdrwc());
				mrhkhzLocal.setQybh(301);
				mrhkhzLocalDao.merge(mrhkhzLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public MRHKHZLocalDao getMrhkhzLocalDao() {
		return mrhkhzLocalDao;
	}

	public void setMrhkhzLocalDao(MRHKHZLocalDao mrhkhzLocalDao) {
		this.mrhkhzLocalDao = mrhkhzLocalDao;
	}

	public MRHKHZDLDao getMrhkhzDLDao() {
		return mrhkhzDLDao;
	}

	public void setMrhkhzDLDao(MRHKHZDLDao mrhkhzDLDao) {
		this.mrhkhzDLDao = mrhkhzDLDao;
	}

	public MRHKHZTBDao getMrhkhzTBDao() {
		return mrhkhzTBDao;
	}

	public void setMrhkhzTBDao(MRHKHZTBDao mrhkhzTBDao) {
		this.mrhkhzTBDao = mrhkhzTBDao;
	}

}
