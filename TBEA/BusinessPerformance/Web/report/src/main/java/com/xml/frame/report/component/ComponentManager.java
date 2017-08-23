package com.xml.frame.report.component;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.w3c.dom.Element;

import com.xml.frame.report.component.controller.Controller;
import com.xml.frame.report.component.controller.Scheduler;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.component.service.Service;
import com.xml.frame.report.util.DataNode;
import com.xml.frame.report.util.DataNode.Visitor;

public class ComponentManager implements ConfigLoadedListener {

	public static class Config{
		Element e;
		String path;
		public Element getE() {
			return e;
		}
		public void setE(Element e) {
			this.e = e;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public Config(Element e, String path) {
			super();
			this.e = e;
			this.path = path;
		}
	}

	ConfigLoader loader;
	ComponentStructureBuilder csb = new ComponentStructureBuilder();
	Scheduler scheduler;
	public static class ComponentJob implements Job{

		@Override
		public void execute(JobExecutionContext arg0)
				throws JobExecutionException {
			Controller controller = instance.createController(null, arg0.getJobDetail().getName());
			if (null != controller){
				try{
					instance.scheduler.onSchedule(new Context(), controller);
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}else{
				QuartzManager.removeJob(arg0.getJobDetail().getName());
				QuartzManager.startJobs();
			}
		}
	};
	
//	Map<Node, String> debugMap = Collections
//			.synchronizedMap(new HashMap<Node, String>());
	Map<String, Config> serviceMap = Collections
			.synchronizedMap(new HashMap<String, Config>());
	Map<String, Config> controllerMap = Collections
			.synchronizedMap(new HashMap<String, Config>());
	
	private static ComponentManager instance;
	
	public ComponentManager getInstance(){
		return instance;
	}
	
	public ComponentManager(Scheduler scheduler, String resPath) {
 		loader = new WatchConfigLoader(resPath);
 		instance = this;
 		loader.addListener(this);
 		loader.addListener(csb);
 		this.scheduler = scheduler;
		loader.load();
	}

	public boolean rename(String path, String oldname, String newname) {
		if (!oldname.equals(newname)) {
			File oldfile = new File(path + "/" + oldname);
			File newfile = new File(path + "/" + newname);
			return oldfile.renameTo(newfile);
		}
		return false;
	}

	private void clearComponent(DataNode node){
		if (node.getData().getId() == ComponentStructureBuilder.SERVICE){
			serviceMap.remove(node.getData().getValue());
		}else if (node.getData().getId() == ComponentStructureBuilder.CONTROLLER){
			if (controllerMap.containsKey(node.getData().getValue())){
				Element e = ((Config) controllerMap.remove(node.getData().getValue())).getE();
				if (e.hasAttribute("cron")){
					QuartzManager.removeJob(node.getData().getValue());
				}
			}
			
		}
	}
	
	private void clearComponents(List<DataNode> nodes){
		for (DataNode tmp : nodes){
			clearComponent(tmp);
		}
	}
	
	private boolean renameFile(String path, String newName){
		int index = path.lastIndexOf("\\");
		String relativePath = path.substring(0, index);
		if (rename(
				csb.getBasePath() + relativePath, 
				path.substring(index + 1), 
				newName)){
			DataNode node = csb.getNode(path);
			if (node != null && node.getSubNodes() != null){
				clearComponents(node.getSubNodes());
				node.getSubNodes().clear();
				node.getData().setValue(relativePath + "\\" + newName);
			}
			return true;
		}
		return false;
	}
	
	public boolean rename(String path, String newName, int type){
		loader.pause();
		boolean ret = false;
		switch (type){
		case ComponentStructureBuilder.FILE:
			ret = renameFile(path, newName);
			break;
		case ComponentStructureBuilder.FOLDER:
			ret = renameFolder(path, newName);
			break;
		case ComponentStructureBuilder.SERVICE:
			ret = renameService(path, newName);
			break;
		case ComponentStructureBuilder.CONTROLLER:
			ret = renameController(path, newName);
			break;
		}
		loader.load();
		return ret;
	}
	
	private boolean renameController(String id, String newName){
		if (controllerMap.containsKey(id)){
			return renameComponent(controllerMap.get(id), newName);
		}
		return false;
	}
	
	private boolean renameComponent(Config config, String newName){
		Element e = config.getE();
		e.setAttribute("id", newName);
		Source xmlSource = new DOMSource(e.getOwnerDocument());
		TransformerFactory factory = TransformerFactory.newInstance();  
		Transformer transformer;
		try {
			transformer = factory.newTransformer();
			Result result = new StreamResult(new File(config.getPath()));
			transformer.transform(xmlSource, result);
			return true;
		} catch (TransformerException e2) {
			e2.printStackTrace();
		}
		return false;
	}

	private boolean renameService(String id, String newName) {
		if (serviceMap.containsKey(id)){
			return renameComponent(serviceMap.get(id), newName);
		}
		return false;
	}

	private boolean renameFolder(String path, String newName) {
		int index = path.lastIndexOf("\\");
		String relativePath = path.substring(0, index);
		if (rename(
				csb.getBasePath() + relativePath, 
				path.substring(index + 1), 
				newName)){
			DataNode node = csb.getNode(path);
			if (node != null){
				node.accept(new Visitor(){
					@Override
					public boolean visit(DataNode node) {
						if (node.getData().getId() == ComponentStructureBuilder.CONTROLLER ||
							node.getData().getId() == ComponentStructureBuilder.SERVICE){
							clearComponent(node);
						}
						return true;
					}
					
				});
				node.getSubNodes().clear();
				node.getData().setValue(relativePath + "\\" + newName);
			}
			return true;
		}
		return false;
	}

	@Override
	public void onService(String id, Element e, String path) {
		if (null != id) {
			serviceMap.put(id, new Config(e, path));
		}
	}

	@Override
	public void onController(String id, Element e, String path) {
		if (null != id) {
			if (e.hasAttribute("cron")){
				QuartzManager.removeJob(id);
				QuartzManager.addJob(id, ComponentJob.class.getName(), 
						e.getAttribute("cron"));
			}
			controllerMap.put(id,  new Config(e, path));
		}
	}

	
	
	public Controller createController(AbstractXmlComponent preComponent,String id) {
		if (controllerMap.containsKey(id)){
			Element eCopy = null;
			synchronized(controllerMap.keySet()){
				eCopy = (Element) controllerMap.get(id).getE().cloneNode(true);
//				if (!debugMap.containsKey(controllerMap.get(id).getE())){
//					debugMap.put(controllerMap.get(id).getE(), XmlUtil.toStringFromDoc(eCopy));
//				}else{
//					String newCopyText = XmlUtil.toStringFromDoc(eCopy);
//					if (!debugMap.get(controllerMap.get(id).getE()).equals(newCopyText)){
//						ReportLogger.trace().info(debugMap.get(controllerMap.get(id).getE()));
//						ReportLogger.trace().info(newCopyText);
//					}
//				}
			}
			return new Controller(preComponent, eCopy, this);
		}
		return null;
	}
	
	public Controller createController(AbstractXmlComponent preComponent,String id, Context local) {
		if (controllerMap.containsKey(id)){
			Element eCopy = null;
			synchronized(controllerMap.keySet()){
				eCopy = (Element) controllerMap.get(id).getE().cloneNode(true);
//				if (!debugMap.containsKey(controllerMap.get(id).getE())){
//					debugMap.put(controllerMap.get(id).getE(), XmlUtil.toStringFromDoc(eCopy));
//				}else{
//					String newCopyText = XmlUtil.toStringFromDoc(eCopy);
//					if (!debugMap.get(controllerMap.get(id).getE()).equals(newCopyText)){
//						ReportLogger.trace().info(debugMap.get(controllerMap.get(id).getE()));
//						ReportLogger.trace().info(newCopyText);
//					}
//				}
			}
			return new Controller(preComponent, eCopy, this, local);
		}
		return null;
	}
	
	private Service createService(AbstractXmlComponent preComponent,Element e){
		return new Service(preComponent,e, this);
	}
	
	
	
	public Service createService(AbstractXmlComponent preComponent,String id, Context local){
		if (serviceMap.containsKey(id)){
			Element eCopy = null;
			synchronized(serviceMap.keySet()){
				eCopy = (Element) serviceMap.get(id).getE().cloneNode(true);
//				if (!debugMap.containsKey(serviceMap.get(id).getE())){
//					debugMap.put(serviceMap.get(id).getE(), XmlUtil.toStringFromDoc(eCopy));
//				}else{
//					String newCopyText = XmlUtil.toStringFromDoc(eCopy);
//					if (!debugMap.get(serviceMap.get(id).getE()).equals(newCopyText)){
//						ReportLogger.trace().info(debugMap.get(serviceMap.get(id).getE()));
//						ReportLogger.trace().info(newCopyText);
//					}
//				}
//				if (ReportLogger.trace().isDebugEnabled()){
//					ReportLogger.trace().debug(XmlUtil.toStringFromDoc(eCopy));
//				}
			}
			return new Service(preComponent,eCopy, this, local);
		}
		return null;
	}
	
	public Service createService(AbstractXmlComponent preComponent,String id) {
		if (serviceMap.containsKey(id)){
			Element eCopy = null;
			synchronized(serviceMap.keySet()){
				eCopy = (Element) serviceMap.get(id).getE().cloneNode(true);
//				if (!debugMap.containsKey(serviceMap.get(id).getE())){
//					debugMap.put(serviceMap.get(id).getE(), XmlUtil.toStringFromDoc(eCopy));
//				}else{
//					String newCopyText = XmlUtil.toStringFromDoc(eCopy);
//					if (!debugMap.get(serviceMap.get(id).getE()).equals(newCopyText)){
//						ReportLogger.trace().info(debugMap.get(serviceMap.get(id).getE()));
//						ReportLogger.trace().info(newCopyText);
//					}
//				}
			}
			return createService(preComponent, eCopy);
		}
		return null;
	}

	public DataNode getCSN(){
		return csb.getRootNode();
	}
	
	public Map<String, Config> getController() {
		return controllerMap;
	}

	@Override
	public void onEnterFolder(String filePath) {
		
	}

	@Override
	public void onExitFolder(String filePath) {
		
	}

	@Override
	public void onEnterFile(String filePath) {
		DataNode node = csb.getNode(filePath);
		if (node != null && node.getSubNodes() != null){
			clearComponents(node.getSubNodes());
			node.getSubNodes().clear();
		}
	}

	@Override
	public void onExitFile(String filePath) {
		
	}

	@Override
	public void onDeleteFile(String filePath) {
		DataNode node = csb.getNode(filePath);
		if (node != null && node.getSubNodes() != null){
			clearComponents(node.getSubNodes());
			csb.getParentNode(filePath).getSubNodes().remove(node);
		}
	}

	@Override
	public void onDeleteFolder(String folderPath) {
		DataNode node = csb.getNode(folderPath);
		if (node != null){
			node.accept(new Visitor(){
			
				@Override
				public boolean visit(DataNode node) {
					if (node.getData().getId() == ComponentStructureBuilder.SERVICE ||
						node.getData().getId() == ComponentStructureBuilder.CONTROLLER){
						clearComponent(node);
					}
					return true;
				}
				
			});
			csb.getParentNode(folderPath).getSubNodes().remove(node);
		}
	}
}