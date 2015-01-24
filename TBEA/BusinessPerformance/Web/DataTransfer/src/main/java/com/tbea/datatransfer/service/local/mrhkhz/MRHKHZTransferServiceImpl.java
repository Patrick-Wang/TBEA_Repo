package com.tbea.datatransfer.service.local.mrhkhz;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.mrhkhz.MRHKHZLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.mrhkhz.MRHKHZDLDao;
import com.tbea.datatransfer.model.dao.zjsb.mrhkhz.MRHKHZSBDao;
import com.tbea.datatransfer.model.dao.zjtb.mrhkhz.MRHKHZTBDao;
import com.tbea.datatransfer.model.entity.local.MRHKHZLocal;
import com.tbea.datatransfer.model.entity.zjbyq.MRHKHZBYQ;
import com.tbea.datatransfer.model.entity.zjxl.MRHKHZXL;

@Transactional("transactionManager")
public class MRHKHZTransferServiceImpl implements MRHKHZTransferService {

	private MRHKHZLocalDao mrhkhzLocalDao;

	private MRHKHZDLDao mrhkhzDLDao;

	private MRHKHZTBDao mrhkhzTBDao;

	private MRHKHZSBDao mrhkhzSBDao;

	@Override
	public boolean transferMRHKHZ() {
		boolean result = false;
		try {
			// dl
			mrhkhzLocalDao.deleteMRHKHZLocalByQY(6);
			MRHKHZLocal mrhkhzLocal = null;
			List<MRHKHZXL> mrhkhzDLList = mrhkhzDLDao.getAllMRHKHZ();
			for (MRHKHZXL mrhkhzDL : mrhkhzDLList) {
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
			List<MRHKHZBYQ> mrhkhzTBList = mrhkhzTBDao.getAllMRHKHZ();
			for (MRHKHZBYQ mrhkhzTB : mrhkhzTBList) {
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

			// sb
			mrhkhzLocalDao.deleteMRHKHZLocalByQY(1);
			List<MRHKHZBYQ> mrhkhzSBList = mrhkhzSBDao.getAllMRHKHZ();
			for (MRHKHZBYQ mrhkhzSB : mrhkhzSBList) {
				mrhkhzLocal = new MRHKHZLocal();
				mrhkhzLocal.setGxrq(mrhkhzSB.getGxrq());
				mrhkhzLocal.setHkrq(mrhkhzSB.getHkrq());
				mrhkhzLocal.setHkje(mrhkhzSB.getHkje());
				mrhkhzLocal.setQzqbbc(mrhkhzSB.getQzqbbc());
				mrhkhzLocal.setQzzqbc(mrhkhzSB.getQzzqbc());
				mrhkhzLocal.setYhkzkjysdhkje(mrhkhzSB.getYhkzkjysdhkje());
				mrhkhzLocal.setJzydyszkzmye(mrhkhzSB.getJzydyszkzmye());
				mrhkhzLocal.setJtxdydzjhlzb(mrhkhzSB.getJtxdydzjhlzb());
				mrhkhzLocal.setSfdrwc(mrhkhzSB.getSfdrwc());
				mrhkhzLocal.setQybh(1);
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

	public MRHKHZSBDao getMrhkhzSBDao() {
		return mrhkhzSBDao;
	}

	public void setMrhkhzSBDao(MRHKHZSBDao mrhkhzSBDao) {
		this.mrhkhzSBDao = mrhkhzSBDao;
	}

}
