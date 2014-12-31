package com.tbea.datatransfer.service.local.xltb;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.xltb.XLTBLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.xltb.XLTBDLDao;
import com.tbea.datatransfer.model.entity.local.XLTBLocal;
import com.tbea.datatransfer.model.entity.zjdl.XLTBDL;

@Transactional("transactionManager")
public class XLTBTransferServiceImpl implements XLTBTransferService {

	private XLTBLocalDao xltbLocalDao;

	private XLTBDLDao xltbDLDao;

	@Override
	public boolean transferXLTB() {
		boolean result = false;
		try {
			// dl
			xltbLocalDao.deleteXLTBLocalByQY(6);
			XLTBLocal xltbLocal = null;
			List<XLTBDL> xltbDLList = xltbDLDao.getAllXLTBDL();
			for (XLTBDL xltbDL : xltbDLList) {
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
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public XLTBLocalDao getXltbLocalDao() {
		return xltbLocalDao;
	}

	public void setXltbLocalDao(XLTBLocalDao xltbLocalDao) {
		this.xltbLocalDao = xltbLocalDao;
	}

	public XLTBDLDao getXltbDLDao() {
		return xltbDLDao;
	}

	public void setXltbDLDao(XLTBDLDao xltbDLDao) {
		this.xltbDLDao = xltbDLDao;
	}

}
