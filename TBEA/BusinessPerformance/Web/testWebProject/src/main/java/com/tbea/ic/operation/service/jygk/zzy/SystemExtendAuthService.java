package com.tbea.ic.operation.service.jygk.zzy;

import java.util.List;

import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
public interface SystemExtendAuthService {
	public List<DwxxDto>  getJygkZzyDwxxListView(Account account);
	public List<DwxxDto>  getJygkZzyDwxxListLr(Account account);
}
