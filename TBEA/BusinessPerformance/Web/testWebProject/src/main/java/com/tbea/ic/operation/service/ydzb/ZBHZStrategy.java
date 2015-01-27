package com.tbea.ic.operation.service.ydzb;

import java.util.List;

import com.tbea.ic.operation.model.entity.YDZBBean;

public interface ZBHZStrategy {
	String[][] getZBHZData(List<YDZBBean> ydzbs);
}
