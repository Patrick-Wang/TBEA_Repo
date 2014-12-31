package com.tbea.test.testWebProject.model.dao.byqfkfstj;

import java.sql.Date;
import java.util.List;

import com.tbea.test.testWebProject.model.entity.BYQFDWFKFS;
import com.tbea.test.testWebProject.model.entity.BYQGWFKFS;
import com.tbea.test.testWebProject.model.entity.BYQNWFKFS;

public interface BYQFKFSDao {

	List<BYQFDWFKFS> getFdwfs(Date d);

	List<BYQGWFKFS> getGwfs(Date d);

	List<BYQNWFKFS> getNwfs(Date d);

	BYQFDWFKFS getLatestFdwfkfs();

	BYQGWFKFS getLatestGwfkfs();

	BYQNWFKFS getLatestNwfkfs();

}
