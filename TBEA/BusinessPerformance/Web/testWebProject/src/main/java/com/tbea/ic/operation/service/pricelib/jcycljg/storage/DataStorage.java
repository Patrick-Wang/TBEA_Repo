package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.util.List;

public interface DataStorage<T> {
	void store(List<Object[]> data);
	List<List<String>> stringify(List<T> objs);
}
