package com.tbea.ic.operation.model.dao.rhkxx;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.entity.RHKXX;

public interface RHKXXDao {

	List<RHKXX> getRhkxxData(Date d);

	RHKXX getLatestRhkxx();

}
