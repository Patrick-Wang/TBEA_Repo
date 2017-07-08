package com.tbea.ic.operation.common;

import java.util.ArrayList;
import java.util.List;

public class DataNode {
	
	public static interface Visitor{
		boolean visit(DataNode node);
	}
	
	private Data data;
	private DataNode parent;
	private List<DataNode> subNodes = new ArrayList<DataNode>();
	public DataNode(Data data) {
		super();
		this.data = data;
	}
	public DataNode() {
		super();
	}
	/**
	 * @return the data
	 */
	public Data getData() {
		return data;
	}
	/**
	 * @return the parent
	 */
	public DataNode getParent() {
		return parent;
	}
	/**
	 * @return the subNodes
	 */
	public List<DataNode> getSubNodes() {
		return subNodes;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Data data) {
		this.data = data;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(DataNode parent) {
		this.parent = parent;
	}
	/**
	 * @param subNodes the subNodes to set
	 */
	public void setSubNodes(List<DataNode> subNodes) {
		this.subNodes = subNodes;
	}
	
	public DataNode append(List<DataNode> subNodes) {
		this.subNodes.addAll(subNodes);
		return this;
	}
	
	public DataNode findByValue(String value){
		if (null != this.subNodes){
			for (DataNode node : this.subNodes){
				if (node.getData().getValue().equals(value)){
					return node;
				}
			}
		}
		return null;
	}
	
	public boolean accept(Visitor visitor){
		if (!visitor.visit(this)){
			return false;
		}
		if (null != this.subNodes){
			for (DataNode node : this.subNodes){
				if (!node.accept(visitor)){
					return false;
				}
			}
		}
		return true;
	}
}
