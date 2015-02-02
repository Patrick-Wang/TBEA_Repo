package com.tbea.datatransfer.service.local.byqwg;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.local.byqwg.BYQWGLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.byqwg.BYQWGDao;
import com.tbea.datatransfer.model.entity.local.BYQWGLocal;
import com.tbea.datatransfer.model.entity.zjbyq.BYQWG;
import com.tbea.datatransfer.service.webservice.WebServiceClient;

@Transactional("transactionManager")
public class BYQWGTransferServiceImpl implements BYQWGTransferService {

	private BYQWGLocalDao byqwgLocalDao;

	private BYQWGDao byqwgSBDao;

	private BYQWGDao byqwgXBDao;

	@Override
	public boolean transferBYQWG() {
		boolean result = false;
		try {
			// sb
			byqwgLocalDao.deleteBYQWGLocalByQY(1);
			BYQWGLocal byqwgLocal = null;
			List<BYQWG> byqwgSBList = byqwgSBDao.getAllBYQWG();
			for (BYQWG byqwgSB : byqwgSBList) {
				byqwgLocal = new BYQWGLocal();
				byqwgLocal.setGxrq(byqwgSB.getGxrq());
				byqwgLocal.setZxcpbh(byqwgSB.getZxcpbh());
				byqwgLocal.setWgsj(byqwgSB.getWgsj());
				byqwgLocal.setSjcz(byqwgSB.getSjcz());
				byqwgLocal.setGgph(byqwgSB.getGgph());
				byqwgLocal.setGgyl(byqwgSB.getGgyl());
				byqwgLocal.setGgdj(byqwgSB.getGgdj());
				byqwgLocal.setDjtyl(byqwgSB.getDjtyl());
				byqwgLocal.setDjtdj(byqwgSB.getDjtdj());
				byqwgLocal.setTjgf(byqwgSB.getTjgf());
				byqwgLocal.setByqyyl(byqwgSB.getByqyyl());
				byqwgLocal.setByqydj(byqwgSB.getByqydj());
				byqwgLocal.setGcyl(byqwgSB.getGcyl());
				byqwgLocal.setGcdj(byqwgSB.getGcdj());
				byqwgLocal.setZbyl(byqwgSB.getZbyl());
				byqwgLocal.setZbdj(byqwgSB.getZbdj());
				byqwgLocal.setQtclcb(byqwgSB.getQtclcb());
				byqwgLocal.setRgjzzfy(byqwgSB.getRgjzzfy());
				byqwgLocal.setYf(byqwgSB.getYf());
				byqwgLocal.setQybh(1);
				byqwgLocalDao.merge(byqwgLocal);
			}
			// xb
			byqwgLocalDao.deleteBYQWGLocalByQY(3);
			List<BYQWG> byqwgXBList = byqwgXBDao.getAllBYQWG();
			for (BYQWG byqwgXB : byqwgXBList) {
				byqwgLocal = new BYQWGLocal();
				byqwgLocal.setGxrq(byqwgXB.getGxrq());
				byqwgLocal.setZxcpbh(byqwgXB.getZxcpbh());
				byqwgLocal.setWgsj(byqwgXB.getWgsj());
				byqwgLocal.setSjcz(byqwgXB.getSjcz());
				byqwgLocal.setGgph(byqwgXB.getGgph());
				byqwgLocal.setGgyl(byqwgXB.getGgyl());
				byqwgLocal.setGgdj(byqwgXB.getGgdj());
				byqwgLocal.setDjtyl(byqwgXB.getDjtyl());
				byqwgLocal.setDjtdj(byqwgXB.getDjtdj());
				byqwgLocal.setTjgf(byqwgXB.getTjgf());
				byqwgLocal.setByqyyl(byqwgXB.getByqyyl());
				byqwgLocal.setByqydj(byqwgXB.getByqydj());
				byqwgLocal.setGcyl(byqwgXB.getGcyl());
				byqwgLocal.setGcdj(byqwgXB.getGcdj());
				byqwgLocal.setZbyl(byqwgXB.getZbyl());
				byqwgLocal.setZbdj(byqwgXB.getZbdj());
				byqwgLocal.setQtclcb(byqwgXB.getQtclcb());
				byqwgLocal.setRgjzzfy(byqwgXB.getRgjzzfy());
				byqwgLocal.setYf(byqwgXB.getYf());
				byqwgLocal.setQybh(1);
				byqwgLocalDao.merge(byqwgLocal);
			}
			// hb
			SimpleDateFormat timeFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			byqwgLocalDao.deleteBYQWGLocalByQY(2);
			WebServiceClient webServiceClient = new WebServiceClient();
			List<Map<String, Object>> recList = webServiceClient.getRec(
					"web_test", "123456", "cb_ws_byqwg");
			for (Map<String, Object> recMap : recList) {
				byqwgLocal = new BYQWGLocal();
				byqwgLocal.setGxrq(CommonMethod.objectToDate(timeFormat,
						recMap.get("gxrq")));
				byqwgLocal.setZxcpbh(CommonMethod.objectToInteger(recMap
						.get("zxcpbh")));
				byqwgLocal.setWgsj(String.valueOf(recMap.get("wgsj")));
				byqwgLocal.setSjcz(CommonMethod.objectToDouble(recMap
						.get("sjcz")));
				byqwgLocal.setGgph(String.valueOf(recMap.get("ggph")));
				byqwgLocal.setGgyl(CommonMethod.objectToDouble(recMap
						.get("ggyl")));
				byqwgLocal.setGgdj(CommonMethod.objectToDouble(recMap
						.get("ggdj")));
				byqwgLocal.setDjtyl(CommonMethod.objectToDouble(recMap
						.get("djtyl")));
				byqwgLocal.setDjtdj(CommonMethod.objectToDouble(recMap
						.get("djtdj")));
				byqwgLocal.setTjgf(CommonMethod.objectToDouble(recMap
						.get("tjgf")));
				byqwgLocal.setByqyyl(CommonMethod.objectToDouble(recMap
						.get("byqyyl")));
				byqwgLocal.setByqydj(CommonMethod.objectToDouble(recMap
						.get("byqydj")));
				byqwgLocal.setGcyl(CommonMethod.objectToDouble(recMap
						.get("gcyl")));
				byqwgLocal.setGcdj(CommonMethod.objectToDouble(recMap
						.get("gcdj")));
				byqwgLocal.setZbyl(CommonMethod.objectToDouble(recMap
						.get("zbyl")));
				byqwgLocal.setZbdj(CommonMethod.objectToDouble(recMap
						.get("zbdj")));
				byqwgLocal.setQtclcb(CommonMethod.objectToDouble(recMap
						.get("qtclcb")));
				byqwgLocal.setRgjzzfy(CommonMethod.objectToDouble(recMap
						.get("rgjzzfy")));
				byqwgLocal.setYf(CommonMethod.objectToDouble(recMap.get("yf")));
				byqwgLocal.setQybh(2);
				byqwgLocalDao.merge(byqwgLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public BYQWGLocalDao getByqwgLocalDao() {
		return byqwgLocalDao;
	}

	public void setByqwgLocalDao(BYQWGLocalDao byqwgLocalDao) {
		this.byqwgLocalDao = byqwgLocalDao;
	}

	public BYQWGDao getByqwgSBDao() {
		return byqwgSBDao;
	}

	public void setByqwgSBDao(BYQWGDao byqwgSBDao) {
		this.byqwgSBDao = byqwgSBDao;
	}

	public BYQWGDao getByqwgXBDao() {
		return byqwgXBDao;
	}

	public void setByqwgXBDao(BYQWGDao byqwgXBDao) {
		this.byqwgXBDao = byqwgXBDao;
	}

}
