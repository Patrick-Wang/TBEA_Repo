package com.tbea.datatransfer.service.inner.yqysysfx;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.inner.yqkqsbhb.YQKQSBHBDao;
import com.tbea.datatransfer.model.entity.inner.YQYSYSFX;

@Transactional("transactionManager")
public class YQYSYSFXServiceImpl implements YQYSYSFXService {

	private YQKQSBHBDao yqkqsbhbDao;


	@Override
	public boolean importYQYSYSFX() {
		boolean result = false;
		try {
			YQYSYSFX yqysysfx = null;
			Integer ysfl = null;
			Integer hs = null;
			Double je = null;
			Integer flsdhs = null;
			Double flsdje = null;
			Integer qybh = null;
			
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

}
