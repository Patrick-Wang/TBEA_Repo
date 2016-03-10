package com.tbea.ic.carrier.model.dao.psn;

import java.util.List;

import com.tbea.ic.carrier.model.entity.Psn;



public interface PsnDao {
	
	String getPsnNoByID(String id);

	int getPsnPagesCount();
	
	List<Psn> getPsns(int pageIndex);
	
	List<Psn> getPsnsById(String id);
}
