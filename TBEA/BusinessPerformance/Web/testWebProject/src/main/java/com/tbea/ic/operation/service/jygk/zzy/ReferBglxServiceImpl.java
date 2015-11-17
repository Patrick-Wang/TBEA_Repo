package com.tbea.ic.operation.service.jygk.zzy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglx;


@Service
@Transactional("transactionManager")
public class ReferBglxServiceImpl implements ReferBglxService{

	@Autowired
	ReferBglxDao referBglxDao;
	@Override
	public List<JygkZzyDwReferBglx> getDataList(List<Company> comps) {		
		return referBglxDao.getDataList(comps);
	}
}
