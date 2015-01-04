package com.tbea.datatransfer.service.local.fkfs.byq;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.fkfs.byq.FKFSBYQNWLocalDao;
import com.tbea.datatransfer.model.dao.zjtb.fkfs.FKFSBYQNWTBDao;
import com.tbea.datatransfer.model.entity.local.FKFSBYQNWLocal;
import com.tbea.datatransfer.model.entity.zjtb.FKFSBYQNWTB;

@Transactional("transactionManager")
public class FKFSBYQNWTransferServiceImpl implements FKFSBYQNWTransferService {

	private FKFSBYQNWLocalDao fkfsbyqnwLocalDao;

	private FKFSBYQNWTBDao fkfsbyqnwTBDao;

	@Override
	public boolean transferFKFSBYQNW() {
		boolean result = false;
		try {
			// tb
			fkfsbyqnwLocalDao.deleteFKFSBYQNWLocalByQY(301);
			FKFSBYQNWLocal fkfsbyqnwLocal = null;
			List<FKFSBYQNWTB> fkfsbyqnwTBList = fkfsbyqnwTBDao.getAllFKFSBYQNWTB();
			for (FKFSBYQNWTB fkfsbyqnwTB : fkfsbyqnwTBList) {
				fkfsbyqnwLocal = new FKFSBYQNWLocal();
				fkfsbyqnwLocal.setGxrq(fkfsbyqnwTB.getGxrq());
				fkfsbyqnwLocal.setGsbm(fkfsbyqnwTB.getGsbm());
//				fkfsbyqnwLocal.setNy(fkfsbyqnwTB.getNy());
				fkfsbyqnwLocal.setNwhtddzlbs(fkfsbyqnwTB.getNwhtddzlbs());
				fkfsbyqnwLocal.setNwhtddzlje(fkfsbyqnwTB.getNwhtddzlje());
				fkfsbyqnwLocal.setN3_3_3_1bs(fkfsbyqnwTB.getN3_3_3_1bs());
				fkfsbyqnwLocal.setN3_3_3_1je(fkfsbyqnwTB.getN3_3_3_1je());
				fkfsbyqnwLocal.setN1_4_4_0d5_0d5bs(fkfsbyqnwTB.getN1_4_4_0d5_0d5bs());
				fkfsbyqnwLocal.setN1_4_4_0d5_0d5je(fkfsbyqnwTB.getN1_4_4_0d5_0d5je());
				fkfsbyqnwLocal.setN1_2_6d5_0d5bs(fkfsbyqnwTB.getN1_2_6d5_0d5bs());
				fkfsbyqnwLocal.setN1_2_6d5_0d5je(fkfsbyqnwTB.getN1_2_6d5_0d5je());
				fkfsbyqnwLocal.setN1_4_4d5_0d5bs(fkfsbyqnwTB.getN1_4_4d5_0d5bs());
				fkfsbyqnwLocal.setN1_4_4d5_0d5je(fkfsbyqnwTB.getN1_4_4d5_0d5je());
				fkfsbyqnwLocal.setQtybs(fkfsbyqnwTB.getQtybs());
				fkfsbyqnwLocal.setQtyje(fkfsbyqnwTB.getQtyje());
				fkfsbyqnwLocal.setQtebs(fkfsbyqnwTB.getQtebs());
				fkfsbyqnwLocal.setQteje(fkfsbyqnwTB.getQteje());
				fkfsbyqnwLocal.setSfdrwc(fkfsbyqnwTB.getSfdrwc());
				fkfsbyqnwLocal.setQybh(301);
				fkfsbyqnwLocalDao.merge(fkfsbyqnwLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public FKFSBYQNWLocalDao getFkfsbyqnwLocalDao() {
		return fkfsbyqnwLocalDao;
	}

	public void setFkfsbyqnwLocalDao(FKFSBYQNWLocalDao fkfsbyqnwLocalDao) {
		this.fkfsbyqnwLocalDao = fkfsbyqnwLocalDao;
	}

	public FKFSBYQNWTBDao getFkfsbyqnwTBDao() {
		return fkfsbyqnwTBDao;
	}

	public void setFkfsbyqnwTBDao(FKFSBYQNWTBDao fkfsbyqnwTBDao) {
		this.fkfsbyqnwTBDao = fkfsbyqnwTBDao;
	}

}
