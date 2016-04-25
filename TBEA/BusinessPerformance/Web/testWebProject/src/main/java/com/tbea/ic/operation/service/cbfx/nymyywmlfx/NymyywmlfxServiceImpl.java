package com.tbea.ic.operation.service.cbfx.nymyywmlfx;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cbfx.nymyywmlfx.NymyywmlfxDao;
import com.tbea.ic.operation.model.dao.cbfx.nymyywmlfx.NymyywmlfxDaoImpl;
import com.tbea.ic.operation.model.entity.cbfx.NymyywmlfxEntity;

@Service(NymyywmlfxServiceImpl.NAME)
@Transactional("transactionManager")
public class NymyywmlfxServiceImpl implements NymyywmlfxService {
	@Resource(name = NymyywmlfxDaoImpl.NAME)
	NymyywmlfxDao nymyywmlfxDao;

	public final static String NAME = "NymyywmlfxServiceImpl";

	@Override
	public List<List<String>> getNymyywmlfx(Date d, Company company) {
		List<NymyywmlfxEntity> entities = nymyywmlfxDao.getByDate(d, company);

		List<List<String>> result = new ArrayList<List<String>>();
		for (NymyywmlfxEntity entity : entities) {
			result.add(toList(entity));
		}

		return result;
	}

	private List<String> toList(NymyywmlfxEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add(entity.getHzkh());
		list.add(entity.getMyxm());
		list.add("" + entity.getSl());
		list.add("" + entity.getSr());
		list.add("" + entity.getCb());
		list.add("" + Util.minus(entity.getSr(), entity.getCb()));
		list.add("" + Util.division(Util.minus(entity.getSr(), entity.getCb()), entity.getSr()));
		return list;
	}

	@Override
	public void importFromNC(Date d, List<Company> comps) {
		// TODO Auto-generated method stub
		
	}
}
