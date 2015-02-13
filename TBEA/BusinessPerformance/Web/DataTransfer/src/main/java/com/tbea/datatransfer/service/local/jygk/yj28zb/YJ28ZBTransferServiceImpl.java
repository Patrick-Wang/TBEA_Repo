package com.tbea.datatransfer.service.local.jygk.yj28zb;

import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.jygk.yj28zb.YJ28ZBLocalDao;
import com.tbea.datatransfer.model.dao.zj.jygk.yj28zb.YJ28ZBDao;
import com.tbea.datatransfer.model.entity.local.jygk.YJ28ZBLocal;
import com.tbea.datatransfer.model.entity.zj.jygk.YJ28ZB;

@Transactional("transactionManager")
public class YJ28ZBTransferServiceImpl implements YJ28ZBTransferService {

	private YJ28ZBLocalDao yj28zbLocalDao;

	private YJ28ZBDao yj28zbSBDao;

	private static List<Integer> dwidListSB = Arrays.asList(1, 101, 102, 103,
			104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115);

	@Override
	public boolean transferYJ28ZB() {
		boolean result = false;
		try {

			YJ28ZBLocal yj28zbLocal = null;
			// sb
			yj28zbLocalDao.deleteYJ28ZBLocalByDW(dwidListSB);
			List<YJ28ZB> yj28zbSBList = yj28zbSBDao.getAllYJ28ZB();
			for (YJ28ZB yj28zbSB : yj28zbSBList) {
				yj28zbLocal = new YJ28ZBLocal();
				yj28zbLocal.setDwid(yj28zbSB.getDwid());
				yj28zbLocal.setZbid(yj28zbSB.getZbid());
				yj28zbLocal.setNf(yj28zbSB.getNf());
				yj28zbLocal.setYf(yj28zbSB.getYf());
				yj28zbLocal.setYj28z(yj28zbSB.getYj28z());
				yj28zbLocal.setYj28shzt(yj28zbSB.getYj28shzt());
				yj28zbLocal.setYj28xgsj(yj28zbSB.getYj28xgsj());
				yj28zbLocalDao.merge(yj28zbLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferYJ28ZB:" + result);
		}
		return result;
	}

	public YJ28ZBLocalDao getYj28zbLocalDao() {
		return yj28zbLocalDao;
	}

	public void setYj28zbLocalDao(YJ28ZBLocalDao yj28zbLocalDao) {
		this.yj28zbLocalDao = yj28zbLocalDao;
	}

	public YJ28ZBDao getYj28zbSBDao() {
		return yj28zbSBDao;
	}

	public void setYj28zbSBDao(YJ28ZBDao yj28zbSBDao) {
		this.yj28zbSBDao = yj28zbSBDao;
	}

}
