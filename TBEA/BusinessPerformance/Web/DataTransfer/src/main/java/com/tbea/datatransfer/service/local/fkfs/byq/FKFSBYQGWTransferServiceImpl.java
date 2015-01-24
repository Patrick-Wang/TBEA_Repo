package com.tbea.datatransfer.service.local.fkfs.byq;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.fkfs.byq.FKFSBYQGWLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.fkfs.FKFSBYQGWBYQDao;
import com.tbea.datatransfer.model.entity.local.FKFSBYQGWLocal;
import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQGWBYQ;

@Transactional("transactionManager")
public class FKFSBYQGWTransferServiceImpl implements FKFSBYQGWTransferService {

	private FKFSBYQGWLocalDao fkfsbyqgwLocalDao;

	private FKFSBYQGWBYQDao fkfsbyqgwTBDao;

	private FKFSBYQGWBYQDao fkfsbyqgwSBDao;
	
	private FKFSBYQGWBYQDao fkfsbyqgwXBDao;

	@Override
	public boolean transferFKFSBYQGW() {
		boolean result = false;
		try {
			// tb
			fkfsbyqgwLocalDao.deleteFKFSBYQGWLocalByQY(301);
			FKFSBYQGWLocal fkfsbyqgwLocal = null;
			List<FKFSBYQGWBYQ> fkfsbyqgwTBList = fkfsbyqgwTBDao
					.getAllFKFSBYQGW();
			for (FKFSBYQGWBYQ fkfsbyqgwTB : fkfsbyqgwTBList) {
				fkfsbyqgwLocal = new FKFSBYQGWLocal();
				fkfsbyqgwLocal.setGxrq(fkfsbyqgwTB.getGxrq());
				fkfsbyqgwLocal.setGsbm(fkfsbyqgwTB.getGsbm());
				// fkfsbyqgwLocal.setNy(fkfsbyqgwTB.getNy());
				fkfsbyqgwLocal.setGwhtddzlbs(fkfsbyqgwTB.getGwhtddzlbs());
				fkfsbyqgwLocal.setGwhtddzlje(fkfsbyqgwTB.getGwhtddzlje());
				fkfsbyqgwLocal.setN3_4_2_1bs(fkfsbyqgwTB.getN3_4_2_1bs());
				fkfsbyqgwLocal.setN3_4_2_1je(fkfsbyqgwTB.getN3_4_2_1je());
				fkfsbyqgwLocal.setN3_4_2d5_0d5bs(fkfsbyqgwTB
						.getN3_4_2d5_0d5bs());
				fkfsbyqgwLocal.setN3_4_2d5_0d5je(fkfsbyqgwTB
						.getN3_4_2d5_0d5je());
				fkfsbyqgwLocal.setN0_9_0_1bs(fkfsbyqgwTB.getN0_9_0_1bs());
				fkfsbyqgwLocal.setN0_9_0_1je(fkfsbyqgwTB.getN0_9_0_1je());
				fkfsbyqgwLocal.setN1_4_4_1bs(fkfsbyqgwTB.getN1_4_4_1bs());
				fkfsbyqgwLocal.setN1_4_4_1je(fkfsbyqgwTB.getN1_4_4_1je());
				fkfsbyqgwLocal.setN1_4_4d5_0d5bs(fkfsbyqgwTB
						.getN1_4_4d5_0d5bs());
				fkfsbyqgwLocal.setN1_4_4d5_0d5je(fkfsbyqgwTB
						.getN1_4_4d5_0d5je());
				fkfsbyqgwLocal.setN0_10_0_0bs(fkfsbyqgwTB.getN0_10_0_0bs());
				fkfsbyqgwLocal.setN0_10_0_0je(fkfsbyqgwTB.getN0_10_0_0je());
				fkfsbyqgwLocal.setN9d5_0d5bs(fkfsbyqgwTB.getN9d5_0d5bs());
				fkfsbyqgwLocal.setN9d5_0d5je(fkfsbyqgwTB.getN9d5_0d5je());
				fkfsbyqgwLocal.setQtbs(fkfsbyqgwTB.getQtbs());
				fkfsbyqgwLocal.setQtje(fkfsbyqgwTB.getQtje());
				fkfsbyqgwLocal.setSfdrwc(fkfsbyqgwTB.getSfdrwc());
				fkfsbyqgwLocal.setQybh(301);
				fkfsbyqgwLocalDao.merge(fkfsbyqgwLocal);
			}
			// xb
			fkfsbyqgwLocalDao.deleteFKFSBYQGWLocalByQY(3);
			List<FKFSBYQGWBYQ> fkfsbyqgwXBList = fkfsbyqgwXBDao
					.getAllFKFSBYQGW();
			for (FKFSBYQGWBYQ fkfsbyqgwXB : fkfsbyqgwXBList) {
				fkfsbyqgwLocal = new FKFSBYQGWLocal();
				fkfsbyqgwLocal.setGxrq(fkfsbyqgwXB.getGxrq());
				fkfsbyqgwLocal.setGsbm(fkfsbyqgwXB.getGsbm());
				// fkfsbyqgwLocal.setNy(fkfsbyqgwTB.getNy());
				fkfsbyqgwLocal.setGwhtddzlbs(fkfsbyqgwXB.getGwhtddzlbs());
				fkfsbyqgwLocal.setGwhtddzlje(fkfsbyqgwXB.getGwhtddzlje());
				fkfsbyqgwLocal.setN3_4_2_1bs(fkfsbyqgwXB.getN3_4_2_1bs());
				fkfsbyqgwLocal.setN3_4_2_1je(fkfsbyqgwXB.getN3_4_2_1je());
				fkfsbyqgwLocal.setN3_4_2d5_0d5bs(fkfsbyqgwXB
						.getN3_4_2d5_0d5bs());
				fkfsbyqgwLocal.setN3_4_2d5_0d5je(fkfsbyqgwXB
						.getN3_4_2d5_0d5je());
				fkfsbyqgwLocal.setN0_9_0_1bs(fkfsbyqgwXB.getN0_9_0_1bs());
				fkfsbyqgwLocal.setN0_9_0_1je(fkfsbyqgwXB.getN0_9_0_1je());
				fkfsbyqgwLocal.setN1_4_4_1bs(fkfsbyqgwXB.getN1_4_4_1bs());
				fkfsbyqgwLocal.setN1_4_4_1je(fkfsbyqgwXB.getN1_4_4_1je());
				fkfsbyqgwLocal.setN1_4_4d5_0d5bs(fkfsbyqgwXB
						.getN1_4_4d5_0d5bs());
				fkfsbyqgwLocal.setN1_4_4d5_0d5je(fkfsbyqgwXB
						.getN1_4_4d5_0d5je());
				fkfsbyqgwLocal.setN0_10_0_0bs(fkfsbyqgwXB.getN0_10_0_0bs());
				fkfsbyqgwLocal.setN0_10_0_0je(fkfsbyqgwXB.getN0_10_0_0je());
				fkfsbyqgwLocal.setN9d5_0d5bs(fkfsbyqgwXB.getN9d5_0d5bs());
				fkfsbyqgwLocal.setN9d5_0d5je(fkfsbyqgwXB.getN9d5_0d5je());
				fkfsbyqgwLocal.setQtbs(fkfsbyqgwXB.getQtbs());
				fkfsbyqgwLocal.setQtje(fkfsbyqgwXB.getQtje());
				fkfsbyqgwLocal.setSfdrwc(fkfsbyqgwXB.getSfdrwc());
				fkfsbyqgwLocal.setQybh(3);
				fkfsbyqgwLocalDao.merge(fkfsbyqgwLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public FKFSBYQGWLocalDao getFkfsbyqgwLocalDao() {
		return fkfsbyqgwLocalDao;
	}

	public void setFkfsbyqgwLocalDao(FKFSBYQGWLocalDao fkfsbyqgwLocalDao) {
		this.fkfsbyqgwLocalDao = fkfsbyqgwLocalDao;
	}

	public FKFSBYQGWBYQDao getFkfsbyqgwTBDao() {
		return fkfsbyqgwTBDao;
	}

	public void setFkfsbyqgwTBDao(FKFSBYQGWBYQDao fkfsbyqgwTBDao) {
		this.fkfsbyqgwTBDao = fkfsbyqgwTBDao;
	}

	public FKFSBYQGWBYQDao getFkfsbyqgwSBDao() {
		return fkfsbyqgwSBDao;
	}

	public void setFkfsbyqgwSBDao(FKFSBYQGWBYQDao fkfsbyqgwSBDao) {
		this.fkfsbyqgwSBDao = fkfsbyqgwSBDao;
	}

	public FKFSBYQGWBYQDao getFkfsbyqgwXBDao() {
		return fkfsbyqgwXBDao;
	}

	public void setFkfsbyqgwXBDao(FKFSBYQGWBYQDao fkfsbyqgwXBDao) {
		this.fkfsbyqgwXBDao = fkfsbyqgwXBDao;
	}

}
