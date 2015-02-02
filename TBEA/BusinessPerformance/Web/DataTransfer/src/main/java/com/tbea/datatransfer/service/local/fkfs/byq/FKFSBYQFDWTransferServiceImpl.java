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

	@Override
	public boolean transferFKFSBYQFDW() {
		boolean result = false;
		try {
			SimpleDateFormat month_sdf = new SimpleDateFormat("yyyyMM");
			Date gxrq = null;
			// tb
			fkfsbyqfdwLocalDao.deleteFKFSBYQFDWLocalByQY(301);
			FKFSBYQFDWLocal fkfsbyqfdwLocal = null;
			List<FKFSBYQFDWBYQ> fkfsbyqfdwTBList = fkfsbyqfdwTBDao
					.getAllFKFSBYQFDW();
			for (FKFSBYQFDWBYQ fkfsbyqfdwTB : fkfsbyqfdwTBList) {
				fkfsbyqfdwLocal = new FKFSBYQFDWLocal();
				gxrq = fkfsbyqfdwTB.getGxrq();
				fkfsbyqfdwLocal.setGxrq(gxrq);
				fkfsbyqfdwLocal.setGsbm(fkfsbyqfdwTB.getGsbm());
				fkfsbyqfdwLocal.setNy(month_sdf.format(gxrq));
				fkfsbyqfdwLocal.setFdwhtddzlbs(fkfsbyqfdwTB.getFdwhtddzlbs());
				fkfsbyqfdwLocal.setFdwhtddzlje(fkfsbyqfdwTB.getFdwhtddzlje());
				fkfsbyqfdwLocal.setWyfkhtbs(fkfsbyqfdwTB.getWyfkhtbs());
				fkfsbyqfdwLocal.setWyfkhtje(fkfsbyqfdwTB.getWyfkhtje());
				fkfsbyqfdwLocal.setYfkxybfzshtbs(fkfsbyqfdwTB
						.getYfkxybfzshtbs());
				fkfsbyqfdwLocal.setYfkxybfzshtje(fkfsbyqfdwTB
						.getYfkxybfzshtje());
				fkfsbyqfdwLocal.setYfkzbfzsdsszjhtbs(fkfsbyqfdwTB
						.getYfkzbfzsdsszjhtbs());
				fkfsbyqfdwLocal.setYfkzbfzsdsszjhtje(fkfsbyqfdwTB
						.getYfkzbfzsdsszjhtje());
				fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtbs(fkfsbyqfdwTB
						.getHwjfhfkblxybfzbshtbs());
				fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtje(fkfsbyqfdwTB
						.getHwjfhfkblxybfzbshtje());
				fkfsbyqfdwLocal.setWddsjhtbs(fkfsbyqfdwTB.getWddsjhtbs());
				fkfsbyqfdwLocal.setWddsjhtje(fkfsbyqfdwTB.getWddsjhtje());
				fkfsbyqfdwLocal.setZbqdysegyhtbs(fkfsbyqfdwTB
						.getZbqdysegyhtbs());
				fkfsbyqfdwLocal.setZbqdysegyhtje(fkfsbyqfdwTB
						.getZbqdysegyhtje());
				fkfsbyqfdwLocal.setXkxhhtbs(fkfsbyqfdwTB.getXkxhhtbs());
				fkfsbyqfdwLocal.setXkxhhtje(fkfsbyqfdwTB.getXkxhhtje());
				fkfsbyqfdwLocal.setSfdrwc(fkfsbyqfdwTB.getSfdrwc());
				fkfsbyqfdwLocal.setQybh(301);
				fkfsbyqfdwLocalDao.merge(fkfsbyqfdwLocal);
			}

			// sb
			fkfsbyqfdwLocalDao.deleteFKFSBYQFDWLocalByQY(1);
			List<FKFSBYQFDWBYQ> fkfsbyqfdwSBList = fkfsbyqfdwSBDao
					.getAllFKFSBYQFDW();
			for (FKFSBYQFDWBYQ fkfsbyqfdwSB : fkfsbyqfdwSBList) {
				fkfsbyqfdwLocal = new FKFSBYQFDWLocal();
				gxrq = fkfsbyqfdwSB.getGxrq();
				fkfsbyqfdwLocal.setGxrq(gxrq);
				fkfsbyqfdwLocal.setGsbm(fkfsbyqfdwSB.getGsbm());
				fkfsbyqfdwLocal.setNy(month_sdf.format(gxrq));
				fkfsbyqfdwLocal.setFdwhtddzlbs(fkfsbyqfdwSB.getFdwhtddzlbs());
				fkfsbyqfdwLocal.setFdwhtddzlje(fkfsbyqfdwSB.getFdwhtddzlje());
				fkfsbyqfdwLocal.setWyfkhtbs(fkfsbyqfdwSB.getWyfkhtbs());
				fkfsbyqfdwLocal.setWyfkhtje(fkfsbyqfdwSB.getWyfkhtje());
				fkfsbyqfdwLocal.setYfkxybfzshtbs(fkfsbyqfdwSB
						.getYfkxybfzshtbs());
				fkfsbyqfdwLocal.setYfkxybfzshtje(fkfsbyqfdwSB
						.getYfkxybfzshtje());
				fkfsbyqfdwLocal.setYfkzbfzsdsszjhtbs(fkfsbyqfdwSB
						.getYfkzbfzsdsszjhtbs());
				fkfsbyqfdwLocal.setYfkzbfzsdsszjhtje(fkfsbyqfdwSB
						.getYfkzbfzsdsszjhtje());
				fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtbs(fkfsbyqfdwSB
						.getHwjfhfkblxybfzbshtbs());
				fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtje(fkfsbyqfdwSB
						.getHwjfhfkblxybfzbshtje());
				fkfsbyqfdwLocal.setWddsjhtbs(fkfsbyqfdwSB.getWddsjhtbs());
				fkfsbyqfdwLocal.setWddsjhtje(fkfsbyqfdwSB.getWddsjhtje());
				fkfsbyqfdwLocal.setZbqdysegyhtbs(fkfsbyqfdwSB
						.getZbqdysegyhtbs());
				fkfsbyqfdwLocal.setZbqdysegyhtje(fkfsbyqfdwSB
						.getZbqdysegyhtje());
				fkfsbyqfdwLocal.setXkxhhtbs(fkfsbyqfdwSB.getXkxhhtbs());
				fkfsbyqfdwLocal.setXkxhhtje(fkfsbyqfdwSB.getXkxhhtje());
				fkfsbyqfdwLocal.setSfdrwc(fkfsbyqfdwSB.getSfdrwc());
				fkfsbyqfdwLocal.setQybh(1);
				fkfsbyqfdwLocalDao.merge(fkfsbyqfdwLocal);
			}

			// xb
			fkfsbyqfdwLocalDao.deleteFKFSBYQFDWLocalByQY(3);
			List<FKFSBYQFDWBYQ> fkfsbyqfdwXBList = fkfsbyqfdwXBDao
					.getAllFKFSBYQFDW();
			for (FKFSBYQFDWBYQ fkfsbyqfdwXB : fkfsbyqfdwXBList) {
				fkfsbyqfdwLocal = new FKFSBYQFDWLocal();
				gxrq = fkfsbyqfdwXB.getGxrq();
				fkfsbyqfdwLocal.setGxrq(gxrq);
				fkfsbyqfdwLocal.setGsbm(fkfsbyqfdwXB.getGsbm());
				fkfsbyqfdwLocal.setNy(month_sdf.format(gxrq));
				fkfsbyqfdwLocal.setFdwhtddzlbs(fkfsbyqfdwXB.getFdwhtddzlbs());
				fkfsbyqfdwLocal.setFdwhtddzlje(fkfsbyqfdwXB.getFdwhtddzlje());
				fkfsbyqfdwLocal.setWyfkhtbs(fkfsbyqfdwXB.getWyfkhtbs());
				fkfsbyqfdwLocal.setWyfkhtje(fkfsbyqfdwXB.getWyfkhtje());
				fkfsbyqfdwLocal.setYfkxybfzshtbs(fkfsbyqfdwXB
						.getYfkxybfzshtbs());
				fkfsbyqfdwLocal.setYfkxybfzshtje(fkfsbyqfdwXB
						.getYfkxybfzshtje());
				fkfsbyqfdwLocal.setYfkzbfzsdsszjhtbs(fkfsbyqfdwXB
						.getYfkzbfzsdsszjhtbs());
				fkfsbyqfdwLocal.setYfkzbfzsdsszjhtje(fkfsbyqfdwXB
						.getYfkzbfzsdsszjhtje());
				fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtbs(fkfsbyqfdwXB
						.getHwjfhfkblxybfzbshtbs());
				fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtje(fkfsbyqfdwXB
						.getHwjfhfkblxybfzbshtje());
				fkfsbyqfdwLocal.setWddsjhtbs(fkfsbyqfdwXB.getWddsjhtbs());
				fkfsbyqfdwLocal.setWddsjhtje(fkfsbyqfdwXB.getWddsjhtje());
				fkfsbyqfdwLocal.setZbqdysegyhtbs(fkfsbyqfdwXB
						.getZbqdysegyhtbs());
				fkfsbyqfdwLocal.setZbqdysegyhtje(fkfsbyqfdwXB
						.getZbqdysegyhtje());
				fkfsbyqfdwLocal.setXkxhhtbs(fkfsbyqfdwXB.getXkxhhtbs());
				fkfsbyqfdwLocal.setXkxhhtje(fkfsbyqfdwXB.getXkxhhtje());
				fkfsbyqfdwLocal.setSfdrwc(fkfsbyqfdwXB.getSfdrwc());
				fkfsbyqfdwLocal.setQybh(3);
				fkfsbyqfdwLocalDao.merge(fkfsbyqfdwLocal);
			}
			// hb
			SimpleDateFormat timeFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			fkfsbyqfdwLocalDao.deleteFKFSBYQFDWLocalByQY(2);
			WebServiceClient webServiceClient = new WebServiceClient();
			List<Map<String, Object>> recList = webServiceClient.getRec(
					"web_test", "123456", "yszk_ws_htfkfstj_byq_fdw");
			for (Map<String, Object> recMap : recList) {
				fkfsbyqfdwLocal = new FKFSBYQFDWLocal();
				gxrq = CommonMethod
						.objectToDate(timeFormat, recMap.get("gxrq"));
				fkfsbyqfdwLocal.setGxrq(gxrq);
				fkfsbyqfdwLocal.setGsbm(String.valueOf(recMap.get("gsbm")));
				fkfsbyqfdwLocal.setNy(month_sdf.format(gxrq));
				fkfsbyqfdwLocal.setFdwhtddzlbs(Integer.valueOf(String
						.valueOf(recMap.get("fdwhtddzlbs"))));
				fkfsbyqfdwLocal.setFdwhtddzlje(CommonMethod
						.objectToDouble(recMap.get("fdwhtddzlje")));
				fkfsbyqfdwLocal.setWyfkhtbs(Integer.valueOf(String
						.valueOf(recMap.get("wyfkhtbs"))));
				fkfsbyqfdwLocal.setWyfkhtje(CommonMethod.objectToDouble(recMap
						.get("wyfkhtje")));
				fkfsbyqfdwLocal.setYfkxybfzshtbs(Integer.valueOf(String
						.valueOf(recMap.get("yfkxybfzshtbs"))));
				fkfsbyqfdwLocal.setYfkxybfzshtje(CommonMethod
						.objectToDouble(recMap.get("yfkxybfzshtje")));
				fkfsbyqfdwLocal.setYfkzbfzsdsszjhtbs(Integer.valueOf(String
						.valueOf(recMap.get("yfkzbfzsdsszjhtbs"))));
				fkfsbyqfdwLocal.setYfkzbfzsdsszjhtje(Double.valueOf(String
						.valueOf(recMap.get("yfkzbfzsdsszjhtje")).replace(",",
								"")));
				fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtbs(Integer.valueOf(String
						.valueOf(recMap.get("hwjfhfkblxybfzbshtbs"))));
				fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtje(Double.valueOf(String
						.valueOf(recMap.get("hwjfhfkblxybfzbshtje")).replace(
								",", "")));
				fkfsbyqfdwLocal.setWddsjhtbs(Integer.valueOf(String
						.valueOf(recMap.get("wddsjhtbs"))));
				fkfsbyqfdwLocal.setWddsjhtje(CommonMethod.objectToDouble(recMap
						.get("wddsjhtje")));
				fkfsbyqfdwLocal.setZbqdysegyhtbs(Integer.valueOf(String
						.valueOf(recMap.get("zbqdysegyhtbs"))));
				fkfsbyqfdwLocal.setZbqdysegyhtje(CommonMethod
						.objectToDouble(recMap.get("zbqdysegyhtje")));
				fkfsbyqfdwLocal.setXkxhhtbs(Integer.valueOf(String
						.valueOf(recMap.get("xkxhhtbs"))));
				fkfsbyqfdwLocal.setXkxhhtje(CommonMethod.objectToDouble(recMap
						.get("xkxhhtje")));
				fkfsbyqfdwLocal.setSfdrwc(String.valueOf(recMap.get("sfdrwc")));
				fkfsbyqfdwLocal.setQybh(2);
				fkfsbyqfdwLocalDao.merge(fkfsbyqfdwLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
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
