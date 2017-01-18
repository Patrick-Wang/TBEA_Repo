package com.tbea.ic.operation.service.wlydd;

import java.sql.Date;

public interface WlyddService {

	void importDlKglydd(Date d);

	void importDlMlspcs(Date d);

	void importXlKglydd(Date d);

	void importXlMlspcs(Date d);

	void importLlKglydd(Date d);
}
