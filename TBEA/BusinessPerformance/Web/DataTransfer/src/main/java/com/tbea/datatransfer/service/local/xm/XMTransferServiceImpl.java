package com.tbea.datatransfer.service.local.xm;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.xm.XMLocalDao;
import com.tbea.datatransfer.model.dao.zjxl.xm.XMXLDao;
import com.tbea.datatransfer.model.entity.local.XMLocal;
import com.tbea.datatransfer.model.entity.zjxl.XMXL;

@Transactional("transactionManager")
public class XMTransferServiceImpl implements XMTransferService {

	private XMLocalDao xmLocalDao;

	private XMXLDao xmDLDao;

	private XMXLDao xmLLDao;
	
	private XMXLDao xmXLDao;

	@Override
	public boolean transferXM() {
		boolean result = false;
		try {
			// dl
			xmLocalDao.deleteXMLocalByQY(6);
			XMLocal xmLocal = null;
			List<XMXL> xmDLList = xmDLDao.getAllXM();
			for (XMXL xmDL : xmDLList) {
				xmLocal = new XMLocal();
				xmLocal.setGxrq(xmDL.getGxrq());
				xmLocal.setXmbh(xmDL.getXmbh());
				xmLocal.setXmmc(xmDL.getXmmc());
				xmLocal.setDdszdw(xmDL.getDdszdw());
				xmLocal.setYhdwmc(xmDL.getYhdwmc());
				xmLocal.setKhhylx(xmDL.getKhhylx());
				xmLocal.setGb(xmDL.getGb());
				xmLocal.setQybh(6);
				xmLocalDao.merge(xmLocal);
			}
			// ll
			xmLocalDao.deleteXMLocalByQY(4);
			List<XMXL> xmLLList = xmLLDao.getAllXM();
			for (XMXL xmLL : xmLLList) {
				xmLocal = new XMLocal();
				xmLocal.setGxrq(xmLL.getGxrq());
				xmLocal.setXmbh(xmLL.getXmbh());
				xmLocal.setXmmc(xmLL.getXmmc());
				xmLocal.setDdszdw(xmLL.getDdszdw());
				xmLocal.setYhdwmc(xmLL.getYhdwmc());
				xmLocal.setKhhylx(xmLL.getKhhylx());
				xmLocal.setGb(xmLL.getGb());
				xmLocal.setQybh(4);
				xmLocalDao.merge(xmLocal);
			}
			// xl
			xmLocalDao.deleteXMLocalByQY(5);
			List<XMXL> xmXLList = xmXLDao.getAllXM();
			for (XMXL xmXL : xmXLList) {
				xmLocal = new XMLocal();
				xmLocal.setGxrq(xmXL.getGxrq());
				xmLocal.setXmbh(xmXL.getXmbh());
				xmLocal.setXmmc(xmXL.getXmmc());
				xmLocal.setDdszdw(xmXL.getDdszdw());
				xmLocal.setYhdwmc(xmXL.getYhdwmc());
				xmLocal.setKhhylx(xmXL.getKhhylx());
				xmLocal.setGb(xmXL.getGb());
				xmLocal.setQybh(5);
				xmLocalDao.merge(xmLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public XMLocalDao getXmLocalDao() {
		return xmLocalDao;
	}

	public void setXmLocalDao(XMLocalDao xmLocalDao) {
		this.xmLocalDao = xmLocalDao;
	}

	public XMXLDao getXmDLDao() {
		return xmDLDao;
	}

	public void setXmDLDao(XMXLDao xmDLDao) {
		this.xmDLDao = xmDLDao;
	}

	public XMXLDao getXmLLDao() {
		return xmLLDao;
	}

	public void setXmLLDao(XMXLDao xmLLDao) {
		this.xmLLDao = xmLLDao;
	}

	public XMXLDao getXmXLDao() {
		return xmXLDao;
	}

	public void setXmXLDao(XMXLDao xmXLDao) {
		this.xmXLDao = xmXLDao;
	}

}
