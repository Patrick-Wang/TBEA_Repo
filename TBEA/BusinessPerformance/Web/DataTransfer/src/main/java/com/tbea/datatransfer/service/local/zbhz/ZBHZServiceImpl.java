package com.tbea.datatransfer.service.local.zbhz;

import java.util.Calendar;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.zbhz.ZBHZDao;
import com.tbea.datatransfer.model.dao.yszk15.bl.BL15Dao;
import com.tbea.datatransfer.model.entity.local.ZBHZ;

@Transactional("transactionManager")
public class ZBHZServiceImpl implements ZBHZService {

	private ZBHZDao zbhzDao;

	private BL15Dao bl15Dao;

	@Override
	public boolean importZBHZ() {
		boolean result = false;
		try {
			zbhzDao.truncateZBHZ();
			Calendar cur = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			cur.set(2013, 1 - 1, 1);
			end.setTimeInMillis(System.currentTimeMillis());
			List<Object[]> zbhz15List = null;
			Integer year = null;
			Integer month = null;
			while (!cur.after(end)) {
				year = cur.get(Calendar.YEAR);
				month = cur.get(Calendar.MONTH) + 1;
				zbhz15List = bl15Dao.getAllZBHZ(year, month);
				ZBHZ zbhz = null;
				for (Object[] zbhz15Array : zbhz15List) {
					zbhz = new ZBHZ();
					zbhz.setNy(year + String.format("%02d", month));
					zbhz.setZbbh(null == zbhz15Array[1] ? 0 : Integer
							.valueOf(zbhz15Array[1].toString()));
					zbhz.setZbmc((String) zbhz15Array[2]);
					zbhz.setByjhz(null == zbhz15Array[3] ? 0 : Double
							.valueOf(zbhz15Array[3].toString()));
					zbhz.setBywcz(null == zbhz15Array[4] ? 0 : Double
							.valueOf(zbhz15Array[4].toString()));
					zbhz.setJhwcl((String) zbhz15Array[5]);
					zbhz.setSywcz(null == zbhz15Array[6] ? 0 : Double
							.valueOf(zbhz15Array[6].toString()));
					zbhz.setJsyzzb((String) zbhz15Array[7]);
					zbhz.setSjdpjz(null == zbhz15Array[8] ? 0 : Double
							.valueOf(zbhz15Array[8].toString()));
					zbhz.setJjzzzb((String) zbhz15Array[9]);
					zbhz.setQnpjz(null == zbhz15Array[10] ? 0 : Double
							.valueOf(zbhz15Array[10].toString()));
					zbhz.setJjzzzb1((String) zbhz15Array[11]);
					zbhz.setQntqz(null == zbhz15Array[12] ? 0 : Double
							.valueOf(zbhz15Array[12].toString()));
					zbhz.setJtqzzb((String) zbhz15Array[13]);
					zbhz.setJdjh(null == zbhz15Array[14] ? 0 : Double
							.valueOf(zbhz15Array[14].toString()));
					zbhz.setJdlj(null == zbhz15Array[15] ? 0 : Double
							.valueOf(zbhz15Array[15].toString()));
					zbhz.setJdwcl((String) zbhz15Array[16]);
					zbhz.setQntqjdwc(null == zbhz15Array[17] ? 0 : Double
							.valueOf(zbhz15Array[17].toString()));
					zbhz.setJqntqzzb((String) zbhz15Array[18]);
					zbhz.setSjdz(null == zbhz15Array[19] ? 0 : Double
							.valueOf(zbhz15Array[19].toString()));
					zbhz.setJsjdzzb((String) zbhz15Array[20]);
					zbhz.setNdjhz(null == zbhz15Array[21] ? 0 : Double
							.valueOf(zbhz15Array[21].toString()));
					zbhz.setNdljwcz(null == zbhz15Array[22] ? 0 : Double
							.valueOf(zbhz15Array[22].toString()));
					zbhz.setNdwcl((String) zbhz15Array[23]);
					zbhz.setQntqlj(null == zbhz15Array[24] ? 0 : Double
							.valueOf(zbhz15Array[24].toString()));
					zbhz.setJqntqljzzb((String) zbhz15Array[25]);
					zbhz.setRowid(null == zbhz15Array[26] ? 0 : Integer
							.valueOf(zbhz15Array[26].toString()));
					zbhzDao.create(zbhz);
				}
				cur.add(cur.MONTH, 1);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println(result);
		}
		return result;
	}

	public ZBHZDao getZbhzDao() {
		return zbhzDao;
	}

	public void setZbhzDao(ZBHZDao zbhzDao) {
		this.zbhzDao = zbhzDao;
	}

	public BL15Dao getBl15Dao() {
		return bl15Dao;
	}

	public void setBl15Dao(BL15Dao bl15Dao) {
		this.bl15Dao = bl15Dao;
	}

}
