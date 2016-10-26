package com.tbea.ic.operation.service.cpzlqk.pdcpycssbhgxxfb;

import java.util.List;

import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;

public class BhgxxfbResp {
	List<String> bhglbs;
	List<List<String>> result;
	List<WaveItem> waveItems;
	Integer zt;
	public BhgxxfbResp() {
		super();
		
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	public BhgxxfbResp(List<String> bhglbs, List<List<String>> result,
			List<WaveItem> waveItems) {
		super();
		this.bhglbs = bhglbs;
		this.result = result;
		this.waveItems = waveItems;
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
	public List<WaveItem> getWaveItems() {
		return waveItems;
	}
	public void setWaveItems(List<WaveItem> waveItems) {
		this.waveItems = waveItems;
	}
}
