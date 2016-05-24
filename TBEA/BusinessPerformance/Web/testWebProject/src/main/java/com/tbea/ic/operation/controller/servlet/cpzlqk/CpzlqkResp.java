package com.tbea.ic.operation.controller.servlet.cpzlqk;

import java.util.List;

import com.tbea.ic.operation.common.formatter.raw.RawEmptyHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
import com.tbea.ic.operation.common.formatter.raw.RawNumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawPercentFormatterHandler;



public class CpzlqkResp {
	
	List<List<String>> tjjg;
	List<WaveItem> waveItems;
	List<String> waveX;

	
	public List<WaveItem> getWaveItems() {
		return waveItems;
	}
	
	public void setWaveItems(List<WaveItem> waveItems) {
		this.waveItems = waveItems;
	}
	
	
	
	public CpzlqkResp(List<List<String>> tjjg, List<WaveItem> waveItems) {
		super();
		this.tjjg = tjjg;
		this.waveItems = waveItems;
	}

	public CpzlqkResp(List<List<String>> tjjg, List<WaveItem> waveItems,
			List<String> waveX) {
		super();
		this.tjjg = tjjg;
		this.waveItems = waveItems;
		this.waveX = waveX;
	}

	public CpzlqkResp format(){
		if (null != waveItems){
			for (WaveItem item : waveItems){
				item.formate();
			}
		}
		
		if (null != tjjg){
			RawFormatterHandler handler = new RawEmptyHandler(null, new Integer[]{0, 1});
			handler.next(new RawPercentFormatterHandler(1, null, new Integer[]{4, 7}))
				.next(new RawNumberFormatterHandler(0));
			RawFormatterServer serv = new RawFormatterServer(handler);
			serv.acceptNullAs("--").format(tjjg);
		}
		return this;
	}

	public List<List<String>> getTjjg() {
		return tjjg;
	}

	public void setTjjg(List<List<String>> tjjg) {
		this.tjjg = tjjg;
	}

	public List<String> getWaveX() {
		return waveX;
	}

	public void setWaveX(List<String> waveX) {
		this.waveX = waveX;
	}
	
}
