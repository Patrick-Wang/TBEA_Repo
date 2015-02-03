package com.tbea.datatransfer.service.local.xltb;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.xltb.XLTBLocalDao;
import com.tbea.datatransfer.model.dao.zjxl.xltb.XLTBXLDao;
import com.tbea.datatransfer.model.entity.local.XLTBLocal;
import com.tbea.datatransfer.model.entity.zjxl.XLTBXL;

@Transactional("transactionManager")
public class XLTBTransferServiceImpl implements XLTBTransferService {

	private XLTBLocalDao xltbLocalDao;

	private XLTBXLDao xltbDLDao;

	private XLTBXLDao xltbLLDao;
	
	private XLTBXLDao xltbXLDao;

	@Override
	public boolean transferXLTB() {
		boolean result = false;
		try {
			// dl
			xltbLocalDao.deleteXLTBLocalByQY(6);
			XLTBLocal xltbLocal = null;
			List<XLTBXL> xltbDLList = xltbDLDao.getAllXLTB();
			for (XLTBXL xltbDL : xltbDLList) {
				xltbLocal = new XLTBLocal();
				xltbLocal.setGxrq(xltbDL.getGxrq());
				xltbLocal.setXmbh(xltbDL.getXmbh());
				xltbLocal.setTbbjsj(xltbDL.getTbbjsj());
				xltbLocal.setCpdl(xltbDL.getCpdl());
				xltbLocal.setXlsl(xltbDL.getXlsl());
				xltbLocal.setCz(xltbDL.getCz());
				xltbLocal.setYjkbsj(xltbDL.getYjkbsj());
				xltbLocal.setYczbgl(xltbDL.getYczbgl());
				xltbLocal.setDjtyl(xltbDL.getDjtyl());
				xltbLocal.setDjtdj(xltbDL.getDjtdj());
				xltbLocal.setLyl(xltbDL.getLyl());
				xltbLocal.setTblvdj(xltbDL.getTblvdj());
				xltbLocal.setQtcbhj(xltbDL.getQtcbhj());
				xltbLocal.setYf(xltbDL.getYf());
				xltbLocal.setQybh(6);
				xltbLocalDao.merge(xltbLocal);
			}
			// ll
			xltbLocalDao.deleteXLTBLocalByQY(4);
			List<XLTBXL> xltbLLList = xltbLLDao.getAllXLTB();
			for (XLTBXL xltbLL : xltbLLList) {
				xltbLocal = new XLTBLocal();
				xltbLocal.setGxrq(xltbLL.getGxrq());
				xltbLocal.setXmbh(xltbLL.getXmbh());
				xltbLocal.setTbbjsj(xltbLL.getTbbjsj());
				xltbLocal.setCpdl(xltbLL.getCpdl());
				xltbLocal.setXlsl(xltbLL.getXlsl());
				xltbLocal.setCz(xltbLL.getCz());
				xltbLocal.setYjkbsj(xltbLL.getYjkbsj());
				xltbLocal.setYczbgl(xltbLL.getYczbgl());
				xltbLocal.setDjtyl(xltbLL.getDjtyl());
				xltbLocal.setDjtdj(xltbLL.getDjtdj());
				xltbLocal.setLyl(xltbLL.getLyl());
				xltbLocal.setTblvdj(xltbLL.getTblvdj());
				xltbLocal.setQtcbhj(xltbLL.getQtcbhj());
				xltbLocal.setYf(xltbLL.getYf());
				xltbLocal.setQybh(4);
				xltbLocalDao.merge(xltbLocal);
			}
			// xl
			xltbLocalDao.deleteXLTBLocalByQY(5);
			List<XLTBXL> xltbXLList = xltbXLDao.getAllXLTB();
			for (XLTBXL xltbXL : xltbXLList) {
				xltbLocal = new XLTBLocal();
				xltbLocal.setGxrq(xltbXL.getGxrq());
				xltbLocal.setXmbh(xltbXL.getXmbh());
				xltbLocal.setTbbjsj(xltbXL.getTbbjsj());
				xltbLocal.setCpdl(xltbXL.getCpdl());
				xltbLocal.setXlsl(xltbXL.getXlsl());
				xltbLocal.setCz(xltbXL.getCz());
				xltbLocal.setYjkbsj(xltbXL.getYjkbsj());
				xltbLocal.setYczbgl(xltbXL.getYczbgl());
				xltbLocal.setDjtyl(xltbXL.getDjtyl());
				xltbLocal.setDjtdj(xltbXL.getDjtdj());
				xltbLocal.setLyl(xltbXL.getLyl());
				xltbLocal.setTblvdj(xltbXL.getTblvdj());
				xltbLocal.setQtcbhj(xltbXL.getQtcbhj());
				xltbLocal.setYf(xltbXL.getYf());
				xltbLocal.setQybh(5);
				xltbLocalDao.merge(xltbLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferXLTB:" + result);
		}
		return result;
	}

	public XLTBLocalDao getXltbLocalDao() {
		return xltbLocalDao;
	}

	public void setXltbLocalDao(XLTBLocalDao xltbLocalDao) {
		this.xltbLocalDao = xltbLocalDao;
	}

	public XLTBXLDao getXltbDLDao() {
		return xltbDLDao;
	}

	public void setXltbDLDao(XLTBXLDao xltbDLDao) {
		this.xltbDLDao = xltbDLDao;
	}

	public XLTBXLDao getXltbLLDao() {
		return xltbLLDao;
	}

	public void setXltbLLDao(XLTBXLDao xltbLLDao) {
		this.xltbLLDao = xltbLLDao;
	}

	public XLTBXLDao getXltbXLDao() {
		return xltbXLDao;
	}

	public void setXltbXLDao(XLTBXLDao xltbXLDao) {
		this.xltbXLDao = xltbXLDao;
	}

}
