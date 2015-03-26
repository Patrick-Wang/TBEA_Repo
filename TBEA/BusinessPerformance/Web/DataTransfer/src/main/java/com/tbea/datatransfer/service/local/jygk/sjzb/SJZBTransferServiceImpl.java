package com.tbea.datatransfer.service.local.jygk.sjzb;

import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.jygk.sjzb.SJZBLocalDao;
import com.tbea.datatransfer.model.dao.zj.jygk.sjzb.SJZBDao;
import com.tbea.datatransfer.model.entity.local.jygk.SJZBLocal;
import com.tbea.datatransfer.model.entity.zj.jygk.SJZB;

@Transactional("transactionManager")
public class SJZBTransferServiceImpl implements SJZBTransferService {

	private SJZBLocalDao sjzbLocalDao;

	private SJZBDao sjzbSBDao;

	private static List<Integer> dwidListSB = Arrays.asList(1, 101, 102, 103,
			104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115);

	@Override
	public boolean transferSJZB() {
		boolean result = false;
		try {

			SJZBLocal sjzbLocal = null;
			// sb
			int nf = 2015;
			int yf = 1;
			sjzbLocalDao.deleteSJZBLocalByDWAndDate(dwidListSB, nf, yf);
			List<SJZB> sjzbSBList = sjzbSBDao.getNewSJZB(nf, yf);
			for (SJZB sjzbSB : sjzbSBList) {
				sjzbLocal = new SJZBLocal();
				sjzbLocal.setDwid(sjzbSB.getDwid());
				sjzbLocal.setZbid(sjzbSB.getZbid());
				sjzbLocal.setNf(sjzbSB.getNf());
				sjzbLocal.setYf(sjzbSB.getYf());
				sjzbLocal.setSjz(sjzbSB.getSjz());
				sjzbLocal.setSjshzt(sjzbSB.getSjshzt());
				sjzbLocal.setSjxgsj(sjzbSB.getSjxgsj());
				sjzbLocalDao.merge(sjzbLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferSJZB:" + result);
		}
		return result;
	}

	public SJZBLocalDao getSjzbLocalDao() {
		return sjzbLocalDao;
	}

	public void setSjzbLocalDao(SJZBLocalDao sjzbLocalDao) {
		this.sjzbLocalDao = sjzbLocalDao;
	}

	public SJZBDao getSjzbSBDao() {
		return sjzbSBDao;
	}

	public void setSjzbSBDao(SJZBDao sjzbSBDao) {
		this.sjzbSBDao = sjzbSBDao;
	}

}
