package com.tbea.datatransfer.service.local.blht;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.bl.BLLocalDao;
import com.tbea.datatransfer.model.dao.local.blht.BLHTDao;
import com.tbea.datatransfer.model.entity.local.BLHT;

@Transactional("transactionManager")
public class BLHTServiceImpl implements BLHTService {

	private BLHTDao blhtDao;

	private BLLocalDao blLocalDao;

	private void importBLHTTotal() throws Exception {
		List<Object[]> blhkjeList = blLocalDao.getBLHKJE();
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		for (Object[] blhkjeArray : blhkjeList) {
			map.put((String) blhkjeArray[0], blhkjeArray);
		}
		List<Object[]> bljeList = blLocalDao.getBLJE();
		for (Object[] bljeArray : bljeList) {
			BLHT blht = new BLHT();
			blht.setNy((String) bljeArray[0]);
			blht.setDqblje(Double.valueOf(bljeArray[1].toString()));
			blht.setDqblfs(Integer.valueOf(bljeArray[2].toString()));

			Object[] tempArray = map.get(bljeArray[0]);
			if (null != tempArray) {
				blht.setDqblzyhkje(Double.valueOf((tempArray[1].toString())));
				blht.setDqblzyhkfs(Integer.valueOf((tempArray[2].toString())));
			} else {
				blht.setDqblzyhkje(0.0D);
				blht.setDqblzyhkfs(0);
			}
//			blht.setQybh(9999);
			blhtDao.merge(blht);
		}
	}

	private void importBLHTByQY() throws Exception {
		List<Object[]> blhkjeList = blLocalDao.getBLHKJEByQY();
		Map<String, Object[]> map = new HashMap<String, Object[]>();

		for (Object[] blhkjeArray : blhkjeList) {
			map.put(((String) blhkjeArray[0] + blhkjeArray[1]), blhkjeArray);
		}
		List<Object[]> bljeList = blLocalDao.getBLJEByQY();
		for (Object[] bljeArray : bljeList) {
			BLHT blht = new BLHT();
			blht.setNy((String) bljeArray[0]);
			blht.setDqblje(Double.valueOf(bljeArray[2].toString()));
			blht.setDqblfs(Integer.valueOf(bljeArray[3].toString()));

			Object[] tempArray = map.get((String) bljeArray[0] + bljeArray[1]);
			if (null != tempArray) {
				blht.setDqblzyhkje(Double.valueOf((tempArray[2].toString())));
				blht.setDqblzyhkfs(Integer.valueOf((tempArray[3].toString())));
			} else {
				blht.setDqblzyhkje(0.0D);
				blht.setDqblzyhkfs(0);
			}
//			blht.setQybh((Integer) bljeArray[1]);
			blhtDao.merge(blht);
		}
	}

	@Override
	public boolean importBLHT() {
		boolean result = false;
		try {
			importBLHTTotal();
			importBLHTByQY();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public BLHTDao getBlhtDao() {
		return blhtDao;
	}

	public void setBlhtDao(BLHTDao blhtDao) {
		this.blhtDao = blhtDao;
	}

	public BLLocalDao getBlLocalDao() {
		return blLocalDao;
	}

	public void setBlLocalDao(BLLocalDao blLocalDao) {
		this.blLocalDao = blLocalDao;
	}

}
