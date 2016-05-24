package com.tbea.ic.operation.controller.servlet.cpzlqk;

import java.util.List;

import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
import com.tbea.ic.operation.common.formatter.raw.RawNumberFormatterHandler;

public class WaveItem {
	private String name;
	private List<String> data;
	
	public WaveItem(String name, List<String> data) {
		super();
		this.name = name;
		this.data = data;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	} 
	
	public void formate(){
		RawFormatterServer serv = new RawFormatterServer(new RawNumberFormatterHandler(3));
		serv.acceptNullAs("0").formatRow(data);
	}
	
	public static WaveItem find(List<WaveItem> items, String name){
		for (int i = 0; i < items.size(); ++i){
			if (items.get(i).getName().equals(name)){
				return items.get(i);
			}
		}
		return null;
	}
}
