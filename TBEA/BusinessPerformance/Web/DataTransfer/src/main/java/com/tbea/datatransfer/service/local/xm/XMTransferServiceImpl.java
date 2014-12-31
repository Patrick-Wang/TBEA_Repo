package com.tbea.datatransfer.service.local.xm;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.xm.XMLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.xm.XMDLDao;
import com.tbea.datatransfer.model.entity.local.XMLocal;
import com.tbea.datatransfer.model.entity.zjdl.XMDL;

@Transactional("transactionManager")
public class XMTransferServiceImpl implements XMTransferService {

	private XMLocalDao xmLocalDao;

	private XMDLDao xmDLDao;

	@Override
	public boolean transferXM() {
		boolean result = false;
		try {
			// dl
			xmLocalDao.deleteXMLocalByQY(6);
			XMLocal xmLocal = null;
			List<XMDL> xmDLList = xmDLDao.getAllXMDL();
			for (XMDL xmDL : xmDLList) {
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

	public XMDLDao getXmDLDao() {
		return xmDLDao;
	}

	public void setXmDLDao(XMDLDao xmDLDao) {
		this.xmDLDao = xmDLDao;
	}

}
