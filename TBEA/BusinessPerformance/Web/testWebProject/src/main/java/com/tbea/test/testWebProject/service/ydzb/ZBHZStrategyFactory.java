package com.tbea.test.testWebProject.service.ydzb;

public class ZBHZStrategyFactory {
	public static ZBHZStrategy createGcyZBHZStrategy(){
		return new GcyZBHZStrategy();
	} 
	
	public static ZBHZStrategy createGdwZBHZStrategy(){
		return new GdwZBHZStrategy();
	} 
}
