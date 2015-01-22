package com.tbea.datatransfer.service.inner.ztyszkfxb;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.inner.ztyszkfxb.ZTYSZKFXBDao;
import com.tbea.datatransfer.model.dao.local.ztyszkfxb.ZTYSZKFXBLocalDao;
import com.tbea.datatransfer.model.entity.inner.ZTYSZKFXB;
import com.tbea.datatransfer.model.entity.local.ZTYSZKFXBLocal;

@Transactional("transactionManager")
public class ZTYSZKFXBServiceImpl implements ZTYSZKFXBService {

	private ZTYSZKFXBDao ztyszkfxbDao;

	private ZTYSZKFXBLocalDao ztyszkfxbLocalDao;

	@Override
	public boolean importZTYSZKFXB() {
		boolean result = false;
		try {
			ZTYSZKFXB ztyszkfxb = null;
			List<ZTYSZKFXBLocal> ztyszkfxbLocalList = ztyszkfxbLocalDao
					.getAllZTYSZKFXBLocal();
			Double byzmyszkye = null;
			Double byblkzye = null;
			Double byyszksjs = null;
			Double ljsr = null;
			String zmyszsrb = null;

			Double qntqzmyszkye = null;
			Double qntqblye = null;
			Double qntqyszksjs = null;
			Double qntqsr = null;
			String qntqzmyszsrb = null;

			String zmyejqntqzzb = null;
			String bljqntqzzb = null;
			String sjysjqntqzzb = null;
			String srjqntqzzb = null;

			Calendar now = Calendar.getInstance();
			int month = now.get(Calendar.MONTH) + 1;
			for (ZTYSZKFXBLocal ztyszkfxbLocal : ztyszkfxbLocalList) {
				ztyszkfxb = new ZTYSZKFXB();
				ztyszkfxb.setGxrq(ztyszkfxbLocal.getGxrq());

				// by
				byzmyszkye = ztyszkfxbLocal.getByzmyszkye();
				ztyszkfxb.setByzmyszkye(byzmyszkye);
				byblkzye = ztyszkfxbLocal.getByblkzye();
				ztyszkfxb.setByblkzye(byblkzye);
				byyszksjs = ztyszkfxbLocal.getByyszksjs();
				ztyszkfxb.setByyszksjs(byyszksjs);
				ljsr = ztyszkfxbLocal.getBysr();
				ztyszkfxb.setLjsr(ljsr);
				zmyszsrb = String.format("%.2f", byzmyszkye
						/ (ljsr / month * 12))
						+ "%";
				ztyszkfxb.setZmyszsrb(zmyszsrb);

				// qntq
				qntqzmyszkye = ztyszkfxbLocal.getQntqzmyszkye();
				ztyszkfxb.setQntqzmyszkye(qntqzmyszkye);
				qntqblye = ztyszkfxbLocal.getQntqblye();
				ztyszkfxb.setQntqblye(qntqblye);
				qntqyszksjs = ztyszkfxbLocal.getQntqyszksjs();
				ztyszkfxb.setQntqyszksjs(qntqyszksjs);
				qntqsr = ztyszkfxbLocal.getQntqsr();
				ztyszkfxb.setQntqsr(qntqsr);
				qntqzmyszsrb = String.format("%.2f", qntqzmyszkye
						/ (qntqsr / month * 12))
						+ "%";
				ztyszkfxb.setQntqzmyszsrb(qntqzmyszsrb);

				// zzb
				zmyejqntqzzb = String.format("%.2f",
						(byzmyszkye - qntqzmyszkye) / qntqzmyszkye) + "%";
				ztyszkfxb.setZmyejqntqzzb(zmyejqntqzzb);
				bljqntqzzb = String.format("%.2f", (byblkzye - qntqblye)
						/ qntqblye)
						+ "%";
				ztyszkfxb.setBljqntqzzb(bljqntqzzb);
				sjysjqntqzzb = String.format("%.2f", (byyszksjs - qntqyszksjs)
						/ qntqyszksjs)
						+ "%";
				ztyszkfxb.setSjysjqntqzzb(sjysjqntqzzb);
				srjqntqzzb = String.format("%.2f", (ljsr - qntqsr) / qntqsr)
						+ "%";
				ztyszkfxb.setSrjqntqzzb(srjqntqzzb);

				ztyszkfxb.setQybh(ztyszkfxbLocal.getQybh());
				ztyszkfxbDao.merge(ztyszkfxb);
			}
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public ZTYSZKFXBDao getZtyszkfxbDao() {
		return ztyszkfxbDao;
	}

	public void setZtyszkfxbDao(ZTYSZKFXBDao ztyszkfxbDao) {
		this.ztyszkfxbDao = ztyszkfxbDao;
	}

	public ZTYSZKFXBLocalDao getZtyszkfxbLocalDao() {
		return ztyszkfxbLocalDao;
	}

	public void setZtyszkfxbLocalDao(ZTYSZKFXBLocalDao ztyszkfxbLocalDao) {
		this.ztyszkfxbLocalDao = ztyszkfxbLocalDao;
	}

}
