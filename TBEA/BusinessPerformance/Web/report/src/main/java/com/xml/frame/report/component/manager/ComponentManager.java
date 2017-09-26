package com.xml.frame.report.component.manager;

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

import org.w3c.dom.Element;

import com.datasource.dynamic.DataSourceFactory;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.component.controller.Controller;
import com.xml.frame.report.component.controller.Scheduler;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.component.manager.loader.ConfigLoadedListener;
import com.xml.frame.report.component.manager.loader.ConfigLoader;
import com.xml.frame.report.component.manager.loader.WatchConfigLoader;
import com.xml.frame.report.component.service.Service;
import com.xml.frame.report.util.DataNode;
import com.xml.frame.report.util.DataNode.Visitor;
import com.xml.frame.report.util.QuartzManager;

public class ComponentManager implements ConfigLoadedListener {

	ComponentStructureBuilder csb = new ComponentStructureBuilder();
	ConfigLoader loader;
	Scheduler scheduler;
	
	Map<String, ComponentInfo> serviceMap = Collections
			.synchronizedMap(new HashMap<String, ComponentInfo>());
	Map<String, ComponentInfo> controllerMap = Collections
			.synchronizedMap(new HashMap<String, ComponentInfo>());

	DataSourceFactory dsFactory;
	
	private static ComponentManager instance;
	
	public static ComponentManager getInstance(){
		return instance;
	}
	
	public static ComponentManager create(Scheduler scheduler, String resPath, DataSourceFactory dsFactory) {
		if (instance == null) {
			instance = new ComponentManager(scheduler, resPath, dsFactory);
			return instance;
		}
		return null;
	}
	
	ComponentManager(Scheduler scheduler, String resPath, DataSourceFactory dsFactory) {
 		this.dsFactory = dsFactory;
 		this.scheduler = scheduler;
 		loader = new WatchConfigLoader(resPath);
 		loader.addListener(this);
 		loader.addListener(csb);
		loader.load();
	}

	public DataSourceFactory getDataSourceFactory() {
		return this.dsFactory;
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
				Element e = ((ComponentInfo) controllerMap.remove(node.getData().getValue())).getE();
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
	
	private boolean renameComponent(ComponentInfo config, String newName){
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
			if (serviceMap.containsKey(id)) {
				ComponentInfo ci = serviceMap.get(id);
				if (!ci.getPath().equals(path)) {
					System.out.println("service " + id + " of " + ci.getPath() +" is covered by " + path);
				}
			}
			serviceMap.put(id, new ComponentInfo(e, path));
		}
	}

	@Override
	public void onController(String id, Element e, String path) {
		if (null != id) {
			if (e.hasAttribute("cron")){
				QuartzManager.removeJob(id);
				QuartzManager.addJob(id, ScheduleJob.class.getName(), 
						e.getAttribute("cron"));
			}
			if (controllerMap.containsKey(id)) {
				ComponentInfo ci = controllerMap.get(id);
				if (!ci.getPath().equals(path)) {
					System.out.println("controller " + id + " of " + ci.getPath() +" is covered by " + path);
				}
			}
			controllerMap.put(id,  new ComponentInfo(e, path));
		}
	}

	Element securityClone(Map<String, ComponentInfo> map, String id) {
		if (map.containsKey(id)){
			Element eCopy = null;
			synchronized(map.keySet()){
				eCopy = (Element) map.get(id).getE().cloneNode(true);
			}
			return eCopy;
		}
		return null;
	}
	
	public Controller createController(AbstractXmlComponent preComponent,String id) {
		Element eCopy = securityClone(controllerMap, id);
		if (null != eCopy){
			return new Controller(preComponent, eCopy, this);
		}
		return null;
	}
	
	public Controller createController(AbstractXmlComponent preComponent,String id, Context local) {
		Element eCopy = securityClone(controllerMap, id);
		if (null != eCopy){
			return new Controller(preComponent, eCopy, this, local);
		}
		return null;
	}
		
	public Service createService(AbstractXmlComponent preComponent,String id, Context local){
		Element eCopy = securityClone(serviceMap, id);
		if (null != eCopy){
			return new Service(preComponent,eCopy, this, local);
		}
		return null;
	}
	
	public Service createService(AbstractXmlComponent preComponent,String id) {
		Element eCopy = securityClone(serviceMap, id);
		if (null != eCopy){
			return new Service(preComponent,eCopy, this);
		}
		return null;
	}
	
	public boolean isServiceNameOccupied(String servName) {
		return this.serviceMap.containsKey(servName);
	}
	
	public boolean isControllerNameOccupied(String clrName) {
		return this.controllerMap.containsKey(clrName);
	}

	public DataNode getCSN(){
		return csb.getRootNode();
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
