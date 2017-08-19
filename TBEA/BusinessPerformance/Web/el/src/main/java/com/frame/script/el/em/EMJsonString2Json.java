package com.frame.script.el.em;

import java.util.List;

import com.frame.script.util.TypeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EMJsonString2Json extends NamedEM {

	public EMJsonString2Json() {
		super("json");
	}

	@Override
	public int paramCount() {
		return 0;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (TypeUtil.instanceOf(stub, String.class)) {
			try {
				return JSONObject.fromObject(stub);
			} catch (Exception e) {
				return JSONArray.fromObject(stub);
			}
		}
		return stub;
	}
}
