package com.tbea.datatransfer.service.inner.ndtbbzjqk;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.inner.ndtbbzjqk.NDTBBZJQKDao;
import com.tbea.datatransfer.model.dao.local.tbbzjxx.TBBZJXXLocalDao;
import com.tbea.datatransfer.model.entity.inner.NDTBBZJQK;
import com.tbea.datatransfer.model.entity.local.TBBZJXXLocal;

@Transactional("transactionManager")
public class NDTBBZJQKServiceImpl implements NDTBBZJQKService {

	private NDTBBZJQKDao ndtbbzjqkDao;

	private TBBZJXXLocalDao tbbzjxxLocalDao;

	@Override
	public boolean importNDTBBZJQK() {
		boolean result = false;
		try {
			NDTBBZJQK ndtbbzjqk = null;
			List<TBBZJXXLocal> tbbzjxxLocalList = tbbzjxxLocalDao
					.getAllTBBZJXXLocal();
			for (TBBZJXXLocal tbbzjxxLocal : tbbzjxxLocalList) {
				ndtbbzjqk = new NDTBBZJQK();
				ndtbbzjqk.setNf(tbbzjxxLocal.getNf());
				ndtbbzjqk.setYf(tbbzjxxLocal.getYf());
				ndtbbzjqk.setJe(tbbzjxxLocal.getJe());
				ndtbbzjqk.setQybh(tbbzjxxLocal.getQybh());
				ndtbbzjqkDao.merge(ndtbbzjqk);
			}
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public NDTBBZJQKDao getNdtbbzjqkDao() {
		return ndtbbzjqkDao;
	}

	public void setNdtbbzjqkDao(NDTBBZJQKDao ndtbbzjqkDao) {
		this.ndtbbzjqkDao = ndtbbzjqkDao;
	}

	public TBBZJXXLocalDao getTbbzjxxLocalDao() {
		return tbbzjxxLocalDao;
	}

	public void setTbbzjxxLocalDao(TBBZJXXLocalDao tbbzjxxLocalDao) {
		this.tbbzjxxLocalDao = tbbzjxxLocalDao;
	}

}
