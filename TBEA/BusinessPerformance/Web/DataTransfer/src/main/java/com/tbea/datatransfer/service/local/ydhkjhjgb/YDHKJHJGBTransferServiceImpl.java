package com.tbea.datatransfer.service.local.ydhkjhjgb;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.local.ydhkjhjgb.YDHKJHJGBLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.ydhkjhjgb.YDHKJHJGBBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.ydhkjhjgb.YDHKJHJGBXLDao;
import com.tbea.datatransfer.model.entity.local.YDHKJHJGBLocal;
import com.tbea.datatransfer.model.entity.zjbyq.YDHKJHJGBBYQ;
import com.tbea.datatransfer.model.entity.zjxl.YDHKJHJGBXL;
import com.tbea.datatransfer.service.webservice.WebServiceClient;

@Transactional("transactionManager")
public class YDHKJHJGBTransferServiceImpl implements YDHKJHJGBTransferService {

	private YDHKJHJGBLocalDao ydhkjhjgbLocalDao;

	private YDHKJHJGBXLDao ydhkjhjgbDLDao;

	private YDHKJHJGBXLDao ydhkjhjgbLLDao;

	private YDHKJHJGBBYQDao ydhkjhjgbTBDao;

	private YDHKJHJGBBYQDao ydhkjhjgbSBDao;

	private YDHKJHJGBXLDao ydhkjhjgbXLDao;

	private YDHKJHJGBBYQDao ydhkjhjgbXBDao;

	@Override
	public boolean transferYDHKJHJGB() {
		boolean result = false;
		try {
			// dl
			ydhkjhjgbLocalDao.deleteYDHKJHJGBLocalByQY(6);
			YDHKJHJGBLocal ydhkjhjgbLocal = null;
			List<YDHKJHJGBXL> ydhkjhjgbDLList = ydhkjhjgbDLDao
					.getAllYDHKJHJGB();
			for (YDHKJHJGBXL ydhkjhjgbDL : ydhkjhjgbDLList) {
				ydhkjhjgbLocal = new YDHKJHJGBLocal();
				ydhkjhjgbLocal.setGxrq(ydhkjhjgbDL.getGxrq());
				ydhkjhjgbLocal.setGsbm(ydhkjhjgbDL.getGsbm());
				ydhkjhjgbLocal.setQbkhyqyszk(ydhkjhjgbDL.getQbkhyqyszk());
				ydhkjhjgbLocal.setQbkhyqk(ydhkjhjgbDL.getQbkhyqk());
				ydhkjhjgbLocal.setQbkhwdqyszk(ydhkjhjgbDL.getQbkhwdqyszk());
				ydhkjhjgbLocal.setQbkhwdqk(ydhkjhjgbDL.getQbkhwdqk());
				ydhkjhjgbLocal.setZqkhyqyszk(ydhkjhjgbDL.getZqkhyqyszk());
				ydhkjhjgbLocal.setZqkhyqk(ydhkjhjgbDL.getZqkhyqk());
				ydhkjhjgbLocal.setZqkhwdqyszk(ydhkjhjgbDL.getZqkhwdqyszk());
				ydhkjhjgbLocal.setZqkhwdqk(ydhkjhjgbDL.getZqkhwdqk());
				ydhkjhjgbLocal.setXyqsk(ydhkjhjgbDL.getXyqsk());
				ydhkjhjgbLocal.setGyqsk(ydhkjhjgbDL.getGyqsk());
				ydhkjhjgbLocal.setSfdrwc(ydhkjhjgbDL.getSfdrwc());
				ydhkjhjgbLocal.setQybh(6);
				ydhkjhjgbLocalDao.merge(ydhkjhjgbLocal);
			}
			// ll
			ydhkjhjgbLocalDao.deleteYDHKJHJGBLocalByQY(4);
			List<YDHKJHJGBXL> ydhkjhjgbLLList = ydhkjhjgbLLDao
					.getAllYDHKJHJGB();
			for (YDHKJHJGBXL ydhkjhjgbLL : ydhkjhjgbLLList) {
				ydhkjhjgbLocal = new YDHKJHJGBLocal();
				ydhkjhjgbLocal.setGxrq(ydhkjhjgbLL.getGxrq());
				ydhkjhjgbLocal.setGsbm(ydhkjhjgbLL.getGsbm());
				ydhkjhjgbLocal.setQbkhyqyszk(ydhkjhjgbLL.getQbkhyqyszk());
				ydhkjhjgbLocal.setQbkhyqk(ydhkjhjgbLL.getQbkhyqk());
				ydhkjhjgbLocal.setQbkhwdqyszk(ydhkjhjgbLL.getQbkhwdqyszk());
				ydhkjhjgbLocal.setQbkhwdqk(ydhkjhjgbLL.getQbkhwdqk());
				ydhkjhjgbLocal.setZqkhyqyszk(ydhkjhjgbLL.getZqkhyqyszk());
				ydhkjhjgbLocal.setZqkhyqk(ydhkjhjgbLL.getZqkhyqk());
				ydhkjhjgbLocal.setZqkhwdqyszk(ydhkjhjgbLL.getZqkhwdqyszk());
				ydhkjhjgbLocal.setZqkhwdqk(ydhkjhjgbLL.getZqkhwdqk());
				ydhkjhjgbLocal.setXyqsk(ydhkjhjgbLL.getXyqsk());
				ydhkjhjgbLocal.setGyqsk(ydhkjhjgbLL.getGyqsk());
				ydhkjhjgbLocal.setSfdrwc(ydhkjhjgbLL.getSfdrwc());
				ydhkjhjgbLocal.setQybh(4);
				ydhkjhjgbLocalDao.merge(ydhkjhjgbLocal);
			}
			// xl
			ydhkjhjgbLocalDao.deleteYDHKJHJGBLocalByQY(5);
			List<YDHKJHJGBXL> ydhkjhjgbXLList = ydhkjhjgbXLDao
					.getAllYDHKJHJGB();
			for (YDHKJHJGBXL ydhkjhjgbXL : ydhkjhjgbXLList) {
				ydhkjhjgbLocal = new YDHKJHJGBLocal();
				ydhkjhjgbLocal.setGxrq(ydhkjhjgbXL.getGxrq());
				ydhkjhjgbLocal.setGsbm(ydhkjhjgbXL.getGsbm());
				ydhkjhjgbLocal.setQbkhyqyszk(ydhkjhjgbXL.getQbkhyqyszk());
				ydhkjhjgbLocal.setQbkhyqk(ydhkjhjgbXL.getQbkhyqk());
				ydhkjhjgbLocal.setQbkhwdqyszk(ydhkjhjgbXL.getQbkhwdqyszk());
				ydhkjhjgbLocal.setQbkhwdqk(ydhkjhjgbXL.getQbkhwdqk());
				ydhkjhjgbLocal.setZqkhyqyszk(ydhkjhjgbXL.getZqkhyqyszk());
				ydhkjhjgbLocal.setZqkhyqk(ydhkjhjgbXL.getZqkhyqk());
				ydhkjhjgbLocal.setZqkhwdqyszk(ydhkjhjgbXL.getZqkhwdqyszk());
				ydhkjhjgbLocal.setZqkhwdqk(ydhkjhjgbXL.getZqkhwdqk());
				ydhkjhjgbLocal.setXyqsk(ydhkjhjgbXL.getXyqsk());
				ydhkjhjgbLocal.setGyqsk(ydhkjhjgbXL.getGyqsk());
				ydhkjhjgbLocal.setSfdrwc(ydhkjhjgbXL.getSfdrwc());
				ydhkjhjgbLocal.setQybh(5);
				ydhkjhjgbLocalDao.merge(ydhkjhjgbLocal);
			}
			// tb
			ydhkjhjgbLocalDao.deleteYDHKJHJGBLocalByQY(301);
			List<YDHKJHJGBBYQ> ydhkjhjgbTBList = ydhkjhjgbTBDao
					.getAllYDHKJHJGB();
			for (YDHKJHJGBBYQ ydhkjhjgbTB : ydhkjhjgbTBList) {
				ydhkjhjgbLocal = new YDHKJHJGBLocal();
				ydhkjhjgbLocal.setGxrq(ydhkjhjgbTB.getGxrq());
				ydhkjhjgbLocal.setGsbm(ydhkjhjgbTB.getGsbm());
				ydhkjhjgbLocal.setQbkhyqyszk(ydhkjhjgbTB.getQbkhyqyszk());
				ydhkjhjgbLocal.setQbkhyqk(ydhkjhjgbTB.getQbkhyqk());
				ydhkjhjgbLocal.setQbkhwdqyszk(ydhkjhjgbTB.getQbkhwdqyszk());
				ydhkjhjgbLocal.setQbkhwdqk(ydhkjhjgbTB.getQbkhwdqk());
				ydhkjhjgbLocal.setZqkhyqyszk(ydhkjhjgbTB.getZqkhyqyszk());
				ydhkjhjgbLocal.setZqkhyqk(ydhkjhjgbTB.getZqkhyqk());
				ydhkjhjgbLocal.setZqkhwdqyszk(ydhkjhjgbTB.getZqkhwdqyszk());
				ydhkjhjgbLocal.setZqkhwdqk(ydhkjhjgbTB.getZqkhwdqk());
				ydhkjhjgbLocal.setXyqsk(ydhkjhjgbTB.getXyqsk());
				ydhkjhjgbLocal.setGyqsk(ydhkjhjgbTB.getGyqsk());
				ydhkjhjgbLocal.setSfdrwc(ydhkjhjgbTB.getSfdrwc());
				ydhkjhjgbLocal.setQybh(301);
				ydhkjhjgbLocalDao.merge(ydhkjhjgbLocal);
			}
			// sb
			ydhkjhjgbLocalDao.deleteYDHKJHJGBLocalByQY(1);
			List<YDHKJHJGBBYQ> ydhkjhjgbSBList = ydhkjhjgbSBDao
					.getAllYDHKJHJGB();
			for (YDHKJHJGBBYQ ydhkjhjgbSB : ydhkjhjgbSBList) {
				ydhkjhjgbLocal = new YDHKJHJGBLocal();
				ydhkjhjgbLocal.setGxrq(ydhkjhjgbSB.getGxrq());
				ydhkjhjgbLocal.setGsbm(ydhkjhjgbSB.getGsbm());
				ydhkjhjgbLocal.setQbkhyqyszk(ydhkjhjgbSB.getQbkhyqyszk());
				ydhkjhjgbLocal.setQbkhyqk(ydhkjhjgbSB.getQbkhyqk());
				ydhkjhjgbLocal.setQbkhwdqyszk(ydhkjhjgbSB.getQbkhwdqyszk());
				ydhkjhjgbLocal.setQbkhwdqk(ydhkjhjgbSB.getQbkhwdqk());
				ydhkjhjgbLocal.setZqkhyqyszk(ydhkjhjgbSB.getZqkhyqyszk());
				ydhkjhjgbLocal.setZqkhyqk(ydhkjhjgbSB.getZqkhyqk());
				ydhkjhjgbLocal.setZqkhwdqyszk(ydhkjhjgbSB.getZqkhwdqyszk());
				ydhkjhjgbLocal.setZqkhwdqk(ydhkjhjgbSB.getZqkhwdqk());
				ydhkjhjgbLocal.setXyqsk(ydhkjhjgbSB.getXyqsk());
				ydhkjhjgbLocal.setGyqsk(ydhkjhjgbSB.getGyqsk());
				ydhkjhjgbLocal.setSfdrwc(ydhkjhjgbSB.getSfdrwc());
				ydhkjhjgbLocal.setQybh(1);
				ydhkjhjgbLocalDao.merge(ydhkjhjgbLocal);
			}
			// xb
			ydhkjhjgbLocalDao.deleteYDHKJHJGBLocalByQY(3);
			List<YDHKJHJGBBYQ> ydhkjhjgbXBList = ydhkjhjgbXBDao
					.getAllYDHKJHJGB();
			for (YDHKJHJGBBYQ ydhkjhjgbXB : ydhkjhjgbXBList) {
				ydhkjhjgbLocal = new YDHKJHJGBLocal();
				ydhkjhjgbLocal.setGxrq(ydhkjhjgbXB.getGxrq());
				ydhkjhjgbLocal.setGsbm(ydhkjhjgbXB.getGsbm());
				ydhkjhjgbLocal.setQbkhyqyszk(ydhkjhjgbXB.getQbkhyqyszk());
				ydhkjhjgbLocal.setQbkhyqk(ydhkjhjgbXB.getQbkhyqk());
				ydhkjhjgbLocal.setQbkhwdqyszk(ydhkjhjgbXB.getQbkhwdqyszk());
				ydhkjhjgbLocal.setQbkhwdqk(ydhkjhjgbXB.getQbkhwdqk());
				ydhkjhjgbLocal.setZqkhyqyszk(ydhkjhjgbXB.getZqkhyqyszk());
				ydhkjhjgbLocal.setZqkhyqk(ydhkjhjgbXB.getZqkhyqk());
				ydhkjhjgbLocal.setZqkhwdqyszk(ydhkjhjgbXB.getZqkhwdqyszk());
				ydhkjhjgbLocal.setZqkhwdqk(ydhkjhjgbXB.getZqkhwdqk());
				ydhkjhjgbLocal.setXyqsk(ydhkjhjgbXB.getXyqsk());
				ydhkjhjgbLocal.setGyqsk(ydhkjhjgbXB.getGyqsk());
				ydhkjhjgbLocal.setSfdrwc(ydhkjhjgbXB.getSfdrwc());
				ydhkjhjgbLocal.setQybh(3);
				ydhkjhjgbLocalDao.merge(ydhkjhjgbLocal);
			}

			// hb
			SimpleDateFormat timeFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			ydhkjhjgbLocalDao.deleteYDHKJHJGBLocalByQY(2);
			WebServiceClient webServiceClient = new WebServiceClient();
			List<Map<String, Object>> recList = webServiceClient.getRec(
					"web_test", "123456", "yszk_ws_ydhkjhjgb");
			for (Map<String, Object> recMap : recList) {
				ydhkjhjgbLocal = new YDHKJHJGBLocal();
				ydhkjhjgbLocal.setGxrq(CommonMethod.objectToDate(timeFormat,
						recMap.get("gxrq")));
				ydhkjhjgbLocal.setGsbm(String.valueOf(recMap.get("gsbm")));
				ydhkjhjgbLocal.setQbkhyqyszk(CommonMethod.objectToDouble(recMap
						.get("qbkhyqyszk")));
				ydhkjhjgbLocal.setQbkhyqk(CommonMethod.objectToDouble(recMap
						.get("qbkhyqk")));
				ydhkjhjgbLocal.setQbkhwdqyszk(CommonMethod
						.objectToDouble(recMap.get("qbkhwdqyszk")));
				ydhkjhjgbLocal.setQbkhwdqk(CommonMethod.objectToDouble(recMap
						.get("qbkhwdqk")));
				ydhkjhjgbLocal.setZqkhyqyszk(CommonMethod.objectToDouble(recMap
						.get("zqkhyqyszk")));
				ydhkjhjgbLocal.setZqkhyqk(CommonMethod.objectToDouble(recMap
						.get("zqkhyqk")));
				ydhkjhjgbLocal.setZqkhwdqyszk(CommonMethod
						.objectToDouble(recMap.get("zqkhwdqyszk")));
				ydhkjhjgbLocal.setZqkhwdqk(CommonMethod.objectToDouble(recMap
						.get("zqkhwdqk")));
				ydhkjhjgbLocal.setXyqsk(CommonMethod.objectToDouble(recMap
						.get("xyqsk")));
				ydhkjhjgbLocal.setGyqsk(CommonMethod.objectToDouble(recMap
						.get("gyqsk")));
				ydhkjhjgbLocal.setSfdrwc(String.valueOf(recMap.get("sfdrwc")));
				ydhkjhjgbLocal.setQybh(2);
				ydhkjhjgbLocalDao.merge(ydhkjhjgbLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferYDHKJHJGB:" + result);
		}
		return result;
	}

	public YDHKJHJGBLocalDao getYdhkjhjgbLocalDao() {
		return ydhkjhjgbLocalDao;
	}

	public void setYdhkjhjgbLocalDao(YDHKJHJGBLocalDao ydhkjhjgbLocalDao) {
		this.ydhkjhjgbLocalDao = ydhkjhjgbLocalDao;
	}

	public YDHKJHJGBXLDao getYdhkjhjgbDLDao() {
		return ydhkjhjgbDLDao;
	}

	public void setYdhkjhjgbDLDao(YDHKJHJGBXLDao ydhkjhjgbDLDao) {
		this.ydhkjhjgbDLDao = ydhkjhjgbDLDao;
	}

	public YDHKJHJGBXLDao getYdhkjhjgbLLDao() {
		return ydhkjhjgbLLDao;
	}

	public void setYdhkjhjgbLLDao(YDHKJHJGBXLDao ydhkjhjgbLLDao) {
		this.ydhkjhjgbLLDao = ydhkjhjgbLLDao;
	}

	public YDHKJHJGBBYQDao getYdhkjhjgbTBDao() {
		return ydhkjhjgbTBDao;
	}

	public void setYdhkjhjgbTBDao(YDHKJHJGBBYQDao ydhkjhjgbTBDao) {
		this.ydhkjhjgbTBDao = ydhkjhjgbTBDao;
	}

	public YDHKJHJGBBYQDao getYdhkjhjgbSBDao() {
		return ydhkjhjgbSBDao;
	}

	public void setYdhkjhjgbSBDao(YDHKJHJGBBYQDao ydhkjhjgbSBDao) {
		this.ydhkjhjgbSBDao = ydhkjhjgbSBDao;
	}

	public YDHKJHJGBXLDao getYdhkjhjgbXLDao() {
		return ydhkjhjgbXLDao;
	}

	public void setYdhkjhjgbXLDao(YDHKJHJGBXLDao ydhkjhjgbXLDao) {
		this.ydhkjhjgbXLDao = ydhkjhjgbXLDao;
	}

	public YDHKJHJGBBYQDao getYdhkjhjgbXBDao() {
		return ydhkjhjgbXBDao;
	}

	public void setYdhkjhjgbXBDao(YDHKJHJGBBYQDao ydhkjhjgbXBDao) {
		this.ydhkjhjgbXBDao = ydhkjhjgbXBDao;
	}

}
