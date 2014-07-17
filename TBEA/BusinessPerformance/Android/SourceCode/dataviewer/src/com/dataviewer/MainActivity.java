package com.dataviewer;

import com.tbea.dataviewer.R;

import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements Callback {

	Handler handler = new Handler(this);
	long exitTime = 0;
	String meterData = "";
	boolean bAllShow = false;
	final static String meterHeader = "";
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
		
		Message messagePerformance = new Message();
		messagePerformance.what = 100002;
		handler.sendMessage(messagePerformance);
		
		TextView tv = (TextView) findViewById(R.id.performance_meter);
		tv.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				bAllShow = !bAllShow;
			}
			
		});
//		Runnable ra = new Runnable(){
//
//			@Override
//			public void run() {
//				findViewById(R.id.al).getBackground().setCallback(null);				
//			}};
//		new Thread(ra).start();
		
	}

	@Override
	public boolean handleMessage(Message arg0) {
		switch(arg0.what){
		case 100001:
			if (null != findViewById(R.id.main_frame).getBackground()){
				findViewById(R.id.main_frame).getBackground().setCallback(null);
				findViewById(R.id.main_frame).setBackgroundDrawable(null);
			}
			findViewById(R.id.host).setVisibility(View.VISIBLE);
			break;
		case 100002:
			TextView tv = (TextView) findViewById(R.id.performance_meter);

			//应用程序最大可用内存  
			double maxMemory = ((double)(Runtime.getRuntime().maxMemory()/1024)) / 1024.0;  
	        //应用程序已获得内存  
			double totalMemory = ((double) (Runtime.getRuntime().totalMemory()/1024))/1024.0;  
	        //应用程序已获得内存中未使用内存  
			double freeMemory = ((double) (Runtime.getRuntime().freeMemory()/1024))/1024.0;
	
			
			Debug.MemoryInfo memoryInfo=new Debug.MemoryInfo();
			Debug.getMemoryInfo(memoryInfo);

			//应用程序最大可用内存  
			//double nativemaxMemory = ((double)(Debug.getNativeHeapSize()/1024)) / 1024.0;  
	        //应用程序已获得内存  
			double nativetotalMemory = ((double) (Debug.getNativeHeapAllocatedSize()/1024))/1024.0;  
	        //应用程序已获得内存中未使用内存  
			//double nativefreeMemory = ((double) (Debug.getNativeHeapFreeSize()/1024))/1024.0;

			
			String currentMeterData = "\tDalvik heap (used/max)\t : " 
					+ String.format("%8.3f", ((double) (totalMemory - freeMemory))) + "M / "
					+ maxMemory 
					+ "M\t\r\n\tDalvik Pss\t\t : "
					+ memoryInfo.dalvikPss / 1024.0
					+ "M\t\r\n\tNative Pss\t : " 
					+ ((double) memoryInfo.nativePss / 1024.0)
					+ "M\t\r\n\tTotal Pss\t\t : "
					+ ((double) memoryInfo.getTotalPss() / 1024.0)
					+ "M\t\r\n";
			meterData += currentMeterData;
			if (bAllShow){
				tv.setText(meterHeader + meterData);
			}
			else{
				tv.setText(meterHeader + currentMeterData);
			}
			
			
	   		Message messagePerformance = new Message();
			messagePerformance.what = 100002;
			handler.sendMessageDelayed(messagePerformance, 1000);
			break;
			default:
				break;
		}
		
		return false;
	}

	@Override
	public void onBackPressed() {
		if (0 == this.getFragmentManager().getBackStackEntryCount()){
			 if ((System.currentTimeMillis() - exitTime) > 2000) {
                 Toast.makeText(getApplicationContext(), "再按一次退出TBEA经营管理门户",
                         Toast.LENGTH_SHORT).show();
                 exitTime = System.currentTimeMillis();
             } else {
            	 System.exit(0);
             }
		}
		else {
			super.onBackPressed();
		}
	}

}
