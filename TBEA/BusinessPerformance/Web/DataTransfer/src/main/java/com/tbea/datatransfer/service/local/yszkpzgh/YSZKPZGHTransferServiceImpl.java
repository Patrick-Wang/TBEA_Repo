package com.tbea.datatransfer.service.local.yszkpzgh;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.yszkpzgh.YSZKPZGHLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.yszkpzgh.YSZKPZGHBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.yszkpzgh.YSZKPZGHXLDao;
import com.tbea.datatransfer.model.entity.local.YSZKPZGHLocal;
import com.tbea.datatransfer.model.entity.zjbyq.YSZKPZGHBYQ;
import com.tbea.datatransfer.model.entity.zjxl.YSZKPZGHXL;

@Transactional("transactionManager")
public class YSZKPZGHTransferServiceImpl implements YSZKPZGHTransferService {

	private YSZKPZGHLocalDao yszkpzghLocalDao;

	private YSZKPZGHXLDao yszkpzghDLDao;

	private YSZKPZGHXLDao yszkpzghLLDao;

	private YSZKPZGHBYQDao yszkpzghTBDao;

	private YSZKPZGHBYQDao yszkpzghSBDao;

	@Override
	public boolean transferYSZKPZGH() {
		boolean result = false;
		try {
			// dl
			yszkpzghLocalDao.deleteYSZKPZGHLocalByQY(6);
			YSZKPZGHLocal yszkpzghLocal = null;
			List<YSZKPZGHXL> yszkpzghDLList = yszkpzghDLDao.getAllYSZKPZGH();
			for (YSZKPZGHXL yszkpzghDL : yszkpzghDLList) {
				yszkpzghLocal = new YSZKPZGHLocal();
				yszkpzghLocal.setGxrq(yszkpzghDL.getGxrq());
				yszkpzghLocal.setYf(yszkpzghDL.getYf());
				yszkpzghLocal.setGsbm(yszkpzghDL.getGsbm());
				yszkpzghLocal.setSymljxssr(yszkpzghDL.getSymljxssr());
				yszkpzghLocal.setByjhxssr(yszkpzghDL.getByjhxssr());
				yszkpzghLocal.setByysnkzb(yszkpzghDL.getByysnkzb());
				yszkpzghLocal.setSymzmysye(yszkpzghDL.getSymzmysye());
				yszkpzghLocal.setByxssrxzysje(yszkpzghDL.getByxssrxzysje());
				yszkpzghLocal.setBykjyszjhlje(yszkpzghDL.getBykjyszjhlje());
				yszkpzghLocal.setByghblzjysje(yszkpzghDL.getByghblzjysje());
				yszkpzghLocal.setByxzblhkcjysje(yszkpzghDL.getByxzblhkcjysje());
				yszkpzghLocal.setSymykpwfhcsysje(yszkpzghDL
						.getSymykpwfhcsysje());
				yszkpzghLocal.setSymyfhwkpzjsjysje(yszkpzghDL
						.getSymyfhwkpzjsjysje());
				yszkpzghLocal.setSymblhkcjysje(yszkpzghDL.getSymblhkcjysje());
				yszkpzghLocal.setSymyscjysje(yszkpzghDL.getSymyscjysje());
				yszkpzghLocal.setQtcjys(yszkpzghDL.getQtcjys());
				yszkpzghLocal.setByfhcpxzysje(yszkpzghDL.getByfhcpxzysje());
				yszkpzghLocal.setByhkjdysje(yszkpzghDL.getByhkjdysje());
				yszkpzghLocal.setSfdrwc(yszkpzghDL.getSfdrwc());
				yszkpzghLocal.setQybh(6);
				yszkpzghLocalDao.merge(yszkpzghLocal);
			}
			// ll
			yszkpzghLocalDao.deleteYSZKPZGHLocalByQY(4);
			List<YSZKPZGHXL> yszkpzghLLList = yszkpzghLLDao.getAllYSZKPZGH();
			for (YSZKPZGHXL yszkpzghLL : yszkpzghLLList) {
				yszkpzghLocal = new YSZKPZGHLocal();
				yszkpzghLocal.setGxrq(yszkpzghLL.getGxrq());
				yszkpzghLocal.setYf(yszkpzghLL.getYf());
				yszkpzghLocal.setGsbm(yszkpzghLL.getGsbm());
				yszkpzghLocal.setSymljxssr(yszkpzghLL.getSymljxssr());
				yszkpzghLocal.setByjhxssr(yszkpzghLL.getByjhxssr());
				yszkpzghLocal.setByysnkzb(yszkpzghLL.getByysnkzb());
				yszkpzghLocal.setSymzmysye(yszkpzghLL.getSymzmysye());
				yszkpzghLocal.setByxssrxzysje(yszkpzghLL.getByxssrxzysje());
				yszkpzghLocal.setBykjyszjhlje(yszkpzghLL.getBykjyszjhlje());
				yszkpzghLocal.setByghblzjysje(yszkpzghLL.getByghblzjysje());
				yszkpzghLocal.setByxzblhkcjysje(yszkpzghLL.getByxzblhkcjysje());
				yszkpzghLocal.setSymykpwfhcsysje(yszkpzghLL
						.getSymykpwfhcsysje());
				yszkpzghLocal.setSymyfhwkpzjsjysje(yszkpzghLL
						.getSymyfhwkpzjsjysje());
				yszkpzghLocal.setSymblhkcjysje(yszkpzghLL.getSymblhkcjysje());
				yszkpzghLocal.setSymyscjysje(yszkpzghLL.getSymyscjysje());
				yszkpzghLocal.setQtcjys(yszkpzghLL.getQtcjys());
				yszkpzghLocal.setByfhcpxzysje(yszkpzghLL.getByfhcpxzysje());
				yszkpzghLocal.setByhkjdysje(yszkpzghLL.getByhkjdysje());
				yszkpzghLocal.setSfdrwc(yszkpzghLL.getSfdrwc());
				yszkpzghLocal.setQybh(4);
				yszkpzghLocalDao.merge(yszkpzghLocal);
			}
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

	public YSZKPZGHXLDao getYszkpzghDLDao() {
		return yszkpzghDLDao;
	}

	public void setYszkpzghDLDao(YSZKPZGHXLDao yszkpzghDLDao) {
		this.yszkpzghDLDao = yszkpzghDLDao;
	}

	public YSZKPZGHXLDao getYszkpzghLLDao() {
		return yszkpzghLLDao;
	}

	public void setYszkpzghLLDao(YSZKPZGHXLDao yszkpzghLLDao) {
		this.yszkpzghLLDao = yszkpzghLLDao;
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

}
