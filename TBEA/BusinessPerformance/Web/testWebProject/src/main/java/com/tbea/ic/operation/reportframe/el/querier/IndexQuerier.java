package com.tbea.ic.operation.reportframe.el.querier;

import com.tbea.ic.operation.reportframe.util.StringUtil;

public class IndexQuerier extends AbstractQuerier {

	public IndexQuerier(String expression) {
		this.expression = expression;
	}
	
	private void passInvisible(){
		while (start < expression.length()){
			if (expression.charAt(start) == '\t' ||
				expression.charAt(start) == '\n' ||
				expression.charAt(start) == '\r' ||
				expression.charAt(start) == ' '){
				++start;
			}else{
				break;
			}
		}
	}
	
	private int queryIndexBlock(){
		if (start >= 0 && expression.length() > start){
			if (expression.charAt(start) != '['){
				passInvisible();
				if (expression.length() > start && expression.charAt(start) == '['){
					return StringUtil.findPair(expression, start, '[', ']');
				}
			}else{
				return StringUtil.findPair(expression, start, '[', ']');
			}
		}
		return -1;
	}

	@Override
	public boolean hasNext() {
		start = end;
		end = this.queryIndexBlock();
		return end >= 0;
	}

	//返回值去掉了[ ] 两个括号
	@Override
	public String next() {
		return expression.substring(start + 1, end - 1);
	}	
}
