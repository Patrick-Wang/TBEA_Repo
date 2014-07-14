package com.dataviewer;

import com.example.dataviewer.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
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

		Message message = new Message();
		message.what = 1;
		handler.sendMessageDelayed(message, 2000);
	}

	@Override
	public boolean handleMessage(Message arg0) {
		if (arg0.what == 1) {
			Drawable draw = null;
			findViewById(R.id.main_frame).setBackgroundDrawable(draw);
			findViewById(R.id.host).setVisibility(View.VISIBLE);
		}

		return false;
	}

}
