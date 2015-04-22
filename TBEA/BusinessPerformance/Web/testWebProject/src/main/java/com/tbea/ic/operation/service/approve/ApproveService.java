package com.tbea.ic.operation.service.approve;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface ApproveService {

	List<List<String[]>> getZb(Account account, List<Company> comps, Date date, ZBType entryType);

	boolean hasApprovePlanPermission(Account account);

	boolean hasApprovePredictPermission(Account account);

	boolean approveNdjhZb(Account account, List<Company> comps, Date date);

	boolean unapproveNdjhZb(Account account, List<Company> comps, Date date);

	boolean approveYj20Zb(Account account, List<Company> comps, Date date);

	boolean approveYj28Zb(Account account, List<Company> comps, Date date);

	boolean approveSjZb(Account account, List<Company> comps, Date date);

	boolean approveYdjdZb(Account account, List<Company> comps, List<Date> dateList);

	boolean unapproveSjZb(Account account, List<Company> comps, Date date);

	boolean unapproveYj28Zb(Account account, List<Company> comps, Date date);

	boolean unapproveYj20Zb(Account account, List<Company> comps, Date date);

	boolean unapproveYdjdZb(Account account, List<Company> comps, List<Date> date);

	List<Integer> getCompanies(ZBType approveType);

	List<Company> getValidSjCompanies(Account account);

	List<Company> getValidJhCompanies(Account account);
}
