package com.tbea.datatransfer.service.inner.rhkxx;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.common.CommonMethod;
import com.tbea.datatransfer.model.dao.inner.rhkxx.RHKXXDao;
import com.tbea.datatransfer.model.dao.local.mrhkhz.MRHKHZLocalDao;
import com.tbea.datatransfer.model.dao.local.ydhkjhjgb.YDHKJHJGBLocalDao;
import com.tbea.datatransfer.model.entity.inner.RHKXX;
import com.tbea.datatransfer.model.entity.local.MRHKHZLocal;

@Transactional("transactionManager")
public class RHKXXServiceImpl implements RHKXXService {

	private RHKXXDao rhkxxDao;

	private MRHKHZLocalDao mrhkhzLocalDao;

	private YDHKJHJGBLocalDao ydhkjhjgbLocalDao;

	@Override
	public boolean importRHKXX() {
		boolean result = false;
		try {
			rhkxxDao.truncateRHKXX();
			RHKXX rhkxx = null;
			List<MRHKHZLocal> mrhkhzLocalList = mrhkhzLocalDao
					.getAllMRHKHZLocal();
			Date hkrq = null;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Integer qybh = null;
			String gsbm = null;
			Object[] bcInfo = null;
			Double qzqbbc = null;
			Double qzzqbc = null;
			Double jtxdydzjhlzb = null;
			Double gdwzxzddhkjh = null;
			Double ylj = null;
			String zjhlzbwc = null;
			String hkjhwcl = null;
			Double mqzydhkjhhj = null;
			Double qyqb = null;
			String yjqyjhwcl = null;
			for (MRHKHZLocal mrhkhzLocal : mrhkhzLocalList) {
				rhkxx = new RHKXX();
				hkrq = mrhkhzLocal.getHkrq();
				rhkxx.setHkrq(hkrq);
				rhkxx.setHkje(mrhkhzLocal.getHkje());
				qybh = mrhkhzLocal.getQybh();
				gsbm = String.valueOf(qybh);
				bcInfo = mrhkhzLocalDao.getYLJBCByQY(formatter.format(hkrq),
						qybh);
				qzqbbc = null == bcInfo[0] ? 0.0D : Double.valueOf(String
						.valueOf(bcInfo[0]));
				rhkxx.setQzqbbc(qzqbbc);
				qzzqbc = null == bcInfo[1] ? 0.0D : Double.valueOf(String
						.valueOf(bcInfo[1]));
				rhkxx.setQzzqbc(qzzqbc);
				rhkxx.setYhkzkjysdhkje(mrhkhzLocal.getYhkzkjysdhkje());
				rhkxx.setJzydyszkzmye(mrhkhzLocal.getJzydyszkzmye());
				jtxdydzjhlzb = mrhkhzLocal.getJtxdydzjhlzb();
				rhkxx.setJtxdydzjhlzb(jtxdydzjhlzb);
				gdwzxzddhkjh = ydhkjhjgbLocalDao.getHKJHZEByQY(
						formatter.format(hkrq), qybh, gsbm);
				rhkxx.setGdwzxzddhkjh(gdwzxzddhkjh);
				ylj = mrhkhzLocalDao.getYLJHKByQY(formatter.format(hkrq), qybh);
				rhkxx.setYlj(ylj);
				zjhlzbwc = CommonMethod.getPercent(ylj, jtxdydzjhlzb);
				rhkxx.setZjhlzbwc(zjhlzbwc);
				hkjhwcl = CommonMethod.getPercent(ylj, gdwzxzddhkjh);
				rhkxx.setHkjhwcl(hkjhwcl);
				mqzydhkjhhj = qzqbbc + qzzqbc;
				rhkxx.setMqzydhkjhhj(mqzydhkjhhj);
				qyqb = qzqbbc + ylj;
				rhkxx.setQyqb(qyqb);
				yjqyjhwcl = CommonMethod.getPercent(qyqb, gdwzxzddhkjh);
				rhkxx.setYjqyjhwcl(yjqyjhwcl);
				rhkxx.setQybh(qybh);
				rhkxxDao.merge(rhkxx);
			}
			result = true;
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		} finally {
			System.out.println("importRHKXX:" + result);
		}
		return result;
	}

	public RHKXXDao getRhkxxDao() {
		return rhkxxDao;
	}

	public void setRhkxxDao(RHKXXDao rhkxxDao) {
		this.rhkxxDao = rhkxxDao;
	}

	public MRHKHZLocalDao getMrhkhzLocalDao() {
		return mrhkhzLocalDao;
	}

	public void setMrhkhzLocalDao(MRHKHZLocalDao mrhkhzLocalDao) {
		this.mrhkhzLocalDao = mrhkhzLocalDao;
	}

	public YDHKJHJGBLocalDao getYdhkjhjgbLocalDao() {
		return ydhkjhjgbLocalDao;
	}

	public void setYdhkjhjgbLocalDao(YDHKJHJGBLocalDao ydhkjhjgbLocalDao) {
		this.ydhkjhjgbLocalDao = ydhkjhjgbLocalDao;
	}

}
