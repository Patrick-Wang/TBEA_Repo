package com.tbea.ic.operation.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xml.frame.report.util.Pair;


public class FormulaServer<T> {
	private Map<Integer, T> paramCache = new HashMap<Integer, T>();
	private List<Formula> formulas = new ArrayList<Formula>();
	FormulaClient<T> client;
	
	
	public FormulaServer(FormulaClient<T> client) {
		super();
		this.client = client;
	}

	public void run(){
		
		int length = formulas.size();
		Pair<Integer, T> pair = null;
		Formula formula = null;
		for (int i = 0, j = 0; i < length; ++i){
			formula = formulas.get(j);
			pair = null;
			client.onStart(this, formula);
			if (formula.isThis()){
				pair = client.onThis(this, formula);
				client.onComplete(this, formula);
				formulas.remove(j);
			}else if(formula.isNull()){
				pair = client.onNull(this, formula);
				client.onComplete(this, formula);
				formulas.remove(j);
			}else{
				++j;
			}
			if (null != pair){
				paramCache.put(pair.getFirst(), pair.getSecond());
			}
		}
		
		length = formulas.size();
		for (int i = 0; i < length; ++i){
			pair = null;
			formula = formulas.get(i);
			boolean hasCache = false;
			List<Integer> vals = formula.getParameters();
			for(Integer param : vals){
				if (paramCache.containsKey(param)){
					hasCache = true;
					break;					
				}
			}
			if (hasCache){
				pair = client.onFormula(this, formula);
			}else{
				pair = client.onFormulaNoCache(this, formula);
			}
			client.onComplete(this, formula);
			if (null != pair){
				//System.out.println(JSONObject.fromObject(pair).toString());
				paramCache.put(pair.getFirst(), pair.getSecond());
			}
		}
		formulas.clear();
		paramCache.clear();
	}
	
	public T getCache(Integer key){
		return paramCache.get(key);
	}
	
	public FormulaServer<T> addFormul(Formula formula) {
		this.formulas.add(formula);
		return this;
	}
}
