package com.tbea.datatransfer.service.local.yszktz;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.yszktz.YSZKTZLocalDao;
import com.tbea.datatransfer.model.dao.zjbyq.yszktz.YSZKTZBYQDao;
import com.tbea.datatransfer.model.dao.zjxl.yszktz.YSZKTZXLDao;
import com.tbea.datatransfer.model.entity.local.YSZKTZLocal;
import com.tbea.datatransfer.model.entity.zjbyq.YSZKTZBYQ;
import com.tbea.datatransfer.model.entity.zjxl.YSZKTZXL;

@Transactional("transactionManager")
public class YSZKTZTransferServiceImpl implements YSZKTZTransferService {

	private YSZKTZLocalDao yszktzLocalDao;

	private YSZKTZXLDao yszktzDLDao;

	private YSZKTZXLDao yszktzLLDao;

	private YSZKTZBYQDao yszktzTBDao;

	private YSZKTZBYQDao yszktzSBDao;

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
				// yszktzLocal.setKxzt(yszktzTB.getKxzt());
				yszktzLocal.setYsje(yszktzTB.getYsje());
				yszktzLocal.setDqrq(yszktzTB.getDqrq());
				yszktzLocal.setYhxje(yszktzTB.getYhxje());
				yszktzLocal.setYfhje(yszktzTB.getYfhje());
				yszktzLocal.setFhrq(yszktzTB.getFhrq());
				yszktzLocal.setYkpje(yszktzTB.getYkpje());
				yszktzLocal.setKprq(yszktzTB.getKprq());
				// yszktzLocal.setYqyyfl(yszktzTB.getYqyyfl());
				// yszktzLocal.setSftgflsdqs(yszktzTB.getSftgflsdqs());
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
				// yszktzLocal.setKxzt(yszktzTB.getKxzt());
				yszktzLocal.setYsje(yszktzSB.getYsje());
				yszktzLocal.setDqrq(yszktzSB.getDqrq());
				yszktzLocal.setYhxje(yszktzSB.getYhxje());
				yszktzLocal.setYfhje(yszktzSB.getYfhje());
				yszktzLocal.setFhrq(yszktzSB.getFhrq());
				yszktzLocal.setYkpje(yszktzSB.getYkpje());
				yszktzLocal.setKprq(yszktzSB.getKprq());
				// yszktzLocal.setYqyyfl(yszktzTB.getYqyyfl());
				// yszktzLocal.setSftgflsdqs(yszktzTB.getSftgflsdqs());
				yszktzLocal.setSfdrwc(yszktzSB.getSfdrwc());
				yszktzLocal.setQybh(1);
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

}
