package com.page.core;

import com.androidquery.AQuery;
import com.squareup.otto.Bus;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class PageActivity extends Activity implements Callback {

	final static Integer hostId = 0x8955663;
	Bus bus = new Bus();
	
	AQuery query;
	Handler handler = new Handler(this);
	PctPixel pctPixel;

	public Bus bus() {
		return bus;
	}

	public Integer pct2Pixel(int pctId){
		String spct = getString(pctId).replace("px", "");
		Double pct = Double.valueOf(spct);
		return pctPixel.toPixel(pct.intValue());
	}
	
	public Integer hostId() {
		return hostId;
	}

	public AQuery query() {
		return query;
	}

	public Handler handler() {
		return handler;
	}

	public void delayed(Runnable run) {
		delayed(run, 1);
	}

	public void delayed(Runnable run, long time) {
		handler.postDelayed(run, time);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layoutMain = new LinearLayout(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		layoutMain.setLayoutParams(params);
		LinearLayout layoutHost = new LinearLayout(this);
		params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		layoutHost.setLayoutParams(params);
		layoutMain.addView(layoutHost);
		layoutHost.setId(hostId);
		setContentView(layoutMain);
		pctPixel = new PctPixel(this);
		query = new AQuery(this);
		coordinatePreProcess(layoutMain);
		Page page = onLoadFirstPage();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(hostId, page);
		ft.commit();
	}

	abstract protected Page onLoadFirstPage();

	public void coordinatePreProcess(View view ) {
		LayoutParams params = view.getLayoutParams();
		if (params != null) {
			params.width = pctPixel.toPixel(params.width);
			params.height = pctPixel.toPixel(params.height);
			if (params instanceof MarginLayoutParams){
				MarginLayoutParams mlp = (MarginLayoutParams) params;
				
				mlp.topMargin = pctPixel.toPixel(mlp.topMargin);
				mlp.bottomMargin = pctPixel.toPixel(mlp.bottomMargin);
				mlp.leftMargin = pctPixel.toPixel(mlp.leftMargin);
				mlp.rightMargin = pctPixel.toPixel(mlp.rightMargin);
			}
			view.setLayoutParams(params);
		}
		
		view.setPadding(
				pctPixel.toPixel(view.getPaddingLeft()), 
				pctPixel.toPixel(view.getPaddingTop()),
				pctPixel.toPixel(view.getPaddingRight()),
				pctPixel.toPixel(view.getPaddingBottom()));
		
		if (view instanceof TextView) {
			float size = ((TextView)view).getTextSize();
			size = pctPixel.toPixel((int) size);
			((TextView)view).setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		}
		
		if (view instanceof ViewGroup) {
			for (int i = ((ViewGroup) view).getChildCount() - 1; i >= 0; --i) {
				coordinatePreProcess(((ViewGroup) view).getChildAt(i));
			}
		}
	}

	@Override
	public boolean handleMessage(Message arg0) {
		return false;
	}
}
