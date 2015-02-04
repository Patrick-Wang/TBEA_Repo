package com.tbea.datatransfer.service.local.fkfs.xl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.fkfs.xl.FKFSXLGWLocalDao;
import com.tbea.datatransfer.model.dao.zjxl.fkfs.FKFSXLGWXLDao;
import com.tbea.datatransfer.model.entity.local.FKFSXLGWLocal;
import com.tbea.datatransfer.model.entity.zjxl.FKFSXLGWXL;

@Transactional("transactionManager")
public class FKFSXLGWTransferServiceImpl implements FKFSXLGWTransferService {

	private FKFSXLGWLocalDao fkfsxlgwLocalDao;

	private FKFSXLGWXLDao fkfsxlgwDLDao;

	private FKFSXLGWXLDao fkfsxlgwLLDao;

	private FKFSXLGWXLDao fkfsxlgwXLDao;

	@Override
	public boolean transferFKFSXLGW() {
		boolean result = false;
		try {
			SimpleDateFormat month_sdf = new SimpleDateFormat("yyyyMM");
			Date gxrq = null;
			// dl
			fkfsxlgwLocalDao.deleteFKFSXLGWLocalByQY(6);
			FKFSXLGWLocal fkfsxlgwLocal = null;
			List<FKFSXLGWXL> fkfsxlgwDLList = fkfsxlgwDLDao.getAllFKFSXLGW();
			for (FKFSXLGWXL fkfsxlgwDL : fkfsxlgwDLList) {
				fkfsxlgwLocal = new FKFSXLGWLocal();
				gxrq = fkfsxlgwDL.getGxrq();
				fkfsxlgwLocal.setGxrq(gxrq);
				fkfsxlgwLocal.setGsbm(fkfsxlgwDL.getGsbm());
				fkfsxlgwLocal.setNy(month_sdf.format(gxrq));
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
			// ll
			fkfsxlgwLocalDao.deleteFKFSXLGWLocalByQY(4);
			List<FKFSXLGWXL> fkfsxlgwLLList = fkfsxlgwLLDao.getAllFKFSXLGW();
			for (FKFSXLGWXL fkfsxlgwLL : fkfsxlgwLLList) {
				fkfsxlgwLocal = new FKFSXLGWLocal();
				gxrq = fkfsxlgwLL.getGxrq();
				fkfsxlgwLocal.setGxrq(gxrq);
				fkfsxlgwLocal.setGsbm(fkfsxlgwLL.getGsbm());
				fkfsxlgwLocal.setNy(month_sdf.format(gxrq));
				fkfsxlgwLocal.setSfjzzb(fkfsxlgwLL.getSfjzzb());
				fkfsxlgwLocal.setGwhtddzlbs(fkfsxlgwLL.getGwhtddzlbs());
				fkfsxlgwLocal.setGwhtddzlje(fkfsxlgwLL.getGwhtddzlje());
				fkfsxlgwLocal.setN3_06_0_01bs(fkfsxlgwLL.getN3_06_0_01bs());
				fkfsxlgwLocal.setN3_06_0_01je(fkfsxlgwLL.getN3_06_0_01je());
				fkfsxlgwLocal.setN0_09_0_01bs(fkfsxlgwLL.getN0_09_0_01bs());
				fkfsxlgwLocal.setN0_09_0_01je(fkfsxlgwLL.getN0_09_0_01je());
				fkfsxlgwLocal.setN3_4_2_1bs(fkfsxlgwLL.getN3_4_2_1bs());
				fkfsxlgwLocal.setN3_4_2_1je(fkfsxlgwLL.getN3_4_2_1je());
				fkfsxlgwLocal.setN2_5_2_1bs(fkfsxlgwLL.getN2_5_2_1bs());
				fkfsxlgwLocal.setN2_5_2_1je(fkfsxlgwLL.getN2_5_2_1je());
				fkfsxlgwLocal.setN2_5_2d5_0d5bs(fkfsxlgwLL.getN2_5_2d5_0d5bs());
				fkfsxlgwLocal.setN2_5_2d5_0d5je(fkfsxlgwLL.getN2_5_2d5_0d5je());
				fkfsxlgwLocal.setN0_10_0_0bs(fkfsxlgwLL.getN0_10_0_0bs());
				fkfsxlgwLocal.setN0_10_0_0je(fkfsxlgwLL.getN0_10_0_0je());
				fkfsxlgwLocal.setN0_9d5_0d5bs(fkfsxlgwLL.getN0_9d5_0d5bs());
				fkfsxlgwLocal.setN0_9d5_0d5je(fkfsxlgwLL.getN0_9d5_0d5je());
				fkfsxlgwLocal.setQtbs(fkfsxlgwLL.getQtbs());
				fkfsxlgwLocal.setQtje(fkfsxlgwLL.getQtje());
				fkfsxlgwLocal.setWzbjhtbs(fkfsxlgwLL.getWzbjhtbs());
				fkfsxlgwLocal.setWzbjhtje(fkfsxlgwLL.getWzbjhtje());
				fkfsxlgwLocal.setZbqcgynhtbs(fkfsxlgwLL.getZbqcgynhtbs());
				fkfsxlgwLocal.setZbqcgynhtje(fkfsxlgwLL.getZbqcgynhtje());
				fkfsxlgwLocal.setSfdrwc(fkfsxlgwLL.getSfdrwc());
				fkfsxlgwLocal.setQybh(4);
				fkfsxlgwLocalDao.merge(fkfsxlgwLocal);
			}
			// xl
			fkfsxlgwLocalDao.deleteFKFSXLGWLocalByQY(5);
			List<FKFSXLGWXL> fkfsxlgwXLList = fkfsxlgwXLDao.getAllFKFSXLGW();
			for (FKFSXLGWXL fkfsxlgwXL : fkfsxlgwXLList) {
				fkfsxlgwLocal = new FKFSXLGWLocal();
				gxrq = fkfsxlgwXL.getGxrq();
				fkfsxlgwLocal.setGxrq(gxrq);
				fkfsxlgwLocal.setGsbm(fkfsxlgwXL.getGsbm());
				fkfsxlgwLocal.setNy(month_sdf.format(gxrq));
				fkfsxlgwLocal.setSfjzzb(fkfsxlgwXL.getSfjzzb());
				fkfsxlgwLocal.setGwhtddzlbs(fkfsxlgwXL.getGwhtddzlbs());
				fkfsxlgwLocal.setGwhtddzlje(fkfsxlgwXL.getGwhtddzlje());
				fkfsxlgwLocal.setN3_06_0_01bs(fkfsxlgwXL.getN3_06_0_01bs());
				fkfsxlgwLocal.setN3_06_0_01je(fkfsxlgwXL.getN3_06_0_01je());
				fkfsxlgwLocal.setN0_09_0_01bs(fkfsxlgwXL.getN0_09_0_01bs());
				fkfsxlgwLocal.setN0_09_0_01je(fkfsxlgwXL.getN0_09_0_01je());
				fkfsxlgwLocal.setN3_4_2_1bs(fkfsxlgwXL.getN3_4_2_1bs());
				fkfsxlgwLocal.setN3_4_2_1je(fkfsxlgwXL.getN3_4_2_1je());
				fkfsxlgwLocal.setN2_5_2_1bs(fkfsxlgwXL.getN2_5_2_1bs());
				fkfsxlgwLocal.setN2_5_2_1je(fkfsxlgwXL.getN2_5_2_1je());
				fkfsxlgwLocal.setN2_5_2d5_0d5bs(fkfsxlgwXL.getN2_5_2d5_0d5bs());
				fkfsxlgwLocal.setN2_5_2d5_0d5je(fkfsxlgwXL.getN2_5_2d5_0d5je());
				fkfsxlgwLocal.setN0_10_0_0bs(fkfsxlgwXL.getN0_10_0_0bs());
				fkfsxlgwLocal.setN0_10_0_0je(fkfsxlgwXL.getN0_10_0_0je());
				fkfsxlgwLocal.setN0_9d5_0d5bs(fkfsxlgwXL.getN0_9d5_0d5bs());
				fkfsxlgwLocal.setN0_9d5_0d5je(fkfsxlgwXL.getN0_9d5_0d5je());
				fkfsxlgwLocal.setQtbs(fkfsxlgwXL.getQtbs());
				fkfsxlgwLocal.setQtje(fkfsxlgwXL.getQtje());
				fkfsxlgwLocal.setWzbjhtbs(fkfsxlgwXL.getWzbjhtbs());
				fkfsxlgwLocal.setWzbjhtje(fkfsxlgwXL.getWzbjhtje());
				fkfsxlgwLocal.setZbqcgynhtbs(fkfsxlgwXL.getZbqcgynhtbs());
				fkfsxlgwLocal.setZbqcgynhtje(fkfsxlgwXL.getZbqcgynhtje());
				fkfsxlgwLocal.setSfdrwc(fkfsxlgwXL.getSfdrwc());
				fkfsxlgwLocal.setQybh(5);
				fkfsxlgwLocalDao.merge(fkfsxlgwLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferFKFSXLGW:" + result);
		}
		return result;
	}

	public FKFSXLGWLocalDao getFkfsxlgwLocalDao() {
		return fkfsxlgwLocalDao;
	}

	public void setFkfsxlgwLocalDao(FKFSXLGWLocalDao fkfsxlgwLocalDao) {
		this.fkfsxlgwLocalDao = fkfsxlgwLocalDao;
	}

	public FKFSXLGWXLDao getFkfsxlgwDLDao() {
		return fkfsxlgwDLDao;
	}

	public void setFkfsxlgwDLDao(FKFSXLGWXLDao fkfsxlgwDLDao) {
		this.fkfsxlgwDLDao = fkfsxlgwDLDao;
	}

	public FKFSXLGWXLDao getFkfsxlgwLLDao() {
		return fkfsxlgwLLDao;
	}

	public void setFkfsxlgwLLDao(FKFSXLGWXLDao fkfsxlgwLLDao) {
		this.fkfsxlgwLLDao = fkfsxlgwLLDao;
	}

	public FKFSXLGWXLDao getFkfsxlgwXLDao() {
		return fkfsxlgwXLDao;
	}

	public void setFkfsxlgwXLDao(FKFSXLGWXLDao fkfsxlgwXLDao) {
		this.fkfsxlgwXLDao = fkfsxlgwXLDao;
	}

}
