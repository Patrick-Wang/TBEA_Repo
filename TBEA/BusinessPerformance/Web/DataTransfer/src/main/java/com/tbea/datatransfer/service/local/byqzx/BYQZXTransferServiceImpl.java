package com.tbea.datatransfer.service.local.byqzx;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.local.byqzx.BYQZXLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.byqzx.BYQZXDao;
import com.tbea.datatransfer.model.entity.local.BYQZXLocal;
import com.tbea.datatransfer.model.entity.zjbyq.BYQZX;
import com.tbea.datatransfer.service.webservice.WebServiceClient;

@Transactional("transactionManager")
public class BYQZXTransferServiceImpl implements BYQZXTransferService {

	private BYQZXLocalDao byqzxLocalDao;

	private BYQZXDao byqzxSBDao;

	private BYQZXDao byqzxXBDao;

	@Override
	public boolean transferBYQZX() {
		boolean result = false;
		try {
			// sb
			byqzxLocalDao.deleteBYQZXLocalByQY(1);
			BYQZXLocal byqzxLocal = null;
			List<BYQZX> byqzxSBList = byqzxSBDao.getAllBYQZX();
			for (BYQZX byqzxSB : byqzxSBList) {
				byqzxLocal = new BYQZXLocal();
				byqzxLocal.setId(CommonMethod.intcat(1, byqzxSB.getId()));
				byqzxLocal.setGxrq(byqzxSB.getGxrq());
				byqzxLocal
						.setTbcpbh(CommonMethod.intcat(1, byqzxSB.getTbcpbh()));
				byqzxLocal.setDdzxjd(byqzxSB.getDdzxjd());
				byqzxLocal.setHth(byqzxSB.getHth());
				byqzxLocal.setHtzbsj(byqzxSB.getHtzbsj());
				byqzxLocal.setJhsj(byqzxSB.getJhsj());
				byqzxLocal.setGzh(byqzxSB.getGzh());
				byqzxLocal.setCz(byqzxSB.getCz());
				byqzxLocal.setGgph(byqzxSB.getGgph());
				byqzxLocal.setGgyl(byqzxSB.getGgyl());
				byqzxLocal.setGgdj(byqzxSB.getGgdj());
				byqzxLocal.setDjtyl(byqzxSB.getDjtyl());
				byqzxLocal.setDjtdj(byqzxSB.getDjtdj());
				byqzxLocal.setTjgf(byqzxSB.getTjgf());
				byqzxLocal.setByqygg(byqzxSB.getByqygg());
				byqzxLocal.setByqyyl(byqzxSB.getByqyyl());
				byqzxLocal.setByqydj(byqzxSB.getByqydj());
				byqzxLocal.setGcyl(byqzxSB.getGcyl());
				byqzxLocal.setGcdj(byqzxSB.getGcdj());
				byqzxLocal.setZbyl(byqzxSB.getZbyl());
				byqzxLocal.setZbdj(byqzxSB.getZbdj());
				byqzxLocal.setQtclcb(byqzxSB.getQtclcb());
				byqzxLocal.setRgjzzfy(byqzxSB.getRgjzzfy());
				byqzxLocal.setYf(byqzxSB.getYf());
				byqzxLocal.setQybh(1);
				byqzxLocalDao.merge(byqzxLocal);
			}
			// xb
			byqzxLocalDao.deleteBYQZXLocalByQY(3);
			List<BYQZX> byqzxXBList = byqzxXBDao.getAllBYQZX();
			for (BYQZX byqzxXB : byqzxXBList) {
				byqzxLocal = new BYQZXLocal();
				byqzxLocal.setId(CommonMethod.intcat(3, byqzxXB.getId()));
				byqzxLocal.setGxrq(byqzxXB.getGxrq());
				byqzxLocal
						.setTbcpbh(CommonMethod.intcat(3, byqzxXB.getTbcpbh()));
				byqzxLocal.setDdzxjd(byqzxXB.getDdzxjd());
				byqzxLocal.setHth(byqzxXB.getHth());
				byqzxLocal.setHtzbsj(byqzxXB.getHtzbsj());
				byqzxLocal.setJhsj(byqzxXB.getJhsj());
				byqzxLocal.setGzh(byqzxXB.getGzh());
				byqzxLocal.setCz(byqzxXB.getCz());
				byqzxLocal.setGgph(byqzxXB.getGgph());
				byqzxLocal.setGgyl(byqzxXB.getGgyl());
				byqzxLocal.setGgdj(byqzxXB.getGgdj());
				byqzxLocal.setDjtyl(byqzxXB.getDjtyl());
				byqzxLocal.setDjtdj(byqzxXB.getDjtdj());
				byqzxLocal.setTjgf(byqzxXB.getTjgf());
				byqzxLocal.setByqygg(byqzxXB.getByqygg());
				byqzxLocal.setByqyyl(byqzxXB.getByqyyl());
				byqzxLocal.setByqydj(byqzxXB.getByqydj());
				byqzxLocal.setGcyl(byqzxXB.getGcyl());
				byqzxLocal.setGcdj(byqzxXB.getGcdj());
				byqzxLocal.setZbyl(byqzxXB.getZbyl());
				byqzxLocal.setZbdj(byqzxXB.getZbdj());
				byqzxLocal.setQtclcb(byqzxXB.getQtclcb());
				byqzxLocal.setRgjzzfy(byqzxXB.getRgjzzfy());
				byqzxLocal.setYf(byqzxXB.getYf());
				byqzxLocal.setQybh(3);
				byqzxLocalDao.merge(byqzxLocal);
			}
			// hb
			SimpleDateFormat timeFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			byqzxLocalDao.deleteBYQZXLocalByQY(2);
			WebServiceClient webServiceClient = new WebServiceClient();
			List<Map<String, Object>> recList = webServiceClient.getRec(
					"web_test", "123456", "cb_ws_byqzx");
			for (Map<String, Object> recMap : recList) {
				byqzxLocal = new BYQZXLocal();
				byqzxLocal.setId(CommonMethod.intcat(2,
						CommonMethod.objectToInteger(recMap.get("ID"))));
				byqzxLocal.setGxrq(CommonMethod.objectToDate(timeFormat,
						recMap.get("gxrq")));
				byqzxLocal.setTbcpbh(CommonMethod.intcat(2,
						CommonMethod.objectToInteger(recMap.get("tbcpbh"))));
				byqzxLocal.setDdzxjd(CommonMethod.objectToInteger(recMap
						.get("ddzxjd")));
				byqzxLocal.setHth(String.valueOf(recMap.get("hth")));
				byqzxLocal.setHtzbsj(CommonMethod.objectToDate(timeFormat,
						recMap.get("htzbsj")));
				byqzxLocal.setJhsj(CommonMethod.objectToDate(timeFormat,
						recMap.get("jhsj")));
				byqzxLocal.setGzh(String.valueOf(recMap.get("gzh")));
				byqzxLocal.setCz(CommonMethod.objectToDouble(recMap.get("cz")));
				byqzxLocal.setGgph(String.valueOf(recMap.get("ggph")));
				byqzxLocal.setGgyl(CommonMethod.objectToDouble(recMap
						.get("ggyl")));
				byqzxLocal.setGgdj(CommonMethod.objectToDouble(recMap
						.get("ggdj")));
				byqzxLocal.setDjtyl(CommonMethod.objectToDouble(recMap
						.get("djtyl")));
				byqzxLocal.setDjtdj(CommonMethod.objectToDouble(recMap
						.get("djtdj")));
				byqzxLocal.setTjgf(CommonMethod.objectToDouble(recMap
						.get("tjgf")));
				byqzxLocal.setByqygg(String.valueOf(recMap.get("byqygg")));
				byqzxLocal.setByqyyl(CommonMethod.objectToDouble(recMap
						.get("byqyyl")));
				byqzxLocal.setByqydj(CommonMethod.objectToDouble(recMap
						.get("byqydj")));
				byqzxLocal.setGcyl(CommonMethod.objectToDouble(recMap
						.get("gcyl")));
				byqzxLocal.setGcdj(CommonMethod.objectToDouble(recMap
						.get("gcdj")));
				byqzxLocal.setZbyl(CommonMethod.objectToDouble(recMap
						.get("zbyl")));
				byqzxLocal.setZbdj(CommonMethod.objectToDouble(recMap
						.get("zbdj")));
				byqzxLocal.setQtclcb(CommonMethod.objectToDouble(recMap
						.get("qtclcb")));
				byqzxLocal.setRgjzzfy(CommonMethod.objectToDouble(recMap
						.get("rgjzzfy")));
				byqzxLocal.setYf(CommonMethod.objectToDouble(recMap.get("yf")));
				byqzxLocal.setQybh(2);
				byqzxLocalDao.merge(byqzxLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferBYQZX:" + result);
		}
		return result;
	}

	public BYQZXLocalDao getByqzxLocalDao() {
		return byqzxLocalDao;
	}

	public void setByqzxLocalDao(BYQZXLocalDao byqzxLocalDao) {
		this.byqzxLocalDao = byqzxLocalDao;
	}

	public BYQZXDao getByqzxSBDao() {
		return byqzxSBDao;
	}

	public void setByqzxSBDao(BYQZXDao byqzxSBDao) {
		this.byqzxSBDao = byqzxSBDao;
	}

	public BYQZXDao getByqzxXBDao() {
		return byqzxXBDao;
	}

	public void setByqzxXBDao(BYQZXDao byqzxXBDao) {
		this.byqzxXBDao = byqzxXBDao;
	}

}
