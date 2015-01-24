package com.tbea.datatransfer.service.local.fkfs.xl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.fkfs.xl.FKFSXLGWLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.fkfs.FKFSXLGWDLDao;
import com.tbea.datatransfer.model.entity.local.FKFSXLGWLocal;
import com.tbea.datatransfer.model.entity.zjxl.FKFSXLGWXL;

@Transactional("transactionManager")
public class FKFSXLGWTransferServiceImpl implements FKFSXLGWTransferService {

	private FKFSXLGWLocalDao fkfsxlgwLocalDao;

	private FKFSXLGWDLDao fkfsxlgwDLDao;

	@Override
	public boolean transferFKFSXLGW() {
		boolean result = false;
		try {
			// dl
			fkfsxlgwLocalDao.deleteFKFSXLGWLocalByQY(6);
			FKFSXLGWLocal fkfsxlgwLocal = null;
			List<FKFSXLGWXL> fkfsxlgwDLList = fkfsxlgwDLDao.getAllFKFSXLGW();
			for (FKFSXLGWXL fkfsxlgwDL : fkfsxlgwDLList) {
				fkfsxlgwLocal = new FKFSXLGWLocal();
				fkfsxlgwLocal.setGxrq(fkfsxlgwDL.getGxrq());
				fkfsxlgwLocal.setGsbm(fkfsxlgwDL.getGsbm());
				fkfsxlgwLocal.setSfjzzb(fkfsxlgwDL.getSfjzzb());
				fkfsxlgwLocal.setGwhtddzlbs(fkfsxlgwDL.getGwhtddzlbs());
				fkfsxlgwLocal.setGwhtddzlje(fkfsxlgwDL.getGwhtddzlje());
				fkfsxlgwLocal.setN3_06_0_01bs(fkfsxlgwDL.getN3_06_0_01bs());
				fkfsxlgwLocal.setN3_06_0_01je(fkfsxlgwDL.getN3_06_0_01je());
				fkfsxlgwLocal.setN0_09_0_01bs(fkfsxlgwDL.getN0_09_0_01bs());
				fkfsxlgwLocal.setN0_09_0_01je(fkfsxlgwDL.getN0_09_0_01je());
				fkfsxlgwLocal.setN3_4_2_1bs(fkfsxlgwDL.getN3_4_2_1bs());
				fkfsxlgwLocal.setN3_4_2_1je(fkfsxlgwDL.getN3_4_2_1je());
				fkfsxlgwLocal.setN2_5_2_1bs(fkfsxlgwDL.getN2_5_2_1bs());
				fkfsxlgwLocal.setN2_5_2_1je(fkfsxlgwDL.getN2_5_2_1je());
				fkfsxlgwLocal.setN2_5_2d5_0d5bs(fkfsxlgwDL.getN2_5_2d5_0d5bs());
				fkfsxlgwLocal.setN2_5_2d5_0d5je(fkfsxlgwDL.getN2_5_2d5_0d5je());
				fkfsxlgwLocal.setN0_10_0_0bs(fkfsxlgwDL.getN0_10_0_0bs());
				fkfsxlgwLocal.setN0_10_0_0je(fkfsxlgwDL.getN0_10_0_0je());
				fkfsxlgwLocal.setN0_9d5_0d5bs(fkfsxlgwDL.getN0_9d5_0d5bs());
				fkfsxlgwLocal.setN0_9d5_0d5je(fkfsxlgwDL.getN0_9d5_0d5je());
				fkfsxlgwLocal.setQtbs(fkfsxlgwDL.getQtbs());
				fkfsxlgwLocal.setQtje(fkfsxlgwDL.getQtje());
				fkfsxlgwLocal.setWzbjhtbs(fkfsxlgwDL.getWzbjhtbs());
				fkfsxlgwLocal.setWzbjhtje(fkfsxlgwDL.getWzbjhtje());
				fkfsxlgwLocal.setZbqcgynhtbs(fkfsxlgwDL.getZbqcgynhtbs());
				fkfsxlgwLocal.setZbqcgynhtje(fkfsxlgwDL.getZbqcgynhtje());
				fkfsxlgwLocal.setSfdrwc(fkfsxlgwDL.getSfdrwc());
				fkfsxlgwLocal.setQybh(6);
				fkfsxlgwLocalDao.merge(fkfsxlgwLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public FKFSXLGWLocalDao getFkfsxlgwLocalDao() {
		return fkfsxlgwLocalDao;
	}

	public void setFkfsxlgwLocalDao(FKFSXLGWLocalDao fkfsxlgwLocalDao) {
		this.fkfsxlgwLocalDao = fkfsxlgwLocalDao;
	}

	public FKFSXLGWDLDao getFkfsxlgwDLDao() {
		return fkfsxlgwDLDao;
	}

	public void setFkfsxlgwDLDao(FKFSXLGWDLDao fkfsxlgwDLDao) {
		this.fkfsxlgwDLDao = fkfsxlgwDLDao;
	}

}
