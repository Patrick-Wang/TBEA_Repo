package com.tbea.datatransfer.service.local.xlwg;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.xlwg.XLWGLocalDao;
import com.tbea.datatransfer.model.dao.zjxl.xlwg.XLWGXLDao;
import com.tbea.datatransfer.model.entity.local.XLWGLocal;
import com.tbea.datatransfer.model.entity.zjxl.XLWGXL;

@Transactional("transactionManager")
public class XLWGTransferServiceImpl implements XLWGTransferService {

	private XLWGLocalDao xlwgLocalDao;

	private XLWGXLDao xlwgDLDao;

	private XLWGXLDao xlwgLLDao;
	
	private XLWGXLDao xlwgXLDao;

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
				// xlwgLocal.setDwmc(xlwgDL.getDwmc());
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
			// ll
			xlwgLocalDao.deleteXLWGLocalByQY(4);
			List<XLWGXL> xlwgLLList = xlwgLLDao.getAllXLWG();
			for (XLWGXL xlwgLL : xlwgLLList) {
				xlwgLocal = new XLWGLocal();
				xlwgLocal.setGxrq(xlwgLL.getGxrq());
				xlwgLocal.setZxcpbh(xlwgLL.getZxcpbh());
				// xlwgLocal.setDwmc(xlwgLL.getDwmc());
				xlwgLocal.setWgsj(xlwgLL.getWgsj());
				xlwgLocal.setCz(xlwgLL.getCz());
				xlwgLocal.setDjtyl(xlwgLL.getDjtyl());
				xlwgLocal.setDjtdj(xlwgLL.getDjtdj());
				xlwgLocal.setTjgf(xlwgLL.getTjgf());
				xlwgLocal.setLyl(xlwgLL.getLyl());
				xlwgLocal.setSjlvdj(xlwgLL.getSjlvdj());
				xlwgLocal.setQtcbhj(xlwgLL.getQtcbhj());
				xlwgLocal.setYf(xlwgLL.getYf());
				xlwgLocal.setQybh(4);
				xlwgLocalDao.merge(xlwgLocal);
			}
			// xl
			xlwgLocalDao.deleteXLWGLocalByQY(5);
			List<XLWGXL> xlwgXLList = xlwgXLDao.getAllXLWG();
			for (XLWGXL xlwgXL : xlwgXLList) {
				xlwgLocal = new XLWGLocal();
				xlwgLocal.setGxrq(xlwgXL.getGxrq());
				xlwgLocal.setZxcpbh(xlwgXL.getZxcpbh());
				// xlwgLocal.setDwmc(xlwgXL.getDwmc());
				xlwgLocal.setWgsj(xlwgXL.getWgsj());
				xlwgLocal.setCz(xlwgXL.getCz());
				xlwgLocal.setDjtyl(xlwgXL.getDjtyl());
				xlwgLocal.setDjtdj(xlwgXL.getDjtdj());
				xlwgLocal.setTjgf(xlwgXL.getTjgf());
				xlwgLocal.setLyl(xlwgXL.getLyl());
				xlwgLocal.setSjlvdj(xlwgXL.getSjlvdj());
				xlwgLocal.setQtcbhj(xlwgXL.getQtcbhj());
				xlwgLocal.setYf(xlwgXL.getYf());
				xlwgLocal.setQybh(5);
				xlwgLocalDao.merge(xlwgLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferXLWG:" + result);
		}
		return result;
	}

	public XLWGLocalDao getXlwgLocalDao() {
		return xlwgLocalDao;
	}

	public void setXlwgLocalDao(XLWGLocalDao xlwgLocalDao) {
		this.xlwgLocalDao = xlwgLocalDao;
	}

	public XLWGXLDao getXlwgDLDao() {
		return xlwgDLDao;
	}

	public void setXlwgDLDao(XLWGXLDao xlwgDLDao) {
		this.xlwgDLDao = xlwgDLDao;
	}

	public XLWGXLDao getXlwgLLDao() {
		return xlwgLLDao;
	}

	public void setXlwgLLDao(XLWGXLDao xlwgLLDao) {
		this.xlwgLLDao = xlwgLLDao;
	}

	public XLWGXLDao getXlwgXLDao() {
		return xlwgXLDao;
	}

	public void setXlwgXLDao(XLWGXLDao xlwgXLDao) {
		this.xlwgXLDao = xlwgXLDao;
	}

}
