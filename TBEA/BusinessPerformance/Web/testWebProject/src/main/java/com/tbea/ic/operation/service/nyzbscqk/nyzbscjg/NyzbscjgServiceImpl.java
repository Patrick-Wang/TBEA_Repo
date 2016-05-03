package com.tbea.ic.operation.service.nyzbscqk.nyzbscjg;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.nyzbscqk.nyzbscjg.NyzbscjgDaoImpl;
import com.tbea.ic.operation.model.dao.nyzbscqk.nyzbscjg.NyzbscjgDao;
import com.tbea.ic.operation.service.nyzbscqk.nyzbscjg.NyzbscjgService;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(NyzbscjgServiceImpl.NAME)
@Transactional("transactionManager")
public class NyzbscjgServiceImpl implements NyzbscjgService {
	@Resource(name=NyzbscjgDaoImpl.NAME)
	NyzbscjgDao nyzbscjgDao;

	public final static String NAME = "NyzbscjgServiceImpl";

	@Override
	public List<List<String>> getNyzbscjg(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<String>> getNyzbscjgEntry(Date d, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode saveNyzbscjg(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorCode submitNyzbscjg(Date d, JSONArray data, Company company) {
		// TODO Auto-generated method stub
		return null;
	}

}
