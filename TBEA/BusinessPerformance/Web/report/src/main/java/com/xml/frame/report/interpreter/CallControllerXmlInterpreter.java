package com.xml.frame.report.interpreter;

import com.xml.frame.report.component.entity.Context;
import org.w3c.dom.Element;

import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.component.controller.Controller;
import sun.nio.ch.ThreadPool;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class CallControllerXmlInterpreter implements XmlInterpreter {

    static SimpleThreadPool stp = new SimpleThreadPool(8);
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isCallController(e)){
			return false;
		}
		
		String id = e.getAttribute("id");
		
		Controller controller = null;
		if ("true".equals(e.getAttribute("inline"))){
			controller = component.getCM().createController(component, id, component.localContext());
		}else{
			controller = component.getCM().createController(component, id);
		}
		
		if (null != controller){
		    if ("true".equals(e.getAttribute("background"))){
		        final Controller cont = controller;
		        final Context c = component.globalContext().clone();
                stp.runTask(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            cont.run(c);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                });
            }else{
                controller.run(component.globalContext());
            }

		}else{
			System.out.println("call controller " + id + " find failed");
		}
		return true;
	}
}