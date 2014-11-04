package com.tbea.test.testWebProject.service.ydzb;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.dao.ydzb.YDZBDao;
import com.tbea.test.testWebProject.model.entity.YDZBBean;

@Service
@Transactional("transactionManager2")
public class YDZBServiceImpl implements  YDZBService {

	@Autowired
	private YDZBDao ydzbDao;

	@Override
	public String[][] getYDZB(Calendar cal) {
		
		return null;
	}

	@Override
	public String[][] getHzb_zbhzData(Date d) {
		String[][] result = new String[33][9];
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<YDZBBean> ydzbs = ydzbDao.getYDZB(cal);
		int index = 0;
		for (YDZBBean ydzb : ydzbs){
			if (ydzb != null && Integer.parseInt(ydzb.getXh()) <= 33){
				index = Integer.parseInt(ydzb.getXh()) - 1;
				result[index][0] = ydzb.getByjh();
				result[index][1] = ydzb.getBywc();
				result[index][2] = ydzb.getJhwcl();
				result[index][3] = ydzb.getNdlj();
				result[index][4] = ydzb.getNdjhwcl();
				result[index][5] = ydzb.getQntq();
				result[index][6] = ydzb.getJqntqzzb();
				result[index][7] = ydzb.getQntqlj();
				result[index][8] = ydzb.getJqntqljzzb();
			}
		}
		return result;
	}

	@Override
	public String[][] getGcy_zbhzData(Date d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getGdw_zbhzData(Date d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getXjlrbData(Date d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getYszkrb_qkbData(Date d) {
		// TODO Auto-generated method stub
		return null;
	}



}
