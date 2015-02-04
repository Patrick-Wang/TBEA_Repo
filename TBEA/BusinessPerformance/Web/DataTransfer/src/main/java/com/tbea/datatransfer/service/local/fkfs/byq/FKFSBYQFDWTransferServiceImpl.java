package com.tbea.datatransfer.service.local.fkfs.byq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.local.fkfs.byq.FKFSBYQFDWLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.fkfs.FKFSBYQFDWBYQDao;
import com.tbea.datatransfer.model.entity.local.FKFSBYQFDWLocal;
import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQFDWBYQ;
import com.tbea.datatransfer.service.webservice.WebServiceClient;

@Transactional("transactionManager")
public class FKFSBYQFDWTransferServiceImpl implements FKFSBYQFDWTransferService {

	private FKFSBYQFDWLocalDao fkfsbyqfdwLocalDao;

	private FKFSBYQFDWBYQDao fkfsbyqfdwTBDao;

	private FKFSBYQFDWBYQDao fkfsbyqfdwSBDao;

	private FKFSBYQFDWBYQDao fkfsbyqfdwXBDao;

	private static SimpleDateFormat month_sdf = new SimpleDateFormat("yyyyMM");

	private static SimpleDateFormat timeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private void transferFKFSBYQFDWByZJB(int qybh,
			List<FKFSBYQFDWBYQ> fkfsbyqfdwList) {
		fkfsbyqfdwLocalDao.deleteFKFSBYQFDWLocalByQY(qybh);

		Date gxrq = null;
		FKFSBYQFDWLocal fkfsbyqfdwLocal = null;
		for (FKFSBYQFDWBYQ fkfsbyqfdw : fkfsbyqfdwList) {
			fkfsbyqfdwLocal = new FKFSBYQFDWLocal();
			gxrq = fkfsbyqfdw.getGxrq();
			fkfsbyqfdwLocal.setGxrq(gxrq);
			fkfsbyqfdwLocal.setGsbm(fkfsbyqfdw.getGsbm());
			fkfsbyqfdwLocal.setNy(month_sdf.format(gxrq));
			fkfsbyqfdwLocal.setFdwhtddzlbs(fkfsbyqfdw.getFdwhtddzlbs());
			fkfsbyqfdwLocal.setFdwhtddzlje(fkfsbyqfdw.getFdwhtddzlje());
			fkfsbyqfdwLocal.setWyfkhtbs(fkfsbyqfdw.getWyfkhtbs());
			fkfsbyqfdwLocal.setWyfkhtje(fkfsbyqfdw.getWyfkhtje());
			fkfsbyqfdwLocal.setYfkxybfzshtbs(fkfsbyqfdw.getYfkxybfzshtbs());
			fkfsbyqfdwLocal.setYfkxybfzshtje(fkfsbyqfdw.getYfkxybfzshtje());
			fkfsbyqfdwLocal.setYfkzbfzsdsszjhtbs(fkfsbyqfdw
					.getYfkzbfzsdsszjhtbs());
			fkfsbyqfdwLocal.setYfkzbfzsdsszjhtje(fkfsbyqfdw
					.getYfkzbfzsdsszjhtje());
			fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtbs(fkfsbyqfdw
					.getHwjfhfkblxybfzbshtbs());
			fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtje(fkfsbyqfdw
					.getHwjfhfkblxybfzbshtje());
			fkfsbyqfdwLocal.setWddsjhtbs(fkfsbyqfdw.getWddsjhtbs());
			fkfsbyqfdwLocal.setWddsjhtje(fkfsbyqfdw.getWddsjhtje());
			fkfsbyqfdwLocal.setZbqdysegyhtbs(fkfsbyqfdw.getZbqdysegyhtbs());
			fkfsbyqfdwLocal.setZbqdysegyhtje(fkfsbyqfdw.getZbqdysegyhtje());
			fkfsbyqfdwLocal.setXkxhhtbs(fkfsbyqfdw.getXkxhhtbs());
			fkfsbyqfdwLocal.setXkxhhtje(fkfsbyqfdw.getXkxhhtje());
			fkfsbyqfdwLocal.setSfdrwc(fkfsbyqfdw.getSfdrwc());
			fkfsbyqfdwLocal.setQybh(qybh);
			fkfsbyqfdwLocalDao.merge(fkfsbyqfdwLocal);
		}
	}

	private void transferFKFSBYQFDWByWS(int qybh, String userid,
			String password, String scheme) {
		fkfsbyqfdwLocalDao.deleteFKFSBYQFDWLocalByQY(qybh);
		WebServiceClient webServiceClient = new WebServiceClient();
		List<Map<String, Object>> recList = webServiceClient.getRec(userid,
				password, scheme);
		Date gxrq = null;
		FKFSBYQFDWLocal fkfsbyqfdwLocal = null;
		for (Map<String, Object> recMap : recList) {
			fkfsbyqfdwLocal = new FKFSBYQFDWLocal();
			gxrq = CommonMethod.objectToDate(timeFormat, recMap.get("gxrq"));
			fkfsbyqfdwLocal.setGxrq(gxrq);
			fkfsbyqfdwLocal.setGsbm(String.valueOf(recMap.get("gsbm")));
			fkfsbyqfdwLocal.setNy(month_sdf.format(gxrq));
			fkfsbyqfdwLocal.setFdwhtddzlbs(Integer.valueOf(String
					.valueOf(recMap.get("fdwhtddzlbs"))));
			fkfsbyqfdwLocal.setFdwhtddzlje(CommonMethod.objectToDouble(recMap
					.get("fdwhtddzlje")));
			fkfsbyqfdwLocal.setWyfkhtbs(Integer.valueOf(String.valueOf(recMap
					.get("wyfkhtbs"))));
			fkfsbyqfdwLocal.setWyfkhtje(CommonMethod.objectToDouble(recMap
					.get("wyfkhtje")));
			fkfsbyqfdwLocal.setYfkxybfzshtbs(Integer.valueOf(String
					.valueOf(recMap.get("yfkxybfzshtbs"))));
			fkfsbyqfdwLocal.setYfkxybfzshtje(CommonMethod.objectToDouble(recMap
					.get("yfkxybfzshtje")));
			fkfsbyqfdwLocal.setYfkzbfzsdsszjhtbs(Integer.valueOf(String
					.valueOf(recMap.get("yfkzbfzsdsszjhtbs"))));
			fkfsbyqfdwLocal.setYfkzbfzsdsszjhtje(Double.valueOf(String.valueOf(
					recMap.get("yfkzbfzsdsszjhtje")).replace(",", "")));
			fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtbs(Integer.valueOf(String
					.valueOf(recMap.get("hwjfhfkblxybfzbshtbs"))));
			fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtje(Double.valueOf(String
					.valueOf(recMap.get("hwjfhfkblxybfzbshtje")).replace(",",
							"")));
			fkfsbyqfdwLocal.setWddsjhtbs(Integer.valueOf(String.valueOf(recMap
					.get("wddsjhtbs"))));
			fkfsbyqfdwLocal.setWddsjhtje(CommonMethod.objectToDouble(recMap
					.get("wddsjhtje")));
			fkfsbyqfdwLocal.setZbqdysegyhtbs(Integer.valueOf(String
					.valueOf(recMap.get("zbqdysegyhtbs"))));
			fkfsbyqfdwLocal.setZbqdysegyhtje(CommonMethod.objectToDouble(recMap
					.get("zbqdysegyhtje")));
			fkfsbyqfdwLocal.setXkxhhtbs(Integer.valueOf(String.valueOf(recMap
					.get("xkxhhtbs"))));
			fkfsbyqfdwLocal.setXkxhhtje(CommonMethod.objectToDouble(recMap
					.get("xkxhhtje")));
			fkfsbyqfdwLocal.setSfdrwc(String.valueOf(recMap.get("sfdrwc")));
			fkfsbyqfdwLocal.setQybh(qybh);
			fkfsbyqfdwLocalDao.merge(fkfsbyqfdwLocal);
		}
	}

	@Override
	public boolean transferFKFSBYQFDW() {
		boolean result = false;
		boolean sbResult = false;
		boolean hbResult = false;
		boolean xbResult = false;
		boolean tbResult = false;
		// sb
		try {
			List<FKFSBYQFDWBYQ> fkfsbyqfdwSBList = fkfsbyqfdwSBDao
					.getAllFKFSBYQFDW();
			transferFKFSBYQFDWByZJB(1, fkfsbyqfdwSBList);
			sbResult = true;
		} catch (Exception e) {
			sbResult = false;
		}
		// hb
		try {
			transferFKFSBYQFDWByWS(2, "web_test", "123456",
					"yszk_ws_htfkfstj_byq_fdw");
			hbResult = true;
		} catch (Exception e) {
			e.printStackTrace();
			hbResult = false;
		}
		// xb
		try {
			List<FKFSBYQFDWBYQ> fkfsbyqfdwXBList = fkfsbyqfdwXBDao
					.getAllFKFSBYQFDW();
			transferFKFSBYQFDWByZJB(3, fkfsbyqfdwXBList);
			xbResult = true;
		} catch (Exception e) {
			xbResult = false;
		}
		// tb
		try {
			List<FKFSBYQFDWBYQ> fkfsbyqfdwTBList = fkfsbyqfdwTBDao
					.getAllFKFSBYQFDW();
			transferFKFSBYQFDWByZJB(301, fkfsbyqfdwTBList);
			tbResult = true;
		} catch (Exception e) {
			tbResult = false;
		}

		if (sbResult && hbResult && xbResult && tbResult) {
			result = true;
			System.out.println("transferFKFSBYQFDW:true");
		} else {
			result = false;
			System.out.println("transferFKFSBYQFDWsb:" + sbResult);
			System.out.println("transferFKFSBYQFDWhb:" + hbResult);
			System.out.println("transferFKFSBYQFDWxb:" + xbResult);
			System.out.println("transferFKFSBYQFDWtb:" + tbResult);
		}
		return result;
	}

	public FKFSBYQFDWLocalDao getFkfsbyqfdwLocalDao() {
		return fkfsbyqfdwLocalDao;
	}

	public void setFkfsbyqfdwLocalDao(FKFSBYQFDWLocalDao fkfsbyqfdwLocalDao) {
		this.fkfsbyqfdwLocalDao = fkfsbyqfdwLocalDao;
	}

	public FKFSBYQFDWBYQDao getFkfsbyqfdwTBDao() {
		return fkfsbyqfdwTBDao;
	}

	public void setFkfsbyqfdwTBDao(FKFSBYQFDWBYQDao fkfsbyqfdwTBDao) {
		this.fkfsbyqfdwTBDao = fkfsbyqfdwTBDao;
	}

	public FKFSBYQFDWBYQDao getFkfsbyqfdwSBDao() {
		return fkfsbyqfdwSBDao;
	}

	public void setFkfsbyqfdwSBDao(FKFSBYQFDWBYQDao fkfsbyqfdwSBDao) {
		this.fkfsbyqfdwSBDao = fkfsbyqfdwSBDao;
	}

	public FKFSBYQFDWBYQDao getFkfsbyqfdwXBDao() {
		return fkfsbyqfdwXBDao;
	}

	public void setFkfsbyqfdwXBDao(FKFSBYQFDWBYQDao fkfsbyqfdwXBDao) {
		this.fkfsbyqfdwXBDao = fkfsbyqfdwXBDao;
	}

}
