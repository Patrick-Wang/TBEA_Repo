package com.tbea.test.testWebProject.service.transfer.htxx;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.dao.transfer.htxx.HTXX15Dao;
import com.tbea.test.testWebProject.model.dao.transfer.htxx.HTXXLocalDao;
import com.tbea.test.testWebProject.model.entity.local.HTXXLocal;
import com.tbea.test.testWebProject.model.entity.yszk15.HTXX15;

//@Service
// @Component
@Transactional("transactionManager")
public class HTXXTransferServiceImpl implements HTXXTransferService {

	// @Autowired
	private HTXXLocalDao htxxLocalDao;

	// @Autowired
	private HTXX15Dao htxx15Dao;

	@Override
	// @Scheduled(cron = "0 42 11 * * ?", fixedDelay = 1000)
	@Transactional("transactionManager")
	public boolean transferHTXX() {
		boolean result = false;
		try {
			HTXXLocal htxxLocal = null;
			List<HTXX15> htxx15List = htxx15Dao.getAllHTXX15();
			for (HTXX15 htxx15 : htxx15List) {
				htxxLocal = new HTXXLocal();
				htxxLocal.setGxrq(htxx15.getGxrq());
				htxxLocal.setHtbh(htxx15.getHtbh());
				htxxLocal.setXmxx(htxx15.getXmxx());
				htxxLocal.setSspq(htxx15.getSspq());
				htxxLocal.setKhbh(htxx15.getKhbh());
				htxxLocal.setKhmc(htxx15.getKhmc());
				htxxLocal.setKhsshy(htxx15.getKhsshy());
				htxxLocal.setQdrq(htxx15.getQdrq());
				htxxLocal.setCpje(htxx15.getCpje());
				htxxLocal.setFy(htxx15.getFy());
				htxxLocal.setZje(htxx15.getZje());
				htxxLocal.setHtzt(htxx15.getHtzt());
				htxxLocal.setSfdrwc(htxx15.getSfdrwc());
				htxxLocal.setQybh(htxx15.getQybh());
				htxxLocalDao.merge(htxxLocal);
			}
			List<HTXXLocal> htxxLocalList = htxxLocalDao.getAllHTXXLocal();
			System.out.println(htxxLocalList);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public HTXXLocalDao getHtxxLocalDao() {
		return htxxLocalDao;
	}

	public void setHtxxLocalDao(HTXXLocalDao htxxLocalDao) {
		this.htxxLocalDao = htxxLocalDao;
	}

	public HTXX15Dao getHtxx15Dao() {
		return htxx15Dao;
	}

	public void setHtxx15Dao(HTXX15Dao htxx15Dao) {
		this.htxx15Dao = htxx15Dao;
	}

}
