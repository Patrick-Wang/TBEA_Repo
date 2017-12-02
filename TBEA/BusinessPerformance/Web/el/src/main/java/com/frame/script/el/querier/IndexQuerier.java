package com.frame.script.el.querier;

import com.util.tools.StringUtil;

public class IndexQuerier extends AbstractQuerier {

	public IndexQuerier(String expression) {
		this.expression = expression;
	}
	
	private boolean passInvisible(){
		boolean isEnd = true;
		while (start < expression.length()){
			if (expression.charAt(start) == '\t' ||
				expression.charAt(start) == '\n' ||
				expression.charAt(start) == '\r' ||
				expression.charAt(start) == ' '){
				++start;
			}else{
				isEnd = false;
				break;
			}
		}
		return isEnd;
	}
	
	private boolean rangeCheck(int index) {
		return index >= 0 && index < expression.length();
	}
	
	private boolean findIndexStart() {
		if (rangeCheck(start)) {
			if (expression.charAt(start) == '[' ||
				(!passInvisible() && expression.charAt(start) == '[')) {
				return true;
			}
		}
		return false;
	}
	
	
	private boolean queryIndexBlock(){
		if (findIndexStart()){
			end = StringUtil.findPair(expression, start, '[', ']');
			if (end < 0) {
				System.out.println(expression + " find end ']' failed from '[' start=" + start);
			}
			return end > 0;
		}
		else {
			end = -1;
			return false;
		}
	}

	@Override
	public boolean hasNext() {
		start = end;
		return queryIndexBlock();
	}

	//返回值去掉了[ ] 两个括号
	@Override
	public String next() {
		return expression.substring(start + 1, end - 1);
	}	
}
