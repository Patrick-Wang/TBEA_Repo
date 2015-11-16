package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

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



@Service
@Transactional("transactionManager")
public class DailyReportServiceImpl implements DailyReportService{

	@Autowired
	ExtendAuthorityDao extendAuthDao;
	
	@Autowired
	YSDAILYDao ysdailyDao;

	@Override
	public boolean hasYszkAuthority(Account account) {
		int count = extendAuthDao.getAuthorityCount(account, 1);
		return count > 0;
	}

	@Override
	public ErrorCode submitYszk(Account account, Date date, JSONArray jData) {
		List<ExtendAuthority> eas = extendAuthDao.getAuthority(account, 1);
		ErrorCode code = ErrorCode.OK;
		if (eas.isEmpty()){
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
	
	
}
