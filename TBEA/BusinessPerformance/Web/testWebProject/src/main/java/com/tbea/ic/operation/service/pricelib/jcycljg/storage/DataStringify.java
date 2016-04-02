package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.util.List;

public interface DataStringify<T> {
	List<List<String>> stringify(List<T> objs);
}
