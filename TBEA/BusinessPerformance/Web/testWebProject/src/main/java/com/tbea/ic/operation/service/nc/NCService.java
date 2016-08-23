package com.tbea.ic.operation.service.nc;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.model.entity.jygk.NCZB;

public interface NCService {

	void connetToNCSystem(String ver, Calendar date, List<String> codeList);

	public List<NCZB> getNCZBByDate(int nf, int yf);

	List<Object[]> get15DBRs(Date d);

}
