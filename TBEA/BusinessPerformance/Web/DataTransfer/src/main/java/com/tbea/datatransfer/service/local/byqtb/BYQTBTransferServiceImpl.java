package com.tbea.datatransfer.service.local.byqtb;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.local.byqtb.BYQTBLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.byqtb.BYQTBDao;
import com.tbea.datatransfer.model.entity.local.BYQTBLocal;
import com.tbea.datatransfer.model.entity.zjbyq.BYQTB;
import com.tbea.datatransfer.service.webservice.WebServiceClient;

@Transactional("transactionManager")
public class BYQTBTransferServiceImpl implements BYQTBTransferService {

	private BYQTBLocalDao byqtbLocalDao;

	private BYQTBDao byqtbSBDao;

	private BYQTBDao byqtbXBDao;

	@Override
	public boolean transferBYQTB() {
		boolean result = false;
		try {
			// sb
			byqtbLocalDao.deleteBYQTBLocalByQY(1);
			BYQTBLocal byqtbLocal = null;
			List<BYQTB> byqtbSBList = byqtbSBDao.getAllBYQTB();
			for (BYQTB byqtbSB : byqtbSBList) {
				byqtbLocal = new BYQTBLocal();
				byqtbLocal.setGxrq(byqtbSB.getGxrq());
				byqtbLocal.setXmxx(byqtbSB.getXmxx());
				byqtbLocal.setTbbjsj(byqtbSB.getTbbjsj());
				byqtbLocal.setYjjhsj(byqtbSB.getYjjhsj());
				byqtbLocal.setXh(byqtbSB.getXh());
				byqtbLocal.setDy(byqtbSB.getDy());
				byqtbLocal.setCl(byqtbSB.getCl());
				byqtbLocal.setCz(byqtbSB.getCz());
				byqtbLocal.setYjkbsj(byqtbSB.getYjkbsj());
				byqtbLocal.setYczbgl(byqtbSB.getYczbgl());
				byqtbLocal.setGgph(byqtbSB.getGgph());
				byqtbLocal.setGgyl(byqtbSB.getGgyl());
				byqtbLocal.setGgdj(byqtbSB.getGgdj());
				byqtbLocal.setDjtyl(byqtbSB.getDjtyl());
				byqtbLocal.setDjtdj(byqtbSB.getDjtdj());
				byqtbLocal.setByqyyl(byqtbSB.getByqyyl());
				byqtbLocal.setByqydj(byqtbSB.getByqydj());
				byqtbLocal.setGcyl(byqtbSB.getGcyl());
				byqtbLocal.setGcdj(byqtbSB.getGcdj());
				byqtbLocal.setZbyl(byqtbSB.getZbyl());
				byqtbLocal.setZbdj(byqtbSB.getZbdj());
				byqtbLocal.setQtclcb(byqtbSB.getQtclcb());
				byqtbLocal.setRgjzzfy(byqtbSB.getRgjzzfy());
				byqtbLocal.setYf(byqtbSB.getYf());
				byqtbLocal.setQybh(1);
				byqtbLocalDao.merge(byqtbLocal);
			}
			// xb
			byqtbLocalDao.deleteBYQTBLocalByQY(3);
			List<BYQTB> byqtbXBList = byqtbXBDao.getAllBYQTB();
			for (BYQTB byqtbXB : byqtbXBList) {
				byqtbLocal = new BYQTBLocal();
				byqtbLocal.setGxrq(byqtbXB.getGxrq());
				byqtbLocal.setXmxx(byqtbXB.getXmxx());
				byqtbLocal.setTbbjsj(byqtbXB.getTbbjsj());
				byqtbLocal.setYjjhsj(byqtbXB.getYjjhsj());
				byqtbLocal.setXh(byqtbXB.getXh());
				byqtbLocal.setDy(byqtbXB.getDy());
				byqtbLocal.setCl(byqtbXB.getCl());
				byqtbLocal.setCz(byqtbXB.getCz());
				byqtbLocal.setYjkbsj(byqtbXB.getYjkbsj());
				byqtbLocal.setYczbgl(byqtbXB.getYczbgl());
				byqtbLocal.setGgph(byqtbXB.getGgph());
				byqtbLocal.setGgyl(byqtbXB.getGgyl());
				byqtbLocal.setGgdj(byqtbXB.getGgdj());
				byqtbLocal.setDjtyl(byqtbXB.getDjtyl());
				byqtbLocal.setDjtdj(byqtbXB.getDjtdj());
				byqtbLocal.setByqyyl(byqtbXB.getByqyyl());
				byqtbLocal.setByqydj(byqtbXB.getByqydj());
				byqtbLocal.setGcyl(byqtbXB.getGcyl());
				byqtbLocal.setGcdj(byqtbXB.getGcdj());
				byqtbLocal.setZbyl(byqtbXB.getZbyl());
				byqtbLocal.setZbdj(byqtbXB.getZbdj());
				byqtbLocal.setQtclcb(byqtbXB.getQtclcb());
				byqtbLocal.setRgjzzfy(byqtbXB.getRgjzzfy());
				byqtbLocal.setYf(byqtbXB.getYf());
				byqtbLocal.setQybh(3);
				byqtbLocalDao.merge(byqtbLocal);
			}
			// hb
			SimpleDateFormat timeFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			byqtbLocalDao.deleteBYQTBLocalByQY(2);
			WebServiceClient webServiceClient = new WebServiceClient();
			List<Map<String, Object>> recList = webServiceClient.getRec(
					"web_test", "123456", "cb_ws_byqtb");
			for (Map<String, Object> recMap : recList) {
				byqtbLocal = new BYQTBLocal();
				byqtbLocal.setGxrq(CommonMethod.objectToDate(timeFormat,
						recMap.get("gxrq")));
				byqtbLocal.setXmxx(String.valueOf(recMap.get("xmxx")));
				byqtbLocal.setTbbjsj(CommonMethod.objectToDate(timeFormat,
						recMap.get("tbbjsj")));
				byqtbLocal.setYjjhsj(CommonMethod.objectToDate(timeFormat,
						recMap.get("yjjhsj")));
				byqtbLocal.setXh(String.valueOf(recMap.get("xh")));
				byqtbLocal.setDy(String.valueOf(recMap.get("dy")));
				byqtbLocal.setCl(String.valueOf(recMap.get("cl")));
				byqtbLocal.setCz(CommonMethod.objectToDouble(recMap.get("cz")));
				byqtbLocal.setYjkbsj(String.valueOf(recMap.get("yjkbsj")));
				byqtbLocal.setYczbgl(CommonMethod.objectToDouble(recMap
						.get("yczbgl")));
				byqtbLocal.setGgph(String.valueOf(recMap.get("ggph")));
				byqtbLocal.setGgyl(CommonMethod.objectToDouble(recMap
						.get("ggyl")));
				byqtbLocal.setGgdj(CommonMethod.objectToDouble(recMap
						.get("ggdj")));
				byqtbLocal.setDjtyl(CommonMethod.objectToDouble(recMap
						.get("djtyl")));
				byqtbLocal.setDjtdj(CommonMethod.objectToDouble(recMap
						.get("djtdj")));
				byqtbLocal.setByqyyl(CommonMethod.objectToDouble(recMap
						.get("byqyyl")));
				byqtbLocal.setByqydj(CommonMethod.objectToDouble(recMap
						.get("byqydj")));
				byqtbLocal.setGcyl(CommonMethod.objectToDouble(recMap
						.get("gcyl")));
				byqtbLocal.setGcdj(CommonMethod.objectToDouble(recMap
						.get("gcdj")));
				byqtbLocal.setZbyl(CommonMethod.objectToDouble(recMap
						.get("zbyl")));
				byqtbLocal.setZbdj(CommonMethod.objectToDouble(recMap
						.get("zbdj")));
				byqtbLocal.setQtclcb(CommonMethod.objectToDouble(recMap
						.get("qtclcb")));
				byqtbLocal.setRgjzzfy(CommonMethod.objectToDouble(recMap
						.get("rgjzzfy")));
				byqtbLocal.setYf(CommonMethod.objectToDouble(recMap.get("yf")));
				byqtbLocal.setQybh(2);
				byqtbLocalDao.merge(byqtbLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public BYQTBLocalDao getByqtbLocalDao() {
		return byqtbLocalDao;
	}

	public void setByqtbLocalDao(BYQTBLocalDao byqtbLocalDao) {
		this.byqtbLocalDao = byqtbLocalDao;
	}

	public BYQTBDao getByqtbSBDao() {
		return byqtbSBDao;
	}

	public void setByqtbSBDao(BYQTBDao byqtbSBDao) {
		this.byqtbSBDao = byqtbSBDao;
	}

	public BYQTBDao getByqtbXBDao() {
		return byqtbXBDao;
	}

	public void setByqtbXBDao(BYQTBDao byqtbXBDao) {
		this.byqtbXBDao = byqtbXBDao;
	}

}
