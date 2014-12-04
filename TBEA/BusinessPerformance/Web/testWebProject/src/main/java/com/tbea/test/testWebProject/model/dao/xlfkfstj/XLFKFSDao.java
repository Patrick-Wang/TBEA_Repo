package com.tbea.test.testWebProject.model.dao.xlfkfstj;

import java.util.List;

import com.tbea.test.testWebProject.model.entity.XLFDWFKFS;
import com.tbea.test.testWebProject.model.entity.XLGWFKFS;
import com.tbea.test.testWebProject.model.entity.XLNWFKFS;

public interface XLFKFSDao {

	List<XLFDWFKFS> getFdwfkfs();

	List<XLGWFKFS> getGwfkfs();

	List<XLNWFKFS> getNwfkfs();

}
