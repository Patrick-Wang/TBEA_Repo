package com.dataviewer;

import com.tbea.dataviewer.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

public class MainActivity extends Activity implements Callback {

	Handler handler = new Handler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_main);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		FragmentTransaction ft = getFragmentManager()
				.beginTransaction();
		ft.add(R.id.host, new LoginPage());
		ft.commit();
		
		Message message = new Message();
		message.what = 100001;
		handler.sendMessageDelayed(message, 2000);
	}

	@Override
	public boolean handleMessage(Message arg0) {
		if (arg0.what == 100001) {
			findViewById(R.id.main_frame).getBackground().setCallback(null);
			findViewById(R.id.main_frame).setBackgroundDrawable(null);
			findViewById(R.id.host).setVisibility(View.VISIBLE);
		}

		return false;
	}

}
