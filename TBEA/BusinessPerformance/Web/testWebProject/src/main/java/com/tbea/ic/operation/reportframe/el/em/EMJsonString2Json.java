package com.tbea.ic.operation.reportframe.el.em;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tbea.ic.operation.reportframe.util.TypeUtil;


public class EMJsonString2Json extends NamedEM{

	public EMJsonString2Json() {
		super("json");
	}
	
	@Override
	public int paramCount() {
		return 0;
	}


	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (TypeUtil.instanceOf(stub, String.class)){
			try{
				return JSONObject.fromObject(stub);
			}catch(Exception e){
				return  JSONArray.fromObject(stub);
			}
		}
		return stub;
	}
}
