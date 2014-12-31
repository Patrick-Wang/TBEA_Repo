package com.tbea.test.testWebProject.service.byqfkfstj;

import java.sql.Date;

public interface BYQFKFSTJService {

	String[][] getFdwData(Date d);

	String[][] getGwData(Date d);

	String[][] getNwData(Date d);

	Date getLatestDate();

}
