package com.tbea.ic.operation.service.approve;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.model.entity.User;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface ApproveService {

	boolean unapproveZb(Date date, User usr, JSONArray fromObject,
			ZBType entryType);

	boolean approveZb(Date date, User usr, JSONArray fromObject,
			ZBType entryType);

	List<String[][]> getZb(Date date, Account account, ZBType entryType);

	boolean hasApprovePlanPermission(Account account);

	boolean hasApprovePredictPermission(Account account);

}
