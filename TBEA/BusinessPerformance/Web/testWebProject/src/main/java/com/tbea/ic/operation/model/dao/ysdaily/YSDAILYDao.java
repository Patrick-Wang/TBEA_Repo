package com.tbea.ic.operation.model.dao.ysdaily;

import java.sql.Date;

import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.YSDAILY;

public interface YSDAILYDao {

	YSDAILY getYsdaily(Date date, DWXX dwxx);

	void update(YSDAILY daily);


}
