package com.tbea.ic.operation.service.jygk.zzy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.SystemExtendAuthDao;
import com.tbea.ic.operation.model.entity.ExtendAuthority;
import com.tbea.ic.operation.model.entity.jygk.Account;


@Service
@Transactional("transactionManager")
public class SystemExtendAuthServiceImpl implements SystemExtendAuthService{

	@Autowired
	SystemExtendAuthDao systemExtendAuthDao;
	
	@Override
	public List<DwxxDto> getJygkZzyDwxxListView(Account account) {
		List<DwxxDto> dwxxList=new ArrayList<DwxxDto>();
		List<ExtendAuthority> extendAuthorityList=systemExtendAuthDao.getDataListByAccAuthType(account.getId(), 3);
		for(ExtendAuthority e: extendAuthorityList){
			DwxxDto d=new DwxxDto();
			d.setId(e.getDwxx().getId());
			d.setName(e.getDwxx().getName());
			dwxxList.add(d);
		}
		return dwxxList;
	}
	@Override
	public List<DwxxDto> getJygkZzyDwxxListLr(Account account) {		
		List<DwxxDto> dwxxList=new ArrayList<DwxxDto>();
		List<ExtendAuthority> extendAuthorityList=systemExtendAuthDao.getDataListByAccAuthType(account.getId(), 2);
		for(ExtendAuthority e: extendAuthorityList){
			DwxxDto d=new DwxxDto();
			d.setId(e.getDwxx().getId());
			d.setName(e.getDwxx().getName());
			dwxxList.add(d);
		}
		return dwxxList;
	}
}
