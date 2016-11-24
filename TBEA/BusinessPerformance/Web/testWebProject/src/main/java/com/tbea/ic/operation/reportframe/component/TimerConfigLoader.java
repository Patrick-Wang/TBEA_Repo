package com.tbea.ic.operation.reportframe.component;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class TimerConfigLoader extends AbstractConfigLoader {

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
