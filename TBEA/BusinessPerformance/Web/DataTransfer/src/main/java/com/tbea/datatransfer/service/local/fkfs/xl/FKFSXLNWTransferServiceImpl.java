package com.tbea.datatransfer.service.local.fkfs.xl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.fkfs.xl.FKFSXLNWLocalDao;
import com.tbea.datatransfer.model.dao.zjxl.fkfs.FKFSXLNWXLDao;
import com.tbea.datatransfer.model.entity.local.FKFSXLNWLocal;
import com.tbea.datatransfer.model.entity.zjxl.FKFSXLNWXL;

@Transactional("transactionManager")
public class FKFSXLNWTransferServiceImpl implements FKFSXLNWTransferService {

	private FKFSXLNWLocalDao fkfsxlnwLocalDao;

	private FKFSXLNWXLDao fkfsxlnwDLDao;

	private FKFSXLNWXLDao fkfsxlnwLLDao;

	private FKFSXLNWXLDao fkfsxlnwXLDao;

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
			// ll
			fkfsxlnwLocalDao.deleteFKFSXLNWLocalByQY(4);
			List<FKFSXLNWXL> fkfsxlnwLLList = fkfsxlnwLLDao.getAllFKFSXLNW();
			for (FKFSXLNWXL fkfsxlnwLL : fkfsxlnwLLList) {
				fkfsxlnwLocal = new FKFSXLNWLocal();
				fkfsxlnwLocal.setGxrq(fkfsxlnwLL.getGxrq());
				fkfsxlnwLocal.setGsbm(fkfsxlnwLL.getGsbm());
				fkfsxlnwLocal.setSfjzzb(fkfsxlnwLL.getSfjzzb());
				fkfsxlnwLocal.setNwhtddzlbs(fkfsxlnwLL.getNwhtddzlbs());
				fkfsxlnwLocal.setNwhtddzlje(fkfsxlnwLL.getNwhtddzlje());
				fkfsxlnwLocal.setN1_6_2_1bs(fkfsxlnwLL.getN1_6_2_1bs());
				fkfsxlnwLocal.setN1_6_2_1je(fkfsxlnwLL.getN1_6_2_1je());
				fkfsxlnwLocal.setN1_2_6_1bs(fkfsxlnwLL.getN1_2_6_1bs());
				fkfsxlnwLocal.setN1_2_6_1je(fkfsxlnwLL.getN1_2_6_1je());
				fkfsxlnwLocal.setN0_09_01bs(fkfsxlnwLL.getN0_09_01bs());
				fkfsxlnwLocal.setN0_09_01je(fkfsxlnwLL.getN0_09_01je());
				fkfsxlnwLocal.setQtbs(fkfsxlnwLL.getQtbs());
				fkfsxlnwLocal.setQtje(fkfsxlnwLL.getQtje());
				fkfsxlnwLocal.setZbqcgynhtbs(fkfsxlnwLL.getZbqcgynhtbs());
				fkfsxlnwLocal.setZbqcgynhtje(fkfsxlnwLL.getZbqcgynhtje());
				fkfsxlnwLocal.setSfdrwc(fkfsxlnwLL.getSfdrwc());
				fkfsxlnwLocal.setQybh(4);
				fkfsxlnwLocalDao.merge(fkfsxlnwLocal);
			}
			// xl
			fkfsxlnwLocalDao.deleteFKFSXLNWLocalByQY(5);
			List<FKFSXLNWXL> fkfsxlnwXLList = fkfsxlnwXLDao.getAllFKFSXLNW();
			for (FKFSXLNWXL fkfsxlnwXL : fkfsxlnwXLList) {
				fkfsxlnwLocal = new FKFSXLNWLocal();
				fkfsxlnwLocal.setGxrq(fkfsxlnwXL.getGxrq());
				fkfsxlnwLocal.setGsbm(fkfsxlnwXL.getGsbm());
				fkfsxlnwLocal.setSfjzzb(fkfsxlnwXL.getSfjzzb());
				fkfsxlnwLocal.setNwhtddzlbs(fkfsxlnwXL.getNwhtddzlbs());
				fkfsxlnwLocal.setNwhtddzlje(fkfsxlnwXL.getNwhtddzlje());
				fkfsxlnwLocal.setN1_6_2_1bs(fkfsxlnwXL.getN1_6_2_1bs());
				fkfsxlnwLocal.setN1_6_2_1je(fkfsxlnwXL.getN1_6_2_1je());
				fkfsxlnwLocal.setN1_2_6_1bs(fkfsxlnwXL.getN1_2_6_1bs());
				fkfsxlnwLocal.setN1_2_6_1je(fkfsxlnwXL.getN1_2_6_1je());
				fkfsxlnwLocal.setN0_09_01bs(fkfsxlnwXL.getN0_09_01bs());
				fkfsxlnwLocal.setN0_09_01je(fkfsxlnwXL.getN0_09_01je());
				fkfsxlnwLocal.setQtbs(fkfsxlnwXL.getQtbs());
				fkfsxlnwLocal.setQtje(fkfsxlnwXL.getQtje());
				fkfsxlnwLocal.setZbqcgynhtbs(fkfsxlnwXL.getZbqcgynhtbs());
				fkfsxlnwLocal.setZbqcgynhtje(fkfsxlnwXL.getZbqcgynhtje());
				fkfsxlnwLocal.setSfdrwc(fkfsxlnwXL.getSfdrwc());
				fkfsxlnwLocal.setQybh(5);
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

	public FKFSXLNWXLDao getFkfsxlnwDLDao() {
		return fkfsxlnwDLDao;
	}

	public void setFkfsxlnwDLDao(FKFSXLNWXLDao fkfsxlnwDLDao) {
		this.fkfsxlnwDLDao = fkfsxlnwDLDao;
	}

	public FKFSXLNWXLDao getFkfsxlnwLLDao() {
		return fkfsxlnwLLDao;
	}

	public void setFkfsxlnwLLDao(FKFSXLNWXLDao fkfsxlnwLLDao) {
		this.fkfsxlnwLLDao = fkfsxlnwLLDao;
	}

	public FKFSXLNWXLDao getFkfsxlnwXLDao() {
		return fkfsxlnwXLDao;
	}

	public void setFkfsxlnwXLDao(FKFSXLNWXLDao fkfsxlnwXLDao) {
		this.fkfsxlnwXLDao = fkfsxlnwXLDao;
	}

}
