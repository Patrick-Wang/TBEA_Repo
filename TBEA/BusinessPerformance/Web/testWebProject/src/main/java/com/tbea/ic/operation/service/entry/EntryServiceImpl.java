package com.tbea.ic.operation.service.entry;

import java.sql.Date;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.model.entity.Permission;
import com.tbea.ic.operation.model.entity.User;

@Service
@Transactional("transactionManager")
public class EntryServiceImpl implements EntryService{

	@Override
	public String[][] getZb(Date date, User usr, ZBType entryType) {
		return 
				 new String[][]{
				 {"1.00", "2.00", "3.00", "4.00", "4.00"},
				 {"1.00", "2.00", "3.00", "4.00", "4.00"},
				 {"1.00", "2.00", "3.00", "4.00", "4.00"}
				 };
	}

	@Override
	public boolean updateZb(Date date, User usr, JSONArray fromObject,
			ZBType entryType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasEntryPlanPermission(User usr) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean hasEntryPredictPermission(User usr) {
		// TODO Auto-generated method stub
		return true;
	}

}
