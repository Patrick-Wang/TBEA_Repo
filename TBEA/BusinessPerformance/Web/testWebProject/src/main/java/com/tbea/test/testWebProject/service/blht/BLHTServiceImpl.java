package com.tbea.test.testWebProject.service.blht;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.dao.blht.BLHTDao;
import com.tbea.test.testWebProject.model.dao.transfer.bl.BLLocalDao;
import com.tbea.test.testWebProject.model.entity.local.BLHT;

@Transactional("transactionManager")
public class BLHTServiceImpl implements BLHTService {

	private BLHTDao blhtDao;

	private BLLocalDao blLocalDao;

	@Override
	public boolean importBLHT() {
		boolean result = false;
		try {
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
				blhtDao.merge(blht);
			}
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
