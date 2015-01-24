package com.tbea.datatransfer.service.local.xlwg;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.xlwg.XLWGLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.xlwg.XLWGDLDao;
import com.tbea.datatransfer.model.entity.local.XLWGLocal;
import com.tbea.datatransfer.model.entity.zjxl.XLWGXL;

@Transactional("transactionManager")
public class XLWGTransferServiceImpl implements XLWGTransferService {

	private XLWGLocalDao xlwgLocalDao;

	private XLWGDLDao xlwgDLDao;

	@Override
	public boolean transferXLWG() {
		boolean result = false;
		try {
			// dl
			xlwgLocalDao.deleteXLWGLocalByQY(6);
			XLWGLocal xlwgLocal = null;
			List<XLWGXL> xlwgDLList = xlwgDLDao.getAllXLWG();
			for (XLWGXL xlwgDL : xlwgDLList) {
				xlwgLocal = new XLWGLocal();
				xlwgLocal.setGxrq(xlwgDL.getGxrq());
				xlwgLocal.setZxcpbh(xlwgDL.getZxcpbh());
//				xlwgLocal.setDwmc(xlwgDL.getDwmc());
				xlwgLocal.setWgsj(xlwgDL.getWgsj());
				xlwgLocal.setCz(xlwgDL.getCz());
				xlwgLocal.setDjtyl(xlwgDL.getDjtyl());
				xlwgLocal.setDjtdj(xlwgDL.getDjtdj());
				xlwgLocal.setTjgf(xlwgDL.getTjgf());
				xlwgLocal.setLyl(xlwgDL.getLyl());
				xlwgLocal.setSjlvdj(xlwgDL.getSjlvdj());
				xlwgLocal.setQtcbhj(xlwgDL.getQtcbhj());
				xlwgLocal.setYf(xlwgDL.getYf());
				xlwgLocal.setQybh(6);
				xlwgLocalDao.merge(xlwgLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public XLWGLocalDao getXlwgLocalDao() {
		return xlwgLocalDao;
	}

	public void setXlwgLocalDao(XLWGLocalDao xlwgLocalDao) {
		this.xlwgLocalDao = xlwgLocalDao;
	}

	public XLWGDLDao getXlwgDLDao() {
		return xlwgDLDao;
	}

	public void setXlwgDLDao(XLWGDLDao xlwgDLDao) {
		this.xlwgDLDao = xlwgDLDao;
	}

}
