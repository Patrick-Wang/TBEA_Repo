package com.tbea.datatransfer.service.local.yszktz;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.yszktz.YSZKTZLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.yszktz.YSZKTZDLDao;
import com.tbea.datatransfer.model.dao.zjtb.yszktz.YSZKTZTBDao;
import com.tbea.datatransfer.model.entity.local.YSZKTZLocal;
import com.tbea.datatransfer.model.entity.zjdl.YSZKTZDL;
import com.tbea.datatransfer.model.entity.zjtb.YSZKTZTB;

@Transactional("transactionManager")
public class YSZKTZTransferServiceImpl implements YSZKTZTransferService {

	private YSZKTZLocalDao yszktzLocalDao;

	private YSZKTZDLDao yszktzDLDao;
	
	private YSZKTZTBDao yszktzTBDao;

	@Override
	@Transactional("transactionManager")
	public boolean transferYSZKTZ() {
		boolean result = false;
		try {
			// dl
			yszktzLocalDao.deleteYSZKTZLocalByQY(6);
			YSZKTZLocal yszktzLocal = null;
			List<YSZKTZDL> yszktzDLList = yszktzDLDao.getAllYSZKTZDL();
			for (YSZKTZDL yszktzDL : yszktzDLList) {
				yszktzLocal = new YSZKTZLocal();
				yszktzLocal.setGxrq(yszktzDL.getGxrq());
				yszktzLocal.setHtbh(yszktzDL.getHtbh());
				yszktzLocal.setKhbh(yszktzDL.getKhbh());
				yszktzLocal.setKhmc(yszktzDL.getKhmc());
				yszktzLocal.setKhsshy(yszktzDL.getKhsshy());
				yszktzLocal.setKxlb(yszktzDL.getKxlb());
				// yszktzLocal.setKxzt(yszktzDL.getKxzt());
				yszktzLocal.setYsje(yszktzDL.getYsje());
				yszktzLocal.setDqrq(yszktzDL.getDqrq());
				yszktzLocal.setYhxje(yszktzDL.getYhxje());
				yszktzLocal.setYfhje(yszktzDL.getYfhje());
				yszktzLocal.setFhrq(yszktzDL.getFhrq());
				yszktzLocal.setYkpje(yszktzDL.getYkpje());
				yszktzLocal.setKprq(yszktzDL.getKprq());
				// yszktzLocal.setYqyyfl(yszktzDL.getYqyyfl());
				// yszktzLocal.setSftgflsdqs(yszktzDL.getSftgflsdqs());
				yszktzLocal.setSfdrwc(yszktzDL.getSfdrwc());
				yszktzLocal.setQybh(6);
				yszktzLocalDao.merge(yszktzLocal);
			}
			// tb
			yszktzLocalDao.deleteYSZKTZLocalByQY(301);
			List<YSZKTZTB> yszktzTBList = yszktzTBDao.getAllYSZKTZTB();
			for (YSZKTZTB yszktzTB : yszktzTBList) {
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

	public YSZKTZDLDao getYszktzDLDao() {
		return yszktzDLDao;
	}

	public void setYszktzDLDao(YSZKTZDLDao yszktzDLDao) {
		this.yszktzDLDao = yszktzDLDao;
	}

	public YSZKTZTBDao getYszktzTBDao() {
		return yszktzTBDao;
	}

	public void setYszktzTBDao(YSZKTZTBDao yszktzTBDao) {
		this.yszktzTBDao = yszktzTBDao;
	}

}
