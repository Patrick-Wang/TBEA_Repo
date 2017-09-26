package com.util.tools;

import java.io.Serializable;


public class Pair<T1, T2> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	T1 first;
	T2 second;
	
	public Pair(T1 first, T2 second) {
		super();
		this.first = first;
		this.second = second;
	}
	
	
	public T1 getFirst() {
		return first;
	}
	public void setFirst(T1 first) {
		this.first = first;
	}
	public T2 getSecond() {
		return second;
	}
	public void setSecond(T2 second) {
		this.second = second;
	}
	
}