package com.tbea.ic.operation.service.approve;

import java.sql.Date;



import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.Account;

import java.util.ArrayList;
import java.util.List;

interface OnGetStatus{
	ZBStatus onGetStatus(Date d, Company comp);
}


public class PreApprovedCompanyFilter {

	
	OnGetStatus mOnGetStatus;
	Account mAccount;
	Date mDate;


	
	public PreApprovedCompanyFilter(Account mAccount,
			Date mDate, OnGetStatus mOnGetStatus) {
		super();
		this.mOnGetStatus = mOnGetStatus;
		this.mAccount = mAccount;
		this.mDate = mDate;
	}


	//返回可以进行审核的公司列表
	public List<Company> filter(List<Company> comps){
		List<Company> retComps = new ArrayList<Company>();
		if (null == mOnGetStatus){
			return retComps;
		}
		
		for (int i = comps.size() - 1; i >= 0; --i){
			ZBStatus status = mOnGetStatus.onGetStatus(mDate, comps.get(i));
			if (mAccount.getRole() == 1){
				if (status == ZBStatus.SUBMITTED_2){
					retComps.add(comps.get(i));
				}
			}else{
				if (status == ZBStatus.SUBMITTED){
					retComps.add(comps.get(i));
				}
			}
		}
		return retComps;
	}
	
	//返回当前公司是否可以进行审核
	public boolean filter(Company comp){
		if (null == mOnGetStatus){
			return false;
		}
		
		ZBStatus status = mOnGetStatus.onGetStatus(mDate, comp);
		if (mAccount.getRole() == 1){
			if (status == ZBStatus.SUBMITTED_2){
				return true;
			}
		}else{
			if (status == ZBStatus.SUBMITTED){
				return true;
			}
		}
		return false;
	}
}
