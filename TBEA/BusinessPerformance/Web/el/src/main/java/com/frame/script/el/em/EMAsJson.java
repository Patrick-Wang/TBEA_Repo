package com.frame.script.el.em;

import java.util.List;

import com.frame.script.util.TypeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class EMAsJson extends NamedEM{

	public EMAsJson() {
		super("asJson");
	}
	
	@Override
	public int paramCount() {
		return 0;
	}


	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (TypeUtil.instanceOf(stub, List.class) || stub.getClass().isArray()){
			return  JSONArray.fromObject(stub).toString();
		}else{
			return JSONObject.fromObject(stub).toString();
		}
	}
}
