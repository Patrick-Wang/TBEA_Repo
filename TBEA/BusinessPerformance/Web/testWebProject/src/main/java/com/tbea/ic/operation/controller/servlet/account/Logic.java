package com.tbea.ic.operation.controller.servlet.account;

public class Logic{
	boolean ret = false;
	
	public Logic(boolean ret) {
		super();
		this.ret = ret;
	}
	public boolean or(boolean val){
		ret = ret || val;
		return val;
	}
	public boolean and(boolean val){
		ret = ret && val;
		return val;
	}
	public boolean value(){
		return ret;
	}
}