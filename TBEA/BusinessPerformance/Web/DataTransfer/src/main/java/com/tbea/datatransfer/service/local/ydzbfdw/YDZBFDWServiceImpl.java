package com.tbea.datatransfer.service.local.ydzbfdw;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.ydzbfdw.YDZBFDWDao;
import com.tbea.datatransfer.model.dao.yszk15.bl.BL15Dao;
import com.tbea.datatransfer.model.entity.local.YDZBFDW;

@Transactional("transactionManager")
public class YDZBFDWServiceImpl implements YDZBFDWService {

	private YDZBFDWDao ydzbfdwDao;

	private BL15Dao bl15Dao;

	@Override
	public boolean importYDZBFDW() {
		boolean result = false;
		try {
			Calendar cur = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			cur.set(2013, 1 - 1, 1);
			end.setTimeInMillis(System.currentTimeMillis());
			List<Object[]> ydzbfdw15List = null;
			Integer year = null;
			Integer month = null;
			while (!cur.after(end)) {
				year = cur.get(Calendar.YEAR);
				month = cur.get(Calendar.MONTH) + 1;
				ydzbfdw15List = bl15Dao.getAllYDZBFDW(year, month);
				YDZBFDW ydzbfdw = null;
				for (Object[] ydzbfdw15Array : ydzbfdw15List) {
					ydzbfdw = new YDZBFDW();
					ydzbfdw.setNy(year + String.format("%02d", month));
					ydzbfdw.setQybh(null == ydzbfdw15Array[1] ? 0 : Integer
							.valueOf(ydzbfdw15Array[1].toString()));
					ydzbfdw.setZbbh(null == ydzbfdw15Array[2] ? 0 : Integer
							.valueOf(ydzbfdw15Array[1].toString()));
					ydzbfdw.setZbmc((String) ydzbfdw15Array[3]);
					ydzbfdw.setByjhz(null == ydzbfdw15Array[4] ? 0 : Double
							.valueOf(ydzbfdw15Array[4].toString()));
					ydzbfdw.setByyjwcz(null == ydzbfdw15Array[5] ? 0 : Double
							.valueOf(ydzbfdw15Array[5].toString()));
					ydzbfdw.setBywcz(null == ydzbfdw15Array[6] ? 0 : Double
							.valueOf(ydzbfdw15Array[6].toString()));
					ydzbfdw.setJhwcl((String) ydzbfdw15Array[7]);
					ydzbfdw.setSywcz(null == ydzbfdw15Array[8] ? 0 : Double
							.valueOf(ydzbfdw15Array[8].toString()));
					ydzbfdw.setJsyzzb((String) ydzbfdw15Array[9]);
					ydzbfdw.setSjdpjz(null == ydzbfdw15Array[10] ? 0 : Double
							.valueOf(ydzbfdw15Array[10].toString()));
					ydzbfdw.setJjzzzb((String) ydzbfdw15Array[11]);
					ydzbfdw.setQnpjz(null == ydzbfdw15Array[12] ? 0 : Double
							.valueOf(ydzbfdw15Array[12].toString()));
					ydzbfdw.setJjzzzb1((String) ydzbfdw15Array[13]);
					ydzbfdw.setQntqz(null == ydzbfdw15Array[14] ? 0 : Double
							.valueOf(ydzbfdw15Array[14].toString()));
					ydzbfdw.setJtqzzb((String) ydzbfdw15Array[15]);
					ydzbfdw.setJdjh(null == ydzbfdw15Array[16] ? 0 : Double
							.valueOf(ydzbfdw15Array[16].toString()));
					ydzbfdw.setJdlj(null == ydzbfdw15Array[17] ? 0 : Double
							.valueOf(ydzbfdw15Array[17].toString()));
					ydzbfdw.setJdwcl((String) ydzbfdw15Array[18]);
					ydzbfdw.setQntqjdwc(null == ydzbfdw15Array[19] ? 0 : Double
							.valueOf(ydzbfdw15Array[19].toString()));
					ydzbfdw.setJqntqzzb((String) ydzbfdw15Array[20]);
					ydzbfdw.setSjdz(null == ydzbfdw15Array[21] ? 0 : Double
							.valueOf(ydzbfdw15Array[21].toString()));
					ydzbfdw.setJsjdzzb((String) ydzbfdw15Array[22]);
					ydzbfdw.setNdjhz(null == ydzbfdw15Array[23] ? 0 : Double
							.valueOf(ydzbfdw15Array[23].toString()));
					ydzbfdw.setNdljwcz(null == ydzbfdw15Array[24] ? 0 : Double
							.valueOf(ydzbfdw15Array[24].toString()));
					ydzbfdw.setNdljjhz(null == ydzbfdw15Array[25] ? 0 : Double
							.valueOf(ydzbfdw15Array[25].toString()));
					ydzbfdw.setNdwcl((String) ydzbfdw15Array[26]);
					ydzbfdw.setQntqlj(null == ydzbfdw15Array[27] ? 0 : Double
							.valueOf(ydzbfdw15Array[27].toString()));
					ydzbfdw.setJqntqljzzb((String) ydzbfdw15Array[28]);
					ydzbfdw.setRowid(null == ydzbfdw15Array[29] ? 0 : Integer
							.valueOf(ydzbfdw15Array[29].toString()));
					ydzbfdw.setName((String) ydzbfdw15Array[30]);
					ydzbfdwDao.create(ydzbfdw);
				}
				cur.add(cur.MONTH, 1);
			}
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public YDZBFDWDao getYdzbfdwDao() {
		return ydzbfdwDao;
	}

	public void setYdzbfdwDao(YDZBFDWDao ydzbfdwDao) {
		this.ydzbfdwDao = ydzbfdwDao;
	}

	public BL15Dao getBl15Dao() {
		return bl15Dao;
	}

	public void setBl15Dao(BL15Dao bl15Dao) {
		this.bl15Dao = bl15Dao;
	}

}
