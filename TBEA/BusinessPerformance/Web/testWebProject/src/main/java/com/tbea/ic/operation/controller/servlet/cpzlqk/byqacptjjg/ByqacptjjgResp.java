package com.tbea.ic.operation.controller.servlet.cpzlqk.byqacptjjg;

import java.util.List;

import com.tbea.ic.operation.common.formatter.raw.RawEmptyHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawFormatterServer;
import com.tbea.ic.operation.common.formatter.raw.RawNumberFormatterHandler;
import com.tbea.ic.operation.common.formatter.raw.RawPercentFormatterHandler;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;



public class ByqacptjjgResp {
	List<List<String>> acptjjg;
	List<WaveItem> waveItems;
	public List<List<String>> getAcptjjg() {
		return acptjjg;
	}
	
	public void setAcptjjg(List<List<String>> acptjjg) {
		this.acptjjg = acptjjg;
	}
	
	public List<WaveItem> getWaveItems() {
		return waveItems;
	}
	
	public void setWaveItems(List<WaveItem> waveItems) {
		this.waveItems = waveItems;
	}
	
	public ByqacptjjgResp(List<List<String>> acptjjg, List<WaveItem> waveItems) {
		super();
		this.acptjjg = acptjjg;
		this.waveItems = waveItems;
	}
	
	public ByqacptjjgResp formate(){
		if (null != waveItems){
			for (WaveItem item : waveItems){
				item.formate();
			}
		}
		
		if (null != acptjjg){
			RawFormatterHandler handler = new RawEmptyHandler(null, new Integer[]{0, 1});
			handler.next(new RawPercentFormatterHandler(1, null, new Integer[]{4, 7}))
				.next(new RawNumberFormatterHandler(0));
			RawFormatterServer serv = new RawFormatterServer(handler);
			serv.acceptNullAs("--").format(acptjjg);
		}
		return this;
	}
	
}
