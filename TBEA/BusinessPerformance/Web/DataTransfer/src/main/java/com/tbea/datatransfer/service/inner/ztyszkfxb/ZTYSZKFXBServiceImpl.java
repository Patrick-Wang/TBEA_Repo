package com.tbea.datatransfer.service.inner.ztyszkfxb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
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
			ztyszkfxbDao.truncateZTYSZKFXB();
			ZTYSZKFXB ztyszkfxb = null;
			List<ZTYSZKFXBLocal> ztyszkfxbLocalList = ztyszkfxbLocalDao
					.getAllZTYSZKFXBLocal();
			Date gxrq = null;
			Integer qybh = null;
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

			Calendar calendar = Calendar.getInstance();
			int month = 0;
			for (ZTYSZKFXBLocal ztyszkfxbLocal : ztyszkfxbLocalList) {
				ztyszkfxb = new ZTYSZKFXB();
				gxrq = ztyszkfxbLocal.getGxrq();
				calendar.setTime(gxrq);
				month = calendar.get(Calendar.MONTH) + 1;
				ztyszkfxb.setGxrq(gxrq);
				qybh = ztyszkfxbLocal.getQybh();
				ztyszkfxb.setQybh(qybh);

				// by
				byzmyszkye = CommonMethod.objectToDouble(ztyszkfxbLocal
						.getByzmyszkye());
				ztyszkfxb.setByzmyszkye(byzmyszkye);
				byblkzye = CommonMethod.objectToDouble(ztyszkfxbLocal
						.getByblkzye());
				ztyszkfxb.setByblkzye(byblkzye);
				byyszksjs = CommonMethod.objectToDouble(ztyszkfxbLocal
						.getByyszksjs());
				ztyszkfxb.setByyszksjs(byyszksjs);
				ljsr = ztyszkfxbLocalDao.getLJSRByQYAndDate(qybh, gxrq);
				ztyszkfxb.setLjsr(ljsr);
				zmyszsrb = CommonMethod.getPercent(byzmyszkye,
						(ljsr / month * 12));
				ztyszkfxb.setZmyszsrb(zmyszsrb);

				// qntq
				qntqzmyszkye = CommonMethod.objectToDouble(ztyszkfxbLocal
						.getQntqzmyszkye());
				ztyszkfxb.setQntqzmyszkye(qntqzmyszkye);
				qntqblye = CommonMethod.objectToDouble(ztyszkfxbLocal
						.getQntqblye());
				ztyszkfxb.setQntqblye(qntqblye);
				qntqyszksjs = CommonMethod.objectToDouble(ztyszkfxbLocal
						.getQntqyszksjs());
				ztyszkfxb.setQntqyszksjs(qntqyszksjs);
				qntqsr = ztyszkfxbLocalDao.getQNTQLJSRByQYAndDate(qybh, gxrq);
				ztyszkfxb.setQntqsr(qntqsr);
				qntqzmyszsrb = CommonMethod.getPercent(qntqzmyszkye, (qntqsr
						/ month * 12));
				ztyszkfxb.setQntqzmyszsrb(qntqzmyszsrb);

				// zzb
				zmyejqntqzzb = CommonMethod.getPercent(
						(byzmyszkye - qntqzmyszkye), qntqzmyszkye);
				ztyszkfxb.setZmyejqntqzzb(zmyejqntqzzb);
				bljqntqzzb = CommonMethod.getPercent((byblkzye - qntqblye),
						qntqblye);
				ztyszkfxb.setBljqntqzzb(bljqntqzzb);
				sjysjqntqzzb = CommonMethod.getPercent(
						(byyszksjs - qntqyszksjs), qntqyszksjs);
				ztyszkfxb.setSjysjqntqzzb(sjysjqntqzzb);
				srjqntqzzb = CommonMethod.getPercent((ljsr - qntqsr), qntqsr);
				ztyszkfxb.setSrjqntqzzb(srjqntqzzb);

				ztyszkfxbDao.merge(ztyszkfxb);
			}
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			System.out.println("importZTYSZKFXB:" + result);
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
