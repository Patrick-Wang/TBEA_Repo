package com.tbea.ic.scanner.page;

import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.page.core.Page;
import com.squareup.otto.Subscribe;
import com.tbea.ic.scaner.R;
import com.tbea.ic.scanner.page.camera.CameraPage;
import com.tbea.ic.scanner.page.camera.ScanEvent;

public class HomePage extends Page {

	SubmitPage web = new SubmitPage();

	@Override
	protected int onGetLayoutId() {
		return R.layout.home;
	}

//	@Subscribe
//	public void onResult(ScanEvent event){
//		if (event.getResult() != null){
//			Toast.makeText(activity(), event.getResult().getText(), Toast.LENGTH_SHORT).show();
//			query().id(R.id.test).text(event.getResult().getText());
//		}
//	}
	
	@Override
	protected void onInitialize() {
//		query().id(R.id.test1).clicked(new OnClickListener(){
//
//			@Override
//			public void onClick(View arg0) {
//				navigateTo(web);
//			}
//			
//		});
		OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View v){
                    PopupMenu popup = new PopupMenu(getActivity(), v);
                    //Inflating the Popup using xml file
                    popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
                   
       
                    //registering popup with OnMenuItemClickListener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                        	navigateTo(web);
                   
                            return true;
                        }
                    });
 
                    popup.show(); //showing popup menu 
            }
        };
		TextView moreMenu = query().id(R.id.rb1).getTextView();
	    moreMenu.setOnClickListener(ocl);
	    moreMenu = query().id(R.id.rb2).getTextView();
	    moreMenu.setOnClickListener(ocl);
	    moreMenu = query().id(R.id.rb3).getTextView();
	    moreMenu.setOnClickListener(ocl);
	    moreMenu = query().id(R.id.rb4).getTextView();
	    moreMenu.setOnClickListener(ocl);
	}
}
