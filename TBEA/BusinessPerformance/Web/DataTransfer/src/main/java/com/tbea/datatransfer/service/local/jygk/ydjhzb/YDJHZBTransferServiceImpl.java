package com.tbea.datatransfer.service.local.jygk.ydjhzb;

import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.jygk.ydjhzb.YDJHZBLocalDao;
import com.tbea.datatransfer.model.dao.zj.jygk.ydjhzb.YDJHZBDao;
import com.tbea.datatransfer.model.entity.local.jygk.YDJHZBLocal;
import com.tbea.datatransfer.model.entity.zj.jygk.YDJHZB;

@Transactional("transactionManager")
public class YDJHZBTransferServiceImpl implements YDJHZBTransferService {

	private YDJHZBLocalDao ydjhzbLocalDao;

	private YDJHZBDao ydjhzbSBDao;

	private static List<Integer> dwidListSB = Arrays.asList(1, 101, 102, 103,
			104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115);

	@Override
	public boolean transferYDJHZB() {
		boolean result = false;
		try {

			YDJHZBLocal ydjhzbLocal = null;
			// sb
			int nf = 2015;
			int yf = 3;
			ydjhzbLocalDao.deleteYDJHZBLocalByDWAndDate(dwidListSB, nf, yf);
			List<YDJHZB> ydjhzbSBList = ydjhzbSBDao.getNewYDJHZB(nf, yf);
			for (YDJHZB ydjhzbSB : ydjhzbSBList) {
				ydjhzbLocal = new YDJHZBLocal();
				ydjhzbLocal.setDwid(ydjhzbSB.getDwid());
				ydjhzbLocal.setZbid(ydjhzbSB.getZbid());
				ydjhzbLocal.setNf(ydjhzbSB.getNf());
				ydjhzbLocal.setYf(ydjhzbSB.getYf());
				ydjhzbLocal.setYdjhz(ydjhzbSB.getYdjhz());
				ydjhzbLocal.setYdjhshzt(ydjhzbSB.getYdjhshzt());
				ydjhzbLocal.setYdjhxgsj(ydjhzbSB.getYdjhxgsj());
				ydjhzbLocalDao.merge(ydjhzbLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferYDJHZB:" + result);
		}
		return result;
	}

	public YDJHZBLocalDao getYdjhzbLocalDao() {
		return ydjhzbLocalDao;
	}

	public void setYdjhzbLocalDao(YDJHZBLocalDao ydjhzbLocalDao) {
		this.ydjhzbLocalDao = ydjhzbLocalDao;
	}

	public YDJHZBDao getYdjhzbSBDao() {
		return ydjhzbSBDao;
	}

	public void setYdjhzbSBDao(YDJHZBDao ydjhzbSBDao) {
		this.ydjhzbSBDao = ydjhzbSBDao;
	}

}
