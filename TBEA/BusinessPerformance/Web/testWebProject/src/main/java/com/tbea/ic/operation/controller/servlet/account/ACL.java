package com.tbea.ic.operation.controller.servlet.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.Pair;

public class ACL {
	List<Pair<String, Boolean>> acls = new ArrayList<Pair<String, Boolean>>();
	public ACL add(String authName, boolean access){
		acls.add(new Pair<String, Boolean>(authName, access));
		return this;
	}
	
	public ACL select(Map<String, Object> map){
		for (Pair<String, Boolean> pair : acls){
			map.put(pair.getFirst(), pair.getSecond());
		}
		return this;
	}
}
