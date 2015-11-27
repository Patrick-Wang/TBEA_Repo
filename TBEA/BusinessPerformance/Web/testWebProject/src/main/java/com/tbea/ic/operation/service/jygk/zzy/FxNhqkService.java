package com.tbea.ic.operation.service.jygk.zzy;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyBglx;

public interface FxNhqkService {

	List<String[]> getZb(Date date, String comp, String entryType);
}
