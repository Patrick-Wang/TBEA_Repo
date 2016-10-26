package com;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.service.report.HBWebService;
import com.tbea.ic.operation.service.util.nc.NCLoggerFactory;

public class Test {

	public static void main(String[] args) {
		HBWebService hbws = new HBWebService();
		
		System.out.println("getHBSjzb++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		List<List<Object>> result = hbws.getHBSjzb(new ArrayList<String>(), Date.valueOf("2016-9-1"));
		for (List<Object> r: result){
			System.out.println(JSONArray.fromObject(r).toString());
		}
		System.out.println("getHBCpqy++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		result = hbws.getHBCpqy(new ArrayList<String>(), Date.valueOf("2016-9-1"));
		for (List<Object> r: result){
			System.out.println(JSONArray.fromObject(r).toString());
		}
		System.out.println("getHBScqy++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		result = hbws.getHBScqy(new ArrayList<String>(), Date.valueOf("2016-9-1"));
		for (List<Object> r: result){
			System.out.println(JSONArray.fromObject(r).toString());
		}
		System.out.println("getHBClwcqk++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		result = hbws.getHBClwcqk(new ArrayList<String>(), Date.valueOf("2016-9-1"));
		for (List<Object> r: result){
			System.out.println(JSONArray.fromObject(r).toString());
		}
	}

}
