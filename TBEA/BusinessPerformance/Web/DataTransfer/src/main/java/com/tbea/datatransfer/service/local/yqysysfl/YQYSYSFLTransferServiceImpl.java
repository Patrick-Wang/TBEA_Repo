package com.tbea.datatransfer.service.local.yqysysfl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.local.yqysysfl.YQYSYSFLLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.yqysysfl.YQYSYSFLBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.yqysysfl.YQYSYSFLXLDao;
import com.tbea.datatransfer.model.entity.local.YQYSYSFLLocal;
import com.tbea.datatransfer.model.entity.zjbyq.YQYSYSFLBYQ;
import com.tbea.datatransfer.model.entity.zjxl.YQYSYSFLXL;
import com.tbea.datatransfer.service.webservice.WebServiceClient;

@Transactional("transactionManager")
public class YQYSYSFLTransferServiceImpl implements YQYSYSFLTransferService {

	private YQYSYSFLLocalDao yqysysflLocalDao;

	private YQYSYSFLBYQDao yqysysflTBDao;

	private YQYSYSFLBYQDao yqysysflSBDao;

	private YQYSYSFLBYQDao yqysysflXBDao;

	private YQYSYSFLXLDao yqysysflDLDao;

	private YQYSYSFLXLDao yqysysflLLDao;

	private YQYSYSFLXLDao yqysysflXLDao;

	private void transferYQYSYSFLBYQByZJB(int qybh,
			List<YQYSYSFLBYQ> yqysysflList) {
		YQYSYSFLLocal yqysysflLocal = null;
		yqysysflLocalDao.deleteYQYSYSFLLocalByQY(qybh);
		for (YQYSYSFLBYQ yqysysfl : yqysysflList) {
			yqysysflLocal = new YQYSYSFLLocal();
			yqysysflLocal.setNf(yqysysfl.getNf());
			yqysysflLocal.setYf(yqysysfl.getYf());
			yqysysflLocal.setYsflmc(yqysysfl.getYsflmc());
			yqysysflLocal.setZhs(yqysysfl.getZhs());
			yqysysflLocal.setZje(yqysysfl.getZje());
			yqysysflLocal.setFlqshs(yqysysfl.getFlqshs());
			yqysysflLocal.setFlqsje(yqysysfl.getFlqsje());
			yqysysflLocal.setSfdrwc(yqysysfl.getSfdrwc());
			yqysysflLocal.setQybh(qybh);
			yqysysflLocalDao.merge(yqysysflLocal);
		}

	}

	private void transferYQYSYSFLXLByZJB(int qybh, List<YQYSYSFLXL> yqysysflList) {
		YQYSYSFLLocal yqysysflLocal = null;
		yqysysflLocalDao.deleteYQYSYSFLLocalByQY(qybh);
		for (YQYSYSFLXL yqysysfl : yqysysflList) {
			yqysysflLocal = new YQYSYSFLLocal();
			yqysysflLocal.setNf(yqysysfl.getNf());
			yqysysflLocal.setYf(yqysysfl.getYf());
			yqysysflLocal.setYsflmc(yqysysfl.getYsflmc());
			yqysysflLocal.setZhs(yqysysfl.getZhs());
			yqysysflLocal.setZje(yqysysfl.getZje());
			yqysysflLocal.setFlqshs(yqysysfl.getFlqshs());
			yqysysflLocal.setFlqsje(yqysysfl.getFlqsje());
			yqysysflLocal.setSfdrwc(yqysysfl.getSfdrwc());
			yqysysflLocal.setQybh(qybh);
			yqysysflLocalDao.merge(yqysysflLocal);
		}

	}

	private void transferYQYSYSFLBYQByWS(int qybh, String userid,
			String password, String scheme) {
		YQYSYSFLLocal yqysysflLocal = null;
		yqysysflLocalDao.deleteYQYSYSFLLocalByQY(qybh);
		WebServiceClient webServiceClient = new WebServiceClient();
		List<Map<String, Object>> recList = webServiceClient.getRec("web_test",
				"123456", "yszk_ws_yqysysfl");
		for (Map<String, Object> recMap : recList) {
			yqysysflLocal = new YQYSYSFLLocal();
			yqysysflLocal.setNf(String.valueOf(recMap.get("nf")));
			yqysysflLocal.setYf(String.valueOf(recMap.get("yf")));
			yqysysflLocal.setYsflmc(String.valueOf(recMap.get("ysflmc")));
			yqysysflLocal
					.setZhs(CommonMethod.objectToInteger(recMap.get("zhs")));
			yqysysflLocal
					.setZje(CommonMethod.objectToDouble(recMap.get("zje")));
			yqysysflLocal.setFlqshs(CommonMethod.objectToInteger(recMap
					.get("flqshs")));
			yqysysflLocal.setFlqsje(CommonMethod.objectToDouble(recMap
					.get("flqsje")));
			yqysysflLocal.setSfdrwc(String.valueOf(recMap.get("sfdrwc")));
			yqysysflLocal.setQybh(qybh);
			yqysysflLocalDao.merge(yqysysflLocal);
		}
	}

	@Override
	public boolean transferYQYSYSFL() {

		boolean result = false;
		boolean sbResult = false;
		boolean hbResult = false;
		boolean xbResult = false;
		boolean tbResult = false;
		boolean llResult = false;
		boolean xlResult = false;
		boolean dlResult = false;
		// sb
		try {
			List<YQYSYSFLBYQ> yqysysflSBList = yqysysflSBDao.getAllYQYSYSFL();
			transferYQYSYSFLBYQByZJB(1, yqysysflSBList);
			sbResult = true;
		} catch (Exception e) {
			sbResult = false;
		}
		// hb
		try {
			transferYQYSYSFLBYQByWS(2, "web_test", "123456", "yszk_ws_yqysysfl");
			hbResult = true;
		} catch (Exception e) {
			e.printStackTrace();
			hbResult = false;
		}
		// xb
		try {
			List<YQYSYSFLBYQ> yqysysflXBList = yqysysflXBDao.getAllYQYSYSFL();
			transferYQYSYSFLBYQByZJB(3, yqysysflXBList);
			xbResult = true;
		} catch (Exception e) {
			xbResult = false;
		}
		// tb
		try {
			List<YQYSYSFLBYQ> yqysysflTBList = yqysysflTBDao.getAllYQYSYSFL();
			transferYQYSYSFLBYQByZJB(301, yqysysflTBList);
			tbResult = true;
		} catch (Exception e) {
			tbResult = false;
		}
		// ll
		try {
			List<YQYSYSFLXL> yqysysflLLList = yqysysflLLDao.getAllYQYSYSFL();
			transferYQYSYSFLXLByZJB(4, yqysysflLLList);
			llResult = true;
		} catch (Exception e) {
			llResult = false;
		}
		// xl
		try {
			List<YQYSYSFLXL> yqysysflXLList = yqysysflXLDao.getAllYQYSYSFL();
			transferYQYSYSFLXLByZJB(5, yqysysflXLList);
			xlResult = true;
		} catch (Exception e) {
			e.printStackTrace();
			xlResult = false;
		}
		// dl
		try {
			List<YQYSYSFLXL> yqysysflDLList = yqysysflDLDao.getAllYQYSYSFL();
			transferYQYSYSFLXLByZJB(6, yqysysflDLList);
			dlResult = true;
		} catch (Exception e) {
			dlResult = false;
		}

		if (sbResult && hbResult && xbResult && tbResult && llResult
				&& xlResult && dlResult) {
			// if (xlResult) {
			result = true;
			System.out.println("transferYQYSYSFL:true");
		} else {
			result = false;
			System.out.println("transferYQYSYSFLsb:" + sbResult);
			System.out.println("transferYQYSYSFLhb:" + hbResult);
			System.out.println("transferYQYSYSFLxb:" + xbResult);
			System.out.println("transferYQYSYSFLtb:" + tbResult);
			System.out.println("transferYQYSYSFLll:" + llResult);
			System.out.println("transferYQYSYSFLxl:" + xlResult);
			System.out.println("transferYQYSYSFLdl:" + dlResult);
		}
		return result;
	}

	public YQYSYSFLLocalDao getYqysysflLocalDao() {
		return yqysysflLocalDao;
	}

	public void setYqysysflLocalDao(YQYSYSFLLocalDao yqysysflLocalDao) {
		this.yqysysflLocalDao = yqysysflLocalDao;
	}

	public YQYSYSFLBYQDao getYqysysflTBDao() {
		return yqysysflTBDao;
	}

	public void setYqysysflTBDao(YQYSYSFLBYQDao yqysysflTBDao) {
		this.yqysysflTBDao = yqysysflTBDao;
	}

	public YQYSYSFLBYQDao getYqysysflSBDao() {
		return yqysysflSBDao;
	}

	public void setYqysysflSBDao(YQYSYSFLBYQDao yqysysflSBDao) {
		this.yqysysflSBDao = yqysysflSBDao;
	}

	public YQYSYSFLBYQDao getYqysysflXBDao() {
		return yqysysflXBDao;
	}

	public void setYqysysflXBDao(YQYSYSFLBYQDao yqysysflXBDao) {
		this.yqysysflXBDao = yqysysflXBDao;
	}

	public YQYSYSFLXLDao getYqysysflDLDao() {
		return yqysysflDLDao;
	}

	public void setYqysysflDLDao(YQYSYSFLXLDao yqysysflDLDao) {
		this.yqysysflDLDao = yqysysflDLDao;
	}

	public YQYSYSFLXLDao getYqysysflLLDao() {
		return yqysysflLLDao;
	}

	public void setYqysysflLLDao(YQYSYSFLXLDao yqysysflLLDao) {
		this.yqysysflLLDao = yqysysflLLDao;
	}

	public YQYSYSFLXLDao getYqysysflXLDao() {
		return yqysysflXLDao;
	}

	public void setYqysysflXLDao(YQYSYSFLXLDao yqysysflXLDao) {
		this.yqysysflXLDao = yqysysflXLDao;
	}

}
