package com.tbea.test.testWebProject.service.blhtdqqkhz;

import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.dao.blhtdqqkhz.BLHTDQQKHZDao;
import com.tbea.test.testWebProject.model.entity.BLHTDQQKHZ;

@Service
@Transactional("transactionManager2")
public class BLHTDQQKHZServiceImpl implements BLHTDQQKHZService {

	@Autowired
	private BLHTDQQKHZDao blDao;

	@Override
	public BLHTDQQKHZ getBLById(int id) {
		return blDao.getById(id);
	}
	
	
	private int compareDateMonth(Date d1, Date d2){
		Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
		Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);
        int delta = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + (cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH));
        return d1.after(d2)? delta : -delta;
	}
	
	//return value format
	//current year's bl ye
	//	[...Dqfkhfxsblye, Dqfkhfxsblfs (refer to BLHTDQQKHZ entity)...]
	//current year's bl fs
	//	[...Dqfkhfxsblye, Dqfkhfxsblfs (refer to BLHTDQQKHZ entity)...]
	public String[][] getBlhtdqqk(Date date){

    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
	       
        List<BLHTDQQKHZ> list = blDao.getBlAfterDate(cal);

		
		String[][] result = new String[2][12]; 
		
		BLHTDQQKHZ fourMonthLaterbl = null;
		int monthDelta;
		for (BLHTDQQKHZ bl : list){
			monthDelta = compareDateMonth((Date)Util.valueOf(bl.getNy()), date);
			if (monthDelta == -1){//n-1 month
				result[0][0] = bl.getDqfkhfxsblye() + "";
				result[0][1] = bl.getDqkhfxsblye() + "";
				result[1][0] = bl.getDqfkhfxsblfs() + "";
				result[1][1] = bl.getDqkhfxsblfs() + "";
			} else if (monthDelta >= 0 && monthDelta < 4){//n, n+1, n+2, n+3 month
				result[0][0 + 2 * (monthDelta + 1)] = bl.getDqblje() + "";
				result[0][1 + 2 * (monthDelta + 1)] = bl.getDqblzyhkje()+ "";
				result[1][0 + 2 * (monthDelta + 1)] = bl.getDqblfs() + "";
				result[1][1 + 2 * (monthDelta + 1)] = bl.getDqblzyhkfs() + "";
			} else {// >= n+4 month
				if (fourMonthLaterbl == null){
					fourMonthLaterbl = new BLHTDQQKHZ();
				}
				fourMonthLaterbl.setDqblje(bl.getDqblje() + fourMonthLaterbl.getDqblje());
				fourMonthLaterbl.setDqblzyhkje(bl.getDqblzyhkje() + fourMonthLaterbl.getDqblzyhkje());
				fourMonthLaterbl.setDqblfs(bl.getDqblfs() + fourMonthLaterbl.getDqblfs());
				fourMonthLaterbl.setDqblzyhkfs(bl.getDqblzyhkfs() + fourMonthLaterbl.getDqblzyhkfs());	
			}
		}
		
		if (fourMonthLaterbl != null){
			result[0][10] = fourMonthLaterbl.getDqblje() + "";
			result[0][11] = fourMonthLaterbl.getDqblzyhkje()+ "";
			result[1][10] = fourMonthLaterbl.getDqblfs() + "";
			result[1][11] = fourMonthLaterbl.getDqblzyhkfs() + "";
		} else{
			result[0][10] = "--";
			result[0][11] = "--";
			result[1][10] = "--";
			result[1][11] = "--";
		}
		
		return result;
	}
	
	
	//return value format
	//	[... previous year's blye from January to current month...]
	//	[... current year's blye from January to current month...]
	public String[][] getBlyeqs(Date date){

    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);

		List<BLHTDQQKHZ> list = blDao.getBltbbh(cal);
		
		String[][] result = new String[2][cal.get(Calendar.MONTH) + 1]; 

		Calendar time = Calendar.getInstance();
		for (BLHTDQQKHZ bl : list){
			time.setTime(Util.valueOf(bl.getNy()));
			if (time.get(Calendar.YEAR) < cal.get(Calendar.YEAR)){
				result[0][time.get(Calendar.MONTH)] =  "" + (bl.getDqfkhfxsblye() + bl.getDqkhfxsblye());
			}
			else{
				result[1][time.get(Calendar.MONTH)] =  "" + (bl.getDqfkhfxsblye() + bl.getDqkhfxsblye());
			}
		}
		
		return result;
	}

}
