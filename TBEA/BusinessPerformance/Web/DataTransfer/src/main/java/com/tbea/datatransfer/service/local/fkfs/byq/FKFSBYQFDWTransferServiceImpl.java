package com.tbea.datatransfer.service.local.fkfs.byq;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.fkfs.byq.FKFSBYQFDWLocalDao;
import com.tbea.datatransfer.model.dao.zjtb.fkfs.FKFSBYQFDWTBDao;
import com.tbea.datatransfer.model.entity.local.FKFSBYQFDWLocal;
import com.tbea.datatransfer.model.entity.zjtb.FKFSBYQFDWTB;

@Transactional("transactionManager")
public class FKFSBYQFDWTransferServiceImpl implements FKFSBYQFDWTransferService {

	private FKFSBYQFDWLocalDao fkfsbyqfdwLocalDao;

	private FKFSBYQFDWTBDao fkfsbyqfdwTBDao;

	@Override
	public boolean transferFKFSBYQFDW() {
		boolean result = false;
		try {
			// tb
			fkfsbyqfdwLocalDao.deleteFKFSBYQFDWLocalByQY(301);
			FKFSBYQFDWLocal fkfsbyqfdwLocal = null;
			List<FKFSBYQFDWTB> fkfsbyqfdwTBList = fkfsbyqfdwTBDao
					.getAllFKFSBYQFDWTB();
			for (FKFSBYQFDWTB fkfsbyqfdwTB : fkfsbyqfdwTBList) {
				fkfsbyqfdwLocal = new FKFSBYQFDWLocal();
				fkfsbyqfdwLocal.setGxrq(fkfsbyqfdwTB.getGxrq());
				fkfsbyqfdwLocal.setGsbm(fkfsbyqfdwTB.getGsbm());
//				fkfsbyqfdwLocal.setNy(fkfsbyqfdwTB.getNy());
				fkfsbyqfdwLocal.setFdwhtddzlbs(fkfsbyqfdwTB.getFdwhtddzlbs());
				fkfsbyqfdwLocal.setFdwhtddzlje(fkfsbyqfdwTB.getFdwhtddzlje());
				fkfsbyqfdwLocal.setWyfkhtbs(fkfsbyqfdwTB.getWyfkhtbs());
				fkfsbyqfdwLocal.setWyfkhtje(fkfsbyqfdwTB.getWyfkhtje());
				fkfsbyqfdwLocal.setYfkxybfzshtbs(fkfsbyqfdwTB
						.getYfkxybfzshtbs());
				fkfsbyqfdwLocal.setYfkxybfzshtje(fkfsbyqfdwTB
						.getYfkxybfzshtje());
				fkfsbyqfdwLocal.setYfkzbfzsdsszjhtbs(fkfsbyqfdwTB
						.getYfkzbfzsdsszjhtbs());
				fkfsbyqfdwLocal.setYfkzbfzsdsszjhtje(fkfsbyqfdwTB
						.getYfkzbfzsdsszjhtje());
				fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtbs(fkfsbyqfdwTB
						.getHwjfhfkblxybfzbshtbs());
				fkfsbyqfdwLocal.setHwjfhfkblxybfzbshtje(fkfsbyqfdwTB
						.getHwjfhfkblxybfzbshtje());
				fkfsbyqfdwLocal.setWddsjhtbs(fkfsbyqfdwTB.getWddsjhtbs());
				fkfsbyqfdwLocal.setWddsjhtje(fkfsbyqfdwTB.getWddsjhtje());
				fkfsbyqfdwLocal.setZbqdysegyhtbs(fkfsbyqfdwTB.getZbqdysegyhtbs());
				fkfsbyqfdwLocal.setZbqdysegyhtje(fkfsbyqfdwTB.getZbqdysegyhtje());
				fkfsbyqfdwLocal.setXkxhhtbs(fkfsbyqfdwTB.getXkxhhtbs());
				fkfsbyqfdwLocal.setXkxhhtje(fkfsbyqfdwTB.getXkxhhtje());
				fkfsbyqfdwLocal.setSfdrwc(fkfsbyqfdwTB.getSfdrwc());
				fkfsbyqfdwLocal.setQybh(301);
				fkfsbyqfdwLocalDao.merge(fkfsbyqfdwLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public FKFSBYQFDWLocalDao getFkfsbyqfdwLocalDao() {
		return fkfsbyqfdwLocalDao;
	}

	public void setFkfsbyqfdwLocalDao(FKFSBYQFDWLocalDao fkfsbyqfdwLocalDao) {
		this.fkfsbyqfdwLocalDao = fkfsbyqfdwLocalDao;
	}

	public FKFSBYQFDWTBDao getFkfsbyqfdwTBDao() {
		return fkfsbyqfdwTBDao;
	}

	public void setFkfsbyqfdwTBDao(FKFSBYQFDWTBDao fkfsbyqfdwTBDao) {
		this.fkfsbyqfdwTBDao = fkfsbyqfdwTBDao;
	}

}
