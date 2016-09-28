package com.tbea.ic.operation.controller.servlet.report;

public class Counter {
	Integer start;

	public Counter newCounter(){
		return new Counter();
	}
	
	public Counter reset(Integer start){
		return this;
	}
	
	public Counter next(){
		++start;
		return this;
	}
	
	public Integer val(){
		return start;
	}
	
	public String format(Integer width){
		return String.format("%0" + width + "d", start);
	}
}
