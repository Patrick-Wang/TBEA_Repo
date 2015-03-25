package com.tbea.datatransfer.service.local.jygk.ydzbzt;

import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.jygk.ydzbzt.YDZBZTLocalDao;
import com.tbea.datatransfer.model.dao.zj.jygk.ydzbzt.YDZBZTDao;
import com.tbea.datatransfer.model.entity.local.jygk.YDZBZTLocal;
import com.tbea.datatransfer.model.entity.zj.jygk.YDZBZT;

@Transactional("transactionManager")
public class YDZBZTTransferServiceImpl implements YDZBZTTransferService {

	private YDZBZTLocalDao ydzbztLocalDao;

	private YDZBZTDao ydzbztSBDao;

	private static List<Integer> dwidListSB = Arrays.asList(1, 101, 102, 103,
			104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115);

	@Override
	public boolean transferYDZBZT() {
		boolean result = false;
		try {

			YDZBZTLocal ydzbztLocal = null;
			// sb
			int nf = 2015;
			int yf = 1;
			ydzbztLocalDao.deleteYDZBZTLocalByDWAndDate(dwidListSB, nf, yf);
			List<YDZBZT> ydzbztSBList = ydzbztSBDao.getNewYDZBZT(nf, yf);
			for (YDZBZT ydzbztSB : ydzbztSBList) {
				ydzbztLocal = new YDZBZTLocal();
				ydzbztLocal.setDwid(ydzbztSB.getDwid());
				ydzbztLocal.setNf(ydzbztSB.getNf());
				ydzbztLocal.setYf(ydzbztSB.getYf());
				ydzbztLocal.setZt(ydzbztSB.getZt());
				ydzbztLocalDao.merge(ydzbztLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferYDZBZT:" + result);
		}
		return result;
	}

	public YDZBZTLocalDao getYdzbztLocalDao() {
		return ydzbztLocalDao;
	}

	public void setYdzbztLocalDao(YDZBZTLocalDao ydzbztLocalDao) {
		this.ydzbztLocalDao = ydzbztLocalDao;
	}

	public YDZBZTDao getYdzbztSBDao() {
		return ydzbztSBDao;
	}

	public void setYdzbztSBDao(YDZBZTDao ydzbztSBDao) {
		this.ydzbztSBDao = ydzbztSBDao;
	}

}
