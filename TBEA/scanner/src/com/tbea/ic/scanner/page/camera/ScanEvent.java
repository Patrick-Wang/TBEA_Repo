package com.tbea.ic.scanner.page.camera;

import com.google.zxing.Result;
import com.page.core.Page;

public class ScanEvent {
	Page page;
	Result result;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public ScanEvent(Page page, Result result) {
		super();
		this.page = page;
		this.result = result;
	}	
}
