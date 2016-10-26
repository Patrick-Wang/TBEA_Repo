package com.tbea.ic.operation.common.querier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ZBStatusQuerier {
	Map<Integer, Set<Integer>> authZts = new HashMap<Integer, Set<Integer>>();
	
	
	public ZBStatusQuerier add(Integer auth, Integer ZBStatus){
		if (!authZts.containsKey(auth)){
			authZts.put(auth, new HashSet<Integer>());
		}
		authZts.get(auth).add(ZBStatus);
		return this;
	}
	
	public List<Integer> queryStatus(List<Integer> auths){
		Set<Integer> status = new HashSet<Integer>();
		for (Integer auth: auths){
			if (authZts.containsKey(auth)){
				status.addAll(authZts.get(auth));
			}
		}
		
		List<Integer> ret = new ArrayList<Integer>();
		for (Integer stat : status){
			ret.add(stat);
		}
		
		return ret;
	}
}
