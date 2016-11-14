package com.tbea.ic.operation.reportframe.el.querier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectQuerier extends AbstractQuerier{
	
	static final Pattern objectPattern = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*"); 
	
	public ObjectQuerier(String expression) {
		this.expression = expression;
	}

	private void queryObjectBlock(){
		Matcher objMatcher = objectPattern.matcher(expression);
		if (objMatcher.find(start)){
			start = objMatcher.start();
			end = queryContinuousIndexBlockEnd(objMatcher.end());
			if (end < 0){
				end = objMatcher.end();
			}
		}else{
			end = -1;
		}
	}
		
	public int queryContinuousIndexBlockEnd(int start){
		IndexQuerier eliQuerier = new IndexQuerier(expression);
		eliQuerier.reset(expression, start);
		int ret = -1;
		while (eliQuerier.hasNext()){
			ret = eliQuerier.end();
		}
		return ret;
	}

	@Override
	public boolean hasNext() {
		start = end;
		this.queryObjectBlock();
		return end >= 0;
	}

	@Override
	public String next() {
		return expression.substring(start, end);
	}
	
}
