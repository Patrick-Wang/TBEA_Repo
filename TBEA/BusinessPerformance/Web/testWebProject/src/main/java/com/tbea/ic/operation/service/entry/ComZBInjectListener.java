package com.tbea.ic.operation.service.entry;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.service.entry.zbInjector.ZBInjectListener;

public class ComZBInjectListener implements ZBInjectListener {


	Map<Long, ZBInjectListener> zbInjectListeners = Collections.synchronizedMap(new HashMap<Long, ZBInjectListener>());

	public void addListener(Long timeStamp, ZBInjectListener listener){
		zbInjectListeners.put(timeStamp, listener);
	}

	public void removeListener(Long timeStamp){
		zbInjectListeners.remove(timeStamp);
	}
	
	@Override
	public void onInjected(Integer zbId, double val, Calendar cal,
			Company comp, ZBStatus status, Calendar time) {

	    Set<Long> keys = zbInjectListeners.keySet();
	    synchronized (keys) {
	        Iterator<Long> i = keys.iterator(); // Must be in the synchronized block
	        while (i.hasNext()){
	        	zbInjectListeners.get(i.next()).onInjected(zbId, val, cal, comp, status, time);
	        }
	    }
	}

}
