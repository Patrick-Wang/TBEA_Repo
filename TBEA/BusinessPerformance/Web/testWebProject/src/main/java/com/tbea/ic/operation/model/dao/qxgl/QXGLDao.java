package com.tbea.ic.operation.model.dao.qxgl;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.QXGL;

public interface QXGLDao {

	Long getJhzlrCount(Account account);

	Long getSjzlrCount(Account account);

	Long getJhzshCount(Account account);

	Long getSjzshCount(Account account);

	List<QXGL> getSjzlr(Account account);

	List<QXGL> getJhzlr(Account account);
	
	List<QXGL> getSjzsh(Account account);

	List<QXGL> getJhzsh(Account account);

	Long getQxglCount(Account account, Company company);
}
