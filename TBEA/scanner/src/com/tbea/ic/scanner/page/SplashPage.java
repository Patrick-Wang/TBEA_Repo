package com.tbea.ic.scanner.page;

import com.page.core.Page;
import com.tbea.ic.scaner.R;

public class SplashPage extends Page {

	@Override
	protected int onGetLayoutId() {
		return R.layout.splash;
	}

	@Override
	protected void onInitialize() {
		delayed(new Runnable(){

			@Override
			public void run() {
				leaveTo(new LoginPage());
			}
			
		}, 3000);
	}
}
