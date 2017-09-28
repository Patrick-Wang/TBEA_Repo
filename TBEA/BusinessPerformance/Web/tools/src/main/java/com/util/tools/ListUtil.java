package com.util.tools;

import java.util.List;

public class ListUtil {
	public static List resize(List list, int size){
		if (list.size() < size){
			for (int i = list.size(); i < size; ++i){
				list.add(null);
			}
		}
		return list;
	}
	
	public static List resize(List list, int size, Object val){
		if (list.size() < size){
			for (int i = list.size(); i < size; ++i){
				list.add(val);
			}
		}
		return list;
	}
}
