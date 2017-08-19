package com.frame.script.el.querier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionQuerier extends AbstractQuerier {
	
	static final Pattern expPattern = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*(\\.[a-zA-Z_][a-zA-Z0-9_]*)*");  
	
	public ExpressionQuerier(String expression) {
		this.expression = expression;
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
	
	//1.user.take[a.b[]]   2.user.walk     
	private int queryExpBlock(int begin){
		Matcher matcher = expPattern.matcher(expression);
		int endTmp = -1;
		if (matcher.find(begin)){
			endTmp = queryContinuousIndexBlockEnd(matcher.end());
			if (endTmp < 0){
				endTmp = matcher.end();
			}else{
				if (expression.length() > endTmp && expression.charAt(endTmp) == '.'){
					endTmp = queryExpBlock(endTmp + 1);
				}
			}
			start = matcher.start();
		}
		return endTmp;
	}

	@Override
	public boolean hasNext() {
		start = end;
		end = this.queryExpBlock(start);
		return end >= 0;
	}

	@Override
	public String next() {
		return expression.substring(start, end);
	}
	
}
