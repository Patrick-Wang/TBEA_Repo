package com.tbea.ic.operation.common;

import java.util.ArrayList;
import java.util.List;

public class EasyList<T> {
	List<T> list = new ArrayList<T>();

	public EasyList<T> add(T t) {
		list.add(t);
		return this;
	}

	public T lastIndexOf(Class<?> cls){
		for (int i = list.size() - 1; i >= 0; --i){
			if (list.get(i).getClass() == cls){
				return list.get(i);
			}
		}
		return null;
	}
	
	public T first() {
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public T last() {
		if (!list.isEmpty()) {
			return list.get(list.size() - 1);
		}
		return null;
	}

	public List<T> toList() {
		return list;
	}

	public EasyList<T> clear() {
		list.clear();
		return this;
	}
}