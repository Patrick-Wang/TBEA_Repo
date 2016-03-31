package com.tbea.ic.scanner.page;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.page.core.Page;
import com.squareup.otto.Subscribe;
import com.tbea.ic.scaner.R;
import com.tbea.ic.scanner.page.camera.CameraPage;
import com.tbea.ic.scanner.page.camera.ScanEvent;

public class SubmitPage extends Page {

	@Override
	protected int onGetLayoutId() {
		return R.layout.submit;
	}

	@Subscribe
	public void onResult(ScanEvent event) {
		if (event.getResult() != null) {
			Toast.makeText(activity(), event.getResult().getText(),
					Toast.LENGTH_SHORT).show();
			query(R.id.seriesnumber).text(event.getResult().getText());
		}else{
			goBack();
		}
	}

	@Override
	protected void onInitialize() {
		query(R.id.submit).clicked(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				Toast.makeText(activity(), "提交 " + query(R.id.seriesnumber).getText(),
						Toast.LENGTH_SHORT).show(); 
			}
			
		});
		query(R.id.rescan).clicked(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				goBack();
			}
			
		});
		navigateTo(new CameraPage());
	}
}
