package com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgxxfb;

import java.util.List;

public class BhgxxfbResp {
	List<String> bhglbs;
	List<List<String>> result;
	public BhgxxfbResp(List<String> bhglbs, List<List<String>> result) {
		super();
		this.bhglbs = bhglbs;
		this.result = result;
	}
	public List<String> getBhglbs() {
		return bhglbs;
	}
	public void setBhglbs(List<String> bhglbs) {
		this.bhglbs = bhglbs;
	}
	public List<List<String>> getResult() {
		return result;
	}
	public void setResult(List<List<String>> result) {
		this.result = result;
	}
}
