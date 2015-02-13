package com.tbea.datatransfer.service.local.jygk.ndjhzb;

import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.jygk.ndjhzb.NDJHZBLocalDao;
import com.tbea.datatransfer.model.dao.zj.jygk.ndjhzb.NDJHZBDao;
import com.tbea.datatransfer.model.entity.local.jygk.NDJHZBLocal;
import com.tbea.datatransfer.model.entity.zj.jygk.NDJHZB;

@Transactional("transactionManager")
public class NDJHZBTransferServiceImpl implements NDJHZBTransferService {

	private NDJHZBLocalDao ndjhzbLocalDao;

	private NDJHZBDao ndjhzbSBDao;

	private static List<Integer> dwidListSB = Arrays.asList(1, 101, 102, 103,
			104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115);

	@Override
	public boolean transferNDJHZB() {
		boolean result = false;
		try {

			NDJHZBLocal ndjhzbLocal = null;
			// sb
			ndjhzbLocalDao.deleteNDJHZBLocalByDW(dwidListSB);
			List<NDJHZB> ndjhzbSBList = ndjhzbSBDao.getAllNDJHZB();
			for (NDJHZB ndjhzbSB : ndjhzbSBList) {
				ndjhzbLocal = new NDJHZBLocal();
				ndjhzbLocal.setDwid(ndjhzbSB.getDwid());
				ndjhzbLocal.setZbid(ndjhzbSB.getZbid());
				ndjhzbLocal.setNf(ndjhzbSB.getNf());
				ndjhzbLocal.setNdjhz(ndjhzbSB.getNdjhz());
				ndjhzbLocal.setNdjhshzt(ndjhzbSB.getNdjhshzt());
				ndjhzbLocal.setNdjhxgsj(ndjhzbSB.getNdjhxgsj());
				ndjhzbLocalDao.merge(ndjhzbLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferNDJHZB:" + result);
		}
		return result;
	}

	public NDJHZBLocalDao getNdjhzbLocalDao() {
		return ndjhzbLocalDao;
	}

	public void setNdjhzbLocalDao(NDJHZBLocalDao ndjhzbLocalDao) {
		this.ndjhzbLocalDao = ndjhzbLocalDao;
	}

	public NDJHZBDao getNdjhzbSBDao() {
		return ndjhzbSBDao;
	}

	public void setNdjhzbSBDao(NDJHZBDao ndjhzbSBDao) {
		this.ndjhzbSBDao = ndjhzbSBDao;
	}

}
