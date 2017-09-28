package com.tbea.ic.operation.service.blhtdqqkhz;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.blhtdqqkhz.BLHTDQQKHZDao;
import com.tbea.ic.operation.model.entity.BLHTDQQKHZ;
import com.util.tools.DateUtil;

@Service
@Transactional("transactionManager")
public class BLHTDQQKHZServiceImpl implements BLHTDQQKHZService {

	@Autowired
	private BLHTDQQKHZDao blDao;

	
	private int compareDateMonth(Date d1, Date d2){
		Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
		Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);
        int delta = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + (cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH));
        return delta;
	}
	
	
	
	private String[][] setBlhtdqqk(String[][] result, Date date, List<BLHTDQQKHZ> list){		
		BLHTDQQKHZ fourMonthLaterbl = null;
		int monthDelta;
		for (BLHTDQQKHZ bl : list){
			monthDelta = compareDateMonth((Date)DateUtil.fromMonth1(bl.getNy()), date);
			if (monthDelta == -1){//n-1 month
				
				result[0][0] = 
						Util.toDouble(result[0][0]) + 
						Util.valueOf(bl.getDqfkhfxsblye()) + "";
				result[0][1] = 
						Util.toDouble(result[0][1]) + 
						Util.valueOf(bl.getDqkhfxsblye()) + "";
				result[1][0] = 
						Util.toDouble(result[1][0]) + 
						Util.valueOf(bl.getDqfkhfxsblfs()) + "";
				result[1][1] = 
						Util.toDouble(result[1][1]) + 
						Util.valueOf(bl.getDqkhfxsblfs()) + "";
				
			} else if (monthDelta >= 0 && monthDelta < 4){//n, n+1, n+2, n+3 month
				result[0][0 + 2 * (monthDelta + 1)] = 
						Util.toDouble(result[0][0 + 2 * (monthDelta + 1)]) + 
						Util.valueOf(bl.getDqblje()) + "";

				result[0][1 + 2 * (monthDelta + 1)] = 
						Util.toDouble(result[0][1 + 2 * (monthDelta + 1)]) + 
						Util.valueOf(bl.getDqblzyhkje()) + "";

				result[1][0 + 2 * (monthDelta + 1)] = 
						Util.toDouble(result[1][0 + 2 * (monthDelta + 1)]) + 
						Util.valueOf(bl.getDqblfs()) + "";

				result[1][1 + 2 * (monthDelta + 1)] = 
						Util.toDouble(result[1][1 + 2 * (monthDelta + 1)]) + 
						Util.valueOf(bl.getDqblzyhkfs()) + "";

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
			result[0][10] = 
					Util.toDouble(result[0][10]) + 
					Util.valueOf(fourMonthLaterbl.getDqblje()) + "";

			result[0][11] = 
					Util.toDouble(result[0][11]) + 
					Util.valueOf(fourMonthLaterbl.getDqblzyhkje()) + "";

			result[1][10] = 
					Util.toDouble(result[1][10]) + 
					Util.valueOf(fourMonthLaterbl.getDqblfs()) + "";

			result[1][11] = 
					Util.toDouble(result[1][11]) + 
					Util.valueOf(fourMonthLaterbl.getDqblzyhkfs()) + "";

		} else{
			result[0][10] = "--";
			result[0][11] = "--";
			result[1][10] = "--";
			result[1][11] = "--";
		}
		
		return result;
	}
	
	
	//return value format
	//current year's bl ye
	//	[...Dqfkhfxsblye, Dqfkhfxsblfs (refer to BLHTDQQKHZ entity)...]
	//current year's bl fs
	//	[...Dqfkhfxsblye, Dqfkhfxsblfs (refer to BLHTDQQKHZ entity)...]
	@Override
	public String[][] getBlhtdqqk(Date date, Company comp){
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
	       
        List<BLHTDQQKHZ> list = blDao.getBlAfterDate(cal, comp);

		String[][] result = new String[2][12]; 

		setBlhtdqqk(result, date, list);
		
		return result;
	}
	
	@Override
	public String[][] getBlhtdqqk(Date d, List<Company> comps) {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MONTH, -1);
        List<BLHTDQQKHZ> list = blDao.getBlAfterDate(cal, comps);
		String[][] result = new String[2][12];
		setBlhtdqqk(result, d, list);
		return result;
	}

	
	private void setBlyeqs(String[][] result, Calendar cal,
			List<BLHTDQQKHZ> list) {

		int curYear = 0;
		int month = 0;
		Calendar time = Calendar.getInstance();
		for (BLHTDQQKHZ bl : list) {
			time.setTime(DateUtil.fromMonth1(bl.getNy()));
			if (time.get(Calendar.YEAR) < cal.get(Calendar.YEAR)) {
				curYear = 0;
			} else {
				curYear = 1;
			}
			month = time.get(Calendar.MONTH);
			result[curYear][month] = Util.toDouble(result[curYear][month])
					+ bl.getDqfkhfxsblye() + bl.getDqkhfxsblye() + "";
		}

	}
	
	//return value format
	//	[... previous year's blye from January to current month...]
	//	[... current year's blye from January to current month...]
	@Override
	public String[][] getBlyeqs(Date date, Company comp){
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
		List<BLHTDQQKHZ> list = blDao.getBltbbh(cal, comp);
		String[][] result = new String[2][cal.get(Calendar.MONTH) + 1]; 
		setBlyeqs(result, cal, list);
		return result;
	}

	@Override
	public String[][] getBlyeqs(Date d, List<Company> comps) {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(d);
		List<BLHTDQQKHZ> list = blDao.getBltbbh(cal, comps);
		String[][] result = new String[2][cal.get(Calendar.MONTH) + 1]; 
		setBlyeqs(result, cal, list);
		return result;
	}
	
	@Override
	public Date getLatestDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date d = Date.valueOf(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-1");
		BLHTDQQKHZ blht = blDao.getLatestBl(d);
		if (null != blht){
			cal.setTime((Date) DateUtil.fromMonth1(blht.getNy()));
			cal.add(Calendar.MONTH, 1);
			return Date.valueOf(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-1");
		}
		return null;
	}
}
