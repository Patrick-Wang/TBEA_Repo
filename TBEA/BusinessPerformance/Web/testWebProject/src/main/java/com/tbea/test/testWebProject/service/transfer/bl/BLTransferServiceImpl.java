package com.tbea.test.testWebProject.service.transfer.bl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.dao.transfer.bl.BL15Dao;
import com.tbea.test.testWebProject.model.dao.transfer.bl.BLLocalDao;
import com.tbea.test.testWebProject.model.entity.local.BLLocal;
import com.tbea.test.testWebProject.model.entity.yszk15.BL15;

//@Service
// @Component
@Transactional("transactionManager")
public class BLTransferServiceImpl implements BLTransferService {

	// @Autowired
	private BLLocalDao blLocalDao;

	// @Autowired
	private BL15Dao bl15Dao;

	@Override
	// @Scheduled(cron = "0 42 11 * * ?", fixedDelay = 1000)
	@Transactional("transactionManager")
	public boolean transferBL() {
		boolean result = false;
		try {
			BLLocal blLocal = null;
			List<BL15> bl15List = bl15Dao.getAllBL15();
			for (BL15 bl15 : bl15List) {
				blLocal = new BLLocal();
				blLocal.setGxrq(bl15.getGxrq());
				blLocal.setBlbh(bl15.getBlbh());
				blLocal.setHtbh(bl15.getHtbh());
				blLocal.setBlrq(bl15.getBlrq());
				blLocal.setKxxz(bl15.getKxxz());
				blLocal.setBlje(bl15.getBlje());
				blLocal.setBldqr(bl15.getBldqr());
				blLocal.setBlhkje(bl15.getBlhkje());
				blLocal.setBlye(bl15.getBlye());
				blLocal.setSfdrwc(bl15.getSfdrwc());
				blLocal.setQybh(bl15.getQybh());
				blLocalDao.merge(blLocal);
			}
			List<BLLocal> blLocalList = blLocalDao.getAllBLLocal();
			System.out.println(blLocalList);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public BLLocalDao getBlLocalDao() {
		return blLocalDao;
	}

	public void setBlLocalDao(BLLocalDao blLocalDao) {
		this.blLocalDao = blLocalDao;
	}

	public BL15Dao getBl15Dao() {
		return bl15Dao;
	}

	public void setBl15Dao(BL15Dao bl15Dao) {
		this.bl15Dao = bl15Dao;
	}

}
