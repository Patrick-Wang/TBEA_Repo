package com.tbea.ic.operation.service.byqfkfstj;

import java.sql.Date;

public interface BYQFKFSTJService {

	String[][] getFdwData(Date d);

	String[][] getGwData(Date d);

	String[][] getNwData(Date d);

	Date getLatestDate();

}
