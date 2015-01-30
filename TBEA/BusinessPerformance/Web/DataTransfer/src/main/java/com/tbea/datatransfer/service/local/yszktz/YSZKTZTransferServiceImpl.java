package com.tbea.datatransfer.service.local.yszktz;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.local.yszktz.YSZKTZLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.yszktz.YSZKTZBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.yszktz.YSZKTZXLDao;
import com.tbea.datatransfer.model.entity.local.YSZKTZLocal;
import com.tbea.datatransfer.model.entity.zjbyq.YSZKTZBYQ;
import com.tbea.datatransfer.model.entity.zjxl.YSZKTZXL;
import com.tbea.datatransfer.service.webservice.WebServiceClient;

@Transactional("transactionManager")
public class YSZKTZTransferServiceImpl implements YSZKTZTransferService {

	private YSZKTZLocalDao yszktzLocalDao;

	private YSZKTZXLDao yszktzDLDao;

	private YSZKTZXLDao yszktzLLDao;

	private YSZKTZBYQDao yszktzTBDao;

	private YSZKTZBYQDao yszktzSBDao;

	private YSZKTZXLDao yszktzXLDao;

	private YSZKTZBYQDao yszktzXBDao;

	@Override
	@Transactional("transactionManager")
	public boolean transferYSZKTZ() {
		boolean result = false;
		try {
			// dl
			yszktzLocalDao.deleteYSZKTZLocalByQY(6);
			YSZKTZLocal yszktzLocal = null;
			List<YSZKTZXL> yszktzDLList = yszktzDLDao.getAllYSZKTZ();
			for (YSZKTZXL yszktzDL : yszktzDLList) {
				yszktzLocal = new YSZKTZLocal();
				yszktzLocal.setGxrq(yszktzDL.getGxrq());
				yszktzLocal.setHtbh(yszktzDL.getHtbh());
				yszktzLocal.setKhbh(yszktzDL.getKhbh());
				yszktzLocal.setKhmc(yszktzDL.getKhmc());
				yszktzLocal.setKhsshy(yszktzDL.getKhsshy());
				yszktzLocal.setKxlb(yszktzDL.getKxlb());
				yszktzLocal.setKxzt(yszktzDL.getKxzt());
				yszktzLocal.setYsje(yszktzDL.getYsje());
				yszktzLocal.setDqrq(yszktzDL.getDqrq());
				yszktzLocal.setYhxje(yszktzDL.getYhxje());
				yszktzLocal.setYfhje(yszktzDL.getYfhje());
				yszktzLocal.setFhrq(yszktzDL.getFhrq());
				yszktzLocal.setYkpje(yszktzDL.getYkpje());
				yszktzLocal.setKprq(yszktzDL.getKprq());
				yszktzLocal.setYqyyfl(yszktzDL.getYqyyfl());
				yszktzLocal.setSftgflsdqs(yszktzDL.getSftgflsdqs());
				yszktzLocal.setSfdrwc(yszktzDL.getSfdrwc());
				yszktzLocal.setQybh(6);
				yszktzLocalDao.merge(yszktzLocal);
			}
			// ll
			yszktzLocalDao.deleteYSZKTZLocalByQY(4);
			List<YSZKTZXL> yszktzLLList = yszktzLLDao.getAllYSZKTZ();
			for (YSZKTZXL yszktzLL : yszktzLLList) {
				yszktzLocal = new YSZKTZLocal();
				yszktzLocal.setGxrq(yszktzLL.getGxrq());
				yszktzLocal.setHtbh(yszktzLL.getHtbh());
				yszktzLocal.setKhbh(yszktzLL.getKhbh());
				yszktzLocal.setKhmc(yszktzLL.getKhmc());
				yszktzLocal.setKhsshy(yszktzLL.getKhsshy());
				yszktzLocal.setKxlb(yszktzLL.getKxlb());
				yszktzLocal.setKxzt(yszktzLL.getKxzt());
				yszktzLocal.setYsje(yszktzLL.getYsje());
				yszktzLocal.setDqrq(yszktzLL.getDqrq());
				yszktzLocal.setYhxje(yszktzLL.getYhxje());
				yszktzLocal.setYfhje(yszktzLL.getYfhje());
				yszktzLocal.setFhrq(yszktzLL.getFhrq());
				yszktzLocal.setYkpje(yszktzLL.getYkpje());
				yszktzLocal.setKprq(yszktzLL.getKprq());
				yszktzLocal.setYqyyfl(yszktzLL.getYqyyfl());
				yszktzLocal.setSftgflsdqs(yszktzLL.getSftgflsdqs());
				yszktzLocal.setSfdrwc(yszktzLL.getSfdrwc());
				yszktzLocal.setQybh(4);
				yszktzLocalDao.merge(yszktzLocal);
			}
			// xl
			yszktzLocalDao.deleteYSZKTZLocalByQY(5);
			List<YSZKTZXL> yszktzXLList = yszktzXLDao.getAllYSZKTZ();
			for (YSZKTZXL yszktzXL : yszktzXLList) {
				yszktzLocal = new YSZKTZLocal();
				yszktzLocal.setGxrq(yszktzXL.getGxrq());
				yszktzLocal.setHtbh(yszktzXL.getHtbh());
				yszktzLocal.setKhbh(yszktzXL.getKhbh());
				yszktzLocal.setKhmc(yszktzXL.getKhmc());
				yszktzLocal.setKhsshy(yszktzXL.getKhsshy());
				yszktzLocal.setKxlb(yszktzXL.getKxlb());
				yszktzLocal.setKxzt(yszktzXL.getKxzt());
				yszktzLocal.setYsje(yszktzXL.getYsje());
				yszktzLocal.setDqrq(yszktzXL.getDqrq());
				yszktzLocal.setYhxje(yszktzXL.getYhxje());
				yszktzLocal.setYfhje(yszktzXL.getYfhje());
				yszktzLocal.setFhrq(yszktzXL.getFhrq());
				yszktzLocal.setYkpje(yszktzXL.getYkpje());
				yszktzLocal.setKprq(yszktzXL.getKprq());
				yszktzLocal.setYqyyfl(yszktzXL.getYqyyfl());
				yszktzLocal.setSftgflsdqs(yszktzXL.getSftgflsdqs());
				yszktzLocal.setSfdrwc(yszktzXL.getSfdrwc());
				yszktzLocal.setQybh(5);
				yszktzLocalDao.merge(yszktzLocal);
			}
			// tb
			yszktzLocalDao.deleteYSZKTZLocalByQY(301);
			List<YSZKTZBYQ> yszktzTBList = yszktzTBDao.getAllYSZKTZ();
			for (YSZKTZBYQ yszktzTB : yszktzTBList) {
				yszktzLocal = new YSZKTZLocal();
				yszktzLocal.setGxrq(yszktzTB.getGxrq());
				yszktzLocal.setHtbh(yszktzTB.getHtbh());
				yszktzLocal.setKhbh(yszktzTB.getKhbh());
				yszktzLocal.setKhmc(yszktzTB.getKhmc());
				yszktzLocal.setKhsshy(yszktzTB.getKhsshy());
				yszktzLocal.setKxlb(yszktzTB.getKxlb());
				yszktzLocal.setKxzt(yszktzTB.getKxzt());
				yszktzLocal.setYsje(yszktzTB.getYsje());
				yszktzLocal.setDqrq(yszktzTB.getDqrq());
				yszktzLocal.setYhxje(yszktzTB.getYhxje());
				yszktzLocal.setYfhje(yszktzTB.getYfhje());
				yszktzLocal.setFhrq(yszktzTB.getFhrq());
				yszktzLocal.setYkpje(yszktzTB.getYkpje());
				yszktzLocal.setKprq(yszktzTB.getKprq());
				yszktzLocal.setYqyyfl(yszktzTB.getYqyyfl());
				yszktzLocal.setSftgflsdqs(yszktzTB.getSftgflsdqs());
				yszktzLocal.setSfdrwc(yszktzTB.getSfdrwc());
				yszktzLocal.setQybh(301);
				yszktzLocalDao.merge(yszktzLocal);
			}

			// sb
			yszktzLocalDao.deleteYSZKTZLocalByQY(1);
			List<YSZKTZBYQ> yszktzSBList = yszktzSBDao.getAllYSZKTZ();
			for (YSZKTZBYQ yszktzSB : yszktzSBList) {
				yszktzLocal = new YSZKTZLocal();
				yszktzLocal.setGxrq(yszktzSB.getGxrq());
				yszktzLocal.setHtbh(yszktzSB.getHtbh());
				yszktzLocal.setKhbh(yszktzSB.getKhbh());
				yszktzLocal.setKhmc(yszktzSB.getKhmc());
				yszktzLocal.setKhsshy(yszktzSB.getKhsshy());
				yszktzLocal.setKxlb(yszktzSB.getKxlb());
				yszktzLocal.setKxzt(yszktzSB.getKxzt());
				yszktzLocal.setYsje(yszktzSB.getYsje());
				yszktzLocal.setDqrq(yszktzSB.getDqrq());
				yszktzLocal.setYhxje(yszktzSB.getYhxje());
				yszktzLocal.setYfhje(yszktzSB.getYfhje());
				yszktzLocal.setFhrq(yszktzSB.getFhrq());
				yszktzLocal.setYkpje(yszktzSB.getYkpje());
				yszktzLocal.setKprq(yszktzSB.getKprq());
				yszktzLocal.setYqyyfl(yszktzSB.getYqyyfl());
				yszktzLocal.setSftgflsdqs(yszktzSB.getSftgflsdqs());
				yszktzLocal.setSfdrwc(yszktzSB.getSfdrwc());
				yszktzLocal.setQybh(1);
				yszktzLocalDao.merge(yszktzLocal);
			}

			// xb
			yszktzLocalDao.deleteYSZKTZLocalByQY(3);
			List<YSZKTZBYQ> yszktzXBList = yszktzXBDao.getAllYSZKTZ();
			for (YSZKTZBYQ yszktzXB : yszktzXBList) {
				yszktzLocal = new YSZKTZLocal();
				yszktzLocal.setGxrq(yszktzXB.getGxrq());
				yszktzLocal.setHtbh(yszktzXB.getHtbh());
				yszktzLocal.setKhbh(yszktzXB.getKhbh());
				yszktzLocal.setKhmc(yszktzXB.getKhmc());
				yszktzLocal.setKhsshy(yszktzXB.getKhsshy());
				yszktzLocal.setKxlb(yszktzXB.getKxlb());
				yszktzLocal.setKxzt(yszktzXB.getKxzt());
				yszktzLocal.setYsje(yszktzXB.getYsje());
				yszktzLocal.setDqrq(yszktzXB.getDqrq());
				yszktzLocal.setYhxje(yszktzXB.getYhxje());
				yszktzLocal.setYfhje(yszktzXB.getYfhje());
				yszktzLocal.setFhrq(yszktzXB.getFhrq());
				yszktzLocal.setYkpje(yszktzXB.getYkpje());
				yszktzLocal.setKprq(yszktzXB.getKprq());
				yszktzLocal.setYqyyfl(yszktzXB.getYqyyfl());
				yszktzLocal.setSftgflsdqs(yszktzXB.getSftgflsdqs());
				yszktzLocal.setSfdrwc(yszktzXB.getSfdrwc());
				yszktzLocal.setQybh(3);
				yszktzLocalDao.merge(yszktzLocal);
			}

			// hb
			SimpleDateFormat timeFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			yszktzLocalDao.deleteYSZKTZLocalByQY(2);
			WebServiceClient webServiceClient = new WebServiceClient();
			List<Map<String, Object>> recList = webServiceClient.getRec(
					"web_test", "123456", "yszk_ws_yszktz");
			for (Map<String, Object> recMap : recList) {

				yszktzLocal = new YSZKTZLocal();
				yszktzLocal.setGxrq(CommonMethod.objectToDate(timeFormat,
						recMap.get("gxrq")));
				yszktzLocal.setHtbh(String.valueOf(recMap.get("htbh")));
				yszktzLocal.setKhbh(String.valueOf(recMap.get("Khbh")));
				yszktzLocal.setKhmc(String.valueOf(recMap.get("Khmc")));
				yszktzLocal.setKhsshy(String.valueOf(recMap.get("Khsshy")));
				yszktzLocal.setKxlb(String.valueOf(recMap.get("kxlb")));
				yszktzLocal.setKxzt(String.valueOf(recMap.get("kxzt")));
				yszktzLocal.setYsje(CommonMethod.objectToDouble(recMap
						.get("ysje")));
				yszktzLocal.setDqrq(CommonMethod.objectToDate(timeFormat,
						recMap.get("dqrq")));
				yszktzLocal.setYhxje(CommonMethod.objectToDouble(recMap
						.get("yhxje")));
				yszktzLocal.setYfhje(CommonMethod.objectToDouble(recMap
						.get("yfhje")));
				yszktzLocal.setFhrq(CommonMethod.objectToDate(timeFormat,
						recMap.get("fhrq")));
				yszktzLocal.setYkpje(CommonMethod.objectToDouble(recMap
						.get("ykpje")));
				yszktzLocal.setKprq(CommonMethod.objectToDate(timeFormat,
						recMap.get("kprq")));
				yszktzLocal.setYqyyfl(CommonMethod.objectToInteger(recMap
						.get("yqyyfl")));
				yszktzLocal.setSftgflsdqs(String.valueOf(recMap
						.get("sftgflsdqs")));
				yszktzLocal.setSfdrwc(String.valueOf(recMap.get("sfdrwc")));
				yszktzLocal.setQybh(2);
				yszktzLocalDao.merge(yszktzLocal);
			}

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public YSZKTZLocalDao getYszktzLocalDao() {
		return yszktzLocalDao;
	}

	public void setYszktzLocalDao(YSZKTZLocalDao yszktzLocalDao) {
		this.yszktzLocalDao = yszktzLocalDao;
	}

	public YSZKTZXLDao getYszktzDLDao() {
		return yszktzDLDao;
	}

	public void setYszktzDLDao(YSZKTZXLDao yszktzDLDao) {
		this.yszktzDLDao = yszktzDLDao;
	}

	public YSZKTZXLDao getYszktzLLDao() {
		return yszktzLLDao;
	}

	public void setYszktzLLDao(YSZKTZXLDao yszktzLLDao) {
		this.yszktzLLDao = yszktzLLDao;
	}

	public YSZKTZBYQDao getYszktzTBDao() {
		return yszktzTBDao;
	}

	public void setYszktzTBDao(YSZKTZBYQDao yszktzTBDao) {
		this.yszktzTBDao = yszktzTBDao;
	}

	public YSZKTZBYQDao getYszktzSBDao() {
		return yszktzSBDao;
	}

	public void setYszktzSBDao(YSZKTZBYQDao yszktzSBDao) {
		this.yszktzSBDao = yszktzSBDao;
	}

	public YSZKTZXLDao getYszktzXLDao() {
		return yszktzXLDao;
	}

	public void setYszktzXLDao(YSZKTZXLDao yszktzXLDao) {
		this.yszktzXLDao = yszktzXLDao;
	}

	public YSZKTZBYQDao getYszktzXBDao() {
		return yszktzXBDao;
	}

	public void setYszktzXBDao(YSZKTZBYQDao yszktzXBDao) {
		this.yszktzXBDao = yszktzXBDao;
	}

}
