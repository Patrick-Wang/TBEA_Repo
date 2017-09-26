package com.xml.frame.report.component.manager.loader;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class TimerConfigLoader extends AbstractConfigLoader {

	public TimerConfigLoader(String resPath) {
		super(resPath);
		// TODO Auto-generated constructor stub
	}

	Timer timer = new Timer(true);
	
	public void load() {
		timer.schedule(new TimerTask() {
			public void run() {
				synchronized(timer){
					scan(new File(resPath));
				}
			}
		}, 0, 30 * 1000);
	}
	
	public void pause(){
		timer.cancel();
		synchronized(timer){
			
		}
	}
}
