package com.util.tools;

import java.util.ArrayList;
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
	
	
	public static List clone(List ls) {
		List lc = new ArrayList();
		lc.addAll(ls);
		return lc;
	}
	
	public static List left(List l, int count) {
		List lr = new ArrayList();
		int len = Math.min(l.size(), count);
		for (int i = 0; i < len; ++i){
			lr.add(l.get(i));
		}
		return lr;
	}
	
	public static List right(List l, int count) {
		List lr = new ArrayList();
		int len = l.size();
		for (int i = Math.max(len - count, 0); i < len; ++i){
			lr.add(l.get(i));
		}
		return lr;
	}
	
	public static List sub(List l, int from) {
		List lr = new ArrayList();
		int len = l.size();
		for (int i = from; i < len; ++i){
			lr.add(l.get(i));
		}
		return lr;
	}
	
	public static List lastSub(List l, int lastFrom) {
		List lr = new ArrayList();
		int len = l.size() - lastFrom;
		for (int i = 0; i < len; ++i){
			lr.add(l.get(i));
		}
		return lr;
	}
	
	public static List mid(List l, int from, int to) {
		List lr = new ArrayList();
		int len = Math.min(l.size(), to);
		for (int i = 0; i < len; ++i){
			lr.add(l.get(i));
		}
		return lr;
	}

	public static Object rmAt(Object list, int index){
	    if (list instanceof List){
	        if (index < ((List)list).size()) {
                ((List) list).remove(index);
            }
        }else if (list.getClass().isArray()){
            if (index < ((Object[])list).length) {
                Object[] newList = new Object[((Object[])list).length - 1];
                for (int i = ((Object[])list).length - 1, j = newList.length - 1; i >= 0; --i){
                    if (i != index){
                        newList[j--] = ((Object[])list)[i];
                    }
                }
                list = newList;
            }
        }
        return list;
    }

    public static Object lastRmAt(Object list, int index){
        if (list instanceof List){
            if (index < ((List)list).size()) {
                int lastIndex = ((List)list).size() - index - 1;
                ((List) list).remove(lastIndex);
            }
        }else if (list.getClass().isArray()){
            if (index < ((Object[])list).length) {
                int lastIndex = ((Object[])list).length - index - 1;
                Object[] newList = new Object[((Object[])list).length - 1];
                for (int i = ((Object[])list).length - 1, j = newList.length - 1; i >= 0; --i){
                    if (i != lastIndex){
                        newList[j--] = ((Object[])list)[i];
                    }
                }
                list = newList;
            }
        }
        return list;
    }
}
