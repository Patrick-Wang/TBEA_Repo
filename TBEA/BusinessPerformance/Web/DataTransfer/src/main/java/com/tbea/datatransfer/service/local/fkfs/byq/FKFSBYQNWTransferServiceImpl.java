package com.tbea.datatransfer.service.local.fkfs.byq;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.fkfs.byq.FKFSBYQNWLocalDao;
import com.tbea.datatransfer.model.dao.zjsb.fkfs.FKFSBYQNWSBDao;
import com.tbea.datatransfer.model.dao.zjtb.fkfs.FKFSBYQNWTBDao;
import com.tbea.datatransfer.model.entity.local.FKFSBYQNWLocal;
import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQNWBYQ;

@Transactional("transactionManager")
public class FKFSBYQNWTransferServiceImpl implements FKFSBYQNWTransferService {

	private FKFSBYQNWLocalDao fkfsbyqnwLocalDao;

	private FKFSBYQNWTBDao fkfsbyqnwTBDao;

	private FKFSBYQNWSBDao fkfsbyqnwSBDao;

	@Override
	public boolean transferFKFSBYQNW() {
		boolean result = false;
		try {
			// tb
			fkfsbyqnwLocalDao.deleteFKFSBYQNWLocalByQY(301);
			FKFSBYQNWLocal fkfsbyqnwLocal = null;
			List<FKFSBYQNWBYQ> fkfsbyqnwTBList = fkfsbyqnwTBDao
					.getAllFKFSBYQNW();
			for (FKFSBYQNWBYQ fkfsbyqnwTB : fkfsbyqnwTBList) {
				fkfsbyqnwLocal = new FKFSBYQNWLocal();
				fkfsbyqnwLocal.setGxrq(fkfsbyqnwTB.getGxrq());
				fkfsbyqnwLocal.setGsbm(fkfsbyqnwTB.getGsbm());
				// fkfsbyqnwLocal.setNy(fkfsbyqnwTB.getNy());
				fkfsbyqnwLocal.setNwhtddzlbs(fkfsbyqnwTB.getNwhtddzlbs());
				fkfsbyqnwLocal.setNwhtddzlje(fkfsbyqnwTB.getNwhtddzlje());
				fkfsbyqnwLocal.setN3_3_3_1bs(fkfsbyqnwTB.getN3_3_3_1bs());
				fkfsbyqnwLocal.setN3_3_3_1je(fkfsbyqnwTB.getN3_3_3_1je());
				fkfsbyqnwLocal.setN1_4_4_0d5_0d5bs(fkfsbyqnwTB
						.getN1_4_4_0d5_0d5bs());
				fkfsbyqnwLocal.setN1_4_4_0d5_0d5je(fkfsbyqnwTB
						.getN1_4_4_0d5_0d5je());
				fkfsbyqnwLocal.setN1_2_6d5_0d5bs(fkfsbyqnwTB
						.getN1_2_6d5_0d5bs());
				fkfsbyqnwLocal.setN1_2_6d5_0d5je(fkfsbyqnwTB
						.getN1_2_6d5_0d5je());
				fkfsbyqnwLocal.setN1_4_4d5_0d5bs(fkfsbyqnwTB
						.getN1_4_4d5_0d5bs());
				fkfsbyqnwLocal.setN1_4_4d5_0d5je(fkfsbyqnwTB
						.getN1_4_4d5_0d5je());
				fkfsbyqnwLocal.setQtybs(fkfsbyqnwTB.getQtybs());
				fkfsbyqnwLocal.setQtyje(fkfsbyqnwTB.getQtyje());
				fkfsbyqnwLocal.setQtebs(fkfsbyqnwTB.getQtebs());
				fkfsbyqnwLocal.setQteje(fkfsbyqnwTB.getQteje());
				fkfsbyqnwLocal.setSfdrwc(fkfsbyqnwTB.getSfdrwc());
				fkfsbyqnwLocal.setQybh(301);
				fkfsbyqnwLocalDao.merge(fkfsbyqnwLocal);
			}
			// sb
			fkfsbyqnwLocalDao.deleteFKFSBYQNWLocalByQY(1);

			List<FKFSBYQNWBYQ> fkfsbyqnwSBList = fkfsbyqnwSBDao
					.getAllFKFSBYQNW();
			for (FKFSBYQNWBYQ fkfsbyqnwSB : fkfsbyqnwSBList) {
				fkfsbyqnwLocal = new FKFSBYQNWLocal();
				fkfsbyqnwLocal.setGxrq(fkfsbyqnwSB.getGxrq());
				fkfsbyqnwLocal.setGsbm(fkfsbyqnwSB.getGsbm());
				// fkfsbyqnwLocal.setNy(fkfsbyqnwTB.getNy());
				fkfsbyqnwLocal.setNwhtddzlbs(fkfsbyqnwSB.getNwhtddzlbs());
				fkfsbyqnwLocal.setNwhtddzlje(fkfsbyqnwSB.getNwhtddzlje());
				fkfsbyqnwLocal.setN3_3_3_1bs(fkfsbyqnwSB.getN3_3_3_1bs());
				fkfsbyqnwLocal.setN3_3_3_1je(fkfsbyqnwSB.getN3_3_3_1je());
				fkfsbyqnwLocal.setN1_4_4_0d5_0d5bs(fkfsbyqnwSB
						.getN1_4_4_0d5_0d5bs());
				fkfsbyqnwLocal.setN1_4_4_0d5_0d5je(fkfsbyqnwSB
						.getN1_4_4_0d5_0d5je());
				fkfsbyqnwLocal.setN1_2_6d5_0d5bs(fkfsbyqnwSB
						.getN1_2_6d5_0d5bs());
				fkfsbyqnwLocal.setN1_2_6d5_0d5je(fkfsbyqnwSB
						.getN1_2_6d5_0d5je());
				fkfsbyqnwLocal.setN1_4_4d5_0d5bs(fkfsbyqnwSB
						.getN1_4_4d5_0d5bs());
				fkfsbyqnwLocal.setN1_4_4d5_0d5je(fkfsbyqnwSB
						.getN1_4_4d5_0d5je());
				fkfsbyqnwLocal.setQtybs(fkfsbyqnwSB.getQtybs());
				fkfsbyqnwLocal.setQtyje(fkfsbyqnwSB.getQtyje());
				fkfsbyqnwLocal.setQtebs(fkfsbyqnwSB.getQtebs());
				fkfsbyqnwLocal.setQteje(fkfsbyqnwSB.getQteje());
				fkfsbyqnwLocal.setSfdrwc(fkfsbyqnwSB.getSfdrwc());
				fkfsbyqnwLocal.setQybh(1);
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

	public FKFSBYQNWSBDao getFkfsbyqnwSBDao() {
		return fkfsbyqnwSBDao;
	}

	public void setFkfsbyqnwSBDao(FKFSBYQNWSBDao fkfsbyqnwSBDao) {
		this.fkfsbyqnwSBDao = fkfsbyqnwSBDao;
	}

}
