package com.tbea.ic.operation.model.dao.ztyszkfx;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.entity.ZTYSZKFX;

public interface ZTYSZKFXDao {

	List<ZTYSZKFX> getZtyszkfxData(Date d);

	ZTYSZKFX getLatestYszk();

}
