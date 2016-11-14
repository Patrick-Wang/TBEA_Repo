package com.tbea.ic.operation.reportframe.el.querier;

import com.tbea.ic.operation.reportframe.util.StringUtil;

public class ELBlockQuerier extends AbstractQuerier {

	public ELBlockQuerier(String expression) {
		this.expression = expression;
	}

	@Override
	public boolean hasNext() {
		start = end;
		int index = expression.indexOf("${", start);
		if (index >= 0){
			start = index;
			end = StringUtil.findPair(expression, start + 1, '{', '}');
		}else{
			end = -1;
		}
		return end >= 0;
	}

	//返回值去掉了${ }两个括号
	@Override
	public String next() {
		return expression.substring(start + 2, end - 1);
	}
}
