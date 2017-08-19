package com.tbea.ic.operation.controller.servlet.cpzlqk;

import java.util.List;

import com.tbea.ic.operation.common.ZBStatus;
import com.xml.frame.report.util.raw.RawEmptyHandler;
import com.xml.frame.report.util.raw.RawFormatterHandler;
import com.xml.frame.report.util.raw.RawFormatterServer;
import com.xml.frame.report.util.raw.RawNumberFormatterHandler;
import com.xml.frame.report.util.raw.RawPercentFormatterHandler;



public class CpzlqkResp {
	
	List<List<String>> tjjg;
	ZBStatus status;
	Integer zt;
	Boolean isXl;
	
	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	List<WaveItem> waveItems;
	List<String> waveX;
	List<String> zrlb;
	List<String> bhglx;
	
	public List<WaveItem> getWaveItems() {
		return waveItems;
	}
	
	public void setWaveItems(List<WaveItem> waveItems) {
		this.waveItems = waveItems;
	}
	
	public CpzlqkResp(List<List<String>> tjjg, ZBStatus status,
			List<String> zrlb, List<String> bhglx) {
		super();
		this.tjjg = tjjg;
		this.status = status;
		this.zrlb = zrlb;
		this.bhglx = bhglx;
	}
	public CpzlqkResp(Boolean isXl) {
		super();
		this.isXl = isXl;
	}

	public CpzlqkResp(Boolean isXl,List<List<String>> tjjg, ZBStatus status) {
		super();
		this.tjjg = tjjg;
		this.status = status;
		this.isXl = isXl;
	}
	
	public CpzlqkResp(Boolean isXl,List<List<String>> tjjg, List<WaveItem> waveItems) {
		super();
		this.tjjg = tjjg;
		this.waveItems = waveItems;
		this.isXl = isXl;
	}

	public CpzlqkResp(Boolean isXl,List<List<String>> tjjg, List<WaveItem> waveItems,
			List<String> waveX) {
		super();
		this.tjjg = tjjg;
		this.isXl = isXl;
		this.waveItems = waveItems;
		this.waveX = waveX;
	}

	public CpzlqkResp(Boolean isXl,List<List<String>> tjjg) {
		this.tjjg = tjjg;
		this.isXl = isXl;
	}

	public CpzlqkResp format(){
		if (null != waveItems){
			for (WaveItem item : waveItems){
				item.formate();
			}
		}
		
		if (null != tjjg){
			RawFormatterHandler handler = new RawEmptyHandler(null, new Integer[]{0, 1});
			RawFormatterHandler handlerPer = handler.next(new RawPercentFormatterHandler(2, null, new Integer[]{4, 7}));
			if (isXl){
				handlerPer.next(new RawNumberFormatterHandler(2));
			}else{
				handlerPer.next(new RawNumberFormatterHandler(0));
			}
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

	public ZBStatus getStatus() {
		return status;
	}

	public void setStatus(ZBStatus status) {
		this.status = status;
	}

	public List<String> getZrlb() {
		return zrlb;
	}

	public void setZrlb(List<String> zrlb) {
		this.zrlb = zrlb;
	}

	public List<String> getBhglx() {
		return bhglx;
	}

	public void setBhglx(List<String> bhglx) {
		this.bhglx = bhglx;
	}
	
}
