package com.tbea.datatransfer.service.local.mrhkhz;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.local.mrhkhz.MRHKHZLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.mrhkhz.MRHKHZBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.mrhkhz.MRHKHZXLDao;
import com.tbea.datatransfer.model.entity.local.MRHKHZLocal;
import com.tbea.datatransfer.model.entity.zjbyq.MRHKHZBYQ;
import com.tbea.datatransfer.model.entity.zjxl.MRHKHZXL;
import com.tbea.datatransfer.service.webservice.WebServiceClient;

@Transactional("transactionManager")
public class MRHKHZTransferServiceImpl implements MRHKHZTransferService {

	private MRHKHZLocalDao mrhkhzLocalDao;

	private MRHKHZXLDao mrhkhzDLDao;

	private MRHKHZXLDao mrhkhzLLDao;

	private MRHKHZBYQDao mrhkhzTBDao;

	private MRHKHZBYQDao mrhkhzSBDao;

	private MRHKHZXLDao mrhkhzXLDao;

	private MRHKHZBYQDao mrhkhzXBDao;

	@Override
	public boolean transferMRHKHZ() {
		boolean result = false;
		try {
			// dl
			mrhkhzLocalDao.deleteMRHKHZLocalByQY(6);
			MRHKHZLocal mrhkhzLocal = null;
			List<MRHKHZXL> mrhkhzDLList = mrhkhzDLDao.getAllMRHKHZ();
			for (MRHKHZXL mrhkhzDL : mrhkhzDLList) {
				mrhkhzLocal = new MRHKHZLocal();
				mrhkhzLocal.setGxrq(mrhkhzDL.getGxrq());
				mrhkhzLocal.setHkrq(mrhkhzDL.getHkrq());
				mrhkhzLocal.setHkje(mrhkhzDL.getHkje());
				mrhkhzLocal.setQzqbbc(mrhkhzDL.getQzqbbc());
				mrhkhzLocal.setQzzqbc(mrhkhzDL.getQzzqbc());
				mrhkhzLocal.setYhkzkjysdhkje(mrhkhzDL.getYhkzkjysdhkje());
				mrhkhzLocal.setJzydyszkzmye(mrhkhzDL.getJzydyszkzmye());
				mrhkhzLocal.setJtxdydzjhlzb(mrhkhzDL.getJtxdydzjhlzb());
				mrhkhzLocal.setSfdrwc(mrhkhzDL.getSfdrwc());
				mrhkhzLocal.setQybh(6);
				mrhkhzLocalDao.merge(mrhkhzLocal);
			}
			// ll
			mrhkhzLocalDao.deleteMRHKHZLocalByQY(4);
			List<MRHKHZXL> mrhkhzLLList = mrhkhzLLDao.getAllMRHKHZ();
			for (MRHKHZXL mrhkhzLL : mrhkhzLLList) {
				mrhkhzLocal = new MRHKHZLocal();
				mrhkhzLocal.setGxrq(mrhkhzLL.getGxrq());
				mrhkhzLocal.setHkrq(mrhkhzLL.getHkrq());
				mrhkhzLocal.setHkje(mrhkhzLL.getHkje());
				mrhkhzLocal.setQzqbbc(mrhkhzLL.getQzqbbc());
				mrhkhzLocal.setQzzqbc(mrhkhzLL.getQzzqbc());
				mrhkhzLocal.setYhkzkjysdhkje(mrhkhzLL.getYhkzkjysdhkje());
				mrhkhzLocal.setJzydyszkzmye(mrhkhzLL.getJzydyszkzmye());
				mrhkhzLocal.setJtxdydzjhlzb(mrhkhzLL.getJtxdydzjhlzb());
				mrhkhzLocal.setSfdrwc(mrhkhzLL.getSfdrwc());
				mrhkhzLocal.setQybh(4);
				mrhkhzLocalDao.merge(mrhkhzLocal);
			}
			// xl
			mrhkhzLocalDao.deleteMRHKHZLocalByQY(5);
			List<MRHKHZXL> mrhkhzXLList = mrhkhzXLDao.getAllMRHKHZ();
			for (MRHKHZXL mrhkhzXL : mrhkhzXLList) {
				mrhkhzLocal = new MRHKHZLocal();
				mrhkhzLocal.setGxrq(mrhkhzXL.getGxrq());
				mrhkhzLocal.setHkrq(mrhkhzXL.getHkrq());
				mrhkhzLocal.setHkje(mrhkhzXL.getHkje());
				mrhkhzLocal.setQzqbbc(mrhkhzXL.getQzqbbc());
				mrhkhzLocal.setQzzqbc(mrhkhzXL.getQzzqbc());
				mrhkhzLocal.setYhkzkjysdhkje(mrhkhzXL.getYhkzkjysdhkje());
				mrhkhzLocal.setJzydyszkzmye(mrhkhzXL.getJzydyszkzmye());
				mrhkhzLocal.setJtxdydzjhlzb(mrhkhzXL.getJtxdydzjhlzb());
				mrhkhzLocal.setSfdrwc(mrhkhzXL.getSfdrwc());
				mrhkhzLocal.setQybh(5);
				mrhkhzLocalDao.merge(mrhkhzLocal);
			}
			// tb
			mrhkhzLocalDao.deleteMRHKHZLocalByQY(301);
			List<MRHKHZBYQ> mrhkhzTBList = mrhkhzTBDao.getAllMRHKHZ();
			for (MRHKHZBYQ mrhkhzTB : mrhkhzTBList) {
				mrhkhzLocal = new MRHKHZLocal();
				mrhkhzLocal.setGxrq(mrhkhzTB.getGxrq());
				mrhkhzLocal.setHkrq(mrhkhzTB.getHkrq());
				mrhkhzLocal.setHkje(mrhkhzTB.getHkje());
				mrhkhzLocal.setQzqbbc(mrhkhzTB.getQzqbbc());
				mrhkhzLocal.setQzzqbc(mrhkhzTB.getQzzqbc());
				mrhkhzLocal.setYhkzkjysdhkje(mrhkhzTB.getYhkzkjysdhkje());
				mrhkhzLocal.setJzydyszkzmye(mrhkhzTB.getJzydyszkzmye());
				mrhkhzLocal.setJtxdydzjhlzb(mrhkhzTB.getJtxdydzjhlzb());
				mrhkhzLocal.setSfdrwc(mrhkhzTB.getSfdrwc());
				mrhkhzLocal.setQybh(301);
				mrhkhzLocalDao.merge(mrhkhzLocal);
			}

			// sb
			mrhkhzLocalDao.deleteMRHKHZLocalByQY(1);
			List<MRHKHZBYQ> mrhkhzSBList = mrhkhzSBDao.getAllMRHKHZ();
			for (MRHKHZBYQ mrhkhzSB : mrhkhzSBList) {
				mrhkhzLocal = new MRHKHZLocal();
				mrhkhzLocal.setGxrq(mrhkhzSB.getGxrq());
				mrhkhzLocal.setHkrq(mrhkhzSB.getHkrq());
				mrhkhzLocal.setHkje(mrhkhzSB.getHkje());
				mrhkhzLocal.setQzqbbc(mrhkhzSB.getQzqbbc());
				mrhkhzLocal.setQzzqbc(mrhkhzSB.getQzzqbc());
				mrhkhzLocal.setYhkzkjysdhkje(mrhkhzSB.getYhkzkjysdhkje());
				mrhkhzLocal.setJzydyszkzmye(mrhkhzSB.getJzydyszkzmye());
				mrhkhzLocal.setJtxdydzjhlzb(mrhkhzSB.getJtxdydzjhlzb());
				mrhkhzLocal.setSfdrwc(mrhkhzSB.getSfdrwc());
				mrhkhzLocal.setQybh(1);
				mrhkhzLocalDao.merge(mrhkhzLocal);
			}
			// xb
			mrhkhzLocalDao.deleteMRHKHZLocalByQY(3);
			List<MRHKHZBYQ> mrhkhzXBList = mrhkhzXBDao.getAllMRHKHZ();
			for (MRHKHZBYQ mrhkhzXB : mrhkhzXBList) {
				mrhkhzLocal = new MRHKHZLocal();
				mrhkhzLocal.setGxrq(mrhkhzXB.getGxrq());
				mrhkhzLocal.setHkrq(mrhkhzXB.getHkrq());
				mrhkhzLocal.setHkje(mrhkhzXB.getHkje());
				mrhkhzLocal.setQzqbbc(mrhkhzXB.getQzqbbc());
				mrhkhzLocal.setQzzqbc(mrhkhzXB.getQzzqbc());
				mrhkhzLocal.setYhkzkjysdhkje(mrhkhzXB.getYhkzkjysdhkje());
				mrhkhzLocal.setJzydyszkzmye(mrhkhzXB.getJzydyszkzmye());
				mrhkhzLocal.setJtxdydzjhlzb(mrhkhzXB.getJtxdydzjhlzb());
				mrhkhzLocal.setSfdrwc(mrhkhzXB.getSfdrwc());
				mrhkhzLocal.setQybh(3);
				mrhkhzLocalDao.merge(mrhkhzLocal);
			}

			// hb
			SimpleDateFormat timeFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			mrhkhzLocalDao.deleteMRHKHZLocalByQY(2);
			WebServiceClient webServiceClient = new WebServiceClient();
			List<Map<String, Object>> recList = webServiceClient.getRec(
					"web_test", "123456", "yszk_ws_mrhkhz");
			for (Map<String, Object> recMap : recList) {
				mrhkhzLocal = new MRHKHZLocal();
				mrhkhzLocal.setGxrq(CommonMethod.objectToDate(timeFormat,
						recMap.get("gxrq")));
				mrhkhzLocal.setHkrq(CommonMethod.objectToDate(timeFormat,
						recMap.get("hkrq")));
				mrhkhzLocal.setHkje(CommonMethod.objectToDouble(recMap
						.get("hkje")));
				mrhkhzLocal.setQzqbbc(CommonMethod.objectToDouble(recMap
						.get("qzqbbc")));
				mrhkhzLocal.setQzzqbc(CommonMethod.objectToDouble(recMap
						.get("qzzqbc")));
				mrhkhzLocal.setYhkzkjysdhkje(CommonMethod.objectToDouble(recMap
						.get("yhkzkjysdhkje")));
				mrhkhzLocal.setJzydyszkzmye(CommonMethod.objectToDouble(recMap
						.get("jzydyszkzmye")));
				mrhkhzLocal.setJtxdydzjhlzb(CommonMethod.objectToDouble(recMap
						.get("jtxdydzjhlzb")));
				mrhkhzLocal.setSfdrwc(String.valueOf(recMap.get("sfdrwc")));
				mrhkhzLocal.setQybh(2);
				mrhkhzLocalDao.merge(mrhkhzLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println("transferMRHKHZ:" + result);
		}
		return result;
	}

	public MRHKHZLocalDao getMrhkhzLocalDao() {
		return mrhkhzLocalDao;
	}

	public void setMrhkhzLocalDao(MRHKHZLocalDao mrhkhzLocalDao) {
		this.mrhkhzLocalDao = mrhkhzLocalDao;
	}

	public MRHKHZXLDao getMrhkhzDLDao() {
		return mrhkhzDLDao;
	}

	public void setMrhkhzDLDao(MRHKHZXLDao mrhkhzDLDao) {
		this.mrhkhzDLDao = mrhkhzDLDao;
	}

	public MRHKHZBYQDao getMrhkhzTBDao() {
		return mrhkhzTBDao;
	}

	public void setMrhkhzTBDao(MRHKHZBYQDao mrhkhzTBDao) {
		this.mrhkhzTBDao = mrhkhzTBDao;
	}

	public MRHKHZBYQDao getMrhkhzSBDao() {
		return mrhkhzSBDao;
	}

	public void setMrhkhzSBDao(MRHKHZBYQDao mrhkhzSBDao) {
		this.mrhkhzSBDao = mrhkhzSBDao;
	}

	public MRHKHZXLDao getMrhkhzLLDao() {
		return mrhkhzLLDao;
	}

	public void setMrhkhzLLDao(MRHKHZXLDao mrhkhzLLDao) {
		this.mrhkhzLLDao = mrhkhzLLDao;
	}

	public MRHKHZXLDao getMrhkhzXLDao() {
		return mrhkhzXLDao;
	}

	public void setMrhkhzXLDao(MRHKHZXLDao mrhkhzXLDao) {
		this.mrhkhzXLDao = mrhkhzXLDao;
	}

	public MRHKHZBYQDao getMrhkhzXBDao() {
		return mrhkhzXBDao;
	}

	public void setMrhkhzXBDao(MRHKHZBYQDao mrhkhzXBDao) {
		this.mrhkhzXBDao = mrhkhzXBDao;
	}

}
