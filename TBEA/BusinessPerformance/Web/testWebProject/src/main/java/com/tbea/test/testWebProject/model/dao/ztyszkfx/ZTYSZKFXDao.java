package com.tbea.test.testWebProject.model.dao.ztyszkfx;

import java.sql.Date;
import java.util.List;

import com.tbea.test.testWebProject.model.entity.ZTYSZKFX;

public interface ZTYSZKFXDao {

	List<ZTYSZKFX> getZtyszkfxData(Date d);

}
