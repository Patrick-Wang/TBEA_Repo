package com.tbea.ic.operation.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormulaServer<T> {
	private Map<Integer, T> paramCache = new HashMap<Integer, T>();
	private List<Pair<Formula, FormulaClient<T>>> rules = new ArrayList<Pair<Formula, FormulaClient<T>>>();
	
	public void run(){
		
		int length = rules.size();
		Pair<Integer, T> pair = null;
		Pair<Formula, FormulaClient<T>> rule = null;
		
		for (int i = 0, j = 0; i < length; ++i){
			rule = rules.get(j);
			pair = null;
			rule.second.onStart(this);
			if (rule.getFirst().isThis()){
				pair = rule.getSecond().onThis();
				rules.remove(j);
			}else if(rule.getFirst().isNull()){
				pair = rule.getSecond().onNull();
				rules.remove(j);
			}else{
				++j;
			}
			if (null != pair){
				paramCache.put(pair.getFirst(), pair.getSecond());
			}
			rule.second.onComplete(this);
		}
		length = rules.size();
		for (int i = 0; i < length; ++i){
			pair = null;
			rule = rules.get(i);
			boolean hasCache = false;
			List<Integer> vals = rule.getFirst().getParameters();
			for(Integer param : vals){
				if (paramCache.containsKey(param)){
					hasCache = true;
					break;					
				}
			}
			
			if (hasCache){
				pair = rule.getSecond().onFormula(this, rule.getFirst());
			}else{
				pair = rule.getSecond().onFormulaNoCache(this, rule.getFirst());
			}
			if (null != pair){
				paramCache.put(pair.getFirst(), pair.getSecond());
			}
		}
		rules.clear();
		paramCache.clear();
	}
	
	public T getCache(Integer key){
		return paramCache.get(key);
	}

	public FormulaServer<T> addRule(Formula formula, FormulaClient<T> client) {
		this.rules.add(new Pair<Formula, FormulaClient<T>>(formula, client));
		return this;
	}
}
