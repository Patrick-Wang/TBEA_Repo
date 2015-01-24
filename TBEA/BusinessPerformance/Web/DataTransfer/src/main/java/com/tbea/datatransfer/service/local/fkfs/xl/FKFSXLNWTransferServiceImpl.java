package com.tbea.datatransfer.service.local.fkfs.xl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.fkfs.xl.FKFSXLNWLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.fkfs.FKFSXLNWDLDao;
import com.tbea.datatransfer.model.entity.local.FKFSXLNWLocal;
import com.tbea.datatransfer.model.entity.zjxl.FKFSXLNWXL;

@Transactional("transactionManager")
public class FKFSXLNWTransferServiceImpl implements FKFSXLNWTransferService {

	private FKFSXLNWLocalDao fkfsxlnwLocalDao;

	private FKFSXLNWDLDao fkfsxlnwDLDao;

	@Override
	public boolean transferFKFSXLNW() {
		boolean result = false;
		try {
			// dl
			fkfsxlnwLocalDao.deleteFKFSXLNWLocalByQY(6);
			FKFSXLNWLocal fkfsxlnwLocal = null;
			List<FKFSXLNWXL> fkfsxlnwDLList = fkfsxlnwDLDao.getAllFKFSXLNW();
			for (FKFSXLNWXL fkfsxlnwDL : fkfsxlnwDLList) {
				fkfsxlnwLocal = new FKFSXLNWLocal();
				fkfsxlnwLocal.setGxrq(fkfsxlnwDL.getGxrq());
				fkfsxlnwLocal.setGsbm(fkfsxlnwDL.getGsbm());
				fkfsxlnwLocal.setSfjzzb(fkfsxlnwDL.getSfjzzb());
				fkfsxlnwLocal.setNwhtddzlbs(fkfsxlnwDL.getNwhtddzlbs());
				fkfsxlnwLocal.setNwhtddzlje(fkfsxlnwDL.getNwhtddzlje());
				fkfsxlnwLocal.setN1_6_2_1bs(fkfsxlnwDL.getN1_6_2_1bs());
				fkfsxlnwLocal.setN1_6_2_1je(fkfsxlnwDL.getN1_6_2_1je());
				fkfsxlnwLocal.setN1_2_6_1bs(fkfsxlnwDL.getN1_2_6_1bs());
				fkfsxlnwLocal.setN1_2_6_1je(fkfsxlnwDL.getN1_2_6_1je());
				fkfsxlnwLocal.setN0_09_01bs(fkfsxlnwDL.getN0_09_01bs());
				fkfsxlnwLocal.setN0_09_01je(fkfsxlnwDL.getN0_09_01je());
				fkfsxlnwLocal.setQtbs(fkfsxlnwDL.getQtbs());
				fkfsxlnwLocal.setQtje(fkfsxlnwDL.getQtje());
				fkfsxlnwLocal.setZbqcgynhtbs(fkfsxlnwDL.getZbqcgynhtbs());
				fkfsxlnwLocal.setZbqcgynhtje(fkfsxlnwDL.getZbqcgynhtje());
				fkfsxlnwLocal.setSfdrwc(fkfsxlnwDL.getSfdrwc());
				fkfsxlnwLocal.setQybh(6);
				fkfsxlnwLocalDao.merge(fkfsxlnwLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public FKFSXLNWLocalDao getFkfsxlnwLocalDao() {
		return fkfsxlnwLocalDao;
	}

	public void setFkfsxlnwLocalDao(FKFSXLNWLocalDao fkfsxlnwLocalDao) {
		this.fkfsxlnwLocalDao = fkfsxlnwLocalDao;
	}

	public FKFSXLNWDLDao getFkfsxlnwDLDao() {
		return fkfsxlnwDLDao;
	}

	public void setFkfsxlnwDLDao(FKFSXLNWDLDao fkfsxlnwDLDao) {
		this.fkfsxlnwDLDao = fkfsxlnwDLDao;
	}

}
