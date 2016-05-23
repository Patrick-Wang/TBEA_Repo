package com.tbea.ic.operation.service.ztyszkfx;

import java.sql.Date;

public interface ZTYSZKFXService {

	String[][] getZtyszkfxData(Date d);

	Date getLatestDate();

}
