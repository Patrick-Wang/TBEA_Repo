package com.tbea.ic.operation.service.jygk.zzy;

import java.util.List;

import net.sf.json.JSONArray;

public interface FxCpylspHqlyddzlService {
	
	public boolean saveDataList(String dwxxid,String nf,String yf,JSONArray data);
	
	public List<String[]> getWriteDataList(String dwxxid,String nf,String yf);
	
	public List<String[]> getViewDataListByq(String dwxxid,String nf,String yf);
	
	public List<String[]> getViewDataListXl(String dwxxid,String nf,String yf);
}
