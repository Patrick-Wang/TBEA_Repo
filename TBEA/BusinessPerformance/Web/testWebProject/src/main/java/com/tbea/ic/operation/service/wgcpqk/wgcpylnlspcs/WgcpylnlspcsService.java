package com.tbea.ic.operation.service.wgcpqk.wgcpylnlspcs;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.wgcpqk.WgcpqkType;

public interface WgcpylnlspcsService {

	List<List<String>> getWgcpylnlspcs(Date d, Company company, WgcpqkType type);
	
	List<List<String>> getWgcpylnlspcsEntry(Date d, Company company, WgcpqkType type);

	ErrorCode saveWgcpylnlspcs(Date d, Company company, WgcpqkType type, JSONArray data);

	ErrorCode submitWgcpylnlspcs(Date d, Company company, WgcpqkType type, JSONArray data);
	
	ZBStatus getWgcpylnlspcsStatus(Date d, Company comp, WgcpqkType type);
}
