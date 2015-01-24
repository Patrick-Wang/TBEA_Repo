package com.tbea.datatransfer.service.local.fkfs.xl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.fkfs.xl.FKFSXLFDWLocalDao;
import com.tbea.datatransfer.model.dao.zjxl.fkfs.FKFSXLFDWXLDao;
import com.tbea.datatransfer.model.entity.local.FKFSXLFDWLocal;
import com.tbea.datatransfer.model.entity.zjxl.FKFSXLFDWXL;

@Transactional("transactionManager")
public class FKFSXLFDWTransferServiceImpl implements FKFSXLFDWTransferService {

	private FKFSXLFDWLocalDao fkfsxlfdwLocalDao;

	private FKFSXLFDWXLDao fkfsxlfdwDLDao;

	private FKFSXLFDWXLDao fkfsxlfdwLLDao;

	private FKFSXLFDWXLDao fkfsxlfdwXLDao;

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
			// ll
			fkfsxlfdwLocalDao.deleteFKFSXLFDWLocalByQY(4);
			List<FKFSXLFDWXL> fkfsxlfdwLLList = fkfsxlfdwLLDao
					.getAllFKFSXLFDW();
			for (FKFSXLFDWXL fkfsxlfdwLL : fkfsxlfdwLLList) {
				fkfsxlfdwLocal = new FKFSXLFDWLocal();
				fkfsxlfdwLocal.setGxrq(fkfsxlfdwLL.getGxrq());
				fkfsxlfdwLocal.setGsbm(fkfsxlfdwLL.getGsbm());
				fkfsxlfdwLocal.setKhbh(fkfsxlfdwLL.getKhbh());
				fkfsxlfdwLocal.setDdzlbs(fkfsxlfdwLL.getDdzlbs());
				fkfsxlfdwLocal.setDdzlje(fkfsxlfdwLL.getDdzlje());
				fkfsxlfdwLocal.setWyfkhtbs(fkfsxlfdwLL.getWyfkhtbs());
				fkfsxlfdwLocal.setWyfkhtje(fkfsxlfdwLL.getWyfkhtje());
				fkfsxlfdwLocal.setYfkxybfzshtbs(fkfsxlfdwLL.getYfkxybfzshtbs());
				fkfsxlfdwLocal.setYfkxybfzshtje(fkfsxlfdwLL.getYfkxybfzshtje());
				fkfsxlfdwLocal.setYfkzbfzsdsszjhtbs(fkfsxlfdwLL
						.getYfkzbfzsdsszjhtbs());
				fkfsxlfdwLocal.setYfkzbfzsdsszjhtje(fkfsxlfdwLL
						.getYfkzbfzsdsszjhtje());
				fkfsxlfdwLocal.setHwjfhfkblxybfzbshtbs(fkfsxlfdwLL
						.getHwjfhfkblxybfzbshtbs());
				fkfsxlfdwLocal.setHwjfhfkblxybfzbshtje(fkfsxlfdwLL
						.getHwjfhfkblxybfzbshtje());
				fkfsxlfdwLocal.setZbjbfzshtbs(fkfsxlfdwLL.getZbjbfzshtbs());
				fkfsxlfdwLocal.setZbjbfzshtje(fkfsxlfdwLL.getZbjbfzshtje());
				fkfsxlfdwLocal.setZbjbfzwhtbs(fkfsxlfdwLL.getZbjbfzwhtbs());
				fkfsxlfdwLocal.setZbjbfzwhtje(fkfsxlfdwLL.getZbjbfzwhtje());
				fkfsxlfdwLocal.setWzbjhtbs(fkfsxlfdwLL.getWzbjhtbs());
				fkfsxlfdwLocal.setWzbjhtje(fkfsxlfdwLL.getWzbjhtje());
				fkfsxlfdwLocal.setZbqcgynhtbs(fkfsxlfdwLL.getZbqcgynhtbs());
				fkfsxlfdwLocal.setZbqcgynhtje(fkfsxlfdwLL.getZbqcgynhtje());
				fkfsxlfdwLocal.setWddsjhtbs(fkfsxlfdwLL.getWddsjhtbs());
				fkfsxlfdwLocal.setWddsjhtje(fkfsxlfdwLL.getWddsjhtje());
				fkfsxlfdwLocal.setXkxhhtbs(fkfsxlfdwLL.getXkxhhtbs());
				fkfsxlfdwLocal.setXkxhhtje(fkfsxlfdwLL.getXkxhhtje());
				fkfsxlfdwLocal.setSfdrwc(fkfsxlfdwLL.getSfdrwc());
				fkfsxlfdwLocal.setQybh(4);
				fkfsxlfdwLocalDao.merge(fkfsxlfdwLocal);
			}
			// xl
			fkfsxlfdwLocalDao.deleteFKFSXLFDWLocalByQY(5);
			List<FKFSXLFDWXL> fkfsxlfdwXLList = fkfsxlfdwXLDao
					.getAllFKFSXLFDW();
			for (FKFSXLFDWXL fkfsxlfdwXL : fkfsxlfdwXLList) {
				fkfsxlfdwLocal = new FKFSXLFDWLocal();
				fkfsxlfdwLocal.setGxrq(fkfsxlfdwXL.getGxrq());
				fkfsxlfdwLocal.setGsbm(fkfsxlfdwXL.getGsbm());
				fkfsxlfdwLocal.setKhbh(fkfsxlfdwXL.getKhbh());
				fkfsxlfdwLocal.setDdzlbs(fkfsxlfdwXL.getDdzlbs());
				fkfsxlfdwLocal.setDdzlje(fkfsxlfdwXL.getDdzlje());
				fkfsxlfdwLocal.setWyfkhtbs(fkfsxlfdwXL.getWyfkhtbs());
				fkfsxlfdwLocal.setWyfkhtje(fkfsxlfdwXL.getWyfkhtje());
				fkfsxlfdwLocal.setYfkxybfzshtbs(fkfsxlfdwXL.getYfkxybfzshtbs());
				fkfsxlfdwLocal.setYfkxybfzshtje(fkfsxlfdwXL.getYfkxybfzshtje());
				fkfsxlfdwLocal.setYfkzbfzsdsszjhtbs(fkfsxlfdwXL
						.getYfkzbfzsdsszjhtbs());
				fkfsxlfdwLocal.setYfkzbfzsdsszjhtje(fkfsxlfdwXL
						.getYfkzbfzsdsszjhtje());
				fkfsxlfdwLocal.setHwjfhfkblxybfzbshtbs(fkfsxlfdwXL
						.getHwjfhfkblxybfzbshtbs());
				fkfsxlfdwLocal.setHwjfhfkblxybfzbshtje(fkfsxlfdwXL
						.getHwjfhfkblxybfzbshtje());
				fkfsxlfdwLocal.setZbjbfzshtbs(fkfsxlfdwXL.getZbjbfzshtbs());
				fkfsxlfdwLocal.setZbjbfzshtje(fkfsxlfdwXL.getZbjbfzshtje());
				fkfsxlfdwLocal.setZbjbfzwhtbs(fkfsxlfdwXL.getZbjbfzwhtbs());
				fkfsxlfdwLocal.setZbjbfzwhtje(fkfsxlfdwXL.getZbjbfzwhtje());
				fkfsxlfdwLocal.setWzbjhtbs(fkfsxlfdwXL.getWzbjhtbs());
				fkfsxlfdwLocal.setWzbjhtje(fkfsxlfdwXL.getWzbjhtje());
				fkfsxlfdwLocal.setZbqcgynhtbs(fkfsxlfdwXL.getZbqcgynhtbs());
				fkfsxlfdwLocal.setZbqcgynhtje(fkfsxlfdwXL.getZbqcgynhtje());
				fkfsxlfdwLocal.setWddsjhtbs(fkfsxlfdwXL.getWddsjhtbs());
				fkfsxlfdwLocal.setWddsjhtje(fkfsxlfdwXL.getWddsjhtje());
				fkfsxlfdwLocal.setXkxhhtbs(fkfsxlfdwXL.getXkxhhtbs());
				fkfsxlfdwLocal.setXkxhhtje(fkfsxlfdwXL.getXkxhhtje());
				fkfsxlfdwLocal.setSfdrwc(fkfsxlfdwXL.getSfdrwc());
				fkfsxlfdwLocal.setQybh(5);
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

	public FKFSXLFDWXLDao getFkfsxlfdwDLDao() {
		return fkfsxlfdwDLDao;
	}

	public void setFkfsxlfdwDLDao(FKFSXLFDWXLDao fkfsxlfdwDLDao) {
		this.fkfsxlfdwDLDao = fkfsxlfdwDLDao;
	}

	public FKFSXLFDWXLDao getFkfsxlfdwLLDao() {
		return fkfsxlfdwLLDao;
	}

	public void setFkfsxlfdwLLDao(FKFSXLFDWXLDao fkfsxlfdwLLDao) {
		this.fkfsxlfdwLLDao = fkfsxlfdwLLDao;
	}

	public FKFSXLFDWXLDao getFkfsxlfdwXLDao() {
		return fkfsxlfdwXLDao;
	}

	public void setFkfsxlfdwXLDao(FKFSXLFDWXLDao fkfsxlfdwXLDao) {
		this.fkfsxlfdwXLDao = fkfsxlfdwXLDao;
	}

}
