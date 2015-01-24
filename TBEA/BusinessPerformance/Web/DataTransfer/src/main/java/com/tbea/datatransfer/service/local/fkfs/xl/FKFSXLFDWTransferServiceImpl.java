package com.tbea.datatransfer.service.local.fkfs.xl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.fkfs.xl.FKFSXLFDWLocalDao;
import com.tbea.datatransfer.model.dao.zjdl.fkfs.FKFSXLFDWDLDao;
import com.tbea.datatransfer.model.entity.local.FKFSXLFDWLocal;
import com.tbea.datatransfer.model.entity.zjxl.FKFSXLFDWXL;

@Transactional("transactionManager")
public class FKFSXLFDWTransferServiceImpl implements FKFSXLFDWTransferService {

	private FKFSXLFDWLocalDao fkfsxlfdwLocalDao;

	private FKFSXLFDWDLDao fkfsxlfdwDLDao;

	@Override
	public boolean transferFKFSXLFDW() {
		boolean result = false;
		try {
			// dl
			fkfsxlfdwLocalDao.deleteFKFSXLFDWLocalByQY(6);
			FKFSXLFDWLocal fkfsxlfdwLocal = null;
			List<FKFSXLFDWXL> fkfsxlfdwDLList = fkfsxlfdwDLDao
					.getAllFKFSXLFDW();
			for (FKFSXLFDWXL fkfsxlfdwDL : fkfsxlfdwDLList) {
				fkfsxlfdwLocal = new FKFSXLFDWLocal();
				fkfsxlfdwLocal.setGxrq(fkfsxlfdwDL.getGxrq());
				fkfsxlfdwLocal.setGsbm(fkfsxlfdwDL.getGsbm());
				fkfsxlfdwLocal.setKhbh(fkfsxlfdwDL.getKhbh());
				fkfsxlfdwLocal.setDdzlbs(fkfsxlfdwDL.getDdzlbs());
				fkfsxlfdwLocal.setDdzlje(fkfsxlfdwDL.getDdzlje());
				fkfsxlfdwLocal.setWyfkhtbs(fkfsxlfdwDL.getWyfkhtbs());
				fkfsxlfdwLocal.setWyfkhtje(fkfsxlfdwDL.getWyfkhtje());
				fkfsxlfdwLocal.setYfkxybfzshtbs(fkfsxlfdwDL.getYfkxybfzshtbs());
				fkfsxlfdwLocal.setYfkxybfzshtje(fkfsxlfdwDL.getYfkxybfzshtje());
				fkfsxlfdwLocal.setYfkzbfzsdsszjhtbs(fkfsxlfdwDL
						.getYfkzbfzsdsszjhtbs());
				fkfsxlfdwLocal.setYfkzbfzsdsszjhtje(fkfsxlfdwDL
						.getYfkzbfzsdsszjhtje());
				fkfsxlfdwLocal.setHwjfhfkblxybfzbshtbs(fkfsxlfdwDL
						.getHwjfhfkblxybfzbshtbs());
				fkfsxlfdwLocal.setHwjfhfkblxybfzbshtje(fkfsxlfdwDL
						.getHwjfhfkblxybfzbshtje());
				fkfsxlfdwLocal.setZbjbfzshtbs(fkfsxlfdwDL.getZbjbfzshtbs());
				fkfsxlfdwLocal.setZbjbfzshtje(fkfsxlfdwDL.getZbjbfzshtje());
				fkfsxlfdwLocal.setZbjbfzwhtbs(fkfsxlfdwDL.getZbjbfzwhtbs());
				fkfsxlfdwLocal.setZbjbfzwhtje(fkfsxlfdwDL.getZbjbfzwhtje());
				fkfsxlfdwLocal.setWzbjhtbs(fkfsxlfdwDL.getWzbjhtbs());
				fkfsxlfdwLocal.setWzbjhtje(fkfsxlfdwDL.getWzbjhtje());
				fkfsxlfdwLocal.setZbqcgynhtbs(fkfsxlfdwDL.getZbqcgynhtbs());
				fkfsxlfdwLocal.setZbqcgynhtje(fkfsxlfdwDL.getZbqcgynhtje());
				fkfsxlfdwLocal.setWddsjhtbs(fkfsxlfdwDL.getWddsjhtbs());
				fkfsxlfdwLocal.setWddsjhtje(fkfsxlfdwDL.getWddsjhtje());
				fkfsxlfdwLocal.setXkxhhtbs(fkfsxlfdwDL.getXkxhhtbs());
				fkfsxlfdwLocal.setXkxhhtje(fkfsxlfdwDL.getXkxhhtje());
				fkfsxlfdwLocal.setSfdrwc(fkfsxlfdwDL.getSfdrwc());
				fkfsxlfdwLocal.setQybh(6);
				fkfsxlfdwLocalDao.merge(fkfsxlfdwLocal);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public FKFSXLFDWLocalDao getFkfsxlfdwLocalDao() {
		return fkfsxlfdwLocalDao;
	}

	public void setFkfsxlfdwLocalDao(FKFSXLFDWLocalDao fkfsxlfdwLocalDao) {
		this.fkfsxlfdwLocalDao = fkfsxlfdwLocalDao;
	}

	public FKFSXLFDWDLDao getFkfsxlfdwDLDao() {
		return fkfsxlfdwDLDao;
	}

	public void setFkfsxlfdwDLDao(FKFSXLFDWDLDao fkfsxlfdwDLDao) {
		this.fkfsxlfdwDLDao = fkfsxlfdwDLDao;
	}

}
