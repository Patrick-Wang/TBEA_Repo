package com.tbea.ic.operation.service.hr.org;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.hr.org.OrgDao;
import com.tbea.ic.operation.model.entity.hr.Org;

@Service
@Transactional("transactionManager")
public class OrgServiceImpl implements OrgService {

	@Autowired
	OrgDao orgDao;

	@Override
	public boolean saveOrg(String batch, List<Org> orgs) {
		Long updateTime = Calendar.getInstance().getTimeInMillis();
		for (int i = 0; i < orgs.size(); ++i){
			orgs.get(i).setUpdateTime(updateTime);
			orgs.get(i).setMdmBatch(batch);
			Org org = orgDao.getByCode(orgs.get(i).getCode());
			if(org != null){
				orgs.get(i).setId(org.getId());
			}
			orgDao.merge(orgs.get(i));
		}
		return true;
	}

}
