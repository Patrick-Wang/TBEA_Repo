package com.tbea.datatransfer.service.local.jygk.yj20zb;

import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.jygk.yj20zb.YJ20ZBLocalDao;
import com.tbea.datatransfer.model.dao.zj.jygk.yj20zb.YJ20ZBDao;
import com.tbea.datatransfer.model.entity.local.jygk.YJ20ZBLocal;
import com.tbea.datatransfer.model.entity.zj.jygk.YJ20ZB;

@Transactional("transactionManager")
public class YJ20ZBTransferServiceImpl implements YJ20ZBTransferService {

	private YJ20ZBLocalDao yj20zbLocalDao;

	private YJ20ZBDao yj20zbSBDao;

	private static List<Integer> dwidListSB = Arrays.asList(1, 101, 102, 103,
			104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115);

	@Override
	public boolean transferYJ20ZB() {
		boolean result = false;
		try {

			YJ20ZBLocal yj20zbLocal = null;
			// sb
			yj20zbLocalDao.deleteYJ20ZBLocalByDW(dwidListSB);
			List<YJ20ZB> yj20zbSBList = yj20zbSBDao.getAllYJ20ZB();
			for (YJ20ZB yj20zbSB : yj20zbSBList) {
				yj20zbLocal = new YJ20ZBLocal();
				yj20zbLocal.setDwid(yj20zbSB.getDwid());
				yj20zbLocal.setZbid(yj20zbSB.getZbid());
				yj20zbLocal.setNf(yj20zbSB.getNf());
				yj20zbLocal.setYf(yj20zbSB.getYf());
				yj20zbLocal.setYj20z(yj20zbSB.getYj20z());
				yj20zbLocal.setYj20shzt(yj20zbSB.getYj20shzt());
				yj20zbLocal.setYj20xgsj(yj20zbSB.getYj20xgsj());
				yj20zbLocalDao.merge(yj20zbLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferYJ20ZB:" + result);
		}
		return result;
	}

	public YJ20ZBLocalDao getYj20zbLocalDao() {
		return yj20zbLocalDao;
	}

	public void setYj20zbLocalDao(YJ20ZBLocalDao yj20zbLocalDao) {
		this.yj20zbLocalDao = yj20zbLocalDao;
	}

	public YJ20ZBDao getYj20zbSBDao() {
		return yj20zbSBDao;
	}

	public void setYj20zbSBDao(YJ20ZBDao yj20zbSBDao) {
		this.yj20zbSBDao = yj20zbSBDao;
	}

}
