package com.tbea.ic.operation.service.entry;

import java.sql.Date;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.model.entity.Permission;
import com.tbea.ic.operation.model.entity.User;

public interface EntryService {

	String[][] getZb(Date date, User usr, ZBType entryType);

	boolean updateZb(Date date, User usr, JSONArray fromObject, ZBType entryType);

	boolean hasEntryPlanPermission(User usr);

	boolean hasEntryPredictPermission(User usr);

}
