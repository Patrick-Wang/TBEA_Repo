package com.tbea.datatransfer.service.local.bl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.bl.BLLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.bl.BLBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.bl.BLXLDao;
import com.tbea.datatransfer.model.entity.local.BLLocal;
import com.tbea.datatransfer.model.entity.zjbyq.BLBYQ;
import com.tbea.datatransfer.model.entity.zjxl.BLXL;
import com.tbea.datatransfer.service.webservice.WebServiceClient;

@Transactional("transactionManager")
public class BLTransferServiceImpl implements BLTransferService {

	private BLLocalDao blLocalDao;

	private BLXLDao blDLDao;

	private BLXLDao blLLDao;

	private BLBYQDao blTBDao;

	private BLBYQDao blSBDao;

	private BLXLDao blXLDao;

	private BLBYQDao blXBDao;

	@Override
	public boolean transferBL() {
		boolean result = false;
		try {
			// dl
			blLocalDao.deleteBLLocalByQY(6);
			BLLocal blLocal = null;
			List<BLXL> blDLList = blDLDao.getAllBL();
			for (BLXL blDL : blDLList) {
				blLocal = new BLLocal();
				blLocal.setGxrq(blDL.getGxrq());
				blLocal.setBlbh(blDL.getBlbh());
				blLocal.setHtbh(blDL.getHtbh());
				blLocal.setBlrq(blDL.getBlrq());
				blLocal.setKxxz(blDL.getKxxz());
				blLocal.setBlje(blDL.getBlje());
				blLocal.setBldqr(blDL.getBldqr());
				blLocal.setBlhkje(blDL.getBlhkje());
				blLocal.setBlye(blDL.getBlye());
				blLocal.setSfdrwc(blDL.getSfdrwc());
				blLocal.setQybh(6);
				blLocalDao.merge(blLocal);
			}
			// ll
			blLocalDao.deleteBLLocalByQY(4);
			List<BLXL> blLLList = blLLDao.getAllBL();
			for (BLXL blLL : blLLList) {
				blLocal = new BLLocal();
				blLocal.setGxrq(blLL.getGxrq());
				blLocal.setBlbh(blLL.getBlbh());
				blLocal.setHtbh(blLL.getHtbh());
				blLocal.setBlrq(blLL.getBlrq());
				blLocal.setKxxz(blLL.getKxxz());
				blLocal.setBlje(blLL.getBlje());
				blLocal.setBldqr(blLL.getBldqr());
				blLocal.setBlhkje(blLL.getBlhkje());
				blLocal.setBlye(blLL.getBlye());
				blLocal.setSfdrwc(blLL.getSfdrwc());
				blLocal.setQybh(4);
				blLocalDao.merge(blLocal);
			}
			// xl
			blLocalDao.deleteBLLocalByQY(5);
			List<BLXL> blXLList = blXLDao.getAllBL();
			for (BLXL blXL : blXLList) {
				blLocal = new BLLocal();
				blLocal.setGxrq(blXL.getGxrq());
				blLocal.setBlbh(blXL.getBlbh());
				blLocal.setHtbh(blXL.getHtbh());
				blLocal.setBlrq(blXL.getBlrq());
				blLocal.setKxxz(blXL.getKxxz());
				blLocal.setBlje(blXL.getBlje());
				blLocal.setBldqr(blXL.getBldqr());
				blLocal.setBlhkje(blXL.getBlhkje());
				blLocal.setBlye(blXL.getBlye());
				blLocal.setSfdrwc(blXL.getSfdrwc());
				blLocal.setQybh(5);
				blLocalDao.merge(blLocal);
			}
			// tb
			blLocalDao.deleteBLLocalByQY(301);
			List<BLBYQ> blTBList = blTBDao.getAllBL();
			for (BLBYQ blTB : blTBList) {
				blLocal = new BLLocal();
				blLocal.setGxrq(blTB.getGxrq());
				blLocal.setBlbh(blTB.getBlbh());
				blLocal.setHtbh(blTB.getHtbh());
				blLocal.setBlrq(blTB.getBlrq());
				blLocal.setKxxz(blTB.getKxxz());
				blLocal.setBlje(blTB.getBlje());
				blLocal.setBldqr(blTB.getBldqr());
				blLocal.setBlhkje(blTB.getBlhkje());
				blLocal.setBlye(blTB.getBlye());
				blLocal.setSfdrwc(blTB.getSfdrwc());
				blLocal.setQybh(301);
				blLocalDao.merge(blLocal);
			}

			// sb
			blLocalDao.deleteBLLocalByQY(1);
			List<BLBYQ> blSBList = blSBDao.getAllBL();
			for (BLBYQ blSB : blSBList) {
				blLocal = new BLLocal();
				blLocal.setGxrq(blSB.getGxrq());
				blLocal.setBlbh(blSB.getBlbh());
				blLocal.setHtbh(blSB.getHtbh());
				blLocal.setBlrq(blSB.getBlrq());
				blLocal.setKxxz(blSB.getKxxz());
				blLocal.setBlje(blSB.getBlje());
				blLocal.setBldqr(blSB.getBldqr());
				blLocal.setBlhkje(blSB.getBlhkje());
				blLocal.setBlye(blSB.getBlye());
				blLocal.setSfdrwc(blSB.getSfdrwc());
				blLocal.setQybh(1);
				blLocalDao.merge(blLocal);
			}

			// xb
			blLocalDao.deleteBLLocalByQY(3);
			List<BLBYQ> blXBList = blXBDao.getAllBL();
			for (BLBYQ blXB : blXBList) {
				blLocal = new BLLocal();
				blLocal.setGxrq(blXB.getGxrq());
				blLocal.setBlbh(blXB.getBlbh());
				blLocal.setHtbh(blXB.getHtbh());
				blLocal.setBlrq(blXB.getBlrq());
				blLocal.setKxxz(blXB.getKxxz());
				blLocal.setBlje(blXB.getBlje());
				blLocal.setBldqr(blXB.getBldqr());
				blLocal.setBlhkje(blXB.getBlhkje());
				blLocal.setBlye(blXB.getBlye());
				blLocal.setSfdrwc(blXB.getSfdrwc());
				blLocal.setQybh(3);
				blLocalDao.merge(blLocal);
			}

			// hb
			SimpleDateFormat timeFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			blLocalDao.deleteBLLocalByQY(2);
			WebServiceClient webServiceClient = new WebServiceClient();
			List<Map<String, Object>> recList = webServiceClient.getRec(
					"web_test", "123456", "yszk_ws_bl");
			for (Map<String, Object> recMap : recList) {
				blLocal = new BLLocal();
				blLocal.setGxrq(timeFormat.parse(String.valueOf(recMap
						.get("gxrq"))));
				blLocal.setBlbh(String.valueOf(recMap.get("blbh")));
				blLocal.setHtbh(String.valueOf(recMap.get("htbh")));
				blLocal.setBlrq(timeFormat.parse(String.valueOf(recMap
						.get("blrq"))));
				blLocal.setKxxz(Integer.valueOf(String.valueOf(recMap
						.get("kxxz"))));
				blLocal.setBlje(Double.valueOf(String.valueOf(recMap
						.get("blje"))));
				blLocal.setBldqr(timeFormat.parse(String.valueOf(recMap
						.get("bldqr"))));
				blLocal.setBlhkje(Double.valueOf(String.valueOf(recMap
						.get("blhkje"))));
				blLocal.setBlye(Double.valueOf(String.valueOf(recMap
						.get("blye"))));
				blLocal.setSfdrwc(String.valueOf(recMap.get("sfdrwc")));
				blLocal.setQybh(2);
				blLocalDao.merge(blLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public BLLocalDao getBlLocalDao() {
		return blLocalDao;
	}

	public void setBlLocalDao(BLLocalDao blLocalDao) {
		this.blLocalDao = blLocalDao;
	}

	public BLXLDao getBlDLDao() {
		return blDLDao;
	}

	public void setBlDLDao(BLXLDao blDLDao) {
		this.blDLDao = blDLDao;
	}

	public BLXLDao getBlLLDao() {
		return blLLDao;
	}

	public void setBlLLDao(BLXLDao blLLDao) {
		this.blLLDao = blLLDao;
	}

	public BLBYQDao getBlTBDao() {
		return blTBDao;
	}

	public void setBlTBDao(BLBYQDao blTBDao) {
		this.blTBDao = blTBDao;
	}

	public BLBYQDao getBlSBDao() {
		return blSBDao;
	}

	public void setBlSBDao(BLBYQDao blSBDao) {
		this.blSBDao = blSBDao;
	}

	public BLXLDao getBlXLDao() {
		return blXLDao;
	}

	public void setBlXLDao(BLXLDao blXLDao) {
		this.blXLDao = blXLDao;
	}

	public BLBYQDao getBlXBDao() {
		return blXBDao;
	}

	public void setBlXBDao(BLBYQDao blXBDao) {
		this.blXBDao = blXBDao;
	}

}
