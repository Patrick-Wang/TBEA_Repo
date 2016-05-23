package com.tbea.ic.operation.model.dao.byqfkfstj;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.entity.BYQFDWFKFS;
import com.tbea.ic.operation.model.entity.BYQGWFKFS;
import com.tbea.ic.operation.model.entity.BYQNWFKFS;

public interface BYQFKFSDao {

	List<BYQFDWFKFS> getFdwfs(Date d);

	List<BYQGWFKFS> getGwfs(Date d);

	List<BYQNWFKFS> getNwfs(Date d);

	BYQFDWFKFS getLatestFdwfkfs();

	BYQGWFKFS getLatestGwfkfs();

	BYQNWFKFS getLatestNwfkfs();

}
