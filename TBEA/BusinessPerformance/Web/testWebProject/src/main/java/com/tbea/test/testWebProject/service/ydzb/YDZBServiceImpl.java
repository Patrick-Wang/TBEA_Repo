package com.tbea.test.testWebProject.service.ydzb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.dao.ydzb.YDZBDao;
import com.tbea.test.testWebProject.model.entity.XJL;
import com.tbea.test.testWebProject.model.entity.YDZBBean;

//5	沈变公司
//6	衡变公司
//7	新变厂
//8	天变公司
//9	鲁缆公司
//10	新缆厂
//11	德缆公司
//23	进出口公司
//25	能动公司
//27	众和公司
//29	新能源
//30	新特能源公司
//66	天池能源
//70	国际工程公司
//74	中疆物流

@Service
@Transactional("transactionManager2")
public class YDZBServiceImpl implements YDZBService {

	@Autowired
	private YDZBDao ydzbDao;
	private static Map<String, Integer> xhMap = new HashMap<String, Integer>();
	private static Map<String, Integer> zbMap = new HashMap<String, Integer>();

	static {
		xhMap.put("2", 0);
		xhMap.put("6", 1);
		xhMap.put("9", 2);
		xhMap.put("10", 3);
		xhMap.put("11", 4);
		xhMap.put("12", 5);
		xhMap.put("13", 6);
		xhMap.put("8", 7);
		xhMap.put("7", 8);
		xhMap.put("17", 9);
		xhMap.put("18", 10);
		xhMap.put("19", 11);
		xhMap.put("25", 12);
		xhMap.put("26", 13);
		xhMap.put("27", 14);
		xhMap.put("28", 15);
		xhMap.put("29", 16);
	}

	static {
		zbMap.put("2", 0);
		zbMap.put("3", 1);
		zbMap.put("6", 2);
		zbMap.put("9", 3);
		zbMap.put("11", 4);
	}

	@Override
	public String[][] getHzb_zbhzData(Date d) {
		String[][] result = new String[xhMap.size()][11];
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<YDZBBean> ydzbs = ydzbDao.getYDZB_V2(cal, Company.getAll());
		int index = 0;
		for (YDZBBean ydzb : ydzbs) {
			if (ydzb != null && xhMap.get(ydzb.getXh()) != null) {
				index = xhMap.get(ydzb.getXh());
				result[index][0] = ydzb.getByjh();
				result[index][1] = ydzb.getBywc();
				result[index][2] = ydzb.getJhwcl();
				result[index][3] = ydzb.getJdlj();
				result[index][4] = ydzb.getJdjhwcl();
				result[index][5] = ydzb.getNdlj();
				result[index][6] = ydzb.getNdjhwcl();
				result[index][7] = ydzb.getQntq();
				result[index][8] = ydzb.getJqntqzzb();
				result[index][9] = ydzb.getQntqlj();
				result[index][10] = ydzb.getJqntqljzzb();
			}
		}
		return result;
	}
	
	private void fillZbhzData(String[][] data, List<YDZBBean> ydzbs, int base, int len) {
		int row = 0;
		if (ydzbs == null){
			return;
		}
		for (YDZBBean ydzb : ydzbs) {
			if (ydzb != null && zbMap.get(ydzb.getXh()) != null) {
				row = zbMap.get(ydzb.getXh());
				data[row * len + base][0] = ydzb.getNdjh();
				data[row * len + base][1] = ydzb.getByjh();
				data[row * len + base][2] = ydzb.getBywc();
				data[row * len + base][3] = ydzb.getJhwcl();
				data[row * len + base][4] = ydzb.getJdlj();
				data[row * len + base][5] = ydzb.getJdjhwcl();
				data[row * len + base][6] = ydzb.getNdlj();
				data[row * len + base][7] = ydzb.getNdjhwcl();
				data[row * len + base][8] = ydzb.getQntq();
				data[row * len + base][9] = ydzb.getJqntqzzb();
				data[row * len + base][10] = ydzb.getQntqlj();
				data[row * len + base][11] = ydzb.getJqntqljzzb();
			}
		}
	}
	

	@Override
	public String[][] getGcy_zbhzData(Date d) {
		String[][] result = new String[7 * zbMap.size()][12];
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<List<YDZBBean>> zbs = new ArrayList<List<YDZBBean>>(7);
		zbs.add(ydzbDao.getYDZB_V2(cal, Company.getSbdcy()));
		zbs.add(ydzbDao.getYDZB_V2(cal, Company.getXnycy()));
		zbs.add(ydzbDao.getYDZB_V2(cal, Company.getNycy()));
		zbs.add(ydzbDao.getYDZB_V2(cal, Company.getGcl()));
		zbs.add(ydzbDao.getYDZB_V2(cal,
				new Array(Company.getSbdcy()).join(Company.getXnycy())
						.join(Company.getNycy()).join(Company.getGcl())
						.toArray()));
		zbs.add(ydzbDao.getYDZB_V2(cal, Company.ZH));
		zbs.add(ydzbDao.getYDZB_V2(cal, Company.getAll()));
		
		for (int i = 0, len = zbs.size(); i < len; ++i){
			fillZbhzData(result, zbs.get(i), i, 7);
		}
		
		return result;
	}

	@Override
	public String[][] getGdw_zbhzData(Date d) {
		String[][] result = new String[21 * zbMap.size()][12];
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<List<YDZBBean>> zbs = new ArrayList<List<YDZBBean>>(21);
		List<int[]> qyGroups = new ArrayList<int[]>();
		qyGroups.add(Company.getSbdcy());
		qyGroups.add(Company.getXnycy());
		qyGroups.add(Company.getNycy());
		qyGroups.add(Company.getGcl());	
		for (int[] qyGroup : qyGroups){
			for (int qy : qyGroup){
				zbs.add(ydzbDao.getYDZB(cal, qy));
			}
			zbs.add(ydzbDao.getYDZB(cal, qyGroup));
		}
		
		zbs.add(ydzbDao.getYDZB(cal, new Array(Company.getSbdcy()).join(Company.getXnycy())
				.join(Company.getNycy()).join(Company.getGcl())
				.toArray()));
		
		zbs.add(ydzbDao.getYDZB(cal, Company.ZH));
		
		zbs.add(ydzbDao.getYDZB(cal, Company.getAll()));
		
		for (int i = 0, len = zbs.size(); i < len; ++i){
			fillZbhzData(result, zbs.get(i), i, 21);
		}
		
		return result;
	}

	@Override
	public String[][] getXjlrbData(Date d) {
	
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<XJL> xjls = ydzbDao.getXJL(cal);
		String[][] result = new String[xjls.size()][10];
		int i = 0;
		for (XJL xjl : xjls){
			result[i][0] = xjl.getDrlr();
			result[i][1] = xjl.getDylr();
			result[i][2] = xjl.getDnlr();
			result[i][3] = xjl.getDrlc();
			result[i][4] = xjl.getDylc();
			result[i][5] = xjl.getDnlc();
			result[i][6] = xjl.getDrjll();
			result[i][7] = xjl.getDyjll();
			result[i][9] = xjl.getBytzs();
			result[i][8] = xjl.getDnjll();
			++i;
		}
		return result;
	}

	@Override
	public String[][] getYszkrb_qkbData(Date d) {
		// TODO Auto-generated method stub
		return null;
	}

}
