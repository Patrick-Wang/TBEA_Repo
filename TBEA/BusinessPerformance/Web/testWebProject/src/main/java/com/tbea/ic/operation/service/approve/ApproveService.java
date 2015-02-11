package com.tbea.ic.operation.service.approve;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface ApproveService {

	List<List<String[]>> getZb(List<Company> comps, Date date, ZBType entryType);

	boolean hasApprovePlanPermission(Account account);

	boolean hasApprovePredictPermission(Account account);

	boolean approveNdjhZb(List<Company> comps, Date date);

	boolean unapproveNdjhZb(List<Company> comps, Date date);

	boolean approveYj20Zb(List<Company> comps, Date date);

	boolean approveYj28Zb(List<Company> comps, Date date);

	boolean approveSjZb(List<Company> comps, Date date);

	boolean approveYdjdZb(List<Company> comps, List<Date> dateList);

	boolean unapproveSjZb(List<Company> comps, Date date);

	boolean unapproveYj28Zb(List<Company> comps, Date date);

	boolean unapproveYj20Zb(List<Company> comps, Date date);

	boolean unapproveYdjdZb(List<Company> comps, List<Date> date);

	List<Integer> getCompanies(ZBType approveType);

	List<Company> getValidSjCompanies(Account account);

	List<Company> getValidJhCompanies(Account account);
}
