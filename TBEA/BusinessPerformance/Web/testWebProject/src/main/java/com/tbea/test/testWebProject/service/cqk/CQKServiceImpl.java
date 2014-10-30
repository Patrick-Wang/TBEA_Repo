package com.tbea.test.testWebProject.service.cqk;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.dao.cqk.CQKDao;
import com.tbea.test.testWebProject.model.dao.transfer.yszktz.YSZKTZLocalDao;
import com.tbea.test.testWebProject.model.entity.local.CQK;

@Transactional("transactionManager")
public class CQKServiceImpl implements CQKService {

	private CQKDao cqkDao;

	private YSZKTZLocalDao yszktzLocalDao;

	private void importCQKByHY(String hyName, List<String> sshyList,
			boolean isIncluded, boolean isTotal) throws Exception {
		CQK cqk = new CQK();
		cqk.setNy("201410");
		cqk.setHy(hyName);
		cqk.setCqk2n(yszktzLocalDao.getCQK(null, 730, sshyList, isIncluded,
				isTotal));
		cqk.setCqk3n(yszktzLocalDao.getCQK(730, 1095, sshyList, isIncluded,
				isTotal));
		cqk.setCqk4njzq(yszktzLocalDao.getCQK(1095, null, sshyList, isIncluded,
				isTotal));
		cqk.setZj(yszktzLocalDao.getCQK(null, null, sshyList, isIncluded,
				isTotal));
		cqkDao.merge(cqk);
		return;
	}

	@Override
	public boolean importCQK() {
		boolean result = false;
		try {
			List<String> sshyGWNWList = new ArrayList<String>();
			sshyGWNWList.add("01");
			sshyGWNWList.add("02");
			importCQKByHY("国网、南网", sshyGWNWList, true, false);

			List<String> sshySSDLList = new ArrayList<String>();
			sshySSDLList.add("08");
			importCQKByHY("省、市电力系统", sshySSDLList, true, false);

			List<String> sshyWDFDList = new ArrayList<String>();
			sshyWDFDList.add("03");
			sshyWDFDList.add("04");
			sshyWDFDList.add("05");
			sshyWDFDList.add("06");
			sshyWDFDList.add("07");
			importCQKByHY("五大发电", sshyWDFDList, true, false);

			List<String> sshyQTDYList = new ArrayList<String>();
			sshyQTDYList.add("09");
			importCQKByHY("其他电源", sshyQTDYList, true, false);

			List<String> sshySYSHList = new ArrayList<String>();
			sshySYSHList.add("16");
			importCQKByHY("石油石化", sshySYSHList, true, false);

			List<String> sshyGDJTList = new ArrayList<String>();
			sshyGDJTList.add("14");
			sshyGDJTList.add("15");
			importCQKByHY("轨道交通", sshyGDJTList, true, false);

			List<String> sshyCKHTList = new ArrayList<String>();
			sshyCKHTList.add("14");
			sshyCKHTList.add("15");
			importCQKByHY("出口合同", sshyCKHTList, true, false);

			List<String> sshyQTList = new ArrayList<String>();
			sshyQTList.addAll(sshyGWNWList);
			sshyQTList.addAll(sshySSDLList);
			sshyQTList.addAll(sshyWDFDList);
			sshyQTList.addAll(sshyQTDYList);
			sshyQTList.addAll(sshySYSHList);
			sshyQTList.addAll(sshyGDJTList);
			sshyQTList.addAll(sshyCKHTList);
			importCQKByHY("其他", sshyQTList, false, false);

			importCQKByHY("合计", null, false, true);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public CQKDao getCqkDao() {
		return cqkDao;
	}

	public void setCqkDao(CQKDao cqkDao) {
		this.cqkDao = cqkDao;
	}

	public YSZKTZLocalDao getYszktzLocalDao() {
		return yszktzLocalDao;
	}

	public void setYszktzLocalDao(YSZKTZLocalDao yszktzLocalDao) {
		this.yszktzLocalDao = yszktzLocalDao;
	}

}
