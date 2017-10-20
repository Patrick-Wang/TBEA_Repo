package com.tbea.erp.report.controller.servlet.session;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Url {
	List<String> segments = new ArrayList<String>();
	public Url(String url) {
		super();
		String[] segs = url.split("/");
		for (String seg : segs){
			if (!seg.isEmpty()){
				segments.add(seg);
			}
		}
	}
	

	public static Url parse(String url){
		return new Url(url);
	}
	
	public String segment(int seg){
		return this.segments.get(seg);
	}
	
	public int segCount(){
		return this.segments.size();
	}
	
	public String skip(int segCount){
		String ret = "";
		for (int i = 0; i < segCount && i < this.segments.size(); ++i){
			ret += "/" + this.segments.get(i);
		}
		return ret;
	}
	
	public Url replace(int seg, String val){
		if (seg < this.segments.size()){
			this.segments.set(seg, val);
		}
		return this;
	}
	
	public Url lastReplace(int seg, String val){
		if (seg < this.segments.size()){
			this.segments.set(this.segments.size() - seg - 1, val);
		}
		return this;
	}
	
	public String url(){
		String ret = "";
		for (int i = 0; i < this.segments.size(); ++i){
			ret += "/" + this.segments.get(i);
		}
		return ret;
	}
	
	public String redirectUrl(){
		return "redirect:" + this.url();
	}
	
}
