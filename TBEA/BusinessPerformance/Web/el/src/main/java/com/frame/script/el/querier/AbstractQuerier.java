package com.frame.script.el.querier;

public abstract class AbstractQuerier implements Querier {

	protected String expression;
	protected int start = 0;
	protected int end = 0;
	
	@Override
	public int start() {
		return start;
	}

	@Override
	public int end() {
		return end;
	}

	@Override
	public void reset(String exp, int start) {
		expression = exp;
		this.start = start;
		this.end = start;
	}

}
