package com.xml.frame.report.util;

import java.util.ArrayList;
import java.util.List;

public class EasyList<T> {
	
	List<T> list = new ArrayList<T>();
	
	public EasyList(T[] arr){
		if (null == arr){
			list = null;
		}else{
			for (int i = 0; i < arr.length; ++i){
				list.add(arr[i]);
			}
		}
	}
	public EasyList(List<T> arr){
		this.list = arr;
	}
	
	public EasyList(){}
	
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

	public List<T> clone(){
		List<T> cp = new ArrayList<T>(list.size());
		for(T l : list){
			cp.add(l);
		}
		return cp;
	}
	
	public EasyList<T> clear() {
		list.clear();
		return this;
	}
}