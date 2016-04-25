package com.tbea.ic.operation.service.cbfx.nymyywmlfx;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cbfx.nymyywmlfx.NymyywmlfxDaoImpl;
import com.tbea.ic.operation.model.dao.cbfx.nymyywmlfx.NymyywmlfxDao;
import com.tbea.ic.operation.service.cbfx.nymyywmlfx.NymyywmlfxService;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(NymyywmlfxServiceImpl.NAME)
@Transactional("transactionManager")
public class NymyywmlfxServiceImpl implements NymyywmlfxService {
	@Resource(name=NymyywmlfxDaoImpl.NAME)
	NymyywmlfxDao nymyywmlfxDao;

	public final static String NAME = "NymyywmlfxServiceImpl";

	@Override
	public List<List<String>> getNymyywmlfx(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode submitNymyywmlfx(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode saveNymyywmlfx(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getNymyywmlfxEntry(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

}
