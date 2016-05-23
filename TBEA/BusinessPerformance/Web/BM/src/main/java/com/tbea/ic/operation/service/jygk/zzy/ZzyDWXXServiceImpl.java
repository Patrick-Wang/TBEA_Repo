package com.tbea.ic.operation.service.jygk.zzy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyDWXXDao;
import com.tbea.ic.operation.model.entity.jygk.DWXX;


@Service
@Transactional("transactionManager")
public class ZzyDWXXServiceImpl implements ZzyDWXXService{

	@Autowired
	ZzyDWXXDao zzyDWXXDao;
	
	@Override
	public DWXX getDwxx(int id){		
		return zzyDWXXDao.getDwxx(id);
	}	
}
