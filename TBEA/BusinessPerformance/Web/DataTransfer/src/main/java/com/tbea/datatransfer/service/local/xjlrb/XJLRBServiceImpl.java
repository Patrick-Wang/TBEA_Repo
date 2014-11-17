package com.tbea.datatransfer.service.local.xjlrb;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.xjlrb.XJLRBDao;
import com.tbea.datatransfer.model.dao.yszk15.bl.BL15Dao;
import com.tbea.datatransfer.model.entity.local.XJLRB;

@Transactional("transactionManager")
public class XJLRBServiceImpl implements XJLRBService {

	private XJLRBDao xjlrbDao;

	private BL15Dao bl15Dao;

	@Override
	public void importXJLRB() {
		Calendar cur = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		cur.set(2013, 1 - 1, 1);
		end.setTimeInMillis(System.currentTimeMillis());
		List<Object[]> xjlrb15List = null;
		while (cur.compareTo(end) <= 0) {
			xjlrb15List = bl15Dao.getXJLRBByDate(cur);
			XJLRB xjlrb = null;
			for (Object[] xjlrb15Array : xjlrb15List) {
				xjlrb = new XJLRB();
				xjlrb.setRq(new Date(cur.getTimeInMillis()));
				xjlrb.setSscy(null == xjlrb15Array[0] ? 0 : Integer
						.valueOf(xjlrb15Array[0].toString()));
				xjlrb.setPx(null == xjlrb15Array[1] ? 0 : Integer
						.valueOf(xjlrb15Array[1].toString()));
				xjlrb.setJgmc((String) xjlrb15Array[2]);
				xjlrb.setDrlr(null == xjlrb15Array[3] ? 0 : Double
						.valueOf(xjlrb15Array[3].toString()));
				xjlrb.setDrlc(null == xjlrb15Array[4] ? 0 : Double
						.valueOf(xjlrb15Array[4].toString()));
				xjlrb.setDrjll(null == xjlrb15Array[5] ? 0 : Double
						.valueOf(xjlrb15Array[5].toString()));
				xjlrb.setDylr(null == xjlrb15Array[6] ? 0 : Double
						.valueOf(xjlrb15Array[6].toString()));
				xjlrb.setDylc(null == xjlrb15Array[7] ? 0 : Double
						.valueOf(xjlrb15Array[7].toString()));
				xjlrb.setDyjll(null == xjlrb15Array[8] ? 0 : Double
						.valueOf(xjlrb15Array[8].toString()));
				xjlrb.setDnlr(null == xjlrb15Array[9] ? 0 : Double
						.valueOf(xjlrb15Array[9].toString()));
				xjlrb.setDnlc(null == xjlrb15Array[10] ? 0 : Double
						.valueOf(xjlrb15Array[10].toString()));
				xjlrb.setDnjll(null == xjlrb15Array[11] ? 0 : Double
						.valueOf(xjlrb15Array[11].toString()));
				xjlrb.setBytzs(null == xjlrb15Array[12] ? 0 : Double
						.valueOf(xjlrb15Array[12].toString()));
				xjlrbDao.create(xjlrb);
			}
			cur.add(cur.DATE, 1);
		}
	}

	public XJLRBDao getXjlrbDao() {
		return xjlrbDao;
	}

	public void setXjlrbDao(XJLRBDao xjlrbDao) {
		this.xjlrbDao = xjlrbDao;
	}

	public BL15Dao getBl15Dao() {
		return bl15Dao;
	}

	public void setBl15Dao(BL15Dao bl15Dao) {
		this.bl15Dao = bl15Dao;
	}

}
