package com.tbea.datatransfer.service.inner.blht;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.inner.blht.BLHTDao;
import com.tbea.datatransfer.model.dao.local.bl.BLLocalDao;
import com.tbea.datatransfer.model.entity.inner.BLHT;

@Transactional("transactionManager")
public class BLHTServiceImpl implements BLHTService {

	private BLHTDao blhtDao;

	private BLLocalDao blLocalDao;

	private void importBLHTTotal() throws Exception {
		// blhkje
		List<Object[]> blhkjeList = blLocalDao.getBLHKJE();
		Map<String, Object[]> blhkjeMap = new HashMap<String, Object[]>();
		for (Object[] blhkjeArray : blhkjeList) {
			blhkjeMap.put((String) blhkjeArray[0], blhkjeArray);
		}
		// khfxs
		List<Object[]> khfxsList = blLocalDao.getKHFXS();
		Map<String, Object[]> khfxsMap = new HashMap<String, Object[]>();
		for (Object[] khfxsArray : khfxsList) {
			khfxsMap.put((String) khfxsArray[0], khfxsArray);
		}
		// fkhfxs
		List<Object[]> fkhfxsList = blLocalDao.getFKHFXS();
		Map<String, Object[]> fkhfxsMap = new HashMap<String, Object[]>();
		for (Object[] fkhfxsArray : fkhfxsList) {
			fkhfxsMap.put((String) fkhfxsArray[0], fkhfxsArray);
		}
		List<Object[]> bljeList = blLocalDao.getBLJE();
		for (Object[] bljeArray : bljeList) {
			BLHT blht = new BLHT();
			blht.setNy((String) bljeArray[0]);

			// blje
			blht.setDqblje(Double.valueOf(bljeArray[1].toString()));
			blht.setDqblfs(Integer.valueOf(bljeArray[2].toString()));

			Object[] tempArray = null;
			// blhkje
			tempArray = blhkjeMap.get(bljeArray[0]);
			if (null != tempArray) {
				blht.setDqblzyhkje(Double.valueOf((tempArray[1].toString())));
				blht.setDqblzyhkfs(Integer.valueOf((tempArray[2].toString())));
			} else {
				blht.setDqblzyhkje(0.0D);
				blht.setDqblzyhkfs(0);
			}
			// khfxs
			tempArray = khfxsMap.get(bljeArray[0]);
			if (null != tempArray) {
				blht.setDqkhfxsblye(Double.valueOf((tempArray[1].toString())));
				blht.setDqkhfxsblfs(Integer.valueOf((tempArray[2].toString())));
			} else {
				blht.setDqkhfxsblye(0.0D);
				blht.setDqkhfxsblfs(0);
			}
			// fkhfxs
			tempArray = fkhfxsMap.get(bljeArray[0]);
			if (null != tempArray) {
				blht.setDqfkhfxsblye(Double.valueOf((tempArray[1].toString())));
				blht.setDqfkhfxsblfs(Integer.valueOf((tempArray[2].toString())));
			} else {
				blht.setDqfkhfxsblye(0.0D);
				blht.setDqfkhfxsblfs(0);
			}
			blht.setQybh(9999);
			blhtDao.merge(blht);
		}
	}

	private void importBLHTByQY() throws Exception {
		// blhkje
		List<Object[]> blhkjeList = blLocalDao.getBLHKJEByQY();
		Map<String, Object[]> blhkjeMap = new HashMap<String, Object[]>();
		for (Object[] blhkjeArray : blhkjeList) {
			blhkjeMap.put(((String) blhkjeArray[0] + blhkjeArray[1]),
					blhkjeArray);
		}
		// khfxs
		List<Object[]> khfxsList = blLocalDao.getKHFXSByQY();
		Map<String, Object[]> khfxsMap = new HashMap<String, Object[]>();
		for (Object[] khfxsArray : khfxsList) {
			khfxsMap.put(((String) khfxsArray[0] + khfxsArray[1]), khfxsArray);
		}
		// fkhfxs
		List<Object[]> fkhfxsList = blLocalDao.getFKHFXSByQY();
		Map<String, Object[]> fkhfxsMap = new HashMap<String, Object[]>();
		for (Object[] fkhfxsArray : fkhfxsList) {
			fkhfxsMap.put(((String) fkhfxsArray[0] + fkhfxsArray[1]),
					fkhfxsArray);
		}
		List<Object[]> bljeList = blLocalDao.getBLJEByQY();
		for (Object[] bljeArray : bljeList) {
			BLHT blht = new BLHT();
			blht.setNy((String) bljeArray[0]);
			// blje
			blht.setDqblje(Double.valueOf(bljeArray[2].toString()));
			blht.setDqblfs(Integer.valueOf(bljeArray[3].toString()));

			Object[] tempArray = null;
			// blhkje
			tempArray = blhkjeMap.get((String) bljeArray[0] + bljeArray[1]);
			if (null != tempArray) {
				blht.setDqblzyhkje(Double.valueOf((tempArray[2].toString())));
				blht.setDqblzyhkfs(Integer.valueOf((tempArray[3].toString())));
			} else {
				blht.setDqblzyhkje(0.0D);
				blht.setDqblzyhkfs(0);
			}
			// khfxs
			tempArray = khfxsMap.get((String) bljeArray[0] + bljeArray[1]);
			if (null != tempArray) {
				blht.setDqkhfxsblye(Double.valueOf((tempArray[2].toString())));
				blht.setDqkhfxsblfs(Integer.valueOf((tempArray[3].toString())));
			} else {
				blht.setDqkhfxsblye(0.0D);
				blht.setDqkhfxsblfs(0);
			}
			// fkhfxs
			tempArray = fkhfxsMap.get((String) bljeArray[0] + bljeArray[1]);
			if (null != tempArray) {
				blht.setDqfkhfxsblye(Double.valueOf((tempArray[2].toString())));
				blht.setDqfkhfxsblfs(Integer.valueOf((tempArray[3].toString())));
			} else {
				blht.setDqfkhfxsblye(0.0D);
				blht.setDqfkhfxsblfs(0);
			}
			blht.setQybh((Integer) bljeArray[1]);
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
