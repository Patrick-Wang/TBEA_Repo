package com.tbea.ic.operation.reportframe.component;

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

import com.tbea.ic.operation.common.DataNode;
import com.tbea.ic.operation.common.DataNode.Visitor;
import com.tbea.ic.operation.reportframe.component.ComponentLoader.ComponentLoadedListener;
import com.tbea.ic.operation.reportframe.component.controller.Controller;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.reportframe.component.service.Service;



public class ComponentManager implements ComponentLoadedListener {

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

	ComponentLoader loader;
	ComponentStructureBuilder csb = new ComponentStructureBuilder();
	Map<String, Config> serviceMap = Collections
			.synchronizedMap(new HashMap<String, Config>());
	Map<String, Config> controllerMap = Collections
			.synchronizedMap(new HashMap<String, Config>());

	public ComponentManager() {
 		loader = new ComponentLoader();
 		loader.addListener(this);
 		loader.addListener(csb);
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
			controllerMap.remove(node.getData().getValue());
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
			controllerMap.put(id,  new Config(e, path));
		}
	}

	public Controller getController(String id) {
		if (controllerMap.containsKey(id)){
			return new Controller(controllerMap.get(id).getE(), this);
		}
		return null;
	}
	
	public Controller getController(String id, Context local) {
		if (controllerMap.containsKey(id)){
			return new Controller(controllerMap.get(id).getE(), this, local);
		}
		return null;
	}
	
	private Service createService(Element e){
		return new Service(e, this);
	}
	
	public Service getService(String id, Context local){
		if (serviceMap.containsKey(id)){
			return new Service(serviceMap.get(id).getE(), this, local);
		}
		return null;
	}
	
	public Service getService(String id) {
		if (serviceMap.containsKey(id)){
			return createService(serviceMap.get(id).getE());
		}
		return null;
	}

	public DataNode getCSB(){
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
}
