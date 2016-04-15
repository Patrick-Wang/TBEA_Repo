package com.tbea.ic.operation.service.sbdddcbjpcqk;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.controller.servlet.sbdddcbjpcqk.KglyddType;

public interface SbdddcbjpcqkService {

	List<List<String>> getByqkglydd(Date d, KglyddType type);

	List<List<String>> getXlkglydd(Date d, KglyddType type);

	List<List<String>> getByqkglyddEntry(Date d, KglyddType type);

	List<List<String>> getXlkglyddEntry(Date d, KglyddType type);

	ErrorCode saveXlkglydd(Date d, KglyddType type);

	ErrorCode saveByqkglydd(Date d, KglyddType type);

	ErrorCode submitByqkglydd(Date d, KglyddType type);

	ErrorCode submitXlkglydd(Date d, KglyddType type);


}
