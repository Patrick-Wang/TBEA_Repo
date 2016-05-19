package com.tbea.ic.operation.service.dzwzgb;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.companys.Company;

public interface DzwzgbService {

	List<List<String>> getDzclcb(Date d, Company company);

	List<List<String>> getDzclcbEntry(Date d, Company comp);

	ErrorCode submitDzclcb(Date d, Company company, JSONArray data);

	ErrorCode saveDzclcb(Date d, Company company, JSONArray data);


}
