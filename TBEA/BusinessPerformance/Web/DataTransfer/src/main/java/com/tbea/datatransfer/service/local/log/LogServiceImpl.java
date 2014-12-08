package com.tbea.datatransfer.service.local.log;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tbea.datatransfer.model.dao.local.ydzbfdw.YDZBFDWDao;
import com.tbea.datatransfer.model.dao.yszk15.bl.BL15Dao;
import com.tbea.datatransfer.model.entity.local.YDZBFDW;

@Transactional("transactionManager")
public class LogServiceImpl implements LogService {

	private YDZBFDWDao ydzbfdwDao;

	private BL15Dao bl15Dao;

	private Map<Integer, Integer> qybhMap;

	@Override
	public boolean logYDZBFDW() {
		boolean result = false;
		try {
			ydzbfdwDao.truncateYDZBFDW();
			Calendar end = Calendar.getInstance();
			end.setTimeInMillis(System.currentTimeMillis());
			List<Object[]> ydzbfdw15List = null;
			Integer year = null;
			Integer month = null;
			Integer newQYBH = null;
			year = end.get(Calendar.YEAR);
			month = end.get(Calendar.MONTH) + 1;
			ydzbfdw15List = bl15Dao.getAllYDZBFDW(year, month);
			YDZBFDW ydzbfdw = null;
			Object[] ydzbfdw15Array = ydzbfdw15List.get(0);
			System.out.println("Ny :" + year + String.format("%02d", month));
			newQYBH = qybhMap.get(ydzbfdw15Array[1]);
			System.out.println("Qybh :"
					+ (null == newQYBH ? Integer.valueOf(ydzbfdw15Array[1]
							.toString()) : newQYBH));
			System.out.println("Zbbh :"
					+ (null == ydzbfdw15Array[2] ? 0 : Integer
							.valueOf(ydzbfdw15Array[2].toString())));
			System.out.println("Zbmc :" + (String) ydzbfdw15Array[3]);
			System.out.println("Byjhz :" + (null == ydzbfdw15Array[4] ? 0
					: Double.valueOf(ydzbfdw15Array[4].toString())));
			System.out.println("Byyjwcz :" + (null == ydzbfdw15Array[5] ? 0
					: Double.valueOf(ydzbfdw15Array[5].toString())));
			System.out.println("Bywcz :" + (null == ydzbfdw15Array[6] ? 0
					: Double.valueOf(ydzbfdw15Array[6].toString())));
			System.out.println("Jhwcl :" + (String) ydzbfdw15Array[7]);
			System.out.println("Sywcz :" + (null == ydzbfdw15Array[8] ? 0
					: Double.valueOf(ydzbfdw15Array[8].toString())));
			System.out.println("Jsyzzb :" + (String) ydzbfdw15Array[9]);
			System.out.println("Sjdpjz :" + (null == ydzbfdw15Array[10] ? 0
					: Double.valueOf(ydzbfdw15Array[10].toString())));
			System.out.println("Jjzzzb :" + (String) ydzbfdw15Array[11]);
			System.out.println("Qnpjz :" + (null == ydzbfdw15Array[12] ? 0
					: Double.valueOf(ydzbfdw15Array[12].toString())));
			System.out.println("Jjzzzb1 :" + (String) ydzbfdw15Array[13]);
			System.out.println("Qntqz :" + (null == ydzbfdw15Array[14] ? 0
					: Double.valueOf(ydzbfdw15Array[14].toString())));
			System.out.println("Jtqzzb :" + (String) ydzbfdw15Array[15]);
			System.out.println("Jdjh :" + (null == ydzbfdw15Array[16] ? 0
					: Double.valueOf(ydzbfdw15Array[16].toString())));
			System.out.println("Jdlj :" + (null == ydzbfdw15Array[17] ? 0
					: Double.valueOf(ydzbfdw15Array[17].toString())));
			System.out.println("Jdwcl :" + (String) ydzbfdw15Array[18]);
			System.out.println("Qntqjdwc :" + (null == ydzbfdw15Array[19] ? 0
					: Double.valueOf(ydzbfdw15Array[19].toString())));
			System.out.println("Jqntqzzb :" + (String) ydzbfdw15Array[20]);
			System.out.println("Sjdz :" + (null == ydzbfdw15Array[21] ? 0
					: Double.valueOf(ydzbfdw15Array[21].toString())));
			System.out.println("Jsjdzzb :" + (String) ydzbfdw15Array[22]);
			System.out.println("Ndjhz :" + (null == ydzbfdw15Array[23] ? 0
					: Double.valueOf(ydzbfdw15Array[23].toString())));
			System.out.println("Ndljwcz :" + (null == ydzbfdw15Array[24] ? 0
					: Double.valueOf(ydzbfdw15Array[24].toString())));
			System.out.println("Ndljjhz :" + (null == ydzbfdw15Array[25] ? 0
					: Double.valueOf(ydzbfdw15Array[25].toString())));
			System.out.println("Ndwcl :" + (String) ydzbfdw15Array[26]);
			System.out.println("Qntqlj :" + (null == ydzbfdw15Array[27] ? 0
					: Double.valueOf(ydzbfdw15Array[27].toString())));
			System.out.println("Jqntqljzzb :" + (String) ydzbfdw15Array[28]);
			System.out.println("Rowid :" + (null == ydzbfdw15Array[29] ? 0
					: Integer.valueOf(ydzbfdw15Array[29].toString())));
			System.out.println("Name :" + (String) ydzbfdw15Array[30]);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			System.out.println(result);
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

	public Map<Integer, Integer> getQybhMap() {
		return qybhMap;
	}

	public void setQybhMap(Map<Integer, Integer> qybhMap) {
		this.qybhMap = qybhMap;
	}

}
