package com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgwtmx;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.controller.servlet.cpzlqk.ByqBhgType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface ByqcpycssbhgwtmxService {

	List<List<String>> getByqcpycssbhgwtmx(Date d,
			YDJDType yjType, ByqBhgType bhgType);


}
