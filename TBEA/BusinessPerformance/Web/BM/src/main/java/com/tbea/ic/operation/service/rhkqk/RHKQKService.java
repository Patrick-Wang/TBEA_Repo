package com.tbea.ic.operation.service.rhkqk;

import java.sql.Date;
import java.util.Calendar;

public interface RHKQKService {

	String[][] getRhkqkData(Date d);

	Date getLatestDate();

}
