package com.tbea.ic.operation.service.cbfx.dmcbfx;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.cbfx.dmcbfx.DmcbfxDaoImpl;
import com.tbea.ic.operation.model.dao.cbfx.dmcbfx.DmcbfxDao;
import com.tbea.ic.operation.service.cbfx.dmcbfx.DmcbfxService;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(DmcbfxServiceImpl.NAME)
@Transactional("transactionManager")
public class DmcbfxServiceImpl implements DmcbfxService {
	@Resource(name=DmcbfxDaoImpl.NAME)
	DmcbfxDao dmcbfxDao;

	public final static String NAME = "DmcbfxServiceImpl";

	@Override
	public List<List<String>> getDmcbfx(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getDmcbfxEntry(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode saveDmcbfx(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode submitDmcbfx(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

}
