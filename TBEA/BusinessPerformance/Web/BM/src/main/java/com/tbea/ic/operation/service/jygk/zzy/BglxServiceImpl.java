package com.tbea.ic.operation.service.jygk.zzy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.BglxDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyBglx;


@Service
@Transactional("transactionManager")
public class BglxServiceImpl implements BglxService{

	@Autowired
	BglxDao bglxDao;
	
	@Override
	public List<JygkZzyBglx> getViewDataList() {		
		return bglxDao.getViewDataList();
	}
}
