package com.tbea.datatransfer.service.local.yszkpzgh;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.local.yszkpzgh.YSZKPZGHLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.yszkpzgh.YSZKPZGHBYQDao;
import com.tbea.datatransfer.model.entity.local.YSZKPZGHLocal;
import com.tbea.datatransfer.model.entity.zjbyq.YSZKPZGHBYQ;
import com.tbea.datatransfer.service.webservice.WebServiceClient;

@Transactional("transactionManager")
public class YSZKPZGHTransferServiceImpl implements YSZKPZGHTransferService {

	private YSZKPZGHLocalDao yszkpzghLocalDao;

	private YSZKPZGHBYQDao yszkpzghTBDao;

	private YSZKPZGHBYQDao yszkpzghSBDao;

	private YSZKPZGHBYQDao yszkpzghXBDao;

	@Override
	public boolean transferYSZKPZGH() {
		boolean result = false;
		try {
			YSZKPZGHLocal yszkpzghLocal = null;
			// tb
			yszkpzghLocalDao.deleteYSZKPZGHLocalByQY(301);
			List<YSZKPZGHBYQ> yszkpzghTBList = yszkpzghTBDao.getAllYSZKPZGH();
			for (YSZKPZGHBYQ yszkpzghTB : yszkpzghTBList) {
				yszkpzghLocal = new YSZKPZGHLocal();
				yszkpzghLocal.setGxrq(yszkpzghTB.getGxrq());
				yszkpzghLocal.setYf(yszkpzghTB.getYf());
				yszkpzghLocal.setGsbm(yszkpzghTB.getGsbm());
				yszkpzghLocal.setSymljxssr(yszkpzghTB.getSymljxssr());
				yszkpzghLocal.setByjhxssr(yszkpzghTB.getByjhxssr());
				yszkpzghLocal.setByysnkzb(yszkpzghTB.getByysnkzb());
				yszkpzghLocal.setSymzmysye(yszkpzghTB.getSymzmysye());
				yszkpzghLocal.setByxssrxzysje(yszkpzghTB.getByxssrxzysje());
				yszkpzghLocal.setBykjyszjhlje(yszkpzghTB.getBykjyszjhlje());
				yszkpzghLocal.setByghblzjysje(yszkpzghTB.getByghblzjysje());
				yszkpzghLocal.setByxzblhkcjysje(yszkpzghTB.getByxzblhkcjysje());
				yszkpzghLocal.setSymykpwfhcsysje(yszkpzghTB
						.getSymykpwfhcsysje());
				yszkpzghLocal.setSymyfhwkpzjsjysje(yszkpzghTB
						.getSymyfhwkpzjsjysje());
				yszkpzghLocal.setSymblhkcjysje(yszkpzghTB.getSymblhkcjysje());
				yszkpzghLocal.setSymyscjysje(yszkpzghTB.getSymyscjysje());
				yszkpzghLocal.setQtcjys(yszkpzghTB.getQtcjys());
				yszkpzghLocal.setByfhcpxzysje(yszkpzghTB.getByfhcpxzysje());
				yszkpzghLocal.setByhkjdysje(yszkpzghTB.getByhkjdysje());
				yszkpzghLocal.setSfdrwc(yszkpzghTB.getSfdrwc());
				yszkpzghLocal.setQybh(301);
				yszkpzghLocalDao.merge(yszkpzghLocal);
			}

			// sb
			yszkpzghLocalDao.deleteYSZKPZGHLocalByQY(1);
			List<YSZKPZGHBYQ> yszkpzghSBList = yszkpzghSBDao.getAllYSZKPZGH();
			for (YSZKPZGHBYQ yszkpzghSB : yszkpzghSBList) {
				yszkpzghLocal = new YSZKPZGHLocal();
				yszkpzghLocal.setGxrq(yszkpzghSB.getGxrq());
				yszkpzghLocal.setYf(yszkpzghSB.getYf());
				yszkpzghLocal.setGsbm(yszkpzghSB.getGsbm());
				yszkpzghLocal.setSymljxssr(yszkpzghSB.getSymljxssr());
				yszkpzghLocal.setByjhxssr(yszkpzghSB.getByjhxssr());
				yszkpzghLocal.setByysnkzb(yszkpzghSB.getByysnkzb());
				yszkpzghLocal.setSymzmysye(yszkpzghSB.getSymzmysye());
				yszkpzghLocal.setByxssrxzysje(yszkpzghSB.getByxssrxzysje());
				yszkpzghLocal.setBykjyszjhlje(yszkpzghSB.getBykjyszjhlje());
				yszkpzghLocal.setByghblzjysje(yszkpzghSB.getByghblzjysje());
				yszkpzghLocal.setByxzblhkcjysje(yszkpzghSB.getByxzblhkcjysje());
				yszkpzghLocal.setSymykpwfhcsysje(yszkpzghSB
						.getSymykpwfhcsysje());
				yszkpzghLocal.setSymyfhwkpzjsjysje(yszkpzghSB
						.getSymyfhwkpzjsjysje());
				yszkpzghLocal.setSymblhkcjysje(yszkpzghSB.getSymblhkcjysje());
				yszkpzghLocal.setSymyscjysje(yszkpzghSB.getSymyscjysje());
				yszkpzghLocal.setQtcjys(yszkpzghSB.getQtcjys());
				yszkpzghLocal.setByfhcpxzysje(yszkpzghSB.getByfhcpxzysje());
				yszkpzghLocal.setByhkjdysje(yszkpzghSB.getByhkjdysje());
				yszkpzghLocal.setSfdrwc(yszkpzghSB.getSfdrwc());
				yszkpzghLocal.setQybh(1);
				yszkpzghLocalDao.merge(yszkpzghLocal);
			}

			// xb
			yszkpzghLocalDao.deleteYSZKPZGHLocalByQY(3);
			List<YSZKPZGHBYQ> yszkpzghXBList = yszkpzghXBDao.getAllYSZKPZGH();
			for (YSZKPZGHBYQ yszkpzghXB : yszkpzghXBList) {
				yszkpzghLocal = new YSZKPZGHLocal();
				yszkpzghLocal.setGxrq(yszkpzghXB.getGxrq());
				yszkpzghLocal.setYf(yszkpzghXB.getYf());
				yszkpzghLocal.setGsbm(yszkpzghXB.getGsbm());
				yszkpzghLocal.setSymljxssr(yszkpzghXB.getSymljxssr());
				yszkpzghLocal.setByjhxssr(yszkpzghXB.getByjhxssr());
				yszkpzghLocal.setByysnkzb(yszkpzghXB.getByysnkzb());
				yszkpzghLocal.setSymzmysye(yszkpzghXB.getSymzmysye());
				yszkpzghLocal.setByxssrxzysje(yszkpzghXB.getByxssrxzysje());
				yszkpzghLocal.setBykjyszjhlje(yszkpzghXB.getBykjyszjhlje());
				yszkpzghLocal.setByghblzjysje(yszkpzghXB.getByghblzjysje());
				yszkpzghLocal.setByxzblhkcjysje(yszkpzghXB.getByxzblhkcjysje());
				yszkpzghLocal.setSymykpwfhcsysje(yszkpzghXB
						.getSymykpwfhcsysje());
				yszkpzghLocal.setSymyfhwkpzjsjysje(yszkpzghXB
						.getSymyfhwkpzjsjysje());
				yszkpzghLocal.setSymblhkcjysje(yszkpzghXB.getSymblhkcjysje());
				yszkpzghLocal.setSymyscjysje(yszkpzghXB.getSymyscjysje());
				yszkpzghLocal.setQtcjys(yszkpzghXB.getQtcjys());
				yszkpzghLocal.setByfhcpxzysje(yszkpzghXB.getByfhcpxzysje());
				yszkpzghLocal.setByhkjdysje(yszkpzghXB.getByhkjdysje());
				yszkpzghLocal.setSfdrwc(yszkpzghXB.getSfdrwc());
				yszkpzghLocal.setQybh(3);
				yszkpzghLocalDao.merge(yszkpzghLocal);
			}

			// hb
			SimpleDateFormat timeFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			yszkpzghLocalDao.deleteYSZKPZGHLocalByQY(2);
			WebServiceClient webServiceClient = new WebServiceClient();
			List<Map<String, Object>> recList = webServiceClient.getRec(
					"web_test", "123456", "yszk_ws_yszkpzgh");
			for (Map<String, Object> recMap : recList) {
				yszkpzghLocal = new YSZKPZGHLocal();
				yszkpzghLocal.setGxrq(CommonMethod.objectToDate(timeFormat,
						recMap.get("gxrq")));
				yszkpzghLocal.setYf(String.valueOf(recMap.get("yf")));
				yszkpzghLocal.setGsbm(String.valueOf(recMap.get("gsbm")));
				yszkpzghLocal.setSymljxssr(CommonMethod.objectToDouble(recMap
						.get("symljxssr")));
				yszkpzghLocal.setByjhxssr(CommonMethod.objectToDouble(recMap
						.get("byjhxssr")));
				yszkpzghLocal.setByysnkzb(CommonMethod.objectToDouble(recMap
						.get("byysnkzb")));
				yszkpzghLocal.setSymzmysye(CommonMethod.objectToDouble(recMap
						.get("symzmysye")));
				yszkpzghLocal.setByxssrxzysje(CommonMethod
						.objectToDouble(recMap.get("byxssrxzysje")));
				yszkpzghLocal.setBykjyszjhlje(CommonMethod
						.objectToDouble(recMap.get("bykjyszjhlje")));
				yszkpzghLocal.setByghblzjysje(CommonMethod
						.objectToDouble(recMap.get("byghblzjysje")));
				yszkpzghLocal.setByxzblhkcjysje(CommonMethod
						.objectToDouble(recMap.get("byxzblhkcjysje")));
				yszkpzghLocal.setSymykpwfhcsysje(CommonMethod
						.objectToDouble(recMap.get("symykpwfhcsysje")));
				yszkpzghLocal.setSymyfhwkpzjsjysje(CommonMethod
						.objectToDouble(recMap.get("symyfhwkpzjsjysje")));
				yszkpzghLocal.setSymblhkcjysje(CommonMethod
						.objectToDouble(recMap.get("symblhkcjysje")));
				yszkpzghLocal.setSymyscjysje(CommonMethod.objectToDouble(recMap
						.get("symyscjysje")));
				yszkpzghLocal.setQtcjys(CommonMethod.objectToDouble(recMap
						.get("qtcjys")));
				yszkpzghLocal.setByfhcpxzysje(CommonMethod
						.objectToDouble(recMap.get("byfhcpxzysje")));
				yszkpzghLocal.setByhkjdysje(CommonMethod.objectToDouble(recMap
						.get("byhkjdysje")));
				yszkpzghLocal.setSfdrwc(String.valueOf(recMap.get("sfdrwc")));
				yszkpzghLocal.setQybh(2);
				yszkpzghLocalDao.merge(yszkpzghLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public YSZKPZGHLocalDao getYszkpzghLocalDao() {
		return yszkpzghLocalDao;
	}

	public void setYszkpzghLocalDao(YSZKPZGHLocalDao yszkpzghLocalDao) {
		this.yszkpzghLocalDao = yszkpzghLocalDao;
	}

	public YSZKPZGHBYQDao getYszkpzghTBDao() {
		return yszkpzghTBDao;
	}

	public void setYszkpzghTBDao(YSZKPZGHBYQDao yszkpzghTBDao) {
		this.yszkpzghTBDao = yszkpzghTBDao;
	}

	public YSZKPZGHBYQDao getYszkpzghSBDao() {
		return yszkpzghSBDao;
	}

	public void setYszkpzghSBDao(YSZKPZGHBYQDao yszkpzghSBDao) {
		this.yszkpzghSBDao = yszkpzghSBDao;
	}

	public YSZKPZGHBYQDao getYszkpzghXBDao() {
		return yszkpzghXBDao;
	}

	public void setYszkpzghXBDao(YSZKPZGHBYQDao yszkpzghXBDao) {
		this.yszkpzghXBDao = yszkpzghXBDao;
	}

}
