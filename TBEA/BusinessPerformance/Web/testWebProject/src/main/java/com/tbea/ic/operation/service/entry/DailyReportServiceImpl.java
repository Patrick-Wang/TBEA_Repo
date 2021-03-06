package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.model.entity.Yszkrb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.authority.ExtendAuthorityDao;
import com.tbea.ic.operation.model.dao.ysdaily.YSDAILYDao;
import com.tbea.ic.operation.model.entity.ExtendAuthority;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.YSDAILY;
import com.tbea.ic.operation.model.entity.jygk.YSDAILYPK;
import com.util.tools.DateUtil;

import net.sf.json.JSONArray;



@Service
@Transactional("transactionManager")
public class DailyReportServiceImpl implements DailyReportService{

	@Autowired
	ExtendAuthorityDao extendAuthDao;
	
	@Autowired
	YSDAILYDao ysdailyDao;

	@Override
	public boolean hasYszkAuthority(Account account) {
		int count = extendAuthDao.getAuthorityCount(account, ExtendAuthority.AuthType.YSZKDailyReportEntry.ordinal());
		return count > 0;
	}

	@Override
	public ErrorCode submitYszk(Account account, Date date, JSONArray jData) {
		List<ExtendAuthority> eas = extendAuthDao.getAuthority(account, ExtendAuthority.AuthType.YSZKDailyReportEntry.ordinal());
		ErrorCode code = ErrorCode.OK;
		if (!eas.isEmpty()){
			ExtendAuthority ea = eas.get(0);
			DWXX dwxx = ea.getDwxx();
			YSDAILY daily = ysdailyDao.getYsdaily(date, dwxx);
			if (daily == null){
				daily = new YSDAILY();
				YSDAILYPK key = new YSDAILYPK();
				key.setDate(date);
				key.setDwxx(dwxx);
				daily.setKey(key);
			}
			daily.setWithdrawalFundsTargetMonth(Util.toDouble(jData.getString(0)));
			daily.setWithdrawalPlan(Util.toDouble(jData.getString(1)));
			daily.setWithdrawalToday(Util.toDouble(jData.getString(2)));
			daily.setKjysWithdrawalToday(Util.toDouble(jData.getString(3)));
			daily.setQbbcMoney(Util.toDouble(jData.getString(4)));
			daily.setZqbcMoney(Util.toDouble(jData.getString(5)));
			daily.setBalanceAccount(Util.toDouble(jData.getString(6)));
			ysdailyDao.update(daily);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			while (maxDay > 0){
				Date tmpDate = DateUtil.toDate(cal);
				daily = ysdailyDao.getYsdaily(tmpDate, dwxx);
				if (daily == null){
					daily = new YSDAILY();
					YSDAILYPK key = new YSDAILYPK();
					key.setDate(tmpDate);
					key.setDwxx(dwxx);
					daily.setKey(key);
				}
				daily.setWithdrawalFundsTargetMonth(Util.toDouble(jData.getString(0)));
				daily.setWithdrawalPlan(Util.toDouble(jData.getString(1)));
				ysdailyDao.update(daily);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				--maxDay;
			}
		}else{
			code = ErrorCode.HAVE_NO_RIGHT;
		}
		return code;
	}

	/**
	 * 录入提交操作
	 * @param account
	 * @param date
	 * @param jData
	 * @return
	 */
	@Override
	public ErrorCode submitYszkLr(Account account, Date date, JSONArray jData) {
		List<ExtendAuthority> eas = extendAuthDao.getAuthority(account, ExtendAuthority.AuthType.YSZKDailyReportEntry.ordinal());
		ErrorCode code = ErrorCode.OK;
		for(int i = 0; i < jData.size(); i++){
			if("".equals(jData.getString(i)) || jData.getString(i) == null){
				return ErrorCode.NULL_STRING;
			}
		}
		if (!eas.isEmpty()){
			ExtendAuthority ea = eas.get(0);
			DWXX dwxx = ea.getDwxx();
			Yszkrb ys = ysdailyDao.getYsdailyRB(date, dwxx);
			if(ys == null){
				ys = new Yszkrb();
				String[] dateStrArr = date.toString().split("-");
				int lrnf = Integer.parseInt(dateStrArr[0]);
				int lryf = Integer.parseInt(dateStrArr[1]);
				int lrrq = Integer.parseInt(dateStrArr[2]);
				ys.setLrnf(lrnf);
				ys.setLryf(lryf);
				ys.setLrrq(lrrq);
				ys.setDwID(dwxx.getId());
			}
			ys.setYszkzb(Util.toDouble(jData.getString(0)));
			ys.setHkjh(Util.toDouble(jData.getString(1)));
			ys.setQbkx(Util.toDouble(jData.getString(2)));
			ys.setZqkx(Util.toDouble(jData.getString(3)));
			ys.setSyysye(Util.toDouble(jData.getString(4)));
			ys.setJrxzyszk(Util.toDouble(jData.getString(5)));
			ys.setJrhk(Util.toDouble(jData.getString(6)));
			ys.setLjkjyshk(Util.toDouble(jData.getString(7)));
			ysdailyDao.merge(ys);

			List<Yszkrb> yszkrbList = ysdailyDao.getYszkrbCountByNfYf(date,dwxx);
			if(!yszkrbList.isEmpty()){
				for(int i = 0; i < yszkrbList.size(); i++){
					Yszkrb y = yszkrbList.get(i);
					y.setYszkzb(Util.toDouble(jData.getString(0)));
					y.setHkjh(Util.toDouble(jData.getString(1)));
					y.setQbkx(Util.toDouble(jData.getString(2)));
					y.setZqkx(Util.toDouble(jData.getString(3)));
					y.setSyysye(Util.toDouble(jData.getString(4)));
					ysdailyDao.merge(y);
				}
			}
		}else{
			code = ErrorCode.HAVE_NO_RIGHT;
		}
		return code;
	}

	@Override
	public List<String[]> getYszkData(Account account, Date date) {
		List<String[]> result = new ArrayList<String[]>();
		result.add(new String[7]);
		List<ExtendAuthority> eas = extendAuthDao.getAuthority(account, 1);
		if (!eas.isEmpty()){
			ExtendAuthority ea = eas.get(0);
			DWXX dwxx = ea.getDwxx();
			YSDAILY daily = ysdailyDao.getYsdaily(date, dwxx);
			if (null != daily)
			{
				result.get(0)[0] = "" + daily.getWithdrawalFundsTargetMonth();
				result.get(0)[1] = "" + daily.getWithdrawalPlan();
				result.get(0)[2] = "" + daily.getWithdrawalToday();
				result.get(0)[3] = "" + daily.getKjysWithdrawalToday();
				result.get(0)[4] = "" + daily.getQbbcMoney();
				result.get(0)[5] = "" + daily.getZqbcMoney();
				result.get(0)[6] = "" + daily.getBalanceAccount();
			}
		}
		return result;
	}

	/**
	 * 得到已经录入的月录入项
	 * @param account
	 * @param date
	 * @return
	 */
	@Override
	public List<String[]> getYszkLRData(Account account, Date date) {
		List<String[]> result = new ArrayList<String[]>();
		result.add(new String[8]);
		List<ExtendAuthority> eas = extendAuthDao.getAuthority(account, 1);
		if (!eas.isEmpty()){
			ExtendAuthority ea = eas.get(0);
			DWXX dwxx = ea.getDwxx();
			Yszkrb ys = ysdailyDao.getYsdailyRBLy(date, dwxx);
			if (null != ys)
			{
				result.get(0)[0] = "" + ys.getYszkzb();
				result.get(0)[1] = "" + ys.getHkjh();
				result.get(0)[2] = "" + ys.getQbkx();
				result.get(0)[3] = "" + ys.getZqkx();
				result.get(0)[4] = "" + ys.getSyysye();
				result.get(0)[5] = "" + ys.getJrxzyszk();
				result.get(0)[6] = "" + ys.getJrhk();
				result.get(0)[7] = "" + ys.getLjkjyshk();
			}
		}
		return result;
	}

	@Override
	public boolean hasJYAnalysisEntryAuthority(Account account) {
		int count = extendAuthDao.getAuthorityCount(account, ExtendAuthority.AuthType.JYAnalysisEntry.ordinal());
		return count > 0;
	}

	@Override
	public boolean hasJYAnalysisLookupAuthority(Account account) {
		int count = extendAuthDao.getAuthorityCount(account, ExtendAuthority.AuthType.JYAnalysisLookup.ordinal());
		return count > 0;
	}
	
	@Override
	public boolean hasYSZKDialyLookupAuthority(Account account) {
		int count = extendAuthDao.getAuthorityCount(account, ExtendAuthority.AuthType.YSZKDialyLookup.ordinal());
		return count > 0;
	}
	
	@Override
	public boolean hasXJLDialyLookupAuthority(Account account) {
		int count = extendAuthDao.getAuthorityCount(account, ExtendAuthority.AuthType.XJLDialyLookup.ordinal());
		return count > 0;
	}
	
	@Override
	public boolean hasJYEntryLookupAuthority(Account account) {
		int count = extendAuthDao.getAuthorityCount(account, ExtendAuthority.AuthType.JYEntryLookup.ordinal());
		return count > 0;
	}
	
}
