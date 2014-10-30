package com.tbea.test.testWebProject.service.transfer.yszktz;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.dao.transfer.yszktz.YSZKTZ15Dao;
import com.tbea.test.testWebProject.model.dao.transfer.yszktz.YSZKTZLocalDao;
import com.tbea.test.testWebProject.model.entity.local.YSZKTZLocal;
import com.tbea.test.testWebProject.model.entity.yszk15.YSZKTZ15;

//@Service
// @Component
@Transactional("transactionManager")
public class YSZKTZTransferServiceImpl implements YSZKTZTransferService {

	// @Autowired
	private YSZKTZLocalDao yszktzLocalDao;

	// @Autowired
	private YSZKTZ15Dao yszktz15Dao;

	@Override
	// @Scheduled(cron = "0 42 11 * * ?", fixedDelay = 1000)
	@Transactional("transactionManager")
	public boolean transferYSZKTZ() {
		boolean result = false;
		try {
			YSZKTZLocal yszktzLocal = null;
			List<YSZKTZ15> yszktz15List = yszktz15Dao.getAllYSZKTZ15();
			for (YSZKTZ15 yszktz15 : yszktz15List) {
				yszktzLocal = new YSZKTZLocal();
				yszktzLocal.setGxrq(yszktz15.getGxrq());
				yszktzLocal.setHtbh(yszktz15.getHtbh());
				yszktzLocal.setKhbh(yszktz15.getKhbh());
				yszktzLocal.setKhmc(yszktz15.getKhmc());
				yszktzLocal.setKhsshy(yszktz15.getKhsshy());
				yszktzLocal.setKxlb(yszktz15.getKxlb());
				// yszktzLocal.setKxzt(yszktz15.getKxzt());
				yszktzLocal.setYsje(yszktz15.getYsje());
				yszktzLocal.setDqrq(yszktz15.getDqrq());
				yszktzLocal.setYhxje(yszktz15.getYhxje());
				yszktzLocal.setYfhje(yszktz15.getYfhje());
				yszktzLocal.setFhrq(yszktz15.getFhrq());
				yszktzLocal.setYkpje(yszktz15.getYkpje());
				yszktzLocal.setKprq(yszktz15.getKprq());
				// yszktzLocal.setYqyyfl(yszktz15.getYqyyfl());
				// yszktzLocal.setSftgflsdqs(yszktz15.getSftgflsdqs());
				yszktzLocal.setSfdrwc(yszktz15.getSfdrwc());
				yszktzLocal.setQybh(yszktz15.getQybh());
				yszktzLocalDao.merge(yszktzLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public YSZKTZLocalDao getYszktzLocalDao() {
		return yszktzLocalDao;
	}

	public void setYszktzLocalDao(YSZKTZLocalDao yszktzLocalDao) {
		this.yszktzLocalDao = yszktzLocalDao;
	}

	public YSZKTZ15Dao getYszktz15Dao() {
		return yszktz15Dao;
	}

	public void setYszktz15Dao(YSZKTZ15Dao yszktz15Dao) {
		this.yszktz15Dao = yszktz15Dao;
	}

}
