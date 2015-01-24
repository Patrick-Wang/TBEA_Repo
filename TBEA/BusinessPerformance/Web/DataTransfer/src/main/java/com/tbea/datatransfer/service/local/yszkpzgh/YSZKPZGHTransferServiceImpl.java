package com.tbea.datatransfer.service.local.yszkpzgh;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.yszkpzgh.YSZKPZGHLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.yszkpzgh.YSZKPZGHBYQDao;
import com.tbea.datatransfer.model.entity.local.YSZKPZGHLocal;
import com.tbea.datatransfer.model.entity.zjbyq.YSZKPZGHBYQ;

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
