package com.tbea.datatransfer.service.local.fkfs.byq;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.fkfs.byq.FKFSBYQNWLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.fkfs.FKFSBYQNWBYQDao;
import com.tbea.datatransfer.model.entity.local.FKFSBYQNWLocal;
import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQNWBYQ;

@Transactional("transactionManager")
public class FKFSBYQNWTransferServiceImpl implements FKFSBYQNWTransferService {

	private FKFSBYQNWLocalDao fkfsbyqnwLocalDao;

	private FKFSBYQNWBYQDao fkfsbyqnwTBDao;

	private FKFSBYQNWBYQDao fkfsbyqnwSBDao;

	private FKFSBYQNWBYQDao fkfsbyqnwXBDao;

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
			// xb
			fkfsbyqnwLocalDao.deleteFKFSBYQNWLocalByQY(3);

			List<FKFSBYQNWBYQ> fkfsbyqnwXBList = fkfsbyqnwXBDao
					.getAllFKFSBYQNW();
			for (FKFSBYQNWBYQ fkfsbyqnwXB : fkfsbyqnwXBList) {
				fkfsbyqnwLocal = new FKFSBYQNWLocal();
				fkfsbyqnwLocal.setGxrq(fkfsbyqnwXB.getGxrq());
				fkfsbyqnwLocal.setGsbm(fkfsbyqnwXB.getGsbm());
				// fkfsbyqnwLocal.setNy(fkfsbyqnwTB.getNy());
				fkfsbyqnwLocal.setNwhtddzlbs(fkfsbyqnwXB.getNwhtddzlbs());
				fkfsbyqnwLocal.setNwhtddzlje(fkfsbyqnwXB.getNwhtddzlje());
				fkfsbyqnwLocal.setN3_3_3_1bs(fkfsbyqnwXB.getN3_3_3_1bs());
				fkfsbyqnwLocal.setN3_3_3_1je(fkfsbyqnwXB.getN3_3_3_1je());
				fkfsbyqnwLocal.setN1_4_4_0d5_0d5bs(fkfsbyqnwXB
						.getN1_4_4_0d5_0d5bs());
				fkfsbyqnwLocal.setN1_4_4_0d5_0d5je(fkfsbyqnwXB
						.getN1_4_4_0d5_0d5je());
				fkfsbyqnwLocal.setN1_2_6d5_0d5bs(fkfsbyqnwXB
						.getN1_2_6d5_0d5bs());
				fkfsbyqnwLocal.setN1_2_6d5_0d5je(fkfsbyqnwXB
						.getN1_2_6d5_0d5je());
				fkfsbyqnwLocal.setN1_4_4d5_0d5bs(fkfsbyqnwXB
						.getN1_4_4d5_0d5bs());
				fkfsbyqnwLocal.setN1_4_4d5_0d5je(fkfsbyqnwXB
						.getN1_4_4d5_0d5je());
				fkfsbyqnwLocal.setQtybs(fkfsbyqnwXB.getQtybs());
				fkfsbyqnwLocal.setQtyje(fkfsbyqnwXB.getQtyje());
				fkfsbyqnwLocal.setQtebs(fkfsbyqnwXB.getQtebs());
				fkfsbyqnwLocal.setQteje(fkfsbyqnwXB.getQteje());
				fkfsbyqnwLocal.setSfdrwc(fkfsbyqnwXB.getSfdrwc());
				fkfsbyqnwLocal.setQybh(3);
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

	public FKFSBYQNWBYQDao getFkfsbyqnwTBDao() {
		return fkfsbyqnwTBDao;
	}

	public void setFkfsbyqnwTBDao(FKFSBYQNWBYQDao fkfsbyqnwTBDao) {
		this.fkfsbyqnwTBDao = fkfsbyqnwTBDao;
	}

	public FKFSBYQNWBYQDao getFkfsbyqnwSBDao() {
		return fkfsbyqnwSBDao;
	}

	public void setFkfsbyqnwSBDao(FKFSBYQNWBYQDao fkfsbyqnwSBDao) {
		this.fkfsbyqnwSBDao = fkfsbyqnwSBDao;
	}

	public FKFSBYQNWBYQDao getFkfsbyqnwXBDao() {
		return fkfsbyqnwXBDao;
	}

	public void setFkfsbyqnwXBDao(FKFSBYQNWBYQDao fkfsbyqnwXBDao) {
		this.fkfsbyqnwXBDao = fkfsbyqnwXBDao;
	}

}
