package com.tbea.ic.operation.controller.servlet.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.util.tools.Pair;

public class ACL implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Pair<String, Boolean>> aces = new ArrayList<Pair<String, Boolean>>();
	public ACL add(String authName, boolean access){
		aces.add(new Pair<String, Boolean>(authName, access));
		return this;
	}
	
	public ACL select(Map<String, Object> map){
		for (Pair<String, Boolean> pair : aces){
			map.put(pair.getFirst(), pair.getSecond());
		}
		return this;
	}
	
	public ACL openAll(){
		for (Pair<String, Boolean> pair : aces){
			pair.setSecond(true);
		}
		return this;
	}
	
	public ACL open(String ace){
		for (Pair<String, Boolean> pair : aces){
			if (pair.getFirst().equals(ace)){
				pair.setSecond(true);
				break;
			}
		}
		return this;
	}
	
	public ACL close(String ace){
		for (Pair<String, Boolean> pair : aces){
			if (pair.getFirst().equals(ace)){
				pair.setSecond(false);
				break;
			}
		}
		return this;
	}
	
	public List<String> getOpenAuths(){
		List<String> auths = new ArrayList<String>();
		for (Pair<String, Boolean> ace : aces) {
			if (ace.getSecond()) {
				auths.add(ace.getFirst());
			}
		}
		return auths;
	}
	
	public Boolean isOpen(String ace){
		for (Pair<String, Boolean> pair : aces){
			if (pair.getFirst().equals(ace)){
				return pair.getSecond();
			}
		}
		return null;
	}
}
