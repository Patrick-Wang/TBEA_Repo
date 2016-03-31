package com.tbea.ic.scanner.page;

import java.util.List;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.page.core.Page;
import com.tbea.ic.scaner.R;
import com.tbea.ic.scanner.net.Server;
import com.tbea.ic.scanner.net.entity.DataNode;

public class HomePage extends Page {

	private SubmitPage submitPage = new SubmitPage();
	
	@Override
	protected int onGetLayoutId() {
		return R.layout.home;
	}


	@SuppressLint("NewApi") @Override
	protected void onInitialize() {
		OnClickListener ocl = new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				DataNode node = (DataNode) v.getTag();
				LinearLayout ll = new LinearLayout(activity());
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				ll.setBackground(activity().getResources().getDrawable(R.drawable.boarder));
			    params.weight = 1.0f;
			    ll.setOrientation(LinearLayout.VERTICAL);
			    ll.setLayoutParams(params);
			    
			    final PopupWindow popWnd = new PopupWindow(ll,
		                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
			    
			    OnClickListener oclItem = new OnClickListener(){

					@Override
					public void onClick(View item) {
						DataNode itemNode = (DataNode) item.getTag();
						navigateTo(submitPage);
						popWnd.dismiss();
					}
			    	
			    };
			    
			    LinearLayout llview = null;
			    for (DataNode sub : node.getSubNodes()){
			    	 TextView view = new TextView(activity());
			    	 view.setText(sub.getData().getValue());
			    	 view.setTag(sub);
			    	 view.setTextSize(20);
			    	 view.setPadding(activity().pct2Pixel(R.pct.x2), activity().pct2Pixel(R.pct.x1), activity().pct2Pixel(R.pct.x2), activity().pct2Pixel(R.pct.x1));
			    	 LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			    	 view.setLayoutParams(param);
			    	 view.setMinimumWidth(activity().pct2Pixel(R.pct.x25));
			    	 view.setGravity(Gravity.CENTER_HORIZONTAL);
			    	 view.setOnClickListener(oclItem);
			    	 ll.addView(view);
			    	 
			    	 llview = new LinearLayout(activity());
			    	 llview.setBackground(activity().getResources().getDrawable(R.drawable.seperator));
			    	 param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 2);
			    	 llview.setLayoutParams(param);
			    	 ll.addView(llview);
			    }
			    
			    if (!node.getSubNodes().isEmpty()){
			    	ll.removeView(llview);
			    }
			    
			    
			    popWnd.setTouchable(true);
			    popWnd.setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
			    int left = v.getLeft();
			    int top = v.getBottom();
				popWnd.showAtLocation(query(R.id.main_content).getView(), Gravity.LEFT | Gravity.BOTTOM, left, top);
			}
		};

		List<DataNode> rights = Server.getInstance().getUser().getRights();

		RadioGroup group = (RadioGroup) query(R.id.tab_menu).getView();
		int height = activity().pct2Pixel(R.pct.y8);
		for (DataNode node : rights) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, height);
		    params.weight = 1.0f;
			RadioButton radio = new RadioButton(activity());
			group.addView(radio);
			radio.setText(node.getData().getValue());
			radio.setTag(node);
			radio.setTextSize(20);
			radio.setGravity(Gravity.BOTTOM);
			radio.setBackground(activity().getResources().getDrawable(R.drawable.cu_al_radio));
			radio.setButtonDrawable(android.R.color.transparent);
			radio.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
			radio.setLayoutParams(params);
			radio.setOnClickListener(ocl);
		}
	}
}
