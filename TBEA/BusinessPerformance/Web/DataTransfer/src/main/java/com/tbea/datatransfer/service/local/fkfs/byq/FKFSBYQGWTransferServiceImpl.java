package com.tbea.datatransfer.service.local.fkfs.byq;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.fkfs.byq.FKFSBYQGWLocalDao;
import com.tbea.datatransfer.model.dao.zjsb.fkfs.FKFSBYQGWSBDao;
import com.tbea.datatransfer.model.dao.zjtb.fkfs.FKFSBYQGWTBDao;
import com.tbea.datatransfer.model.entity.local.FKFSBYQGWLocal;
import com.tbea.datatransfer.model.entity.zjsb.FKFSBYQGWSB;
import com.tbea.datatransfer.model.entity.zjtb.FKFSBYQGWTB;

@Transactional("transactionManager")
public class FKFSBYQGWTransferServiceImpl implements FKFSBYQGWTransferService {

	private FKFSBYQGWLocalDao fkfsbyqgwLocalDao;

	private FKFSBYQGWTBDao fkfsbyqgwTBDao;
	
	private FKFSBYQGWSBDao fkfsbyqgwSBDao;

	@Override
	public boolean transferFKFSBYQGW() {
		boolean result = false;
		try {
			// tb
			fkfsbyqgwLocalDao.deleteFKFSBYQGWLocalByQY(301);
			FKFSBYQGWLocal fkfsbyqgwLocal = null;
			List<FKFSBYQGWTB> fkfsbyqgwTBList = fkfsbyqgwTBDao
					.getAllFKFSBYQGWTB();
			for (FKFSBYQGWTB fkfsbyqgwTB : fkfsbyqgwTBList) {
				fkfsbyqgwLocal = new FKFSBYQGWLocal();
				fkfsbyqgwLocal.setGxrq(fkfsbyqgwTB.getGxrq());
				fkfsbyqgwLocal.setGsbm(fkfsbyqgwTB.getGsbm());
//				fkfsbyqgwLocal.setNy(fkfsbyqgwTB.getNy());
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
				fkfsbyqgwLocal.setN1_4_4d5_0d5bs(fkfsbyqgwTB.getN1_4_4d5_0d5bs());
				fkfsbyqgwLocal.setN1_4_4d5_0d5je(fkfsbyqgwTB.getN1_4_4d5_0d5je());
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
			// sb
			fkfsbyqgwLocalDao.deleteFKFSBYQGWLocalByQY(1);
			
			List<FKFSBYQGWSB> fkfsbyqgwSBList = fkfsbyqgwSBDao
					.getAllFKFSBYQGWSB();
			for (FKFSBYQGWTB fkfsbyqgwSB : fkfsbyqgwTBList) {
				fkfsbyqgwLocal = new FKFSBYQGWLocal();
				fkfsbyqgwLocal.setGxrq(fkfsbyqgwSB.getGxrq());
				fkfsbyqgwLocal.setGsbm(fkfsbyqgwSB.getGsbm());
//				fkfsbyqgwLocal.setNy(fkfsbyqgwTB.getNy());
				fkfsbyqgwLocal.setGwhtddzlbs(fkfsbyqgwSB.getGwhtddzlbs());
				fkfsbyqgwLocal.setGwhtddzlje(fkfsbyqgwSB.getGwhtddzlje());
				fkfsbyqgwLocal.setN3_4_2_1bs(fkfsbyqgwSB.getN3_4_2_1bs());
				fkfsbyqgwLocal.setN3_4_2_1je(fkfsbyqgwSB.getN3_4_2_1je());
				fkfsbyqgwLocal.setN3_4_2d5_0d5bs(fkfsbyqgwSB
						.getN3_4_2d5_0d5bs());
				fkfsbyqgwLocal.setN3_4_2d5_0d5je(fkfsbyqgwSB
						.getN3_4_2d5_0d5je());
				fkfsbyqgwLocal.setN0_9_0_1bs(fkfsbyqgwSB.getN0_9_0_1bs());
				fkfsbyqgwLocal.setN0_9_0_1je(fkfsbyqgwSB.getN0_9_0_1je());
				fkfsbyqgwLocal.setN1_4_4_1bs(fkfsbyqgwSB.getN1_4_4_1bs());
				fkfsbyqgwLocal.setN1_4_4_1je(fkfsbyqgwSB.getN1_4_4_1je());
				fkfsbyqgwLocal.setN1_4_4d5_0d5bs(fkfsbyqgwSB.getN1_4_4d5_0d5bs());
				fkfsbyqgwLocal.setN1_4_4d5_0d5je(fkfsbyqgwSB.getN1_4_4d5_0d5je());
				fkfsbyqgwLocal.setN0_10_0_0bs(fkfsbyqgwSB.getN0_10_0_0bs());
				fkfsbyqgwLocal.setN0_10_0_0je(fkfsbyqgwSB.getN0_10_0_0je());
				fkfsbyqgwLocal.setN9d5_0d5bs(fkfsbyqgwSB.getN9d5_0d5bs());
				fkfsbyqgwLocal.setN9d5_0d5je(fkfsbyqgwSB.getN9d5_0d5je());
				fkfsbyqgwLocal.setQtbs(fkfsbyqgwSB.getQtbs());
				fkfsbyqgwLocal.setQtje(fkfsbyqgwSB.getQtje());
				fkfsbyqgwLocal.setSfdrwc(fkfsbyqgwSB.getSfdrwc());
				fkfsbyqgwLocal.setQybh(301);
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

	public FKFSBYQGWTBDao getFkfsbyqgwTBDao() {
		return fkfsbyqgwTBDao;
	}

	public void setFkfsbyqgwTBDao(FKFSBYQGWTBDao fkfsbyqgwTBDao) {
		this.fkfsbyqgwTBDao = fkfsbyqgwTBDao;
	}

}
