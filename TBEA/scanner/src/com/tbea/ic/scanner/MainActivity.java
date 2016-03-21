package com.tbea.ic.scanner;


import android.os.Bundle;
import com.page.core.Page;
import com.page.core.PageActivity;
import com.tbea.ic.scanner.net.Server;
import com.tbea.ic.scanner.page.SplashPage;

public class MainActivity extends PageActivity {

	@Override
	protected Page onLoadFirstPage() {
		return new SplashPage();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Server.resetAsLANServer(query());
	}


}
